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
import org.tensorflow.types.family.TNumber;

/**
 * Computes the mean along sparse segments of a tensor.
 * Like {@code SparseSegmentMean}, but allows missing ids in {@code segment_ids}. If an id is
 * missing, the {@code output} tensor at that position will be zeroed.
 * <p>Read
 *  <a href="https://tensorflow.org/api_docs/python/tf/math#Segmentation">the section on segmentation</a> 
 * for an explanation of segments.
 */
@OpMetadata(
    opType = SparseSegmentMeanWithNumSegments.OP_NAME,
    inputsClass = SparseSegmentMeanWithNumSegments.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseSegmentMeanWithNumSegments<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseSegmentMeanWithNumSegments";

  private Output<T> output;

  public SparseSegmentMeanWithNumSegments(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseSegmentMeanWithNumSegments operation.
   *
   * @param scope current scope
   * @param data The data value
   * @param indices A 1-D tensor. Has same rank as {@code segment_ids}.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param numSegments Should equal the number of distinct segment IDs.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseSegmentMeanWithNumSegments} output and operands
   * @return a new instance of SparseSegmentMeanWithNumSegments
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> SparseSegmentMeanWithNumSegments<T> create(Scope scope,
      Operand<T> data, Operand<? extends TNumber> indices, Operand<? extends TNumber> segmentIds,
      Operand<? extends TNumber> numSegments, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseSegmentMeanWithNumSegments");
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(segmentIds.asOutput());
    opBuilder.addInput(numSegments.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.sparseGradient != null) {
          opBuilder.setAttr("sparse_gradient", opts.sparseGradient);
        }
      }
    }
    return new SparseSegmentMeanWithNumSegments<>(opBuilder.build());
  }

  /**
   * Sets the sparseGradient option.
   *
   * @param sparseGradient the sparseGradient option
   * @return this Options instance.
   */
  public static Options sparseGradient(Boolean sparseGradient) {
    return new Options().sparseGradient(sparseGradient);
  }

  /**
   * Gets output.
   * Has same shape as data, except for dimension 0 which has size
   * {@code num_segments}.
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
   * Optional attributes for {@link org.tensorflow.op.sparse.SparseSegmentMeanWithNumSegments}
   */
  public static class Options {
    private Boolean sparseGradient;

    private Options() {
    }

    /**
     * Sets the sparseGradient option.
     *
     * @param sparseGradient the sparseGradient option
     * @return this Options instance.
     */
    public Options sparseGradient(Boolean sparseGradient) {
      this.sparseGradient = sparseGradient;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SparseSegmentMeanWithNumSegments.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<SparseSegmentMeanWithNumSegments<T>> {
    /**
     * The data input
     */
    public final Operand<T> data;

    /**
     * A 1-D tensor. Has same rank as {@code segment_ids}.
     */
    public final Operand<? extends TNumber> indices;

    /**
     * A 1-D tensor. Values should be sorted and can be repeated.
     */
    public final Operand<? extends TNumber> segmentIds;

    /**
     * Should equal the number of distinct segment IDs.
     */
    public final Operand<? extends TNumber> numSegments;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tidx attribute
     */
    public final DataType Tidx;

    /**
     * The Tnumsegments attribute
     */
    public final DataType Tnumsegments;

    /**
     * The Tsegmentids attribute
     */
    public final DataType Tsegmentids;

    /**
     * The sparseGradient attribute
     */
    public final boolean sparseGradient;

    public Inputs(GraphOperation op) {
      super(new SparseSegmentMeanWithNumSegments<>(op), op, Arrays.asList("T", "Tidx", "Tnumsegments", "Tsegmentids", "sparse_gradient"));
      int inputIndex = 0;
      data = (Operand<T>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      segmentIds = (Operand<? extends TNumber>) op.input(inputIndex++);
      numSegments = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tidx = op.attributes().getAttrType("Tidx");
      Tnumsegments = op.attributes().getAttrType("Tnumsegments");
      Tsegmentids = op.attributes().getAttrType("Tsegmentids");
      sparseGradient = op.attributes().getAttrBool("sparse_gradient");
    }
  }
}
