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
 * Multiply the matrix &quot;a&quot; by the matrix &quot;b&quot;.
 * The inputs must be two-dimensional matrices and the inner dimension of
 * &quot;a&quot; (after being transposed if transpose_a is true) must match the
 * outer dimension of &quot;b&quot; (after being transposed if transposed_b is
 * true).
 * <p><em>Note</em>: The default kernel implementation for MatMul on GPUs uses
 * cublas.
 *
 * @param <T> data type for {@code product} output
 */
@OpMetadata(
    opType = MatMul.OP_NAME,
    inputsClass = MatMul.Inputs.class
)
@Operator(
    group = "linalg"
)
public final class MatMul<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "MatMul";

  private Output<T> product;

  public MatMul(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    product = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new MatMul operation.
   *
   * @param scope current scope
   * @param a The a value
   * @param b The b value
   * @param options carries optional attribute values
   * @param <T> data type for {@code MatMul} output and operands
   * @return a new instance of MatMul
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> MatMul<T> create(Scope scope, Operand<T> a, Operand<T> b,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "MatMul");
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
      }
    }
    return new MatMul<>(opBuilder.build());
  }

  /**
   * Sets the transposeA option.
   *
   * @param transposeA If true, &quot;a&quot; is transposed before multiplication.
   * @return this Options instance.
   */
  public static Options transposeA(Boolean transposeA) {
    return new Options().transposeA(transposeA);
  }

  /**
   * Sets the transposeB option.
   *
   * @param transposeB If true, &quot;b&quot; is transposed before multiplication.
   * @return this Options instance.
   */
  public static Options transposeB(Boolean transposeB) {
    return new Options().transposeB(transposeB);
  }

  /**
   * Gets product.
   *
   * @return product.
   */
  public Output<T> product() {
    return product;
  }

  @Override
  public Output<T> asOutput() {
    return product;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.linalg.MatMul}
   */
  public static class Options {
    private Boolean transposeA;

    private Boolean transposeB;

    private Options() {
    }

    /**
     * Sets the transposeA option.
     *
     * @param transposeA If true, &quot;a&quot; is transposed before multiplication.
     * @return this Options instance.
     */
    public Options transposeA(Boolean transposeA) {
      this.transposeA = transposeA;
      return this;
    }

    /**
     * Sets the transposeB option.
     *
     * @param transposeB If true, &quot;b&quot; is transposed before multiplication.
     * @return this Options instance.
     */
    public Options transposeB(Boolean transposeB) {
      this.transposeB = transposeB;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = MatMul.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<MatMul<T>> {
    /**
     * The a input
     */
    public final Operand<T> a;

    /**
     * The b input
     */
    public final Operand<T> b;

    /**
     * If true, "a" is transposed before multiplication.
     */
    public final boolean transposeA;

    /**
     * If true, "b" is transposed before multiplication.
     */
    public final boolean transposeB;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new MatMul<>(op), op, Arrays.asList("transpose_a", "transpose_b", "T"));
      int inputIndex = 0;
      a = (Operand<T>) op.input(inputIndex++);
      b = (Operand<T>) op.input(inputIndex++);
      transposeA = op.attributes().getAttrBool("transpose_a");
      transposeB = op.attributes().getAttrBool("transpose_b");
      T = op.attributes().getAttrType("T");
    }
  }
}
