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
 * Update '*var' according to the adadelta scheme.
 * accum = rho() * accum + (1 - rho()) * grad.square();
 * update = (update_accum + epsilon).sqrt() * (accum + epsilon()).rsqrt() * grad;
 * update_accum = rho() * update_accum + (1 - rho()) * update.square();
 * var -= update;
 */
@OpMetadata(
    opType = ResourceApplyAdadelta.OP_NAME,
    inputsClass = ResourceApplyAdadelta.Inputs.class
)
@Operator(
    group = "train"
)
public final class ResourceApplyAdadelta extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ResourceApplyAdadelta";

  public ResourceApplyAdadelta(Operation operation) {
    super(operation, OP_NAME);
  }

  /**
   * Factory method to create a class wrapping a new ResourceApplyAdadelta operation.
   *
   * @param scope current scope
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
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ResourceApplyAdadelta create(Scope scope,
      Operand<? extends TType> var, Operand<? extends TType> accum,
      Operand<? extends TType> accumUpdate, Operand<T> lr, Operand<T> rho, Operand<T> epsilon,
      Operand<T> grad, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ResourceApplyAdadelta");
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(accum.asOutput());
    opBuilder.addInput(accumUpdate.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(rho.asOutput());
    opBuilder.addInput(epsilon.asOutput());
    opBuilder.addInput(grad.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new ResourceApplyAdadelta(opBuilder.build());
  }

  /**
   * Sets the useLocking option.
   *
   * @param useLocking If True, updating of the var, accum and update_accum tensors will be protected by
   * a lock; otherwise the behavior is undefined, but may exhibit less contention.
   * @return this Options instance.
   */
  public static Options useLocking(Boolean useLocking) {
    return new Options().useLocking(useLocking);
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.train.ResourceApplyAdadelta}
   */
  public static class Options {
    private Boolean useLocking;

    private Options() {
    }

    /**
     * Sets the useLocking option.
     *
     * @param useLocking If True, updating of the var, accum and update_accum tensors will be protected by
     * a lock; otherwise the behavior is undefined, but may exhibit less contention.
     * @return this Options instance.
     */
    public Options useLocking(Boolean useLocking) {
      this.useLocking = useLocking;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = ResourceApplyAdadelta.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<ResourceApplyAdadelta> {
    /**
     * Should be from a Variable().
     */
    public final Operand<? extends TType> var;

    /**
     * Should be from a Variable().
     */
    public final Operand<? extends TType> accum;

    /**
     * Should be from a Variable().
     */
    public final Operand<? extends TType> accumUpdate;

    /**
     * Scaling factor. Must be a scalar.
     */
    public final Operand<T> lr;

    /**
     * Decay factor. Must be a scalar.
     */
    public final Operand<T> rho;

    /**
     * Constant factor. Must be a scalar.
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
     * If True, updating of the var, accum and update_accum tensors will be protected by
     * a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
    public final boolean useLocking;

    public Inputs(GraphOperation op) {
      super(new ResourceApplyAdadelta(op), op, Arrays.asList("T", "use_locking"));
      int inputIndex = 0;
      var = (Operand<? extends TType>) op.input(inputIndex++);
      accum = (Operand<? extends TType>) op.input(inputIndex++);
      accumUpdate = (Operand<? extends TType>) op.input(inputIndex++);
      lr = (Operand<T>) op.input(inputIndex++);
      rho = (Operand<T>) op.input(inputIndex++);
      epsilon = (Operand<T>) op.input(inputIndex++);
      grad = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      useLocking = op.attributes().getAttrBool("use_locking");
    }
  }
}
