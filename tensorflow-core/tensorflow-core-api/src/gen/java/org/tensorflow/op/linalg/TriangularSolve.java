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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Solves systems of linear equations with upper or lower triangular matrices by backsubstitution.
 * <p>
 * 
 * `matrix` is a tensor of shape `[..., M, M]` whose inner-most 2 dimensions form
 * square matrices. If `lower` is `True` then the strictly upper triangular part
 * of each inner-most matrix is assumed to be zero and not accessed.
 * If `lower` is False then the strictly lower triangular part of each inner-most
 * matrix is assumed to be zero and not accessed.
 * `rhs` is a tensor of shape `[..., M, N]`.
 * <p>
 * The output is a tensor of shape `[..., M, N]`. If `adjoint` is
 * `True` then the innermost matrices in `output` satisfy matrix equations
 * `matrix[..., :, :] * output[..., :, :] = rhs[..., :, :]`.
 * If `adjoint` is `False` then the strictly then the  innermost matrices in
 * `output` satisfy matrix equations
 * `adjoint(matrix[..., i, k]) * output[..., k, j] = rhs[..., i, j]`.
 * <p>
 * Note, the batch shapes for the inputs only need to broadcast.
 * <p>
 * Example:
 * <pre>{@code
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
 * # <tf.Tensor: shape=(4, 1), dtype=float32, numpy=
 * # array([[ 1.3333334 ],
 * #        [-0.66666675],
 * #        [ 2.6666665 ],
 * #        [-1.3333331 ]], dtype=float32)>
 * 
 * # in python3 one can use `a@x`
 * tf.matmul(a, x)
 * # <tf.Tensor: shape=(4, 1), dtype=float32, numpy=
 * # array([[4.       ],
 * #        [2.       ],
 * #        [4.       ],
 * #        [1.9999999]], dtype=float32)>
 * }</pre>
 * 
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "linalg")
public final class TriangularSolve<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.TriangularSolve}
   */
  public static class Options {
    
    /**
     * @param lower Boolean indicating whether the innermost matrices in `matrix` are
     * lower or upper triangular.
     */
    public Options lower(Boolean lower) {
      this.lower = lower;
      return this;
    }
    
    /**
     * @param adjoint Boolean indicating whether to solve with `matrix` or its (block-wise)
     *          adjoint.
     * <p>
     * @compatibility(numpy)
     * Equivalent to scipy.linalg.solve_triangular
     * @end_compatibility
     */
    public Options adjoint(Boolean adjoint) {
      this.adjoint = adjoint;
      return this;
    }
    
    private Boolean lower;
    private Boolean adjoint;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new TriangularSolve operation.
   * 
   * @param scope current scope
   * @param matrix Shape is `[..., M, M]`.
   * @param rhs Shape is `[..., M, K]`.
   * @param options carries optional attributes values
   * @return a new instance of TriangularSolve
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TriangularSolve<T> create(Scope scope, Operand<T> matrix, Operand<T> rhs, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("MatrixTriangularSolve", scope.makeOpName("TriangularSolve"));
    opBuilder.addInput(matrix.asOutput());
    opBuilder.addInput(rhs.asOutput());
    opBuilder = scope.apply(opBuilder);
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
    return new TriangularSolve<T>(opBuilder.build());
  }
  
  /**
   * @param lower Boolean indicating whether the innermost matrices in `matrix` are
   * lower or upper triangular.
   */
  public static Options lower(Boolean lower) {
    return new Options().lower(lower);
  }
  
  /**
   * @param adjoint Boolean indicating whether to solve with `matrix` or its (block-wise)
   *          adjoint.
   * <p>
   * @compatibility(numpy)
   * Equivalent to scipy.linalg.solve_triangular
   * @end_compatibility
   */
  public static Options adjoint(Boolean adjoint) {
    return new Options().adjoint(adjoint);
  }
  
  /**
   * Shape is `[..., M, K]`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "MatrixTriangularSolve";
  
  private Output<T> output;
  
  private TriangularSolve(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
