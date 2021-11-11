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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

/**
 * Computes gradients of the average pooling function.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = AvgPoolGrad.OP_NAME,
    inputsClass = AvgPoolGrad.Inputs.class
)
public final class AvgPoolGrad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "AvgPoolGrad";

  private Output<T> output;

  public AvgPoolGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new AvgPoolGrad operation.
   *
   * @param scope current scope
   * @param origInputShape 1-D.  Shape of the original input to {@code avg_pool}.
   * @param grad 4-D with shape {@code [batch, height, width, channels]}.  Gradients w.r.t.
   * the output of {@code avg_pool}.
   * @param ksize The size of the sliding window for each dimension of the input.
   * @param strides The stride of the sliding window for each dimension of the input.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code AvgPoolGrad} output and operands
   * @return a new instance of AvgPoolGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> AvgPoolGrad<T> create(Scope scope,
      Operand<TInt32> origInputShape, Operand<T> grad, List<Long> ksize, List<Long> strides,
      String padding, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "AvgPoolGrad");
    opBuilder.addInput(origInputShape.asOutput());
    opBuilder.addInput(grad.asOutput());
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
    return new AvgPoolGrad<>(opBuilder.build());
  }

  /**
   * Sets the dataFormat option.
   *
   * @param dataFormat Specify the data format of the input and output data. With the
   * default format &quot;NHWC&quot;, the data is stored in the order of:
   * [batch, in_height, in_width, in_channels].
   * Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
   * [batch, in_channels, in_height, in_width].
   * @return this Options instance.
   */
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
  }

  /**
   * Gets output.
   * 4-D.  Gradients w.r.t. the input of {@code avg_pool}.
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
   * Optional attributes for {@link org.tensorflow.op.nn.AvgPoolGrad}
   */
  public static class Options {
    private String dataFormat;

    private Options() {
    }

    /**
     * Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     * default format &quot;NHWC&quot;, the data is stored in the order of:
     * [batch, in_height, in_width, in_channels].
     * Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     * [batch, in_channels, in_height, in_width].
     * @return this Options instance.
     */
    public Options dataFormat(String dataFormat) {
      this.dataFormat = dataFormat;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = AvgPoolGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<AvgPoolGrad<T>> {
    /**
     * 1-D.  Shape of the original input to {@code avg_pool}.
     */
    public final Operand<TInt32> origInputShape;

    /**
     * 4-D with shape {@code [batch, height, width, channels]}.  Gradients w.r.t.
     * the output of {@code avg_pool}.
     */
    public final Operand<T> grad;

    /**
     * The size of the sliding window for each dimension of the input.
     */
    public final long[] ksize;

    /**
     * The stride of the sliding window for each dimension of the input.
     */
    public final long[] strides;

    /**
     * The type of padding algorithm to use.
     */
    public final String padding;

    /**
     * Specify the data format of the input and output data. With the
     * default format "NHWC", the data is stored in the order of:
     *     [batch, in_height, in_width, in_channels].
     * Alternatively, the format could be "NCHW", the data storage order of:
     *     [batch, in_channels, in_height, in_width].
     */
    public final String dataFormat;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new AvgPoolGrad<>(op), op, Arrays.asList("ksize", "strides", "padding", "data_format", "T"));
      int inputIndex = 0;
      origInputShape = (Operand<TInt32>) op.input(inputIndex++);
      grad = (Operand<T>) op.input(inputIndex++);
      ksize = op.attributes().getAttrIntList("ksize");
      strides = op.attributes().getAttrIntList("strides");
      padding = op.attributes().getAttrString("padding");
      dataFormat = op.attributes().getAttrString("data_format");
      T = op.attributes().getAttrType("T");
    }
  }
}
