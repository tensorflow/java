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

package org.tensorflow.op.xla;

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Wraps the XLA Sort operator, documented at
 * https://www.tensorflow.org/performance/xla/operation_semantics#sort
 * .
 * <p>Sorts a tensor. Currently only sorts in ascending order are supported.
 *
 * @param <T> data type for {@code sorted_keys} output
 *
 * @param <U> data type for {@code sorted_values} output
 */
@Operator(
    group = "xla"
)
public final class KeyValueSort<T extends TNumber, U extends TType> extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "XlaKeyValueSort";

  private Output<T> sortedKeys;

  private Output<U> sortedValues;

  private KeyValueSort(Operation operation) {
    super(operation);
    int outputIdx = 0;
    sortedKeys = operation.output(outputIdx++);
    sortedValues = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new XlaKeyValueSort operation.
   *
   * @param scope current scope
   * @param keys A {@code Tensor} of type K.
   * @param values A {@code Tensor} of type V.
   * @param <T> data type for {@code XlaKeyValueSort} output and operands
   * @param <U> data type for {@code XlaKeyValueSort} output and operands
   * @return a new instance of KeyValueSort
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber, U extends TType> KeyValueSort<T, U> create(Scope scope,
      Operand<T> keys, Operand<U> values) {
    OperationBuilder opBuilder = scope.env().opBuilder("XlaKeyValueSort", scope.makeOpName("KeyValueSort"));
    opBuilder.addInput(keys.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new KeyValueSort<>(opBuilder.build());
  }

  /**
   * Gets sortedKeys.
   * A {@code Tensor} of type K.
   * @return sortedKeys.
   */
  public Output<T> sortedKeys() {
    return sortedKeys;
  }

  /**
   * Gets sortedValues.
   * A {@code Tensor} of type V.
   * @return sortedValues.
   */
  public Output<U> sortedValues() {
    return sortedValues;
  }
}
