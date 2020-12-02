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

import org.tensorflow.DataType
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
import org.tensorflow.op.quantization.QuantizeDownAndShrinkRange
import org.tensorflow.op.quantization.QuantizedConcat
import org.tensorflow.op.quantization.RequantizationRange
import org.tensorflow.op.quantization.Requantize
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building {@code quantization} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class QuantizationOps(
  /**
   * Get the parent {@link KotlinOps} object.
   */
  public val ops: KotlinOps
) {
  public val java: org.tensorflow.op.QuantizationOps = ops.java.quantization

  /**
   * Returns the current {@link Scope scope} of this API
   */
  public val scope: Scope = ops.scope

  public fun <T : TType> dequantize(
    input: Operand<T>,
    minRange: Operand<TFloat32>,
    maxRange: Operand<TFloat32>,
    vararg options: Dequantize.Options
  ): Dequantize<TFloat32> = java.dequantize<T>(input, minRange, maxRange, *options)

  public fun <U : TNumber, T : TType> dequantize(
    input: Operand<T>,
    minRange: Operand<TFloat32>,
    maxRange: Operand<TFloat32>,
    dtype: DataType<U>,
    vararg options: Dequantize.Options
  ): Dequantize<U> = java.dequantize<U, T>(input, minRange, maxRange, dtype, *options)

  public fun fakeQuantWithMinMaxArgs(inputs: Operand<TFloat32>, vararg
      options: FakeQuantWithMinMaxArgs.Options): FakeQuantWithMinMaxArgs =
      java.fakeQuantWithMinMaxArgs(inputs, *options)

  public fun fakeQuantWithMinMaxArgsGradient(
    gradients: Operand<TFloat32>,
    inputs: Operand<TFloat32>,
    vararg options: FakeQuantWithMinMaxArgsGradient.Options
  ): FakeQuantWithMinMaxArgsGradient = java.fakeQuantWithMinMaxArgsGradient(gradients, inputs,
      *options)

  public fun fakeQuantWithMinMaxVars(
    inputs: Operand<TFloat32>,
    min: Operand<TFloat32>,
    max: Operand<TFloat32>,
    vararg options: FakeQuantWithMinMaxVars.Options
  ): FakeQuantWithMinMaxVars = java.fakeQuantWithMinMaxVars(inputs, min, max, *options)

  public fun fakeQuantWithMinMaxVarsGradient(
    gradients: Operand<TFloat32>,
    inputs: Operand<TFloat32>,
    min: Operand<TFloat32>,
    max: Operand<TFloat32>,
    vararg options: FakeQuantWithMinMaxVarsGradient.Options
  ): FakeQuantWithMinMaxVarsGradient = java.fakeQuantWithMinMaxVarsGradient(gradients, inputs, min,
      max, *options)

  public fun fakeQuantWithMinMaxVarsPerChannel(
    inputs: Operand<TFloat32>,
    min: Operand<TFloat32>,
    max: Operand<TFloat32>,
    vararg options: FakeQuantWithMinMaxVarsPerChannel.Options
  ): FakeQuantWithMinMaxVarsPerChannel = java.fakeQuantWithMinMaxVarsPerChannel(inputs, min, max,
      *options)

  public fun fakeQuantWithMinMaxVarsPerChannelGradient(
    gradients: Operand<TFloat32>,
    inputs: Operand<TFloat32>,
    min: Operand<TFloat32>,
    max: Operand<TFloat32>,
    vararg options: FakeQuantWithMinMaxVarsPerChannelGradient.Options
  ): FakeQuantWithMinMaxVarsPerChannelGradient =
      java.fakeQuantWithMinMaxVarsPerChannelGradient(gradients, inputs, min, max, *options)

  public fun <T : TType> quantize(
    input: Operand<TFloat32>,
    minRange: Operand<TFloat32>,
    maxRange: Operand<TFloat32>,
    T_: DataType<T>,
    vararg options: Quantize.Options
  ): Quantize<T> = java.quantize<T>(input, minRange, maxRange, T_, *options)

  public fun <T : TNumber> quantizeAndDequantize(
    input: Operand<T>,
    inputMin: Operand<T>,
    inputMax: Operand<T>,
    numBits: Operand<TInt32>,
    vararg options: QuantizeAndDequantize.Options
  ): QuantizeAndDequantize<T> = java.quantizeAndDequantize<T>(input, inputMin, inputMax, numBits,
      *options)

  public fun <U : TType, T : TType> quantizeDownAndShrinkRange(
    input: Operand<T>,
    inputMin: Operand<TFloat32>,
    inputMax: Operand<TFloat32>,
    outType: DataType<U>
  ): QuantizeDownAndShrinkRange<U> = java.quantizeDownAndShrinkRange<U, T>(input, inputMin,
      inputMax, outType)

  public fun <T : TType> quantizedConcat(
    concatDim: Operand<TInt32>,
    values: Iterable<Operand<T>>,
    inputMins: Iterable<Operand<TFloat32>>,
    inputMaxes: Iterable<Operand<TFloat32>>
  ): QuantizedConcat<T> = java.quantizedConcat<T>(concatDim, values, inputMins, inputMaxes)

  public fun <T : TType> requantizationRange(
    input: Operand<T>,
    inputMin: Operand<TFloat32>,
    inputMax: Operand<TFloat32>
  ): RequantizationRange = java.requantizationRange<T>(input, inputMin, inputMax)

  public fun <U : TType, T : TType> requantize(
    input: Operand<T>,
    inputMin: Operand<TFloat32>,
    inputMax: Operand<TFloat32>,
    requestedOutputMin: Operand<TFloat32>,
    requestedOutputMax: Operand<TFloat32>,
    outType: DataType<U>
  ): Requantize<U> = java.requantize<U, T>(input, inputMin, inputMax, requestedOutputMin,
      requestedOutputMax, outType)
}
