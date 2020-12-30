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
import org.tensorflow.types.family.TType;

/**
 * Calculate product with tridiagonal matrix.
 * <p>
 * Calculates product of two matrices, where left matrix is a tridiagonal matrix.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class TridiagonalMatMul<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new TridiagonalMatMul operation.
   * 
   * @param scope current scope
   * @param superdiag Tensor of shape `[..., 1, M]`, representing superdiagonals of
   * tri-diagonal matrices to the left of multiplication. Last element is ignored.
   * @param maindiag Tensor of shape `[..., 1, M]`, representing main diagonals of tri-diagonal
   * matrices to the left of multiplication.
   * @param subdiag Tensor of shape `[..., 1, M]`, representing subdiagonals of tri-diagonal
   * matrices to the left of multiplication. First element is ignored.
   * @param rhs Tensor of shape `[..., M, N]`, representing MxN matrices to the right of
   * multiplication.
   * @return a new instance of TridiagonalMatMul
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TridiagonalMatMul<T> create(Scope scope, Operand<T> superdiag, Operand<T> maindiag, Operand<T> subdiag, Operand<T> rhs) {
    OperationBuilder opBuilder = scope.env().opBuilder("TridiagonalMatMul", scope.makeOpName("TridiagonalMatMul"));
    opBuilder.addInput(superdiag.asOutput());
    opBuilder.addInput(maindiag.asOutput());
    opBuilder.addInput(subdiag.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new TridiagonalMatMul<T>(opBuilder.build());
  }
  
  /**
   * Tensor of shape `[..., M, N]` containing the product.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TridiagonalMatMul";
  
  private Output<T> output;
  
  private TridiagonalMatMul(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
