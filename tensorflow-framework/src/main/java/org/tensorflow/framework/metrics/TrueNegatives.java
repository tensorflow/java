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
import org.tensorflow.types.family.TNumber;

/**
 * Metric that calculates the number of true negatives.
 *
 * <p>If {@code sampleWeights} is given, calculates the sum of the weights of true negatives. This
 * metric creates one local variable, {@code accumulator} that is used to keep track of the number
 * of true negatives.
 *
 * <p>If {@code sampleWeights} is {@code null}, weights default to 1. Use {@code sampleWeights} of 0
 * to mask values.
 *
 * @param <T> The data type for the metric result
 */
public class TrueNegatives<T extends TNumber> extends ConfusionMatrixConditionCount<T> {

  /**
   * Creates a TrueNegatives metric, using {@link Class#getSimpleName()} for the metric name and a
   * default threshold of {@link #DEFAULT_THRESHOLD}.
   *
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public TrueNegatives(long seed, Class<T> type) {
    this(null, DEFAULT_THRESHOLD, seed, type);
  }

  /**
   * Creates a TrueNegatives metric, using {@link Class#getSimpleName()} for the metric name
   *
   * @param threshold a threshold value in the range {@code [0, 1]}. A threshold is compared with
   *     prediction values to determine the truth value of predictions (i.e., above the threshold is
   *     {@code true}, below is {@code false}). One metric value is generated for each threshold
   *     value
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public TrueNegatives(float threshold, long seed, Class<T> type) {
    this(null, new float[] {threshold}, seed, type);
  }

  /**
   * Creates a TrueNegatives metric, using {@link Class#getSimpleName()} for the metric name
   *
   * @param thresholds threshold values in the range {@code [0, 1]}. A threshold is compared with
   *     prediction values to determine the truth value of predictions (i.e., above the threshold is
   *     {@code true}, below is {@code false}). One metric value is generated for each threshold
   *     value
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public TrueNegatives(float[] thresholds, long seed, Class<T> type) {
    this(null, thresholds, seed, type);
  }

  /**
   * Creates a TrueNegatives metric, using a default threshold of {@link #DEFAULT_THRESHOLD}.
   *
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public TrueNegatives(String name, long seed, Class<T> type) {
    this(name, DEFAULT_THRESHOLD, seed, type);
  }

  /**
   * Creates a TrueNegatives metric
   *
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param threshold a threshold value in the range {@code [0, 1]}. A threshold is compared with
   *     prediction values to determine the truth value of predictions (i.e., above the threshold is
   *     {@code true}, below is {@code false}). One metric value is generated for each threshold
   *     value
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public TrueNegatives(String name, float threshold, long seed, Class<T> type) {
    this(name, new float[] {threshold}, seed, type);
  }

  /**
   * Creates a TrueNegatives metric
   *
   * @param name the name of the metric, if null then {@link Class#getSimpleName()} is used
   * @param thresholds threshold values in the range {@code [0, 1]}. A threshold is compared with
   *     prediction values to determine the truth value of predictions (i.e., above the threshold is
   *     {@code true}, below is {@code false}). One metric value is generated for each threshold
   *     value
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the variables
   */
  public TrueNegatives(String name, float[] thresholds, long seed, Class<T> type) {
    super(name, ConfusionMatrixEnum.TRUE_NEGATIVES, thresholds, seed, type);
  }
}
