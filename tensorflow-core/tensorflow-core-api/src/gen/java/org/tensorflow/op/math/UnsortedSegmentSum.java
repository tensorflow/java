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

package org.tensorflow.op.math;

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
import org.tensorflow.types.family.TType;

/**
 * Computes the sum along segments of a tensor.
 * Read
 *  <a href="https://tensorflow.org/api_docs/python/tf/math#Segmentation">the section on segmentation</a> 
 * for an explanation of segments.
 * <p>Computes a tensor such that
 * \(output[i] = \sum_{j...} data[j...]\) where the sum is over tuples {@code j...} such
 * that {@code segment_ids[j...] == i}.  Unlike {@code SegmentSum}, {@code segment_ids}
 * need not be sorted and need not cover all values in the full
 * range of valid values.
 * <p>If the sum is empty for a given segment ID {@code i}, {@code output[i] = 0}.
 * If the given segment ID {@code i} is negative, the value is dropped and will not be
 * added to the sum of the segment.
 * <p>{@code num_segments} should equal the number of distinct segment IDs.
 * <p>Caution: On CPU, values in {@code segment_ids} are always validated to be less than
 * {@code num_segments}, and an error is thrown for out-of-bound indices. On GPU, this
 * does not throw an error for out-of-bound indices. On Gpu, out-of-bound indices
 * result in safe but unspecified behavior, which may include ignoring
 * out-of-bound indices or outputting a tensor with a 0 stored in the first
 * dimension of its shape if {@code num_segments} is 0.
 * <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
 * <img style="width:100%" src="https://www.tensorflow.org/images/UnsortedSegmentSum.png" alt>
 * </div>
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>c = [[1,2,3,4], [5,6,7,8], [4,3,2,1]]
 * tf.math.unsorted_segment_sum(c, [0, 1, 0], num_segments=2).numpy()
 * array([[5, 5, 5, 5],
 * [5, 6, 7, 8]], dtype=int32)
 * </blockquote>
 * </blockquote>
 * </blockquote>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = UnsortedSegmentSum.OP_NAME,
    inputsClass = UnsortedSegmentSum.Inputs.class
)
@Operator(
    group = "math"
)
public final class UnsortedSegmentSum<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UnsortedSegmentSum";

  private Output<T> output;

  public UnsortedSegmentSum(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UnsortedSegmentSum operation.
   *
   * @param scope current scope
   * @param data The data value
   * @param segmentIds A tensor whose shape is a prefix of {@code data.shape}.
   * The values must be less than {@code num_segments}.
   * <p>Caution: The values are always validated to be in range on CPU, never validated
   * on GPU.
   * @param numSegments The numSegments value
   * @param <T> data type for {@code UnsortedSegmentSum} output and operands
   * @return a new instance of UnsortedSegmentSum
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> UnsortedSegmentSum<T> create(Scope scope, Operand<T> data,
      Operand<? extends TNumber> segmentIds, Operand<? extends TNumber> numSegments) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "UnsortedSegmentSum");
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(segmentIds.asOutput());
    opBuilder.addInput(numSegments.asOutput());
    return new UnsortedSegmentSum<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Has same shape as data, except for the first {@code segment_ids.rank}
   * dimensions, which are replaced with a single dimension which has size
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

  @OpInputsMetadata(
      outputsClass = UnsortedSegmentSum.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<UnsortedSegmentSum<T>> {
    /**
     * The data input
     */
    public final Operand<T> data;

    /**
     * A tensor whose shape is a prefix of {@code data.shape}.
     * The values must be less than {@code num_segments}.
     * <p>Caution: The values are always validated to be in range on CPU, never validated
     * on GPU.
     */
    public final Operand<? extends TNumber> segmentIds;

    /**
     * The numSegments input
     */
    public final Operand<? extends TNumber> numSegments;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    /**
     * The Tnumsegments attribute
     */
    public final DataType Tnumsegments;

    public Inputs(GraphOperation op) {
      super(new UnsortedSegmentSum<>(op), op, Arrays.asList("T", "Tindices", "Tnumsegments"));
      int inputIndex = 0;
      data = (Operand<T>) op.input(inputIndex++);
      segmentIds = (Operand<? extends TNumber>) op.input(inputIndex++);
      numSegments = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
      Tnumsegments = op.attributes().getAttrType("Tnumsegments");
    }
  }
}
