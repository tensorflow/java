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
		mode: String? = null,
		narrowRange: Boolean? = null,
		axis: Long? = null
	): Dequantize<TFloat32> = java.dequantize<T>(	
		input,
		minRange,
		maxRange,
		*listOfNotNull(
			mode?.let{ org.tensorflow.op.quantization.Dequantize.mode(it) },
			narrowRange?.let{ org.tensorflow.op.quantization.Dequantize.narrowRange(it) },
			axis?.let{ org.tensorflow.op.quantization.Dequantize.axis(it) }
		).toTypedArray()
		)

	public fun <U : TNumber, T : TType> dequantize(
		input: Operand<T>,
		minRange: Operand<TFloat32>,
		maxRange: Operand<TFloat32>,
		dtype: DataType<U>,
		mode: String? = null,
		narrowRange: Boolean? = null,
		axis: Long? = null
	): Dequantize<U> = java.dequantize<U, T>(	
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
			narrowRange?.let{ org.tensorflow.op.quantization.FakeQuantWithMinMaxArgsGradient.narrowRange(it)
			}
		).toTypedArray()
		)

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
			narrowRange?.let{ org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsGradient.narrowRange(it)
			}
		).toTypedArray()
		)

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
			org.tensorflow.op.quantization.FakeQuantWithMinMaxVarsPerChannelGradient.narrowRange(it) }
		).toTypedArray()
		)

	public fun <T : TType> quantize(
		input: Operand<TFloat32>,
		minRange: Operand<TFloat32>,
		maxRange: Operand<TFloat32>,
		T_: DataType<T>,
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

	public fun <U : TType, T : TType> quantizeDownAndShrinkRange(
		input: Operand<T>,
		inputMin: Operand<TFloat32>,
		inputMax: Operand<TFloat32>,
		outType: DataType<U>
	): QuantizeDownAndShrinkRange<U> = java.quantizeDownAndShrinkRange<U, T>(	
		input,
		inputMin,
		inputMax,
		outType
		)

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

	public fun <T : TType> requantizationRange(
		input: Operand<T>,
		inputMin: Operand<TFloat32>,
		inputMax: Operand<TFloat32>
	): RequantizationRange = java.requantizationRange<T>(	
		input,
		inputMin,
		inputMax
		)

	public fun <U : TType, T : TType> requantize(
		input: Operand<T>,
		inputMin: Operand<TFloat32>,
		inputMax: Operand<TFloat32>,
		requestedOutputMin: Operand<TFloat32>,
		requestedOutputMax: Operand<TFloat32>,
		outType: DataType<U>
	): Requantize<U> = java.requantize<U, T>(	
		input,
		inputMin,
		inputMax,
		requestedOutputMin,
		requestedOutputMax,
		outType
		)
}
