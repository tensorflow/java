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

/**
 * Outputs random values from the Gamma distribution(s) described by alpha.
 * This op uses the algorithm by Marsaglia et al. to acquire samples via
 * transformation-rejection from pairs of uniform and normal random variables.
 * See http://dl.acm.org/citation.cfm?id=358414
 *
 * @param <U> data type for {@code output} output
 */
@Operator(
    group = "random"
)
public final class RandomGamma<U extends TNumber> extends RawOp implements Operand<U> {
  /**
   * The name of this op, as known by TensorFlow core engine
   */
  public static final String OP_NAME = "RandomGamma";

  private Output<U> output;

  private RandomGamma(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }

  /**
   * Factory method to create a class wrapping a new RandomGamma operation.
   *
   * @param scope current scope
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   * distribution described by the shape parameters given in alpha.
   * @param alpha A tensor in which each scalar is a &quot;shape&quot; parameter describing the
   * associated gamma distribution.
   * @param options carries optional attribute values
   * @param <U> data type for {@code RandomGamma} output and operands
   * @return a new instance of RandomGamma
   */
  @Endpoint(
      describeByClass = true
  )
  public static <U extends TNumber> RandomGamma<U> create(Scope scope,
      Operand<? extends TNumber> shape, Operand<U> alpha, Options... options) {
    OperationBuilder opBuilder = scope.opBuilder(OP_NAME, "RandomGamma");
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(alpha.asOutput());
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
    return new RandomGamma<>(opBuilder.build());
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
   * A tensor with shape {@code shape + shape(alpha)}. Each slice
   * {@code [:, ..., :, i0, i1, ...iN]} contains the samples drawn for
   * {@code alpha[i0, i1, ...iN]}. The dtype of the output matches the dtype of alpha.
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
   * Optional attributes for {@link org.tensorflow.op.random.RandomGamma}
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
}
