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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Outputs deterministic pseudorandom random values from a uniform distribution.
 * <p>
 * The generated values follow a uniform distribution in the range `[0, 1)`. The
 * lower bound 0 is included in the range, while the upper bound 1 is excluded.
 * <p>
 * The outputs are a deterministic function of `shape` and `seed`.
 * 
 * @param <V> data type for {@code output()} output
 */
@Operator(group = "random")
public final class StatelessRandomUniform<V extends TNumber> extends RawOp implements Operand<V> {
  
  /**
   * Factory method to create a class wrapping a new StatelessRandomUniform operation.
   * 
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param dtype The type of the output.
   * @return a new instance of StatelessRandomUniform
   */
  @Endpoint(describeByClass = true)
  public static <V extends TNumber, T extends TNumber, U extends TNumber> StatelessRandomUniform<V> create(Scope scope, Operand<T> shape, Operand<U> seed, DataType<V> dtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("StatelessRandomUniform", scope.makeOpName("StatelessRandomUniform"));
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", dtype);
    return new StatelessRandomUniform<V>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new StatelessRandomUniform operation using default output types.
   * 
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @return a new instance of StatelessRandomUniform
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber> StatelessRandomUniform<TFloat32> create(Scope scope, Operand<T> shape, Operand<U> seed) {
    return create(scope, shape, seed, TFloat32.DTYPE);
  }
  
  /**
   * Random values with specified shape.
   */
  public Output<V> output() {
    return output;
  }
  
  @Override
  public Output<V> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StatelessRandomUniform";
  
  private Output<V> output;
  
  private StatelessRandomUniform(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
