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

import org.gradle.internal.DisplayName;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A supplier of zero or more mappings from value of type {@link K} to value of type {@link V}.
 */
public interface MapCollector<K, V> extends ValueSupplier {

    boolean present();

    void collectInto(DisplayName owner, MapEntryCollector<K, V> collector, Map<K, V> dest);

    boolean maybeCollectInto(MapEntryCollector<K, V> collector, Map<K, V> dest);

    void collectKeysInto(ValueCollector<K> collector, Collection<K> dest);

    boolean maybeCollectKeysInto(ValueCollector<K> collector, Collection<K> dest);

    void visit(List<ProviderInternal<? extends Map<? extends K, ? extends V>>> sources);
}
