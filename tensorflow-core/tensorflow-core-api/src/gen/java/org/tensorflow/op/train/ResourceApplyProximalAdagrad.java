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
 * Update '*var' and '*accum' according to FOBOS with Adagrad learning rate.
 * accum += grad * grad
 * prox_v = var - lr * grad * (1 / sqrt(accum))
 * var = sign(prox_v)/(1+lr<em>l2) * max{|prox_v|-lr</em>l1,0}
 */
@OpMetadata(
    opType = ResourceApplyProximalAdagrad.OP_NAME,
    inputsClass = ResourceApplyProximalAdagrad.Inputs.class
)
@Operator(
    group = "train"
)
public final class ResourceApplyProximalAdagrad extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceApplyProximalAdagrad";

  public ResourceApplyProximalAdagrad(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new ResourceApplyProximalAdagrad operation.
   *
   * @param scope current scope
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
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ResourceApplyProximalAdagrad create(Scope scope,
      Operand<? extends TType> var, Operand<? extends TType> accum, Operand<T> lr, Operand<T> l1,
      Operand<T> l2, Operand<T> grad, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResourceApplyProximalAdagrad");
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(accum.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(l1.asOutput());
    opBuilder.addInput(l2.asOutput());
    opBuilder.addInput(grad.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new ResourceApplyProximalAdagrad(opBuilder.build());
  }

  /**
   * Sets the useLocking option.
   *
   * @param useLocking If True, updating of the var and accum tensors will be protected by
   * a lock; otherwise the behavior is undefined, but may exhibit less contention.
   * @return this Options instance.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.train.ResourceApplyProximalAdagrad}
   */
  public static class Options {
    private Boolean useLocking;

    private Options() {
    }

    /**
     * Sets the useLocking option.
     *
     * @param useLocking If True, updating of the var and accum tensors will be protected by
     * a lock; otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
    public Options useLocking(Boolean useLocking) {
      this.useLocking = useLocking;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ResourceApplyProximalAdagrad.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<ResourceApplyProximalAdagrad> {
    /**
     * Should be from a Variable().
     */
    public final Operand<? extends TType> var;

    /**
     * Should be from a Variable().
     */
    public final Operand<? extends TType> accum;

    /**
     * Scaling factor. Must be a scalar.
     */
    public final Operand<T> lr;

    /**
     * L1 regularization. Must be a scalar.
     */
    public final Operand<T> l1;

    /**
     * L2 regularization. Must be a scalar.
     */
    public final Operand<T> l2;

    /**
     * The gradient.
     */
    public final Operand<T> grad;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * If True, updating of the var and accum tensors will be protected by
     * a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
    public final boolean useLocking;

    public Inputs(GraphOperation op) {
      super(new ResourceApplyProximalAdagrad(op), op, Arrays.asList("T", "use_locking"));
      int inputIndex = 0;
      var = (Operand<? extends TType>) op.input(inputIndex++);
      accum = (Operand<? extends TType>) op.input(inputIndex++);
      lr = (Operand<T>) op.input(inputIndex++);
      l1 = (Operand<T>) op.input(inputIndex++);
      l2 = (Operand<T>) op.input(inputIndex++);
      grad = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      useLocking = op.attributes().getAttrBool("use_locking");
    }
  }
}
