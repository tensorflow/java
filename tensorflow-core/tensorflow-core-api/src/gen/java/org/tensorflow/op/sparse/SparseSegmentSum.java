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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the sum along sparse segments of a tensor.
 * <p>
 * Read
 * [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
 * for an explanation of segments.
 * <p>
 * Like `SegmentSum`, but `segment_ids` can have rank less than `data`'s first
 * dimension, selecting a subset of dimension 0, specified by `indices`.
 * <p>
 * For example:
 * <pre>{@code
 * c = tf.constant([[1,2,3,4], [-1,-2,-3,-4], [5,6,7,8]])
 * 
 * # Select two rows, one segment.
 * tf.sparse_segment_sum(c, tf.constant([0, 1]), tf.constant([0, 0]))
 * # => [[0 0 0 0]]
 * 
 * # Select two rows, two segment.
 * tf.sparse_segment_sum(c, tf.constant([0, 1]), tf.constant([0, 1]))
 * # => [[ 1  2  3  4]
 * #     [-1 -2 -3 -4]]
 * 
 * # Select all rows, two segments.
 * tf.sparse_segment_sum(c, tf.constant([0, 1, 2]), tf.constant([0, 0, 1]))
 * # => [[0 0 0 0]
 * #     [5 6 7 8]]
 * 
 * # Which is equivalent to:
 * tf.segment_sum(c, tf.constant([0, 0, 1]))
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "sparse")
public final class SparseSegmentSum<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SparseSegmentSum operation.
   * 
   * @param scope current scope
   * @param data 
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @return a new instance of SparseSegmentSum
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber, V extends TNumber> SparseSegmentSum<T> create(Scope scope, Operand<T> data, Operand<U> indices, Operand<V> segmentIds) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseSegmentSum", scope.makeOpName("SparseSegmentSum"));
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(segmentIds.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseSegmentSum<T>(opBuilder.build());
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
  public static final String OP_NAME = "SparseSegmentSum";
  
  private Output<T> output;
  
  private SparseSegmentSum(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
