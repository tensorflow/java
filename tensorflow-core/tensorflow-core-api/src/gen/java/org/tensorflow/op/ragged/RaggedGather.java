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
 * Gather ragged slices from `params` axis `0` according to `indices`.
 * <p>
 * Outputs a `RaggedTensor` output composed from `output_dense_values` and
 * `output_nested_splits`, such that:
 * <pre>{@code
 * output.shape = indices.shape + params.shape[1:]
 * output.ragged_rank = indices.shape.ndims + params.ragged_rank
 * output[i...j, d0...dn] = params[indices[i...j], d0...dn]
 * }</pre>
 * where
 * <ul>
 * <li>
 * `params =
 *    ragged.from_nested_row_splits(params_dense_values, params_nested_splits)`
 *    provides the values that should be gathered.
 * </li>
 * <li>
 * `indices` ia a dense tensor with dtype `int32` or `int64`, indicating which
 *    values should be gathered.
 * </li>
 * <li>
 * `output =
 *    ragged.from_nested_row_splits(output_dense_values, output_nested_splits)`
 *    is the output tensor.
 * </li>
 * </ul>
 * (Note: This c++ op is used to implement the higher-level python
 * `tf.ragged.gather` op, which also supports ragged indices.)
 * 
 * 
 * @param <T> data type for {@code outputNestedSplits()} output
 * @param <U> data type for {@code outputDenseValues()} output
 */
public final class RaggedGather<T extends TNumber, U extends TType> extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new RaggedGather operation.
   * 
   * @param scope current scope
   * @param paramsNestedSplits The `nested_row_splits` tensors that define the row-partitioning for the
   * `params` RaggedTensor input.
   * @param paramsDenseValues The `flat_values` for the `params` RaggedTensor. There was a terminology change
   * at the python level from dense_values to flat_values, so dense_values is the
   * deprecated name.
   * @param indices Indices in the outermost dimension of `params` of the values that should be
   * gathered.
   * @param OUTPUTRAGGEDRANK The ragged rank of the output RaggedTensor. `output_nested_splits` will contain
   * this number of `row_splits` tensors. This value should equal
   * `indices.shape.ndims + params.ragged_rank - 1`.
   * @return a new instance of RaggedGather
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TType, V extends TNumber> RaggedGather<T, U> create(Scope scope, Iterable<Operand<T>> paramsNestedSplits, Operand<U> paramsDenseValues, Operand<V> indices, Long OUTPUTRAGGEDRANK) {
    OperationBuilder opBuilder = scope.env().opBuilder("RaggedGather", scope.makeOpName("RaggedGather"));
    opBuilder.addInputList(Operands.asOutputs(paramsNestedSplits));
    opBuilder.addInput(paramsDenseValues.asOutput());
    opBuilder.addInput(indices.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("OUTPUT_RAGGED_RANK", OUTPUTRAGGEDRANK);
    return new RaggedGather<T, U>(opBuilder.build());
  }
  
  /**
   * The `nested_row_splits` tensors that define the row-partitioning for the
   * returned RaggedTensor.
   */
  public List<Output<T>> outputNestedSplits() {
    return outputNestedSplits;
  }
  
  /**
   * The `flat_values` for the returned RaggedTensor.
   */
  public Output<U> outputDenseValues() {
    return outputDenseValues;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RaggedGather";
  
  private List<Output<T>> outputNestedSplits;
  private Output<U> outputDenseValues;
  
  @SuppressWarnings("unchecked")
  private RaggedGather(Operation operation) {
    super(operation);
    int outputIdx = 0;
    int outputNestedSplitsLength = operation.outputListLength("output_nested_splits");
    outputNestedSplits = Arrays.asList((Output<T>[])operation.outputList(outputIdx, outputNestedSplitsLength));
    outputIdx += outputNestedSplitsLength;
    outputDenseValues = operation.output(outputIdx++);
  }
}
