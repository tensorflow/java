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
 * Computes the determinant of one or more square matrices.
 * The input is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
 * form square matrices. The output is a tensor containing the determinants
 * for all input submatrices {@code [..., :, :]}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Det.OP_NAME,
    inputsClass = Det.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class Det<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MatrixDeterminant";

  private Output<T> output;

  public Det(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MatrixDeterminant operation.
   *
   * @param scope current scope
   * @param input Shape is {@code [..., M, M]}.
   * @param <T> data type for {@code MatrixDeterminant} output and operands
   * @return a new instance of Det
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Det<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Det");
    opBuilder.addInput(input.asOutput());
    return new Det<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Shape is {@code [...]}.
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
      outputsClass = Det.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Det<T>> {
    /**
     * Shape is {@code [..., M, M]}.
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Det<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
