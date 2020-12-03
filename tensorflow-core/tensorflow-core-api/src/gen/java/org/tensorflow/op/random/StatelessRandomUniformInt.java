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

import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Outputs deterministic pseudorandom random integers from a uniform distribution.
 * <p>
 * The generated values follow a uniform distribution in the range `[minval, maxval)`.
 * <p>
 * The outputs are a deterministic function of `shape`, `seed`, `minval`, and `maxval`.
 * 
 * @param <V> data type for {@code output()} output
 */
public final class StatelessRandomUniformInt<V extends TNumber> extends RawOp implements Operand<V> {
  
  /**
   * Factory method to create a class wrapping a new StatelessRandomUniformInt operation.
   * 
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param minval Minimum value (inclusive, scalar).
   * @param maxval Maximum value (exclusive, scalar).
   * @return a new instance of StatelessRandomUniformInt
   */
  @Endpoint(describeByClass = true)
  public static <V extends TNumber, T extends TNumber, U extends TNumber> StatelessRandomUniformInt<V> create(Scope scope, Operand<T> shape, Operand<U> seed, Operand<V> minval, Operand<V> maxval) {
    OperationBuilder opBuilder = scope.env().opBuilder("StatelessRandomUniformInt", scope.makeOpName("StatelessRandomUniformInt"));
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(minval.asOutput());
    opBuilder.addInput(maxval.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new StatelessRandomUniformInt<V>(opBuilder.build());
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
  public static final String OP_NAME = "StatelessRandomUniformInt";
  
  private Output<V> output;
  
  private StatelessRandomUniformInt(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
