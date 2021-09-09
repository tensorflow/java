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
 * A metric that that implements a weighted mean {@link MetricReduction#WEIGHTED_MEAN }
 *
 * @param <T> The data type for the metric result
 */
public class Mean<T extends TNumber> extends Reduce<T> {
  /**
   * Creates a Reducible Metric with a metric reductions of {@link MetricReduction#SUM} and using
   * {@link Class#getSimpleName()} for the metric name.
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public Mean(long seed, Class<T> type) {
    this(null, seed, type);
  }

  /**
   * Creates a Reducible Metric with a metric reductions of {@link MetricReduction#SUM}
   *
   * @param name the name for this metric. If null, name defaults to {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the type for the variables and result
   */
  public Mean(String name, long seed, Class<T> type) {
    super(name, MetricReduction.WEIGHTED_MEAN, seed, type);
  }
}
