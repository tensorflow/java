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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the gradients of depthwise convolution with respect to the filter.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = DepthwiseConv2dNativeBackpropFilter.OP_NAME,
    inputsClass = DepthwiseConv2dNativeBackpropFilter.Inputs.class
)
@Operator(
    group = "nn"
)
public final class DepthwiseConv2dNativeBackpropFilter<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "DepthwiseConv2dNativeBackpropFilter";

  private Output<T> output;

  public DepthwiseConv2dNativeBackpropFilter(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new DepthwiseConv2dNativeBackpropFilter operation.
   *
   * @param scope current scope
   * @param input 4-D with shape based on {@code data_format}.  For example, if
   * {@code data_format} is 'NHWC' then {@code input} is a 4-D {@code [batch, in_height, in_width, in_channels]} tensor.
   * @param filterSizes An integer vector representing the tensor shape of {@code filter},
   * where {@code filter} is a 4-D
   * {@code [filter_height, filter_width, in_channels, depthwise_multiplier]} tensor.
   * @param outBackprop 4-D with shape  based on {@code data_format}.
   * For example, if {@code data_format} is 'NHWC' then
   * out_backprop shape is {@code [batch, out_height, out_width, out_channels]}.
   * Gradients w.r.t. the output of the convolution.
   * @param strides The stride of the sliding window for each dimension of the input
   * of the convolution.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code DepthwiseConv2dNativeBackpropFilter} output and operands
   * @return a new instance of DepthwiseConv2dNativeBackpropFilter
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> DepthwiseConv2dNativeBackpropFilter<T> create(Scope scope,
      Operand<T> input, Operand<TInt32> filterSizes, Operand<T> outBackprop, List<Long> strides,
      String padding, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "DepthwiseConv2dNativeBackpropFilter");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(filterSizes.asOutput());
    opBuilder.addInput(outBackprop.asOutput());
    long[] stridesArray = new long[strides.size()];
    for (int i = 0 ; i < stridesArray.length ; i++) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.explicitPaddings != null) {
          long[] explicitPaddingsArray = new long[opts.explicitPaddings.size()];
          for (int i = 0 ; i < explicitPaddingsArray.length ; i++) {
            explicitPaddingsArray[i] = opts.explicitPaddings.get(i);
          }
          opBuilder.setAttr("explicit_paddings", explicitPaddingsArray);
        }
        if (opts.dataFormat != null) {
          opBuilder.setAttr("data_format", opts.dataFormat);
        }
        if (opts.dilations != null) {
          long[] dilationsArray = new long[opts.dilations.size()];
          for (int i = 0 ; i < dilationsArray.length ; i++) {
            dilationsArray[i] = opts.dilations.get(i);
          }
          opBuilder.setAttr("dilations", dilationsArray);
        }
      }
    }
    return new DepthwiseConv2dNativeBackpropFilter<>(opBuilder.build());
  }

  /**
   * Sets the explicitPaddings option.
   *
   * @param explicitPaddings the explicitPaddings option
   * @return this Options instance.
   */
  public static Options explicitPaddings(List<Long> explicitPaddings) {
    return new Options().explicitPaddings(explicitPaddings);
  }

  /**
   * Sets the explicitPaddings option.
   *
   * @param explicitPaddings the explicitPaddings option
   * @return this Options instance.
   */
  public static Options explicitPaddings(Long... explicitPaddings) {
    return new Options().explicitPaddings(explicitPaddings);
  }

  /**
   * Sets the dataFormat option.
   *
   * @param dataFormat Specify the data format of the input and output data. With the
   * default format &quot;NHWC&quot;, the data is stored in the order of:
   * [batch, height, width, channels].
   * Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
   * [batch, channels, height, width].
   * @return this Options instance.
   */
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
  }

  /**
   * Sets the dilations option.
   *
   * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
   * {@code input}. If set to k &gt; 1, there will be k-1 skipped cells between each filter
   * element on that dimension. The dimension order is determined by the value of
   * {@code data_format}, see above for details. Dilations in the batch and depth
   * dimensions must be 1.
   * @return this Options instance.
   */
  public static Options dilations(List<Long> dilations) {
    return new Options().dilations(dilations);
  }

  /**
   * Sets the dilations option.
   *
   * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
   * {@code input}. If set to k &gt; 1, there will be k-1 skipped cells between each filter
   * element on that dimension. The dimension order is determined by the value of
   * {@code data_format}, see above for details. Dilations in the batch and depth
   * dimensions must be 1.
   * @return this Options instance.
   */
  public static Options dilations(Long... dilations) {
    return new Options().dilations(dilations);
  }

  /**
   * Gets output.
   * 4-D with shape
   * {@code [filter_height, filter_width, in_channels, out_channels]}.  Gradient w.r.t.
   * the {@code filter} input of the convolution.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropFilter}
   */
  public static class Options {
    private List<Long> explicitPaddings;

    private String dataFormat;

    private List<Long> dilations;

    private Options() {
    }

    /**
     * Sets the explicitPaddings option.
     *
     * @param explicitPaddings the explicitPaddings option
     * @return this Options instance.
     */
    public Options explicitPaddings(List<Long> explicitPaddings) {
      this.explicitPaddings = explicitPaddings;
      return this;
    }

    /**
     * Sets the explicitPaddings option.
     *
     * @param explicitPaddings the explicitPaddings option
     * @return this Options instance.
     */
    public Options explicitPaddings(Long... explicitPaddings) {
      this.explicitPaddings = Arrays.asList(explicitPaddings);
      return this;
    }

    /**
     * Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     * default format &quot;NHWC&quot;, the data is stored in the order of:
     * [batch, height, width, channels].
     * Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     * [batch, channels, height, width].
     * @return this Options instance.
     */
    public Options dataFormat(String dataFormat) {
      this.dataFormat = dataFormat;
      return this;
    }

    /**
     * Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
     * {@code input}. If set to k &gt; 1, there will be k-1 skipped cells between each filter
     * element on that dimension. The dimension order is determined by the value of
     * {@code data_format}, see above for details. Dilations in the batch and depth
     * dimensions must be 1.
     * @return this Options instance.
     */
    public Options dilations(List<Long> dilations) {
      this.dilations = dilations;
      return this;
    }

    /**
     * Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
     * {@code input}. If set to k &gt; 1, there will be k-1 skipped cells between each filter
     * element on that dimension. The dimension order is determined by the value of
     * {@code data_format}, see above for details. Dilations in the batch and depth
     * dimensions must be 1.
     * @return this Options instance.
     */
    public Options dilations(Long... dilations) {
      this.dilations = Arrays.asList(dilations);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = DepthwiseConv2dNativeBackpropFilter.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<DepthwiseConv2dNativeBackpropFilter<T>> {
    /**
     * 4-D with shape based on {@code data_format}.  For example, if
     * {@code data_format} is 'NHWC' then {@code input} is a 4-D {@code [batch, in_height, in_width, in_channels]} tensor.
     */
    public final Operand<T> input;

    /**
     * An integer vector representing the tensor shape of {@code filter},
     * where {@code filter} is a 4-D
     * {@code [filter_height, filter_width, in_channels, depthwise_multiplier]} tensor.
     */
    public final Operand<TInt32> filterSizes;

    /**
     * 4-D with shape  based on {@code data_format}.
     * For example, if {@code data_format} is 'NHWC' then
     * out_backprop shape is {@code [batch, out_height, out_width, out_channels]}.
     * Gradients w.r.t. the output of the convolution.
     */
    public final Operand<T> outBackprop;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The stride of the sliding window for each dimension of the input
     * of the convolution.
     */
    public final long[] strides;

    /**
     * The type of padding algorithm to use.
     */
    public final String padding;

    /**
     * The explicitPaddings attribute
     */
    public final long[] explicitPaddings;

    /**
     * Specify the data format of the input and output data. With the
     * default format "NHWC", the data is stored in the order of:
     *     [batch, height, width, channels].
     * Alternatively, the format could be "NCHW", the data storage order of:
     *     [batch, channels, height, width].
     */
    public final String dataFormat;

    /**
     * 1-D tensor of length 4.  The dilation factor for each dimension of
     * `input`. If set to k > 1, there will be k-1 skipped cells between each filter
     * element on that dimension. The dimension order is determined by the value of
     * `data_format`, see above for details. Dilations in the batch and depth
     * dimensions must be 1.
     */
    public final long[] dilations;

    public Inputs(GraphOperation op) {
      super(new DepthwiseConv2dNativeBackpropFilter<>(op), op, Arrays.asList("T", "strides", "padding", "explicit_paddings", "data_format", "dilations"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      filterSizes = (Operand<TInt32>) op.input(inputIndex++);
      outBackprop = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      strides = op.attributes().getAttrIntList("strides");
      padding = op.attributes().getAttrString("padding");
      explicitPaddings = op.attributes().getAttrIntList("explicit_paddings");
      dataFormat = op.attributes().getAttrString("data_format");
      dilations = op.attributes().getAttrIntList("dilations");
    }
  }
}
