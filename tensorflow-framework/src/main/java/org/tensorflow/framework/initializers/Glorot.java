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

import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TFloating;

/**
 * The Glorot initializer, also called Xavier initializer.
 *
 * <p>Draws samples from a random distribution.
 *
 * <p>If the distribution is TRUNCATED_NORMAL, then the distribution is centered on 0 with <code>
 *  stddev = Math.sqrt(2. / (fanIn + fanOut))</code> where <code>fanIn</code> is the number of input
 * units in the weight tensor and <code>fanOut</code> is the number of output units in the weight
 * tensor.
 *
 * <p>If the distribution is UNIFORM, then samples are drawn from a uniform distribution within
 * <code>[-limit, limit]</code>, where <code>limit = sqrt(6 / (fanIn + fanOut))</code> ( <code>fanIn
 * </code> is the number of input units in the weight tensor and <code> fanOut</code> is the number
 * of output units).
 *
 * <p>Examples:
 *
 * <p>Glorot Normal:
 *
 * <pre>
 *     long seed = 1001l;
 *     Glorot&lt;TFloat32, TFloat32&gt; initializer =
 *             new org.tensorflow.framework.initializers.Glorot&lt;&gt;(tf,
 *             Distribution.TRUNCATED_NORMAL, seed);
 *     Operand&lt;TFloat32&gt; values =
 *             initializer.call(tf.constant(Shape.of(2,2)), TFloat32.class);
 * </pre>
 *
 * <p>Glorot Uniform:
 *
 * <pre>
 *    long seed = 1001l;
 *    Glorot&lt;TFloat32, TFloat32&gt; initializer =
 *             new org.tensorflow.framework.initializers.Glorot&lt;&gt;(tf,
 *             Distribution.UNIFORM, seed);
 *     Operand&lt;TFloat32&gt; values =
 *             initializer.call(tf.constant(Shape.of(2,2)), TFloat32.class);
 * </pre>
 *
 * <p><b>NOTE:</b>
 * <p>For a GlorotNormal equivalent initializer, use {@link
 * VarianceScaling.Distribution#TRUNCATED_NORMAL} for the distribution parameter.
 * <p>For a GlorotUniform equivalent initializer, use {@link VarianceScaling.Distribution#UNIFORM}
 * for the distribution parameter.
 * <p></p>
 *
 * @param <T> The TType for the call operation
 * @see VarianceScaling.Distribution
 * @see <a href="http://proceedings.mlr.press/v9/glorot10a.html">Glorot et al., 2010</a>
 */
public class Glorot<T extends TFloating> extends VarianceScaling<T> {

  public static final double SCALE = 1.0;

  /**
   * Creates a Glorot initializer
   *
   * @param tf the TensorFlow Ops
   * @param distribution The distribution type for the Glorot initializer.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and dtype.
   * @see VarianceScaling.Distribution
   */
  public Glorot(Ops tf, Distribution distribution, long seed) {
    super(tf, SCALE, Mode.FAN_AVG, distribution, seed);
  }
}
