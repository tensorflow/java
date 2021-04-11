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
import org.tensorflow.types.family.TType;

/**
 * Update '*var' according to the adagrad scheme.
 * accum += grad * grad
 * var -= lr * grad * (1 / (sqrt(accum) + epsilon))
 */
public final class ResourceApplyAdagrad extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceApplyAdagradV2";

  private ResourceApplyAdagrad(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new ResourceApplyAdagradV2 operation.
   *
   * @param scope current scope
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param epsilon Constant factor. Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyAdagradV2} output and operands
   * @return a new instance of ResourceApplyAdagrad
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ResourceApplyAdagrad create(Scope scope,
      Operand<? extends TType> var, Operand<? extends TType> accum, Operand<T> lr,
      Operand<T> epsilon, Operand<T> grad, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ResourceApplyAdagradV2", scope.makeOpName("ResourceApplyAdagrad"));
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(accum.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(epsilon.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder = scope.apply(opBuilder);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
        if (opts.updateSlots != null) {
          opBuilder.setAttr("update_slots", opts.updateSlots);
        }
      }
    }
    return new ResourceApplyAdagrad(opBuilder.build());
  }

  /**
   * Sets the useLocking option.
   *
   * @param useLocking If {@code True}, updating of the var and accum tensors will be protected
   * by a lock; otherwise the behavior is undefined, but may exhibit less
   * contention.
   * @return this Options instance.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }

  /**
   * Sets the updateSlots option.
   *
   * @param updateSlots the updateSlots option
   * @return this Options instance.
   */
  public static Options updateSlots(Boolean updateSlots) {
    return new Options().updateSlots(updateSlots);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.train.ResourceApplyAdagrad}
   */
  public static class Options {
    private Boolean useLocking;

    private Boolean updateSlots;

    private Options() {
    }

    /**
     * Sets the useLocking option.
     *
     * @param useLocking If {@code True}, updating of the var and accum tensors will be protected
     * by a lock; otherwise the behavior is undefined, but may exhibit less
     * contention.
     * @return this Options instance.
     */
    public Options useLocking(Boolean useLocking) {
      this.useLocking = useLocking;
      return this;
    }

    /**
     * Sets the updateSlots option.
     *
     * @param updateSlots the updateSlots option
     * @return this Options instance.
     */
    public Options updateSlots(Boolean updateSlots) {
      this.updateSlots = updateSlots;
      return this;
    }
  }
}
