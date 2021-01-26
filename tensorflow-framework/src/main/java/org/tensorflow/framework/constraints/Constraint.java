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
import org.tensorflow.types.family.TNumber;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Base class for Constraints. Constraint subclasses impose constraints on weight values
 *
 * @param <T> the date type for the weights
 */
public abstract class Constraint<T extends TNumber> {

  public static final float EPSILON = 1e-7f;

  private final Ops tf;

  /**
   * Creates a Constraint
   *
   * @param tf the TensorFlow Ops
   */
  public Constraint(Ops tf) {
    this.tf = tf;
  }

  /**
   * Applies the constraint against the provided weights
   *
   * @param weights the weights
   * @return the constrained weights
   */
  public abstract Operand<T> call(Operand<T> weights);

  /**
   * Gets the TensorFlow Ops
   *
   * @return the TensorFlow Ops
   */
  public Ops getTF() {
    return tf;
  }

  /**
   * Gets the element-wise square root.
   *
   * @param x the input Operand.
   * @return the element-wise square root.
   */
  protected Operand<T> sqrt(Operand<T> x) {
    Class<T> type = x.type();
    Operand<T> zero = cast(tf, tf.constant(0), type);
    Operand<T> inf = cast(tf, tf.constant(Double.POSITIVE_INFINITY), type);
    return tf.math.sqrt(tf.clipByValue(x, zero, inf));
  }

  /**
   * Gets the element-wise value clipping.
   *
   * @param x the Operand to clip
   * @param minValue the minimum value
   * @param maxValue the maximum value
   * @return the operand with clipped values
   */
  protected Operand<T> clip(Operand<T> x, double minValue, double maxValue) {
    if (x == null) throw new IllegalArgumentException("Operand x must not be null");
    Ops tf = getTF();
    Class<T> type = x.type();

    double min = Math.min(minValue, maxValue);
    double max = Math.max(minValue, maxValue);

    Operand<T> minValueConstant = cast(tf, tf.constant(min), type);
    Operand<T> maxValueConstant = cast(tf, tf.constant(max), type);
    return tf.clipByValue(x, minValueConstant, maxValueConstant);
  }
}
