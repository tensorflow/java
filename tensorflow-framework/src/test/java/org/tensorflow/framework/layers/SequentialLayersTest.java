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
package org.tensorflow.framework.layers;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

public class SequentialLayersTest {

  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  /** Tests executing a thread through sequential layers. */
  @Test
  public void testSequentialLayers() {
    long seed = 1001L;

    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      float[][] inputArray = {
        {6.600953f, 4.659476f},
        {6.943807f, 2.113826f},
        {4.667166f, 6.931125f},
        {7.716860f, 3.205337f}
      };
      Operand<TFloat32> input = tf.constant(inputArray);
      List<Layer<TFloat32>> sequencedLayers =
          Arrays.asList(
              new Input<>(input, TFloat32.class),
              new Dense<>(3, seed, TFloat32.class),
              new Dropout<>(0.3f, seed, TFloat32.class),
              new Flatten<>(TFloat32.class));

      Operand<TFloat32> result = input;
      for (Layer<TFloat32> layer : sequencedLayers) {
        result = layer.call(tf, result, TFloat32.class);
      }
      session.run(tf.init());
      float[][] expected =
          new float[][] {
            {0f, 12.723986f, 0f},
            {0f, 0f, 0f},
            {7.344245f, 11.509995f, 7.875926f},
            {8.921803f, 0f, 0f}
          };

      session.evaluate(tf.constant(expected), result);
    }
  }
}
