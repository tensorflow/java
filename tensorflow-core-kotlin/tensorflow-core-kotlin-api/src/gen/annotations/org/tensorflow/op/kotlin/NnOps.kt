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

import kotlin.Int
import org.tensorflow.DataType
import org.tensorflow.Operand
import org.tensorflow.op.Scope
import org.tensorflow.op.nn.AvgPool
import org.tensorflow.op.nn.AvgPool3d
import org.tensorflow.op.nn.AvgPool3dGrad
import org.tensorflow.op.nn.BatchNormWithGlobalNormalization
import org.tensorflow.op.nn.BatchNormWithGlobalNormalizationGrad
import org.tensorflow.op.nn.BiasAdd
import org.tensorflow.op.nn.BiasAddGrad
import org.tensorflow.op.nn.ComputeAccidentalHits
import org.tensorflow.op.nn.Conv2d
import org.tensorflow.op.nn.Conv2dBackpropFilter
import org.tensorflow.op.nn.Conv2dBackpropInput
import org.tensorflow.op.nn.Conv3d
import org.tensorflow.op.nn.Conv3dBackpropFilter
import org.tensorflow.op.nn.Conv3dBackpropInput
import org.tensorflow.op.nn.CtcBeamSearchDecoder
import org.tensorflow.op.nn.CtcGreedyDecoder
import org.tensorflow.op.nn.CtcLoss
import org.tensorflow.op.nn.CudnnRNNCanonicalToParams
import org.tensorflow.op.nn.CudnnRNNParamsToCanonical
import org.tensorflow.op.nn.CudnnRnnParamsSize
import org.tensorflow.op.nn.DataFormatDimMap
import org.tensorflow.op.nn.DataFormatVecPermute
import org.tensorflow.op.nn.DepthToSpace
import org.tensorflow.op.nn.DepthwiseConv2dNative
import org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropFilter
import org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropInput
import org.tensorflow.op.nn.Dilation2d
import org.tensorflow.op.nn.Dilation2dBackpropFilter
import org.tensorflow.op.nn.Dilation2dBackpropInput
import org.tensorflow.op.nn.Elu
import org.tensorflow.op.nn.FixedUnigramCandidateSampler
import org.tensorflow.op.nn.FractionalAvgPool
import org.tensorflow.op.nn.FractionalMaxPool
import org.tensorflow.op.nn.FusedBatchNorm
import org.tensorflow.op.nn.FusedBatchNormGrad
import org.tensorflow.op.nn.FusedPadConv2d
import org.tensorflow.op.nn.FusedResizeAndPadConv2d
import org.tensorflow.op.nn.InTopK
import org.tensorflow.op.nn.L2Loss
import org.tensorflow.op.nn.LeakyRelu
import org.tensorflow.op.nn.LearnedUnigramCandidateSampler
import org.tensorflow.op.nn.LocalResponseNormalization
import org.tensorflow.op.nn.LogSoftmax
import org.tensorflow.op.nn.MaxPool
import org.tensorflow.op.nn.MaxPool3d
import org.tensorflow.op.nn.MaxPool3dGrad
import org.tensorflow.op.nn.MaxPool3dGradGrad
import org.tensorflow.op.nn.MaxPoolGrad
import org.tensorflow.op.nn.MaxPoolGradGrad
import org.tensorflow.op.nn.MaxPoolGradGradWithArgmax
import org.tensorflow.op.nn.MaxPoolWithArgmax
import org.tensorflow.op.nn.NthElement
import org.tensorflow.op.nn.QuantizedAvgPool
import org.tensorflow.op.nn.QuantizedBatchNormWithGlobalNormalization
import org.tensorflow.op.nn.QuantizedBiasAdd
import org.tensorflow.op.nn.QuantizedConv2d
import org.tensorflow.op.nn.QuantizedInstanceNorm
import org.tensorflow.op.nn.QuantizedMaxPool
import org.tensorflow.op.nn.QuantizedRelu
import org.tensorflow.op.nn.QuantizedRelu6
import org.tensorflow.op.nn.QuantizedReluX
import org.tensorflow.op.nn.Relu
import org.tensorflow.op.nn.Relu6
import org.tensorflow.op.nn.Selu
import org.tensorflow.op.nn.Softmax
import org.tensorflow.op.nn.Softsign
import org.tensorflow.op.nn.SpaceToBatch
import org.tensorflow.op.nn.SpaceToDepth
import org.tensorflow.op.nn.TopK
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building {@code nn} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class NnOps(
	/**
	 * Get the parent {@link KotlinOps} object.
	 */
	public val ops: KotlinOps
) {
	public val java: org.tensorflow.op.NnOps = ops.java.nn

	/**
	 * Returns the current {@link Scope scope} of this API
	 */
	public val scope: Scope = ops.scope

	public val raw: NnRawOps = NnRawOps(ops)

	public fun <T : TNumber> avgPool(
		value: Operand<T>,
		ksize: List<Long>,
		strides: List<Long>,
		padding: String,
		dataFormat: String? = null
	): AvgPool<T> = java.avgPool<T>(	
		value,
		ksize,
		strides,
		padding,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.AvgPool.dataFormat(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> avgPool3d(
		input: Operand<T>,
		ksize: List<Long>,
		strides: List<Long>,
		padding: String,
		dataFormat: String? = null
	): AvgPool3d<T> = java.avgPool3d<T>(	
		input,
		ksize,
		strides,
		padding,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.AvgPool3d.dataFormat(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> avgPool3dGrad(
		origInputShape: Operand<TInt32>,
		grad: Operand<T>,
		ksize: List<Long>,
		strides: List<Long>,
		padding: String,
		dataFormat: String? = null
	): AvgPool3dGrad<T> = java.avgPool3dGrad<T>(	
		origInputShape,
		grad,
		ksize,
		strides,
		padding,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.AvgPool3dGrad.dataFormat(it) }
		).toTypedArray()
		)

	public fun <T : TType> batchNormWithGlobalNormalization(
		t: Operand<T>,
		m: Operand<T>,
		v: Operand<T>,
		beta: Operand<T>,
		gamma: Operand<T>,
		varianceEpsilon: Float,
		scaleAfterNormalization: Boolean
	): BatchNormWithGlobalNormalization<T> = java.batchNormWithGlobalNormalization<T>(	
		t,
		m,
		v,
		beta,
		gamma,
		varianceEpsilon,
		scaleAfterNormalization
		)

	public fun <T : TType> batchNormWithGlobalNormalizationGrad(
		t: Operand<T>,
		m: Operand<T>,
		v: Operand<T>,
		gamma: Operand<T>,
		backprop: Operand<T>,
		varianceEpsilon: Float,
		scaleAfterNormalization: Boolean
	): BatchNormWithGlobalNormalizationGrad<T> = java.batchNormWithGlobalNormalizationGrad<T>(	
		t,
		m,
		v,
		gamma,
		backprop,
		varianceEpsilon,
		scaleAfterNormalization
		)

	public fun <T : TType> biasAdd(
		value: Operand<T>,
		bias: Operand<T>,
		dataFormat: String? = null
	): BiasAdd<T> = java.biasAdd<T>(	
		value,
		bias,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.BiasAdd.dataFormat(it) }
		).toTypedArray()
		)

	public fun <T : TType> biasAddGrad(outBackprop: Operand<T>, dataFormat: String? = null):
			BiasAddGrad<T> = java.biasAddGrad<T>(	
		outBackprop,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.BiasAddGrad.dataFormat(it) }
		).toTypedArray()
		)

	public fun computeAccidentalHits(
		trueClasses: Operand<TInt64>,
		sampledCandidates: Operand<TInt64>,
		numTrue: Long,
		seed: Long? = null,
		seed2: Long? = null
	): ComputeAccidentalHits = java.computeAccidentalHits(	
		trueClasses,
		sampledCandidates,
		numTrue,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.nn.ComputeAccidentalHits.seed(it) },
			seed2?.let{ org.tensorflow.op.nn.ComputeAccidentalHits.seed2(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> conv2d(
		input: Operand<T>,
		filter: Operand<T>,
		strides: List<Long>,
		padding: String,
		useCudnnOnGpu: Boolean? = null,
		explicitPaddings: List<Long>? = null,
		dataFormat: String? = null,
		dilations: List<Long>? = null
	): Conv2d<T> = java.conv2d<T>(	
		input,
		filter,
		strides,
		padding,
		*listOfNotNull(
			useCudnnOnGpu?.let{ org.tensorflow.op.nn.Conv2d.useCudnnOnGpu(it) },
			explicitPaddings?.let{ org.tensorflow.op.nn.Conv2d.explicitPaddings(it) },
			dataFormat?.let{ org.tensorflow.op.nn.Conv2d.dataFormat(it) },
			dilations?.let{ org.tensorflow.op.nn.Conv2d.dilations(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> conv2dBackpropFilter(
		input: Operand<T>,
		filterSizes: Operand<TInt32>,
		outBackprop: Operand<T>,
		strides: List<Long>,
		padding: String,
		useCudnnOnGpu: Boolean? = null,
		explicitPaddings: List<Long>? = null,
		dataFormat: String? = null,
		dilations: List<Long>? = null
	): Conv2dBackpropFilter<T> = java.conv2dBackpropFilter<T>(	
		input,
		filterSizes,
		outBackprop,
		strides,
		padding,
		*listOfNotNull(
			useCudnnOnGpu?.let{ org.tensorflow.op.nn.Conv2dBackpropFilter.useCudnnOnGpu(it) },
			explicitPaddings?.let{ org.tensorflow.op.nn.Conv2dBackpropFilter.explicitPaddings(it) },
			dataFormat?.let{ org.tensorflow.op.nn.Conv2dBackpropFilter.dataFormat(it) },
			dilations?.let{ org.tensorflow.op.nn.Conv2dBackpropFilter.dilations(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> conv2dBackpropInput(
		inputSizes: Operand<TInt32>,
		filter: Operand<T>,
		outBackprop: Operand<T>,
		strides: List<Long>,
		padding: String,
		useCudnnOnGpu: Boolean? = null,
		explicitPaddings: List<Long>? = null,
		dataFormat: String? = null,
		dilations: List<Long>? = null
	): Conv2dBackpropInput<T> = java.conv2dBackpropInput<T>(	
		inputSizes,
		filter,
		outBackprop,
		strides,
		padding,
		*listOfNotNull(
			useCudnnOnGpu?.let{ org.tensorflow.op.nn.Conv2dBackpropInput.useCudnnOnGpu(it) },
			explicitPaddings?.let{ org.tensorflow.op.nn.Conv2dBackpropInput.explicitPaddings(it) },
			dataFormat?.let{ org.tensorflow.op.nn.Conv2dBackpropInput.dataFormat(it) },
			dilations?.let{ org.tensorflow.op.nn.Conv2dBackpropInput.dilations(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> conv3d(
		input: Operand<T>,
		filter: Operand<T>,
		strides: List<Long>,
		padding: String,
		dataFormat: String? = null,
		dilations: List<Long>? = null
	): Conv3d<T> = java.conv3d<T>(	
		input,
		filter,
		strides,
		padding,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.Conv3d.dataFormat(it) },
			dilations?.let{ org.tensorflow.op.nn.Conv3d.dilations(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> conv3dBackpropFilter(
		input: Operand<T>,
		filterSizes: Operand<TInt32>,
		outBackprop: Operand<T>,
		strides: List<Long>,
		padding: String,
		dataFormat: String? = null,
		dilations: List<Long>? = null
	): Conv3dBackpropFilter<T> = java.conv3dBackpropFilter<T>(	
		input,
		filterSizes,
		outBackprop,
		strides,
		padding,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.Conv3dBackpropFilter.dataFormat(it) },
			dilations?.let{ org.tensorflow.op.nn.Conv3dBackpropFilter.dilations(it) }
		).toTypedArray()
		)

	public fun <U : TNumber, T : TNumber> conv3dBackpropInput(
		inputSizes: Operand<T>,
		filter: Operand<U>,
		outBackprop: Operand<U>,
		strides: List<Long>,
		padding: String,
		dataFormat: String? = null,
		dilations: List<Long>? = null
	): Conv3dBackpropInput<U> = java.conv3dBackpropInput<U, T>(	
		inputSizes,
		filter,
		outBackprop,
		strides,
		padding,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.Conv3dBackpropInput.dataFormat(it) },
			dilations?.let{ org.tensorflow.op.nn.Conv3dBackpropInput.dilations(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> ctcBeamSearchDecoder(
		inputs: Operand<T>,
		sequenceLength: Operand<TInt32>,
		beamWidth: Long,
		topPaths: Long,
		mergeRepeated: Boolean? = null
	): CtcBeamSearchDecoder<T> = java.ctcBeamSearchDecoder<T>(	
		inputs,
		sequenceLength,
		beamWidth,
		topPaths,
		*listOfNotNull(
			mergeRepeated?.let{ org.tensorflow.op.nn.CtcBeamSearchDecoder.mergeRepeated(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> ctcGreedyDecoder(
		inputs: Operand<T>,
		sequenceLength: Operand<TInt32>,
		mergeRepeated: Boolean? = null
	): CtcGreedyDecoder<T> = java.ctcGreedyDecoder<T>(	
		inputs,
		sequenceLength,
		*listOfNotNull(
			mergeRepeated?.let{ org.tensorflow.op.nn.CtcGreedyDecoder.mergeRepeated(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> ctcLoss(
		inputs: Operand<T>,
		labelsIndices: Operand<TInt64>,
		labelsValues: Operand<TInt32>,
		sequenceLength: Operand<TInt32>,
		preprocessCollapseRepeated: Boolean? = null,
		ctcMergeRepeated: Boolean? = null,
		ignoreLongerOutputsThanInputs: Boolean? = null
	): CtcLoss<T> = java.ctcLoss<T>(	
		inputs,
		labelsIndices,
		labelsValues,
		sequenceLength,
		*listOfNotNull(
			preprocessCollapseRepeated?.let{ org.tensorflow.op.nn.CtcLoss.preprocessCollapseRepeated(it) },
			ctcMergeRepeated?.let{ org.tensorflow.op.nn.CtcLoss.ctcMergeRepeated(it) },
			ignoreLongerOutputsThanInputs?.let{
			org.tensorflow.op.nn.CtcLoss.ignoreLongerOutputsThanInputs(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> cudnnRNNCanonicalToParams(
		numLayers: Operand<TInt32>,
		numUnits: Operand<TInt32>,
		inputSize: Operand<TInt32>,
		weights: Iterable<Operand<T>>,
		biases: Iterable<Operand<T>>,
		rnnMode: String? = null,
		inputMode: String? = null,
		direction: String? = null,
		dropout: Float? = null,
		seed: Long? = null,
		seed2: Long? = null,
		numProj: Long? = null
	): CudnnRNNCanonicalToParams<T> = java.cudnnRNNCanonicalToParams<T>(	
		numLayers,
		numUnits,
		inputSize,
		weights,
		biases,
		*listOfNotNull(
			rnnMode?.let{ org.tensorflow.op.nn.CudnnRNNCanonicalToParams.rnnMode(it) },
			inputMode?.let{ org.tensorflow.op.nn.CudnnRNNCanonicalToParams.inputMode(it) },
			direction?.let{ org.tensorflow.op.nn.CudnnRNNCanonicalToParams.direction(it) },
			dropout?.let{ org.tensorflow.op.nn.CudnnRNNCanonicalToParams.dropout(it) },
			seed?.let{ org.tensorflow.op.nn.CudnnRNNCanonicalToParams.seed(it) },
			seed2?.let{ org.tensorflow.op.nn.CudnnRNNCanonicalToParams.seed2(it) },
			numProj?.let{ org.tensorflow.op.nn.CudnnRNNCanonicalToParams.numProj(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> cudnnRNNParamsToCanonical(
		numLayers: Operand<TInt32>,
		numUnits: Operand<TInt32>,
		inputSize: Operand<TInt32>,
		params: Operand<T>,
		numParamsWeights: Long,
		numParamsBiases: Long,
		rnnMode: String? = null,
		inputMode: String? = null,
		direction: String? = null,
		dropout: Float? = null,
		seed: Long? = null,
		seed2: Long? = null,
		numProj: Long? = null
	): CudnnRNNParamsToCanonical<T> = java.cudnnRNNParamsToCanonical<T>(	
		numLayers,
		numUnits,
		inputSize,
		params,
		numParamsWeights,
		numParamsBiases,
		*listOfNotNull(
			rnnMode?.let{ org.tensorflow.op.nn.CudnnRNNParamsToCanonical.rnnMode(it) },
			inputMode?.let{ org.tensorflow.op.nn.CudnnRNNParamsToCanonical.inputMode(it) },
			direction?.let{ org.tensorflow.op.nn.CudnnRNNParamsToCanonical.direction(it) },
			dropout?.let{ org.tensorflow.op.nn.CudnnRNNParamsToCanonical.dropout(it) },
			seed?.let{ org.tensorflow.op.nn.CudnnRNNParamsToCanonical.seed(it) },
			seed2?.let{ org.tensorflow.op.nn.CudnnRNNParamsToCanonical.seed2(it) },
			numProj?.let{ org.tensorflow.op.nn.CudnnRNNParamsToCanonical.numProj(it) }
		).toTypedArray()
		)

	public fun <U : TNumber, T : TNumber> cudnnRnnParamsSize(
		numLayers: Operand<TInt32>,
		numUnits: Operand<TInt32>,
		inputSize: Operand<TInt32>,
		T_: DataType<T>,
		S: DataType<U>,
		rnnMode: String? = null,
		inputMode: String? = null,
		direction: String? = null,
		dropout: Float? = null,
		seed: Long? = null,
		seed2: Long? = null,
		numProj: Long? = null
	): CudnnRnnParamsSize<U> = java.cudnnRnnParamsSize<U, T>(	
		numLayers,
		numUnits,
		inputSize,
		T_,
		S,
		*listOfNotNull(
			rnnMode?.let{ org.tensorflow.op.nn.CudnnRnnParamsSize.rnnMode(it) },
			inputMode?.let{ org.tensorflow.op.nn.CudnnRnnParamsSize.inputMode(it) },
			direction?.let{ org.tensorflow.op.nn.CudnnRnnParamsSize.direction(it) },
			dropout?.let{ org.tensorflow.op.nn.CudnnRnnParamsSize.dropout(it) },
			seed?.let{ org.tensorflow.op.nn.CudnnRnnParamsSize.seed(it) },
			seed2?.let{ org.tensorflow.op.nn.CudnnRnnParamsSize.seed2(it) },
			numProj?.let{ org.tensorflow.op.nn.CudnnRnnParamsSize.numProj(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> dataFormatDimMap(
		x: Operand<T>,
		srcFormat: String? = null,
		dstFormat: String? = null
	): DataFormatDimMap<T> = java.dataFormatDimMap<T>(	
		x,
		*listOfNotNull(
			srcFormat?.let{ org.tensorflow.op.nn.DataFormatDimMap.srcFormat(it) },
			dstFormat?.let{ org.tensorflow.op.nn.DataFormatDimMap.dstFormat(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> dataFormatVecPermute(
		x: Operand<T>,
		srcFormat: String? = null,
		dstFormat: String? = null
	): DataFormatVecPermute<T> = java.dataFormatVecPermute<T>(	
		x,
		*listOfNotNull(
			srcFormat?.let{ org.tensorflow.op.nn.DataFormatVecPermute.srcFormat(it) },
			dstFormat?.let{ org.tensorflow.op.nn.DataFormatVecPermute.dstFormat(it) }
		).toTypedArray()
		)

	public fun <T : TType> depthToSpace(
		input: Operand<T>,
		blockSize: Long,
		dataFormat: String? = null
	): DepthToSpace<T> = java.depthToSpace<T>(	
		input,
		blockSize,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.DepthToSpace.dataFormat(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> depthwiseConv2dNative(
		input: Operand<T>,
		filter: Operand<T>,
		strides: List<Long>,
		padding: String,
		explicitPaddings: List<Long>? = null,
		dataFormat: String? = null,
		dilations: List<Long>? = null
	): DepthwiseConv2dNative<T> = java.depthwiseConv2dNative<T>(	
		input,
		filter,
		strides,
		padding,
		*listOfNotNull(
			explicitPaddings?.let{ org.tensorflow.op.nn.DepthwiseConv2dNative.explicitPaddings(it) },
			dataFormat?.let{ org.tensorflow.op.nn.DepthwiseConv2dNative.dataFormat(it) },
			dilations?.let{ org.tensorflow.op.nn.DepthwiseConv2dNative.dilations(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> depthwiseConv2dNativeBackpropFilter(
		input: Operand<T>,
		filterSizes: Operand<TInt32>,
		outBackprop: Operand<T>,
		strides: List<Long>,
		padding: String,
		explicitPaddings: List<Long>? = null,
		dataFormat: String? = null,
		dilations: List<Long>? = null
	): DepthwiseConv2dNativeBackpropFilter<T> = java.depthwiseConv2dNativeBackpropFilter<T>(	
		input,
		filterSizes,
		outBackprop,
		strides,
		padding,
		*listOfNotNull(
			explicitPaddings?.let{
			org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropFilter.explicitPaddings(it) },
			dataFormat?.let{ org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropFilter.dataFormat(it) },
			dilations?.let{ org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropFilter.dilations(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> depthwiseConv2dNativeBackpropInput(
		inputSizes: Operand<TInt32>,
		filter: Operand<T>,
		outBackprop: Operand<T>,
		strides: List<Long>,
		padding: String,
		explicitPaddings: List<Long>? = null,
		dataFormat: String? = null,
		dilations: List<Long>? = null
	): DepthwiseConv2dNativeBackpropInput<T> = java.depthwiseConv2dNativeBackpropInput<T>(	
		inputSizes,
		filter,
		outBackprop,
		strides,
		padding,
		*listOfNotNull(
			explicitPaddings?.let{
			org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropInput.explicitPaddings(it) },
			dataFormat?.let{ org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropInput.dataFormat(it) },
			dilations?.let{ org.tensorflow.op.nn.DepthwiseConv2dNativeBackpropInput.dilations(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> dilation2d(
		input: Operand<T>,
		filter: Operand<T>,
		strides: List<Long>,
		rates: List<Long>,
		padding: String
	): Dilation2d<T> = java.dilation2d<T>(	
		input,
		filter,
		strides,
		rates,
		padding
		)

	public fun <T : TNumber> dilation2dBackpropFilter(
		input: Operand<T>,
		filter: Operand<T>,
		outBackprop: Operand<T>,
		strides: List<Long>,
		rates: List<Long>,
		padding: String
	): Dilation2dBackpropFilter<T> = java.dilation2dBackpropFilter<T>(	
		input,
		filter,
		outBackprop,
		strides,
		rates,
		padding
		)

	public fun <T : TNumber> dilation2dBackpropInput(
		input: Operand<T>,
		filter: Operand<T>,
		outBackprop: Operand<T>,
		strides: List<Long>,
		rates: List<Long>,
		padding: String
	): Dilation2dBackpropInput<T> = java.dilation2dBackpropInput<T>(	
		input,
		filter,
		outBackprop,
		strides,
		rates,
		padding
		)

	public fun <T : TNumber> elu(features: Operand<T>): Elu<T> = java.elu<T>(	
		features
		)

	public fun fixedUnigramCandidateSampler(
		trueClasses: Operand<TInt64>,
		numTrue: Long,
		numSampled: Long,
		unique: Boolean,
		rangeMax: Long,
		vocabFile: String? = null,
		distortion: Float? = null,
		numReservedIds: Long? = null,
		numShards: Long? = null,
		shard: Long? = null,
		unigrams: List<Float>? = null,
		seed: Long? = null,
		seed2: Long? = null
	): FixedUnigramCandidateSampler = java.fixedUnigramCandidateSampler(	
		trueClasses,
		numTrue,
		numSampled,
		unique,
		rangeMax,
		*listOfNotNull(
			vocabFile?.let{ org.tensorflow.op.nn.FixedUnigramCandidateSampler.vocabFile(it) },
			distortion?.let{ org.tensorflow.op.nn.FixedUnigramCandidateSampler.distortion(it) },
			numReservedIds?.let{ org.tensorflow.op.nn.FixedUnigramCandidateSampler.numReservedIds(it) },
			numShards?.let{ org.tensorflow.op.nn.FixedUnigramCandidateSampler.numShards(it) },
			shard?.let{ org.tensorflow.op.nn.FixedUnigramCandidateSampler.shard(it) },
			unigrams?.let{ org.tensorflow.op.nn.FixedUnigramCandidateSampler.unigrams(it) },
			seed?.let{ org.tensorflow.op.nn.FixedUnigramCandidateSampler.seed(it) },
			seed2?.let{ org.tensorflow.op.nn.FixedUnigramCandidateSampler.seed2(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> fractionalAvgPool(
		value: Operand<T>,
		poolingRatio: List<Float>,
		pseudoRandom: Boolean? = null,
		overlapping: Boolean? = null,
		deterministic: Boolean? = null,
		seed: Long? = null,
		seed2: Long? = null
	): FractionalAvgPool<T> = java.fractionalAvgPool<T>(	
		value,
		poolingRatio,
		*listOfNotNull(
			pseudoRandom?.let{ org.tensorflow.op.nn.FractionalAvgPool.pseudoRandom(it) },
			overlapping?.let{ org.tensorflow.op.nn.FractionalAvgPool.overlapping(it) },
			deterministic?.let{ org.tensorflow.op.nn.FractionalAvgPool.deterministic(it) },
			seed?.let{ org.tensorflow.op.nn.FractionalAvgPool.seed(it) },
			seed2?.let{ org.tensorflow.op.nn.FractionalAvgPool.seed2(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> fractionalMaxPool(
		value: Operand<T>,
		poolingRatio: List<Float>,
		pseudoRandom: Boolean? = null,
		overlapping: Boolean? = null,
		deterministic: Boolean? = null,
		seed: Long? = null,
		seed2: Long? = null
	): FractionalMaxPool<T> = java.fractionalMaxPool<T>(	
		value,
		poolingRatio,
		*listOfNotNull(
			pseudoRandom?.let{ org.tensorflow.op.nn.FractionalMaxPool.pseudoRandom(it) },
			overlapping?.let{ org.tensorflow.op.nn.FractionalMaxPool.overlapping(it) },
			deterministic?.let{ org.tensorflow.op.nn.FractionalMaxPool.deterministic(it) },
			seed?.let{ org.tensorflow.op.nn.FractionalMaxPool.seed(it) },
			seed2?.let{ org.tensorflow.op.nn.FractionalMaxPool.seed2(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TNumber> fusedBatchNorm(
		x: Operand<T>,
		scale: Operand<U>,
		offset: Operand<U>,
		mean: Operand<U>,
		variance: Operand<U>,
		epsilon: Float? = null,
		exponentialAvgFactor: Float? = null,
		dataFormat: String? = null,
		isTraining: Boolean? = null
	): FusedBatchNorm<T, U> = java.fusedBatchNorm<T, U>(	
		x,
		scale,
		offset,
		mean,
		variance,
		*listOfNotNull(
			epsilon?.let{ org.tensorflow.op.nn.FusedBatchNorm.epsilon(it) },
			exponentialAvgFactor?.let{ org.tensorflow.op.nn.FusedBatchNorm.exponentialAvgFactor(it) },
			dataFormat?.let{ org.tensorflow.op.nn.FusedBatchNorm.dataFormat(it) },
			isTraining?.let{ org.tensorflow.op.nn.FusedBatchNorm.isTraining(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TNumber> fusedBatchNormGrad(
		yBackprop: Operand<T>,
		x: Operand<T>,
		scale: Operand<TFloat32>,
		reserveSpace1: Operand<U>,
		reserveSpace2: Operand<U>,
		reserveSpace3: Operand<U>,
		epsilon: Float? = null,
		dataFormat: String? = null,
		isTraining: Boolean? = null
	): FusedBatchNormGrad<T, U> = java.fusedBatchNormGrad<T, U>(	
		yBackprop,
		x,
		scale,
		reserveSpace1,
		reserveSpace2,
		reserveSpace3,
		*listOfNotNull(
			epsilon?.let{ org.tensorflow.op.nn.FusedBatchNormGrad.epsilon(it) },
			dataFormat?.let{ org.tensorflow.op.nn.FusedBatchNormGrad.dataFormat(it) },
			isTraining?.let{ org.tensorflow.op.nn.FusedBatchNormGrad.isTraining(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> fusedPadConv2d(
		input: Operand<T>,
		paddings: Operand<TInt32>,
		filter: Operand<T>,
		mode: String,
		strides: List<Long>,
		padding: String
	): FusedPadConv2d<T> = java.fusedPadConv2d<T>(	
		input,
		paddings,
		filter,
		mode,
		strides,
		padding
		)

	public fun <T : TNumber> fusedResizeAndPadConv2d(
		input: Operand<T>,
		size: Operand<TInt32>,
		paddings: Operand<TInt32>,
		filter: Operand<T>,
		mode: String,
		strides: List<Long>,
		padding: String,
		resizeAlignCorners: Boolean? = null
	): FusedResizeAndPadConv2d<T> = java.fusedResizeAndPadConv2d<T>(	
		input,
		size,
		paddings,
		filter,
		mode,
		strides,
		padding,
		*listOfNotNull(
			resizeAlignCorners?.let{ org.tensorflow.op.nn.FusedResizeAndPadConv2d.resizeAlignCorners(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> inTopK(
		predictions: Operand<TFloat32>,
		targets: Operand<T>,
		k: Operand<T>
	): InTopK = java.inTopK<T>(	
		predictions,
		targets,
		k
		)

	public fun <T : TNumber> l2Loss(t: Operand<T>): L2Loss<T> = java.l2Loss<T>(	
		t
		)

	public fun <T : TNumber> leakyRelu(features: Operand<T>, alpha: Float? = null): LeakyRelu<T> =
			java.leakyRelu<T>(	
		features,
		*listOfNotNull(
			alpha?.let{ org.tensorflow.op.nn.LeakyRelu.alpha(it) }
		).toTypedArray()
		)

	public fun learnedUnigramCandidateSampler(
		trueClasses: Operand<TInt64>,
		numTrue: Long,
		numSampled: Long,
		unique: Boolean,
		rangeMax: Long,
		seed: Long? = null,
		seed2: Long? = null
	): LearnedUnigramCandidateSampler = java.learnedUnigramCandidateSampler(	
		trueClasses,
		numTrue,
		numSampled,
		unique,
		rangeMax,
		*listOfNotNull(
			seed?.let{ org.tensorflow.op.nn.LearnedUnigramCandidateSampler.seed(it) },
			seed2?.let{ org.tensorflow.op.nn.LearnedUnigramCandidateSampler.seed2(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> localResponseNormalization(
		input: Operand<T>,
		depthRadius: Long? = null,
		bias: Float? = null,
		alpha: Float? = null,
		beta: Float? = null
	): LocalResponseNormalization<T> = java.localResponseNormalization<T>(	
		input,
		*listOfNotNull(
			depthRadius?.let{ org.tensorflow.op.nn.LocalResponseNormalization.depthRadius(it) },
			bias?.let{ org.tensorflow.op.nn.LocalResponseNormalization.bias(it) },
			alpha?.let{ org.tensorflow.op.nn.LocalResponseNormalization.alpha(it) },
			beta?.let{ org.tensorflow.op.nn.LocalResponseNormalization.beta(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> logSoftmax(logits: Operand<T>): LogSoftmax<T> = java.logSoftmax<T>(	
		logits
		)

	public fun <T : TType> maxPool(
		input: Operand<T>,
		ksize: Operand<TInt32>,
		strides: Operand<TInt32>,
		padding: String,
		dataFormat: String? = null
	): MaxPool<T> = java.maxPool<T>(	
		input,
		ksize,
		strides,
		padding,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.MaxPool.dataFormat(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> maxPool3d(
		input: Operand<T>,
		ksize: List<Long>,
		strides: List<Long>,
		padding: String,
		dataFormat: String? = null
	): MaxPool3d<T> = java.maxPool3d<T>(	
		input,
		ksize,
		strides,
		padding,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.MaxPool3d.dataFormat(it) }
		).toTypedArray()
		)

	public fun <U : TNumber, T : TNumber> maxPool3dGrad(
		origInput: Operand<T>,
		origOutput: Operand<T>,
		grad: Operand<U>,
		ksize: List<Long>,
		strides: List<Long>,
		padding: String,
		dataFormat: String? = null
	): MaxPool3dGrad<U> = java.maxPool3dGrad<U, T>(	
		origInput,
		origOutput,
		grad,
		ksize,
		strides,
		padding,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.MaxPool3dGrad.dataFormat(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> maxPool3dGradGrad(
		origInput: Operand<T>,
		origOutput: Operand<T>,
		grad: Operand<T>,
		ksize: List<Long>,
		strides: List<Long>,
		padding: String,
		dataFormat: String? = null
	): MaxPool3dGradGrad<T> = java.maxPool3dGradGrad<T>(	
		origInput,
		origOutput,
		grad,
		ksize,
		strides,
		padding,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.MaxPool3dGradGrad.dataFormat(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> maxPoolGrad(
		origInput: Operand<T>,
		origOutput: Operand<T>,
		grad: Operand<T>,
		ksize: Operand<TInt32>,
		strides: Operand<TInt32>,
		padding: String,
		dataFormat: String? = null
	): MaxPoolGrad<T> = java.maxPoolGrad<T>(	
		origInput,
		origOutput,
		grad,
		ksize,
		strides,
		padding,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.MaxPoolGrad.dataFormat(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> maxPoolGradGrad(
		origInput: Operand<T>,
		origOutput: Operand<T>,
		grad: Operand<T>,
		ksize: Operand<TInt32>,
		strides: Operand<TInt32>,
		padding: String,
		dataFormat: String? = null
	): MaxPoolGradGrad<T> = java.maxPoolGradGrad<T>(	
		origInput,
		origOutput,
		grad,
		ksize,
		strides,
		padding,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.MaxPoolGradGrad.dataFormat(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TNumber> maxPoolGradGradWithArgmax(
		input: Operand<T>,
		grad: Operand<T>,
		argmax: Operand<U>,
		ksize: List<Long>,
		strides: List<Long>,
		padding: String,
		includeBatchInIndex: Boolean? = null
	): MaxPoolGradGradWithArgmax<T> = java.maxPoolGradGradWithArgmax<T, U>(	
		input,
		grad,
		argmax,
		ksize,
		strides,
		padding,
		*listOfNotNull(
			includeBatchInIndex?.let{ org.tensorflow.op.nn.MaxPoolGradGradWithArgmax.includeBatchInIndex(it)
			}
		).toTypedArray()
		)

	public fun <T : TNumber> maxPoolWithArgmax(
		input: Operand<T>,
		ksize: List<Long>,
		strides: List<Long>,
		padding: String,
		includeBatchInIndex: Boolean? = null
	): MaxPoolWithArgmax<T, TInt64> = java.maxPoolWithArgmax<T>(	
		input,
		ksize,
		strides,
		padding,
		*listOfNotNull(
			includeBatchInIndex?.let{ org.tensorflow.op.nn.MaxPoolWithArgmax.includeBatchInIndex(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TNumber> maxPoolWithArgmax(
		input: Operand<T>,
		ksize: List<Long>,
		strides: List<Long>,
		Targmax: DataType<U>,
		padding: String,
		includeBatchInIndex: Boolean? = null
	): MaxPoolWithArgmax<T, U> = java.maxPoolWithArgmax<T, U>(	
		input,
		ksize,
		strides,
		Targmax,
		padding,
		*listOfNotNull(
			includeBatchInIndex?.let{ org.tensorflow.op.nn.MaxPoolWithArgmax.includeBatchInIndex(it) }
		).toTypedArray()
		)

	public fun <T : TNumber> nthElement(
		input: Operand<T>,
		n: Operand<TInt32>,
		reverse: Boolean? = null
	): NthElement<T> = java.nthElement<T>(	
		input,
		n,
		*listOfNotNull(
			reverse?.let{ org.tensorflow.op.nn.NthElement.reverse(it) }
		).toTypedArray()
		)

	public fun <T : TType> quantizedAvgPool(
		input: Operand<T>,
		minInput: Operand<TFloat32>,
		maxInput: Operand<TFloat32>,
		ksize: List<Long>,
		strides: List<Long>,
		padding: String
	): QuantizedAvgPool<T> = java.quantizedAvgPool<T>(	
		input,
		minInput,
		maxInput,
		ksize,
		strides,
		padding
		)

	public fun <U : TType, T : TType> quantizedBatchNormWithGlobalNormalization(
		t: Operand<T>,
		tMin: Operand<TFloat32>,
		tMax: Operand<TFloat32>,
		m: Operand<T>,
		mMin: Operand<TFloat32>,
		mMax: Operand<TFloat32>,
		v: Operand<T>,
		vMin: Operand<TFloat32>,
		vMax: Operand<TFloat32>,
		beta: Operand<T>,
		betaMin: Operand<TFloat32>,
		betaMax: Operand<TFloat32>,
		gamma: Operand<T>,
		gammaMin: Operand<TFloat32>,
		gammaMax: Operand<TFloat32>,
		outType: DataType<U>,
		varianceEpsilon: Float,
		scaleAfterNormalization: Boolean
	): QuantizedBatchNormWithGlobalNormalization<U> = java.quantizedBatchNormWithGlobalNormalization<U,
			T>(	
		t,
		tMin,
		tMax,
		m,
		mMin,
		mMax,
		v,
		vMin,
		vMax,
		beta,
		betaMin,
		betaMax,
		gamma,
		gammaMin,
		gammaMax,
		outType,
		varianceEpsilon,
		scaleAfterNormalization
		)

	public fun <V : TType, T : TType, U : TType> quantizedBiasAdd(
		input: Operand<T>,
		bias: Operand<U>,
		minInput: Operand<TFloat32>,
		maxInput: Operand<TFloat32>,
		minBias: Operand<TFloat32>,
		maxBias: Operand<TFloat32>,
		outType: DataType<V>
	): QuantizedBiasAdd<V> = java.quantizedBiasAdd<V, T, U>(	
		input,
		bias,
		minInput,
		maxInput,
		minBias,
		maxBias,
		outType
		)

	public fun <V : TType, T : TType, U : TType> quantizedConv2d(
		input: Operand<T>,
		filter: Operand<U>,
		minInput: Operand<TFloat32>,
		maxInput: Operand<TFloat32>,
		minFilter: Operand<TFloat32>,
		maxFilter: Operand<TFloat32>,
		outType: DataType<V>,
		strides: List<Long>,
		padding: String,
		dilations: List<Long>? = null
	): QuantizedConv2d<V> = java.quantizedConv2d<V, T, U>(	
		input,
		filter,
		minInput,
		maxInput,
		minFilter,
		maxFilter,
		outType,
		strides,
		padding,
		*listOfNotNull(
			dilations?.let{ org.tensorflow.op.nn.QuantizedConv2d.dilations(it) }
		).toTypedArray()
		)

	public fun <T : TType> quantizedInstanceNorm(
		x: Operand<T>,
		xMin: Operand<TFloat32>,
		xMax: Operand<TFloat32>,
		outputRangeGiven: Boolean? = null,
		givenYMin: Float? = null,
		givenYMax: Float? = null,
		varianceEpsilon: Float? = null,
		minSeparation: Float? = null
	): QuantizedInstanceNorm<T> = java.quantizedInstanceNorm<T>(	
		x,
		xMin,
		xMax,
		*listOfNotNull(
			outputRangeGiven?.let{ org.tensorflow.op.nn.QuantizedInstanceNorm.outputRangeGiven(it) },
			givenYMin?.let{ org.tensorflow.op.nn.QuantizedInstanceNorm.givenYMin(it) },
			givenYMax?.let{ org.tensorflow.op.nn.QuantizedInstanceNorm.givenYMax(it) },
			varianceEpsilon?.let{ org.tensorflow.op.nn.QuantizedInstanceNorm.varianceEpsilon(it) },
			minSeparation?.let{ org.tensorflow.op.nn.QuantizedInstanceNorm.minSeparation(it) }
		).toTypedArray()
		)

	public fun <T : TType> quantizedMaxPool(
		input: Operand<T>,
		minInput: Operand<TFloat32>,
		maxInput: Operand<TFloat32>,
		ksize: List<Long>,
		strides: List<Long>,
		padding: String
	): QuantizedMaxPool<T> = java.quantizedMaxPool<T>(	
		input,
		minInput,
		maxInput,
		ksize,
		strides,
		padding
		)

	public fun <U : TType, T : TType> quantizedRelu(
		features: Operand<T>,
		minFeatures: Operand<TFloat32>,
		maxFeatures: Operand<TFloat32>,
		outType: DataType<U>
	): QuantizedRelu<U> = java.quantizedRelu<U, T>(	
		features,
		minFeatures,
		maxFeatures,
		outType
		)

	public fun <U : TType, T : TType> quantizedRelu6(
		features: Operand<T>,
		minFeatures: Operand<TFloat32>,
		maxFeatures: Operand<TFloat32>,
		outType: DataType<U>
	): QuantizedRelu6<U> = java.quantizedRelu6<U, T>(	
		features,
		minFeatures,
		maxFeatures,
		outType
		)

	public fun <U : TType, T : TType> quantizedReluX(
		features: Operand<T>,
		maxValue: Operand<TFloat32>,
		minFeatures: Operand<TFloat32>,
		maxFeatures: Operand<TFloat32>,
		outType: DataType<U>
	): QuantizedReluX<U> = java.quantizedReluX<U, T>(	
		features,
		maxValue,
		minFeatures,
		maxFeatures,
		outType
		)

	public fun <T : TType> relu(features: Operand<T>): Relu<T> = java.relu<T>(	
		features
		)

	public fun <T : TNumber> relu6(features: Operand<T>): Relu6<T> = java.relu6<T>(	
		features
		)

	public fun <T : TNumber> selu(features: Operand<T>): Selu<T> = java.selu<T>(	
		features
		)

	public fun <T : TNumber> sigmoidCrossEntropyWithLogits(labels: Operand<T>, logits: Operand<T>):
			Operand<T> = java.sigmoidCrossEntropyWithLogits<T>(	
		labels,
		logits
		)

	public fun <T : TNumber> softmax(logits: Operand<T>): Softmax<T> = java.softmax<T>(	
		logits
		)

	public fun <T : TNumber, U : TNumber> softmaxCrossEntropyWithLogits(
		labels: Operand<U>,
		logits: Operand<T>,
		axis: Int
	): Operand<T> = java.softmaxCrossEntropyWithLogits<T, U>(	
		labels,
		logits,
		axis
		)

	public fun <T : TNumber> softsign(features: Operand<T>): Softsign<T> = java.softsign<T>(	
		features
		)

	public fun <T : TType, U : TNumber> spaceToBatch(
		input: Operand<T>,
		paddings: Operand<U>,
		blockSize: Long
	): SpaceToBatch<T> = java.spaceToBatch<T, U>(	
		input,
		paddings,
		blockSize
		)

	public fun <T : TType> spaceToDepth(
		input: Operand<T>,
		blockSize: Long,
		dataFormat: String? = null
	): SpaceToDepth<T> = java.spaceToDepth<T>(	
		input,
		blockSize,
		*listOfNotNull(
			dataFormat?.let{ org.tensorflow.op.nn.SpaceToDepth.dataFormat(it) }
		).toTypedArray()
		)

	public fun <T : TNumber, U : TNumber> sparseSoftmaxCrossEntropyWithLogits(labels: Operand<T>,
			logits: Operand<U>): Operand<*> = java.sparseSoftmaxCrossEntropyWithLogits<T, U>(	
		labels,
		logits
		)

	public fun <T : TNumber> topK(
		input: Operand<T>,
		k: Operand<TInt32>,
		sorted: Boolean? = null
	): TopK<T> = java.topK<T>(	
		input,
		k,
		*listOfNotNull(
			sorted?.let{ org.tensorflow.op.nn.TopK.sorted(it) }
		).toTypedArray()
		)
}
