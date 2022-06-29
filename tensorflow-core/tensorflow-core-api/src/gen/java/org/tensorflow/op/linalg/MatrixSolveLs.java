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
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.family.TType;

/**
 * Solves one or more linear least-squares problems.
 * {@code matrix} is a tensor of shape {@code [..., M, N]} whose inner-most 2 dimensions
 * form real or complex matrices of size {@code [M, N]}. {@code Rhs} is a tensor of the same
 * type as {@code matrix} and shape {@code [..., M, K]}.
 * The output is a tensor shape {@code [..., N, K]} where each output matrix solves
 * each of the equations
 * {@code matrix[..., :, :]} * {@code output[..., :, :]} = {@code rhs[..., :, :]}
 * in the least squares sense.
 * <p>We use the following notation for (complex) matrix and right-hand sides
 * in the batch:
 * <p>{@code matrix}=\(A \in \mathbb{C}^{m \times n}\),
 * {@code rhs}=\(B  \in \mathbb{C}^{m \times k}\),
 * {@code output}=\(X  \in \mathbb{C}^{n \times k}\),
 * {@code l2_regularizer}=\(\lambda \in \mathbb{R}\).
 * <p>If {@code fast} is {@code True}, then the solution is computed by solving the normal
 * equations using Cholesky decomposition. Specifically, if \(m \ge n\) then
 * \(X = (A^H A + \lambda I)^{-1} A^H B\), which solves the least-squares
 * problem \(X = \mathrm{argmin}_{Z \in \Re^{n \times k} } ||A Z - B||_F^2 + \lambda ||Z||<em>F^2\).
 * If \(m \lt n\) then {@code output} is computed as
 * \(X = A^H (A A^H + \lambda I)^{-1} B\), which (for \(\lambda = 0\)) is the
 * minimum-norm solution to the under-determined linear system, i.e.
 * \(X = \mathrm{argmin}</em>{Z \in \mathbb{C}^{n \times k} } ||Z||<em>F^2 \),
 * subject to \(A Z = B\). Notice that the fast path is only numerically stable
 * when \(A\) is numerically full rank and has a condition number
 * \(\mathrm{cond}(A) \lt \frac{1}{\sqrt{\epsilon</em>{mach} } }\) or \(\lambda\) is
 * sufficiently large.
 * <p>If {@code fast} is {@code False} an algorithm based on the numerically robust complete
 * orthogonal decomposition is used. This computes the minimum-norm
 * least-squares solution, even when \(A\) is rank deficient. This path is
 * typically 6-7 times slower than the fast path. If {@code fast} is {@code False} then
 * {@code l2_regularizer} is ignored.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = MatrixSolveLs.OP_NAME,
    inputsClass = MatrixSolveLs.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class MatrixSolveLs<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MatrixSolveLs";

  private Output<T> output;

  public MatrixSolveLs(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MatrixSolveLs operation.
   *
   * @param scope current scope
   * @param matrix Shape is {@code [..., M, N]}.
   * @param rhs Shape is {@code [..., M, K]}.
   * @param l2Regularizer Scalar tensor.
   * <p>{@literal @}compatibility(numpy)<br>
   * Equivalent to np.linalg.lstsq
   * <br>{@literal @}end_compatibility
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatrixSolveLs} output and operands
   * @return a new instance of MatrixSolveLs
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> MatrixSolveLs<T> create(Scope scope, Operand<T> matrix,
      Operand<T> rhs, Operand<TFloat64> l2Regularizer, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MatrixSolveLs");
    opBuilder.addInput(matrix.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder.addInput(l2Regularizer.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.fast != null) {
          opBuilder.setAttr("fast", opts.fast);
        }
      }
    }
    return new MatrixSolveLs<>(opBuilder.build());
  }

  /**
   * Sets the fast option.
   *
   * @param fast the fast option
   * @return this Options instance.
   */
  public static Options fast(Boolean fast) {
    return new Options().fast(fast);
  }

  /**
   * Gets output.
   * Shape is {@code [..., N, K]}.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.MatrixSolveLs}
   */
  public static class Options {
    private Boolean fast;

    private Options() {
    }

    /**
     * Sets the fast option.
     *
     * @param fast the fast option
     * @return this Options instance.
     */
    public Options fast(Boolean fast) {
      this.fast = fast;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = MatrixSolveLs.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<MatrixSolveLs<T>> {
    /**
     * Shape is {@code [..., M, N]}.
     */
    public final Operand<T> matrix;

    /**
     * Shape is {@code [..., M, K]}.
     */
    public final Operand<T> rhs;

    /**
     * Scalar tensor.
     * <p>{@literal @}compatibility(numpy)<br>
     * Equivalent to np.linalg.lstsq
     * <br>{@literal @}end_compatibility
     */
    public final Operand<TFloat64> l2Regularizer;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The fast attribute
     */
    public final boolean fast;

    public Inputs(GraphOperation op) {
      super(new MatrixSolveLs<>(op), op, Arrays.asList("T", "fast"));
      int inputIndex = 0;
      matrix = (Operand<T>) op.input(inputIndex++);
      rhs = (Operand<T>) op.input(inputIndex++);
      l2Regularizer = (Operand<TFloat64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      fast = op.attributes().getAttrBool("fast");
    }
  }
}
