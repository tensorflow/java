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
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.index.Index;

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

  @Override
  LongNdArray slice(Index... indices);

  @Override
  LongNdArray get(long... coordinates);

  @Override
  LongNdArray set(NdArray<Long> src, long... coordinates);

  @Override
  default Long getObject(long... coordinates) {
    return getLong(coordinates);
  }

  @Override
  default LongNdArray setObject(Long value, long... coordinates) {
    return setLong(value, coordinates);
  }

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
}
