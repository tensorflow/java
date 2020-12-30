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
 * Computes the sum along sparse segments of a tensor divided by the sqrt of N.
 * <p>
 * N is the size of the segment being reduced.
 * <p>
 * Like `SparseSegmentSqrtN`, but allows missing ids in `segment_ids`. If an id is
 * missing, the `output` tensor at that position will be zeroed.
 * <p>
 * Read
 * [the section on segmentation](https://tensorflow.org/api_docs/python/tf/math#Segmentation)
 * for an explanation of segments.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "sparse")
public final class SparseSegmentSqrtNWithNumSegments<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new SparseSegmentSqrtNWithNumSegments operation.
   * 
   * @param scope current scope
   * @param data 
   * @param indices A 1-D tensor. Has same rank as `segment_ids`.
   * @param segmentIds A 1-D tensor. Values should be sorted and can be repeated.
   * @param numSegments Should equal the number of distinct segment IDs.
   * @return a new instance of SparseSegmentSqrtNWithNumSegments
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber, V extends TNumber, W extends TNumber> SparseSegmentSqrtNWithNumSegments<T> create(Scope scope, Operand<T> data, Operand<U> indices, Operand<V> segmentIds, Operand<W> numSegments) {
    OperationBuilder opBuilder = scope.env().opBuilder("SparseSegmentSqrtNWithNumSegments", scope.makeOpName("SparseSegmentSqrtNWithNumSegments"));
    opBuilder.addInput(data.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(segmentIds.asOutput());
    opBuilder.addInput(numSegments.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SparseSegmentSqrtNWithNumSegments<T>(opBuilder.build());
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
  public static final String OP_NAME = "SparseSegmentSqrtNWithNumSegments";
  
  private Output<T> output;
  
  private SparseSegmentSqrtNWithNumSegments(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
