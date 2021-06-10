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
import org.tensorflow.framework.activations.ReLU;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TType;

/**
 * Leaky version of a Rectified Linear Unit.
 *
 * <p>It allows a small gradient when the unit is not active:
 *
 * <pre>{@code
 * f(x) = alpha * x if x < 0
 * f(x) = x if x >= 0
 * }</pre>
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class LeakyReLU<T extends TFloating> extends Layer<T> {

  /** The default Negative slope coefficient. */
  public static float DEFAULT_ALPHA = 0.3f;

  private final float alpha;

  /**
   * Creates a LeakyReLU Layer with a unique name generated based on * {@link Class#getSimpleName()}
   * and {@link #DEFAULT_ALPHA} for the alpha value.
   *
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public LeakyReLU(Class<T> type, Options options) {
    this(null, DEFAULT_ALPHA, type, options);
  }

  /**
   * Creates a LeakyReLU Layer with {@link #DEFAULT_ALPHA} for the alpha value.
   *
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param type the data type for the layer's weights and computation.
   */
  public LeakyReLU(String name, Class<T> type) {
    this(name, DEFAULT_ALPHA, type, null);
  }

  /**
   * Creates a LeakyReLU Layer with a unique name generated based on * {@link
   * Class#getSimpleName()}.
   *
   * @param alpha Negative slope coefficient. Must be &gt;= 0.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public LeakyReLU(float alpha, Class<T> type, Options options) {
    this(null, alpha, type, options);
  }
  /**
   * Creates a LeakyReLU Layer
   *
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param alpha Negative slope coefficient. Must be &gt;= 0.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public LeakyReLU(String name, float alpha, Class<T> type, Options options) {
    super(name, true, type, options);
    this.alpha = alpha;
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
    ReLU<T> reLU = new ReLU<>(alpha, ReLU.MAX_VALUE_DEFAULT, ReLU.THRESHOLD_DEFAULT);
    List<Operand<T>> tInputs = convertList(inputs);
    List<Operand<T>> results = new ArrayList<>();
    tInputs.forEach(input -> results.add(reLU.call(getTF(), input)));
    return callPostProcess(convertTo(results, resultType), training);
  }
}
