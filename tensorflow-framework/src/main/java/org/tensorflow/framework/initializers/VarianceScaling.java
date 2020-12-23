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
package org.tensorflow.framework.initializers;

import org.tensorflow.Operand;
import org.tensorflow.framework.utils.ShapeUtils;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TFloating;

/**
 * Initializer capable of adapting its scale to the shape of weights tensors.
 *
 * <p>With <code>distribution=TRUNCATED_NORMAL or NORMAL</code>, samples are drawn from
 * a truncated/untruncated normal distribution with a mean of zero and a standard deviation (after
 * truncation, if used) <code>stddev = Math.sqrt(scale / n)</code>, where <code>n</code> is:
 *
 * <ul>
 *   <li>number of input units in the weight tensor, if <code>mode=FAN_IN</code>
 *   <li>number of output units, if <code>mode=FAN_OUT</code>
 *   <li>average of the numbers of input and output units, if <code>mode=FAN_AVG</code>
 * </ul>
 *
 * <p>With <code>distribution=UNIFORM</code>, samples are drawn from a uniform distribution within
 * <code>[-limit, limit]</code>, where <code>limit = Math.sqrt(3 * scale / n);</code>.
 *
 * <p>Examples:
 *
 * <pre>
 *      long seed = 1234l;
 *      float scale = 0.1f;
 *      VarianceScaling&lt;TFloat32, TFloat32&gt; initializer =
 *          new org.tensorflow.framework.initializers.VarianceScaling&lt;&gt;(
 *              tf, scale, Mode.FAN_IN, Distribution.UNIFORM, seed);
 *      Operand&lt;TFloat32&gt; values =
 *          initializer.call(tf.constant(Shape.of(2,2)), TFloat32.class);
 * </pre>
 *
 * @param <T> The TType for the call operation
 * @see VarianceScaling.Mode
 * @see VarianceScaling.Distribution
 */
public class VarianceScaling<T extends TFloating> extends BaseInitializer<T> {

  public static final double SCALE_DEFAULT = 1.0;
  public static final Mode MODE_DEFAULT = Mode.FAN_IN;
  public static final Distribution DISTRIBUTION_DEFAULT = Distribution.TRUNCATED_NORMAL;

  private final double scale;
  private final Mode mode;
  private final Distribution distribution;
  private final long seed;


  /**
   * Creates a VarianceScaling Initializer
   *
   * @param tf the TensorFlow Ops
   * @param seed sed to create random seeds.
   */
  public VarianceScaling(Ops tf, long seed) {
    this(tf, SCALE_DEFAULT, MODE_DEFAULT, DISTRIBUTION_DEFAULT, seed);
  }

  /**
   * Creates a VarianceScaling Initializer
   *
   * @param tf the TensorFlow Ops
   * @param scale Scaling factor (positive float).
   * @param mode the mode for the variance
   * @param distribution Random distribution to use.
   * @param seed Used to create random seeds.
   */
  public VarianceScaling(Ops tf, double scale, Mode mode, Distribution distribution, long seed) {
    super(tf);
    if (scale <= 0.0) {
      throw new IllegalArgumentException("scale must be greater than 0, got " + scale);
    }
    this.scale = scale;
    this.mode = mode;
    this.distribution = distribution;
    this.seed = seed;
  }

  /** {@inheritDoc} */
  @Override
  public Operand<T> call(Operand<TInt64> dims, Class<T> type) {
    Shape shape = ShapeUtils.toShape(this.tf.scope(), dims);
    double lscale = this.scale;
    double[] fans /* fanIn, fanOut */ = computeFans(shape);
    switch (mode) {
      case FAN_IN:
        lscale /= Math.max(1., fans[0]);
        break;
      case FAN_OUT:
        lscale /= Math.max(1., fans[1]);
        break;
      case FAN_AVG:
        lscale /= Math.max(1., (fans[0] + fans[1]) / 2.);
        break;
    }
    Operand<T> distOp;
    Operand<T> mulOp = null;
    double stddev;
    long[] seeds = {seed, 0};
    switch (distribution) {
      case TRUNCATED_NORMAL:
        distOp = tf.random.statelessTruncatedNormal(dims, tf.constant(seeds), type);
        stddev = Math.sqrt(lscale) / .87962566103423978;
        mulOp = tf.math.mul(distOp, tf.dtypes.cast(tf.constant(stddev), type));
        break;
      case NORMAL:
        distOp = tf.random.statelessRandomNormal(dims, tf.constant(seeds), type);
        stddev = Math.sqrt(lscale);
        mulOp = tf.math.mul(distOp, tf.dtypes.cast(tf.constant(stddev), type));
        break;
      case UNIFORM:
        distOp = tf.random.statelessRandomUniform(dims, tf.constant(seeds), type);
        stddev = Math.sqrt(3.0 * lscale);
        mulOp = tf.math.mul(distOp, tf.dtypes.cast(tf.constant(stddev), type));
        break;
    }
    return mulOp;
  }

  /**
   * Computes the Fans values based on the shape provided.
   *
   * @param shape the shape used to calculate the fans.
   * @return a double array containing the fan values, [fanIn, fanOut].
   */
  private double[] computeFans(Shape shape) {
    double fanIn;
    double fanOut;
    long[] dims = shape.asArray();
    if (dims == null || dims.length < 1) {
      fanIn = fanOut = 1;
    } else if (dims.length == 1) {
      fanIn = fanOut = dims[0];
    } else if (dims.length == 2) {
      fanIn = dims[0];
      fanOut = dims[1];
    } else {
      double receptiveFieldSize = 1.;
      for (int i = dims.length - 2; i >= 0; i--) {
        receptiveFieldSize *= dims[i];
      }
      fanIn = dims[dims.length - 2] * receptiveFieldSize;
      fanOut = dims[dims.length - 1] * receptiveFieldSize;
    }

    return new double[] {fanIn, fanOut};
  }

  /** The mode to use for calculating the fan values. */
  public enum Mode {
    FAN_IN,
    FAN_OUT,
    FAN_AVG
  }

  /** The random distribution to use when initializing the values. */
  public enum Distribution {
    TRUNCATED_NORMAL,
    NORMAL,
    UNIFORM
  }
}
