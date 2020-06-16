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

package org.gradle.internal.resource.local;

import com.google.common.base.Preconditions;
import org.gradle.internal.file.FileAccessTimeJournal;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Tracks access to files and directories at the supplied depth within the supplied base
 * directory by setting their last access time in the supplied {@link FileAccessTimeJournal}.
 */
public class SingleDepthFileAccessTracker implements FileAccessTracker {

    private final Path baseDir;
    private final int endNameIndex;
    private final int startNameIndex;
    private final FileAccessTimeJournal journal;

    public SingleDepthFileAccessTracker(FileAccessTimeJournal journal, File baseDir, int depth) {
        this.journal = journal;
        Preconditions.checkArgument(depth > 0, "depth must be > 0: %s", depth);
        this.baseDir = baseDir.toPath().toAbsolutePath();
        this.startNameIndex = this.baseDir.getNameCount();
        this.endNameIndex = startNameIndex + depth;
    }

    @Override
    public void markAccessed(File file) {
        markAccessed(toSubPath(file));
    }

    @Override
    public void markAccessed(Collection<File> files) {
        for (Path path : collectSubPaths(files)) {
            markAccessed(path);
        }
    }

    private void markAccessed(Path path) {
        if (path != null) {
            journal.setLastAccessTime(path.toFile(), System.currentTimeMillis());
        }
    }

    private Set<Path> collectSubPaths(Collection<File> files) {
        Set<Path> paths = new HashSet<Path>();
        for (File file : files) {
            paths.add(toSubPath(file));
        }
        return paths;
    }

    private Path toSubPath(File file) {
        Path path = file.toPath().toAbsolutePath();
        if (path.getNameCount() >= endNameIndex && path.startsWith(baseDir)) {
            return baseDir.resolve(path.subpath(startNameIndex, endNameIndex));
        }
        return null;
    }
}
