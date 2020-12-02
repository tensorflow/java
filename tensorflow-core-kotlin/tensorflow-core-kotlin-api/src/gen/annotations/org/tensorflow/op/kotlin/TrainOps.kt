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
import org.tensorflow.ndarray.Shape
import org.tensorflow.op.Scope
import org.tensorflow.op.train.AccumulatorApplyGradient
import org.tensorflow.op.train.AccumulatorNumAccumulated
import org.tensorflow.op.train.AccumulatorSetGlobalStep
import org.tensorflow.op.train.AccumulatorTakeGradient
import org.tensorflow.op.train.ApplyAdadelta
import org.tensorflow.op.train.ApplyAdagrad
import org.tensorflow.op.train.ApplyAdagradDa
import org.tensorflow.op.train.ApplyAdam
import org.tensorflow.op.train.ApplyAddSign
import org.tensorflow.op.train.ApplyCenteredRmsProp
import org.tensorflow.op.train.ApplyFtrl
import org.tensorflow.op.train.ApplyGradientDescent
import org.tensorflow.op.train.ApplyMomentum
import org.tensorflow.op.train.ApplyPowerSign
import org.tensorflow.op.train.ApplyProximalAdagrad
import org.tensorflow.op.train.ApplyProximalGradientDescent
import org.tensorflow.op.train.ApplyRmsProp
import org.tensorflow.op.train.BatchMatMul
import org.tensorflow.op.train.ConditionalAccumulator
import org.tensorflow.op.train.GenerateVocabRemapping
import org.tensorflow.op.train.MergeV2Checkpoints
import org.tensorflow.op.train.NegTrain
import org.tensorflow.op.train.PreventGradient
import org.tensorflow.op.train.ResourceApplyAdadelta
import org.tensorflow.op.train.ResourceApplyAdagradDa
import org.tensorflow.op.train.ResourceApplyAdam
import org.tensorflow.op.train.ResourceApplyAdamWithAmsgrad
import org.tensorflow.op.train.ResourceApplyAddSign
import org.tensorflow.op.train.ResourceApplyCenteredRmsProp
import org.tensorflow.op.train.ResourceApplyFtrl
import org.tensorflow.op.train.ResourceApplyGradientDescent
import org.tensorflow.op.train.ResourceApplyKerasMomentum
import org.tensorflow.op.train.ResourceApplyMomentum
import org.tensorflow.op.train.ResourceApplyPowerSign
import org.tensorflow.op.train.ResourceApplyProximalAdagrad
import org.tensorflow.op.train.ResourceApplyProximalGradientDescent
import org.tensorflow.op.train.ResourceApplyRmsProp
import org.tensorflow.op.train.ResourceSparseApplyAdadelta
import org.tensorflow.op.train.ResourceSparseApplyAdagrad
import org.tensorflow.op.train.ResourceSparseApplyAdagradDa
import org.tensorflow.op.train.ResourceSparseApplyCenteredRmsProp
import org.tensorflow.op.train.ResourceSparseApplyFtrl
import org.tensorflow.op.train.ResourceSparseApplyKerasMomentum
import org.tensorflow.op.train.ResourceSparseApplyMomentum
import org.tensorflow.op.train.ResourceSparseApplyProximalAdagrad
import org.tensorflow.op.train.ResourceSparseApplyProximalGradientDescent
import org.tensorflow.op.train.ResourceSparseApplyRmsProp
import org.tensorflow.op.train.Restore
import org.tensorflow.op.train.RestoreSlice
import org.tensorflow.op.train.Save
import org.tensorflow.op.train.SaveSlices
import org.tensorflow.op.train.SdcaFprint
import org.tensorflow.op.train.SdcaShrinkL1
import org.tensorflow.op.train.SparseApplyAdadelta
import org.tensorflow.op.train.SparseApplyAdagradDa
import org.tensorflow.op.train.SparseApplyCenteredRmsProp
import org.tensorflow.op.train.SparseApplyFtrl
import org.tensorflow.op.train.SparseApplyMomentum
import org.tensorflow.op.train.SparseApplyProximalAdagrad
import org.tensorflow.op.train.SparseApplyProximalGradientDescent
import org.tensorflow.op.train.SparseApplyRmsProp
import org.tensorflow.op.train.TileGrad
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.TString
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building {@code train} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class TrainOps(
  /**
   * Get the parent {@link KotlinOps} object.
   */
  public val ops: KotlinOps
) {
  public val java: org.tensorflow.op.TrainOps = ops.java.train

  /**
   * Returns the current {@link Scope scope} of this API
   */
  public val scope: Scope = ops.scope

  public fun <T : TType> accumulatorApplyGradient(
    handle: Operand<TString>,
    localStep: Operand<TInt64>,
    gradient: Operand<T>
  ): AccumulatorApplyGradient = java.accumulatorApplyGradient<T>(handle, localStep, gradient)

  public fun accumulatorNumAccumulated(handle: Operand<TString>): AccumulatorNumAccumulated =
      java.accumulatorNumAccumulated(handle)

  public fun accumulatorSetGlobalStep(handle: Operand<TString>, newGlobalStep: Operand<TInt64>):
      AccumulatorSetGlobalStep = java.accumulatorSetGlobalStep(handle, newGlobalStep)

  public fun <T : TType> accumulatorTakeGradient(
    handle: Operand<TString>,
    numRequired: Operand<TInt32>,
    dtype: DataType<T>
  ): AccumulatorTakeGradient<T> = java.accumulatorTakeGradient<T>(handle, numRequired, dtype)

  public fun <T : TType> applyAdadelta(
    `var`: Operand<T>,
    accum: Operand<T>,
    accumUpdate: Operand<T>,
    lr: Operand<T>,
    rho: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    vararg options: ApplyAdadelta.Options
  ): ApplyAdadelta<T> = java.applyAdadelta<T>(`var`, accum, accumUpdate, lr, rho, epsilon, grad,
      *options)

  public fun <T : TType> applyAdagrad(
    `var`: Operand<T>,
    accum: Operand<T>,
    lr: Operand<T>,
    grad: Operand<T>,
    vararg options: ApplyAdagrad.Options
  ): ApplyAdagrad<T> = java.applyAdagrad<T>(`var`, accum, lr, grad, *options)

  public fun <T : TType> applyAdagradDa(
    `var`: Operand<T>,
    gradientAccumulator: Operand<T>,
    gradientSquaredAccumulator: Operand<T>,
    grad: Operand<T>,
    lr: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    globalStep: Operand<TInt64>,
    vararg options: ApplyAdagradDa.Options
  ): ApplyAdagradDa<T> = java.applyAdagradDa<T>(`var`, gradientAccumulator,
      gradientSquaredAccumulator, grad, lr, l1, l2, globalStep, *options)

  public fun <T : TType> applyAdam(
    `var`: Operand<T>,
    m: Operand<T>,
    v: Operand<T>,
    beta1Power: Operand<T>,
    beta2Power: Operand<T>,
    lr: Operand<T>,
    beta1: Operand<T>,
    beta2: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    vararg options: ApplyAdam.Options
  ): ApplyAdam<T> = java.applyAdam<T>(`var`, m, v, beta1Power, beta2Power, lr, beta1, beta2,
      epsilon, grad, *options)

  public fun <T : TType> applyAddSign(
    `var`: Operand<T>,
    m: Operand<T>,
    lr: Operand<T>,
    alpha: Operand<T>,
    signDecay: Operand<T>,
    beta: Operand<T>,
    grad: Operand<T>,
    vararg options: ApplyAddSign.Options
  ): ApplyAddSign<T> = java.applyAddSign<T>(`var`, m, lr, alpha, signDecay, beta, grad, *options)

  public fun <T : TType> applyCenteredRmsProp(
    `var`: Operand<T>,
    mg: Operand<T>,
    ms: Operand<T>,
    mom: Operand<T>,
    lr: Operand<T>,
    rho: Operand<T>,
    momentum: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    vararg options: ApplyCenteredRmsProp.Options
  ): ApplyCenteredRmsProp<T> = java.applyCenteredRmsProp<T>(`var`, mg, ms, mom, lr, rho, momentum,
      epsilon, grad, *options)

  public fun <T : TType> applyFtrl(
    `var`: Operand<T>,
    accum: Operand<T>,
    linear: Operand<T>,
    grad: Operand<T>,
    lr: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    l2Shrinkage: Operand<T>,
    lrPower: Operand<T>,
    vararg options: ApplyFtrl.Options
  ): ApplyFtrl<T> = java.applyFtrl<T>(`var`, accum, linear, grad, lr, l1, l2, l2Shrinkage, lrPower,
      *options)

  public fun <T : TType> applyGradientDescent(
    `var`: Operand<T>,
    alpha: Operand<T>,
    delta: Operand<T>,
    vararg options: ApplyGradientDescent.Options
  ): ApplyGradientDescent<T> = java.applyGradientDescent<T>(`var`, alpha, delta, *options)

  public fun <T : TType> applyMomentum(
    `var`: Operand<T>,
    accum: Operand<T>,
    lr: Operand<T>,
    grad: Operand<T>,
    momentum: Operand<T>,
    vararg options: ApplyMomentum.Options
  ): ApplyMomentum<T> = java.applyMomentum<T>(`var`, accum, lr, grad, momentum, *options)

  public fun <T : TType> applyPowerSign(
    `var`: Operand<T>,
    m: Operand<T>,
    lr: Operand<T>,
    logbase: Operand<T>,
    signDecay: Operand<T>,
    beta: Operand<T>,
    grad: Operand<T>,
    vararg options: ApplyPowerSign.Options
  ): ApplyPowerSign<T> = java.applyPowerSign<T>(`var`, m, lr, logbase, signDecay, beta, grad,
      *options)

  public fun <T : TType> applyProximalAdagrad(
    `var`: Operand<T>,
    accum: Operand<T>,
    lr: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    grad: Operand<T>,
    vararg options: ApplyProximalAdagrad.Options
  ): ApplyProximalAdagrad<T> = java.applyProximalAdagrad<T>(`var`, accum, lr, l1, l2, grad,
      *options)

  public fun <T : TType> applyProximalGradientDescent(
    `var`: Operand<T>,
    alpha: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    delta: Operand<T>,
    vararg options: ApplyProximalGradientDescent.Options
  ): ApplyProximalGradientDescent<T> = java.applyProximalGradientDescent<T>(`var`, alpha, l1, l2,
      delta, *options)

  public fun <T : TType> applyRmsProp(
    `var`: Operand<T>,
    ms: Operand<T>,
    mom: Operand<T>,
    lr: Operand<T>,
    rho: Operand<T>,
    momentum: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    vararg options: ApplyRmsProp.Options
  ): ApplyRmsProp<T> = java.applyRmsProp<T>(`var`, ms, mom, lr, rho, momentum, epsilon, grad,
      *options)

  public fun <T : TType> batchMatMul(
    x: Operand<T>,
    y: Operand<T>,
    vararg options: BatchMatMul.Options
  ): BatchMatMul<T> = java.batchMatMul<T>(x, y, *options)

  public fun <T : TType> conditionalAccumulator(
    dtype: DataType<T>,
    shape: Shape,
    vararg options: ConditionalAccumulator.Options
  ): ConditionalAccumulator = java.conditionalAccumulator<T>(dtype, shape, *options)

  public fun generateVocabRemapping(
    newVocabFile: Operand<TString>,
    oldVocabFile: Operand<TString>,
    newVocabOffset: Long,
    numNewVocab: Long,
    vararg options: GenerateVocabRemapping.Options
  ): GenerateVocabRemapping = java.generateVocabRemapping(newVocabFile, oldVocabFile,
      newVocabOffset, numNewVocab, *options)

  public fun mergeV2Checkpoints(
    checkpointPrefixes: Operand<TString>,
    destinationPrefix: Operand<TString>,
    vararg options: MergeV2Checkpoints.Options
  ): MergeV2Checkpoints = java.mergeV2Checkpoints(checkpointPrefixes, destinationPrefix, *options)

  public fun negTrain(
    wIn: Operand<TFloat32>,
    wOut: Operand<TFloat32>,
    examples: Operand<TInt32>,
    labels: Operand<TInt32>,
    lr: Operand<TFloat32>,
    vocabCount: List<Long>,
    numNegativeSamples: Long
  ): NegTrain = java.negTrain(wIn, wOut, examples, labels, lr, vocabCount, numNegativeSamples)

  public fun <T : TType> preventGradient(input: Operand<T>, vararg
      options: PreventGradient.Options): PreventGradient<T> = java.preventGradient<T>(input,
      *options)

  public fun <T : TType> resourceApplyAdadelta(
    `var`: Operand<*>,
    accum: Operand<*>,
    accumUpdate: Operand<*>,
    lr: Operand<T>,
    rho: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    vararg options: ResourceApplyAdadelta.Options
  ): ResourceApplyAdadelta = java.resourceApplyAdadelta<T>(`var`, accum, accumUpdate, lr, rho,
      epsilon, grad, *options)

  public fun <T : TType> resourceApplyAdagradDa(
    `var`: Operand<*>,
    gradientAccumulator: Operand<*>,
    gradientSquaredAccumulator: Operand<*>,
    grad: Operand<T>,
    lr: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    globalStep: Operand<TInt64>,
    vararg options: ResourceApplyAdagradDa.Options
  ): ResourceApplyAdagradDa = java.resourceApplyAdagradDa<T>(`var`, gradientAccumulator,
      gradientSquaredAccumulator, grad, lr, l1, l2, globalStep, *options)

  public fun <T : TType> resourceApplyAdam(
    `var`: Operand<*>,
    m: Operand<*>,
    v: Operand<*>,
    beta1Power: Operand<T>,
    beta2Power: Operand<T>,
    lr: Operand<T>,
    beta1: Operand<T>,
    beta2: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    vararg options: ResourceApplyAdam.Options
  ): ResourceApplyAdam = java.resourceApplyAdam<T>(`var`, m, v, beta1Power, beta2Power, lr, beta1,
      beta2, epsilon, grad, *options)

  public fun <T : TType> resourceApplyAdamWithAmsgrad(
    `var`: Operand<*>,
    m: Operand<*>,
    v: Operand<*>,
    vhat: Operand<*>,
    beta1Power: Operand<T>,
    beta2Power: Operand<T>,
    lr: Operand<T>,
    beta1: Operand<T>,
    beta2: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    vararg options: ResourceApplyAdamWithAmsgrad.Options
  ): ResourceApplyAdamWithAmsgrad = java.resourceApplyAdamWithAmsgrad<T>(`var`, m, v, vhat,
      beta1Power, beta2Power, lr, beta1, beta2, epsilon, grad, *options)

  public fun <T : TType> resourceApplyAddSign(
    `var`: Operand<*>,
    m: Operand<*>,
    lr: Operand<T>,
    alpha: Operand<T>,
    signDecay: Operand<T>,
    beta: Operand<T>,
    grad: Operand<T>,
    vararg options: ResourceApplyAddSign.Options
  ): ResourceApplyAddSign = java.resourceApplyAddSign<T>(`var`, m, lr, alpha, signDecay, beta, grad,
      *options)

  public fun <T : TType> resourceApplyCenteredRmsProp(
    `var`: Operand<*>,
    mg: Operand<*>,
    ms: Operand<*>,
    mom: Operand<*>,
    lr: Operand<T>,
    rho: Operand<T>,
    momentum: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    vararg options: ResourceApplyCenteredRmsProp.Options
  ): ResourceApplyCenteredRmsProp = java.resourceApplyCenteredRmsProp<T>(`var`, mg, ms, mom, lr,
      rho, momentum, epsilon, grad, *options)

  public fun <T : TType> resourceApplyFtrl(
    `var`: Operand<*>,
    accum: Operand<*>,
    linear: Operand<*>,
    grad: Operand<T>,
    lr: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    l2Shrinkage: Operand<T>,
    lrPower: Operand<T>,
    vararg options: ResourceApplyFtrl.Options
  ): ResourceApplyFtrl = java.resourceApplyFtrl<T>(`var`, accum, linear, grad, lr, l1, l2,
      l2Shrinkage, lrPower, *options)

  public fun <T : TType> resourceApplyGradientDescent(
    `var`: Operand<*>,
    alpha: Operand<T>,
    delta: Operand<T>,
    vararg options: ResourceApplyGradientDescent.Options
  ): ResourceApplyGradientDescent = java.resourceApplyGradientDescent<T>(`var`, alpha, delta,
      *options)

  public fun <T : TType> resourceApplyKerasMomentum(
    `var`: Operand<*>,
    accum: Operand<*>,
    lr: Operand<T>,
    grad: Operand<T>,
    momentum: Operand<T>,
    vararg options: ResourceApplyKerasMomentum.Options
  ): ResourceApplyKerasMomentum = java.resourceApplyKerasMomentum<T>(`var`, accum, lr, grad,
      momentum, *options)

  public fun <T : TType> resourceApplyMomentum(
    `var`: Operand<*>,
    accum: Operand<*>,
    lr: Operand<T>,
    grad: Operand<T>,
    momentum: Operand<T>,
    vararg options: ResourceApplyMomentum.Options
  ): ResourceApplyMomentum = java.resourceApplyMomentum<T>(`var`, accum, lr, grad, momentum,
      *options)

  public fun <T : TType> resourceApplyPowerSign(
    `var`: Operand<*>,
    m: Operand<*>,
    lr: Operand<T>,
    logbase: Operand<T>,
    signDecay: Operand<T>,
    beta: Operand<T>,
    grad: Operand<T>,
    vararg options: ResourceApplyPowerSign.Options
  ): ResourceApplyPowerSign = java.resourceApplyPowerSign<T>(`var`, m, lr, logbase, signDecay, beta,
      grad, *options)

  public fun <T : TType> resourceApplyProximalAdagrad(
    `var`: Operand<*>,
    accum: Operand<*>,
    lr: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    grad: Operand<T>,
    vararg options: ResourceApplyProximalAdagrad.Options
  ): ResourceApplyProximalAdagrad = java.resourceApplyProximalAdagrad<T>(`var`, accum, lr, l1, l2,
      grad, *options)

  public fun <T : TType> resourceApplyProximalGradientDescent(
    `var`: Operand<*>,
    alpha: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    delta: Operand<T>,
    vararg options: ResourceApplyProximalGradientDescent.Options
  ): ResourceApplyProximalGradientDescent = java.resourceApplyProximalGradientDescent<T>(`var`,
      alpha, l1, l2, delta, *options)

  public fun <T : TType> resourceApplyRmsProp(
    `var`: Operand<*>,
    ms: Operand<*>,
    mom: Operand<*>,
    lr: Operand<T>,
    rho: Operand<T>,
    momentum: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    vararg options: ResourceApplyRmsProp.Options
  ): ResourceApplyRmsProp = java.resourceApplyRmsProp<T>(`var`, ms, mom, lr, rho, momentum, epsilon,
      grad, *options)

  public fun <T : TType, U : TNumber> resourceSparseApplyAdadelta(
    `var`: Operand<*>,
    accum: Operand<*>,
    accumUpdate: Operand<*>,
    lr: Operand<T>,
    rho: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    vararg options: ResourceSparseApplyAdadelta.Options
  ): ResourceSparseApplyAdadelta = java.resourceSparseApplyAdadelta<T, U>(`var`, accum, accumUpdate,
      lr, rho, epsilon, grad, indices, *options)

  public fun <T : TType, U : TNumber> resourceSparseApplyAdagrad(
    `var`: Operand<*>,
    accum: Operand<*>,
    lr: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    vararg options: ResourceSparseApplyAdagrad.Options
  ): ResourceSparseApplyAdagrad = java.resourceSparseApplyAdagrad<T, U>(`var`, accum, lr, grad,
      indices, *options)

  public fun <T : TType, U : TNumber> resourceSparseApplyAdagradDa(
    `var`: Operand<*>,
    gradientAccumulator: Operand<*>,
    gradientSquaredAccumulator: Operand<*>,
    grad: Operand<T>,
    indices: Operand<U>,
    lr: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    globalStep: Operand<TInt64>,
    vararg options: ResourceSparseApplyAdagradDa.Options
  ): ResourceSparseApplyAdagradDa = java.resourceSparseApplyAdagradDa<T, U>(`var`,
      gradientAccumulator, gradientSquaredAccumulator, grad, indices, lr, l1, l2, globalStep,
      *options)

  public fun <T : TType, U : TNumber> resourceSparseApplyCenteredRmsProp(
    `var`: Operand<*>,
    mg: Operand<*>,
    ms: Operand<*>,
    mom: Operand<*>,
    lr: Operand<T>,
    rho: Operand<T>,
    momentum: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    vararg options: ResourceSparseApplyCenteredRmsProp.Options
  ): ResourceSparseApplyCenteredRmsProp = java.resourceSparseApplyCenteredRmsProp<T, U>(`var`, mg,
      ms, mom, lr, rho, momentum, epsilon, grad, indices, *options)

  public fun <T : TType, U : TNumber> resourceSparseApplyFtrl(
    `var`: Operand<*>,
    accum: Operand<*>,
    linear: Operand<*>,
    grad: Operand<T>,
    indices: Operand<U>,
    lr: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    l2Shrinkage: Operand<T>,
    lrPower: Operand<T>,
    vararg options: ResourceSparseApplyFtrl.Options
  ): ResourceSparseApplyFtrl = java.resourceSparseApplyFtrl<T, U>(`var`, accum, linear, grad,
      indices, lr, l1, l2, l2Shrinkage, lrPower, *options)

  public fun <T : TType, U : TNumber> resourceSparseApplyKerasMomentum(
    `var`: Operand<*>,
    accum: Operand<*>,
    lr: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    momentum: Operand<T>,
    vararg options: ResourceSparseApplyKerasMomentum.Options
  ): ResourceSparseApplyKerasMomentum = java.resourceSparseApplyKerasMomentum<T, U>(`var`, accum,
      lr, grad, indices, momentum, *options)

  public fun <T : TType, U : TNumber> resourceSparseApplyMomentum(
    `var`: Operand<*>,
    accum: Operand<*>,
    lr: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    momentum: Operand<T>,
    vararg options: ResourceSparseApplyMomentum.Options
  ): ResourceSparseApplyMomentum = java.resourceSparseApplyMomentum<T, U>(`var`, accum, lr, grad,
      indices, momentum, *options)

  public fun <T : TType, U : TNumber> resourceSparseApplyProximalAdagrad(
    `var`: Operand<*>,
    accum: Operand<*>,
    lr: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    vararg options: ResourceSparseApplyProximalAdagrad.Options
  ): ResourceSparseApplyProximalAdagrad = java.resourceSparseApplyProximalAdagrad<T, U>(`var`,
      accum, lr, l1, l2, grad, indices, *options)

  public fun <T : TType, U : TNumber> resourceSparseApplyProximalGradientDescent(
    `var`: Operand<*>,
    alpha: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    vararg options: ResourceSparseApplyProximalGradientDescent.Options
  ): ResourceSparseApplyProximalGradientDescent = java.resourceSparseApplyProximalGradientDescent<T,
      U>(`var`, alpha, l1, l2, grad, indices, *options)

  public fun <T : TType, U : TNumber> resourceSparseApplyRmsProp(
    `var`: Operand<*>,
    ms: Operand<*>,
    mom: Operand<*>,
    lr: Operand<T>,
    rho: Operand<T>,
    momentum: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    vararg options: ResourceSparseApplyRmsProp.Options
  ): ResourceSparseApplyRmsProp = java.resourceSparseApplyRmsProp<T, U>(`var`, ms, mom, lr, rho,
      momentum, epsilon, grad, indices, *options)

  public fun restore(
    prefix: Operand<TString>,
    tensorNames: Operand<TString>,
    shapeAndSlices: Operand<TString>,
    dtypes: List<DataType<*>>
  ): Restore = java.restore(prefix, tensorNames, shapeAndSlices, dtypes)

  public fun <T : TType> restoreSlice(
    filePattern: Operand<TString>,
    tensorName: Operand<TString>,
    shapeAndSlice: Operand<TString>,
    dt: DataType<T>,
    vararg options: RestoreSlice.Options
  ): RestoreSlice<T> = java.restoreSlice<T>(filePattern, tensorName, shapeAndSlice, dt, *options)

  public fun save(
    prefix: Operand<TString>,
    tensorNames: Operand<TString>,
    shapeAndSlices: Operand<TString>,
    tensors: Iterable<Operand<*>>
  ): Save = java.save(prefix, tensorNames, shapeAndSlices, tensors)

  public fun saveSlices(
    filename: Operand<TString>,
    tensorNames: Operand<TString>,
    shapesAndSlices: Operand<TString>,
    `data`: Iterable<Operand<*>>
  ): SaveSlices = java.saveSlices(filename, tensorNames, shapesAndSlices, data)

  public fun sdcaFprint(input: Operand<TString>): SdcaFprint = java.sdcaFprint(input)

  public fun sdcaShrinkL1(
    weights: Iterable<Operand<TFloat32>>,
    l1: Float,
    l2: Float
  ): SdcaShrinkL1 = java.sdcaShrinkL1(weights, l1, l2)

  public fun <T : TType, U : TNumber> sparseApplyAdadelta(
    `var`: Operand<T>,
    accum: Operand<T>,
    accumUpdate: Operand<T>,
    lr: Operand<T>,
    rho: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    vararg options: SparseApplyAdadelta.Options
  ): SparseApplyAdadelta<T> = java.sparseApplyAdadelta<T, U>(`var`, accum, accumUpdate, lr, rho,
      epsilon, grad, indices, *options)

  public fun <T : TType, U : TNumber> sparseApplyAdagradDa(
    `var`: Operand<T>,
    gradientAccumulator: Operand<T>,
    gradientSquaredAccumulator: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    lr: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    globalStep: Operand<TInt64>,
    vararg options: SparseApplyAdagradDa.Options
  ): SparseApplyAdagradDa<T> = java.sparseApplyAdagradDa<T, U>(`var`, gradientAccumulator,
      gradientSquaredAccumulator, grad, indices, lr, l1, l2, globalStep, *options)

  public fun <T : TType, U : TNumber> sparseApplyCenteredRmsProp(
    `var`: Operand<T>,
    mg: Operand<T>,
    ms: Operand<T>,
    mom: Operand<T>,
    lr: Operand<T>,
    rho: Operand<T>,
    momentum: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    vararg options: SparseApplyCenteredRmsProp.Options
  ): SparseApplyCenteredRmsProp<T> = java.sparseApplyCenteredRmsProp<T, U>(`var`, mg, ms, mom, lr,
      rho, momentum, epsilon, grad, indices, *options)

  public fun <T : TType, U : TNumber> sparseApplyFtrl(
    `var`: Operand<T>,
    accum: Operand<T>,
    linear: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    lr: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    l2Shrinkage: Operand<T>,
    lrPower: Operand<T>,
    vararg options: SparseApplyFtrl.Options
  ): SparseApplyFtrl<T> = java.sparseApplyFtrl<T, U>(`var`, accum, linear, grad, indices, lr, l1,
      l2, l2Shrinkage, lrPower, *options)

  public fun <T : TType, U : TNumber> sparseApplyMomentum(
    `var`: Operand<T>,
    accum: Operand<T>,
    lr: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    momentum: Operand<T>,
    vararg options: SparseApplyMomentum.Options
  ): SparseApplyMomentum<T> = java.sparseApplyMomentum<T, U>(`var`, accum, lr, grad, indices,
      momentum, *options)

  public fun <T : TType, U : TNumber> sparseApplyProximalAdagrad(
    `var`: Operand<T>,
    accum: Operand<T>,
    lr: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    vararg options: SparseApplyProximalAdagrad.Options
  ): SparseApplyProximalAdagrad<T> = java.sparseApplyProximalAdagrad<T, U>(`var`, accum, lr, l1, l2,
      grad, indices, *options)

  public fun <T : TType, U : TNumber> sparseApplyProximalGradientDescent(
    `var`: Operand<T>,
    alpha: Operand<T>,
    l1: Operand<T>,
    l2: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    vararg options: SparseApplyProximalGradientDescent.Options
  ): SparseApplyProximalGradientDescent<T> = java.sparseApplyProximalGradientDescent<T, U>(`var`,
      alpha, l1, l2, grad, indices, *options)

  public fun <T : TType, U : TNumber> sparseApplyRmsProp(
    `var`: Operand<T>,
    ms: Operand<T>,
    mom: Operand<T>,
    lr: Operand<T>,
    rho: Operand<T>,
    momentum: Operand<T>,
    epsilon: Operand<T>,
    grad: Operand<T>,
    indices: Operand<U>,
    vararg options: SparseApplyRmsProp.Options
  ): SparseApplyRmsProp<T> = java.sparseApplyRmsProp<T, U>(`var`, ms, mom, lr, rho, momentum,
      epsilon, grad, indices, *options)

  public fun <T : TType> tileGrad(input: Operand<T>, multiples: Operand<TInt32>): TileGrad<T> =
      java.tileGrad<T>(input, multiples)
}
