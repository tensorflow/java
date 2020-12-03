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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * A conditional accumulator for aggregating gradients.
 * <p>
 * The accumulator accepts gradients marked with local_step greater or
 * equal to the most recent global_step known to the accumulator. The
 * average can be extracted from the accumulator, provided sufficient
 * gradients have been accumulated. Extracting the average automatically
 * resets the aggregate to 0, and increments the global_step recorded by
 * the accumulator.
 */
@Operator(group = "train")
public final class ConditionalAccumulator extends RawOp implements Operand<TString> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.train.ConditionalAccumulator}
   */
  public static class Options {
    
    /**
     * @param container If non-empty, this accumulator is placed in the given container.
     * Otherwise, a default container is used.
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }
    
    /**
     * @param sharedName If non-empty, this accumulator will be shared under the
     * given name across multiple sessions.
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
    
    /**
     * @param reductionType 
     */
    public Options reductionType(String reductionType) {
      this.reductionType = reductionType;
      return this;
    }
    
    private String container;
    private String sharedName;
    private String reductionType;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new ConditionalAccumulator operation.
   * 
   * @param scope current scope
   * @param dtype The type of the value being accumulated.
   * @param shape The shape of the values, can be [], in which case shape is unknown.
   * @param options carries optional attributes values
   * @return a new instance of ConditionalAccumulator
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> ConditionalAccumulator create(Scope scope, DataType<T> dtype, Shape shape, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ConditionalAccumulator", scope.makeOpName("ConditionalAccumulator"));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", dtype);
    opBuilder.setAttr("shape", shape);
    if (options != null) {
      for (Options opts : options) {
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
        if (opts.reductionType != null) {
          opBuilder.setAttr("reduction_type", opts.reductionType);
        }
      }
    }
    return new ConditionalAccumulator(opBuilder.build());
  }
  
  /**
   * @param container If non-empty, this accumulator is placed in the given container.
   * Otherwise, a default container is used.
   */
  public static Options container(String container) {
    return new Options().container(container);
  }
  
  /**
   * @param sharedName If non-empty, this accumulator will be shared under the
   * given name across multiple sessions.
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }
  
  /**
   * @param reductionType 
   */
  public static Options reductionType(String reductionType) {
    return new Options().reductionType(reductionType);
  }
  
  /**
   * The handle to the accumulator.
   */
  public Output<TString> handle() {
    return handle;
  }
  
  @Override
  public Output<TString> asOutput() {
    return handle;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "ConditionalAccumulator";
  
  private Output<TString> handle;
  
  private ConditionalAccumulator(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}
