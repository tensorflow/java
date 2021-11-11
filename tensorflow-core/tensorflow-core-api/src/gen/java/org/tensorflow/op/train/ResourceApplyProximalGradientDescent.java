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
 * Update '*var' as FOBOS algorithm with fixed learning rate.
 * prox_v = var - alpha * delta
 * var = sign(prox_v)/(1+alpha<em>l2) * max{|prox_v|-alpha</em>l1,0}
 */
@OpMetadata(
    opType = ResourceApplyProximalGradientDescent.OP_NAME,
    inputsClass = ResourceApplyProximalGradientDescent.Inputs.class
)
@Operator(
    group = "train"
)
public final class ResourceApplyProximalGradientDescent extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceApplyProximalGradientDescent";

  public ResourceApplyProximalGradientDescent(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new ResourceApplyProximalGradientDescent operation.
   *
   * @param scope current scope
   * @param var Should be from a Variable().
   * @param alpha Scaling factor. Must be a scalar.
   * @param l1 L1 regularization. Must be a scalar.
   * @param l2 L2 regularization. Must be a scalar.
   * @param delta The change.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ResourceApplyProximalGradientDescent} output and operands
   * @return a new instance of ResourceApplyProximalGradientDescent
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ResourceApplyProximalGradientDescent create(Scope scope,
      Operand<? extends TType> var, Operand<T> alpha, Operand<T> l1, Operand<T> l2,
      Operand<T> delta, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResourceApplyProximalGradientDescent");
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(alpha.asOutput());
    opBuilder.addInput(l1.asOutput());
    opBuilder.addInput(l2.asOutput());
    opBuilder.addInput(delta.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new ResourceApplyProximalGradientDescent(opBuilder.build());
  }

  /**
   * Sets the useLocking option.
   *
   * @param useLocking If True, the subtraction will be protected by a lock;
   * otherwise the behavior is undefined, but may exhibit less contention.
   * @return this Options instance.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.train.ResourceApplyProximalGradientDescent}
   */
  public static class Options {
    private Boolean useLocking;

    private Options() {
    }

    /**
     * Sets the useLocking option.
     *
     * @param useLocking If True, the subtraction will be protected by a lock;
     * otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
    public Options useLocking(Boolean useLocking) {
      this.useLocking = useLocking;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ResourceApplyProximalGradientDescent.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<ResourceApplyProximalGradientDescent> {
    /**
     * Should be from a Variable().
     */
    public final Operand<? extends TType> var;

    /**
     * Scaling factor. Must be a scalar.
     */
    public final Operand<T> alpha;

    /**
     * L1 regularization. Must be a scalar.
     */
    public final Operand<T> l1;

    /**
     * L2 regularization. Must be a scalar.
     */
    public final Operand<T> l2;

    /**
     * The change.
     */
    public final Operand<T> delta;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * If True, the subtraction will be protected by a lock;
     * otherwise the behavior is undefined, but may exhibit less contention.
     */
    public final boolean useLocking;

    public Inputs(GraphOperation op) {
      super(new ResourceApplyProximalGradientDescent(op), op, Arrays.asList("T", "use_locking"));
      int inputIndex = 0;
      var = (Operand<? extends TType>) op.input(inputIndex++);
      alpha = (Operand<T>) op.input(inputIndex++);
      l1 = (Operand<T>) op.input(inputIndex++);
      l2 = (Operand<T>) op.input(inputIndex++);
      delta = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      useLocking = op.attributes().getAttrBool("use_locking");
    }
  }
}
