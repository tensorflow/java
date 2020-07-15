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
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.index.Index;

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

  @Override
  FloatNdArray slice(Index... coordinates);

  @Override
  FloatNdArray get(long... coordinates);

  @Override
  FloatNdArray set(NdArray<Float> src, long... coordinates);

  @Override
  default Float getObject(long... coordinates) {
    return getFloat(coordinates);
  }

  @Override
  default FloatNdArray setObject(Float value, long... coordinates) {
    return setFloat(value, coordinates);
  }

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
}
