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
package org.tensorflow.ndarray;

import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.index.Index;

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

  @Override
  ShortNdArray slice(Index... coordinates);

  @Override
  ShortNdArray get(long... coordinates);

  @Override
  ShortNdArray set(NdArray<Short> src, long... coordinates);

  @Override
  default Short getObject(long... coordinates) {
    return getShort(coordinates);
  }

  @Override
  default ShortNdArray setObject(Short value, long... coordinates) {
    return setShort(value, coordinates);
  }

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
}
