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
package org.tensorflow.framework.constraints;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.ReduceSum;
import org.tensorflow.types.family.TNumber;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Constrains the weights to have the norm between a lower bound and an upper bound.
 *
 * @param <T> the data type for the weights
 */
public class MinMaxNorm<T extends TNumber> extends Constraint<T> {
  public static final float MIN_VALUE_DEFAULT = 0.0F;
  public static final float MAX_VALUE_DEFAULT = 1.0F;
  public static final float RATE_DEFAULT = 1.0F;
  public static final int AXIS_DEFAULT = 0;

  /** the minimum norm for the incoming weights. */
  private final float minValue;
  /** the maximum norm for the incoming weights. */
  private final float maxValue;

  /**
   * rate for enforcing the constraint: weights will be rescaled to yield (1 - rate) * norm + rate *
   * norm.clip(min_value, max_value). Effectively, this means that rate=1.0 stands for strict
   * enforcement of the constraint, while rate<1.0 means that weights will be rescaled at each step
   * to slowly move towards a value inside the desired interval.
   */
  private final float rate;

  /** axis along which to calculate weight norms. */
  private final int[] axes;

  /**
   * Create a MaxNorm constraint using {@link #MIN_VALUE_DEFAULT} for the min value, {@link
   * #MAX_VALUE_DEFAULT} for the max value, {@link #RATE_DEFAULT} for the rate and {@link
   * #AXIS_DEFAULT} for the axis
   *
   * @param tf the TensorFlow Ops
   */
  public MinMaxNorm(Ops tf) {
    this(tf, MIN_VALUE_DEFAULT, MAX_VALUE_DEFAULT, RATE_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Create a MaxNorm constraint using {@link #RATE_DEFAULT} for the rate and {@link #AXIS_DEFAULT}
   * for the axis
   *
   * @param tf the TensorFlow Ops
   * @param minValue the minimum norm for the incoming weights.
   * @param maxValue the maximum norm for the incoming weights.
   */
  public MinMaxNorm(Ops tf, float minValue, float maxValue) {
    this(tf, minValue, maxValue, RATE_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Create a MaxNorm constraint
   *
   * @param tf the TensorFlow Ops
   * @param minValue the minimum norm for the incoming weights.
   * @param maxValue the maximum norm for the incoming weights.
   * @param rate the rate for enforcing the constraint.
   * @param axis integer, axis along which to calculate weight norms.
   */
  public MinMaxNorm(Ops tf, float minValue, float maxValue, float rate, int axis) {
    this(tf, minValue, maxValue, rate, new int[] {axis});
  }
  /**
   * Create a MaxNorm constraint
   *
   * @param tf the TensorFlow Ops
   * @param minValue the minimum norm for the incoming weights.
   * @param maxValue the maximum norm for the incoming weights.
   * @param rate the rate for enforcing the constraint.
   * @param axes integer, axis along which to calculate weight norms.
   */
  public MinMaxNorm(Ops tf, float minValue, float maxValue, float rate, int[] axes) {
    super(tf);
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.rate = rate;
    this.axes = axes;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Operand<T> weights) {
    Class<T> type = weights.type();
    Ops tf = getTF();
    Operand<T> norms =
        sqrt(
            tf.reduceSum(
                tf.math.square(weights), tf.constant(getAxes()), ReduceSum.keepDims(Boolean.TRUE)));
    Operand<T> desired =
        tf.math.add(
            tf.math.mul(
                tf.dtypes.cast(tf.constant(this.getRate()), type),
                clip(norms, this.getMinValue(), this.getMaxValue())),
            tf.math.mul(
                tf.math.sub(
                    tf.dtypes.cast(tf.constant(1), type),
                    tf.dtypes.cast(tf.constant(this.getRate()), type)),
                norms));

    return tf.math.mul(
        weights, tf.math.div(desired, tf.math.add(cast(tf, tf.constant(EPSILON), type), norms)));
  }

  /**
   * Gets the minValue
   *
   * @return the minValue
   */
  public float getMinValue() {
    return minValue;
  }

  /**
   * Gets the maxValue
   *
   * @return the maxValue
   */
  public float getMaxValue() {
    return maxValue;
  }

  /**
   * Gets the rate
   *
   * @return the rate
   */
  public float getRate() {
    return rate;
  }

  /**
   * Gets the axes
   *
   * @return the axes
   */
  public int[] getAxes() {
    return axes;
  }
}
