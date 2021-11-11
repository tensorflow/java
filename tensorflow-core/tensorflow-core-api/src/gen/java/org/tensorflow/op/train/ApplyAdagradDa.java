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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Update '*var' according to the proximal adagrad scheme.
 *
 * @param <T> data type for {@code out} output
 */
@OpMetadata(
    opType = ApplyAdagradDa.OP_NAME,
    inputsClass = ApplyAdagradDa.Inputs.class
)
@Operator(
    group = "train"
)
public final class ApplyAdagradDa<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "ApplyAdagradDA";

  private Output<T> out;

  public ApplyAdagradDa(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new ApplyAdagradDA operation.
   *
   * @param scope current scope
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
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> ApplyAdagradDa<T> create(Scope scope, Operand<T> var,
      Operand<T> gradientAccumulator, Operand<T> gradientSquaredAccumulator, Operand<T> grad,
      Operand<T> lr, Operand<T> l1, Operand<T> l2, Operand<TInt64> globalStep, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "ApplyAdagradDa");
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(gradientAccumulator.asOutput());
    opBuilder.addInput(gradientSquaredAccumulator.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(l1.asOutput());
    opBuilder.addInput(l2.asOutput());
    opBuilder.addInput(globalStep.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new ApplyAdagradDa<>(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.train.ApplyAdagradDa}
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
      outputsClass = ApplyAdagradDa.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<ApplyAdagradDa<T>> {
    /**
     * Should be from a Variable().
     */
    public final Operand<T> var;

    /**
     * Should be from a Variable().
     */
    public final Operand<T> gradientAccumulator;

    /**
     * Should be from a Variable().
     */
    public final Operand<T> gradientSquaredAccumulator;

    /**
     * The gradient.
     */
    public final Operand<T> grad;

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
     * Training step number. Must be a scalar.
     */
    public final Operand<TInt64> globalStep;

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
      super(new ApplyAdagradDa<>(op), op, Arrays.asList("T", "use_locking"));
      int inputIndex = 0;
      var = (Operand<T>) op.input(inputIndex++);
      gradientAccumulator = (Operand<T>) op.input(inputIndex++);
      gradientSquaredAccumulator = (Operand<T>) op.input(inputIndex++);
      grad = (Operand<T>) op.input(inputIndex++);
      lr = (Operand<T>) op.input(inputIndex++);
      l1 = (Operand<T>) op.input(inputIndex++);
      l2 = (Operand<T>) op.input(inputIndex++);
      globalStep = (Operand<TInt64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      useLocking = op.attributes().getAttrBool("use_locking");
    }
  }
}
