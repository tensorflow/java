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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.family.TType;

/**
 * Randomly shuffles a tensor along its first dimension.
 * The tensor is shuffled along dimension 0, such that each {@code value[j]} is mapped
 * to one and only one {@code output[i]}. For example, a mapping that might occur for a
 * 3x2 tensor is:
 * <pre>
 * [[1, 2],       [[5, 6],
 *  [3, 4],  ==&gt;   [1, 2],
 *  [5, 6]]        [3, 4]]
 * </pre>
 *
 * @param <T> data type for {@code output} output
 */
@OpMetadata(
    opType = RandomShuffle.OP_NAME,
    inputsClass = RandomShuffle.Inputs.class
)
@Operator(
    group = "random"
)
public final class RandomShuffle<T extends TType> extends RawOp implements Operand<T> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RandomShuffle";

  private Output<T> output;

  public RandomShuffle(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RandomShuffle operation.
   *
   * @param scope current scope
   * @param value The tensor to be shuffled.
   * @param options carries optional attribute values
   * @param <T> data type for {@code RandomShuffle} output and operands
   * @return a new instance of RandomShuffle
   */
  @Endpoint(
      describeByClass = true
  )
  public static <T extends TType> RandomShuffle<T> create(Scope scope, Operand<T> value,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RandomShuffle");
    opBuilder.addInput(value.asOutput());
    if (options != null) {
      for (Options opts : options) {
        if (opts.seed != null) {
          opBuilder.setAttr("seed", opts.seed);
        }
        if (opts.seed2 != null) {
          opBuilder.setAttr("seed2", opts.seed2);
        }
      }
    }
    return new RandomShuffle<>(opBuilder.build());
  }

  /**
   * Sets the seed option.
   *
   * @param seed If either {@code seed} or {@code seed2} are set to be non-zero, the random number
   * generator is seeded by the given seed.  Otherwise, it is seeded by a
   * random seed.
   * @return this Options instance.
   */
  public static Options seed(Long seed) {
    return new Options().seed(seed);
  }

  /**
   * Sets the seed2 option.
   *
   * @param seed2 A second seed to avoid seed collision.
   * @return this Options instance.
   */
  public static Options seed2(Long seed2) {
    return new Options().seed2(seed2);
  }

  /**
   * Gets output.
   * A tensor of same shape and type as {@code value}, shuffled along its first
   * dimension.
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
   * Optional attributes for {@link org.tensorflow.op.random.RandomShuffle}
   */
  public static class Options {
    private Long seed;

    private Long seed2;

    private Options() {
    }

    /**
     * Sets the seed option.
     *
     * @param seed If either {@code seed} or {@code seed2} are set to be non-zero, the random number
     * generator is seeded by the given seed.  Otherwise, it is seeded by a
     * random seed.
     * @return this Options instance.
     */
    public Options seed(Long seed) {
      this.seed = seed;
      return this;
    }

    /**
     * Sets the seed2 option.
     *
     * @param seed2 A second seed to avoid seed collision.
     * @return this Options instance.
     */
    public Options seed2(Long seed2) {
      this.seed2 = seed2;
      return this;
    }
  }

  @OpInputsMetadata(
      outputsClass = RandomShuffle.class
  )
  public static class Inputs<T extends TType> extends RawOpInputs<RandomShuffle<T>> {
    /**
     * The tensor to be shuffled.
     */
    public final Operand<T> value;

    /**
     * If either `seed` or `seed2` are set to be non-zero, the random number
     * generator is seeded by the given seed.  Otherwise, it is seeded by a
     * random seed.
     */
    public final long seed;

    /**
     * A second seed to avoid seed collision.
     */
    public final long seed2;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new RandomShuffle<>(op), op, Arrays.asList("seed", "seed2", "T"));
      int inputIndex = 0;
      value = (Operand<T>) op.input(inputIndex++);
      seed = op.attributes().getAttrInt("seed");
      seed2 = op.attributes().getAttrInt("seed2");
      T = op.attributes().getAttrType("T");
    }
  }
}
