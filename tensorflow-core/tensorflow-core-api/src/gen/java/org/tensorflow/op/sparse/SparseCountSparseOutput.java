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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Performs sparse-output bin counting for a sparse tensor input.
 * Counts the number of times each value occurs in the input.
 *
 * @param <U> data type for {@code output_values} output
 */
@OpMetadata(
    opType = SparseCountSparseOutput.OP_NAME,
    inputsClass = SparseCountSparseOutput.Inputs.class
)
public final class SparseCountSparseOutput<U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseCountSparseOutput";

  private Output<TInt64> outputIndices;

  private Output<U> outputValues;

  private Output<TInt64> outputDenseShape;

  public SparseCountSparseOutput(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
    outputDenseShape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseCountSparseOutput operation.
   *
   * @param scope current scope
   * @param indices Tensor containing the indices of the sparse tensor to count.
   * @param values Tensor containing values of the sparse tensor to count.
   * @param denseShape Tensor containing the dense shape of the sparse tensor to count.
   * @param weights A Tensor of the same shape as indices containing per-index weight values.
   * May also be the empty tensor if no weights are used.
   * @param binaryOutput Whether to output the number of occurrences of each value or 1.
   * @param options carries optional attribute values
   * @param <U> data type for {@code SparseCountSparseOutput} output and operands
   * @return a new instance of SparseCountSparseOutput
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> SparseCountSparseOutput<U> create(Scope scope,
      Operand<TInt64> indices, Operand<? extends TNumber> values, Operand<TInt64> denseShape,
      Operand<U> weights, Boolean binaryOutput, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseCountSparseOutput");
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(denseShape.asOutput());
    opBuilder.addInput(weights.asOutput());
    opBuilder.setAttr("binary_output", binaryOutput);
    if (options != null) {
      for (Options opts : options) {
        if (opts.minlength != null) {
          opBuilder.setAttr("minlength", opts.minlength);
        }
        if (opts.maxlength != null) {
          opBuilder.setAttr("maxlength", opts.maxlength);
        }
      }
    }
    return new SparseCountSparseOutput<>(opBuilder.build());
  }

  /**
   * Sets the minlength option.
   *
   * @param minlength Minimum value to count. Can be set to -1 for no minimum.
   * @return this Options instance.
   */
  public static Options minlength(Long minlength) {
    return new Options().minlength(minlength);
  }

  /**
   * Sets the maxlength option.
   *
   * @param maxlength Maximum value to count. Can be set to -1 for no maximum.
   * @return this Options instance.
   */
  public static Options maxlength(Long maxlength) {
    return new Options().maxlength(maxlength);
  }

  /**
   * Gets outputIndices.
   * Indices tensor for the resulting sparse tensor object.
   * @return outputIndices.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }

  /**
   * Gets outputValues.
   * Values tensor for the resulting sparse tensor object.
   * @return outputValues.
   */
  public Output<U> outputValues() {
    return outputValues;
  }

  /**
   * Gets outputDenseShape.
   * Shape tensor for the resulting sparse tensor object.
   * @return outputDenseShape.
   */
  public Output<TInt64> outputDenseShape() {
    return outputDenseShape;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.SparseCountSparseOutput}
   */
  public static class Options {
    private Long minlength;

    private Long maxlength;

    private Options() {
    }

    /**
     * Sets the minlength option.
     *
     * @param minlength Minimum value to count. Can be set to -1 for no minimum.
     * @return this Options instance.
     */
    public Options minlength(Long minlength) {
      this.minlength = minlength;
      return this;
    }

    /**
     * Sets the maxlength option.
     *
     * @param maxlength Maximum value to count. Can be set to -1 for no maximum.
     * @return this Options instance.
     */
    public Options maxlength(Long maxlength) {
      this.maxlength = maxlength;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SparseCountSparseOutput.class
  )
  public static class Inputs<U extends TNumber> extends RawOpInputs<SparseCountSparseOutput<U>> {
    /**
     * Tensor containing the indices of the sparse tensor to count.
     */
    public final Operand<TInt64> indices;

    /**
     * Tensor containing values of the sparse tensor to count.
     */
    public final Operand<? extends TNumber> values;

    /**
     * Tensor containing the dense shape of the sparse tensor to count.
     */
    public final Operand<TInt64> denseShape;

    /**
     * A Tensor of the same shape as indices containing per-index weight values.
     * May also be the empty tensor if no weights are used.
     */
    public final Operand<U> weights;

    /**
     * Dtype of the input values tensor.
     */
    public final DataType T;

    /**
     * Minimum value to count. Can be set to -1 for no minimum.
     */
    public final long minlength;

    /**
     * Maximum value to count. Can be set to -1 for no maximum.
     */
    public final long maxlength;

    /**
     * Whether to output the number of occurrences of each value or 1.
     */
    public final boolean binaryOutput;

    /**
     * Dtype of the output values tensor.
     */
    public final DataType outputType;

    public Inputs(GraphOperation op) {
      super(new SparseCountSparseOutput<>(op), op, Arrays.asList("T", "minlength", "maxlength", "binary_output", "output_type"));
      int inputIndex = 0;
      indices = (Operand<TInt64>) op.input(inputIndex++);
      values = (Operand<? extends TNumber>) op.input(inputIndex++);
      denseShape = (Operand<TInt64>) op.input(inputIndex++);
      weights = (Operand<U>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      minlength = op.attributes().getAttrInt("minlength");
      maxlength = op.attributes().getAttrInt("maxlength");
      binaryOutput = op.attributes().getAttrBool("binary_output");
      outputType = op.attributes().getAttrType("output_type");
    }
  }
}
