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
import org.tensorflow.types.family.TType;

/**
 * Adds {@code bias} to {@code value}.
 * This is a special case of {@code tf.add} where {@code bias} is restricted to be 1-D.
 * Broadcasting is supported, so {@code value} may have any number of dimensions.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = BiasAdd.OP_NAME,
    inputsClass = BiasAdd.Inputs.class
)
@Operator(
    group = "nn"
)
public final class BiasAdd<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "BiasAdd";

  private Output<T> output;

  public BiasAdd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new BiasAdd operation.
   *
   * @param scope current scope
   * @param value Any number of dimensions.
   * @param bias 1-D with size the last dimension of {@code value}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code BiasAdd} output and operands
   * @return a new instance of BiasAdd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> BiasAdd<T> create(Scope scope, Operand<T> value, Operand<T> bias,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "BiasAdd");
    opBuilder.addInput(value.asOutput());
    opBuilder.addInput(bias.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.dataFormat != null) {
          opBuilder.setAttr("data_format", opts.dataFormat);
        }
      }
    }
    return new BiasAdd<>(opBuilder.build());
  }

  /**
   * Sets the dataFormat option.
   *
   * @param dataFormat Specify the data format of the input and output data. With the
   * default format &quot;NHWC&quot;, the bias tensor will be added to the last dimension
   * of the value tensor.
   * Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
   * [batch, in_channels, in_height, in_width].
   * The tensor will be added to &quot;in_channels&quot;, the third-to-the-last
   * dimension.
   * @return this Options instance.
   */
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
  }

  /**
   * Gets output.
   * Broadcasted sum of {@code value} and {@code bias}.
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
   * Optional attributes for {@link org.tensorflow.op.nn.BiasAdd}
   */
  public static class Options {
    private String dataFormat;

    private Options() {
    }

    /**
     * Sets the dataFormat option.
     *
     * @param dataFormat Specify the data format of the input and output data. With the
     * default format &quot;NHWC&quot;, the bias tensor will be added to the last dimension
     * of the value tensor.
     * Alternatively, the format could be &quot;NCHW&quot;, the data storage order of:
     * [batch, in_channels, in_height, in_width].
     * The tensor will be added to &quot;in_channels&quot;, the third-to-the-last
     * dimension.
     * @return this Options instance.
     */
    public Options dataFormat(String dataFormat) {
      this.dataFormat = dataFormat;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = BiasAdd.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<BiasAdd<T>> {
    /**
     * Any number of dimensions.
     */
    public final Operand<T> value;

    /**
     * 1-D with size the last dimension of {@code value}.
     */
    public final Operand<T> bias;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Specify the data format of the input and output data. With the
     * default format "NHWC", the bias tensor will be added to the last dimension
     * of the value tensor.
     * Alternatively, the format could be "NCHW", the data storage order of:
     *     [batch, in_channels, in_height, in_width].
     * The tensor will be added to "in_channels", the third-to-the-last
     *     dimension.
     */
    public final String dataFormat;

    public Inputs(GraphOperation op) {
      super(new BiasAdd<>(op), op, Arrays.asList("T", "data_format"));
      int inputIndex = 0;
      value = (Operand<T>) op.input(inputIndex++);
      bias = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      dataFormat = op.attributes().getAttrString("data_format");
    }
  }
}
