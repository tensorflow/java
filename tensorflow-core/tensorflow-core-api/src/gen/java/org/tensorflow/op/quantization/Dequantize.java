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

package org.tensorflow.op.quantization;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TNumber;

/**
 * Dequantize the 'input' tensor into a float or bfloat16 Tensor.
 * [min_range, max_range] are scalar floats that specify the range for
 * the output. The 'mode' attribute controls exactly which calculations are
 * used to convert the float values to their quantized equivalents.
 * <p>In 'MIN_COMBINED' mode, each value of the tensor will undergo the following:
 * <pre>
 * if T == qint8: in[i] += (range(T) + 1)/ 2.0
 * out[i] = min_range + (in[i]* (max_range - min_range) / range(T))
 * </pre>
 * <p>here {@code range(T) = numeric_limits<T>::max() - numeric_limits<T>::min()}
 * <p><em>MIN_COMBINED Mode Example</em>
 * <p>If the input comes from a QuantizedRelu6, the output type is
 * quint8 (range of 0-255) but the possible range of QuantizedRelu6 is
 * 0-6.  The min_range and max_range values are therefore 0.0 and 6.0.
 * Dequantize on quint8 will take each value, cast to float, and multiply
 * by 6 / 255.
 * Note that if quantizedtype is qint8, the operation will additionally add
 * each value by 128 prior to casting.
 * <p>If the mode is 'MIN_FIRST', then this approach is used:
 * <pre>
 * num_discrete_values = 1 &lt;&lt; (# of bits in T)
 * range_adjust = num_discrete_values / (num_discrete_values - 1)
 * range = (range_max - range_min) * range_adjust
 * range_scale = range / num_discrete_values
 * const double offset_input = static_cast&lt;double&gt;(input) - lowest_quantized;
 * result = range_min + ((input - numeric_limits&lt;T&gt;::min()) * range_scale)
 * </pre>
 * <p>If the mode is {@code SCALED}, dequantization is performed by multiplying each
 * input value by a scaling_factor. (Thus an input of 0 always maps to 0.0).
 * <p>The scaling_factor is determined from {@code min_range}, {@code max_range}, and
 * {@code narrow_range} in a way that is compatible with {@code QuantizeAndDequantize{V2|V3}}
 * and {@code QuantizeV2}, using the following algorithm:
 * <pre>
 *
 *   const int min_expected_T = std::numeric_limits&lt;T&gt;::min() +
 *     (narrow_range ? 1 : 0);
 *   const int max_expected_T = std::numeric_limits&lt;T&gt;::max();
 *   const float max_expected_T = std::numeric_limits&lt;float&gt;::max();
 *
 *   const float scale_factor =
 *     (std::numeric_limits&lt;T&gt;::min() == 0) ? (max_range / max_expected_T)
 *                                          : std::max(min_range / min_expected_T,
 *                                                     max_range / max_expected_T);
 * </pre>
 */
@OpMetadata(
    opType = Dequantize.OP_NAME,
    inputsClass = Dequantize.Inputs.class
)
@Operator(
    group = "quantization"
)
public final class Dequantize<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Dequantize";

  private Output<U> output;

  public Dequantize(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Dequantize operation.
   *
   * @param scope current scope
   * @param input The input value
   * @param minRange The minimum scalar value possibly produced for the input.
   * @param maxRange The maximum scalar value possibly produced for the input.
   * @param dtype Type of the output tensor. Currently Dequantize supports float and bfloat16.
   * If 'dtype' is 'bfloat16', it only supports 'MIN_COMBINED' mode.
   * @param options carries optional attribute values
   * @param <U> data type for {@code Dequantize} output and operands
   * @return a new instance of Dequantize
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> Dequantize<U> create(Scope scope,
      Operand<? extends TNumber> input, Operand<TFloat32> minRange, Operand<TFloat32> maxRange,
      Class<U> dtype, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Dequantize");
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(minRange.asOutput());
    opBuilder.addInput(maxRange.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    if (options != null) {
      for (Options opts : options) {
        if (opts.mode != null) {
          opBuilder.setAttr("mode", opts.mode);
        }
        if (opts.narrowRange != null) {
          opBuilder.setAttr("narrow_range", opts.narrowRange);
        }
        if (opts.axis != null) {
          opBuilder.setAttr("axis", opts.axis);
        }
      }
    }
    return new Dequantize<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new Dequantize operation, with the default output types.
   *
   * @param scope current scope
   * @param input The input value
   * @param minRange The minimum scalar value possibly produced for the input.
   * @param maxRange The maximum scalar value possibly produced for the input.
   * @param options carries optional attribute values
   * @return a new instance of Dequantize, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static Dequantize<TFloat32> create(Scope scope, Operand<? extends TNumber> input,
      Operand<TFloat32> minRange, Operand<TFloat32> maxRange, Options... options) {
    return create(scope, input, minRange, maxRange, TFloat32.class, options);
  }

  /**
   * Sets the mode option.
   *
   * @param mode the mode option
   * @return this Options instance.
   */
  public static Options mode(String mode) {
    return new Options().mode(mode);
  }

  /**
   * Sets the narrowRange option.
   *
   * @param narrowRange the narrowRange option
   * @return this Options instance.
   */
  public static Options narrowRange(Boolean narrowRange) {
    return new Options().narrowRange(narrowRange);
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
   * Optional attributes for {@link org.tensorflow.op.quantization.Dequantize}
   */
  public static class Options {
    private String mode;

    private Boolean narrowRange;

    private Long axis;

    private Options() {
    }

    /**
     * Sets the mode option.
     *
     * @param mode the mode option
     * @return this Options instance.
     */
    public Options mode(String mode) {
      this.mode = mode;
      return this;
    }

    /**
     * Sets the narrowRange option.
     *
     * @param narrowRange the narrowRange option
     * @return this Options instance.
     */
    public Options narrowRange(Boolean narrowRange) {
      this.narrowRange = narrowRange;
      return this;
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
      outputsClass = Dequantize.class
  )
  public static class Inputs extends RawOpInputs<Dequantize<?>> {
    /**
     * The input input
     */
    public final Operand<? extends TNumber> input;

    /**
     * The minimum scalar value possibly produced for the input.
     */
    public final Operand<TFloat32> minRange;

    /**
     * The maximum scalar value possibly produced for the input.
     */
    public final Operand<TFloat32> maxRange;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The mode attribute
     */
    public final String mode;

    /**
     * The narrowRange attribute
     */
    public final boolean narrowRange;

    /**
     * The axis attribute
     */
    public final long axis;

    /**
     * Type of the output tensor. Currently Dequantize supports float and bfloat16.
     * If 'dtype' is 'bfloat16', it only supports 'MIN_COMBINED' mode.
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new Dequantize<>(op), op, Arrays.asList("T", "mode", "narrow_range", "axis", "dtype"));
      int inputIndex = 0;
      input = (Operand<? extends TNumber>) op.input(inputIndex++);
      minRange = (Operand<TFloat32>) op.input(inputIndex++);
      maxRange = (Operand<TFloat32>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      mode = op.attributes().getAttrString("mode");
      narrowRange = op.attributes().getAttrBool("narrow_range");
      axis = op.attributes().getAttrInt("axis");
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
