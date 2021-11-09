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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Update '*var' according to the Adam algorithm.
 * $$\text{lr}<em>t := \mathrm{lr} \cdot \frac{\sqrt{1 - \beta_2^t}}{1 - \beta_1^t}$$
 * $$m_t := \beta_1 \cdot m</em>{t-1} + (1 - \beta_1) \cdot g$$
 * $$v_t := \beta_2 \cdot v_{t-1} + (1 - \beta_2) \cdot g^2$$
 * $$\text{var} := \begin{cases} \text{var} - (m_t \beta_1 + g \cdot (1 - \beta_1))\cdot\text{lr}_t/(\sqrt{v_t} + \epsilon), &amp;\text{if use_nesterov}\\  \text{var} - m_t \cdot \text{lr}_t /(\sqrt{v_t} + \epsilon), &amp;\text{otherwise} \end{cases}$$
 */
@OpMetadata(
    opType = ResourceApplyAdam.OP_NAME,
    inputsClass = ResourceApplyAdam.Inputs.class
)
@Operator(
    group = "train"
)
public final class ResourceApplyAdam extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceApplyAdam";

  public ResourceApplyAdam(Operation operation) {
    super(operation, OP_NAME);
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
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResourceApplyAdam");
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

  @OpInputsMetadata(
      outputsClass = ResourceApplyAdam.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<ResourceApplyAdam> {
    /**
     * Should be from a Variable().
     */
    public final Operand<? extends TType> var;

    /**
     * Should be from a Variable().
     */
    public final Operand<? extends TType> m;

    /**
     * Should be from a Variable().
     */
    public final Operand<? extends TType> v;

    /**
     * Must be a scalar.
     */
    public final Operand<T> beta1Power;

    /**
     * Must be a scalar.
     */
    public final Operand<T> beta2Power;

    /**
     * Scaling factor. Must be a scalar.
     */
    public final Operand<T> lr;

    /**
     * Momentum factor. Must be a scalar.
     */
    public final Operand<T> beta1;

    /**
     * Momentum factor. Must be a scalar.
     */
    public final Operand<T> beta2;

    /**
     * Ridge term. Must be a scalar.
     */
    public final Operand<T> epsilon;

    /**
     * The gradient.
     */
    public final Operand<T> grad;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * If `True`, updating of the var, m, and v tensors will be protected
     * by a lock; otherwise the behavior is undefined, but may exhibit less
     * contention.
     */
    public final boolean useLocking;

    /**
     * If `True`, uses the nesterov update.
     */
    public final boolean useNesterov;

    public Inputs(GraphOperation op) {
      super(new ResourceApplyAdam(op), op, Arrays.asList("T", "use_locking", "use_nesterov"));
      int inputIndex = 0;
      var = (Operand<? extends TType>) op.input(inputIndex++);
      m = (Operand<? extends TType>) op.input(inputIndex++);
      v = (Operand<? extends TType>) op.input(inputIndex++);
      beta1Power = (Operand<T>) op.input(inputIndex++);
      beta2Power = (Operand<T>) op.input(inputIndex++);
      lr = (Operand<T>) op.input(inputIndex++);
      beta1 = (Operand<T>) op.input(inputIndex++);
      beta2 = (Operand<T>) op.input(inputIndex++);
      epsilon = (Operand<T>) op.input(inputIndex++);
      grad = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      useLocking = op.attributes().getAttrBool("use_locking");
      useNesterov = op.attributes().getAttrBool("use_nesterov");
    }
  }
}
