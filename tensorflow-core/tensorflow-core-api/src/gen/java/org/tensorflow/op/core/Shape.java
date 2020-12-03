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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Returns the shape of a tensor.
 * <p>
 * This operation returns a 1-D integer tensor representing the shape of `input`.
 * <p>
 * For example:
 * <pre>{@code
 * # 't' is [[[1, 1, 1], [2, 2, 2]], [[3, 3, 3], [4, 4, 4]]]
 * shape(t) ==> [2, 2, 3]
 * }</pre>
 * 
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator
public final class Shape<U extends TNumber> extends RawOp implements Operand<U> {
  
  /**
   * Factory method to create a class wrapping a new Shape operation.
   * 
   * @param scope current scope
   * @param input 
   * @param outType 
   * @return a new instance of Shape
   */
  @Endpoint(describeByClass = true)
  public static <U extends TNumber, T extends TType> Shape<U> create(Scope scope, Operand<T> input, DataType<U> outType) {
    OperationBuilder opBuilder = scope.env().opBuilder("Shape", scope.makeOpName("Shape"));
    opBuilder.addInput(input.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("out_type", outType);
    return new Shape<U>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new Shape operation using default output types.
   * 
   * @param scope current scope
   * @param input 
   * @return a new instance of Shape
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> Shape<TInt32> create(Scope scope, Operand<T> input) {
    return create(scope, input, TInt32.DTYPE);
  }
  
  /**
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "Shape";
  
  private Output<U> output;
  
  private Shape(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
