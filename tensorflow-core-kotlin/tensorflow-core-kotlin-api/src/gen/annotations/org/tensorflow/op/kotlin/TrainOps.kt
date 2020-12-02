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
	): AccumulatorApplyGradient = java.accumulatorApplyGradient<T>(	
		handle,
		localStep,
		gradient
		)

	public fun accumulatorNumAccumulated(handle: Operand<TString>): AccumulatorNumAccumulated =
			java.accumulatorNumAccumulated(	
		handle
		)

	public fun accumulatorSetGlobalStep(handle: Operand<TString>, newGlobalStep: Operand<TInt64>):
			AccumulatorSetGlobalStep = java.accumulatorSetGlobalStep(	
		handle,
		newGlobalStep
		)

	public fun <T : TType> accumulatorTakeGradient(
		handle: Operand<TString>,
		numRequired: Operand<TInt32>,
		dtype: DataType<T>
	): AccumulatorTakeGradient<T> = java.accumulatorTakeGradient<T>(	
		handle,
		numRequired,
		dtype
		)

	public fun <T : TType> applyAdadelta(
		`var`: Operand<T>,
		accum: Operand<T>,
		accumUpdate: Operand<T>,
		lr: Operand<T>,
		rho: Operand<T>,
		epsilon: Operand<T>,
		grad: Operand<T>,
		useLocking: Boolean? = null
	): ApplyAdadelta<T> = java.applyAdadelta<T>(	
		`var`,
		accum,
		accumUpdate,
		lr,
		rho,
		epsilon,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ApplyAdadelta.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> applyAdagrad(
		`var`: Operand<T>,
		accum: Operand<T>,
		lr: Operand<T>,
		grad: Operand<T>,
		useLocking: Boolean? = null,
		updateSlots: Boolean? = null
	): ApplyAdagrad<T> = java.applyAdagrad<T>(	
		`var`,
		accum,
		lr,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ApplyAdagrad.useLocking(it) },
			updateSlots?.let{ org.tensorflow.op.train.ApplyAdagrad.updateSlots(it) }
		).toTypedArray()
		)

	public fun <T : TType> applyAdagradDa(
		`var`: Operand<T>,
		gradientAccumulator: Operand<T>,
		gradientSquaredAccumulator: Operand<T>,
		grad: Operand<T>,
		lr: Operand<T>,
		l1: Operand<T>,
		l2: Operand<T>,
		globalStep: Operand<TInt64>,
		useLocking: Boolean? = null
	): ApplyAdagradDa<T> = java.applyAdagradDa<T>(	
		`var`,
		gradientAccumulator,
		gradientSquaredAccumulator,
		grad,
		lr,
		l1,
		l2,
		globalStep,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ApplyAdagradDa.useLocking(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null,
		useNesterov: Boolean? = null
	): ApplyAdam<T> = java.applyAdam<T>(	
		`var`,
		m,
		v,
		beta1Power,
		beta2Power,
		lr,
		beta1,
		beta2,
		epsilon,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ApplyAdam.useLocking(it) },
			useNesterov?.let{ org.tensorflow.op.train.ApplyAdam.useNesterov(it) }
		).toTypedArray()
		)

	public fun <T : TType> applyAddSign(
		`var`: Operand<T>,
		m: Operand<T>,
		lr: Operand<T>,
		alpha: Operand<T>,
		signDecay: Operand<T>,
		beta: Operand<T>,
		grad: Operand<T>,
		useLocking: Boolean? = null
	): ApplyAddSign<T> = java.applyAddSign<T>(	
		`var`,
		m,
		lr,
		alpha,
		signDecay,
		beta,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ApplyAddSign.useLocking(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null
	): ApplyCenteredRmsProp<T> = java.applyCenteredRmsProp<T>(	
		`var`,
		mg,
		ms,
		mom,
		lr,
		rho,
		momentum,
		epsilon,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ApplyCenteredRmsProp.useLocking(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null,
		multiplyLinearByLr: Boolean? = null
	): ApplyFtrl<T> = java.applyFtrl<T>(	
		`var`,
		accum,
		linear,
		grad,
		lr,
		l1,
		l2,
		l2Shrinkage,
		lrPower,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ApplyFtrl.useLocking(it) },
			multiplyLinearByLr?.let{ org.tensorflow.op.train.ApplyFtrl.multiplyLinearByLr(it) }
		).toTypedArray()
		)

	public fun <T : TType> applyGradientDescent(
		`var`: Operand<T>,
		alpha: Operand<T>,
		delta: Operand<T>,
		useLocking: Boolean? = null
	): ApplyGradientDescent<T> = java.applyGradientDescent<T>(	
		`var`,
		alpha,
		delta,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ApplyGradientDescent.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> applyMomentum(
		`var`: Operand<T>,
		accum: Operand<T>,
		lr: Operand<T>,
		grad: Operand<T>,
		momentum: Operand<T>,
		useLocking: Boolean? = null,
		useNesterov: Boolean? = null
	): ApplyMomentum<T> = java.applyMomentum<T>(	
		`var`,
		accum,
		lr,
		grad,
		momentum,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ApplyMomentum.useLocking(it) },
			useNesterov?.let{ org.tensorflow.op.train.ApplyMomentum.useNesterov(it) }
		).toTypedArray()
		)

	public fun <T : TType> applyPowerSign(
		`var`: Operand<T>,
		m: Operand<T>,
		lr: Operand<T>,
		logbase: Operand<T>,
		signDecay: Operand<T>,
		beta: Operand<T>,
		grad: Operand<T>,
		useLocking: Boolean? = null
	): ApplyPowerSign<T> = java.applyPowerSign<T>(	
		`var`,
		m,
		lr,
		logbase,
		signDecay,
		beta,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ApplyPowerSign.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> applyProximalAdagrad(
		`var`: Operand<T>,
		accum: Operand<T>,
		lr: Operand<T>,
		l1: Operand<T>,
		l2: Operand<T>,
		grad: Operand<T>,
		useLocking: Boolean? = null
	): ApplyProximalAdagrad<T> = java.applyProximalAdagrad<T>(	
		`var`,
		accum,
		lr,
		l1,
		l2,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ApplyProximalAdagrad.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> applyProximalGradientDescent(
		`var`: Operand<T>,
		alpha: Operand<T>,
		l1: Operand<T>,
		l2: Operand<T>,
		delta: Operand<T>,
		useLocking: Boolean? = null
	): ApplyProximalGradientDescent<T> = java.applyProximalGradientDescent<T>(	
		`var`,
		alpha,
		l1,
		l2,
		delta,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ApplyProximalGradientDescent.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> applyRmsProp(
		`var`: Operand<T>,
		ms: Operand<T>,
		mom: Operand<T>,
		lr: Operand<T>,
		rho: Operand<T>,
		momentum: Operand<T>,
		epsilon: Operand<T>,
		grad: Operand<T>,
		useLocking: Boolean? = null
	): ApplyRmsProp<T> = java.applyRmsProp<T>(	
		`var`,
		ms,
		mom,
		lr,
		rho,
		momentum,
		epsilon,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ApplyRmsProp.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> batchMatMul(
		x: Operand<T>,
		y: Operand<T>,
		adjX: Boolean? = null,
		adjY: Boolean? = null
	): BatchMatMul<T> = java.batchMatMul<T>(	
		x,
		y,
		*listOfNotNull(
			adjX?.let{ org.tensorflow.op.train.BatchMatMul.adjX(it) },
			adjY?.let{ org.tensorflow.op.train.BatchMatMul.adjY(it) }
		).toTypedArray()
		)

	public fun <T : TType> conditionalAccumulator(
		dtype: DataType<T>,
		shape: Shape,
		container: String? = null,
		sharedName: String? = null,
		reductionType: String? = null
	): ConditionalAccumulator = java.conditionalAccumulator<T>(	
		dtype,
		shape,
		*listOfNotNull(
			container?.let{ org.tensorflow.op.train.ConditionalAccumulator.container(it) },
			sharedName?.let{ org.tensorflow.op.train.ConditionalAccumulator.sharedName(it) },
			reductionType?.let{ org.tensorflow.op.train.ConditionalAccumulator.reductionType(it) }
		).toTypedArray()
		)

	public fun generateVocabRemapping(
		newVocabFile: Operand<TString>,
		oldVocabFile: Operand<TString>,
		newVocabOffset: Long,
		numNewVocab: Long,
		oldVocabSize: Long? = null
	): GenerateVocabRemapping = java.generateVocabRemapping(	
		newVocabFile,
		oldVocabFile,
		newVocabOffset,
		numNewVocab,
		*listOfNotNull(
			oldVocabSize?.let{ org.tensorflow.op.train.GenerateVocabRemapping.oldVocabSize(it) }
		).toTypedArray()
		)

	public fun mergeV2Checkpoints(
		checkpointPrefixes: Operand<TString>,
		destinationPrefix: Operand<TString>,
		deleteOldDirs: Boolean? = null
	): MergeV2Checkpoints = java.mergeV2Checkpoints(	
		checkpointPrefixes,
		destinationPrefix,
		*listOfNotNull(
			deleteOldDirs?.let{ org.tensorflow.op.train.MergeV2Checkpoints.deleteOldDirs(it) }
		).toTypedArray()
		)

	public fun negTrain(
		wIn: Operand<TFloat32>,
		wOut: Operand<TFloat32>,
		examples: Operand<TInt32>,
		labels: Operand<TInt32>,
		lr: Operand<TFloat32>,
		vocabCount: List<Long>,
		numNegativeSamples: Long
	): NegTrain = java.negTrain(	
		wIn,
		wOut,
		examples,
		labels,
		lr,
		vocabCount,
		numNegativeSamples
		)

	public fun <T : TType> preventGradient(input: Operand<T>, message: String? = null):
			PreventGradient<T> = java.preventGradient<T>(	
		input,
		*listOfNotNull(
			message?.let{ org.tensorflow.op.train.PreventGradient.message(it) }
		).toTypedArray()
		)

	public fun <T : TType> resourceApplyAdadelta(
		`var`: Operand<*>,
		accum: Operand<*>,
		accumUpdate: Operand<*>,
		lr: Operand<T>,
		rho: Operand<T>,
		epsilon: Operand<T>,
		grad: Operand<T>,
		useLocking: Boolean? = null
	): ResourceApplyAdadelta = java.resourceApplyAdadelta<T>(	
		`var`,
		accum,
		accumUpdate,
		lr,
		rho,
		epsilon,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyAdadelta.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> resourceApplyAdagradDa(
		`var`: Operand<*>,
		gradientAccumulator: Operand<*>,
		gradientSquaredAccumulator: Operand<*>,
		grad: Operand<T>,
		lr: Operand<T>,
		l1: Operand<T>,
		l2: Operand<T>,
		globalStep: Operand<TInt64>,
		useLocking: Boolean? = null
	): ResourceApplyAdagradDa = java.resourceApplyAdagradDa<T>(	
		`var`,
		gradientAccumulator,
		gradientSquaredAccumulator,
		grad,
		lr,
		l1,
		l2,
		globalStep,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyAdagradDa.useLocking(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null,
		useNesterov: Boolean? = null
	): ResourceApplyAdam = java.resourceApplyAdam<T>(	
		`var`,
		m,
		v,
		beta1Power,
		beta2Power,
		lr,
		beta1,
		beta2,
		epsilon,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyAdam.useLocking(it) },
			useNesterov?.let{ org.tensorflow.op.train.ResourceApplyAdam.useNesterov(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null
	): ResourceApplyAdamWithAmsgrad = java.resourceApplyAdamWithAmsgrad<T>(	
		`var`,
		m,
		v,
		vhat,
		beta1Power,
		beta2Power,
		lr,
		beta1,
		beta2,
		epsilon,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyAdamWithAmsgrad.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> resourceApplyAddSign(
		`var`: Operand<*>,
		m: Operand<*>,
		lr: Operand<T>,
		alpha: Operand<T>,
		signDecay: Operand<T>,
		beta: Operand<T>,
		grad: Operand<T>,
		useLocking: Boolean? = null
	): ResourceApplyAddSign = java.resourceApplyAddSign<T>(	
		`var`,
		m,
		lr,
		alpha,
		signDecay,
		beta,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyAddSign.useLocking(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null
	): ResourceApplyCenteredRmsProp = java.resourceApplyCenteredRmsProp<T>(	
		`var`,
		mg,
		ms,
		mom,
		lr,
		rho,
		momentum,
		epsilon,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyCenteredRmsProp.useLocking(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null,
		multiplyLinearByLr: Boolean? = null
	): ResourceApplyFtrl = java.resourceApplyFtrl<T>(	
		`var`,
		accum,
		linear,
		grad,
		lr,
		l1,
		l2,
		l2Shrinkage,
		lrPower,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyFtrl.useLocking(it) },
			multiplyLinearByLr?.let{ org.tensorflow.op.train.ResourceApplyFtrl.multiplyLinearByLr(it) }
		).toTypedArray()
		)

	public fun <T : TType> resourceApplyGradientDescent(
		`var`: Operand<*>,
		alpha: Operand<T>,
		delta: Operand<T>,
		useLocking: Boolean? = null
	): ResourceApplyGradientDescent = java.resourceApplyGradientDescent<T>(	
		`var`,
		alpha,
		delta,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyGradientDescent.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> resourceApplyKerasMomentum(
		`var`: Operand<*>,
		accum: Operand<*>,
		lr: Operand<T>,
		grad: Operand<T>,
		momentum: Operand<T>,
		useLocking: Boolean? = null,
		useNesterov: Boolean? = null
	): ResourceApplyKerasMomentum = java.resourceApplyKerasMomentum<T>(	
		`var`,
		accum,
		lr,
		grad,
		momentum,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyKerasMomentum.useLocking(it) },
			useNesterov?.let{ org.tensorflow.op.train.ResourceApplyKerasMomentum.useNesterov(it) }
		).toTypedArray()
		)

	public fun <T : TType> resourceApplyMomentum(
		`var`: Operand<*>,
		accum: Operand<*>,
		lr: Operand<T>,
		grad: Operand<T>,
		momentum: Operand<T>,
		useLocking: Boolean? = null,
		useNesterov: Boolean? = null
	): ResourceApplyMomentum = java.resourceApplyMomentum<T>(	
		`var`,
		accum,
		lr,
		grad,
		momentum,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyMomentum.useLocking(it) },
			useNesterov?.let{ org.tensorflow.op.train.ResourceApplyMomentum.useNesterov(it) }
		).toTypedArray()
		)

	public fun <T : TType> resourceApplyPowerSign(
		`var`: Operand<*>,
		m: Operand<*>,
		lr: Operand<T>,
		logbase: Operand<T>,
		signDecay: Operand<T>,
		beta: Operand<T>,
		grad: Operand<T>,
		useLocking: Boolean? = null
	): ResourceApplyPowerSign = java.resourceApplyPowerSign<T>(	
		`var`,
		m,
		lr,
		logbase,
		signDecay,
		beta,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyPowerSign.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> resourceApplyProximalAdagrad(
		`var`: Operand<*>,
		accum: Operand<*>,
		lr: Operand<T>,
		l1: Operand<T>,
		l2: Operand<T>,
		grad: Operand<T>,
		useLocking: Boolean? = null
	): ResourceApplyProximalAdagrad = java.resourceApplyProximalAdagrad<T>(	
		`var`,
		accum,
		lr,
		l1,
		l2,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyProximalAdagrad.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> resourceApplyProximalGradientDescent(
		`var`: Operand<*>,
		alpha: Operand<T>,
		l1: Operand<T>,
		l2: Operand<T>,
		delta: Operand<T>,
		useLocking: Boolean? = null
	): ResourceApplyProximalGradientDescent = java.resourceApplyProximalGradientDescent<T>(	
		`var`,
		alpha,
		l1,
		l2,
		delta,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyProximalGradientDescent.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> resourceApplyRmsProp(
		`var`: Operand<*>,
		ms: Operand<*>,
		mom: Operand<*>,
		lr: Operand<T>,
		rho: Operand<T>,
		momentum: Operand<T>,
		epsilon: Operand<T>,
		grad: Operand<T>,
		useLocking: Boolean? = null
	): ResourceApplyRmsProp = java.resourceApplyRmsProp<T>(	
		`var`,
		ms,
		mom,
		lr,
		rho,
		momentum,
		epsilon,
		grad,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceApplyRmsProp.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> resourceSparseApplyAdadelta(
		`var`: Operand<*>,
		accum: Operand<*>,
		accumUpdate: Operand<*>,
		lr: Operand<T>,
		rho: Operand<T>,
		epsilon: Operand<T>,
		grad: Operand<T>,
		indices: Operand<U>,
		useLocking: Boolean? = null
	): ResourceSparseApplyAdadelta = java.resourceSparseApplyAdadelta<T, U>(	
		`var`,
		accum,
		accumUpdate,
		lr,
		rho,
		epsilon,
		grad,
		indices,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceSparseApplyAdadelta.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> resourceSparseApplyAdagrad(
		`var`: Operand<*>,
		accum: Operand<*>,
		lr: Operand<T>,
		grad: Operand<T>,
		indices: Operand<U>,
		useLocking: Boolean? = null,
		updateSlots: Boolean? = null
	): ResourceSparseApplyAdagrad = java.resourceSparseApplyAdagrad<T, U>(	
		`var`,
		accum,
		lr,
		grad,
		indices,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceSparseApplyAdagrad.useLocking(it) },
			updateSlots?.let{ org.tensorflow.op.train.ResourceSparseApplyAdagrad.updateSlots(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null
	): ResourceSparseApplyAdagradDa = java.resourceSparseApplyAdagradDa<T, U>(	
		`var`,
		gradientAccumulator,
		gradientSquaredAccumulator,
		grad,
		indices,
		lr,
		l1,
		l2,
		globalStep,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceSparseApplyAdagradDa.useLocking(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null
	): ResourceSparseApplyCenteredRmsProp = java.resourceSparseApplyCenteredRmsProp<T, U>(	
		`var`,
		mg,
		ms,
		mom,
		lr,
		rho,
		momentum,
		epsilon,
		grad,
		indices,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceSparseApplyCenteredRmsProp.useLocking(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null,
		multiplyLinearByLr: Boolean? = null
	): ResourceSparseApplyFtrl = java.resourceSparseApplyFtrl<T, U>(	
		`var`,
		accum,
		linear,
		grad,
		indices,
		lr,
		l1,
		l2,
		l2Shrinkage,
		lrPower,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceSparseApplyFtrl.useLocking(it) },
			multiplyLinearByLr?.let{ org.tensorflow.op.train.ResourceSparseApplyFtrl.multiplyLinearByLr(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> resourceSparseApplyKerasMomentum(
		`var`: Operand<*>,
		accum: Operand<*>,
		lr: Operand<T>,
		grad: Operand<T>,
		indices: Operand<U>,
		momentum: Operand<T>,
		useLocking: Boolean? = null,
		useNesterov: Boolean? = null
	): ResourceSparseApplyKerasMomentum = java.resourceSparseApplyKerasMomentum<T, U>(	
		`var`,
		accum,
		lr,
		grad,
		indices,
		momentum,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceSparseApplyKerasMomentum.useLocking(it) },
			useNesterov?.let{ org.tensorflow.op.train.ResourceSparseApplyKerasMomentum.useNesterov(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> resourceSparseApplyMomentum(
		`var`: Operand<*>,
		accum: Operand<*>,
		lr: Operand<T>,
		grad: Operand<T>,
		indices: Operand<U>,
		momentum: Operand<T>,
		useLocking: Boolean? = null,
		useNesterov: Boolean? = null
	): ResourceSparseApplyMomentum = java.resourceSparseApplyMomentum<T, U>(	
		`var`,
		accum,
		lr,
		grad,
		indices,
		momentum,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceSparseApplyMomentum.useLocking(it) },
			useNesterov?.let{ org.tensorflow.op.train.ResourceSparseApplyMomentum.useNesterov(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> resourceSparseApplyProximalAdagrad(
		`var`: Operand<*>,
		accum: Operand<*>,
		lr: Operand<T>,
		l1: Operand<T>,
		l2: Operand<T>,
		grad: Operand<T>,
		indices: Operand<U>,
		useLocking: Boolean? = null
	): ResourceSparseApplyProximalAdagrad = java.resourceSparseApplyProximalAdagrad<T, U>(	
		`var`,
		accum,
		lr,
		l1,
		l2,
		grad,
		indices,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceSparseApplyProximalAdagrad.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> resourceSparseApplyProximalGradientDescent(
		`var`: Operand<*>,
		alpha: Operand<T>,
		l1: Operand<T>,
		l2: Operand<T>,
		grad: Operand<T>,
		indices: Operand<U>,
		useLocking: Boolean? = null
	): ResourceSparseApplyProximalGradientDescent = java.resourceSparseApplyProximalGradientDescent<T,
			U>(	
		`var`,
		alpha,
		l1,
		l2,
		grad,
		indices,
		*listOfNotNull(
			useLocking?.let{
			org.tensorflow.op.train.ResourceSparseApplyProximalGradientDescent.useLocking(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null
	): ResourceSparseApplyRmsProp = java.resourceSparseApplyRmsProp<T, U>(	
		`var`,
		ms,
		mom,
		lr,
		rho,
		momentum,
		epsilon,
		grad,
		indices,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.ResourceSparseApplyRmsProp.useLocking(it) }
		).toTypedArray()
		)

	public fun restore(
		prefix: Operand<TString>,
		tensorNames: Operand<TString>,
		shapeAndSlices: Operand<TString>,
		dtypes: List<DataType<*>>
	): Restore = java.restore(	
		prefix,
		tensorNames,
		shapeAndSlices,
		dtypes
		)

	public fun <T : TType> restoreSlice(
		filePattern: Operand<TString>,
		tensorName: Operand<TString>,
		shapeAndSlice: Operand<TString>,
		dt: DataType<T>,
		preferredShard: Long? = null
	): RestoreSlice<T> = java.restoreSlice<T>(	
		filePattern,
		tensorName,
		shapeAndSlice,
		dt,
		*listOfNotNull(
			preferredShard?.let{ org.tensorflow.op.train.RestoreSlice.preferredShard(it) }
		).toTypedArray()
		)

	public fun save(
		prefix: Operand<TString>,
		tensorNames: Operand<TString>,
		shapeAndSlices: Operand<TString>,
		tensors: Iterable<Operand<*>>
	): Save = java.save(	
		prefix,
		tensorNames,
		shapeAndSlices,
		tensors
		)

	public fun saveSlices(
		filename: Operand<TString>,
		tensorNames: Operand<TString>,
		shapesAndSlices: Operand<TString>,
		`data`: Iterable<Operand<*>>
	): SaveSlices = java.saveSlices(	
		filename,
		tensorNames,
		shapesAndSlices,
		data
		)

	public fun sdcaFprint(input: Operand<TString>): SdcaFprint = java.sdcaFprint(	
		input
		)

	public fun sdcaShrinkL1(
		weights: Iterable<Operand<TFloat32>>,
		l1: Float,
		l2: Float
	): SdcaShrinkL1 = java.sdcaShrinkL1(	
		weights,
		l1,
		l2
		)

	public fun <T : TType, U : TNumber> sparseApplyAdadelta(
		`var`: Operand<T>,
		accum: Operand<T>,
		accumUpdate: Operand<T>,
		lr: Operand<T>,
		rho: Operand<T>,
		epsilon: Operand<T>,
		grad: Operand<T>,
		indices: Operand<U>,
		useLocking: Boolean? = null
	): SparseApplyAdadelta<T> = java.sparseApplyAdadelta<T, U>(	
		`var`,
		accum,
		accumUpdate,
		lr,
		rho,
		epsilon,
		grad,
		indices,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.SparseApplyAdadelta.useLocking(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null
	): SparseApplyAdagradDa<T> = java.sparseApplyAdagradDa<T, U>(	
		`var`,
		gradientAccumulator,
		gradientSquaredAccumulator,
		grad,
		indices,
		lr,
		l1,
		l2,
		globalStep,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.SparseApplyAdagradDa.useLocking(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null
	): SparseApplyCenteredRmsProp<T> = java.sparseApplyCenteredRmsProp<T, U>(	
		`var`,
		mg,
		ms,
		mom,
		lr,
		rho,
		momentum,
		epsilon,
		grad,
		indices,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.SparseApplyCenteredRmsProp.useLocking(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null,
		multiplyLinearByLr: Boolean? = null
	): SparseApplyFtrl<T> = java.sparseApplyFtrl<T, U>(	
		`var`,
		accum,
		linear,
		grad,
		indices,
		lr,
		l1,
		l2,
		l2Shrinkage,
		lrPower,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.SparseApplyFtrl.useLocking(it) },
			multiplyLinearByLr?.let{ org.tensorflow.op.train.SparseApplyFtrl.multiplyLinearByLr(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> sparseApplyMomentum(
		`var`: Operand<T>,
		accum: Operand<T>,
		lr: Operand<T>,
		grad: Operand<T>,
		indices: Operand<U>,
		momentum: Operand<T>,
		useLocking: Boolean? = null,
		useNesterov: Boolean? = null
	): SparseApplyMomentum<T> = java.sparseApplyMomentum<T, U>(	
		`var`,
		accum,
		lr,
		grad,
		indices,
		momentum,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.SparseApplyMomentum.useLocking(it) },
			useNesterov?.let{ org.tensorflow.op.train.SparseApplyMomentum.useNesterov(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> sparseApplyProximalAdagrad(
		`var`: Operand<T>,
		accum: Operand<T>,
		lr: Operand<T>,
		l1: Operand<T>,
		l2: Operand<T>,
		grad: Operand<T>,
		indices: Operand<U>,
		useLocking: Boolean? = null
	): SparseApplyProximalAdagrad<T> = java.sparseApplyProximalAdagrad<T, U>(	
		`var`,
		accum,
		lr,
		l1,
		l2,
		grad,
		indices,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.SparseApplyProximalAdagrad.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType, U : TNumber> sparseApplyProximalGradientDescent(
		`var`: Operand<T>,
		alpha: Operand<T>,
		l1: Operand<T>,
		l2: Operand<T>,
		grad: Operand<T>,
		indices: Operand<U>,
		useLocking: Boolean? = null
	): SparseApplyProximalGradientDescent<T> = java.sparseApplyProximalGradientDescent<T, U>(	
		`var`,
		alpha,
		l1,
		l2,
		grad,
		indices,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.SparseApplyProximalGradientDescent.useLocking(it) }
		).toTypedArray()
		)

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
		useLocking: Boolean? = null
	): SparseApplyRmsProp<T> = java.sparseApplyRmsProp<T, U>(	
		`var`,
		ms,
		mom,
		lr,
		rho,
		momentum,
		epsilon,
		grad,
		indices,
		*listOfNotNull(
			useLocking?.let{ org.tensorflow.op.train.SparseApplyRmsProp.useLocking(it) }
		).toTypedArray()
		)

	public fun <T : TType> tileGrad(input: Operand<T>, multiples: Operand<TInt32>): TileGrad<T> =
			java.tileGrad<T>(	
		input,
		multiples
		)
}
