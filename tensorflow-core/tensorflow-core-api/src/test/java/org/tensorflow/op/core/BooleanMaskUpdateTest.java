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

import java.util.List;

import org.junit.jupiter.api.Test;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.JavaScope;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt32;

public class BooleanMaskUpdateTest {

  @Test
  public void testBooleanMaskUpdateSlice() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new JavaScope(g);

      Operand<TInt32> input = Constant
          .tensorOf(scope, new int[][]{{0, 0, 0}, {1, 1, 1}, {2, 2, 2}});

      Operand<TBool> mask = Constant.arrayOf(scope, true, false, false);

      Operand<TInt32> value = Constant.tensorOf(scope, new int[][]{{-1, -1, -1}});

      Operand<TInt32> output = BooleanMaskUpdate.create(scope, input, mask, value);

      Operand<TInt32> bcastOutput = BooleanMaskUpdate
          .create(scope, input, mask, Constant.scalarOf(scope, -1));

      List<Tensor> results = sess.runner().fetch(output).fetch(bcastOutput).run();
      try (TInt32 result = (TInt32) results.get(0);
          TInt32 bcastResult = (TInt32) results.get(1)) {

        assertEquals(Shape.of(3, 3), result.shape());

        assertEquals(-1, result.getInt(0, 0));
        assertEquals(-1, result.getInt(0, 1));
        assertEquals(-1, result.getInt(0, 2));
        assertEquals(1, result.getInt(1, 0));
        assertEquals(1, result.getInt(1, 1));
        assertEquals(1, result.getInt(1, 2));
        assertEquals(2, result.getInt(2, 0));
        assertEquals(2, result.getInt(2, 1));
        assertEquals(2, result.getInt(2, 2));

        assertEquals(result, bcastResult);
      }
    }
  }

  @Test
  public void testBooleanMaskUpdateSliceWithBroadcast() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new JavaScope(g);

      Operand<TInt32> input = Constant
          .tensorOf(scope, new int[][]{{0, 0, 0}, {1, 1, 1}, {2, 2, 2}});

      Operand<TBool> mask = Constant.arrayOf(scope, true, false, false);

      Operand<TInt32> value = Constant.vectorOf(scope, new int[]{-1, -1, -1});

      Operand<TInt32> output = BooleanMaskUpdate.create(scope, input, mask, value);

      Operand<TInt32> bcastOutput = BooleanMaskUpdate
          .create(scope, input, mask, Constant.scalarOf(scope, -1));

      List<Tensor> results = sess.runner().fetch(output).fetch(bcastOutput).run();
      try (TInt32 result = (TInt32) results.get(0);
          TInt32 bcastResult = (TInt32) results.get(1)) {

        assertEquals(Shape.of(3, 3), result.shape());

        assertEquals(-1, result.getInt(0, 0));
        assertEquals(-1, result.getInt(0, 1));
        assertEquals(-1, result.getInt(0, 2));
        assertEquals(1, result.getInt(1, 0));
        assertEquals(1, result.getInt(1, 1));
        assertEquals(1, result.getInt(1, 2));
        assertEquals(2, result.getInt(2, 0));
        assertEquals(2, result.getInt(2, 1));
        assertEquals(2, result.getInt(2, 2));

        assertEquals(result, bcastResult);
      }
    }
  }

  @Test
  public void testBooleanMaskUpdateAxis() {
    try (Graph g = new Graph();
        Session sess = new Session(g)) {
      Scope scope = new JavaScope(g);

      Operand<TInt32> input = Constant
          .tensorOf(scope, new int[][][]{{{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}}});

      Operand<TBool> mask = Constant
          .arrayOf(scope, true, true, false, false, true, true, true, false, false, false);

      Operand<TInt32> value = Constant.arrayOf(scope, -1, -1, -1, -1, -1);

      Operand<TInt32> output = BooleanMaskUpdate
          .create(scope, input, mask, value, BooleanMaskUpdate.axis(2));

      Operand<TInt32> bcastOutput = BooleanMaskUpdate
          .create(scope, input, mask, Constant.scalarOf(scope, -1), BooleanMaskUpdate.axis(2));

      List<Tensor> results = sess.runner().fetch(output).fetch(bcastOutput).run();
      try (TInt32 result = (TInt32) results.get(0);
          TInt32 bcastResult = (TInt32) results.get(1)) {

        assertEquals(Shape.of(1, 1, 10), result.shape());

        assertEquals(-1, result.getInt(0, 0, 0));
        assertEquals(-1, result.getInt(0, 0, 1));
        assertEquals(2, result.getInt(0, 0, 2));
        assertEquals(3, result.getInt(0, 0, 3));
        assertEquals(-1, result.getInt(0, 0, 4));
        assertEquals(-1, result.getInt(0, 0, 5));
        assertEquals(-1, result.getInt(0, 0, 6));
        assertEquals(7, result.getInt(0, 0, 7));
        assertEquals(8, result.getInt(0, 0, 8));
        assertEquals(9, result.getInt(0, 0, 9));

        assertEquals(result, bcastResult);
      }
    }
  }
}
