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
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.family.TNumber;

/**
 * The BatchMatrixSolveLs operation
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = BatchMatrixSolveLs.OP_NAME,
    inputsClass = BatchMatrixSolveLs.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class BatchMatrixSolveLs<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BatchMatrixSolveLs";

  private Output<T> output;

  public BatchMatrixSolveLs(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BatchMatrixSolveLs operation.
   *
   * @param scope current scope
   * @param matrix The matrix value
   * @param rhs The rhs value
   * @param l2Regularizer The l2Regularizer value
   * @param options carries optional attribute values
   * @param <T> data type for {@code BatchMatrixSolveLs} output and operands
   * @return a new instance of BatchMatrixSolveLs
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> BatchMatrixSolveLs<T> create(Scope scope, Operand<T> matrix,
      Operand<T> rhs, Operand<TFloat64> l2Regularizer, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BatchMatrixSolveLs");
    opBuilder.addInput(matrix.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder.addInput(l2Regularizer.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.fast != null) {
          opBuilder.setAttr("fast", opts.fast);
        }
      }
    }
    return new BatchMatrixSolveLs<>(opBuilder.build());
  }

  /**
   * Sets the fast option.
   *
   * @param fast the fast option
   * @return this Options instance.
   */
  public static Options fast(Boolean fast) {
    return new Options().fast(fast);
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
   * Optional attributes for {@link org.tensorflow.op.linalg.BatchMatrixSolveLs}
   */
  public static class Options {
    private Boolean fast;

    private Options() {
    }

    /**
     * Sets the fast option.
     *
     * @param fast the fast option
     * @return this Options instance.
     */
    public Options fast(Boolean fast) {
      this.fast = fast;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = BatchMatrixSolveLs.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<BatchMatrixSolveLs<T>> {
    /**
     * The matrix input
     */
    public final Operand<T> matrix;

    /**
     * The rhs input
     */
    public final Operand<T> rhs;

    /**
     * The l2Regularizer input
     */
    public final Operand<TFloat64> l2Regularizer;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The fast attribute
     */
    public final boolean fast;

    public Inputs(GraphOperation op) {
      super(new BatchMatrixSolveLs<>(op), op, Arrays.asList("T", "fast"));
      int inputIndex = 0;
      matrix = (Operand<T>) op.input(inputIndex++);
      rhs = (Operand<T>) op.input(inputIndex++);
      l2Regularizer = (Operand<TFloat64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      fast = op.attributes().getAttrBool("fast");
    }
  }
}
