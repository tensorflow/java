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
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TType;

/**
 * Wraps arbitrary Java Lambda as a Layer.
 *
 * <p>The <code>Lambda</code> layer exists so that arbitrary TensorFlow functions can be used when
 * constructing <code>Sequential</code> models. <code>Lambda</code> layers are best suited for
 * simple operations or quick experimentation.
 *
 * <p>the Java lambda function is in the form <code>x = function(input)</code>. The first argument
 * is the TensorFlow Ops, the second argument is the input Operand. For example:
 *
 * <pre>{@code
 * Lambda lambda = new Lambda((ops, input) -> ops.math.mul(ops.constant(2), input), TFloat32.class);
 * }</pre>
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class Lambda<T extends TFloating> extends Layer<T> {
  private BiFunction<Ops, Operand<T>, Operand<T>> function;

  /**
   * Creates a Lambda layer, generating a unique name based on {@link Class#getSimpleName()}
   *
   * @param type the data type for the layer's weights and computation.
   */
  public Lambda(Class<T> type) {
    this(null, null, type, null);
  }

  /**
   * Creates a Lambda layer, generating a unique name based on {@link Class#getSimpleName()}
   *
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public Lambda(Class<T> type, Options options) {
    this(null, null, type, options);
  }

  /**
   * Creates a Lambda layer
   *
   * @param name the unique name for this layer, if null, generates a unique name based on {@link
   *     Class#getSimpleName()}.
   * @param type the data type for the layer's weights and computation.
   */
  public Lambda(String name, Class<T> type) {
    this(name, null, type, null);
  }

  /**
   * Creates a Lambda layer
   *
   * @param name the unique name for this layer, if null, generates a unique name based on {@link
   *     Class#getSimpleName()}.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public Lambda(String name, Class<T> type, Options options) {
    this(name, null, type, options);
  }

  /**
   * Creates a Lambda layer, generating a unique name based on {@link Class#getSimpleName()}
   *
   * @param function The Java lambda function in the form <code>x = function(input)</code>. The
   *     first argument is the TensorFlow Ops, the second argument is the input Operand. If function
   *     is null, then the input is returned un changed.
   * @param type the data type for the layer's weights and computation.
   */
  public Lambda(BiFunction<Ops, Operand<T>, Operand<T>> function, Class<T> type) {
    this(null, function, type, null);
  }

  /**
   * Creates a Lambda layer, generating a unique name based on {@link Class#getSimpleName()}
   *
   * @param function The Java lambda function in the form <code>x = function(input)</code>. The
   *     first argument is the TensorFlow Ops, the second argument is the input Operand. If function
   *     is null, then the input is returned un changed.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options.
   */
  public Lambda(BiFunction<Ops, Operand<T>, Operand<T>> function, Class<T> type, Options options) {
    this(null, function, type, options);
  }

  /**
   * Creates a Lambda layer
   *
   * @param name the unique name for this layer, if null, generates a unique name based on {@link
   *     Class#getSimpleName()}.
   * @param function the Java lambda function in the form <code>x = function(input)</code>. The
   *     first argument is the TensorFlow Ops, the second argument is the input Operand. If function
   *     is null, then the input is returned un changed.
   * @param type the data type for the layer's weights and computation.
   */
  public Lambda(String name, BiFunction<Ops, Operand<T>, Operand<T>> function, Class<T> type) {
    this(name, function, type, null);
  }

  /**
   * Creates a Lambda layer
   *
   * @param name the unique name for this layer, if null, generates a unique name based on {@link
   *     Class#getSimpleName()}.
   * @param function the Java lambda function in the form <code>x = function(input)</code>. The
   *     first argument is the TensorFlow Ops, the second argument is the input Operand. If function
   *     is null, then the input is returned un changed.
   * @param type the data type for the layer's weights and computation.
   * @param options the layer's options
   */
  public Lambda(
      String name,
      BiFunction<Ops, Operand<T>, Operand<T>> function,
      Class<T> type,
      Options options) {
    super(name, true, type, options);
    this.function = function;
  }

  /**
   * Sets the lambda function
   *
   * @param function the Java lambda function in the form <code>
   *     x = function(input)</code>. The first argument is the TensorFlow Ops, the second argument
   *     is the input Operand.
   */
  public void setLambda(BiFunction<Ops, Operand<T>, Operand<T>> function) {
    this.function = function;
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TType> List<Operand<U>> call(
      Ops tf,
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {
    if (inputs.isEmpty()) {
      return Collections.emptyList();
    }
    Ops ltf = init(tf);
    List<Operand<T>> outputs = new ArrayList<>();
    for (Operand<? extends TType> input : inputs) {
      if (function != null) {
        Operand<T> tInput = cast(ltf, input, getType());
        Operand<T> result = function.apply(ltf, tInput);
        outputs.add(result);
      } else {
        outputs.add(cast(ltf, input, getType()));
      }
    }
    return convertTo(outputs, resultType);
  }
}
