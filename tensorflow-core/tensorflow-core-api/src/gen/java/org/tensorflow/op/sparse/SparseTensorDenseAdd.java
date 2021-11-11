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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Adds up a {@code SparseTensor} and a dense {@code Tensor}, producing a dense {@code Tensor}.
 * This Op does not require {@code a_indices} be sorted in standard lexicographic order.
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = SparseTensorDenseAdd.OP_NAME,
    inputsClass = SparseTensorDenseAdd.Inputs.class
)
@Operator(
    group = "sparse"
)
public final class SparseTensorDenseAdd<U extends TType> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "SparseTensorDenseAdd";

  private Output<U> output;

  public SparseTensorDenseAdd(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new SparseTensorDenseAdd operation.
   *
   * @param scope current scope
   * @param aIndices 2-D.  The {@code indices} of the {@code SparseTensor}, with shape {@code [nnz, ndims]}.
   * @param aValues 1-D.  The {@code values} of the {@code SparseTensor}, with shape {@code [nnz]}.
   * @param aShape 1-D.  The {@code shape} of the {@code SparseTensor}, with shape {@code [ndims]}.
   * @param b {@code ndims}-D Tensor.  With shape {@code a_shape}.
   * @param <U> data type for {@code SparseTensorDenseAdd} output and operands
   * @param <T> data type for {@code SparseTensorDenseAdd} output and operands
   * @return a new instance of SparseTensorDenseAdd
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType, T extends TNumber> SparseTensorDenseAdd<U> create(Scope scope,
      Operand<T> aIndices, Operand<U> aValues, Operand<T> aShape, Operand<U> b) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "SparseTensorDenseAdd");
    opBuilder.addInput(aIndices.asOutput());
    opBuilder.addInput(aValues.asOutput());
    opBuilder.addInput(aShape.asOutput());
    opBuilder.addInput(b.asOutput());
    return new SparseTensorDenseAdd<>(opBuilder.build());
  }

  /**
   * Gets output.
   *
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = SparseTensorDenseAdd.class
  )
  public static class Inputs<T extends TNumber, U extends TType> extends RawOpInputs<SparseTensorDenseAdd<U>> {
    /**
     * 2-D.  The {@code indices} of the {@code SparseTensor}, with shape {@code [nnz, ndims]}.
     */
    public final Operand<T> aIndices;

    /**
     * 1-D.  The {@code values} of the {@code SparseTensor}, with shape {@code [nnz]}.
     */
    public final Operand<U> aValues;

    /**
     * 1-D.  The {@code shape} of the {@code SparseTensor}, with shape {@code [ndims]}.
     */
    public final Operand<T> aShape;

    /**
     * {@code ndims}-D Tensor.  With shape {@code a_shape}.
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

    public Inputs(GraphOperation op) {
      super(new SparseTensorDenseAdd<>(op), op, Arrays.asList("T", "Tindices"));
      int inputIndex = 0;
      aIndices = (Operand<T>) op.input(inputIndex++);
      aValues = (Operand<U>) op.input(inputIndex++);
      aShape = (Operand<T>) op.input(inputIndex++);
      b = (Operand<U>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tindices = op.attributes().getAttrType("Tindices");
    }
  }
}
