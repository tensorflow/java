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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes QuantizedConv2D per channel.
 *
 * @param <V> data type for {@code output} output
 */
@OpMetadata(
    opType = QuantizedConv2DPerChannel.OP_NAME,
    inputsClass = QuantizedConv2DPerChannel.Inputs.class
)
public final class QuantizedConv2DPerChannel<V extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizedConv2DPerChannel";

  private Output<V> output;

  private Output<TFloat32> minOutput;

  private Output<TFloat32> maxOutput;

  public QuantizedConv2DPerChannel(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
    minOutput = operation.output(outputIdx++);
    maxOutput = operation.output(outputIdx++);
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
   * @param padding The value of the padding attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code QuantizedConv2DPerChannel} output and operands
   * @return a new instance of QuantizedConv2DPerChannel
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> QuantizedConv2DPerChannel<V> create(Scope scope,
      Operand<? extends TNumber> input, Operand<? extends TNumber> filter,
      Operand<TFloat32> minInput, Operand<TFloat32> maxInput, Operand<TFloat32> minFilter,
      Operand<TFloat32> maxFilter, Class<V> outType, List<Long> strides, String padding,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizedConv2DPerChannel");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(filter.asOutput());
    opBuilder.addInput(minInput.asOutput());
    opBuilder.addInput(maxInput.asOutput());
    opBuilder.addInput(minFilter.asOutput());
    opBuilder.addInput(maxFilter.asOutput());
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
    return new QuantizedConv2DPerChannel<>(opBuilder.build());
  }

  /**
   * Sets the dilations option.
   *
   * @param dilations list of dilation values.
   * @return this Options instance.
   */
  public static Options dilations(List<Long> dilations) {
    return new Options().dilations(dilations);
  }

  /**
   * Sets the dilations option.
   *
   * @param dilations list of dilation values.
   * @return this Options instance.
   */
  public static Options dilations(Long... dilations) {
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
   * The minimum value of the final output tensor.
   * @return minOutput.
   */
  public Output<TFloat32> minOutput() {
    return minOutput;
  }

  /**
   * Gets maxOutput.
   * The maximum value of the final output tensor.
   * @return maxOutput.
   */
  public Output<TFloat32> maxOutput() {
    return maxOutput;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.QuantizedConv2DPerChannel}
   */
  public static class Options {
    private List<Long> dilations;

    private Options() {
    }

    /**
     * Sets the dilations option.
     *
     * @param dilations list of dilation values.
     * @return this Options instance.
     */
    public Options dilations(List<Long> dilations) {
      this.dilations = dilations;
      return this;
    }

    /**
     * Sets the dilations option.
     *
     * @param dilations list of dilation values.
     * @return this Options instance.
     */
    public Options dilations(Long... dilations) {
      this.dilations = Arrays.asList(dilations);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = QuantizedConv2DPerChannel.class
  )
  public static class Inputs extends RawOpInputs<QuantizedConv2DPerChannel<?>> {
    /**
     * The original input tensor.
     */
    public final Operand<? extends TNumber> input;

    /**
     * The original filter tensor.
     */
    public final Operand<? extends TNumber> filter;

    /**
     * The minimum value of the input tensor
     */
    public final Operand<TFloat32> minInput;

    /**
     * The maximum value of the input tensor.
     */
    public final Operand<TFloat32> maxInput;

    /**
     * The minimum value of the filter tensor.
     */
    public final Operand<TFloat32> minFilter;

    /**
     * The maximum value of the filter tensor.
     */
    public final Operand<TFloat32> maxFilter;

    /**
     * The quantized type of input tensor that needs to be converted.
     */
    public final DataType Tinput;

    /**
     * The quantized type of filter tensor that needs to be converted.
     */
    public final DataType Tfilter;

    /**
     * The quantized type of output tensor that needs to be converted.
     */
    public final DataType outType;

    /**
     * list of stride values.
     */
    public final long[] strides;

    /**
     * The padding attribute
     */
    public final String padding;

    /**
     * list of dilation values.
     */
    public final long[] dilations;

    public Inputs(GraphOperation op) {
      super(new QuantizedConv2DPerChannel<>(op), op, Arrays.asList("Tinput", "Tfilter", "out_type", "strides", "padding", "dilations"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      filter = (Operand<? extends TNumber>) op.input(inputIndex++);
      minInput = (Operand<TFloat32>) op.input(inputIndex++);
      maxInput = (Operand<TFloat32>) op.input(inputIndex++);
      minFilter = (Operand<TFloat32>) op.input(inputIndex++);
      maxFilter = (Operand<TFloat32>) op.input(inputIndex++);
      Tinput = op.attributes().getAttrType("Tinput");
      Tfilter = op.attributes().getAttrType("Tfilter");
      outType = op.attributes().getAttrType("out_type");
      strides = op.attributes().getAttrIntList("strides");
      padding = op.attributes().getAttrString("padding");
      dilations = op.attributes().getAttrIntList("dilations");
    }
  }
}
