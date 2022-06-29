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

package org.tensorflow.op.ragged;

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Converts a {@code RaggedTensor} into a {@code SparseTensor} with the same values.
 * input=ragged.from_nested_row_splits(rt_dense_values, rt_nested_splits)
 * output=SparseTensor(indices=sparse_indices, values=sparse_values,
 * dense_shape=sparse_dense_shape)
 *
 * @param <U> data type for {@code sparse_values} output
 */
@OpMetadata(
    opType = RaggedTensorToSparse.OP_NAME,
    inputsClass = RaggedTensorToSparse.Inputs.class
)
public final class RaggedTensorToSparse<U extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RaggedTensorToSparse";

  private Output<TInt64> sparseIndices;

  private Output<U> sparseValues;

  private Output<TInt64> sparseDenseShape;

  public RaggedTensorToSparse(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    sparseIndices = operation.output(outputIdx++);
    sparseValues = operation.output(outputIdx++);
    sparseDenseShape = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RaggedTensorToSparse operation.
   *
   * @param scope current scope
   * @param rtNestedSplits The {@code row_splits} for the {@code RaggedTensor}.
   * @param rtDenseValues The {@code flat_values} for the {@code RaggedTensor}.
   * @param <U> data type for {@code RaggedTensorToSparse} output and operands
   * @return a new instance of RaggedTensorToSparse
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> RaggedTensorToSparse<U> create(Scope scope,
      Iterable<Operand<? extends TNumber>> rtNestedSplits, Operand<U> rtDenseValues) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RaggedTensorToSparse");
    opBuilder.addInputList(Operands.asOutputs(rtNestedSplits));
    opBuilder.addInput(rtDenseValues.asOutput());
    return new RaggedTensorToSparse<>(opBuilder.build());
  }

  /**
   * Gets sparseIndices.
   * The indices for the {@code SparseTensor}.
   * @return sparseIndices.
   */
  public Output<TInt64> sparseIndices() {
    return sparseIndices;
  }

  /**
   * Gets sparseValues.
   * The values of the {@code SparseTensor}.
   * @return sparseValues.
   */
  public Output<U> sparseValues() {
    return sparseValues;
  }

  /**
   * Gets sparseDenseShape.
   * {@code sparse_dense_shape} is a tight bounding box of the input {@code RaggedTensor}.
   * @return sparseDenseShape.
   */
  public Output<TInt64> sparseDenseShape() {
    return sparseDenseShape;
  }

  @OpInputsMetadata(
      outputsClass = RaggedTensorToSparse.class
  )
  public static class Inputs<U extends TType> extends RawOpInputs<RaggedTensorToSparse<U>> {
    /**
     * The {@code row_splits} for the {@code RaggedTensor}.
     */
    public final Iterable<Operand<? extends TNumber>> rtNestedSplits;

    /**
     * The {@code flat_values} for the {@code RaggedTensor}.
     */
    public final Operand<U> rtDenseValues;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tsplits attribute
     */
    public final DataType Tsplits;

    public Inputs(GraphOperation op) {
      super(new RaggedTensorToSparse<>(op), op, Arrays.asList("T", "Tsplits"));
      int inputIndex = 0;
      int rtNestedSplitsLength = op.inputListLength("rt_nested_splits");
      rtNestedSplits = Arrays.asList((Operand<? extends TNumber>[]) op.inputList(inputIndex, rtNestedSplitsLength));
      inputIndex += rtNestedSplitsLength;
      rtDenseValues = (Operand<U>) op.input(inputIndex++);
      T = op.attributes().getAttrType("T");
      Tsplits = op.attributes().getAttrType("Tsplits");
    }
  }
}
