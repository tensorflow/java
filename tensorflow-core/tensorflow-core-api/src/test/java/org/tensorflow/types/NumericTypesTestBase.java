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
import org.tensorflow.Tensor;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.math.Sub;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.NdArray;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.index.Indices;
import org.tensorflow.types.family.TNumber;

abstract class NumericTypesTestBase<T extends TNumber & NdArray<U>, U> {

  @Test
  public void initializeTensorsWithZeros() {
    // Allocate a tensor of 32-bits integer of the shape (2, 3, 2)
    Tensor<T> tensor = allocateTensor(Shape.of(2, 3, 2));
    NdArray<U> tensorData = tensor.data();

    assertEquals(3, tensorData.rank());
    assertEquals(12, tensorData.size());

    try (EagerSession session = EagerSession.create()) {
      Ops tf = Ops.create(session);

      // Initialize tensor memory with zeros and take a snapshot
      tensorData.scalars().forEach(scalar -> scalar.setObject(valueOf(0)));
      Constant<T> x = tf.constant(tensor);

      // Initialize the same tensor memory with ones and take a snapshot
      tensorData.scalars().forEach(scalar -> scalar.setObject(valueOf(1)));
      Constant<T> y = tf.constant(tensor);

      // Subtract y from x and validate the result
      Sub<T> sub = tf.math.sub(x, y);
      sub.data().scalars().forEach(scalar ->
          assertEquals(valueOf(-1), scalar.getObject())
      );
    }
  }

  @Test
  public void genericTest() {
    IntNdArray heapData = NdArrays.vectorOf(0, 1, 2, 3);

    // Creates a 2x2 matrix
    try (Tensor<TInt32> tensor = TInt32.tensorOf(Shape.of(2, 2))) {
      IntNdArray tensorData = tensor.data();

      // Copy first 2 values of the vector to the first row of the matrix
      tensorData.set(heapData.slice(Indices.range(0, 2)), 0);

      // Copy values at an odd position in the vector as the second row of the matrix
      tensorData.set(heapData.slice(Indices.odd()), 1);

      assertEquals(0, tensorData.getInt(0, 0));
      assertEquals(1, tensorData.getInt(0, 1));
      assertEquals(1, tensorData.getInt(1, 0));
      assertEquals(3, tensorData.getInt(1, 1));

      // Read rows of the tensor in reverse order
      IntNdArray reversedTensorData = tensorData.slice(Indices.all(), Indices.flip());

      assertEquals(1, reversedTensorData.getInt(0, 0));
      assertEquals(0, reversedTensorData.getInt(0, 1));
      assertEquals(3, reversedTensorData.getInt(1, 0));
      assertEquals(1, reversedTensorData.getInt(1, 1));

      try (EagerSession session = EagerSession.create()) {
        Ops tf = Ops.create(session);

        // Compute the power of the tensor by itself
        Constant<TInt32> x = tf.constant(tensor);
        IntNdArray result = tf.math.pow(x, x).data();

        // Validate result by computing the same operation in Java
        tensorData.scalars().forEachIndexed((coords, s) ->
          assertEquals(Math.pow(s.getInt(), s.getInt()), result.getInt(coords), 1e-7f)
        );
      }
    }
  }

  abstract Tensor<T> allocateTensor(Shape shape);

  abstract U valueOf(Integer value);
}
