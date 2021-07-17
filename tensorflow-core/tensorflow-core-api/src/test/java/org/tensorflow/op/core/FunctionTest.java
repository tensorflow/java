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

import org.junit.jupiter.api.Test;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.EagerSession;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.Signature;
import org.tensorflow.op.Ops;
import org.tensorflow.op.math.Add;
import org.tensorflow.types.TFloat32;

/** Tests for GraphFunction and it's ops */
public class FunctionTest {

  private static Signature plusFive(Ops tf) {
    Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
    Add<TFloat32> output = tf.math.add(input, tf.constant(5.0f));
    return Signature.builder().key("plusFive").input("x", input).output("y", output).build();
  }

  @Test
  public void testConcreteFunctionEager() {
    try (EagerSession sess = EagerSession.create();
        ConcreteFunction function = ConcreteFunction.create(FunctionTest::plusFive)) {
      Ops tf = Ops.create(sess);
      Operand<TFloat32> a = tf.constant(10f);
      Operand<TFloat32> result = (Operand<TFloat32>) function.call(tf, a);
      try (TFloat32 t = result.asTensor()) {
        assertEquals(15f, t.getFloat());
      }
    }
  }

  @Test
  public void testConcreteFunctionGraph() {
    try (Graph graph = new Graph();
        ConcreteFunction function = ConcreteFunction.create(FunctionTest::plusFive)) {
      Ops tf = Ops.create(graph);
      Operand<TFloat32> a = tf.constant(10f);
      Operand<TFloat32> result = (Operand<TFloat32>) function.call(tf, a);
      try (Session sess = new Session(graph);
          TFloat32 t = (TFloat32) sess.runner().fetch(result).run().get(0)) {
        assertEquals(15f, t.getFloat());
      }
    }
  }
}
