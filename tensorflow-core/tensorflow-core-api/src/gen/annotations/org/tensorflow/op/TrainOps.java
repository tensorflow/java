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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.train.AccumulatorApplyGradient;
import org.tensorflow.op.train.AccumulatorNumAccumulated;
import org.tensorflow.op.train.AccumulatorSetGlobalStep;
import org.tensorflow.op.train.AccumulatorTakeGradient;
import org.tensorflow.op.train.ApplyAdadelta;
import org.tensorflow.op.train.ApplyAdagrad;
import org.tensorflow.op.train.ApplyAdagradDa;
import org.tensorflow.op.train.ApplyAdam;
import org.tensorflow.op.train.ApplyAddSign;
import org.tensorflow.op.train.ApplyCenteredRmsProp;
import org.tensorflow.op.train.ApplyFtrl;
import org.tensorflow.op.train.ApplyGradientDescent;
import org.tensorflow.op.train.ApplyMomentum;
import org.tensorflow.op.train.ApplyPowerSign;
import org.tensorflow.op.train.ApplyProximalAdagrad;
import org.tensorflow.op.train.ApplyProximalGradientDescent;
import org.tensorflow.op.train.ApplyRmsProp;
import org.tensorflow.op.train.BatchMatMul;
import org.tensorflow.op.train.ConditionalAccumulator;
import org.tensorflow.op.train.GenerateVocabRemapping;
import org.tensorflow.op.train.MergeV2Checkpoints;
import org.tensorflow.op.train.NegTrain;
import org.tensorflow.op.train.PreventGradient;
import org.tensorflow.op.train.ResourceApplyAdadelta;
import org.tensorflow.op.train.ResourceApplyAdagradDa;
import org.tensorflow.op.train.ResourceApplyAdam;
import org.tensorflow.op.train.ResourceApplyAdamWithAmsgrad;
import org.tensorflow.op.train.ResourceApplyAddSign;
import org.tensorflow.op.train.ResourceApplyCenteredRmsProp;
import org.tensorflow.op.train.ResourceApplyFtrl;
import org.tensorflow.op.train.ResourceApplyGradientDescent;
import org.tensorflow.op.train.ResourceApplyKerasMomentum;
import org.tensorflow.op.train.ResourceApplyMomentum;
import org.tensorflow.op.train.ResourceApplyPowerSign;
import org.tensorflow.op.train.ResourceApplyProximalAdagrad;
import org.tensorflow.op.train.ResourceApplyProximalGradientDescent;
import org.tensorflow.op.train.ResourceApplyRmsProp;
import org.tensorflow.op.train.ResourceSparseApplyAdadelta;
import org.tensorflow.op.train.ResourceSparseApplyAdagrad;
import org.tensorflow.op.train.ResourceSparseApplyAdagradDa;
import org.tensorflow.op.train.ResourceSparseApplyCenteredRmsProp;
import org.tensorflow.op.train.ResourceSparseApplyFtrl;
import org.tensorflow.op.train.ResourceSparseApplyKerasMomentum;
import org.tensorflow.op.train.ResourceSparseApplyMomentum;
import org.tensorflow.op.train.ResourceSparseApplyProximalAdagrad;
import org.tensorflow.op.train.ResourceSparseApplyProximalGradientDescent;
import org.tensorflow.op.train.ResourceSparseApplyRmsProp;
import org.tensorflow.op.train.Restore;
import org.tensorflow.op.train.RestoreSlice;
import org.tensorflow.op.train.Save;
import org.tensorflow.op.train.SaveSlices;
import org.tensorflow.op.train.SdcaFprint;
import org.tensorflow.op.train.SdcaShrinkL1;
import org.tensorflow.op.train.SparseApplyAdadelta;
import org.tensorflow.op.train.SparseApplyAdagradDa;
import org.tensorflow.op.train.SparseApplyCenteredRmsProp;
import org.tensorflow.op.train.SparseApplyFtrl;
import org.tensorflow.op.train.SparseApplyMomentum;
import org.tensorflow.op.train.SparseApplyProximalAdagrad;
import org.tensorflow.op.train.SparseApplyProximalGradientDescent;
import org.tensorflow.op.train.SparseApplyRmsProp;
import org.tensorflow.op.train.TileGrad;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code train} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class TrainOps {
  private final Scope scope;

  private final Ops ops;

  TrainOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * Applies a gradient to a given accumulator.
   *  Does not add if local_step is lesser than the accumulator's global_step.
   *
   * @param handle The handle to a accumulator.
   * @param localStep The local_step value at which the gradient was computed.
   * @param gradient A tensor of the gradient to be accumulated.
   * @return a new instance of AccumulatorApplyGradient
   */
  public AccumulatorApplyGradient accumulatorApplyGradient(Operand<TString> handle,
      Operand<TInt64> localStep, Operand<? extends TType> gradient) {
    return AccumulatorApplyGradient.create(scope, handle, localStep, gradient);
  }

  /**
   * Returns the number of gradients aggregated in the given accumulators.
   *
   * @param handle The handle to an accumulator.
   * @return a new instance of AccumulatorNumAccumulated
   */
  public AccumulatorNumAccumulated accumulatorNumAccumulated(Operand<TString> handle) {
    return AccumulatorNumAccumulated.create(scope, handle);
  }

  /**
   * Updates the accumulator with a new value for global_step.
   *  Logs warning if the accumulator's value is already higher than
   *  new_global_step.
   *
   * @param handle The handle to an accumulator.
   * @param newGlobalStep The new global_step value to set.
   * @return a new instance of AccumulatorSetGlobalStep
   */
  public AccumulatorSetGlobalStep accumulatorSetGlobalStep(Operand<TString> handle,
      Operand<TInt64> newGlobalStep) {
    return AccumulatorSetGlobalStep.create(scope, handle, newGlobalStep);
  }

  /**
   * Extracts the average gradient in the given ConditionalAccumulator.
   *  The op blocks until sufficient (i.e., more than num_required)
   *  gradients have been accumulated.  If the accumulator has already
   *  aggregated more than num_required gradients, it returns the average of
   *  the accumulated gradients.  Also automatically increments the recorded
   *  global_step in the accumulator by 1, and resets the aggregate to 0.
   *
   * @param <T> data type for {@code average} output
   * @param handle The handle to an accumulator.
   * @param numRequired Number of gradients required before we return an aggregate.
   * @param dtype The data type of accumulated gradients. Needs to correspond to the type
   *  of the accumulator.
   * @param <T> data type for {@code AccumulatorTakeGradient} output and operands
   * @return a new instance of AccumulatorTakeGradient
   */
  public <T extends TType> AccumulatorTakeGradient<T> accumulatorTakeGradient(
      Operand<TString> handle, Operand<TInt32> numRequired, Class<T> dtype) {
    return AccumulatorTakeGradient.create(scope, handle, numRequired, dtype);
  }

  /**
   * Update '*var' according to the adadelta scheme.
   *  accum = rho() * accum + (1 - rho()) * grad.square();
   *  update = (update_accum + epsilon).sqrt() * (accum + epsilon()).rsqrt() * grad;
   *  update_accum = rho() * update_accum + (1 - rho()) * update.square();
   *  var -= update;
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param accumUpdate Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param rho Decay factor. Must be a scalar.
   * @param epsilon Constant factor. Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyAdadelta} output and operands
   * @return a new instance of ApplyAdadelta
   */
  public <T extends TType> ApplyAdadelta<T> applyAdadelta(Operand<T> var, Operand<T> accum,
      Operand<T> accumUpdate, Operand<T> lr, Operand<T> rho, Operand<T> epsilon, Operand<T> grad,
      ApplyAdadelta.Options... options) {
    return ApplyAdadelta.create(scope, var, accum, accumUpdate, lr, rho, epsilon, grad, options);
  }

  /**
   * Update '*var' according to the adagrad scheme.
   *  accum += grad * grad
   *  var -= lr * grad * (1 / sqrt(accum))
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyAdagrad} output and operands
   * @return a new instance of ApplyAdagrad
   */
  public <T extends TType> ApplyAdagrad<T> applyAdagrad(Operand<T> var, Operand<T> accum,
      Operand<T> lr, Operand<T> grad, ApplyAdagrad.Options... options) {
    return ApplyAdagrad.create(scope, var, accum, lr, grad, options);
  }

  /**
   * Update '*var' according to the proximal adagrad scheme.
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param gradientAccumulator Should be from a Variable().
   * @param gradientSquaredAccumulator Should be from a Variable().
   * @param grad The gradient.
   * @param lr Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 regularization. Must be a scalar.
   * @param globalStep Training step number. Must be a scalar.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyAdagradDA} output and operands
   * @return a new instance of ApplyAdagradDa
   */
  public <T extends TType> ApplyAdagradDa<T> applyAdagradDa(Operand<T> var,
      Operand<T> gradientAccumulator, Operand<T> gradientSquaredAccumulator, Operand<T> grad,
      Operand<T> lr, Operand<T> l1, Operand<T> l2, Operand<TInt64> globalStep,
      ApplyAdagradDa.Options... options) {
    return ApplyAdagradDa.create(scope, var, gradientAccumulator, gradientSquaredAccumulator, grad, lr, l1, l2, globalStep, options);
  }

  /**
   * Update '*var' according to the Adam algorithm.
   *  $$lr_t := \text{learning_rate} * \sqrt{1 - beta_2^t} / (1 - beta_1^t)$$
   *  $$m_t := beta_1 * m_{t-1} + (1 - beta_1) * g$$
   *  $$v_t := beta_2 * v_{t-1} + (1 - beta_2) * g * g$$
   *  $$variable := variable - lr_t * m_t / (\sqrt{v_t} + \epsilon)$$
   *
   * @param <T> data type for {@code out} output
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
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyAdam} output and operands
   * @return a new instance of ApplyAdam
   */
  public <T extends TType> ApplyAdam<T> applyAdam(Operand<T> var, Operand<T> m, Operand<T> v,
      Operand<T> beta1Power, Operand<T> beta2Power, Operand<T> lr, Operand<T> beta1,
      Operand<T> beta2, Operand<T> epsilon, Operand<T> grad, ApplyAdam.Options... options) {
    return ApplyAdam.create(scope, var, m, v, beta1Power, beta2Power, lr, beta1, beta2, epsilon, grad, options);
  }

  /**
   * Update '*var' according to the AddSign update.
   *  m_t &lt;- beta1 * m_{t-1} + (1 - beta1) * g
   *  update &lt;- (alpha + sign_decay * sign(g) *sign(m)) * g
   *  variable &lt;- variable - lr_t * update
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param m Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param alpha Must be a scalar.
   * @param signDecay Must be a scalar.
   * @param beta Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyAddSign} output and operands
   * @return a new instance of ApplyAddSign
   */
  public <T extends TType> ApplyAddSign<T> applyAddSign(Operand<T> var, Operand<T> m, Operand<T> lr,
      Operand<T> alpha, Operand<T> signDecay, Operand<T> beta, Operand<T> grad,
      ApplyAddSign.Options... options) {
    return ApplyAddSign.create(scope, var, m, lr, alpha, signDecay, beta, grad, options);
  }

  /**
   * Update '*var' according to the centered RMSProp algorithm.
   *  The centered RMSProp algorithm uses an estimate of the centered second moment
   *  (i.e., the variance) for normalization, as opposed to regular RMSProp, which
   *  uses the (uncentered) second moment. This often helps with training, but is
   *  slightly more expensive in terms of computation and memory.
   *  <p>Note that in dense implementation of this algorithm, mg, ms, and mom will
   *  update even if the grad is zero, but in this sparse implementation, mg, ms,
   *  and mom will not update in iterations during which the grad is zero.
   *  <p>mean_square = decay * mean_square + (1-decay) * gradient ** 2
   *  mean_grad = decay * mean_grad + (1-decay) * gradient
   *  <p>Delta = learning_rate * gradient / sqrt(mean_square + epsilon - mean_grad ** 2)
   *  <p>mg &lt;- rho * mg_{t-1} + (1-rho) * grad
   *  ms &lt;- rho * ms_{t-1} + (1-rho) * grad * grad
   *  mom &lt;- momentum * mom_{t-1} + lr * grad / sqrt(ms - mg * mg + epsilon)
   *  var &lt;- var - mom
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param mg Should be from a Variable().
   * @param ms Should be from a Variable().
   * @param mom Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param rho Decay rate. Must be a scalar.
   * @param momentum Momentum Scale. Must be a scalar.
   * @param epsilon Ridge term. Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyCenteredRMSProp} output and operands
   * @return a new instance of ApplyCenteredRmsProp
   */
  public <T extends TType> ApplyCenteredRmsProp<T> applyCenteredRmsProp(Operand<T> var,
      Operand<T> mg, Operand<T> ms, Operand<T> mom, Operand<T> lr, Operand<T> rho,
      Operand<T> momentum, Operand<T> epsilon, Operand<T> grad,
      ApplyCenteredRmsProp.Options... options) {
    return ApplyCenteredRmsProp.create(scope, var, mg, ms, mom, lr, rho, momentum, epsilon, grad, options);
  }

  /**
   * Update '*var' according to the Ftrl-proximal scheme.
   *  grad_with_shrinkage = grad + 2 * l2_shrinkage * var
   *  accum_new = accum + grad * grad
   *  linear += grad_with_shrinkage -
   *  (accum_new^(-lr_power) - accum^(-lr_power)) / lr * var
   *  quadratic = 1.0 / (accum_new^(lr_power) * lr) + 2 * l2
   *  var = (sign(linear) * l1 - linear) / quadratic if |linear| &gt; l1 else 0.0
   *  accum = accum_new
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param linear Should be from a Variable().
   * @param grad The gradient.
   * @param lr Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 shrinkage regularization. Must be a scalar.
   * @param l2Shrinkage the l2Shrinkage value
   * @param lrPower Scaling factor. Must be a scalar.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyFtrlV2} output and operands
   * @return a new instance of ApplyFtrl
   */
  public <T extends TType> ApplyFtrl<T> applyFtrl(Operand<T> var, Operand<T> accum,
      Operand<T> linear, Operand<T> grad, Operand<T> lr, Operand<T> l1, Operand<T> l2,
      Operand<T> l2Shrinkage, Operand<T> lrPower, ApplyFtrl.Options... options) {
    return ApplyFtrl.create(scope, var, accum, linear, grad, lr, l1, l2, l2Shrinkage, lrPower, options);
  }

  /**
   * Update '*var' by subtracting 'alpha' * 'delta' from it.
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param alpha Scaling factor. Must be a scalar.
   * @param delta The change.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyGradientDescent} output and operands
   * @return a new instance of ApplyGradientDescent
   */
  public <T extends TType> ApplyGradientDescent<T> applyGradientDescent(Operand<T> var,
      Operand<T> alpha, Operand<T> delta, ApplyGradientDescent.Options... options) {
    return ApplyGradientDescent.create(scope, var, alpha, delta, options);
  }

  /**
   * Update '*var' according to the momentum scheme.
   *  Set use_nesterov = True if you want to use Nesterov momentum.
   *  <p>accum = accum * momentum + grad
   *  var -= lr * accum
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param grad The gradient.
   * @param momentum Momentum. Must be a scalar.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyMomentum} output and operands
   * @return a new instance of ApplyMomentum
   */
  public <T extends TType> ApplyMomentum<T> applyMomentum(Operand<T> var, Operand<T> accum,
      Operand<T> lr, Operand<T> grad, Operand<T> momentum, ApplyMomentum.Options... options) {
    return ApplyMomentum.create(scope, var, accum, lr, grad, momentum, options);
  }

  /**
   * Update '*var' according to the AddSign update.
   *  m_t &lt;- beta1 * m_{t-1} + (1 - beta1) * g
   *  update &lt;- exp(logbase * sign_decay * sign(g) * sign(m_t)) * g
   *  variable &lt;- variable - lr_t * update
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param m Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param logbase Must be a scalar.
   * @param signDecay Must be a scalar.
   * @param beta Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyPowerSign} output and operands
   * @return a new instance of ApplyPowerSign
   */
  public <T extends TType> ApplyPowerSign<T> applyPowerSign(Operand<T> var, Operand<T> m,
      Operand<T> lr, Operand<T> logbase, Operand<T> signDecay, Operand<T> beta, Operand<T> grad,
      ApplyPowerSign.Options... options) {
    return ApplyPowerSign.create(scope, var, m, lr, logbase, signDecay, beta, grad, options);
  }

  /**
   * Update '*var' and '*accum' according to FOBOS with Adagrad learning rate.
   *  accum += grad * grad
   *  prox_v = var - lr * grad * (1 / sqrt(accum))
   *  var = sign(prox_v)/(1+lr<em>l2) * max{|prox_v|-lr</em>l1,0}
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 regularization. Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyProximalAdagrad} output and operands
   * @return a new instance of ApplyProximalAdagrad
   */
  public <T extends TType> ApplyProximalAdagrad<T> applyProximalAdagrad(Operand<T> var,
      Operand<T> accum, Operand<T> lr, Operand<T> l1, Operand<T> l2, Operand<T> grad,
      ApplyProximalAdagrad.Options... options) {
    return ApplyProximalAdagrad.create(scope, var, accum, lr, l1, l2, grad, options);
  }

  /**
   * Update '*var' as FOBOS algorithm with fixed learning rate.
   *  prox_v = var - alpha * delta
   *  var = sign(prox_v)/(1+alpha<em>l2) * max{|prox_v|-alpha</em>l1,0}
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param alpha Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 regularization. Must be a scalar.
   * @param delta The change.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyProximalGradientDescent} output and operands
   * @return a new instance of ApplyProximalGradientDescent
   */
  public <T extends TType> ApplyProximalGradientDescent<T> applyProximalGradientDescent(
      Operand<T> var, Operand<T> alpha, Operand<T> l1, Operand<T> l2, Operand<T> delta,
      ApplyProximalGradientDescent.Options... options) {
    return ApplyProximalGradientDescent.create(scope, var, alpha, l1, l2, delta, options);
  }

  /**
   * Update '*var' according to the RMSProp algorithm.
   *  Note that in dense implementation of this algorithm, ms and mom will
   *  update even if the grad is zero, but in this sparse implementation, ms
   *  and mom will not update in iterations during which the grad is zero.
   *  <p>mean_square = decay * mean_square + (1-decay) * gradient ** 2
   *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon)
   *  <p>ms &lt;- rho * ms_{t-1} + (1-rho) * grad * grad
   *  mom &lt;- momentum * mom_{t-1} + lr * grad / sqrt(ms + epsilon)
   *  var &lt;- var - mom
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param ms Should be from a Variable().
   * @param mom Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param rho Decay rate. Must be a scalar.
   * @param momentum the momentum value
   * @param epsilon Ridge term. Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyRMSProp} output and operands
   * @return a new instance of ApplyRmsProp
   */
  public <T extends TType> ApplyRmsProp<T> applyRmsProp(Operand<T> var, Operand<T> ms,
      Operand<T> mom, Operand<T> lr, Operand<T> rho, Operand<T> momentum, Operand<T> epsilon,
      Operand<T> grad, ApplyRmsProp.Options... options) {
    return ApplyRmsProp.create(scope, var, ms, mom, lr, rho, momentum, epsilon, grad, options);
  }

  /**
   * Multiplies slices of two tensors in batches.
   *  Multiplies all slices of {@code Tensor} {@code x} and {@code y} (each slice can be
   *  viewed as an element of a batch), and arranges the individual results
   *  in a single output tensor of the same batch size. Each of the
   *  individual slices can optionally be adjointed (to adjoint a matrix
   *  means to transpose and conjugate it) before multiplication by setting
   *  the {@code adj_x} or {@code adj_y} flag to {@code True}, which are by default {@code False}.
   *  <p>The input tensors {@code x} and {@code y} are 2-D or higher with shape {@code [..., r_x, c_x]}
   *  and {@code [..., r_y, c_y]}.
   *  <p>The output tensor is 2-D or higher with shape {@code [..., r_o, c_o]}, where:
   *  <pre>
   *  r_o = c_x if adj_x else r_x
   *  c_o = r_y if adj_y else c_y
   *  </pre>
   *  <p>It is computed as:
   *  <pre>
   *  output[..., :, :] = matrix(x[..., :, :]) * matrix(y[..., :, :])
   *  </pre>
   *  <p><em>NOTE</em>: {@code train.BatchMatMul} supports broadcasting in the batch dimensions. More
   *  about broadcasting
   *   <a href="http://docs.scipy.org/doc/numpy/user/basics.broadcasting.html">here</a> .
   *
   * @param <T> data type for {@code output} output
   * @param x 2-D or higher with shape {@code [..., r_x, c_x]}.
   * @param y 2-D or higher with shape {@code [..., r_y, c_y]}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code BatchMatMulV2} output and operands
   * @return a new instance of BatchMatMul
   */
  public <T extends TType> BatchMatMul<T> batchMatMul(Operand<T> x, Operand<T> y,
      BatchMatMul.Options... options) {
    return BatchMatMul.create(scope, x, y, options);
  }

  /**
   * A conditional accumulator for aggregating gradients.
   *  The accumulator accepts gradients marked with local_step greater or
   *  equal to the most recent global_step known to the accumulator. The
   *  average can be extracted from the accumulator, provided sufficient
   *  gradients have been accumulated. Extracting the average automatically
   *  resets the aggregate to 0, and increments the global_step recorded by
   *  the accumulator.
   *
   * @param dtype The type of the value being accumulated.
   * @param shape The shape of the values, can be [], in which case shape is unknown.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ConditionalAccumulator} output and operands
   * @return a new instance of ConditionalAccumulator
   */
  public <T extends TType> ConditionalAccumulator conditionalAccumulator(Class<T> dtype,
      Shape shape, ConditionalAccumulator.Options... options) {
    return ConditionalAccumulator.create(scope, dtype, shape, options);
  }

  /**
   * Given a path to new and old vocabulary files, returns a remapping Tensor of
   *  length {@code num_new_vocab}, where {@code remapping[i]} contains the row number in the old
   *  vocabulary that corresponds to row {@code i} in the new vocabulary (starting at line
   *  {@code new_vocab_offset} and up to {@code num_new_vocab} entities), or {@code -1} if entry {@code i}
   *  in the new vocabulary is not in the old vocabulary.  The old vocabulary is
   *  constrained to the first {@code old_vocab_size} entries if {@code old_vocab_size} is not the
   *  default value of -1.
   *  <p>{@code num_vocab_offset} enables
   *  use in the partitioned variable case, and should generally be set through
   *  examining partitioning info.  The format of the files should be a text file,
   *  with each line containing a single entity within the vocabulary.
   *  <p>For example, with {@code new_vocab_file} a text file containing each of the following
   *  elements on a single line: {@code [f0, f1, f2, f3]}, old_vocab_file = [f1, f0, f3],
   *  {@code num_new_vocab = 3, new_vocab_offset = 1}, the returned remapping would be
   *  {@code [0, -1, 2]}.
   *  <p>The op also returns a count of how many entries in the new vocabulary
   *  were present in the old vocabulary, which is used to calculate the number of
   *  values to initialize in a weight matrix remapping
   *  <p>This functionality can be used to remap both row vocabularies (typically,
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
   * @param options carries optional attribute values
   * @return a new instance of GenerateVocabRemapping
   */
  public GenerateVocabRemapping generateVocabRemapping(Operand<TString> newVocabFile,
      Operand<TString> oldVocabFile, Long newVocabOffset, Long numNewVocab,
      GenerateVocabRemapping.Options... options) {
    return GenerateVocabRemapping.create(scope, newVocabFile, oldVocabFile, newVocabOffset, numNewVocab, options);
  }

  /**
   * V2 format specific: merges the metadata files of sharded checkpoints.  The
   *  result is one logical checkpoint, with one physical metadata file and renamed
   *  data files.
   *  <p>Intended for &quot;grouping&quot; multiple checkpoints in a sharded checkpoint setup.
   *  <p>If delete_old_dirs is true, attempts to delete recursively the dirname of each
   *  path in the input checkpoint_prefixes.  This is useful when those paths are non
   *  user-facing temporary locations.
   *
   * @param checkpointPrefixes prefixes of V2 checkpoints to merge.
   * @param destinationPrefix scalar.  The desired final prefix.  Allowed to be the same
   *  as one of the checkpoint_prefixes.
   * @param options carries optional attribute values
   * @return a new instance of MergeV2Checkpoints
   */
  public MergeV2Checkpoints mergeV2Checkpoints(Operand<TString> checkpointPrefixes,
      Operand<TString> destinationPrefix, MergeV2Checkpoints.Options... options) {
    return MergeV2Checkpoints.create(scope, checkpointPrefixes, destinationPrefix, options);
  }

  /**
   * Training via negative sampling.
   *
   * @param wIn input word embedding.
   * @param wOut output word embedding.
   * @param examples A vector of word ids.
   * @param labels A vector of word ids.
   * @param lr the lr value
   * @param vocabCount Count of words in the vocabulary.
   * @param numNegativeSamples Number of negative samples per example.
   * @return a new instance of NegTrain
   */
  public NegTrain negTrain(Operand<TFloat32> wIn, Operand<TFloat32> wOut, Operand<TInt32> examples,
      Operand<TInt32> labels, Operand<TFloat32> lr, List<Long> vocabCount,
      Long numNegativeSamples) {
    return NegTrain.create(scope, wIn, wOut, examples, labels, lr, vocabCount, numNegativeSamples);
  }

  /**
   * An identity op that triggers an error if a gradient is requested.
   *  When executed in a graph, this op outputs its input tensor as-is.
   *  <p>When building ops to compute gradients, the TensorFlow gradient system
   *  will return an error when trying to lookup the gradient of this op,
   *  because no gradient must ever be registered for this function.  This
   *  op exists to prevent subtle bugs from silently returning unimplemented
   *  gradients in some corner cases.
   *
   * @param <T> data type for {@code output} output
   * @param input any tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code PreventGradient} output and operands
   * @return a new instance of PreventGradient
   */
  public <T extends TType> PreventGradient<T> preventGradient(Operand<T> input,
      PreventGradient.Options... options) {
    return PreventGradient.create(scope, input, options);
  }

  /**
   * Update '*var' according to the adadelta scheme.
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
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyAdadelta} output and operands
   * @return a new instance of ResourceApplyAdadelta
   */
  public <T extends TType> ResourceApplyAdadelta resourceApplyAdadelta(Operand<? extends TType> var,
      Operand<? extends TType> accum, Operand<? extends TType> accumUpdate, Operand<T> lr,
      Operand<T> rho, Operand<T> epsilon, Operand<T> grad,
      ResourceApplyAdadelta.Options... options) {
    return ResourceApplyAdadelta.create(scope, var, accum, accumUpdate, lr, rho, epsilon, grad, options);
  }

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
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyAdagradDA} output and operands
   * @return a new instance of ResourceApplyAdagradDa
   */
  public <T extends TType> ResourceApplyAdagradDa resourceApplyAdagradDa(
      Operand<? extends TType> var, Operand<? extends TType> gradientAccumulator,
      Operand<? extends TType> gradientSquaredAccumulator, Operand<T> grad, Operand<T> lr,
      Operand<T> l1, Operand<T> l2, Operand<TInt64> globalStep,
      ResourceApplyAdagradDa.Options... options) {
    return ResourceApplyAdagradDa.create(scope, var, gradientAccumulator, gradientSquaredAccumulator, grad, lr, l1, l2, globalStep, options);
  }

  /**
   * Update '*var' according to the Adam algorithm.
   *  $$\text{lr}<em>t := \mathrm{learning_rate} * \sqrt{1 - \beta_2^t} / (1 - \beta_1^t)$$
   *  $$m_t := \beta_1 * m</em>{t-1} + (1 - \beta_1) * g$$
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
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyAdam} output and operands
   * @return a new instance of ResourceApplyAdam
   */
  public <T extends TType> ResourceApplyAdam resourceApplyAdam(Operand<? extends TType> var,
      Operand<? extends TType> m, Operand<? extends TType> v, Operand<T> beta1Power,
      Operand<T> beta2Power, Operand<T> lr, Operand<T> beta1, Operand<T> beta2, Operand<T> epsilon,
      Operand<T> grad, ResourceApplyAdam.Options... options) {
    return ResourceApplyAdam.create(scope, var, m, v, beta1Power, beta2Power, lr, beta1, beta2, epsilon, grad, options);
  }

  /**
   * Update '*var' according to the Adam algorithm.
   *  $$\text{lr}<em>t := \mathrm{learning_rate} * \sqrt{1 - \beta_2^t} / (1 - \beta_1^t)$$
   *  $$m_t := \beta_1 * m</em>{t-1} + (1 - \beta_1) * g$$
   *  $$v_t := \beta_2 * v_{t-1} + (1 - \beta_2) * g * g$$
   *  $$\hat{v}<em>t := max{\hat{v}</em>{t-1}, v_t}$$
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
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyAdamWithAmsgrad} output and operands
   * @return a new instance of ResourceApplyAdamWithAmsgrad
   */
  public <T extends TType> ResourceApplyAdamWithAmsgrad resourceApplyAdamWithAmsgrad(
      Operand<? extends TType> var, Operand<? extends TType> m, Operand<? extends TType> v,
      Operand<? extends TType> vhat, Operand<T> beta1Power, Operand<T> beta2Power, Operand<T> lr,
      Operand<T> beta1, Operand<T> beta2, Operand<T> epsilon, Operand<T> grad,
      ResourceApplyAdamWithAmsgrad.Options... options) {
    return ResourceApplyAdamWithAmsgrad.create(scope, var, m, v, vhat, beta1Power, beta2Power, lr, beta1, beta2, epsilon, grad, options);
  }

  /**
   * Update '*var' according to the AddSign update.
   *  m_t &lt;- beta1 * m_{t-1} + (1 - beta1) * g
   *  update &lt;- (alpha + sign_decay * sign(g) *sign(m)) * g
   *  variable &lt;- variable - lr_t * update
   *
   * @param var Should be from a Variable().
   * @param m Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param alpha Must be a scalar.
   * @param signDecay Must be a scalar.
   * @param beta Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyAddSign} output and operands
   * @return a new instance of ResourceApplyAddSign
   */
  public <T extends TType> ResourceApplyAddSign resourceApplyAddSign(Operand<? extends TType> var,
      Operand<? extends TType> m, Operand<T> lr, Operand<T> alpha, Operand<T> signDecay,
      Operand<T> beta, Operand<T> grad, ResourceApplyAddSign.Options... options) {
    return ResourceApplyAddSign.create(scope, var, m, lr, alpha, signDecay, beta, grad, options);
  }

  /**
   * Update '*var' according to the centered RMSProp algorithm.
   *  The centered RMSProp algorithm uses an estimate of the centered second moment
   *  (i.e., the variance) for normalization, as opposed to regular RMSProp, which
   *  uses the (uncentered) second moment. This often helps with training, but is
   *  slightly more expensive in terms of computation and memory.
   *  <p>Note that in dense implementation of this algorithm, mg, ms, and mom will
   *  update even if the grad is zero, but in this sparse implementation, mg, ms,
   *  and mom will not update in iterations during which the grad is zero.
   *  <p>mean_square = decay * mean_square + (1-decay) * gradient ** 2
   *  mean_grad = decay * mean_grad + (1-decay) * gradient
   *  <p>Delta = learning_rate * gradient / sqrt(mean_square + epsilon - mean_grad ** 2)
   *  <p>mg &lt;- rho * mg_{t-1} + (1-rho) * grad
   *  ms &lt;- rho * ms_{t-1} + (1-rho) * grad * grad
   *  mom &lt;- momentum * mom_{t-1} + lr * grad / sqrt(ms - mg * mg + epsilon)
   *  var &lt;- var - mom
   *
   * @param var Should be from a Variable().
   * @param mg Should be from a Variable().
   * @param ms Should be from a Variable().
   * @param mom Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param rho Decay rate. Must be a scalar.
   * @param momentum Momentum Scale. Must be a scalar.
   * @param epsilon Ridge term. Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyCenteredRMSProp} output and operands
   * @return a new instance of ResourceApplyCenteredRmsProp
   */
  public <T extends TType> ResourceApplyCenteredRmsProp resourceApplyCenteredRmsProp(
      Operand<? extends TType> var, Operand<? extends TType> mg, Operand<? extends TType> ms,
      Operand<? extends TType> mom, Operand<T> lr, Operand<T> rho, Operand<T> momentum,
      Operand<T> epsilon, Operand<T> grad, ResourceApplyCenteredRmsProp.Options... options) {
    return ResourceApplyCenteredRmsProp.create(scope, var, mg, ms, mom, lr, rho, momentum, epsilon, grad, options);
  }

  /**
   * Update '*var' according to the Ftrl-proximal scheme.
   *  grad_with_shrinkage = grad + 2 * l2_shrinkage * var
   *  accum_new = accum + grad_with_shrinkage * grad_with_shrinkage
   *  linear += grad_with_shrinkage +
   *  (accum_new^(-lr_power) - accum^(-lr_power)) / lr * var
   *  quadratic = 1.0 / (accum_new^(lr_power) * lr) + 2 * l2
   *  var = (sign(linear) * l1 - linear) / quadratic if |linear| &gt; l1 else 0.0
   *  accum = accum_new
   *
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param linear Should be from a Variable().
   * @param grad The gradient.
   * @param lr Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 shrinkage regularization. Must be a scalar.
   * @param l2Shrinkage the l2Shrinkage value
   * @param lrPower Scaling factor. Must be a scalar.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyFtrlV2} output and operands
   * @return a new instance of ResourceApplyFtrl
   */
  public <T extends TType> ResourceApplyFtrl resourceApplyFtrl(Operand<? extends TType> var,
      Operand<? extends TType> accum, Operand<? extends TType> linear, Operand<T> grad,
      Operand<T> lr, Operand<T> l1, Operand<T> l2, Operand<T> l2Shrinkage, Operand<T> lrPower,
      ResourceApplyFtrl.Options... options) {
    return ResourceApplyFtrl.create(scope, var, accum, linear, grad, lr, l1, l2, l2Shrinkage, lrPower, options);
  }

  /**
   * Update '*var' by subtracting 'alpha' * 'delta' from it.
   *
   * @param var Should be from a Variable().
   * @param alpha Scaling factor. Must be a scalar.
   * @param delta The change.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyGradientDescent} output and operands
   * @return a new instance of ResourceApplyGradientDescent
   */
  public <T extends TType> ResourceApplyGradientDescent resourceApplyGradientDescent(
      Operand<? extends TType> var, Operand<T> alpha, Operand<T> delta,
      ResourceApplyGradientDescent.Options... options) {
    return ResourceApplyGradientDescent.create(scope, var, alpha, delta, options);
  }

  /**
   * Update '*var' according to the momentum scheme.
   *  Set use_nesterov = True if you want to use Nesterov momentum.
   *  <p>accum = accum * momentum - lr * grad
   *  var += accum
   *
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param grad The gradient.
   * @param momentum Momentum. Must be a scalar.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyKerasMomentum} output and operands
   * @return a new instance of ResourceApplyKerasMomentum
   */
  public <T extends TType> ResourceApplyKerasMomentum resourceApplyKerasMomentum(
      Operand<? extends TType> var, Operand<? extends TType> accum, Operand<T> lr, Operand<T> grad,
      Operand<T> momentum, ResourceApplyKerasMomentum.Options... options) {
    return ResourceApplyKerasMomentum.create(scope, var, accum, lr, grad, momentum, options);
  }

  /**
   * Update '*var' according to the momentum scheme.
   *  Set use_nesterov = True if you want to use Nesterov momentum.
   *  <p>accum = accum * momentum + grad
   *  var -= lr * accum
   *
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param grad The gradient.
   * @param momentum Momentum. Must be a scalar.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyMomentum} output and operands
   * @return a new instance of ResourceApplyMomentum
   */
  public <T extends TType> ResourceApplyMomentum resourceApplyMomentum(Operand<? extends TType> var,
      Operand<? extends TType> accum, Operand<T> lr, Operand<T> grad, Operand<T> momentum,
      ResourceApplyMomentum.Options... options) {
    return ResourceApplyMomentum.create(scope, var, accum, lr, grad, momentum, options);
  }

  /**
   * Update '*var' according to the AddSign update.
   *  m_t &lt;- beta1 * m_{t-1} + (1 - beta1) * g
   *  update &lt;- exp(logbase * sign_decay * sign(g) * sign(m_t)) * g
   *  variable &lt;- variable - lr_t * update
   *
   * @param var Should be from a Variable().
   * @param m Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param logbase Must be a scalar.
   * @param signDecay Must be a scalar.
   * @param beta Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyPowerSign} output and operands
   * @return a new instance of ResourceApplyPowerSign
   */
  public <T extends TType> ResourceApplyPowerSign resourceApplyPowerSign(
      Operand<? extends TType> var, Operand<? extends TType> m, Operand<T> lr, Operand<T> logbase,
      Operand<T> signDecay, Operand<T> beta, Operand<T> grad,
      ResourceApplyPowerSign.Options... options) {
    return ResourceApplyPowerSign.create(scope, var, m, lr, logbase, signDecay, beta, grad, options);
  }

  /**
   * Update '*var' and '*accum' according to FOBOS with Adagrad learning rate.
   *  accum += grad * grad
   *  prox_v = var - lr * grad * (1 / sqrt(accum))
   *  var = sign(prox_v)/(1+lr<em>l2) * max{|prox_v|-lr</em>l1,0}
   *
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 regularization. Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyProximalAdagrad} output and operands
   * @return a new instance of ResourceApplyProximalAdagrad
   */
  public <T extends TType> ResourceApplyProximalAdagrad resourceApplyProximalAdagrad(
      Operand<? extends TType> var, Operand<? extends TType> accum, Operand<T> lr, Operand<T> l1,
      Operand<T> l2, Operand<T> grad, ResourceApplyProximalAdagrad.Options... options) {
    return ResourceApplyProximalAdagrad.create(scope, var, accum, lr, l1, l2, grad, options);
  }

  /**
   * Update '*var' as FOBOS algorithm with fixed learning rate.
   *  prox_v = var - alpha * delta
   *  var = sign(prox_v)/(1+alpha<em>l2) * max{|prox_v|-alpha</em>l1,0}
   *
   * @param var Should be from a Variable().
   * @param alpha Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 regularization. Must be a scalar.
   * @param delta The change.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyProximalGradientDescent} output and operands
   * @return a new instance of ResourceApplyProximalGradientDescent
   */
  public <T extends TType> ResourceApplyProximalGradientDescent resourceApplyProximalGradientDescent(
      Operand<? extends TType> var, Operand<T> alpha, Operand<T> l1, Operand<T> l2,
      Operand<T> delta, ResourceApplyProximalGradientDescent.Options... options) {
    return ResourceApplyProximalGradientDescent.create(scope, var, alpha, l1, l2, delta, options);
  }

  /**
   * Update '*var' according to the RMSProp algorithm.
   *  Note that in dense implementation of this algorithm, ms and mom will
   *  update even if the grad is zero, but in this sparse implementation, ms
   *  and mom will not update in iterations during which the grad is zero.
   *  <p>mean_square = decay * mean_square + (1-decay) * gradient ** 2
   *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon)
   *  <p>ms &lt;- rho * ms_{t-1} + (1-rho) * grad * grad
   *  mom &lt;- momentum * mom_{t-1} + lr * grad / sqrt(ms + epsilon)
   *  var &lt;- var - mom
   *
   * @param var Should be from a Variable().
   * @param ms Should be from a Variable().
   * @param mom Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param rho Decay rate. Must be a scalar.
   * @param momentum the momentum value
   * @param epsilon Ridge term. Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyRMSProp} output and operands
   * @return a new instance of ResourceApplyRmsProp
   */
  public <T extends TType> ResourceApplyRmsProp resourceApplyRmsProp(Operand<? extends TType> var,
      Operand<? extends TType> ms, Operand<? extends TType> mom, Operand<T> lr, Operand<T> rho,
      Operand<T> momentum, Operand<T> epsilon, Operand<T> grad,
      ResourceApplyRmsProp.Options... options) {
    return ResourceApplyRmsProp.create(scope, var, ms, mom, lr, rho, momentum, epsilon, grad, options);
  }

  /**
   * var: Should be from a Variable().
   *
   * @param var the var value
   * @param accum Should be from a Variable().
   * @param accumUpdate : Should be from a Variable().
   * @param lr Learning rate. Must be a scalar.
   * @param rho Decay factor. Must be a scalar.
   * @param epsilon Constant factor. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceSparseApplyAdadelta} output and operands
   * @return a new instance of ResourceSparseApplyAdadelta
   */
  public <T extends TType> ResourceSparseApplyAdadelta resourceSparseApplyAdadelta(
      Operand<? extends TType> var, Operand<? extends TType> accum,
      Operand<? extends TType> accumUpdate, Operand<T> lr, Operand<T> rho, Operand<T> epsilon,
      Operand<T> grad, Operand<? extends TNumber> indices,
      ResourceSparseApplyAdadelta.Options... options) {
    return ResourceSparseApplyAdadelta.create(scope, var, accum, accumUpdate, lr, rho, epsilon, grad, indices, options);
  }

  /**
   * Update relevant entries in '*var' and '*accum' according to the adagrad scheme.
   *  That is for rows we have grad for, we update var and accum as follows:
   *  accum += grad * grad
   *  var -= lr * grad * (1 / sqrt(accum))
   *
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Learning rate. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceSparseApplyAdagrad} output and operands
   * @return a new instance of ResourceSparseApplyAdagrad
   */
  public <T extends TType> ResourceSparseApplyAdagrad resourceSparseApplyAdagrad(
      Operand<? extends TType> var, Operand<? extends TType> accum, Operand<T> lr, Operand<T> grad,
      Operand<? extends TNumber> indices, ResourceSparseApplyAdagrad.Options... options) {
    return ResourceSparseApplyAdagrad.create(scope, var, accum, lr, grad, indices, options);
  }

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
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceSparseApplyAdagradDA} output and operands
   * @return a new instance of ResourceSparseApplyAdagradDa
   */
  public <T extends TType> ResourceSparseApplyAdagradDa resourceSparseApplyAdagradDa(
      Operand<? extends TType> var, Operand<? extends TType> gradientAccumulator,
      Operand<? extends TType> gradientSquaredAccumulator, Operand<T> grad,
      Operand<? extends TNumber> indices, Operand<T> lr, Operand<T> l1, Operand<T> l2,
      Operand<TInt64> globalStep, ResourceSparseApplyAdagradDa.Options... options) {
    return ResourceSparseApplyAdagradDa.create(scope, var, gradientAccumulator, gradientSquaredAccumulator, grad, indices, lr, l1, l2, globalStep, options);
  }

  /**
   * Update '*var' according to the centered RMSProp algorithm.
   *  The centered RMSProp algorithm uses an estimate of the centered second moment
   *  (i.e., the variance) for normalization, as opposed to regular RMSProp, which
   *  uses the (uncentered) second moment. This often helps with training, but is
   *  slightly more expensive in terms of computation and memory.
   *  <p>Note that in dense implementation of this algorithm, mg, ms, and mom will
   *  update even if the grad is zero, but in this sparse implementation, mg, ms,
   *  and mom will not update in iterations during which the grad is zero.
   *  <p>mean_square = decay * mean_square + (1-decay) * gradient ** 2
   *  mean_grad = decay * mean_grad + (1-decay) * gradient
   *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon - mean_grad ** 2)
   *  <p>ms &lt;- rho * ms_{t-1} + (1-rho) * grad * grad
   *  mom &lt;- momentum * mom_{t-1} + lr * grad / sqrt(ms + epsilon)
   *  var &lt;- var - mom
   *
   * @param var Should be from a Variable().
   * @param mg Should be from a Variable().
   * @param ms Should be from a Variable().
   * @param mom Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param rho Decay rate. Must be a scalar.
   * @param momentum the momentum value
   * @param epsilon Ridge term. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var, ms and mom.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceSparseApplyCenteredRMSProp} output and operands
   * @return a new instance of ResourceSparseApplyCenteredRmsProp
   */
  public <T extends TType> ResourceSparseApplyCenteredRmsProp resourceSparseApplyCenteredRmsProp(
      Operand<? extends TType> var, Operand<? extends TType> mg, Operand<? extends TType> ms,
      Operand<? extends TType> mom, Operand<T> lr, Operand<T> rho, Operand<T> momentum,
      Operand<T> epsilon, Operand<T> grad, Operand<? extends TNumber> indices,
      ResourceSparseApplyCenteredRmsProp.Options... options) {
    return ResourceSparseApplyCenteredRmsProp.create(scope, var, mg, ms, mom, lr, rho, momentum, epsilon, grad, indices, options);
  }

  /**
   * Update relevant entries in '*var' according to the Ftrl-proximal scheme.
   *  That is for rows we have grad for, we update var, accum and linear as follows:
   *  grad_with_shrinkage = grad + 2 * l2_shrinkage * var
   *  accum_new = accum + grad_with_shrinkage * grad_with_shrinkage
   *  linear += grad_with_shrinkage +
   *  (accum_new^(-lr_power) - accum^(-lr_power)) / lr * var
   *  quadratic = 1.0 / (accum_new^(lr_power) * lr) + 2 * l2
   *  var = (sign(linear) * l1 - linear) / quadratic if |linear| &gt; l1 else 0.0
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
   * @param l2Shrinkage the l2Shrinkage value
   * @param lrPower Scaling factor. Must be a scalar.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceSparseApplyFtrlV2} output and operands
   * @return a new instance of ResourceSparseApplyFtrl
   */
  public <T extends TType> ResourceSparseApplyFtrl resourceSparseApplyFtrl(
      Operand<? extends TType> var, Operand<? extends TType> accum, Operand<? extends TType> linear,
      Operand<T> grad, Operand<? extends TNumber> indices, Operand<T> lr, Operand<T> l1,
      Operand<T> l2, Operand<T> l2Shrinkage, Operand<T> lrPower,
      ResourceSparseApplyFtrl.Options... options) {
    return ResourceSparseApplyFtrl.create(scope, var, accum, linear, grad, indices, lr, l1, l2, l2Shrinkage, lrPower, options);
  }

  /**
   * Update relevant entries in '*var' and '*accum' according to the momentum scheme.
   *  Set use_nesterov = True if you want to use Nesterov momentum.
   *  <p>That is for rows we have grad for, we update var and accum as follows:
   *  <p>accum = accum * momentum - lr * grad
   *  var += accum
   *
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Learning rate. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param momentum Momentum. Must be a scalar.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceSparseApplyKerasMomentum} output and operands
   * @return a new instance of ResourceSparseApplyKerasMomentum
   */
  public <T extends TType> ResourceSparseApplyKerasMomentum resourceSparseApplyKerasMomentum(
      Operand<? extends TType> var, Operand<? extends TType> accum, Operand<T> lr, Operand<T> grad,
      Operand<? extends TNumber> indices, Operand<T> momentum,
      ResourceSparseApplyKerasMomentum.Options... options) {
    return ResourceSparseApplyKerasMomentum.create(scope, var, accum, lr, grad, indices, momentum, options);
  }

  /**
   * Update relevant entries in '*var' and '*accum' according to the momentum scheme.
   *  Set use_nesterov = True if you want to use Nesterov momentum.
   *  <p>That is for rows we have grad for, we update var and accum as follows:
   *  <p>accum = accum * momentum + grad
   *  var -= lr * accum
   *
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Learning rate. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param momentum Momentum. Must be a scalar.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceSparseApplyMomentum} output and operands
   * @return a new instance of ResourceSparseApplyMomentum
   */
  public <T extends TType> ResourceSparseApplyMomentum resourceSparseApplyMomentum(
      Operand<? extends TType> var, Operand<? extends TType> accum, Operand<T> lr, Operand<T> grad,
      Operand<? extends TNumber> indices, Operand<T> momentum,
      ResourceSparseApplyMomentum.Options... options) {
    return ResourceSparseApplyMomentum.create(scope, var, accum, lr, grad, indices, momentum, options);
  }

  /**
   * Sparse update entries in '*var' and '*accum' according to FOBOS algorithm.
   *  That is for rows we have grad for, we update var and accum as follows:
   *  accum += grad * grad
   *  prox_v = var
   *  prox_v -= lr * grad * (1 / sqrt(accum))
   *  var = sign(prox_v)/(1+lr<em>l2) * max{|prox_v|-lr</em>l1,0}
   *
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Learning rate. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 regularization. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceSparseApplyProximalAdagrad} output and operands
   * @return a new instance of ResourceSparseApplyProximalAdagrad
   */
  public <T extends TType> ResourceSparseApplyProximalAdagrad resourceSparseApplyProximalAdagrad(
      Operand<? extends TType> var, Operand<? extends TType> accum, Operand<T> lr, Operand<T> l1,
      Operand<T> l2, Operand<T> grad, Operand<? extends TNumber> indices,
      ResourceSparseApplyProximalAdagrad.Options... options) {
    return ResourceSparseApplyProximalAdagrad.create(scope, var, accum, lr, l1, l2, grad, indices, options);
  }

  /**
   * Sparse update '*var' as FOBOS algorithm with fixed learning rate.
   *  That is for rows we have grad for, we update var as follows:
   *  prox_v = var - alpha * grad
   *  var = sign(prox_v)/(1+alpha<em>l2) * max{|prox_v|-alpha</em>l1,0}
   *
   * @param var Should be from a Variable().
   * @param alpha Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 regularization. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceSparseApplyProximalGradientDescent} output and operands
   * @return a new instance of ResourceSparseApplyProximalGradientDescent
   */
  public <T extends TType> ResourceSparseApplyProximalGradientDescent resourceSparseApplyProximalGradientDescent(
      Operand<? extends TType> var, Operand<T> alpha, Operand<T> l1, Operand<T> l2, Operand<T> grad,
      Operand<? extends TNumber> indices,
      ResourceSparseApplyProximalGradientDescent.Options... options) {
    return ResourceSparseApplyProximalGradientDescent.create(scope, var, alpha, l1, l2, grad, indices, options);
  }

  /**
   * Update '*var' according to the RMSProp algorithm.
   *  Note that in dense implementation of this algorithm, ms and mom will
   *  update even if the grad is zero, but in this sparse implementation, ms
   *  and mom will not update in iterations during which the grad is zero.
   *  <p>mean_square = decay * mean_square + (1-decay) * gradient ** 2
   *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon)
   *  <p>ms &lt;- rho * ms_{t-1} + (1-rho) * grad * grad
   *  mom &lt;- momentum * mom_{t-1} + lr * grad / sqrt(ms + epsilon)
   *  var &lt;- var - mom
   *
   * @param var Should be from a Variable().
   * @param ms Should be from a Variable().
   * @param mom Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param rho Decay rate. Must be a scalar.
   * @param momentum the momentum value
   * @param epsilon Ridge term. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var, ms and mom.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceSparseApplyRMSProp} output and operands
   * @return a new instance of ResourceSparseApplyRmsProp
   */
  public <T extends TType> ResourceSparseApplyRmsProp resourceSparseApplyRmsProp(
      Operand<? extends TType> var, Operand<? extends TType> ms, Operand<? extends TType> mom,
      Operand<T> lr, Operand<T> rho, Operand<T> momentum, Operand<T> epsilon, Operand<T> grad,
      Operand<? extends TNumber> indices, ResourceSparseApplyRmsProp.Options... options) {
    return ResourceSparseApplyRmsProp.create(scope, var, ms, mom, lr, rho, momentum, epsilon, grad, indices, options);
  }

  /**
   * Restores tensors from a V2 checkpoint.
   *  For backward compatibility with the V1 format, this Op currently allows
   *  restoring from a V1 checkpoint as well:
   *  <ul>
   *  <li>This Op first attempts to find the V2 index file pointed to by &quot;prefix&quot;, and
   *  if found proceed to read it as a V2 checkpoint;</li>
   *  <li>Otherwise the V1 read path is invoked.
   *  Relying on this behavior is not recommended, as the ability to fall back to read
   *  V1 might be deprecated and eventually removed.</li>
   *  </ul>
   *  <p>By default, restores the named tensors in full.  If the caller wishes to restore
   *  specific slices of stored tensors, &quot;shape_and_slices&quot; should be non-empty
   *  strings and correspondingly well-formed.
   *  <p>Callers must ensure all the named tensors are indeed stored in the checkpoint.
   *
   * @param prefix Must have a single element.  The prefix of a V2 checkpoint.
   * @param tensorNames shape {N}.  The names of the tensors to be restored.
   * @param shapeAndSlices shape {N}.  The slice specs of the tensors to be restored.
   *  Empty strings indicate that they are non-partitioned tensors.
   * @param dtypes shape {N}.  The list of expected dtype for the tensors.  Must match
   *  those stored in the checkpoint.
   * @return a new instance of Restore
   */
  public Restore restore(Operand<TString> prefix, Operand<TString> tensorNames,
      Operand<TString> shapeAndSlices, List<Class<? extends TType>> dtypes) {
    return Restore.create(scope, prefix, tensorNames, shapeAndSlices, dtypes);
  }

  /**
   * Restores a tensor from checkpoint files.
   *  This is like {@code Restore} except that restored tensor can be listed as filling
   *  only a slice of a larger tensor.  {@code shape_and_slice} specifies the shape of the
   *  larger tensor and the slice that the restored tensor covers.
   *  <p>The {@code shape_and_slice} input has the same format as the
   *  elements of the {@code shapes_and_slices} input of the {@code SaveSlices} op.
   *
   * @param <T> data type for {@code tensor} output
   * @param filePattern Must have a single element. The pattern of the files from
   *  which we read the tensor.
   * @param tensorName Must have a single element. The name of the tensor to be
   *  restored.
   * @param shapeAndSlice Scalar. The shapes and slice specifications to use when
   *  restoring a tensors.
   * @param dt The type of the tensor to be restored.
   * @param options carries optional attribute values
   * @param <T> data type for {@code RestoreSlice} output and operands
   * @return a new instance of RestoreSlice
   */
  public <T extends TType> RestoreSlice<T> restoreSlice(Operand<TString> filePattern,
      Operand<TString> tensorName, Operand<TString> shapeAndSlice, Class<T> dt,
      RestoreSlice.Options... options) {
    return RestoreSlice.create(scope, filePattern, tensorName, shapeAndSlice, dt, options);
  }

  /**
   * Saves tensors in V2 checkpoint format.
   *  By default, saves the named tensors in full.  If the caller wishes to save
   *  specific slices of full tensors, &quot;shape_and_slices&quot; should be non-empty strings
   *  and correspondingly well-formed.
   *
   * @param prefix Must have a single element. The prefix of the V2 checkpoint to which we
   *  write the tensors.
   * @param tensorNames shape {N}. The names of the tensors to be saved.
   * @param shapeAndSlices shape {N}.  The slice specs of the tensors to be saved.
   *  Empty strings indicate that they are non-partitioned tensors.
   * @param tensors {@code N} tensors to save.
   * @return a new instance of Save
   */
  public Save save(Operand<TString> prefix, Operand<TString> tensorNames,
      Operand<TString> shapeAndSlices, Iterable<Operand<?>> tensors) {
    return Save.create(scope, prefix, tensorNames, shapeAndSlices, tensors);
  }

  /**
   * Saves input tensors slices to disk.
   *  This is like {@code Save} except that tensors can be listed in the saved file as being
   *  a slice of a larger tensor.  {@code shapes_and_slices} specifies the shape of the
   *  larger tensor and the slice that this tensor covers. {@code shapes_and_slices} must
   *  have as many elements as {@code tensor_names}.
   *  <p>Elements of the {@code shapes_and_slices} input must either be:
   *  <ul>
   *  <li>The empty string, in which case the corresponding tensor is
   *  saved normally.</li>
   *  <li>A string of the form {@code dim0 dim1 ... dimN-1 slice-spec} where the
   *  {@code dimI} are the dimensions of the larger tensor and {@code slice-spec}
   *  specifies what part is covered by the tensor to save.</li>
   *  </ul>
   *  <p>{@code slice-spec} itself is a {@code :}-separated list: {@code slice0:slice1:...:sliceN-1}
   *  where each {@code sliceI} is either:
   *  <ul>
   *  <li>The string {@code -} meaning that the slice covers all indices of this dimension</li>
   *  <li>{@code start,length} where {@code start} and {@code length} are integers.  In that
   *  case the slice covers {@code length} indices starting at {@code start}.</li>
   *  </ul>
   *  <p>See also {@code Save}.
   *
   * @param filename Must have a single element. The name of the file to which we write the
   *  tensor.
   * @param tensorNames Shape {@code [N]}. The names of the tensors to be saved.
   * @param shapesAndSlices Shape {@code [N]}.  The shapes and slice specifications to use when
   *  saving the tensors.
   * @param data {@code N} tensors to save.
   * @return a new instance of SaveSlices
   */
  public SaveSlices saveSlices(Operand<TString> filename, Operand<TString> tensorNames,
      Operand<TString> shapesAndSlices, Iterable<Operand<?>> data) {
    return SaveSlices.create(scope, filename, tensorNames, shapesAndSlices, data);
  }

  /**
   * Computes fingerprints of the input strings.
   *
   * @param input vector of strings to compute fingerprints on.
   * @return a new instance of SdcaFprint
   */
  public SdcaFprint sdcaFprint(Operand<TString> input) {
    return SdcaFprint.create(scope, input);
  }

  /**
   * Applies L1 regularization shrink step on the parameters.
   *
   * @param weights a list of vectors where each value is the weight associated with a
   *  feature group.
   * @param l1 Symmetric l1 regularization strength.
   * @param l2 Symmetric l2 regularization strength. Should be a positive float.
   * @return a new instance of SdcaShrinkL1
   */
  public SdcaShrinkL1 sdcaShrinkL1(Iterable<Operand<TFloat32>> weights, Float l1, Float l2) {
    return SdcaShrinkL1.create(scope, weights, l1, l2);
  }

  /**
   * var: Should be from a Variable().
   *
   * @param <T> data type for {@code out} output
   * @param var the var value
   * @param accum Should be from a Variable().
   * @param accumUpdate : Should be from a Variable().
   * @param lr Learning rate. Must be a scalar.
   * @param rho Decay factor. Must be a scalar.
   * @param epsilon Constant factor. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseApplyAdadelta} output and operands
   * @return a new instance of SparseApplyAdadelta
   */
  public <T extends TType> SparseApplyAdadelta<T> sparseApplyAdadelta(Operand<T> var,
      Operand<T> accum, Operand<T> accumUpdate, Operand<T> lr, Operand<T> rho, Operand<T> epsilon,
      Operand<T> grad, Operand<? extends TNumber> indices, SparseApplyAdadelta.Options... options) {
    return SparseApplyAdadelta.create(scope, var, accum, accumUpdate, lr, rho, epsilon, grad, indices, options);
  }

  /**
   * Update entries in '*var' and '*accum' according to the proximal adagrad scheme.
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param gradientAccumulator Should be from a Variable().
   * @param gradientSquaredAccumulator Should be from a Variable().
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param lr Learning rate. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 regularization. Must be a scalar.
   * @param globalStep Training step number. Must be a scalar.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseApplyAdagradDA} output and operands
   * @return a new instance of SparseApplyAdagradDa
   */
  public <T extends TType> SparseApplyAdagradDa<T> sparseApplyAdagradDa(Operand<T> var,
      Operand<T> gradientAccumulator, Operand<T> gradientSquaredAccumulator, Operand<T> grad,
      Operand<? extends TNumber> indices, Operand<T> lr, Operand<T> l1, Operand<T> l2,
      Operand<TInt64> globalStep, SparseApplyAdagradDa.Options... options) {
    return SparseApplyAdagradDa.create(scope, var, gradientAccumulator, gradientSquaredAccumulator, grad, indices, lr, l1, l2, globalStep, options);
  }

  /**
   * Update '*var' according to the centered RMSProp algorithm.
   *  The centered RMSProp algorithm uses an estimate of the centered second moment
   *  (i.e., the variance) for normalization, as opposed to regular RMSProp, which
   *  uses the (uncentered) second moment. This often helps with training, but is
   *  slightly more expensive in terms of computation and memory.
   *  <p>Note that in dense implementation of this algorithm, mg, ms, and mom will
   *  update even if the grad is zero, but in this sparse implementation, mg, ms,
   *  and mom will not update in iterations during which the grad is zero.
   *  <p>mean_square = decay * mean_square + (1-decay) * gradient ** 2
   *  mean_grad = decay * mean_grad + (1-decay) * gradient
   *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon - mean_grad ** 2)
   *  <p>$$ms &lt;- rho * ms_{t-1} + (1-rho) * grad * grad$$
   *  $$mom &lt;- momentum * mom_{t-1} + lr * grad / sqrt(ms + epsilon)$$
   *  $$var &lt;- var - mom$$
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param mg Should be from a Variable().
   * @param ms Should be from a Variable().
   * @param mom Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param rho Decay rate. Must be a scalar.
   * @param momentum the momentum value
   * @param epsilon Ridge term. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var, ms and mom.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseApplyCenteredRMSProp} output and operands
   * @return a new instance of SparseApplyCenteredRmsProp
   */
  public <T extends TType> SparseApplyCenteredRmsProp<T> sparseApplyCenteredRmsProp(Operand<T> var,
      Operand<T> mg, Operand<T> ms, Operand<T> mom, Operand<T> lr, Operand<T> rho,
      Operand<T> momentum, Operand<T> epsilon, Operand<T> grad, Operand<? extends TNumber> indices,
      SparseApplyCenteredRmsProp.Options... options) {
    return SparseApplyCenteredRmsProp.create(scope, var, mg, ms, mom, lr, rho, momentum, epsilon, grad, indices, options);
  }

  /**
   * Update relevant entries in '*var' according to the Ftrl-proximal scheme.
   *  That is for rows we have grad for, we update var, accum and linear as follows:
   *  grad_with_shrinkage = grad + 2 * l2_shrinkage * var
   *  accum_new = accum + grad * grad
   *  linear += grad_with_shrinkage -
   *  (accum_new^(-lr_power) - accum^(-lr_power)) / lr * var
   *  quadratic = 1.0 / (accum_new^(lr_power) * lr) + 2 * l2
   *  var = (sign(linear) * l1 - linear) / quadratic if |linear| &gt; l1 else 0.0
   *  accum = accum_new
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param linear Should be from a Variable().
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param lr Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 shrinkage regularization. Must be a scalar.
   * @param l2Shrinkage the l2Shrinkage value
   * @param lrPower Scaling factor. Must be a scalar.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseApplyFtrlV2} output and operands
   * @return a new instance of SparseApplyFtrl
   */
  public <T extends TType> SparseApplyFtrl<T> sparseApplyFtrl(Operand<T> var, Operand<T> accum,
      Operand<T> linear, Operand<T> grad, Operand<? extends TNumber> indices, Operand<T> lr,
      Operand<T> l1, Operand<T> l2, Operand<T> l2Shrinkage, Operand<T> lrPower,
      SparseApplyFtrl.Options... options) {
    return SparseApplyFtrl.create(scope, var, accum, linear, grad, indices, lr, l1, l2, l2Shrinkage, lrPower, options);
  }

  /**
   * Update relevant entries in '*var' and '*accum' according to the momentum scheme.
   *  Set use_nesterov = True if you want to use Nesterov momentum.
   *  <p>That is for rows we have grad for, we update var and accum as follows:
   *  <p>$$accum = accum * momentum + grad$$
   *  $$var -= lr * accum$$
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Learning rate. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param momentum Momentum. Must be a scalar.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseApplyMomentum} output and operands
   * @return a new instance of SparseApplyMomentum
   */
  public <T extends TType> SparseApplyMomentum<T> sparseApplyMomentum(Operand<T> var,
      Operand<T> accum, Operand<T> lr, Operand<T> grad, Operand<? extends TNumber> indices,
      Operand<T> momentum, SparseApplyMomentum.Options... options) {
    return SparseApplyMomentum.create(scope, var, accum, lr, grad, indices, momentum, options);
  }

  /**
   * Sparse update entries in '*var' and '*accum' according to FOBOS algorithm.
   *  That is for rows we have grad for, we update var and accum as follows:
   *  $$accum += grad * grad$$
   *  $$prox_v = var$$
   *  $$prox_v -= lr * grad * (1 / sqrt(accum))$$
   *  $$var = sign(prox_v)/(1+lr<em>l2) * max{|prox_v|-lr</em>l1,0}$$
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Learning rate. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 regularization. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseApplyProximalAdagrad} output and operands
   * @return a new instance of SparseApplyProximalAdagrad
   */
  public <T extends TType> SparseApplyProximalAdagrad<T> sparseApplyProximalAdagrad(Operand<T> var,
      Operand<T> accum, Operand<T> lr, Operand<T> l1, Operand<T> l2, Operand<T> grad,
      Operand<? extends TNumber> indices, SparseApplyProximalAdagrad.Options... options) {
    return SparseApplyProximalAdagrad.create(scope, var, accum, lr, l1, l2, grad, indices, options);
  }

  /**
   * Sparse update '*var' as FOBOS algorithm with fixed learning rate.
   *  That is for rows we have grad for, we update var as follows:
   *  $$prox_v = var - alpha * grad$$
   *  $$var = sign(prox_v)/(1+alpha<em>l2) * max{|prox_v|-alpha</em>l1,0}$$
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param alpha Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 regularization. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseApplyProximalGradientDescent} output and operands
   * @return a new instance of SparseApplyProximalGradientDescent
   */
  public <T extends TType> SparseApplyProximalGradientDescent<T> sparseApplyProximalGradientDescent(
      Operand<T> var, Operand<T> alpha, Operand<T> l1, Operand<T> l2, Operand<T> grad,
      Operand<? extends TNumber> indices, SparseApplyProximalGradientDescent.Options... options) {
    return SparseApplyProximalGradientDescent.create(scope, var, alpha, l1, l2, grad, indices, options);
  }

  /**
   * Update '*var' according to the RMSProp algorithm.
   *  Note that in dense implementation of this algorithm, ms and mom will
   *  update even if the grad is zero, but in this sparse implementation, ms
   *  and mom will not update in iterations during which the grad is zero.
   *  <p>mean_square = decay * mean_square + (1-decay) * gradient ** 2
   *  Delta = learning_rate * gradient / sqrt(mean_square + epsilon)
   *  <p>$$ms &lt;- rho * ms_{t-1} + (1-rho) * grad * grad$$
   *  $$mom &lt;- momentum * mom_{t-1} + lr * grad / sqrt(ms + epsilon)$$
   *  $$var &lt;- var - mom$$
   *
   * @param <T> data type for {@code out} output
   * @param var Should be from a Variable().
   * @param ms Should be from a Variable().
   * @param mom Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param rho Decay rate. Must be a scalar.
   * @param momentum the momentum value
   * @param epsilon Ridge term. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var, ms and mom.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseApplyRMSProp} output and operands
   * @return a new instance of SparseApplyRmsProp
   */
  public <T extends TType> SparseApplyRmsProp<T> sparseApplyRmsProp(Operand<T> var, Operand<T> ms,
      Operand<T> mom, Operand<T> lr, Operand<T> rho, Operand<T> momentum, Operand<T> epsilon,
      Operand<T> grad, Operand<? extends TNumber> indices, SparseApplyRmsProp.Options... options) {
    return SparseApplyRmsProp.create(scope, var, ms, mom, lr, rho, momentum, epsilon, grad, indices, options);
  }

  /**
   * Returns the gradient of {@code Tile}.
   *  Since {@code Tile} takes an input and repeats the input {@code multiples} times
   *  along each dimension, {@code train.TileGrad} takes in {@code multiples} and aggregates
   *  each repeated tile of {@code input} into {@code output}.
   *
   * @param <T> data type for {@code output} output
   * @param input the input value
   * @param multiples the multiples value
   * @param <T> data type for {@code TileGrad} output and operands
   * @return a new instance of TileGrad
   */
  public <T extends TType> TileGrad<T> tileGrad(Operand<T> input, Operand<TInt32> multiples) {
    return TileGrad.create(scope, input, multiples);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
