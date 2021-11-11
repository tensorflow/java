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
 * Quantizes then dequantizes a tensor.
 * This is almost identical to QuantizeAndDequantizeV2, except that it returns a
 * gradient of 1 for inputs that are within the quantization range, or 0 otherwise.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = QuantizeAndDequantizeV4.OP_NAME,
    inputsClass = QuantizeAndDequantizeV4.Inputs.class
)
@Operator(
    group = "quantization"
)
public final class QuantizeAndDequantizeV4<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "QuantizeAndDequantizeV4";

  private Output<T> output;

  public QuantizeAndDequantizeV4(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new QuantizeAndDequantizeV4 operation.
   *
   * @param scope current scope
   * @param input Tensor to quantize and then dequantize.
   * @param inputMin If {@code range_given == True}, this specifies the minimum input value that needs to
   * be represented, otherwise it is determined from the min value of the {@code input}
   * tensor.
   * @param inputMax If {@code range_given == True}, this specifies the maximum input value that needs to
   * be represented, otherwise it is determined from the max value of the {@code input}
   * tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code QuantizeAndDequantizeV4} output and operands
   * @return a new instance of QuantizeAndDequantizeV4
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> QuantizeAndDequantizeV4<T> create(Scope scope, Operand<T> input,
      Operand<T> inputMin, Operand<T> inputMax, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "QuantizeAndDequantizeV4");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(inputMin.asOutput());
    opBuilder.addInput(inputMax.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.signedInput != null) {
          opBuilder.setAttr("signed_input", opts.signedInput);
        }
        if (opts.numBits != null) {
          opBuilder.setAttr("num_bits", opts.numBits);
        }
        if (opts.rangeGiven != null) {
          opBuilder.setAttr("range_given", opts.rangeGiven);
        }
        if (opts.roundMode != null) {
          opBuilder.setAttr("round_mode", opts.roundMode);
        }
        if (opts.narrowRange != null) {
          opBuilder.setAttr("narrow_range", opts.narrowRange);
        }
        if (opts.axis != null) {
          opBuilder.setAttr("axis", opts.axis);
        }
      }
    }
    return new QuantizeAndDequantizeV4<>(opBuilder.build());
  }

  /**
   * Sets the signedInput option.
   *
   * @param signedInput Whether the quantization is signed or unsigned. (actually this parameter should
   * have been called <b>{@code signed_output}&lt;/b&gt;)
   * @return this Options instance.
   */
  public static Options signedInput(Boolean signedInput) {
    return new Options().signedInput(signedInput);
  }

  /**
   * Sets the numBits option.
   *
   * @param numBits The bitwidth of the quantization.
   * @return this Options instance.
   */
  public static Options numBits(Long numBits) {
    return new Options().numBits(numBits);
  }

  /**
   * Sets the rangeGiven option.
   *
   * @param rangeGiven Whether the range is given or should be determined from the {@code input} tensor.
   * @return this Options instance.
   */
  public static Options rangeGiven(Boolean rangeGiven) {
    return new Options().rangeGiven(rangeGiven);
  }

  /**
   * Sets the roundMode option.
   *
   * @param roundMode The 'round_mode' attribute controls which rounding tie-breaking algorithm is
   * used when rounding float values to their quantized equivalents. The following
   * rounding modes are currently supported:
   * <ul>
   * <li>HALF_TO_EVEN: this is the default round_mode.</li>
   * <li>HALF_UP: round towards positive. In this mode 7.5 rounds up to 8 and -7.5
   * rounds up to -7.</li>
   * </ul>
   * @return this Options instance.
   */
  public static Options roundMode(String roundMode) {
    return new Options().roundMode(roundMode);
  }

  /**
   * Sets the narrowRange option.
   *
   * @param narrowRange If True, then the absolute value of the quantized minimum value is the same as
   * the quantized maximum value, instead of 1 greater.
   * i.e. for 8 bit quantization, the minimum value is -127 instead of -128.
   * @return this Options instance.
   */
  public static Options narrowRange(Boolean narrowRange) {
    return new Options().narrowRange(narrowRange);
  }

  /**
   * Sets the axis option.
   *
   * @param axis If specified, this axis is treated as a channel or slice axis, and a separate
   * quantization range is used for each channel or slice along this axis.
   * @return this Options instance.
   */
  public static Options axis(Long axis) {
    return new Options().axis(axis);
  }

  /**
   * Gets output.
   *
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
   * Optional attributes for {@link org.tensorflow.op.quantization.QuantizeAndDequantizeV4}
   */
  public static class Options {
    private Boolean signedInput;

    private Long numBits;

    private Boolean rangeGiven;

    private String roundMode;

    private Boolean narrowRange;

    private Long axis;

    private Options() {
    }

    /**
     * Sets the signedInput option.
     *
     * @param signedInput Whether the quantization is signed or unsigned. (actually this parameter should
     * have been called <b>{@code signed_output}&lt;/b&gt;)
     * @return this Options instance.
     */
    public Options signedInput(Boolean signedInput) {
      this.signedInput = signedInput;
      return this;
    }

    /**
     * Sets the numBits option.
     *
     * @param numBits The bitwidth of the quantization.
     * @return this Options instance.
     */
    public Options numBits(Long numBits) {
      this.numBits = numBits;
      return this;
    }

    /**
     * Sets the rangeGiven option.
     *
     * @param rangeGiven Whether the range is given or should be determined from the {@code input} tensor.
     * @return this Options instance.
     */
    public Options rangeGiven(Boolean rangeGiven) {
      this.rangeGiven = rangeGiven;
      return this;
    }

    /**
     * Sets the roundMode option.
     *
     * @param roundMode The 'round_mode' attribute controls which rounding tie-breaking algorithm is
     * used when rounding float values to their quantized equivalents. The following
     * rounding modes are currently supported:
     * <ul>
     * <li>HALF_TO_EVEN: this is the default round_mode.</li>
     * <li>HALF_UP: round towards positive. In this mode 7.5 rounds up to 8 and -7.5
     * rounds up to -7.</li>
     * </ul>
     * @return this Options instance.
     */
    public Options roundMode(String roundMode) {
      this.roundMode = roundMode;
      return this;
    }

    /**
     * Sets the narrowRange option.
     *
     * @param narrowRange If True, then the absolute value of the quantized minimum value is the same as
     * the quantized maximum value, instead of 1 greater.
     * i.e. for 8 bit quantization, the minimum value is -127 instead of -128.
     * @return this Options instance.
     */
    public Options narrowRange(Boolean narrowRange) {
      this.narrowRange = narrowRange;
      return this;
    }

    /**
     * Sets the axis option.
     *
     * @param axis If specified, this axis is treated as a channel or slice axis, and a separate
     * quantization range is used for each channel or slice along this axis.
     * @return this Options instance.
     */
    public Options axis(Long axis) {
      this.axis = axis;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = QuantizeAndDequantizeV4.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<QuantizeAndDequantizeV4<T>> {
    /**
     * Tensor to quantize and then dequantize.
     */
    public final Operand<T> input;

    /**
     * If {@code range_given == True}, this specifies the minimum input value that needs to
     * be represented, otherwise it is determined from the min value of the {@code input}
     * tensor.
     */
    public final Operand<T> inputMin;

    /**
     * If {@code range_given == True}, this specifies the maximum input value that needs to
     * be represented, otherwise it is determined from the max value of the {@code input}
     * tensor.
     */
    public final Operand<T> inputMax;

    /**
     * Whether the quantization is signed or unsigned. (actually this parameter should
     * have been called <b>`signed_output`</b>)
     */
    public final boolean signedInput;

    /**
     * The bitwidth of the quantization.
     */
    public final long numBits;

    /**
     * Whether the range is given or should be determined from the `input` tensor.
     */
    public final boolean rangeGiven;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The 'round_mode' attribute controls which rounding tie-breaking algorithm is
     * used when rounding float values to their quantized equivalents. The following
     * rounding modes are currently supported:
     *
     * *   HALF_TO_EVEN: this is the default round_mode.
     * *   HALF_UP: round towards positive. In this mode 7.5 rounds up to 8 and -7.5
     *     rounds up to -7.
     */
    public final String roundMode;

    /**
     * If True, then the absolute value of the quantized minimum value is the same as
     * the quantized maximum value, instead of 1 greater.
     * i.e. for 8 bit quantization, the minimum value is -127 instead of -128.
     */
    public final boolean narrowRange;

    /**
     * If specified, this axis is treated as a channel or slice axis, and a separate
     * quantization range is used for each channel or slice along this axis.
     */
    public final long axis;

    public Inputs(GraphOperation op) {
      super(new QuantizeAndDequantizeV4<>(op), op, Arrays.asList("signed_input", "num_bits", "range_given", "T", "round_mode", "narrow_range", "axis"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      inputMin = (Operand<T>) op.input(inputIndex++);
      inputMax = (Operand<T>) op.input(inputIndex++);
      signedInput = op.attributes().getAttrBool("signed_input");
      numBits = op.attributes().getAttrInt("num_bits");
      rangeGiven = op.attributes().getAttrBool("range_given");
      T = op.attributes().getAttrType("T");
      roundMode = op.attributes().getAttrString("round_mode");
      narrowRange = op.attributes().getAttrBool("narrow_range");
      axis = op.attributes().getAttrInt("axis");
    }
  }
}
