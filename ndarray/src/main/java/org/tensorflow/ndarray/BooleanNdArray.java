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

import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.index.Index;

/**
 * An {@link NdArray} of booleans.
 */
public interface BooleanNdArray extends NdArray<Boolean> {

  /**
   * Returns the boolean value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  BooleanNdArray matrix = NdArrays.ofBooleans(shape(2, 2));  // matrix rank = 2
   *  matrix.getBoolean(0, 1);  // succeeds, returns false
   *  matrix.getBoolean(0);  // throws IllegalRankException
   *
   *  BooleanNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.getBoolean();  // succeeds, returns false
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to resolve
   * @return value of that scalar
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  boolean getBoolean(long... coordinates);

  /**
   * Assigns the boolean value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  BooleanNdArray matrix = NdArrays.ofBooleans(shape(2, 2));  // matrix rank = 2
   *  matrix.setBoolean(true, 0, 1);  // succeeds
   *  matrix.setBoolean(true, 0);  // throws IllegalRankException
   *
   *  BooleanNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.setBoolean(true);  // succeeds
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to assign
   * @return this array
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  BooleanNdArray setBoolean(boolean value, long... coordinates);

  @Override
  BooleanNdArray slice(Index... indices);

  @Override
  BooleanNdArray get(long... coordinates);

  @Override
  BooleanNdArray set(NdArray<Boolean> src, long... coordinates);

  @Override
  default Boolean getObject(long... coordinates) {
    return getBoolean(coordinates);
  }

  @Override
  default BooleanNdArray setObject(Boolean value, long... coordinates) {
    return setBoolean(value, coordinates);
  }

  @Override
  NdArraySequence<BooleanNdArray> elements(int dimensionIdx);

  @Override
  NdArraySequence<BooleanNdArray> scalars();

  @Override
  BooleanNdArray copyTo(NdArray<Boolean> dst);

  @Override
  BooleanNdArray read(DataBuffer<Boolean> dst);

  BooleanNdArray read(BooleanDataBuffer dst);

  @Override
  BooleanNdArray write(DataBuffer<Boolean> src);

  BooleanNdArray write(BooleanDataBuffer src);
}
