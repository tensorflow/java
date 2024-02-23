/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
 * The BatchMatrixTriangularSolve operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = BatchMatrixTriangularSolve.OP_NAME,
    inputsClass = BatchMatrixTriangularSolve.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class BatchMatrixTriangularSolve<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BatchMatrixTriangularSolve";

  private Output<T> output;

  public BatchMatrixTriangularSolve(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BatchMatrixTriangularSolve operation.
   *
   * @param scope current scope
   * @param matrix The matrix value
   * @param rhs The rhs value
   * @param options carries optional attribute values
   * @param <T> data type for {@code BatchMatrixTriangularSolve} output and operands
   * @return a new instance of BatchMatrixTriangularSolve
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> BatchMatrixTriangularSolve<T> create(Scope scope,
      Operand<T> matrix, Operand<T> rhs, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BatchMatrixTriangularSolve");
    opBuilder.addInput(matrix.asOutput());
    opBuilder.addInput(rhs.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.lower != null) {
          opBuilder.setAttr("lower", opts.lower);
        }
        if (opts.adjoint != null) {
          opBuilder.setAttr("adjoint", opts.adjoint);
        }
      }
    }
    return new BatchMatrixTriangularSolve<>(opBuilder.build());
  }

  /**
   * Sets the lower option.
   *
   * @param lower the lower option
   * @return this Options instance.
   */
  public static Options lower(Boolean lower) {
    return new Options().lower(lower);
  }

  /**
   * Sets the adjoint option.
   *
   * @param adjoint the adjoint option
   * @return this Options instance.
   */
  public static Options adjoint(Boolean adjoint) {
    return new Options().adjoint(adjoint);
  }

  /**
   * Gets output.
   *
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
   * Optional attributes for {@link org.tensorflow.op.linalg.BatchMatrixTriangularSolve}
   */
  public static class Options {
    private Boolean lower;

    private Boolean adjoint;

    private Options() {
    }

    /**
     * Sets the lower option.
     *
     * @param lower the lower option
     * @return this Options instance.
     */
    public Options lower(Boolean lower) {
      this.lower = lower;
      return this;
    }

    /**
     * Sets the adjoint option.
     *
     * @param adjoint the adjoint option
     * @return this Options instance.
     */
    public Options adjoint(Boolean adjoint) {
      this.adjoint = adjoint;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = BatchMatrixTriangularSolve.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<BatchMatrixTriangularSolve<T>> {
    /**
     * The matrix input
     */
    public final Operand<T> matrix;

    /**
     * The rhs input
     */
    public final Operand<T> rhs;

    /**
     * The lower attribute
     */
    public final boolean lower;

    /**
     * The adjoint attribute
     */
    public final boolean adjoint;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new BatchMatrixTriangularSolve<>(op), op, Arrays.asList("lower", "adjoint", "T"));
      int inputIndex = 0;
      matrix = (Operand<T>) op.input(inputIndex++);
      rhs = (Operand<T>) op.input(inputIndex++);
      lower = op.attributes().getAttrBool("lower");
      adjoint = op.attributes().getAttrBool("adjoint");
      T = op.attributes().getAttrType("T");
    }
  }
}
