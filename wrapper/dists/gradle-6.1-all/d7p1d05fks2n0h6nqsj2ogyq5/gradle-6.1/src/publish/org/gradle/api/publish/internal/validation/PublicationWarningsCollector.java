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

package org.gradle.api.publish.internal.validation;

import org.gradle.api.logging.Logger;
import org.gradle.internal.logging.text.TreeFormatter;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@NotThreadSafe
public class PublicationWarningsCollector {

    private final Logger logger;
    private final String unsupportedFeature;
    private final String incompatibleFeature;
    private final String footer;
    private final String disableMethod;
    private final Map<String, CollectedWarnings> variantToWarnings;
    private String currentVariant;
    private CollectedWarnings currentWarnings;

    public PublicationWarningsCollector(Logger logger, String unsupportedFeature, String incompatibleFeature, String footer, String disableMethod) {
        this.footer = footer;
        this.disableMethod = disableMethod;
        this.variantToWarnings = new TreeMap<>();
        this.logger = logger;
        this.unsupportedFeature = unsupportedFeature;
        this.incompatibleFeature = incompatibleFeature;
    }

    public void addUnsupported(String text) {
        currentWarnings.addUnsupported(text);
    }

    public void addIncompatible(String text) {
        currentWarnings.addIncompatible(text);
    }

    public void complete(String header, Set<String> silencedVariants) {
        saveVariantWarnings();

        variantToWarnings.keySet().removeAll(silencedVariants);
        if (!variantToWarnings.isEmpty()) {
            TreeFormatter treeFormatter = new TreeFormatter();
            treeFormatter.node(header + " warnings (silence with '" + disableMethod + "(variant)')");
            treeFormatter.startChildren();
            variantToWarnings.forEach((key, warnings) -> {
                treeFormatter.node("Variant " + key + ":");
                treeFormatter.startChildren();
                if (warnings.getVariantUnsupported() != null) {
                    warnings.getVariantUnsupported().forEach(treeFormatter::node);
                }
                if (warnings.getUnsupportedUsages() != null) {
                    treeFormatter.node(unsupportedFeature);
                    treeFormatter.startChildren();
                    warnings.getUnsupportedUsages().forEach(treeFormatter::node);
                    treeFormatter.endChildren();
                }
                if (warnings.getIncompatibleUsages() != null) {
                    treeFormatter.node(incompatibleFeature);
                    treeFormatter.startChildren();
                    warnings.getIncompatibleUsages().forEach(treeFormatter::node);
                    treeFormatter.endChildren();
                }
                treeFormatter.endChildren();
            });
            treeFormatter.endChildren();
            treeFormatter.node(footer);
            logger.lifecycle(treeFormatter.toString());
        }
    }

    public void newContext(String name) {
        saveVariantWarnings();
        currentVariant = name;
        currentWarnings = new CollectedWarnings();
    }

    private void saveVariantWarnings() {
        if (currentVariant != null && !currentWarnings.isEmpty()) {
            variantToWarnings.put(currentVariant, currentWarnings);
        }
    }

    public void addVariantUnsupported(String text) {
        currentWarnings.addVariantUnsupported(text);
    }
}
