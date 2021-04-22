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

package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/**
 * Returns the TopK unique values in the array in sorted order. The
 * running time is proportional to the product of K and the input
 * size. Sorting the whole array is more efficient for sufficiently large
 * values of K. The median-of-medians algorithm is probably faster, but
 * difficult to implement efficiently in XLA. If there are fewer than K
 * unique numbers (not NANs), the results are padded with negative
 * infinity. NaNs are never returned. Subnormal numbers are flushed to
 * zero. If an element appears at multiple indices, the highest index is
 * returned. If a TopK element never appears in the input due to padding
 * values, the indices are padded with negative one. If a padding value
 * appears in the input and padding is needed, the highest index of the
 * padding value will be returned. The semantics are not the same as
 * kth_order_statistic.
 */
@Operator
public final class TopKUnique extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TopKUnique";

  private Output<TFloat32> topk;

  private Output<TInt32> topkIndices;

  private TopKUnique(Operation operation) {
    super(operation);
    int outputIdx = 0;
    topk = operation.output(outputIdx++);
    topkIndices = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TopKUnique operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param k the value of the k property
   * @return a new instance of TopKUnique
   */
  @Endpoint(
      describeByClass = true
  )
  public static TopKUnique create(Scope scope, Operand<TFloat32> input, Long k) {
    OperationBuilder opBuilder = scope.env().opBuilder("TopKUnique", scope.makeOpName("TopKUnique"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("k", k);
    return new TopKUnique(opBuilder.build());
  }

  /**
   * Gets topk.
   *
   * @return topk.
   */
  public Output<TFloat32> topk() {
    return topk;
  }

  /**
   * Gets topkIndices.
   *
   * @return topkIndices.
   */
  public Output<TInt32> topkIndices() {
    return topkIndices;
  }
}
