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
import org.tensorflow.types.TInt32;

public class LinearTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of Linear call method. */
  @Test
  public void testCallInt() {
    int[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    int[] expected = {1, -2, 3, -4, -1, 2, -3, 4};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Linear instance = new Linear();
        Operand<TInt32> result = instance.call(tf, tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of Linear call method. */
  @Test
  public void testCallFloat() {
    float[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    float[] expected = {1, -2, 3, -4, -1, 2, -3, 4};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Linear instance = new Linear();
        Operand<TFloat32> result = instance.call(tf, tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  /** Test of Linear call method. */
  @Test
  public void testCallDouble() {
    double[] input = {1, -2, 3, -4, -1, 2, -3, 4};
    double[] expected = {1, -2, 3, -4, -1, 2, -3, 4};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Linear instance = new Linear();
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(expected, result);
      }
  }

  @Test
  public void testConfig() {
    Activation instance = Activation.create(Linear.NAME);
    assertTrue(instance instanceof Linear);
    Linear linear = new Linear(Collections.singletonMap(Linear.NAME_KEY, Linear.NAME));
    assertNotNull(linear);
  }

  @Test
  public void testGetConfig() {
    Linear instance = new Linear();
    assertEquals(Linear.NAME, instance.getConfig().get(Linear.NAME_KEY));
  }

  /** Test of Activation create method with bad data */
  @Test
  public void testBadConfig() {

    final Map<String, Object> configBadKey = new HashMap<>();
    configBadKey.put("beta", 2.0f);
    configBadKey.put(Linear.NAME_KEY, Linear.NAME);
    assertThrows(IllegalArgumentException.class, () -> Activation.create(configBadKey));

    final Map<String, Object> configBadClass = new HashMap<>();
    configBadClass.put(Linear.NAME_KEY, "bogus");
    assertThrows(IllegalArgumentException.class, () -> new Linear(configBadClass));
  }
}
