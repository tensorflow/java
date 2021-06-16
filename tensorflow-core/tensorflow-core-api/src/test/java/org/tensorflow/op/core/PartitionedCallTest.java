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
import org.junit.jupiter.api.Test;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.EagerSession;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.Signature;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;

public class PartitionedCallTest {

  public static Signature plusTwo(Ops tf) {
    Operand<TInt32> x = tf.placeholder(TInt32.class);
    Operand<TInt32> y = tf.math.add(x, tf.constant(2));
    return Signature.builder().input("x", x).output("y", y).build();
  }

  @Test
  public void testEager() {
    try (EagerSession e = EagerSession.create();
        ConcreteFunction f = ConcreteFunction.create(PartitionedCallTest::plusTwo)) {
      Ops tf = Ops.create(e);
      Operand<TInt32> x = tf.constant(3);
      Operand<TInt32> y =
          (Operand<TInt32>)
              tf.partitionedCall(Arrays.asList(x), Arrays.asList(TInt32.class), f).output().get(0);
      assertEquals(5, y.asTensor().getInt());
    }
  }

  @Test
  public void testGraph() {
    try (Graph g = new Graph();
        ConcreteFunction f = ConcreteFunction.create(PartitionedCallTest::plusTwo)) {
      Ops tf = Ops.create(g);
      Operand<TInt32> x = tf.placeholder(TInt32.class);
      Operand<TInt32> y =
          (Operand<TInt32>)
              tf.partitionedCall(Arrays.asList(x), Arrays.asList(TInt32.class), f).output().get(0);

      try (Session s = new Session(g);
          TInt32 out = (TInt32) s.runner().feed(x, TInt32.scalarOf(3)).fetch(y).run().get(0)) {
        assertEquals(5, out.getInt());
      }
    }
  }
}
