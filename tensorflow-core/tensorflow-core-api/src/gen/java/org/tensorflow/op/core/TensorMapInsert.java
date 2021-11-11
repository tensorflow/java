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
import org.tensorflow.types.family.TType;

/**
 * Returns a map that is the 'input_handle' with the given key-value pair inserted.
 * input_handle: the original map
 * output_handle: the map with key and value inserted
 * key: the key to be inserted
 * value: the value to be inserted
 */
@OpMetadata(
    opType = TensorMapInsert.OP_NAME,
    inputsClass = TensorMapInsert.Inputs.class
)
@Operator
public final class TensorMapInsert extends RawOp implements Operand<TType> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorMapInsert";

  private Output<? extends TType> outputHandle;

  @SuppressWarnings("unchecked")
  public TensorMapInsert(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    outputHandle = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorMapInsert operation.
   *
   * @param scope current scope
   * @param inputHandle The inputHandle value
   * @param key The key value
   * @param value The value value
   * @return a new instance of TensorMapInsert
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorMapInsert create(Scope scope, Operand<? extends TType> inputHandle,
      Operand<? extends TType> key, Operand<? extends TType> value) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorMapInsert");
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(key.asOutput());
    opBuilder.addInput(value.asOutput());
    return new TensorMapInsert(opBuilder.build());
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

  @OpInputsMetadata(
      outputsClass = TensorMapInsert.class
  )
  public static class Inputs extends RawOpInputs<TensorMapInsert> {
    /**
     * The inputHandle input
     */
    public final Operand<? extends TType> inputHandle;

    /**
     * The key input
     */
    public final Operand<? extends TType> key;

    /**
     * The value input
     */
    public final Operand<? extends TType> value;

    /**
     * The keyDtype attribute
     */
    public final DataType keyDtype;

    /**
     * The valueDtype attribute
     */
    public final DataType valueDtype;

    public Inputs(GraphOperation op) {
      super(new TensorMapInsert(op), op, Arrays.asList("key_dtype", "value_dtype"));
      int inputIndex = 0;
      inputHandle = (Operand<? extends TType>) op.input(inputIndex++);
      key = (Operand<? extends TType>) op.input(inputIndex++);
      value = (Operand<? extends TType>) op.input(inputIndex++);
      keyDtype = op.attributes().getAttrType("key_dtype");
      valueDtype = op.attributes().getAttrType("value_dtype");
    }
  }
}
