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

import org.tensorflow.op.Ops;

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
 * <p>The difference between this class and the {@link L1L2} is use of the default regularization
 * penalty {@link #DEFAULT_REGULARIZATION_PENALTY}, whereas {@link L1L2} defaults to 0.
 */
public class L1_L2 extends L1L2 {

  /**
   * Creates a regularizer that applies an L1 and l2 regularization penalty of {@link
   * #DEFAULT_REGULARIZATION_PENALTY}
   *
   * @param tf the TensorFlow Ops
   */
  public L1_L2(Ops tf) {
    this(tf, DEFAULT_REGULARIZATION_PENALTY, DEFAULT_REGULARIZATION_PENALTY);
  }

  /**
   * Creates a regularizer that applies an L1 and l2 regularization penalty
   *
   * @param tf the TensorFlow Ops
   * @param l1 the L1 regularization penalty. If null, then l1 will be set to {@link
   *     #DEFAULT_REGULARIZATION_PENALTY}.
   * @param l2 the L2 regularization penalty. If null, then l2 will be set to {@link
   *     #DEFAULT_REGULARIZATION_PENALTY}.
   * @throws IllegalArgumentException if the l1 or l2 regularization factor is NaN or is infinite.
   */
  public L1_L2(Ops tf, Float l1, Float l2) {
    super(
        tf,
        l1 == null ? DEFAULT_REGULARIZATION_PENALTY : l1,
        l2 == null ? DEFAULT_REGULARIZATION_PENALTY : l2);
  }
}
