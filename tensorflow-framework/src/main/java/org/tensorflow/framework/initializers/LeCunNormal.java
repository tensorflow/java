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
 * LeCun normal initializer.
 *
 * <p>Draws samples from a truncated normal distribution centered on 0 with
 * <code>stddev = sqrt(1 / fanIn)</code> where <code>fanIn</code> is the number of input units in the weight tensor.
 *
 * <p>Examples:
 *
 * <pre>
 *      LeCunNormal&lt;TFloat32, TFloat32&gt; initializer =
 *              new org.tensorflow.framework.initializers.LeCunNormal&lt;&gt;(tf);
 *      Operand&lt;TFloat32&gt; values =
 *              initializer.call(tf.constant(Shape.of(2,2)), TFloat32.DTYPE);
 * </pre>
 *
 * @param <T> The TType for the call operation
 * @param <U> The TNumber for the call operation
 *
 * @see <a href="https://papers.nips.cc/paper/6698-self-normalizing-neural-networks">Self-Normalizing Neural Networks, Klambauer et al., 2017</a>
 * @see <a href="http://yann.lecun.com/exdb/publis/pdf/lecun-98b.pdf">Efficient Backprop, Lecun et al., 1998</a>
 */
public class LeCunNormal<T extends TType, U extends TNumber> extends VarianceScaling<T, U> {

  /**
   * Creates a LeCunNormal Initializer
   *
   * @param tf the TensorFlow Ops
   */
  public LeCunNormal(Ops tf) {
    super(tf, 1.0, Mode.FAN_IN, Distribution.TRUNCATED_NORMAL, null);
  }

  /**
   * Creates a LeCunNormal Initializer
   *
   * @param tf the TensorFlow Ops
   * @param seed the seed for random number generation
   */
  public LeCunNormal(Ops tf, Long seed) {
    super(tf, 1.0, Mode.FAN_IN, Distribution.TRUNCATED_NORMAL, seed);
  }
}
