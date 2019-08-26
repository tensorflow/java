/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.nio.nd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.tensorflow.nio.StaticApi.*;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.Arrays;
import java.util.stream.LongStream;

import org.junit.Test;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.nd.iterator.ValueIterator;

public abstract class NdArrayTestBase<T> {

  protected abstract NdArray<T> allocate(Shape shape);

  protected abstract DataBuffer<T> allocateBuffer(long capacity);

  protected abstract T valueOf(Long val);

  protected T zeroOrNull() {
    return valueOf(0L);
  }

  @Test
  public void shapeAndSizes() {
    Shape scalarShape = shape();
    NdArray<T> scalar = allocate(scalarShape);
    assertEquals(scalarShape, scalar.shape());
    assertEquals(0, scalar.rank());

    Shape vectorShape = shape(10);
    NdArray<T> vector = allocate(vectorShape);
    assertEquals(vectorShape, vector.shape());
    assertEquals(1, vector.rank());
  }

  @Test
  public void writeAndReadValues() {
    NdArray<T> matrix = allocate(shape(5, 4));
    assertEquals(zeroOrNull(), matrix.get(3, 3));

    matrix.set(valueOf(10L), 3, 3);
    assertEquals(valueOf(10L), matrix.get(3, 3));
    try {
      matrix.set(valueOf(10L), 3, 4);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }
    try {
      matrix.set(valueOf(10L), -1, 3);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }
    try {
      matrix.get(3);
      fail();
    } catch (IllegalRankException e) {
      // as expected
    }
    try {
      matrix.set(valueOf(10L), 3);
      fail();
    } catch (IllegalRankException e) {
      // as expected
    }
  }

  @Test
  public void iterateValues() {
    NdArray<T> matrix3d = allocate(shape(5, 4, 5));
    matrix3d.values().forEach(v -> assertEquals(zeroOrNull(), v));

    long val = 0L;
    for (ValueIterator<T> iter = matrix3d.values().iterator(); iter.hasNext();) {
      iter.next(valueOf(val++));
    }
    val = 0L;
    for (ValueIterator<T> iter = matrix3d.values().iterator(); iter.hasNext();) {
      assertEquals(valueOf(val++), iter.next());
    }
    assertEquals(valueOf(0L), matrix3d.get(0, 0, 0));
    assertEquals(valueOf(6L), matrix3d.get(0, 1, 1));
    assertEquals(valueOf(15L), matrix3d.get(0, 3, 0));
    assertEquals(valueOf(20L), matrix3d.get(1, 0, 0));
    assertEquals(valueOf(29L), matrix3d.get(1, 1, 4));
    assertEquals(valueOf(99L), matrix3d.get(4, 3, 4));
  }

  @Test
  public void iterateElements() {
    NdArray<T> matrix3d = allocate(shape(5, 4, 5));

    long val = 0;
    for (NdArray<T> matrix: matrix3d.childElements()) {
      assertEquals(2L, matrix.shape().numDimensions());
      assertEquals(4L, matrix.shape().numElements(0));
      assertEquals(5L, matrix.shape().numElements(1));

      for (NdArray<T> vector: matrix.childElements()) {
        assertEquals(1L, vector.shape().numDimensions()) ;
        assertEquals(5L, vector.shape().numElements(0));

        for (NdArray<T> scalar: vector.childElements()) {
          assertEquals(0L, scalar.shape().numDimensions()) ;
          scalar.set(valueOf(val++));
          try {
            scalar.childElements().iterator();
            fail();
          } catch (IllegalRankException e) {
            // as expected
          }
        }
      }
    }
    assertEquals(valueOf(0L), matrix3d.get(0, 0, 0));
    assertEquals(valueOf(5L), matrix3d.get(0, 1, 0));
    assertEquals(valueOf(9L), matrix3d.get(0, 1, 4));
    assertEquals(valueOf(20L), matrix3d.get(1, 0, 0));
    assertEquals(valueOf(25L), matrix3d.get(1, 1, 0));
    assertEquals(valueOf(99L), matrix3d.get(4, 3, 4));
  }

