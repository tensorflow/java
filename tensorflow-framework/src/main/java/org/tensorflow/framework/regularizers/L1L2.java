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
 *
 * <p>The difference between this class and the {@link L1_L2} is use of the default regularization
 * penalty {@link #DEFAULT_REGULARIZATION_PENALTY}, whereas {@link L1L2} defaults to 0.
 */
public class L1L2 extends Regularizer {

  private final float l1;
  private final float l2;

  /**
   * Creates an L1L2 regularizer with no l1 or l2 penalty with zero penalty
   *
   * @param tf the TensorFlow Ops
   */
  public L1L2(Ops tf) {
    this(tf, null, null);
  }

  /**
   * Creates an L1L2 regularizer
   *
   * @param tf the TensorFlow Ops
   * @param l1 L1 regularization factor, if null it is set to 0.
   * @param l2 L2 regularization factor, if null it is set to 0.
   * @throws IllegalArgumentException if the l1 or l2 regularization factor is {@link Float#isNaN}
   *     of {@link Float#isInfinite}
   */
  public L1L2(Ops tf, Float l1, Float l2) {
    super(tf);
    if (l1 != null) {
      if (l1.isNaN() || l1.isInfinite()) {
        throw new IllegalArgumentException(
            String.format(
                "L1 Value: %f is not a valid regularization penalty number, a positive/negative infinity or NaN is not a property value",
                l1));
      }
      this.l1 = l1;
    } else {
      this.l1 = 0f;
    }
    if (l2 != null) {
      if (l2.isNaN() || l2.isInfinite()) {
        throw new IllegalArgumentException(
            String.format(
                "L2 Value: %f is not a valid regularization penalty number, a positive/negative infinity or NaN is not a property value",
                l2));
      }
      this.l2 = l2;
    } else {
      this.l2 = 0f;
    }
  }

  /**
   * Creates an L1L2 instance using {@link #DEFAULT_REGULARIZATION_PENALTY} for the l1 and l2
   * values.
   *
   * @param tf the TensorFlow Ops
   * @return a L1L2 instance using {@link #DEFAULT_REGULARIZATION_PENALTY} for the l1 and l2 values.
   */
  public static L1L2 l1_l2(Ops tf) {
    return new L1L2(tf, DEFAULT_REGULARIZATION_PENALTY, DEFAULT_REGULARIZATION_PENALTY);
  }

  /** {@inheritDoc} */
  @Override
  public <R extends TNumber> Operand<R> call(Operand<R> input) {
    Ops tf = getTF();
    if (this.getL1() == 0f && this.getL2() == 0f) {
      return tf.dtypes.cast(tf.constant(0), input.type());
    }
    Operand<R> regularization = tf.dtypes.cast(tf.constant(0), input.type());

    if (this.getL1() != 0.f) {
      Operand<R> l1Op = tf.dtypes.cast(tf.constant(this.getL1()), input.type());
      Operand<R> abs = tf.math.abs(input);
      Operand<R> reduceSum = tf.reduceSum(abs, LossesHelper.allAxes(tf, input));
      regularization = tf.math.add(regularization, tf.math.mul(l1Op, reduceSum));
    }

    if (this.getL2() != 0.f) {
      Operand<R> l2Op = tf.dtypes.cast(tf.constant(this.getL2()), input.type());
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
