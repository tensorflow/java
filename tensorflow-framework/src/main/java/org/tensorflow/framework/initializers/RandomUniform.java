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
import org.tensorflow.op.random.RandomUniformInt;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Initializer that generates tensors with a uniform distribution.
 * <p>Examples:
 *
 * <pre>
 *      RandomUniform&lt;TFloat32, TFloat32&gt; initializer =
 *              new org.tensorflow.framework.initializers.RandomUniform&lt;&gt;(tf);
 *      Operand&lt;TFloat32&gt; values =
 *              initializer.call(tf.constant(Shape.of(2,2)), TFloat32.DTYPE);
 * </pre>
 *
 * @param <T> The TType for the call operation
 * @param <U> The TNumber for the call operation
 * */
public class RandomUniform<T extends TType, U extends TNumber> extends BaseInitializer<T> {

  public static final double MINVAL_DEFAULT = 0.05;
  public static final double MAXVAL_DEFAULT = 0.05;

  private final Double minval;
  private final Double maxval;
  private final Long seed;

  /**
   * Creates a RandomUniform initializer
   *
   * @param tf the TensorFlow Ops
   */
  public RandomUniform(Ops tf) {
    this(tf, MINVAL_DEFAULT, MAXVAL_DEFAULT, null);
  }

  /**
   * Creates a RandomUniform initializer
   *
   * @param tf the TensorFlow Ops
   * @param minval Lower bound of the range of random values to generate (inclusive).
   * @param maxval Upper bound of the range of random values to generate (exclusive).
   */
  public RandomUniform(Ops tf, double minval, double maxval) {
    this(tf, minval, maxval, null);
  }

  /**
   * Creates a RandomUniform initializer
   *
   * @param tf the TensorFlow Ops
   * @param minval Lower bound of the range of random values to generate (inclusive).
   * @param maxval Upper bound of the range of random values to generate (exclusive).
   * @param seed Used to create random seeds.
   */
  public RandomUniform(Ops tf, double minval, double maxval, Long seed) {
    super(tf);
    this.minval = minval;
    this.maxval = maxval;
    this.seed = seed;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Operand<TInt64> dims, DataType<T> dtype) {
    if (!dtype.isNumeric())
      throw new IllegalArgumentException("The data type must be numeric. Found : " + dtype.name());
    @SuppressWarnings("unchecked")
    DataType<U> numdType = (DataType<U>) dtype;
    Operand<U> distOp;

    if (dtype.isInteger()) {
      RandomUniformInt.Options options = RandomUniformInt.seed(this.seed);
      distOp =
          tf.random.randomUniformInt(
              dims,
              tf.dtypes.cast(tf.constant(this.minval), numdType),
              tf.dtypes.cast(tf.constant(this.maxval), numdType),
              options);
      @SuppressWarnings("unchecked")
      Operand<T> distOpT = (Operand<T>) distOp;
      return distOpT;
    } else {
      long lseed = this.seed == null ? 0L : this.seed;
      long[] seeds = {lseed, 0L};

      distOp = tf.random.statelessRandomUniform(dims, tf.constant(seeds), numdType);
      @SuppressWarnings("unchecked")
      Operand<T> distOpT = (Operand<T>) distOp;
      if (this.minval == 0) {
        if (this.maxval != 1.0) {
          distOpT = tf.math.mul(distOpT, tf.dtypes.cast(tf.constant(this.maxval), dtype));
        }
      } else {
        distOpT =
            tf.math.mul(distOpT, tf.dtypes.cast(tf.constant(this.maxval - this.minval), dtype));
        distOpT = tf.math.add(distOpT, tf.dtypes.cast(tf.constant(this.minval), dtype));
      }
      return distOpT;
    }
  }
}
