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

import java.util.Arrays;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes quantized depthwise Conv2D.
 *
 * @param <V> data type for {@code output} output
 */
public final class QuantizedDepthwiseConv2D<V extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedDepthwiseConv2D";

  private Output<V> output;

  private Output<TFloat32> minOutput;

  private Output<TFloat32> maxOutput;

  private QuantizedDepthwiseConv2D(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    minOutput = operation.output(outputIdx++);
    maxOutput = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizedDepthwiseConv2D operation.
   *
   * @param scope current scope
   * @param input The original input tensor.
   * @param filter The original filter tensor.
   * @param minInput The float value that the minimum quantized input value represents.
   * @param maxInput The float value that the maximum quantized input value represents.
   * @param minFilter The float value that the minimum quantized filter value represents.
   * @param maxFilter The float value that the maximum quantized filter value represents.
   * @param outType The type of the output.
   * @param strides List of stride values.
   * @param padding the value of the padding property
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedDepthwiseConv2D} output and operands
   * @return a new instance of QuantizedDepthwiseConv2D
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> QuantizedDepthwiseConv2D<V> create(Scope scope,
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter,
      Operand<TFloat32> maxFilter, Class<V> outType, List<Long> strides, String padding,
      Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("QuantizedDepthwiseConv2D", scope.makeOpName("QuantizedDepthwiseConv2D"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(filter.asOutput());
    opBuilder.addInput(minInput.asOutput());
    opBuilder.addInput(maxInput.asOutput());
    opBuilder.addInput(minFilter.asOutput());
    opBuilder.addInput(maxFilter.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    long[] stridesArray = new long[strides.size()];
    for (int i = 0 ; i < stridesArray.length ; i++) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.dilations != null) {
          long[] dilationsArray = new long[opts.dilations.size()];
          for (int i = 0 ; i < dilationsArray.length ; i++) {
            dilationsArray[i] = opts.dilations.get(i);
          }
          opBuilder.setAttr("dilations", dilationsArray);
        }
      }
    }
    return new QuantizedDepthwiseConv2D<>(opBuilder.build());
  }

  /**
   * Sets the dilations option.
   *
   * @param dilations List of dilation values.
   * @return this Options instance.
   */
  public static Options dilations(List<Long> dilations) {
    return new Options().dilations(dilations);
  }

  /**
   * Sets the dilations option.
   *
   * @param dilations List of dilation values.
   * @return this Options instance.
   */
  public static Options dilations(Long[] dilations) {
    return new Options().dilations(dilations);
  }

  /**
   * Gets output.
   * The output tensor.
   * @return output.
   */
  public Output<V> output() {
    return output;
  }

  /**
   * Gets minOutput.
   * The float value that the minimum quantized output value represents.
   * @return minOutput.
   */
  public Output<TFloat32> minOutput() {
    return minOutput;
  }

  /**
   * Gets maxOutput.
   * The float value that the maximum quantized output value represents.
   * @return maxOutput.
   */
  public Output<TFloat32> maxOutput() {
    return maxOutput;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.QuantizedDepthwiseConv2D}
   */
  public static class Options {
    private List<Long> dilations;

    private Options() {
    }

    /**
     * Sets the dilations option.
     *
     * @param dilations List of dilation values.
     * @return this Options instance.
     */
    public Options dilations(List<Long> dilations) {
      this.dilations = dilations;
      return this;
    }

    /**
     * Sets the dilations option.
     *
     * @param dilations List of dilation values.
     * @return this Options instance.
     */
    public Options dilations(Long... dilations) {
      this.dilations = Arrays.asList(dilations);
      return this;
    }
  }
}
