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

package org.tensorflow.op.nn;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Says whether the targets are in the top `K` predictions.
 * <p>
 * This outputs a `batch_size` bool array, an entry `out[i]` is `true` if the
 * prediction for the target class is among the top `k` predictions among
 * all predictions for example `i`. Note that the behavior of `InTopK` differs
 * from the `TopK` op in its handling of ties; if multiple classes have the
 * same prediction value and straddle the top-`k` boundary, all of those
 * classes are considered to be in the top `k`.
 * <p>
 * More formally, let
 * <p>
 *   \\(predictions_i\\) be the predictions for all classes for example `i`,
 *   \\(targets_i\\) be the target class for example `i`,
 *   \\(out_i\\) be the output for example `i`,
 * <p>
 * $$out_i = predictions_{i, targets_i} \in TopKIncludingTies(predictions_i)$$
 */
@Operator(group = "nn")
public final class InTopK extends RawOp implements Operand<TBool> {
  
  /**
   * Factory method to create a class wrapping a new InTopK operation.
   * 
   * @param scope current scope
   * @param predictions A `batch_size` x `classes` tensor.
   * @param targets A `batch_size` vector of class ids.
   * @param k Number of top elements to look at for computing precision.
   * @return a new instance of InTopK
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> InTopK create(Scope scope, Operand<TFloat32> predictions, Operand<T> targets, Operand<T> k) {
    OperationBuilder opBuilder = scope.env().opBuilder("InTopKV2", scope.makeOpName("InTopK"));
    opBuilder.addInput(predictions.asOutput());
    opBuilder.addInput(targets.asOutput());
    opBuilder.addInput(k.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new InTopK(opBuilder.build());
  }
  
  /**
   * Computed precision at `k` as a `bool Tensor`.
   */
  public Output<TBool> precision() {
    return precision;
  }
  
  @Override
  public Output<TBool> asOutput() {
    return precision;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "InTopKV2";
  
  private Output<TBool> precision;
  
  private InTopK(Operation operation) {
    super(operation);
    int outputIdx = 0;
    precision = operation.output(outputIdx++);
  }
}
