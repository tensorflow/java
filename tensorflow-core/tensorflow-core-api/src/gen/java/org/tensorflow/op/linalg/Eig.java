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

package org.tensorflow.op.linalg;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
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
 * Computes the eigen decomposition of one or more square matrices.
 * Computes the eigenvalues and (optionally) right eigenvectors of each inner matrix in
 * {@code input} such that {@code input[..., :, :] = v[..., :, :] * diag(e[..., :])}. The eigenvalues
 * are sorted in non-decreasing order.
 * <pre>
 * # a is a tensor.
 * # e is a tensor of eigenvalues.
 * # v is a tensor of eigenvectors.
 * e, v = eig(a)
 * e = eig(a, compute_v=False)
 * </pre>
 *
 * @param <U> data type for {@code e} output
 */
@OpMetadata(
    opType = Eig.OP_NAME,
    inputsClass = Eig.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class Eig<U extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Eig";

  private Output<U> e;

  private Output<U> v;

  public Eig(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    e = operation.output(outputIdx++);
    v = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Eig operation.
   *
   * @param scope current scope
   * @param input {@code Tensor} input of shape {@code [N, N]}.
   * @param Tout The value of the Tout attribute
   * @param options carries optional attribute values
   * @param <U> data type for {@code Eig} output and operands
   * @return a new instance of Eig
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> Eig<U> create(Scope scope, Operand<? extends TType> input,
      Class<U> Tout, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Eig");
    opBuilder.addInput(input.asOutput());
    opBuilder.setAttr("Tout", Operands.toDataType(Tout));
    if (options != null) {
      for (Options opts : options) {
        if (opts.computeV != null) {
          opBuilder.setAttr("compute_v", opts.computeV);
        }
      }
    }
    return new Eig<>(opBuilder.build());
  }

  /**
   * Sets the computeV option.
   *
   * @param computeV If {@code True} then eigenvectors will be computed and returned in {@code v}.
   * Otherwise, only the eigenvalues will be computed.
   * @return this Options instance.
   */
  public static Options computeV(Boolean computeV) {
    return new Options().computeV(computeV);
  }

  /**
   * Gets e.
   * Eigenvalues. Shape is {@code [N]}.
   * @return e.
   */
  public Output<U> e() {
    return e;
  }

  /**
   * Gets v.
   * Eigenvectors. Shape is {@code [N, N]}.
   * @return v.
   */
  public Output<U> v() {
    return v;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.Eig}
   */
  public static class Options {
    private Boolean computeV;

    private Options() {
    }

    /**
     * Sets the computeV option.
     *
     * @param computeV If {@code True} then eigenvectors will be computed and returned in {@code v}.
     * Otherwise, only the eigenvalues will be computed.
     * @return this Options instance.
     */
    public Options computeV(Boolean computeV) {
      this.computeV = computeV;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Eig.class
  )
  public static class Inputs extends RawOpInputs<Eig<?>> {
    /**
     * {@code Tensor} input of shape {@code [N, N]}.
     */
    public final Operand<? extends TType> input;

    /**
     * If `True` then eigenvectors will be computed and returned in `v`.
     * Otherwise, only the eigenvalues will be computed.
     */
    public final boolean computeV;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tout attribute
     */
    public final DataType Tout;

    public Inputs(GraphOperation op) {
      super(new Eig<>(op), op, Arrays.asList("compute_v", "T", "Tout"));
      int inputIndex = 0;
      input = (Operand<? extends TType>) op.input(inputIndex++);
      computeV = op.attributes().getAttrBool("compute_v");
      T = op.attributes().getAttrType("T");
      Tout = op.attributes().getAttrType("Tout");
    }
  }
}
