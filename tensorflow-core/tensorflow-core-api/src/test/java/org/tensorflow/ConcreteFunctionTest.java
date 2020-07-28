package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Tensor<TFloat32> x = TFloat32.scalarOf(3.0f)) {
      assertEquals(8.0f, f.call(x).expect(TFloat32.DTYPE).data().getFloat());
    }
  }

  @Test
  public void createFunctionFromGraph() {
    try (Graph g = new Graph()) {
      Signature signature = plusFive(Ops.create(g));
      try (ConcreteFunction f = ConcreteFunction.create(signature, g);
          Tensor<TFloat32> x = TFloat32.scalarOf(3.0f)) {
        assertEquals(8.0f, f.call(x).expect(TFloat32.DTYPE).data().getFloat());
      }
    }
  }

  @Test
  public void createFunctionFromSession() {
    try (Graph g = new Graph()) {
      Signature signature = plusFive(Ops.create(g));
      try (Session s = new Session(g)) {
        try (ConcreteFunction f = ConcreteFunction.create(signature, s);
            Tensor<TFloat32> x = TFloat32.scalarOf(3.0f)) {
          assertEquals(8.0f, f.call(x).expect(TFloat32.DTYPE).data().getFloat());
        }
      }
    }
  }

  @Test
  public void chainFunctions() {
    try (ConcreteFunction f1 = ConcreteFunction.create(ConcreteFunctionTest::plusFive);
        ConcreteFunction f2 = ConcreteFunction.create(ConcreteFunctionTest::minusTwo);
        Tensor<TFloat32> x = TFloat32.scalarOf(3.0f)) {
      assertEquals(6.0f, f2.call(f1.call(x)).expect(TFloat32.DTYPE).data().getFloat());
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
    try {
      s.run("Add");
      fail();
    } catch (IllegalStateException e) {
      // as expected
    }
    try {
      g.toGraphDef();
      fail();
    } catch (IllegalStateException e) {
      // as expected
    }
  }

  @Test
  public void closingFunctionCreatedFromGraphOnlyReleaseResourcesItOwns() {
    try (Graph g = new Graph()) {
      Signature signature = plusFive(Ops.create(g));
      Session s;
      try (ConcreteFunction f = ConcreteFunction.create(signature, g)) {
        s = f.session();
      }
      try {
        s.run(Init.DEFAULT_NAME);
        fail();
      } catch (IllegalStateException e) {
        // as expected
      }
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
