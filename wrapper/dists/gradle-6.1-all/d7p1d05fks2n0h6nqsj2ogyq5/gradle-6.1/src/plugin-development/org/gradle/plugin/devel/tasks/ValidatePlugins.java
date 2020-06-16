/*
 * Copyright 2016 the original author or authors.
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

package org.gradle.plugin.devel.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.Incubating;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.internal.DocumentationRegistry;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.Classpath;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.PathSensitive;
import org.gradle.api.tasks.PathSensitivity;
import org.gradle.api.tasks.SkipWhenEmpty;
import org.gradle.api.tasks.TaskAction;
import org.gradle.internal.execution.WorkValidationException;
import org.gradle.plugin.devel.tasks.internal.ValidateAction;
import org.gradle.workers.WorkerExecutor;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Validates plugins by checking property annotations on work items like tasks and artifact transforms.
 *
 * See the user guide for more information on
 * <a href="https://docs.gradle.org/current/userguide/more_about_tasks.html#sec:up_to_date_checks" target="_top">incremental build</a> and
 * <a href="https://docs.gradle.org/current/userguide/build_cache.html#sec:task_output_caching" target="_top">caching task outputs</a>.
 *
 * @since 6.0
 */
@CacheableTask
@Incubating
public abstract class ValidatePlugins extends DefaultTask {

    public ValidatePlugins() {
        getEnableStricterValidation().convention(false);
        getIgnoreFailures().convention(false);
        getFailOnWarning().convention(true);
    }

    @TaskAction
    public void validateTaskClasses() throws IOException {
        getWorkerExecutor()
            .classLoaderIsolation(spec -> spec.getClasspath().setFrom(getClasses(), getClasspath()))
            .submit(ValidateAction.class, params -> {
                params.getClasses().setFrom(getClasses());
                params.getOutputFile().set(getOutputFile());
                params.getEnableStricterValidation().set(getEnableStricterValidation());
            });
        getWorkerExecutor().await();

        List<String> problemMessages = Files.readAllLines(getOutputFile().get().getAsFile().toPath());

        if (problemMessages.isEmpty()) {
            getLogger().info("Plugin validation finished without warnings.");
        } else {
            if (getFailOnWarning().get() || problemMessages.stream().anyMatch(line -> line.startsWith("Error:"))) {
                if (getIgnoreFailures().get()) {
                    getLogger().warn("Plugin validation finished with errors. See {} for more information on how to annotate task properties.{}",
                        getDocumentationRegistry().getDocumentationFor("more_about_tasks", "sec:task_input_output_annotations"),
                        toMessageList(problemMessages));
                } else {
                    throw new WorkValidationException(String.format("Plugin validation failed. See %s for more information on how to annotate task properties.",
                        getDocumentationRegistry().getDocumentationFor("more_about_tasks", "sec:task_input_output_annotations")),
                        toExceptionList(problemMessages));
                }
            } else {
                getLogger().warn("Plugin validation finished with warnings:{}",
                    toMessageList(problemMessages));
            }
        }
    }

    private static CharSequence toMessageList(List<String> problemMessages) {
        StringBuilder builder = new StringBuilder();
        for (String problemMessage : problemMessages) {
            builder.append(String.format("%n  - %s", problemMessage));
        }
        return builder;
    }

    private static List<InvalidUserDataException> toExceptionList(List<String> problemMessages) {
        return problemMessages.stream()
            .map(InvalidUserDataException::new)
            .collect(Collectors.toList());
    }

    /**
     * The classes to validate.
     */
    @PathSensitive(PathSensitivity.RELATIVE)
    @InputFiles
    @SkipWhenEmpty
    public abstract ConfigurableFileCollection getClasses();

    /**
     * The classpath used to load the classes under validation.
     */
    @Classpath
    public abstract ConfigurableFileCollection getClasspath();

    /**
     * Specifies whether the build should break when plugin verifications fails.
     */
    @Input
    public abstract Property<Boolean> getIgnoreFailures();

    /**
     * Returns whether the build should break when the verifications performed by this task detects a warning.
     */
    @Input
    public abstract Property<Boolean> getFailOnWarning();

    /**
     * Enable the stricter validation for cacheable tasks for all tasks.
     */
    @Input
    public abstract Property<Boolean> getEnableStricterValidation();

    /**
     * Returns the output file to store the report in.
     */
    @OutputFile
    public abstract RegularFileProperty getOutputFile();

    @Inject
    abstract protected DocumentationRegistry getDocumentationRegistry();

    @Inject
    abstract protected WorkerExecutor getWorkerExecutor();
}
