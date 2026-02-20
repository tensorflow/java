/*
 Copyright 2019-2023 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.ndarray;

import static org.junit.jupiter.api.Assertions.*;
import static org.tensorflow.ndarray.NdArrays.vectorOfObjects;
import static org.tensorflow.ndarray.index.Indices.all;
import static org.tensorflow.ndarray.index.Indices.at;
import static org.tensorflow.ndarray.index.Indices.even;
import static org.tensorflow.ndarray.index.Indices.flip;
import static org.tensorflow.ndarray.index.Indices.odd;
import static org.tensorflow.ndarray.index.Indices.range;
import static org.tensorflow.ndarray.index.Indices.seq;
import static org.tensorflow.ndarray.index.Indices.sliceFrom;
import static org.tensorflow.ndarray.index.Indices.sliceTo;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.index.Indices;

public abstract class NdArrayTestBase<T> {

  protected abstract NdArray<T> allocate(Shape shape);

  protected abstract DataBuffer<T> allocateBuffer(long size);

  protected abstract T valueOf(Long val);

  protected T zeroOrNull() {
    return valueOf(0L);
  }

  @Test
  public void shapeAndSizes() {
    Shape scalarShape = Shape.scalar();
    NdArray<T> scalar = allocate(scalarShape);
    assertEquals(scalarShape, scalar.shape());
    assertEquals(0, scalar.rank());
    assertEquals(scalarShape, Shape.of());

    Shape vectorShape = Shape.of(10);
    NdArray<T> vector = allocate(vectorShape);
    assertEquals(vectorShape, vector.shape());
    assertEquals(1, vector.rank());
  }

  @Test
  public void setAndGetValues() {
    NdArray<T> matrix = allocate(Shape.of(5, 4));
    assertEquals(zeroOrNull(), matrix.getObject(3, 3));

    matrix.setObject(valueOf(10L), 3, 3);
    assertEquals(valueOf(10L), matrix.getObject(3, 3));
    try {
      matrix.setObject(valueOf(10L), 3, 4);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }
    try {
      matrix.setObject(valueOf(10L), -1, 3);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }
    try {
      matrix.getObject(3);
      fail();
    } catch (IllegalRankException e) {
      // as expected
    }
    try {
      matrix.setObject(valueOf(10L), 3);
      fail();
    } catch (IllegalRankException e) {
      // as expected
    }

    NdArray<T> matrix2 = allocate(Shape.of(3, 2))
        .set(vectorOfObjects(valueOf(1L), valueOf(2L)), 0)
        .set(vectorOfObjects(valueOf(3L), valueOf(4L)), 1)
        .setObject(valueOf(5L), 2, 0)
        .setObject(valueOf(6L), 2, 1);

    assertEquals(valueOf(1L), matrix2.getObject(0, 0));
    assertEquals(valueOf(2L), matrix2.getObject(0, 1));
    assertEquals(valueOf(3L), matrix2.getObject(1, 0));
    assertEquals(valueOf(4L), matrix2.getObject(1, 1));
    assertEquals(valueOf(5L), matrix2.getObject(2, 0));
    assertEquals(valueOf(6L), matrix2.getObject(2, 1));
  }

  @Test
  public void iterateElements() {
    NdArray<T> matrix3d = allocate(Shape.of(5, 4, 5));

    matrix3d.scalars().forEachIndexed((coords, scalar) -> {
      scalar.setObject(valueOf(coords[2]));
    });

    assertEquals(valueOf(0L), matrix3d.getObject(0, 0, 0));
    assertEquals(valueOf(1L), matrix3d.getObject(0, 0, 1));
    assertEquals(valueOf(4L), matrix3d.getObject(0, 0, 4));
    assertEquals(valueOf(2L), matrix3d.getObject(0, 1, 2));

    matrix3d.elements(1).forEach(vector -> {
      vector.set(vectorOfObjects(valueOf(5L), valueOf(6L), valueOf(7L), valueOf(8L), valueOf(9L)));
    });

    assertEquals(valueOf(5L), matrix3d.getObject(0, 0, 0));
    assertEquals(valueOf(6L), matrix3d.getObject(0, 0, 1));
    assertEquals(valueOf(9L), matrix3d.getObject(0, 0, 4));
    assertEquals(valueOf(7L), matrix3d.getObject(0, 1, 2));

    long value = 0L;
    for (NdArray<T> matrix : matrix3d.elements(0)) {
      assertEquals(2L, matrix.shape().numDimensions());
      assertEquals(4L, matrix.shape().get(0));
      assertEquals(5L, matrix.shape().get(1));

      for (NdArray<T> vector : matrix.elements(0)) {
        assertEquals(1L, vector.shape().numDimensions());
        assertEquals(5L, vector.shape().get(0));

        for (NdArray<T> scalar : vector.scalars()) {
          assertEquals(0L, scalar.shape().numDimensions());
          scalar.setObject(valueOf(value++));
          try {
            scalar.elements(0);
            fail();
          } catch (IllegalArgumentException e) {
            // as expected
          }
        }
      }
    }
    assertEquals(valueOf(0L), matrix3d.getObject(0, 0, 0));
    assertEquals(valueOf(5L), matrix3d.getObject(0, 1, 0));
    assertEquals(valueOf(9L), matrix3d.getObject(0, 1, 4));
    assertEquals(valueOf(20L), matrix3d.getObject(1, 0, 0));
    assertEquals(valueOf(25L), matrix3d.getObject(1, 1, 0));
    assertEquals(valueOf(99L), matrix3d.getObject(4, 3, 4));
  }

  @Test
  public void slices() {
    NdArray<T> matrix3d = allocate(Shape.of(5, 4, 5));

    T val100 = valueOf(100L);
    matrix3d.setObject(val100, 1, 0, 0);
    T val101 = valueOf(101L);
    matrix3d.setObject(val101, 1, 0, 1);

    // Vector (1,0,*)
    NdArray<T> vector10X = matrix3d.get(1, 0);
    assertEquals(Shape.of(5), vector10X.shape());
    assertEquals(val100, vector10X.getObject(0));
    assertEquals(val101, vector10X.getObject(1));

    T val102 = valueOf(102L);
    vector10X.setObject(val102, 2);
    assertEquals(val102, vector10X.getObject(2));
    assertEquals(val102, matrix3d.getObject(1, 0, 2));

    // Vector (*,0,0)
    NdArray<T> vectorX00 = matrix3d.slice(all(), at(0), at(0));
    assertEquals(Shape.of(5), vectorX00.shape());
    assertEquals(val100, vectorX00.getObject(1));
    T val200 = valueOf(200L);
    vectorX00.setObject(val200, 2);
    assertEquals(val200, vectorX00.getObject(2));
    assertEquals(val200, matrix3d.getObject(2, 0, 0));

    // Vector (1,0,[2,0])
    NdArray<T> vector10_20 = matrix3d.slice(at(1), at(0), seq(2, 0));
    assertEquals(vector10_20.shape(), Shape.of(2));
    assertEquals(val102, vector10_20.getObject(0));
    assertEquals(val100, vector10_20.getObject(1));

    // Vector (1,0,[even])
    NdArray<T> vector10_even = matrix3d.slice(at(1), at(0), even());
    assertEquals(vector10_even.shape(), Shape.of(3));
    assertEquals(val100, vector10_even.getObject(0));
    assertEquals(val102, vector10_even.getObject(1));

    // Vector ([odd]) from vector (1,0,[even])
    NdArray<T> vector10_even_odd = vector10_even.slice(odd());
    assertEquals(vector10_even_odd.shape(), Shape.of(1));
    assertEquals(val102, vector10_even_odd.getObject(0));

    // Vector (1,0,[flip])
    NdArray<T> vector10_flip = matrix3d.slice(at(1), at(0), flip());
    assertEquals(vector10_flip.shape(), Shape.of(5));
    assertEquals(val100, vector10_flip.getObject(4));
    assertEquals(val101, vector10_flip.getObject(3));

    // Vector (1,0,[from 1]) from vector (1,0,*)
    NdArray<T> vector10_1toX = vector10X.slice(sliceFrom(1));
    assertEquals(vector10_1toX.shape(), Shape.of(4));
    assertEquals(val101, vector10_1toX.getObject(0));
    assertEquals(val102, vector10_1toX.getObject(1));

    // Vector (1,0,[to 1]) from vector (1,0,*)
    NdArray<T> vector10_Xto1 = vector10X.slice(sliceTo(2));
    assertEquals(vector10_Xto1.shape(), Shape.of(2));
    assertEquals(val100, vector10_Xto1.getObject(0));
    assertEquals(val101, vector10_Xto1.getObject(1));

    // Vector (1,0,[1 to 3])
    NdArray<T> vector10_1to3 = matrix3d.slice(at(1), at(0), range(1, 3));
    assertEquals(vector10_1to3.shape(), Shape.of(2));
    assertEquals(val101, vector10_1to3.getObject(0));
    assertEquals(val102, vector10_1to3.getObject(1));

    // Scalar (1,0,0) from vector (1,0,*)
    NdArray<T> scalar100 = vector10X.get(0);
    assertEquals(Shape.of(), scalar100.shape());
    assertEquals(val100, scalar100.getObject());

    // Slice scalar (1,0,z)
    LongNdArray z = NdArrays.scalarOf(2L);
    NdArray<T> scalar102 = matrix3d.slice(at(1), at(0), at(z));
    assertEquals(scalar102.shape(), Shape.of());
    assertEquals(val102, scalar102.getObject());

    // Slicing the 3D matrix so we only keep the first element of the second dimension
    NdArray<T> matrix_X0Z = matrix3d.slice(all(), at(0));
    assertEquals(2, matrix_X0Z.rank());
    assertEquals(Shape.of(5, 5), matrix_X0Z.shape());
    assertEquals(val100, matrix_X0Z.getObject(1, 0));
    assertEquals(val101, matrix_X0Z.getObject(1, 1));
    assertEquals(val200, matrix_X0Z.getObject(2, 0));
  }

  @Test
  public void writeAndReadWithBuffers() {
    DataBuffer<T> buffer = allocateBuffer(15L);
    for (long val = 0L; val < buffer.size(); ++val) {
      buffer.setObject(valueOf(val), val);
    }
    NdArray<T> matrix = allocate(Shape.of(3, 5));
    matrix.copyFrom(buffer);
    assertEquals(valueOf(0L), matrix.getObject(0, 0));
    assertEquals(valueOf(4L), matrix.getObject(0, 4));
    assertEquals(valueOf(5L), matrix.getObject(1, 0));
    assertEquals(valueOf(10L), matrix.getObject(2, 0));
    assertEquals(valueOf(14L), matrix.getObject(2, 4));

    matrix.setObject(valueOf(100L), 1, 0);
    matrix.copyTo(buffer);
    assertEquals(valueOf(0L), buffer.getObject(0));
    assertEquals(valueOf(4L), buffer.getObject(4));
    assertEquals(valueOf(100L), buffer.getObject(5));
    assertEquals(valueOf(10L), buffer.getObject(10));
    assertEquals(valueOf(14L), buffer.getObject(14));

    try {
      matrix.copyFrom(buffer.narrow(10));
      fail();
    } catch (BufferUnderflowException e) {
      // as expected
    }
    try {
      matrix.copyTo(buffer.narrow(10));
      fail();
    } catch (BufferOverflowException e) {
      // as expected
    }
  }

  @Test
  public void ndArrayCopies() {
    NdArray<T> matrixA = allocate(Shape.of(3, 5));

    long value = 0L;
    for (NdArray<T> s : matrixA.scalars()) {
      s.setObject(valueOf(value++));
    }
    NdArray<T> matrixB = allocate(Shape.of(3, 5)).setObject(valueOf(100L), 1, 0);
    matrixA.copyTo(matrixB);
    assertEquals(valueOf(0L), matrixB.getObject(0, 0));
    assertEquals(valueOf(4L), matrixB.getObject(0, 4));
    assertEquals(valueOf(5L), matrixB.getObject(1, 0));
    assertEquals(valueOf(10L), matrixB.getObject(2, 0));
    assertEquals(valueOf(14L), matrixB.getObject(2, 4));

    NdArray<T> matrixC = allocate(Shape.of(3, 4));
    try {
      matrixA.copyTo(matrixC);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
  }

  @Test
  public void equalsAndHashCode() {
    NdArray<T> array1 = allocate(Shape.of(2, 2));
    NdArray<T> array2 = allocate(Shape.of(2, 2));
    NdArray<T> array3 = allocate(Shape.of(2, 2));
    NdArray<T> array4 = allocate(Shape.of(1, 2, 2));

    @SuppressWarnings("unchecked")
    T[][][] values = (T[][][]) (new Object[][][]{
        {{valueOf(0L), valueOf(1L)}, {valueOf(2L), valueOf(0L)}}
    });

    StdArrays.copyTo(values[0], array1);
    StdArrays.copyTo(values[0], array2);
    StdArrays.copyTo(values[0], array3);
    array3.setObject(valueOf(0L), 0, 1);
    StdArrays.copyTo(values, array4);

    assertEquals(array1, array2);
    assertEquals(array1.hashCode(), array2.hashCode());
    assertNotEquals(array1, array3);
    assertNotEquals(array1.hashCode(), array3.hashCode());
    assertNotEquals(array1, array4);
    assertNotEquals(array1.hashCode(), array4.hashCode());
  }

  @Test
  public void iterateScalarsOnSegmentedElements() {
    NdArray<T> originalTensor = allocate(Shape.of(2, 3));

    originalTensor
        .setObject(valueOf(0L), 0, 0)
        .setObject(valueOf(1L), 0, 1)
        .setObject(valueOf(2L), 0, 2)
        .setObject(valueOf(3L), 1, 0)
        .setObject(valueOf(4L), 1, 1)
        .setObject(valueOf(5L), 1, 2);

    NdArray<T> slice = originalTensor.slice(Indices.all(), Indices.sliceFrom(1));
    assertEquals(Shape.of(2, 2), slice.shape());

    slice.elements(0).forEachIndexed((eCoord, e) -> {
      e.scalars().forEachIndexed((sCoord, s) -> {
        assertEquals(valueOf((eCoord[0] * originalTensor.shape().get(1)) + sCoord[0] + 1), s.getObject());
      });
    });
  }

  @Test
  public void streamingObjects() {
    NdArray<T> scalar = allocate(Shape.scalar());
    scalar.setObject(valueOf(1L));
    var values = scalar.streamOfObjects().collect(Collectors.toList());
    assertIterableEquals(List.of(valueOf(1L)), values);

    NdArray<T> vector = allocate(Shape.of(5));
    vector.setObject(valueOf(1L), 0);
    vector.setObject(valueOf(2L), 1);
    vector.setObject(valueOf(3L), 2);
    vector.setObject(valueOf(4L), 3);
    vector.setObject(valueOf(5L), 4);
    values = vector.streamOfObjects().collect(Collectors.toList());
    assertIterableEquals(List.of(valueOf(1L), valueOf(2L), valueOf(3L), valueOf(4L), valueOf(5L)), values);

    NdArray<T> matrix = allocate(Shape.of(2, 2));
    matrix.setObject(valueOf(1L), 0, 0);
    matrix.setObject(valueOf(2L), 0, 1);
    matrix.setObject(valueOf(3L), 1, 0);
    matrix.setObject(valueOf(4L), 1, 1);
    values = matrix.streamOfObjects().collect(Collectors.toList());
    assertIterableEquals(List.of(valueOf(1L), valueOf(2L), valueOf(3L), valueOf(4L)), values);
  }

  @Test
  public void withShape() {
    Shape originalShape = Shape.scalar();
    Shape newShape = originalShape.prepend(1).prepend(1); // [1, 1]

    NdArray<T> originalArray = allocate(originalShape);
    originalArray.setObject(valueOf(10L));
    assertEquals(valueOf(10L), originalArray.getObject());

    NdArray<T> newArray = originalArray.withShape(newShape);
    assertNotNull(newArray);
    assertEquals(newShape, newArray.shape());
    assertEquals(originalShape, originalArray.shape());
    assertEquals(valueOf(10L), newArray.getObject(0, 0));

    NdArray<T> sameArray = originalArray.withShape(Shape.scalar());
    assertSame(originalArray, sameArray);

    assertThrows(IllegalArgumentException.class, () -> originalArray.withShape(Shape.of(2)));
    assertThrows(IllegalArgumentException.class, () -> originalArray.withShape(Shape.unknown()));

    NdArray<T> originalMatrix = allocate(Shape.of(2, 3));
    assertThrows(IllegalArgumentException.class, () -> originalMatrix.withShape(Shape.scalar()));
    NdArray<T> newMatrix = originalMatrix.withShape(Shape.of(3, 2));
    assertEquals(Shape.of(3, 2), newMatrix.shape());
  }
}
