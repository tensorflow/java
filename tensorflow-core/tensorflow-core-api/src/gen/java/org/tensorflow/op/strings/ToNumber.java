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

package org.tensorflow.op.strings;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Converts each string in the input Tensor to the specified numeric type.
 * <p>
 * (Note that int32 overflow results in an error while float overflow
 * results in a rounded value.)
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "strings")
public final class ToNumber<T extends TNumber> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Factory method to create a class wrapping a new ToNumber operation.
   * 
   * @param scope current scope
   * @param stringTensor 
   * @param outType The numeric type to interpret each string in `string_tensor` as.
   * @return a new instance of ToNumber
   */
  public static <T extends TNumber> ToNumber<T> create(Scope scope, Operand<TString> stringTensor, DataType<T> outType) {
    OperationBuilder opBuilder = scope.env().opBuilder("StringToNumber", scope.makeOpName("ToNumber"));
    opBuilder.addInput(stringTensor.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    opBuilder.setAttr("out_type", outType);
    return new ToNumber<T>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new ToNumber operation using default output types.
   * 
   * @param scope current scope
   * @param stringTensor 
   * @return a new instance of ToNumber
   */
  public static ToNumber<TFloat32> create(Scope scope, Operand<TString> stringTensor) {
    return create(scope, stringTensor, TFloat32.DTYPE);
  }
  
  /**
   * A Tensor of the same shape as the input `string_tensor`.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  private Output<T> output;
  
  private ToNumber(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
