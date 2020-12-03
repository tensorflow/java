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

package org.tensorflow.op.data;

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
 * Computes rectified linear gradients for a LeakyRelu operation.
 * 
 * @param <T> data type for {@code backprops()} output
 */
public final class LeakyReluGrad<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.data.LeakyReluGrad}
   */
  public static class Options {
    
    /**
     * @param alpha 
     */
    public Options alpha(Float alpha) {
      this.alpha = alpha;
      return this;
    }
    
    private Float alpha;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new LeakyReluGrad operation.
   * 
   * @param scope current scope
   * @param gradients The backpropagated gradients to the corresponding LeakyRelu operation.
   * @param features The features passed as input to the corresponding LeakyRelu operation,
   * OR the outputs of that operation (both work equivalently).
   * @param options carries optional attributes values
   * @return a new instance of LeakyReluGrad
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> LeakyReluGrad<T> create(Scope scope, Operand<T> gradients, Operand<T> features, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("LeakyReluGrad", scope.makeOpName("LeakyReluGrad"));
    opBuilder.addInput(gradients.asOutput());
    opBuilder.addInput(features.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.alpha != null) {
          opBuilder.setAttr("alpha", opts.alpha);
        }
      }
    }
    return new LeakyReluGrad<T>(opBuilder.build());
  }
  
  /**
   * @param alpha 
   */
  public static Options alpha(Float alpha) {
    return new Options().alpha(alpha);
  }
  
  /**
   * `gradients * (features > 0) + alpha * gradients * (features <= 0)`.
   */
  public Output<T> backprops() {
    return backprops;
  }
  
  @Override
  public Output<T> asOutput() {
    return backprops;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "LeakyReluGrad";
  
  private Output<T> backprops;
  
  private LeakyReluGrad(Operation operation) {
    super(operation);
    int outputIdx = 0;
    backprops = operation.output(outputIdx++);
  }
}
