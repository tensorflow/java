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
import org.tensorflow.types.family.TType;

/**
 * Computes the Cholesky decomposition of one or more square matrices.
 * The input is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
 * form square matrices.
 * <p>The input has to be symmetric and positive definite. Only the lower-triangular
 * part of the input will be used for this operation. The upper-triangular part
 * will not be read.
 * <p>The output is a tensor of the same shape as the input
 * containing the Cholesky decompositions for all input submatrices {@code [..., :, :]}.
 * <p><strong>Note</strong>: The gradient computation on GPU is faster for large matrices but
 * not for large batch dimensions when the submatrices are small. In this
 * case it might be faster to use the CPU.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Cholesky.OP_NAME,
    inputsClass = Cholesky.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class Cholesky<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Cholesky";

  private Output<T> output;

  public Cholesky(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Cholesky operation.
   *
   * @param scope current scope
   * @param input Shape is {@code [..., M, M]}.
   * @param <T> data type for {@code Cholesky} output and operands
   * @return a new instance of Cholesky
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Cholesky<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Cholesky");
    opBuilder.addInput(input.asOutput());
    return new Cholesky<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Shape is {@code [..., M, M]}.
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
      outputsClass = Cholesky.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Cholesky<T>> {
    /**
     * Shape is {@code [..., M, M]}.
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Cholesky<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
