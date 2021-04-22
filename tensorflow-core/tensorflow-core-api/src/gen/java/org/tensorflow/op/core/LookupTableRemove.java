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
import org.tensorflow.types.family.TType;

/**
 * Removes keys and its associated values from a table.
 * The tensor {@code keys} must of the same type as the keys of the table. Keys not
 * already in the table are silently ignored.
 */
public final class LookupTableRemove extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LookupTableRemoveV2";

  private LookupTableRemove(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new LookupTableRemoveV2 operation.
   *
   * @param scope current scope
   * @param tableHandle Handle to the table.
   * @param keys Any shape.  Keys of the elements to remove.
   * @return a new instance of LookupTableRemove
   */
  @Endpoint(
      describeByClass = true
  )
  public static LookupTableRemove create(Scope scope, Operand<? extends TType> tableHandle,
      Operand<? extends TType> keys) {
    OperationBuilder opBuilder = scope.env().opBuilder("LookupTableRemoveV2", scope.makeOpName("LookupTableRemove"));
    opBuilder.addInput(tableHandle.asOutput());
    opBuilder.addInput(keys.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new LookupTableRemove(opBuilder.build());
  }
}
