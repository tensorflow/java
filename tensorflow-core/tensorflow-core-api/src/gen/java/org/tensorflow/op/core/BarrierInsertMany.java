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
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * For each key, assigns the respective value to the specified component.
 * <p>
 * If a key is not found in the barrier, this operation will create a new
 * incomplete element. If a key is found in the barrier, and the element
 * already has a value at component_index, this operation will fail with
 * INVALID_ARGUMENT, and leave the barrier in an undefined state.
 */
@Operator
public final class BarrierInsertMany extends RawOp {
  
  /**
   * Factory method to create a class wrapping a new BarrierInsertMany operation.
   * 
   * @param scope current scope
   * @param handle The handle to a barrier.
   * @param keys A one-dimensional tensor of keys, with length n.
   * @param values An any-dimensional tensor of values, which are associated with the
   * respective keys. The 0th dimension must have length n.
   * @param componentIndex The component of the barrier elements that is being assigned.
   * @return a new instance of BarrierInsertMany
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> BarrierInsertMany create(Scope scope, Operand<TString> handle, Operand<TString> keys, Operand<T> values, Long componentIndex) {
    OperationBuilder opBuilder = scope.env().opBuilder("BarrierInsertMany", scope.makeOpName("BarrierInsertMany"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(keys.asOutput());
    opBuilder.addInput(values.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("component_index", componentIndex);
    return new BarrierInsertMany(opBuilder.build());
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "BarrierInsertMany";
  
  private BarrierInsertMany(Operation operation) {
    super(operation);
  }
}
