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

/**
 * Compute the pairwise cross product.
 * {@code a} and {@code b} must be the same shape; they can either be simple 3-element vectors,
 * or any shape where the innermost dimension is 3. In the latter case, each pair
 * of corresponding 3-element vectors is cross-multiplied independently.
 *
 * @param <T> data type for {@code product} output
 */
@Operator(
    group = "linalg"
)
public final class Cross<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Cross";

  private Output<T> product;

  private Cross(Operation operation) {
    super(operation);
    int outputIdx = 0;
    product = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Cross operation.
   *
   * @param scope current scope
   * @param a A tensor containing 3-element vectors.
   * @param b Another tensor, of same type and shape as {@code a}.
   * @param <T> data type for {@code Cross} output and operands
   * @return a new instance of Cross
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Cross<T> create(Scope scope, Operand<T> a, Operand<T> b) {
    OperationBuilder opBuilder = scope.env().opBuilder("Cross", scope.makeOpName("Cross"));
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Cross<>(opBuilder.build());
  }

  /**
   * Gets product.
   * Pairwise cross product of the vectors in {@code a} and {@code b}.
   * @return product.
   */
  public Output<T> product() {
    return product;
  }

  @Override
  public Output<T> asOutput() {
    return product;
  }
}
