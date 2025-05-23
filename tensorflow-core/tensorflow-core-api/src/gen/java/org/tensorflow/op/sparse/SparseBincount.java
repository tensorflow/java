/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.sparse;

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Counts the number of occurrences of each value in an integer array.
 * Outputs a vector with length {@code size} and the same dtype as {@code weights}. If
 * {@code weights} are empty, then index {@code i} stores the number of times the value {@code i} is
 * counted in {@code arr}. If {@code weights} are non-empty, then index {@code i} stores the sum of
 * the value in {@code weights} at each index where the corresponding value in {@code arr} is
 * {@code i}.
 * <p>Values in {@code arr} outside of the range [0, size) are ignored.
 */
@OpMetadata(
    opType = SparseBincount.OP_NAME,
    inputsClass = SparseBincount.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseBincount<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseBincount";

  private Output<U> output;

  public SparseBincount(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseBincount operation.
   *
   * @param scope current scope
   * @param indices 2D int64 {@code Tensor}.
   * @param values 1D int {@code Tensor}.
   * @param denseShape 1D int64 {@code Tensor}.
   * @param sizeOutput non-negative int scalar {@code Tensor}.
   * @param weights is an int32, int64, float32, or float64 {@code Tensor} with the same
   * shape as {@code input}, or a length-0 {@code Tensor}, in which case it acts as all weights
   * equal to 1.
   * @param options carries optional attribute values
   * @param <U> data type for {@code SparseBincount} output and operands
   * @param <T> data type for {@code SparseBincount} output and operands
   * @return a new instance of SparseBincount
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber, T extends TNumber> SparseBincount<U> create(Scope scope,
      Operand<TInt64> indices, Operand<T> values, Operand<TInt64> denseShape, Operand<T> sizeOutput,
      Operand<U> weights, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseBincount");
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(denseShape.asOutput());
    opBuilder.addInput(sizeOutput.asOutput());
    opBuilder.addInput(weights.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.binaryOutput != null) {
          opBuilder.setAttr("binary_output", opts.binaryOutput);
        }
      }
    }
    return new SparseBincount<>(opBuilder.build());
  }

  /**
   * Sets the binaryOutput option.
   *
   * @param binaryOutput bool; Whether the kernel should count the appearance or number of occurrences.
   * @return this Options instance.
   */
  public static Options binaryOutput(Boolean binaryOutput) {
    return new Options().binaryOutput(binaryOutput);
  }

  /**
   * Gets output.
   * 1D {@code Tensor} with length equal to {@code size} or 2D {@code Tensor} with [batch_size, {@code size}].
   * The counts or summed weights for each value in the range [0, size).
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.SparseBincount}
   */
  public static class Options {
    private Boolean binaryOutput;

    private Options() {
    }

    /**
     * Sets the binaryOutput option.
     *
     * @param binaryOutput bool; Whether the kernel should count the appearance or number of occurrences.
     * @return this Options instance.
     */
    public Options binaryOutput(Boolean binaryOutput) {
      this.binaryOutput = binaryOutput;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SparseBincount.class
  )
  public static class Inputs<T extends TNumber, U extends TNumber> extends RawOpInputs<SparseBincount<U>> {
    /**
     * 2D int64 {@code Tensor}.
     */
    public final Operand<TInt64> indices;

    /**
     * 1D int {@code Tensor}.
     */
    public final Operand<T> values;

    /**
     * 1D int64 {@code Tensor}.
     */
    public final Operand<TInt64> denseShape;

    /**
     * non-negative int scalar {@code Tensor}.
     */
    public final Operand<T> sizeOutput;

    /**
     * is an int32, int64, float32, or float64 {@code Tensor} with the same
     * shape as {@code input}, or a length-0 {@code Tensor}, in which case it acts as all weights
     * equal to 1.
     */
    public final Operand<U> weights;

    /**
     * The Tidx attribute
     */
    public final DataType Tidx;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * bool; Whether the kernel should count the appearance or number of occurrences.
     */
    public final boolean binaryOutput;

    public Inputs(GraphOperation op) {
      super(new SparseBincount<>(op), op, Arrays.asList("Tidx", "T", "binary_output"));
      int inputIndex = 0;
      indices = (Operand<TInt64>) op.input(inputIndex++);
      values = (Operand<T>) op.input(inputIndex++);
      denseShape = (Operand<TInt64>) op.input(inputIndex++);
      sizeOutput = (Operand<T>) op.input(inputIndex++);
      weights = (Operand<U>) op.input(inputIndex++);
      Tidx = op.attributes().getAttrType("Tidx");
      T = op.attributes().getAttrType("T");
      binaryOutput = op.attributes().getAttrBool("binary_output");
    }
  }
}
