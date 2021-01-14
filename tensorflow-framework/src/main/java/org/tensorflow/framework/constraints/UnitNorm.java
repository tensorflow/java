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
 * Constrains the weights to have unit norm.
 *
 * @param <T> the data type for the weights
 */
public class UnitNorm<T extends TNumber> extends Constraint<T> {
  public static final int AXIS_DEFAULT = 0;

  /** integer, axis along which to calculate weight norms. */
  private final int[] axes;

  /**
   * Create a UnitNorm Constraint with the axis set to {@link #AXIS_DEFAULT}
   *
   * @param tf the TensorFlow Ops
   */
  public UnitNorm(Ops tf) {
    this(tf, AXIS_DEFAULT);
  }

  /**
   * Create a UnitNorm Constraint
   *
   * @param tf the TensorFlow Ops
   * @param axis axis along which to calculate weight norms.
   */
  public UnitNorm(Ops tf, int axis) {
    this(tf, new int[] {axis});
  }

  /**
   * Create a UnitNorm Constraint
   *
   * @param tf the TensorFlow Ops
   * @param axes axes along which to calculate weight norms.
   */
  public UnitNorm(Ops tf, int[] axes) {
    super(tf);
    this.axes = axes;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Operand<T> weights) {
    Class<T> type = weights.type();

    Ops tf = getTF();
    return tf.math.div(
        weights,
        tf.math.add(
            cast(tf, tf.constant(EPSILON), type),
            sqrt(
                tf.reduceSum(
                    tf.math.square(weights),
                    tf.constant(getAxes()),
                    ReduceSum.keepDims(Boolean.TRUE)))));
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
