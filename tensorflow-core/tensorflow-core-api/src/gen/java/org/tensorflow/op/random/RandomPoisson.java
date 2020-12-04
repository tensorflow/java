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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.RawOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Outputs random values from the Poisson distribution(s) described by rate.
 * <p>
 * This op uses two algorithms, depending on rate. If rate >= 10, then
 * the algorithm by Hormann is used to acquire samples via
 * transformation-rejection.
 * See http://www.sciencedirect.com/science/article/pii/0167668793909974.
 * <p>
 * Otherwise, Knuth's algorithm is used to acquire samples via multiplying uniform
 * random variables.
 * See Donald E. Knuth (1969). Seminumerical Algorithms. The Art of Computer
 * Programming, Volume 2. Addison Wesley
 * 
 * @param <V> data type for {@code output()} output
 */
@Operator(group = "random")
public final class RandomPoisson<V extends TNumber> extends RawOp implements Operand<V> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.random.RandomPoisson}
   */
  public static class Options {
    
    /**
     * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
     * generator is seeded by the given seed.  Otherwise, it is seeded by a
     * random seed.
     */
    public Options seed(Long seed) {
      this.seed = seed;
      return this;
    }
    
    /**
     * @param seed2 A second seed to avoid seed collision.
     */
    public Options seed2(Long seed2) {
      this.seed2 = seed2;
      return this;
    }
    
    private Long seed;
    private Long seed2;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new RandomPoisson operation.
   * 
   * @param scope current scope
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   * distribution described by the shape parameters given in rate.
   * @param rate A tensor in which each scalar is a "rate" parameter describing the
   * associated poisson distribution.
   * @param dtype 
   * @param options carries optional attributes values
   * @return a new instance of RandomPoisson
   */
  @Endpoint(describeByClass = true)
  public static <V extends TNumber, T extends TNumber, U extends TNumber> RandomPoisson<V> create(Scope scope, Operand<T> shape, Operand<U> rate, DataType<V> dtype, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("RandomPoissonV2", scope.makeOpName("RandomPoisson"));
    opBuilder.addInput(shape.asOutput());
    opBuilder.addInput(rate.asOutput());
    opBuilder = scope.apply(opBuilder);
    opBuilder.setAttr("dtype", dtype);
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
    return new RandomPoisson<V>(opBuilder.build());
  }
  
  /**
   * Factory method to create a class wrapping a new RandomPoisson operation using default output types.
   * 
   * @param scope current scope
   * @param shape 1-D integer tensor. Shape of independent samples to draw from each
   * distribution described by the shape parameters given in rate.
   * @param rate A tensor in which each scalar is a "rate" parameter describing the
   * associated poisson distribution.
   * @param options carries optional attributes values
   * @return a new instance of RandomPoisson
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber, U extends TNumber> RandomPoisson<TInt64> create(Scope scope, Operand<T> shape, Operand<U> rate, Options... options) {
    return create(scope, shape, rate, TInt64.DTYPE, options);
  }
  
  /**
   * @param seed If either `seed` or `seed2` are set to be non-zero, the random number
   * generator is seeded by the given seed.  Otherwise, it is seeded by a
   * random seed.
   */
  public static Options seed(Long seed) {
    return new Options().seed(seed);
  }
  
  /**
   * @param seed2 A second seed to avoid seed collision.
   */
  public static Options seed2(Long seed2) {
    return new Options().seed2(seed2);
  }
  
  /**
   * A tensor with shape `shape + shape(rate)`. Each slice
   * `[:, ..., :, i0, i1, ...iN]` contains the samples drawn for
   * `rate[i0, i1, ...iN]`.
   */
  public Output<V> output() {
    return output;
  }
  
  @Override
  public Output<V> asOutput() {
    return output;
  }
  
  /** The name of this op, as known by TensorFlow core engine */
  public static final String OP_NAME = "RandomPoissonV2";
  
  private Output<V> output;
  
  private RandomPoisson(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
