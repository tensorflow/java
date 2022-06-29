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
import org.tensorflow.types.family.TNumber;

/**
 * Computes the sum along sparse segments of a tensor.
 * Like {@code SparseSegmentSum}, but allows missing ids in {@code segment_ids}. If an id is
 * missing, the {@code output} tensor at that position will be zeroed.
 * <p>Read
 *  <a href="https://tensorflow.org/api_docs/python/tf/sparse#Segmentation">the section on segmentation</a> 
 * for an explanation of segments.
 * <p>For example:
 * <pre>
 * c = tf.constant([[1,2,3,4], [-1,-2,-3,-4], [5,6,7,8]])
 *
 * tf.sparse_segment_sum_with_num_segments(
 *     c, tf.constant([0, 1]), tf.constant([0, 0]), num_segments=3)
 * # =&gt; [[0 0 0 0]
 * #     [0 0 0 0]
 * #     [0 0 0 0]]
 *
 * tf.sparse_segment_sum_with_num_segments(c,
 *                                         tf.constant([0, 1]),
 *                                         tf.constant([0, 2],
 *                                         num_segments=4))
 * # =&gt; [[ 1  2  3  4]
 * #     [ 0  0  0  0]
 * #     [-1 -2 -3 -4]
 * #     [ 0  0  0  0]]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = SparseSegmentSumWithNumSegments.OP_NAME,
    inputsClass = SparseSegmentSumWithNumSegments.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseSegmentSumWithNumSegments<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseSegmentSumWithNumSegments";

  private Output<T> output;

  public SparseSegmentSumWithNumSegments(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseSegmentSumWithNumSegments operation.
   *
   * @param scope current scope
   * @param data The data value
   * @param indices A 1-D tensor. Has same rank as {@code segment_ids}.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param numSegments Should equal the number of distinct segment IDs.
   * @param <T> data type for {@code SparseSegmentSumWithNumSegments} output and operands
   * @return a new instance of SparseSegmentSumWithNumSegments
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> SparseSegmentSumWithNumSegments<T> create(Scope scope,
      Operand<T> data, Operand<? extends TNumber> indices, Operand<? extends TNumber> segmentIds,
      Operand<? extends TNumber> numSegments) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseSegmentSumWithNumSegments");
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(segmentIds.asOutput());
    opBuilder.addInput(numSegments.asOutput());
    return new SparseSegmentSumWithNumSegments<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Has same shape as data, except for dimension 0 which
   * has size {@code num_segments}.
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
      outputsClass = SparseSegmentSumWithNumSegments.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<SparseSegmentSumWithNumSegments<T>> {
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

    public Inputs(GraphOperation op) {
      super(new SparseSegmentSumWithNumSegments<>(op), op, Arrays.asList("T", "Tidx", "Tnumsegments", "Tsegmentids"));
      int inputIndex = 0;
      data = (Operand<T>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      segmentIds = (Operand<? extends TNumber>) op.input(inputIndex++);
      numSegments = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tidx = op.attributes().getAttrType("Tidx");
      Tnumsegments = op.attributes().getAttrType("Tnumsegments");
      Tsegmentids = op.attributes().getAttrType("Tsegmentids");
    }
  }
}
