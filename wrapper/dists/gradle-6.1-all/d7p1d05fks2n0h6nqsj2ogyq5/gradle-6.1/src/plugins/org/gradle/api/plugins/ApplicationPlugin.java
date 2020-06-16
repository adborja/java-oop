/*
 * Copyright 2010 the original author or authors.
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

package org.gradle.api.plugins;

import org.gradle.api.Action;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.UncheckedIOException;
import org.gradle.api.distribution.Distribution;
import org.gradle.api.distribution.DistributionContainer;
import org.gradle.api.distribution.plugins.DistributionPlugin;
import org.gradle.api.file.CopySpec;
import org.gradle.api.plugins.internal.DefaultApplicationPluginConvention;
import org.gradle.api.plugins.internal.DefaultJavaApplication;
import org.gradle.api.tasks.JavaExec;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.Sync;
import org.gradle.api.tasks.TaskProvider;
import org.gradle.api.tasks.application.CreateStartScripts;

import java.io.File;
import java.util.concurrent.Callable;

import static org.gradle.api.distribution.plugins.DistributionPlugin.TASK_INSTALL_NAME;

/**
 * <p>A {@link Plugin} which runs a project as a Java Application.</p>
 *
 * <p>The plugin can be configured via its companion {@link ApplicationPluginConvention} object.</p>
 */
public class ApplicationPlugin implements Plugin<Project> {
    public static final String APPLICATION_PLUGIN_NAME = "application";
    public static final String APPLICATION_GROUP = APPLICATION_PLUGIN_NAME;
    public static final String TASK_RUN_NAME = "run";
    public static final String TASK_START_SCRIPTS_NAME = "startScripts";
    public static final String TASK_DIST_ZIP_NAME = "distZip";
    public static final String TASK_DIST_TAR_NAME = "distTar";

    @Override
    public void apply(final Project project) {
        project.getPluginManager().apply(JavaPlugin.class);
        project.getPluginManager().apply(DistributionPlugin.class);

        ApplicationPluginConvention pluginConvention = addExtensions(project);
        addRunTask(project, pluginConvention);
        addCreateScriptsTask(project, pluginConvention);
        configureInstallTask(project.getTasks().named(TASK_INSTALL_NAME, Sync.class), pluginConvention);

        DistributionContainer distributions = (DistributionContainer) project.getExtensions().getByName("distributions");
        Distribution mainDistribution = distributions.getByName(DistributionPlugin.MAIN_DISTRIBUTION_NAME);
        configureDistribution(project, mainDistribution, pluginConvention);
    }

    private void configureInstallTask(TaskProvider<Sync> installTask, ApplicationPluginConvention pluginConvention) {
        installTask.configure(task -> task.doFirst("don't overwrite existing directories", new PreventDestinationOverwrite(pluginConvention)));
    }

    private static class PreventDestinationOverwrite implements Action<Task> {
        private final ApplicationPluginConvention pluginConvention;

        private PreventDestinationOverwrite(ApplicationPluginConvention pluginConvention) {
            this.pluginConvention = pluginConvention;
        }

        @Override
        public void execute(Task task) {
            Sync sync = (Sync)task;
            File destinationDir = sync.getDestinationDir();
            if (destinationDir.isDirectory()) {
                String[] children = destinationDir.list();
                if (children == null) {
                    throw new UncheckedIOException("Could not list directory " + destinationDir);
                }
                if (children.length > 0) {
                    if (!new File(destinationDir, "lib").isDirectory() || !new File(destinationDir, pluginConvention.getExecutableDir()).isDirectory()) {
                        throw new GradleException("The specified installation directory \'"
                                + destinationDir
                                + "\' is neither empty nor does it contain an installation for \'"
                                + pluginConvention.getApplicationName()
                                + "\'.\n"
                                + "If you really want to install to this directory, delete it and run the install task again.\n"
                                + "Alternatively, choose a different installation directory.");
                    }
                }
            }
        }
    }

    private ApplicationPluginConvention addExtensions(Project project) {
        ApplicationPluginConvention pluginConvention = new DefaultApplicationPluginConvention(project);
        pluginConvention.setApplicationName(project.getName());
        project.getConvention().getPlugins().put("application", pluginConvention);
        project.getExtensions().create(JavaApplication.class, "application", DefaultJavaApplication.class, pluginConvention);
        return pluginConvention;
    }

    private void addRunTask(Project project, ApplicationPluginConvention pluginConvention) {
        project.getTasks().register(TASK_RUN_NAME, JavaExec.class, run -> {
            run.setDescription("Runs this project as a JVM application");
            run.setGroup(APPLICATION_GROUP);

            JavaPluginConvention javaPluginConvention = project.getConvention().getPlugin(JavaPluginConvention.class);
            run.setClasspath(javaPluginConvention.getSourceSets().getByName(SourceSet.MAIN_SOURCE_SET_NAME).getRuntimeClasspath());
            run.getConventionMapping().map("main", pluginConvention::getMainClassName);
            run.getConventionMapping().map("jvmArgs", pluginConvention::getApplicationDefaultJvmArgs);
        });
    }

    // @Todo: refactor this task configuration to extend a copy task and use replace tokens
    private void addCreateScriptsTask(Project project, ApplicationPluginConvention pluginConvention) {
        project.getTasks().register(TASK_START_SCRIPTS_NAME, CreateStartScripts.class, startScripts -> {
            startScripts.setDescription("Creates OS specific scripts to run the project as a JVM application.");
            startScripts.setClasspath(project.getTasks().getAt(JavaPlugin.JAR_TASK_NAME).getOutputs().getFiles().plus(project.getConfigurations().getByName(JavaPlugin.RUNTIME_CLASSPATH_CONFIGURATION_NAME)));

            startScripts.getConventionMapping().map("mainClassName", pluginConvention::getMainClassName);

            startScripts.getConventionMapping().map("applicationName", pluginConvention::getApplicationName);

            startScripts.getConventionMapping().map("outputDir", () -> new File(project.getBuildDir(), "scripts"));

            startScripts.getConventionMapping().map("executableDir", pluginConvention::getExecutableDir);

            startScripts.getConventionMapping().map("defaultJvmOpts", pluginConvention::getApplicationDefaultJvmArgs);
        });
    }

    private CopySpec configureDistribution(Project project, Distribution mainDistribution, ApplicationPluginConvention pluginConvention) {
        mainDistribution.getDistributionBaseName().convention(project.provider(pluginConvention::getApplicationName));
        CopySpec distSpec = mainDistribution.getContents();

        TaskProvider<Task> jar = project.getTasks().named(JavaPlugin.JAR_TASK_NAME);
        TaskProvider<Task> startScripts = project.getTasks().named(TASK_START_SCRIPTS_NAME);

        CopySpec libChildSpec = project.copySpec();
        libChildSpec.into("lib");
        libChildSpec.from(jar);
        libChildSpec.from(project.getConfigurations().named(JavaPlugin.RUNTIME_CLASSPATH_CONFIGURATION_NAME));

        CopySpec binChildSpec = project.copySpec();

        binChildSpec.into((Callable<Object>) pluginConvention::getExecutableDir);
        binChildSpec.from(startScripts);
        binChildSpec.setFileMode(0755);

        CopySpec childSpec = project.copySpec();
        childSpec.from(project.file("src/dist"));
        childSpec.with(libChildSpec);
        childSpec.with(binChildSpec);

        distSpec.with(childSpec);

        distSpec.with(pluginConvention.getApplicationDistribution());
        return distSpec;
    }
}
