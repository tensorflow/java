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
import org.tensorflow.types.family.TNumber;

/**
 * Computes a 2-D convolution given 4-D {@code input} and {@code filter} tensors.
 * Given an input tensor of shape {@code [batch, in_height, in_width, in_channels]}
 * and a filter / kernel tensor of shape
 * {@code [filter_height, filter_width, in_channels, out_channels]}, this op
 * performs the following:
 * <ol>
 * <li>Flattens the filter to a 2-D matrix with shape
 * {@code [filter_height * filter_width * in_channels, output_channels]}.</li>
 * <li>Extracts image patches from the input tensor to form a <em>virtual</em>
 * tensor of shape {@code [batch, out_height, out_width, filter_height * filter_width * in_channels]}.</li>
 * <li>For each patch, right-multiplies the filter matrix and the image patch
 * vector.</li>
 * </ol>
 * <p>In detail, with the default NHWC format,
 * <pre>
 * output[b, i, j, k] =
 *     sum_{di, dj, q} input[b, strides[1] * i + di, strides[2] * j + dj, q] *
 *                     filter[di, dj, q, k]
 * </pre>
 * <p>Must have {@code strides[0] = strides[3] = 1}.  For the most common case of the same
 * horizontal and vertices strides, {@code strides = [1, stride, stride, 1]}.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = Conv2d.OP_NAME,
    inputsClass = Conv2d.Inputs.class
)
@Operator(
    group = "nn"
)
public final class Conv2d<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Conv2D";

  private Output<T> output;

  public Conv2d(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Conv2D operation.
   *
   * @param scope current scope
   * @param input A 4-D tensor. The dimension order is interpreted according to the value
   * of {@code data_format}, see below for details.
   * @param filter A 4-D tensor of shape
   * {@code [filter_height, filter_width, in_channels, out_channels]}
   * @param strides 1-D tensor of length 4.  The stride of the sliding window for each
   * dimension of {@code input}. The dimension order is determined by the value of
   * {@code data_format}, see below for details.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Conv2D} output and operands
   * @return a new instance of Conv2d
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Conv2d<T> create(Scope scope, Operand<T> input,
      Operand<T> filter, List<Long> strides, String padding, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Conv2d");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(filter.asOutput());
    long[] stridesArray = new long[strides.size()];
    for (int i = 0 ; i < stridesArray.length ; i++) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useCudnnOnGpu != null) {
          opBuilder.setAttr("use_cudnn_on_gpu", opts.useCudnnOnGpu);
        }
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
    return new Conv2d<>(opBuilder.build());
  }

  /**
   * Sets the useCudnnOnGpu option.
   *
   * @param useCudnnOnGpu the useCudnnOnGpu option
   * @return this Options instance.
   */
  public static Options useCudnnOnGpu(Boolean useCudnnOnGpu) {
    return new Options().useCudnnOnGpu(useCudnnOnGpu);
  }

  /**
   * Sets the explicitPaddings option.
   *
   * @param explicitPaddings If {@code padding} is {@code "EXPLICIT"}, the list of explicit padding amounts. For the ith
   * dimension, the amount of padding inserted before and after the dimension is
   * {@code explicit_paddings[2 * i]} and {@code explicit_paddings[2 * i + 1]}, respectively. If
   * {@code padding} is not {@code "EXPLICIT"}, {@code explicit_paddings} must be empty.
   * @return this Options instance.
   */
  public static Options explicitPaddings(List<Long> explicitPaddings) {
    return new Options().explicitPaddings(explicitPaddings);
  }

  /**
   * Sets the explicitPaddings option.
   *
   * @param explicitPaddings If {@code padding} is {@code "EXPLICIT"}, the list of explicit padding amounts. For the ith
   * dimension, the amount of padding inserted before and after the dimension is
   * {@code explicit_paddings[2 * i]} and {@code explicit_paddings[2 * i + 1]}, respectively. If
   * {@code padding} is not {@code "EXPLICIT"}, {@code explicit_paddings} must be empty.
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
   * {@code input}. If set to k &gt; 1, there will be k-1 skipped cells between each
   * filter element on that dimension. The dimension order is determined by the
   * value of {@code data_format}, see above for details. Dilations in the batch and
   * depth dimensions must be 1.
   * @return this Options instance.
   */
  public static Options dilations(List<Long> dilations) {
    return new Options().dilations(dilations);
  }

  /**
   * Sets the dilations option.
   *
   * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
   * {@code input}. If set to k &gt; 1, there will be k-1 skipped cells between each
   * filter element on that dimension. The dimension order is determined by the
   * value of {@code data_format}, see above for details. Dilations in the batch and
   * depth dimensions must be 1.
   * @return this Options instance.
   */
  public static Options dilations(Long... dilations) {
    return new Options().dilations(dilations);
  }

  /**
   * Gets output.
   * A 4-D tensor. The dimension order is determined by the value of
   * {@code data_format}, see below for details.
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
   * Optional attributes for {@link org.tensorflow.op.nn.Conv2d}
   */
  public static class Options {
    private Boolean useCudnnOnGpu;

    private List<Long> explicitPaddings;

    private String dataFormat;

    private List<Long> dilations;

    private Options() {
    }

    /**
     * Sets the useCudnnOnGpu option.
     *
     * @param useCudnnOnGpu the useCudnnOnGpu option
     * @return this Options instance.
     */
    public Options useCudnnOnGpu(Boolean useCudnnOnGpu) {
      this.useCudnnOnGpu = useCudnnOnGpu;
      return this;
    }

    /**
     * Sets the explicitPaddings option.
     *
     * @param explicitPaddings If {@code padding} is {@code "EXPLICIT"}, the list of explicit padding amounts. For the ith
     * dimension, the amount of padding inserted before and after the dimension is
     * {@code explicit_paddings[2 * i]} and {@code explicit_paddings[2 * i + 1]}, respectively. If
     * {@code padding} is not {@code "EXPLICIT"}, {@code explicit_paddings} must be empty.
     * @return this Options instance.
     */
    public Options explicitPaddings(List<Long> explicitPaddings) {
      this.explicitPaddings = explicitPaddings;
      return this;
    }

    /**
     * Sets the explicitPaddings option.
     *
     * @param explicitPaddings If {@code padding} is {@code "EXPLICIT"}, the list of explicit padding amounts. For the ith
     * dimension, the amount of padding inserted before and after the dimension is
     * {@code explicit_paddings[2 * i]} and {@code explicit_paddings[2 * i + 1]}, respectively. If
     * {@code padding} is not {@code "EXPLICIT"}, {@code explicit_paddings} must be empty.
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
     * {@code input}. If set to k &gt; 1, there will be k-1 skipped cells between each
     * filter element on that dimension. The dimension order is determined by the
     * value of {@code data_format}, see above for details. Dilations in the batch and
     * depth dimensions must be 1.
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
     * {@code input}. If set to k &gt; 1, there will be k-1 skipped cells between each
     * filter element on that dimension. The dimension order is determined by the
     * value of {@code data_format}, see above for details. Dilations in the batch and
     * depth dimensions must be 1.
     * @return this Options instance.
     */
    public Options dilations(Long... dilations) {
      this.dilations = Arrays.asList(dilations);
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Conv2d.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<Conv2d<T>> {
    /**
     * A 4-D tensor. The dimension order is interpreted according to the value
     * of {@code data_format}, see below for details.
     */
    public final Operand<T> input;

    /**
     * A 4-D tensor of shape
     * {@code [filter_height, filter_width, in_channels, out_channels]}
     */
    public final Operand<T> filter;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * 1-D tensor of length 4.  The stride of the sliding window for each
     * dimension of `input`. The dimension order is determined by the value of
     * `data_format`, see below for details.
     */
    public final long[] strides;

    /**
     * The useCudnnOnGpu attribute
     */
    public final boolean useCudnnOnGpu;

    /**
     * The type of padding algorithm to use.
     */
    public final String padding;

    /**
     * If `padding` is `"EXPLICIT"`, the list of explicit padding amounts. For the ith
     * dimension, the amount of padding inserted before and after the dimension is
     * `explicit_paddings[2 * i]` and `explicit_paddings[2 * i + 1]`, respectively. If
     * `padding` is not `"EXPLICIT"`, `explicit_paddings` must be empty.
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
     * `input`. If set to k > 1, there will be k-1 skipped cells between each
     * filter element on that dimension. The dimension order is determined by the
     * value of `data_format`, see above for details. Dilations in the batch and
     * depth dimensions must be 1.
     */
    public final long[] dilations;

    public Inputs(GraphOperation op) {
      super(new Conv2d<>(op), op, Arrays.asList("T", "strides", "use_cudnn_on_gpu", "padding", "explicit_paddings", "data_format", "dilations"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      filter = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      strides = op.attributes().getAttrIntList("strides");
      useCudnnOnGpu = op.attributes().getAttrBool("use_cudnn_on_gpu");
      padding = op.attributes().getAttrString("padding");
      explicitPaddings = op.attributes().getAttrIntList("explicit_paddings");
      dataFormat = op.attributes().getAttrString("data_format");
      dilations = op.attributes().getAttrIntList("dilations");
    }
  }
}
