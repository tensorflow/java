/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.proto.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Computes a N-D convolution given (N+1+batch_dims)-D {@code input} and (N+2)-D {@code filter} tensors.
 * General function for computing a N-D convolution. It is required that
 * {@code 1 <= N <= 3}.
 */
@OpMetadata(
    opType = Conv.OP_NAME,
    inputsClass = Conv.Inputs.class
)
@Operator(
    group = "nn"
)
public final class Conv<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Conv";

  private Output<T> output;

  public Conv(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Conv operation.
   *
   * @param scope current scope
   * @param input Tensor of type T and shape {@code batch_shape + spatial_shape + [in_channels]} in the
   * case that {@code channels_last_format = true} or shape
   * {@code batch_shape + [in_channels] + spatial_shape} if {@code channels_last_format = false}.
   * spatial_shape is N-dimensional with {@code N=2} or {@code N=3}.
   * Also note that {@code batch_shape} is dictated by the parameter {@code batch_dims}
   * and defaults to 1.
   * @param filter An {@code (N+2)-D} Tensor with the same type as {@code input} and shape
   * {@code spatial_filter_shape + [in_channels, out_channels]}, where spatial_filter_shape
   * is N-dimensional with {@code N=2} or {@code N=3}.
   * @param strides 1-D tensor of length {@code N+2}. The stride of the sliding window for each
   * dimension of {@code input}. Must have {@code strides[0] = strides[N+1] = 1}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Conv} output and operands
   * @return a new instance of Conv
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> Conv<T> create(Scope scope, Operand<T> input, Operand<T> filter,
      List<Long> strides, String padding, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Conv");
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
        if (opts.batchDims != null) {
          opBuilder.setAttr("batch_dims", opts.batchDims);
        }
        if (opts.groups != null) {
          opBuilder.setAttr("groups", opts.groups);
        }
      }
    }
    return new Conv<>(opBuilder.build());
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
   * @param dataFormat Used to set the data format. By default {@code CHANNELS_FIRST}, uses
   * {@code NHWC (2D) / NDHWC (3D)} or if {@code CHANNELS_LAST}, uses {@code NCHW (2D) / NCDHW (3D)}.
   * @return this Options instance.
   */
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
  }

  /**
   * Sets the dilations option.
   *
   * @param dilations 1-D tensor of length {@code N+2}. The dilation factor for each dimension of
   * {@code input}. If set to {@code k > 1}, there will be {@code k-1} skipped cells between each
   * filter element on that dimension. The dimension order is determined by the
   * value of {@code channels_last_format}, see above for details. Dilations in the batch
   * and depth dimensions must be 1.
   * @return this Options instance.
   */
  public static Options dilations(List<Long> dilations) {
    return new Options().dilations(dilations);
  }

  /**
   * Sets the dilations option.
   *
   * @param dilations 1-D tensor of length {@code N+2}. The dilation factor for each dimension of
   * {@code input}. If set to {@code k > 1}, there will be {@code k-1} skipped cells between each
   * filter element on that dimension. The dimension order is determined by the
   * value of {@code channels_last_format}, see above for details. Dilations in the batch
   * and depth dimensions must be 1.
   * @return this Options instance.
   */
  public static Options dilations(Long... dilations) {
    return new Options().dilations(dilations);
  }

  /**
   * Sets the batchDims option.
   *
   * @param batchDims A positive integer specifying the number of batch dimensions for the input
   * tensor. Should be less than the rank of the input tensor.
   * @return this Options instance.
   */
  public static Options batchDims(Long batchDims) {
    return new Options().batchDims(batchDims);
  }

  /**
   * Sets the groups option.
   *
   * @param groups A positive integer specifying the number of groups in which the input is split
   * along the channel axis. Each group is convolved separately with
   * {@code filters / groups} filters. The output is the concatenation of all the groups
   * results along the channel axis. Input channels and filters must both be
   * divisible by groups.
   * @return this Options instance.
   */
  public static Options groups(Long groups) {
    return new Options().groups(groups);
  }

  /**
   * Gets output.
   * A (N+1+batch_dims)-D tensor. The dimension order is determined by the value of
   * {@code channels_last_format}, see below for details.
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
   * Optional attributes for {@link org.tensorflow.op.nn.Conv}
   */
  public static class Options {
    private List<Long> explicitPaddings;

    private String dataFormat;

    private List<Long> dilations;

    private Long batchDims;

    private Long groups;

    private Options() {
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
     * @param dataFormat Used to set the data format. By default {@code CHANNELS_FIRST}, uses
     * {@code NHWC (2D) / NDHWC (3D)} or if {@code CHANNELS_LAST}, uses {@code NCHW (2D) / NCDHW (3D)}.
     * @return this Options instance.
     */
    public Options dataFormat(String dataFormat) {
      this.dataFormat = dataFormat;
      return this;
    }

    /**
     * Sets the dilations option.
     *
     * @param dilations 1-D tensor of length {@code N+2}. The dilation factor for each dimension of
     * {@code input}. If set to {@code k > 1}, there will be {@code k-1} skipped cells between each
     * filter element on that dimension. The dimension order is determined by the
     * value of {@code channels_last_format}, see above for details. Dilations in the batch
     * and depth dimensions must be 1.
     * @return this Options instance.
     */
    public Options dilations(List<Long> dilations) {
      this.dilations = dilations;
      return this;
    }

    /**
     * Sets the dilations option.
     *
     * @param dilations 1-D tensor of length {@code N+2}. The dilation factor for each dimension of
     * {@code input}. If set to {@code k > 1}, there will be {@code k-1} skipped cells between each
     * filter element on that dimension. The dimension order is determined by the
     * value of {@code channels_last_format}, see above for details. Dilations in the batch
     * and depth dimensions must be 1.
     * @return this Options instance.
     */
    public Options dilations(Long... dilations) {
      this.dilations = Arrays.asList(dilations);
      return this;
    }

    /**
     * Sets the batchDims option.
     *
     * @param batchDims A positive integer specifying the number of batch dimensions for the input
     * tensor. Should be less than the rank of the input tensor.
     * @return this Options instance.
     */
    public Options batchDims(Long batchDims) {
      this.batchDims = batchDims;
      return this;
    }

    /**
     * Sets the groups option.
     *
     * @param groups A positive integer specifying the number of groups in which the input is split
     * along the channel axis. Each group is convolved separately with
     * {@code filters / groups} filters. The output is the concatenation of all the groups
     * results along the channel axis. Input channels and filters must both be
     * divisible by groups.
     * @return this Options instance.
     */
    public Options groups(Long groups) {
      this.groups = groups;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Conv.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<Conv<T>> {
    /**
     * Tensor of type T and shape {@code batch_shape + spatial_shape + [in_channels]} in the
     * case that {@code channels_last_format = true} or shape
     * {@code batch_shape + [in_channels] + spatial_shape} if {@code channels_last_format = false}.
     * spatial_shape is N-dimensional with {@code N=2} or {@code N=3}.
     * Also note that {@code batch_shape} is dictated by the parameter {@code batch_dims}
     * and defaults to 1.
     */
    public final Operand<T> input;

    /**
     * An {@code (N+2)-D} Tensor with the same type as {@code input} and shape
     * {@code spatial_filter_shape + [in_channels, out_channels]}, where spatial_filter_shape
     * is N-dimensional with {@code N=2} or {@code N=3}.
     */
    public final Operand<T> filter;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * 1-D tensor of length {@code N+2}. The stride of the sliding window for each
     * dimension of {@code input}. Must have {@code strides[0] = strides[N+1] = 1}.
     */
    public final long[] strides;

    /**
     * The type of padding algorithm to use.
     */
    public final String padding;

    /**
     * If {@code padding} is {@code "EXPLICIT"}, the list of explicit padding amounts. For the ith
     * dimension, the amount of padding inserted before and after the dimension is
     * {@code explicit_paddings[2 * i]} and {@code explicit_paddings[2 * i + 1]}, respectively. If
     * {@code padding} is not {@code "EXPLICIT"}, {@code explicit_paddings} must be empty.
     */
    public final long[] explicitPaddings;

    /**
     * Used to set the data format. By default {@code CHANNELS_FIRST}, uses
     * {@code NHWC (2D) / NDHWC (3D)} or if {@code CHANNELS_LAST}, uses {@code NCHW (2D) / NCDHW (3D)}.
     */
    public final String dataFormat;

    /**
     * 1-D tensor of length {@code N+2}. The dilation factor for each dimension of
     * {@code input}. If set to {@code k > 1}, there will be {@code k-1} skipped cells between each
     * filter element on that dimension. The dimension order is determined by the
     * value of {@code channels_last_format}, see above for details. Dilations in the batch
     * and depth dimensions must be 1.
     */
    public final long[] dilations;

    /**
     * A positive integer specifying the number of batch dimensions for the input
     * tensor. Should be less than the rank of the input tensor.
     */
    public final long batchDims;

    /**
     * A positive integer specifying the number of groups in which the input is split
     * along the channel axis. Each group is convolved separately with
     * {@code filters / groups} filters. The output is the concatenation of all the groups
     * results along the channel axis. Input channels and filters must both be
     * divisible by groups.
     */
    public final long groups;

    public Inputs(GraphOperation op) {
      super(new Conv<>(op), op, Arrays.asList("T", "strides", "padding", "explicit_paddings", "data_format", "dilations", "batch_dims", "groups"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      filter = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      strides = op.attributes().getAttrIntList("strides");
      padding = op.attributes().getAttrString("padding");
      explicitPaddings = op.attributes().getAttrIntList("explicit_paddings");
      dataFormat = op.attributes().getAttrString("data_format");
      dilations = op.attributes().getAttrIntList("dilations");
      batchDims = op.attributes().getAttrInt("batch_dims");
      groups = op.attributes().getAttrInt("groups");
    }
  }
}
