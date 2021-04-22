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
 * Computes rectified linear: {@code max(features, features * alpha)}.
 *
 * @param <T> data type for {@code activations} output
 */
@Operator(
    group = "nn"
)
public final class LeakyRelu<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LeakyRelu";

  private Output<T> activations;

  private LeakyRelu(Operation operation) {
    super(operation);
    int outputIdx = 0;
    activations = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new LeakyRelu operation.
   *
   * @param scope current scope
   * @param features the features value
   * @param options carries optional attribute values
   * @param <T> data type for {@code LeakyRelu} output and operands
   * @return a new instance of LeakyRelu
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> LeakyRelu<T> create(Scope scope, Operand<T> features,
      Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("LeakyRelu", scope.makeOpName("LeakyRelu"));
    opBuilder.addInput(features.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.alpha != null) {
          opBuilder.setAttr("alpha", opts.alpha);
        }
      }
    }
    return new LeakyRelu<>(opBuilder.build());
  }

  /**
   * Sets the alpha option.
   *
   * @param alpha the alpha option
   * @return this Options instance.
   */
  public static Options alpha(Float alpha) {
    return new Options().alpha(alpha);
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

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.LeakyRelu}
   */
  public static class Options {
    private Float alpha;

    private Options() {
    }

    /**
     * Sets the alpha option.
     *
     * @param alpha the alpha option
     * @return this Options instance.
     */
    public Options alpha(Float alpha) {
      this.alpha = alpha;
      return this;
    }
  }
}
