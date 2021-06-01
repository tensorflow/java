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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;

/**
 * Tests for using Operands in different environments
 */
public class WrongEnvTest {

  /**
   * Should work fine
   */
  @Test
  public void testTwoEagers() {
    try (EagerSession e1 = EagerSession.create();
        EagerSession e2 = EagerSession.create()) {
      Ops tf1 = Ops.create(e1);
      Ops tf2 = Ops.create(e2);

      Operand<TInt32> a = tf1.constant(5);
      Operand<TInt32> b = tf2.constant(6);

      Operand<TInt32> c = tf2.math.add(a, b);

      try (TInt32 tensor = c.asTensor()) {
        assertEquals(11, tensor.getInt());
      }

    }
  }

  @Test
  public void testEagerInGraph() {
    try (EagerSession e1 = EagerSession.create();
        Graph e2 = new Graph()) {
      Ops tf1 = Ops.create(e1);
      Ops tf2 = Ops.create(e2);

      Operand<TInt32> a = tf1.constant(5);
      Operand<TInt32> b = tf2.constant(6);

      Operand<TInt32> c = tf2.math.add(a, b);

      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("was from an eager session, can't use in a graph"));
    }
  }

  @Test
  public void testGraphInEager() {
    try (Graph e1 = new Graph();
        EagerSession e2 = EagerSession.create()) {
      Ops tf1 = Ops.create(e1);
      Ops tf2 = Ops.create(e2);

      Operand<TInt32> a = tf1.constant(5);
      Operand<TInt32> b = tf2.constant(6);

      Operand<TInt32> c = tf2.math.add(a, b);

      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("Can't use graph operation"));
    }
  }

  @Test
  public void testTwoGraphs() {
    try (Graph e1 = new Graph();
        Graph e2 = new Graph()) {
      Ops tf1 = Ops.create(e1);
      Ops tf2 = Ops.create(e2);

      Operand<TInt32> a = tf1.constant(5);
      Operand<TInt32> b = tf2.constant(6);

      Operand<TInt32> c = tf2.math.add(a, b);

      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("was from a different graph"));
    }
  }

}
