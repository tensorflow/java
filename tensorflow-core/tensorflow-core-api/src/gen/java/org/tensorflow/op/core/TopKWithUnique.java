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
 * Returns the TopK values in the array in sorted order. This is a combination
 * of MakeUnique and TopKUnique. The returned top-K will have its lower bits
 * replaced by iota, thus it will be close to the original value but not exactly
 * the same. The running time is proportional to the product of K and the input
 * size. NaNs are never returned. Subnormal numbers are flushed to zero.
 */
@Operator
public final class TopKWithUnique extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TopKWithUnique";

  private Output<TFloat32> topk;

  private Output<TInt32> topkIndices;

  private TopKWithUnique(Operation operation) {
    super(operation);
    int outputIdx = 0;
    topk = operation.output(outputIdx++);
    topkIndices = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TopKWithUnique operation.
   *
   * @param scope current scope
   * @param input the input value
   * @param k the value of the k property
   * @return a new instance of TopKWithUnique
   */
  @Endpoint(
      describeByClass = true
  )
  public static TopKWithUnique create(Scope scope, Operand<TFloat32> input, Long k) {
    OperationBuilder opBuilder = scope.env().opBuilder("TopKWithUnique", scope.makeOpName("TopKWithUnique"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("k", k);
    return new TopKWithUnique(opBuilder.build());
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
