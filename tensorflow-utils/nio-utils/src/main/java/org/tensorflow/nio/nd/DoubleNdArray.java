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
import org.tensorflow.nio.buffer.DoubleDataBuffer;
import org.tensorflow.nio.nd.index.Index;

/**
 * An {@link NdArray} of doubles.
 */
public interface DoubleNdArray extends NdArray<Double> {

  /**
   * Returns the double value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  DoubleNdArray matrix = NdArrays.ofDoubles(shape(2, 2));  // matrix rank = 2
   *  matrix.getDouble(0, 1);  // succeeds, returns 0.0
   *  matrix.getDouble(0);  // throws IllegalRankException
   *
   *  DoubleNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.getDouble();  // succeeds, returns 0.0
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to resolve
   * @return value of that scalar
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  double getDouble(long... coordinates);

  /**
   * Assigns the double value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  DoubleNdArray matrix = NdArrays.ofDoubles(shape(2, 2));  // matrix rank = 2
   *  matrix.setDouble(10.0, 0, 1);  // succeeds
   *  matrix.setDouble(10.0, 0);  // throws IllegalRankException
   *
   *  DoubleNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.setDouble(10.0);  // succeeds
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to assign
   * @return this array
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  DoubleNdArray setDouble(double value, long... coordinates);

  /**
   * Reads the content of this N-dimensional array into the destination double array.
   *
   * <p>The size of the destination array must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param dst the destination array
   * @return this array
   * @throws java.nio.BufferOverflowException if the destination array cannot hold the content of this array
   */
  default DoubleNdArray read(double[] dst) {
    return read(dst, 0);
  }

  /**
   * Reads the content of this N-dimensional array into the destination double array.
   *
   * <p>{@code dst.length - offset} must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param dst the destination array
   * @param offset the index of the first double to write in the destination array
   * @return this array
   * @throws java.nio.BufferOverflowException if the destination array cannot hold the content of this array
   * @throws IndexOutOfBoundsException if offset is greater than dst length or is negative
   */
  DoubleNdArray read(double[] dst, int offset);

  /**
   * Writes the content of this N-dimensional array from the source double array.
   *
   * <p>The size of the source array must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param src the source array
   * @return this array
   * @throws java.nio.BufferUnderflowException if the size of the source array is less than the size of this array
   */
  default DoubleNdArray write(double[] src) {
    return write(src, 0);
  }

  /**
   * Writes the content of this N-dimensional array from the source double array.
   *
   * <p>{@code src.length - offset} must be equal or greater to the {@link #size()} of this array,
   * or an exception is thrown. After the copy, content of the both arrays can be altered
   * independently, without affecting each other.
   *
   * @param src the source array
   * @param offset the index of the first double to read from the source array
   * @return this array
   * @throws java.nio.BufferUnderflowException if the size of the source array is less than the size of this array
   * @throws IndexOutOfBoundsException if offset is greater than src length or is negative
   */
  DoubleNdArray write(double[] src, int offset);

  @Override
  DoubleNdArray slice(Index... indices);

  @Override
  DoubleNdArray get(long... coordinates);

  @Override
  DoubleNdArray set(NdArray<Double> src, long... coordinates);

  @Override
  DoubleNdArray setValue(Double value, long... coordinates);

  @Override
  NdArraySequence<DoubleNdArray> elements(int dimensionIdx);

  @Override
  NdArraySequence<DoubleNdArray> scalars();

  @Override
  DoubleNdArray copyTo(NdArray<Double> dst);

  @Override
  DoubleNdArray read(DataBuffer<Double> dst);

  DoubleNdArray read(DoubleDataBuffer dst);

  @Override
  DoubleNdArray write(DataBuffer<Double> src);

  DoubleNdArray write(DoubleDataBuffer src);

  @Override
  DoubleNdArray read(Double[] dst);

  @Override
  DoubleNdArray read(Double[] dst, int offset);

  @Override
  DoubleNdArray write(Double[] src);

  @Override
  DoubleNdArray write(Double[] src, int offset);
}
