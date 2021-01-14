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
 * Constrains the weights incident to each hidden unit to have a norm less than or equal to a
 * desired value.
 *
 * @param <T> the data type for the weights
 */
public class MaxNorm<T extends TNumber> extends Constraint<T> {
  public static final float MAX_VALUE_DEFAULT = 2.0f;
  public static final int AXIS_DEFAULT = 0;

  /** the maximum norm for the incoming weights. */
  private final float maxValue;
  /** integer, axis along which to calculate weight norms. */
  private final int[] axes;

  /**
   * Create a MaxNorm constraint using {@link #MAX_VALUE_DEFAULT} for the max value and {@link
   * #AXIS_DEFAULT} for the axis.
   *
   * @param tf the TensorFlow Ops
   */
  public MaxNorm(Ops tf) {
    this(tf, MAX_VALUE_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Create a MaxNorm constraint using {@link #AXIS_DEFAULT} for the axis.
   *
   * @param tf the TensorFlow Ops
   * @param maxValue the maximum norm for the incoming weights.
   */
  public MaxNorm(Ops tf, float maxValue) {
    this(tf, maxValue, AXIS_DEFAULT);
  }

  /**
   * Create a MaxNorm constraint
   *
   * @param tf the TensorFlow Ops
   * @param maxValue the maximum norm for the incoming weights.
   * @param axis axis along which to calculate weight norms.
   */
  public MaxNorm(Ops tf, float maxValue, int axis) {
    this(tf, maxValue, new int[] {axis});
  }

  /**
   * Create a MaxNorm constraint
   *
   * @param tf the TensorFlow Ops
   * @param maxValue the maximum norm for the incoming weights.
   * @param axes axes along which to calculate weight norms.
   */
  public MaxNorm(Ops tf, float maxValue, int[] axes) {
    super(tf);
    this.maxValue = maxValue;
    this.axes = axes;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Operand<T> weights) {
    Ops tf = getTF();
    Class<T> type = weights.type();
    Operand<T> norms =
        sqrt(
            tf.reduceSum(
                tf.math.square(weights), tf.constant(getAxes()), ReduceSum.keepDims(Boolean.TRUE)));
    Operand<T> desired = clip(norms, 0f, this.getMaxValue());

    return tf.math.mul(
        weights, tf.math.div(desired, tf.math.add(cast(tf, tf.constant(EPSILON), type), norms)));
  }

  /**
   * Gets the max value
   *
   * @return the maxValue
   */
  public float getMaxValue() {
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
