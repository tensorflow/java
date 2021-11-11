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
 * Update '*var' according to the centered RMSProp algorithm.
 * The centered RMSProp algorithm uses an estimate of the centered second moment
 * (i.e., the variance) for normalization, as opposed to regular RMSProp, which
 * uses the (uncentered) second moment. This often helps with training, but is
 * slightly more expensive in terms of computation and memory.
 * <p>Note that in dense implementation of this algorithm, mg, ms, and mom will
 * update even if the grad is zero, but in this sparse implementation, mg, ms,
 * and mom will not update in iterations during which the grad is zero.
 * <p>mean_square = decay * mean_square + (1-decay) * gradient ** 2
 * mean_grad = decay * mean_grad + (1-decay) * gradient
 * <p>Delta = learning_rate * gradient / sqrt(mean_square + epsilon - mean_grad ** 2)
 * <p>mg &lt;- rho * mg_{t-1} + (1-rho) * grad
 * ms &lt;- rho * ms_{t-1} + (1-rho) * grad * grad
 * mom &lt;- momentum * mom_{t-1} + lr * grad / sqrt(ms - mg * mg + epsilon)
 * var &lt;- var - mom
 */
@OpMetadata(
    opType = ResourceApplyCenteredRmsProp.OP_NAME,
    inputsClass = ResourceApplyCenteredRmsProp.Inputs.class
)
@Operator(
    group = "train"
)
public final class ResourceApplyCenteredRmsProp extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceApplyCenteredRMSProp";

  public ResourceApplyCenteredRmsProp(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new ResourceApplyCenteredRMSProp operation.
   *
   * @param scope current scope
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
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ResourceApplyCenteredRmsProp create(Scope scope,
      Operand<? extends TType> var, Operand<? extends TType> mg, Operand<? extends TType> ms,
      Operand<? extends TType> mom, Operand<T> lr, Operand<T> rho, Operand<T> momentum,
      Operand<T> epsilon, Operand<T> grad, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResourceApplyCenteredRmsProp");
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(mg.asOutput());
    opBuilder.addInput(ms.asOutput());
    opBuilder.addInput(mom.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(rho.asOutput());
    opBuilder.addInput(momentum.asOutput());
    opBuilder.addInput(epsilon.asOutput());
    opBuilder.addInput(grad.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new ResourceApplyCenteredRmsProp(opBuilder.build());
  }

  /**
   * Sets the useLocking option.
   *
   * @param useLocking If {@code True}, updating of the var, mg, ms, and mom tensors is
   * protected by a lock; otherwise the behavior is undefined, but may exhibit less
   * contention.
   * @return this Options instance.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.train.ResourceApplyCenteredRmsProp}
   */
  public static class Options {
    private Boolean useLocking;

    private Options() {
    }

    /**
     * Sets the useLocking option.
     *
     * @param useLocking If {@code True}, updating of the var, mg, ms, and mom tensors is
     * protected by a lock; otherwise the behavior is undefined, but may exhibit less
     * contention.
     * @return this Options instance.
     */
    public Options useLocking(Boolean useLocking) {
      this.useLocking = useLocking;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ResourceApplyCenteredRmsProp.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<ResourceApplyCenteredRmsProp> {
    /**
     * Should be from a Variable().
     */
    public final Operand<? extends TType> var;

    /**
     * Should be from a Variable().
     */
    public final Operand<? extends TType> mg;

    /**
     * Should be from a Variable().
     */
    public final Operand<? extends TType> ms;

    /**
     * Should be from a Variable().
     */
    public final Operand<? extends TType> mom;

    /**
     * Scaling factor. Must be a scalar.
     */
    public final Operand<T> lr;

    /**
     * Decay rate. Must be a scalar.
     */
    public final Operand<T> rho;

    /**
     * Momentum Scale. Must be a scalar.
     */
    public final Operand<T> momentum;

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
     * If `True`, updating of the var, mg, ms, and mom tensors is
     * protected by a lock; otherwise the behavior is undefined, but may exhibit less
     * contention.
     */
    public final boolean useLocking;

    public Inputs(GraphOperation op) {
      super(new ResourceApplyCenteredRmsProp(op), op, Arrays.asList("T", "use_locking"));
      int inputIndex = 0;
      var = (Operand<? extends TType>) op.input(inputIndex++);
      mg = (Operand<? extends TType>) op.input(inputIndex++);
      ms = (Operand<? extends TType>) op.input(inputIndex++);
      mom = (Operand<? extends TType>) op.input(inputIndex++);
      lr = (Operand<T>) op.input(inputIndex++);
      rho = (Operand<T>) op.input(inputIndex++);
      momentum = (Operand<T>) op.input(inputIndex++);
      epsilon = (Operand<T>) op.input(inputIndex++);
      grad = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      useLocking = op.attributes().getAttrBool("use_locking");
    }
  }
}
