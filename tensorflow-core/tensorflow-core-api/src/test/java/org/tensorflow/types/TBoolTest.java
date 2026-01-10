/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */

package org.tensorflow.types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.tensorflow.EagerSession;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.index.Indices;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.math.LogicalAnd;
import org.tensorflow.op.math.LogicalNot;
import org.tensorflow.op.math.LogicalOr;

public class TBoolTest {

  @Test
  public void createScalar() {
    TBool tensorT = TBool.scalarOf(true);
    assertNotNull(tensorT);
    assertEquals(Shape.scalar(), tensorT.shape());
    assertEquals(true, tensorT.getObject());

    TBool tensorF = TBool.scalarOf(false);
    assertNotNull(tensorF);
    assertEquals(Shape.scalar(), tensorF.shape());
    assertEquals(false, tensorF.getObject());
  }

  @Test
  public void createVector() {
    TBool tensor = TBool.vectorOf(true, false);
    assertNotNull(tensor);
    assertEquals(Shape.of(2), tensor.shape());
    assertEquals(true, tensor.getObject(0));
    assertEquals(false, tensor.getObject(1));
  }

  @Test
  public void createCopy() {
    NdArray<Boolean> bools =
        NdArrays.ofObjects(Boolean.class, Shape.of(2, 2))
            .setObject(true, 0, 0)
            .setObject(false, 0, 1)
            .setObject(false, 1, 0)
            .setObject(true, 1, 1);

    TBool tensor = TBool.tensorOf(bools);
    assertNotNull(tensor);
    bools.scalars().forEachIndexed((idx, s) -> assertEquals(s.getObject(), tensor.getObject(idx)));
  }

  @Test
  public void initializeTensorsWithBools() {
    // Allocate a tensor of booleans of the shape (2, 3, 2)
    TBool tensor = TBool.tensorOf(Shape.of(2, 3, 2));

    assertEquals(3, tensor.rank());
    assertEquals(12, tensor.size());
    NdArray<Boolean> data = (NdArray<Boolean>) tensor;

    try (EagerSession session = EagerSession.create()) {
      Ops tf = Ops.create(session);

      // Initialize tensor memory with falses and take a snapshot
      data.scalars().forEach(scalar -> ((NdArray<Boolean>) scalar).setObject(false));
      Constant<TBool> x = tf.constantOf(tensor);

      // Initialize the same tensor memory with trues and take a snapshot
      data.scalars().forEach(scalar -> ((NdArray<Boolean>) scalar).setObject(true));
      Constant<TBool> y = tf.constantOf(tensor);

      // Calculate x AND y and validate the result
      LogicalAnd xAndY = tf.math.logicalAnd(x, y);
      ((NdArray<Boolean>) xAndY.asTensor())
          .scalars()
          .forEach(scalar -> assertEquals(false, scalar.getObject()));

      // Calculate x OR y and validate the result
      LogicalOr xOrY = tf.math.logicalOr(x, y);
      ((NdArray<Boolean>) xOrY.asTensor())
          .scalars()
          .forEach(scalar -> assertEquals(true, scalar.getObject()));

      // Calculate !x and validate the result against y
      LogicalNot notX = tf.math.logicalNot(x);
      assertEquals(y.asTensor(), notX.asTensor());
    }
  }

  @Test
  public void setAndCompute() {
    NdArray<Boolean> heapData =
        NdArrays.ofBooleans(Shape.of(4))
            .setObject(true, 0)
            .setObject(false, 1)
            .setObject(true, 2)
            .setObject(false, 3);

    // Creates a 2x2 matrix
    try (TBool tensor = TBool.tensorOf(Shape.of(2, 2))) {
      NdArray<Boolean> data = (NdArray<Boolean>) tensor;

      // Copy first 2 values of the vector to the first row of the matrix
      data.set(heapData.slice(Indices.range(0, 2)), 0);

      // Copy values at an odd position in the vector as the second row of the matrix
      data.set(heapData.slice(Indices.odd()), 1);

      assertEquals(true, data.getObject(0, 0));
      assertEquals(false, data.getObject(0, 1));
      assertEquals(false, data.getObject(1, 0));
      assertEquals(false, data.getObject(1, 1));

      // Read rows of the tensor in reverse order
      NdArray<Boolean> flippedData = data.slice(Indices.flip(), Indices.flip());

      assertEquals(false, flippedData.getObject(0, 0));
      assertEquals(false, flippedData.getObject(0, 1));
      assertEquals(false, flippedData.getObject(1, 0));
      assertEquals(true, flippedData.getObject(1, 1));

      try (EagerSession session = EagerSession.create()) {
        Ops tf = Ops.create(session);

        LogicalNot sub = tf.math.logicalNot(tf.constantOf(tensor));
        NdArray<Boolean> result = (NdArray<Boolean>) sub.asTensor();

        assertEquals(false, result.getObject(0, 0));
        assertEquals(true, result.getObject(0, 1));
        assertEquals(true, result.getObject(1, 0));
        assertEquals(true, result.getObject(1, 1));
      }
    }
  }
}
