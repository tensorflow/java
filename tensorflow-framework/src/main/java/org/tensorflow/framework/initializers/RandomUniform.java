/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.framework.initializers;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.op.random.RandomUniformInt;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TIntegral;
import org.tensorflow.types.family.TNumber;

/**
 * Initializer that generates tensors with a uniform distribution.
 *
 * <p>Examples:
 *
 * <pre>
 *     long seed = 1001l;
 *     RandomUniform&lt;TFloat32, TFloat32&gt; initializer =
 *              new org.tensorflow.framework.initializers.RandomUniform&lt;&gt;(tf, seed);
 *     Operand&lt;TFloat32&gt; values =
 *              initializer.call(tf.constant(Shape.of(2,2)), TFloat32.class);
 * </pre>
 *
 * @param <T> The TType for the call operation
 */
public class RandomUniform<T extends TNumber> extends BaseInitializer<T> {

  public static final double MINVAL_DEFAULT = -0.05;
  public static final double MAXVAL_DEFAULT = 0.05;

  private final Double minval;
  private final Double maxval;
  private final long seed;

  /**
   * Creates a RandomUniform initializer using {@link #MINVAL_DEFAULT} for the minval and
   * {@link #MAXVAL_DEFAULT} for the maxval
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and dtype.
   */
  public RandomUniform(Ops tf, long seed) {
    this(tf, MINVAL_DEFAULT, MAXVAL_DEFAULT, seed);
  }

  /**
   * Creates a RandomUniform initializer
   *
   * @param tf the TensorFlow Ops
   * @param minval Lower bound of the range of random values to generate (inclusive).
   * @param maxval Upper bound of the range of random values to generate (exclusive).
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and dtype.
   */
  public RandomUniform(Ops tf, double minval, double maxval, long seed) {
    super(tf);
    this.minval = minval;
    this.maxval = maxval;
    this.seed = seed;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Operand<TInt64> dims, Class<T> type) {
    Operand<T> distOp;
    if (TIntegral.class.isAssignableFrom(type)) {
      RandomUniformInt.Options options = RandomUniformInt.seed(this.seed);
      distOp =
          tf.random.randomUniformInt(
              dims,
              tf.dtypes.cast(tf.constant(this.minval), type),
              tf.dtypes.cast(tf.constant(this.maxval), type),
              options);
    } else {
      long[] seeds = {seed, 0};
      distOp = tf.random.statelessRandomUniform(dims, tf.constant(seeds), type);
      if (this.minval == 0) {
        if (this.maxval != 1.0) {
          distOp = tf.math.mul(distOp, tf.dtypes.cast(tf.constant(this.maxval), type));
        }
      } else {
        distOp = tf.math.mul(distOp, tf.dtypes.cast(tf.constant(this.maxval - this.minval), type));
        distOp = tf.math.add(distOp, tf.dtypes.cast(tf.constant(this.minval), type));
      }
    }
    return distOp;
  }
}
