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
import org.tensorflow.op.random.RandomUniform;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TType;

import java.util.ArrayList;
import java.util.List;

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Applies Alpha Dropout to the input.
 *
 * <p>Alpha Dropout is a <code>Dropout</code> that keeps mean and variance of inputs to their
 * original values, in order to ensure the self-normalizing property even after this dropout. Alpha
 * Dropout fits well to Scaled Exponential Linear Units by randomly setting activations to the
 * negative saturation value.
 */
public class AlphaDropout<T extends TFloating> extends Layer<T> {
  private static final long DEFAULT_GRAPH_SEED = 87654321;
  private final float rate;
  private final Shape noiseShape;
  private final long seed;

  /**
   * Creates a AlphaDropout layer, using a unique name will be generated based on {@link
   * Class#getSimpleName()} and no noiseShape.
   *
   * @param tf the TensorFlow Ops
   * @param rate A number between 0 and 1. Drop probability (as with {@link Dropout}). The
   *     multiplicative noise will have standard deviation <code>sqrt(rate / (1 - rate))</code>.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   * @param options the layer's options.
   */
  public AlphaDropout(Ops tf, float rate, long seed, Class<T> type, Options options) {

    this(tf, null, rate, null, seed, type, options);
  }

  /**
   * Creates a AlphaDropout layer, using a unique name will be generated based on {@link
   * Class#getSimpleName()}.
   *
   * @param tf the TensorFlow Ops
   * @param rate A number between 0 and 1. Drop probability (as with {@link Dropout}). The
   *     multiplicative noise will have standard deviation <code>sqrt(rate / (1 - rate))</code>.
   * @param noiseShape Optional, 1D integer tensor representing the shape of the binary dropout mask
   *     that will be multiplied with the input. For instance, if your inputs have shape
   *     (batch_size, timesteps, features) and you want the dropout mask to be the same for all
   *     timesteps, you can use noise_shape=(batch_size, 1, features). May be null.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   * @param options the layer's options.
   */
  public AlphaDropout(
      Ops tf, float rate, Shape noiseShape, long seed, Class<T> type, Options options) {
    this(tf, null, rate, noiseShape, seed, type, options);
  }

  /**
   * Creates a AlphaDropout layer
   *
   * @param tf the TensorFlow Ops
   * @param name name the unique name for this layer. If null, a unique name will be generated based
   *     on {@link Class#getSimpleName()}.
   * @param rate A number between 0 and 1. Drop probability (as with {@link Dropout}). The
   *     multiplicative noise will have standard deviation <code>sqrt(rate / (1 - rate))</code>.
   * @param noiseShape Optional, 1D integer tensor representing the shape of the binary dropout mask
   *     that will be multiplied with the input. For instance, if your inputs have shape
   *     (batch_size, timesteps, features) and you want the dropout mask to be the same for all
   *     timesteps, you can use noise_shape=(batch_size, 1, features). May be null.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   * @param options the layer's options.
   */
  public AlphaDropout(
      Ops tf,
      String name,
      float rate,
      Shape noiseShape,
      long seed,
      Class<T> type,
      Options options) {
    super(tf, name, true, type, options);
    if (rate < 0 || rate >= 1)
      throw new IllegalArgumentException("The rate must be between >= 0 and < 1, inclusive.");
    this.rate = rate;
    this.noiseShape = noiseShape;
    this.seed = seed;
    setSupportsMasking(true);
  }

  /** {@inheritDoc} */
  @Override
  public <U extends TType> List<Operand<U>> call(
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {
    Ops tf = getTF();

    if (!training || rate < 0 || rate > 1) {
      return convertList(inputs, resultType);
    }

    // training = true
    List<Operand<T>> outputs = new ArrayList<>();
    Operand<T> rateT = cast(tf, tf.constant(rate), getType());
    Operand<T> alpha = cast(tf, tf.constant(1.6732632423543772848170429916717), getType());
    Operand<T> scale = cast(tf, tf.constant(1.0507009873554804934193349852946), getType());
    // alpha_p = -alpha * scale
    Operand<T> alpha_p = tf.math.mul(tf.math.neg(alpha), scale);
    Operand<T> one = cast(tf, tf.constant(1), getType());
    Operand<T> minusPoint5 = cast(tf, tf.constant(-0.5), getType());
    // a = ((1 - rate) * (1 + rate * alpha_p**2))**-0.5
    Operand<T> a =
        tf.math.pow(
            tf.math.mul(
                tf.math.sub(one, rateT),
                tf.math.add(one, tf.math.mul(rateT, tf.math.mul(alpha_p, alpha_p)))),
            minusPoint5);
    // b = -a * alpha_p * rate
    Operand<T> b = tf.math.mul(tf.math.neg(a), tf.math.mul(alpha_p, rateT));

    for (Operand<? extends TType> input : inputs) {
      Operand<T> tInput = cast(tf, input, getType());
      Operand<TInt64> noise =
          noiseShape == null ? tf.shape(input, TInt64.class) : tf.constant(noiseShape);
      Operand<T> randomTensor =
          tf.random.randomUniform(
              noise, getType(), RandomUniform.seed(DEFAULT_GRAPH_SEED), RandomUniform.seed2(seed));
      Operand<T> keptIdx = cast(tf, tf.math.greaterEqual(randomTensor, rateT), getType());
      Operand<T> x =
          tf.math.add(
              tf.math.mul(tInput, keptIdx), tf.math.mul(alpha_p, tf.math.sub(one, keptIdx)));
      // result = a*x + b
      //noinspection SuspiciousNameCombination
      Operand<T> result = tf.math.add(tf.math.mul(a, x), b);
      outputs.add(result);
    }

    return callPostProcess(convertTo(outputs, resultType), true);
  }

  /** {@inheritDoc} */
  @Override
  public List<Shape> computeOutputShape(List<Shape> inputShapes) {
    return inputShapes;
  }
}
