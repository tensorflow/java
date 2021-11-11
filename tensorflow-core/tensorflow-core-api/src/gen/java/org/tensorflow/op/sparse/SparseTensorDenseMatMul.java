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

package org.tensorflow.op.sparse;

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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Multiply SparseTensor (of rank 2) &quot;A&quot; by dense matrix &quot;B&quot;.
 * No validity checking is performed on the indices of A.  However, the following
 * input format is recommended for optimal behavior:
 * <p>if adjoint_a == false:
 * A should be sorted in lexicographically increasing order.  Use SparseReorder
 * if you're not sure.
 * if adjoint_a == true:
 * A should be sorted in order of increasing dimension 1 (i.e., &quot;column major&quot;
 * order instead of &quot;row major&quot; order).
 *
 * @param <U> data type for {@code product} output
 */
@OpMetadata(
    opType = SparseTensorDenseMatMul.OP_NAME,
    inputsClass = SparseTensorDenseMatMul.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseTensorDenseMatMul<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseTensorDenseMatMul";

  private Output<U> product;

  public SparseTensorDenseMatMul(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    product = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseTensorDenseMatMul operation.
   *
   * @param scope current scope
   * @param aIndices 2-D.  The {@code indices} of the {@code SparseTensor}, size {@code [nnz, 2]} Matrix.
   * @param aValues 1-D.  The {@code values} of the {@code SparseTensor}, size {@code [nnz]} Vector.
   * @param aShape 1-D.  The {@code shape} of the {@code SparseTensor}, size {@code [2]} Vector.
   * @param b 2-D.  A dense Matrix.
   * @param options carries optional attribute values
   * @param <U> data type for {@code SparseTensorDenseMatMul} output and operands
   * @return a new instance of SparseTensorDenseMatMul
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> SparseTensorDenseMatMul<U> create(Scope scope,
      Operand<? extends TNumber> aIndices, Operand<U> aValues, Operand<TInt64> aShape, Operand<U> b,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseTensorDenseMatMul");
    opBuilder.addInput(aIndices.asOutput());
    opBuilder.addInput(aValues.asOutput());
    opBuilder.addInput(aShape.asOutput());
    opBuilder.addInput(b.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.adjointA != null) {
          opBuilder.setAttr("adjoint_a", opts.adjointA);
        }
        if (opts.adjointB != null) {
          opBuilder.setAttr("adjoint_b", opts.adjointB);
        }
      }
    }
    return new SparseTensorDenseMatMul<>(opBuilder.build());
  }

  /**
   * Sets the adjointA option.
   *
   * @param adjointA Use the adjoint of A in the matrix multiply.  If A is complex, this
   * is transpose(conj(A)).  Otherwise it's transpose(A).
   * @return this Options instance.
   */
  public static Options adjointA(Boolean adjointA) {
    return new Options().adjointA(adjointA);
  }

  /**
   * Sets the adjointB option.
   *
   * @param adjointB Use the adjoint of B in the matrix multiply.  If B is complex, this
   * is transpose(conj(B)).  Otherwise it's transpose(B).
   * @return this Options instance.
   */
  public static Options adjointB(Boolean adjointB) {
    return new Options().adjointB(adjointB);
  }

  /**
   * Gets product.
   *
   * @return product.
   */
  public Output<U> product() {
    return product;
  }

  @Override
  public Output<U> asOutput() {
    return product;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.sparse.SparseTensorDenseMatMul}
   */
  public static class Options {
    private Boolean adjointA;

    private Boolean adjointB;

    private Options() {
    }

    /**
     * Sets the adjointA option.
     *
     * @param adjointA Use the adjoint of A in the matrix multiply.  If A is complex, this
     * is transpose(conj(A)).  Otherwise it's transpose(A).
     * @return this Options instance.
     */
    public Options adjointA(Boolean adjointA) {
      this.adjointA = adjointA;
      return this;
    }

    /**
     * Sets the adjointB option.
     *
     * @param adjointB Use the adjoint of B in the matrix multiply.  If B is complex, this
     * is transpose(conj(B)).  Otherwise it's transpose(B).
     * @return this Options instance.
     */
    public Options adjointB(Boolean adjointB) {
      this.adjointB = adjointB;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = SparseTensorDenseMatMul.class
  )
  public static class Inputs<U extends TType> extends RawOpInputs<SparseTensorDenseMatMul<U>> {
    /**
     * 2-D.  The {@code indices} of the {@code SparseTensor}, size {@code [nnz, 2]} Matrix.
     */
    public final Operand<? extends TNumber> aIndices;

    /**
     * 1-D.  The {@code values} of the {@code SparseTensor}, size {@code [nnz]} Vector.
     */
    public final Operand<U> aValues;

    /**
     * 1-D.  The {@code shape} of the {@code SparseTensor}, size {@code [2]} Vector.
     */
    public final Operand<TInt64> aShape;

    /**
     * 2-D.  A dense Matrix.
     */
    public final Operand<U> b;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tindices attribute
     */
    public final DataType Tindices;

    /**
     * Use the adjoint of A in the matrix multiply.  If A is complex, this
     * is transpose(conj(A)).  Otherwise it's transpose(A).
     */
    public final boolean adjointA;

    /**
     * Use the adjoint of B in the matrix multiply.  If B is complex, this
     * is transpose(conj(B)).  Otherwise it's transpose(B).
     */
    public final boolean adjointB;

    public Inputs(GraphOperation op) {
      super(new SparseTensorDenseMatMul<>(op), op, Arrays.asList("T", "Tindices", "adjoint_a", "adjoint_b"));
      int inputIndex = 0;
      aIndices = (Operand<? extends TNumber>) op.input(inputIndex++);
      aValues = (Operand<U>) op.input(inputIndex++);
      aShape = (Operand<TInt64>) op.input(inputIndex++);
      b = (Operand<U>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
      adjointA = op.attributes().getAttrBool("adjoint_a");
      adjointB = op.attributes().getAttrBool("adjoint_b");
    }
  }
}
