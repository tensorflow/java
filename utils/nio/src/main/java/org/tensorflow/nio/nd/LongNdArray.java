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
import org.tensorflow.nio.buffer.DataBuffers;
import org.tensorflow.nio.nd.index.Index;

/**
 * An {@link NdArray} of longs.
 */
public interface LongNdArray extends NdArray<Long> {

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
    return read(DataBuffers.wrap(dst, false));
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
   * @throws IllegalArgumentException if offset is greater than dst length or is negative
   */
  default LongNdArray read(long[] dst, int offset) {
    return read(DataBuffers.wrap(dst, false).position(offset));
  }

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
    return write(DataBuffers.wrap(src, false));
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
   * @throws IllegalArgumentException if offset is greater than src length or is negative
   */
  default LongNdArray write(long[] src, int offset) {
    return write(DataBuffers.wrap(src, false).position(offset));
  }

  @Override
  LongNdArray at(long... indices);

  @Override
  LongNdArray slice(Index... indices);

  @Override
  Iterable<LongNdArray> childElements();

  @Override
  LongNdArray set(Long value, long... indices);

  @Override
  LongNdArray copyTo(NdArray<Long> dst);

  @Override
  LongNdArray copyFrom(NdArray<Long> src);

  @Override
  LongNdArray read(DataBuffer<Long> dst);

  @Override
  LongNdArray write(DataBuffer<Long> src);

  @Override
  LongNdArray read(Long[] dst);

  @Override
  LongNdArray read(Long[] dst, int offset);

  @Override
  LongNdArray write(Long[] src);

  @Override
  LongNdArray write(Long[] src, int offset);
}
