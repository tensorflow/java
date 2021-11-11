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
 * Generates a feature cross from a list of tensors, and returns it as a
 * RaggedTensor.  See {@code tf.ragged.cross} for more details.
 *
 * @param <T> data type for {@code output_values} output
 *
 * @param <U> data type for {@code output_row_splits} output
 */
@OpMetadata(
    opType = RaggedCross.OP_NAME,
    inputsClass = RaggedCross.Inputs.class
)
public final class RaggedCross<T extends TType, U extends TNumber> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RaggedCross";

  private Output<T> outputValues;

  private Output<U> outputRowSplits;

  public RaggedCross(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputValues = operation.output(outputIdx++);
    outputRowSplits = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RaggedCross operation.
   *
   * @param scope current scope
   * @param raggedValues The values tensor for each RaggedTensor input.
   * @param raggedRowSplits The row_splits tensor for each RaggedTensor input.
   * @param sparseIndices The indices tensor for each SparseTensor input.
   * @param sparseValues The values tensor for each SparseTensor input.
   * @param sparseShape The dense_shape tensor for each SparseTensor input.
   * @param denseInputs The tf.Tensor inputs.
   * @param inputOrder String specifying the tensor type for each input.  The {@code i}th character in
   * this string specifies the type of the {@code i}th input, and is one of: 'R' (ragged),
   * 'D' (dense), or 'S' (sparse).  This attr is used to ensure that the crossed
   * values are combined in the order of the inputs from the call to tf.ragged.cross.
   * @param hashedOutput The value of the hashedOutput attribute
   * @param numBuckets The value of the numBuckets attribute
   * @param hashKey The value of the hashKey attribute
   * @param outValuesType The value of the outValuesType attribute
   * @param outRowSplitsType The value of the outRowSplitsType attribute
   * @param <T> data type for {@code RaggedCross} output and operands
   * @param <U> data type for {@code RaggedCross} output and operands
   * @return a new instance of RaggedCross
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType, U extends TNumber> RaggedCross<T, U> create(Scope scope,
      Iterable<Operand<?>> raggedValues, Iterable<Operand<?>> raggedRowSplits,
      Iterable<Operand<TInt64>> sparseIndices, Iterable<Operand<?>> sparseValues,
      Iterable<Operand<TInt64>> sparseShape, Iterable<Operand<?>> denseInputs, String inputOrder,
      Boolean hashedOutput, Long numBuckets, Long hashKey, Class<T> outValuesType,
      Class<U> outRowSplitsType) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RaggedCross");
    opBuilder.addInputList(Operands.asOutputs(raggedValues));
    opBuilder.addInputList(Operands.asOutputs(raggedRowSplits));
    opBuilder.addInputList(Operands.asOutputs(sparseIndices));
    opBuilder.addInputList(Operands.asOutputs(sparseValues));
    opBuilder.addInputList(Operands.asOutputs(sparseShape));
    opBuilder.addInputList(Operands.asOutputs(denseInputs));
    opBuilder.setAttr("input_order", inputOrder);
    opBuilder.setAttr("hashed_output", hashedOutput);
    opBuilder.setAttr("num_buckets", numBuckets);
    opBuilder.setAttr("hash_key", hashKey);
    opBuilder.setAttr("out_values_type", Operands.toDataType(outValuesType));
    opBuilder.setAttr("out_row_splits_type", Operands.toDataType(outRowSplitsType));
    return new RaggedCross<>(opBuilder.build());
  }

  /**
   * Gets outputValues.
   * The {@code values} for the returned {@code RaggedTensor}.
   * @return outputValues.
   */
  public Output<T> outputValues() {
    return outputValues;
  }

  /**
   * Gets outputRowSplits.
   * The {@code row_splits} for the returned {@code RaggedTensor}.
   * @return outputRowSplits.
   */
  public Output<U> outputRowSplits() {
    return outputRowSplits;
  }

  @OpInputsMetadata(
      outputsClass = RaggedCross.class
  )
  public static class Inputs extends RawOpInputs<RaggedCross<?, ?>> {
    /**
     * The values tensor for each RaggedTensor input.
     */
    public final Iterable<Operand<?>> raggedValues;

    /**
     * The row_splits tensor for each RaggedTensor input.
     */
    public final Iterable<Operand<?>> raggedRowSplits;

    /**
     * The indices tensor for each SparseTensor input.
     */
    public final Iterable<Operand<TInt64>> sparseIndices;

    /**
     * The values tensor for each SparseTensor input.
     */
    public final Iterable<Operand<?>> sparseValues;

    /**
     * The dense_shape tensor for each SparseTensor input.
     */
    public final Iterable<Operand<TInt64>> sparseShape;

    /**
     * The tf.Tensor inputs.
     */
    public final Iterable<Operand<?>> denseInputs;

    /**
     * String specifying the tensor type for each input.  The `i`th character in
     * this string specifies the type of the `i`th input, and is one of: 'R' (ragged),
     * 'D' (dense), or 'S' (sparse).  This attr is used to ensure that the crossed
     * values are combined in the order of the inputs from the call to tf.ragged.cross.
     */
    public final String inputOrder;

    /**
     * The hashedOutput attribute
     */
    public final boolean hashedOutput;

    /**
     * The numBuckets attribute
     */
    public final long numBuckets;

    /**
     * The hashKey attribute
     */
    public final long hashKey;

    /**
     * The raggedValuesTypes attribute
     */
    public final DataType[] raggedValuesTypes;

    /**
     * The raggedSplitsTypes attribute
     */
    public final DataType[] raggedSplitsTypes;

    /**
     * The sparseValuesTypes attribute
     */
    public final DataType[] sparseValuesTypes;

    /**
     * The denseTypes attribute
     */
    public final DataType[] denseTypes;

    /**
     * The outValuesType attribute
     */
    public final DataType outValuesType;

    /**
     * The outRowSplitsType attribute
     */
    public final DataType outRowSplitsType;

    public Inputs(GraphOperation op) {
      super(new RaggedCross<>(op), op, Arrays.asList("input_order", "hashed_output", "num_buckets", "hash_key", "ragged_values_types", "ragged_splits_types", "sparse_values_types", "dense_types", "out_values_type", "out_row_splits_type"));
      int inputIndex = 0;
      int raggedValuesLength = op.inputListLength("ragged_values");
      raggedValues = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, raggedValuesLength));
      inputIndex += raggedValuesLength;
      int raggedRowSplitsLength = op.inputListLength("ragged_row_splits");
      raggedRowSplits = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, raggedRowSplitsLength));
      inputIndex += raggedRowSplitsLength;
      int sparseIndicesLength = op.inputListLength("sparse_indices");
      sparseIndices = Arrays.asList((Operand<TInt64>[]) op.inputList(inputIndex, sparseIndicesLength));
      inputIndex += sparseIndicesLength;
      int sparseValuesLength = op.inputListLength("sparse_values");
      sparseValues = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, sparseValuesLength));
      inputIndex += sparseValuesLength;
      int sparseShapeLength = op.inputListLength("sparse_shape");
      sparseShape = Arrays.asList((Operand<TInt64>[]) op.inputList(inputIndex, sparseShapeLength));
      inputIndex += sparseShapeLength;
      int denseInputsLength = op.inputListLength("dense_inputs");
      denseInputs = Arrays.asList((Operand<?>[]) op.inputList(inputIndex, denseInputsLength));
      inputIndex += denseInputsLength;
      inputOrder = op.attributes().getAttrString("input_order");
      hashedOutput = op.attributes().getAttrBool("hashed_output");
      numBuckets = op.attributes().getAttrInt("num_buckets");
      hashKey = op.attributes().getAttrInt("hash_key");
      raggedValuesTypes = op.attributes().getAttrTypeList("ragged_values_types");
      raggedSplitsTypes = op.attributes().getAttrTypeList("ragged_splits_types");
      sparseValuesTypes = op.attributes().getAttrTypeList("sparse_values_types");
      denseTypes = op.attributes().getAttrTypeList("dense_types");
      outValuesType = op.attributes().getAttrType("out_values_type");
      outRowSplitsType = op.attributes().getAttrType("out_row_splits_type");
    }
  }
}
