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

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TType;

import java.util.ArrayList;
import java.util.List;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Layer that applies an activation function to an output.
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class Activation<T extends TFloating> extends Layer<T> {
  private final org.tensorflow.framework.activations.Activation<T> activation;

  /**
   * Creates an Activation layer using {@link Class#getSimpleName()} for the name.
   *
   * @param tf the TensorFlow Ops.
   * @param activation the activation to apply
   * @param type the data type for the weights and computation
   */
  public Activation(
          Ops tf,
          org.tensorflow.framework.activations.Activation<T> activation,
          Class<T> type) {
    this(tf, null, activation, type, null);
  }

  /**
   * Creates an Activation layer using {@link Class#getSimpleName()} for the name.
   *
   * @param tf the TensorFlow Ops.
   * @param activation the activation to apply
   * @param type the data type for the weights and computation
   * @param options the layer's options, may be null
   */
  public Activation(
      Ops tf,
      org.tensorflow.framework.activations.Activation<T> activation,
      Class<T> type,
      Options options) {
    this(tf, null, activation, type, options);
  }

  /**
   * Creates an Activation layer
   *
   * @param tf the TensorFlow Ops.
   * @param name the unique name for this layer, if null will use {@link Class#getSimpleName()} for
   *     the name.
   * @param activation the activation to apply
   * @param type the data type for the weights and computation
   */
  public Activation(
          Ops tf,
          String name,
          org.tensorflow.framework.activations.Activation<T> activation,
          Class<T> type) {
    this(tf, name, activation, type, null);
  }
  /**
   * Creates an Activation layer
   *
   * @param tf the TensorFlow Ops.
   * @param name the unique name for this layer, if null will use {@link Class#getSimpleName()} for
   *     the name.
   * @param activation the activation to apply
   * @param type the data type for the weights and computation
   * @param options the layer's options, may be null
   */
  public Activation(
      Ops tf,
      String name,
      org.tensorflow.framework.activations.Activation<T> activation,
      Class<T> type,
      Options options) {
    super(tf, name, true, type, options);
    this.activation = activation;
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TType> List<Operand<U>> call(
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {
    Ops tf = getTF();
    List<Operand<U>> results = new ArrayList<>();
    inputs.forEach(
        input -> results.add(cast(tf, activation.call(cast(tf, input, getType())), resultType)));
    return callPostProcess(results, training);
  }

  /** {@inheritDoc} */
  @Override
  public List<Shape> computeOutputShape(List<Shape> inputShapes) {
    return inputShapes;
  }
}
