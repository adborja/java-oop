/*
 * Copyright 2009 the original author or authors.
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
package org.gradle.cache.internal;

import org.gradle.api.Action;
import org.gradle.cache.CacheBuilder;
import org.gradle.cache.CacheRepository;
import org.gradle.cache.CleanupAction;
import org.gradle.cache.FileLockManager;
import org.gradle.cache.LockOptions;
import org.gradle.cache.PersistentCache;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Collections;
import java.util.Map;

import static org.gradle.cache.internal.filelock.LockOptionsBuilder.mode;

public class DefaultCacheRepository implements CacheRepository {
    private final CacheScopeMapping cacheScopeMapping;
    private final CacheFactory factory;

    public DefaultCacheRepository(CacheScopeMapping cacheScopeMapping, CacheFactory factory) {
        this.cacheScopeMapping = cacheScopeMapping;
        this.factory = factory;
    }

    @Override
    public CacheBuilder cache(String key) {
        return new PersistentCacheBuilder(null, key);
    }

    @Override
    public CacheBuilder cache(File baseDir) {
        return new PersistentCacheBuilder(baseDir);
    }

    @Override
    public CacheBuilder cache(@Nullable Object scope, String key) {
        return new PersistentCacheBuilder(scope, key);
    }

    private class PersistentCacheBuilder implements CacheBuilder {
        final Object scope;
        final String key;
        final File baseDir;
        Map<String, ?> properties = Collections.emptyMap();
        Action<? super PersistentCache> initializer;
        CleanupAction cleanup;
        LockOptions lockOptions = mode(FileLockManager.LockMode.Shared);
        String displayName;
        VersionStrategy versionStrategy = VersionStrategy.CachePerVersion;
        LockTarget lockTarget = LockTarget.DefaultTarget;

        PersistentCacheBuilder(@Nullable Object scope, String key) {
            this.scope = scope;
            this.key = key;
            this.baseDir = null;
        }

        PersistentCacheBuilder(File baseDir) {
            this.scope = null;
            this.key = null;
            this.baseDir = baseDir;
        }

        @Override
        public CacheBuilder withProperties(Map<String, ?> properties) {
            this.properties = properties;
            return this;
        }

        @Override
        public CacheBuilder withCrossVersionCache(LockTarget lockTarget) {
            this.versionStrategy = VersionStrategy.SharedCache;
            this.lockTarget = lockTarget;
            return this;
        }

        @Override
        public CacheBuilder withDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        @Override
        public CacheBuilder withLockOptions(LockOptions lockOptions) {
            this.lockOptions = lockOptions;
            return this;
        }

        @Override
        public CacheBuilder withInitializer(Action<? super PersistentCache> initializer) {
            this.initializer = initializer;
            return this;
        }

        @Override
        public CacheBuilder withCleanup(CleanupAction cleanup) {
            this.cleanup = cleanup;
            return this;
        }

        @Override
        public PersistentCache open() {
            File cacheBaseDir;
            if (baseDir != null) {
                cacheBaseDir = baseDir;
            } else {
                cacheBaseDir = cacheScopeMapping.getBaseDirectory(scope, key, versionStrategy);
            }
            return factory.open(cacheBaseDir, displayName, properties, lockTarget, lockOptions, initializer, cleanup);
        }
    }
}