  @Test
  public void slices() {
    NdArray<T> matrix3d = allocate(shape(5, 4, 5));
    
    T val100 = valueOf(100L);
    matrix3d.set(val100, 1, 0, 0);
    T val101 = valueOf(101L);
    matrix3d.set(val101, 1, 0, 1);

    // Vector (1,0,*)
    NdArray<T> vector10X = matrix3d.at(1, 0);
    assertEquals(shape(5), vector10X.shape());
    assertEquals(val100, vector10X.get(0));
    assertEquals(val101, vector10X.get(1));

    T val102 = valueOf(102L);
    vector10X.set(val102, 2);
    assertEquals(val102, vector10X.get(2));
    assertEquals(val102, matrix3d.get(1, 0, 2));

    // Vector (*,0,0)
    NdArray<T> vectorX00 = matrix3d.slice(all(), at(0), at(0));
    assertEquals(shape(5, 0, 0), vectorX00.shape());
    assertEquals(val100, vectorX00.get(1));
    T val200 = valueOf(200L);
    vectorX00.set(val200, 2);
    assertEquals(val200, vectorX00.get(2));
    assertEquals(val200, matrix3d.get(2, 0, 0));

    // Vector (1,0,[2,0])
    NdArray<T> vector10_20 = matrix3d.slice(at(1), at(0), seq(2, 0));
    assertEquals(vector10_20.shape(), shape(2));
    assertEquals(val102, vector10_20.get(0));
    assertEquals(val100, vector10_20.get(1));

    // Vector (1,0,[even])
    NdArray<T> vector10_even = matrix3d.slice(at(1), at(0), even());
    assertEquals(vector10_even.shape(), shape(3));
    assertEquals(val100, vector10_even.get(0));
    assertEquals(val102, vector10_even.get(1));

    // Vector ([odd]) from vector (1,0,[even])
    NdArray<T> vector10_even_odd = vector10_even.slice(odd());
    assertEquals(vector10_even_odd.shape(), shape(1));
    assertEquals(val102, vector10_even_odd.get(0));

    // Vector (1,0,[flip])
    NdArray<T> vector10_flip = matrix3d.slice(at(1), at(0), flip());
    assertEquals(vector10_flip.shape(), shape(5));
    assertEquals(val100, vector10_flip.get(4));
    assertEquals(val101, vector10_flip.get(3));

    // Vector (1,0,[from 1]) from vector (1,0,*)
    NdArray<T> vector10_1toX = vector10X.slice(from(1));
    assertEquals(vector10_1toX.shape(), shape(4));
    assertEquals(val101, vector10_1toX.get(0));
    assertEquals(val102, vector10_1toX.get(1));

    // Vector (1,0,[to 1]) from vector (1,0,*)
    NdArray<T> vector10_Xto1 = vector10X.slice(to(2));
    assertEquals(vector10_Xto1.shape(), shape(2));
    assertEquals(val100, vector10_Xto1.get(0));
    assertEquals(val101, vector10_Xto1.get(1));

    // Vector (1,0,[1 to 3])
    NdArray<T> vector10_1to3 = matrix3d.slice(at(1), at(0), range(1, 3));
    assertEquals(vector10_1to3.shape(), shape(2));
    assertEquals(val101, vector10_1to3.get(0));
    assertEquals(val102, vector10_1to3.get(1));

    // Scalar (1,0,0) from vector (1,0,*)
    NdArray<T> scalar100 = vector10X.at(0);
    assertEquals(shape(), scalar100.shape());
    assertEquals(val100, scalar100.get());

    // Slice scalar (1,0,z)
    LongNdArray z = NdArrays.wrap(new long[] {2L}, shape());
    NdArray<T> scalar102 = matrix3d.slice(at(1), at(0), at(z));
    assertEquals(scalar102.shape(), shape());
    assertEquals(val102, scalar102.get());
  }

  @Test
  public void writeAndReadWithBuffers() {
    DataBuffer<T> buffer = allocateBuffer(15L);
    long val = 0L;
    while (buffer.hasRemaining()) {
      buffer.put(valueOf(val++));
    }
    NdArray<T> matrix = allocate(shape(3, 5));
    matrix.write(buffer.rewind());
    assertEquals(valueOf(0L), matrix.get(0, 0));
    assertEquals(valueOf(4L), matrix.get(0, 4));
    assertEquals(valueOf(5L), matrix.get(1, 0));
    assertEquals(valueOf(10L), matrix.get(2, 0));
    assertEquals(valueOf(14L), matrix.get(2, 4));

    matrix.set(valueOf(100L), 1, 0);
    matrix.read(buffer.rewind());
    assertEquals(valueOf(0L), buffer.get(0));
    assertEquals(valueOf(4L), buffer.get(4));
    assertEquals(valueOf(100L), buffer.get(5));
    assertEquals(valueOf(10L), buffer.get(10));
    assertEquals(valueOf(14L), buffer.get(14));
  }

