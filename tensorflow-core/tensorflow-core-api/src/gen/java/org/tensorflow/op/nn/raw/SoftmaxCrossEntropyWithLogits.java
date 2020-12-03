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

package org.tensorflow.op.nn.raw;

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
 * Computes softmax cross entropy cost and gradients to backpropagate.
 * <p>
 * Inputs are the logits, not probabilities.
 * 
 * @param <T> data type for {@code loss()} output
 */
@Operator(group = "nn.raw")
public final class SoftmaxCrossEntropyWithLogits<T extends TNumber> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new SoftmaxCrossEntropyWithLogits operation.
   * 
   * @param scope current scope
   * @param features batch_size x num_classes matrix
   * @param labels batch_size x num_classes matrix
   * The caller must ensure that each batch of labels represents a valid
   * probability distribution.
   * @return a new instance of SoftmaxCrossEntropyWithLogits
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> SoftmaxCrossEntropyWithLogits<T> create(Scope scope, Operand<T> features, Operand<T> labels) {
    OperationBuilder opBuilder = scope.env().opBuilder("SoftmaxCrossEntropyWithLogits", scope.makeOpName("SoftmaxCrossEntropyWithLogits"));
    opBuilder.addInput(features.asOutput());
    opBuilder.addInput(labels.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new SoftmaxCrossEntropyWithLogits<T>(opBuilder.build());
  }
  
  /**
   * Per example loss (batch_size vector).
   */
  public Output<T> loss() {
    return loss;
  }
  
  /**
   * backpropagated gradients (batch_size x num_classes matrix).
   */
  public Output<T> backprop() {
    return backprop;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SoftmaxCrossEntropyWithLogits";
  
  private Output<T> loss;
  private Output<T> backprop;
  
  private SoftmaxCrossEntropyWithLogits(Operation operation) {
    super(operation);
    int outputIdx = 0;
    loss = operation.output(outputIdx++);
    backprop = operation.output(outputIdx++);
  }
}
