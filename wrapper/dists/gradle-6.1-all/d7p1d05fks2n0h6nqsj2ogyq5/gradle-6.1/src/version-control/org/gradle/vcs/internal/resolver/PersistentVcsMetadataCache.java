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

package org.gradle.vcs.internal.resolver;

import org.gradle.api.artifacts.VersionConstraint;
import org.gradle.cache.CacheRepository;
import org.gradle.cache.FileLockManager;
import org.gradle.cache.PersistentCache;
import org.gradle.cache.PersistentIndexedCache;
import org.gradle.internal.Factory;
import org.gradle.internal.concurrent.Stoppable;
import org.gradle.internal.serialize.Decoder;
import org.gradle.internal.serialize.Encoder;
import org.gradle.internal.serialize.Serializer;
import org.gradle.vcs.git.internal.GitVersionRef;
import org.gradle.vcs.internal.VcsDirectoryLayout;
import org.gradle.vcs.internal.VersionControlRepositoryConnection;
import org.gradle.vcs.internal.VersionRef;

import javax.annotation.Nullable;

import static org.gradle.cache.internal.filelock.LockOptionsBuilder.mode;

public class PersistentVcsMetadataCache implements Stoppable {
    private static final VersionRefSerializer VALUE_SERIALIZER = new VersionRefSerializer();
    private final PersistentCache cache;
    private final PersistentIndexedCache<String, VersionRef> workingDirCache;

    public PersistentVcsMetadataCache(VcsDirectoryLayout directoryLayout, CacheRepository cacheRepository) {
        cache = cacheRepository
            .cache(directoryLayout.getMetadataDir())
            .withDisplayName("VCS metadata")
            .withLockOptions(mode(FileLockManager.LockMode.None)) // Don't need to lock anything until we use the caches
            .open();
        workingDirCache = cache.createCache("workingDirs", String.class, VALUE_SERIALIZER);
    }

    @Override
    public void stop() {
        cache.close();
    }

    @Nullable
    public VersionRef getVersionForSelector(final VersionControlRepositoryConnection repository, final VersionConstraint constraint) {
        return cache.useCache(new Factory<VersionRef>() {
            @Nullable
            @Override
            public VersionRef create() {
                return workingDirCache.get(constraintCacheKey(repository, constraint));
            }
        });
    }

    public void putVersionForSelector(final VersionControlRepositoryConnection repository, final VersionConstraint constraint, final VersionRef selectedVersion) {
        cache.useCache(new Runnable() {
            @Override
            public void run() {
                workingDirCache.put(constraintCacheKey(repository, constraint), selectedVersion);
            }
        });
    }

    private String constraintCacheKey(VersionControlRepositoryConnection repository, VersionConstraint constraint) {
        if (constraint.getBranch() != null) {
            return repository.getUniqueId() + ":b:" + constraint.getBranch();
        }
        return repository.getUniqueId() + ":v:" + constraint.getRequiredVersion();
    }

    private static class VersionRefSerializer implements Serializer<VersionRef> {
        @Override
        public void write(Encoder encoder, VersionRef value) throws Exception {
            GitVersionRef gitRef = (GitVersionRef) value;
            encoder.writeString(gitRef.getVersion());
            encoder.writeString(gitRef.getCanonicalId());
        }

        @Override
        public VersionRef read(Decoder decoder) throws Exception {
            String version = decoder.readString();
            String canonicalId = decoder.readString();
            return GitVersionRef.from(version, canonicalId);
        }
    }
}
