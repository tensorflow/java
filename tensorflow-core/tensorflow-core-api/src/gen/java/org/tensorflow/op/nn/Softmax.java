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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Computes softmax activations.
 * <p>
 * For each batch `i` and class `j` we have
 * <p>
 *     $$softmax[i, j] = exp(logits[i, j]) / sum_j(exp(logits[i, j]))$$
 * 
 * @param <T> data type for {@code softmax()} output
 */
@Operator(group = "nn")
public final class Softmax<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Softmax operation.
   * 
   * @param scope current scope
   * @param logits 2-D with shape `[batch_size, num_classes]`.
   * @return a new instance of Softmax
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> Softmax<T> create(Scope scope, Operand<T> logits) {
    OperationBuilder opBuilder = scope.env().opBuilder("Softmax", scope.makeOpName("Softmax"));
    opBuilder.addInput(logits.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Softmax<T>(opBuilder.build());
  }
  
  /**
   * Same shape as `logits`.
   */
  public Output<T> softmax() {
    return softmax;
  }
  
  @Override
  public Output<T> asOutput() {
    return softmax;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Softmax";
  
  private Output<T> softmax;
  
  private Softmax(Operation operation) {
    super(operation);
    int outputIdx = 0;
    softmax = operation.output(outputIdx++);
  }
}
