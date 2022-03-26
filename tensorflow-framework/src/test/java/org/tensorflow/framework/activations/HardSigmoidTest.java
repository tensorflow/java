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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;

public class HardSigmoidTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of HardSigmoid call method. */
  @Test
  public void testCallFloat() {
    float[] input = {-3.0f, -1.0f, 0.0f, 1.0f, 3.0f};
    float[] expected = {0.f, 0.3f, 0.5f, 0.7f, 1.f};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        HardSigmoid instance = new HardSigmoid();
        Operand<TFloat32> result = instance.call(tf, tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of HardSigmoid call method. */
  @Test
  public void testCallDouble() {
    double[] input = {-3.0, -1.0, 0.0, 1.0, 3.0};
    double[] expected = {0., 0.3, 0.5, 0.7, 1.};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        HardSigmoid instance = new HardSigmoid();
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testConfig() {
    Activation instance = Activation.create(HardSigmoid.NAME);
    assertTrue(instance instanceof HardSigmoid);
    HardSigmoid hardSigmoid =
        new HardSigmoid(Collections.singletonMap(HardSigmoid.NAME_KEY, HardSigmoid.NAME));
    assertNotNull(hardSigmoid);
  }

  @Test
  public void testGetConfig() {
    HardSigmoid instance = new HardSigmoid();
    assertEquals(HardSigmoid.NAME, instance.getConfig().get("name"));
  }

  /** Test of Activation create method with bad data */
  @Test
  public void testBadConfig() {

    final Map<String, Object> configBadKey = new HashMap<>();
    configBadKey.put("beta", 2.0f);
    configBadKey.put(HardSigmoid.NAME_KEY, HardSigmoid.NAME);
    assertThrows(IllegalArgumentException.class, () -> Activation.create(configBadKey));

    final Map<String, Object> configBadClass = new HashMap<>();
    configBadClass.put(HardSigmoid.NAME_KEY, "bogus");
    assertThrows(IllegalArgumentException.class, () -> new HardSigmoid(configBadClass));
  }
}
