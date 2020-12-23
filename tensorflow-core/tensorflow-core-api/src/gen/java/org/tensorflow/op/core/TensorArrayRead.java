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
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

/**
 * Read an element from the TensorArray into output `value`.
 * 
 * @param <T> data type for {@code value()} output
 */
@Operator
public final class TensorArrayRead<T extends TType> extends RawOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new TensorArrayRead operation.
   * 
   * @param scope current scope
   * @param handle The handle to a TensorArray.
   * @param index 
   * @param flowIn A float scalar that enforces proper chaining of operations.
   * @param dtype The type of the elem that is returned.
   * @return a new instance of TensorArrayRead
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> TensorArrayRead<T> create(Scope scope, Operand<?> handle, Operand<TInt32> index, Operand<TFloat32> flowIn, Class<T> dtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("TensorArrayReadV3", scope.makeOpName("TensorArrayRead"));
    opBuilder.addInput(handle.asOutput());
    opBuilder.addInput(index.asOutput());
    opBuilder.addInput(flowIn.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new TensorArrayRead<T>(opBuilder.build());
  }
  
  /**
   * The tensor that is read from the TensorArray.
   */
  public Output<T> value() {
    return value;
  }
  
  @Override
  public Output<T> asOutput() {
    return value;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "TensorArrayReadV3";
  
  private Output<T> value;
  
  private TensorArrayRead(Operation operation) {
    super(operation);
    int outputIdx = 0;
    value = operation.output(outputIdx++);
  }
}
