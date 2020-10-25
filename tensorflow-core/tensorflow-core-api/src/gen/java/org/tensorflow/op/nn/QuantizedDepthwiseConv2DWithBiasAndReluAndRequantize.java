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

package org.tensorflow.op.nn;

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

/**
 * Computes quantized depthwise Conv2D with Bias, Relu and Requantize.
 * 
 * @param <W> data type for {@code output()} output
 */
public final class QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize<W extends TType> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize}
   */
  public static class Options {
    
    /**
     * @param dilations List of dilation values.
     */
    public Options dilations(List<Long> dilations) {
      this.dilations = dilations;
      return this;
    }
    
    /**
     * @param paddingList 
     */
    public Options paddingList(List<Long> paddingList) {
      this.paddingList = paddingList;
      return this;
    }
    
    private List<Long> dilations;
    private List<Long> paddingList;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize operation.
   * 
   * @param scope current scope
   * @param input The original input tensor.
   * @param filter The original filter tensor.
   * @param bias The original bias tensor.
   * @param minInput The float value that the minimum quantized input value represents.
   * @param maxInput The float value that the maximum quantized input value represents.
   * @param minFilter The float value that the minimum quantized filter value represents.
   * @param maxFilter The float value that the maximum quantized filter value represents.
   * @param minFreezedOutput The minimum float value of the output tensor.
   * @param maxFreezedOutput The maximum float value of the output tensor.
   * @param outType The type of the output.
   * @param strides List of stride values.
   * @param padding 
   * @param options carries optional attributes values
   * @return a new instance of QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize
   */
  @Endpoint(describeByClass = true)
  public static <W extends TType, T extends TType, U extends TType, V extends TType> QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize<W> create(Scope scope, Operand<T> input, Operand<U> filter, Operand<V> bias, Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter, Operand<TFloat32> maxFilter, Operand<TFloat32> minFreezedOutput, Operand<TFloat32> maxFreezedOutput, Class<W> outType, List<Long> strides, String padding, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize", scope.makeOpName("QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize"));
    opBuilder.addInput(input.asOutput(scope));
    opBuilder.addInput(filter.asOutput(scope));
    opBuilder.addInput(bias.asOutput(scope));
    opBuilder.addInput(minInput.asOutput(scope));
    opBuilder.addInput(maxInput.asOutput(scope));
    opBuilder.addInput(minFilter.asOutput(scope));
    opBuilder.addInput(maxFilter.asOutput(scope));
    opBuilder.addInput(minFreezedOutput.asOutput(scope));
    opBuilder.addInput(maxFreezedOutput.asOutput(scope));
    opBuilder = scope.applyControlDependencies(opBuilder);
    opBuilder.setAttr("out_type", outType);
    long[] stridesArray = new long[strides.size()];
    for (int i = 0; i < stridesArray.length; ++i) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.dilations != null) {
          long[] dilationsArray = new long[opts.dilations.size()];
          for (int i = 0; i < dilationsArray.length; ++i) {
            dilationsArray[i] = opts.dilations.get(i);
          }
          opBuilder.setAttr("dilations", dilationsArray);
        }
        if (opts.paddingList != null) {
          long[] paddingListArray = new long[opts.paddingList.size()];
          for (int i = 0; i < paddingListArray.length; ++i) {
            paddingListArray[i] = opts.paddingList.get(i);
          }
          opBuilder.setAttr("padding_list", paddingListArray);
        }
      }
    }
    return new QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize<W>(opBuilder.build());
  }
  
  /**
   * @param dilations List of dilation values.
   */
  public static Options dilations(List<Long> dilations) {
    return new Options().dilations(dilations);
  }
  
  /**
   * @param paddingList 
   */
  public static Options paddingList(List<Long> paddingList) {
    return new Options().paddingList(paddingList);
  }
  
  /**
   * The output tensor.
   */
  public Output<W> output() {
    return output;
  }
  
  /**
   * The float value that the minimum quantized output value represents.
   */
  public Output<TFloat32> minOutput() {
    return minOutput;
  }
  
  /**
   * The float value that the maximum quantized output value represents.
   */
  public Output<TFloat32> maxOutput() {
    return maxOutput;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize";
  
  private Output<W> output;
  private Output<TFloat32> minOutput;
  private Output<TFloat32> maxOutput;
  
  private QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    minOutput = operation.output(outputIdx++);
    maxOutput = operation.output(outputIdx++);
  }
}
