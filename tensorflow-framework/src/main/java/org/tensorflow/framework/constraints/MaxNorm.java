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

/**
 * Constrains the weights incident to each hidden unit to have a norm less than or equal to a
 * desired value.
 */
public class MaxNorm extends AbstractConstraint {
  public static final double MAX_VALUE_DEFAULT = 2.0;
  public static final int AXIS_DEFAULT = 0;

  /** the maximum norm for the incoming weights. */
  private final double maxValue;
  /** integer, axis along which to calculate weight norms. */
  private final int[] axes;

  /**
   * Create a MaxNorm constraint using {@link #MAX_VALUE_DEFAULT} for the max value and {@link
   * #AXIS_DEFAULT} for the axis.
   */
  public MaxNorm() {
    this(MAX_VALUE_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Create a MaxNorm constraint using {@link #AXIS_DEFAULT} for the axis.
   *
   * @param maxValue the maximum norm for the incoming weights.
   */
  public MaxNorm(double maxValue) {
    this(maxValue, AXIS_DEFAULT);
  }

  /**
   * Create a MaxNorm constraint
   *
   * @param maxValue the maximum norm for the incoming weights.
   * @param axis axis along which to calculate weight norms.
   */
  public MaxNorm(double maxValue, int axis) {
    this(maxValue, new int[] {axis});
  }

  /**
   * Create a MaxNorm constraint
   *
   * @param maxValue the maximum norm for the incoming weights.
   * @param axes axes along which to calculate weight norms.
   */
  public MaxNorm(double maxValue, int[] axes) {
    super();
    this.maxValue = maxValue;
    this.axes = axes;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> weights) {
    Class<T> type = weights.type();
    Operand<T> norms = norm(tf, weights, getAxes());
    Operand<T> desired = clip(tf, norms, 0f, this.getMaxValue());

    return tf.math.mul(
        weights, tf.math.div(desired, tf.math.add(cast(tf, tf.constant(EPSILON), type), norms)));
  }

  /**
   * Gets the max value
   *
   * @return the maxValue
   */
  public double getMaxValue() {
    return maxValue;
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
