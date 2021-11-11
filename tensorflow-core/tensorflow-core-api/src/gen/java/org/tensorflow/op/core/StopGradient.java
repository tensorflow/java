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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Stops gradient computation.
 * When executed in a graph, this op outputs its input tensor as-is.
 * <p>When building ops to compute gradients, this op prevents the contribution of
 * its inputs to be taken into account.  Normally, the gradient generator adds ops
 * to a graph to compute the derivatives of a specified 'loss' by recursively
 * finding out inputs that contributed to its computation.  If you insert this op
 * in the graph it inputs are masked from the gradient generator.  They are not
 * taken into account for computing gradients.
 * <p>This is useful any time you want to compute a value with TensorFlow but need
 * to pretend that the value was a constant. For example, the softmax function
 * for a vector x can be written as
 * <pre>
 *
 *   def softmax(x):
 *     numerator = tf.exp(x)
 *     denominator = tf.reduce_sum(numerator)
 *     return numerator / denominator
 * </pre>
 * <p>This however is susceptible to overflow if the values in x are large. An
 * alternative more stable way is to subtract the maximum of x from each of the
 * values.
 * <pre>
 *
 *   def stable_softmax(x):
 *     z = x - tf.reduce_max(x)
 *     numerator = tf.exp(z)
 *     denominator = tf.reduce_sum(numerator)
 *     return numerator / denominator
 * </pre>
 * <p>However, when we backprop through the softmax to x, we dont want to backprop
 * through the {@code tf.reduce_max(x)} (if the max values are not unique then the
 * gradient could flow to the wrong input) calculation and treat that as a
 * constant. Therefore, we should write this out as
 * <pre>
 *
 *   def stable_softmax(x):
 *     z = x - tf.stop_gradient(tf.reduce_max(x))
 *     numerator = tf.exp(z)
 *     denominator = tf.reduce_sum(numerator)
 *     return numerator / denominator
 * </pre>
 * <p>Some other examples include:
 * <ul>
 * <li>The <em>EM</em> algorithm where the <em>M-step</em> should not involve backpropagation
 * through the output of the <em>E-step</em>.</li>
 * <li>Contrastive divergence training of Boltzmann machines where, when
 * differentiating the energy function, the training must not backpropagate
 * through the graph that generated the samples from the model.</li>
 * <li>Adversarial training, where no backprop should happen through the adversarial
 * example generation process.</li>
 * </ul>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = StopGradient.OP_NAME,
    inputsClass = StopGradient.Inputs.class
)
@Operator
public final class StopGradient<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StopGradient";

  private Output<T> output;

  public StopGradient(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StopGradient operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param <T> data type for {@code StopGradient} output and operands
   * @return a new instance of StopGradient
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> StopGradient<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StopGradient");
    opBuilder.addInput(input.asOutput());
    return new StopGradient<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = StopGradient.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<StopGradient<T>> {
    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new StopGradient<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
