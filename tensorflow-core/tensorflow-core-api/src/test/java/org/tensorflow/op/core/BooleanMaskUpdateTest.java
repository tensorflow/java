/*
  Copyright 2021 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.op.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.index.Indices;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

public class BooleanMaskUpdateTest {

  @Test
  public void testBooleanMaskUpdateSlice() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);

      Operand<TInt32> input = Constant.tensorOf(scope, new int[][]{ {0, 0, 0}, {1, 1, 1}, {2, 2, 2}});

      Operand<TBool> mask = Constant.arrayOf(scope, true, false, false);

      Operand<TInt32> value = Constant.tensorOf(scope, new int[][]{{-1, -1, -1}});

      Operand<TInt32> output = BooleanMaskUpdate.create(scope, input, mask, value);

      try (TFloat32 result = (TFloat32) sess.runner().fetch(output).run().get(0)) {
        // expected shape from Python tensorflow
        assertEquals(Shape.of(3, 3), result.shape());
        assertEquals(-1, result.getFloat(0, 0));
        assertEquals(-1, result.getFloat(0, 1));
        assertEquals(-1, result.getFloat(0, 2));
        assertEquals(1, result.getFloat(1, 0));
        assertEquals(1, result.getFloat(1, 1));
        assertEquals(1, result.getFloat(1, 2));
        assertEquals(2, result.getFloat(2, 0));
        assertEquals(2, result.getFloat(2, 1));
        assertEquals(2, result.getFloat(2, 2));
      }
    }
  }

  @Test
  public void testBooleanMaskUpdateAxis() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new Scope(g);

      Operand<TInt32> input = Constant.tensorOf(scope, new int[][][]{{{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}}});

      Operand<TBool> mask = Constant.arrayOf(scope, true, true, false, false, true, true, true, false, false, false);

      Operand<TInt32> value = Constant.arrayOf(scope, -1, -1, -1, -1, -1);

      Operand<TInt32> output = BooleanMaskUpdate.create(scope, input, mask, value, BooleanMaskUpdate.axis(2));

      try (TFloat32 result = (TFloat32) sess.runner().fetch(output).run().get(0)) {
        // expected shape from Python tensorflow
        assertEquals(Shape.of(1, 1, 10), result.shape());
        assertEquals(-1, result.getFloat(0, 0, 0));
        assertEquals(-1, result.getFloat(0, 0, 1));
        assertEquals(2, result.getFloat(0, 0, 2));
        assertEquals(3, result.getFloat(0, 0, 3));
        assertEquals(-1, result.getFloat(0, 0, 4));
        assertEquals(-1, result.getFloat(0, 0, 5));
        assertEquals(-1, result.getFloat(0, 0, 6));
        assertEquals(7, result.getFloat(0, 0, 7));
        assertEquals(8, result.getFloat(0, 0, 8));
        assertEquals(9, result.getFloat(0, 0, 9));
      }
    }
  }
}
