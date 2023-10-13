/* Copyright 2018-2022 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TNumber;

/**
 * Outputs the position of {@code value} in a permutation of [0, ..., max_index].
 * Output values are a bijection of the {@code index} for any combination and {@code seed} and {@code max_index}.
 * <p>If multiple inputs are vectors (matrix in case of seed) then the size of the
 * first dimension must match.
 * <p>The outputs are deterministic.
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RandomIndexShuffle.OP_NAME,
    inputsClass = RandomIndexShuffle.Inputs.class
)
@Operator
public final class RandomIndexShuffle<T extends TNumber> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RandomIndexShuffle";

  private Output<T> output;

  public RandomIndexShuffle(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RandomIndexShuffle operation.
   *
   * @param scope current scope
   * @param index A scalar tensor or a vector of dtype {@code dtype}. The index (or indices) to be shuffled. Must be within [0, max_index].
   * @param seed A tensor of dtype {@code Tseed} and shape [3] or [n, 3]. The random seed.
   * @param maxIndex A scalar tensor or vector of dtype {@code dtype}. The upper bound(s) of the interval (inclusive).
   * @param options carries optional attribute values
   * @param <T> data type for {@code RandomIndexShuffle} output and operands
   * @return a new instance of RandomIndexShuffle
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TNumber> RandomIndexShuffle<T> create(Scope scope, Operand<T> index,
      Operand<? extends TNumber> seed, Operand<T> maxIndex, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RandomIndexShuffle");
    opBuilder.addInput(index.asOutput());
    opBuilder.addInput(seed.asOutput());
    opBuilder.addInput(maxIndex.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.rounds != null) {
          opBuilder.setAttr("rounds", opts.rounds);
        }
      }
    }
    return new RandomIndexShuffle<>(opBuilder.build());
  }

  /**
   * Sets the rounds option.
   *
   * @param rounds The number of rounds to use the in block cipher.
   * @return this Options instance.
   */
  public static Options rounds(Long rounds) {
    return new Options().rounds(rounds);
  }

  /**
   * Gets output.
   * A scalar tensor of dtype {@code dtype}, within [0, max_index]. The randomly shuffled index.
   * @return output.
   */
  public Output<T> output() {
    return output;
  }

  @Override
  public Output<T> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.core.RandomIndexShuffle}
   */
  public static class Options {
    private Long rounds;

    private Options() {
    }

    /**
     * Sets the rounds option.
     *
     * @param rounds The number of rounds to use the in block cipher.
     * @return this Options instance.
     */
    public Options rounds(Long rounds) {
      this.rounds = rounds;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RandomIndexShuffle.class
  )
  public static class Inputs<T extends TNumber> extends RawOpInputs<RandomIndexShuffle<T>> {
    /**
     * A scalar tensor or a vector of dtype {@code dtype}. The index (or indices) to be shuffled. Must be within [0, max_index].
     */
    public final Operand<T> index;

    /**
     * A tensor of dtype {@code Tseed} and shape [3] or [n, 3]. The random seed.
     */
    public final Operand<? extends TNumber> seed;

    /**
     * A scalar tensor or vector of dtype {@code dtype}. The upper bound(s) of the interval (inclusive).
     */
    public final Operand<T> maxIndex;

    /**
     * The number of rounds to use the in block cipher.
     */
    public final long rounds;

    /**
     * The dtype of the input and output.
     */
    public final DataType dtype;

    /**
     * The type of `seed`.
     */
    public final DataType Tseed;

    public Inputs(GraphOperation op) {
      super(new RandomIndexShuffle<>(op), op, Arrays.asList("rounds", "dtype", "Tseed"));
      int inputIndex = 0;
      index = (Operand<T>) op.input(inputIndex++);
      seed = (Operand<? extends TNumber>) op.input(inputIndex++);
      maxIndex = (Operand<T>) op.input(inputIndex++);
      rounds = op.attributes().getAttrInt("rounds");
      dtype = op.attributes().getAttrType("dtype");
      Tseed = op.attributes().getAttrType("Tseed");
    }
  }
}
