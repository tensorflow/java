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
import org.tensorflow.framework.layers.impl.Merge;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

import java.util.List;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Layer hat subtracts two inputs.
 *
 * <p>It takes as input a list of tensors, all of the same shape, and returns a single tensor (also
 * of the same shape).
 *
 * <p>It takes as input a list of tensors of size 2, both of the same shape, and returns a single
 * tensor, (inputs[0] - inputs[1]), also of the same shape.
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class Subtract<T extends TFloating> extends Merge<T> {

  /**
   * Creates an Add Layer using {@link Class#getSimpleName()} as the layer name.
   *
   * @param tf the TensorFlow Ops
   * @param type the data type for the weights and computation
   */
  public Subtract(Ops tf, Class<T> type) {
    this(tf, null, type, null);
  }

  /**
   * Creates an Add Layer using {@link Class#getSimpleName()} as the layer name.
   *
   * @param tf the TensorFlow Ops
   * @param type the data type for the weights and computation
   * @param options the layer's options.
   */
  public Subtract(Ops tf, Class<T> type, Options options) {
    this(tf, null, type, options);
  }

  /**
   * Creates an Add Layer
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param type the data type for the weights and computation
   */
  public Subtract(Ops tf, String name, Class<T> type) {
    this(tf, name, type, null);
  }

  /**
   * Creates an Add Layer
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the layer, if null the name is set to {@link Class#getSimpleName()}
   * @param type the data type for the weights and computation
   * @param options the layer's options.
   */
  public Subtract(Ops tf, String name, Class<T> type, Options options) {
    super(tf, name, type, options);
  }

  /** {@inheritDoc} */
  @Override
  protected void build(List<Shape> inputShapes) {
    if (inputShapes.size() != 2) {
      throw new IllegalArgumentException("A Subtract layer should be called on exactly 2 inputs");
    }
    super.build(inputShapes);
  }

  /** {@inheritDoc} */
  @Override
  protected Operand<? extends TType> mergeFunction(List<Operand<? extends TNumber>> inputs) {
    if (inputs.size() != 2) {
      throw new IllegalArgumentException("A Subtract layer should be called on exactly 2 inputs");
    }
    Ops tf = getTF();
    Operand<T> output = cast(tf, tf.identity(inputs.get(0)), getType());
    return tf.math.sub(output, cast(tf, inputs.get(1), getType()));
  }
}
