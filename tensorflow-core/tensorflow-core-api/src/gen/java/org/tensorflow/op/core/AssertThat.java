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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;

/**
 * Asserts that the given condition is true.
 * <p>
 * If `condition` evaluates to false, print the list of tensors in `data`.
 * `summarize` determines how many entries of the tensors to print.
 */
@Operator
public final class AssertThat extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.AssertThat}
   */
  public static class Options {
    
    /**
     * @param summarize Print this many entries of each tensor.
     */
    public Options summarize(Long summarize) {
      this.summarize = summarize;
      return this;
    }
    
    private Long summarize;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new AssertThat operation.
   * 
   * @param scope current scope
   * @param condition The condition to evaluate.
   * @param data The tensors to print out when condition is false.
   * @param options carries optional attributes values
   * @return a new instance of AssertThat
   */
  @Endpoint(describeByClass = true)
  public static AssertThat create(Scope scope, Operand<TBool> condition, Iterable<Operand<?>> data, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Assert", scope.makeOpName("AssertThat"));
    opBuilder.addInput(condition.asOutput());
    opBuilder.addInputList(Operands.asOutputs(data));
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.summarize != null) {
          opBuilder.setAttr("summarize", opts.summarize);
        }
      }
    }
    return new AssertThat(opBuilder.build());
  }
  
  /**
   * @param summarize Print this many entries of each tensor.
   */
  public static Options summarize(Long summarize) {
    return new Options().summarize(summarize);
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Assert";
  
  private AssertThat(Operation operation) {
    super(operation);
  }
}
