/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.math.Add;
import org.tensorflow.types.TInt32;

/** Unit tests for {@link RawOp} */
public class RawOpTest {

  @Test
  public void wrongOpType() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Operand<TInt32> a = tf.constant(10);
      assertThrows(IllegalArgumentException.class, () -> new Add<TInt32>(a.op()));
    }
  }

  @Test
  public void equalsHashcode() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);

      Output<TInt32> array = tf.constant(new int[2]).asOutput();

      RawOp test1 =
          new RawOp(g.baseScope().opBuilder("Shape", "shape1").addInput(array).build(), "Shape") {};
      RawOp test2 =
          new RawOp(g.baseScope().opBuilder("Shape", "shape2").addInput(array).build(), "Shape") {};
      RawOp test3 = new RawOp(test1.operation, test1.operation.type()) {};

      // equals() tests
      assertNotEquals(test1, test2);
      assertEquals(test1, test3);
      assertEquals(test3, test1);
      assertNotEquals(test2, test3);

      // hashcode() tests
      Set<RawOp> ops = new HashSet<>();
      assertTrue(ops.add(test1));
      assertTrue(ops.add(test2));
      assertFalse(ops.add(test3));
    }
  }
}
