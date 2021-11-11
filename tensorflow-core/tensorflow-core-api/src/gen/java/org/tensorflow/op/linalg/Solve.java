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
 * Solves systems of linear equations.
 * {@code Matrix} is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions
 * form square matrices. {@code Rhs} is a tensor of shape {@code [..., M, K]}. The {@code output} is
 * a tensor shape {@code [..., M, K]}.  If {@code adjoint} is {@code False} then each output matrix
 * satisfies {@code matrix[..., :, :] * output[..., :, :] = rhs[..., :, :]}.
 * If {@code adjoint} is {@code True} then each output matrix satisfies
 * {@code adjoint(matrix[..., :, :]) * output[..., :, :] = rhs[..., :, :]}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Solve.OP_NAME,
    inputsClass = Solve.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class Solve<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MatrixSolve";

  private Output<T> output;

  public Solve(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MatrixSolve operation.
   *
   * @param scope current scope
   * @param matrix Shape is {@code [..., M, M]}.
   * @param rhs Shape is {@code [..., M, K]}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatrixSolve} output and operands
   * @return a new instance of Solve
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Solve<T> create(Scope scope, Operand<T> matrix, Operand<T> rhs,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Solve");
    opBuilder.addInput(matrix.asOutput());
    opBuilder.addInput(rhs.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.adjoint != null) {
          opBuilder.setAttr("adjoint", opts.adjoint);
        }
      }
    }
    return new Solve<>(opBuilder.build());
  }

  /**
   * Sets the adjoint option.
   *
   * @param adjoint Boolean indicating whether to solve with {@code matrix} or its (block-wise)
   * adjoint.
   * @return this Options instance.
   */
  public static Options adjoint(Boolean adjoint) {
    return new Options().adjoint(adjoint);
  }

  /**
   * Gets output.
   * Shape is {@code [..., M, K]}.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.Solve}
   */
  public static class Options {
    private Boolean adjoint;

    private Options() {
    }

    /**
     * Sets the adjoint option.
     *
     * @param adjoint Boolean indicating whether to solve with {@code matrix} or its (block-wise)
     * adjoint.
     * @return this Options instance.
     */
    public Options adjoint(Boolean adjoint) {
      this.adjoint = adjoint;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Solve.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Solve<T>> {
    /**
     * Shape is {@code [..., M, M]}.
     */
    public final Operand<T> matrix;

    /**
     * Shape is {@code [..., M, K]}.
     */
    public final Operand<T> rhs;

    /**
     * Boolean indicating whether to solve with `matrix` or its (block-wise)
     * adjoint.
     */
    public final boolean adjoint;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Solve<>(op), op, Arrays.asList("adjoint", "T"));
      int inputIndex = 0;
      matrix = (Operand<T>) op.input(inputIndex++);
      rhs = (Operand<T>) op.input(inputIndex++);
      adjoint = op.attributes().getAttrBool("adjoint");
      T = op.attributes().getAttrType("T");
    }
  }
}
