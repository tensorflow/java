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
import org.tensorflow.types.family.TType;

/**
 * Looks up keys in a table, outputs the corresponding values.
 * <p>
 * The tensor `keys` must of the same type as the keys of the table.
 * The output `values` is of the type of the table values.
 * <p>
 * The scalar `default_value` is the value output for keys not present in the
 * table. It must also be of the same type as the table values.
 * 
 * @param <U> data type for {@code values()} output
 */
@Operator
public final class LookupTableFind<U extends TType> extends RawOp implements Operand<U> {
  
  /**
   * Factory method to create a class wrapping a new LookupTableFind operation.
   * 
   * @param scope current scope
   * @param tableHandle Handle to the table.
   * @param keys Any shape.  Keys to look up.
   * @param defaultValue 
   * @return a new instance of LookupTableFind
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TType> LookupTableFind<U> create(Scope scope, Operand<?> tableHandle, Operand<T> keys, Operand<U> defaultValue) {
    OperationBuilder opBuilder = scope.env().opBuilder("LookupTableFindV2", scope.makeOpName("LookupTableFind"));
    opBuilder.addInput(tableHandle.asOutput());
    opBuilder.addInput(keys.asOutput());
    opBuilder.addInput(defaultValue.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new LookupTableFind<U>(opBuilder.build());
  }
  
  /**
   * Same shape as `keys`.  Values found in the table, or `default_values`
   * for missing keys.
   */
  public Output<U> values() {
    return values;
  }
  
  @Override
  public Output<U> asOutput() {
    return values;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "LookupTableFindV2";
  
  private Output<U> values;
  
  private LookupTableFind(Operation operation) {
    super(operation);
    int outputIdx = 0;
    values = operation.output(outputIdx++);
  }
}
