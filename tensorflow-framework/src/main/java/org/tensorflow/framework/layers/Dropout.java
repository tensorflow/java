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
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.random.RandomUniform;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TFloating;
import org.tensorflow.types.family.TType;

/**
 * Applies Dropout to the input.
 *
 * <p>The Dropout layer randomly sets input units to 0 with a frequency of rate at each step during
 * training time, which helps prevent overfitting. Inputs not set to 0 are scaled up by 1/(1 - rate)
 * such that the sum over all inputs is unchanged.
 *
 * <p>Note that the Dropout layer only applies when training is set to true such that no values are
 * dropped during inference. When using model.fit, training will be appropriately set to true
 * automatically, and in other contexts, you can set the kwarg explicitly to True when calling the
 * layer.
 *
 * <p>(This is in contrast to setting trainable=false for a Dropout layer. trainable does not affect
 * the layer's behavior, as Dropout does not have any variables/weights that can be frozen during
 * training.)
 *
 * @param <T> the data type for the layer's weights and computation.
 * @see <a href="https://arxiv.org/abs/1207.0580">Hinton G, et al. 2012, Improving neural networks
 *     by preventing co-adaptation of feature detectors</a>
 */
public class Dropout<T extends TFloating> extends Layer<T> {

  private final float rate;
  private final Shape noiseShape;
  private final long seed;

  /**
   * Creates a Dropout layer, using a unique name will be generated based on {@link
   * Class#getSimpleName()} and no noiseShape.
   *
   * @param rate A number between 0 and 1. Fraction of the input units to drop.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   */
  public Dropout(float rate, long seed, Class<T> type) {

    this(null, rate, null, seed, type, null);
  }

  /**
   * Creates a Dropout layer, using a unique name will be generated based on {@link
   * Class#getSimpleName()} and no noiseShape.
   *
   * @param rate A number between 0 and 1. Fraction of the input units to drop.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   * @param options the layer's options.
   */
  public Dropout(float rate, long seed, Class<T> type, Options options) {

    this(null, rate, null, seed, type, options);
  }

  /**
   * Creates a Dropout layer, using a unique name will be generated based on {@link
   * Class#getSimpleName()}.
   *
   * @param rate A number between 0 and 1. Fraction of the input units to drop.
   * @param noiseShape Optional, 1D integer tensor representing the shape of the binary dropout mask
   *     that will be multiplied with the input. For instance, if your inputs have shape
   *     (batch_size, timesteps, features) and you want the dropout mask to be the same for all
   *     timesteps, you can use noise_shape=(batch_size, 1, features). May be null.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   */
  public Dropout(float rate, Shape noiseShape, long seed, Class<T> type) {
    this(null, rate, noiseShape, seed, type, null);
  }

  /**
   * Creates a Dropout layer, using a unique name will be generated based on {@link
   * Class#getSimpleName()}.
   *
   * @param rate A number between 0 and 1. Fraction of the input units to drop.
   * @param noiseShape Optional, 1D integer tensor representing the shape of the binary dropout mask
   *     that will be multiplied with the input. For instance, if your inputs have shape
   *     (batch_size, timesteps, features) and you want the dropout mask to be the same for all
   *     timesteps, you can use noise_shape=(batch_size, 1, features). May be null.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   * @param options the layer's options.
   */
  public Dropout(float rate, Shape noiseShape, long seed, Class<T> type, Options options) {
    this(null, rate, noiseShape, seed, type, options);
  }

  /**
   * Creates a Dropout layer
   *
   * @param name name the unique name for this layer. If null, a unique name will be generated based
   *     on {@link Class#getSimpleName()}.
   * @param rate A number between 0 and 1. Fraction of the input units to drop.
   * @param noiseShape Optional, 1D integer tensor representing the shape of the binary dropout mask
   *     that will be multiplied with the input. For instance, if your inputs have shape
   *     (batch_size, timesteps, features) and you want the dropout mask to be the same for all
   *     timesteps, you can use noise_shape=(batch_size, 1, features). May be null.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   */
  public Dropout(String name, float rate, Shape noiseShape, long seed, Class<T> type) {
    this(name, rate, noiseShape, seed, type, null);
  }

  /**
   * Creates a Dropout layer
   *
   * @param name name the unique name for this layer. If null, a unique name will be generated based
   *     on {@link Class#getSimpleName()}.
   * @param rate A number between 0 and 1. Fraction of the input units to drop.
   * @param noiseShape Optional, 1D integer tensor representing the shape of the binary dropout mask
   *     that will be multiplied with the input. For instance, if your inputs have shape
   *     (batch_size, timesteps, features) and you want the dropout mask to be the same for all
   *     timesteps, you can use noise_shape=(batch_size, 1, features). May be null.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @param type the data type for the weights and computation
   * @param options the layer's options.
   */
  public Dropout(
      String name, float rate, Shape noiseShape, long seed, Class<T> type, Options options) {
    super(name, true, type, options);
    if (rate < 0 || rate >= 1)
      throw new IllegalArgumentException("The rate must be between >= 0 and < 1, inclusive.");
    this.rate = rate;
    this.noiseShape = noiseShape;
    this.seed = seed;
    setSupportsMasking(true);
  }

  /** {@inheritDoc} */
  @SuppressWarnings("unchecked")
  @Override
  public <U extends TType> List<Operand<U>> call(
      Ops tf,
      List<Operand<? extends TType>> inputs,
      List<Operand<TBool>> masks,
      boolean training,
      Class<U> resultType) {

    Ops ltf = init(tf);
    List<Operand<U>> outputs = new ArrayList<>();

    for (Operand<? extends TType> input : inputs) {

      Operand<? extends TFloating> output;
      if (!TFloating.class.isAssignableFrom(input.type())) {
        output = cast(ltf, input, TFloat64.class);
      } else {
        output = (Operand<? extends TFloating>) input;
      }

      if (training) {
        Operand<T> rateV = cast(ltf, ltf.constant(rate), getType());

        Operand<TInt64> noise =
            noiseShape == null ? tf.shape(input, TInt64.class) : tf.constant(noiseShape);

        Operand<T> tOutput = cast(getTF(), output, getType());
        tOutput = dropout(tOutput, rateV, noise, seed);

        outputs.add(cast(getTF(), tOutput, resultType));
      } else {
        outputs.add(cast(getTF(), output, resultType));
      }
    }
    return callPostProcess(outputs, training);
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
   *     setting rate=0.1 would drop 10% of input elements.
   * @param noiseShape the noise shape representing the shape for randomly generated keep/drop
   *     flags.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   * @return an Operand with the same shape as the input with a percentage of entries dropped out.
   */
  /* TODO, this is defined as tf.dropout() in python and is an nn op. Do we want it here? */
  private Operand<T> dropout(
      Operand<T> input, Operand<T> rate, Operand<TInt64> noiseShape, long seed) {
    Ops tf = getTF();

    Operand<T> one = cast(tf, tf.constant(1.), input.type());
    Operand<T> keepProb = tf.math.sub(one, rate);
    Operand<T> scale = tf.math.div(one, keepProb);
    Operand<T> ret = tf.math.mul(input, scale);

    Operand<T> randomTensor =
        tf.random.randomUniform(noiseShape, input.type(), RandomUniform.seed(seed));
    Operand<TBool> keepMask = tf.math.greaterEqual(randomTensor, rate);
    ret = tf.math.mul(ret, cast(tf, keepMask, ret.type()));
    return tf.reshape(ret, tf.shape(input));
  }
}
