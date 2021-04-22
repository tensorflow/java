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
import org.tensorflow.types.family.TNumber;

/**
 * Outputs deterministic pseudorandom random numbers from a gamma distribution.
 * Outputs random values from a gamma distribution.
 * <p>The outputs are a deterministic function of {@code shape}, {@code seed}, and {@code alpha}.
 *
 * @param <V> data type for {@code output} output
 */
public final class StatelessRandomGamma<V extends TNumber> extends RawOp implements Operand<V> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessRandomGammaV2";

  private Output<V> output;

  private StatelessRandomGamma(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatelessRandomGammaV2 operation.
   *
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param alpha The concentration of the gamma distribution. Shape must match the rightmost
   * dimensions of {@code shape}.
   * @param <V> data type for {@code StatelessRandomGammaV2} output and operands
   * @return a new instance of StatelessRandomGamma
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> StatelessRandomGamma<V> create(Scope scope,
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed, Operand<V> alpha) {
    OperationBuilder opBuilder = scope.env().opBuilder("StatelessRandomGammaV2", scope.makeOpName("StatelessRandomGamma"));
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(alpha.asOutput());
    opBuilder = scope.apply(opBuilder);
    return new StatelessRandomGamma<>(opBuilder.build());
  }

  /**
   * Gets output.
   * Random values with specified shape.
   * @return output.
   */
  public Output<V> output() {
    return output;
  }

  @Override
  public Output<V> asOutput() {
    return output;
  }
}