  @Test
  public void ndArrayCopies() {
    NdArray<T> matrixA = allocate(shape(3, 5));
    long val = 0L;
    for (ValueIterator<T> iter = matrixA.values().iterator(); iter.hasNext();) {
      iter.next(valueOf(val++));
    }
    NdArray<T> matrixB = allocate(shape(3, 5));
    matrixB.copyFrom(matrixA);
    assertEquals(valueOf(0L), matrixB.get(0, 0));
    assertEquals(valueOf(4L), matrixB.get(0, 4));
    assertEquals(valueOf(5L), matrixB.get(1, 0));
    assertEquals(valueOf(10L), matrixB.get(2, 0));
    assertEquals(valueOf(14L), matrixB.get(2, 4));

    matrixB.set(valueOf(100L), 1, 0);
    matrixB.copyTo(matrixA);
    assertEquals(valueOf(0L), matrixA.get(0, 0));
    assertEquals(valueOf(4L), matrixA.get(0, 4));
    assertEquals(valueOf(100L), matrixA.get(1, 0));
    assertEquals(valueOf(10L), matrixA.get(2, 0));
    assertEquals(valueOf(14L), matrixA.get(2, 4));

    NdArray<T> matrixC = allocate(shape(3, 4));
    try {
      matrixC.copyFrom(matrixA);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      matrixA.copyTo(matrixC);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
  }

  @Test
  @SuppressWarnings("unchecked")
  public void writeAndReadWithArrays() {
    T[] values = (T[])LongStream.range(0L, 16L).boxed().map(this::valueOf).toArray();

    NdArray<T> matrix = allocate(shape(3, 4));
    matrix.write(values);
    assertEquals(valueOf(0L), matrix.get(0, 0));
    assertEquals(valueOf(3L), matrix.get(0, 3));
    assertEquals(valueOf(4L), matrix.get(1, 0));
    assertEquals(valueOf(11L), matrix.get(2, 3));

    matrix.write(values, 4);
    assertEquals(valueOf(4L), matrix.get(0, 0));
    assertEquals(valueOf(7L), matrix.get(0, 3));
    assertEquals(valueOf(8L), matrix.get(1, 0));
    assertEquals(valueOf(15L), matrix.get(2, 3));

    matrix.set(valueOf(100L), 1, 0);
    matrix.read(values, 2);
    assertEquals(valueOf(4L), values[2]);
    assertEquals(valueOf(7L), values[5]);
    assertEquals(valueOf(100L), values[6]);
    assertEquals(valueOf(15L), values[13]);
    assertEquals(valueOf(15L), values[15]);

    matrix.read(values);
    assertEquals(valueOf(4L), values[0]);
    assertEquals(valueOf(7L), values[3]);
    assertEquals(valueOf(100L), values[4]);
    assertEquals(valueOf(15L), values[11]);
    assertEquals(valueOf(15L), values[13]);
    assertEquals(valueOf(15L), values[15]);

    try {
      matrix.write((T[])LongStream.range(0L, 4L).boxed().map(this::valueOf).toArray());
      fail();
    } catch (BufferUnderflowException e) {
      // as expected
    }
    try {
      matrix.write(values, values.length);
      fail();
    } catch (BufferUnderflowException e) {
      // as expected
    }
    try {
      matrix.write(values, -1);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      matrix.write(values, values.length + 1);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      matrix.read((T[])LongStream.range(0L, 4L).boxed().map(this::valueOf).toArray());
      fail();
    } catch (BufferOverflowException e) {
      // as expected
    }
    try {
      matrix.read(values, values.length);
      fail();
    } catch (BufferOverflowException e) {
      // as expected
    }
    try {
      matrix.read(values, -1);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      matrix.read(values, values.length + 1);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
  }
}
