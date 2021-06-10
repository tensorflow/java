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
 * Exponential Linear Unit layer.
 *
 * <p>It follows::
 *
 * <pre>{@code
 * f(x) =  alpha * (exp(x) - 1.) for x < 0
 * f(x) = x for x >= 0
 * }</pre>
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class ELU<T extends TFloating> extends Layer<T> {
  /** The default Scale for the negative factor, default is no scaling */
  public static float DEFAULT_ALPHA = 1.0f;

  private final float alpha;

  /**
   * Creates a ELU Layer with a unique name generated based on * {@link Class#getSimpleName()} and
   * {@link #DEFAULT_ALPHA} for the alpha value.
   *
   * @param type the data type for the layer's weights and computation.
   */
  public ELU(Class<T> type) {
    this(null, DEFAULT_ALPHA, type, null);
  }

  /**
   * Creates a ELU Layer with {@link #DEFAULT_ALPHA} for the alpha value.
   *
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param type the data type for the layer's weights and computation.
   */
  public ELU(String name, Class<T> type) {
    this(name, DEFAULT_ALPHA, type, null);
  }

  /**
   * Creates a ELU Layer with a unique name generated based on * {@link Class#getSimpleName()}.
   *
   * @param alpha Negative slope coefficient. Must be &gt;= 0.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options
   */
  public ELU(float alpha, Class<T> type, Options options) {
    this(null, alpha, type, options);
  }
  /**
   * Creates a ELU Layer
   *
   * @param name the unique name for this layer. If null, a unique name will be generated based on
   *     {@link Class#getSimpleName()}.
   * @param alpha Negative slope coefficient. Must be &gt;= 0.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public ELU(String name, float alpha, Class<T> type, Options options) {
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

    org.tensorflow.framework.activations.ELU<T> elu =
        new org.tensorflow.framework.activations.ELU<>(alpha);
    List<Operand<T>> tInputs = convertList(inputs, getType());
    List<Operand<U>> results = new ArrayList<>();
    tInputs.forEach(tInput -> results.add(cast(getTF(), elu.call(getTF(), tInput), resultType)));
    return callPostProcess(results, training);
  }
}
