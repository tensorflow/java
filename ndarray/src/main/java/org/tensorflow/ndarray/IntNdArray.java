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
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.index.Index;

/**
 * An {@link NdArray} of integers.
 */
public interface IntNdArray extends NdArray<Integer> {

  /**
   * Returns the integer value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  IntNdArray matrix = NdArrays.ofInts(shape(2, 2));  // matrix rank = 2
   *  matrix.getInt(0, 1);  // succeeds, returns 0
   *  matrix.getInt(0);  // throws IllegalRankException
   *
   *  IntNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.getInt();  // succeeds, returns 0
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to resolve
   * @return value of that scalar
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  int getInt(long... coordinates);

  /**
   * Assigns the integer value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  IntNdArray matrix = NdArrays.ofInts(shape(2, 2));  // matrix rank = 2
   *  matrix.setInt(10, 0, 1);  // succeeds
   *  matrix.setInt(10, 0);  // throws IllegalRankException
   *
   *  IntNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.setInt(10);  // succeeds
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to assign
   * @return this array
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  IntNdArray setInt(int value, long... coordinates);

  @Override
  IntNdArray slice(Index... indices);

  @Override
  IntNdArray get(long... coordinates);

  @Override
  IntNdArray set(NdArray<Integer> src, long... coordinates);

  @Override
  default Integer getObject(long... coordinates) {
    return getInt(coordinates);
  }

  @Override
  default IntNdArray setObject(Integer value, long... coordinates) {
    return setInt(value, coordinates);
  }

  @Override
  NdArraySequence<IntNdArray> elements(int dimensionIdx);

  @Override
  NdArraySequence<IntNdArray> scalars();

  @Override
  IntNdArray copyTo(NdArray<Integer> dst);

  @Override
  IntNdArray read(DataBuffer<Integer> dst);

  IntNdArray read(IntDataBuffer dst);

  @Override
  IntNdArray write(DataBuffer<Integer> src);

  IntNdArray write(IntDataBuffer src);
}
