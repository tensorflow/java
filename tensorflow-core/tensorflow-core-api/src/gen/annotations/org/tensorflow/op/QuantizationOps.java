// Copyright 2020 The TensorFlow Authors. All Rights Reserved.
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
import org.tensorflow.op.quantization.RequantizationRange;
import org.tensorflow.op.quantization.Requantize;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code quantization} operations as {@link Op Op}s
 *
 * @see {@link Ops}
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
   * @param <U> data type for {@code output} output
   * @param input the input value
   * @param minRange The minimum scalar value possibly produced for the input.
   * @param maxRange The maximum scalar value possibly produced for the input.
   * @param options carries optional attribute values
   * @return a new instance of Dequantize, with default output types
   */
  public Dequantize<TFloat32> dequantize(Operand<? extends TNumber> input,
      Operand<TFloat32> minRange, Operand<TFloat32> maxRange, Dequantize.Options[] options) {
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
   * @param <U> data type for {@code output} output
   * @param input the input value
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
   * Fake-quantize the 'inputs' tensor, type float to 'outputs' tensor of same type.
   *  Attributes
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
   *  <p>Quantization is called fake since the output is still in floating point.
   *
   * @param inputs the inputs value
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
   *
   * @param inputs the inputs value
   * @param min the min value
   * @param max the max value
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
   * @param min the min value
   * @param max the max value
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
   * @param inputs the inputs value
   * @param min the min value
   * @param max the max value
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
   * @param min the min value
   * @param max the max value
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
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param minRange The minimum value of the quantization range. This value may be adjusted by the
   *  op depending on other parameters. The adjusted value is written to {@code output_min}.
   *  If the {@code axis} attribute is specified, this must be a 1-D tensor whose size
   *  matches the {@code axis} dimension of the input and output tensors.
   * @param maxRange The maximum value of the quantization range. This value may be adjusted by the
   *  op depending on other parameters. The adjusted value is written to {@code output_max}.
   *  If the {@code axis} attribute is specified, this must be a 1-D tensor whose size
   *  matches the {@code axis} dimension of the input and output tensors.
   * @param T the value of the T property
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
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param inputMin the inputMin value
   * @param inputMax the inputMax value
   * @param numBits the numBits value
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
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param inputMin the inputMin value
   * @param inputMax the inputMax value
   * @param numBits the numBits value
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
   * Returns the gradient of {@code quantization.QuantizeAndDequantizeV4}.
   *  This is almost identical to QuantizeAndDequantizeV2, except that it returns a
   *  gradient of 1 for inputs that are within the quantization range, or 0 otherwise.
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param inputMin the inputMin value
   * @param inputMax the inputMax value
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
   * @param <T> data type for {@code input_backprop} output
   * @param gradients the gradients value
   * @param input the input value
   * @param inputMin the inputMin value
   * @param inputMax the inputMax value
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
   * @param <U> data type for {@code output} output
   * @param input the input value
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
   * @param <T> data type for {@code output} output
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
   * Computes a range that covers the actual values present in a quantized tensor.
   *  Given a quantized tensor described by {@code (input, input_min, input_max)}, outputs a
   *  range that covers the actual values present in that tensor. This op is typically
   *  used to produce the {@code requested_output_min} and {@code requested_output_max} for
   *  {@code Requantize}.
   *
   * @param input the input value
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
   * @param <U> data type for {@code output} output
   * @param input the input value
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
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
