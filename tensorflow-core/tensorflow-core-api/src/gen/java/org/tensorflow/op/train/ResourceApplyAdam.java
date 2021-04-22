/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.train;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Update '*var' according to the Adam algorithm.
 * $$\text{lr}<em>t := \mathrm{learning_rate} * \sqrt{1 - \beta_2^t} / (1 - \beta_1^t)$$
 * $$m_t := \beta_1 * m</em>{t-1} + (1 - \beta_1) * g$$
 * $$v_t := \beta_2 * v_{t-1} + (1 - \beta_2) * g * g$$
 * $$\text{variable} := \text{variable} - \text{lr}_t * m_t / (\sqrt{v_t} + \epsilon)$$
 */
@Operator(
    group = "train"
)
public final class ResourceApplyAdam extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceApplyAdam";

  private ResourceApplyAdam(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new ResourceApplyAdam operation.
   *
   * @param scope current scope
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
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ResourceApplyAdam create(Scope scope,
      Operand<? extends TType> var, Operand<? extends TType> m, Operand<? extends TType> v,
      Operand<T> beta1Power, Operand<T> beta2Power, Operand<T> lr, Operand<T> beta1,
      Operand<T> beta2, Operand<T> epsilon, Operand<T> grad, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ResourceApplyAdam", scope.makeOpName("ResourceApplyAdam"));
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(m.asOutput());
    opBuilder.addInput(v.asOutput());
    opBuilder.addInput(beta1Power.asOutput());
    opBuilder.addInput(beta2Power.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(beta1.asOutput());
    opBuilder.addInput(beta2.asOutput());
    opBuilder.addInput(epsilon.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
        if (opts.useNesterov != null) {
          opBuilder.setAttr("use_nesterov", opts.useNesterov);
        }
      }
    }
    return new ResourceApplyAdam(opBuilder.build());
  }

  /**
   * Sets the useLocking option.
   *
   * @param useLocking If {@code True}, updating of the var, m, and v tensors will be protected
   * by a lock; otherwise the behavior is undefined, but may exhibit less
   * contention.
   * @return this Options instance.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }

  /**
   * Sets the useNesterov option.
   *
   * @param useNesterov If {@code True}, uses the nesterov update.
   * @return this Options instance.
   */
  public static Options useNesterov(Boolean useNesterov) {
    return new Options().useNesterov(useNesterov);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.train.ResourceApplyAdam}
   */
  public static class Options {
    private Boolean useLocking;

    private Boolean useNesterov;

    private Options() {
    }

    /**
     * Sets the useLocking option.
     *
     * @param useLocking If {@code True}, updating of the var, m, and v tensors will be protected
     * by a lock; otherwise the behavior is undefined, but may exhibit less
     * contention.
     * @return this Options instance.
     */
    public Options useLocking(Boolean useLocking) {
      this.useLocking = useLocking;
      return this;
    }

    /**
     * Sets the useNesterov option.
     *
     * @param useNesterov If {@code True}, uses the nesterov update.
     * @return this Options instance.
     */
    public Options useNesterov(Boolean useNesterov) {
      this.useNesterov = useNesterov;
      return this;
    }
  }
}
