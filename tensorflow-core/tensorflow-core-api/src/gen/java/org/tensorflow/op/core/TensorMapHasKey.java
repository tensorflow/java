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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TType;

/**
 * Returns whether the given key exists in the map.
 * input_handle: the input map
 * key: the key to check
 * has_key: whether the key is already in the map or not
 */
@Operator
public final class TensorMapHasKey extends RawOp implements Operand<TBool> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "TensorMapHasKey";

  private Output<TBool> hasKey;

  private TensorMapHasKey(Operation operation) {
    super(operation);
    int outputIdx = 0;
    hasKey = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new TensorMapHasKey operation.
   *
   * @param scope current scope
   * @param inputHandle the inputHandle value
   * @param key the key value
   * @return a new instance of TensorMapHasKey
   */
  @Endpoint(
      describeByClass = true
  )
  public static TensorMapHasKey create(Scope scope, Operand<? extends TType> inputHandle,
      Operand<? extends TType> key) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorMapHasKey", scope.makeOpName("TensorMapHasKey"));
    opBuilder.addInput(inputHandle.asOutput());
    opBuilder.addInput(key.asOutput());
    opBuilder = scope.apply(opBuilder);
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
}
