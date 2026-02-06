/* Copyright 2018-2023 The TensorFlow Authors. All Rights Reserved.

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
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.proto.DataType;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/**
 * Outputs random values from the Poisson distribution(s) described by rate.
 * This op uses two algorithms, depending on rate. If rate &gt;= 10, then
 * the algorithm by Hormann is used to acquire samples via
 * transformation-rejection.
 * See http://www.sciencedirect.com/science/article/pii/0167668793909974.
 * <p>Otherwise, Knuth's algorithm is used to acquire samples via multiplying uniform
 * random variables.
 * See Donald E. Knuth (1969). Seminumerical Algorithms. The Art of Computer
 * Programming, Volume 2. Addison Wesley
 */
@OpMetadata(
    opType = RandomPoisson.OP_NAME,
    inputsClass = RandomPoisson.Inputs.class
)
@Operator(
    group = "random"
)
public final class RandomPoisson<V extends TNumber> extends RawOp implements Operand<V> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RandomPoissonV2";

  private Output<V> output;

  public RandomPoisson(Operation operation) {
    super(operation, OP_NAME);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RandomPoissonV2 operation.
   *
   * @param scope current scope
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   * distribution described by the shape parameters given in rate.
   * @param rate A tensor in which each scalar is a &quot;rate&quot; parameter describing the
   * associated poisson distribution.
   * @param dtype The value of the dtype attribute
   * @param options carries optional attribute values
   * @param <V> data type for {@code RandomPoissonV2} output and operands
   * @return a new instance of RandomPoisson
   */
  @Endpoint(
      describeByClass = true
  )
  public static <V extends TNumber> RandomPoisson<V> create(Scope scope,
      Operand<? extends TNumber> shape, Operand<? extends TNumber> rate, Class<V> dtype,
      Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RandomPoisson");
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(rate.asOutput());
    opBuilder.setAttr("dtype", Operands.toDataType(dtype));
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
    return new RandomPoisson<>(opBuilder.build());
  }

  /**
   * Factory method to create a class wrapping a new RandomPoissonV2 operation, with the default output types.
   *
   * @param scope current scope
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   * distribution described by the shape parameters given in rate.
   * @param rate A tensor in which each scalar is a &quot;rate&quot; parameter describing the
   * associated poisson distribution.
   * @param options carries optional attribute values
   * @return a new instance of RandomPoisson, with default output types
   */
  @Endpoint(
      describeByClass = true
  )
  public static RandomPoisson<TInt64> create(Scope scope, Operand<? extends TNumber> shape,
      Operand<? extends TNumber> rate, Options... options) {
    return create(scope, shape, rate, TInt64.class, options);
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
   * A tensor with shape {@code shape + shape(rate)}. Each slice
   * {@code [:, ..., :, i0, i1, ...iN]} contains the samples drawn for
   * {@code rate[i0, i1, ...iN]}.
   * @return output.
   */
  public Output<V> output() {
    return output;
  }

  @Override
  public Output<V> asOutput() {
    return output;
  }

  /**
   * Optional attributes for {@link org.tensorflow.op.random.RandomPoisson}
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
      outputsClass = RandomPoisson.class
  )
  public static class Inputs extends RawOpInputs<RandomPoisson<?>> {
    /**
     * 1-D integer tensor. Shape of independent samples to draw from each
     * distribution described by the shape parameters given in rate.
     */
    public final Operand<? extends TNumber> shape;

    /**
     * A tensor in which each scalar is a &quot;rate&quot; parameter describing the
     * associated poisson distribution.
     */
    public final Operand<? extends TNumber> rate;

    /**
     * If either {@code seed} or {@code seed2} are set to be non-zero, the random number
     * generator is seeded by the given seed.  Otherwise, it is seeded by a
     * random seed.
     */
    public final long seed;

    /**
     * A second seed to avoid seed collision.
     */
    public final long seed2;

    /**
     * The S attribute
     */
    public final DataType S;

    /**
     * The R attribute
     */
    public final DataType R;

    /**
     * The dtype attribute
     */
    public final DataType dtype;

    public Inputs(GraphOperation op) {
      super(new RandomPoisson<>(op), op, Arrays.asList("seed", "seed2", "S", "R", "dtype"));
      int inputIndex = 0;
      shape = (Operand<? extends TNumber>) op.input(inputIndex++);
      rate = (Operand<? extends TNumber>) op.input(inputIndex++);
      seed = op.attributes().getAttrInt("seed");
      seed2 = op.attributes().getAttrInt("seed2");
      S = op.attributes().getAttrType("S");
      R = op.attributes().getAttrType("R");
      dtype = op.attributes().getAttrType("dtype");
    }
  }
}
