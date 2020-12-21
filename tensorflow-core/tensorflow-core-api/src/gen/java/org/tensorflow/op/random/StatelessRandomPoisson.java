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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Outputs deterministic pseudorandom random numbers from a Poisson distribution.
 * <p>
 * Outputs random values from a Poisson distribution.
 * <p>
 * The outputs are a deterministic function of `shape`, `seed`, and `lam`.
 * 
 * @param <W> data type for {@code output()} output
 */
public final class StatelessRandomPoisson<W extends TNumber> extends RawOp implements Operand<W> {
  
  /**
   * Factory method to create a class wrapping a new StatelessRandomPoisson operation.
   * 
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param lam The rate of the Poisson distribution. Shape must match the rightmost dimensions
   * of `shape`.
   * @param dtype The type of the output.
   * @return a new instance of StatelessRandomPoisson
   */
  @Endpoint(describeByClass = true)
  public static <W extends TNumber, T extends TNumber, U extends TNumber, V extends TNumber> StatelessRandomPoisson<W> create(Scope scope, Operand<T> shape, Operand<U> seed, Operand<V> lam, Class<W> dtype) {
    OperationBuilder opBuilder = scope.env().opBuilder("StatelessRandomPoisson", scope.makeOpName("StatelessRandomPoisson"));
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(lam.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new StatelessRandomPoisson<W>(opBuilder.build());
  }
  
  /**
   * Random values with specified shape.
   */
  public Output<W> output() {
    return output;
  }
  
  @Override
  public Output<W> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "StatelessRandomPoisson";
  
  private Output<W> output;
  
  private StatelessRandomPoisson(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
