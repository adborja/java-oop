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

import org.gradle.api.Action;
import org.gradle.api.file.RegularFile;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.internal.provider.sources.EnvironmentVariableValueSource;
import org.gradle.api.internal.provider.sources.FileBytesValueSource;
import org.gradle.api.internal.provider.sources.FileTextValueSource;
import org.gradle.api.internal.provider.sources.SystemPropertyValueSource;
import org.gradle.api.file.FileContents;
import org.gradle.api.provider.Provider;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.provider.ValueSource;
import org.gradle.api.provider.ValueSourceParameters;
import org.gradle.api.provider.ValueSourceSpec;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;

public class DefaultProviderFactory implements ProviderFactory {

    @Nullable
    private final ValueSourceProviderFactory valueSourceProviderFactory;

    public DefaultProviderFactory() {
        this(null);
    }

    public DefaultProviderFactory(@Nullable ValueSourceProviderFactory valueSourceProviderFactory) {
        this.valueSourceProviderFactory = valueSourceProviderFactory;
    }

    @Override
    public <T> Provider<T> provider(final Callable<? extends T> value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        return new DefaultProvider<T>(value);
    }

    @Override
    public Provider<String> environmentVariable(String variableName) {
        return environmentVariable(Providers.of(variableName));
    }

    @Override
    public Provider<String> environmentVariable(Provider<String> variableName) {
        return of(
            EnvironmentVariableValueSource.class,
            spec -> spec.getParameters().getVariableName().set(variableName)
        );
    }

    @Override
    public Provider<String> systemProperty(String propertyName) {
        return systemProperty(Providers.of(propertyName));
    }

    @Override
    public Provider<String> systemProperty(Provider<String> propertyName) {
        return of(
            SystemPropertyValueSource.class,
            spec -> spec.getParameters().getPropertyName().set(propertyName)
        );
    }

    @Override
    public FileContents fileContents(RegularFile file) {
        return fileContents(property -> property.set(file));
    }

    @Override
    public FileContents fileContents(Provider<RegularFile> file) {
        return fileContents(property -> property.set(file));
    }

    private FileContents fileContents(Action<RegularFileProperty> setFileProperty) {
        return new FileContents() {
            @Override
            public Provider<String> getAsText() {
                return of(
                    FileTextValueSource.class,
                    spec -> setFileProperty.execute(spec.getParameters().getFile())
                );
            }

            @Override
            public Provider<byte[]> getAsBytes() {
                return of(
                    FileBytesValueSource.class,
                    spec -> setFileProperty.execute(spec.getParameters().getFile())
                );
            }
        };
    }

    @Override
    public <T, P extends ValueSourceParameters> Provider<T> of(Class<? extends ValueSource<T, P>> valueSourceType, Action<? super ValueSourceSpec<P>> configuration) {
        if (valueSourceProviderFactory == null) {
            throw new UnsupportedOperationException();
        }
        return valueSourceProviderFactory.createProviderOf(valueSourceType, configuration);
    }
}
