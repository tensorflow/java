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

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.nd.index.Index;

/**
 * An {@link NdArray} of bytes.
 */
public interface ByteNdArray extends NdArray<Byte> {

  /**
   * Returns the byte value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  ByteNdArray matrix = NdArrays.ofBytes(shape(2, 2));  // matrix rank = 2
   *  matrix.getByte(0, 1);  // succeeds, returns 0
   *  matrix.getByte(0);  // throws IllegalRankException
   *
   *  ByteNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.getByte();  // succeeds, returns 0
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to resolve
   * @return value of that scalar
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  byte getByte(long... coordinates);

  /**
   * Assigns the byte value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  ByteNdArray matrix = NdArrays.ofBytes(shape(2, 2));  // matrix rank = 2
   *  matrix.setByte(10, 0, 1);  // succeeds
   *  matrix.setByte(10, 0);  // throws IllegalRankException
   *
   *  ByteNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.setByte(10);  // succeeds
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to assign
   * @return this array
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  ByteNdArray setByte(byte value, long... coordinates);

  /**
   * Reads the content of this N-dimensional array into the destination byte array.
   *
   * <p>The size of the destination array must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param dst the destination array
   * @return this array
   * @throws java.nio.BufferOverflowException if the destination array cannot hold the content of this array
   */
  default ByteNdArray read(byte[] dst) {
    return read(dst, 0);
  }

  /**
   * Reads the content of this N-dimensional array into the destination byte array.
   *
   * <p>{@code dst.length - offset} must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param dst the destination array
   * @param offset the index of the first byte to write in the destination array
   * @return this array
   * @throws java.nio.BufferOverflowException if the destination array cannot hold the content of this array
   * @throws IndexOutOfBoundsException if offset is greater than dst length or is negative
   */
  ByteNdArray read(byte[] dst, int offset);

  /**
   * Writes the content of this N-dimensional array from the source byte array.
   *
   * <p>The size of the source array must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param src the source array
   * @return this array
   * @throws java.nio.BufferUnderflowException if the size of the source array is less than the size of this array
   */
  default ByteNdArray write(byte[] src) {
    return write(src, 0);
  }

  /**
   * Writes the content of this N-dimensional array from the source byte array.
   *
   * <p>{@code src.length - offset} must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param src the source array
   * @param offset the index of the first byte to read from the source array
   * @return this array
   * @throws java.nio.BufferUnderflowException if the size of the source array is less than the size of this array
   * @throws IndexOutOfBoundsException if offset is greater than src length or is negative
   */
  ByteNdArray write(byte[] src, int offset);

  @Override
  ByteNdArray slice(Index... indices);

  @Override
  ByteNdArray get(long... coordinates);

  @Override
  ByteNdArray set(NdArray<Byte> src, long... coordinates);

  @Override
  ByteNdArray setValue(Byte value, long... coordinates);

  @Override
  NdArraySequence<ByteNdArray> elements(int dimensionIdx);

  @Override
  NdArraySequence<ByteNdArray> scalars();

  @Override
  ByteNdArray copyTo(NdArray<Byte> dst);

  @Override
  ByteNdArray read(DataBuffer<Byte> dst);

  ByteNdArray read(ByteDataBuffer dst);

  @Override
  ByteNdArray write(DataBuffer<Byte> src);

  ByteNdArray write(ByteDataBuffer src);

  @Override
  ByteNdArray read(Byte[] dst);

  @Override
  ByteNdArray read(Byte[] dst, int offset);

  @Override
  ByteNdArray write(Byte[] src);

  @Override
  ByteNdArray write(Byte[] src, int offset);
}
