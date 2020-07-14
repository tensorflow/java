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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Split;
import org.tensorflow.op.core.Variable;
import org.tensorflow.op.linalg.MatMul;
import org.tensorflow.op.math.Add;
import org.tensorflow.proto.framework.ConfigProto;
import org.tensorflow.proto.framework.RunOptions;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.types.TInt32;

/** Unit tests for {@link org.tensorflow.Session}. */
public class SessionTest {

  @Test
  public void runUsingOperationNames() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);
      transpose_A_times_X(tf, new int[][] {{2}, {3}});
      try (Tensor<TInt32> x = TInt32.tensorOf(StdArrays.ndCopyOf(new int[][] {{5}, {7}}));
          AutoCloseableList<Tensor<?>> outputs =
              new AutoCloseableList<>(s.runner().feed("X", x).fetch("Y").run())) {
        assertEquals(1, outputs.size());
        assertEquals(31, outputs.get(0).expect(TInt32.DTYPE).data().getInt(0, 0));
      }
    }
  }

  @Test
  public void runUsingOperationHandles() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);
      transpose_A_times_X(tf, new int[][] {{2}, {3}});
      Output<TInt32> feed = g.operation("X").output(0);
      Output<TInt32> fetch = g.operation("Y").output(0);
      try (Tensor<TInt32> x = TInt32.tensorOf(StdArrays.ndCopyOf(new int[][] {{5}, {7}}));
          AutoCloseableList<Tensor<?>> outputs =
              new AutoCloseableList<>(s.runner().feed(feed, x).fetch(fetch).run())) {
        assertEquals(1, outputs.size());
        assertEquals(31, outputs.get(0).expect(TInt32.DTYPE).data().getInt(0, 0));
      }
    }
  }

  @Test
  public void runUsingColonSeparatedNames() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);
      Split<TInt32> split = tf.split(tf.constant(0), tf.array(1, 2, 3, 4), 2L);
      tf.math.add(split.output().get(0), split.output().get(1));

      // Fetch using colon separated names.
      try (Tensor<TInt32> fetched =
          s.runner().fetch("Split:1").run().get(0).expect(TInt32.DTYPE)) {
        assertEquals(3, fetched.data().getInt(0));
        assertEquals(4, fetched.data().getInt(1));
      }
      // Feed using colon separated names.
      try (Tensor<TInt32> fed = TInt32.vectorOf(4, 3, 2, 1);
          Tensor<TInt32> fetched =
              s.runner()
                  .feed("Split:0", fed)
                  .feed("Split:1", fed)
                  .fetch("Add")
                  .run()
                  .get(0)
                  .expect(TInt32.DTYPE)) {
        assertEquals(NdArrays.vectorOf(8, 6, 4, 2), fetched.data());
      }
    }
  }

  @Test
  public void runWithMetadata() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);
      transpose_A_times_X(tf, new int[][] {{2}, {3}});
      try (Tensor<TInt32> x = TInt32.tensorOf(StdArrays.ndCopyOf(new int[][] {{5}, {7}}))) {
        Session.Run result =
            s.runner()
                .feed("X", x)
                .fetch("Y")
                .setOptions(fullTraceRunOptions())
                .runAndFetchMetadata();
        // Sanity check on outputs.
        AutoCloseableList<Tensor<?>> outputs = new AutoCloseableList<>(result.outputs);
        assertEquals(1, outputs.size());
        assertEquals(31, outputs.get(0).expect(TInt32.DTYPE).data().getInt(0, 0));
        // Sanity check on metadata
        assertNotNull(result.metadata);
        assertTrue(result.metadata.hasStepStats(), result.metadata.toString());
        outputs.close();
      }
    }
  }

  @Test
  public void runMultipleOutputs() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);
      tf.withName("c1").constant(2718);
      tf.withName("c2").constant(31415);
      AutoCloseableList<Tensor<?>> outputs =
          new AutoCloseableList<>(s.runner().fetch("c2").fetch("c1").run());
      assertEquals(2, outputs.size());
      assertEquals(31415, outputs.get(0).expect(TInt32.DTYPE).data().getInt());
      assertEquals(2718, outputs.get(1).expect(TInt32.DTYPE).data().getInt());
      outputs.close();
    }
  }

  @Test
  public void failOnUseAfterClose() {
    try (Graph g = new Graph()) {
      Session s = new Session(g);
      s.close();
      try {
        s.runner().run();
        fail("methods on a session should fail after close() is called");
      } catch (IllegalStateException e) {
        // expected exception
      }
    }
  }

  @Test
  public void createWithConfigProto() {
    try (Graph g = new Graph();
        Session s = new Session(g, singleThreadConfigProto())) {}
  }

  @Test
  public void runInit() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);

      Variable<TInt32> var1 = tf.variable(Shape.scalar(), TInt32.DTYPE);
      tf.initAdd(tf.assign(var1, tf.constant(10)));
      Variable<TInt32> var2 = tf.variable(tf.constant(20));
      Add<TInt32> add = tf.math.add(var1, var2);

      try (Session s = new Session(g)) {
        s.run(tf.init());

        try (Tensor<TInt32> t = s.runner().fetch(add).run().get(0).expect(TInt32.DTYPE)) {
          assertEquals(30, t.data().getInt());
        }
      }
    }
  }

  @Test
  public void runInitByName() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);

      Variable<TInt32> var1 = tf.variable(Shape.scalar(), TInt32.DTYPE);
      tf.initAdd(tf.assign(var1, tf.constant(10)));
      Variable<TInt32> var2 = tf.variable(tf.constant(20));
      Add<TInt32> add = tf.math.add(var1, var2);
      tf.withName("init_test").init();

      try (Session s = new Session(g)) {
        s.run("init_test");

        try (Tensor<TInt32> t = s.runner().fetch(add).run().get(0).expect(TInt32.DTYPE)) {
          assertEquals(30, t.data().getInt());
        }
        try {
          s.run("wrong_name");
          fail();
        } catch (IllegalArgumentException e) {
          // as expected
        }
      }
    }
  }

  private static RunOptions fullTraceRunOptions() {
    return RunOptions.newBuilder()
        .setTraceLevel(RunOptions.TraceLevel.FULL_TRACE)
        .build();
  }

  private static ConfigProto singleThreadConfigProto() {
    return ConfigProto.newBuilder()
        .setInterOpParallelismThreads(1)
        .setIntraOpParallelismThreads(1)
        .build();
  }

  private static void transpose_A_times_X(Ops tf, int[][] a) {
    tf.withName("Y").linalg.matMul(
        tf.withName("A").constant(a),
        tf.withName("X").placeholder(TInt32.DTYPE),
        MatMul.transposeA(true).transposeB(false)
    );
  }
}
