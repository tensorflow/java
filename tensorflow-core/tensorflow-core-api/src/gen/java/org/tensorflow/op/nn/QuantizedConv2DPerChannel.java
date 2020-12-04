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
import org.tensorflow.DataType;
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
 * Computes QuantizedConv2D per channel.
 * 
 * @param <V> data type for {@code output()} output
 */
public final class QuantizedConv2DPerChannel<V extends TType> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.QuantizedConv2DPerChannel}
   */
  public static class Options {
    
    /**
     * @param dilations list of dilation values.
     */
    public Options dilations(List<Long> dilations) {
      this.dilations = dilations;
      return this;
    }
    
    private List<Long> dilations;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new QuantizedConv2DPerChannel operation.
   * 
   * @param scope current scope
   * @param input The original input tensor.
   * @param filter The original filter tensor.
   * @param minInput The minimum value of the input tensor
   * @param maxInput The maximum value of the input tensor.
   * @param minFilter The minimum value of the filter tensor.
   * @param maxFilter The maximum value of the filter tensor.
   * @param outType The quantized type of output tensor that needs to be converted.
   * @param strides list of stride values.
   * @param padding 
   * @param options carries optional attributes values
   * @return a new instance of QuantizedConv2DPerChannel
   */
  @Endpoint(describeByClass = true)
  public static <V extends TType, T extends TType, U extends TType> QuantizedConv2DPerChannel<V> create(Scope scope, Operand<T> input, Operand<U> filter, Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter, Operand<TFloat32> maxFilter, DataType<V> outType, List<Long> strides, String padding, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizedConv2DPerChannel", scope.makeOpName("QuantizedConv2DPerChannel"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(filter.asOutput());
    opBuilder.addInput(minInput.asOutput());
    opBuilder.addInput(maxInput.asOutput());
    opBuilder.addInput(minFilter.asOutput());
    opBuilder.addInput(maxFilter.asOutput());
    opBuilder = scope.apply(opBuilder);
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
      }
    }
    return new QuantizedConv2DPerChannel<V>(opBuilder.build());
  }
  
  /**
   * @param dilations list of dilation values.
   */
  public static Options dilations(List<Long> dilations) {
    return new Options().dilations(dilations);
  }
  
  /**
   * The output tensor.
   */
  public Output<V> output() {
    return output;
  }
  
  /**
   * The minimum value of the final output tensor.
   */
  public Output<TFloat32> minOutput() {
    return minOutput;
  }
  
  /**
   * The maximum value of the final output tensor.
   */
  public Output<TFloat32> maxOutput() {
    return maxOutput;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "QuantizedConv2DPerChannel";
  
  private Output<V> output;
  private Output<TFloat32> minOutput;
  private Output<TFloat32> maxOutput;
  
  private QuantizedConv2DPerChannel(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    minOutput = operation.output(outputIdx++);
    maxOutput = operation.output(outputIdx++);
  }
}
