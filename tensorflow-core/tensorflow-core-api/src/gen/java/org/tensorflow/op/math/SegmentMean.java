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
 * Computes the mean along segments of a tensor.
 * Read
 *  <a href="https://tensorflow.org/api_docs/python/tf/math#Segmentation">the section on segmentation</a> 
 * for an explanation of segments.
 * <p>Computes a tensor such that
 * \(output_i = \frac{\sum_j data_j}{N}\) where {@code mean} is
 * over {@code j} such that {@code segment_ids[j] == i} and {@code N} is the total number of
 * values summed.
 * <p>If the mean is empty for a given segment ID {@code i}, {@code output[i] = 0}.
 * <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
 * <img style="width:100%" src="https://www.tensorflow.org/images/SegmentMean.png" alt>
 * </div>
 * <p>For example:
 * <pre>
 * c = tf.constant([[1.0,2,3,4], [4, 3, 2, 1], [5,6,7,8]])
 * tf.segment_mean(c, tf.constant([0, 0, 1]))
 * # ==&gt; [[2.5, 2.5, 2.5, 2.5],
 * #      [5, 6, 7, 8]]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = SegmentMean.OP_NAME,
    inputsClass = SegmentMean.Inputs.class
)
@Operator(
    group = "math"
)
public final class SegmentMean<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SegmentMean";

  private Output<T> output;

  public SegmentMean(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SegmentMean operation.
   *
   * @param scope current scope
   * @param data The data value
   * @param segmentIds A 1-D tensor whose size is equal to the size of {@code data}'s
   * first dimension.  Values should be sorted and can be repeated.
   * @param <T> data type for {@code SegmentMean} output and operands
   * @return a new instance of SegmentMean
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SegmentMean<T> create(Scope scope, Operand<T> data,
      Operand<? extends TNumber> segmentIds) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SegmentMean");
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(segmentIds.asOutput());
    return new SegmentMean<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Has same shape as data, except for dimension 0 which
   * has size {@code k}, the number of segments.
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
      outputsClass = SegmentMean.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SegmentMean<T>> {
    /**
     * The data input
     */
    public final Operand<T> data;

    /**
     * A 1-D tensor whose size is equal to the size of {@code data}'s
     * first dimension.  Values should be sorted and can be repeated.
     */
    public final Operand<? extends TNumber> segmentIds;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    public Inputs(GraphOperation op) {
      super(new SegmentMean<>(op), op, Arrays.asList("T", "Tindices"));
      int inputIndex = 0;
      data = (Operand<T>) op.input(inputIndex++);
      segmentIds = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
