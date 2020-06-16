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

package org.gradle.api.plugins.internal;

import org.gradle.api.file.CopySpec;
import org.gradle.api.plugins.ApplicationPluginConvention;
import org.gradle.api.plugins.JavaApplication;

public class DefaultJavaApplication implements JavaApplication {
    private final ApplicationPluginConvention convention;

    public DefaultJavaApplication(ApplicationPluginConvention convention) {
        this.convention = convention;
    }

    @Override
    public String getApplicationName() {
        return convention.getApplicationName();
    }

    @Override
    public void setApplicationName(String applicationName) {
        convention.setApplicationName(applicationName);
    }

    @Override
    public String getMainClassName() {
        return convention.getMainClassName();
    }

    @Override
    public void setMainClassName(String mainClassName) {
        convention.setMainClassName(mainClassName);
    }

    @Override
    public Iterable<String> getApplicationDefaultJvmArgs() {
        return convention.getApplicationDefaultJvmArgs();
    }

    @Override
    public void setApplicationDefaultJvmArgs(Iterable<String> applicationDefaultJvmArgs) {
        convention.setApplicationDefaultJvmArgs(applicationDefaultJvmArgs);
    }

    @Override
    public String getExecutableDir() {
        return convention.getExecutableDir();
    }

    @Override
    public void setExecutableDir(String executableDir) {
        convention.setExecutableDir(executableDir);
    }

    @Override
    public CopySpec getApplicationDistribution() {
        return convention.getApplicationDistribution();
    }

    @Override
    public void setApplicationDistribution(CopySpec applicationDistribution) {
        convention.setApplicationDistribution(applicationDistribution);
    }
}
