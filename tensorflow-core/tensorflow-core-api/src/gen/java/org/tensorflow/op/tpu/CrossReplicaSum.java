/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.tpu;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * An Op to sum inputs across replicated TPU instances.
 * Each instance supplies its own input.
 * <p>For example, suppose there are 8 TPU instances: {@code [A, B, C, D, E, F, G, H]}.
 * Passing group_assignment={@code [[0,2,4,6],[1,3,5,7]]} sets {@code A, C, E, G} as group 0,
 * and {@code B, D, F, H} as group 1. Thus we get the outputs:
 * {@code [A+C+E+G, B+D+F+H, A+C+E+G, B+D+F+H, A+C+E+G, B+D+F+H, A+C+E+G, B+D+F+H]}.
 *
 * @param <T> data type for {@code output} output
 */
public final class CrossReplicaSum<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CrossReplicaSum";

  private Output<T> output;

  private CrossReplicaSum(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CrossReplicaSum operation.
   *
   * @param scope current scope
   * @param input The local input to the sum.
   * @param groupAssignment An int32 tensor with shape
   * [num_groups, num_replicas_per_group]. {@code group_assignment[i]} represents the
   * replica ids in the ith subgroup.
   * @param <T> data type for {@code CrossReplicaSum} output and operands
   * @return a new instance of CrossReplicaSum
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> CrossReplicaSum<T> create(Scope scope, Operand<T> input,
      Operand<TInt32> groupAssignment) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CrossReplicaSum");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(groupAssignment.asOutput());
    return new CrossReplicaSum<>(opBuilder.build());
  }

  /**
   * Gets output.
   * The sum of all the distributed inputs.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }
}
