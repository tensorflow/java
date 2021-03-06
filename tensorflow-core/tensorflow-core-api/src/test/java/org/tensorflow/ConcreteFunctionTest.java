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
==============================================================================*/
package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Init;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.Sub;
import org.tensorflow.types.TFloat32;

public class ConcreteFunctionTest {

  private static Signature plusFive(Ops tf) {
    Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
    Add<TFloat32> output = tf.math.add(input, tf.constant(5.0f));
    Init init = tf.init();  // for native resource management tests
    return Signature.builder().key("plusFive").input("x", input).output("y", output).build();
  }

  private static Signature minusTwo(Ops tf) {
    Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
    Sub<TFloat32> output = tf.math.sub(input, tf.constant(2.0f));
    return Signature.builder().key("minusTwo").input("x", input).output("y", output).build();
  }

  @SuppressWarnings("unchecked")
  private static Signature plusFiveMinusTwo(Ops tf) {
    Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
    try (ConcreteFunction plusFive = ConcreteFunction.create(ConcreteFunctionTest::plusFive);
        ConcreteFunction minusTwo = ConcreteFunction.create(ConcreteFunctionTest::minusTwo)) {
      Operand<TFloat32> result = (Operand<TFloat32>) minusTwo.call(tf, plusFive.call(tf, input));
      return Signature.builder().key("plusFiveMinusTwo").input("x", input).output("y", result).build();
    }
  }

  @Test
  public void createFunction() {
    try (ConcreteFunction f = ConcreteFunction.create(ConcreteFunctionTest::plusFive);
        TFloat32 x = TFloat32.scalarOf(3.0f)) {
      assertEquals(8.0f, ((TFloat32) f.call(x)).getFloat());
    }
  }

  @Test
  public void createFunctionFromGraph() {
    try (Graph g = new Graph()) {
      Signature signature = plusFive(Ops.create(g));
      try (ConcreteFunction f = ConcreteFunction.create(signature, g);
          TFloat32 x = TFloat32.scalarOf(3.0f)) {
        assertEquals(8.0f, ((TFloat32)f.call(x)).getFloat());
      }
    }
  }

  @Test
  public void createFunctionFromSession() {
    try (Graph g = new Graph()) {
      Signature signature = plusFive(Ops.create(g));
      try (Session s = new Session(g)) {
        try (ConcreteFunction f = ConcreteFunction.create(signature, s);
            TFloat32 x = TFloat32.scalarOf(3.0f)) {
          assertEquals(8.0f, ((TFloat32)f.call(x)).getFloat());
        }
      }
    }
  }

  @Test
  public void chainFunctions() {
    try (ConcreteFunction f1 = ConcreteFunction.create(ConcreteFunctionTest::plusFive);
        ConcreteFunction f2 = ConcreteFunction.create(ConcreteFunctionTest::minusTwo);
        TFloat32 x = TFloat32.scalarOf(3.0f)) {
      assertEquals(6.0f, ((TFloat32) f2.call(f1.call(x))).getFloat());
    }
  }

  @Test
  public void getGraphFunctions() {
    try (ConcreteFunction function = ConcreteFunction.create(ConcreteFunctionTest::plusFive);
        Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      tf.call(function, tf.constant(3f));

      ConcreteFunction attached = g.getFunction(function.getNativeFunctionName());
      assertNotNull(attached);

      try (TFloat32 x = TFloat32.scalarOf(10f);
          TFloat32 y = (TFloat32) attached.call(x)) {
        assertEquals(15f, y.getFloat());
      }
    }
  }

  @Test
  public void testNestedFunctionEager() {
    try (EagerSession sess = EagerSession.create();
        ConcreteFunction function = ConcreteFunction.create(ConcreteFunctionTest::plusFiveMinusTwo)) {
      Ops tf = Ops.create(sess);
      Operand<TFloat32> a = tf.constant(10f);
      Operand<TFloat32> result = (Operand<TFloat32>) function.call(tf, a);
      try (TFloat32 t = result.asTensor()) {
        assertEquals(13f, t.getFloat());
      }
    }
  }

  @Test
  public void testNestedFunctionGraph() {
    try (Graph graph = new Graph();
        ConcreteFunction function = ConcreteFunction.create(ConcreteFunctionTest::plusFiveMinusTwo)) {
      Ops tf = Ops.create(graph);
      Operand<TFloat32> a = tf.constant(10f);
      Operand<TFloat32> result = (Operand<TFloat32>) function.call(tf, a);
      try (Session sess = new Session(graph);
          TFloat32 t = (TFloat32) sess.runner().fetch(result).run().get(0)) {
        assertEquals(13f, t.getFloat());
      }
    }
  }
}
