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
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * He normal initializer.
 * <p>It draws samples from a truncated normal distribution centered on 0 with stddev = sqrt(2 / fan_in) where fan_in is the number of input units in the weight tensor.</p>
 * <p>Examples:
 * <pre>
 *     HeNormal&lt;TFloat32, TFloat32&gt; initializer =
 *             new org.tensorflow.framework.initializers.HeNormal&lt;&gt;(tf);
 *     Operand&lt;TFloat32&gt; values =
 *             initializer.call(tf.constant(Shape.of(2,2)), TFloat32.DTYPE);
 * </pre>
 * @param <T> The TType for the call operation
 * @param <U> The TNumber for the call operation
 *
 * @see <a href="https://www.cv-foundation.org/openaccess/content_iccv_2015/html/He_Delving_Deep_into_ICCV_2015_paper.html">He et al., 2015</a>
 */
public class HeNormal<T extends TType, U extends TNumber> extends VarianceScaling<T, U> {

  public static final double SCALE = 2.0;

  /**
   * Creates an HeNormal Initializer
   *
   * @param tf the TensorFlow Ops
   */
  public HeNormal(Ops tf) {
    super(tf, SCALE, Mode.FAN_IN, Distribution.TRUNCATED_NORMAL, null);
  }

  /**
   * Creates an HeNormal Initializer
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation
   */
  public HeNormal(Ops tf, Long seed) {
    super(tf, SCALE, Mode.FAN_IN, Distribution.TRUNCATED_NORMAL, seed);
  }
}
