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
 * Computes second-order gradients of the maxpooling function.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = MaxPoolGradGrad.OP_NAME,
    inputsClass = MaxPoolGradGrad.Inputs.class
)
@Operator(
    group = "nn"
)
public final class MaxPoolGradGrad<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MaxPoolGradGradV2";

  private Output<T> output;

  public MaxPoolGradGrad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MaxPoolGradGradV2 operation.
   *
   * @param scope current scope
   * @param origInput The original input tensor.
   * @param origOutput The original output tensor.
   * @param grad 4-D.  Gradients of gradients w.r.t. the input of {@code max_pool}.
   * @param ksize The size of the window for each dimension of the input tensor.
   * @param strides The stride of the sliding window for each dimension of the
   * input tensor.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MaxPoolGradGradV2} output and operands
   * @return a new instance of MaxPoolGradGrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> MaxPoolGradGrad<T> create(Scope scope, Operand<T> origInput,
      Operand<T> origOutput, Operand<T> grad, Operand<TInt32> ksize, Operand<TInt32> strides,
      String padding, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MaxPoolGradGrad");
    opBuilder.addInput(origInput.asOutput());
    opBuilder.addInput(origOutput.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(ksize.asOutput());
    opBuilder.addInput(strides.asOutput());
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.dataFormat != null) {
          opBuilder.setAttr("data_format", opts.dataFormat);
        }
      }
    }
    return new MaxPoolGradGrad<>(opBuilder.build());
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
   * Gradients of gradients w.r.t. the input to {@code max_pool}.
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
   * Optional attributes for {@link org.tensorflow.op.nn.MaxPoolGradGrad}
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
      outputsClass = MaxPoolGradGrad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<MaxPoolGradGrad<T>> {
    /**
     * The original input tensor.
     */
    public final Operand<T> origInput;

    /**
     * The original output tensor.
     */
    public final Operand<T> origOutput;

    /**
     * 4-D.  Gradients of gradients w.r.t. the input of {@code max_pool}.
     */
    public final Operand<T> grad;

    /**
     * The size of the window for each dimension of the input tensor.
     */
    public final Operand<TInt32> ksize;

    /**
     * The stride of the sliding window for each dimension of the
     * input tensor.
     */
    public final Operand<TInt32> strides;

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
      super(new MaxPoolGradGrad<>(op), op, Arrays.asList("padding", "data_format", "T"));
      int inputIndex = 0;
      origInput = (Operand<T>) op.input(inputIndex++);
      origOutput = (Operand<T>) op.input(inputIndex++);
      grad = (Operand<T>) op.input(inputIndex++);
      ksize = (Operand<TInt32>) op.input(inputIndex++);
      strides = (Operand<TInt32>) op.input(inputIndex++);
      padding = op.attributes().getAttrString("padding");
      dataFormat = op.attributes().getAttrString("data_format");
      T = op.attributes().getAttrType("T");
    }
  }
}
