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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Generates a feature cross from a list of tensors, and returns it as a
 * RaggedTensor.  See `tf.ragged.cross` for more details.
 * 
 * @param <T> data type for {@code outputValues()} output
 * @param <U> data type for {@code outputRowSplits()} output
 */
public final class RaggedCross<T extends TType, U extends TNumber> extends RawOp {
  
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
   * @param inputOrder String specifying the tensor type for each input.  The `i`th character in
   * this string specifies the type of the `i`th input, and is one of: 'R' (ragged),
   * 'D' (dense), or 'S' (sparse).  This attr is used to ensure that the crossed
   * values are combined in the order of the inputs from the call to tf.ragged.cross.
   * @param hashedOutput 
   * @param numBuckets 
   * @param hashKey 
   * @param outValuesType 
   * @param outRowSplitsType 
   * @return a new instance of RaggedCross
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType, U extends TNumber> RaggedCross<T, U> create(Scope scope, Iterable<Operand<?>> raggedValues, Iterable<Operand<?>> raggedRowSplits, Iterable<Operand<TInt64>> sparseIndices, Iterable<Operand<?>> sparseValues, Iterable<Operand<TInt64>> sparseShape, Iterable<Operand<?>> denseInputs, String inputOrder, Boolean hashedOutput, Long numBuckets, Long hashKey, Class<T> outValuesType, Class<U> outRowSplitsType) {
    OperationBuilder opBuilder = scope.env().opBuilder("RaggedCross", scope.makeOpName("RaggedCross"));
    opBuilder.addInputList(Operands.asOutputs(raggedValues));
    opBuilder.addInputList(Operands.asOutputs(raggedRowSplits));
    opBuilder.addInputList(Operands.asOutputs(sparseIndices));
    opBuilder.addInputList(Operands.asOutputs(sparseValues));
    opBuilder.addInputList(Operands.asOutputs(sparseShape));
    opBuilder.addInputList(Operands.asOutputs(denseInputs));
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("input_order", inputOrder);
    opBuilder.setAttr("hashed_output", hashedOutput);
    opBuilder.setAttr("num_buckets", numBuckets);
    opBuilder.setAttr("hash_key", hashKey);
    opBuilder.setAttr("out_values_type", Operands.toDataType(outValuesType));
    opBuilder.setAttr("out_row_splits_type", Operands.toDataType(outRowSplitsType));
    return new RaggedCross<T, U>(opBuilder.build());
  }
  
  /**
   * The `values` for the returned `RaggedTensor`.
   */
  public Output<T> outputValues() {
    return outputValues;
  }
  
  /**
   * The `row_splits` for the returned `RaggedTensor`.
   */
  public Output<U> outputRowSplits() {
    return outputRowSplits;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RaggedCross";
  
  private Output<T> outputValues;
  private Output<U> outputRowSplits;
  
  private RaggedCross(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputValues = operation.output(outputIdx++);
    outputRowSplits = operation.output(outputIdx++);
  }
}
