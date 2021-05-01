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
 * @param <R> the data type for the weights
 */
public class L1L2<R extends TNumber> extends Regularizer<R> {

  private final Float l1;
  private final Float l2;

  /**
   * Creates an L1L2 regularizer with no l1 or l2 penalty with default penal
   *
   * @param tf the TensorFlow Ops
   * @param type the data type for the weights
   */
  public L1L2(Ops tf, Class<R> type) {
    this(tf, null, null, type);
  }

  /**
   * Creates an L1L2 regularizer
   *
   * @param tf the TensorFlow Ops
   * @param l1 L1 regularization factor, if null it is set to 0.
   * @param l2 L2 regularization factor, if null it is set to 0.
   * @param type the data type for the weights
   * @throws IllegalArgumentException if the l1 or l2 regularization factor is {@link }NaN or is
   *     infinite.
   */
  public L1L2(Ops tf, Float l1, Float l2, Class<R> type) {
    super(tf, type);
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

  /** {@inheritDoc} */
  @Override
  public Operand<R> call(Operand<R> input) {
    Ops tf = getTF();
    if (this.getL1() == null && this.getL2() == null) {
      return tf.dtypes.cast(tf.constant(0), input.type());
    }
    Operand<R> regularization = tf.dtypes.cast(tf.constant(0), input.type());

    if (this.getL1() != null && this.getL1() != 0.f) {
      Operand<R> l1Op = tf.dtypes.cast(tf.constant(this.getL1()), input.type());
      Operand<R> abs = tf.math.abs(input);
      Operand<R> reduceSum = tf.reduceSum(abs, LossesHelper.allAxes(tf, input));
      regularization = tf.math.add(regularization, tf.math.mul(l1Op, reduceSum));
    }

    if (this.getL2() != null && this.getL2() != 0.f) {
      Operand<R> l2Op = tf.dtypes.cast(tf.constant(this.getL2()), input.type());
      Operand<R> sqr = tf.math.abs(input);
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
  public Float getL1() {
    return l1;
  }

  /**
   * Gets the L2 regularization factor
   *
   * @return the L2 regularization factor
   */
  public Float getL2() {
    return l2;
  }
}
