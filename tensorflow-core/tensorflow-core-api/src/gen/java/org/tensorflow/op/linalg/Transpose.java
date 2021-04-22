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

package org.tensorflow.op.linalg;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Shuffle dimensions of x according to a permutation.
 * The output {@code y} has the same rank as {@code x}. The shapes of {@code x} and {@code y} satisfy:
 * {@code y.shape[i] == x.shape[perm[i]] for i in [0, 1, ..., rank(x) - 1]}
 *
 * @param <T> data type for {@code y} output
 */
@Operator(
    group = "linalg"
)
public final class Transpose<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Transpose";

  private Output<T> y;

  private Transpose(Operation operation) {
    super(operation);
    int outputIdx = 0;
    y = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Transpose operation.
   *
   * @param scope current scope
   * @param x the x value
   * @param perm the perm value
   * @param <T> data type for {@code Transpose} output and operands
   * @return a new instance of Transpose
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Transpose<T> create(Scope scope, Operand<T> x,
      Operand<? extends TNumber> perm) {
    OperationBuilder opBuilder = scope.env().opBuilder("Transpose", scope.makeOpName("Transpose"));
    opBuilder.addInput(x.asOutput());
    opBuilder.addInput(perm.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Transpose<>(opBuilder.build());
  }

  /**
   * Gets y.
   *
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
