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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Computes the minimum along segments of a tensor.
 * <p>
 * Read
 * [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
 * for an explanation of segments.
 * <p>
 * Computes a tensor such that
 * \\(output_i = \min_j(data_j)\\) where `min` is over `j` such
 * that `segment_ids[j] == i`.
 * <p>
 * If the min is empty for a given segment ID `i`, `output[i] = 0`.
 * <p>
 * <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
 * <img style="width:100%" src="https://www.tensorflow.org/images/SegmentMin.png" alt>
 * </div>
 * <p>
 * For example:
 * <pre>{@code
 * c = tf.constant([[1,2,3,4], [4, 3, 2, 1], [5,6,7,8]])
 * tf.segment_min(c, tf.constant([0, 0, 1]))
 * # ==> [[1, 2, 2, 1],
 * #      [5, 6, 7, 8]]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "math")
public final class SegmentMin<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SegmentMin operation.
   * 
   * @param scope current scope
   * @param data 
   * @param segmentIds A 1-D tensor whose size is equal to the size of `data`'s
   * first dimension.  Values should be sorted and can be repeated.
   * @return a new instance of SegmentMin
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber> SegmentMin<T> create(Scope scope, Operand<T> data, Operand<U> segmentIds) {
    OperationBuilder opBuilder = scope.env().opBuilder("SegmentMin", scope.makeOpName("SegmentMin"));
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(segmentIds.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SegmentMin<T>(opBuilder.build());
  }
  
  /**
   * Has same shape as data, except for dimension 0 which
   * has size `k`, the number of segments.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SegmentMin";
  
  private Output<T> output;
  
  private SegmentMin(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
