/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.op.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

public class GradientsTest {

  @Test
  public void createGradients() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Ops tf = Ops.create(g);

      Output<TFloat32> x = tf.placeholder(TFloat32.class).output();
      Output<TFloat32> y0 = tf.math.square(x).y();
      Output<TFloat32> y1 = tf.math.square(y0).y();

      Gradients grads = Gradients.create(tf.scope(), y1, Arrays.asList(x, y0));

      assertNotNull(grads);
      assertNotNull(grads.dy());
      assertEquals(2, grads.dy().size());

      try (TFloat32 c = TFloat32.scalarOf(3.0f);
          Session.Result outputs =
              sess.runner().feed(x, c).fetch(grads.dy(0)).fetch(grads.dy(1)).run()) {

        assertEquals(108.0f, ((TFloat32)outputs.get(0)).getFloat(), 0.0f);
        assertEquals(18.0f, ((TFloat32)outputs.get(1)).getFloat(), 0.0f);
      }
    }
  }

  @Test
  public void createGradientsWithSum() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Ops tf = Ops.create(g);

      Output<TFloat32> x = tf.placeholder(TFloat32.class).output();
      Output<TFloat32> y0 = tf.math.square(x).y();
      Output<TFloat32> y1 = tf.math.square(y0).y();

      Gradients grads = Gradients.create(tf.scope(), Arrays.asList(y0, y1), Arrays.asList(x));

      assertNotNull(grads);
      assertNotNull(grads.dy());
      assertEquals(1, grads.dy().size());

      try (TFloat32 c = TFloat32.scalarOf(3.0f);
          Session.Result outputs = sess.runner().feed(x, c).fetch(grads.dy(0)).run()) {

        assertEquals(114.0f, ((TFloat32)outputs.get(0)).getFloat(), 0.0f);
      }
    }
  }

  @Test
  public void createGradientsWithInitialValues() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Ops tf = Ops.create(g);

      Output<TFloat32> x = tf.placeholder(TFloat32.class).output();
      Output<TFloat32> y0 = tf.math.square(x).y();
      Output<TFloat32> y1 = tf.math.square(y0).y();

      Gradients grads0 = Gradients.create(tf.scope(), y1, Arrays.asList(y0));
      Gradients grads1 = Gradients.create(tf.scope(), y0, Arrays.asList(x), Gradients.dx(grads0.dy()));

      assertNotNull(grads1);
      assertNotNull(grads1.dy());
      assertEquals(1, grads1.dy().size());

      try (TFloat32 c = TFloat32.scalarOf(3.0f);
          Session.Result outputs =
              sess.runner().feed(x, c).fetch(grads1.dy(0)).run()) {

        assertEquals(108.0f, ((TFloat32)outputs.get(0)).getFloat(), 0.0f);
      }
    }
  }

  @Test
  public void validateGradientsNames() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g).withSubScope("sub");

      Output<TFloat32> x = tf.placeholder(TFloat32.class).output();
      Output<TFloat32> y = tf.math.square(x).y();

      Gradients grad0 = Gradients.create(tf.scope(), y, Arrays.asList(x));
      assertTrue(grad0.dy(0).op().name().startsWith("sub/Gradients/"));

      Gradients grad1 = Gradients.create(tf.scope().withName("MyGradients"), y, Arrays.asList(x));
      assertTrue(grad1.dy(0).op().name().startsWith("sub/MyGradients/"));
    }
  }
}
