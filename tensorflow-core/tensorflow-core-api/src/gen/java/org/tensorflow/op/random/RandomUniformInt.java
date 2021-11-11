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
import org.tensorflow.types.family.TNumber;

/**
 * Outputs random integers from a uniform distribution.
 * The generated values are uniform integers in the range {@code [minval, maxval)}.
 * The lower bound {@code minval} is included in the range, while the upper bound
 * {@code maxval} is excluded.
 * <p>The random integers are slightly biased unless {@code maxval - minval} is an exact
 * power of two.  The bias is small for values of {@code maxval - minval} significantly
 * smaller than the range of the output (either {@code 2^32} or {@code 2^64}).
 *
 * @param <U> data type for {@code output} output
 */
@OpMetadata(
    opType = RandomUniformInt.OP_NAME,
    inputsClass = RandomUniformInt.Inputs.class
)
@Operator(
    group = "random"
)
public final class RandomUniformInt<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RandomUniformInt";

  private Output<U> output;

  public RandomUniformInt(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RandomUniformInt operation.
   *
   * @param scope current scope
   * @param shape The shape of the output tensor.
   * @param minval 0-D.  Inclusive lower bound on the generated integers.
   * @param maxval 0-D.  Exclusive upper bound on the generated integers.
   * @param options carries optional attribute values
   * @param <U> data type for {@code RandomUniformInt} output and operands
   * @return a new instance of RandomUniformInt
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> RandomUniformInt<U> create(Scope scope,
      Operand<? extends TNumber> shape, Operand<U> minval, Operand<U> maxval, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RandomUniformInt");
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(minval.asOutput());
    opBuilder.addInput(maxval.asOutput());
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
    return new RandomUniformInt<>(opBuilder.build());
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
   * A tensor of the specified shape filled with uniform random integers.
   * @return output.
   */
  public Output<U> output() {
    return output;
  }

  @Override
  public Output<U> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.random.RandomUniformInt}
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
      outputsClass = RandomUniformInt.class
  )
  public static class Inputs<U extends TNumber> extends RawOpInputs<RandomUniformInt<U>> {
    /**
     * The shape of the output tensor.
     */
    public final Operand<? extends TNumber> shape;

    /**
     * 0-D.  Inclusive lower bound on the generated integers.
     */
    public final Operand<U> minval;

    /**
     * 0-D.  Exclusive upper bound on the generated integers.
     */
    public final Operand<U> maxval;

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
     * The Tout attribute
     */
    public final DataType Tout;

    /**
     * The T attribute
     */
    public final DataType T;

    public Inputs(GraphOperation op) {
      super(new RandomUniformInt<>(op), op, Arrays.asList("seed", "seed2", "Tout", "T"));
      int inputIndex = 0;
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      minval = (Operand<U>) op.input(inputIndex++);
      maxval = (Operand<U>) op.input(inputIndex++);
      seed = op.attributes().getAttrInt("seed");
      seed2 = op.attributes().getAttrInt("seed2");
      Tout = op.attributes().getAttrType("Tout");
      T = op.attributes().getAttrType("T");
    }
  }
}
