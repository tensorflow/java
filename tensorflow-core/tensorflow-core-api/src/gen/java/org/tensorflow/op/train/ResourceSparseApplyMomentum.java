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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Update relevant entries in '*var' and '*accum' according to the momentum scheme.
 * Set use_nesterov = True if you want to use Nesterov momentum.
 * <p>That is for rows we have grad for, we update var and accum as follows:
 * <p>accum = accum * momentum + grad
 * var -= lr * accum
 */
@Operator(
    group = "train"
)
public final class ResourceSparseApplyMomentum extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceSparseApplyMomentum";

  private ResourceSparseApplyMomentum(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new ResourceSparseApplyMomentum operation.
   *
   * @param scope current scope
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
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ResourceSparseApplyMomentum create(Scope scope,
      Operand<? extends TType> var, Operand<? extends TType> accum, Operand<T> lr, Operand<T> grad,
      Operand<? extends TNumber> indices, Operand<T> momentum, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("ResourceSparseApplyMomentum", scope.makeOpName("ResourceSparseApplyMomentum"));
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(accum.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder.addInput(momentum.asOutput());
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
    return new ResourceSparseApplyMomentum(opBuilder.build());
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
   * Sets the useNesterov option.
   *
   * @param useNesterov If {@code True}, the tensor passed to compute grad will be
   * var - lr * momentum * accum, so in the end, the var you get is actually
   * var - lr * momentum * accum.
   * @return this Options instance.
   */
  public static Options useNesterov(Boolean useNesterov) {
    return new Options().useNesterov(useNesterov);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.train.ResourceSparseApplyMomentum}
   */
  public static class Options {
    private Boolean useLocking;

    private Boolean useNesterov;

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
     * Sets the useNesterov option.
     *
     * @param useNesterov If {@code True}, the tensor passed to compute grad will be
     * var - lr * momentum * accum, so in the end, the var you get is actually
     * var - lr * momentum * accum.
     * @return this Options instance.
     */
    public Options useNesterov(Boolean useNesterov) {
      this.useNesterov = useNesterov;
      return this;
    }
  }
}
