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
import org.tensorflow.types.family.TType;

/**
 * Updates the table to associates keys with values.
 * The tensor {@code keys} must be of the same type as the keys of the table.
 * The tensor {@code values} must be of the type of the table values.
 */
@Operator
public final class LookupTableInsert extends RawOp {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "LookupTableInsertV2";

  private LookupTableInsert(Operation operation) {
    super(operation);
  }

  /**
   * Factory method to create a class wrapping a new LookupTableInsertV2 operation.
   *
   * @param scope current scope
   * @param tableHandle Handle to the table.
   * @param keys Any shape.  Keys to look up.
   * @param values Values to associate with keys.
   * @return a new instance of LookupTableInsert
   */
  @Endpoint(
      describeByClass = true
  )
  public static LookupTableInsert create(Scope scope, Operand<? extends TType> tableHandle,
      Operand<? extends TType> keys, Operand<? extends TType> values) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "LookupTableInsert");
    opBuilder.addInput(tableHandle.asOutput());
    opBuilder.addInput(keys.asOutput());
    opBuilder.addInput(values.asOutput());
    return new LookupTableInsert(opBuilder.build());
  }
}
