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

import static org.tensorflow.framework.utils.CastHelper.cast;

import java.util.ArrayList;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TType;

/**
 * Thresholded Rectified Linear Unit.
 *
 * <p>It follows::
 *
 * <pre>{@code
 * f(x) = x for x > theta
 * f(x) = 0 otherwise`
 * }</pre>
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class ThresholdedReLU<T extends TFloating> extends Layer<T> {
  /** The default Threshold location of activation. */
  public static float DEFAULT_THETA = 1.0f;

  private final float theta;

  /**
   * Creates a ThresholdedReLU Layer with a unique name generated based on * {@link
   * Class#getSimpleName()} and {@link #DEFAULT_THETA} for the theta value.
   *
   * @param type the data type for the layer's weights and computation.
   */
  public ThresholdedReLU(Class<T> type) {

    this(null, DEFAULT_THETA, type, null);
  }

  /**
   * Creates a ThresholdedReLU Layer with {@link #DEFAULT_THETA} for the theta value.
   *
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param type the data type for the layer's weights and computation.
   */
  public ThresholdedReLU(String name, Class<T> type) {
    this(name, DEFAULT_THETA, type, null);
  }

  /**
   * Creates a ThresholdedReLU Layer with a unique name generated based on * {@link
   * Class#getSimpleName()}.
   *
   * @param theta Negative slope coefficient. Must be &gt;= 0.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public ThresholdedReLU(float theta, Class<T> type, Options options) {
    this(null, theta, type, options);
  }
  /**
   * Creates a ThresholdedReLU Layer
   *
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param theta Threshold location of activation.. Must be &gt;= 0.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   * @throws IllegalArgumentException if theta is *lt; 0.
   */
  public ThresholdedReLU(String name, float theta, Class<T> type, Options options) {
    super(name, true, type, options);
    if (theta < 0) {
      throw new IllegalArgumentException("theta must be >= 0, got " + theta);
    }
    this.theta = theta;
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
    Ops ltf = init(tf);
    Operand<T> tTheta = cast(ltf, ltf.constant(theta), getType());
    List<Operand<T>> tInputs = convertList(inputs);
    List<Operand<T>> results = new ArrayList<>();
    tInputs.forEach(
        input ->
            results.add(
                ltf.math.mul(input, cast(ltf, ltf.math.greater(input, tTheta), getType()))));
    return callPostProcess(convertTo(results, resultType), training);
  }
}
