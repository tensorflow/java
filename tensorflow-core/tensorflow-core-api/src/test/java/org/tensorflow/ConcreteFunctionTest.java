/* Copyright 2020-2021 The TensorFlow Authors. All Rights Reserved.

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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.Sub;
import org.tensorflow.proto.framework.DataType;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

public class ConcreteFunctionTest {

  private static Signature plusFive(Ops tf) {
    Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
    Add<TFloat32> output = tf.math.add(input, tf.constant(5.0f));
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
      return Signature.builder()
          .key("plusFiveMinusTwo")
          .input("x", input)
          .output("y", result)
          .build();
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
        assertEquals(8.0f, ((TFloat32) f.call(x)).getFloat());
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
          assertEquals(8.0f, ((TFloat32) f.call(x)).getFloat());
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

      ConcreteFunction attached = g.getFunction(function.getDefinedName());
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
        ConcreteFunction function =
            ConcreteFunction.create(ConcreteFunctionTest::plusFiveMinusTwo)) {
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
        ConcreteFunction function =
            ConcreteFunction.create(ConcreteFunctionTest::plusFiveMinusTwo)) {
      Ops tf = Ops.create(graph);
      Operand<TFloat32> a = tf.constant(10f);
      Operand<TFloat32> result = (Operand<TFloat32>) function.call(tf, a);
      try (Session sess = new Session(graph);
          TFloat32 t = (TFloat32) sess.runner().fetch(result).run().get(0)) {
        assertEquals(13f, t.getFloat());
      }
    }
  }

  @Test
  public void testFunctionWithTwoOutputs() {
    ConcreteFunction cf =
        ConcreteFunction.create(
            tf -> {
              Placeholder<TInt32> x = tf.placeholder(TInt32.class);
              Operand<TInt32> dblX = tf.math.add(x, x);
              Operand<TInt32> tripX = tf.math.add(x, dblX);
              return Signature.builder()
                  .input("x", x)
                  .output("dbl", dblX)
                  .output("trpl", tripX)
                  .build();
            });

    Map<String, Tensor> inputs = new HashMap<>();
    inputs.put("x", TInt32.scalarOf(2));
    Result outputs = cf.call(inputs);
    assertEquals(4, ((TInt32) outputs.get("dbl").get()).getInt());
    assertEquals(6, ((TInt32) outputs.get("trpl").get()).getInt());
  }

  private static Signature square(Ops tf) {
    Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
    Operand<TFloat32> output = tf.math.square(input);
    return Signature.builder()
        .methodName("square")
        .key("square")
        .input("x", input)
        .output("y", output)
        .build();
  }

  // call op gradients are not defined in c++
  //  @Test
  public void testGradientsGraph() {
    try (Graph g = new Graph();
        ConcreteFunction square = ConcreteFunction.create(ConcreteFunctionTest::square);
        Session s = new Session(g)) {
      Ops tf = Ops.create(g);

      Output<TFloat32> x1 = tf.placeholder(TFloat32.class).output();
      Output<TFloat32> x2 = tf.placeholder(TFloat32.class).output();
      Output<TFloat32> y0 = (Output<TFloat32>) square.call(tf, x1);
      Output<TFloat32> y1 = (Output<TFloat32>) square.call(tf, y0);
      Output<TFloat32> y2 = tf.math.addN(Arrays.asList(y0, x2)).sum();

      Output<?>[] grads0 = g.addGradients(y1, new Output[] {x1});
      assertNotNull(grads0);
      assertEquals(1, grads0.length);
      assertEquals(DataType.DT_FLOAT, grads0[0].dataType());

      Output<?>[] grads1 = g.addGradients(y2, new Output[] {x1, x2});
      assertNotNull(grads1);
      assertEquals(2, grads1.length);
      assertEquals(DataType.DT_FLOAT, grads1[0].dataType());
      assertEquals(DataType.DT_FLOAT, grads1[1].dataType());

      try (TFloat32 c1 = TFloat32.scalarOf(3.0f);
          TFloat32 c2 = TFloat32.scalarOf(2.0f);
          Result outputs =
              s.runner()
                  .feed(x1, c1)
                  .feed(x2, c2)
                  .fetch(grads0[0])
                  .fetch(grads1[0])
                  .fetch(grads1[1])
                  .run()) {

        assertEquals(3, outputs.size());
        assertEquals(108.0f, ((TFloat32) outputs.get(0)).getFloat(), 0.0f);
        assertEquals(6.0f, ((TFloat32) outputs.get(1)).getFloat(), 0.0f);
        assertEquals(1.0f, ((TFloat32) outputs.get(2)).getFloat(), 0.0f);
      }
    }
  }
}
