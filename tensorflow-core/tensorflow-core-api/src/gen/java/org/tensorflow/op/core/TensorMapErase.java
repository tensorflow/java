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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TType;

/**
 * Returns a tensor map with item from given key erased.
 * input_handle: the original map
 * output_handle: the map with value from given key removed
 * key: the key of the value to be erased
 */
@Operator
public final class TensorMapErase extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorMapErase";

  private Output<? extends TType> outputHandle;

  @SuppressWarnings("unchecked")
  private TensorMapErase(Operation operation) {
    super(operation);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorMapErase operation.
   *
   * @param scope current scope
   * @param inputHandle the inputHandle value
   * @param key the key value
   * @param valueDtype the value of the valueDtype property
   * @param <U> data type for {@code TensorMapErase} output and operands
   * @return a new instance of TensorMapErase
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TType> TensorMapErase create(Scope scope,
      Operand<? extends TType> inputHandle, Operand<? extends TType> key, Class<U> valueDtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorMapErase", scope.makeOpName("TensorMapErase"));
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(key.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("value_dtype", Operands.toDataType(valueDtype));
    return new TensorMapErase(opBuilder.build());
  }

  /**
   * Gets outputHandle.
   *
   * @return outputHandle.
   */
  public Output<? extends TType> outputHandle() {
    return outputHandle;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Output<TType> asOutput() {
    return (Output<TType>) outputHandle;
  }
}
