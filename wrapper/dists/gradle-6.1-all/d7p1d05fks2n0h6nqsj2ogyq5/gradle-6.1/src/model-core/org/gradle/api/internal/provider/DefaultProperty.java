/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.internal.provider;

import org.gradle.api.provider.Property;
import org.gradle.api.provider.Provider;

import javax.annotation.Nullable;

public class DefaultProperty<T> extends AbstractProperty<T> implements Property<T> {
    private final Class<T> type;
    private final ValueSanitizer<T> sanitizer;
    private ScalarSupplier<? extends T> convention = Providers.noValue();
    private ScalarSupplier<? extends T> value;

    public DefaultProperty(Class<T> type) {
        applyDefaultValue();
        this.type = type;
        this.sanitizer = ValueSanitizers.forType(type);
    }

    @Override
    protected ValueSupplier getSupplier() {
        return value;
    }

    @Override
    public Object unpackState() {
        return getProvider();
    }

    @Override
    public Class<?> publicType() {
        return Property.class;
    }

    @Override
    public int getFactoryId() {
        return ManagedFactories.PropertyManagedFactory.FACTORY_ID;
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public void setFromAnyValue(Object object) {
        if (object instanceof Provider) {
            set((Provider<T>) object);
        } else {
            set((T) object);
        }
    }

    @Override
    public void set(T value) {
        if (value == null) {
            if (beforeReset()) {
                this.value = convention;
            }
            return;
        }

        if (beforeMutate()) {
            this.value = Providers.fixedValue(getValidationDisplayName(), value, type, sanitizer);
        }
    }

    @Override
    public Property<T> value(@Nullable T value) {
        set(value);
        return this;
    }

    @Override
    public Property<T> value(Provider<? extends T> provider) {
        set(provider);
        return this;
    }

    public ProviderInternal<? extends T> getProvider() {
        return value.asProvider();
    }

    public DefaultProperty<T> provider(Provider<? extends T> provider) {
        set(provider);
        return this;
    }

    @Override
    public void set(Provider<? extends T> provider) {
        if (!beforeMutate()) {
            return;
        }
        if (provider == null) {
            throw new IllegalArgumentException("Cannot set the value of a property using a null provider.");
        }
        ProviderInternal<? extends T> p = Providers.internal(provider);
        this.value = p.asSupplier(getValidationDisplayName(), type, sanitizer);
    }

    @Override
    public Property<T> convention(T value) {
        if (value == null) {
            applyConvention(Providers.noValue());
        } else {
            applyConvention(Providers.fixedValue(getValidationDisplayName(), value, type, sanitizer));
        }
        return this;
    }

    @Override
    public Property<T> convention(Provider<? extends T> valueProvider) {
        ProviderInternal<? extends T> providerInternal = Providers.internal(valueProvider);
        ScalarSupplier<? extends T> conventionSupplier = providerInternal.asSupplier(getValidationDisplayName(), type, sanitizer);
        applyConvention(conventionSupplier);
        return this;
    }

    private void applyConvention(ScalarSupplier<? extends T> conventionSupplier) {
        if (shouldApplyConvention()) {
            this.value = conventionSupplier;
        }
        this.convention = conventionSupplier;
    }

    @Override
    protected void applyDefaultValue() {
        value = Providers.noValue();
    }

    @Override
    protected void makeFinal() {
        value = value.withFinalValue();
        convention = Providers.noValue();
    }

    @Override
    public T get() {
        beforeRead();
        return value.get(getDisplayName());
    }

    @Override
    public T getOrNull() {
        beforeRead();
        return value.getOrNull();
    }

    @Override
    public T getOrElse(T defaultValue) {
        beforeRead();
        T t = value.getOrNull();
        if (t == null) {
            return defaultValue;
        }
        return t;
    }

    @Override
    public boolean isPresent() {
        beforeRead();
        return value.isPresent();
    }

    @Override
    protected String describeContents() {
        // NOTE: Do not realize the value of the Provider in toString().  The debugger will try to call this method and make debugging really frustrating.
        return String.format("property(%s, %s)", type, value);
    }
}
