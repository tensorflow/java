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
    vararg options: AvgPool.Options
  ): AvgPool<T> = java.avgPool<T>(value, ksize, strides, padding, *options)

  public fun <T : TNumber> avgPool3d(
    input: Operand<T>,
    ksize: List<Long>,
    strides: List<Long>,
    padding: String,
    vararg options: AvgPool3d.Options
  ): AvgPool3d<T> = java.avgPool3d<T>(input, ksize, strides, padding, *options)

  public fun <T : TNumber> avgPool3dGrad(
    origInputShape: Operand<TInt32>,
    grad: Operand<T>,
    ksize: List<Long>,
    strides: List<Long>,
    padding: String,
    vararg options: AvgPool3dGrad.Options
  ): AvgPool3dGrad<T> = java.avgPool3dGrad<T>(origInputShape, grad, ksize, strides, padding,
      *options)

  public fun <T : TType> batchNormWithGlobalNormalization(
    t: Operand<T>,
    m: Operand<T>,
    v: Operand<T>,
    beta: Operand<T>,
    gamma: Operand<T>,
    varianceEpsilon: Float,
    scaleAfterNormalization: Boolean
  ): BatchNormWithGlobalNormalization<T> = java.batchNormWithGlobalNormalization<T>(t, m, v, beta,
      gamma, varianceEpsilon, scaleAfterNormalization)

  public fun <T : TType> batchNormWithGlobalNormalizationGrad(
    t: Operand<T>,
    m: Operand<T>,
    v: Operand<T>,
    gamma: Operand<T>,
    backprop: Operand<T>,
    varianceEpsilon: Float,
    scaleAfterNormalization: Boolean
  ): BatchNormWithGlobalNormalizationGrad<T> = java.batchNormWithGlobalNormalizationGrad<T>(t, m, v,
      gamma, backprop, varianceEpsilon, scaleAfterNormalization)

  public fun <T : TType> biasAdd(
    value: Operand<T>,
    bias: Operand<T>,
    vararg options: BiasAdd.Options
  ): BiasAdd<T> = java.biasAdd<T>(value, bias, *options)

  public fun <T : TType> biasAddGrad(outBackprop: Operand<T>, vararg options: BiasAddGrad.Options):
      BiasAddGrad<T> = java.biasAddGrad<T>(outBackprop, *options)

  public fun computeAccidentalHits(
    trueClasses: Operand<TInt64>,
    sampledCandidates: Operand<TInt64>,
    numTrue: Long,
    vararg options: ComputeAccidentalHits.Options
  ): ComputeAccidentalHits = java.computeAccidentalHits(trueClasses, sampledCandidates, numTrue,
      *options)

  public fun <T : TNumber> conv2d(
    input: Operand<T>,
    filter: Operand<T>,
    strides: List<Long>,
    padding: String,
    vararg options: Conv2d.Options
  ): Conv2d<T> = java.conv2d<T>(input, filter, strides, padding, *options)

  public fun <T : TNumber> conv2dBackpropFilter(
    input: Operand<T>,
    filterSizes: Operand<TInt32>,
    outBackprop: Operand<T>,
    strides: List<Long>,
    padding: String,
    vararg options: Conv2dBackpropFilter.Options
  ): Conv2dBackpropFilter<T> = java.conv2dBackpropFilter<T>(input, filterSizes, outBackprop,
      strides, padding, *options)

  public fun <T : TNumber> conv2dBackpropInput(
    inputSizes: Operand<TInt32>,
    filter: Operand<T>,
    outBackprop: Operand<T>,
    strides: List<Long>,
    padding: String,
    vararg options: Conv2dBackpropInput.Options
  ): Conv2dBackpropInput<T> = java.conv2dBackpropInput<T>(inputSizes, filter, outBackprop, strides,
      padding, *options)

  public fun <T : TNumber> conv3d(
    input: Operand<T>,
    filter: Operand<T>,
    strides: List<Long>,
    padding: String,
    vararg options: Conv3d.Options
  ): Conv3d<T> = java.conv3d<T>(input, filter, strides, padding, *options)

  public fun <T : TNumber> conv3dBackpropFilter(
    input: Operand<T>,
    filterSizes: Operand<TInt32>,
    outBackprop: Operand<T>,
    strides: List<Long>,
    padding: String,
    vararg options: Conv3dBackpropFilter.Options
  ): Conv3dBackpropFilter<T> = java.conv3dBackpropFilter<T>(input, filterSizes, outBackprop,
      strides, padding, *options)

  public fun <U : TNumber, T : TNumber> conv3dBackpropInput(
    inputSizes: Operand<T>,
    filter: Operand<U>,
    outBackprop: Operand<U>,
    strides: List<Long>,
    padding: String,
    vararg options: Conv3dBackpropInput.Options
  ): Conv3dBackpropInput<U> = java.conv3dBackpropInput<U, T>(inputSizes, filter, outBackprop,
      strides, padding, *options)

  public fun <T : TNumber> ctcBeamSearchDecoder(
    inputs: Operand<T>,
    sequenceLength: Operand<TInt32>,
    beamWidth: Long,
    topPaths: Long,
    vararg options: CtcBeamSearchDecoder.Options
  ): CtcBeamSearchDecoder<T> = java.ctcBeamSearchDecoder<T>(inputs, sequenceLength, beamWidth,
      topPaths, *options)

  public fun <T : TNumber> ctcGreedyDecoder(
    inputs: Operand<T>,
    sequenceLength: Operand<TInt32>,
    vararg options: CtcGreedyDecoder.Options
  ): CtcGreedyDecoder<T> = java.ctcGreedyDecoder<T>(inputs, sequenceLength, *options)

  public fun <T : TNumber> ctcLoss(
    inputs: Operand<T>,
    labelsIndices: Operand<TInt64>,
    labelsValues: Operand<TInt32>,
    sequenceLength: Operand<TInt32>,
    vararg options: CtcLoss.Options
  ): CtcLoss<T> = java.ctcLoss<T>(inputs, labelsIndices, labelsValues, sequenceLength, *options)

  public fun <T : TNumber> cudnnRNNCanonicalToParams(
    numLayers: Operand<TInt32>,
    numUnits: Operand<TInt32>,
    inputSize: Operand<TInt32>,
    weights: Iterable<Operand<T>>,
    biases: Iterable<Operand<T>>,
    vararg options: CudnnRNNCanonicalToParams.Options
  ): CudnnRNNCanonicalToParams<T> = java.cudnnRNNCanonicalToParams<T>(numLayers, numUnits,
      inputSize, weights, biases, *options)

  public fun <T : TNumber> cudnnRNNParamsToCanonical(
    numLayers: Operand<TInt32>,
    numUnits: Operand<TInt32>,
    inputSize: Operand<TInt32>,
    params: Operand<T>,
    numParamsWeights: Long,
    numParamsBiases: Long,
    vararg options: CudnnRNNParamsToCanonical.Options
  ): CudnnRNNParamsToCanonical<T> = java.cudnnRNNParamsToCanonical<T>(numLayers, numUnits,
      inputSize, params, numParamsWeights, numParamsBiases, *options)

  public fun <U : TNumber, T : TNumber> cudnnRnnParamsSize(
    numLayers: Operand<TInt32>,
    numUnits: Operand<TInt32>,
    inputSize: Operand<TInt32>,
    T_: DataType<T>,
    S: DataType<U>,
    vararg options: CudnnRnnParamsSize.Options
  ): CudnnRnnParamsSize<U> = java.cudnnRnnParamsSize<U, T>(numLayers, numUnits, inputSize, T_, S,
      *options)

  public fun <T : TNumber> dataFormatDimMap(x: Operand<T>, vararg
      options: DataFormatDimMap.Options): DataFormatDimMap<T> = java.dataFormatDimMap<T>(x,
      *options)

  public fun <T : TNumber> dataFormatVecPermute(x: Operand<T>, vararg
      options: DataFormatVecPermute.Options): DataFormatVecPermute<T> =
      java.dataFormatVecPermute<T>(x, *options)

  public fun <T : TType> depthToSpace(
    input: Operand<T>,
    blockSize: Long,
    vararg options: DepthToSpace.Options
  ): DepthToSpace<T> = java.depthToSpace<T>(input, blockSize, *options)

  public fun <T : TNumber> depthwiseConv2dNative(
    input: Operand<T>,
    filter: Operand<T>,
    strides: List<Long>,
    padding: String,
    vararg options: DepthwiseConv2dNative.Options
  ): DepthwiseConv2dNative<T> = java.depthwiseConv2dNative<T>(input, filter, strides, padding,
      *options)

  public fun <T : TNumber> depthwiseConv2dNativeBackpropFilter(
    input: Operand<T>,
    filterSizes: Operand<TInt32>,
    outBackprop: Operand<T>,
    strides: List<Long>,
    padding: String,
    vararg options: DepthwiseConv2dNativeBackpropFilter.Options
  ): DepthwiseConv2dNativeBackpropFilter<T> = java.depthwiseConv2dNativeBackpropFilter<T>(input,
      filterSizes, outBackprop, strides, padding, *options)

  public fun <T : TNumber> depthwiseConv2dNativeBackpropInput(
    inputSizes: Operand<TInt32>,
    filter: Operand<T>,
    outBackprop: Operand<T>,
    strides: List<Long>,
    padding: String,
    vararg options: DepthwiseConv2dNativeBackpropInput.Options
  ): DepthwiseConv2dNativeBackpropInput<T> = java.depthwiseConv2dNativeBackpropInput<T>(inputSizes,
      filter, outBackprop, strides, padding, *options)

  public fun <T : TNumber> dilation2d(
    input: Operand<T>,
    filter: Operand<T>,
    strides: List<Long>,
    rates: List<Long>,
    padding: String
  ): Dilation2d<T> = java.dilation2d<T>(input, filter, strides, rates, padding)

  public fun <T : TNumber> dilation2dBackpropFilter(
    input: Operand<T>,
    filter: Operand<T>,
    outBackprop: Operand<T>,
    strides: List<Long>,
    rates: List<Long>,
    padding: String
  ): Dilation2dBackpropFilter<T> = java.dilation2dBackpropFilter<T>(input, filter, outBackprop,
      strides, rates, padding)

  public fun <T : TNumber> dilation2dBackpropInput(
    input: Operand<T>,
    filter: Operand<T>,
    outBackprop: Operand<T>,
    strides: List<Long>,
    rates: List<Long>,
    padding: String
  ): Dilation2dBackpropInput<T> = java.dilation2dBackpropInput<T>(input, filter, outBackprop,
      strides, rates, padding)

  public fun <T : TNumber> elu(features: Operand<T>): Elu<T> = java.elu<T>(features)

  public fun fixedUnigramCandidateSampler(
    trueClasses: Operand<TInt64>,
    numTrue: Long,
    numSampled: Long,
    unique: Boolean,
    rangeMax: Long,
    vararg options: FixedUnigramCandidateSampler.Options
  ): FixedUnigramCandidateSampler = java.fixedUnigramCandidateSampler(trueClasses, numTrue,
      numSampled, unique, rangeMax, *options)

  public fun <T : TNumber> fractionalAvgPool(
    value: Operand<T>,
    poolingRatio: List<Float>,
    vararg options: FractionalAvgPool.Options
  ): FractionalAvgPool<T> = java.fractionalAvgPool<T>(value, poolingRatio, *options)

  public fun <T : TNumber> fractionalMaxPool(
    value: Operand<T>,
    poolingRatio: List<Float>,
    vararg options: FractionalMaxPool.Options
  ): FractionalMaxPool<T> = java.fractionalMaxPool<T>(value, poolingRatio, *options)

  public fun <T : TNumber, U : TNumber> fusedBatchNorm(
    x: Operand<T>,
    scale: Operand<U>,
    offset: Operand<U>,
    mean: Operand<U>,
    variance: Operand<U>,
    vararg options: FusedBatchNorm.Options
  ): FusedBatchNorm<T, U> = java.fusedBatchNorm<T, U>(x, scale, offset, mean, variance, *options)

  public fun <T : TNumber, U : TNumber> fusedBatchNormGrad(
    yBackprop: Operand<T>,
    x: Operand<T>,
    scale: Operand<TFloat32>,
    reserveSpace1: Operand<U>,
    reserveSpace2: Operand<U>,
    reserveSpace3: Operand<U>,
    vararg options: FusedBatchNormGrad.Options
  ): FusedBatchNormGrad<T, U> = java.fusedBatchNormGrad<T, U>(yBackprop, x, scale, reserveSpace1,
      reserveSpace2, reserveSpace3, *options)

  public fun <T : TNumber> fusedPadConv2d(
    input: Operand<T>,
    paddings: Operand<TInt32>,
    filter: Operand<T>,
    mode: String,
    strides: List<Long>,
    padding: String
  ): FusedPadConv2d<T> = java.fusedPadConv2d<T>(input, paddings, filter, mode, strides, padding)

  public fun <T : TNumber> fusedResizeAndPadConv2d(
    input: Operand<T>,
    size: Operand<TInt32>,
    paddings: Operand<TInt32>,
    filter: Operand<T>,
    mode: String,
    strides: List<Long>,
    padding: String,
    vararg options: FusedResizeAndPadConv2d.Options
  ): FusedResizeAndPadConv2d<T> = java.fusedResizeAndPadConv2d<T>(input, size, paddings, filter,
      mode, strides, padding, *options)

  public fun <T : TNumber> inTopK(
    predictions: Operand<TFloat32>,
    targets: Operand<T>,
    k: Operand<T>
  ): InTopK = java.inTopK<T>(predictions, targets, k)

  public fun <T : TNumber> l2Loss(t: Operand<T>): L2Loss<T> = java.l2Loss<T>(t)

  public fun <T : TNumber> leakyRelu(features: Operand<T>, vararg options: LeakyRelu.Options):
      LeakyRelu<T> = java.leakyRelu<T>(features, *options)

  public fun learnedUnigramCandidateSampler(
    trueClasses: Operand<TInt64>,
    numTrue: Long,
    numSampled: Long,
    unique: Boolean,
    rangeMax: Long,
    vararg options: LearnedUnigramCandidateSampler.Options
  ): LearnedUnigramCandidateSampler = java.learnedUnigramCandidateSampler(trueClasses, numTrue,
      numSampled, unique, rangeMax, *options)

  public fun <T : TNumber> localResponseNormalization(input: Operand<T>, vararg
      options: LocalResponseNormalization.Options): LocalResponseNormalization<T> =
      java.localResponseNormalization<T>(input, *options)

  public fun <T : TNumber> logSoftmax(logits: Operand<T>): LogSoftmax<T> =
      java.logSoftmax<T>(logits)

  public fun <T : TType> maxPool(
    input: Operand<T>,
    ksize: Operand<TInt32>,
    strides: Operand<TInt32>,
    padding: String,
    vararg options: MaxPool.Options
  ): MaxPool<T> = java.maxPool<T>(input, ksize, strides, padding, *options)

  public fun <T : TNumber> maxPool3d(
    input: Operand<T>,
    ksize: List<Long>,
    strides: List<Long>,
    padding: String,
    vararg options: MaxPool3d.Options
  ): MaxPool3d<T> = java.maxPool3d<T>(input, ksize, strides, padding, *options)

  public fun <U : TNumber, T : TNumber> maxPool3dGrad(
    origInput: Operand<T>,
    origOutput: Operand<T>,
    grad: Operand<U>,
    ksize: List<Long>,
    strides: List<Long>,
    padding: String,
    vararg options: MaxPool3dGrad.Options
  ): MaxPool3dGrad<U> = java.maxPool3dGrad<U, T>(origInput, origOutput, grad, ksize, strides,
      padding, *options)

  public fun <T : TNumber> maxPool3dGradGrad(
    origInput: Operand<T>,
    origOutput: Operand<T>,
    grad: Operand<T>,
    ksize: List<Long>,
    strides: List<Long>,
    padding: String,
    vararg options: MaxPool3dGradGrad.Options
  ): MaxPool3dGradGrad<T> = java.maxPool3dGradGrad<T>(origInput, origOutput, grad, ksize, strides,
      padding, *options)

  public fun <T : TNumber> maxPoolGrad(
    origInput: Operand<T>,
    origOutput: Operand<T>,
    grad: Operand<T>,
    ksize: Operand<TInt32>,
    strides: Operand<TInt32>,
    padding: String,
    vararg options: MaxPoolGrad.Options
  ): MaxPoolGrad<T> = java.maxPoolGrad<T>(origInput, origOutput, grad, ksize, strides, padding,
      *options)

  public fun <T : TNumber> maxPoolGradGrad(
    origInput: Operand<T>,
    origOutput: Operand<T>,
    grad: Operand<T>,
    ksize: Operand<TInt32>,
    strides: Operand<TInt32>,
    padding: String,
    vararg options: MaxPoolGradGrad.Options
  ): MaxPoolGradGrad<T> = java.maxPoolGradGrad<T>(origInput, origOutput, grad, ksize, strides,
      padding, *options)

  public fun <T : TNumber, U : TNumber> maxPoolGradGradWithArgmax(
    input: Operand<T>,
    grad: Operand<T>,
    argmax: Operand<U>,
    ksize: List<Long>,
    strides: List<Long>,
    padding: String,
    vararg options: MaxPoolGradGradWithArgmax.Options
  ): MaxPoolGradGradWithArgmax<T> = java.maxPoolGradGradWithArgmax<T, U>(input, grad, argmax, ksize,
      strides, padding, *options)

  public fun <T : TNumber> maxPoolWithArgmax(
    input: Operand<T>,
    ksize: List<Long>,
    strides: List<Long>,
    padding: String,
    vararg options: MaxPoolWithArgmax.Options
  ): MaxPoolWithArgmax<T, TInt64> = java.maxPoolWithArgmax<T>(input, ksize, strides, padding,
      *options)

  public fun <T : TNumber, U : TNumber> maxPoolWithArgmax(
    input: Operand<T>,
    ksize: List<Long>,
    strides: List<Long>,
    Targmax: DataType<U>,
    padding: String,
    vararg options: MaxPoolWithArgmax.Options
  ): MaxPoolWithArgmax<T, U> = java.maxPoolWithArgmax<T, U>(input, ksize, strides, Targmax, padding,
      *options)

  public fun <T : TNumber> nthElement(
    input: Operand<T>,
    n: Operand<TInt32>,
    vararg options: NthElement.Options
  ): NthElement<T> = java.nthElement<T>(input, n, *options)

  public fun <T : TType> quantizedAvgPool(
    input: Operand<T>,
    minInput: Operand<TFloat32>,
    maxInput: Operand<TFloat32>,
    ksize: List<Long>,
    strides: List<Long>,
    padding: String
  ): QuantizedAvgPool<T> = java.quantizedAvgPool<T>(input, minInput, maxInput, ksize, strides,
      padding)

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
  ): QuantizedBatchNormWithGlobalNormalization<U> =
      java.quantizedBatchNormWithGlobalNormalization<U, T>(t, tMin, tMax, m, mMin, mMax, v, vMin,
      vMax, beta, betaMin, betaMax, gamma, gammaMin, gammaMax, outType, varianceEpsilon,
      scaleAfterNormalization)

  public fun <V : TType, T : TType, U : TType> quantizedBiasAdd(
    input: Operand<T>,
    bias: Operand<U>,
    minInput: Operand<TFloat32>,
    maxInput: Operand<TFloat32>,
    minBias: Operand<TFloat32>,
    maxBias: Operand<TFloat32>,
    outType: DataType<V>
  ): QuantizedBiasAdd<V> = java.quantizedBiasAdd<V, T, U>(input, bias, minInput, maxInput, minBias,
      maxBias, outType)

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
    vararg options: QuantizedConv2d.Options
  ): QuantizedConv2d<V> = java.quantizedConv2d<V, T, U>(input, filter, minInput, maxInput,
      minFilter, maxFilter, outType, strides, padding, *options)

  public fun <T : TType> quantizedInstanceNorm(
    x: Operand<T>,
    xMin: Operand<TFloat32>,
    xMax: Operand<TFloat32>,
    vararg options: QuantizedInstanceNorm.Options
  ): QuantizedInstanceNorm<T> = java.quantizedInstanceNorm<T>(x, xMin, xMax, *options)

  public fun <T : TType> quantizedMaxPool(
    input: Operand<T>,
    minInput: Operand<TFloat32>,
    maxInput: Operand<TFloat32>,
    ksize: List<Long>,
    strides: List<Long>,
    padding: String
  ): QuantizedMaxPool<T> = java.quantizedMaxPool<T>(input, minInput, maxInput, ksize, strides,
      padding)

  public fun <U : TType, T : TType> quantizedRelu(
    features: Operand<T>,
    minFeatures: Operand<TFloat32>,
    maxFeatures: Operand<TFloat32>,
    outType: DataType<U>
  ): QuantizedRelu<U> = java.quantizedRelu<U, T>(features, minFeatures, maxFeatures, outType)

  public fun <U : TType, T : TType> quantizedRelu6(
    features: Operand<T>,
    minFeatures: Operand<TFloat32>,
    maxFeatures: Operand<TFloat32>,
    outType: DataType<U>
  ): QuantizedRelu6<U> = java.quantizedRelu6<U, T>(features, minFeatures, maxFeatures, outType)

  public fun <U : TType, T : TType> quantizedReluX(
    features: Operand<T>,
    maxValue: Operand<TFloat32>,
    minFeatures: Operand<TFloat32>,
    maxFeatures: Operand<TFloat32>,
    outType: DataType<U>
  ): QuantizedReluX<U> = java.quantizedReluX<U, T>(features, maxValue, minFeatures, maxFeatures,
      outType)

  public fun <T : TType> relu(features: Operand<T>): Relu<T> = java.relu<T>(features)

  public fun <T : TNumber> relu6(features: Operand<T>): Relu6<T> = java.relu6<T>(features)

  public fun <T : TNumber> selu(features: Operand<T>): Selu<T> = java.selu<T>(features)

  public fun <T : TNumber> sigmoidCrossEntropyWithLogits(labels: Operand<T>, logits: Operand<T>):
      Operand<T> = java.sigmoidCrossEntropyWithLogits<T>(labels, logits)

  public fun <T : TNumber> softmax(logits: Operand<T>): Softmax<T> = java.softmax<T>(logits)

  public fun <T : TNumber, U : TNumber> softmaxCrossEntropyWithLogits(
    labels: Operand<U>,
    logits: Operand<T>,
    axis: Int
  ): Operand<T> = java.softmaxCrossEntropyWithLogits<T, U>(labels, logits, axis)

  public fun <T : TNumber> softsign(features: Operand<T>): Softsign<T> = java.softsign<T>(features)

  public fun <T : TType, U : TNumber> spaceToBatch(
    input: Operand<T>,
    paddings: Operand<U>,
    blockSize: Long
  ): SpaceToBatch<T> = java.spaceToBatch<T, U>(input, paddings, blockSize)

  public fun <T : TType> spaceToDepth(
    input: Operand<T>,
    blockSize: Long,
    vararg options: SpaceToDepth.Options
  ): SpaceToDepth<T> = java.spaceToDepth<T>(input, blockSize, *options)

  public fun <T : TNumber, U : TNumber> sparseSoftmaxCrossEntropyWithLogits(labels: Operand<T>,
      logits: Operand<U>): Operand<*> = java.sparseSoftmaxCrossEntropyWithLogits<T, U>(labels,
      logits)

  public fun <T : TNumber> topK(
    input: Operand<T>,
    k: Operand<TInt32>,
    vararg options: TopK.Options
  ): TopK<T> = java.topK<T>(input, k, *options)
}
