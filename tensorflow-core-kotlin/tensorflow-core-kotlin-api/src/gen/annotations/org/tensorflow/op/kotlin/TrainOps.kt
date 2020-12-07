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
 * An API for building `train` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class TrainOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.TrainOps = ops.java.train

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * Applies a gradient to a given accumulator.
     *
     *  Does not add if local_step is lesser than the accumulator's global_step.
     *
     * @param handle The handle to a accumulator.
     * @param localStep The local_step value at which the gradient was computed.
     * @param gradient A tensor of the gradient to be accumulated.
     * @return a new instance of AccumulatorApplyGradient
     * @see org.tensorflow.op.TrainOps.accumulatorApplyGradient
     */
    public fun <T : TType> accumulatorApplyGradient(
        handle: Operand<TString>,
        localStep: Operand<TInt64>,
        gradient: Operand<T>
    ): AccumulatorApplyGradient = java.accumulatorApplyGradient<T>(
        handle,
        localStep,
        gradient
    )

    /**
     * Returns the number of gradients aggregated in the given accumulators.
     *
     * @param handle The handle to an accumulator.
     * @return a new instance of AccumulatorNumAccumulated
     * @see org.tensorflow.op.TrainOps.accumulatorNumAccumulated
     */
    public fun accumulatorNumAccumulated(handle: Operand<TString>): AccumulatorNumAccumulated =
        java.accumulatorNumAccumulated(
            handle
        )

    /**
     * Updates the accumulator with a new value for global_step.
     *
     *  Logs warning if the accumulator's value is already higher than
     *  new_global_step.
     *
     * @param handle The handle to an accumulator.
     * @param newGlobalStep The new global_step value to set.
     * @return a new instance of AccumulatorSetGlobalStep
     * @see org.tensorflow.op.TrainOps.accumulatorSetGlobalStep
     */
    public fun accumulatorSetGlobalStep(handle: Operand<TString>, newGlobalStep: Operand<TInt64>):
        AccumulatorSetGlobalStep = java.accumulatorSetGlobalStep(
            handle,
            newGlobalStep
        )

    /**
     * Extracts the average gradient in the given ConditionalAccumulator.
     *
     *  The op blocks until sufficient (i.e., more than num_required)
     *  gradients have been accumulated.  If the accumulator has already
     *  aggregated more than num_required gradients, it returns the average of
     *  the accumulated gradients.  Also automatically increments the recorded
     *  global_step in the accumulator by 1, and resets the aggregate to 0.
     *
     * @param T data type for ` average()` output
     * @param handle The handle to an accumulator.
     * @param numRequired Number of gradients required before we return an aggregate.
     * @param dtype The data type of accumulated gradients. Needs to correspond to the type
     *  of the accumulator.
     * @return a new instance of AccumulatorTakeGradient
     * @see org.tensorflow.op.TrainOps.accumulatorTakeGradient
     */
    public fun <T : TType> accumulatorTakeGradient(
        handle: Operand<TString>,
        numRequired: Operand<TInt32>,
        dtype: DataType<T>
    ): AccumulatorTakeGradient<T> = java.accumulatorTakeGradient<T>(
        handle,
        numRequired,
        dtype
    )

    /**
     * Update '*var' according to the adadelta scheme.
     *
     *  accum = rho() * accum + (1 - rho()) * grad.square();
     *  update = (update_accum + epsilon).sqrt() * (accum + epsilon()).rsqrt() * grad;
     *  update_accum = rho() * update_accum + (1 - rho()) * update.square();
     *  var -= update;
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param accumUpdate Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param rho Decay factor. Must be a scalar.
     * @param epsilon Constant factor. Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ApplyAdadelta
     * @see org.tensorflow.op.TrainOps.applyAdadelta
     * @param useLocking If True, updating of the var, accum and update_accum tensors will be
     * protected by
     *  a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ApplyAdadelta.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the adagrad scheme.
     *
     *  accum += grad * grad
     *  var -= lr * grad * (1 / sqrt(accum))
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ApplyAdagrad
     * @see org.tensorflow.op.TrainOps.applyAdagrad
     * @param useLocking If `True`, updating of the var and accum tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param updateSlots @param updateSlots
     */
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
            useLocking?.let { org.tensorflow.op.train.ApplyAdagrad.useLocking(it) },
            updateSlots?.let { org.tensorflow.op.train.ApplyAdagrad.updateSlots(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the proximal adagrad scheme.
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param gradientAccumulator Should be from a Variable().
     * @param gradientSquaredAccumulator Should be from a Variable().
     * @param grad The gradient.
     * @param lr Scaling factor. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 regularization. Must be a scalar.
     * @param globalStep Training step number. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of ApplyAdagradDa
     * @see org.tensorflow.op.TrainOps.applyAdagradDa
     * @param useLocking If True, updating of the var and accum tensors will be protected by
     *  a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ApplyAdagradDa.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the Adam algorithm.
     *
     *  $$lr_t := \text{learning\_rate} * \sqrt{1 - beta_2^t} / (1 - beta_1^t)$$
     *  $$m_t := beta_1 * m_{t-1} + (1 - beta_1) * g$$
     *  $$v_t := beta_2 * v_{t-1} + (1 - beta_2) * g * g$$
     *  $$variable := variable - lr_t * m_t / (\sqrt{v_t} + \epsilon)$$
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param m Should be from a Variable().
     * @param v Should be from a Variable().
     * @param beta1Power Must be a scalar.
     * @param beta2Power Must be a scalar.
     * @param lr Scaling factor. Must be a scalar.
     * @param beta1 Momentum factor. Must be a scalar.
     * @param beta2 Momentum factor. Must be a scalar.
     * @param epsilon Ridge term. Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ApplyAdam
     * @see org.tensorflow.op.TrainOps.applyAdam
     * @param useLocking If `True`, updating of the var, m, and v tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param useNesterov If `True`, uses the nesterov update.
     */
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
            useLocking?.let { org.tensorflow.op.train.ApplyAdam.useLocking(it) },
            useNesterov?.let { org.tensorflow.op.train.ApplyAdam.useNesterov(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the AddSign update.
     *
     *  m_t <- beta1 * m_{t-1} + (1 - beta1) * g
     *  update <- (alpha + sign_decay * sign(g) *sign(m)) * g
     *  variable <- variable - lr_t * update
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param m Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param alpha Must be a scalar.
     * @param signDecay Must be a scalar.
     * @param beta Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ApplyAddSign
     * @see org.tensorflow.op.TrainOps.applyAddSign
     * @param useLocking If `True`, updating of the var and m tensors is
     *  protected by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ApplyAddSign.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the centered RMSProp algorithm.
     *
     *  The centered RMSProp algorithm uses an estimate of the centered second moment
     *  (i.e., the variance) for normalization, as opposed to regular RMSProp, which
     *  uses the (uncentered) second moment. This often helps with training, but is
     *  slightly more expensive in terms of computation and memory.
     *
     *  Note that in dense implementation of this algorithm, mg, ms, and mom will
     *  update even if the grad is zero, but in this sparse implementation, mg, ms,
     *  and mom will not update in iterations during which the grad is zero.
     *
     *  mean_square = decay * mean_square + (1-decay) * gradient ** 2
     *  mean_grad = decay * mean_grad + (1-decay) * gradient
     *
     *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon - mean_grad ** 2)
     *
     *  mg <- rho * mg_{t-1} + (1-rho) * grad
     *  ms <- rho * ms_{t-1} + (1-rho) * grad * grad
     *  mom <- momentum * mom_{t-1} + lr * grad / sqrt(ms - mg * mg + epsilon)
     *  var <- var - mom
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param mg Should be from a Variable().
     * @param ms Should be from a Variable().
     * @param mom Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param rho Decay rate. Must be a scalar.
     * @param momentum
     * @param epsilon Ridge term. Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ApplyCenteredRmsProp
     * @see org.tensorflow.op.TrainOps.applyCenteredRmsProp
     * @param useLocking If `True`, updating of the var, mg, ms, and mom tensors is
     *  protected by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ApplyCenteredRmsProp.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the Ftrl-proximal scheme.
     *
     *  grad_with_shrinkage = grad + 2 * l2_shrinkage * var
     *  accum_new = accum + grad * grad
     *  linear += grad_with_shrinkage -
     *      (accum_new^(-lr_power) - accum^(-lr_power)) / lr * var
     *  quadratic = 1.0 / (accum_new^(lr_power) * lr) + 2 * l2
     *  var = (sign(linear) * l1 - linear) / quadratic if |linear| > l1 else 0.0
     *  accum = accum_new
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param linear Should be from a Variable().
     * @param grad The gradient.
     * @param lr Scaling factor. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 shrinkage regularization. Must be a scalar.
     * @param l2Shrinkage
     * @param lrPower Scaling factor. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of ApplyFtrl
     * @see org.tensorflow.op.TrainOps.applyFtrl
     * @param useLocking If `True`, updating of the var and accum tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param multiplyLinearByLr @param multiplyLinearByLr
     */
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
            useLocking?.let { org.tensorflow.op.train.ApplyFtrl.useLocking(it) },
            multiplyLinearByLr?.let { org.tensorflow.op.train.ApplyFtrl.multiplyLinearByLr(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' by subtracting 'alpha' * 'delta' from it.
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param alpha Scaling factor. Must be a scalar.
     * @param delta The change.
     * @param options carries optional attributes values
     * @return a new instance of ApplyGradientDescent
     * @see org.tensorflow.op.TrainOps.applyGradientDescent
     * @param useLocking If `True`, the subtraction will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ApplyGradientDescent.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the momentum scheme.
     *
     *  Set use_nesterov = True if you want to use Nesterov momentum.
     *
     *  accum = accum * momentum + grad
     *  var -= lr * accum
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param grad The gradient.
     * @param momentum Momentum. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of ApplyMomentum
     * @see org.tensorflow.op.TrainOps.applyMomentum
     * @param useLocking If `True`, updating of the var and accum tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param useNesterov If `True`, the tensor passed to compute grad will be
     *  var - lr * momentum * accum, so in the end, the var you get is actually
     *  var - lr * momentum * accum.
     */
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
            useLocking?.let { org.tensorflow.op.train.ApplyMomentum.useLocking(it) },
            useNesterov?.let { org.tensorflow.op.train.ApplyMomentum.useNesterov(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the AddSign update.
     *
     *  m_t <- beta1 * m_{t-1} + (1 - beta1) * g
     *  update <- exp(logbase * sign_decay * sign(g) * sign(m_t)) * g
     *  variable <- variable - lr_t * update
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param m Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param logbase Must be a scalar.
     * @param signDecay Must be a scalar.
     * @param beta Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ApplyPowerSign
     * @see org.tensorflow.op.TrainOps.applyPowerSign
     * @param useLocking If `True`, updating of the var and m tensors is
     *  protected by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ApplyPowerSign.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' and '*accum' according to FOBOS with Adagrad learning rate.
     *
     *  accum += grad <i> grad
     *  prox_v = var - lr </i> grad <i> (1 / sqrt(accum))
     *  var = sign(prox_v)/(1+lr</i>l2) <i> max{|prox_v|-lr</i>l1,0}
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 regularization. Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ApplyProximalAdagrad
     * @see org.tensorflow.op.TrainOps.applyProximalAdagrad
     * @param useLocking If True, updating of the var and accum tensors will be protected by
     *  a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ApplyProximalAdagrad.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' as FOBOS algorithm with fixed learning rate.
     *
     *  prox_v = var - alpha <i> delta
     *  var = sign(prox_v)/(1+alpha</i>l2) <i> max{|prox_v|-alpha</i>l1,0}
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param alpha Scaling factor. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 regularization. Must be a scalar.
     * @param delta The change.
     * @param options carries optional attributes values
     * @return a new instance of ApplyProximalGradientDescent
     * @see org.tensorflow.op.TrainOps.applyProximalGradientDescent
     * @param useLocking If True, the subtraction will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ApplyProximalGradientDescent.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the RMSProp algorithm.
     *
     *  Note that in dense implementation of this algorithm, ms and mom will
     *  update even if the grad is zero, but in this sparse implementation, ms
     *  and mom will not update in iterations during which the grad is zero.
     *
     *  mean_square = decay * mean_square + (1-decay) * gradient ** 2
     *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon)
     *
     *  ms <- rho * ms_{t-1} + (1-rho) * grad * grad
     *  mom <- momentum * mom_{t-1} + lr * grad / sqrt(ms + epsilon)
     *  var <- var - mom
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param ms Should be from a Variable().
     * @param mom Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param rho Decay rate. Must be a scalar.
     * @param momentum
     * @param epsilon Ridge term. Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ApplyRmsProp
     * @see org.tensorflow.op.TrainOps.applyRmsProp
     * @param useLocking If `True`, updating of the var, ms, and mom tensors is protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ApplyRmsProp.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Multiplies slices of two tensors in batches.
     *
     *  Multiplies all slices of `Tensor` `x` and `y` (each slice can be
     *  viewed as an element of a batch), and arranges the individual results
     *  in a single output tensor of the same batch size. Each of the
     *  individual slices can optionally be adjointed (to adjoint a matrix
     *  means to transpose and conjugate it) before multiplication by setting
     *  the `adj_x` or `adj_y` flag to `True`, which are by default `False`.
     *
     *  The input tensors `x` and `y` are 2-D or higher with shape `&#91;..., r_x, c_x]`
     *  and `&#91;..., r_y, c_y]`.
     *
     *  The output tensor is 2-D or higher with shape `&#91;..., r_o, c_o]`, where:
     *
     *      r_o = c_x if adj_x else r_x
     *      c_o = r_y if adj_y else c_y
     *
     *  It is computed as:
     *
     *      output&#91;..., :, :] = matrix(x&#91;..., :, :]) * matrix(y&#91;..., :, :])
     *
     *  <i>NOTE</i>: `train.BatchMatMul` supports broadcasting in the batch dimensions. More
     *  about broadcasting
     *  &#91;here](http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html).
     *
     * @param T data type for ` output()` output
     * @param x 2-D or higher with shape `&#91;..., r_x, c_x]`.
     * @param y 2-D or higher with shape `&#91;..., r_y, c_y]`.
     * @param options carries optional attributes values
     * @return a new instance of BatchMatMul
     * @see org.tensorflow.op.TrainOps.batchMatMul
     * @param adjX If `True`, adjoint the slices of `x`. Defaults to `False`.
     * @param adjY If `True`, adjoint the slices of `y`. Defaults to `False`.
     */
    public fun <T : TType> batchMatMul(
        x: Operand<T>,
        y: Operand<T>,
        adjX: Boolean? = null,
        adjY: Boolean? = null
    ): BatchMatMul<T> = java.batchMatMul<T>(
        x,
        y,
        *listOfNotNull(
            adjX?.let { org.tensorflow.op.train.BatchMatMul.adjX(it) },
            adjY?.let { org.tensorflow.op.train.BatchMatMul.adjY(it) }
        ).toTypedArray()
    )

    /**
     * A conditional accumulator for aggregating gradients.
     *
     *  The accumulator accepts gradients marked with local_step greater or
     *  equal to the most recent global_step known to the accumulator. The
     *  average can be extracted from the accumulator, provided sufficient
     *  gradients have been accumulated. Extracting the average automatically
     *  resets the aggregate to 0, and increments the global_step recorded by
     *  the accumulator.
     *
     * @param dtype The type of the value being accumulated.
     * @param shape The shape of the values, can be &#91;], in which case shape is unknown.
     * @param options carries optional attributes values
     * @return a new instance of ConditionalAccumulator
     * @see org.tensorflow.op.TrainOps.conditionalAccumulator
     * @param container If non-empty, this accumulator is placed in the given container.
     *  Otherwise, a default container is used.
     * @param sharedName If non-empty, this accumulator will be shared under the
     *  given name across multiple sessions.
     * @param reductionType @param reductionType
     */
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
            container?.let { org.tensorflow.op.train.ConditionalAccumulator.container(it) },
            sharedName?.let { org.tensorflow.op.train.ConditionalAccumulator.sharedName(it) },
            reductionType?.let { org.tensorflow.op.train.ConditionalAccumulator.reductionType(it) }
        ).toTypedArray()
    )

    /**
     * Given a path to new and old vocabulary files, returns a remapping Tensor of
     *
     *  length `num_new_vocab`, where `remapping&#91;i]` contains the row number in the old
     *  vocabulary that corresponds to row `i` in the new vocabulary (starting at line
     *  `new_vocab_offset` and up to `num_new_vocab` entities), or `-1` if entry `i`
     *  in the new vocabulary is not in the old vocabulary.  The old vocabulary is
     *  constrained to the first `old_vocab_size` entries if `old_vocab_size` is not the
     *  default value of -1.
     *
     *  `num_vocab_offset` enables
     *  use in the partitioned variable case, and should generally be set through
     *  examining partitioning info.  The format of the files should be a text file,
     *  with each line containing a single entity within the vocabulary.
     *
     *  For example, with `new_vocab_file` a text file containing each of the following
     *  elements on a single line: `&#91;f0, f1, f2, f3]`, old_vocab_file = &#91;f1, f0, f3],
     *  `num_new_vocab = 3, new_vocab_offset = 1`, the returned remapping would be
     *  `&#91;0, -1, 2]`.
     *
     *  The op also returns a count of how many entries in the new vocabulary
     *  were present in the old vocabulary, which is used to calculate the number of
     *  values to initialize in a weight matrix remapping
     *
     *  This functionality can be used to remap both row vocabularies (typically,
     *  features) and column vocabularies (typically, classes) from TensorFlow
     *  checkpoints.  Note that the partitioning logic relies on contiguous vocabularies
     *  corresponding to div-partitioned variables.  Moreover, the underlying remapping
     *  uses an IndexTable (as opposed to an inexact CuckooTable), so client code should
     *  use the corresponding index_table_from_file() as the FeatureColumn framework
     *  does (as opposed to tf.feature_to_id(), which uses a CuckooTable).
     *
     * @param newVocabFile Path to the new vocab file.
     * @param oldVocabFile Path to the old vocab file.
     * @param newVocabOffset How many entries into the new vocab file to start reading.
     * @param numNewVocab Number of entries in the new vocab file to remap.
     * @param options carries optional attributes values
     * @return a new instance of GenerateVocabRemapping
     * @see org.tensorflow.op.TrainOps.generateVocabRemapping
     * @param oldVocabSize Number of entries in the old vocab file to consider.  If -1,
     *  use the entire old vocabulary.
     */
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
            oldVocabSize?.let { org.tensorflow.op.train.GenerateVocabRemapping.oldVocabSize(it) }
        ).toTypedArray()
    )

    /**
     * V2 format specific: merges the metadata files of sharded checkpoints.  The
     *
     *  result is one logical checkpoint, with one physical metadata file and renamed
     *  data files.
     *
     *  Intended for "grouping" multiple checkpoints in a sharded checkpoint setup.
     *
     *  If delete_old_dirs is true, attempts to delete recursively the dirname of each
     *  path in the input checkpoint_prefixes.  This is useful when those paths are non
     *  user-facing temporary locations.
     *
     * @param checkpointPrefixes prefixes of V2 checkpoints to merge.
     * @param destinationPrefix scalar.  The desired final prefix.  Allowed to be the same
     *  as one of the checkpoint_prefixes.
     * @param options carries optional attributes values
     * @return a new instance of MergeV2Checkpoints
     * @see org.tensorflow.op.TrainOps.mergeV2Checkpoints
     * @param deleteOldDirs see above.
     */
    public fun mergeV2Checkpoints(
        checkpointPrefixes: Operand<TString>,
        destinationPrefix: Operand<TString>,
        deleteOldDirs: Boolean? = null
    ): MergeV2Checkpoints = java.mergeV2Checkpoints(
        checkpointPrefixes,
        destinationPrefix,
        *listOfNotNull(
            deleteOldDirs?.let { org.tensorflow.op.train.MergeV2Checkpoints.deleteOldDirs(it) }
        ).toTypedArray()
    )

    /**
     * Training via negative sampling.
     *
     * @param wIn input word embedding.
     * @param wOut output word embedding.
     * @param examples A vector of word ids.
     * @param labels A vector of word ids.
     * @param lr
     * @param vocabCount Count of words in the vocabulary.
     * @param numNegativeSamples Number of negative samples per example.
     * @return a new instance of NegTrain
     * @see org.tensorflow.op.TrainOps.negTrain
     */
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

    /**
     * An identity op that triggers an error if a gradient is requested.
     *
     *  When executed in a graph, this op outputs its input tensor as-is.
     *
     *  When building ops to compute gradients, the TensorFlow gradient system
     *  will return an error when trying to lookup the gradient of this op,
     *  because no gradient must ever be registered for this function.  This
     *  op exists to prevent subtle bugs from silently returning unimplemented
     *  gradients in some corner cases.
     *
     * @param T data type for ` output()` output
     * @param input any tensor.
     * @param options carries optional attributes values
     * @return a new instance of PreventGradient
     * @see org.tensorflow.op.TrainOps.preventGradient
     * @param message Will be printed in the error when anyone tries to differentiate
     *  this operation.
     */
    public fun <T : TType> preventGradient(input: Operand<T>, message: String? = null):
        PreventGradient<T> = java.preventGradient<T>(
            input,
            *listOfNotNull(
                message?.let { org.tensorflow.op.train.PreventGradient.message(it) }
            ).toTypedArray()
        )

    /**
     * Update '*var' according to the adadelta scheme.
     *
     *  accum = rho() * accum + (1 - rho()) * grad.square();
     *  update = (update_accum + epsilon).sqrt() * (accum + epsilon()).rsqrt() * grad;
     *  update_accum = rho() * update_accum + (1 - rho()) * update.square();
     *  var -= update;
     *
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param accumUpdate Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param rho Decay factor. Must be a scalar.
     * @param epsilon Constant factor. Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyAdadelta
     * @see org.tensorflow.op.TrainOps.resourceApplyAdadelta
     * @param useLocking If True, updating of the var, accum and update_accum tensors will be
     * protected by
     *  a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyAdadelta.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the proximal adagrad scheme.
     *
     * @param var Should be from a Variable().
     * @param gradientAccumulator Should be from a Variable().
     * @param gradientSquaredAccumulator Should be from a Variable().
     * @param grad The gradient.
     * @param lr Scaling factor. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 regularization. Must be a scalar.
     * @param globalStep Training step number. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyAdagradDa
     * @see org.tensorflow.op.TrainOps.resourceApplyAdagradDa
     * @param useLocking If True, updating of the var and accum tensors will be protected by
     *  a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyAdagradDa.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the Adam algorithm.
     *
     *  $$\text{lr}_t := \mathrm{learning_rate} * \sqrt{1 - \beta_2^t} / (1 - \beta_1^t)$$
     *  $$m_t := \beta_1 * m_{t-1} + (1 - \beta_1) * g$$
     *  $$v_t := \beta_2 * v_{t-1} + (1 - \beta_2) * g * g$$
     *  $$\text{variable} := \text{variable} - \text{lr}_t * m_t / (\sqrt{v_t} + \epsilon)$$
     *
     * @param var Should be from a Variable().
     * @param m Should be from a Variable().
     * @param v Should be from a Variable().
     * @param beta1Power Must be a scalar.
     * @param beta2Power Must be a scalar.
     * @param lr Scaling factor. Must be a scalar.
     * @param beta1 Momentum factor. Must be a scalar.
     * @param beta2 Momentum factor. Must be a scalar.
     * @param epsilon Ridge term. Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyAdam
     * @see org.tensorflow.op.TrainOps.resourceApplyAdam
     * @param useLocking If `True`, updating of the var, m, and v tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param useNesterov If `True`, uses the nesterov update.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyAdam.useLocking(it) },
            useNesterov?.let { org.tensorflow.op.train.ResourceApplyAdam.useNesterov(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the Adam algorithm.
     *
     *  $$\text{lr}_t := \mathrm{learning_rate} * \sqrt{1 - \beta_2^t} / (1 - \beta_1^t)$$
     *  $$m_t := \beta_1 * m_{t-1} + (1 - \beta_1) * g$$
     *  $$v_t := \beta_2 * v_{t-1} + (1 - \beta_2) * g * g$$
     *  $$\hat{v}_t := max{\hat{v}_{t-1}, v_t}$$
     *  $$\text{variable} := \text{variable} - \text{lr}_t * m_t / (\sqrt{\hat{v}_t} + \epsilon)$$
     *
     * @param var Should be from a Variable().
     * @param m Should be from a Variable().
     * @param v Should be from a Variable().
     * @param vhat Should be from a Variable().
     * @param beta1Power Must be a scalar.
     * @param beta2Power Must be a scalar.
     * @param lr Scaling factor. Must be a scalar.
     * @param beta1 Momentum factor. Must be a scalar.
     * @param beta2 Momentum factor. Must be a scalar.
     * @param epsilon Ridge term. Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyAdamWithAmsgrad
     * @see org.tensorflow.op.TrainOps.resourceApplyAdamWithAmsgrad
     * @param useLocking If `True`, updating of the var, m, and v tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyAdamWithAmsgrad.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the AddSign update.
     *
     *  m_t <- beta1 * m_{t-1} + (1 - beta1) * g
     *  update <- (alpha + sign_decay * sign(g) *sign(m)) * g
     *  variable <- variable - lr_t * update
     *
     * @param var Should be from a Variable().
     * @param m Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param alpha Must be a scalar.
     * @param signDecay Must be a scalar.
     * @param beta Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyAddSign
     * @see org.tensorflow.op.TrainOps.resourceApplyAddSign
     * @param useLocking If `True`, updating of the var and m tensors is
     *  protected by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyAddSign.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the centered RMSProp algorithm.
     *
     *  The centered RMSProp algorithm uses an estimate of the centered second moment
     *  (i.e., the variance) for normalization, as opposed to regular RMSProp, which
     *  uses the (uncentered) second moment. This often helps with training, but is
     *  slightly more expensive in terms of computation and memory.
     *
     *  Note that in dense implementation of this algorithm, mg, ms, and mom will
     *  update even if the grad is zero, but in this sparse implementation, mg, ms,
     *  and mom will not update in iterations during which the grad is zero.
     *
     *  mean_square = decay * mean_square + (1-decay) * gradient ** 2
     *  mean_grad = decay * mean_grad + (1-decay) * gradient
     *
     *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon - mean_grad ** 2)
     *
     *  mg <- rho * mg_{t-1} + (1-rho) * grad
     *  ms <- rho * ms_{t-1} + (1-rho) * grad * grad
     *  mom <- momentum * mom_{t-1} + lr * grad / sqrt(ms - mg * mg + epsilon)
     *  var <- var - mom
     *
     * @param var Should be from a Variable().
     * @param mg Should be from a Variable().
     * @param ms Should be from a Variable().
     * @param mom Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param rho Decay rate. Must be a scalar.
     * @param momentum
     * @param epsilon Ridge term. Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyCenteredRmsProp
     * @see org.tensorflow.op.TrainOps.resourceApplyCenteredRmsProp
     * @param useLocking If `True`, updating of the var, mg, ms, and mom tensors is
     *  protected by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyCenteredRmsProp.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the Ftrl-proximal scheme.
     *
     *  grad_with_shrinkage = grad + 2 * l2_shrinkage * var
     *  accum_new = accum + grad_with_shrinkage * grad_with_shrinkage
     *  linear += grad_with_shrinkage +
     *      (accum_new^(-lr_power) - accum^(-lr_power)) / lr * var
     *  quadratic = 1.0 / (accum_new^(lr_power) * lr) + 2 * l2
     *  var = (sign(linear) * l1 - linear) / quadratic if |linear| > l1 else 0.0
     *  accum = accum_new
     *
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param linear Should be from a Variable().
     * @param grad The gradient.
     * @param lr Scaling factor. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 shrinkage regularization. Must be a scalar.
     * @param l2Shrinkage
     * @param lrPower Scaling factor. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyFtrl
     * @see org.tensorflow.op.TrainOps.resourceApplyFtrl
     * @param useLocking If `True`, updating of the var and accum tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param multiplyLinearByLr @param multiplyLinearByLr
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyFtrl.useLocking(it) },
            multiplyLinearByLr?.let { org.tensorflow.op.train.ResourceApplyFtrl.multiplyLinearByLr(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' by subtracting 'alpha' * 'delta' from it.
     *
     * @param var Should be from a Variable().
     * @param alpha Scaling factor. Must be a scalar.
     * @param delta The change.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyGradientDescent
     * @see org.tensorflow.op.TrainOps.resourceApplyGradientDescent
     * @param useLocking If `True`, the subtraction will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyGradientDescent.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the momentum scheme.
     *
     *  Set use_nesterov = True if you want to use Nesterov momentum.
     *
     *  accum = accum * momentum - lr * grad
     *  var += accum
     *
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param grad The gradient.
     * @param momentum Momentum. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyKerasMomentum
     * @see org.tensorflow.op.TrainOps.resourceApplyKerasMomentum
     * @param useLocking If `True`, updating of the var and accum tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param useNesterov If `True`, the tensor passed to compute grad will be
     *  var + momentum * accum, so in the end, the var you get is actually
     *  var + momentum * accum.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyKerasMomentum.useLocking(it) },
            useNesterov?.let { org.tensorflow.op.train.ResourceApplyKerasMomentum.useNesterov(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the momentum scheme.
     *
     *  Set use_nesterov = True if you want to use Nesterov momentum.
     *
     *  accum = accum * momentum + grad
     *  var -= lr * accum
     *
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param grad The gradient.
     * @param momentum Momentum. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyMomentum
     * @see org.tensorflow.op.TrainOps.resourceApplyMomentum
     * @param useLocking If `True`, updating of the var and accum tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param useNesterov If `True`, the tensor passed to compute grad will be
     *  var - lr * momentum * accum, so in the end, the var you get is actually
     *  var - lr * momentum * accum.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyMomentum.useLocking(it) },
            useNesterov?.let { org.tensorflow.op.train.ResourceApplyMomentum.useNesterov(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the AddSign update.
     *
     *  m_t <- beta1 * m_{t-1} + (1 - beta1) * g
     *  update <- exp(logbase * sign_decay * sign(g) * sign(m_t)) * g
     *  variable <- variable - lr_t * update
     *
     * @param var Should be from a Variable().
     * @param m Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param logbase Must be a scalar.
     * @param signDecay Must be a scalar.
     * @param beta Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyPowerSign
     * @see org.tensorflow.op.TrainOps.resourceApplyPowerSign
     * @param useLocking If `True`, updating of the var and m tensors is
     *  protected by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyPowerSign.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' and '*accum' according to FOBOS with Adagrad learning rate.
     *
     *  accum += grad <i> grad
     *  prox_v = var - lr </i> grad <i> (1 / sqrt(accum))
     *  var = sign(prox_v)/(1+lr</i>l2) <i> max{|prox_v|-lr</i>l1,0}
     *
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 regularization. Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyProximalAdagrad
     * @see org.tensorflow.op.TrainOps.resourceApplyProximalAdagrad
     * @param useLocking If True, updating of the var and accum tensors will be protected by
     *  a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyProximalAdagrad.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' as FOBOS algorithm with fixed learning rate.
     *
     *  prox_v = var - alpha <i> delta
     *  var = sign(prox_v)/(1+alpha</i>l2) <i> max{|prox_v|-alpha</i>l1,0}
     *
     * @param var Should be from a Variable().
     * @param alpha Scaling factor. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 regularization. Must be a scalar.
     * @param delta The change.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyProximalGradientDescent
     * @see org.tensorflow.op.TrainOps.resourceApplyProximalGradientDescent
     * @param useLocking If True, the subtraction will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyProximalGradientDescent.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the RMSProp algorithm.
     *
     *  Note that in dense implementation of this algorithm, ms and mom will
     *  update even if the grad is zero, but in this sparse implementation, ms
     *  and mom will not update in iterations during which the grad is zero.
     *
     *  mean_square = decay * mean_square + (1-decay) * gradient ** 2
     *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon)
     *
     *  ms <- rho * ms_{t-1} + (1-rho) * grad * grad
     *  mom <- momentum * mom_{t-1} + lr * grad / sqrt(ms + epsilon)
     *  var <- var - mom
     *
     * @param var Should be from a Variable().
     * @param ms Should be from a Variable().
     * @param mom Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param rho Decay rate. Must be a scalar.
     * @param momentum
     * @param epsilon Ridge term. Must be a scalar.
     * @param grad The gradient.
     * @param options carries optional attributes values
     * @return a new instance of ResourceApplyRmsProp
     * @see org.tensorflow.op.TrainOps.resourceApplyRmsProp
     * @param useLocking If `True`, updating of the var, ms, and mom tensors is protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceApplyRmsProp.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * var: Should be from a Variable().
     *
     * @param var
     * @param accum Should be from a Variable().
     * @param accumUpdate : Should be from a Variable().
     * @param lr Learning rate. Must be a scalar.
     * @param rho Decay factor. Must be a scalar.
     * @param epsilon Constant factor. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param options carries optional attributes values
     * @return a new instance of ResourceSparseApplyAdadelta
     * @see org.tensorflow.op.TrainOps.resourceSparseApplyAdadelta
     * @param useLocking If True, updating of the var and accum tensors will be protected by
     *  a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceSparseApplyAdadelta.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update relevant entries in '*var' and '*accum' according to the adagrad scheme.
     *
     *  That is for rows we have grad for, we update var and accum as follows:
     *  accum += grad * grad
     *  var -= lr * grad * (1 / sqrt(accum))
     *
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param lr Learning rate. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param options carries optional attributes values
     * @return a new instance of ResourceSparseApplyAdagrad
     * @see org.tensorflow.op.TrainOps.resourceSparseApplyAdagrad
     * @param useLocking If `True`, updating of the var and accum tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param updateSlots @param updateSlots
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceSparseApplyAdagrad.useLocking(it) },
            updateSlots?.let { org.tensorflow.op.train.ResourceSparseApplyAdagrad.updateSlots(it) }
        ).toTypedArray()
    )

    /**
     * Update entries in '*var' and '*accum' according to the proximal adagrad scheme.
     *
     * @param var Should be from a Variable().
     * @param gradientAccumulator Should be from a Variable().
     * @param gradientSquaredAccumulator Should be from a Variable().
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param lr Learning rate. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 regularization. Must be a scalar.
     * @param globalStep Training step number. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of ResourceSparseApplyAdagradDa
     * @see org.tensorflow.op.TrainOps.resourceSparseApplyAdagradDa
     * @param useLocking If True, updating of the var and accum tensors will be protected by
     *  a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceSparseApplyAdagradDa.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the centered RMSProp algorithm.
     *
     *  The centered RMSProp algorithm uses an estimate of the centered second moment
     *  (i.e., the variance) for normalization, as opposed to regular RMSProp, which
     *  uses the (uncentered) second moment. This often helps with training, but is
     *  slightly more expensive in terms of computation and memory.
     *
     *  Note that in dense implementation of this algorithm, mg, ms, and mom will
     *  update even if the grad is zero, but in this sparse implementation, mg, ms,
     *  and mom will not update in iterations during which the grad is zero.
     *
     *  mean_square = decay * mean_square + (1-decay) * gradient ** 2
     *  mean_grad = decay * mean_grad + (1-decay) * gradient
     *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon - mean_grad ** 2)
     *
     *  ms <- rho * ms_{t-1} + (1-rho) * grad * grad
     *  mom <- momentum * mom_{t-1} + lr * grad / sqrt(ms + epsilon)
     *  var <- var - mom
     *
     * @param var Should be from a Variable().
     * @param mg Should be from a Variable().
     * @param ms Should be from a Variable().
     * @param mom Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param rho Decay rate. Must be a scalar.
     * @param momentum
     * @param epsilon Ridge term. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var, ms and mom.
     * @param options carries optional attributes values
     * @return a new instance of ResourceSparseApplyCenteredRmsProp
     * @see org.tensorflow.op.TrainOps.resourceSparseApplyCenteredRmsProp
     * @param useLocking If `True`, updating of the var, mg, ms, and mom tensors is
     *  protected by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceSparseApplyCenteredRmsProp.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update relevant entries in '*var' according to the Ftrl-proximal scheme.
     *
     *  That is for rows we have grad for, we update var, accum and linear as follows:
     *  grad_with_shrinkage = grad + 2 * l2_shrinkage * var
     *  accum_new = accum + grad_with_shrinkage * grad_with_shrinkage
     *  linear += grad_with_shrinkage +
     *      (accum_new^(-lr_power) - accum^(-lr_power)) / lr * var
     *  quadratic = 1.0 / (accum_new^(lr_power) * lr) + 2 * l2
     *  var = (sign(linear) * l1 - linear) / quadratic if |linear| > l1 else 0.0
     *  accum = accum_new
     *
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param linear Should be from a Variable().
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param lr Scaling factor. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 shrinkage regularization. Must be a scalar.
     * @param l2Shrinkage
     * @param lrPower Scaling factor. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of ResourceSparseApplyFtrl
     * @see org.tensorflow.op.TrainOps.resourceSparseApplyFtrl
     * @param useLocking If `True`, updating of the var and accum tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param multiplyLinearByLr @param multiplyLinearByLr
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceSparseApplyFtrl.useLocking(it) },
            multiplyLinearByLr?.let {
                org.tensorflow.op.train.ResourceSparseApplyFtrl.multiplyLinearByLr(it)
            }
        ).toTypedArray()
    )

    /**
     * Update relevant entries in '*var' and '*accum' according to the momentum scheme.
     *
     *  Set use_nesterov = True if you want to use Nesterov momentum.
     *
     *  That is for rows we have grad for, we update var and accum as follows:
     *
     *  accum = accum * momentum - lr * grad
     *  var += accum
     *
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param lr Learning rate. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param momentum Momentum. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of ResourceSparseApplyKerasMomentum
     * @see org.tensorflow.op.TrainOps.resourceSparseApplyKerasMomentum
     * @param useLocking If `True`, updating of the var and accum tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param useNesterov If `True`, the tensor passed to compute grad will be
     *  var + momentum * accum, so in the end, the var you get is actually
     *  var + momentum * accum.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceSparseApplyKerasMomentum.useLocking(it) },
            useNesterov?.let { org.tensorflow.op.train.ResourceSparseApplyKerasMomentum.useNesterov(it) }
        ).toTypedArray()
    )

    /**
     * Update relevant entries in '*var' and '*accum' according to the momentum scheme.
     *
     *  Set use_nesterov = True if you want to use Nesterov momentum.
     *
     *  That is for rows we have grad for, we update var and accum as follows:
     *
     *  accum = accum * momentum + grad
     *  var -= lr * accum
     *
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param lr Learning rate. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param momentum Momentum. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of ResourceSparseApplyMomentum
     * @see org.tensorflow.op.TrainOps.resourceSparseApplyMomentum
     * @param useLocking If `True`, updating of the var and accum tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param useNesterov If `True`, the tensor passed to compute grad will be
     *  var - lr * momentum * accum, so in the end, the var you get is actually
     *  var - lr * momentum * accum.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceSparseApplyMomentum.useLocking(it) },
            useNesterov?.let { org.tensorflow.op.train.ResourceSparseApplyMomentum.useNesterov(it) }
        ).toTypedArray()
    )

    /**
     * Sparse update entries in '*var' and '*accum' according to FOBOS algorithm.
     *
     *  That is for rows we have grad for, we update var and accum as follows:
     *  accum += grad <i> grad
     *  prox_v = var
     *  prox_v -= lr </i> grad <i> (1 / sqrt(accum))
     *  var = sign(prox_v)/(1+lr</i>l2) <i> max{|prox_v|-lr</i>l1,0}
     *
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param lr Learning rate. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 regularization. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param options carries optional attributes values
     * @return a new instance of ResourceSparseApplyProximalAdagrad
     * @see org.tensorflow.op.TrainOps.resourceSparseApplyProximalAdagrad
     * @param useLocking If True, updating of the var and accum tensors will be protected by
     *  a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceSparseApplyProximalAdagrad.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Sparse update '*var' as FOBOS algorithm with fixed learning rate.
     *
     *  That is for rows we have grad for, we update var as follows:
     *  prox_v = var - alpha <i> grad
     *  var = sign(prox_v)/(1+alpha</i>l2) <i> max{|prox_v|-alpha</i>l1,0}
     *
     * @param var Should be from a Variable().
     * @param alpha Scaling factor. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 regularization. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param options carries optional attributes values
     * @return a new instance of ResourceSparseApplyProximalGradientDescent
     * @see org.tensorflow.op.TrainOps.resourceSparseApplyProximalGradientDescent
     * @param useLocking If True, the subtraction will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     */
    public fun <T : TType, U : TNumber> resourceSparseApplyProximalGradientDescent(
        `var`: Operand<*>,
        alpha: Operand<T>,
        l1: Operand<T>,
        l2: Operand<T>,
        grad: Operand<T>,
        indices: Operand<U>,
        useLocking: Boolean? = null
    ): ResourceSparseApplyProximalGradientDescent =
        java.resourceSparseApplyProximalGradientDescent<T, U>(
            `var`,
            alpha,
            l1,
            l2,
            grad,
            indices,
            *listOfNotNull(
                useLocking?.let {
                    org.tensorflow.op.train.ResourceSparseApplyProximalGradientDescent.useLocking(it)
                }
            ).toTypedArray()
        )

    /**
     * Update '*var' according to the RMSProp algorithm.
     *
     *  Note that in dense implementation of this algorithm, ms and mom will
     *  update even if the grad is zero, but in this sparse implementation, ms
     *  and mom will not update in iterations during which the grad is zero.
     *
     *  mean_square = decay * mean_square + (1-decay) * gradient ** 2
     *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon)
     *
     *  ms <- rho * ms_{t-1} + (1-rho) * grad * grad
     *  mom <- momentum * mom_{t-1} + lr * grad / sqrt(ms + epsilon)
     *  var <- var - mom
     *
     * @param var Should be from a Variable().
     * @param ms Should be from a Variable().
     * @param mom Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param rho Decay rate. Must be a scalar.
     * @param momentum
     * @param epsilon Ridge term. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var, ms and mom.
     * @param options carries optional attributes values
     * @return a new instance of ResourceSparseApplyRmsProp
     * @see org.tensorflow.op.TrainOps.resourceSparseApplyRmsProp
     * @param useLocking If `True`, updating of the var, ms, and mom tensors is protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.ResourceSparseApplyRmsProp.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Restores tensors from a V2 checkpoint.
     *
     *  For backward compatibility with the V1 format, this Op currently allows
     *  restoring from a V1 checkpoint as well:
     *    - This Op first attempts to find the V2 index file pointed to by "prefix", and
     *      if found proceed to read it as a V2 checkpoint;
     *    - Otherwise the V1 read path is invoked.
     *  Relying on this behavior is not recommended, as the ability to fall back to read
     *  V1 might be deprecated and eventually removed.
     *
     *  By default, restores the named tensors in full.  If the caller wishes to restore
     *  specific slices of stored tensors, "shape_and_slices" should be non-empty
     *  strings and correspondingly well-formed.
     *
     *  Callers must ensure all the named tensors are indeed stored in the checkpoint.
     *
     * @param prefix Must have a single element.  The prefix of a V2 checkpoint.
     * @param tensorNames shape {N}.  The names of the tensors to be restored.
     * @param shapeAndSlices shape {N}.  The slice specs of the tensors to be restored.
     *  Empty strings indicate that they are non-partitioned tensors.
     * @param dtypes shape {N}.  The list of expected dtype for the tensors.  Must match
     *  those stored in the checkpoint.
     * @return a new instance of Restore
     * @see org.tensorflow.op.TrainOps.restore
     */
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

    /**
     * Restores a tensor from checkpoint files.
     *
     *  This is like `Restore` except that restored tensor can be listed as filling
     *  only a slice of a larger tensor.  `shape_and_slice` specifies the shape of the
     *  larger tensor and the slice that the restored tensor covers.
     *
     *  The `shape_and_slice` input has the same format as the
     *  elements of the `shapes_and_slices` input of the `SaveSlices` op.
     *
     * @param T data type for ` tensor()` output
     * @param filePattern Must have a single element. The pattern of the files from
     *  which we read the tensor.
     * @param tensorName Must have a single element. The name of the tensor to be
     *  restored.
     * @param shapeAndSlice Scalar. The shapes and slice specifications to use when
     *  restoring a tensors.
     * @param dt The type of the tensor to be restored.
     * @param options carries optional attributes values
     * @return a new instance of RestoreSlice
     * @see org.tensorflow.op.TrainOps.restoreSlice
     * @param preferredShard Index of file to open first if multiple files match
     *  `file_pattern`. See the documentation for `Restore`.
     */
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
            preferredShard?.let { org.tensorflow.op.train.RestoreSlice.preferredShard(it) }
        ).toTypedArray()
    )

    /**
     * Saves tensors in V2 checkpoint format.
     *
     *  By default, saves the named tensors in full.  If the caller wishes to save
     *  specific slices of full tensors, "shape_and_slices" should be non-empty strings
     *  and correspondingly well-formed.
     *
     * @param prefix Must have a single element. The prefix of the V2 checkpoint to which we
     *  write the tensors.
     * @param tensorNames shape {N}. The names of the tensors to be saved.
     * @param shapeAndSlices shape {N}.  The slice specs of the tensors to be saved.
     *  Empty strings indicate that they are non-partitioned tensors.
     * @param tensors `N` tensors to save.
     * @return a new instance of Save
     * @see org.tensorflow.op.TrainOps.save
     */
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

    /**
     * Saves input tensors slices to disk.
     *
     *  This is like `Save` except that tensors can be listed in the saved file as being
     *  a slice of a larger tensor.  `shapes_and_slices` specifies the shape of the
     *  larger tensor and the slice that this tensor covers. `shapes_and_slices` must
     *  have as many elements as `tensor_names`.
     *
     *  Elements of the `shapes_and_slices` input must either be:
     *  <ul>
     *  <li>
     *  The empty string, in which case the corresponding tensor is
     *     saved normally.
     *  </li>
     *  <li>
     *  A string of the form `dim0 dim1 ... dimN-1 slice-spec` where the
     *     `dimI` are the dimensions of the larger tensor and `slice-spec`
     *     specifies what part is covered by the tensor to save.
     *  </li>
     *  </ul>
     *  `slice-spec` itself is a `:`-separated list: `slice0:slice1:...:sliceN-1`
     *  where each `sliceI` is either:
     *  <ul>
     *  <li>
     *  The string `-` meaning that the slice covers all indices of this dimension
     *  </li>
     *  <li>
     *  `start,length` where `start` and `length` are integers.  In that
     *     case the slice covers `length` indices starting at `start`.
     *  </li>
     *  </ul>
     *  See also `Save`.
     *
     * @param filename Must have a single element. The name of the file to which we write the
     *  tensor.
     * @param tensorNames Shape `&#91;N]`. The names of the tensors to be saved.
     * @param shapesAndSlices Shape `&#91;N]`.  The shapes and slice specifications to use when
     *  saving the tensors.
     * @param data `N` tensors to save.
     * @return a new instance of SaveSlices
     * @see org.tensorflow.op.TrainOps.saveSlices
     */
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

    /**
     * Computes fingerprints of the input strings.
     *
     * @param input vector of strings to compute fingerprints on.
     * @return a new instance of SdcaFprint
     * @see org.tensorflow.op.TrainOps.sdcaFprint
     */
    public fun sdcaFprint(input: Operand<TString>): SdcaFprint = java.sdcaFprint(
        input
    )

    /**
     * Applies L1 regularization shrink step on the parameters.
     *
     * @param weights a list of vectors where each value is the weight associated with a
     *  feature group.
     * @param l1 Symmetric l1 regularization strength.
     * @param l2 Symmetric l2 regularization strength. Should be a positive float.
     * @return a new instance of SdcaShrinkL1
     * @see org.tensorflow.op.TrainOps.sdcaShrinkL1
     */
    public fun sdcaShrinkL1(
        weights: Iterable<Operand<TFloat32>>,
        l1: Float,
        l2: Float
    ): SdcaShrinkL1 = java.sdcaShrinkL1(
        weights,
        l1,
        l2
    )

    /**
     * var: Should be from a Variable().
     *
     * @param T data type for ` out()` output
     * @param var
     * @param accum Should be from a Variable().
     * @param accumUpdate : Should be from a Variable().
     * @param lr Learning rate. Must be a scalar.
     * @param rho Decay factor. Must be a scalar.
     * @param epsilon Constant factor. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param options carries optional attributes values
     * @return a new instance of SparseApplyAdadelta
     * @see org.tensorflow.op.TrainOps.sparseApplyAdadelta
     * @param useLocking If True, updating of the var and accum tensors will be protected by
     *  a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.SparseApplyAdadelta.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update entries in '*var' and '*accum' according to the proximal adagrad scheme.
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param gradientAccumulator Should be from a Variable().
     * @param gradientSquaredAccumulator Should be from a Variable().
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param lr Learning rate. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 regularization. Must be a scalar.
     * @param globalStep Training step number. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of SparseApplyAdagradDa
     * @see org.tensorflow.op.TrainOps.sparseApplyAdagradDa
     * @param useLocking If True, updating of the var and accum tensors will be protected by
     *  a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.SparseApplyAdagradDa.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the centered RMSProp algorithm.
     *
     *  The centered RMSProp algorithm uses an estimate of the centered second moment
     *  (i.e., the variance) for normalization, as opposed to regular RMSProp, which
     *  uses the (uncentered) second moment. This often helps with training, but is
     *  slightly more expensive in terms of computation and memory.
     *
     *  Note that in dense implementation of this algorithm, mg, ms, and mom will
     *  update even if the grad is zero, but in this sparse implementation, mg, ms,
     *  and mom will not update in iterations during which the grad is zero.
     *
     *  mean_square = decay * mean_square + (1-decay) * gradient ** 2
     *  mean_grad = decay * mean_grad + (1-decay) * gradient
     *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon - mean_grad ** 2)
     *
     *  $$ms <- rho * ms_{t-1} + (1-rho) * grad * grad$$
     *  $$mom <- momentum * mom_{t-1} + lr * grad / sqrt(ms + epsilon)$$
     *  $$var <- var - mom$$
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param mg Should be from a Variable().
     * @param ms Should be from a Variable().
     * @param mom Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param rho Decay rate. Must be a scalar.
     * @param momentum
     * @param epsilon Ridge term. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var, ms and mom.
     * @param options carries optional attributes values
     * @return a new instance of SparseApplyCenteredRmsProp
     * @see org.tensorflow.op.TrainOps.sparseApplyCenteredRmsProp
     * @param useLocking If `True`, updating of the var, mg, ms, and mom tensors is
     *  protected by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.SparseApplyCenteredRmsProp.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update relevant entries in '*var' according to the Ftrl-proximal scheme.
     *
     *  That is for rows we have grad for, we update var, accum and linear as follows:
     *  grad_with_shrinkage = grad + 2 * l2_shrinkage * var
     *  accum_new = accum + grad * grad
     *  linear += grad_with_shrinkage -
     *      (accum_new^(-lr_power) - accum^(-lr_power)) / lr * var
     *  quadratic = 1.0 / (accum_new^(lr_power) * lr) + 2 * l2
     *  var = (sign(linear) * l1 - linear) / quadratic if |linear| > l1 else 0.0
     *  accum = accum_new
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param linear Should be from a Variable().
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param lr Scaling factor. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 shrinkage regularization. Must be a scalar.
     * @param l2Shrinkage
     * @param lrPower Scaling factor. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of SparseApplyFtrl
     * @see org.tensorflow.op.TrainOps.sparseApplyFtrl
     * @param useLocking If `True`, updating of the var and accum tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param multiplyLinearByLr @param multiplyLinearByLr
     */
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
            useLocking?.let { org.tensorflow.op.train.SparseApplyFtrl.useLocking(it) },
            multiplyLinearByLr?.let { org.tensorflow.op.train.SparseApplyFtrl.multiplyLinearByLr(it) }
        ).toTypedArray()
    )

    /**
     * Update relevant entries in '*var' and '*accum' according to the momentum scheme.
     *
     *  Set use_nesterov = True if you want to use Nesterov momentum.
     *
     *  That is for rows we have grad for, we update var and accum as follows:
     *
     *  $$accum = accum * momentum + grad$$
     *  $$var -= lr * accum$$
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param lr Learning rate. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param momentum Momentum. Must be a scalar.
     * @param options carries optional attributes values
     * @return a new instance of SparseApplyMomentum
     * @see org.tensorflow.op.TrainOps.sparseApplyMomentum
     * @param useLocking If `True`, updating of the var and accum tensors will be protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     * @param useNesterov If `True`, the tensor passed to compute grad will be
     *  var - lr * momentum * accum, so in the end, the var you get is actually
     *  var - lr * momentum * accum.
     */
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
            useLocking?.let { org.tensorflow.op.train.SparseApplyMomentum.useLocking(it) },
            useNesterov?.let { org.tensorflow.op.train.SparseApplyMomentum.useNesterov(it) }
        ).toTypedArray()
    )

    /**
     * Sparse update entries in '*var' and '*accum' according to FOBOS algorithm.
     *
     *  That is for rows we have grad for, we update var and accum as follows:
     *  $$accum += grad <i> grad$$
     *  $$prox_v = var$$
     *  $$prox_v -= lr </i> grad <i> (1 / sqrt(accum))$$
     *  $$var = sign(prox_v)/(1+lr</i>l2) <i> max{|prox_v|-lr</i>l1,0}$$
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param accum Should be from a Variable().
     * @param lr Learning rate. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 regularization. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param options carries optional attributes values
     * @return a new instance of SparseApplyProximalAdagrad
     * @see org.tensorflow.op.TrainOps.sparseApplyProximalAdagrad
     * @param useLocking If True, updating of the var and accum tensors will be protected by
     *  a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.SparseApplyProximalAdagrad.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Sparse update '*var' as FOBOS algorithm with fixed learning rate.
     *
     *  That is for rows we have grad for, we update var as follows:
     *  $$prox_v = var - alpha <i> grad$$
     *  $$var = sign(prox_v)/(1+alpha</i>l2) <i> max{|prox_v|-alpha</i>l1,0}$$
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param alpha Scaling factor. Must be a scalar.
     * @param l1 L1 regularization. Must be a scalar.
     * @param l2 L2 regularization. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var and accum.
     * @param options carries optional attributes values
     * @return a new instance of SparseApplyProximalGradientDescent
     * @see org.tensorflow.op.TrainOps.sparseApplyProximalGradientDescent
     * @param useLocking If True, the subtraction will be protected by a lock;
     *  otherwise the behavior is undefined, but may exhibit less contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.SparseApplyProximalGradientDescent.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Update '*var' according to the RMSProp algorithm.
     *
     *  Note that in dense implementation of this algorithm, ms and mom will
     *  update even if the grad is zero, but in this sparse implementation, ms
     *  and mom will not update in iterations during which the grad is zero.
     *
     *  mean_square = decay * mean_square + (1-decay) * gradient ** 2
     *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon)
     *
     *  $$ms <- rho * ms_{t-1} + (1-rho) * grad * grad$$
     *  $$mom <- momentum * mom_{t-1} + lr * grad / sqrt(ms + epsilon)$$
     *  $$var <- var - mom$$
     *
     * @param T data type for ` out()` output
     * @param var Should be from a Variable().
     * @param ms Should be from a Variable().
     * @param mom Should be from a Variable().
     * @param lr Scaling factor. Must be a scalar.
     * @param rho Decay rate. Must be a scalar.
     * @param momentum
     * @param epsilon Ridge term. Must be a scalar.
     * @param grad The gradient.
     * @param indices A vector of indices into the first dimension of var, ms and mom.
     * @param options carries optional attributes values
     * @return a new instance of SparseApplyRmsProp
     * @see org.tensorflow.op.TrainOps.sparseApplyRmsProp
     * @param useLocking If `True`, updating of the var, ms, and mom tensors is protected
     *  by a lock; otherwise the behavior is undefined, but may exhibit less
     *  contention.
     */
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
            useLocking?.let { org.tensorflow.op.train.SparseApplyRmsProp.useLocking(it) }
        ).toTypedArray()
    )

    /**
     * Returns the gradient of `Tile`.
     *
     *  Since `Tile` takes an input and repeats the input `multiples` times
     *  along each dimension, `train.TileGrad` takes in `multiples` and aggregates
     *  each repeated tile of `input` into `output`.
     *
     * @param T data type for ` output()` output
     * @param input
     * @param multiples
     * @return a new instance of TileGrad
     * @see org.tensorflow.op.TrainOps.tileGrad
     */
    public fun <T : TType> tileGrad(input: Operand<T>, multiples: Operand<TInt32>): TileGrad<T> =
        java.tileGrad<T>(
            input,
            multiples
        )
}
