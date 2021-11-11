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
 * Performs 3D max pooling on the input.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = MaxPool3d.OP_NAME,
    inputsClass = MaxPool3d.Inputs.class
)
@Operator(
    group = "nn"
)
public final class MaxPool3d<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MaxPool3D";

  private Output<T> output;

  public MaxPool3d(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MaxPool3D operation.
   *
   * @param scope current scope
   * @param input Shape {@code [batch, depth, rows, cols, channels]} tensor to pool over.
   * @param ksize 1-D tensor of length 5. The size of the window for each dimension of
   * the input tensor. Must have {@code ksize[0] = ksize[4] = 1}.
   * @param strides 1-D tensor of length 5. The stride of the sliding window for each
   * dimension of {@code input}. Must have {@code strides[0] = strides[4] = 1}.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPool3D} output and operands
   * @return a new instance of MaxPool3d
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> MaxPool3d<T> create(Scope scope, Operand<T> input,
      List<Long> ksize, List<Long> strides, String padding, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MaxPool3d");
    opBuilder.addInput(input.asOutput());
    long[] ksizeArray = new long[ksize.size()];
    for (int i = 0 ; i < ksizeArray.length ; i++) {
      ksizeArray[i] = ksize.get(i);
    }
    opBuilder.setAttr("ksize", ksizeArray);
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
      }
    }
    return new MaxPool3d<>(opBuilder.build());
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
   * Gets output.
   * The max pooled output tensor.
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
   * Optional attributes for {@link org.tensorflow.op.nn.MaxPool3d}
   */
  public static class Options {
    private String dataFormat;

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
  }

  @OpInputsMetadata(
      outputsClass = MaxPool3d.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<MaxPool3d<T>> {
    /**
     * Shape {@code [batch, depth, rows, cols, channels]} tensor to pool over.
     */
    public final Operand<T> input;

    /**
     * 1-D tensor of length 5. The size of the window for each dimension of
     * the input tensor. Must have `ksize[0] = ksize[4] = 1`.
     */
    public final long[] ksize;

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
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new MaxPool3d<>(op), op, Arrays.asList("ksize", "strides", "padding", "data_format", "T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      ksize = op.attributes().getAttrIntList("ksize");
      strides = op.attributes().getAttrIntList("strides");
      padding = op.attributes().getAttrString("padding");
      dataFormat = op.attributes().getAttrString("data_format");
      T = op.attributes().getAttrType("T");
    }
  }
}
