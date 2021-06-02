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

/**
 * A regularizer that applies an L1 or Lasso(least absolute shrinkage and selection operator)
 * Regression, regularization penalty.
 *
 * <p>The L1 regularization penalty is computed as: <code>loss = l1 * reduceSum(abs(x))</code>
 */
public class L1 extends L1L2 {

  /**
   * Create a regularizer that applies an L1 regularization penalty of {@link
   * #DEFAULT_REGULARIZATION_PENALTY} and a name based on the class name.
   */
  public L1() {
    this(null, DEFAULT_REGULARIZATION_PENALTY);
  }

  /**
   * Create a regularizer that applies an L1 regularization penalty of {@link
   * #DEFAULT_REGULARIZATION_PENALTY}
   *
   * @param name the name for this AbstractRegularizer
   */
  public L1(String name) {
    this(name, DEFAULT_REGULARIZATION_PENALTY);
  }

  /**
   * Create a regularizer that applies an L1 regularization penalty and a name based on the class
   * name.
   *
   * @param l1 the L1 regularization penalty
   * @throws IllegalArgumentException if the l1 regularization factor is NaN or is infinite.
   */
  public L1(float l1) {
    this(null, l1);
  }

  /**
   * Create a regularizer that applies an L1 regularization penalty
   *
   * @param name the name for this AbstractRegularizer
   * @param l1 the L1 regularization penalty
   * @throws IllegalArgumentException if the l1 regularization factor is NaN or is infinite.
   */
  public L1(String name, float l1) {
    super(name, l1, 0f);
  }
}
