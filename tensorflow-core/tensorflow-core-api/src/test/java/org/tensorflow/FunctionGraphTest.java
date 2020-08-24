package org.tensorflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.math.Add;
import org.tensorflow.types.TFloat32;

public class FunctionGraphTest {

  private static Signature addTwo(Ops tf) {
    Placeholder<TFloat32> input = tf.placeholder(TFloat32.DTYPE);
    Add<TFloat32> output = tf.math.add(input, tf.constant(2.0f));
    return Signature.builder("addTwo").input("x", input).output("y", output).build();
  }

  @Test
  public void createFunctionFromScratch() {
    try (FunctionGraph f = FunctionGraph.create(FunctionGraphTest::addTwo);
        Tensor<TFloat32> x = TFloat32.scalarOf(2.0f)) {
      assertEquals(4.0f, f.call(x).expect(TFloat32.DTYPE).data().getFloat());
    }
  }

  @Test
  public void createFunctionFromGraph() {
    try (Graph g = new Graph()) {
      Signature signature = addTwo(Ops.create(g));
      try (FunctionGraph f = FunctionGraph.create(signature, g);
          Tensor<TFloat32> x = TFloat32.scalarOf(2.0f)) {
        assertEquals(4.0f, f.call(x).expect(TFloat32.DTYPE).data().getFloat());
      }
    }
  }

  @Test
  public void createFunctionFromSession() {
    try (Graph g = new Graph()) {
      Signature signature = addTwo(Ops.create(g));
      try (Session s = new Session(g)) {
        try (FunctionGraph f = FunctionGraph.create(signature, s);
            Tensor<TFloat32> x = TFloat32.scalarOf(2.0f)) {
          assertEquals(4.0f, f.call(x).expect(TFloat32.DTYPE).data().getFloat());
        }
      }
    }
  }
}
