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
import org.tensorflow.types.family.TType;

/**
 * An Op to exchange data across TPU replicas.
 * On each replica, the input is split into {@code split_count} blocks along
 * {@code split_dimension} and send to the other replicas given group_assignment. After
 * receiving {@code split_count} - 1 blocks from other replicas, we concatenate the
 * blocks along {@code concat_dimension} as the output.
 * <p>For example, suppose there are 2 TPU replicas:
 * replica 0 receives input: {@code [[A, B]]}
 * replica 1 receives input: {@code [[C, D]]}
 * <p>group_assignment={@code [[0, 1]]}
 * concat_dimension=0
 * split_dimension=1
 * split_count=2
 * <p>replica 0's output: {@code [[A], [C]]}
 * replica 1's output: {@code [[B], [D]]}
 *
 * @param <T> data type for {@code output} output
 */
public final class AllToAll<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AllToAll";

  private Output<T> output;

  private AllToAll(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AllToAll operation.
   *
   * @param scope current scope
   * @param input The local input to the sum.
   * @param groupAssignment An int32 tensor with shape
   * [num_groups, num_replicas_per_group]. {@code group_assignment[i]} represents the
   * replica ids in the ith subgroup.
   * @param concatDimension The dimension number to concatenate.
   * @param splitDimension The dimension number to split.
   * @param splitCount The number of splits, this number must equal to the sub-group
   * size(group_assignment.get_shape()[1])
   * @param <T> data type for {@code AllToAll} output and operands
   * @return a new instance of AllToAll
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> AllToAll<T> create(Scope scope, Operand<T> input,
      Operand<TInt32> groupAssignment, Long concatDimension, Long splitDimension, Long splitCount) {
    OperationBuilder opBuilder = scope.env().opBuilder("AllToAll", scope.makeOpName("AllToAll"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(groupAssignment.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("concat_dimension", concatDimension);
    opBuilder.setAttr("split_dimension", splitDimension);
    opBuilder.setAttr("split_count", splitCount);
    return new AllToAll<>(opBuilder.build());
  }

  /**
   * Gets output.
   * The exchanged result.
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
