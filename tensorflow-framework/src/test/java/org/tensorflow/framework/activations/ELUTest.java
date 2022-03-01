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
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

public class ELUTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of ELU call method */
  @Test
  public void testCallFloat() {
    float[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    float[] expected = {1f, -0.86466473f, 3f, -0.9816844f, -0.63212055f, 2f, -0.95021296f, 4f};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ELU instance = new ELU();
        Operand<TFloat32> result = instance.call(tf, tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of ELU call method */
  @Test
  public void testCallDouble() {
    double[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    double[] expected = {1, -0.86466473, 3, -0.9816844, -0.63212055, 2, -0.95021293, 4F};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ELU instance = new ELU();
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of ELU call method */
  @Test
  public void testAlpha() {
    double[] input = {1, -2, 3, -4, -5, 6, 7, 8};
    double[] expected = {1, -1.7293295, 3, -1.9633688, -1.9865241, 6, 7, 8};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ELU instance = new ELU(2.0f);
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testConfig() {
    Activation instance = Activation.create(ELU.NAME);
    assertTrue(instance instanceof ELU);

    Map<String, Object> config = new HashMap<>();
    config.put("alpha", 2.0f);
    config.put(AbstractActivation.NAME_KEY, ELU.NAME);

    instance = Activation.create(config);
    assertTrue(instance instanceof ELU);
    assertEquals(2.0, ((ELU) instance).getAlpha());

    instance = Activation.create("elu");
    assertNotNull(instance);
    assertEquals(1.0f, ((ELU) instance).getAlpha());
  }

  @Test
  public void testGetConfig() {
    ELU instance = new ELU(2.0f);
    Map<String, Object> config = instance.getConfig();
    assertEquals(ELU.NAME, config.get(AbstractActivation.NAME_KEY));
    assertEquals(2.0f, ((Number) config.get("alpha")).floatValue());
  }

  /** Test of Activation create method with bad data */
  @Test
  public void testBadConfig() {
    final Map<String, Object> configBadKey = new HashMap<>();
    configBadKey.put("beta", 2.0f);
    configBadKey.put(AbstractActivation.NAME_KEY, ELU.NAME);
    assertThrows(IllegalArgumentException.class, () -> Activation.create(configBadKey));

    final Map<String, Object> configBadClass = new HashMap<>();
    configBadClass.put("alpha", 2.0f);
    configBadClass.put(AbstractActivation.NAME_KEY, "bogus");
    assertThrows(IllegalArgumentException.class, () -> new ELU(configBadClass));
  }
}
