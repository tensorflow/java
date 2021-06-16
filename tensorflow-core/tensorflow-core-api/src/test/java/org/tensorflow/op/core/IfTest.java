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
=======================================================================
*/
package org.tensorflow.op.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.EagerSession;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.Signature;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;

public class IfTest {

  private static Operand<TInt32> basicIf(Ops tf, Operand<TInt32> a, Operand<TInt32> b) {
    ConcreteFunction thenBranch =
        ConcreteFunction.create(
            (ops) -> {
              Operand<TInt32> a1 = ops.placeholder(TInt32.class);
              Operand<TInt32> b1 = ops.placeholder(TInt32.class);
              return Signature.builder().input("a", a1).input("b", b1).output("y", a1).build();
            });

    ConcreteFunction elseBranch =
        ConcreteFunction.create(
            (ops) -> {
              Operand<TInt32> a1 = ops.placeholder(TInt32.class);
              Operand<TInt32> b1 = ops.placeholder(TInt32.class);
              Operand<TInt32> y = ops.math.neg(b1);
              return Signature.builder().input("a", a1).input("b", b1).output("y", y).build();
            });

    return (Operand<TInt32>)
        tf.ifOp(
                tf.math.greater(a, b),
                Arrays.asList(a, b),
                Arrays.asList(TInt32.class),
                thenBranch,
                elseBranch)
            .output()
            .get(0);
  }

  @Test
  public void testGraph() {
    try (Graph g = new Graph();
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);
      Operand<TInt32> a = tf.placeholder(TInt32.class);
      Operand<TInt32> b = tf.placeholder(TInt32.class);
      Operand<TInt32> c = basicIf(tf, a, b);

      assertEquals(StatelessIf.OP_NAME, c.op().type());

      try (TInt32 out =
          (TInt32)
              s.runner()
                  .feed(a, TInt32.scalarOf(2))
                  .feed(b, TInt32.scalarOf(1))
                  .fetch(c)
                  .run()
                  .get(0)) {
        assertEquals(2, out.getInt());
      }

      try (TInt32 out =
          (TInt32)
              s.runner()
                  .feed(a, TInt32.scalarOf(2))
                  .feed(b, TInt32.scalarOf(3))
                  .fetch(c)
                  .run()
                  .get(0)) {
        assertEquals(-3, out.getInt());
      }
    }
  }

  @Test
  public void testStatefullness() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Operand<TInt32> a = tf.placeholder(TInt32.class);
      Operand<TInt32> b = tf.placeholder(TInt32.class);

      ConcreteFunction thenBranch =
          ConcreteFunction.create(
              (ops) -> {
                Operand<TInt32> a1 = ops.placeholder(TInt32.class);
                Operand<TInt32> b1 = ops.placeholder(TInt32.class);
                Operand<TInt32> result =
                    (Operand<TInt32>)
                        ops.statefulIf(
                                ops.constant(false),
                                Collections.emptyList(),
                                Arrays.asList(TInt32.class),
                                ConcreteFunction.create(
                                    (ops1) ->
                                        Signature.builder().output("y", ops1.constant(1)).build()),
                                ConcreteFunction.create(
                                    (ops1) ->
                                        Signature.builder().output("y", ops1.constant(1)).build()))
                            .output()
                            .get(0);
                return Signature.builder()
                    .input("a", a1)
                    .input("b", b1)
                    .output("y", result)
                    .build();
              });

      ConcreteFunction elseBranch =
          ConcreteFunction.create(
              (ops) -> {
                Operand<TInt32> a1 = ops.placeholder(TInt32.class);
                Operand<TInt32> b1 = ops.placeholder(TInt32.class);
                Operand<TInt32> y = ops.math.neg(b1);
                return Signature.builder().input("a", a1).input("b", b1).output("y", y).build();
              });

      Operand<TInt32> output =
          (Operand<TInt32>)
              tf.ifOp(
                      tf.math.greater(a, b),
                      Arrays.asList(a, b),
                      Arrays.asList(TInt32.class),
                      thenBranch,
                      elseBranch)
                  .output()
                  .get(0);

      assertEquals(StatefulIf.OP_NAME, output.op().type());
    }
  }

  @Test
  public void testEager() {
    try (EagerSession e = EagerSession.create()) {
      Ops tf = Ops.create(e);

      Operand<TInt32> out1 = basicIf(tf, tf.constant(2), tf.constant(1));

      assertEquals(StatelessIf.OP_NAME, out1.op().type());

      try (TInt32 out = out1.asTensor()) {
        assertEquals(2, out.getInt());
      }

      try (TInt32 out = basicIf(tf, tf.constant(2), tf.constant(3)).asTensor()) {
        assertEquals(-3, out.getInt());
      }
    }
  }
}
