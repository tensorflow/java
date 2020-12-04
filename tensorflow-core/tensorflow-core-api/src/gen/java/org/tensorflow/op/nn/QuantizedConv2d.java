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
 * Computes a 2D convolution given quantized 4D input and filter tensors.
 * <p>
 * The inputs are quantized tensors where the lowest value represents the real
 * number of the associated minimum, and the highest represents the maximum.
 * This means that you can only interpret the quantized output in the same way, by
 * taking the returned minimum and maximum values into account.
 * 
 * @param <V> data type for {@code output()} output
 */
@Operator(group = "nn")
public final class QuantizedConv2d<V extends TType> extends RawOp {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.QuantizedConv2d}
   */
  public static class Options {
    
    /**
     * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
     * `input`. If set to k > 1, there will be k-1 skipped cells between each
     * filter element on that dimension. The dimension order is determined by the
     * value of `data_format`, see above for details. Dilations in the batch and
     * depth dimensions must be 1.
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
   * Factory method to create a class wrapping a new QuantizedConv2d operation.
   * 
   * @param scope current scope
   * @param input 
   * @param filter filter's input_depth dimension must match input's depth dimensions.
   * @param minInput The float value that the lowest quantized input value represents.
   * @param maxInput The float value that the highest quantized input value represents.
   * @param minFilter The float value that the lowest quantized filter value represents.
   * @param maxFilter The float value that the highest quantized filter value represents.
   * @param outType 
   * @param strides The stride of the sliding window for each dimension of the input
   * tensor.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of QuantizedConv2d
   */
  @Endpoint(describeByClass = true)
  public static <V extends TType, T extends TType, U extends TType> QuantizedConv2d<V> create(Scope scope, Operand<T> input, Operand<U> filter, Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter, Operand<TFloat32> maxFilter, DataType<V> outType, List<Long> strides, String padding, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizedConv2D", scope.makeOpName("QuantizedConv2d"));
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
    return new QuantizedConv2d<V>(opBuilder.build());
  }
  
  /**
   * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
   * `input`. If set to k > 1, there will be k-1 skipped cells between each
   * filter element on that dimension. The dimension order is determined by the
   * value of `data_format`, see above for details. Dilations in the batch and
   * depth dimensions must be 1.
   */
  public static Options dilations(List<Long> dilations) {
    return new Options().dilations(dilations);
  }
  
  /**
   */
  public Output<V> output() {
    return output;
  }
  
  /**
   * The float value that the lowest quantized output value represents.
   */
  public Output<TFloat32> minOutput() {
    return minOutput;
  }
  
  /**
   * The float value that the highest quantized output value represents.
   */
  public Output<TFloat32> maxOutput() {
    return maxOutput;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "QuantizedConv2D";
  
  private Output<V> output;
  private Output<TFloat32> minOutput;
  private Output<TFloat32> maxOutput;
  
  private QuantizedConv2d(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    minOutput = operation.output(outputIdx++);
    maxOutput = operation.output(outputIdx++);
  }
}
