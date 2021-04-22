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
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * <pre>
 * Subtracts `v` into specified rows of `x`.
 *
 * Computes y = x; y[i, :] -= v; return y.
 * </pre>
 *
 * @param <T> data type for {@code y} output
 */
@Operator
public final class InplaceSub<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "InplaceSub";

  private Output<T> y;

  private InplaceSub(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new InplaceSub operation.
   *
   * @param scope current scope
   * @param x A {@code Tensor} of type T.
   * @param i A vector. Indices into the left-most dimension of {@code x}.
   * @param v A {@code Tensor} of type T. Same dimension sizes as x except the first dimension, which must be the same as i's size.
   * @param <T> data type for {@code InplaceSub} output and operands
   * @return a new instance of InplaceSub
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> InplaceSub<T> create(Scope scope, Operand<T> x, Operand<TInt32> i,
      Operand<T> v) {
    OperationBuilder opBuilder = scope.env().opBuilder("InplaceSub", scope.makeOpName("InplaceSub"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(i.asOutput());
    opBuilder.addInput(v.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new InplaceSub<>(opBuilder.build());
  }

  /**
   * Gets y.
   * A {@code Tensor} of type T. An alias of {@code x}. The content of {@code y} is undefined if there are duplicates in {@code i}.
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
