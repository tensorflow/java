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
package org.tensorflow.framework.activations;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

/** @author Jim Clarke */
public class SoftplusTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of Softplus call method */
  @Test
  public void testCallFloat() {
    float[] input = {1, 2, 3, 4, 5, 6, 7, 8};
    float[] expected = {
      1.3132616F, 2.126928F, 3.0485873F, 4.01815F, 5.0067153F, 6.0024757F, 7.0009117F, 8.000336F
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Softplus<TFloat32> instance = new Softplus<>();
        Operand<TFloat32> result = instance.call(tf, tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of Softplus call method */
  @Test
  public void testCallDouble() {
    double[] input = {1, 2, 3, 4, 5, 6, 7, 8};
    double[] expected = {
      1.3132616875182228, 2.1269280110429727, 3.048587351573742,
      4.0181499279178094, 5.006715348489118, 6.00247568513773,
      7.000911466453774, 8.000335406372896,
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Softplus<TFloat64> instance = new Softplus<>();
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(expected, result);
      }
  }
}
