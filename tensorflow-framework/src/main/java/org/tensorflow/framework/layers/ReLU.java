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
package org.tensorflow.framework.layers;

import java.util.ArrayList;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TType;

/**
 * Rectified Linear Unit activation layer
 *
 * <p>With default values, it returns element-wise {@code max(x, 0)}
 *
 * <p>Otherwise, it follows:
 *
 * <pre>{@code
 * f(x) = max_value if x >= max_value
 *  f(x) = x if threshold <= x < max_value
 *  f(x) = negative_slope * (x - threshold) otherwise
 * }</pre>
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class ReLU<T extends TFloating> extends Layer<T> {
  /** The default Maximum activation value. */
  public static float DEFAULT_MAX_VALUE = Float.NaN;
  /** The default negative slope */
  public static float DEFAULT_NEGATIVE_SLOPE = 0;
  /** The default threshold value for thresholded activation. */
  public static float DEFAULT_THRESHOLD = 0;

  private final float maxValue;
  private final float negativeSlope;
  private final float threshold;

  /**
   * Creates a ReLU Layer with a unique name generated based on * {@link Class#getSimpleName()} and
   * using {@link #DEFAULT_NEGATIVE_SLOPE} for the negative slope, {@link #DEFAULT_MAX_VALUE} as the
   * maximum value and {@link #DEFAULT_THRESHOLD} as the threshold.
   *
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public ReLU(Class<T> type, Options options) {
    this(null, DEFAULT_NEGATIVE_SLOPE, DEFAULT_MAX_VALUE, DEFAULT_THRESHOLD, type, options);
  }

  /**
   * Creates a ReLU Layer using {@link #DEFAULT_NEGATIVE_SLOPE} for the negative slope, {@link
   * #DEFAULT_MAX_VALUE} as the maximum value and {@link #DEFAULT_THRESHOLD} as the threshold.
   *
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param type the data type for the layer's weights and computation.
   */
  public ReLU(String name, Class<T> type) {
    this(name, DEFAULT_NEGATIVE_SLOPE, DEFAULT_MAX_VALUE, DEFAULT_THRESHOLD, type, null);
  }

  /**
   * Creates a ReLU Layer using {@link #DEFAULT_NEGATIVE_SLOPE} for the negative slope, {@link
   * #DEFAULT_MAX_VALUE} as the maximum value and {@link #DEFAULT_THRESHOLD} as the threshold.
   *
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public ReLU(String name, Class<T> type, Options options) {
    this(name, DEFAULT_NEGATIVE_SLOPE, DEFAULT_MAX_VALUE, DEFAULT_THRESHOLD, type, options);
  }

  /**
   * Creates a ReLU Layer with a unique name generated based on * {@link Class#getSimpleName()},
   * using {@link #DEFAULT_MAX_VALUE} as the maximum value and {@link #DEFAULT_THRESHOLD} as the
   * threshold.
   *
   * @param negativeSlope Negative slope coefficient. Must be &gt;= 0.
   * @param type the data type for the layer's weights and computation.
   * @throws IllegalArgumentException if negativeSlope is &lt; 0
   */
  public ReLU(float negativeSlope, Class<T> type) {
    this(null, negativeSlope, DEFAULT_MAX_VALUE, DEFAULT_THRESHOLD, type, null);
  }

  /**
   * Creates a ReLU Layer with a unique name generated based on * {@link Class#getSimpleName()},
   * using {@link #DEFAULT_MAX_VALUE} as the maximum value and {@link #DEFAULT_THRESHOLD} as the
   * threshold.
   *
   * @param negativeSlope Negative slope coefficient. Must be &gt;= 0.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   * @throws IllegalArgumentException if negativeSlope is &lt; 0
   */
  public ReLU(float negativeSlope, Class<T> type, Options options) {
    this(null, negativeSlope, DEFAULT_MAX_VALUE, DEFAULT_THRESHOLD, type, options);
  }

  /**
   * Creates a ReLU Layer using a unique name will be generated based on * {@link
   * Class#getSimpleName()}.
   *
   * @param negativeSlope Negative slope coefficient. Must be &gt;= 0.
   * @param maxValue Maximum activation value. Must be &gt;= 0.
   * @param threshold Threshold value for thresholded activation.
   * @param type the data type for the layer's weights and computation.
   * @throws IllegalArgumentException if maxValue or negativeSlope is &lt; 0
   */
  public ReLU(float negativeSlope, float maxValue, float threshold, Class<T> type) {
    this(null, negativeSlope, maxValue, threshold, type, null);
  }

  /**
   * Creates a ReLU Layer using a unique name will be generated based on * {@link
   * Class#getSimpleName()}.
   *
   * @param negativeSlope Negative slope coefficient. Must be &gt;= 0.
   * @param maxValue Maximum activation value. Must be &gt;= 0.
   * @param threshold Threshold value for thresholded activation.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   * @throws IllegalArgumentException if maxValue or negativeSlope is &lt; 0
   */
  public ReLU(
      float negativeSlope, float maxValue, float threshold, Class<T> type, Options options) {
    this(null, negativeSlope, maxValue, threshold, type, options);
  }

  /**
   * Creates a ReLU Layer
   *
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param negativeSlope Negative slope coefficient. Must be &gt;= 0.
   * @param maxValue Maximum activation value. Must be &gt;= 0.
   * @param threshold Threshold value for thresholded activation.
   * @param type the data type for the layer's weights and computation.
   * @throws IllegalArgumentException if maxValue or negativeSlope is &lt; 0
   */
  public ReLU(String name, float negativeSlope, float maxValue, float threshold, Class<T> type) {
    this(name, negativeSlope, maxValue, threshold, type, null);
  }
  /**
   * Creates a ReLU Layer
   *
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param negativeSlope Negative slope coefficient. Must be &gt;= 0.
   * @param maxValue Maximum activation value. Must be &gt;= 0.
   * @param threshold Threshold value for thresholded activation.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   * @throws IllegalArgumentException if maxValue or negativeSlope is &lt; 0
   */
  public ReLU(
      String name,
      float negativeSlope,
      float maxValue,
      float threshold,
      Class<T> type,
      Options options) {
    super(name, true, type, options);
    if (!Float.isNaN(maxValue) && maxValue < 0) {
      throw new IllegalArgumentException("maxValue must be >= 0, got " + maxValue);
    }
    if (negativeSlope < 0) {
      throw new IllegalArgumentException("negativeSlope must be >= 0, got " + negativeSlope);
    }

    this.maxValue = maxValue;
    this.negativeSlope = negativeSlope;
    this.threshold = threshold;
    setSupportsMasking(true);
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TType> List<Operand<U>> call(
      Ops tf,
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {

    init(tf);
    org.tensorflow.framework.activations.ReLU<T> reLU =
        new org.tensorflow.framework.activations.ReLU<>(negativeSlope, maxValue, threshold);
    List<Operand<T>> tInputs = convertList(inputs);
    List<Operand<T>> results = new ArrayList<>();
    tInputs.forEach(input -> results.add(reLU.call(getTF(), input)));
    return callPostProcess(convertTo(results, resultType), training);
  }
}
