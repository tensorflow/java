/*
  Copyright 2020 The TensorFlow Authors. All Rights Reserved.

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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.variable.MutableVariable;

/**
 * Unit tests for {@link org.tensorflow.variable.Variable}/{@link org.tensorflow.variable.MutableVariable}
 */
public class VariableTest {

  @Test
  public void testEager() {
    try(EagerSession es = EagerSession.create()) {
      Ops tf = Ops.create(es);
      MutableVariable<TFloat32> variable = tf.Variable(Shape.of(10, 10), TFloat32.class).asMutableVariable();

      assertFalse(variable.isInitialized());
      assertFalse(variable.isValueInitialized().asTensor().getBoolean(0));

      variable.initialize(tf.ones(tf.array(10, 10), TFloat32.class));

      assertTrue(variable.isInitialized());
      assertTrue(variable.isValueInitialized().asTensor().getBoolean(0));

      assertEquals(1, variable.value().asTensor().getFloat(0, 0));

      variable.assign(tf.math.add(tf.ones(tf.array(10, 10), TFloat32.class), tf.constant(2f)));

      assertEquals(3, variable.value().asTensor().getFloat(0, 0));

      variable.assignAdd(tf.ones(tf.array(10, 10), TFloat32.class));

      assertEquals(4, variable.value().asTensor().getFloat(0, 0));

      variable.assignSub(tf.ones(tf.array(10, 10), TFloat32.class));

      assertEquals(3, variable.value().asTensor().getFloat(0, 0));
    }
  }

  @Test
  public void testGraph() {
    try(Graph graph = new Graph()) {
      Ops tf = Ops.create(graph);
      MutableVariable<TFloat32> variable = tf.Variable(Shape.of(10, 10), TFloat32.class).asMutableVariable();

      assertFalse(variable.isInitialized());

      variable.initialize(tf.ones(tf.array(10, 10), TFloat32.class));

      assertTrue(variable.isInitialized());

      Operand<TFloat32> original = variable.value();

      Op assign = variable.assign(tf.math.add(tf.ones(tf.array(10, 10), TFloat32.class), tf.constant(2f)));
      Operand<TFloat32> afterAssign = variable.value();

      Op increment = variable.assignAdd(tf.ones(tf.array(10, 10), TFloat32.class));
      Operand<TFloat32> afterIncrement = variable.value();

      Op decrement = variable.assignSub(tf.ones(tf.array(10, 10), TFloat32.class));
      Operand<TFloat32> afterDecrement = variable.value();

      try(Session session = new Session(graph)) {

        assertFalse(((TBool) session.runner().fetch(variable.isValueInitialized()).run().get(0)).getBoolean(0));

        session.run(tf.init());

        assertTrue(((TBool) session.runner().fetch(variable.isValueInitialized()).run().get(0)).getBoolean(0));

        // test control deps (in-run assign)

        assertEquals(1, ((TFloat32) session.runner().fetch(original).run().get(0)).getFloat(0, 0));

        assertEquals(3, ((TFloat32) session.runner().fetch(afterAssign).run().get(0)).getFloat(0, 0));

        assertEquals(4, ((TFloat32) session.runner().fetch(afterIncrement).run().get(0)).getFloat(0, 0));

        assertEquals(3, ((TFloat32) session.runner().fetch(afterDecrement).run().get(0)).getFloat(0, 0));

        // test persistence (multi-run assign)

        session.run(decrement);
        session.run(decrement);

        assertEquals(1, ((TFloat32) session.runner().fetch(original).run().get(0)).getFloat(0, 0));

        session.run(increment);
        session.run(increment);
        session.run(increment);
        session.run(increment);

        assertEquals(5, ((TFloat32) session.runner().fetch(original).run().get(0)).getFloat(0, 0));

        session.run(assign);

        assertEquals(3, ((TFloat32) session.runner().fetch(original).run().get(0)).getFloat(0, 0));
      }
    }

  }

}
