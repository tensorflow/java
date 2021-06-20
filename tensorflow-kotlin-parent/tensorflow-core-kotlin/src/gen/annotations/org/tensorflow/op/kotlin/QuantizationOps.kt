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
package org.tensorflow.op.kotlin

import kotlin.Array
import kotlin.Boolean
import kotlin.Float
import kotlin.Long
import kotlin.String
import kotlin.jvm.JvmName
import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.quantization.Dequantize
import org.tensorflow.op.quantization.FakeQuantWithMinMaxArgs
import org.tensorflow.op.quantization.FakeQuantWithMinMaxArgsGradient
import org.tensorflow.op.quantization.FakeQuantWithMinMaxVars
import org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsGradient
import org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannel
import org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannelGradient
import org.tensorflow.op.quantization.Quantize
import org.tensorflow.op.quantization.QuantizeAndDequantize
import org.tensorflow.op.quantization.QuantizeAndDequantizeV3
import org.tensorflow.op.quantization.QuantizeAndDequantizeV4
import org.tensorflow.op.quantization.QuantizeAndDequantizeV4Grad
import org.tensorflow.op.quantization.QuantizeDownAndShrinkRange
import org.tensorflow.op.quantization.QuantizedConcat
import org.tensorflow.op.quantization.RequantizationRange
import org.tensorflow.op.quantization.Requantize
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building `quantization` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class QuantizationOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.QuantizationOps = ops.java.quantization

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Dequantize the 'input' tensor into a float or bfloat16 Tensor.
     *  &#91;min_range, max_range&#93; are scalar floats that specify the range for
     *  the output. The 'mode' attribute controls exactly which calculations are
     *  used to convert the float values to their quantized equivalents.
     *  
     * In 'MIN_COMBINED' mode, each value of the tensor will undergo the following:
     *  ```
     * if T == qint8: in[i] += (range(T) + 1)/ 2.0
     *  out[i] = min_range + (in[i]* (max_range - min_range) / range(T))
     *  
     * ```
     *  
     * here `range(T) = numeric_limits<T>::max() - numeric_limits<T>::min()`
     *  
     * _MIN_COMBINED Mode Example_
     *  
     * If the input comes from a QuantizedRelu6, the output type is
     *  quint8 (range of 0-255) but the possible range of QuantizedRelu6 is
     *  0-6.  The min_range and max_range values are therefore 0.0 and 6.0.
     *  Dequantize on quint8 will take each value, cast to float, and multiply
     *  by 6 / 255.
     *  Note that if quantizedtype is qint8, the operation will additionally add
     *  each value by 128 prior to casting.
     *  
     * If the mode is 'MIN_FIRST', then this approach is used:
     *  ```
     * num_discrete_values = 1 << (# of bits in T)
     *  range_adjust = num_discrete_values / (num_discrete_values - 1)
     *  range = (range_max - range_min) * range_adjust
     *  range_scale = range / num_discrete_values
     *  const double offset_input = static_cast<double>(input) - lowest_quantized;
     *  result = range_min + ((input - numeric_limits<T>::min()) * range_scale)
     *  
     * ```
     *  
     * If the mode is `SCALED`, dequantization is performed by multiplying each
     *  input value by a scaling_factor. (Thus an input of 0 always maps to 0.0).
     *  
     * The scaling_factor is determined from `min_range`, `max_range`, and
     *  `narrow_range` in a way that is compatible with `QuantizeAndDequantize{V2|V3`}
     *  and `QuantizeV2`, using the following algorithm:
     *  ```
     * const int min_expected_T = std::numeric_limits<T>::min() +
     *      (narrow_range ? 1 : 0);
     *    const int max_expected_T = std::numeric_limits<T>::max();
     *    const float max_expected_T = std::numeric_limits<float>::max();
     *
     *    const float scale_factor =
     *      (std::numeric_limits<T>::min() == 0) ? (max_range / max_expected_T)
     *                                           : std::max(min_range / min_expected_T,
     *                                                      max_range / max_expected_T);
     *  
     * ```
     *
     * @param <U> data type for `output` output
     * @param input the input value
     * @param minRange The minimum scalar value possibly produced for the input.
     * @param maxRange The maximum scalar value possibly produced for the input.
     * @param options carries optional attribute values
     * @return a new instance of Dequantize, with default output types
     * @see org.tensorflow.op.QuantizationOps.dequantize
     */
    public fun dequantize(
        input: Operand<out TNumber>,
        minRange: Operand<TFloat32>,
        maxRange: Operand<TFloat32>,
        options: Array<Dequantize.Options>
    ): Dequantize<TFloat32> = java.dequantize(    
        input,
        minRange,
        maxRange,
        options
        )

    /**
     * Dequantize the 'input' tensor into a float or bfloat16 Tensor.
     *  &#91;min_range, max_range&#93; are scalar floats that specify the range for
     *  the output. The 'mode' attribute controls exactly which calculations are
     *  used to convert the float values to their quantized equivalents.
     *  
     * In 'MIN_COMBINED' mode, each value of the tensor will undergo the following:
     *  ```
     * if T == qint8: in[i] += (range(T) + 1)/ 2.0
     *  out[i] = min_range + (in[i]* (max_range - min_range) / range(T))
     *  
     * ```
     *  
     * here `range(T) = numeric_limits<T>::max() - numeric_limits<T>::min()`
     *  
     * _MIN_COMBINED Mode Example_
     *  
     * If the input comes from a QuantizedRelu6, the output type is
     *  quint8 (range of 0-255) but the possible range of QuantizedRelu6 is
     *  0-6.  The min_range and max_range values are therefore 0.0 and 6.0.
     *  Dequantize on quint8 will take each value, cast to float, and multiply
     *  by 6 / 255.
     *  Note that if quantizedtype is qint8, the operation will additionally add
     *  each value by 128 prior to casting.
     *  
     * If the mode is 'MIN_FIRST', then this approach is used:
     *  ```
     * num_discrete_values = 1 << (# of bits in T)
     *  range_adjust = num_discrete_values / (num_discrete_values - 1)
     *  range = (range_max - range_min) * range_adjust
     *  range_scale = range / num_discrete_values
     *  const double offset_input = static_cast<double>(input) - lowest_quantized;
     *  result = range_min + ((input - numeric_limits<T>::min()) * range_scale)
     *  
     * ```
     *  
     * If the mode is `SCALED`, dequantization is performed by multiplying each
     *  input value by a scaling_factor. (Thus an input of 0 always maps to 0.0).
     *  
     * The scaling_factor is determined from `min_range`, `max_range`, and
     *  `narrow_range` in a way that is compatible with `QuantizeAndDequantize{V2|V3`}
     *  and `QuantizeV2`, using the following algorithm:
     *  ```
     * const int min_expected_T = std::numeric_limits<T>::min() +
     *      (narrow_range ? 1 : 0);
     *    const int max_expected_T = std::numeric_limits<T>::max();
     *    const float max_expected_T = std::numeric_limits<float>::max();
     *
     *    const float scale_factor =
     *      (std::numeric_limits<T>::min() == 0) ? (max_range / max_expected_T)
     *                                           : std::max(min_range / min_expected_T,
     *                                                      max_range / max_expected_T);
     *  
     * ```
     *
     * @param <U> data type for `output` output
     * @param input the input value
     * @param minRange The minimum scalar value possibly produced for the input.
     * @param maxRange The maximum scalar value possibly produced for the input.
     * @param dtype Type of the output tensor. Currently Dequantize supports float and bfloat16.
     *  If 'dtype' is 'bfloat16', it only supports 'MIN_COMBINED' mode.
     * @param options carries optional attribute values
     * @param <U> data type for `Dequantize` output and operands
     * @return a new instance of Dequantize
     * @see org.tensorflow.op.QuantizationOps.dequantize
     * @param mode Sets the mode option.
     *
     * @param mode the mode option
     * @return this Options instance.
     * @param narrowRange Sets the narrowRange option.
     *
     * @param narrowRange the narrowRange option
     * @return this Options instance.
     * @param axis Sets the axis option.
     *
     * @param axis the axis option
     * @return this Options instance.
     */
    public fun <U : TNumber> dequantize(
        input: Operand<out TNumber>,
        minRange: Operand<TFloat32>,
        maxRange: Operand<TFloat32>,
        dtype: Class<U>,
        mode: String? = null,
        narrowRange: Boolean? = null,
        axis: Long? = null
    ): Dequantize<U> = java.dequantize<U>(    
        input,
        minRange,
        maxRange,
        dtype,
        *listOfNotNull(
            mode?.let{ org.tensorflow.op.quantization.Dequantize.mode(it) },
            narrowRange?.let{ org.tensorflow.op.quantization.Dequantize.narrowRange(it) },
            axis?.let{ org.tensorflow.op.quantization.Dequantize.axis(it) }
        ).toTypedArray()
        )

    /**
     * Fake-quantize the 'inputs' tensor, type float to 'outputs' tensor of same type.
     *  Attributes
     *  <ul>
     *  <li>`&#91;min; max&#93;` define the clamping range for the `inputs` data.</li>
     *  <li>`inputs` values are quantized into the quantization range (
     *  `&#91;0; 2^num_bits - 1&#93;` when `narrow_range` is false and `&#91;1; 2^num_bits - 1&#93;`
     *  when it is true) and then de-quantized and output as floats in `&#91;min; max&#93;`
     *  interval.</li>
     *  <li>`num_bits` is the bitwidth of the quantization; between 2 and 16, inclusive.</li>
     *  </ul>
     *  
     * Before quantization, `min` and `max` values are adjusted with the following
     *  logic.
     *  It is suggested to have `min <= 0 <= max`. If `0` is not in the range of values,
     *  the behavior can be unexpected:
     *  <ul>
     *  <li>If `0 < min < max`: `min_adj = 0` and `max_adj = max - min`.</li>
     *  <li>If `min < max < 0`: `min_adj = min - max` and `max_adj = 0`.</li>
     *  <li>If `min <= 0 <= max`: `scale = (max - min) / (2^num_bits - 1) `,
     *  `min_adj = scale * round(min / scale)` and `max_adj = max + min_adj - min`.</li>
     *  </ul>
     *  
     * Quantization is called fake since the output is still in floating point.
     *
     * @param inputs the inputs value
     * @param options carries optional attribute values
     * @return a new instance of FakeQuantWithMinMaxArgs
     * @see org.tensorflow.op.QuantizationOps.fakeQuantWithMinMaxArgs
     * @param min Sets the min option.
     *
     * @param min the min option
     * @return this Options instance.
     * @param max Sets the max option.
     *
     * @param max the max option
     * @return this Options instance.
     * @param numBits Sets the numBits option.
     *
     * @param numBits the numBits option
     * @return this Options instance.
     * @param narrowRange Sets the narrowRange option.
     *
     * @param narrowRange the narrowRange option
     * @return this Options instance.
     */
    public fun fakeQuantWithMinMaxArgs(
        inputs: Operand<TFloat32>,
        min: Float? = null,
        max: Float? = null,
        numBits: Long? = null,
        narrowRange: Boolean? = null
    ): FakeQuantWithMinMaxArgs = java.fakeQuantWithMinMaxArgs(    
        inputs,
        *listOfNotNull(
            min?.let{ org.tensorflow.op.quantization.FakeQuantWithMinMaxArgs.min(it) },
            max?.let{ org.tensorflow.op.quantization.FakeQuantWithMinMaxArgs.max(it) },
            numBits?.let{ org.tensorflow.op.quantization.FakeQuantWithMinMaxArgs.numBits(it) },
            narrowRange?.let{ org.tensorflow.op.quantization.FakeQuantWithMinMaxArgs.narrowRange(it) }
        ).toTypedArray()
        )

    /**
     * Compute gradients for a FakeQuantWithMinMaxArgs operation.
     *
     * @param gradients Backpropagated gradients above the FakeQuantWithMinMaxArgs operation.
     * @param inputs Values passed as inputs to the FakeQuantWithMinMaxArgs operation.
     * @param options carries optional attribute values
     * @return a new instance of FakeQuantWithMinMaxArgsGradient
     * @see org.tensorflow.op.QuantizationOps.fakeQuantWithMinMaxArgsGradient
     * @param min Sets the min option.
     *
     * @param min the min option
     * @return this Options instance.
     * @param max Sets the max option.
     *
     * @param max the max option
     * @return this Options instance.
     * @param numBits Sets the numBits option.
     *
     * @param numBits the numBits option
     * @return this Options instance.
     * @param narrowRange Sets the narrowRange option.
     *
     * @param narrowRange the narrowRange option
     * @return this Options instance.
     */
    public fun fakeQuantWithMinMaxArgsGradient(
        gradients: Operand<TFloat32>,
        inputs: Operand<TFloat32>,
        min: Float? = null,
        max: Float? = null,
        numBits: Long? = null,
        narrowRange: Boolean? = null
    ): FakeQuantWithMinMaxArgsGradient = java.fakeQuantWithMinMaxArgsGradient(    
        gradients,
        inputs,
        *listOfNotNull(
            min?.let{ org.tensorflow.op.quantization.FakeQuantWithMinMaxArgsGradient.min(it) },
            max?.let{ org.tensorflow.op.quantization.FakeQuantWithMinMaxArgsGradient.max(it) },
            numBits?.let{ org.tensorflow.op.quantization.FakeQuantWithMinMaxArgsGradient.numBits(it) },
            narrowRange?.let{
            org.tensorflow.op.quantization.FakeQuantWithMinMaxArgsGradient.narrowRange(it) }
        ).toTypedArray()
        )

    /**
     * Fake-quantize the 'inputs' tensor of type float via global float scalars
     *  Fake-quantize the `inputs` tensor of type float via global float scalars
     *  `min` and `max` to `outputs` tensor of same shape as `inputs`.
     *  
     * Attributes
     *  <ul>
     *  <li>`&#91;min; max&#93;` define the clamping range for the `inputs` data.</li>
     *  <li>`inputs` values are quantized into the quantization range (
     *  `&#91;0; 2^num_bits - 1&#93;` when `narrow_range` is false and `&#91;1; 2^num_bits - 1&#93;`
     *  when it is true) and then de-quantized and output as floats in `&#91;min; max&#93;`
     *  interval.</li>
     *  <li>`num_bits` is the bitwidth of the quantization; between 2 and 16, inclusive.</li>
     *  </ul>
     *  
     * Before quantization, `min` and `max` values are adjusted with the following
     *  logic.
     *  It is suggested to have `min <= 0 <= max`. If `0` is not in the range of values,
     *  the behavior can be unexpected:
     *  <ul>
     *  <li>If `0 < min < max`: `min_adj = 0` and `max_adj = max - min`.</li>
     *  <li>If `min < max < 0`: `min_adj = min - max` and `max_adj = 0`.</li>
     *  <li>If `min <= 0 <= max`: `scale = (max - min) / (2^num_bits - 1) `,
     *  `min_adj = scale * round(min / scale)` and `max_adj = max + min_adj - min`.</li>
     *  </ul>
     *  
     * This operation has a gradient and thus allows for training `min` and `max`
     *  values.
     *
     * @param inputs the inputs value
     * @param min the min value
     * @param max the max value
     * @param options carries optional attribute values
     * @return a new instance of FakeQuantWithMinMaxVars
     * @see org.tensorflow.op.QuantizationOps.fakeQuantWithMinMaxVars
     * @param numBits Sets the numBits option.
     *
     * @param numBits the numBits option
     * @return this Options instance.
     * @param narrowRange Sets the narrowRange option.
     *
     * @param narrowRange the narrowRange option
     * @return this Options instance.
     */
    public fun fakeQuantWithMinMaxVars(
        inputs: Operand<TFloat32>,
        min: Operand<TFloat32>,
        max: Operand<TFloat32>,
        numBits: Long? = null,
        narrowRange: Boolean? = null
    ): FakeQuantWithMinMaxVars = java.fakeQuantWithMinMaxVars(    
        inputs,
        min,
        max,
        *listOfNotNull(
            numBits?.let{ org.tensorflow.op.quantization.FakeQuantWithMinMaxVars.numBits(it) },
            narrowRange?.let{ org.tensorflow.op.quantization.FakeQuantWithMinMaxVars.narrowRange(it) }
        ).toTypedArray()
        )

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
     * @see org.tensorflow.op.QuantizationOps.fakeQuantWithMinMaxVarsGradient
     * @param numBits Sets the numBits option.
     *
     * @param numBits The bitwidth of the quantization; between 2 and 8, inclusive.
     * @return this Options instance.
     * @param narrowRange Sets the narrowRange option.
     *
     * @param narrowRange Whether to quantize into 2^num_bits - 1 distinct values.
     * @return this Options instance.
     */
    public fun fakeQuantWithMinMaxVarsGradient(
        gradients: Operand<TFloat32>,
        inputs: Operand<TFloat32>,
        min: Operand<TFloat32>,
        max: Operand<TFloat32>,
        numBits: Long? = null,
        narrowRange: Boolean? = null
    ): FakeQuantWithMinMaxVarsGradient = java.fakeQuantWithMinMaxVarsGradient(    
        gradients,
        inputs,
        min,
        max,
        *listOfNotNull(
            numBits?.let{ org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsGradient.numBits(it) },
            narrowRange?.let{
            org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsGradient.narrowRange(it) }
        ).toTypedArray()
        )

    /**
     * Fake-quantize the 'inputs' tensor of type float via per-channel floats
     *  Fake-quantize the `inputs` tensor of type float per-channel and one of the
     *  shapes: `[d]`, `&#91;b, d&#93;` `&#91;b, h, w, d&#93;` via per-channel floats `min` and
     * `max`
     *  of shape `[d]` to `outputs` tensor of same shape as `inputs`.
     *  
     * Attributes
     *  <ul>
     *  <li>`&#91;min; max&#93;` define the clamping range for the `inputs` data.</li>
     *  <li>`inputs` values are quantized into the quantization range (
     *  `&#91;0; 2^num_bits - 1&#93;` when `narrow_range` is false and `&#91;1; 2^num_bits - 1&#93;`
     *  when it is true) and then de-quantized and output as floats in `&#91;min; max&#93;`
     *  interval.</li>
     *  <li>`num_bits` is the bitwidth of the quantization; between 2 and 16, inclusive.</li>
     *  </ul>
     *  
     * Before quantization, `min` and `max` values are adjusted with the following
     *  logic.
     *  It is suggested to have `min <= 0 <= max`. If `0` is not in the range of values,
     *  the behavior can be unexpected:
     *  <ul>
     *  <li>If `0 < min < max`: `min_adj = 0` and `max_adj = max - min`.</li>
     *  <li>If `min < max < 0`: `min_adj = min - max` and `max_adj = 0`.</li>
     *  <li>If `min <= 0 <= max`: `scale = (max - min) / (2^num_bits - 1) `,
     *  `min_adj = scale * round(min / scale)` and `max_adj = max + min_adj - min`.</li>
     *  </ul>
     *  
     * This operation has a gradient and thus allows for training `min` and `max`
     *  values.
     *
     * @param inputs the inputs value
     * @param min the min value
     * @param max the max value
     * @param options carries optional attribute values
     * @return a new instance of FakeQuantWithMinMaxVarsPerChannel
     * @see org.tensorflow.op.QuantizationOps.fakeQuantWithMinMaxVarsPerChannel
     * @param numBits Sets the numBits option.
     *
     * @param numBits the numBits option
     * @return this Options instance.
     * @param narrowRange Sets the narrowRange option.
     *
     * @param narrowRange the narrowRange option
     * @return this Options instance.
     */
    public fun fakeQuantWithMinMaxVarsPerChannel(
        inputs: Operand<TFloat32>,
        min: Operand<TFloat32>,
        max: Operand<TFloat32>,
        numBits: Long? = null,
        narrowRange: Boolean? = null
    ): FakeQuantWithMinMaxVarsPerChannel = java.fakeQuantWithMinMaxVarsPerChannel(    
        inputs,
        min,
        max,
        *listOfNotNull(
            numBits?.let{ org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannel.numBits(it) },
            narrowRange?.let{
            org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannel.narrowRange(it) }
        ).toTypedArray()
        )

    /**
     * Compute gradients for a FakeQuantWithMinMaxVarsPerChannel operation.
     *
     * @param gradients Backpropagated gradients above the FakeQuantWithMinMaxVars operation,
     *  shape one of: `[d]`, `&#91;b, d&#93;`,  `&#91;b, h, w, d&#93;`.
     * @param inputs Values passed as inputs to the FakeQuantWithMinMaxVars operation, shape
     *  same as `gradients`.
     *  min, max: Quantization interval, floats of shape `[d]`.
     * @param min the min value
     * @param max the max value
     * @param options carries optional attribute values
     * @return a new instance of FakeQuantWithMinMaxVarsPerChannelGradient
     * @see org.tensorflow.op.QuantizationOps.fakeQuantWithMinMaxVarsPerChannelGradient
     * @param numBits Sets the numBits option.
     *
     * @param numBits The bitwidth of the quantization; between 2 and 16, inclusive.
     * @return this Options instance.
     * @param narrowRange Sets the narrowRange option.
     *
     * @param narrowRange Whether to quantize into 2^num_bits - 1 distinct values.
     * @return this Options instance.
     */
    public fun fakeQuantWithMinMaxVarsPerChannelGradient(
        gradients: Operand<TFloat32>,
        inputs: Operand<TFloat32>,
        min: Operand<TFloat32>,
        max: Operand<TFloat32>,
        numBits: Long? = null,
        narrowRange: Boolean? = null
    ): FakeQuantWithMinMaxVarsPerChannelGradient = java.fakeQuantWithMinMaxVarsPerChannelGradient(    
        gradients,
        inputs,
        min,
        max,
        *listOfNotNull(
            numBits?.let{
            org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannelGradient.numBits(it) },
            narrowRange?.let{
            org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannelGradient.narrowRange(it)
            }
        ).toTypedArray()
        )

    /**
     * Quantize the 'input' tensor of type float to 'output' tensor of type 'T'.
     *  &#91;min_range, max_range&#93; are scalar floats that specify the range for
     *  the 'input' data. The 'mode' attribute controls exactly which calculations are
     *  used to convert the float values to their quantized equivalents.  The
     *  'round_mode' attribute controls which rounding tie-breaking algorithm is used
     *  when rounding float values to their quantized equivalents.
     *  
     * In 'MIN_COMBINED' mode, each value of the tensor will undergo the following:
     *  ```
     * out[i] = (in[i] - min_range) * range(T) / (max_range - min_range)
     *  if T == qint8: out[i] -= (range(T) + 1) / 2.0
     *  
     * ```
     *  
     * here `range(T) = numeric_limits<T>::max() - numeric_limits<T>::min()`
     *  
     * _MIN_COMBINED Mode Example_
     *  
     * Assume the input is type float and has a possible range of &#91;0.0, 6.0&#93; and the
     *  output type is quint8 (&#91;0, 255&#93;). The min_range and max_range values should be
     *  specified as 0.0 and 6.0. Quantizing from float to quint8 will multiply each
     *  value of the input by 255/6 and cast to quint8.
     *  
     * If the output type was qint8 (&#91;-128, 127&#93;), the operation will additionally
     *  subtract each value by 128 prior to casting, so that the range of values aligns
     *  with the range of qint8.
     *  
     * If the mode is 'MIN_FIRST', then this approach is used:
     *  ```
     * num_discrete_values = 1 << (# of bits in T)
     *  range_adjust = num_discrete_values / (num_discrete_values - 1)
     *  range = (range_max - range_min) * range_adjust
     *  range_scale = num_discrete_values / range
     *  quantized = round(input * range_scale) - round(range_min * range_scale) +
     *    numeric_limits<T>::min()
     *  quantized = max(quantized, numeric_limits<T>::min())
     *  quantized = min(quantized, numeric_limits<T>::max())
     *  
     * ```
     *  
     * The biggest difference between this and MIN_COMBINED is that the minimum range
     *  is rounded first, before it's subtracted from the rounded value. With
     *  MIN_COMBINED, a small bias is introduced where repeated iterations of quantizing
     *  and dequantizing will introduce a larger and larger error.
     *  
     * _SCALED mode Example_
     *  
     * `SCALED` mode matches the quantization approach used in
     *  `QuantizeAndDequantize{V2|V3`}.
     *  
     * If the mode is `SCALED`, the quantization is performed by multiplying each
     *  input value by a scaling_factor.
     *  The scaling_factor is determined from `min_range` and `max_range` to be as large
     *  as possible such that the range from `min_range` to `max_range` is representable
     *  within values of type T.
     *  ```
     * const int min_T = std::numeric_limits<T>::min();
     *    const int max_T = std::numeric_limits<T>::max();
     *    const float max_float = std::numeric_limits<float>::max();
     *
     *    const float scale_factor_from_min_side =
     *        (min_T * min_range > 0) ? min_T / min_range : max_float;
     *    const float scale_factor_from_max_side =
     *        (max_T * max_range > 0) ? max_T / max_range : max_float;
     *
     *    const float scale_factor = std::min(scale_factor_from_min_side,
     *                                        scale_factor_from_max_side);
     *  
     * ```
     *  
     * We next use the scale_factor to adjust min_range and max_range as follows:
     *  ```
     * min_range = min_T / scale_factor;
     *        max_range = max_T / scale_factor;
     *  
     * ```
     *  
     * e.g. if T = qint8, and initially min_range = -10, and max_range = 9, we would
     *  compare -128/-10.0 = 12.8 to 127/9.0 = 14.11, and set scaling_factor = 12.8
     *  In this case, min_range would remain -10, but max_range would be adjusted to
     *  127 / 12.8 = 9.921875
     *  
     * So we will quantize input values in the range (-10, 9.921875) to (-128, 127).
     *  
     * The input tensor can now be quantized by clipping values to the range
     *  `min_range` to `max_range`, then multiplying by scale_factor as follows:
     *  ```
     * result = round(min(max_range, max(min_range, input)) * scale_factor)
     *  
     * ```
     *  
     * The adjusted `min_range` and `max_range` are returned as outputs 2 and 3 of
     *  this operation. These outputs should be used as the range for any further
     *  calculations.
     *  
     * _narrow_range (bool) attribute_
     *  
     * If true, we do not use the minimum quantized value.
     *  i.e. for int8 the quantized output, it would be restricted to the range
     *  -127..127 instead of the full -128..127 range.
     *  This is provided for compatibility with certain inference backends.
     *  (Only applies to SCALED mode)
     *  
     * _axis (int) attribute_
     *  
     * An optional `axis` attribute can specify a dimension index of the input tensor,
     *  such that quantization ranges will be calculated and applied separately for each
     *  slice of the tensor along that dimension. This is useful for per-channel
     *  quantization.
     *  
     * If axis is specified, min_range and max_range
     *  
     * if `axis`=None, per-tensor quantization is performed as normal.
     *  
     * _ensure_minimum_range (float) attribute_
     *  
     * Ensures the minimum quantization range is at least this value.
     *  The legacy default value for this is 0.01, but it is strongly suggested to
     *  set it to 0 for new uses.
     *
     * @param <T> data type for `output` output
     * @param input the input value
     * @param minRange The minimum value of the quantization range. This value may be adjusted by
     * the
     *  op depending on other parameters. The adjusted value is written to `output_min`.
     *  If the `axis` attribute is specified, this must be a 1-D tensor whose size
     *  matches the `axis` dimension of the input and output tensors.
     * @param maxRange The maximum value of the quantization range. This value may be adjusted by
     * the
     *  op depending on other parameters. The adjusted value is written to `output_max`.
     *  If the `axis` attribute is specified, this must be a 1-D tensor whose size
     *  matches the `axis` dimension of the input and output tensors.
     * @param T the value of the T property
     * @param options carries optional attribute values
     * @param <T> data type for `QuantizeV2` output and operands
     * @return a new instance of Quantize
     * @see org.tensorflow.op.QuantizationOps.quantize
     * @param mode Sets the mode option.
     *
     * @param mode the mode option
     * @return this Options instance.
     * @param roundMode Sets the roundMode option.
     *
     * @param roundMode the roundMode option
     * @return this Options instance.
     * @param narrowRange Sets the narrowRange option.
     *
     * @param narrowRange the narrowRange option
     * @return this Options instance.
     * @param axis Sets the axis option.
     *
     * @param axis the axis option
     * @return this Options instance.
     * @param ensureMinimumRange Sets the ensureMinimumRange option.
     *
     * @param ensureMinimumRange the ensureMinimumRange option
     * @return this Options instance.
     */
    public fun <T : TNumber> quantize(
        input: Operand<TFloat32>,
        minRange: Operand<TFloat32>,
        maxRange: Operand<TFloat32>,
        T_: Class<T>,
        mode: String? = null,
        roundMode: String? = null,
        narrowRange: Boolean? = null,
        axis: Long? = null,
        ensureMinimumRange: Float? = null
    ): Quantize<T> = java.quantize<T>(    
        input,
        minRange,
        maxRange,
        T_,
        *listOfNotNull(
            mode?.let{ org.tensorflow.op.quantization.Quantize.mode(it) },
            roundMode?.let{ org.tensorflow.op.quantization.Quantize.roundMode(it) },
            narrowRange?.let{ org.tensorflow.op.quantization.Quantize.narrowRange(it) },
            axis?.let{ org.tensorflow.op.quantization.Quantize.axis(it) },
            ensureMinimumRange?.let{ org.tensorflow.op.quantization.Quantize.ensureMinimumRange(it) }
        ).toTypedArray()
        )

    /**
     * Quantizes then dequantizes a tensor.
     *  This is almost identical to QuantizeAndDequantizeV2, except that num_bits is a
     *  tensor, so its value can change during training.
     *
     * @param <T> data type for `output` output
     * @param input the input value
     * @param inputMin the inputMin value
     * @param inputMax the inputMax value
     * @param numBits the numBits value
     * @param options carries optional attribute values
     * @param <T> data type for `QuantizeAndDequantizeV3` output and operands
     * @return a new instance of QuantizeAndDequantize
     * @see org.tensorflow.op.QuantizationOps.quantizeAndDequantize
     * @param signedInput Sets the signedInput option.
     *
     * @param signedInput the signedInput option
     * @return this Options instance.
     * @param rangeGiven Sets the rangeGiven option.
     *
     * @param rangeGiven the rangeGiven option
     * @return this Options instance.
     * @param narrowRange Sets the narrowRange option.
     *
     * @param narrowRange the narrowRange option
     * @return this Options instance.
     * @param axis Sets the axis option.
     *
     * @param axis the axis option
     * @return this Options instance.
     */
    public fun <T : TNumber> quantizeAndDequantize(
        input: Operand<T>,
        inputMin: Operand<T>,
        inputMax: Operand<T>,
        numBits: Operand<TInt32>,
        signedInput: Boolean? = null,
        rangeGiven: Boolean? = null,
        narrowRange: Boolean? = null,
        axis: Long? = null
    ): QuantizeAndDequantize<T> = java.quantizeAndDequantize<T>(    
        input,
        inputMin,
        inputMax,
        numBits,
        *listOfNotNull(
            signedInput?.let{ org.tensorflow.op.quantization.QuantizeAndDequantize.signedInput(it) },
            rangeGiven?.let{ org.tensorflow.op.quantization.QuantizeAndDequantize.rangeGiven(it) },
            narrowRange?.let{ org.tensorflow.op.quantization.QuantizeAndDequantize.narrowRange(it) },
            axis?.let{ org.tensorflow.op.quantization.QuantizeAndDequantize.axis(it) }
        ).toTypedArray()
        )

    /**
     * Quantizes then dequantizes a tensor.
     *  This is almost identical to QuantizeAndDequantizeV2, except that num_bits is a
     *  tensor, so its value can change during training.
     *
     * @param <T> data type for `output` output
     * @param input the input value
     * @param inputMin the inputMin value
     * @param inputMax the inputMax value
     * @param numBits the numBits value
     * @param options carries optional attribute values
     * @param <T> data type for `QuantizeAndDequantizeV3` output and operands
     * @return a new instance of QuantizeAndDequantizeV3
     * @see org.tensorflow.op.QuantizationOps.quantizeAndDequantizeV3
     * @param signedInput Sets the signedInput option.
     *
     * @param signedInput the signedInput option
     * @return this Options instance.
     * @param rangeGiven Sets the rangeGiven option.
     *
     * @param rangeGiven the rangeGiven option
     * @return this Options instance.
     * @param narrowRange Sets the narrowRange option.
     *
     * @param narrowRange the narrowRange option
     * @return this Options instance.
     * @param axis Sets the axis option.
     *
     * @param axis the axis option
     * @return this Options instance.
     */
    public fun <T : TNumber> quantizeAndDequantizeV3(
        input: Operand<T>,
        inputMin: Operand<T>,
        inputMax: Operand<T>,
        numBits: Operand<TInt32>,
        signedInput: Boolean? = null,
        rangeGiven: Boolean? = null,
        narrowRange: Boolean? = null,
        axis: Long? = null
    ): QuantizeAndDequantizeV3<T> = java.quantizeAndDequantizeV3<T>(    
        input,
        inputMin,
        inputMax,
        numBits,
        *listOfNotNull(
            signedInput?.let{ org.tensorflow.op.quantization.QuantizeAndDequantizeV3.signedInput(it) },
            rangeGiven?.let{ org.tensorflow.op.quantization.QuantizeAndDequantizeV3.rangeGiven(it) },
            narrowRange?.let{ org.tensorflow.op.quantization.QuantizeAndDequantizeV3.narrowRange(it) },
            axis?.let{ org.tensorflow.op.quantization.QuantizeAndDequantizeV3.axis(it) }
        ).toTypedArray()
        )

    /**
     * Returns the gradient of `quantization.QuantizeAndDequantizeV4`.
     *  This is almost identical to QuantizeAndDequantizeV2, except that it returns a
     *  gradient of 1 for inputs that are within the quantization range, or 0 otherwise.
     *
     * @param <T> data type for `output` output
     * @param input the input value
     * @param inputMin the inputMin value
     * @param inputMax the inputMax value
     * @param options carries optional attribute values
     * @param <T> data type for `QuantizeAndDequantizeV4` output and operands
     * @return a new instance of QuantizeAndDequantizeV4
     * @see org.tensorflow.op.QuantizationOps.quantizeAndDequantizeV4
     * @param signedInput Sets the signedInput option.
     *
     * @param signedInput the signedInput option
     * @return this Options instance.
     * @param numBits Sets the numBits option.
     *
     * @param numBits the numBits option
     * @return this Options instance.
     * @param rangeGiven Sets the rangeGiven option.
     *
     * @param rangeGiven the rangeGiven option
     * @return this Options instance.
     * @param roundMode Sets the roundMode option.
     *
     * @param roundMode the roundMode option
     * @return this Options instance.
     * @param narrowRange Sets the narrowRange option.
     *
     * @param narrowRange the narrowRange option
     * @return this Options instance.
     * @param axis Sets the axis option.
     *
     * @param axis the axis option
     * @return this Options instance.
     */
    public fun <T : TNumber> quantizeAndDequantizeV4(
        input: Operand<T>,
        inputMin: Operand<T>,
        inputMax: Operand<T>,
        signedInput: Boolean? = null,
        numBits: Long? = null,
        rangeGiven: Boolean? = null,
        roundMode: String? = null,
        narrowRange: Boolean? = null,
        axis: Long? = null
    ): QuantizeAndDequantizeV4<T> = java.quantizeAndDequantizeV4<T>(    
        input,
        inputMin,
        inputMax,
        *listOfNotNull(
            signedInput?.let{ org.tensorflow.op.quantization.QuantizeAndDequantizeV4.signedInput(it) },
            numBits?.let{ org.tensorflow.op.quantization.QuantizeAndDequantizeV4.numBits(it) },
            rangeGiven?.let{ org.tensorflow.op.quantization.QuantizeAndDequantizeV4.rangeGiven(it) },
            roundMode?.let{ org.tensorflow.op.quantization.QuantizeAndDequantizeV4.roundMode(it) },
            narrowRange?.let{ org.tensorflow.op.quantization.QuantizeAndDequantizeV4.narrowRange(it) },
            axis?.let{ org.tensorflow.op.quantization.QuantizeAndDequantizeV4.axis(it) }
        ).toTypedArray()
        )

    /**
     * Returns the gradient of `QuantizeAndDequantizeV4`.
     *  Returns a gradient of 1 for inputs that are within the quantization range,
     *  or 0 otherwise.
     *
     * @param <T> data type for `input_backprop` output
     * @param gradients the gradients value
     * @param input the input value
     * @param inputMin the inputMin value
     * @param inputMax the inputMax value
     * @param options carries optional attribute values
     * @param <T> data type for `QuantizeAndDequantizeV4Grad` output and operands
     * @return a new instance of QuantizeAndDequantizeV4Grad
     * @see org.tensorflow.op.QuantizationOps.quantizeAndDequantizeV4Grad
     * @param axis Sets the axis option.
     *
     * @param axis the axis option
     * @return this Options instance.
     */
    public fun <T : TNumber> quantizeAndDequantizeV4Grad(
        gradients: Operand<T>,
        input: Operand<T>,
        inputMin: Operand<T>,
        inputMax: Operand<T>,
        axis: Long? = null
    ): QuantizeAndDequantizeV4Grad<T> = java.quantizeAndDequantizeV4Grad<T>(    
        gradients,
        input,
        inputMin,
        inputMax,
        *listOfNotNull(
            axis?.let{ org.tensorflow.op.quantization.QuantizeAndDequantizeV4Grad.axis(it) }
        ).toTypedArray()
        )

    /**
     * Convert the quantized 'input' tensor into a lower-precision 'output', using the
     *  actual distribution of the values to maximize the usage of the lower bit depth
     *  and adjusting the output min and max ranges accordingly.
     *  
     * &#91;input_min, input_max&#93; are scalar floats that specify the range for the float
     *  interpretation of the 'input' data. For example, if input_min is -1.0f and
     *  input_max is 1.0f, and we are dealing with quint16 quantized data, then a 0
     *  value in the 16-bit data should be interpreted as -1.0f, and a 65535 means 1.0f.
     *  
     * This operator tries to squeeze as much precision as possible into an output with
     *  a lower bit depth by calculating the actual min and max values found in the
     *  data. For example, maybe that quint16 input has no values lower than 16,384 and
     *  none higher than 49,152. That means only half the range is actually needed, all
     *  the float interpretations are between -0.5f and 0.5f, so if we want to compress
     *  the data into a quint8 output, we can use that range rather than the theoretical
     *  -1.0f to 1.0f that is suggested by the input min and max.
     *  
     * In practice, this is most useful for taking output from operations like
     *  QuantizedMatMul that can produce higher bit-depth outputs than their inputs and
     *  may have large potential output ranges, but in practice have a distribution of
     *  input values that only uses a small fraction of the possible range. By feeding
     *  that output into this operator, we can reduce it from 32 bits down to 8 with
     *  minimal loss of accuracy.
     *
     * @param <U> data type for `output` output
     * @param input the input value
     * @param inputMin The float value that the minimum quantized input value represents.
     * @param inputMax The float value that the maximum quantized input value represents.
     * @param outType The type of the output. Should be a lower bit depth than Tinput.
     * @param <U> data type for `QuantizeDownAndShrinkRange` output and operands
     * @return a new instance of QuantizeDownAndShrinkRange
     * @see org.tensorflow.op.QuantizationOps.quantizeDownAndShrinkRange
     */
    public fun <U : TNumber> quantizeDownAndShrinkRange(
        input: Operand<out TNumber>,
        inputMin: Operand<TFloat32>,
        inputMax: Operand<TFloat32>,
        outType: Class<U>
    ): QuantizeDownAndShrinkRange<U> = java.quantizeDownAndShrinkRange<U>(    
        input,
        inputMin,
        inputMax,
        outType
        )

    /**
     * Concatenates quantized tensors along one dimension.
     *
     * @param <T> data type for `output` output
     * @param concatDim 0-D.  The dimension along which to concatenate.  Must be in the
     *  range [0, rank(values)).
     * @param values The `N` Tensors to concatenate. Their ranks and types must match,
     *  and their sizes must match in all dimensions except `concat_dim`.
     * @param inputMins The minimum scalar values for each of the input tensors.
     * @param inputMaxes The maximum scalar values for each of the input tensors.
     * @param <T> data type for `QuantizedConcat` output and operands
     * @return a new instance of QuantizedConcat
     * @see org.tensorflow.op.QuantizationOps.quantizedConcat
     */
    public fun <T : TType> quantizedConcat(
        concatDim: Operand<TInt32>,
        values: Iterable<Operand<T>>,
        inputMins: Iterable<Operand<TFloat32>>,
        inputMaxes: Iterable<Operand<TFloat32>>
    ): QuantizedConcat<T> = java.quantizedConcat<T>(    
        concatDim,
        values,
        inputMins,
        inputMaxes
        )

    /**
     * Computes a range that covers the actual values present in a quantized tensor.
     *  Given a quantized tensor described by `(input, input_min, input_max)`, outputs a
     *  range that covers the actual values present in that tensor. This op is typically
     *  used to produce the `requested_output_min` and `requested_output_max` for
     *  `Requantize`.
     *
     * @param input the input value
     * @param inputMin The float value that the minimum quantized input value represents.
     * @param inputMax The float value that the maximum quantized input value represents.
     * @return a new instance of RequantizationRange
     * @see org.tensorflow.op.QuantizationOps.requantizationRange
     */
    public fun requantizationRange(
        input: Operand<out TNumber>,
        inputMin: Operand<TFloat32>,
        inputMax: Operand<TFloat32>
    ): RequantizationRange = java.requantizationRange(    
        input,
        inputMin,
        inputMax
        )

    /**
     * Converts the quantized `input` tensor into a lower-precision `output`.
     *  Converts the quantized `input` tensor into a lower-precision `output`, using the
     *  output range specified with `requested_output_min` and `requested_output_max`.
     *  
     * `&#91;input_min, input_max&#93;` are scalar floats that specify the range for the float
     *  interpretation of the `input` data. For example, if `input_min` is -1.0f and
     *  `input_max` is 1.0f, and we are dealing with `quint16` quantized data, then a 0
     *  value in the 16-bit data should be interpreted as -1.0f, and a 65535 means 1.0f.
     *
     * @param <U> data type for `output` output
     * @param input the input value
     * @param inputMin The float value that the minimum quantized input value represents.
     * @param inputMax The float value that the maximum quantized input value represents.
     * @param requestedOutputMin The float value that the minimum quantized output value represents.
     * @param requestedOutputMax The float value that the maximum quantized output value represents.
     * @param outType The type of the output. Should be a lower bit depth than Tinput.
     * @param <U> data type for `Requantize` output and operands
     * @return a new instance of Requantize
     * @see org.tensorflow.op.QuantizationOps.requantize
     */
    public fun <U : TNumber> requantize(
        input: Operand<out TNumber>,
        inputMin: Operand<TFloat32>,
        inputMax: Operand<TFloat32>,
        requestedOutputMin: Operand<TFloat32>,
        requestedOutputMax: Operand<TFloat32>,
        outType: Class<U>
    ): Requantize<U> = java.requantize<U>(    
        input,
        inputMin,
        inputMax,
        requestedOutputMin,
        requestedOutputMax,
        outType
        )

    /**
     * Dequantize the 'input' tensor into a float or bfloat16 Tensor.
     *  &#91;min_range, max_range&#93; are scalar floats that specify the range for
     *  the output. The 'mode' attribute controls exactly which calculations are
     *  used to convert the float values to their quantized equivalents.
     *  
     * In 'MIN_COMBINED' mode, each value of the tensor will undergo the following:
     *  ```
     * if T == qint8: in[i] += (range(T) + 1)/ 2.0
     *  out[i] = min_range + (in[i]* (max_range - min_range) / range(T))
     *  
     * ```
     *  
     * here `range(T) = numeric_limits<T>::max() - numeric_limits<T>::min()`
     *  
     * _MIN_COMBINED Mode Example_
     *  
     * If the input comes from a QuantizedRelu6, the output type is
     *  quint8 (range of 0-255) but the possible range of QuantizedRelu6 is
     *  0-6.  The min_range and max_range values are therefore 0.0 and 6.0.
     *  Dequantize on quint8 will take each value, cast to float, and multiply
     *  by 6 / 255.
     *  Note that if quantizedtype is qint8, the operation will additionally add
     *  each value by 128 prior to casting.
     *  
     * If the mode is 'MIN_FIRST', then this approach is used:
     *  ```
     * num_discrete_values = 1 << (# of bits in T)
     *  range_adjust = num_discrete_values / (num_discrete_values - 1)
     *  range = (range_max - range_min) * range_adjust
     *  range_scale = range / num_discrete_values
     *  const double offset_input = static_cast<double>(input) - lowest_quantized;
     *  result = range_min + ((input - numeric_limits<T>::min()) * range_scale)
     *  
     * ```
     *  
     * If the mode is `SCALED`, dequantization is performed by multiplying each
     *  input value by a scaling_factor. (Thus an input of 0 always maps to 0.0).
     *  
     * The scaling_factor is determined from `min_range`, `max_range`, and
     *  `narrow_range` in a way that is compatible with `QuantizeAndDequantize{V2|V3`}
     *  and `QuantizeV2`, using the following algorithm:
     *  ```
     * const int min_expected_T = std::numeric_limits<T>::min() +
     *      (narrow_range ? 1 : 0);
     *    const int max_expected_T = std::numeric_limits<T>::max();
     *    const float max_expected_T = std::numeric_limits<float>::max();
     *
     *    const float scale_factor =
     *      (std::numeric_limits<T>::min() == 0) ? (max_range / max_expected_T)
     *                                           : std::max(min_range / min_expected_T,
     *                                                      max_range / max_expected_T);
     *  
     * ```
     *
     * @param <U> data type for `output` output
     * @param input the input value
     * @param minRange The minimum scalar value possibly produced for the input.
     * @param maxRange The maximum scalar value possibly produced for the input.
     * @param dtype Type of the output tensor. Currently Dequantize supports float and bfloat16.
     *  If 'dtype' is 'bfloat16', it only supports 'MIN_COMBINED' mode.
     * @param options carries optional attribute values
     * @param <U> data type for `Dequantize` output and operands
     * @return a new instance of Dequantize
     * @see org.tensorflow.op.QuantizationOps.dequantize
     * @param mode Sets the mode option.
     *
     * @param mode the mode option
     * @return this Options instance.
     * @param narrowRange Sets the narrowRange option.
     *
     * @param narrowRange the narrowRange option
     * @return this Options instance.
     * @param axis Sets the axis option.
     *
     * @param axis the axis option
     * @return this Options instance.
     */
    @JvmName("dequantizeReified")
    public inline fun <reified U : TNumber> dequantize(
        input: Operand<out TNumber>,
        minRange: Operand<TFloat32>,
        maxRange: Operand<TFloat32>,
        mode: String? = null,
        narrowRange: Boolean? = null,
        axis: Long? = null
    ): Dequantize<U> = dequantize<U>(input, minRange, maxRange, U::class.java, mode, narrowRange,
            axis)

    /**
     * Quantize the 'input' tensor of type float to 'output' tensor of type 'T'.
     *  &#91;min_range, max_range&#93; are scalar floats that specify the range for
     *  the 'input' data. The 'mode' attribute controls exactly which calculations are
     *  used to convert the float values to their quantized equivalents.  The
     *  'round_mode' attribute controls which rounding tie-breaking algorithm is used
     *  when rounding float values to their quantized equivalents.
     *  
     * In 'MIN_COMBINED' mode, each value of the tensor will undergo the following:
     *  ```
     * out[i] = (in[i] - min_range) * range(T) / (max_range - min_range)
     *  if T == qint8: out[i] -= (range(T) + 1) / 2.0
     *  
     * ```
     *  
     * here `range(T) = numeric_limits<T>::max() - numeric_limits<T>::min()`
     *  
     * _MIN_COMBINED Mode Example_
     *  
     * Assume the input is type float and has a possible range of &#91;0.0, 6.0&#93; and the
     *  output type is quint8 (&#91;0, 255&#93;). The min_range and max_range values should be
     *  specified as 0.0 and 6.0. Quantizing from float to quint8 will multiply each
     *  value of the input by 255/6 and cast to quint8.
     *  
     * If the output type was qint8 (&#91;-128, 127&#93;), the operation will additionally
     *  subtract each value by 128 prior to casting, so that the range of values aligns
     *  with the range of qint8.
     *  
     * If the mode is 'MIN_FIRST', then this approach is used:
     *  ```
     * num_discrete_values = 1 << (# of bits in T)
     *  range_adjust = num_discrete_values / (num_discrete_values - 1)
     *  range = (range_max - range_min) * range_adjust
     *  range_scale = num_discrete_values / range
     *  quantized = round(input * range_scale) - round(range_min * range_scale) +
     *    numeric_limits<T>::min()
     *  quantized = max(quantized, numeric_limits<T>::min())
     *  quantized = min(quantized, numeric_limits<T>::max())
     *  
     * ```
     *  
     * The biggest difference between this and MIN_COMBINED is that the minimum range
     *  is rounded first, before it's subtracted from the rounded value. With
     *  MIN_COMBINED, a small bias is introduced where repeated iterations of quantizing
     *  and dequantizing will introduce a larger and larger error.
     *  
     * _SCALED mode Example_
     *  
     * `SCALED` mode matches the quantization approach used in
     *  `QuantizeAndDequantize{V2|V3`}.
     *  
     * If the mode is `SCALED`, the quantization is performed by multiplying each
     *  input value by a scaling_factor.
     *  The scaling_factor is determined from `min_range` and `max_range` to be as large
     *  as possible such that the range from `min_range` to `max_range` is representable
     *  within values of type T.
     *  ```
     * const int min_T = std::numeric_limits<T>::min();
     *    const int max_T = std::numeric_limits<T>::max();
     *    const float max_float = std::numeric_limits<float>::max();
     *
     *    const float scale_factor_from_min_side =
     *        (min_T * min_range > 0) ? min_T / min_range : max_float;
     *    const float scale_factor_from_max_side =
     *        (max_T * max_range > 0) ? max_T / max_range : max_float;
     *
     *    const float scale_factor = std::min(scale_factor_from_min_side,
     *                                        scale_factor_from_max_side);
     *  
     * ```
     *  
     * We next use the scale_factor to adjust min_range and max_range as follows:
     *  ```
     * min_range = min_T / scale_factor;
     *        max_range = max_T / scale_factor;
     *  
     * ```
     *  
     * e.g. if T = qint8, and initially min_range = -10, and max_range = 9, we would
     *  compare -128/-10.0 = 12.8 to 127/9.0 = 14.11, and set scaling_factor = 12.8
     *  In this case, min_range would remain -10, but max_range would be adjusted to
     *  127 / 12.8 = 9.921875
     *  
     * So we will quantize input values in the range (-10, 9.921875) to (-128, 127).
     *  
     * The input tensor can now be quantized by clipping values to the range
     *  `min_range` to `max_range`, then multiplying by scale_factor as follows:
     *  ```
     * result = round(min(max_range, max(min_range, input)) * scale_factor)
     *  
     * ```
     *  
     * The adjusted `min_range` and `max_range` are returned as outputs 2 and 3 of
     *  this operation. These outputs should be used as the range for any further
     *  calculations.
     *  
     * _narrow_range (bool) attribute_
     *  
     * If true, we do not use the minimum quantized value.
     *  i.e. for int8 the quantized output, it would be restricted to the range
     *  -127..127 instead of the full -128..127 range.
     *  This is provided for compatibility with certain inference backends.
     *  (Only applies to SCALED mode)
     *  
     * _axis (int) attribute_
     *  
     * An optional `axis` attribute can specify a dimension index of the input tensor,
     *  such that quantization ranges will be calculated and applied separately for each
     *  slice of the tensor along that dimension. This is useful for per-channel
     *  quantization.
     *  
     * If axis is specified, min_range and max_range
     *  
     * if `axis`=None, per-tensor quantization is performed as normal.
     *  
     * _ensure_minimum_range (float) attribute_
     *  
     * Ensures the minimum quantization range is at least this value.
     *  The legacy default value for this is 0.01, but it is strongly suggested to
     *  set it to 0 for new uses.
     *
     * @param <T> data type for `output` output
     * @param input the input value
     * @param minRange The minimum value of the quantization range. This value may be adjusted by
     * the
     *  op depending on other parameters. The adjusted value is written to `output_min`.
     *  If the `axis` attribute is specified, this must be a 1-D tensor whose size
     *  matches the `axis` dimension of the input and output tensors.
     * @param maxRange The maximum value of the quantization range. This value may be adjusted by
     * the
     *  op depending on other parameters. The adjusted value is written to `output_max`.
     *  If the `axis` attribute is specified, this must be a 1-D tensor whose size
     *  matches the `axis` dimension of the input and output tensors.
     * @param T the value of the T property
     * @param options carries optional attribute values
     * @param <T> data type for `QuantizeV2` output and operands
     * @return a new instance of Quantize
     * @see org.tensorflow.op.QuantizationOps.quantize
     * @param mode Sets the mode option.
     *
     * @param mode the mode option
     * @return this Options instance.
     * @param roundMode Sets the roundMode option.
     *
     * @param roundMode the roundMode option
     * @return this Options instance.
     * @param narrowRange Sets the narrowRange option.
     *
     * @param narrowRange the narrowRange option
     * @return this Options instance.
     * @param axis Sets the axis option.
     *
     * @param axis the axis option
     * @return this Options instance.
     * @param ensureMinimumRange Sets the ensureMinimumRange option.
     *
     * @param ensureMinimumRange the ensureMinimumRange option
     * @return this Options instance.
     */
    @JvmName("quantizeReified")
    public inline fun <reified T : TNumber> quantize(
        input: Operand<TFloat32>,
        minRange: Operand<TFloat32>,
        maxRange: Operand<TFloat32>,
        mode: String? = null,
        roundMode: String? = null,
        narrowRange: Boolean? = null,
        axis: Long? = null,
        ensureMinimumRange: Float? = null
    ): Quantize<T> = quantize<T>(input, minRange, maxRange, T::class.java, mode, roundMode,
            narrowRange, axis, ensureMinimumRange)

    /**
     * Convert the quantized 'input' tensor into a lower-precision 'output', using the
     *  actual distribution of the values to maximize the usage of the lower bit depth
     *  and adjusting the output min and max ranges accordingly.
     *  
     * &#91;input_min, input_max&#93; are scalar floats that specify the range for the float
     *  interpretation of the 'input' data. For example, if input_min is -1.0f and
     *  input_max is 1.0f, and we are dealing with quint16 quantized data, then a 0
     *  value in the 16-bit data should be interpreted as -1.0f, and a 65535 means 1.0f.
     *  
     * This operator tries to squeeze as much precision as possible into an output with
     *  a lower bit depth by calculating the actual min and max values found in the
     *  data. For example, maybe that quint16 input has no values lower than 16,384 and
     *  none higher than 49,152. That means only half the range is actually needed, all
     *  the float interpretations are between -0.5f and 0.5f, so if we want to compress
     *  the data into a quint8 output, we can use that range rather than the theoretical
     *  -1.0f to 1.0f that is suggested by the input min and max.
     *  
     * In practice, this is most useful for taking output from operations like
     *  QuantizedMatMul that can produce higher bit-depth outputs than their inputs and
     *  may have large potential output ranges, but in practice have a distribution of
     *  input values that only uses a small fraction of the possible range. By feeding
     *  that output into this operator, we can reduce it from 32 bits down to 8 with
     *  minimal loss of accuracy.
     *
     * @param <U> data type for `output` output
     * @param input the input value
     * @param inputMin The float value that the minimum quantized input value represents.
     * @param inputMax The float value that the maximum quantized input value represents.
     * @param outType The type of the output. Should be a lower bit depth than Tinput.
     * @param <U> data type for `QuantizeDownAndShrinkRange` output and operands
     * @return a new instance of QuantizeDownAndShrinkRange
     * @see org.tensorflow.op.QuantizationOps.quantizeDownAndShrinkRange
     */
    @JvmName("quantizeDownAndShrinkRangeReified")
    public inline fun <reified U : TNumber> quantizeDownAndShrinkRange(
        input: Operand<out TNumber>,
        inputMin: Operand<TFloat32>,
        inputMax: Operand<TFloat32>
    ): QuantizeDownAndShrinkRange<U> = quantizeDownAndShrinkRange<U>(input, inputMin, inputMax,
            U::class.java)

    /**
     * Converts the quantized `input` tensor into a lower-precision `output`.
     *  Converts the quantized `input` tensor into a lower-precision `output`, using the
     *  output range specified with `requested_output_min` and `requested_output_max`.
     *  
     * `&#91;input_min, input_max&#93;` are scalar floats that specify the range for the float
     *  interpretation of the `input` data. For example, if `input_min` is -1.0f and
     *  `input_max` is 1.0f, and we are dealing with `quint16` quantized data, then a 0
     *  value in the 16-bit data should be interpreted as -1.0f, and a 65535 means 1.0f.
     *
     * @param <U> data type for `output` output
     * @param input the input value
     * @param inputMin The float value that the minimum quantized input value represents.
     * @param inputMax The float value that the maximum quantized input value represents.
     * @param requestedOutputMin The float value that the minimum quantized output value represents.
     * @param requestedOutputMax The float value that the maximum quantized output value represents.
     * @param outType The type of the output. Should be a lower bit depth than Tinput.
     * @param <U> data type for `Requantize` output and operands
     * @return a new instance of Requantize
     * @see org.tensorflow.op.QuantizationOps.requantize
     */
    @JvmName("requantizeReified")
    public inline fun <reified U : TNumber> requantize(
        input: Operand<out TNumber>,
        inputMin: Operand<TFloat32>,
        inputMax: Operand<TFloat32>,
        requestedOutputMin: Operand<TFloat32>,
        requestedOutputMax: Operand<TFloat32>
    ): Requantize<U> = requantize<U>(input, inputMin, inputMax, requestedOutputMin,
            requestedOutputMax, U::class.java)
}
