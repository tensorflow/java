package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collections;
import jdk.nashorn.internal.codegen.FunctionSignature;
import org.junit.jupiter.api.Test;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.math.Add;
import org.tensorflow.types.TFloat32;

public class FunctionGraphTest {

  @Test
  public void createFunctionFromGraph() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Placeholder<TFloat32> x = tf.placeholder(TFloat32.DTYPE);
      Add<TFloat32> y = tf.math.add(x, tf.constant(2.0f));
      try (Session s = new Session(g)) {
        FunctionGraph function = FunctionGraph.builder().input("x", x).output("y", y).build(s);
        try (Tensor<TFloat32> xValue = TFloat32.scalarOf(10.0f)) {
          // Call with explicit input/output names
          try (Tensor<TFloat32> yValue = function.call(Collections.singletonMap("x", xValue))
              .get("y")
              .expect(TFloat32.DTYPE)) {
            assertEquals(12.0f, yValue.data().getFloat());
          }
          // Call with implicit single input/output names
          try (Tensor<TFloat32> yValue = function.call(xValue).expect(TFloat32.DTYPE)) {
            assertEquals(12.0f, yValue.data().getFloat());
          }
        }
      }
    }
  }

  @Test
  public void cannotCallFunctionAfterSessionIsClosed() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Placeholder<TFloat32> x = tf.placeholder(TFloat32.DTYPE);
      Add<TFloat32> y = tf.math.add(x, tf.constant(2.0f));
      FunctionGraph function;
      try (Session s = new Session(g)) {
        function = FunctionGraph.builder().input("x", x).output("y", y).build(s);
      }
      try (Tensor<TFloat32> xValue = TFloat32.scalarOf(10.0f)) {
        function.call(xValue);
        fail();
      } catch (IllegalStateException e) {
        // as expected
      }
    }
  }
}
