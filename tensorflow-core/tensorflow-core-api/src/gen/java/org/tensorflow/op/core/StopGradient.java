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
import org.tensorflow.types.family.TType;

/**
 * Stops gradient computation.
 * <p>
 * When executed in a graph, this op outputs its input tensor as-is.
 * <p>
 * When building ops to compute gradients, this op prevents the contribution of
 * its inputs to be taken into account.  Normally, the gradient generator adds ops
 * to a graph to compute the derivatives of a specified 'loss' by recursively
 * finding out inputs that contributed to its computation.  If you insert this op
 * in the graph it inputs are masked from the gradient generator.  They are not
 * taken into account for computing gradients.
 * <p>
 * This is useful any time you want to compute a value with TensorFlow but need
 * to pretend that the value was a constant. Some examples include:
 * <ul>
 * <li>
 * The <i>EM</i> algorithm where the <i>M-step</i> should not involve backpropagation
 *    through the output of the <i>E-step</i>.
 * </li>
 * <li>
 * Contrastive divergence training of Boltzmann machines where, when
 *    differentiating the energy function, the training must not backpropagate
 *    through the graph that generated the samples from the model.
 * </li>
 * <li>
 * Adversarial training, where no backprop should happen through the adversarial
 *    example generation process.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator
public final class StopGradient<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new StopGradient operation.
   * 
   * @param scope current scope
   * @param input 
   * @return a new instance of StopGradient
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> StopGradient<T> create(Scope scope, Operand<T> input) {
    OperationBuilder opBuilder = scope.env().opBuilder("StopGradient", scope.makeOpName("StopGradient"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new StopGradient<T>(opBuilder.build());
  }
  
  /**
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StopGradient";
  
  private Output<T> output;
  
  private StopGradient(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
