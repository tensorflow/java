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
package org.tensorflow.framework.regularizers;

import static org.tensorflow.framework.utils.CastHelper.cast;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossesHelper;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * A regularizer that applies both L1 and L2 regularization penalties.
 *
 * <p>The L1 regularization penalty is computed as:
 *
 * <pre>loss = l1 * reduceSum(abs(x))</pre>
 *
 * <p>The L2 regularization penalty is computed as
 *
 * <pre>loss = l2 * reduceSum(square(x))</pre>
 */
public class L1L2 extends AbstractRegularizer {

  private final float l1;
  private final float l2;

  /** Creates an L1L2 regularizer with no l1 or l2 penalty with zero penalty */
  public L1L2() {
    this(DEFAULT_REGULARIZATION_PENALTY, DEFAULT_REGULARIZATION_PENALTY);
  }

  /**
   * Creates an L1L2 regularizer
   *
   * @param l1 L1 regularization factor, if null it is set to 0.
   * @param l2 L2 regularization factor, if null it is set to 0.
   * @throws IllegalArgumentException if the l1 or l2 regularization factor is {@link Float#isNaN}
   *     of {@link Float#isInfinite}
   */
  public L1L2(float l1, float l2) {
    this(null, l1, l2);
  }

  /**
   * Creates an L1L2 regularizer
   *
   * @param name the name for this regularizer, if null the class name will be used.
   * @param l1 L1 regularization factor, if null it is set to 0.
   * @param l2 L2 regularization factor, if null it is set to 0.
   * @throws IllegalArgumentException if the l1 or l2 regularization factor is {@link Float#isNaN}
   *     of {@link Float#isInfinite}
   */
  public L1L2(String name, float l1, float l2) {
    super(name);
    if (Float.isNaN(l1) || Float.isInfinite(l1)) {
      throw new IllegalArgumentException(
          String.format(
              "L1 Value: %f is not a valid regularization penalty number, a positive/negative infinity or NaN is not a property value",
              l1));
    }
    this.l1 = l1;

    if (Float.isNaN(l2) || Float.isInfinite(l2)) {
      throw new IllegalArgumentException(
          String.format(
              "L2 Value: %f is not a valid regularization penalty number, a positive/negative infinity or NaN is not a property value",
              l2));
    }
    this.l2 = l2;
  }

  /** {@inheritDoc} */
  @Override
  public <R extends TNumber> Operand<R> call(Ops tf, Operand<R> input) {
    if (this.getL1() == 0f && this.getL2() == 0f) {
      return cast(tf, tf.constant(0), input.type());
    }
    Operand<R> regularization = cast(tf, tf.constant(0), input.type());

    if (this.getL1() != 0.f) {
      Operand<R> l1Op = cast(tf, tf.constant(this.getL1()), input.type());
      Operand<R> abs = tf.math.abs(input);
      Operand<R> reduceSum = tf.reduceSum(abs, LossesHelper.allAxes(tf, input));
      regularization = tf.math.add(regularization, tf.math.mul(l1Op, reduceSum));
    }

    if (this.getL2() != 0.f) {
      Operand<R> l2Op = cast(tf, tf.constant(this.getL2()), input.type());
      Operand<R> sqr = tf.math.square(input);
      Operand<R> reduceSum = tf.reduceSum(sqr, LossesHelper.allAxes(tf, input));
      regularization = tf.math.add(regularization, tf.math.mul(l2Op, reduceSum));
    }

    return regularization;
  }

  /**
   * Gets the L1 regularization factor
   *
   * @return the L1 regularization factor
   */
  public float getL1() {
    return l1;
  }

  /**
   * Gets the L2 regularization factor
   *
   * @return the L2 regularization factor
   */
  public float getL2() {
    return l2;
  }
}
