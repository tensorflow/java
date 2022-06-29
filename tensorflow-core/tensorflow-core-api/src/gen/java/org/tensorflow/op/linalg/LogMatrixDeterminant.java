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
 * Computes the sign and the log of the absolute value of the determinant of
 * one or more square matrices.
 * <p>The input is a tensor of shape {@code [N, M, M]} whose inner-most 2 dimensions
 * form square matrices. The outputs are two tensors containing the signs and
 * absolute values of the log determinants for all N input submatrices
 * {@code [..., :, :]} such that {@code determinant = sign*exp(log_abs_determinant)}.
 * The {@code log_abs_determinant} is computed as {@code det(P)*sum(log(diag(LU)))} where {@code LU}
 * is the {@code LU} decomposition of the input and {@code P} is the corresponding
 * permutation matrix.
 *
 * @param <T> data type for {@code sign} output
 */
@OpMetadata(
    opType = LogMatrixDeterminant.OP_NAME,
    inputsClass = LogMatrixDeterminant.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class LogMatrixDeterminant<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LogMatrixDeterminant";

  private Output<T> sign;

  private Output<T> logAbsDeterminant;

  public LogMatrixDeterminant(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sign = operation.output(outputIdx++);
    logAbsDeterminant = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LogMatrixDeterminant operation.
   *
   * @param scope current scope
   * @param input Shape is {@code [N, M, M]}.
   * @param <T> data type for {@code LogMatrixDeterminant} output and operands
   * @return a new instance of LogMatrixDeterminant
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> LogMatrixDeterminant<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LogMatrixDeterminant");
    opBuilder.addInput(input.asOutput());
    return new LogMatrixDeterminant<>(opBuilder.build());
  }

  /**
   * Gets sign.
   * The signs of the log determinants of the inputs. Shape is {@code [N]}.
   * @return sign.
   */
  public Output<T> sign() {
    return sign;
  }

  /**
   * Gets logAbsDeterminant.
   * The logs of the absolute values of the determinants
   * of the N input matrices.  Shape is {@code [N]}.
   * @return logAbsDeterminant.
   */
  public Output<T> logAbsDeterminant() {
    return logAbsDeterminant;
  }

  @OpInputsMetadata(
      outputsClass = LogMatrixDeterminant.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<LogMatrixDeterminant<T>> {
    /**
     * Shape is {@code [N, M, M]}.
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new LogMatrixDeterminant<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
