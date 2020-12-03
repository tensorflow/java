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
 * Creates or finds a child frame, and makes `data` available to the child frame.
 * <p>
 * This op is used together with `Exit` to create loops in the graph.
 * The unique `frame_name` is used by the `Executor` to identify frames. If
 * `is_constant` is true, `output` is a constant in the child frame; otherwise
 * it may be changed in the child frame. At most `parallel_iterations` iterations
 * are run in parallel in the child frame.
 * 
 * @param <T> data type for {@code output()} output
 */
public final class Enter<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.core.Enter}
   */
  public static class Options {
    
    /**
     * @param isConstant If true, the output is constant within the child frame.
     */
    public Options isConstant(Boolean isConstant) {
      this.isConstant = isConstant;
      return this;
    }
    
    /**
     * @param parallelIterations The number of iterations allowed to run in parallel.
     */
    public Options parallelIterations(Long parallelIterations) {
      this.parallelIterations = parallelIterations;
      return this;
    }
    
    private Boolean isConstant;
    private Long parallelIterations;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Enter operation.
   * 
   * @param scope current scope
   * @param data The tensor to be made available to the child frame.
   * @param frameName The name of the child frame.
   * @param options carries optional attributes values
   * @return a new instance of Enter
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Enter<T> create(Scope scope, Operand<T> data, String frameName, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Enter", scope.makeOpName("Enter"));
    opBuilder.addInput(data.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("frame_name", frameName);
    if (options != null) {
      for (Options opts : options) {
        if (opts.isConstant != null) {
          opBuilder.setAttr("is_constant", opts.isConstant);
        }
        if (opts.parallelIterations != null) {
          opBuilder.setAttr("parallel_iterations", opts.parallelIterations);
        }
      }
    }
    return new Enter<T>(opBuilder.build());
  }
  
  /**
   * @param isConstant If true, the output is constant within the child frame.
   */
  public static Options isConstant(Boolean isConstant) {
    return new Options().isConstant(isConstant);
  }
  
  /**
   * @param parallelIterations The number of iterations allowed to run in parallel.
   */
  public static Options parallelIterations(Long parallelIterations) {
    return new Options().parallelIterations(parallelIterations);
  }
  
  /**
   * The same tensor as `data`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Enter";
  
  private Output<T> output;
  
  private Enter(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
