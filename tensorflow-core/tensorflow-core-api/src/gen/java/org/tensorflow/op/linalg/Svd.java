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
 * Computes the singular value decompositions of one or more matrices.
 * Computes the SVD of each inner matrix in {@code input} such that
 * {@code input[..., :, :] = u[..., :, :] * diag(s[..., :, :]) * transpose(v[..., :, :])}
 * <pre>
 * # a is a tensor containing a batch of matrices.
 * # s is a tensor of singular values for each matrix.
 * # u is the tensor containing the left singular vectors for each matrix.
 * # v is the tensor containing the right singular vectors for each matrix.
 * s, u, v = svd(a)
 * s, _, _ = svd(a, compute_uv=False)
 * </pre>
 *
 * @param <T> data type for {@code s} output
 */
@OpMetadata(
    opType = Svd.OP_NAME,
    inputsClass = Svd.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class Svd<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Svd";

  private Output<T> s;

  private Output<T> u;

  private Output<T> v;

  public Svd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    s = operation.output(outputIdx++);
    u = operation.output(outputIdx++);
    v = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Svd operation.
   *
   * @param scope current scope
   * @param input A tensor of shape {@code [..., M, N]} whose inner-most 2 dimensions
   * form matrices of size {@code [M, N]}. Let {@code P} be the minimum of {@code M} and {@code N}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Svd} output and operands
   * @return a new instance of Svd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Svd<T> create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Svd");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.computeUv != null) {
          opBuilder.setAttr("compute_uv", opts.computeUv);
        }
        if (opts.fullMatrices != null) {
          opBuilder.setAttr("full_matrices", opts.fullMatrices);
        }
      }
    }
    return new Svd<>(opBuilder.build());
  }

  /**
   * Sets the computeUv option.
   *
   * @param computeUv If true, left and right singular vectors will be
   * computed and returned in {@code u} and {@code v}, respectively.
   * If false, {@code u} and {@code v} are not set and should never referenced.
   * @return this Options instance.
   */
  public static Options computeUv(Boolean computeUv) {
    return new Options().computeUv(computeUv);
  }

  /**
   * Sets the fullMatrices option.
   *
   * @param fullMatrices If true, compute full-sized {@code u} and {@code v}. If false
   * (the default), compute only the leading {@code P} singular vectors.
   * Ignored if {@code compute_uv} is {@code False}.
   * @return this Options instance.
   */
  public static Options fullMatrices(Boolean fullMatrices) {
    return new Options().fullMatrices(fullMatrices);
  }

  /**
   * Gets s.
   * Singular values. Shape is {@code [..., P]}.
   * @return s.
   */
  public Output<T> s() {
    return s;
  }

  /**
   * Gets u.
   * Left singular vectors. If {@code full_matrices} is {@code False} then shape is
   * {@code [..., M, P]}; if {@code full_matrices} is {@code True} then shape is
   * {@code [..., M, M]}. Undefined if {@code compute_uv} is {@code False}.
   * @return u.
   */
  public Output<T> u() {
    return u;
  }

  /**
   * Gets v.
   * Left singular vectors. If {@code full_matrices} is {@code False} then shape is
   * {@code [..., N, P]}. If {@code full_matrices} is {@code True} then shape is {@code [..., N, N]}.
   * Undefined if {@code compute_uv} is false.
   * @return v.
   */
  public Output<T> v() {
    return v;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.Svd}
   */
  public static class Options {
    private Boolean computeUv;

    private Boolean fullMatrices;

    private Options() {
    }

    /**
     * Sets the computeUv option.
     *
     * @param computeUv If true, left and right singular vectors will be
     * computed and returned in {@code u} and {@code v}, respectively.
     * If false, {@code u} and {@code v} are not set and should never referenced.
     * @return this Options instance.
     */
    public Options computeUv(Boolean computeUv) {
      this.computeUv = computeUv;
      return this;
    }

    /**
     * Sets the fullMatrices option.
     *
     * @param fullMatrices If true, compute full-sized {@code u} and {@code v}. If false
     * (the default), compute only the leading {@code P} singular vectors.
     * Ignored if {@code compute_uv} is {@code False}.
     * @return this Options instance.
     */
    public Options fullMatrices(Boolean fullMatrices) {
      this.fullMatrices = fullMatrices;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Svd.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Svd<T>> {
    /**
     * A tensor of shape {@code [..., M, N]} whose inner-most 2 dimensions
     * form matrices of size {@code [M, N]}. Let {@code P} be the minimum of {@code M} and {@code N}.
     */
    public final Operand<T> input;

    /**
     * If true, left and right singular vectors will be
     * computed and returned in `u` and `v`, respectively.
     * If false, `u` and `v` are not set and should never referenced.
     */
    public final boolean computeUv;

    /**
     * If true, compute full-sized `u` and `v`. If false
     * (the default), compute only the leading `P` singular vectors.
     * Ignored if `compute_uv` is `False`.
     */
    public final boolean fullMatrices;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Svd<>(op), op, Arrays.asList("compute_uv", "full_matrices", "T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      computeUv = op.attributes().getAttrBool("compute_uv");
      fullMatrices = op.attributes().getAttrBool("full_matrices");
      T = op.attributes().getAttrType("T");
    }
  }
}
