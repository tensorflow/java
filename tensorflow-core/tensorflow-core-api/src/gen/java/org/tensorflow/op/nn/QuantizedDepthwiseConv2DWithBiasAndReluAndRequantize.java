/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes quantized depthwise Conv2D with Bias, Relu and Requantize.
 *
 * @param <W> data type for {@code output} output
 */
@OpMetadata(
    opType = QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize.OP_NAME,
    inputsClass = QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize.Inputs.class
)
public final class QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize<W extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize";

  private Output<W> output;

  private Output<TFloat32> minOutput;

  private Output<TFloat32> maxOutput;

  public QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    minOutput = operation.output(outputIdx++);
    maxOutput = operation.output(outputIdx++);
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
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <W> data type for {@code QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize} output and operands
   * @return a new instance of QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize
   */
  @Endpoint(
      describeByClass = true
  )
  public static <W extends TNumber> QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize<W> create(
      Scope scope, Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<? extends TNumber> bias, Operand<TFloat32> minInput, Operand<TFloat32> maxInput,
      Operand<TFloat32> minFilter, Operand<TFloat32> maxFilter, Operand<TFloat32> minFreezedOutput,
      Operand<TFloat32> maxFreezedOutput, Class<W> outType, List<Long> strides, String padding,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(filter.asOutput());
    opBuilder.addInput(bias.asOutput());
    opBuilder.addInput(minInput.asOutput());
    opBuilder.addInput(maxInput.asOutput());
    opBuilder.addInput(minFilter.asOutput());
    opBuilder.addInput(maxFilter.asOutput());
    opBuilder.addInput(minFreezedOutput.asOutput());
    opBuilder.addInput(maxFreezedOutput.asOutput());
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
        if (opts.paddingList != null) {
          long[] paddingListArray = new long[opts.paddingList.size()];
          for (int i = 0 ; i < paddingListArray.length ; i++) {
            paddingListArray[i] = opts.paddingList.get(i);
          }
          opBuilder.setAttr("padding_list", paddingListArray);
        }
      }
    }
    return new QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize<>(opBuilder.build());
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
  public static Options dilations(Long... dilations) {
    return new Options().dilations(dilations);
  }

  /**
   * Sets the paddingList option.
   *
   * @param paddingList the paddingList option
   * @return this Options instance.
   */
  public static Options paddingList(List<Long> paddingList) {
    return new Options().paddingList(paddingList);
  }

  /**
   * Sets the paddingList option.
   *
   * @param paddingList the paddingList option
   * @return this Options instance.
   */
  public static Options paddingList(Long... paddingList) {
    return new Options().paddingList(paddingList);
  }

  /**
   * Gets output.
   * The output tensor.
   * @return output.
   */
  public Output<W> output() {
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
   * Optional attributes for {@link org.tensorflow.op.nn.QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize}
   */
  public static class Options {
    private List<Long> dilations;

    private List<Long> paddingList;

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

    /**
     * Sets the paddingList option.
     *
     * @param paddingList the paddingList option
     * @return this Options instance.
     */
    public Options paddingList(List<Long> paddingList) {
      this.paddingList = paddingList;
      return this;
    }

    /**
     * Sets the paddingList option.
     *
     * @param paddingList the paddingList option
     * @return this Options instance.
     */
    public Options paddingList(Long... paddingList) {
      this.paddingList = Arrays.asList(paddingList);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize.class
  )
  public static class Inputs extends RawOpInputs<QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize<?>> {
    /**
     * The original input tensor.
     */
    public final Operand<? extends TNumber> input;

    /**
     * The original filter tensor.
     */
    public final Operand<? extends TNumber> filter;

    /**
     * The original bias tensor.
     */
    public final Operand<? extends TNumber> bias;

    /**
     * The float value that the minimum quantized input value represents.
     */
    public final Operand<TFloat32> minInput;

    /**
     * The float value that the maximum quantized input value represents.
     */
    public final Operand<TFloat32> maxInput;

    /**
     * The float value that the minimum quantized filter value represents.
     */
    public final Operand<TFloat32> minFilter;

    /**
     * The float value that the maximum quantized filter value represents.
     */
    public final Operand<TFloat32> maxFilter;

    /**
     * The minimum float value of the output tensor.
     */
    public final Operand<TFloat32> minFreezedOutput;

    /**
     * The maximum float value of the output tensor.
     */
    public final Operand<TFloat32> maxFreezedOutput;

    /**
     * The type of the input.
     */
    public final DataType Tinput;

    /**
     * The type of the filter.
     */
    public final DataType Tfilter;

    /**
     * The type of the bias.
     */
    public final DataType Tbias;

    /**
     * The type of the output.
     */
    public final DataType outType;

    /**
     * List of stride values.
     */
    public final long[] strides;

    /**
     * The padding attribute
     */
    public final String padding;

    /**
     * List of dilation values.
     */
    public final long[] dilations;

    /**
     * The paddingList attribute
     */
    public final long[] paddingList;

    public Inputs(GraphOperation op) {
      super(new QuantizedDepthwiseConv2DWithBiasAndReluAndRequantize<>(op), op, Arrays.asList("Tinput", "Tfilter", "Tbias", "out_type", "strides", "padding", "dilations", "padding_list"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      filter = (Operand<? extends TNumber>) op.input(inputIndex++);
      bias = (Operand<? extends TNumber>) op.input(inputIndex++);
      minInput = (Operand<TFloat32>) op.input(inputIndex++);
      maxInput = (Operand<TFloat32>) op.input(inputIndex++);
      minFilter = (Operand<TFloat32>) op.input(inputIndex++);
      maxFilter = (Operand<TFloat32>) op.input(inputIndex++);
      minFreezedOutput = (Operand<TFloat32>) op.input(inputIndex++);
      maxFreezedOutput = (Operand<TFloat32>) op.input(inputIndex++);
      Tinput = op.attributes().getAttrType("Tinput");
      Tfilter = op.attributes().getAttrType("Tfilter");
      Tbias = op.attributes().getAttrType("Tbias");
      outType = op.attributes().getAttrType("out_type");
      strides = op.attributes().getAttrIntList("strides");
      padding = op.attributes().getAttrString("padding");
      dilations = op.attributes().getAttrIntList("dilations");
      paddingList = op.attributes().getAttrIntList("padding_list");
    }
  }
}
