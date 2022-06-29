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
 * Computes the QR decompositions of one or more matrices.
 * Computes the QR decomposition of each inner matrix in {@code tensor} such that
 * {@code tensor[..., :, :] = q[..., :, :] * r[..., :,:])}
 * <p>Currently, the gradient for the QR decomposition is well-defined only when
 * the first {@code P} columns of the inner matrix are linearly independent, where
 * {@code P} is the minimum of {@code M} and {@code N}, the 2 inner-most dimmensions of {@code tensor}.
 * <pre>
 * # a is a tensor.
 * # q is a tensor of orthonormal matrices.
 * # r is a tensor of upper triangular matrices.
 * q, r = qr(a)
 * q_full, r_full = qr(a, full_matrices=True)
 * </pre>
 *
 * @param <T> data type for {@code q} output
 */
@OpMetadata(
    opType = Qr.OP_NAME,
    inputsClass = Qr.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class Qr<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "Qr";

  private Output<T> q;

  private Output<T> r;

  public Qr(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    q = operation.output(outputIdx++);
    r = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new Qr operation.
   *
   * @param scope current scope
   * @param input A tensor of shape {@code [..., M, N]} whose inner-most 2 dimensions
   * form matrices of size {@code [M, N]}. Let {@code P} be the minimum of {@code M} and {@code N}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code Qr} output and operands
   * @return a new instance of Qr
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> Qr<T> create(Scope scope, Operand<T> input, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "Qr");
    opBuilder.addInput(input.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.fullMatrices != null) {
          opBuilder.setAttr("full_matrices", opts.fullMatrices);
        }
      }
    }
    return new Qr<>(opBuilder.build());
  }

  /**
   * Sets the fullMatrices option.
   *
   * @param fullMatrices If true, compute full-sized {@code q} and {@code r}. If false
   * (the default), compute only the leading {@code P} columns of {@code q}.
   * @return this Options instance.
   */
  public static Options fullMatrices(Boolean fullMatrices) {
    return new Options().fullMatrices(fullMatrices);
  }

  /**
   * Gets q.
   * Orthonormal basis for range of {@code a}. If {@code full_matrices} is {@code False} then
   * shape is {@code [..., M, P]}; if {@code full_matrices} is {@code True} then shape is
   * {@code [..., M, M]}.
   * @return q.
   */
  public Output<T> q() {
    return q;
  }

  /**
   * Gets r.
   * Triangular factor. If {@code full_matrices} is {@code False} then shape is
   * {@code [..., P, N]}. If {@code full_matrices} is {@code True} then shape is {@code [..., M, N]}.
   * @return r.
   */
  public Output<T> r() {
    return r;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.Qr}
   */
  public static class Options {
    private Boolean fullMatrices;

    private Options() {
    }

    /**
     * Sets the fullMatrices option.
     *
     * @param fullMatrices If true, compute full-sized {@code q} and {@code r}. If false
     * (the default), compute only the leading {@code P} columns of {@code q}.
     * @return this Options instance.
     */
    public Options fullMatrices(Boolean fullMatrices) {
      this.fullMatrices = fullMatrices;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = Qr.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<Qr<T>> {
    /**
     * A tensor of shape {@code [..., M, N]} whose inner-most 2 dimensions
     * form matrices of size {@code [M, N]}. Let {@code P} be the minimum of {@code M} and {@code N}.
     */
    public final Operand<T> input;

    /**
     * If true, compute full-sized `q` and `r`. If false
     * (the default), compute only the leading `P` columns of `q`.
     */
    public final boolean fullMatrices;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new Qr<>(op), op, Arrays.asList("full_matrices", "T"));
      int inputIndex = 0;
      input = (Operand<T>) op.input(inputIndex++);
      fullMatrices = op.attributes().getAttrBool("full_matrices");
      T = op.attributes().getAttrType("T");
    }
  }
}
