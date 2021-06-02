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

/** Constrains the weights to have unit norm. */
public class UnitNorm extends AbstractConstraint {
  public static final int AXIS_DEFAULT = 0;

  /** integer, axis along which to calculate weight norms. */
  private final int[] axes;

  /** Create a UnitNorm AbstractConstraint with the axis set to {@link #AXIS_DEFAULT} */
  public UnitNorm() {
    this(AXIS_DEFAULT);
  }

  /**
   * Create a UnitNorm AbstractConstraint
   *
   * @param axis axis along which to calculate weight norms.
   */
  public UnitNorm(int axis) {
    this(new int[] {axis});
  }

  /**
   * Create a UnitNorm AbstractConstraint
   *
   * @param axes axes along which to calculate weight norms.
   */
  public UnitNorm(int[] axes) {
    super();
    this.axes = axes;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(Ops tf, Operand<T> weights) {
    Class<T> type = weights.type();

    return tf.math.div(
        weights, tf.math.add(cast(tf, tf.constant(EPSILON), type), norm(tf, weights, getAxes())));
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
