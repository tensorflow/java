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
 * He uniform initializer.
 * <p>Draws samples from a uniform distribution within <code>[-limit, limit]</code>, where <code>limit = sqrt(6 / fanIn)</code> (fan_in is the number of input units in the weight tensor).</p>
 * <p>Examples:
 * <pre>
 *     HeUniform&lt;TFloat32, TFloat32&gt; initializer =
 *             new org.tensorflow.framework.initializers.HeUniform&lt;&gt;(tf);
 *     Operand&lt;TFloat32&gt; values =
 *             initializer.call(tf.constant(Shape.of(2,2)), TFloat32.DTYPE);
 * </pre>
 * @param <T> The TType for the call operation
 * @param <U> The TNumber for the call operation
 *
 * @see <a href="https://www.cv-foundation.org/openaccess/content_iccv_2015/html/He_Delving_Deep_into_ICCV_2015_paper.html">He et al., 2015</a>
 */
public class HeUniform<T extends TType, U extends TNumber> extends VarianceScaling<T, U> {

  public static final double SCALE = 2.0;
  /**
   * Creates an HeUniform Initializer
   *
   * @param tf the TensorFlow Ops
   */
  public HeUniform(Ops tf) {
    super(tf, SCALE, Mode.FAN_IN, Distribution.UNIFORM, null);
  }

  /**
   * Creates an HeUniform Initializer
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation
   */
  public HeUniform(Ops tf, Long seed) {
    super(tf, SCALE, Mode.FAN_IN, Distribution.UNIFORM, seed);
  }
}
