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

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Create a dense tensor from a ragged tensor, possibly altering its shape.
 * <p>
 * The `ragged_to_dense` op creates a dense tensor from a list of row partition
 * tensors, a value vector, and default values. If the shape is unspecified, the
 * minimal shape required to contain all the elements in the ragged tensor (the
 * natural shape) will be used. If some dimensions are left unspecified, then the
 * size of the natural shape is used in that dimension.
 * <p>
 * The default_value will be broadcast to the output shape. After that, the values
 * from the ragged tensor overwrite the default values. Note that the default_value
 * must have less dimensions than the value.
 * <p>
 * The row partition tensors are in the order of the dimensions.
 * At present, the types can be:
 * <ul>
 * <li>
 * "ROW_SPLITS": the row_splits tensor from the ragged tensor.
 * </li>
 * <li>
 * "VALUE_ROWIDS": the value_rowids tensor from the ragged tensor.
 * </li>
 * <li>
 * "FIRST_DIM_SIZE": if value_rowids is used for the first dimension, then it
 *   is preceded by "FIRST_DIM_SIZE".
 * 
 * @param <U> data type for {@code result()} output
 */
public final class RaggedTensorToTensor<U extends TType> extends RawOp implements Operand<U> {
  
  /**
   * Factory method to create a class wrapping a new RaggedTensorToTensor operation.
   * 
   * @param scope current scope
   * @param shape The desired shape of the the output tensor. If left unspecified (empty),
   * the minimal shape required to contain all the elements in the ragged tensor
   * (the natural shape) will be used. If some dimensions are left unspecified, then
   * the size of the natural shape is used in that dimension.
   * <p>
   * Note that dense dimensions cannot be modified by the shape argument. Trying to
   * change the size of a dense dimension will cause the op to fail.
   * Examples:
   * natural shape: [4, 5, 6]
   * shape: -1
   * output shape: [4, 5, 6]
   * <p>
   * natural shape: [4, 5, 6]
   * shape: [3, -1, 2]
   * output shape: [3, 5, 2]
   * <p>
   * natural shape: [4, 5, 6]
   * shape: [3, 7, 2]
   * output shape: [3, 7, 2]
   * 
   * @param values A 1D tensor representing the values of the ragged tensor.
   * @param defaultValue The default_value when the shape is larger than the ragged tensor. The
   * default_value is broadcast until it is the shape of the output tensor, and
   * then overwritten by values in the ragged tensor. The default value must be
   * compatible with this broadcast operation, and must have fewer dimensions than
   * the value tensor.
   * @param rowPartitionTensors 
   * @param rowPartitionTypes The types of the row partition tensors. At present, these can be:
   * <ul>
   * <li>
   * "ROW_SPLITS": the row_splits tensor from the ragged tensor.
   * </li>
   * <li>
   * "VALUE_ROWIDS": the value_rowids tensor from the ragged tensor.
   * </li>
   * <li>
   * "FIRST_DIM_SIZE": if value_rowids is used for the first dimension, then it
   *   is preceeded by "FIRST_DIM_SIZE".
   * The tensors are in the order of the dimensions.
   * @return a new instance of RaggedTensorToTensor
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TNumber, V extends TNumber> RaggedTensorToTensor<U> create(Scope scope, Operand<T> shape, Operand<U> values, Operand<U> defaultValue, Iterable<Operand<V>> rowPartitionTensors, List<String> rowPartitionTypes) {
    OperationBuilder opBuilder = scope.env().opBuilder("RaggedTensorToTensor", scope.makeOpName("RaggedTensorToTensor"));
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder.addInput(defaultValue.asOutput());
    opBuilder.addInputList(Operands.asOutputs(rowPartitionTensors));
    opBuilder = scope.apply(opBuilder);
    String[] rowPartitionTypesArray = new String[rowPartitionTypes.size()];
    for (int i = 0; i < rowPartitionTypesArray.length; ++i) {
      rowPartitionTypesArray[i] = rowPartitionTypes.get(i);
    }
    opBuilder.setAttr("row_partition_types", rowPartitionTypesArray);
    return new RaggedTensorToTensor<U>(opBuilder.build());
  }
  
  /**
   * The resulting dense tensor.
   */
  public Output<U> result() {
    return result;
  }
  
  @Override
  public Output<U> asOutput() {
    return result;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RaggedTensorToTensor";
  
  private Output<U> result;
  
  private RaggedTensorToTensor(Operation operation) {
    super(operation);
    int outputIdx = 0;
    result = operation.output(outputIdx++);
  }
}
