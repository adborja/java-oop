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

package org.gradle.api.internal.tasks.testing.junitplatform;

import org.gradle.api.internal.tasks.testing.DefaultTestClassDescriptor;
import org.gradle.api.internal.tasks.testing.DefaultTestDescriptor;
import org.gradle.api.internal.tasks.testing.TestCompleteEvent;
import org.gradle.api.internal.tasks.testing.TestDescriptorInternal;
import org.gradle.api.internal.tasks.testing.TestResultProcessor;
import org.gradle.api.internal.tasks.testing.TestStartEvent;
import org.gradle.api.tasks.testing.TestResult.ResultType;
import org.gradle.internal.MutableBoolean;
import org.gradle.internal.id.IdGenerator;
import org.gradle.internal.time.Clock;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.support.descriptor.ClassSource;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.gradle.api.tasks.testing.TestResult.ResultType.SKIPPED;
import static org.junit.platform.engine.TestDescriptor.Type.CONTAINER;
import static org.junit.platform.engine.TestExecutionResult.Status.ABORTED;
import static org.junit.platform.engine.TestExecutionResult.Status.FAILED;

public class JUnitPlatformTestExecutionListener implements TestExecutionListener {
    private final ConcurrentMap<String, TestDescriptorInternal> descriptorsByUniqueId = new ConcurrentHashMap<>();
    private final TestResultProcessor resultProcessor;
    private final Clock clock;
    private final IdGenerator<?> idGenerator;
    private TestPlan currentTestPlan;

