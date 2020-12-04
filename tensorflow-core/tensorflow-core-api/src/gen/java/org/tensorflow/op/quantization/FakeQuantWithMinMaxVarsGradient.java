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
 * Compute gradients for a FakeQuantWithMinMaxVars operation.
 */
@Operator(group = "quantization")
public final class FakeQuantWithMinMaxVarsGradient extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsGradient}
   */
  public static class Options {
    
    /**
     * @param numBits The bitwidth of the quantization; between 2 and 8, inclusive.
     */
    public Options numBits(Long numBits) {
      this.numBits = numBits;
      return this;
    }
    
    /**
     * @param narrowRange Whether to quantize into 2^num_bits - 1 distinct values.
     */
    public Options narrowRange(Boolean narrowRange) {
      this.narrowRange = narrowRange;
      return this;
    }
    
    private Long numBits;
    private Boolean narrowRange;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new FakeQuantWithMinMaxVarsGradient operation.
   * 
   * @param scope current scope
   * @param gradients Backpropagated gradients above the FakeQuantWithMinMaxVars operation.
   * @param inputs Values passed as inputs to the FakeQuantWithMinMaxVars operation.
   * min, max: Quantization interval, scalar floats.
   * @param min 
   * @param max 
   * @param options carries optional attributes values
   * @return a new instance of FakeQuantWithMinMaxVarsGradient
   */
  @Endpoint(describeByClass = true)
  public static FakeQuantWithMinMaxVarsGradient create(Scope scope, Operand<TFloat32> gradients, Operand<TFloat32> inputs, Operand<TFloat32> min, Operand<TFloat32> max, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("FakeQuantWithMinMaxVarsGradient", scope.makeOpName("FakeQuantWithMinMaxVarsGradient"));
    opBuilder.addInput(gradients.asOutput());
    opBuilder.addInput(inputs.asOutput());
    opBuilder.addInput(min.asOutput());
    opBuilder.addInput(max.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.numBits != null) {
          opBuilder.setAttr("num_bits", opts.numBits);
        }
        if (opts.narrowRange != null) {
          opBuilder.setAttr("narrow_range", opts.narrowRange);
        }
      }
    }
    return new FakeQuantWithMinMaxVarsGradient(opBuilder.build());
  }
  
  /**
   * @param numBits The bitwidth of the quantization; between 2 and 8, inclusive.
   */
  public static Options numBits(Long numBits) {
    return new Options().numBits(numBits);
  }
  
  /**
   * @param narrowRange Whether to quantize into 2^num_bits - 1 distinct values.
   */
  public static Options narrowRange(Boolean narrowRange) {
    return new Options().narrowRange(narrowRange);
  }
  
  /**
   * Backpropagated gradients w.r.t. inputs:
   * `gradients * (inputs >= min && inputs <= max)`.
   */
  public Output<TFloat32> backpropsWrtInput() {
    return backpropsWrtInput;
  }
  
  /**
   * Backpropagated gradients w.r.t. min parameter:
   * `sum(gradients * (inputs < min))`.
   */
  public Output<TFloat32> backpropWrtMin() {
    return backpropWrtMin;
  }
  
  /**
   * Backpropagated gradients w.r.t. max parameter:
   * `sum(gradients * (inputs > max))`.
   */
  public Output<TFloat32> backpropWrtMax() {
    return backpropWrtMax;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "FakeQuantWithMinMaxVarsGradient";
  
  private Output<TFloat32> backpropsWrtInput;
  private Output<TFloat32> backpropWrtMin;
  private Output<TFloat32> backpropWrtMax;
  
  private FakeQuantWithMinMaxVarsGradient(Operation operation) {
    super(operation);
    int outputIdx = 0;
    backpropsWrtInput = operation.output(outputIdx++);
    backpropWrtMin = operation.output(outputIdx++);
    backpropWrtMax = operation.output(outputIdx++);
  }
}
