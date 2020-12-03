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
 * Computes the maximum along segments of a tensor.
 * <p>
 * Read
 * [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
 * for an explanation of segments.
 * <p>
 * This operator is similar to the unsorted segment sum operator found
 * [(here)](../../../api_docs/python/math_ops.md#UnsortedSegmentSum).
 * Instead of computing the sum over segments, it computes the maximum such that:
 * <p>
 * \\(output_i = \max_{j...} data[j...]\\) where max is over tuples `j...` such
 * that `segment_ids[j...] == i`.
 * <p>
 * If the maximum is empty for a given segment ID `i`, it outputs the smallest
 * possible value for the specific numeric type,
 * `output[i] = numeric_limits<T>::lowest()`.
 * <p>
 * If the given segment ID `i` is negative, then the corresponding value is
 * dropped, and will not be included in the result.
 * <p>
 * <div style="width:70%; margin:auto; margin-bottom:10px; margin-top:20px;">
 * <img style="width:100%" src="https://www.tensorflow.org/images/UnsortedSegmentMax.png" alt>
 * </div>
 * <p>
 * For example:
 * <pre>{@code
 * c = tf.constant([[1,2,3,4], [5,6,7,8], [4,3,2,1]])
 * tf.unsorted_segment_max(c, tf.constant([0, 1, 0]), num_segments=2)
 * # ==> [[ 4,  3, 3, 4],
 * #       [5,  6, 7, 8]]
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "math")
public final class UnsortedSegmentMax<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new UnsortedSegmentMax operation.
   * 
   * @param scope current scope
   * @param data 
   * @param segmentIds A tensor whose shape is a prefix of `data.shape`.
   * @param numSegments 
   * @return a new instance of UnsortedSegmentMax
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber, V extends TNumber> UnsortedSegmentMax<T> create(Scope scope, Operand<T> data, Operand<U> segmentIds, Operand<V> numSegments) {
    OperationBuilder opBuilder = scope.env().opBuilder("UnsortedSegmentMax", scope.makeOpName("UnsortedSegmentMax"));
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(segmentIds.asOutput());
    opBuilder.addInput(numSegments.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new UnsortedSegmentMax<T>(opBuilder.build());
  }
  
  /**
   * Has same shape as data, except for the first `segment_ids.rank`
   * dimensions, which are replaced with a single dimension which has size
   * `num_segments`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "UnsortedSegmentMax";
  
  private Output<T> output;
  
  private UnsortedSegmentMax(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
