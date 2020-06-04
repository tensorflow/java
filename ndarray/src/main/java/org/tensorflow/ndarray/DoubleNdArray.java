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
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.index.Index;

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

  @Override
  DoubleNdArray slice(Index... indices);

  @Override
  DoubleNdArray get(long... coordinates);

  @Override
  DoubleNdArray set(NdArray<Double> src, long... coordinates);

  @Override
  default Double getObject(long... coordinates) {
    return getDouble(coordinates);
  }

  @Override
  default DoubleNdArray setObject(Double value, long... coordinates) {
    return setDouble(value, coordinates);
  }

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
}
