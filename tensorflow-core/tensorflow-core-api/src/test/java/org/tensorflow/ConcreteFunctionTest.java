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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Init;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.Sub;
import org.tensorflow.types.TFloat32;

public class ConcreteFunctionTest {

  private static Signature plusFive(Ops tf) {
    Placeholder<TFloat32> input = tf.placeholder(TFloat32.DTYPE);
    Add<TFloat32> output = tf.math.add(input, tf.constant(5.0f));
    Init init = tf.init();  // for native resource management tests
    return Signature.builder().key("plusFive").input("x", input).output("y", output).build();
  }

  private static Signature minusTwo(Ops tf) {
    Placeholder<TFloat32> input = tf.placeholder(TFloat32.DTYPE);
    Sub<TFloat32> output = tf.math.sub(input, tf.constant(2.0f));
    return Signature.builder().key("minusTwo").input("x", input).output("y", output).build();
  }

  @Test
  public void createFunction() {
    try (ConcreteFunction f = ConcreteFunction.create(ConcreteFunctionTest::plusFive);
        TFloat32 x = TFloat32.scalarOf(3.0f)) {
      assertEquals(8.0f, ((TFloat32)f.call(x)).getFloat());
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
      assertEquals(6.0f, ((TFloat32)f2.call(f1.call(x))).getFloat());
    }
  }

  @Test
  public void closingFunctionReleaseAllResourcesItOwns() {
    Graph g;
    Session s;
    try (ConcreteFunction f = ConcreteFunction.create(ConcreteFunctionTest::plusFive)) {
      g = f.graph();
      s = f.session();
    }
    assertThrows(IllegalStateException.class, () -> s.run("Add"));
    assertThrows(IllegalStateException.class, () -> g.toGraphDef());
  }

  @Test
  public void closingFunctionCreatedFromGraphOnlyReleaseResourcesItOwns() {
    try (Graph g = new Graph()) {
      Signature signature = plusFive(Ops.create(g));
      Session s;
      try (ConcreteFunction f = ConcreteFunction.create(signature, g)) {
        s = f.session();
      }
      assertThrows(IllegalStateException.class, () -> s.run(Init.DEFAULT_NAME));
      g.toGraphDef();  // check that graph is still valid
    }
  }

  @Test
  public void closingFunctionCreatedFromSessionDoesNotReleaseResources() {
    try (Graph g = new Graph()) {
      Signature signature = plusFive(Ops.create(g));
      try (Session s = new Session(g)) {
        try (ConcreteFunction f = ConcreteFunction.create(signature, s)) {
        }
        s.run(Init.DEFAULT_NAME);  // check that session is still valid
      }
      g.toGraphDef();  // check that graph is still valid
    }
  }
}
