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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.Tensor;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.family.TType;

public class InputTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  @Test
  void call() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      float[][] array = new float[][] {{0, 1}, {2, 3}};
      Operand<TFloat32> input = tf.constant(array);
      Input<TFloat32> instance = new Input<>(input, TFloat32.class);
      List<Operand<TFloat32>> result =
          instance.call(tf, Collections.singletonList(input), null, false, TFloat32.class);

      assertNotNull(result);
      assertEquals(1, result.size());

      session.evaluate(input, result.get(0));
    }
  }

  @Test
  void getOutput() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      float[][] array = new float[][] {{0, 1}, {2, 3}};
      Operand<TFloat32> input = tf.constant(array);
      Input<TFloat32> instance = new Input<>(input, TFloat32.class);
      instance.init(tf);
      Operand<TFloat32> result = instance.getOutput(TFloat32.class);

      session.evaluate(input, result);

      result = Input.input(tf, input, TFloat32.class);
      session.evaluate(input, result);
    }
  }

  @Test
  void isPlaceholder() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      float[][] array = new float[][] {{0, 1}, {2, 3}};
      Operand<TFloat32> input = tf.constant(array);
      Input<TFloat32> instance =
          new Input<>(
              TFloat32.class, TFloat32.class, Layer.Options.create().inputShape(input.shape()));
      instance.init(tf);

      assertTrue(instance.isPlaceholder());
      Operand<TFloat32> result = instance.getOutput(TFloat32.class);
      assertTrue(result instanceof Placeholder);
      try (TFloat32 inputTensor =
          (TFloat32) session.getGraphSession().runner().fetch(input).run().get(0)) {
        Map<Operand<? extends TType>, Tensor> feedMap =
            Collections.singletonMap(result, inputTensor);
        session.evaluate(tf.constant(array), tf.identity(result), feedMap);
      }
    }
  }

  @Test
  void getInputType() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      float[][] array = new float[][] {{0, 1}, {2, 3}};
      Operand<TFloat32> input = tf.constant(array);
      Input<TFloat32> instance = new Input<>(input, TFloat32.class);
      assertEquals(TFloat32.class, instance.getInputType());
    }
  }
}
