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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Computes the sum of elements across dimensions of a SparseTensor.
 * This Op takes a SparseTensor and is the sparse counterpart to
 * {@code tf.reduce_sum()}.  In contrast to SparseReduceSum, this Op returns a
 * SparseTensor.
 * <p>Reduces {@code sp_input} along the dimensions given in {@code reduction_axes}.  Unless
 * {@code keep_dims} is true, the rank of the tensor is reduced by 1 for each entry in
 * {@code reduction_axes}. If {@code keep_dims} is true, the reduced dimensions are retained
 * with length 1.
 * <p>If {@code reduction_axes} has no entries, all dimensions are reduced, and a tensor
 * with a single element is returned.  Additionally, the axes can be negative,
 * which are interpreted according to the indexing rules in Python.
 *
 * @param <T> data type for {@code output_values} output
 */
@OpMetadata(
    opType = SparseReduceSumSparse.OP_NAME,
    inputsClass = SparseReduceSumSparse.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseReduceSumSparse<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseReduceSumSparse";

  private Output<TInt64> outputIndices;

  private Output<T> outputValues;

  private Output<TInt64> outputShape;

  public SparseReduceSumSparse(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
    outputShape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseReduceSumSparse operation.
   *
   * @param scope current scope
   * @param inputIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   * SparseTensor, possibly not in canonical ordering.
   * @param inputValues 1-D.  {@code N} non-empty values corresponding to {@code input_indices}.
   * @param inputShape 1-D.  Shape of the input SparseTensor.
   * @param reductionAxes 1-D.  Length-{@code K} vector containing the reduction axes.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseReduceSumSparse} output and operands
   * @return a new instance of SparseReduceSumSparse
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseReduceSumSparse<T> create(Scope scope,
      Operand<TInt64> inputIndices, Operand<T> inputValues, Operand<TInt64> inputShape,
      Operand<TInt32> reductionAxes, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseReduceSumSparse");
    opBuilder.addInput(inputIndices.asOutput());
    opBuilder.addInput(inputValues.asOutput());
    opBuilder.addInput(inputShape.asOutput());
    opBuilder.addInput(reductionAxes.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.keepDims != null) {
          opBuilder.setAttr("keep_dims", opts.keepDims);
        }
      }
    }
    return new SparseReduceSumSparse<>(opBuilder.build());
  }

  /**
   * Sets the keepDims option.
   *
   * @param keepDims If true, retain reduced dimensions with length 1.
   * @return this Options instance.
   */
  public static Options keepDims(Boolean keepDims) {
    return new Options().keepDims(keepDims);
  }

  /**
   * Gets outputIndices.
   *
   * @return outputIndices.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }

  /**
   * Gets outputValues.
   *
   * @return outputValues.
   */
  public Output<T> outputValues() {
    return outputValues;
  }

  /**
   * Gets outputShape.
   *
   * @return outputShape.
   */
  public Output<TInt64> outputShape() {
    return outputShape;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.SparseReduceSumSparse}
   */
  public static class Options {
    private Boolean keepDims;

    private Options() {
    }

    /**
     * Sets the keepDims option.
     *
     * @param keepDims If true, retain reduced dimensions with length 1.
     * @return this Options instance.
     */
    public Options keepDims(Boolean keepDims) {
      this.keepDims = keepDims;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SparseReduceSumSparse.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SparseReduceSumSparse<T>> {
    /**
     * 2-D.  {@code N x R} matrix with the indices of non-empty values in a
     * SparseTensor, possibly not in canonical ordering.
     */
    public final Operand<TInt64> inputIndices;

    /**
     * 1-D.  {@code N} non-empty values corresponding to {@code input_indices}.
     */
    public final Operand<T> inputValues;

    /**
     * 1-D.  Shape of the input SparseTensor.
     */
    public final Operand<TInt64> inputShape;

    /**
     * 1-D.  Length-{@code K} vector containing the reduction axes.
     */
    public final Operand<TInt32> reductionAxes;

    /**
     * If true, retain reduced dimensions with length 1.
     */
    public final boolean keepDims;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseReduceSumSparse<>(op), op, Arrays.asList("keep_dims", "T"));
      int inputIndex = 0;
      inputIndices = (Operand<TInt64>) op.input(inputIndex++);
      inputValues = (Operand<T>) op.input(inputIndex++);
      inputShape = (Operand<TInt64>) op.input(inputIndex++);
      reductionAxes = (Operand<TInt32>) op.input(inputIndex++);
      keepDims = op.attributes().getAttrBool("keep_dims");
      T = op.attributes().getAttrType("T");
    }
  }
}
