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
import org.tensorflow.types.TInt64;

/**
 * Computes the number of elements in the given table.
 */
@Operator
public final class LookupTableSize extends RawOp implements Operand<TInt64> {
  
  /**
   * Factory method to create a class wrapping a new LookupTableSize operation.
   * 
   * @param scope current scope
   * @param tableHandle Handle to the table.
   * @return a new instance of LookupTableSize
   */
  @Endpoint(describeByClass = true)
  public static LookupTableSize create(Scope scope, Operand<?> tableHandle) {
    OperationBuilder opBuilder = scope.env().opBuilder("LookupTableSizeV2", scope.makeOpName("LookupTableSize"));
    opBuilder.addInput(tableHandle.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new LookupTableSize(opBuilder.build());
  }
  
  /**
   * Scalar that contains number of elements in the table.
   */
  public Output<TInt64> output() {
    return output;
  }
  
  @Override
  public Output<TInt64> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "LookupTableSizeV2";
  
  private Output<TInt64> output;
  
  private LookupTableSize(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
