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
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.index.Indices;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Concat;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;

public class CustomGradientTest {

  @Test
  public void addGradientsToGraph() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      TensorFlow.registerCustomGradient(Concat.class,
          (tf, op, gradInputs) -> {
            Operand<?> out = gradInputs.get(0);
            Operand<?> a = tf.stridedSlice(out, Indices.slice(0, 1));
            Operand<?> b = tf.stridedSlice(out, Indices.slice(1, 2));
            return Arrays.asList(a, b);
          });
      Ops tf = Ops.create(g);

      Output<TFloat32> x1 = tf.placeholder(TFloat32.class).output();
      Output<TFloat32> x2 = tf.placeholder(TFloat32.class).output();
      Operand<TFloat32> x = tf.concat(Arrays.asList(x1, x2), tf.constant(0));
      Output<TFloat32> y = tf.math.square(x).y();

      Output<?>[] grads0 = g.addGradients(y, toArray(x1, x2));
      assertNotNull(grads0);
      assertEquals(2, grads0.length);
      assertEquals(DataType.DT_FLOAT, grads0[0].dataType());
      assertEquals(DataType.DT_FLOAT, grads0[1].dataType());

      try (TFloat32 c1 = TFloat32.scalarOf(3.0f);
          TFloat32 c2 = TFloat32.scalarOf(2.0f);
          AutoCloseableList<Tensor> outputs = new AutoCloseableList<>(
              s.runner()
                  .feed(x1, c1)
                  .feed(x2, c2)
                  .fetch(grads0[0])
                  .fetch(grads0[1])
                  .run())) {

        assertEquals(2, outputs.size());
        assertEquals(3.0f, ((TFloat32) outputs.get(0)).getFloat(), 0.0f);
        assertEquals(2.0f, ((TFloat32) outputs.get(1)).getFloat(), 0.0f);
      }
    }
  }

  private static Output<?>[] toArray(Output<?>... outputs) {
    return outputs;
  }
}
