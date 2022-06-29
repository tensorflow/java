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
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * Returns whether the given key exists in the map.
 * input_handle: the input map
 * key: the key to check
 * has_key: whether the key is already in the map or not
 */
@OpMetadata(
    opType = TensorMapHasKey.OP_NAME,
    inputsClass = TensorMapHasKey.Inputs.class
)
@Operator
public final class TensorMapHasKey extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorMapHasKey";

  private Output<TBool> hasKey;

  public TensorMapHasKey(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    hasKey = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorMapHasKey operation.
   *
   * @param scope current scope
   * @param inputHandle The inputHandle value
   * @param key The key value
   * @return a new instance of TensorMapHasKey
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorMapHasKey create(Scope scope, Operand<? extends TType> inputHandle,
      Operand<? extends TType> key) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "TensorMapHasKey");
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(key.asOutput());
    return new TensorMapHasKey(opBuilder.build());
  }

  /**
   * Gets hasKey.
   *
   * @return hasKey.
   */
  public Output<TBool> hasKey() {
    return hasKey;
  }

  @Override
  public Output<TBool> asOutput() {
    return hasKey;
  }

  @OpInputsMetadata(
      outputsClass = TensorMapHasKey.class
  )
  public static class Inputs extends RawOpInputs<TensorMapHasKey> {
    /**
     * The inputHandle input
     */
    public final Operand<? extends TType> inputHandle;

    /**
     * The key input
     */
    public final Operand<? extends TType> key;

    /**
     * The keyDtype attribute
     */
    public final DataType keyDtype;

    public Inputs(GraphOperation op) {
      super(new TensorMapHasKey(op), op, Arrays.asList("key_dtype"));
      int inputIndex = 0;
      inputHandle = (Operand<? extends TType>) op.input(inputIndex++);
      key = (Operand<? extends TType>) op.input(inputIndex++);
      keyDtype = op.attributes().getAttrType("key_dtype");
    }
  }
}
