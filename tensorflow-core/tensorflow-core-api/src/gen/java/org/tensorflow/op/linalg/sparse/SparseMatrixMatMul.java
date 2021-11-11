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

package org.tensorflow.op.linalg.sparse;

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
 * Matrix-multiplies a sparse matrix with a dense matrix.
 * Returns a dense matrix.
 * For inputs A and B, where A is CSR and B is dense; this op returns a dense C;
 * <p>If transpose_output is false, returns:
 * <pre>
 *   C = A . B
 * </pre>
 * <p>If transpose_output is {@code true}, returns:
 * <pre>
 *   C = transpose(A . B) = transpose(B) . transpose(A)
 * </pre>
 * <p>where the transposition is performed along the two innermost (matrix)
 * dimensions.
 * <p>If conjugate_output is {@code true}, returns:
 * <pre>
 *   C = conjugate(A . B) = conjugate(A) . conjugate(B)
 * </pre>
 * <p>If both conjugate_output and transpose_output are {@code true}, returns:
 * <pre>
 *   C = conjugate(transpose(A . B)) = conjugate(transpose(B)) .
 *                                     conjugate(transpose(A))
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = SparseMatrixMatMul.OP_NAME,
    inputsClass = SparseMatrixMatMul.Inputs.class
)
public final class SparseMatrixMatMul<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseMatrixMatMul";

  private Output<T> output;

  public SparseMatrixMatMul(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseMatrixMatMul operation.
   *
   * @param scope current scope
   * @param a A CSRSparseMatrix.
   * @param b A dense tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code SparseMatrixMatMul} output and operands
   * @return a new instance of SparseMatrixMatMul
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseMatrixMatMul<T> create(Scope scope,
      Operand<? extends TType> a, Operand<T> b, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseMatrixMatMul");
    opBuilder.addInput(a.asOutput());
    opBuilder.addInput(b.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.transposeA != null) {
          opBuilder.setAttr("transpose_a", opts.transposeA);
        }
        if (opts.transposeB != null) {
          opBuilder.setAttr("transpose_b", opts.transposeB);
        }
        if (opts.adjointA != null) {
          opBuilder.setAttr("adjoint_a", opts.adjointA);
        }
        if (opts.adjointB != null) {
          opBuilder.setAttr("adjoint_b", opts.adjointB);
        }
        if (opts.transposeOutput != null) {
          opBuilder.setAttr("transpose_output", opts.transposeOutput);
        }
        if (opts.conjugateOutput != null) {
          opBuilder.setAttr("conjugate_output", opts.conjugateOutput);
        }
      }
    }
    return new SparseMatrixMatMul<>(opBuilder.build());
  }

  /**
   * Sets the transposeA option.
   *
   * @param transposeA Indicates whether {@code a} should be transposed.
   * @return this Options instance.
   */
  public static Options transposeA(Boolean transposeA) {
    return new Options().transposeA(transposeA);
  }

  /**
   * Sets the transposeB option.
   *
   * @param transposeB Indicates whether {@code b} should be transposed.
   * @return this Options instance.
   */
  public static Options transposeB(Boolean transposeB) {
    return new Options().transposeB(transposeB);
  }

  /**
   * Sets the adjointA option.
   *
   * @param adjointA Indicates whether {@code a} should be conjugate-transposed.
   * @return this Options instance.
   */
  public static Options adjointA(Boolean adjointA) {
    return new Options().adjointA(adjointA);
  }

  /**
   * Sets the adjointB option.
   *
   * @param adjointB Indicates whether {@code b} should be conjugate-transposed.
   * @return this Options instance.
   */
  public static Options adjointB(Boolean adjointB) {
    return new Options().adjointB(adjointB);
  }

  /**
   * Sets the transposeOutput option.
   *
   * @param transposeOutput Transposes the product of {@code a} and {@code b}.
   * @return this Options instance.
   */
  public static Options transposeOutput(Boolean transposeOutput) {
    return new Options().transposeOutput(transposeOutput);
  }

  /**
   * Sets the conjugateOutput option.
   *
   * @param conjugateOutput Conjugates the product of {@code a} and {@code b}.
   * @return this Options instance.
   */
  public static Options conjugateOutput(Boolean conjugateOutput) {
    return new Options().conjugateOutput(conjugateOutput);
  }

  /**
   * Gets output.
   * A dense output tensor.
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
   * Optional attributes for {@link org.tensorflow.op.linalg.sparse.SparseMatrixMatMul}
   */
  public static class Options {
    private Boolean transposeA;

    private Boolean transposeB;

    private Boolean adjointA;

    private Boolean adjointB;

    private Boolean transposeOutput;

    private Boolean conjugateOutput;

    private Options() {
    }

    /**
     * Sets the transposeA option.
     *
     * @param transposeA Indicates whether {@code a} should be transposed.
     * @return this Options instance.
     */
    public Options transposeA(Boolean transposeA) {
      this.transposeA = transposeA;
      return this;
    }

    /**
     * Sets the transposeB option.
     *
     * @param transposeB Indicates whether {@code b} should be transposed.
     * @return this Options instance.
     */
    public Options transposeB(Boolean transposeB) {
      this.transposeB = transposeB;
      return this;
    }

    /**
     * Sets the adjointA option.
     *
     * @param adjointA Indicates whether {@code a} should be conjugate-transposed.
     * @return this Options instance.
     */
    public Options adjointA(Boolean adjointA) {
      this.adjointA = adjointA;
      return this;
    }

    /**
     * Sets the adjointB option.
     *
     * @param adjointB Indicates whether {@code b} should be conjugate-transposed.
     * @return this Options instance.
     */
    public Options adjointB(Boolean adjointB) {
      this.adjointB = adjointB;
      return this;
    }

    /**
     * Sets the transposeOutput option.
     *
     * @param transposeOutput Transposes the product of {@code a} and {@code b}.
     * @return this Options instance.
     */
    public Options transposeOutput(Boolean transposeOutput) {
      this.transposeOutput = transposeOutput;
      return this;
    }

    /**
     * Sets the conjugateOutput option.
     *
     * @param conjugateOutput Conjugates the product of {@code a} and {@code b}.
     * @return this Options instance.
     */
    public Options conjugateOutput(Boolean conjugateOutput) {
      this.conjugateOutput = conjugateOutput;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SparseMatrixMatMul.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SparseMatrixMatMul<T>> {
    /**
     * A CSRSparseMatrix.
     */
    public final Operand<? extends TType> a;

    /**
     * A dense tensor.
     */
    public final Operand<T> b;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * Indicates whether `a` should be transposed.
     */
    public final boolean transposeA;

    /**
     * Indicates whether `b` should be transposed.
     */
    public final boolean transposeB;

    /**
     * Indicates whether `a` should be conjugate-transposed.
     */
    public final boolean adjointA;

    /**
     * Indicates whether `b` should be conjugate-transposed.
     */
    public final boolean adjointB;

    /**
     * Transposes the product of `a` and `b`.
     */
    public final boolean transposeOutput;

    /**
     * Conjugates the product of `a` and `b`.
     */
    public final boolean conjugateOutput;

    public Inputs(GraphOperation op) {
      super(new SparseMatrixMatMul<>(op), op, Arrays.asList("T", "transpose_a", "transpose_b", "adjoint_a", "adjoint_b", "transpose_output", "conjugate_output"));
      int inputIndex = 0;
      a = (Operand<? extends TType>) op.input(inputIndex++);
      b = (Operand<T>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      transposeA = op.attributes().getAttrBool("transpose_a");
      transposeB = op.attributes().getAttrBool("transpose_b");
      adjointA = op.attributes().getAttrBool("adjoint_a");
      adjointB = op.attributes().getAttrBool("adjoint_b");
      transposeOutput = op.attributes().getAttrBool("transpose_output");
      conjugateOutput = op.attributes().getAttrBool("conjugate_output");
    }
  }
}
