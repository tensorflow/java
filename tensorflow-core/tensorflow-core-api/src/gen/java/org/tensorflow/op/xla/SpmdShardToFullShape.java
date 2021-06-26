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

package org.tensorflow.op.xla;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * An op used by XLA SPMD partitioner to switch from manual partitioning to
 * automatic partitioning. It converts the shard-shaped, manually partitioned input
 * into full-shaped tensor to be partitioned automatically with the same sharding
 * used by manual partitioning.
 *
 * @param <T> data type for {@code output} output
 */
@Operator(
    group = "xla"
)
public final class SpmdShardToFullShape<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaSpmdShardToFullShape";

  private Output<T> output;

  private SpmdShardToFullShape(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaSpmdShardToFullShape operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param manualSharding the value of the manualSharding property
   * @param fullShape the value of the fullShape property
   * @param <T> data type for {@code XlaSpmdShardToFullShape} output and operands
   * @return a new instance of SpmdShardToFullShape
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SpmdShardToFullShape<T> create(Scope scope, Operand<T> input,
      String manualSharding, Shape fullShape) {
    OperationBuilder opBuilder = scope.env().opBuilder(OP_NAME, scope.makeOpName("SpmdShardToFullShape"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("manual_sharding", manualSharding);
    opBuilder.setAttr("full_shape", fullShape);
    return new SpmdShardToFullShape<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
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
