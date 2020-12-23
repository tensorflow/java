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
 * LeCun normal initializer.
 *
 * <p>Draws samples from a random distribution. * *
 *
 * <p>If the distribution is TRUNCATED_NORMAL, it draws samples from a truncated normal distribution
 * centered on <code>0</code> with <code>
 * stddev = sqrt(1 / fanIn)</code> where <code>fanIn</code> is the number of input units in the
 * weight tensor.
 *
 * <p>If the distribution is UNIFORM, itraws samples from a uniform distribution within <code>
 * [-limit, limit]</code>, where <code>limit = Math.sqrt(3 / fanIn)</code> (<code>fanIn</code> is
 * the number of input units in the weight tensor)
 *
 * <p>Examples:
 *
 * <p>LeCun Normal:
 *
 * <pre>
 *      long seed = 1001l;
 *      LeCunNormal&lt;TFloat32, TFloat32&gt; initializer =
 *              new org.tensorflow.framework.initializers.LeCunNormal&lt;&gt;(tf,
 *               Distribution.TRUNCATED_NORMAL, seed);
 *      Operand&lt;TFloat32&gt; values =
 *              initializer.call(tf.constant(Shape.of(2,2)), TFloat32.class);
 * </pre>
 *
 * <p>LeCun Uniform:
 *
 * <pre>
 *      long seed = 1001l;
 *      LeCunNormal&lt;TFloat32, TFloat32&gt; initializer =
 *              new org.tensorflow.framework.initializers.LeCunNormal&lt;&gt;(tf,
 *               Distribution.UNIFORM, seed);
 *      Operand&lt;TFloat32&gt; values =
 *              initializer.call(tf.constant(Shape.of(2,2)), TFloat32.class);
 * </pre>
 *
 * *
 *
 * <p><b>NOTE:</b> *
 *
 * <p>For a LeCunNormal equivalent initializer, use {@link VarianceScaling.Distribution#TRUNCATED_NORMAL} for the distribution parameter. *
 *
 * <p>For a LeCunUniform equivalent initializer, use {@link VarianceScaling.Distribution#UNIFORM} *
 * for the distribution parameter. *
 *
 * <p>
 *
 * @param <T> The TType for the call operation
 * @see <a
 *     href="https://papers.nips.cc/paper/6698-self-normalizing-neural-networks">Self-Normalizing
 *     Neural Networks, Klambauer et al., 2017</a>
 * @see <a href="http://yann.lecun.com/exdb/publis/pdf/lecun-98b.pdf">Efficient Backprop, Lecun et
 *     al., 1998</a>
 * @see VarianceScaling.Distribution
 */
public class LeCun<T extends TFloating> extends VarianceScaling<T> {

  /**
   * Creates a LeCunNormal Initializer
   *
   * @param tf the TensorFlow Ops
   * @param distribution The distribution type for the Glorot initializer.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and dtype.
   */
  public LeCun(Ops tf, Distribution distribution, long seed) {
    super(tf, 1.0, Mode.FAN_IN, distribution, seed);
  }
}
