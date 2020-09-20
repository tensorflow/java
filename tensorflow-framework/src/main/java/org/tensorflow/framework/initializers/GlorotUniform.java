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
 * he Glorot uniform initializer, also called Xavier uniform initializer..
 *
 * <p>Draws samples from a uniform distribution within
 * <code>[-limit, limit]</code>, where <code></code>limit = sqrt(6 / (fanIn + fanOut))</code>
 * ( <code>fanIn</code> is the number of input units in the weight tensor and <code> fanOut</code> is the number of output units).
 *
 * <p>Examples:
 * <pre>
 *     GlorotUniform&lt;TFloat32, TFloat32&gt; initializer =
 *             new org.tensorflow.framework.initializers.GlorotUniform&lt;&gt;(tf);
 *     Operand&lt;TFloat32&gt; values =
 *             initializer.call(tf.constant(Shape.of(2,2)), TFloat32.DTYPE);
 * </pre>
 *
 * @param <T> The TType for the call operation
 * @param <U> The TNumber for the call operation
 *
 * @see <a href="http://proceedings.mlr.press/v9/glorot10a.html">Glorot et al., 2010</a>
 */
public class GlorotUniform<T extends TType, U extends TNumber> extends VarianceScaling<T, U> {
  public static final double SCALE = 1.0;
  /**
   * Creates a GlorotUniform initializer
   *
   * @param tf the TensorFlow Ops
   */
  public GlorotUniform(Ops tf) {
    super(tf, SCALE, Mode.FAN_AVG, Distribution.UNIFORM, null);
  }

  /**
   * Creates a GlorotUniform initializer
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation
   */
  public GlorotUniform(Ops tf, Long seed) {
    super(tf, SCALE, Mode.FAN_AVG, Distribution.UNIFORM, seed);
  }
}
