/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.tensorflow.exceptions.TFInvalidArgumentException;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Barrier;
import org.tensorflow.op.debugging.DebugIdentity;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TString;

/** Unit tests for {@link org.tensorflow.GraphOperation}. */
public class GraphOperationTest {

  @Test
  public void outputListLengthFailsOnInvalidName() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Operation op = tf.math.add(tf.constant(1), tf.constant(2)).op();
      assertEquals(1, op.outputListLength("z"));

      try {
        op.outputListLength("unknown");
        fail("Did not catch bad name");
      } catch (TFInvalidArgumentException iae) {
        // expected
      }
    }
  }

  @Test
  public void operationEquality() {
    GraphOperation op1;
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      op1 = (GraphOperation) tf.withName("op1").constant(1).op();
      GraphOperation op2 = (GraphOperation) tf.withName("op2").constant(2).op();
      GraphOperation op3 = new GraphOperation(g, op1.getUnsafeNativeHandle());
      GraphOperation op4 = g.operation("op1");
      assertEquals(op1, op1);
      assertNotEquals(op1, op2);
      assertEquals(op1, op3);
      assertEquals(op1.hashCode(), op3.hashCode());
      assertEquals(op1, op4);
      assertEquals(op1.hashCode(), op4.hashCode());
      assertEquals(op3, op4);
      assertNotEquals(op2, op3);
      assertNotEquals(op2, op4);
    }
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Operation newOp1 = tf.withName("op1").constant(1).op();
      assertNotEquals(op1, newOp1);
    }
  }

  @Test
  public void operationCollection() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      GraphOperation op1 = (GraphOperation) tf.withName("op1").constant(1).op();
      GraphOperation op2 = (GraphOperation) tf.withName("op2").constant(2).op();
      GraphOperation op3 = new GraphOperation(g, op1.getUnsafeNativeHandle());
      GraphOperation op4 = g.operation("op1");
      Set<Operation> ops = new HashSet<>();
      ops.addAll(Arrays.asList(op1, op2, op3, op4));
      assertEquals(2, ops.size());
      assertTrue(ops.contains(op1));
      assertTrue(ops.contains(op2));
      assertTrue(ops.contains(op3));
      assertTrue(ops.contains(op4));
    }
  }

  @Test
  public void operationToString() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Operation op = tf.withName("c").array(1).op();
      assertNotNull(op.toString());
    }
  }

  @Test
  public void outputEquality() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Output<TInt32> output = tf.withName("c").constant(1).asOutput();
      Output<TInt32> output1 = output.op().output(0);
      Output<TInt32> output2 = g.operation("c").output(0);
      assertEquals(output, output1);
      assertEquals(output.hashCode(), output1.hashCode());
      assertEquals(output, output2);
      assertEquals(output.hashCode(), output2.hashCode());
    }
  }

  @Test
  public void outputCollection() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Output<TInt32> output = tf.withName("c").constant(1).asOutput();
      Output<TInt32> output1 = output.op().output(0);
      Output<TInt32> output2 = g.operation("c").output(0);
      Set<Output<TInt32>> ops = new HashSet<>();
      ops.addAll(Arrays.asList(output, output1, output2));
      assertEquals(1, ops.size());
      assertTrue(ops.contains(output));
      assertTrue(ops.contains(output1));
      assertTrue(ops.contains(output2));
    }
  }

  @Test
  public void outputToString() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Output<TInt32> output = tf.withName("c").constant(1).asOutput();
      assertNotNull(output.toString());
    }
  }

  @Test
  public void outputListLength() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      assertEquals(1, tf.split(tf.constant(0), tf.array(0, 1), 1L).op().outputListLength("output"));
      assertEquals(2, tf.split(tf.constant(0), tf.array(0, 1), 2L).op().outputListLength("output"));
      assertEquals(
          3, tf.split(tf.constant(0), tf.array(0, 1, 2), 3L).op().outputListLength("output"));
    }
  }

  @Test
  public void inputListLength() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      assertEquals(
          1, tf.split(tf.constant(0), tf.array(0, 1), 1L).op().inputListLength("split_dim"));
      try {
        tf.split(tf.constant(0), tf.array(0, 1), 2L).op().inputListLength("inputs");
      } catch (TFInvalidArgumentException iae) {
        // expected
      }
    }
  }

  @Test
  public void outputList() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Operation split = tf.split(tf.constant(0), tf.array(0, 1, 2), 3L).op();
      Output<?>[] outputs = split.outputList(1, 2);
      assertNotNull(outputs);
      assertEquals(2, outputs.length);
      for (int i = 0; i < outputs.length; ++i) {
        assertEquals(i + 1, outputs[i].index());
      }
    }
  }

  @Test
  public void outputTensorNotSupported() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Operation split = tf.split(tf.constant(0), tf.array(0, 1, 2), 3L).op();
      try {
        split.output(0).asTensor();
        fail();
      } catch (IllegalStateException e) {
      }
    }
  }

  @Test
  public void inputs() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);

      Operand<TFloat32> a = tf.constant(1f);
      Operand<TFloat32> b = tf.constant(2f);
      Operand<TFloat32> c = tf.math.add(a, b);

      GraphOperation op = (GraphOperation) c.op();

      assertEquals(2, op.numInputs());
      assertEquals(Arrays.asList(a.asOutput(), b.asOutput()), op.inputs());
      assertEquals(a.asOutput(), op.input(0));
      assertEquals(b.asOutput(), op.input(1));
    }
  }

  @Test
  public void consumers() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);

      Operand<TFloat32> a = tf.constant(1f);
      Operand<TFloat32> b = tf.constant(2f);
      Operand<TFloat32> c = tf.math.add(a, b);

      GraphOperation op = (GraphOperation) a.op();

      assertEquals(1, op.numConsumers());
      assertEquals(new LinkedHashSet<>(Collections.singletonList(c.op())), op.consumers());
    }
  }

  @Test
  public void controlInputs() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);

      Operand<TFloat32> a = tf.constant(1f);
      Operand<TFloat32> b = tf.constant(2f);
      Operand<TFloat32> c = tf.withControlDependencies(Arrays.asList(a, b)).constant(3f);

      GraphOperation op = (GraphOperation) c.op();

      assertEquals(2, op.numControlInputs());
      assertEquals(new LinkedHashSet<>(Arrays.asList(a.op(), b.op())), op.controlInputs());
    }
  }

  @Test
  public void controlConsumers() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);

      Operand<TFloat32> a = tf.constant(1f);
      Operand<TFloat32> b = tf.constant(2f);
      Operand<TFloat32> c = tf.withControlDependencies(Arrays.asList(a, b)).constant(3f);

      GraphOperation op = (GraphOperation) a.op();

      assertEquals(1, op.numControlConsumers());
      assertEquals(new LinkedHashSet<>(Collections.singletonList(c.op())), op.controlConsumers());
    }
  }

  @Test
  public void getAttributes() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);

      Operand<TFloat32> a = tf.array(1f);
      Operand<TFloat32> c =
          DebugIdentity.create(
              tf.scope(),
              a,
              DebugIdentity.debugUrls(Arrays.asList("a", "b")),
              DebugIdentity.outputSlot(0L));
      Operand<TString> barrier =
          tf.barrier(
              Arrays.asList(TInt32.class, TInt32.class),
              Barrier.shapes(Shape.of(1, 2), Shape.of(3, 4)));

      GraphOperation op1 = (GraphOperation) c.op();

      assertEquals(0, op1.getAttrInt("output_slot"));
      assertArrayEquals(new String[] {"a", "b"}, op1.getAttrStringList("debug_urls"));

      GraphOperation op2 = (GraphOperation) barrier.op();
      assertArrayEquals(
          new DataType[] {DataType.DT_INT32, DataType.DT_INT32},
          op2.getAttrTypeList("component_types"));
      assertArrayEquals(
          new Shape[] {Shape.of(1, 2), Shape.of(3, 4)}, op2.getAttrShapeList("shapes"));
    }
  }
}
