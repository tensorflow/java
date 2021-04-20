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
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TFloating;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Initializer that generates tensors with a normal distribution.
 *
 * <p>Examples:
 *
 * <pre>
 *     long seed = 1001l;
 *     RandomNormal&lt;TFloat32, TFloat32&gt; initializer =
 *              new org.tensorflow.framework.initializers.RandomNormal&lt;&gt;(tf, seed);
 *     Operand&lt;TFloat32&gt; values =
 *              initializer.call(tf.constant(Shape.of(2,2)), TFloat32.class);
 * </pre>
 */
public class RandomNormal extends BaseInitializer<TFloating> {

  public static final double MEAN_DEFAULT = 0.0;
  public static final double STDDEV_DEFAULT = 1.0;

  private final double mean;
  private final double stddev;
  private final long seed;

  /**
   * Creates the RandomUniform initializer using {@link #MEAN_DEFAULT} for the mean and {@link
   * #STDDEV_DEFAULT} for the standard deviation.
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   */
  public RandomNormal(Ops tf, long seed) {
    this(tf, MEAN_DEFAULT, STDDEV_DEFAULT, seed);
  }

  /**
   * Creates the RandomUniform initializer using {@link #STDDEV_DEFAULT} for the standard deviation.
   *
   * @param tf the TensorFlow Ops
   * @param mean Mean of the random values to generate.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   */
  public RandomNormal(Ops tf, double mean, long seed) {
    this(tf, mean, STDDEV_DEFAULT, seed);
  }

  /**
   * creates the RandomUniform initializer
   *
   * @param tf the TensorFlow Ops
   * @param mean Mean of the random values to generate.
   * @param stddev Standard deviation of the random values to generate.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @throws IllegalArgumentException if standard deviation is less than 0.
   */
  public RandomNormal(Ops tf, double mean, double stddev, long seed) {
    super(tf);
    if (stddev < 0) {
      throw new IllegalArgumentException(
          "Standard deviation (stddev) cannot be less than 0, got " + stddev);
    }
    this.mean = mean;
    this.stddev = stddev;
    this.seed = seed;
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TFloating> Operand<U> call(Operand<TInt64> dims, Class<U> type) {
    long[] seeds = {seed, 0};
    Operand<U> distOp = tf.random.statelessRandomNormal(dims, tf.constant(seeds), type);
    Operand<U> op = tf.math.mul(distOp, cast(tf, tf.constant(this.stddev), type));
    return tf.math.add(op, cast(tf, tf.constant(mean), distOp.type()));
  }
}
