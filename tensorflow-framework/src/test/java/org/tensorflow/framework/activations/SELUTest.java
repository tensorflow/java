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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

public class SELUTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of SELU call method */
  @Test
  public void testCallFloat() {
    float[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    float[] expected = {
      1.050701F, -1.5201665F, 3.152103F, -1.7258986F, -1.1113307F, 2.101402F, -1.6705687F, 4.202804F
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        SELU instance = new SELU();
        Operand<TFloat32> result = instance.call(tf, tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of SELU call method */
  @Test
  public void testCallDouble() {
    double[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    double[] expected = {
      1.0507009873554805,
      -1.520166468595695,
      3.1521029620664414,
      -1.7258986281898947,
      -1.1113307378125628,
      2.101401974710961,
      -1.670568728767112,
      4.202803949421922,
    };
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        SELU instance = new SELU();
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testConfig() {
    Activation instance = Activation.create(SELU.NAME);
    assertTrue(instance instanceof SELU);
  }

  @Test
  public void testGetConfig() {
    SELU instance = new SELU();
    assertEquals(SELU.NAME, instance.getConfig().get("name"));
  }

  /** Test of Activation create method with bad data */
  @Test
  public void testBadConfig() {

    final Map<String, Object> configBadKey = new HashMap<>();
    configBadKey.put("beta", 2.0f);
    configBadKey.put(SELU.NAME_KEY, SELU.NAME);
    assertThrows(IllegalArgumentException.class, () -> Activation.create(configBadKey));

    final Map<String, Object> configBadClass = new HashMap<>();
    configBadClass.put(SELU.NAME_KEY, Linear.NAME);
    assertThrows(IllegalArgumentException.class, () -> new SELU(configBadClass));
  }
}
