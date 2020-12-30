/* Copyright 2019 The TensorFlow Authors. All Rights Reserved.

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

package org.tensorflow.op.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;

public final class GeneratedOperationsTest {

  @Test
  public void tensorInputTensorOutput() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Ops ops = Ops.create(g);
      Operand<TInt32> x = ops.math.add(ops.constant(1), ops.constant(2));
      try (TInt32 result = (TInt32)sess.runner().fetch(x).run().get(0)) {
        assertEquals(3, result.getInt());
      }
    }
  }

  @Test
  public void testListInputTensorOutput() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Ops ops = Ops.create(g);
      ArrayList<Operand<TInt32>> inputs = new ArrayList<>();
      inputs.add(ops.constant(1));
      inputs.add(ops.constant(2));
      inputs.add(ops.constant(3));
      Operand<TInt32> x = ops.math.addN(inputs);
      try (TInt32 result = (TInt32)sess.runner().fetch(x).run().get(0)) {
        assertEquals(6, result.getInt());
      }
    }
  }

  /**
   * Test for Ops.withControlDependencies.
   *
   * <p>Creates an add node with a control dependency to an assign node. In other words, the assign
   * node is a control input to the add node. When the add node is run, the assign node is expected
   * to have run beforehand due to the control dependency.
   */
  @Test
  public void testControlDependencies() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Ops ops = Ops.create(g);
      Operand<TInt32> variable = ops.variable(Shape.scalar(), TInt32.class);
      Operand<?> initVariable = ops.assign(variable, ops.constant(0));
      ArrayList<Op> controls = new ArrayList<>();
      controls.add(ops.assign(variable, ops.constant(3)));
      Operand<TInt32> x =
          ops.withControlDependencies(controls).math.add(variable, ops.constant(0));
      sess.runner().addTarget(initVariable).run();
      try (TInt32 result = (TInt32)sess.runner().fetch(x).run().get(0)) {
        assertEquals(3, result.getInt());
      }
    }
  }
}
