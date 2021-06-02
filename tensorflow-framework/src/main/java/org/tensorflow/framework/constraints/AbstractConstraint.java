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
import org.tensorflow.op.core.ReduceSum;
import org.tensorflow.types.family.TNumber;

/** Base class for Constraints. AbstractConstraint subclasses impose constraints on weight values */
public abstract class AbstractConstraint implements Constraint {

  public static final float EPSILON = 1e-7f;

  /** Creates a AbstractConstraint */
  public AbstractConstraint() {}

  /**
   * Gets the element-wise square root.
   *
   * @param tf the TensorFlow Ops
   * @param x the input Operand.
   * @return the element-wise square root.
   * @param <T> The data type for the operand and result.
   * @throws IllegalArgumentException if x is null
   */
  protected <T extends TNumber> Operand<T> sqrt(Ops tf, Operand<T> x) {
    if (x == null) throw new IllegalArgumentException("Operand x must not be null");
    Class<T> type = x.type();
    Operand<T> zero = cast(tf, tf.constant(0), type);
    Operand<T> inf = cast(tf, tf.constant(Double.POSITIVE_INFINITY), type);
    return tf.math.sqrt(tf.clipByValue(x, zero, inf));
  }

  /**
   * Gets the element-wise value clipping.
   *
   * @param tf the TensorFlow Ops
   * @param x the Operand to clip
   * @param minValue the minimum value
   * @param maxValue the maximum value
   * @return the operand with clipped values
   * @param <T> The data type for the operand and result.
   * @throws IllegalArgumentException if x is null
   */
  protected <T extends TNumber> Operand<T> clip(
      Ops tf, Operand<T> x, double minValue, double maxValue) {
    if (x == null) throw new IllegalArgumentException("Operand x must not be null");
    Class<T> type = x.type();

    double min = Math.min(minValue, maxValue);
    double max = Math.max(minValue, maxValue);

    Operand<T> minValueConstant = cast(tf, tf.constant(min), type);
    Operand<T> maxValueConstant = cast(tf, tf.constant(max), type);
    return tf.clipByValue(x, minValueConstant, maxValueConstant);
  }

  /**
   * Calculates the norm of the weights along the axes
   *
   * @param tf the TensorFlow Ops
   * @param weights the weights used to calculate the norms
   * @param axes the axes along which to calculate weight norms.
   * @param <T> the data type for the weights and the result
   * @return the norms
   * @throws IllegalArgumentException if weights is null
   */
  protected <T extends TNumber> Operand<T> norm(Ops tf, Operand<T> weights, int[] axes) {
    if (weights == null) throw new IllegalArgumentException("weights must not be null");
    return sqrt(
        tf,
        tf.reduceSum(tf.math.square(weights), tf.constant(axes), ReduceSum.keepDims(Boolean.TRUE)));
  }
}
