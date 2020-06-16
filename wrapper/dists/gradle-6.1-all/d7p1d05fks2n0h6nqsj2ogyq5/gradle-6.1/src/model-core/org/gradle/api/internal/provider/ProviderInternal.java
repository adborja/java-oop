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
import org.gradle.api.Task;
import org.gradle.api.Transformer;
import org.gradle.api.internal.tasks.TaskDependencyContainer;
import org.gradle.api.internal.tasks.TaskDependencyResolveContext;
import org.gradle.api.provider.Provider;
import org.gradle.internal.DisplayName;

import javax.annotation.Nullable;

public interface ProviderInternal<T> extends Provider<T>, TaskDependencyContainer {
    /**
     * Return the upper bound on the type of all values that this provider may produce, if known.
     *
     * This could probably move to the public API.
     */
    @Nullable
    Class<T> getType();

    /**
     * Returns true when the <em>value</em> of this provider is produced by a task. The <em>value</em> is the object returned by {@link #get()} and other query methods.
     * This is distinct from the <em>content</em>, which is the state of the value or the thing that the value points to. For example, for a file property, the file path
     * represents the value of the property, and the contents of the file on the file system represents the content of the property.
     *
     * <p>Note that a task producing the value of this provider is not necessarily the same as a task producing the <em>content</em> of
     * the value of this provider.
     */
    boolean isValueProducedByTask();

    /**
     * Visits the tasks that produce the <em>content</em> of the value of this provider, if any.
     *
     * At some point, this method can {@link #maybeVisitBuildDependencies(TaskDependencyResolveContext)} could be merged.
     */
    void visitProducerTasks(Action<? super Task> visitor);

    /**
     * Visits the build dependencies of this provider, if possible.
     *
     * @return true if the dependencies have been added (possibly none), false if the build dependencies are unknown.
     */
    boolean maybeVisitBuildDependencies(TaskDependencyResolveContext context);

    @Override
    <S> ProviderInternal<S> map(Transformer<? extends S, ? super T> transformer);

    /**
     * Returns a view of this provider that can be used to supply a value to a {@link org.gradle.api.provider.Property} instance.
     */
    ScalarSupplier<T> asSupplier(DisplayName owner, Class<? super T> targetType, ValueSanitizer<? super T> sanitizer);
}
