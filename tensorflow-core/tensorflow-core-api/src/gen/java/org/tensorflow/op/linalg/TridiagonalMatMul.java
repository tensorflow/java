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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Calculate product with tridiagonal matrix.
 * Calculates product of two matrices, where left matrix is a tridiagonal matrix.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = TridiagonalMatMul.OP_NAME,
    inputsClass = TridiagonalMatMul.Inputs.class
)
public final class TridiagonalMatMul<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TridiagonalMatMul";

  private Output<T> output;

  public TridiagonalMatMul(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TridiagonalMatMul operation.
   *
   * @param scope current scope
   * @param superdiag Tensor of shape {@code [..., 1, M]}, representing superdiagonals of
   * tri-diagonal matrices to the left of multiplication. Last element is ignored.
   * @param maindiag Tensor of shape {@code [..., 1, M]}, representing main diagonals of tri-diagonal
   * matrices to the left of multiplication.
   * @param subdiag Tensor of shape {@code [..., 1, M]}, representing subdiagonals of tri-diagonal
   * matrices to the left of multiplication. First element is ignored.
   * @param rhs Tensor of shape {@code [..., M, N]}, representing MxN matrices to the right of
   * multiplication.
   * @param <T> data type for {@code TridiagonalMatMul} output and operands
   * @return a new instance of TridiagonalMatMul
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TridiagonalMatMul<T> create(Scope scope, Operand<T> superdiag,
      Operand<T> maindiag, Operand<T> subdiag, Operand<T> rhs) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TridiagonalMatMul");
    opBuilder.addInput(superdiag.asOutput());
    opBuilder.addInput(maindiag.asOutput());
    opBuilder.addInput(subdiag.asOutput());
    opBuilder.addInput(rhs.asOutput());
    return new TridiagonalMatMul<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Tensor of shape {@code [..., M, N]} containing the product.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = TridiagonalMatMul.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<TridiagonalMatMul<T>> {
    /**
     * Tensor of shape {@code [..., 1, M]}, representing superdiagonals of
     * tri-diagonal matrices to the left of multiplication. Last element is ignored.
     */
    public final Operand<T> superdiag;

    /**
     * Tensor of shape {@code [..., 1, M]}, representing main diagonals of tri-diagonal
     * matrices to the left of multiplication.
     */
    public final Operand<T> maindiag;

    /**
     * Tensor of shape {@code [..., 1, M]}, representing subdiagonals of tri-diagonal
     * matrices to the left of multiplication. First element is ignored.
     */
    public final Operand<T> subdiag;

    /**
     * Tensor of shape {@code [..., M, N]}, representing MxN matrices to the right of
     * multiplication.
     */
    public final Operand<T> rhs;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new TridiagonalMatMul<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      superdiag = (Operand<T>) op.input(inputIndex++);
      maindiag = (Operand<T>) op.input(inputIndex++);
      subdiag = (Operand<T>) op.input(inputIndex++);
      rhs = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
