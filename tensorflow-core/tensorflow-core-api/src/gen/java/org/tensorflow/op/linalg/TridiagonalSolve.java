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
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Solves tridiagonal systems of equations.
 * Solves tridiagonal systems of equations.
 * Supports batch dimensions and multiple right-hand sides per each left-hand
 * side.
 * On CPU, solution is computed via Gaussian elimination with or without partial
 * pivoting, depending on {@code partial_pivoting} attribute. On GPU, Nvidia's cuSPARSE
 * library is used: https://docs.nvidia.com/cuda/cusparse/index.html#gtsv
 * Partial pivoting is not yet supported by XLA backends.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = TridiagonalSolve.OP_NAME,
    inputsClass = TridiagonalSolve.Inputs.class
)
public final class TridiagonalSolve<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TridiagonalSolve";

  private Output<T> output;

  public TridiagonalSolve(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TridiagonalSolve operation.
   *
   * @param scope current scope
   * @param diagonals Tensor of shape {@code [..., 3, M]} whose innermost 2 dimensions represent the
   * tridiagonal matrices with three rows being the superdiagonal, diagonals, and
   * subdiagonals, in order. The last element of the superdiagonal and the first
   * element of the subdiagonal is ignored.
   * @param rhs Tensor of shape {@code [..., M, K]}, representing K right-hand sides per each
   * left-hand side.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TridiagonalSolve} output and operands
   * @return a new instance of TridiagonalSolve
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> TridiagonalSolve<T> create(Scope scope, Operand<T> diagonals,
      Operand<T> rhs, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TridiagonalSolve");
    opBuilder.addInput(diagonals.asOutput());
    opBuilder.addInput(rhs.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.partialPivoting != null) {
          opBuilder.setAttr("partial_pivoting", opts.partialPivoting);
        }
        if (opts.perturbSingular != null) {
          opBuilder.setAttr("perturb_singular", opts.perturbSingular);
        }
      }
    }
    return new TridiagonalSolve<>(opBuilder.build());
  }

  /**
   * Sets the partialPivoting option.
   *
   * @param partialPivoting Whether to apply partial pivoting. Partial pivoting makes the procedure more
   * stable, but slower.
   * @return this Options instance.
   */
  public static Options partialPivoting(Boolean partialPivoting) {
    return new Options().partialPivoting(partialPivoting);
  }

  /**
   * Sets the perturbSingular option.
   *
   * @param perturbSingular the perturbSingular option
   * @return this Options instance.
   */
  public static Options perturbSingular(Boolean perturbSingular) {
    return new Options().perturbSingular(perturbSingular);
  }

  /**
   * Gets output.
   * Tensor of shape {@code [..., M, K]} containing the solutions
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
   * Optional attributes for {@link org.tensorflow.op.linalg.TridiagonalSolve}
   */
  public static class Options {
    private Boolean partialPivoting;

    private Boolean perturbSingular;

    private Options() {
    }

    /**
     * Sets the partialPivoting option.
     *
     * @param partialPivoting Whether to apply partial pivoting. Partial pivoting makes the procedure more
     * stable, but slower.
     * @return this Options instance.
     */
    public Options partialPivoting(Boolean partialPivoting) {
      this.partialPivoting = partialPivoting;
      return this;
    }

    /**
     * Sets the perturbSingular option.
     *
     * @param perturbSingular the perturbSingular option
     * @return this Options instance.
     */
    public Options perturbSingular(Boolean perturbSingular) {
      this.perturbSingular = perturbSingular;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = TridiagonalSolve.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<TridiagonalSolve<T>> {
    /**
     * Tensor of shape {@code [..., 3, M]} whose innermost 2 dimensions represent the
     * tridiagonal matrices with three rows being the superdiagonal, diagonals, and
     * subdiagonals, in order. The last element of the superdiagonal and the first
     * element of the subdiagonal is ignored.
     */
    public final Operand<T> diagonals;

    /**
     * Tensor of shape {@code [..., M, K]}, representing K right-hand sides per each
     * left-hand side.
     */
    public final Operand<T> rhs;

    /**
     * Whether to apply partial pivoting. Partial pivoting makes the procedure more
     * stable, but slower.
     */
    public final boolean partialPivoting;

    /**
     * The perturbSingular attribute
     */
    public final boolean perturbSingular;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new TridiagonalSolve<>(op), op, Arrays.asList("partial_pivoting", "perturb_singular", "T"));
      int inputIndex = 0;
      diagonals = (Operand<T>) op.input(inputIndex++);
      rhs = (Operand<T>) op.input(inputIndex++);
      partialPivoting = op.attributes().getAttrBool("partial_pivoting");
      perturbSingular = op.attributes().getAttrBool("perturb_singular");
      T = op.attributes().getAttrType("T");
    }
  }
}
