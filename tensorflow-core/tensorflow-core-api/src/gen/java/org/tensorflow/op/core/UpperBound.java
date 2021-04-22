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

package org.tensorflow.op.core;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Applies upper_bound(sorted_search_values, values) along each row.
 * Each set of rows with the same index in (sorted_inputs, values) is treated
 * independently.  The resulting row is the equivalent of calling
 * {@code np.searchsorted(sorted_inputs, values, side='right')}.
 * <p>The result is not a global index to the entire
 * {@code Tensor}, but rather just the index in the last dimension.
 * <p>A 2-D example:
 * sorted_sequence = [[0, 3, 9, 9, 10],
 * [1, 2, 3, 4, 5]]
 * values = [[2, 4, 9],
 * [0, 2, 6]]
 * <p>result = UpperBound(sorted_sequence, values)
 * <p>result == [[1, 2, 4],
 * [0, 2, 5]]
 *
 * @param <U> data type for {@code output} output
 */
public final class UpperBound<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "UpperBound";

  private Output<U> output;

  private UpperBound(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new UpperBound operation.
   *
   * @param scope current scope
   * @param sortedInputs 2-D Tensor where each row is ordered.
   * @param values 2-D Tensor with the same numbers of rows as {@code sorted_search_values}. Contains
   * the values that will be searched for in {@code sorted_search_values}.
   * @param outType the value of the outType property
   * @param <U> data type for {@code UpperBound} output and operands
   * @param <T> data type for {@code UpperBound} output and operands
   * @return a new instance of UpperBound
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber, T extends TType> UpperBound<U> create(Scope scope,
      Operand<T> sortedInputs, Operand<T> values, Class<U> outType) {
    OperationBuilder opBuilder = scope.env().opBuilder("UpperBound", scope.makeOpName("UpperBound"));
    opBuilder.addInput(sortedInputs.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_type", Operands.toDataType(outType));
    return new UpperBound<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new UpperBound operation, with the default output types.
   *
   * @param scope current scope
   * @param sortedInputs 2-D Tensor where each row is ordered.
   * @param values 2-D Tensor with the same numbers of rows as {@code sorted_search_values}. Contains
   * the values that will be searched for in {@code sorted_search_values}.
   * @param <T> data type for {@code UpperBound} output and operands
   * @return a new instance of UpperBound, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> UpperBound<TInt32> create(Scope scope, Operand<T> sortedInputs,
      Operand<T> values) {
    return create(scope, sortedInputs, values, TInt32.class);
  }

  /**
   * Gets output.
   * A {@code Tensor} with the same shape as {@code values}.  It contains the last scalar index
   * into the last dimension where values can be inserted without changing the
   * ordered property.
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }
}
