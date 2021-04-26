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
import org.tensorflow.op.random.ParameterizedTruncatedNormal;
import org.tensorflow.types.TBool;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TType;

import java.util.ArrayList;
import java.util.List;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Apply multiplicative 1-centered Gaussian noise.
 *
 * <p>As it is a regularization layer, it is only active at training time.
 *
 * @param <T> the data type for the layer's weights and computation.
 */
public class GaussianDropout<T extends TFloating> extends Layer<T> {

  private final float rate;
  private final long seed;

  /**
   * Creates a GaussianDropout layer, using a unique name will be generated based on {@link
   * Class#getSimpleName()} and no noiseShape.
   *
   * @param tf the TensorFlow Ops.
   * @param rate A number between 0 and 1. Drop probability. The multiplicative noise will have
   *     standard deviation: sqrt(rate / (1 - rate)).
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   */
  public GaussianDropout(Ops tf, float rate, long seed, Class<T> type) {

    this(tf, null, rate, seed, type, null);
  }

  /**
   * Creates a GaussianDropout layer, using a unique name will be generated based on {@link
   * Class#getSimpleName()} and no noiseShape.
   *
   * @param tf the TensorFlow Ops.
   * @param rate A number between 0 and 1. Drop probability. The multiplicative noise will have
   *     standard deviation: sqrt(rate / (1 - rate)).
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   * @param options the layer's options.
   */
  public GaussianDropout(Ops tf, float rate, long seed, Class<T> type, Options options) {

    this(tf, null, rate, seed, type, options);
  }

  /**
   * Creates a GaussianDropout layer
   *
   * @param tf the TensorFlow Ops.
   * @param name name the unique name for this layer. If null, a unique name will be generated based
   *     on {@link Class#getSimpleName()}.
   * @param rate A number between 0 and 1. Fraction of the input units to drop.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   */
  public GaussianDropout(
          Ops tf, String name, float rate, long seed, Class<T> type) {
    this(tf, name, rate, seed, type, null);
  }


  /**
   * Creates a GaussianDropout layer
   *
   * @param tf the TensorFlow Ops.
   * @param name name the unique name for this layer. If null, a unique name will be generated based
   *     on {@link Class#getSimpleName()}.
   * @param rate A number between 0 and 1. Fraction of the input units to drop.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   * @param options the layer's options.
   */
  public GaussianDropout(
      Ops tf, String name, float rate, long seed, Class<T> type, Options options) {
    super(tf, name, true, type, options);
    if (rate < 0 || rate >= 1)
      throw new IllegalArgumentException("The rate must be between >= 0 and < 1, inclusive.");
    this.rate = rate;
    this.seed = seed;
    setSupportsMasking(true);
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unchecked")
  @Override
  public <U extends TType> List<Operand<U>> call(
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {

    Ops tf = getTF();
    List<Operand<T>> outputs = new ArrayList<>();

    for (Operand<? extends TType> input : inputs) {

      Operand<T> output = cast(tf, input, getType());

      if (training && rate >= 0 && rate <= 1) {

        Operand<T> rateV = cast(tf, tf.constant(rate), getType());

        output = dropout(output, rateV, seed);
        outputs.add(output);
      } else {
        outputs.add(output);
      }
    }
    return callPostProcess(convertTo(outputs, resultType), training);
  }

  /** {@inheritDoc} */
  @Override
  public List<Shape> computeOutputShape(List<Shape> inputShapes) {
    return inputShapes;
  }

  /**
   * Computes dropout: randomly sets elements to zero to prevent overfitting.
   *
   * @param input the input
   * @param rate the drop out rate, the probability that each element is dropped. For example,
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @return an Operand with the same shape as the input with a percentage of entries dropped out.
   */
  /* TODO, this is defined as tf.dropout() in python and is an nn op. Do we want it here? */
  private Operand<T> dropout(Operand<T> input, Operand<T> rate, long seed) {
    Ops tf = getTF();

    Operand<T> one = cast(tf, tf.constant(1), input.type());
    Operand<T> zero = cast(tf, tf.constant(0), input.type());
    Operand<T> keepProb = tf.math.sub(one, rate);
    Operand<T> stdDev = tf.math.sqrt(tf.math.div(rate, keepProb));

    Operand<T> randomNormal =
        tf.random.parameterizedTruncatedNormal(
            tf.shape(input), one, stdDev, zero, one, ParameterizedTruncatedNormal.seed(seed));

    return tf.math.mul(input, randomNormal);
  }
}
