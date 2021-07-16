/* Copyright 2019-2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================
*/
package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Init;
import org.tensorflow.op.core.Split;
import org.tensorflow.op.core.Variable;
import org.tensorflow.op.linalg.MatMul;
import org.tensorflow.op.math.Add;
import org.tensorflow.proto.framework.ConfigProto;
import org.tensorflow.proto.framework.GraphDef;
import org.tensorflow.proto.framework.RunOptions;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

/** Unit tests for {@link org.tensorflow.Session}. */
public class SessionTest {

  @Test
  public void runUsingFunction() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);
      transpose_A_times_X(tf, new int[][] {{2}, {3}});
      Signature sig =
          Signature.builder("sess").input("X", g.output("X")).output("Y", g.output("Y")).build();
      SessionFunction func = s.function(sig);

      try (TInt32 x = TInt32.tensorOf(StdArrays.ndCopyOf(new int[][] {{5}, {7}}));
          TInt32 y = (TInt32) func.call(x)) {
        assertEquals(31, y.getInt(0, 0));
      }
    }
  }

  @Test
  public void runUsingOperationNames() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);
      transpose_A_times_X(tf, new int[][] {{2}, {3}});
      try (TInt32 x = TInt32.tensorOf(StdArrays.ndCopyOf(new int[][] {{5}, {7}}));
          AutoCloseableList<Tensor> outputs =
              new AutoCloseableList<>(s.runner().feed("X", x).fetch("Y").run())) {
        assertEquals(1, outputs.size());
        assertEquals(31, ((TInt32) outputs.get(0)).getInt(0, 0));
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
      try (TInt32 x = TInt32.tensorOf(StdArrays.ndCopyOf(new int[][] {{5}, {7}}));
          AutoCloseableList<Tensor> outputs =
              new AutoCloseableList<>(s.runner().feed(feed, x).fetch(fetch).run())) {
        assertEquals(1, outputs.size());
        assertEquals(31, ((TInt32) outputs.get(0)).getInt(0, 0));
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
      try (TInt32 fetched = (TInt32) s.runner().fetch("Split:1").run().get(0)) {
        assertEquals(3, fetched.getInt(0));
        assertEquals(4, fetched.getInt(1));
      }
      // Feed using colon separated names.
      try (TInt32 fed = TInt32.vectorOf(4, 3, 2, 1);
          TInt32 fetched =
              (TInt32)
                  s.runner().feed("Split:0", fed).feed("Split:1", fed).fetch("Add").run().get(0)) {
        assertEquals(NdArrays.vectorOf(8, 6, 4, 2), fetched);
      }
    }
  }

  @Test
  public void runWithMetadata() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);
      transpose_A_times_X(tf, new int[][] {{2}, {3}});
      try (TInt32 x = TInt32.tensorOf(StdArrays.ndCopyOf(new int[][] {{5}, {7}}))) {
        Session.Run result =
            s.runner()
                .feed("X", x)
                .fetch("Y")
                .setOptions(fullTraceRunOptions())
                .runAndFetchMetadata();
        // Sanity check on outputs.
        AutoCloseableList<Tensor> outputs = new AutoCloseableList<>(result.outputs);
        assertEquals(1, outputs.size());
        assertEquals(31, ((TInt32) outputs.get(0)).getInt(0, 0));
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
      AutoCloseableList<Tensor> outputs =
          new AutoCloseableList<>(s.runner().fetch("c2").fetch("c1").run());
      assertEquals(2, outputs.size());
      assertEquals(31415, ((TInt32) outputs.get(0)).getInt());
      assertEquals(2718, ((TInt32) outputs.get(1)).getInt());
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

      Variable<TInt32> var1 = tf.variable(Shape.scalar(), TInt32.class);
      tf.initAdd(tf.assign(var1, tf.constant(10)));
      Variable<TInt32> var2 = tf.variable(tf.constant(20));
      Add<TInt32> add = tf.math.add(var1, var2);

      try (Session s = new Session(g)) {
        s.run(tf.init());

        try (TInt32 t = (TInt32) s.runner().fetch(add).run().get(0)) {
          assertEquals(30, t.getInt());
        }
      }
    }
  }

  @Test
  public void runInitByName() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);

      Variable<TInt32> var1 = tf.variable(Shape.scalar(), TInt32.class);
      tf.initAdd(tf.assign(var1, tf.constant(10)));
      Variable<TInt32> var2 = tf.variable(tf.constant(20));
      Add<TInt32> add = tf.math.add(var1, var2);
      tf.withName("init_test").init();

      try (Session s = new Session(g)) {
        s.run("init_test");

        try (TInt32 t = (TInt32) s.runner().fetch(add).run().get(0)) {
          assertEquals(30, t.getInt());
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

  @Test
  public void saveAndRestore() throws IOException {
    Path testFolder = Files.createTempDirectory("tf-session-save-restore-test");
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Variable<TFloat32> x =
          tf.withName("x")
              .variable(tf.random.randomUniform(tf.constant(Shape.of(3, 3L)), TFloat32.class));
      Variable<TFloat32> y =
          tf.withName("y")
              .variable(tf.random.randomUniform(tf.constant(Shape.of(3, 3L)), TFloat32.class));
      Init init = tf.init();

      try (Session s = new Session(g)) {
        s.run(init);
        s.save(testFolder.resolve("checkpoint").toString());
        GraphDef graphDef = g.toGraphDef();

        try (Graph restoredGraph = new Graph()) {
          restoredGraph.importGraphDef(graphDef);
          try (Session restoredSession = new Session(restoredGraph)) {
            restoredSession.restore(testFolder.resolve("checkpoint").toString());
            try (AutoCloseableList<Tensor> oldList =
                    new AutoCloseableList<>(s.runner().fetch("x").fetch("y").run());
                AutoCloseableList<Tensor> newList =
                    new AutoCloseableList<>(restoredSession.runner().fetch("x").fetch("y").run())) {
              assertEquals(oldList.get(0), newList.get(0));
              assertEquals(oldList.get(1), newList.get(1));
            }
          }
        }
      }
    }
    assertTrue(Files.exists(testFolder.resolve("checkpoint.index")));
    assertTrue(Files.exists(testFolder.resolve("checkpoint.data-00000-of-00001")));

    // Cleanup test dir
    Files.walk(testFolder)
        .sorted(Comparator.reverseOrder())
        .map(Path::toFile)
        .forEach(File::delete);
  }

  @Test
  public static void testFetchVariable() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);
      Operand<?> variable = tf.varHandleOp(TInt32.class, Shape.scalar());
      Op assign = tf.assignVariableOp(variable, tf.constant(2));

      try (TInt32 value = (TInt32) s.runner().addTarget(assign).fetch(variable).run().get(0)) {
        assertEquals(2, value.getInt());
      }
    }
  }

  private static int numOperations(Graph g) {
    int numOperations = 0;
    for (Iterator<GraphOperation> it = g.operations(); it.hasNext(); ) {
      Operation o = it.next();
      numOperations++;
    }
    return numOperations;
  }

  @Test
  public static void testFetchVariableReusingRead() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);
      Operand<?> variable = tf.varHandleOp(TInt32.class, Shape.scalar());
      Op assign = tf.assignVariableOp(variable, tf.constant(2));

      Operand<TInt32> read = tf.readVariableOp(variable, TInt32.class);

      int ops = numOperations(g);

      try (TInt32 value = (TInt32) s.runner().addTarget(assign).fetch(variable).run().get(0)) {
        assertEquals(2, value.getInt());
      }

      assertEquals(0, numOperations(g) - ops);
    }
  }

  private static RunOptions fullTraceRunOptions() {
    return RunOptions.newBuilder().setTraceLevel(RunOptions.TraceLevel.FULL_TRACE).build();
  }

  private static ConfigProto singleThreadConfigProto() {
    return ConfigProto.newBuilder()
        .setInterOpParallelismThreads(1)
        .setIntraOpParallelismThreads(1)
        .build();
  }

  private static void transpose_A_times_X(Ops tf, int[][] a) {
    tf.withName("Y")
        .linalg
        .matMul(
            tf.withName("A").constant(a),
            tf.withName("X").placeholder(TInt32.class),
            MatMul.transposeA(true).transposeB(false));
  }
}
