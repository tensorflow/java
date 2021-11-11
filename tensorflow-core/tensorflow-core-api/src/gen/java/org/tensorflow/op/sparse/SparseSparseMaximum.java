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

/**
 * Returns the element-wise max of two SparseTensors.
 * Assumes the two SparseTensors have the same shape, i.e., no broadcasting.
 *
 * @param <T> data type for {@code output_values} output
 */
@OpMetadata(
    opType = SparseSparseMaximum.OP_NAME,
    inputsClass = SparseSparseMaximum.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseSparseMaximum<T extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseSparseMaximum";

  private Output<TInt64> outputIndices;

  private Output<T> outputValues;

  public SparseSparseMaximum(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputIndices = operation.output(outputIdx++);
    outputValues = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseSparseMaximum operation.
   *
   * @param scope current scope
   * @param aIndices 2-D.  {@code N x R} matrix with the indices of non-empty values in a
   * SparseTensor, in the canonical lexicographic ordering.
   * @param aValues 1-D.  {@code N} non-empty values corresponding to {@code a_indices}.
   * @param aShape 1-D.  Shape of the input SparseTensor.
   * @param bIndices counterpart to {@code a_indices} for the other operand.
   * @param bValues counterpart to {@code a_values} for the other operand; must be of the same dtype.
   * @param bShape counterpart to {@code a_shape} for the other operand; the two shapes must be equal.
   * @param <T> data type for {@code SparseSparseMaximum} output and operands
   * @return a new instance of SparseSparseMaximum
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> SparseSparseMaximum<T> create(Scope scope,
      Operand<TInt64> aIndices, Operand<T> aValues, Operand<TInt64> aShape,
      Operand<TInt64> bIndices, Operand<T> bValues, Operand<TInt64> bShape) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseSparseMaximum");
    opBuilder.addInput(aIndices.asOutput());
    opBuilder.addInput(aValues.asOutput());
    opBuilder.addInput(aShape.asOutput());
    opBuilder.addInput(bIndices.asOutput());
    opBuilder.addInput(bValues.asOutput());
    opBuilder.addInput(bShape.asOutput());
    return new SparseSparseMaximum<>(opBuilder.build());
  }

  /**
   * Gets outputIndices.
   * 2-D.  The indices of the output SparseTensor.
   * @return outputIndices.
   */
  public Output<TInt64> outputIndices() {
    return outputIndices;
  }

  /**
   * Gets outputValues.
   * 1-D.  The values of the output SparseTensor.
   * @return outputValues.
   */
  public Output<T> outputValues() {
    return outputValues;
  }

  @OpInputsMetadata(
      outputsClass = SparseSparseMaximum.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<SparseSparseMaximum<T>> {
    /**
     * 2-D.  {@code N x R} matrix with the indices of non-empty values in a
     * SparseTensor, in the canonical lexicographic ordering.
     */
    public final Operand<TInt64> aIndices;

    /**
     * 1-D.  {@code N} non-empty values corresponding to {@code a_indices}.
     */
    public final Operand<T> aValues;

    /**
     * 1-D.  Shape of the input SparseTensor.
     */
    public final Operand<TInt64> aShape;

    /**
     * counterpart to {@code a_indices} for the other operand.
     */
    public final Operand<TInt64> bIndices;

    /**
     * counterpart to {@code a_values} for the other operand; must be of the same dtype.
     */
    public final Operand<T> bValues;

    /**
     * counterpart to {@code a_shape} for the other operand; the two shapes must be equal.
     */
    public final Operand<TInt64> bShape;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new SparseSparseMaximum<>(op), op, Arrays.asList("T"));
      int inputIndex = 0;
      aIndices = (Operand<TInt64>) op.input(inputIndex++);
      aValues = (Operand<T>) op.input(inputIndex++);
      aShape = (Operand<TInt64>) op.input(inputIndex++);
      bIndices = (Operand<TInt64>) op.input(inputIndex++);
      bValues = (Operand<T>) op.input(inputIndex++);
      bShape = (Operand<TInt64>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
    }
  }
}
