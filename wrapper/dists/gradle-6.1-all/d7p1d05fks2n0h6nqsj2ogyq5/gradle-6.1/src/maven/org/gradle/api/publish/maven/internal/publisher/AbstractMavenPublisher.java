/*
 * Copyright 2019 the original author or authors.
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

package org.gradle.api.publish.maven.internal.publisher;

import org.apache.maven.artifact.repository.metadata.Metadata;
import org.apache.maven.artifact.repository.metadata.Versioning;
import org.apache.maven.artifact.repository.metadata.io.xpp3.MetadataXpp3Reader;
import org.apache.maven.artifact.repository.metadata.io.xpp3.MetadataXpp3Writer;
import org.gradle.api.UncheckedIOException;
import org.gradle.api.internal.artifacts.repositories.resolver.ExternalResourceResolver;
import org.gradle.api.internal.artifacts.repositories.transport.NetworkOperationBackOffAndRetry;
import org.gradle.api.publish.maven.MavenArtifact;
import org.gradle.internal.Factory;
import org.gradle.internal.UncheckedException;
import org.gradle.internal.hash.HashUtil;
import org.gradle.internal.hash.HashValue;
import org.gradle.internal.resource.ExternalResourceName;
import org.gradle.internal.resource.ExternalResourceReadResult;
import org.gradle.internal.resource.ExternalResourceRepository;
import org.gradle.internal.resource.ReadableContent;
import org.gradle.internal.resource.local.ByteArrayReadableContent;
import org.gradle.internal.resource.local.FileReadableContent;
import org.gradle.internal.xml.XmlTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

import static org.apache.maven.artifact.ArtifactUtils.isSnapshot;

abstract class AbstractMavenPublisher implements MavenPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(MavenPublisher.class);

    private static final String POM_FILE_ENCODING = "UTF-8";
    private final Factory<File> temporaryDirFactory;
    private final XmlTransformer xmlTransformer = new XmlTransformer();

    AbstractMavenPublisher(Factory<File> temporaryDirFactory) {
        this.temporaryDirFactory = temporaryDirFactory;
    }

    protected void publish(MavenNormalizedPublication publication, ExternalResourceRepository repository, URI rootUri, boolean localRepo) {
        String groupId = publication.getGroupId();
        String artifactId = publication.getArtifactId();
        String version = publication.getVersion();

        ModuleArtifactPublisher artifactPublisher = new ModuleArtifactPublisher(repository, localRepo, rootUri, groupId, artifactId, version);

        if (isSnapshot(version)) {
            ExternalResourceName snapshotMetadataPath = artifactPublisher.getSnapshotMetadataLocation();
            Metadata snapshotMetadata = createSnapshotMetadata(publication, groupId, artifactId, version, repository, snapshotMetadataPath);

            artifactPublisher.publish(snapshotMetadataPath, writeMetadataToTmpFile(snapshotMetadata, "snapshot-maven-metadata.xml"));

            if (!localRepo) {
                // Use the timestamped version for all published artifacts:
                // The timestamped version is hidden deep in `Metadata.versioning.snapshotVersions`
                artifactPublisher.artifactVersion = snapshotMetadata.getVersioning().getSnapshotVersions().get(0).getVersion();
            }
        }

        if (publication.getMainArtifact() != null) {
            artifactPublisher.publish(null, publication.getMainArtifact().getExtension(), publication.getMainArtifact().getFile());
        }
        artifactPublisher.publish(null, "pom", publication.getPomArtifact().getFile());
        for (MavenArtifact artifact : publication.getAdditionalArtifacts()) {
            artifactPublisher.publish(artifact.getClassifier(), artifact.getExtension(), artifact.getFile());
        }

        ExternalResourceName externalResource = artifactPublisher.getMetadataLocation();
        Metadata metadata = createMetadata(groupId, artifactId, version, repository, externalResource);
        artifactPublisher.publish(externalResource, writeMetadataToTmpFile(metadata, "module-maven-metadata.xml"));
    }

    private Metadata createMetadata(String groupId, String artifactId, String version, ExternalResourceRepository repository, ExternalResourceName metadataResource) {
        Versioning versioning = getExistingVersioning(repository, metadataResource);
        if (!versioning.getVersions().contains(version)) {
            versioning.addVersion(version);
        }
        versioning.setLatest(version);
        if (!isSnapshot(version)) {
            versioning.setRelease(version);
        }
        versioning.updateTimestamp();

        Metadata metadata = new Metadata();
        metadata.setGroupId(groupId);
        metadata.setArtifactId(artifactId);
        metadata.setVersioning(versioning);
        return metadata;
    }

    private Versioning getExistingVersioning(ExternalResourceRepository repository, ExternalResourceName metadataResource) {
        ExternalResourceReadResult<Metadata> existing = readExistingMetadata(repository, metadataResource);

        if (existing != null) {
            Metadata recessive = existing.getResult();
            if (recessive.getVersioning() != null) {
                return recessive.getVersioning();
            }
        }
        return new Versioning();
    }

    private File writeMetadataToTmpFile(Metadata metadata, String fileName) {
        File metadataFile = new File(temporaryDirFactory.create(), fileName);
        xmlTransformer.transform(metadataFile, POM_FILE_ENCODING, writer -> {
            try {
                new MetadataXpp3Writer().write(writer, metadata);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
        return metadataFile;
    }

    ExternalResourceReadResult<Metadata> readExistingMetadata(ExternalResourceRepository repository, ExternalResourceName metadataResource) {
        return repository.resource(metadataResource).withContentIfPresent(inputStream -> {
            try {
                return new MetadataXpp3Reader().read(inputStream, false);
            } catch (Exception e) {
                throw UncheckedException.throwAsUncheckedException(e);
            }
        });
    }

    protected abstract Metadata createSnapshotMetadata(MavenNormalizedPublication publication, String groupId, String artifactId, String version, ExternalResourceRepository repository, ExternalResourceName metadataResource);

    /**
     * Publishes artifacts for a single Maven module.
     */
    private static class ModuleArtifactPublisher {
        private final NetworkOperationBackOffAndRetry networkOperationRunner = new NetworkOperationBackOffAndRetry();
        private final ExternalResourceRepository repository;
        private final boolean localRepo;
        private final URI rootUri;
        private final String groupPath;
        private final String artifactId;
        private final String moduleVersion;
        private String artifactVersion;

        ModuleArtifactPublisher(ExternalResourceRepository repository, boolean localRepo, URI rootUri, String groupId, String artifactId, String moduleVersion) {
            this.repository = repository.withProgressLogging();
            this.localRepo = localRepo;
            this.rootUri = rootUri;
            this.groupPath = groupId.replace('.', '/');
            this.artifactId = artifactId;
            this.moduleVersion = moduleVersion;
            this.artifactVersion = moduleVersion;
        }

        /**
         * Return the location of the module `maven-metadata.xml`, which lists all published versions for a Maven module.
         */
        ExternalResourceName getMetadataLocation() {
            String path = groupPath + '/' + artifactId + '/' + getMetadataFileName();
            return new ExternalResourceName(rootUri, path);
        }

        /**
         * Return the location of the snapshot `maven-metadata.xml`, which contains details of the latest published snapshot for a Maven module.
         */
        ExternalResourceName getSnapshotMetadataLocation() {
            String path = groupPath + '/' + artifactId + '/' + moduleVersion + '/' + getMetadataFileName();
            return new ExternalResourceName(rootUri, path);
        }

        private String getMetadataFileName() {
            if (localRepo) {
                return "maven-metadata-local.xml";
            }
            return "maven-metadata.xml";
        }

        /**
         * Publishes a single module artifact, based on classifier and extension.
         */
        void publish(String classifier, String extension, File content) {
            StringBuilder path = new StringBuilder(128);
            path.append(groupPath).append('/');
            path.append(artifactId).append('/');
            path.append(moduleVersion).append('/');
            path.append(artifactId).append('-').append(artifactVersion);

            if (classifier != null) {
                path.append('-').append(classifier);
            }
            if (extension.length() > 0) {
                path.append('.').append(extension);
            }

            ExternalResourceName externalResource = new ExternalResourceName(rootUri, path.toString());
            publish(externalResource, maybeSubstituteVersion(extension, content));
        }

        // When publishing unique snapshot versions, we need to tweak the generated
        // Gradle Module Metadata file in order to replace the URI/file names of the
        // published files
        private File maybeSubstituteVersion(String extension, File content) {
            if ("module".equals(extension) && !artifactVersion.equals(moduleVersion)) {
                File timestamped = new File(content.getParentFile(), content.getName() + "-" + artifactVersion);
                if (timestamped.exists()) {
                    return timestamped;
                }
                // we're publishing a timestamped snapshot version so we need to tweak the generated module file
                try (PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(timestamped), StandardCharsets.UTF_8)))) {
                    try (Stream<String> lines = Files.lines(content.toPath(), StandardCharsets.UTF_8)) {
                        lines.forEach(line -> {
                            if (line.contains("\"url\"")) {
                                // We cannot replace versions that appear as path elements, and so there should only be one version to replace at most
                                writer.println(line.replaceFirst(moduleVersion + "([^/])", artifactVersion + "$1"));
                            } else {
                                writer.println(line);
                            }
                        });
                    }
                } catch (IOException e) {
                    throw UncheckedException.throwAsUncheckedException(e);
                }
                return timestamped;
            }
            return content;
        }

        void publish(ExternalResourceName externalResource, File content) {
            if (!localRepo) {
                LOGGER.info("Uploading {} to {}", externalResource.getShortDisplayName(), externalResource.getPath());
            }
            putResource(externalResource, new FileReadableContent(content));
            if (!localRepo) {
                publishChecksums(externalResource, content);
            }
        }

        private void publishChecksums(ExternalResourceName destination, File content) {
            publishChecksum(destination, content, "sha1", 40);
            publishChecksum(destination, content, "md5", 32);
            if (!ExternalResourceResolver.disableExtraChecksums()) {
                publishPossiblyUnsupportedChecksum(destination, content, "sha-256", 64);
                publishPossiblyUnsupportedChecksum(destination, content, "sha-512", 128);
            }
        }

        private void publishPossiblyUnsupportedChecksum(ExternalResourceName destination, File content, String algorithm, int length) {
            try {
                publishChecksum(destination, content, algorithm, length);
            } catch (Exception ex) {
                LOGGER.warn("Cannot upload checksum for " + content.getName() + ". Remote repository doesn't support " + algorithm + ". Error: " + ex.getMessage());
            }
        }

        private void publishChecksum(ExternalResourceName destination, File content, String algorithm, int length) {
            byte[] checksum = createChecksumFile(content, algorithm.toUpperCase(), length);
            putResource(destination.append("." + algorithm.replaceAll("-", "")), new ByteArrayReadableContent(checksum));
        }

        private byte[] createChecksumFile(File src, String algorithm, int checksumLength) {
            HashValue hash = HashUtil.createHash(src, algorithm);
            String formattedHashString = hash.asZeroPaddedHexString(checksumLength);
            return formattedHashString.getBytes(StandardCharsets.US_ASCII);
        }

        private void putResource(ExternalResourceName externalResource, ReadableContent readableContent) {
            networkOperationRunner.withBackoffAndRetry(new Runnable() {
                @Override
                public void run() {
                    repository.resource(externalResource).put(readableContent);
                }

                @Override
                public String toString() {
                    return "PUT " + externalResource.getDisplayName();
                }
            });
        }
    }

}
