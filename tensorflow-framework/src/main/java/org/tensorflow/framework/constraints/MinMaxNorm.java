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

import static org.tensorflow.framework.utils.CastHelper.cast;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/** Constrains the weights to have the norm between a lower bound and an upper bound. */
public class MinMaxNorm extends AbstractConstraint {
  public static final double MIN_VALUE_DEFAULT = 0.0;
  public static final double MAX_VALUE_DEFAULT = 1.0;
  public static final double RATE_DEFAULT = 1.0;
  public static final int AXIS_DEFAULT = 0;

  /** the minimum norm for the incoming weights. */
  private final double minValue;
  /** the maximum norm for the incoming weights. */
  private final double maxValue;

  /**
   * rate for enforcing the constraint: weights will be rescaled to yield (1 - rate) * norm + rate *
   * norm.clip(min_value, max_value). Effectively, this means that rate=1.0 stands for strict
   * enforcement of the constraint, while rate<1.0 means that weights will be rescaled at each step
   * to slowly move towards a value inside the desired interval.
   */
  private final double rate;

  /** axis along which to calculate weight norms. */
  private final int[] axes;

  /**
   * Create a MinMaxNorm constraint using {@link #MIN_VALUE_DEFAULT} for the min value, {@link
   * #MAX_VALUE_DEFAULT} for the max value, {@link #RATE_DEFAULT} for the rate and {@link
   * #AXIS_DEFAULT} for the axis
   */
  public MinMaxNorm() {
    this(MIN_VALUE_DEFAULT, MAX_VALUE_DEFAULT, RATE_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Create a MinMaxNorm constraint using {@link #RATE_DEFAULT} for the rate and {@link
   * #AXIS_DEFAULT} for the axis
   *
   * @param minValue the minimum norm for the incoming weights.
   * @param maxValue the maximum norm for the incoming weights.
   */
  public MinMaxNorm(double minValue, double maxValue) {
    this(minValue, maxValue, RATE_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Create a MinMaxNorm constraint
   *
   * @param minValue the minimum norm for the incoming weights.
   * @param maxValue the maximum norm for the incoming weights.
   * @param rate the rate for enforcing the constraint.
   * @param axis integer, axis along which to calculate weight norms.
   */
  public MinMaxNorm(double minValue, double maxValue, double rate, int axis) {
    this(minValue, maxValue, rate, new int[] {axis});
  }
  /**
   * Create a MinMaxNorm constraint
   *
   * @param minValue the minimum norm for the incoming weights.
   * @param maxValue the maximum norm for the incoming weights.
   * @param rate the rate for enforcing the constraint.
   * @param axes integer, axis along which to calculate weight norms.
   */
  public MinMaxNorm(double minValue, double maxValue, double rate, int[] axes) {
    super();
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.rate = rate;
    this.axes = axes;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> weights) {
    Class<T> type = weights.type();
    Operand<T> norms = norm(tf, weights, getAxes());
    Operand<T> desired =
        tf.math.add(
            tf.math.mul(
                tf.dtypes.cast(tf.constant(this.getRate()), type),
                clip(tf, norms, this.getMinValue(), this.getMaxValue())),
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
  public double getMinValue() {
    return minValue;
  }

  /**
   * Gets the maxValue
   *
   * @return the maxValue
   */
  public double getMaxValue() {
    return maxValue;
  }

  /**
   * Gets the rate
   *
   * @return the rate
   */
  public double getRate() {
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
