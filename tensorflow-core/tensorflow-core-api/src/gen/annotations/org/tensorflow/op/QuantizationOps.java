// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op;

import org.tensorflow.Operand;
import org.tensorflow.op.quantization.Dequantize;
import org.tensorflow.op.quantization.FakeQuantWithMinMaxArgs;
import org.tensorflow.op.quantization.FakeQuantWithMinMaxArgsGradient;
import org.tensorflow.op.quantization.FakeQuantWithMinMaxVars;
import org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsGradient;
import org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannel;
import org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannelGradient;
import org.tensorflow.op.quantization.Quantize;
import org.tensorflow.op.quantization.QuantizeAndDequantize;
import org.tensorflow.op.quantization.QuantizeAndDequantizeV3;
import org.tensorflow.op.quantization.QuantizeAndDequantizeV4;
import org.tensorflow.op.quantization.QuantizeAndDequantizeV4Grad;
import org.tensorflow.op.quantization.QuantizeDownAndShrinkRange;
import org.tensorflow.op.quantization.QuantizedConcat;
import org.tensorflow.op.quantization.QuantizedMatMulWithBiasAndDequantize;
import org.tensorflow.op.quantization.QuantizedMatMulWithBiasAndRequantize;
import org.tensorflow.op.quantization.RequantizationRange;
import org.tensorflow.op.quantization.Requantize;
import org.tensorflow.op.quantization.UniformDequantize;
import org.tensorflow.op.quantization.UniformQuantize;
import org.tensorflow.op.quantization.UniformQuantizedDot;
import org.tensorflow.op.quantization.UniformQuantizedDotHybrid;
import org.tensorflow.op.quantization.UniformRequantize;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code quantization} operations as {@link Op Op}s
 *
 * @see Ops
 */
public final class QuantizationOps {
  private final Scope scope;

  private final Ops ops;

  QuantizationOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Dequantize the 'input' tensor into a float or bfloat16 Tensor.
   *  [min_range, max_range] are scalar floats that specify the range for
   *  the output. The 'mode' attribute controls exactly which calculations are
   *  used to convert the float values to their quantized equivalents.
   *  <p>In 'MIN_COMBINED' mode, each value of the tensor will undergo the following:
   *  <pre>
   *  if T == qint8: in[i] += (range(T) + 1)/ 2.0
   *  out[i] = min_range + (in[i]* (max_range - min_range) / range(T))
   *  </pre>
   *  <p>here {@code range(T) = numeric_limits<T>::max() - numeric_limits<T>::min()}
   *  <p><em>MIN_COMBINED Mode Example</em>
   *  <p>If the input comes from a QuantizedRelu6, the output type is
   *  quint8 (range of 0-255) but the possible range of QuantizedRelu6 is
   *  0-6.  The min_range and max_range values are therefore 0.0 and 6.0.
   *  Dequantize on quint8 will take each value, cast to float, and multiply
   *  by 6 / 255.
   *  Note that if quantizedtype is qint8, the operation will additionally add
   *  each value by 128 prior to casting.
   *  <p>If the mode is 'MIN_FIRST', then this approach is used:
   *  <pre>
   *  num_discrete_values = 1 &lt;&lt; (# of bits in T)
   *  range_adjust = num_discrete_values / (num_discrete_values - 1)
   *  range = (range_max - range_min) * range_adjust
   *  range_scale = range / num_discrete_values
   *  const double offset_input = static_cast&lt;double&gt;(input) - lowest_quantized;
   *  result = range_min + ((input - numeric_limits&lt;T&gt;::min()) * range_scale)
   *  </pre>
   *  <p>If the mode is {@code SCALED}, dequantization is performed by multiplying each
   *  input value by a scaling_factor. (Thus an input of 0 always maps to 0.0).
   *  <p>The scaling_factor is determined from {@code min_range}, {@code max_range}, and
   *  {@code narrow_range} in a way that is compatible with {@code QuantizeAndDequantize{V2|V3}}
   *  and {@code QuantizeV2}, using the following algorithm:
   *  <pre>
   *
   *    const int min_expected_T = std::numeric_limits&lt;T&gt;::min() +
   *      (narrow_range ? 1 : 0);
   *    const int max_expected_T = std::numeric_limits&lt;T&gt;::max();
   *    const float max_expected_T = std::numeric_limits&lt;float&gt;::max();
   *
   *    const float scale_factor =
   *      (std::numeric_limits&lt;T&gt;::min() == 0) ? (max_range / max_expected_T)
   *                                           : std::max(min_range / min_expected_T,
   *                                                      max_range / max_expected_T);
   *  </pre>
   *
   * @param input The input value
   * @param minRange The minimum scalar value possibly produced for the input.
   * @param maxRange The maximum scalar value possibly produced for the input.
   * @param options carries optional attribute values
   * @return a new instance of Dequantize, with default output types
   */
  public Dequantize<TFloat32> dequantize(Operand<? extends TNumber> input,
      Operand<TFloat32> minRange, Operand<TFloat32> maxRange, Dequantize.Options... options) {
    return Dequantize.create(scope, input, minRange, maxRange, options);
  }

  /**
   * Dequantize the 'input' tensor into a float or bfloat16 Tensor.
   *  [min_range, max_range] are scalar floats that specify the range for
   *  the output. The 'mode' attribute controls exactly which calculations are
   *  used to convert the float values to their quantized equivalents.
   *  <p>In 'MIN_COMBINED' mode, each value of the tensor will undergo the following:
   *  <pre>
   *  if T == qint8: in[i] += (range(T) + 1)/ 2.0
   *  out[i] = min_range + (in[i]* (max_range - min_range) / range(T))
   *  </pre>
   *  <p>here {@code range(T) = numeric_limits<T>::max() - numeric_limits<T>::min()}
   *  <p><em>MIN_COMBINED Mode Example</em>
   *  <p>If the input comes from a QuantizedRelu6, the output type is
   *  quint8 (range of 0-255) but the possible range of QuantizedRelu6 is
   *  0-6.  The min_range and max_range values are therefore 0.0 and 6.0.
   *  Dequantize on quint8 will take each value, cast to float, and multiply
   *  by 6 / 255.
   *  Note that if quantizedtype is qint8, the operation will additionally add
   *  each value by 128 prior to casting.
   *  <p>If the mode is 'MIN_FIRST', then this approach is used:
   *  <pre>
   *  num_discrete_values = 1 &lt;&lt; (# of bits in T)
   *  range_adjust = num_discrete_values / (num_discrete_values - 1)
   *  range = (range_max - range_min) * range_adjust
   *  range_scale = range / num_discrete_values
   *  const double offset_input = static_cast&lt;double&gt;(input) - lowest_quantized;
   *  result = range_min + ((input - numeric_limits&lt;T&gt;::min()) * range_scale)
   *  </pre>
   *  <p>If the mode is {@code SCALED}, dequantization is performed by multiplying each
   *  input value by a scaling_factor. (Thus an input of 0 always maps to 0.0).
   *  <p>The scaling_factor is determined from {@code min_range}, {@code max_range}, and
   *  {@code narrow_range} in a way that is compatible with {@code QuantizeAndDequantize{V2|V3}}
   *  and {@code QuantizeV2}, using the following algorithm:
   *  <pre>
   *
   *    const int min_expected_T = std::numeric_limits&lt;T&gt;::min() +
   *      (narrow_range ? 1 : 0);
   *    const int max_expected_T = std::numeric_limits&lt;T&gt;::max();
   *    const float max_expected_T = std::numeric_limits&lt;float&gt;::max();
   *
   *    const float scale_factor =
   *      (std::numeric_limits&lt;T&gt;::min() == 0) ? (max_range / max_expected_T)
   *                                           : std::max(min_range / min_expected_T,
   *                                                      max_range / max_expected_T);
   *  </pre>
   *
   * @param input The input value
   * @param minRange The minimum scalar value possibly produced for the input.
   * @param maxRange The maximum scalar value possibly produced for the input.
   * @param dtype Type of the output tensor. Currently Dequantize supports float and bfloat16.
   *  If 'dtype' is 'bfloat16', it only supports 'MIN_COMBINED' mode.
   * @param options carries optional attribute values
   * @param <U> data type for {@code Dequantize} output and operands
   * @return a new instance of Dequantize
   */
  public <U extends TNumber> Dequantize<U> dequantize(Operand<? extends TNumber> input,
      Operand<TFloat32> minRange, Operand<TFloat32> maxRange, Class<U> dtype,
      Dequantize.Options... options) {
    return Dequantize.create(scope, input, minRange, maxRange, dtype, options);
  }

  /**
   * Fake-quantize the 'inputs' tensor, type float to 'outputs' tensor of same shape and type.
   *  Quantization is called fake since the output is still in floating point.
   *  The API converts inputs into values within the range [min and max] and returns
   *  as output.
   *  <p>Attributes
   *  <ul>
   *  <li>{@code [min; max]} define the clamping range for the {@code inputs} data.</li>
   *  <li>{@code inputs} values are quantized into the quantization range (
   *  {@code [0; 2^num_bits - 1]} when {@code narrow_range} is false and {@code [1; 2^num_bits - 1]}
   *  when it is true) and then de-quantized and output as floats in {@code [min; max]}
   *  interval.</li>
   *  <li>{@code num_bits} is the bitwidth of the quantization; between 2 and 16, inclusive.</li>
   *  </ul>
   *  <p>Before quantization, {@code min} and {@code max} values are adjusted with the following
   *  logic.
   *  It is suggested to have {@code min <= 0 <= max}. If {@code 0} is not in the range of values,
   *  the behavior can be unexpected:
   *  <ul>
   *  <li>If {@code 0 < min < max}: {@code min_adj = 0} and {@code max_adj = max - min}.</li>
   *  <li>If {@code min < max < 0}: {@code min_adj = min - max} and {@code max_adj = 0}.</li>
   *  <li>If {@code min <= 0 <= max}: {@code scale = (max - min) / (2^num_bits - 1) },
   *  {@code min_adj = scale * round(min / scale)} and {@code max_adj = max + min_adj - min}.</li>
   *  </ul>
   *  <p>Examples
   *  <pre>
   *
   *  inp = tf.constant ([10.03, -10.23, 3])
   *  out = tf.quantization.fake_quant_with_min_max_args(inp, min=-5, max=5,
   *                                                     num_bits=16)
   *  print(out)
   *
   *  #  Output:
   *  #  tf.Tensor([ 4.9999237 -5.0000763  3.0000763], shape=(3,), dtype=float32)
   *  </pre>
   *  <p>Raises:
   *  <ul>
   *  <li>InvalidArgumentError:
   *  <ul>
   *  <li>If num_bits are outside of range [2, 16].</li>
   *  <li>If min &gt;= max.</li>
   *  </ul>
   *  </li>
   *  <li>ValueError: If {@code inputs} are of any other type than float32.</li>
   *  </ul>
   *
   * @param inputs The inputs value
   * @param options carries optional attribute values
   * @return a new instance of FakeQuantWithMinMaxArgs
   */
  public FakeQuantWithMinMaxArgs fakeQuantWithMinMaxArgs(Operand<TFloat32> inputs,
      FakeQuantWithMinMaxArgs.Options... options) {
    return FakeQuantWithMinMaxArgs.create(scope, inputs, options);
  }

  /**
   * Compute gradients for a FakeQuantWithMinMaxArgs operation.
   *
   * @param gradients Backpropagated gradients above the FakeQuantWithMinMaxArgs operation.
   * @param inputs Values passed as inputs to the FakeQuantWithMinMaxArgs operation.
   * @param options carries optional attribute values
   * @return a new instance of FakeQuantWithMinMaxArgsGradient
   */
  public FakeQuantWithMinMaxArgsGradient fakeQuantWithMinMaxArgsGradient(
      Operand<TFloat32> gradients, Operand<TFloat32> inputs,
      FakeQuantWithMinMaxArgsGradient.Options... options) {
    return FakeQuantWithMinMaxArgsGradient.create(scope, gradients, inputs, options);
  }

  /**
   * Fake-quantize the 'inputs' tensor of type float via global float scalars
   *  Fake-quantize the {@code inputs} tensor of type float via global float scalars
   *  {@code min} and {@code max} to {@code outputs} tensor of same shape as {@code inputs}.
   *  <p>Attributes
   *  <ul>
   *  <li>{@code [min; max]} define the clamping range for the {@code inputs} data.</li>
   *  <li>{@code inputs} values are quantized into the quantization range (
   *  {@code [0; 2^num_bits - 1]} when {@code narrow_range} is false and {@code [1; 2^num_bits - 1]}
   *  when it is true) and then de-quantized and output as floats in {@code [min; max]}
   *  interval.</li>
   *  <li>{@code num_bits} is the bitwidth of the quantization; between 2 and 16, inclusive.</li>
   *  </ul>
   *  <p>Before quantization, {@code min} and {@code max} values are adjusted with the following
   *  logic.
   *  It is suggested to have {@code min <= 0 <= max}. If {@code 0} is not in the range of values,
   *  the behavior can be unexpected:
   *  <ul>
   *  <li>If {@code 0 < min < max}: {@code min_adj = 0} and {@code max_adj = max - min}.</li>
   *  <li>If {@code min < max < 0}: {@code min_adj = min - max} and {@code max_adj = 0}.</li>
   *  <li>If {@code min <= 0 <= max}: {@code scale = (max - min) / (2^num_bits - 1) },
   *  {@code min_adj = scale * round(min / scale)} and {@code max_adj = max + min_adj - min}.</li>
   *  </ul>
   *  <p>This operation has a gradient and thus allows for training {@code min} and {@code max}
   *  values.
   *  <blockquote>
   *  <blockquote>
   *  <blockquote>
   *  <p>constant_input = tf.constant([[1.2, -0.3, 0.7], [2.1, 0.5, -1.0]], dtype=tf.float32)
   *  <p>min_val = -0.5
   *  max_val = 0.8
   *  num_bits = 8
   *  narrow_range = False #False:for the quantization range [0; 2^num_bits - 1]
   *  <p>quantized_data = tf.quantization.fake_quant_with_min_max_vars(
   *  ...   inputs=constant_input, min=min_val, max=max_val, num_bits=num_bits, narrow_range=narrow_range
   *  ... )
   *  <p>print(&quot;Input:\n&quot;, constant_input.numpy())
   *  Input:
   *  [[ 1.2 -0.3  0.7]
   *  [ 2.1  0.5 -1. ]]
   *  print(&quot;Output:\n&quot;, quantized_data.numpy())
   *  Output:
   *  [[ 0.8003921 -0.3007843  0.6984313]
   *  [ 0.8003921  0.4996078 -0.4996078]]
   *  </blockquote>
   *  </blockquote>
   *  </blockquote>
   *
   * @param inputs The inputs value
   * @param min The min value
   * @param max The max value
   * @param options carries optional attribute values
   * @return a new instance of FakeQuantWithMinMaxVars
   */
  public FakeQuantWithMinMaxVars fakeQuantWithMinMaxVars(Operand<TFloat32> inputs,
      Operand<TFloat32> min, Operand<TFloat32> max, FakeQuantWithMinMaxVars.Options... options) {
    return FakeQuantWithMinMaxVars.create(scope, inputs, min, max, options);
  }

  /**
   * Compute gradients for a FakeQuantWithMinMaxVars operation.
   *
   * @param gradients Backpropagated gradients above the FakeQuantWithMinMaxVars operation.
   * @param inputs Values passed as inputs to the FakeQuantWithMinMaxVars operation.
   *  min, max: Quantization interval, scalar floats.
   * @param min The min value
   * @param max The max value
   * @param options carries optional attribute values
   * @return a new instance of FakeQuantWithMinMaxVarsGradient
   */
  public FakeQuantWithMinMaxVarsGradient fakeQuantWithMinMaxVarsGradient(
      Operand<TFloat32> gradients, Operand<TFloat32> inputs, Operand<TFloat32> min,
      Operand<TFloat32> max, FakeQuantWithMinMaxVarsGradient.Options... options) {
    return FakeQuantWithMinMaxVarsGradient.create(scope, gradients, inputs, min, max, options);
  }

  /**
   * Fake-quantize the 'inputs' tensor of type float via per-channel floats
   *  Fake-quantize the {@code inputs} tensor of type float per-channel and one of the
   *  shapes: {@code [d]}, {@code [b, d]} {@code [b, h, w, d]} via per-channel floats {@code min} and {@code max}
   *  of shape {@code [d]} to {@code outputs} tensor of same shape as {@code inputs}.
   *  <p>Attributes
   *  <ul>
   *  <li>{@code [min; max]} define the clamping range for the {@code inputs} data.</li>
   *  <li>{@code inputs} values are quantized into the quantization range (
   *  {@code [0; 2^num_bits - 1]} when {@code narrow_range} is false and {@code [1; 2^num_bits - 1]}
   *  when it is true) and then de-quantized and output as floats in {@code [min; max]}
   *  interval.</li>
   *  <li>{@code num_bits} is the bitwidth of the quantization; between 2 and 16, inclusive.</li>
   *  </ul>
   *  <p>Before quantization, {@code min} and {@code max} values are adjusted with the following
   *  logic.
   *  It is suggested to have {@code min <= 0 <= max}. If {@code 0} is not in the range of values,
   *  the behavior can be unexpected:
   *  <ul>
   *  <li>If {@code 0 < min < max}: {@code min_adj = 0} and {@code max_adj = max - min}.</li>
   *  <li>If {@code min < max < 0}: {@code min_adj = min - max} and {@code max_adj = 0}.</li>
   *  <li>If {@code min <= 0 <= max}: {@code scale = (max - min) / (2^num_bits - 1) },
   *  {@code min_adj = scale * round(min / scale)} and {@code max_adj = max + min_adj - min}.</li>
   *  </ul>
   *  <p>This operation has a gradient and thus allows for training {@code min} and {@code max}
   *  values.
   *
   * @param inputs The inputs value
   * @param min The min value
   * @param max The max value
   * @param options carries optional attribute values
   * @return a new instance of FakeQuantWithMinMaxVarsPerChannel
   */
  public FakeQuantWithMinMaxVarsPerChannel fakeQuantWithMinMaxVarsPerChannel(
      Operand<TFloat32> inputs, Operand<TFloat32> min, Operand<TFloat32> max,
      FakeQuantWithMinMaxVarsPerChannel.Options... options) {
    return FakeQuantWithMinMaxVarsPerChannel.create(scope, inputs, min, max, options);
  }

  /**
   * Compute gradients for a FakeQuantWithMinMaxVarsPerChannel operation.
   *
   * @param gradients Backpropagated gradients above the FakeQuantWithMinMaxVars operation,
   *  shape one of: {@code [d]}, {@code [b, d]},  {@code [b, h, w, d]}.
   * @param inputs Values passed as inputs to the FakeQuantWithMinMaxVars operation, shape
   *  same as {@code gradients}.
   *  min, max: Quantization interval, floats of shape {@code [d]}.
   * @param min The min value
   * @param max The max value
   * @param options carries optional attribute values
   * @return a new instance of FakeQuantWithMinMaxVarsPerChannelGradient
   */
  public FakeQuantWithMinMaxVarsPerChannelGradient fakeQuantWithMinMaxVarsPerChannelGradient(
      Operand<TFloat32> gradients, Operand<TFloat32> inputs, Operand<TFloat32> min,
      Operand<TFloat32> max, FakeQuantWithMinMaxVarsPerChannelGradient.Options... options) {
    return FakeQuantWithMinMaxVarsPerChannelGradient.create(scope, gradients, inputs, min, max, options);
  }

  /**
   * Quantize the 'input' tensor of type float to 'output' tensor of type 'T'.
   *  [min_range, max_range] are scalar floats that specify the range for
   *  the 'input' data. The 'mode' attribute controls exactly which calculations are
   *  used to convert the float values to their quantized equivalents.  The
   *  'round_mode' attribute controls which rounding tie-breaking algorithm is used
   *  when rounding float values to their quantized equivalents.
   *  <p>In 'MIN_COMBINED' mode, each value of the tensor will undergo the following:
   *  <pre>
   *  out[i] = (in[i] - min_range) * range(T) / (max_range - min_range)
   *  if T == qint8: out[i] -= (range(T) + 1) / 2.0
   *  </pre>
   *  <p>here {@code range(T) = numeric_limits<T>::max() - numeric_limits<T>::min()}
   *  <p><em>MIN_COMBINED Mode Example</em>
   *  <p>Assume the input is type float and has a possible range of [0.0, 6.0] and the
   *  output type is quint8 ([0, 255]). The min_range and max_range values should be
   *  specified as 0.0 and 6.0. Quantizing from float to quint8 will multiply each
   *  value of the input by 255/6 and cast to quint8.
   *  <p>If the output type was qint8 ([-128, 127]), the operation will additionally
   *  subtract each value by 128 prior to casting, so that the range of values aligns
   *  with the range of qint8.
   *  <p>If the mode is 'MIN_FIRST', then this approach is used:
   *  <pre>
   *  num_discrete_values = 1 &lt;&lt; (# of bits in T)
   *  range_adjust = num_discrete_values / (num_discrete_values - 1)
   *  range = (range_max - range_min) * range_adjust
   *  range_scale = num_discrete_values / range
   *  quantized = round(input * range_scale) - round(range_min * range_scale) +
   *    numeric_limits&lt;T&gt;::min()
   *  quantized = max(quantized, numeric_limits&lt;T&gt;::min())
   *  quantized = min(quantized, numeric_limits&lt;T&gt;::max())
   *  </pre>
   *  <p>The biggest difference between this and MIN_COMBINED is that the minimum range
   *  is rounded first, before it's subtracted from the rounded value. With
   *  MIN_COMBINED, a small bias is introduced where repeated iterations of quantizing
   *  and dequantizing will introduce a larger and larger error.
   *  <p><em>SCALED mode Example</em>
   *  <p>{@code SCALED} mode matches the quantization approach used in
   *  {@code QuantizeAndDequantize{V2|V3}}.
   *  <p>If the mode is {@code SCALED}, the quantization is performed by multiplying each
   *  input value by a scaling_factor.
   *  The scaling_factor is determined from {@code min_range} and {@code max_range} to be as large
   *  as possible such that the range from {@code min_range} to {@code max_range} is representable
   *  within values of type T.
   *  <pre>
   *
   *    const int min_T = std::numeric_limits&lt;T&gt;::min();
   *    const int max_T = std::numeric_limits&lt;T&gt;::max();
   *    const float max_float = std::numeric_limits&lt;float&gt;::max();
   *
   *    const float scale_factor_from_min_side =
   *        (min_T * min_range &gt; 0) ? min_T / min_range : max_float;
   *    const float scale_factor_from_max_side =
   *        (max_T * max_range &gt; 0) ? max_T / max_range : max_float;
   *
   *    const float scale_factor = std::min(scale_factor_from_min_side,
   *                                        scale_factor_from_max_side);
   *  </pre>
   *  <p>We next use the scale_factor to adjust min_range and max_range as follows:
   *  <pre>
   *        min_range = min_T / scale_factor;
   *        max_range = max_T / scale_factor;
   *  </pre>
   *  <p>e.g. if T = qint8, and initially min_range = -10, and max_range = 9, we would
   *  compare -128/-10.0 = 12.8 to 127/9.0 = 14.11, and set scaling_factor = 12.8
   *  In this case, min_range would remain -10, but max_range would be adjusted to
   *  127 / 12.8 = 9.921875
   *  <p>So we will quantize input values in the range (-10, 9.921875) to (-128, 127).
   *  <p>The input tensor can now be quantized by clipping values to the range
   *  {@code min_range} to {@code max_range}, then multiplying by scale_factor as follows:
   *  <pre>
   *  result = round(min(max_range, max(min_range, input)) * scale_factor)
   *  </pre>
   *  <p>The adjusted {@code min_range} and {@code max_range} are returned as outputs 2 and 3 of
   *  this operation. These outputs should be used as the range for any further
   *  calculations.
   *  <p><em>narrow_range (bool) attribute</em>
   *  <p>If true, we do not use the minimum quantized value.
   *  i.e. for int8 the quantized output, it would be restricted to the range
   *  -127..127 instead of the full -128..127 range.
   *  This is provided for compatibility with certain inference backends.
   *  (Only applies to SCALED mode)
   *  <p><em>axis (int) attribute</em>
   *  <p>An optional {@code axis} attribute can specify a dimension index of the input tensor,
   *  such that quantization ranges will be calculated and applied separately for each
   *  slice of the tensor along that dimension. This is useful for per-channel
   *  quantization.
   *  <p>If axis is specified, min_range and max_range
   *  <p>if {@code axis}=None, per-tensor quantization is performed as normal.
   *  <p><em>ensure_minimum_range (float) attribute</em>
   *  <p>Ensures the minimum quantization range is at least this value.
   *  The legacy default value for this is 0.01, but it is strongly suggested to
   *  set it to 0 for new uses.
   *
   * @param input The input value
   * @param minRange The minimum value of the quantization range. This value may be adjusted by the
   *  op depending on other parameters. The adjusted value is written to {@code output_min}.
   *  If the {@code axis} attribute is specified, this must be a 1-D tensor whose size
   *  matches the {@code axis} dimension of the input and output tensors.
   * @param maxRange The maximum value of the quantization range. This value may be adjusted by the
   *  op depending on other parameters. The adjusted value is written to {@code output_max}.
   *  If the {@code axis} attribute is specified, this must be a 1-D tensor whose size
   *  matches the {@code axis} dimension of the input and output tensors.
   * @param T The value of the T attribute
   * @param options carries optional attribute values
   * @param <T> data type for {@code QuantizeV2} output and operands
   * @return a new instance of Quantize
   */
  public <T extends TNumber> Quantize<T> quantize(Operand<TFloat32> input,
      Operand<TFloat32> minRange, Operand<TFloat32> maxRange, Class<T> T,
      Quantize.Options... options) {
    return Quantize.create(scope, input, minRange, maxRange, T, options);
  }

  /**
   * Quantizes then dequantizes a tensor.
   *  This is almost identical to QuantizeAndDequantizeV2, except that num_bits is a
   *  tensor, so its value can change during training.
   *
   * @param input The input value
   * @param inputMin The inputMin value
   * @param inputMax The inputMax value
   * @param numBits The numBits value
   * @param options carries optional attribute values
   * @param <T> data type for {@code QuantizeAndDequantizeV3} output and operands
   * @return a new instance of QuantizeAndDequantize
   */
  public <T extends TNumber> QuantizeAndDequantize<T> quantizeAndDequantize(Operand<T> input,
      Operand<T> inputMin, Operand<T> inputMax, Operand<TInt32> numBits,
      QuantizeAndDequantize.Options... options) {
    return QuantizeAndDequantize.create(scope, input, inputMin, inputMax, numBits, options);
  }

  /**
   * Quantizes then dequantizes a tensor.
   *  This is almost identical to QuantizeAndDequantizeV2, except that num_bits is a
   *  tensor, so its value can change during training.
   *
   * @param input The input value
   * @param inputMin The inputMin value
   * @param inputMax The inputMax value
   * @param numBits The numBits value
   * @param options carries optional attribute values
   * @param <T> data type for {@code QuantizeAndDequantizeV3} output and operands
   * @return a new instance of QuantizeAndDequantizeV3
   */
  public <T extends TNumber> QuantizeAndDequantizeV3<T> quantizeAndDequantizeV3(Operand<T> input,
      Operand<T> inputMin, Operand<T> inputMax, Operand<TInt32> numBits,
      QuantizeAndDequantizeV3.Options... options) {
    return QuantizeAndDequantizeV3.create(scope, input, inputMin, inputMax, numBits, options);
  }

  /**
   * Quantizes then dequantizes a tensor.
   *  This is almost identical to QuantizeAndDequantizeV2, except that it returns a
   *  gradient of 1 for inputs that are within the quantization range, or 0 otherwise.
   *
   * @param input Tensor to quantize and then dequantize.
   * @param inputMin If {@code range_given == True}, this specifies the minimum input value that needs to
   *  be represented, otherwise it is determined from the min value of the {@code input}
   *  tensor.
   * @param inputMax If {@code range_given == True}, this specifies the maximum input value that needs to
   *  be represented, otherwise it is determined from the max value of the {@code input}
   *  tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code QuantizeAndDequantizeV4} output and operands
   * @return a new instance of QuantizeAndDequantizeV4
   */
  public <T extends TNumber> QuantizeAndDequantizeV4<T> quantizeAndDequantizeV4(Operand<T> input,
      Operand<T> inputMin, Operand<T> inputMax, QuantizeAndDequantizeV4.Options... options) {
    return QuantizeAndDequantizeV4.create(scope, input, inputMin, inputMax, options);
  }

  /**
   * Returns the gradient of {@code QuantizeAndDequantizeV4}.
   *  Returns a gradient of 1 for inputs that are within the quantization range,
   *  or 0 otherwise.
   *
   * @param gradients The gradients value
   * @param input The input value
   * @param inputMin The inputMin value
   * @param inputMax The inputMax value
   * @param options carries optional attribute values
   * @param <T> data type for {@code QuantizeAndDequantizeV4Grad} output and operands
   * @return a new instance of QuantizeAndDequantizeV4Grad
   */
  public <T extends TNumber> QuantizeAndDequantizeV4Grad<T> quantizeAndDequantizeV4Grad(
      Operand<T> gradients, Operand<T> input, Operand<T> inputMin, Operand<T> inputMax,
      QuantizeAndDequantizeV4Grad.Options... options) {
    return QuantizeAndDequantizeV4Grad.create(scope, gradients, input, inputMin, inputMax, options);
  }

  /**
   * Convert the quantized 'input' tensor into a lower-precision 'output', using the
   *  actual distribution of the values to maximize the usage of the lower bit depth
   *  and adjusting the output min and max ranges accordingly.
   *  <p>[input_min, input_max] are scalar floats that specify the range for the float
   *  interpretation of the 'input' data. For example, if input_min is -1.0f and
   *  input_max is 1.0f, and we are dealing with quint16 quantized data, then a 0
   *  value in the 16-bit data should be interpreted as -1.0f, and a 65535 means 1.0f.
   *  <p>This operator tries to squeeze as much precision as possible into an output with
   *  a lower bit depth by calculating the actual min and max values found in the
   *  data. For example, maybe that quint16 input has no values lower than 16,384 and
   *  none higher than 49,152. That means only half the range is actually needed, all
   *  the float interpretations are between -0.5f and 0.5f, so if we want to compress
   *  the data into a quint8 output, we can use that range rather than the theoretical
   *  -1.0f to 1.0f that is suggested by the input min and max.
   *  <p>In practice, this is most useful for taking output from operations like
   *  QuantizedMatMul that can produce higher bit-depth outputs than their inputs and
   *  may have large potential output ranges, but in practice have a distribution of
   *  input values that only uses a small fraction of the possible range. By feeding
   *  that output into this operator, we can reduce it from 32 bits down to 8 with
   *  minimal loss of accuracy.
   *
   * @param input The input value
   * @param inputMin The float value that the minimum quantized input value represents.
   * @param inputMax The float value that the maximum quantized input value represents.
   * @param outType The type of the output. Should be a lower bit depth than Tinput.
   * @param <U> data type for {@code QuantizeDownAndShrinkRange} output and operands
   * @return a new instance of QuantizeDownAndShrinkRange
   */
  public <U extends TNumber> QuantizeDownAndShrinkRange<U> quantizeDownAndShrinkRange(
      Operand<? extends TNumber> input, Operand<TFloat32> inputMin, Operand<TFloat32> inputMax,
      Class<U> outType) {
    return QuantizeDownAndShrinkRange.create(scope, input, inputMin, inputMax, outType);
  }

  /**
   * Concatenates quantized tensors along one dimension.
   *
   * @param concatDim 0-D.  The dimension along which to concatenate.  Must be in the
   *  range [0, rank(values)).
   * @param values The {@code N} Tensors to concatenate. Their ranks and types must match,
   *  and their sizes must match in all dimensions except {@code concat_dim}.
   * @param inputMins The minimum scalar values for each of the input tensors.
   * @param inputMaxes The maximum scalar values for each of the input tensors.
   * @param <T> data type for {@code QuantizedConcat} output and operands
   * @return a new instance of QuantizedConcat
   */
  public <T extends TType> QuantizedConcat<T> quantizedConcat(Operand<TInt32> concatDim,
      Iterable<Operand<T>> values, Iterable<Operand<TFloat32>> inputMins,
      Iterable<Operand<TFloat32>> inputMaxes) {
    return QuantizedConcat.create(scope, concatDim, values, inputMins, inputMaxes);
  }

  /**
   * The QuantizedMatMulWithBiasAndDequantize operation
   *
   * @param a The a value
   * @param b The b value
   * @param bias The bias value
   * @param minA The minA value
   * @param maxA The maxA value
   * @param minB The minB value
   * @param maxB The maxB value
   * @param minFreezedOutput The minFreezedOutput value
   * @param maxFreezedOutput The maxFreezedOutput value
   * @param Toutput The value of the Toutput attribute
   * @param options carries optional attribute values
   * @param <W> data type for {@code QuantizedMatMulWithBiasAndDequantize} output and operands
   * @return a new instance of QuantizedMatMulWithBiasAndDequantize
   */
  public <W extends TNumber> QuantizedMatMulWithBiasAndDequantize<W> quantizedMatMulWithBiasAndDequantize(
      Operand<? extends TNumber> a, Operand<? extends TNumber> b, Operand<? extends TNumber> bias,
      Operand<TFloat32> minA, Operand<TFloat32> maxA, Operand<TFloat32> minB,
      Operand<TFloat32> maxB, Operand<TFloat32> minFreezedOutput,
      Operand<TFloat32> maxFreezedOutput, Class<W> Toutput,
      QuantizedMatMulWithBiasAndDequantize.Options... options) {
    return QuantizedMatMulWithBiasAndDequantize.create(scope, a, b, bias, minA, maxA, minB, maxB, minFreezedOutput, maxFreezedOutput, Toutput, options);
  }

  /**
   * The QuantizedMatMulWithBiasAndRequantize operation
   *
   * @param a The a value
   * @param b The b value
   * @param bias The bias value
   * @param minA The minA value
   * @param maxA The maxA value
   * @param minB The minB value
   * @param maxB The maxB value
   * @param minFreezedOutput The minFreezedOutput value
   * @param maxFreezedOutput The maxFreezedOutput value
   * @param Toutput The value of the Toutput attribute
   * @param options carries optional attribute values
   * @param <W> data type for {@code QuantizedMatMulWithBiasAndRequantize} output and operands
   * @return a new instance of QuantizedMatMulWithBiasAndRequantize
   */
  public <W extends TNumber> QuantizedMatMulWithBiasAndRequantize<W> quantizedMatMulWithBiasAndRequantize(
      Operand<? extends TNumber> a, Operand<? extends TNumber> b, Operand<? extends TNumber> bias,
      Operand<TFloat32> minA, Operand<TFloat32> maxA, Operand<TFloat32> minB,
      Operand<TFloat32> maxB, Operand<TFloat32> minFreezedOutput,
      Operand<TFloat32> maxFreezedOutput, Class<W> Toutput,
      QuantizedMatMulWithBiasAndRequantize.Options... options) {
    return QuantizedMatMulWithBiasAndRequantize.create(scope, a, b, bias, minA, maxA, minB, maxB, minFreezedOutput, maxFreezedOutput, Toutput, options);
  }

  /**
   * Computes a range that covers the actual values present in a quantized tensor.
   *  Given a quantized tensor described by {@code (input, input_min, input_max)}, outputs a
   *  range that covers the actual values present in that tensor. This op is typically
   *  used to produce the {@code requested_output_min} and {@code requested_output_max} for
   *  {@code Requantize}.
   *
   * @param input The input value
   * @param inputMin The float value that the minimum quantized input value represents.
   * @param inputMax The float value that the maximum quantized input value represents.
   * @return a new instance of RequantizationRange
   */
  public RequantizationRange requantizationRange(Operand<? extends TNumber> input,
      Operand<TFloat32> inputMin, Operand<TFloat32> inputMax) {
    return RequantizationRange.create(scope, input, inputMin, inputMax);
  }

  /**
   * Converts the quantized {@code input} tensor into a lower-precision {@code output}.
   *  Converts the quantized {@code input} tensor into a lower-precision {@code output}, using the
   *  output range specified with {@code requested_output_min} and {@code requested_output_max}.
   *  <p>{@code [input_min, input_max]} are scalar floats that specify the range for the float
   *  interpretation of the {@code input} data. For example, if {@code input_min} is -1.0f and
   *  {@code input_max} is 1.0f, and we are dealing with {@code quint16} quantized data, then a 0
   *  value in the 16-bit data should be interpreted as -1.0f, and a 65535 means 1.0f.
   *
   * @param input The input value
   * @param inputMin The float value that the minimum quantized input value represents.
   * @param inputMax The float value that the maximum quantized input value represents.
   * @param requestedOutputMin The float value that the minimum quantized output value represents.
   * @param requestedOutputMax The float value that the maximum quantized output value represents.
   * @param outType The type of the output. Should be a lower bit depth than Tinput.
   * @param <U> data type for {@code Requantize} output and operands
   * @return a new instance of Requantize
   */
  public <U extends TNumber> Requantize<U> requantize(Operand<? extends TNumber> input,
      Operand<TFloat32> inputMin, Operand<TFloat32> inputMax, Operand<TFloat32> requestedOutputMin,
      Operand<TFloat32> requestedOutputMax, Class<U> outType) {
    return Requantize.create(scope, input, inputMin, inputMax, requestedOutputMin, requestedOutputMax, outType);
  }

  /**
   * Perform dequantization on the quantized Tensor {@code input}.
   *  Given quantized {@code input} which was quantized using {@code scales} and {@code zero_points}, performs dequantization using the formula:
   *  dequantized_data = (quantized_data - zero_point) * scale.
   *
   * @param input Must be a Tensor of Tin.
   * @param scales The float value(s) used as scale(s) when quantizing original data that input represents.
   *  Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (input.dim_size(quantization_axis),) (per-axis quantization).
   * @param zeroPoints The int32 value(s) used as zero_point(s) when quantizing original data that input represents.
   *  Same shape condition as scales.
   * @param Tout The type of output Tensor. A tf.DType from: tf.qint8, tf.qint32
   * @param quantizationMinVal The quantization min value that was used when input was quantized.
   *  The purpose of this attribute is typically (but not limited to) to indicate narrow range, where this is set to:
   *  {@code (Tin lowest) + 1} if narrow range, and {@code (Tin lowest)} otherwise.
   *  For example, if Tin is qint8, this is set to -127 if narrow range quantized or -128 if not.
   * @param quantizationMaxVal The quantization max value that was used when input was quantized.
   *  The purpose of this attribute is typically (but not limited to) indicate narrow range, where this is set to:
   *  {@code (Tout max)} for both narrow range and not narrow range.
   *  For example, if Tin is qint8, this is set to 127.
   * @param options carries optional attribute values
   * @param <U> data type for {@code UniformDequantize} output and operands
   * @return a new instance of UniformDequantize
   */
  public <U extends TNumber> UniformDequantize<U> uniformDequantize(
      Operand<? extends TNumber> input, Operand<TFloat32> scales, Operand<TInt32> zeroPoints,
      Class<U> Tout, Long quantizationMinVal, Long quantizationMaxVal,
      UniformDequantize.Options... options) {
    return UniformDequantize.create(scope, input, scales, zeroPoints, Tout, quantizationMinVal, quantizationMaxVal, options);
  }

  /**
   * Perform quantization on Tensor {@code input}.
   *  Given {@code input}, {@code scales} and {@code zero_points}, performs quantization using the formula:
   *  quantized_data = floor(input_data * (1.0f / scale) + 0.5f) + zero_point
   *
   * @param input Must be a Tensor of Tin.
   * @param scales The float value(s) to use as scale(s) to quantize {@code input}.
   *  Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (input.dim_size(quantization_axis),) (per-axis quantization).
   * @param zeroPoints The int32 value(s) to use as zero_point(s) to quantize {@code input}.
   *  Same shape condition as scales.
   * @param Tout The type of output Tensor. A tf.DType from: tf.float32
   * @param quantizationMinVal The quantization min value to quantize {@code input}.
   *  The purpose of this attribute is typically (but not limited to) to indicate narrow range, where this is set to:
   *  {@code (Tin lowest) + 1} if narrow range, and {@code (Tin lowest)} otherwise.
   *  For example, if Tin is qint8, this is set to -127 if narrow range quantized or -128 if not.
   * @param quantizationMaxVal The quantization max value to quantize {@code input}.
   *  The purpose of this attribute is typically (but not limited to) indicate narrow range, where this is set to:
   *  {@code (Tout max)} for both narrow range and not narrow range.
   *  For example, if Tin is qint8, this is set to 127.
   * @param options carries optional attribute values
   * @param <U> data type for {@code UniformQuantize} output and operands
   * @return a new instance of UniformQuantize
   */
  public <U extends TNumber> UniformQuantize<U> uniformQuantize(Operand<? extends TNumber> input,
      Operand<TFloat32> scales, Operand<TInt32> zeroPoints, Class<U> Tout, Long quantizationMinVal,
      Long quantizationMaxVal, UniformQuantize.Options... options) {
    return UniformQuantize.create(scope, input, scales, zeroPoints, Tout, quantizationMinVal, quantizationMaxVal, options);
  }

  /**
   * Perform quantized dot of quantized Tensor {@code lhs} and quantized Tensor {@code rhs} to make quantized {@code output}.
   *  Given quantized {@code lhs} and quantized {@code rhs}, performs quantized dot on {@code lhs} and {@code rhs} to make quantized {@code output}.
   *  {@code lhs} and {@code rhs} must be 2D Tensors and the lhs.dim_size(1) must match rhs.dim_size(0).
   *  {@code lhs} and {@code rhs} must be quantized Tensor, where data value is quantized using the formula:
   *  quantized_data = clip(original_data / scale + zero_point, quantization_min_val, quantization_max_val).
   *  {@code output} is also quantized, using the same formula.
   *  If {@code rhs} is per-tensor quantized, {@code output} must be also per-tensor quantized.
   *
   * @param lhs Must be a 2D Tensor of Tin.
   * @param rhs Must be a 2D Tensor of Tin.
   * @param lhsScales The float value(s) used as scale when quantizing original data that lhs represents.
   *  Must be a scalar Tensor (lhs supports only per-tensor quantization).
   * @param lhsZeroPoints The int32 value(s) used as zero_point when quantizing original data that lhs represents.
   *  Same shape condition as lhs_scales.
   * @param rhsScales The float value(s) used as scale when quantizing original data that rhs represents.
   *  Must be a scalar Tensor (per-tensor quantization) or 1D Tensor of size (rhs.dim_size(1),) (per-channel quantization).
   * @param rhsZeroPoints The int32 value(s) used as zero_point when quantizing original data that rhs represents.
   *  Same shape condition as rhs_scales.
   * @param outputScales The float value(s) to use as scales when quantizing original data that output represents.
   *  Must be a scalar Tensor (per-tensor quantization) or 1D Tensor of size (output.dim_size(1),) (per-channel quantization).
   *  If rhs is per-tensor quantized, output must be also per-tensor quantized.
   *  This means that if rhs_scales and rhs_zero_points are scalar Tensors, output_scales and output_zero_points must be scalar Tensors as well.
   * @param outputZeroPoints The int32 value(s) used as zero_point when quantizing original data that output represents.
   *  Same shape condition as rhs_scales.
   * @param Tout The type of output Tensor.
   * @param lhsQuantizationMinVal The min value of the quantized data stored in lhs.
   *  For example, if Tin is qint8, this must be set to -127 if narrow range quantized or -128 if not.
   * @param lhsQuantizationMaxVal The max value of the quantized data stored in rhs.
   *  For example, if Tin is qint8, this must be set to 127.
   * @param rhsQuantizationMinVal The min value of the quantized data stored in rhs.
   *  For example, if Trhs is qint8, this must be set to -127 if narrow range quantized or -128 if not.
   * @param rhsQuantizationMaxVal The max value of the quantized data stored in rhs.
   *  For example, if Trhs is qint8, this must be set to 127.
   * @param outputQuantizationMinVal The min value of the quantized data stored in output.
   *  For example, if Tout is qint8, this must be set to -127 if narrow range quantized or -128 if not.
   * @param outputQuantizationMaxVal The max value of the quantized data stored in output.
   *  For example, if Tout is qint8, this must be set to 127.
   * @param options carries optional attribute values
   * @param <U> data type for {@code UniformQuantizedDot} output and operands
   * @param <T> data type for {@code UniformQuantizedDot} output and operands
   * @return a new instance of UniformQuantizedDot
   */
  public <U extends TNumber, T extends TNumber> UniformQuantizedDot<U> uniformQuantizedDot(
      Operand<T> lhs, Operand<T> rhs, Operand<TFloat32> lhsScales, Operand<TInt32> lhsZeroPoints,
      Operand<TFloat32> rhsScales, Operand<TInt32> rhsZeroPoints, Operand<TFloat32> outputScales,
      Operand<TInt32> outputZeroPoints, Class<U> Tout, Long lhsQuantizationMinVal,
      Long lhsQuantizationMaxVal, Long rhsQuantizationMinVal, Long rhsQuantizationMaxVal,
      Long outputQuantizationMinVal, Long outputQuantizationMaxVal,
      UniformQuantizedDot.Options... options) {
    return UniformQuantizedDot.create(scope, lhs, rhs, lhsScales, lhsZeroPoints, rhsScales, rhsZeroPoints, outputScales, outputZeroPoints, Tout, lhsQuantizationMinVal, lhsQuantizationMaxVal, rhsQuantizationMinVal, rhsQuantizationMaxVal, outputQuantizationMinVal, outputQuantizationMaxVal, options);
  }

  /**
   * Perform hybrid quantized dot of float Tensor {@code lhs} and quantized Tensor {@code rhs}.
   *  Given float {@code lhs} and quantized {@code rhs}, internally performs quantization on {@code lhs}, and then performs quantized dot on quantized lhs and {@code rhs}.
   *  The internal quantization on {@code lhs} is a quantization to qint8, dynamic range, per-batch (per-axis along axis 0), asymmetric, and not narrow range (the range is [-128, 127]).
   *  {@code lhs} and {@code rhs} must be 2D Tensors and the lhs.dim_size(1) must match rhs.dim_size(0).
   *  {@code rhs} must be quantized Tensor, where its data value is quantized using the formula:
   *  quantized_data = clip(original_data / scale + zero_point, quantization_min_val, quantization_max_val).
   *
   * @param lhs Must be a 2D Tensor of Tlhs.
   * @param rhs Must be a 2D Tensor of Trhs.
   * @param rhsScales The float value(s) used as scale when quantizing original data that rhs represents.
   *  Must be a scalar Tensor (per-tensor quantization) or 1D Tensor of size (rhs.dim_size(1),) (per-channel quantization).
   * @param rhsZeroPoints The int32 value(s) used as zero_point when quantizing original data that rhs represents.
   *  Same shape condition as rhs_scales.
   * @param Tout The type of output Tensor.
   * @param rhsQuantizationMinVal The min value of the quantized data stored in rhs.
   *  For example, if Trhs is qint8, this must be set to -127 if narrow range quantized or -128 if not.
   * @param rhsQuantizationMaxVal The max value of the quantized data stored in rhs.
   *  For example, if Trhs is qint8, this must be set to 127.
   * @param options carries optional attribute values
   * @param <V> data type for {@code UniformQuantizedDotHybrid} output and operands
   * @return a new instance of UniformQuantizedDotHybrid
   */
  public <V extends TNumber> UniformQuantizedDotHybrid<V> uniformQuantizedDotHybrid(
      Operand<? extends TNumber> lhs, Operand<? extends TNumber> rhs, Operand<TFloat32> rhsScales,
      Operand<TInt32> rhsZeroPoints, Class<V> Tout, Long rhsQuantizationMinVal,
      Long rhsQuantizationMaxVal, UniformQuantizedDotHybrid.Options... options) {
    return UniformQuantizedDotHybrid.create(scope, lhs, rhs, rhsScales, rhsZeroPoints, Tout, rhsQuantizationMinVal, rhsQuantizationMaxVal, options);
  }

  /**
   * Given quantized tensor {@code input}, requantize it with new quantization parameters.
   *  Given quantized tensor {@code input}, which was quantized using {input_scales, input_zero_points, input_quantization_axis, input_quantization_min_val, input_quantization_max_val},
   *  requantize it to a tensor, which is quantized using {output_scales, output_zero_points, output_quantization_axis, output_quantization_min_val, output_quantization_max_val}.
   *  The requantization is done by using the formula:
   *  output_quantized_data = clip(
   *  (input_quantized_data - input_zero_point) * (input_scale / output_scale) + output_zero_point,
   *  output_quantization_min_val,
   *  output_quantization_max_val)
   *  <p>Per-tensor and per-axis quantization supported cases are followings:
   *  <ul>
   *  <li>per-tensor -&gt; per-tensor</li>
   *  <li>per-tensor -&gt; per-axis</li>
   *  <li>per-axis -&gt; per-axis where input_quantization_axis equals output_quantization_axis.
   *  i.e. At least one among input_quantization_axis and output_quantization_axis must be -1, or two must be equal.</li>
   *  </ul>
   *
   * @param input Must be a Tensor of Tin.
   * @param inputScales The float value(s) used as scale(s) when quantizing original data that {@code input} represents.
   *  Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (input.dim_size(quantization_axis),) (per-axis quantization).
   * @param inputZeroPoints The int32 value(s) used as zero_point(s) when quantizing original data that {@code input} represents.
   *  Same shape condition as scales.
   * @param outputScales The float value(s) to use as new scale(s) to quantize original data that {@code input} represents.
   *  Must be a scalar Tensor if quantization_axis is -1 (per-tensor quantization), otherwise 1D Tensor of size (input.dim_size(quantization_axis),) (per-axis quantization).
   * @param outputZeroPoints The int32 value(s) to use as new zero_point(s) to quantize original data that {@code input} represents.
   *  Same shape condition as scales.
   * @param Tout The type of output Tensor. A tf.DType from: tf.qint8, tf.qint32
   * @param inputQuantizationMinVal The quantization min value that was used when quantizing original data that {@code input} represents.
   *  The purpose of this attribute is typically (but not limited to) to indicate narrow range, where this is set to:
   *  {@code (Tin lowest) + 1} if narrow range, and {@code (Tin lowest)} otherwise.
   *  For example, if Tin is qint8, this is set to -127 if narrow range quantized or -128 if not.
   * @param inputQuantizationMaxVal The quantization max value that was used when quantizing original data that {@code input} represents.
   *  The purpose of this attribute is typically (but not limited to) indicate narrow range, where this is set to:
   *  {@code (Tout max)} for both narrow range and not narrow range.
   *  For example, if Tin is qint8, this is set to 127.
   * @param outputQuantizationMinVal The new quantization min value to quantize original data that {@code input} represents.
   * @param outputQuantizationMaxVal The new quantization max value to quantize original data that {@code input} represents.
   * @param options carries optional attribute values
   * @param <U> data type for {@code UniformRequantize} output and operands
   * @return a new instance of UniformRequantize
   */
  public <U extends TNumber> UniformRequantize<U> uniformRequantize(
      Operand<? extends TNumber> input, Operand<TFloat32> inputScales,
      Operand<TInt32> inputZeroPoints, Operand<TFloat32> outputScales,
      Operand<TInt32> outputZeroPoints, Class<U> Tout, Long inputQuantizationMinVal,
      Long inputQuantizationMaxVal, Long outputQuantizationMinVal, Long outputQuantizationMaxVal,
      UniformRequantize.Options... options) {
    return UniformRequantize.create(scope, input, inputScales, inputZeroPoints, outputScales, outputZeroPoints, Tout, inputQuantizationMinVal, inputQuantizationMaxVal, outputQuantizationMinVal, outputQuantizationMaxVal, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
