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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the reverse mode backpropagated gradient of the Cholesky algorithm.
 * For an explanation see &quot;Differentiation of the Cholesky algorithm&quot; by
 * Iain Murray http://arxiv.org/abs/1602.07527.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = CholeskyGrad.OP_NAME,
    inputsClass = CholeskyGrad.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class CholeskyGrad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "CholeskyGrad";

  private Output<T> output;

  public CholeskyGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new CholeskyGrad operation.
   *
   * @param scope current scope
   * @param l Output of batch Cholesky algorithm l = cholesky(A). Shape is {@code [..., M, M]}.
   * Algorithm depends only on lower triangular part of the innermost matrices of
   * this tensor.
   * @param grad df/dl where f is some scalar function. Shape is {@code [..., M, M]}.
   * Algorithm depends only on lower triangular part of the innermost matrices of
   * this tensor.
   * @param <T> data type for {@code CholeskyGrad} output and operands
   * @return a new instance of CholeskyGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> CholeskyGrad<T> create(Scope scope, Operand<T> l,
      Operand<T> grad) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "CholeskyGrad");
    opBuilder.addInput(l.asOutput());
    opBuilder.addInput(grad.asOutput());
    return new CholeskyGrad<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Symmetrized version of df/dA . Shape is {@code [..., M, M]}
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
      outputsClass = CholeskyGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<CholeskyGrad<T>> {
    /**
     * Output of batch Cholesky algorithm l = cholesky(A). Shape is {@code [..., M, M]}.
     * Algorithm depends only on lower triangular part of the innermost matrices of
     * this tensor.
     */
    public final Operand<T> l;

    /**
     * df/dl where f is some scalar function. Shape is {@code [..., M, M]}.
     * Algorithm depends only on lower triangular part of the innermost matrices of
     * this tensor.
     */
    public final Operand<T> grad;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new CholeskyGrad<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      l = (Operand<T>) op.input(inputIndex++);
      grad = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
