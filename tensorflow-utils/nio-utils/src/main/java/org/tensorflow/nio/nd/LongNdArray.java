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
import org.tensorflow.nio.buffer.LongDataBuffer;
import org.tensorflow.nio.nd.index.Index;

/**
 * An {@link NdArray} of longs.
 */
public interface LongNdArray extends NdArray<Long> {

  /**
   * Returns the long value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  LongNdArray matrix = NdArrays.ofLongs(shape(2, 2));  // matrix rank = 2
   *  matrix.getLong(0, 1);  // succeeds, returns 0L
   *  matrix.getLong(0);  // throws IllegalRankException
   *
   *  LongNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.getLong();  // succeeds, returns 0L
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to resolve
   * @return value of that scalar
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  long getLong(long... coordinates);

  /**
   * Assigns the long value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  LongNdArray matrix = NdArrays.ofLongs(shape(2, 2));  // matrix rank = 2
   *  matrix.setLong(10L, 0, 1);  // succeeds
   *  matrix.setLong(10L, 0);  // throws IllegalRankException
   *
   *  LongNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.setLong(10L);  // succeeds
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to assign
   * @return this array
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  LongNdArray setLong(long value, long... coordinates);

  /**
   * Reads the content of this N-dimensional array into the destination long array.
   *
   * <p>The size of the destination array must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param dst the destination array
   * @return this array
   * @throws java.nio.BufferOverflowException if the destination array cannot hold the content of this array
   */
  default LongNdArray read(long[] dst) {
    return read(dst, 0);
  }

  /**
   * Reads the content of this N-dimensional array into the destination long array.
   *
   * <p>{@code dst.length - offset} must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param dst the destination array
   * @param offset the index of the first long to write in the destination array
   * @return this array
   * @throws java.nio.BufferOverflowException if the destination array cannot hold the content of this array
   * @throws IndexOutOfBoundsException if offset is greater than dst length or is negative
   */
  LongNdArray read(long[] dst, int offset);

  /**
   * Writes the content of this N-dimensional array from the source long array.
   *
   * <p>The size of the source array must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param src the source array
   * @return this array
   * @throws java.nio.BufferUnderflowException if the size of the source array is less than the size of this array
   */
  default LongNdArray write(long[] src) {
    return write(src, 0);
  }

  /**
   * Writes the content of this N-dimensional array from the source long array.
   *
   * <p>{@code src.length - offset} must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param src the source array
   * @param offset the index of the first long to read from the source array
   * @return this array
   * @throws java.nio.BufferUnderflowException if the size of the source array is less than the size of this array
   * @throws IndexOutOfBoundsException if offset is greater than src length or is negative
   */
  LongNdArray write(long[] src, int offset);

  @Override
  LongNdArray slice(Index... indices);

  @Override
  LongNdArray get(long... coordinates);

  @Override
  LongNdArray set(NdArray<Long> src, long... coordinates);

  @Override
  LongNdArray setValue(Long value, long... coordinates);

  @Override
  NdArraySequence<LongNdArray> elements(int dimensionIdx);

  @Override
  NdArraySequence<LongNdArray> scalars();

  @Override
  LongNdArray copyTo(NdArray<Long> dst);

  @Override
  LongNdArray read(DataBuffer<Long> dst);

  LongNdArray read(LongDataBuffer dst);

  @Override
  LongNdArray write(DataBuffer<Long> src);

  LongNdArray write(LongDataBuffer src);

  @Override
  LongNdArray read(Long[] dst);

  @Override
  LongNdArray read(Long[] dst, int offset);

  @Override
  LongNdArray write(Long[] src);

  @Override
  LongNdArray write(Long[] src, int offset);
}
