/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat64;

public class GELUTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of GELU call method */
  @Test
  public void testCallNoAproximate() {
    double[][] input = {
      {
        0.18463433217903202,
        0.7748109168364575,
        0.5901821703159541,
        0.11772865177047143,
        0.39442705615436113
      },
      {
        0.14569713527198846,
        0.022968622140421502,
        0.19299343598670116,
        0.8063201076826957,
        0.2908883528612243
      }
    };

    double[][] expected = {
      {0.10584016, 0.60495245, 0.42638642, 0.06438094, 0.2577057},
      {0.08128731, 0.011694758, 0.11126418, 0.63696945, 0.178731}
    };

    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        GELU instance = new GELU();
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  /** Test of GELU call method */
  @Test
  public void testCallAproximate() {
    double[][] input = {
      {
        0.7528694935987742,
        0.9496349687413689,
        0.6676759543267402,
        0.5424082144274655,
        0.16766158529053699
      },
      {
        0.1022611836463726,
        0.7906577638705719,
        0.9607832735098116,
        0.5693112764986582,
        0.731933160073661
      }
    };

    double[][] expected = {
      {
        0.5828287788543643,
        0.7869710854047693,
        0.4992606099752831,
        0.38304258917872785,
        0.09499264800335701
      },
      {
        0.055295175281590385,
        0.620923776708609,
        0.798915192168026,
        0.40727355470673604,
        0.5619842135196427
      }
    };

    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        GELU instance = new GELU(true);
        Operand<TFloat64> result = instance.call(tf, tf.constant(input));
        session.evaluate(tf.constant(expected), result);
      }
  }

  @Test
  public void testConfig() {
    Activation instance = Activation.create(GELU.NAME);
    assertTrue(instance instanceof GELU);

    Map<String, Object> config = new HashMap<>();
    config.put("name", GELU.NAME);
    config.put("approximate", true);
    instance = Activation.create(config);
    assertNotNull(instance);
    assertTrue(((GELU) instance).isApproximate());
  }

  @Test
  public void testGetConfig() {
    GELU instance = new GELU(true);
    Map<String, Object> config = instance.getConfig();
    assertTrue(config.containsKey("approximate"));
    assertTrue(config.get("approximate") instanceof Boolean);
    assertTrue((Boolean) config.get("approximate"));
  }

  /** Test of Activation create method with bad data */
  @Test
  public void testBadConfig() {

    final Map<String, Object> configBadKey = new HashMap<>();
    configBadKey.put("beta", 2.0f);
    configBadKey.put(GELU.NAME_KEY, GELU.NAME);
    assertThrows(IllegalArgumentException.class, () -> Activation.create(configBadKey));

    final Map<String, Object> configBadClass = new HashMap<>();
    configBadClass.put(GELU.NAME_KEY, "bogus");
    assertThrows(IllegalArgumentException.class, () -> new GELU(configBadClass));
  }
}
