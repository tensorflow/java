/*
 Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================
*/
package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.index.Indices;
import org.tensorflow.op.Ops;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.nn.NthElement;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;

public class CustomGradientTest {

  @Test
  public void testAlreadyExisting() {
    assertFalse(
        TensorFlow.registerCustomGradient(
            Cast.Inputs.class,
            (tf, op, gradInputs) -> {
              Operand<?> out = gradInputs.get(0);
              Operand<?> a = tf.stridedSlice(out, Indices.slice(0, 1));
              Operand<?> b = tf.stridedSlice(out, Indices.slice(1, 2));
              return Arrays.asList(a, b, tf.constant(0f));
            }));
  }

  @Test
  public void testCustomGradient() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      assertTrue(
          TensorFlow.registerCustomGradient(
              NthElement.Inputs.class,
              (tf, op, gradInputs) ->
                  Arrays.asList(tf.withName("inAGrad").constant(0f), tf.constant(0f))));

      Ops tf = Ops.create(g);
      Output<TFloat32> x = tf.placeholder(TFloat32.class).output();
      Output<TFloat32> y =
          tf.math.add(tf.nn.nthElement(x, tf.constant(2)), tf.constant(4f)).asOutput();

      Output<?>[] grads0 = g.addGradients(y, toArray(x));
      assertNotNull(grads0);
      assertEquals(1, grads0.length);
      assertEquals(DataType.DT_FLOAT, grads0[0].dataType());

      try (TFloat32 c1 = TFloat32.vectorOf(3.0f, 2.0f, 1.0f, 0.0f);
          Result outputs = s.runner().feed(x, c1).fetch(grads0[0]).run()) {

        assertEquals(1, outputs.size());
        assertEquals(0.0f, ((TFloat32) outputs.get(0)).getFloat(), 0.0f);
      }
    }
  }

  private static Output<?>[] toArray(Output<?>... outputs) {
    return outputs;
  }
}
