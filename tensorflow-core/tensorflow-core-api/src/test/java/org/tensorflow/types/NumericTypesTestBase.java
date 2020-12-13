/*
 *  Copyright 2019 The TensorFlow Authors. All Rights Reserved.
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

import org.junit.jupiter.api.Test;
import org.tensorflow.EagerSession;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.index.Indices;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.Sub;
import org.tensorflow.types.family.TNumber;

abstract class NumericTypesTestBase<T extends TNumber, U> {

  @Test
  public void initializeTensorsWithZeros() {
    // Allocate a tensor of 32-bits integer of the shape (2, 3, 2)
    T tensor = allocateTensor(Shape.of(2, 3, 2));

    assertEquals(3, tensor.rank());
    assertEquals(12, tensor.size());
    NdArray<U> data = (NdArray<U>)tensor;

    try (EagerSession session = EagerSession.create()) {
      Ops tf = Ops.create(session);

      // Initialize tensor memory with zeros and take a snapshot
      data.scalars().forEach(scalar -> ((NdArray<U>)scalar).setObject(valueOf(0)));
      Constant<T> x = tf.constantOf(tensor);

      // Initialize the same tensor memory with ones and take a snapshot
      data.scalars().forEach(scalar -> ((NdArray<U>)scalar).setObject(valueOf(1)));
      Constant<T> y = tf.constantOf(tensor);

      // Subtract y from x and validate the result
      Sub<T> sub = tf.math.sub(x, y);
      ((NdArray<U>)sub.asTensor()).scalars().forEach(scalar ->
          assertEquals(valueOf(-1), scalar.getObject())
      );
    }
  }

  @Test
  public void setAndCompute() {
    NdArray<U> heapData = allocateNdArray(Shape.of(4))
        .setObject(valueOf(0), 0)
        .setObject(valueOf(1), 1)
        .setObject(valueOf(2), 2)
        .setObject(valueOf(3), 3);

    // Creates a 2x2 matrix
    try (T tensor = allocateTensor(Shape.of(2, 2))) {
      NdArray<U> data = (NdArray<U>)tensor;

      // Copy first 2 values of the vector to the first row of the matrix
      data.set(heapData.slice(Indices.range(0, 2)), 0);

      // Copy values at an odd position in the vector as the second row of the matrix
      data.set(heapData.slice(Indices.odd()), 1);

      assertEquals(valueOf(0), data.getObject(0, 0));
      assertEquals(valueOf(1), data.getObject(0, 1));
      assertEquals(valueOf(1), data.getObject(1, 0));
      assertEquals(valueOf(3), data.getObject(1, 1));

      // Read rows of the tensor in reverse order
      NdArray<U> flippedData = data.slice(Indices.flip(), Indices.flip());

      assertEquals(valueOf(3), flippedData.getObject(0, 0));
      assertEquals(valueOf(1), flippedData.getObject(0, 1));
      assertEquals(valueOf(1), flippedData.getObject(1, 0));
      assertEquals(valueOf(0), flippedData.getObject(1, 1));

      try (EagerSession session = EagerSession.create()) {
        Ops tf = Ops.create(session);

        Add<T> add = tf.math.add(tf.constantOf(tensor), tf.constantOf(tensor));
        NdArray<U> result = (NdArray<U>)add.asTensor();

        assertEquals(valueOf(0), result.getObject(0, 0));
        assertEquals(valueOf(2), result.getObject(0, 1));
        assertEquals(valueOf(2), result.getObject(1, 0));
        assertEquals(valueOf(6), result.getObject(1, 1));
      }
    }
  }

  abstract T allocateTensor(Shape shape);

  abstract NdArray<U> allocateNdArray(Shape shape);

  abstract U valueOf(Integer value);
}
