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
 * Adds two {@code SparseTensor} objects to produce another {@code SparseTensor}.
 * The input {@code SparseTensor} objects' indices are assumed ordered in standard
 * lexicographic order.  If this is not the case, before this step run
 * {@code SparseReorder} to restore index ordering.
 * <p>By default, if two values sum to zero at some index, the output {@code SparseTensor}
 * would still include that particular location in its index, storing a zero in the
 * corresponding value slot.  To override this, callers can specify {@code thresh},
 * indicating that if the sum has a magnitude strictly smaller than {@code thresh}, its
 * corresponding value and index would then not be included.  In particular,
 * {@code thresh == 0} (default) means everything is kept and actual thresholding happens
 * only for a positive value.
 * <p>In the following shapes, {@code nnz} is the count after taking {@code thresh} into account.
 *
 * @param <T> data type for {@code sum_values} output
 */
@OpMetadata(
    opType = SparseAdd.OP_NAME,
    inputsClass = SparseAdd.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseAdd<T extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseAdd";

  private Output<TInt64> sumIndices;

  private Output<T> sumValues;

  private Output<TInt64> sumShape;

  public SparseAdd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sumIndices = operation.output(outputIdx++);
    sumValues = operation.output(outputIdx++);
    sumShape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseAdd operation.
   *
   * @param scope current scope
   * @param aIndices 2-D.  The {@code indices} of the first {@code SparseTensor}, size {@code [nnz, ndims]} Matrix.
   * @param aValues 1-D.  The {@code values} of the first {@code SparseTensor}, size {@code [nnz]} Vector.
   * @param aShape 1-D.  The {@code shape} of the first {@code SparseTensor}, size {@code [ndims]} Vector.
   * @param bIndices 2-D.  The {@code indices} of the second {@code SparseTensor}, size {@code [nnz, ndims]} Matrix.
   * @param bValues 1-D.  The {@code values} of the second {@code SparseTensor}, size {@code [nnz]} Vector.
   * @param bShape 1-D.  The {@code shape} of the second {@code SparseTensor}, size {@code [ndims]} Vector.
   * @param thresh 0-D.  The magnitude threshold that determines if an output value/index
   * pair takes space.
   * @param <T> data type for {@code SparseAdd} output and operands
   * @return a new instance of SparseAdd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> SparseAdd<T> create(Scope scope, Operand<TInt64> aIndices,
      Operand<T> aValues, Operand<TInt64> aShape, Operand<TInt64> bIndices, Operand<T> bValues,
      Operand<TInt64> bShape, Operand<? extends TNumber> thresh) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseAdd");
    opBuilder.addInput(aIndices.asOutput());
    opBuilder.addInput(aValues.asOutput());
    opBuilder.addInput(aShape.asOutput());
    opBuilder.addInput(bIndices.asOutput());
    opBuilder.addInput(bValues.asOutput());
    opBuilder.addInput(bShape.asOutput());
    opBuilder.addInput(thresh.asOutput());
    return new SparseAdd<>(opBuilder.build());
  }

  /**
   * Gets sumIndices.
   *
   * @return sumIndices.
   */
  public Output<TInt64> sumIndices() {
    return sumIndices;
  }

  /**
   * Gets sumValues.
   *
   * @return sumValues.
   */
  public Output<T> sumValues() {
    return sumValues;
  }

  /**
   * Gets sumShape.
   *
   * @return sumShape.
   */
  public Output<TInt64> sumShape() {
    return sumShape;
  }

  @OpInputsMetadata(
      outputsClass = SparseAdd.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<SparseAdd<T>> {
    /**
     * 2-D.  The {@code indices} of the first {@code SparseTensor}, size {@code [nnz, ndims]} Matrix.
     */
    public final Operand<TInt64> aIndices;

    /**
     * 1-D.  The {@code values} of the first {@code SparseTensor}, size {@code [nnz]} Vector.
     */
    public final Operand<T> aValues;

    /**
     * 1-D.  The {@code shape} of the first {@code SparseTensor}, size {@code [ndims]} Vector.
     */
    public final Operand<TInt64> aShape;

    /**
     * 2-D.  The {@code indices} of the second {@code SparseTensor}, size {@code [nnz, ndims]} Matrix.
     */
    public final Operand<TInt64> bIndices;

    /**
     * 1-D.  The {@code values} of the second {@code SparseTensor}, size {@code [nnz]} Vector.
     */
    public final Operand<T> bValues;

    /**
     * 1-D.  The {@code shape} of the second {@code SparseTensor}, size {@code [ndims]} Vector.
     */
    public final Operand<TInt64> bShape;

    /**
     * 0-D.  The magnitude threshold that determines if an output value/index
     * pair takes space.
     */
    public final Operand<? extends TNumber> thresh;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Treal attribute
     */
    public final DataType Treal;

    public Inputs(GraphOperation op) {
      super(new SparseAdd<>(op), op, Arrays.asList("T", "Treal"));
      int inputIndex = 0;
      aIndices = (Operand<TInt64>) op.input(inputIndex++);
      aValues = (Operand<T>) op.input(inputIndex++);
      aShape = (Operand<TInt64>) op.input(inputIndex++);
      bIndices = (Operand<TInt64>) op.input(inputIndex++);
      bValues = (Operand<T>) op.input(inputIndex++);
      bShape = (Operand<TInt64>) op.input(inputIndex++);
      thresh = (Operand<? extends TNumber>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Treal = op.attributes().getAttrType("Treal");
    }
  }
}
