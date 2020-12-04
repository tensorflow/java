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

package org.tensorflow.op.random;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TType;

/**
 * Outputs random values from a uniform distribution.
 * <p>
 * The generated values follow a uniform distribution in the range `[0, 1)`. The
 * lower bound 0 is included in the range, while the upper bound 1 is excluded.
 * 
 * @param <U> data type for {@code output()} output
 */
public final class StatefulUniform<U extends TType> extends RawOp implements Operand<U> {
  
  /**
   * Factory method to create a class wrapping a new StatefulUniform operation.
   * 
   * @param scope current scope
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @param dtype The type of the output.
   * @return a new instance of StatefulUniform
   */
  @Endpoint(describeByClass = true)
  public static <U extends TType, T extends TType> StatefulUniform<U> create(Scope scope, Operand<?> resource, Operand<TInt64> algorithm, Operand<T> shape, DataType<U> dtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("StatefulUniform", scope.makeOpName("StatefulUniform"));
    opBuilder.addInput(resource.asOutput());
    opBuilder.addInput(algorithm.asOutput());
    opBuilder.addInput(shape.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", dtype);
    return new StatefulUniform<U>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new StatefulUniform operation using default output types.
   * 
   * @param scope current scope
   * @param resource The handle of the resource variable that stores the state of the RNG.
   * @param algorithm The RNG algorithm.
   * @param shape The shape of the output tensor.
   * @return a new instance of StatefulUniform
   */
  @Endpoint(describeByClass = true)
  public static <T extends TType> StatefulUniform<TFloat32> create(Scope scope, Operand<?> resource, Operand<TInt64> algorithm, Operand<T> shape) {
    return create(scope, resource, algorithm, shape, TFloat32.DTYPE);
  }
  
  /**
   * Random values with specified shape.
   */
  public Output<U> output() {
    return output;
  }
  
  @Override
  public Output<U> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StatefulUniform";
  
  private Output<U> output;
  
  private StatefulUniform(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
