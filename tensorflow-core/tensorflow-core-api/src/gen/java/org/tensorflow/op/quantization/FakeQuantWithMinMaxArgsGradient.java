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

package org.tensorflow.op.quantization;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;

/**
 * Compute gradients for a FakeQuantWithMinMaxArgs operation.
 */
@Operator(group = "quantization")
public final class FakeQuantWithMinMaxArgsGradient extends RawOp implements Operand<TFloat32> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.quantization.FakeQuantWithMinMaxArgsGradient}
   */
  public static class Options {
    
    /**
     * @param min 
     */
    public Options min(Float min) {
      this.min = min;
      return this;
    }
    
    /**
     * @param max 
     */
    public Options max(Float max) {
      this.max = max;
      return this;
    }
    
    /**
     * @param numBits 
     */
    public Options numBits(Long numBits) {
      this.numBits = numBits;
      return this;
    }
    
    /**
     * @param narrowRange 
     */
    public Options narrowRange(Boolean narrowRange) {
      this.narrowRange = narrowRange;
      return this;
    }
    
    private Float min;
    private Float max;
    private Long numBits;
    private Boolean narrowRange;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new FakeQuantWithMinMaxArgsGradient operation.
   * 
   * @param scope current scope
   * @param gradients Backpropagated gradients above the FakeQuantWithMinMaxArgs operation.
   * @param inputs Values passed as inputs to the FakeQuantWithMinMaxArgs operation.
   * @param options carries optional attributes values
   * @return a new instance of FakeQuantWithMinMaxArgsGradient
   */
  @Endpoint(describeByClass = true)
  public static FakeQuantWithMinMaxArgsGradient create(Scope scope, Operand<TFloat32> gradients, Operand<TFloat32> inputs, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("FakeQuantWithMinMaxArgsGradient", scope.makeOpName("FakeQuantWithMinMaxArgsGradient"));
    opBuilder.addInput(gradients.asOutput());
    opBuilder.addInput(inputs.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.min != null) {
          opBuilder.setAttr("min", opts.min);
        }
        if (opts.max != null) {
          opBuilder.setAttr("max", opts.max);
        }
        if (opts.numBits != null) {
          opBuilder.setAttr("num_bits", opts.numBits);
        }
        if (opts.narrowRange != null) {
          opBuilder.setAttr("narrow_range", opts.narrowRange);
        }
      }
    }
    return new FakeQuantWithMinMaxArgsGradient(opBuilder.build());
  }
  
  /**
   * @param min 
   */
  public static Options min(Float min) {
    return new Options().min(min);
  }
  
  /**
   * @param max 
   */
  public static Options max(Float max) {
    return new Options().max(max);
  }
  
  /**
   * @param numBits 
   */
  public static Options numBits(Long numBits) {
    return new Options().numBits(numBits);
  }
  
  /**
   * @param narrowRange 
   */
  public static Options narrowRange(Boolean narrowRange) {
    return new Options().narrowRange(narrowRange);
  }
  
  /**
   * Backpropagated gradients below the FakeQuantWithMinMaxArgs operation:
   * `gradients * (inputs >= min && inputs <= max)`.
   */
  public Output<TFloat32> backprops() {
    return backprops;
  }
  
  @Override
  public Output<TFloat32> asOutput() {
    return backprops;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "FakeQuantWithMinMaxArgsGradient";
  
  private Output<TFloat32> backprops;
  
  private FakeQuantWithMinMaxArgsGradient(Operation operation) {
    super(operation);
    int outputIdx = 0;
    backprops = operation.output(outputIdx++);
  }
}
