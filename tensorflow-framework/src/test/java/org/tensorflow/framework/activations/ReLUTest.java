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
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

public class ReLUTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of ReLU call method */
  @Test
  public void testCallFloat() {
    float[][] input = {{1, -2}, {3, -4}, {-1, 2}, {-3, 4}};
    float[][] expected = {{1, 0}, {3, 0}, {0, 2}, {0, 4}};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU instance = new ReLU();
        Operand<TFloat32> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of ReLU call method */
  @Test
  public void testCallInt() {
    int[][] input = {{1, -2}, {3, -4}, {-1, 2}, {-3, 4}};
    int[][] expected = {{1, 0}, {3, 0}, {0, 2}, {0, 4}};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU instance = new ReLU();
        Operand<TInt32> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of ReLU call method */
  @Test
  public void testCallLong() {
    long[][] input = {{1, -2}, {3, -4}, {-1, 2}, {-3, 4}};
    long[][] expected = {{1, 0}, {3, 0}, {0, 2}, {0, 4}};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU instance = new ReLU();
        Operand<TInt64> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of ReLU call method */
  @Test
  public void testCallFloat16() {
    float[][] input = {{1, -2}, {3, -4}, {-1, 2}, {-3, 4}};
    float[][] expected = {{1, 0}, {3, 0}, {0, 2}, {0, 4}};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU instance = new ReLU();
        Operand<TFloat16> result =
            instance.call(tf, tf.dtypes.cast(tf.constant(input), TFloat16.class));
        session.evaluate(tf.dtypes.cast(tf.constant(expected), TFloat16.class), result);
      }
  }

  /** Test of ReLU call method */
  @Test
  public void testCallDouble() {
    double[][] input = {{1, -2}, {3, -4}, {-1, 2}, {-3, 4}};
    double[][] expected = {{1, 0}, {3, 0}, {0, 2}, {0, 4}};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU instance = new ReLU();
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  @Test
  public void testAlpha() {
    double[] input = {-10., -5., 0.0, 5., 10.};
    double[] expected = {-5., -2.5, 0., 5., 10.};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU instance = new ReLU(0.5f, ReLU.MAX_VALUE_DEFAULT, ReLU.THRESHOLD_DEFAULT);
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  @Test
  public void testMaxValue() {
    double[] input = {-10., -5., 0.0, 5., 10.};
    double[] expected = {0., 0., 0., 5., 5.};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU instance = new ReLU(ReLU.ALPHA_DEFAULT, 5, ReLU.THRESHOLD_DEFAULT);
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  @Test
  public void testThreshold() {
    double[] input = {-10., -5., 0.0, 5., 10.};
    double[] expected = {-0., -0., 0., 0., 10.};
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        ReLU instance = new ReLU(ReLU.ALPHA_DEFAULT, ReLU.MAX_VALUE_DEFAULT, 5.0f);
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  @Test
  public void testConfig() {
    Activation instance = Activation.create(ReLU.NAME);
    assertTrue(instance instanceof ReLU);
    Map<String, Object> config = new HashMap<>();
    config.put("name", ReLU.NAME);
    config.put("alpha", 2.0);
    config.put("max_value", 25);
    config.put("threshold", .95);

    instance = Activation.create(config);
    assertNotNull(instance);
    assertEquals(2.0f, ((ReLU) instance).getAlpha());
    assertEquals(25.0f, ((ReLU) instance).getMaxValue());
    assertEquals(.95f, ((ReLU) instance).getThreshold());
  }

  @Test
  public void testGetConfig() {
    ReLU instance = new ReLU();
    Map<String, Object> config = instance.getConfig();
    assertTrue(config.containsKey("alpha"));
    assertEquals(instance.getAlpha(), ((Number) config.get("alpha")).floatValue());
    assertTrue(config.containsKey("max_value"));
    assertEquals(instance.getMaxValue(), ((Number) config.get("max_value")).floatValue());
    assertTrue(config.containsKey("threshold"));
    assertEquals(instance.getThreshold(), ((Number) config.get("threshold")).floatValue());
  }

  /** Test of Activation create method with bad data */
  @Test
  public void testBadConfig() {

    final Map<String, Object> configBadKey = new HashMap<>();
    configBadKey.put("beta", 2.0f);
    configBadKey.put(ReLU.NAME_KEY, ReLU.NAME);
    assertThrows(IllegalArgumentException.class, () -> Activation.create(configBadKey));

    final Map<String, Object> configBadClass = new HashMap<>();
    configBadClass.put(ReLU.NAME_KEY, Linear.NAME);
    assertThrows(IllegalArgumentException.class, () -> new ReLU(configBadClass));
  }
}
