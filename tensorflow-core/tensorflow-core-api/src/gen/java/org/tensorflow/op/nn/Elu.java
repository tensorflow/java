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
 * Computes exponential linear: {@code exp(features) - 1} if &lt; 0, {@code features} otherwise.
 * See  <a href="http://arxiv.org/abs/1511.07289">Fast and Accurate Deep Network Learning by Exponential Linear Units (ELUs)
 * </a>
 *
 * @param <T> data type for {@code activations} output
 */
@Operator(
    group = "nn"
)
public final class Elu<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Elu";

  private Output<T> activations;

  private Elu(Operation operation) {
    super(operation);
    int outputIdx = 0;
    activations = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Elu operation.
   *
   * @param scope current scope
   * @param features the features value
   * @param <T> data type for {@code Elu} output and operands
   * @return a new instance of Elu
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Elu<T> create(Scope scope, Operand<T> features) {
    OperationBuilder opBuilder = scope.env().opBuilder("Elu", scope.makeOpName("Elu"));
    opBuilder.addInput(features.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new Elu<>(opBuilder.build());
  }

  /**
   * Gets activations.
   *
   * @return activations.
   */
  public Output<T> activations() {
    return activations;
  }

  @Override
  public Output<T> asOutput() {
    return activations;
  }
}
