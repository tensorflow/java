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
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Operator;

/**
 * Outputs random values from a normal distribution. This op is deprecated in favor of op 'StatefulStandardNormalV2'
 * <p>
 * The generated values will have mean 0 and standard deviation 1.
 * 
 * @param <U> data type for {@code output()} output
 */
@Operator
public final class StatefulStandardNormal<U> extends PrimitiveOp implements Operand<U> {
  
  /**
   * Factory method to create a class wrapping a new StatefulStandardNormal operation.
   * 
   * @param scope current scope
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @return a new instance of StatefulStandardNormal
   */
  public static <U, T> StatefulStandardNormal<U> create(Scope scope, Operand<?> resource, Operand<T> shape, Class<U> dtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("StatefulStandardNormal", scope.makeOpName("StatefulStandardNormal"));
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    opBuilder.setAttr("dtype", DataType.fromClass(dtype));
    return new StatefulStandardNormal<U>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new StatefulStandardNormal operation using default output types.
   * 
   * @param scope current scope
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param shape The shape of the output tensor.
   * @return a new instance of StatefulStandardNormal
   */
  public static <T> StatefulStandardNormal<Float> create(Scope scope, Operand<?> resource, Operand<T> shape) {
    return create(scope, resource, shape, Float.class);
  }
  
  /**
   * A tensor of the specified shape filled with random normal values.
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  private Output<U> output;
  
  private StatefulStandardNormal(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
