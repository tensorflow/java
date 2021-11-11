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
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Update '*var' according to the adagrad scheme.
 * accum += grad * grad
 * var -= lr * grad * (1 / sqrt(accum))
 *
 * @param <T> data type for {@code out} output
 */
@OpMetadata(
    opType = ApplyAdagradV2.OP_NAME,
    inputsClass = ApplyAdagradV2.Inputs.class
)
public final class ApplyAdagradV2<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ApplyAdagradV2";

  private Output<T> out;

  public ApplyAdagradV2(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ApplyAdagradV2 operation.
   *
   * @param scope current scope
   * @param var Should be from a Variable().
   * @param accum Should be from a Variable().
   * @param lr Scaling factor. Must be a scalar.
   * @param epsilon Constant factor. Must be a scalar.
   * @param grad The gradient.
   * @param options carries optional attribute values
   * @param <T> data type for {@code ApplyAdagradV2} output and operands
   * @return a new instance of ApplyAdagradV2
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ApplyAdagradV2<T> create(Scope scope, Operand<T> var,
      Operand<T> accum, Operand<T> lr, Operand<T> epsilon, Operand<T> grad, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ApplyAdagradV2");
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(accum.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(epsilon.asOutput());
    opBuilder.addInput(grad.asOutput());
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
    return new ApplyAdagradV2<>(opBuilder.build());
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
   * Gets out.
   * Same as &quot;var&quot;.
   * @return out.
   */
  public Output<T> out() {
    return out;
  }

  @Override
  public Output<T> asOutput() {
    return out;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.train.ApplyAdagradV2}
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

  @OpInputsMetadata(
      outputsClass = ApplyAdagradV2.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<ApplyAdagradV2<T>> {
    /**
     * Should be from a Variable().
     */
    public final Operand<T> var;

    /**
     * Should be from a Variable().
     */
    public final Operand<T> accum;

    /**
     * Scaling factor. Must be a scalar.
     */
    public final Operand<T> lr;

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
     * If `True`, updating of the var and accum tensors will be protected
     * by a lock; otherwise the behavior is undefined, but may exhibit less
     * contention.
     */
    public final boolean useLocking;

    /**
     * The updateSlots attribute
     */
    public final boolean updateSlots;

    public Inputs(GraphOperation op) {
      super(new ApplyAdagradV2<>(op), op, Arrays.asList("T", "use_locking", "update_slots"));
      int inputIndex = 0;
      var = (Operand<T>) op.input(inputIndex++);
      accum = (Operand<T>) op.input(inputIndex++);
      lr = (Operand<T>) op.input(inputIndex++);
      epsilon = (Operand<T>) op.input(inputIndex++);
      grad = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      useLocking = op.attributes().getAttrBool("use_locking");
      updateSlots = op.attributes().getAttrBool("update_slots");
    }
  }
}
