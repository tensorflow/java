/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.op;

import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Operation;

/**
 * A logical unit of computation.
 *
 * <p>{@code Op} implementations provide a strongly typed API for building and executing
 * operations without the use of literals and indexes, as required in internal classes like
 * {@link Operation}.
 *
 * <p>Ops can be classified under two categories:
 * <ul>
 * <li><i>{@link RawOp Raw} ops</i> target a single TensorFlow operation and are, in most cases,
 * generated automatically from the {@code OpDef} proto definitions exposed by TensorFlow runtime
 * library.</li>
 * <li><i>Composite ops</i> execute a series of other ops to accomplish a task logically presented
 * as a single unit of computation.
 * </ul>
 */
public interface Op {

  /**
   * Return this unit of computation as a single {@link Operation}.
   *
   * <p>For a {@link RawOp raw} op, the returned value correspond to the TensorFlow operation
   * wrapped by this op.
   *
   * <p>For a composite op, the returned value usually correspond to the last operation invoked in a
   * chain or to a {@link org.tensorflow.op.core.NoOp NoOp} grouping one or more operations as a
   * single unit of computation.
   *
   * @return an {@link Operation}
   */
  Operation op();

  /**
   * Return the execution environment this op was created in.
   */
  default ExecutionEnvironment env() {
    return op().env();
  }
}
