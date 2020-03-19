/* Copyright 2016 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.tensorflow.op.Ops;
import org.tensorflow.op.linalg.MatMul;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/** Unit tests for {@link org.tensorflow.Graph}. */
@RunWith(JUnit4.class)
public class GraphTest {

  @Test
  public void graphDefRoundTrip() {
    byte[] graphDef;
    // Create a graph for A * X + B
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      tf.withName("Y").linalg.matMul(
          tf.withName("A").val(new int[2][2]),
          tf.withName("X").placeholder(TInt32.DTYPE),
          MatMul.transposeA(true).transposeB(false)
      );
      graphDef = g.toGraphDef();
    }
    // Import the GraphDef and find all the nodes.
    try (Graph g = new Graph()) {
      g.importGraphDef(graphDef);
      validateImportedGraph(g, "");
    }
    try (Graph g = new Graph()) {
      g.importGraphDef(graphDef, "BugsBunny");
      validateImportedGraph(g, "BugsBunny/");
    }
  }

  // Helper function whose implementation is based on knowledge of how
  // TestUtil.transpose_A_times_X is implemented.
  private static void validateImportedGraph(Graph g, String prefix) {
    Operation op = g.operation(prefix + "A");
    assertNotNull(op);
    assertEquals(prefix + "A", op.name());
    assertEquals("Const", op.type());
    assertEquals(1, op.numOutputs());
    assertEquals(op, op.output(0).op());

    op = g.operation(prefix + "X");
    assertNotNull(op);
    assertEquals(prefix + "X", op.name());
    assertEquals("Placeholder", op.type());
    assertEquals(1, op.numOutputs());
    assertEquals(op, op.output(0).op());

    op = g.operation(prefix + "Y");
    assertNotNull(op);
    assertEquals(prefix + "Y", op.name());
    assertEquals("MatMul", op.type());
    assertEquals(1, op.numOutputs());
    assertEquals(op, op.output(0).op());
  }

  @Test
  public void iterateOverOperations() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Iterator<Operation> iterator = g.operations();
      HashSet<Operation> operations;

      assertFalse(iterator.hasNext());

      operations = new HashSet<>();
      operations.add(tf.withName("Const-A").val(1.0f).op());
      operations.add(tf.withName("Const-B").val(23).op());
      operations.add(tf.withName("Const-C").val(1.618).op());

      iterator = g.operations();

      assertTrue(iterator.hasNext());
      assertTrue(operations.remove(iterator.next()));

      assertTrue(iterator.hasNext());
      assertTrue(operations.remove(iterator.next()));

      assertTrue(iterator.hasNext());
      assertTrue(operations.remove(iterator.next()));

      assertFalse(iterator.hasNext());
    }
  }

  @Test
  public void failImportOnInvalidGraphDefs() {
    try (Graph g = new Graph()) {
      try {
        g.importGraphDef(null);
      } catch (IllegalArgumentException e) {
        // expected exception.
      }

      try {
        g.importGraphDef(new byte[] {1});
      } catch (IllegalArgumentException e) {
        // expected exception.
      }
    }
  }

  @Test
  public void failOnUseAfterClose() {
    Graph g = new Graph();
    g.close();
    try {
      g.toGraphDef();
    } catch (IllegalStateException e) {
      // expected exception.
    }
  }

  @Test
  public void addGradientsToGraph() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);

      Output<TFloat32> x1 = tf.placeholder(TFloat32.DTYPE).output();
      Output<TFloat32> x2 = tf.placeholder(TFloat32.DTYPE).output();
      Output<TFloat32> y0 = tf.math.square(x1).y();
      Output<TFloat32> y1 = tf.math.square(y0).y();
      Output<TFloat32> y2 = tf.math.addN(Arrays.asList(y0, x2)).sum();
      
      Output<?>[] grads0 = g.addGradients(y1, toArray(x1));
      assertNotNull(grads0);
      assertEquals(1, grads0.length);
      assertEquals(TFloat32.DTYPE, grads0[0].dataType());

      Output<?>[] grads1 = g.addGradients(y2, toArray(x1, x2));
      assertNotNull(grads1);
      assertEquals(2, grads1.length);
      assertEquals(TFloat32.DTYPE, grads1[0].dataType());
      assertEquals(TFloat32.DTYPE, grads1[1].dataType());
      
      try (Tensor<TFloat32> c1 = TFloat32.scalarOf(3.0f);
          Tensor<TFloat32> c2 = TFloat32.scalarOf(2.0f);
          AutoCloseableList<Tensor<?>> outputs = new AutoCloseableList<>(
              s.runner()
                  .feed(x1, c1)
                  .feed(x2, c2)
                  .fetch(grads0[0])
                  .fetch(grads1[0])
                  .fetch(grads1[1])
                  .run())) {
     
        assertEquals(3, outputs.size());
        assertEquals(108.0f, outputs.get(0).expect(TFloat32.DTYPE).data().getFloat(), 0.0f);
        assertEquals(6.0f, outputs.get(1).expect(TFloat32.DTYPE).data().getFloat(), 0.0f);
        assertEquals(1.0f, outputs.get(2).expect(TFloat32.DTYPE).data().getFloat(), 0.0f);
      }
    }
  }

  @Test
  public void addGradientSumsToGraph() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);

      Output<TFloat32> x = tf.placeholder(TFloat32.DTYPE).output();
      Output<TFloat32> y0 = tf.math.square(x).y();
      Output<TFloat32> y1 = tf.math.square(y0).y();

      Output<?>[] grad = g.addGradients(null, toArray(y0, y1), toArray(x), null);
      assertNotNull(grad);
      assertEquals(1, grad.length);
      assertEquals(TFloat32.DTYPE, grad[0].dataType());

      try (Tensor<TFloat32> c = TFloat32.scalarOf(3.0f);
          Tensor<TFloat32> output = s.runner()
              .feed(x, c)
              .fetch(grad[0])
              .run()
              .get(0)
              .expect(TFloat32.DTYPE)) {
        assertEquals(114.0f, output.data().getFloat(), 0.0f);
      }
    }
  }

  @Test
  public void addGradientsWithInitialValuesToGraph() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);

      Output<TFloat32> x = tf.placeholder(TFloat32.DTYPE).output();
      Output<TFloat32> y0 = tf.math.square(x).y();
      Output<TFloat32> y1 = tf.math.square(y0).y();
      
      Output<?>[] grad0 = g.addGradients(y1, toArray(y0));
      assertNotNull(grad0);
      assertEquals(1, grad0.length);
      assertEquals(TFloat32.DTYPE, grad0[0].dataType());

      Output<?>[] grad1 = g.addGradients(null, toArray(y0), toArray(x), toArray(grad0[0]));
      assertNotNull(grad1);
      assertEquals(1, grad1.length);
      assertEquals(TFloat32.DTYPE, grad1[0].dataType());

      try (Tensor<TFloat32> c = TFloat32.scalarOf(3.0f);
          Tensor<TFloat32> output = s.runner()
              .feed(x, c)
              .fetch(grad1[0])
              .run()
              .get(0)
              .expect(TFloat32.DTYPE)) {
        assertEquals(108.0f, output.data().getFloat(), 0.0f);
      }
    }
  }

  @Test
  public void validateGradientsNames() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);

      Output<TFloat32> x = tf.placeholder(TFloat32.DTYPE).output();
      Output<TFloat32> y0 = tf.math.square(x).y();

      Output<?>[] grad0 = g.addGradients(null, toArray(y0), toArray(x), null);
      assertTrue(grad0[0].op().name().startsWith("gradients/"));

      Output<?>[] grad1 = g.addGradients(null, toArray(y0), toArray(x), null);
      assertTrue(grad1[0].op().name().startsWith("gradients_1/"));

      Output<?>[] grad2 = g.addGradients("more_gradients", toArray(y0), toArray(x), null);
      assertTrue(grad2[0].op().name().startsWith("more_gradients/"));

      Output<?>[] grad3 = g.addGradients("even_more_gradients", toArray(y0), toArray(x), null);
      assertTrue(grad3[0].op().name().startsWith("even_more_gradients/"));

      try {
        g.addGradients("even_more_gradients", toArray(y0), toArray(x), null);
      } catch (IllegalArgumentException e) {
        // expected exception
      }
    }
  }

  @Test
  public void buildWhileLoopSingleInput() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);

      Output<?> input = tf.placeholder(TInt32.DTYPE).output();

      @SuppressWarnings("unchecked")
      Output<?>[] loopOutputs = g.whileLoop(
          toArray(input),
          (condGraph, condInputs, condOutputs) -> {
            Ops tfc = Ops.create(condGraph);
            condOutputs[0] = tfc.math.less((Output<TInt32>)condInputs[0], tfc.val(16)).z();
          },
          (bodyGraph, bodyInputs, bodyOutputs) -> {
            Ops tfb = Ops.create(bodyGraph);
            bodyOutputs[0] = tfb.math.square((Output<TInt32>)bodyInputs[0]).y();
          },
          "test_loop");

      try (Tensor<TInt32> c = TInt32.scalarOf(2);
          Tensor<TInt32> output = s.runner()
              .feed(input, c)
              .fetch(loopOutputs[0])
              .run()
              .get(0)
              .expect(TInt32.DTYPE)) {
        assertEquals(16, output.data().getInt()); // ((2^2)^2)
      }
    }
  }

  @Test
  public void buildWhileLoopMultipleInputs() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);

      Output<?> input1 = tf.placeholder(TInt32.DTYPE).output();
      Output<?> input2 = tf.placeholder(TInt32.DTYPE).output();
      Output<?>[] inputs = toArray(input1, input2);

      @SuppressWarnings("unchecked")
      Output<?>[] loopOutputs = g.whileLoop(
          inputs,
          (condGraph, condInputs, condOutputs) -> {
            Ops tfc = Ops.create(condGraph);
            condOutputs[0] = tfc.math.less((Output<TInt32>)condInputs[0], tfc.val(16)).z();
          },
          (bodyGraph, bodyInputs, bodyOutputs) -> {
            Ops tfb = Ops.create(bodyGraph);
            bodyOutputs[0] = tfb.math.square((Output<TInt32>)bodyInputs[0]).y();
            bodyOutputs[1] = tfb.math.square((Output<TInt32>)bodyInputs[1]).y();
          },
          "test_loop");

      try (Tensor<TInt32> c1 = TInt32.scalarOf(2);
          Tensor<TInt32> c2 = TInt32.scalarOf(5);
          AutoCloseableList<Tensor<?>> outputs =
              new AutoCloseableList<>(
                  s.runner()
                      .feed(input1, c1)
                      .feed(input2, c2)
                      .fetch(loopOutputs[0])
                      .fetch(loopOutputs[1])
                      .run())) {
        assertEquals(2, outputs.size());
        assertEquals(16, outputs.get(0).expect(TInt32.DTYPE).data().getInt()); // ((2^2)^2)
        assertEquals(625, outputs.get(1).expect(TInt32.DTYPE).data().getInt()); // ((5^2)^2)
      }
    }
  }

  private static Output<?>[] toArray(Output<?>... outputs) {
    return outputs;
  }
}
