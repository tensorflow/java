/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * var: Should be from a Variable().
 *
 * @param <T> data type for {@code out} output
 */
@OpMetadata(
    opType = SparseApplyAdadelta.OP_NAME,
    inputsClass = SparseApplyAdadelta.Inputs.class
)
@Operator(
    group = "train"
)
public final class SparseApplyAdadelta<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseApplyAdadelta";

  private Output<T> out;

  public SparseApplyAdadelta(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    out = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseApplyAdadelta operation.
   *
   * @param scope current scope
   * @param var The var value
   * @param accum Should be from a Variable().
   * @param accumUpdate : Should be from a Variable().
   * @param lr Learning rate. Must be a scalar.
   * @param rho Decay factor. Must be a scalar.
   * @param epsilon Constant factor. Must be a scalar.
   * @param grad The gradient.
   * @param indices A vector of indices into the first dimension of var and accum.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseApplyAdadelta} output and operands
   * @return a new instance of SparseApplyAdadelta
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseApplyAdadelta<T> create(Scope scope, Operand<T> var,
      Operand<T> accum, Operand<T> accumUpdate, Operand<T> lr, Operand<T> rho, Operand<T> epsilon,
      Operand<T> grad, Operand<? extends TNumber> indices, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseApplyAdadelta");
    opBuilder.addInput(var.asOutput());
    opBuilder.addInput(accum.asOutput());
    opBuilder.addInput(accumUpdate.asOutput());
    opBuilder.addInput(lr.asOutput());
    opBuilder.addInput(rho.asOutput());
    opBuilder.addInput(epsilon.asOutput());
    opBuilder.addInput(grad.asOutput());
    opBuilder.addInput(indices.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.useLocking != null) {
          opBuilder.setAttr("use_locking", opts.useLocking);
        }
      }
    }
    return new SparseApplyAdadelta<>(opBuilder.build());
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
   * Optional attributes for {@link org.tensorflow.op.train.SparseApplyAdadelta}
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
      outputsClass = SparseApplyAdadelta.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SparseApplyAdadelta<T>> {
    /**
     * The var input
     */
    public final Operand<T> var;

    /**
     * Should be from a Variable().
     */
    public final Operand<T> accum;

    /**
     * : Should be from a Variable().
     */
    public final Operand<T> accumUpdate;

    /**
     * Learning rate. Must be a scalar.
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
     * A vector of indices into the first dimension of var and accum.
     */
    public final Operand<? extends TNumber> indices;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    /**
     * If True, updating of the var and accum tensors will be protected by
     * a lock; otherwise the behavior is undefined, but may exhibit less contention.
     */
    public final boolean useLocking;

    public Inputs(GraphOperation op) {
      super(new SparseApplyAdadelta<>(op), op, Arrays.asList("T", "Tindices", "use_locking"));
      int inputIndex = 0;
      var = (Operand<T>) op.input(inputIndex++);
      accum = (Operand<T>) op.input(inputIndex++);
      accumUpdate = (Operand<T>) op.input(inputIndex++);
      lr = (Operand<T>) op.input(inputIndex++);
      rho = (Operand<T>) op.input(inputIndex++);
      epsilon = (Operand<T>) op.input(inputIndex++);
      grad = (Operand<T>) op.input(inputIndex++);
      indices = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
      useLocking = op.attributes().getAttrBool("use_locking");
    }
  }
}
