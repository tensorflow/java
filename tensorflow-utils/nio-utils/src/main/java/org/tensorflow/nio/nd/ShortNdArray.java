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

import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.ShortDataBuffer;
import org.tensorflow.nio.nd.index.Index;

/**
 * An {@link NdArray} of shorts.
 */
public interface ShortNdArray extends NdArray<Short> {

  /**
   * Returns the short value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  ShortNdArray matrix = NdArrays.ofShorts(shape(2, 2));  // matrix rank = 2
   *  matrix.getShort(0, 1);  // succeeds, returns 0.0f
   *  matrix.getShort(0);  // throws IllegalRankException
   *
   *  ShortNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.getShort();  // succeeds, returns 0.0f
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to resolve
   * @return value of that scalar
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  short getShort(long... coordinates);

  /**
   * Assigns the short value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  ShortNdArray matrix = NdArrays.ofShorts(shape(2, 2));  // matrix rank = 2
   *  matrix.setShort(10.0f, 0, 1);  // succeeds
   *  matrix.setShort(10.0f, 0);  // throws IllegalRankException
   *
   *  ShortNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.setShort(10.0f);  // succeeds
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to assign
   * @return this array
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  ShortNdArray setShort(short value, long... coordinates);

  /**
   * Reads the content of this N-dimensional array into the destination short array.
   *
   * <p>The size of the destination array must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param dst the destination array
   * @return this array
   * @throws java.nio.BufferOverflowException if the destination array cannot hold the content of this array
   */
  default ShortNdArray read(short[] dst) {
    return read(dst, 0);
  }

  /**
   * Reads the content of this N-dimensional array into the destination short array.
   *
   * <p>{@code dst.length - offset} must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param dst the destination array
   * @param offset the index of the first short to write in the destination array
   * @return this array
   * @throws java.nio.BufferOverflowException if the destination array cannot hold the content of this array
   * @throws IndexOutOfBoundsException if offset is greater than dst length or is negative
   */
  ShortNdArray read(short[] dst, int offset);

  /**
   * Writes the content of this N-dimensional array from the source short array.
   *
   * <p>The size of the source array must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param src the source array
   * @return this array
   * @throws java.nio.BufferUnderflowException if the size of the source array is less than the size of this array
   */
  default ShortNdArray write(short[] src) {
    return write(src, 0);
  }

  /**
   * Writes the content of this N-dimensional array from the source short array.
   *
   * <p>{@code src.length - offset} must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param src the source array
   * @param offset the index of the first short to read from the source array
   * @return this array
   * @throws java.nio.BufferUnderflowException if the size of the source array is less than the size of this array
   * @throws IndexOutOfBoundsException if offset is greater than src length or is negative
   */
  ShortNdArray write(short[] src, int offset);

  @Override
  ShortNdArray slice(Index... coordinates);

  @Override
  ShortNdArray get(long... coordinates);

  @Override
  ShortNdArray set(NdArray<Short> src, long... coordinates);

  @Override
  ShortNdArray setValue(Short value, long... indices);

  @Override
  NdArraySequence<ShortNdArray> elements(int dimensionIdx);

  @Override
  NdArraySequence<ShortNdArray> scalars();

  @Override
  ShortNdArray copyTo(NdArray<Short> dst);

  @Override
  ShortNdArray read(DataBuffer<Short> dst);

  ShortNdArray read(ShortDataBuffer dst);

  @Override
  ShortNdArray write(DataBuffer<Short> src);

  ShortNdArray write(ShortDataBuffer src);

  @Override
  ShortNdArray read(Short[] dst);

  @Override
  ShortNdArray read(Short[] dst, int offset);

  @Override
  ShortNdArray write(Short[] src);

  @Override
  ShortNdArray write(Short[] src, int offset);
}