    public JUnitPlatformTestExecutionListener(TestResultProcessor resultProcessor, Clock clock, IdGenerator<?> idGenerator) {
        this.resultProcessor = resultProcessor;
        this.clock = clock;
        this.idGenerator = idGenerator;
    }

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        this.currentTestPlan = testPlan;
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        this.currentTestPlan = null;
        this.descriptorsByUniqueId.clear();
    }

    @Override
    public void executionSkipped(TestIdentifier testIdentifier, String reason) {
        executionSkipped(testIdentifier);
    }

    private void executionSkipped(TestIdentifier testIdentifier) {
        executionStarted(testIdentifier);
        reportSkipped(testIdentifier);
    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        if (testIdentifier.isTest() || isClass(testIdentifier)) {
            reportStartedUnlessAlreadyStarted(testIdentifier);
        }
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testExecutionResult.getStatus() == ABORTED) {
            reportSkipped(testIdentifier);
            return;
        }
        if (testExecutionResult.getStatus() == FAILED) {
            reportStartedUnlessAlreadyStarted(testIdentifier);
            Throwable failure = testExecutionResult.getThrowable().orElseGet(() -> new AssertionError("test failed but did not report an exception"));
            if (testIdentifier.isTest()) {
                resultProcessor.failure(getId(testIdentifier), failure);
            } else {
                TestDescriptorInternal syntheticTestDescriptor = createSyntheticTestDescriptorForContainer(testIdentifier);
                resultProcessor.started(syntheticTestDescriptor, startEvent(getId(testIdentifier)));
                resultProcessor.failure(syntheticTestDescriptor.getId(), failure);
                resultProcessor.completed(syntheticTestDescriptor.getId(), completeEvent());
            }
        }
        if (wasStarted(testIdentifier)) {
            resultProcessor.completed(getId(testIdentifier), completeEvent());
        }
    }

    private void reportStartedUnlessAlreadyStarted(TestIdentifier testIdentifier) {
        boolean wasNotAlreadyStarted = createDescriptorIfAbsent(testIdentifier);
        // guard against edge cases (e.g. JUnit 4 classes with custom runners that report the class as ignored after reporting it as started)
        if (wasNotAlreadyStarted) {
            TestDescriptorInternal descriptor = descriptorsByUniqueId.get(testIdentifier.getUniqueId());
            resultProcessor.started(descriptor, startEvent(testIdentifier));
        }
    }

    private void reportSkipped(TestIdentifier testIdentifier) {
        currentTestPlan.getChildren(testIdentifier).stream()
            .filter(child -> !wasStarted(child))
            .forEach(child -> executionSkipped(child));
        if (testIdentifier.isTest()) {
            resultProcessor.completed(getId(testIdentifier), completeEvent(SKIPPED));
        } else if (isClass(testIdentifier)) {
            resultProcessor.completed(getId(testIdentifier), completeEvent());
        }
    }

    private TestStartEvent startEvent(TestIdentifier testIdentifier) {
        Object idOfClosestStartedAncestor = getAncestors(testIdentifier).stream()
            .map(TestIdentifier::getUniqueId)
            .filter(descriptorsByUniqueId::containsKey)
            .findFirst()
            .map(descriptorsByUniqueId::get)
            .map(TestDescriptorInternal::getId)
            .orElse(null);
        return startEvent(idOfClosestStartedAncestor);
    }

    private TestStartEvent startEvent(Object parentId) {
        return new TestStartEvent(clock.getCurrentTime(), parentId);
    }

    private TestCompleteEvent completeEvent() {
        return completeEvent(null);
    }

    private TestCompleteEvent completeEvent(ResultType resultType) {
        return new TestCompleteEvent(clock.getCurrentTime(), resultType);
    }

    private boolean wasStarted(TestIdentifier testIdentifier) {
        return descriptorsByUniqueId.containsKey(testIdentifier.getUniqueId());
    }

    private boolean createDescriptorIfAbsent(TestIdentifier node) {
        MutableBoolean wasCreated = new MutableBoolean(false);
        descriptorsByUniqueId.computeIfAbsent(node.getUniqueId(), uniqueId -> {
            wasCreated.set(true);
            if (node.getType() == CONTAINER || isClass(node) && !hasSameSourceAsAncestor(node)) {
                TestIdentifier classIdentifier = findClassSource(node);
                String className = className(classIdentifier);
                String classDisplayName = classDisplayName(classIdentifier);
                return new DefaultTestClassDescriptor(idGenerator.generateId(), className, classDisplayName);
            }
            return createTestDescriptor(node, node.getLegacyReportingName(), node.getDisplayName());
        });
        return wasCreated.get();
    }

    private TestDescriptorInternal createSyntheticTestDescriptorForContainer(TestIdentifier node) {
        boolean testsStarted = currentTestPlan.getDescendants(node).stream().anyMatch(this::wasStarted);
        String name = testsStarted ? "executionError": "initializationError";
        return createTestDescriptor(node, name, name);
    }

    private TestDescriptorInternal createTestDescriptor(TestIdentifier test, String name, String displayName) {
        TestIdentifier classIdentifier = findClassSource(test);
        String className = className(classIdentifier);
        String classDisplayName = classDisplayName(classIdentifier);
        return new DefaultTestDescriptor(idGenerator.generateId(), className, name, classDisplayName, displayName);
    }

    private Object getId(TestIdentifier testIdentifier) {
        return descriptorsByUniqueId.get(testIdentifier.getUniqueId()).getId();
    }

    private Set<TestIdentifier> getAncestors(TestIdentifier testIdentifier) {
        Set<TestIdentifier> result = new LinkedHashSet<>();
        Optional<String> parentId = testIdentifier.getParentId();
        while (parentId.isPresent()) {
            TestIdentifier parent = currentTestPlan.getTestIdentifier(parentId.get());
            result.add(parent);
            parentId = parent.getParentId();
        }
        return result;
    }

    private boolean isClass(TestIdentifier test) {
        return test.getSource().isPresent() && test.getSource().get() instanceof ClassSource;
    }

    private boolean hasSameSourceAsAncestor(TestIdentifier node) {
        Optional<TestIdentifier> parent = currentTestPlan.getParent(node);
        while (parent.isPresent()) {
            if (Objects.equals(parent.get().getSource(), node.getSource())) {
                return true;
            }
            parent = currentTestPlan.getParent(parent.get());
        }
        return false;
    }

    private TestIdentifier findClassSource(TestIdentifier testIdentifier) {
        // For tests in default method of interface,
        // we might not be able to get the implementation class directly.
        // In this case, we need to retrieve test plan to get the real implementation class.
        TestIdentifier current = testIdentifier;
        while (current != null) {
            if (isClass(current)) {
                return current;
            }
            current = current.getParentId().map(currentTestPlan::getTestIdentifier).orElse(null);
        }
        return null;
    }

    private String className(TestIdentifier testClassIdentifier) {
        if (testClassIdentifier != null && isClass(testClassIdentifier)) {
            return ((ClassSource) testClassIdentifier.getSource().get()).getClassName();
        } else {
            return "UnknownClass";
        }
    }

    private String classDisplayName(TestIdentifier testClassIdentifier) {
        if (testClassIdentifier != null) {
            return testClassIdentifier.getDisplayName();
        } else {
            return "UnknownClass";
        }
    }

}
