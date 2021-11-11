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

package org.tensorflow.op.quantization;

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
import org.tensorflow.types.family.TNumber;

/**
 * Returns the gradient of {@code QuantizeAndDequantizeV4}.
 * Returns a gradient of 1 for inputs that are within the quantization range,
 * or 0 otherwise.
 *
 * @param <T> data type for {@code input_backprop} output
 */
@OpMetadata(
    opType = QuantizeAndDequantizeV4Grad.OP_NAME,
    inputsClass = QuantizeAndDequantizeV4Grad.Inputs.class
)
@Operator(
    group = "quantization"
)
public final class QuantizeAndDequantizeV4Grad<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizeAndDequantizeV4Grad";

  private Output<T> inputBackprop;

  private Output<T> inputMinBackprop;

  private Output<T> inputMaxBackprop;

  public QuantizeAndDequantizeV4Grad(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    inputBackprop = operation.output(outputIdx++);
    inputMinBackprop = operation.output(outputIdx++);
    inputMaxBackprop = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizeAndDequantizeV4Grad operation.
   *
   * @param scope current scope
   * @param gradients The gradients value
   * @param input The input value
   * @param inputMin The inputMin value
   * @param inputMax The inputMax value
   * @param options carries optional attribute values
   * @param <T> data type for {@code QuantizeAndDequantizeV4Grad} output and operands
   * @return a new instance of QuantizeAndDequantizeV4Grad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> QuantizeAndDequantizeV4Grad<T> create(Scope scope,
      Operand<T> gradients, Operand<T> input, Operand<T> inputMin, Operand<T> inputMax,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizeAndDequantizeV4Grad");
    opBuilder.addInput(gradients.asOutput());
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputMin.asOutput());
    opBuilder.addInput(inputMax.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.axis != null) {
          opBuilder.setAttr("axis", opts.axis);
        }
      }
    }
    return new QuantizeAndDequantizeV4Grad<>(opBuilder.build());
  }

  /**
   * Sets the axis option.
   *
   * @param axis the axis option
   * @return this Options instance.
   */
  public static Options axis(Long axis) {
    return new Options().axis(axis);
  }

  /**
   * Gets inputBackprop.
   *
   * @return inputBackprop.
   */
  public Output<T> inputBackprop() {
    return inputBackprop;
  }

  /**
   * Gets inputMinBackprop.
   *
   * @return inputMinBackprop.
   */
  public Output<T> inputMinBackprop() {
    return inputMinBackprop;
  }

  /**
   * Gets inputMaxBackprop.
   *
   * @return inputMaxBackprop.
   */
  public Output<T> inputMaxBackprop() {
    return inputMaxBackprop;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.quantization.QuantizeAndDequantizeV4Grad}
   */
  public static class Options {
    private Long axis;

    private Options() {
    }

    /**
     * Sets the axis option.
     *
     * @param axis the axis option
     * @return this Options instance.
     */
    public Options axis(Long axis) {
      this.axis = axis;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = QuantizeAndDequantizeV4Grad.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<QuantizeAndDequantizeV4Grad<T>> {
    /**
     * The gradients input
     */
    public final Operand<T> gradients;

    /**
     * The input input
     */
    public final Operand<T> input;

    /**
     * The inputMin input
     */
    public final Operand<T> inputMin;

    /**
     * The inputMax input
     */
    public final Operand<T> inputMax;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The axis attribute
     */
    public final long axis;

    public Inputs(GraphOperation op) {
      super(new QuantizeAndDequantizeV4Grad<>(op), op, Arrays.asList("T", "axis"));
      int inputIndex = 0;
      gradients = (Operand<T>) op.input(inputIndex++);
      input = (Operand<T>) op.input(inputIndex++);
      inputMin = (Operand<T>) op.input(inputIndex++);
      inputMax = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      axis = op.attributes().getAttrInt("axis");
    }
  }
}
