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

package org.tensorflow.op.train;

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
 * An identity op that triggers an error if a gradient is requested.
 * <p>
 * When executed in a graph, this op outputs its input tensor as-is.
 * <p>
 * When building ops to compute gradients, the TensorFlow gradient system
 * will return an error when trying to lookup the gradient of this op,
 * because no gradient must ever be registered for this function.  This
 * op exists to prevent subtle bugs from silently returning unimplemented
 * gradients in some corner cases.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "train")
public final class PreventGradient<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.train.PreventGradient}
   */
  public static class Options {
    
    /**
     * @param message Will be printed in the error when anyone tries to differentiate
     * this operation.
     */
    public Options message(String message) {
      this.message = message;
      return this;
    }
    
    private String message;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new PreventGradient operation.
   * 
   * @param scope current scope
   * @param input any tensor.
   * @param options carries optional attributes values
   * @return a new instance of PreventGradient
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> PreventGradient<T> create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("PreventGradient", scope.makeOpName("PreventGradient"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.message != null) {
          opBuilder.setAttr("message", opts.message);
        }
      }
    }
    return new PreventGradient<T>(opBuilder.build());
  }
  
  /**
   * @param message Will be printed in the error when anyone tries to differentiate
   * this operation.
   */
  public static Options message(String message) {
    return new Options().message(message);
  }
  
  /**
   * the same input tensor.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "PreventGradient";
  
  private Output<T> output;
  
  private PreventGradient(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
