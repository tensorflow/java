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

import org.tensorflow.framework.metrics.impl.ConfusionMatrixConditionCount;
import org.tensorflow.framework.metrics.impl.ConfusionMatrixEnum;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Metric that calculates the number of false positives.
 *
 * <p>If <code>sampleWeights</code> is given, calculates the sum of the weights of false positives.
 * This metric creates one local variable, <code>accumulator</code> that is used to keep track of
 * the number of false positives.
 *
 * <p>If <code>sampleWeights</code> is <code>null</code>, weights default to 1. Use <code>
 * sampleWeights</code> of 0 to mask values.
 *
 * @param <T> The data type for the metric result
 */
public class FalsePositives<T extends TNumber> extends ConfusionMatrixConditionCount<T> {

  /**
   * Creates a FalsePositives metric, using {@link Class#getSimpleName()} for the metric name and a
   * default threshold of {@link #DEFAULT_THRESHOLD}.
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public FalsePositives(Ops tf, long seed, Class<T> type) {
    this(tf, null, DEFAULT_THRESHOLD, seed, type);
  }

  /**
   * Creates a FalsePositives metric, using {@link Class#getSimpleName()} for the metric name
   *
   * @param tf the TensorFlow Ops
   * @param threshold a threshold value in the range <code>[0, 1]</code>. A threshold is compared
   *     with prediction values to determine the truth value of predictions (i.e., above the
   *     threshold is <code>true</code>, below is <code>false</code>). One metric value is generated
   *     for each threshold value
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public FalsePositives(Ops tf, float threshold, long seed, Class<T> type) {
    this(tf, null, new float[] {threshold}, seed, type);
  }

  /**
   * Creates a FalsePositives metric, using {@link Class#getSimpleName()} for the metric name
   *
   * @param tf the TensorFlow Ops
   * @param thresholds threshold values in the range <code>[0, 1]</code>. A threshold is compared
   *     with prediction values to determine the truth value of predictions (i.e., above the
   *     threshold is <code>true</code>, below is <code>false</code>). One metric value is generated
   *     for each threshold value
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public FalsePositives(Ops tf, float[] thresholds, long seed, Class<T> type) {
    this(tf, null, thresholds, seed, type);
  }

  /**
   * Creates a FalsePositives metric, using a default threshold of {@link #DEFAULT_THRESHOLD}.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public FalsePositives(Ops tf, String name, long seed, Class<T> type) {
    this(tf, name, DEFAULT_THRESHOLD, seed, type);
  }

  /**
   * Creates a FalsePositives metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param threshold a threshold value in the range <code>[0, 1]</code>. A threshold is compared
   *     with prediction values to determine the truth value of predictions (i.e., above the
   *     threshold is <code>true</code>, below is <code>false</code>). One metric value is generated
   *     for each threshold value
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public FalsePositives(Ops tf, String name, float threshold, long seed, Class<T> type) {
    this(tf, name, new float[] {threshold}, seed, type);
  }

  /**
   * Creates a FalsePositives metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param thresholds threshold values in the range <code>[0, 1]</code>. A threshold is compared
   *     with prediction values to determine the truth value of predictions (i.e., above the
   *     threshold is <code>true</code>, below is <code>false</code>). One metric value is generated
   *     for each threshold value
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public FalsePositives(Ops tf, String name, float[] thresholds, long seed, Class<T> type) {
    super(tf, name, ConfusionMatrixEnum.FALSE_POSITIVES, thresholds, seed, type);
  }
}
