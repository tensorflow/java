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
import org.tensorflow.op.Operands;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.RawOpInputs;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.OpInputsMetadata;
import org.tensorflow.op.annotation.OpMetadata;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Outputs deterministic pseudorandom random numbers from a binomial distribution.
 * Outputs random values from a binomial distribution.
 * <p>The outputs are a deterministic function of {@code shape}, {@code seed}, {@code counts}, and {@code probs}.
 *
 * @param <W> data type for {@code output} output
 */
@OpMetadata(
    opType = StatelessRandomBinomial.OP_NAME,
    inputsClass = StatelessRandomBinomial.Inputs.class
)
public final class StatelessRandomBinomial<W extends TNumber> extends RawOp implements Operand<W> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "StatelessRandomBinomial";

  private Output<W> output;

  public StatelessRandomBinomial(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new StatelessRandomBinomial operation.
   *
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param counts The counts of the binomial distribution. Must be broadcastable with {@code probs},
   * and broadcastable with the rightmost dimensions of {@code shape}.
   * @param probs The probability of success for the binomial distribution. Must be broadcastable
   * with {@code counts} and broadcastable with the rightmost dimensions of {@code shape}.
   * @param dtype The type of the output.
   * @param <W> data type for {@code StatelessRandomBinomial} output and operands
   * @param <V> data type for {@code StatelessRandomBinomial} output and operands
   * @return a new instance of StatelessRandomBinomial
   */
  @Endpoint(
      describeByClass = true
  )
  public static <W extends TNumber, V extends TNumber> StatelessRandomBinomial<W> create(
      Scope scope, Operand<? extends TNumber> shape, Operand<? extends TNumber> seed,
      Operand<V> counts, Operand<V> probs, Class<W> dtype) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "StatelessRandomBinomial");
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(counts.asOutput());
    opBuilder.addInput(probs.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
    return new StatelessRandomBinomial<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new StatelessRandomBinomial operation, with the default output types.
   *
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param seed 2 seeds (shape [2]).
   * @param counts The counts of the binomial distribution. Must be broadcastable with {@code probs},
   * and broadcastable with the rightmost dimensions of {@code shape}.
   * @param probs The probability of success for the binomial distribution. Must be broadcastable
   * with {@code counts} and broadcastable with the rightmost dimensions of {@code shape}.
   * @param <V> data type for {@code StatelessRandomBinomial} output and operands
   * @return a new instance of StatelessRandomBinomial, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> StatelessRandomBinomial<TInt64> create(Scope scope,
      Operand<? extends TNumber> shape, Operand<? extends TNumber> seed, Operand<V> counts,
      Operand<V> probs) {
    return create(scope, shape, seed, counts, probs, TInt64.class);
  }

  /**
   * Gets output.
   * Random values with specified shape.
   * @return output.
   */
  public Output<W> output() {
    return output;
  }

  @Override
  public Output<W> asOutput() {
    return output;
  }

  @OpInputsMetadata(
      outputsClass = StatelessRandomBinomial.class
  )
  public static class Inputs<V extends TNumber> extends RawOpInputs<StatelessRandomBinomial<?>> {
    /**
     * The shape of the output tensor.
     */
    public final Operand<? extends TNumber> shape;

    /**
     * 2 seeds (shape [2]).
     */
    public final Operand<? extends TNumber> seed;

    /**
     * The counts of the binomial distribution. Must be broadcastable with {@code probs},
     * and broadcastable with the rightmost dimensions of {@code shape}.
     */
    public final Operand<V> counts;

    /**
     * The probability of success for the binomial distribution. Must be broadcastable
     * with {@code counts} and broadcastable with the rightmost dimensions of {@code shape}.
     */
    public final Operand<V> probs;

    /**
     * The S attribute
     */
    public final DataType S;

    /**
     * The Tseed attribute
     */
    public final DataType Tseed;

    /**
     * The T attribute
     */
    public final DataType T;

    /**
     * The type of the output.
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new StatelessRandomBinomial<>(op), op, Arrays.asList("S", "Tseed", "T", "dtype"));
      int inputIndex = 0;
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      seed = (Operand<? extends TNumber>) op.input(inputIndex++);
      counts = (Operand<V>) op.input(inputIndex++);
      probs = (Operand<V>) op.input(inputIndex++);
      S = op.attributes().getAttrType("S");
      Tseed = op.attributes().getAttrType("Tseed");
      T = op.attributes().getAttrType("T");
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
