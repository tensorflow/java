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
 * Solves systems of linear equations with upper or lower triangular matrices by backsubstitution.
 * {@code matrix} is a tensor of shape {@code [..., M, M]} whose inner-most 2 dimensions form
 * square matrices. If {@code lower} is {@code True} then the strictly upper triangular part
 * of each inner-most matrix is assumed to be zero and not accessed.
 * If {@code lower} is False then the strictly lower triangular part of each inner-most
 * matrix is assumed to be zero and not accessed.
 * {@code rhs} is a tensor of shape {@code [..., M, N]}.
 * <p>The output is a tensor of shape {@code [..., M, N]}. If {@code adjoint} is
 * {@code True} then the innermost matrices in {@code output} satisfy matrix equations
 * {@code matrix[..., :, :] * output[..., :, :] = rhs[..., :, :]}.
 * If {@code adjoint} is {@code False} then the strictly then the  innermost matrices in
 * {@code output} satisfy matrix equations
 * {@code adjoint(matrix[..., i, k]) * output[..., k, j] = rhs[..., i, j]}.
 * <p>Note, the batch shapes for the inputs only need to broadcast.
 * <p>Example:
 * <pre>
 *
 * a = tf.constant([[3,  0,  0,  0],
 *                  [2,  1,  0,  0],
 *                  [1,  0,  1,  0],
 *                  [1,  1,  1,  1]], dtype=tf.float32)
 *
 * b = tf.constant([[4],
 *                  [2],
 *                  [4],
 *                  [2]], dtype=tf.float32)
 *
 * x = tf.linalg.triangular_solve(a, b, lower=True)
 * x
 * # &lt;tf.Tensor: shape=(4, 1), dtype=float32, numpy=
 * # array([[ 1.3333334 ],
 * #        [-0.66666675],
 * #        [ 2.6666665 ],
 * #        [-1.3333331 ]], dtype=float32)&gt;
 *
 * # in python3 one can use `a@x`
 * tf.matmul(a, x)
 * # &lt;tf.Tensor: shape=(4, 1), dtype=float32, numpy=
 * # array([[4.       ],
 * #        [2.       ],
 * #        [4.       ],
 * #        [1.9999999]], dtype=float32)&gt;
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = TriangularSolve.OP_NAME,
    inputsClass = TriangularSolve.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class TriangularSolve<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MatrixTriangularSolve";

  private Output<T> output;

  public TriangularSolve(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MatrixTriangularSolve operation.
   *
   * @param scope current scope
   * @param matrix Shape is {@code [..., M, M]}.
   * @param rhs Shape is {@code [..., M, K]}.
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatrixTriangularSolve} output and operands
   * @return a new instance of TriangularSolve
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TriangularSolve<T> create(Scope scope, Operand<T> matrix,
      Operand<T> rhs, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TriangularSolve");
    opBuilder.addInput(matrix.asOutput());
    opBuilder.addInput(rhs.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.lower != null) {
          opBuilder.setAttr("lower", opts.lower);
        }
        if (opts.adjoint != null) {
          opBuilder.setAttr("adjoint", opts.adjoint);
        }
      }
    }
    return new TriangularSolve<>(opBuilder.build());
  }

  /**
   * Sets the lower option.
   *
   * @param lower Boolean indicating whether the innermost matrices in {@code matrix} are
   * lower or upper triangular.
   * @return this Options instance.
   */
  public static Options lower(Boolean lower) {
    return new Options().lower(lower);
  }

  /**
   * Sets the adjoint option.
   *
   * @param adjoint Boolean indicating whether to solve with {@code matrix} or its (block-wise)
   * adjoint.
   * <p>{@literal @}compatibility(numpy)<br>
   * Equivalent to scipy.linalg.solve_triangular
   * <br>{@literal @}end_compatibility
   * @return this Options instance.
   */
  public static Options adjoint(Boolean adjoint) {
    return new Options().adjoint(adjoint);
  }

  /**
   * Gets output.
   * Shape is {@code [..., M, K]}.
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
   * Optional attributes for {@link org.tensorflow.op.linalg.TriangularSolve}
   */
  public static class Options {
    private Boolean lower;

    private Boolean adjoint;

    private Options() {
    }

    /**
     * Sets the lower option.
     *
     * @param lower Boolean indicating whether the innermost matrices in {@code matrix} are
     * lower or upper triangular.
     * @return this Options instance.
     */
    public Options lower(Boolean lower) {
      this.lower = lower;
      return this;
    }

    /**
     * Sets the adjoint option.
     *
     * @param adjoint Boolean indicating whether to solve with {@code matrix} or its (block-wise)
     * adjoint.
     * <p>{@literal @}compatibility(numpy)<br>
     * Equivalent to scipy.linalg.solve_triangular
     * <br>{@literal @}end_compatibility
     * @return this Options instance.
     */
    public Options adjoint(Boolean adjoint) {
      this.adjoint = adjoint;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = TriangularSolve.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<TriangularSolve<T>> {
    /**
     * Shape is {@code [..., M, M]}.
     */
    public final Operand<T> matrix;

    /**
     * Shape is {@code [..., M, K]}.
     */
    public final Operand<T> rhs;

    /**
     * Boolean indicating whether the innermost matrices in `matrix` are
     * lower or upper triangular.
     */
    public final boolean lower;

    /**
     * Boolean indicating whether to solve with `matrix` or its (block-wise)
     *          adjoint.
     *
     * @compatibility(numpy)
     * Equivalent to scipy.linalg.solve_triangular
     * @end_compatibility
     */
    public final boolean adjoint;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new TriangularSolve<>(op), op, Arrays.asList("lower", "adjoint", "T"));
      int inputIndex = 0;
      matrix = (Operand<T>) op.input(inputIndex++);
      rhs = (Operand<T>) op.input(inputIndex++);
      lower = op.attributes().getAttrBool("lower");
      adjoint = op.attributes().getAttrBool("adjoint");
      T = op.attributes().getAttrType("T");
    }
  }
}
