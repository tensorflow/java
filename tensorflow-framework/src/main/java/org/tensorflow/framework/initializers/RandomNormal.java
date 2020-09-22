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

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

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
 *              initializer.call(tf.constant(Shape.of(2,2)), TFloat32.DTYPE);
 * </pre>
 *
 * @param <T> The TType for the call operation
 * @param <U> The TNumber for the call operation
 */
public class RandomNormal<T extends TType, U extends TNumber> extends BaseInitializer<T> {
  public static final double MEAN_DEFAULT = 0.0;
  public static final double STDDEV_DEFAULT = 1.0;

  private final double mean;
  private final double stddev;
  private final Long seed;

  /**
   * creates the RandomUniform initializer
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and dtype.
   */
  public RandomNormal(Ops tf, Long seed) {
    this(tf, MEAN_DEFAULT, STDDEV_DEFAULT, seed);
  }

  /**
   * creates the RandomUniform initializer
   *
   * @param tf the TensorFlow Ops
   * @param mean Mean of the random values to generate. Default is 0.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and dtype.
   */
  public RandomNormal(Ops tf, double mean, Long seed) {
    this(tf, mean, STDDEV_DEFAULT, seed);
  }

  /**
   * creates the RandomUniform initializer
   *
   * @param tf the TensorFlow Ops
   * @param mean Mean of the random values to generate. Default is 0.
   * @param stddev Standard deviation of the random values to generate. Default is 1.0.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and dtype.
   */
  public RandomNormal(Ops tf, double mean, double stddev, Long seed) {
    super(tf);
    this.mean = mean;
    this.stddev = stddev;
    this.seed = seed;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Operand<TInt64> dims, DataType<T> dtype) {
    if (!dtype.isNumeric())
      throw new IllegalArgumentException("The data type must be numeric. Found : " + dtype.name());
    long lseed = this.seed == null ? 0L : this.seed;
    long[] seeds = {lseed, 0L};
    @SuppressWarnings("unchecked")
    DataType<U> numdType = (DataType<U>) dtype;
    @SuppressWarnings("unchecked")
    Operand<T> distOp =
        (Operand<T>) tf.random.statelessRandomNormal(dims, tf.constant(seeds), numdType);
    Operand<T> op = tf.math.mul(distOp, tf.dtypes.cast(tf.constant(this.stddev), dtype));
    return tf.math.add(op, tf.dtypes.cast(tf.constant(mean), dtype));
  }
}
