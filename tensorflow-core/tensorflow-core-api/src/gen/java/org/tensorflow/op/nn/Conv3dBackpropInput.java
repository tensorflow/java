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
 * Computes the gradients of 3-D convolution with respect to the input.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = Conv3dBackpropInput.OP_NAME,
    inputsClass = Conv3dBackpropInput.Inputs.class
)
@Operator(
    group = "nn"
)
public final class Conv3dBackpropInput<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Conv3DBackpropInputV2";

  private Output<U> output;

  public Conv3dBackpropInput(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Conv3DBackpropInputV2 operation.
   *
   * @param scope current scope
   * @param inputSizes An integer vector representing the tensor shape of {@code input},
   * where {@code input} is a 5-D
   * {@code [batch, depth, rows, cols, in_channels]} tensor.
   * @param filter Shape {@code [depth, rows, cols, in_channels, out_channels]}.
   * {@code in_channels} must match between {@code input} and {@code filter}.
   * @param outBackprop Backprop signal of shape {@code [batch, out_depth, out_rows, out_cols, out_channels]}.
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   * dimension of {@code input}. Must have {@code strides[0] = strides[4] = 1}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <U> data type for {@code Conv3DBackpropInputV2} output and operands
   * @return a new instance of Conv3dBackpropInput
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> Conv3dBackpropInput<U> create(Scope scope,
      Operand<? extends TNumber> inputSizes, Operand<U> filter, Operand<U> outBackprop,
      List<Long> strides, String padding, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Conv3dBackpropInput");
    opBuilder.addInput(inputSizes.asOutput());
    opBuilder.addInput(filter.asOutput());
    opBuilder.addInput(outBackprop.asOutput());
    long[] stridesArray = new long[strides.size()];
    for (int i = 0 ; i < stridesArray.length ; i++) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
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
    return new Conv3dBackpropInput<>(opBuilder.build());
  }

  /**
   * Sets the dataFormat option.
   *
   * @param dataFormat The data format of the input and output data. With the
   * default format &quot;NDHWC&quot;, the data is stored in the order of:
   * [batch, in_depth, in_height, in_width, in_channels].
   * Alternatively, the format could be &quot;NCDHW&quot;, the data storage order is:
   * [batch, in_channels, in_depth, in_height, in_width].
   * @return this Options instance.
   */
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
  }

  /**
   * Sets the dilations option.
   *
   * @param dilations 1-D tensor of length 5.  The dilation factor for each dimension of
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
   * @param dilations 1-D tensor of length 5.  The dilation factor for each dimension of
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
   *
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.nn.Conv3dBackpropInput}
   */
  public static class Options {
    private String dataFormat;

    private List<Long> dilations;

    private Options() {
    }

    /**
     * Sets the dataFormat option.
     *
     * @param dataFormat The data format of the input and output data. With the
     * default format &quot;NDHWC&quot;, the data is stored in the order of:
     * [batch, in_depth, in_height, in_width, in_channels].
     * Alternatively, the format could be &quot;NCDHW&quot;, the data storage order is:
     * [batch, in_channels, in_depth, in_height, in_width].
     * @return this Options instance.
     */
    public Options dataFormat(String dataFormat) {
      this.dataFormat = dataFormat;
      return this;
    }

    /**
     * Sets the dilations option.
     *
     * @param dilations 1-D tensor of length 5.  The dilation factor for each dimension of
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
     * @param dilations 1-D tensor of length 5.  The dilation factor for each dimension of
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
      outputsClass = Conv3dBackpropInput.class
  )
  public static class Inputs<U extends TNumber> extends RawOpInputs<Conv3dBackpropInput<U>> {
    /**
     * An integer vector representing the tensor shape of {@code input},
     * where {@code input} is a 5-D
     * {@code [batch, depth, rows, cols, in_channels]} tensor.
     */
    public final Operand<? extends TNumber> inputSizes;

    /**
     * Shape {@code [depth, rows, cols, in_channels, out_channels]}.
     * {@code in_channels} must match between {@code input} and {@code filter}.
     */
    public final Operand<U> filter;

    /**
     * Backprop signal of shape {@code [batch, out_depth, out_rows, out_cols, out_channels]}.
     */
    public final Operand<U> outBackprop;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * 1-D tensor of length 5. The stride of the sliding window for each
     * dimension of `input`. Must have `strides[0] = strides[4] = 1`.
     */
    public final long[] strides;

    /**
     * The type of padding algorithm to use.
     */
    public final String padding;

    /**
     * The data format of the input and output data. With the
     * default format "NDHWC", the data is stored in the order of:
     *     [batch, in_depth, in_height, in_width, in_channels].
     * Alternatively, the format could be "NCDHW", the data storage order is:
     *     [batch, in_channels, in_depth, in_height, in_width].
     */
    public final String dataFormat;

    /**
     * 1-D tensor of length 5.  The dilation factor for each dimension of
     * `input`. If set to k > 1, there will be k-1 skipped cells between each
     * filter element on that dimension. The dimension order is determined by the
     * value of `data_format`, see above for details. Dilations in the batch and
     * depth dimensions must be 1.
     */
    public final long[] dilations;

    /**
     * The Tshape attribute
     */
    public final DataType Tshape;

    public Inputs(GraphOperation op) {
      super(new Conv3dBackpropInput<>(op), op, Arrays.asList("T", "strides", "padding", "data_format", "dilations", "Tshape"));
      int inputIndex = 0;
      inputSizes = (Operand<? extends TNumber>) op.input(inputIndex++);
      filter = (Operand<U>) op.input(inputIndex++);
      outBackprop = (Operand<U>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      strides = op.attributes().getAttrIntList("strides");
      padding = op.attributes().getAttrString("padding");
      dataFormat = op.attributes().getAttrString("data_format");
      dilations = op.attributes().getAttrIntList("dilations");
      Tshape = op.attributes().getAttrType("Tshape");
    }
  }
}
