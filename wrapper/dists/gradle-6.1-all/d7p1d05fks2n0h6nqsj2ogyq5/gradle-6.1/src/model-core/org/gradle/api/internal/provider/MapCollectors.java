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
import com.google.common.collect.ImmutableMap;
import org.gradle.api.Action;
import org.gradle.api.Task;
import org.gradle.api.internal.tasks.TaskDependencyResolveContext;
import org.gradle.internal.DisplayName;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MapCollectors {

    public static class EmptyMap implements MapCollector<Object, Object> {

        @Override
        public boolean present() {
            return true;
        }

        @Override
        public void collectInto(DisplayName owner, MapEntryCollector<Object, Object> collector, Map<Object, Object> dest) {
        }

        @Override
        public boolean maybeCollectInto(MapEntryCollector<Object, Object> collector, Map<Object, Object> dest) {
            return true;
        }

        @Override
        public void collectKeysInto(ValueCollector<Object> collector, Collection<Object> dest) {
        }

        @Override
        public boolean maybeCollectKeysInto(ValueCollector<Object> collector, Collection<Object> dest) {
            return true;
        }

        @Override
        public void visit(List<ProviderInternal<? extends Map<?, ?>>> sources) {
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
    }

    public static class SingleEntry<K, V> implements MapCollector<K, V> {

        private final K key;
        private final V value;

        public SingleEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean present() {
            return true;
        }

        @Override
        public void collectInto(DisplayName owner, MapEntryCollector<K, V> collector, Map<K, V> dest) {
            maybeCollectInto(collector, dest);
        }

        @Override
        public boolean maybeCollectInto(MapEntryCollector<K, V> collector, Map<K, V> dest) {
            collector.add(key, value, dest);
            return true;
        }

        @Override
        public void collectKeysInto(ValueCollector<K> collector, Collection<K> dest) {
            collector.add(key, dest);
        }

        @Override
        public boolean maybeCollectKeysInto(ValueCollector<K> collector, Collection<K> dest) {
            collectKeysInto(collector, dest);
            return true;
        }

        @Override
        public void visit(List<ProviderInternal<? extends Map<? extends K, ? extends V>>> sources) {
            sources.add(Providers.of(ImmutableMap.of(key, value)));
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
            SingleEntry<?, ?> that = (SingleEntry<?, ?>) o;
            return Objects.equal(key, that.key) && Objects.equal(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key, value);
        }
    }

    public static class EntryWithValueFromProvider<K, V> implements MapCollector<K, V> {

        private final K key;
        private final ProviderInternal<? extends V> providerOfValue;

        public EntryWithValueFromProvider(K key, ProviderInternal<? extends V> providerOfValue) {
            this.key = key;
            this.providerOfValue = providerOfValue;
        }

        @Override
        public boolean present() {
            return providerOfValue.isPresent();
        }

        @Override
        public void collectInto(DisplayName owner, MapEntryCollector<K, V> collector, Map<K, V> dest) {
            collector.add(key, providerOfValue.get(), dest);
        }

        @Override
        public boolean maybeCollectInto(MapEntryCollector<K, V> collector, Map<K, V> dest) {
            V value = providerOfValue.getOrNull();
            if (value != null) {
                collector.add(key, value, dest);
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void collectKeysInto(ValueCollector<K> collector, Collection<K> dest) {
            if (providerOfValue.isPresent()) {
                collector.add(key, dest);
            } else {
                throw new IllegalStateException(Providers.NULL_VALUE);
            }
        }

        @Override
        public boolean maybeCollectKeysInto(ValueCollector<K> collector, Collection<K> dest) {
            if (providerOfValue.isPresent()) {
                collector.add(key, dest);
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void visit(List<ProviderInternal<? extends Map<? extends K, ? extends V>>> sources) {
            sources.add(providerOfValue.map(v -> ImmutableMap.of(key, v)));
        }

        @Override
        public boolean maybeVisitBuildDependencies(TaskDependencyResolveContext context) {
            return providerOfValue.maybeVisitBuildDependencies(context);
        }

        @Override
        public void visitProducerTasks(Action<? super Task> visitor) {
            providerOfValue.visitProducerTasks(visitor);
        }

        @Override
        public boolean isValueProducedByTask() {
            return providerOfValue.isValueProducedByTask();
        }
    }

    public static class EntriesFromMap<K, V> implements MapCollector<K, V> {

        private final Map<? extends K, ? extends V> entries;

        public EntriesFromMap(Map<? extends K, ? extends V> entries) {
            this.entries = entries;
        }

        @Override
        public boolean present() {
            return true;
        }

        @Override
        public void collectInto(DisplayName owner, MapEntryCollector<K, V> collector, Map<K, V> dest) {
            maybeCollectInto(collector, dest);
        }

        @Override
        public boolean maybeCollectInto(MapEntryCollector<K, V> collector, Map<K, V> dest) {
            collector.addAll(entries.entrySet(), dest);
            return true;
        }

        @Override
        public void collectKeysInto(ValueCollector<K> collector, Collection<K> dest) {
            collector.addAll(entries.keySet(), dest);
        }

        @Override
        public boolean maybeCollectKeysInto(ValueCollector<K> collector, Collection<K> dest) {
            collectKeysInto(collector, dest);
            return true;
        }

        @Override
        public void visit(List<ProviderInternal<? extends Map<? extends K, ? extends V>>> sources) {
            sources.add(Providers.of(entries));
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
    }

    public static class EntriesFromMapProvider<K, V> implements MapCollector<K, V> {

        private final ProviderInternal<? extends Map<? extends K, ? extends V>> providerOfEntries;

        public EntriesFromMapProvider(ProviderInternal<? extends Map<? extends K, ? extends V>> providerOfEntries) {
            this.providerOfEntries = providerOfEntries;
        }

        @Override
        public boolean present() {
            return providerOfEntries.isPresent();
        }

        @Override
        public void collectInto(DisplayName owner, MapEntryCollector<K, V> collector, Map<K, V> dest) {
            collector.addAll(providerOfEntries.get().entrySet(), dest);
        }

        @Override
        public boolean maybeCollectInto(MapEntryCollector<K, V> collector, Map<K, V> dest) {
            Map<? extends K, ? extends V> entries = providerOfEntries.getOrNull();
            if (entries != null) {
                collector.addAll(entries.entrySet(), dest);
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void collectKeysInto(ValueCollector<K> collector, Collection<K> dest) {
            collector.addAll(providerOfEntries.get().keySet(), dest);
        }

        @Override
        public boolean maybeCollectKeysInto(ValueCollector<K> collector, Collection<K> dest) {
            Map<? extends K, ? extends V> entries = providerOfEntries.getOrNull();
            if (entries != null) {
                collector.addAll(entries.keySet(), dest);
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void visit(List<ProviderInternal<? extends Map<? extends K, ? extends V>>> sources) {
            sources.add(providerOfEntries);
        }

        @Override
        public boolean maybeVisitBuildDependencies(TaskDependencyResolveContext context) {
            return providerOfEntries.maybeVisitBuildDependencies(context);
        }

        @Override
        public void visitProducerTasks(Action<? super Task> visitor) {
            providerOfEntries.visitProducerTasks(visitor);
        }

        @Override
        public boolean isValueProducedByTask() {
            return providerOfEntries.isValueProducedByTask();
        }
    }

    public static class NoValue implements MapCollector<Object, Object> {

        @Override
        public boolean present() {
            return false;
        }

        @Override
        public void collectInto(DisplayName owner, MapEntryCollector<Object, Object> collector, Map<Object, Object> dest) {
            throw Providers.nullValue(owner);
        }

        @Override
        public boolean maybeCollectInto(MapEntryCollector<Object, Object> collector, Map<Object, Object> dest) {
            return false;
        }

        @Override
        public void collectKeysInto(ValueCollector<Object> collector, Collection<Object> dest) {
            throw new IllegalStateException(Providers.NULL_VALUE);
        }

        @Override
        public boolean maybeCollectKeysInto(ValueCollector<Object> collector, Collection<Object> dest) {
            return false;
        }

        @Override
        public void visit(List<ProviderInternal<? extends Map<?, ?>>> sources) {
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
    }
}
