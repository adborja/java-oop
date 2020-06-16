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

package org.gradle.integtests.fixtures

import org.gradle.integtests.fixtures.executer.GradleContextualExecuter
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * JUnit Rule supporting the {@link ToBeFixedForInstantExecution} annotation.
 */
class ToBeFixedForInstantExecutionRule implements TestRule {

    @Override
    Statement apply(Statement base, Description description) {
        def annotation = description.getAnnotation(ToBeFixedForInstantExecution.class)
        if (!GradleContextualExecuter.isInstant() || annotation == null) {
            return base
        }
        if (annotation.value() == ToBeFixedForInstantExecution.Skip.DO_NOT_SKIP) {
            return new ExpectingFailureRuleStatement(base)
        }
        return new UnsupportedWithInstantExecutionRule.SkippingRuleStatement(base)
    }

    private static class ExpectingFailureRuleStatement extends Statement {

        private final Statement next

        private ExpectingFailureRuleStatement(Statement next) {
            this.next = next
        }

        @Override
        void evaluate() throws Throwable {
            try {
                next.evaluate()
                throw new ToBeFixedForInstantExecutionExtension.UnexpectedSuccessException()
            } catch (ToBeFixedForInstantExecutionExtension.UnexpectedSuccessException ex) {
                throw ex
            } catch (Throwable ex) {
                System.err.println("Failed with instant execution as expected:")
                ex.printStackTrace()
            }
        }
    }
}
