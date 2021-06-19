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
package org.tensorflow.framework.metrics;

import org.tensorflow.framework.metrics.impl.Reduce;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the (weighted) sum of the given values.
 *
 * <p>For example, if values is {@code [1, 3, 5, 7]} then the sum is {@code 16}. If the weights were
 * specified as {@code [1, 1, 0, 0]}, then the sum would be {@code 4.}
 *
 * <p>This metric creates one variable, {@code total}, that is used to compute the sum of values.
 * This is ultimately returned as sum.
 *
 * <p>If sample_weight is None, weights default to 1. Use sample_weight of 0 to mask values.
 */
public class Sum<T extends TNumber> extends Reduce<T> {

  /**
   * Creates a Sum metric with a name of {@link Class#getSimpleName()}
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public Sum(long seed, Class<T> type) {
    super(null, MetricReduction.SUM, seed, type);
  }

  /**
   * Creates a Sum metric.
   *
   * @param name the name of the metric instance. If null, defaults to {@link Class#getSimpleName()}
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public Sum(String name, long seed, Class<T> type) {
    super(name, MetricReduction.SUM, seed, type);
  }
}
