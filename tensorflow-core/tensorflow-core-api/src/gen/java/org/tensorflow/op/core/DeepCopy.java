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

package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Makes a copy of {@code x}.
 *
 * @param <T> data type for {@code y} output
 */
@Operator
public final class DeepCopy<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DeepCopy";

  private Output<T> y;

  private DeepCopy(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DeepCopy operation.
   *
   * @param scope current scope
   * @param x The source tensor of type {@code T}.
   * @param <T> data type for {@code DeepCopy} output and operands
   * @return a new instance of DeepCopy
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> DeepCopy<T> create(Scope scope, Operand<T> x) {
    OperationBuilder opBuilder = scope.env().opBuilder("DeepCopy", scope.makeOpName("DeepCopy"));
    opBuilder.addInput(x.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new DeepCopy<>(opBuilder.build());
  }

  /**
   * Gets y.
   * <pre>
   * y: A `Tensor` of type `T`. A copy of `x`. Guaranteed that `y`
   *   is not an alias of `x`.
   * </pre>
   * @return y.
   */
  public Output<T> y() {
    return y;
  }

  @Override
  public Output<T> asOutput() {
    return y;
  }
}
