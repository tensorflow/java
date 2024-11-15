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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Computes the product along segments of a tensor.
 * Read
 *  <a href="https://tensorflow.org/api_docs/python/tf/math#Segmentation">the section on segmentation</a> 
 * for an explanation of segments.
 * <p>Computes a tensor such that
 * \(output_i = \prod_j data_j\) where the product is over {@code j} such
 * that {@code segment_ids[j] == i}.
 * <p>If the product is empty for a given segment ID {@code i}, {@code output[i] = 1}.
 * <p>Note: That this op is currently only supported with jit_compile=True.
 * <p>The only difference with SegmentProd is the additional input  {@code num_segments}.
 * This helps in evaluating the output shape in compile time.
 * {@code num_segments} should be consistent with segment_ids.
 * e.g. Max(segment_ids) - 1 should be equal to {@code num_segments} for a 1-d segment_ids
 * With inconsistent num_segments, the op still runs. only difference is,
 * the output takes the size of num_segments irrespective of size of segment_ids and data.
 * for num_segments less than expected output size, the last elements are ignored
 * for num_segments more than the expected output size, last elements are assigned 1.
 * <p>For example:
 * <blockquote>
 * <blockquote>
 * <blockquote>
 * <p>{@literal @}tf.function(jit_compile=True)
 * ... def test(c):
 * ...   return tf.raw_ops.SegmentProdV2(data=c, segment_ids=tf.constant([0, 0, 1]), num_segments=2)
 * c = tf.constant([[1,2,3,4], [4, 3, 2, 1], [5,6,7,8]])
 * test(c).numpy()
 * array([[4, 6, 6, 4],
 * [5, 6, 7, 8]], dtype=int32)
 * </blockquote>
 * </blockquote>
 * </blockquote>
 */
@OpMetadata(
    opType = SegmentProd.OP_NAME,
    inputsClass = SegmentProd.Inputs.class
)
@Operator(
    group = "math"
)
public final class SegmentProd<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SegmentProdV2";

  private Output<T> output;

  public SegmentProd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SegmentProdV2 operation.
   *
   * @param scope current scope
   * @param data The data value
   * @param segmentIds A 1-D tensor whose size is equal to the size of {@code data}'s
   * first dimension.  Values should be sorted and can be repeated.
   * The values must be less than {@code num_segments}.
   * <p>Caution: The values are always validated to be sorted on CPU, never validated
   * on GPU.
   * @param numSegments The numSegments value
   * @param <T> data type for {@code SegmentProdV2} output and operands
   * @return a new instance of SegmentProd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SegmentProd<T> create(Scope scope, Operand<T> data,
      Operand<? extends TNumber> segmentIds, Operand<? extends TNumber> numSegments) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SegmentProd");
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(segmentIds.asOutput());
    opBuilder.addInput(numSegments.asOutput());
    return new SegmentProd<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Has same shape as data, except for the first {@code segment_ids.rank}
   * dimensions, which are replaced with a single dimensionw which has size
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
      outputsClass = SegmentProd.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SegmentProd<T>> {
    /**
     * The data input
     */
    public final Operand<T> data;

    /**
     * A 1-D tensor whose size is equal to the size of {@code data}'s
     * first dimension.  Values should be sorted and can be repeated.
     * The values must be less than {@code num_segments}.
     * <p>Caution: The values are always validated to be sorted on CPU, never validated
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
      super(new SegmentProd<>(op), op, Arrays.asList("T", "Tindices", "Tnumsegments"));
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
