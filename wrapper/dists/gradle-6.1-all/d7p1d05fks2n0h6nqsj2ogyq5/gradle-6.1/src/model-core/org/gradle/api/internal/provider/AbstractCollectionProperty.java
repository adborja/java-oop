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

import com.google.common.base.Preconditions;
import org.gradle.api.Action;
import org.gradle.api.Task;
import org.gradle.api.internal.provider.Collectors.ElementFromProvider;
import org.gradle.api.internal.provider.Collectors.ElementsFromArray;
import org.gradle.api.internal.provider.Collectors.ElementsFromCollection;
import org.gradle.api.internal.provider.Collectors.ElementsFromCollectionProvider;
import org.gradle.api.internal.provider.Collectors.EmptyCollection;
import org.gradle.api.internal.provider.Collectors.NoValueCollector;
import org.gradle.api.internal.provider.Collectors.SingleElement;
import org.gradle.api.internal.tasks.TaskDependencyResolveContext;
import org.gradle.api.provider.HasMultipleValues;
import org.gradle.api.provider.Provider;
import org.gradle.internal.DisplayName;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractCollectionProperty<T, C extends Collection<T>> extends AbstractProperty<C> implements CollectionPropertyInternal<T, C> {
    private static final EmptyCollection EMPTY_COLLECTION = new EmptyCollection();
    private static final NoValueCollector NO_VALUE_COLLECTOR = new NoValueCollector();
    private final Class<? extends Collection> collectionType;
    private final Class<T> elementType;
    private final ValueCollector<T> valueCollector;
    private Collector<T> convention = (Collector<T>) NO_VALUE_COLLECTOR;
    private Collector<T> defaultValue = (Collector<T>) EMPTY_COLLECTION;
    private Collector<T> value;

    AbstractCollectionProperty(Class<? extends Collection> collectionType, Class<T> elementType) {
        applyDefaultValue();
        this.collectionType = collectionType;
        this.elementType = elementType;
        valueCollector = new ValidatingValueCollector<T>(collectionType, elementType, ValueSanitizers.forType(elementType));
    }

    @Override
    protected ValueSupplier getSupplier() {
        return value;
    }

    /**
     * Creates an immutable collection from the given current values of this property.
     */
    protected abstract C fromValue(Collection<T> values);

    @Override
    public void add(final T element) {
        Preconditions.checkNotNull(element, String.format("Cannot add a null element to a property of type %s.", collectionType.getSimpleName()));
        if (!beforeMutate()) {
            return;
        }
        addCollector(new SingleElement<T>(element));
    }

    @Override
    public void add(final Provider<? extends T> providerOfElement) {
        if (!beforeMutate()) {
            return;
        }
        addCollector(new ElementFromProvider<T>(Providers.internal(providerOfElement)));
    }

    @Override
    public void addAll(T... elements) {
        if (!beforeMutate()) {
            return;
        }
        addCollector(new ElementsFromArray<T>(elements));
    }

    @Override
    public void addAll(Iterable<? extends T> elements) {
        if (!beforeMutate()) {
            return;
        }
        addCollector(new ElementsFromCollection<T>(elements));
    }

    @Override
    public void addAll(Provider<? extends Iterable<? extends T>> provider) {
        if (!beforeMutate()) {
            return;
        }
        addCollector(new ElementsFromCollectionProvider<T>(Providers.internal(provider)));
    }

    private void addCollector(Collector<T> collector) {
        value = new PlusCollector<>(value, collector);
    }

    @Nullable
    @Override
    public Class<C> getType() {
        return (Class<C>) collectionType;
    }

    @Override
    public Class<T> getElementType() {
        return elementType;
    }

    /**
     * Unpacks this property into a list of element providers.
     */
    public List<ProviderInternal<? extends Iterable<? extends T>>> getProviders() {
        List<ProviderInternal<? extends Iterable<? extends T>>> sources = new ArrayList<>();
        value.visit(sources);
        return sources;
    }

    /**
     * Sets the value of this property the given list of element providers.
     */
    public void providers(List<ProviderInternal<? extends Iterable<? extends T>>> providers) {
        if (!beforeMutate()) {
            return;
        }
        value = defaultValue;
        for (ProviderInternal<? extends Iterable<? extends T>> provider : providers) {
            value = new PlusCollector<>(value, new ElementsFromCollectionProvider<>(provider));
        }
    }

    @Override
    public boolean isPresent() {
        beforeRead();
        return value.present();
    }

    @Override
    public C get() {
        beforeRead();
        List<T> values = new ArrayList<T>();
        value.collectInto(getDisplayName(), valueCollector, values);
        return fromValue(values);
    }

    @Nullable
    @Override
    public C getOrNull() {
        beforeRead();
        return doGetOrNull();
    }

    @Nullable
    private C doGetOrNull() {
        List<T> values = new ArrayList<T>();
        if (!value.maybeCollectInto(valueCollector, values)) {
            return null;
        }
        return fromValue(values);
    }

    @Override
    public void setFromAnyValue(Object object) {
        if (object instanceof Provider) {
            set((Provider<C>) object);
        } else {
            if (object != null && !(object instanceof Iterable)) {
                throw new IllegalArgumentException(String.format("Cannot set the value of a property of type %s using an instance of type %s.", collectionType.getName(), object.getClass().getName()));
            }
            set((Iterable<? extends T>) object);
        }
    }

    @Override
    public void set(@Nullable final Iterable<? extends T> elements) {
        if (elements == null) {
            if (beforeReset()) {
                set(convention);
                defaultValue = (Collector<T>) NO_VALUE_COLLECTOR;
            }
            return;
        }
        if (beforeMutate()) {
            set(new ElementsFromCollection<T>(elements));
        }
    }

    @Override
    public void set(final Provider<? extends Iterable<? extends T>> provider) {
        if (!beforeMutate()) {
            return;
        }
        if (provider == null) {
            throw new IllegalArgumentException("Cannot set the value of a property using a null provider.");
        }
        ProviderInternal<? extends Iterable<? extends T>> p = Providers.internal(provider);
        if (p.getType() != null && !Iterable.class.isAssignableFrom(p.getType())) {
            throw new IllegalArgumentException(String.format("Cannot set the value of a property of type %s using a provider of type %s.", collectionType.getName(), p.getType().getName()));
        }
        if (p instanceof CollectionPropertyInternal) {
            CollectionPropertyInternal<T, C> collectionProp = (CollectionPropertyInternal<T, C>) p;
            if (!elementType.isAssignableFrom(collectionProp.getElementType())) {
                throw new IllegalArgumentException(String.format("Cannot set the value of a property of type %s with element type %s using a provider with element type %s.", collectionType.getName(), elementType.getName(), collectionProp.getElementType().getName()));
            }
        }
        set(new ElementsFromCollectionProvider<T>(p));
    }

    @Override
    public HasMultipleValues<T> value(@Nullable Iterable<? extends T> elements) {
        set(elements);
        return this;
    }

    @Override
    public HasMultipleValues<T> value(Provider<? extends Iterable<? extends T>> provider) {
        set(provider);
        return this;
    }

    @Override
    public HasMultipleValues<T> empty() {
        if (!beforeMutate()) {
            return this;
        }
        set((Collector<T>) EMPTY_COLLECTION);
        return this;
    }

    @Override
    protected void applyDefaultValue() {
        value = defaultValue;
    }

    @Override
    protected void makeFinal() {
        C collection = doGetOrNull();
        if (collection != null) {
            set(new ElementsFromCollection<T>(collection));
        } else {
            set((Collector<T>) NO_VALUE_COLLECTOR);
        }
        convention = (Collector<T>) NO_VALUE_COLLECTOR;
    }

    private void set(Collector<T> collector) {
        value = collector;
    }

    @Override
    public HasMultipleValues<T> convention(Iterable<? extends T> elements) {
        convention(new ElementsFromCollection<T>(elements));
        return this;
    }

    @Override
    public HasMultipleValues<T> convention(Provider<? extends Iterable<? extends T>> provider) {
        convention(new ElementsFromCollectionProvider<T>(Providers.internal(provider)));
        return this;
    }

    private void convention(Collector<T> collector) {
        if (shouldApplyConvention()) {
            this.value = collector;
        }
        convention = collector;
    }

    @Override
    protected String describeContents() {
        return String.format("%s(%s, %s)", collectionType.getSimpleName().toLowerCase(), elementType, value.toString());
    }

    private static class PlusCollector<T> implements Collector<T> {
        private final Collector<T> left;
        private final Collector<T> right;

        public PlusCollector(Collector<T> left, Collector<T> right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean present() {
            return left.present() && right.present();
        }

        @Override
        public int size() {
            return left.size() + right.size();
        }

        @Override
        public void collectInto(DisplayName owner, ValueCollector<T> collector, Collection<T> dest) {
            left.collectInto(owner, collector, dest);
            right.collectInto(owner, collector, dest);
        }

        @Override
        public boolean maybeCollectInto(ValueCollector<T> collector, Collection<T> dest) {
            if (left.maybeCollectInto(collector, dest)) {
                return right.maybeCollectInto(collector, dest);
            }
            return false;
        }

        @Override
        public void visit(List<ProviderInternal<? extends Iterable<? extends T>>> sources) {
            left.visit(sources);
            right.visit(sources);
        }

        @Override
        public boolean maybeVisitBuildDependencies(TaskDependencyResolveContext context) {
            if (left.maybeVisitBuildDependencies(context)) {
                return right.maybeVisitBuildDependencies(context);
            }
            return false;
        }

        @Override
        public void visitProducerTasks(Action<? super Task> visitor) {
            left.visitProducerTasks(visitor);
            right.visitProducerTasks(visitor);
        }

        @Override
        public boolean isValueProducedByTask() {
            return left.isValueProducedByTask() || right.isValueProducedByTask();
        }
    }
}
