/*
 * Copyright 2018 the original author or authors.
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

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.gradle.api.Action;
import org.gradle.api.Task;
import org.gradle.api.internal.tasks.TaskDependencyResolveContext;
import org.gradle.api.provider.Provider;
import org.gradle.internal.DisplayName;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public class Collectors {
    public interface ProvidedCollector<T> extends Collector<T> {
        boolean isProvidedBy(Provider<?> provider);
    }

    public static class EmptyCollection implements Collector<Object> {
        @Override
        public boolean present() {
            return true;
        }

        @Override
        public boolean maybeCollectInto(ValueCollector<Object> collector, Collection<Object> collection) {
            return true;
        }

        @Override
        public void collectInto(DisplayName owner, ValueCollector<Object> collector, Collection<Object> dest) {
        }

        @Override
        public void visit(List<ProviderInternal<? extends Iterable<?>>> sources) {
        }

        @Override
        public boolean maybeVisitBuildDependencies(TaskDependencyResolveContext context) {
            return true;
        }

        @Override
        public void visitProducerTasks(Action<? super Task> visitor) {
        }

        @Override
        public boolean isValueProducedByTask() {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }
    }

    public static class SingleElement<T> implements Collector<T> {
        private final T element;

        public SingleElement(T element) {
            this.element = element;
        }

        @Override
        public boolean present() {
            return true;
        }

        @Override
        public void collectInto(DisplayName owner, ValueCollector<T> collector, Collection<T> collection) {
            collector.add(element, collection);
        }

        @Override
        public boolean maybeCollectInto(ValueCollector<T> collector, Collection<T> collection) {
            collector.add(element, collection);
            return true;
        }

        @Override
        public void visit(List<ProviderInternal<? extends Iterable<? extends T>>> sources) {
            sources.add(Providers.of(ImmutableList.of(element)));
        }

        @Override
        public boolean maybeVisitBuildDependencies(TaskDependencyResolveContext context) {
            return false;
        }

        @Override
        public void visitProducerTasks(Action<? super Task> visitor) {
        }

        @Override
        public boolean isValueProducedByTask() {
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SingleElement<?> that = (SingleElement<?>) o;
            return Objects.equal(element, that.element);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(element);
        }

        @Override
        public int size() {
            return 1;
        }
    }

    public static class ElementFromProvider<T> implements ProvidedCollector<T> {
        private final ProviderInternal<? extends T> providerOfElement;

        public ElementFromProvider(ProviderInternal<? extends T> providerOfElement) {
            this.providerOfElement = providerOfElement;
        }

        @Override
        public boolean present() {
            return providerOfElement.isPresent();
        }

        @Override
        public void collectInto(DisplayName owner, ValueCollector<T> collector, Collection<T> collection) {
            T value = providerOfElement.get();
            collector.add(value, collection);
        }

        @Override
        public boolean maybeCollectInto(ValueCollector<T> collector, Collection<T> collection) {
            T value = providerOfElement.getOrNull();
            if (value == null) {
                return false;
            }
            collector.add(value, collection);
            return true;
        }

        @Override
        public boolean isProvidedBy(Provider<?> provider) {
            return Objects.equal(provider, providerOfElement);
        }

        @Override
        public void visit(List<ProviderInternal<? extends Iterable<? extends T>>> sources) {
            sources.add(providerOfElement.map(e -> ImmutableList.of(e)));
        }

        @Override
        public boolean maybeVisitBuildDependencies(TaskDependencyResolveContext context) {
            return providerOfElement.maybeVisitBuildDependencies(context);
        }

        @Override
        public void visitProducerTasks(Action<? super Task> visitor) {
            providerOfElement.visitProducerTasks(visitor);
        }

        @Override
        public boolean isValueProducedByTask() {
            return providerOfElement.isValueProducedByTask();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ElementFromProvider<?> that = (ElementFromProvider<?>) o;
            return Objects.equal(providerOfElement, that.providerOfElement);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(providerOfElement);
        }

        @Override
        public int size() {
            return 1;
        }
    }

    public static class ElementsFromCollection<T> implements Collector<T> {
        private final Iterable<? extends T> value;

        public ElementsFromCollection(Iterable<? extends T> value) {
            this.value = value;
        }

        @Override
        public boolean present() {
            return true;
        }

        @Override
        public void collectInto(DisplayName owner, ValueCollector<T> collector, Collection<T> collection) {
            collector.addAll(value, collection);
        }

        @Override
        public boolean maybeCollectInto(ValueCollector<T> collector, Collection<T> collection) {
            collector.addAll(value, collection);
            return true;
        }

        @Override
        public void visit(List<ProviderInternal<? extends Iterable<? extends T>>> sources) {
            sources.add(Providers.of(value));
        }

        @Override
        public boolean maybeVisitBuildDependencies(TaskDependencyResolveContext context) {
            return false;
        }

        @Override
        public void visitProducerTasks(Action<? super Task> visitor) {
        }

        @Override
        public boolean isValueProducedByTask() {
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ElementsFromCollection<?> that = (ElementsFromCollection<?>) o;
            return Objects.equal(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }

        @Override
        public int size() {
            return Iterables.size(value);
        }
    }

    public static class ElementsFromCollectionProvider<T> implements ProvidedCollector<T> {
        private final ProviderInternal<? extends Iterable<? extends T>> provider;

        public ElementsFromCollectionProvider(ProviderInternal<? extends Iterable<? extends T>> provider) {
            this.provider = provider;
        }

        @Override
        public boolean present() {
            return provider.isPresent();
        }

        @Override
        public void collectInto(DisplayName owner, ValueCollector<T> collector, Collection<T> collection) {
            Iterable<? extends T> value = provider.get();
            collector.addAll(value, collection);
        }

        @Override
        public boolean maybeCollectInto(ValueCollector<T> collector, Collection<T> collection) {
            Iterable<? extends T> value = provider.getOrNull();
            if (value == null) {
                return false;
            }
            collector.addAll(value, collection);
            return true;
        }

        @Override
        public void visit(List<ProviderInternal<? extends Iterable<? extends T>>> sources) {
            sources.add(provider);
        }

        @Override
        public boolean maybeVisitBuildDependencies(TaskDependencyResolveContext context) {
            return provider.maybeVisitBuildDependencies(context);
        }

        @Override
        public void visitProducerTasks(Action<? super Task> visitor) {
            provider.visitProducerTasks(visitor);
        }

        @Override
        public boolean isValueProducedByTask() {
            return provider.isValueProducedByTask();
        }

        @Override
        public boolean isProvidedBy(Provider<?> provider) {
            return Objects.equal(provider, provider);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ElementsFromCollectionProvider<?> that = (ElementsFromCollectionProvider<?>) o;
            return Objects.equal(provider, that.provider);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(provider);
        }

        @Override
        public int size() {
            if (provider instanceof CollectionProviderInternal) {
                return ((CollectionProviderInternal) provider).size();
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }

    public static class NoValueCollector implements Collector<Object> {
        @Override
        public boolean present() {
            return false;
        }

        @Override
        public void collectInto(DisplayName owner, ValueCollector<Object> collector, Collection<Object> dest) {
            throw Providers.nullValue(owner);
        }

        @Override
        public boolean maybeCollectInto(ValueCollector<Object> collector, Collection<Object> collection) {
            return false;
        }

        @Override
        public void visit(List<ProviderInternal<? extends Iterable<?>>> sources) {
        }

        @Override
        public boolean maybeVisitBuildDependencies(TaskDependencyResolveContext context) {
            return true;
        }

        @Override
        public void visitProducerTasks(Action<? super Task> visitor) {
        }

        @Override
        public boolean isValueProducedByTask() {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }
    }

    public static class ElementsFromArray<T> implements Collector<T> {
        private final T[] value;

        ElementsFromArray(T[] value) {
            this.value = value;
        }

        @Override
        public boolean present() {
            return true;
        }

        @Override
        public void collectInto(DisplayName owner, ValueCollector<T> collector, Collection<T> dest) {
            for (T t : value) {
                collector.add(t, dest);
            }
        }

        @Override
        public boolean maybeCollectInto(ValueCollector<T> collector, Collection<T> dest) {
            collectInto(null, collector, dest);
            return true;
        }

        @Override
        public void visit(List<ProviderInternal<? extends Iterable<? extends T>>> sources) {
            sources.add(Providers.of(ImmutableList.copyOf(value)));
        }

        @Override
        public boolean maybeVisitBuildDependencies(TaskDependencyResolveContext context) {
            return false;
        }

        @Override
        public void visitProducerTasks(Action<? super Task> visitor) {
        }

        @Override
        public boolean isValueProducedByTask() {
            return false;
        }

        @Override
        public int size() {
            return value.length;
        }
    }

    public static class TypedCollector<T> implements ProvidedCollector<T> {
        private final Class<? extends T> type;
        protected final Collector<T> delegate;
        private final ValueCollector<T> valueCollector;

        public TypedCollector(@Nullable Class<? extends T> type, Collector<T> delegate) {
            this.type = type;
            this.delegate = delegate;
            this.valueCollector = ValueSanitizers.collectorFor(type);
        }

        @Nullable
        public Class<? extends T> getType() {
            return type;
        }

        @Override
        public boolean present() {
            return delegate.present();
        }

        public void collectInto(Collection<T> collection) {
            delegate.collectInto(null, valueCollector, collection);
        }

        @Override
        public void collectInto(DisplayName owner, ValueCollector<T> collector, Collection<T> dest) {
            delegate.collectInto(owner, collector, dest);
        }

        @Override
        public boolean maybeCollectInto(ValueCollector<T> collector, Collection<T> dest) {
            return delegate.maybeCollectInto(collector, dest);
        }

        @Override
        public boolean isProvidedBy(Provider<?> provider) {
            return delegate instanceof ProvidedCollector && ((ProvidedCollector<T>) delegate).isProvidedBy(provider);
        }

        @Override
        public void visit(List<ProviderInternal<? extends Iterable<? extends T>>> sources) {
            delegate.visit(sources);
        }

        @Override
        public boolean maybeVisitBuildDependencies(TaskDependencyResolveContext context) {
            return delegate.maybeVisitBuildDependencies(context);
        }

        @Override
        public void visitProducerTasks(Action<? super Task> visitor) {
            delegate.visitProducerTasks(visitor);
        }

        @Override
        public boolean isValueProducedByTask() {
            return delegate.isValueProducedByTask();
        }

        @Override
        public int size() {
            return delegate.size();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TypedCollector<?> that = (TypedCollector<?>) o;
            return Objects.equal(type, that.type) &&
                Objects.equal(delegate, that.delegate);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(type, delegate);
        }
    }
}
