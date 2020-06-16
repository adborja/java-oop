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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.gradle.api.Action;
import org.gradle.api.Task;
import org.gradle.api.internal.tasks.TaskDependencyResolveContext;
import org.gradle.api.provider.MapProperty;
import org.gradle.api.provider.Provider;
import org.gradle.internal.Cast;
import org.gradle.internal.DisplayName;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class DefaultMapProperty<K, V> extends AbstractProperty<Map<K, V>> implements MapProperty<K, V>, MapProviderInternal<K, V> {

    private static final MapCollectors.EmptyMap EMPTY_MAP = new MapCollectors.EmptyMap();
    private static final MapCollectors.NoValue NO_VALUE = new MapCollectors.NoValue();

    private static final String NULL_KEY_FORBIDDEN_MESSAGE = String.format("Cannot add an entry with a null key to a property of type %s.", Map.class.getSimpleName());
    private static final String NULL_VALUE_FORBIDDEN_MESSAGE = String.format("Cannot add an entry with a null value to a property of type %s.", Map.class.getSimpleName());

    private final Class<K> keyType;
    private final Class<V> valueType;
    private final ValueCollector<K> keyCollector;
    private final MapEntryCollector<K, V> entryCollector;
    private MapCollector<K, V> convention = Cast.uncheckedCast(NO_VALUE);
    private MapCollector<K, V> defaultValue = Cast.uncheckedCast(EMPTY_MAP);
    private MapCollector<K, V> value;

    public DefaultMapProperty(Class<K> keyType, Class<V> valueType) {
        applyDefaultValue();
        this.keyType = keyType;
        this.valueType = valueType;
        keyCollector = new ValidatingValueCollector<>(Set.class, keyType, ValueSanitizers.forType(keyType));
        entryCollector = new ValidatingMapEntryCollector<>(keyType, valueType, ValueSanitizers.forType(keyType), ValueSanitizers.forType(valueType));
    }

    @Override
    protected ValueSupplier getSupplier() {
        return value;
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public Class<Map<K, V>> getType() {
        return (Class) Map.class;
    }

    @Override
    public Class<K> getKeyType() {
        return keyType;
    }

    @Override
    public Class<V> getValueType() {
        return valueType;
    }

    @Override
    public Class<?> publicType() {
        return MapProperty.class;
    }

    @Override
    public int getFactoryId() {
        return ManagedFactories.MapPropertyManagedFactory.FACTORY_ID;
    }

    @Override
    public boolean isPresent() {
        beforeRead();
        return value.present();
    }

    @Override
    public Map<K, V> get() {
        beforeRead();
        Map<K, V> entries = new LinkedHashMap<>();
        value.collectInto(getDisplayName(), entryCollector, entries);
        return ImmutableMap.copyOf(entries);
    }

    @Nullable
    @Override
    public Map<K, V> getOrNull() {
        beforeRead();
        return doGetOrNull();
    }

    @Nullable
    private Map<K, V> doGetOrNull() {
        Map<K, V> entries = new LinkedHashMap<>();
        if (!value.maybeCollectInto(entryCollector, entries)) {
            return null;
        }
        return ImmutableMap.copyOf(entries);
    }

    @Override
    public Provider<V> getting(final K key) {
        return new DefaultProvider<>(new Callable<V>() {
            @Override
            @Nullable
            public V call() {
                beforeRead();
                Map<K, V> dest = new LinkedHashMap<>();
                if (value.maybeCollectInto(entryCollector, dest)) {
                    return dest.get(key);
                }
                return null;
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public MapProperty<K, V> empty() {
        if (beforeMutate()) {
            set((MapCollector<K, V>) EMPTY_MAP);
        }
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setFromAnyValue(@Nullable Object object) {
        if (object == null || object instanceof Map<?, ?>) {
            set((Map) object);
        } else if (object instanceof Provider<?>) {
            set((Provider) object);
        } else {
            throw new IllegalArgumentException(String.format(
                "Cannot set the value of a property of type %s using an instance of type %s.", Map.class.getName(), object.getClass().getName()));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void set(@Nullable Map<? extends K, ? extends V> entries) {
        if (entries == null) {
            if (beforeReset()) {
                set(convention);
                this.defaultValue = (MapCollector<K, V>) NO_VALUE;
            }
            return;
        }
        if (beforeMutate()) {
            set(new MapCollectors.EntriesFromMap<>(entries));
        }
    }

    @Override
    public void set(Provider<? extends Map<? extends K, ? extends V>> provider) {
        if (!beforeMutate()) {
            return;
        }
        ProviderInternal<? extends Map<? extends K, ? extends V>> p = checkMapProvider(provider);
        set(new MapCollectors.EntriesFromMapProvider<>(p));
    }

    @Override
    public MapProperty<K, V> value(@Nullable Map<? extends K, ? extends V> entries) {
        set(entries);
        return this;
    }

    @Override
    public MapProperty<K, V> value(Provider<? extends Map<? extends K, ? extends V>> provider) {
        set(provider);
        return this;
    }

    private void set(MapCollector<K, V> collector) {
        value = collector;
    }

    @Override
    public void put(K key, V value) {
        Preconditions.checkNotNull(key, NULL_KEY_FORBIDDEN_MESSAGE);
        Preconditions.checkNotNull(value, NULL_VALUE_FORBIDDEN_MESSAGE);
        if (!beforeMutate()) {
            return;
        }
        addCollector(new MapCollectors.SingleEntry<>(key, value));
    }

    @Override
    public void put(K key, Provider<? extends V> providerOfValue) {
        Preconditions.checkNotNull(key, NULL_KEY_FORBIDDEN_MESSAGE);
        Preconditions.checkNotNull(providerOfValue, NULL_VALUE_FORBIDDEN_MESSAGE);
        if (!beforeMutate()) {
            return;
        }
        ProviderInternal<? extends V> p = Providers.internal(providerOfValue);
        if (p.getType() != null && !valueType.isAssignableFrom(p.getType())) {
            throw new IllegalArgumentException(String.format("Cannot add an entry to a property of type %s with values of type %s using a provider of type %s.",
                Map.class.getName(), valueType.getName(), p.getType().getName()));
        }
        addCollector(new MapCollectors.EntryWithValueFromProvider<>(key, p));
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> entries) {
        if (!beforeMutate()) {
            return;
        }
        addCollector(new MapCollectors.EntriesFromMap<>(entries));
    }

    @Override
    public void putAll(Provider<? extends Map<? extends K, ? extends V>> provider) {
        if (!beforeMutate()) {
            return;
        }
        ProviderInternal<? extends Map<? extends K, ? extends V>> p = checkMapProvider(provider);
        addCollector(new MapCollectors.EntriesFromMapProvider<>(p));
    }

    private void addCollector(MapCollector<K, V> collector) {
        value = new PlusCollector<>(value, collector);
    }

    @SuppressWarnings("unchecked")
    private ProviderInternal<? extends Map<? extends K, ? extends V>> checkMapProvider(@Nullable Provider<? extends Map<? extends K, ? extends V>> provider) {
        if (provider == null) {
            throw new IllegalArgumentException("Cannot set the value of a property using a null provider.");
        }
        ProviderInternal<? extends Map<? extends K, ? extends V>> p = Providers.internal(provider);
        if (p.getType() != null && !Map.class.isAssignableFrom(p.getType())) {
            throw new IllegalArgumentException(String.format("Cannot set the value of a property of type %s using a provider of type %s.",
                Map.class.getName(), p.getType().getName()));
        }
        if (p instanceof MapProviderInternal) {
            Class<? extends K> providerKeyType = ((MapProviderInternal<? extends K, ? extends V>) p).getKeyType();
            Class<? extends V> providerValueType = ((MapProviderInternal<? extends K, ? extends V>) p).getValueType();
            if (!keyType.isAssignableFrom(providerKeyType) || !valueType.isAssignableFrom(providerValueType)) {
                throw new IllegalArgumentException(String.format("Cannot set the value of a property of type %s with key type %s and value type %s " +
                        "using a provider with key type %s and value type %s.", Map.class.getName(), keyType.getName(), valueType.getName(),
                    providerKeyType.getName(), providerValueType.getName()));
            }
        }
        return p;
    }

    @Override
    public MapProperty<K, V> convention(Map<? extends K, ? extends V> value) {
        convention(new MapCollectors.EntriesFromMap<>(value));
        return this;
    }

    @Override
    public MapProperty<K, V> convention(Provider<? extends Map<? extends K, ? extends V>> valueProvider) {
        convention(new MapCollectors.EntriesFromMapProvider<>(Providers.internal(valueProvider)));
        return this;
    }

    private void convention(MapCollector<K, V> collector) {
        if (shouldApplyConvention()) {
            this.value = collector;
        }
        this.convention = collector;
    }

    public List<? extends ProviderInternal<? extends Map<? extends K, ? extends V>>> getProviders() {
        List<ProviderInternal<? extends Map<? extends K, ? extends V>>> providers = new ArrayList<>();
        value.visit(providers);
        return providers;
    }

    public void providers(List<? extends ProviderInternal<? extends Map<? extends K, ? extends V>>> providers) {
        if (!beforeMutate()) {
            return;
        }
        value = defaultValue;
        for (ProviderInternal<? extends Map<? extends K, ? extends V>> provider : providers) {
            value = new PlusCollector<>(value, new MapCollectors.EntriesFromMapProvider<>(provider));
        }
    }

    @Override
    public Provider<Set<K>> keySet() {
        return new KeySetProvider();
    }

    @Override
    protected String describeContents() {
        return String.format("Map(%s->%s, %s)", keyType.getSimpleName().toLowerCase(), valueType.getSimpleName(), value.toString());
    }

    @Override
    protected void applyDefaultValue() {
        value = defaultValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void makeFinal() {
        Map<K, V> entries = doGetOrNull();
        if (entries != null) {
            if (entries.isEmpty()) {
                set((MapCollector<K, V>) EMPTY_MAP);
            } else {
                set(new MapCollectors.EntriesFromMap<>(entries));
            }
        } else {
            set((MapCollector<K, V>) NO_VALUE);
        }
    }

    private class KeySetProvider extends AbstractReadOnlyProvider<Set<K>> {

        @Nullable
        @Override
        @SuppressWarnings("unchecked")
        public Class<Set<K>> getType() {
            return (Class) Set.class;
        }

        @Override
        public Set<K> get() {
            beforeRead();
            Set<K> keys = new LinkedHashSet<>();
            value.collectKeysInto(keyCollector, keys);
            return ImmutableSet.copyOf(keys);
        }

        @Nullable
        @Override
        public Set<K> getOrNull() {
            beforeRead();
            Set<K> keys = new LinkedHashSet<>();
            if (!value.maybeCollectKeysInto(keyCollector, keys)) {
                return null;
            }
            return ImmutableSet.copyOf(keys);
        }
    }

    private static class PlusCollector<K, V> implements MapCollector<K, V> {
        private final MapCollector<K, V> left;
        private final MapCollector<K, V> right;

        public PlusCollector(MapCollector<K, V> left, MapCollector<K, V> right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean present() {
            return left.present() && right.present();
        }

        @Override
        public void collectInto(DisplayName owner, MapEntryCollector<K, V> collector, Map<K, V> dest) {
            left.collectInto(owner, collector, dest);
            right.collectInto(owner, collector, dest);
        }

        @Override
        public boolean maybeCollectInto(MapEntryCollector<K, V> collector, Map<K, V> dest) {
            if (left.maybeCollectInto(collector, dest)) {
                return right.maybeCollectInto(collector, dest);
            }
            return false;
        }

        @Override
        public void collectKeysInto(ValueCollector<K> collector, Collection<K> dest) {
            left.collectKeysInto(collector, dest);
            right.collectKeysInto(collector, dest);
        }

        @Override
        public boolean maybeCollectKeysInto(ValueCollector<K> collector, Collection<K> dest) {
            if (left.maybeCollectKeysInto(collector, dest)) {
                return right.maybeCollectKeysInto(collector, dest);
            }
            return false;
        }

        @Override
        public void visit(List<ProviderInternal<? extends Map<? extends K, ? extends V>>> sources) {
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
