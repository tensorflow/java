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
import org.tensorflow.types.family.TNumber;

/**
 * Returns the gradient of `quantization.QuantizeAndDequantizeV4`.
 * <p>
 * This is almost identical to QuantizeAndDequantizeV2, except that it returns a
 * gradient of 1 for inputs that are within the quantization range, or 0 otherwise.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "quantization")
public final class QuantizeAndDequantizeV4<T extends TNumber> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.quantization.QuantizeAndDequantizeV4}
   */
  public static class Options {
    
    /**
     * @param signedInput 
     */
    public Options signedInput(Boolean signedInput) {
      this.signedInput = signedInput;
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
     * @param rangeGiven 
     */
    public Options rangeGiven(Boolean rangeGiven) {
      this.rangeGiven = rangeGiven;
      return this;
    }
    
    /**
     * @param roundMode 
     */
    public Options roundMode(String roundMode) {
      this.roundMode = roundMode;
      return this;
    }
    
    /**
     * @param narrowRange 
     */
    public Options narrowRange(Boolean narrowRange) {
      this.narrowRange = narrowRange;
      return this;
    }
    
    /**
     * @param axis 
     */
    public Options axis(Long axis) {
      this.axis = axis;
      return this;
    }
    
    private Boolean signedInput;
    private Long numBits;
    private Boolean rangeGiven;
    private String roundMode;
    private Boolean narrowRange;
    private Long axis;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new QuantizeAndDequantizeV4 operation.
   * 
   * @param scope current scope
   * @param input 
   * @param inputMin 
   * @param inputMax 
   * @param options carries optional attributes values
   * @return a new instance of QuantizeAndDequantizeV4
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> QuantizeAndDequantizeV4<T> create(Scope scope, Operand<T> input, Operand<T> inputMin, Operand<T> inputMax, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizeAndDequantizeV4", scope.makeOpName("QuantizeAndDequantizeV4"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputMin.asOutput());
    opBuilder.addInput(inputMax.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.signedInput != null) {
          opBuilder.setAttr("signed_input", opts.signedInput);
        }
        if (opts.numBits != null) {
          opBuilder.setAttr("num_bits", opts.numBits);
        }
        if (opts.rangeGiven != null) {
          opBuilder.setAttr("range_given", opts.rangeGiven);
        }
        if (opts.roundMode != null) {
          opBuilder.setAttr("round_mode", opts.roundMode);
        }
        if (opts.narrowRange != null) {
          opBuilder.setAttr("narrow_range", opts.narrowRange);
        }
        if (opts.axis != null) {
          opBuilder.setAttr("axis", opts.axis);
        }
      }
    }
    return new QuantizeAndDequantizeV4<T>(opBuilder.build());
  }
  
  /**
   * @param signedInput 
   */
  public static Options signedInput(Boolean signedInput) {
    return new Options().signedInput(signedInput);
  }
  
  /**
   * @param numBits 
   */
  public static Options numBits(Long numBits) {
    return new Options().numBits(numBits);
  }
  
  /**
   * @param rangeGiven 
   */
  public static Options rangeGiven(Boolean rangeGiven) {
    return new Options().rangeGiven(rangeGiven);
  }
  
  /**
   * @param roundMode 
   */
  public static Options roundMode(String roundMode) {
    return new Options().roundMode(roundMode);
  }
  
  /**
   * @param narrowRange 
   */
  public static Options narrowRange(Boolean narrowRange) {
    return new Options().narrowRange(narrowRange);
  }
  
  /**
   * @param axis 
   */
  public static Options axis(Long axis) {
    return new Options().axis(axis);
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
  public static final String OP_NAME = "QuantizeAndDequantizeV4";
  
  private Output<T> output;
  
  private QuantizeAndDequantizeV4(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
