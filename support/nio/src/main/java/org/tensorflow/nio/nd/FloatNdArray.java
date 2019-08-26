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
 * An {@link NdArray} of floats.
 */
public interface FloatNdArray extends NdArray<Float> {

  /**
   * Reads the content of this N-dimensional array into the destination float array.
   *
   * <p>The size of the destination array must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param dst the destination array
   * @return this array
   * @throws java.nio.BufferOverflowException if the destination array cannot hold the content of this array
   */
  default FloatNdArray read(float[] dst) {
    return read(DataBuffers.wrap(dst, false));
  }

  /**
   * Reads the content of this N-dimensional array into the destination float array.
   *
   * <p>{@code dst.length - offset} must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param dst the destination array
   * @param offset the index of the first float to write in the destination array
   * @return this array
   * @throws java.nio.BufferOverflowException if the destination array cannot hold the content of this array
   * @throws IllegalArgumentException if offset is greater than dst length or is negative
   */
  default FloatNdArray read(float[] dst, int offset) {
    return read(DataBuffers.wrap(dst, false).position(offset));
  }

  /**
   * Writes the content of this N-dimensional array from the source float array.
   *
   * <p>The size of the source array must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param src the source array
   * @return this array
   * @throws java.nio.BufferUnderflowException if the size of the source array is less than the size of this array
   */
  default FloatNdArray write(float[] src) {
    return write(DataBuffers.wrap(src, false));
  }

  /**
   * Writes the content of this N-dimensional array from the source float array.
   *
   * <p>{@code src.length - offset} must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param src the source array
   * @param offset the index of the first float to read from the source array
   * @return this array
   * @throws java.nio.BufferUnderflowException if the size of the source array is less than the size of this array
   * @throws IllegalArgumentException if offset is greater than src length or is negative
   */
  default FloatNdArray write(float[] src, int offset) {
    return write(DataBuffers.wrap(src, false).position(offset));
  }

  @Override
  FloatNdArray at(long... indices);
  
  @Override
  FloatNdArray slice(Index... indices);

  @Override
  Iterable<FloatNdArray> childElements();

  @Override
  FloatNdArray set(Float value, long... indices);

  @Override
  FloatNdArray copyTo(NdArray<Float> dst);

  @Override
  FloatNdArray copyFrom(NdArray<Float> src);

  @Override
  FloatNdArray read(DataBuffer<Float> dst);

  @Override
  FloatNdArray write(DataBuffer<Float> src);

  @Override
  FloatNdArray read(Float[] dst);

  @Override
  FloatNdArray read(Float[] dst, int offset);

  @Override
  FloatNdArray write(Float[] src);

  @Override
  FloatNdArray write(Float[] src, int offset);
}
