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

/**
 * Computes scaled exponential linear: `scale * alpha * (exp(features) - 1)`
 * <p>
 * if < 0, `scale * features` otherwise.
 * <p>
 * To be used together with
 * `initializer = tf.variance_scaling_initializer(factor=1.0, mode='FAN_IN')`.
 * For correct dropout, use `tf.contrib.nn.alpha_dropout`.
 * <p>
 * See [Self-Normalizing Neural Networks](https://arxiv.org/abs/1706.02515)
 * 
 * @param <T> data type for {@code activations()} output
 */
@Operator(group = "nn")
public final class Selu<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new Selu operation.
   * 
   * @param scope current scope
   * @param features 
   * @return a new instance of Selu
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> Selu<T> create(Scope scope, Operand<T> features) {
    OperationBuilder opBuilder = scope.env().opBuilder("Selu", scope.makeOpName("Selu"));
    opBuilder.addInput(features.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Selu<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> activations() {
    return activations;
  }
  
  @Override
  public Output<T> asOutput() {
    return activations;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Selu";
  
  private Output<T> activations;
  
  private Selu(Operation operation) {
    super(operation);
    int outputIdx = 0;
    activations = operation.output(outputIdx++);
  }
}
