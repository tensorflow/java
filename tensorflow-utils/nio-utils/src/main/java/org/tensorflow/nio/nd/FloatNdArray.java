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
import org.tensorflow.nio.buffer.FloatDataBuffer;
import org.tensorflow.nio.nd.index.Index;

/**
 * An {@link NdArray} of floats.
 */
public interface FloatNdArray extends NdArray<Float> {

  /**
   * Returns the float value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  FloatNdArray matrix = NdArrays.ofFloats(shape(2, 2));  // matrix rank = 2
   *  matrix.getFloat(0, 1);  // succeeds, returns 0.0f
   *  matrix.getFloat(0);  // throws IllegalRankException
   *
   *  FloatNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.getFloat();  // succeeds, returns 0.0f
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to resolve
   * @return value of that scalar
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  float getFloat(long... coordinates);

  /**
   * Assigns the float value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  FloatNdArray matrix = NdArrays.ofFloats(shape(2, 2));  // matrix rank = 2
   *  matrix.setFloat(10.0f, 0, 1);  // succeeds
   *  matrix.setFloat(10.0f, 0);  // throws IllegalRankException
   *
   *  FloatNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.setFloat(10.0f);  // succeeds
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to assign
   * @return this array
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  FloatNdArray setFloat(float value, long... coordinates);

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
    return read(dst, 0);
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
   * @throws IndexOutOfBoundsException if offset is greater than dst length or is negative
   */
  FloatNdArray read(float[] dst, int offset);

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
    return write(src, 0);
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
   * @throws IndexOutOfBoundsException if offset is greater than src length or is negative
   */
  FloatNdArray write(float[] src, int offset);

  @Override
  FloatNdArray slice(Index... coordinates);

  @Override
  FloatNdArray get(long... coordinates);

  @Override
  FloatNdArray set(NdArray<Float> src, long... coordinates);

  @Override
  FloatNdArray setValue(Float value, long... indices);

  @Override
  NdArraySequence<FloatNdArray> elements(int dimensionIdx);

  @Override
  NdArraySequence<FloatNdArray> scalars();

  @Override
  FloatNdArray copyTo(NdArray<Float> dst);

  @Override
  FloatNdArray read(DataBuffer<Float> dst);

  FloatNdArray read(FloatDataBuffer dst);

  @Override
  FloatNdArray write(DataBuffer<Float> src);

  FloatNdArray write(FloatDataBuffer src);

  @Override
  FloatNdArray read(Float[] dst);

  @Override
  FloatNdArray read(Float[] dst, int offset);

  @Override
  FloatNdArray write(Float[] src);

  @Override
  FloatNdArray write(Float[] src, int offset);
}
