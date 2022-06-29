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

import java.util.Arrays;
import org.tensorflow.GraphOperation;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Outputs deterministic pseudorandom random integers from a uniform distribution.
 * The generated values follow a uniform distribution in the range {@code [minval, maxval)}.
 * <p>The outputs are a deterministic function of {@code shape}, {@code seed}, {@code minval}, and {@code maxval}.
 *
 * @param <V> data type for {@code output} output
 */
@OpMetadata(
    opType = StatelessRandomUniformInt.OP_NAME,
    inputsClass = StatelessRandomUniformInt.Inputs.class
)
public final class StatelessRandomUniformInt<V extends TNumber> extends RawOp implements Operand<V> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessRandomUniformInt";

  private Output<V> output;

  public StatelessRandomUniformInt(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatelessRandomUniformInt operation.
   *
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param minval Minimum value (inclusive, scalar).
   * @param maxval Maximum value (exclusive, scalar).
   * @param <V> data type for {@code StatelessRandomUniformInt} output and operands
   * @return a new instance of StatelessRandomUniformInt
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> StatelessRandomUniformInt<V> create(Scope scope,
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed, Operand<V> minval,
      Operand<V> maxval) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatelessRandomUniformInt");
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(minval.asOutput());
    opBuilder.addInput(maxval.asOutput());
    return new StatelessRandomUniformInt<>(opBuilder.build());
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

  @OpInputsMetadata(
      outputsClass = StatelessRandomUniformInt.class
  )
  public static class Inputs<V extends TNumber> extends RawOpInputs<StatelessRandomUniformInt<V>> {
    /**
     * The shape of the output tensor.
     */
    public final Operand<? extends TNumber> shape;

    /**
     * 2 seeds (shape [2]).
     */
    public final Operand<? extends TNumber> seed;

    /**
     * Minimum value (inclusive, scalar).
     */
    public final Operand<V> minval;

    /**
     * Maximum value (exclusive, scalar).
     */
    public final Operand<V> maxval;

    /**
     * The type of the output.
     */
    public final DataType dtype;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The Tseed attribute
     */
    public final DataType Tseed;

    public Inputs(GraphOperation op) {
      super(new StatelessRandomUniformInt<>(op), op, Arrays.asList("dtype", "T", "Tseed"));
      int inputIndex = 0;
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      seed = (Operand<? extends TNumber>) op.input(inputIndex++);
      minval = (Operand<V>) op.input(inputIndex++);
      maxval = (Operand<V>) op.input(inputIndex++);
      dtype = op.attributes().getAttrType("dtype");
      T = op.attributes().getAttrType("T");
      Tseed = op.attributes().getAttrType("Tseed");
    }
  }
}
