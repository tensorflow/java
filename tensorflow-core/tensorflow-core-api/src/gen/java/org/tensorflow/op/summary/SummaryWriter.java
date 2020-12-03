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

package org.tensorflow.op.summary;

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
 */
public final class SummaryWriter extends RawOp implements Operand<TType> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.summary.SummaryWriter}
   */
  public static class Options {
    
    /**
     * @param sharedName 
     */
    public Options sharedName(String sharedName) {
      this.sharedName = sharedName;
      return this;
    }
    
    /**
     * @param container 
     */
    public Options container(String container) {
      this.container = container;
      return this;
    }
    
    private String sharedName;
    private String container;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new SummaryWriter operation.
   * 
   * @param scope current scope
   * @param options carries optional attributes values
   * @return a new instance of SummaryWriter
   */
  @Endpoint(describeByClass = true)
  public static SummaryWriter create(Scope scope, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("SummaryWriter", scope.makeOpName("SummaryWriter"));
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.sharedName != null) {
          opBuilder.setAttr("shared_name", opts.sharedName);
        }
        if (opts.container != null) {
          opBuilder.setAttr("container", opts.container);
        }
      }
    }
    return new SummaryWriter(opBuilder.build());
  }
  
  /**
   * @param sharedName 
   */
  public static Options sharedName(String sharedName) {
    return new Options().sharedName(sharedName);
  }
  
  /**
   * @param container 
   */
  public static Options container(String container) {
    return new Options().container(container);
  }
  
  /**
   */
  public Output<?> writer() {
    return writer;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) writer;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "SummaryWriter";
  
  private Output<?> writer;
  
  private SummaryWriter(Operation operation) {
    super(operation);
    int outputIdx = 0;
    writer = operation.output(outputIdx++);
  }
}
