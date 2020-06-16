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

package org.gradle.workers.internal;

import org.gradle.initialization.ClassLoaderRegistry;
import org.gradle.initialization.LegacyTypesSupport;
import org.gradle.internal.instantiation.InstantiatorFactory;
import org.gradle.internal.operations.BuildOperationExecutor;
import org.gradle.internal.operations.BuildOperationRef;
import org.gradle.internal.service.ServiceRegistry;
import org.gradle.workers.IsolationMode;

public class IsolatedClassloaderWorkerFactory implements WorkerFactory {
    private final BuildOperationExecutor buildOperationExecutor;
    private final ServiceRegistry internalServices;
    private final ClassLoaderRegistry classLoaderRegistry;
    private final LegacyTypesSupport legacyTypesSupport;
    private final ActionExecutionSpecFactory actionExecutionSpecFactory;
    private final InstantiatorFactory instantiatorFactory;

    public IsolatedClassloaderWorkerFactory(BuildOperationExecutor buildOperationExecutor, ServiceRegistry internalServices, ClassLoaderRegistry classLoaderRegistry) {
        this.buildOperationExecutor = buildOperationExecutor;
        this.internalServices = internalServices;
        this.classLoaderRegistry = classLoaderRegistry;
        this.legacyTypesSupport = internalServices.get(LegacyTypesSupport.class);
        this.actionExecutionSpecFactory = internalServices.get(ActionExecutionSpecFactory.class);
        this.instantiatorFactory = internalServices.get(InstantiatorFactory.class);
    }

    @Override
    public BuildOperationAwareWorker getWorker(WorkerRequirement workerRequirement) {
        return new AbstractWorker(buildOperationExecutor) {
            @Override
            public DefaultWorkResult execute(ActionExecutionSpec spec, BuildOperationRef parentBuildOperation) {
                return executeWrappedInBuildOperation(spec, parentBuildOperation, workSpec -> {
                    ServiceRegistry workServices = new WorkerPublicServicesBuilder(internalServices).withInternalServicesVisible(workSpec.isInternalServicesRequired()).build();
                    ClassLoader workerInfrastructureClassloader = classLoaderRegistry.getPluginsClassLoader();
                    ClassLoaderStructure classLoaderStructure = ((IsolatedClassLoaderWorkerRequirement)workerRequirement).getClassLoaderStructure();
                    ClassLoader workerClassLoader = IsolatedClassloaderWorker.createIsolatedWorkerClassloader(classLoaderStructure, workerInfrastructureClassloader, legacyTypesSupport);
                    Worker worker = new IsolatedClassloaderWorker(workerClassLoader, workServices, actionExecutionSpecFactory, instantiatorFactory);
                    return worker.execute(workSpec);
                });
            }
        };
    }

    @Override
    public IsolationMode getIsolationMode() {
        return IsolationMode.CLASSLOADER;
    }
}
