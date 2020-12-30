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
 * He initializer.
 *
 * <p>If the distribution is TRUNCATED_NORMAL, it draws samples from a truncated normal distribution
 * centered on <code>0</code> with <code>stddev = sqrt(2 / fanIn)</code> where <code>fanIn</code> is
 * the number of input units in the weight tensor.
 *
 * <p>If the distribution is UNIFORM, it draws samples from a uniform distribution within <code>
 * [-limit, limit]</code>, where <code> limit = sqrt(6 / fanIn)</code> (fanIn is the number of input
 * units in the weight tensor).
 *
 * <p>Examples:
 *
 * <p>He Normal:
 *
 * <pre>
 *     long seed = 1001l;
 *     He&lt;TFloat32, TFloat32&gt; initializer =
 *             new org.tensorflow.framework.initializers.He&lt;&gt;(tf,
 *             Distribution.TRUNCATED_NORMAL, seed););
 *     Operand&lt;TFloat32&gt; values =
 *             initializer.call(tf.constant(Shape.of(2,2)), TFloat32.class);
 * </pre>
 *
 * <p>He Uniform:
 *
 * <pre>
 *     long seed = 1001l;
 *     He&lt;TFloat32, TFloat32&gt; initializer =
 *             new org.tensorflow.framework.initializers.He&lt;&gt;(tf,
 *             Distribution.UNIFORM, seed););
 *     Operand&lt;TFloat32&gt; values =
 *             initializer.call(tf.constant(Shape.of(2,2)), TFloat32.class);
 * </pre>
 *
 * <p><b>NOTE:</b>
 * <p>For an HeNormal equivalent initializer, use {@link
 * VarianceScaling.Distribution#TRUNCATED_NORMAL} for the distribution parameter.
 * <p>For an HeUniform equivalent initializer, use {@link VarianceScaling.Distribution#UNIFORM}
 * for the distribution parameter.
 * <p></p>
 *
 * @param <T> The TType for the call operation
 * @see <a
 *     href="https://www.cv-foundation.org/openaccess/content_iccv_2015/html/He_Delving_Deep_into_ICCV_2015_paper.html">He
 *     et al., 2015</a>
 */
public class He<T extends TFloating> extends VarianceScaling<T> {

  public static final double SCALE = 2.0;

  /**
   * Creates an He Initializer
   *
   * @param tf the TensorFlow Ops
   * @param distribution The distribution type for the He initializer.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and dtype.
   * @see VarianceScaling.Distribution
   */
  public He(Ops tf, Distribution distribution, long seed) {
    super(tf, SCALE, Mode.FAN_IN, distribution, seed);
  }
}
