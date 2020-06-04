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

import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.index.Index;

/**
 * An {@link NdArray} of bytes.
 */
public interface ByteNdArray extends NdArray<Byte> {

  /**
   * Returns the byte value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  ByteNdArray matrix = NdArrays.ofBytes(shape(2, 2));  // matrix rank = 2
   *  matrix.getByte(0, 1);  // succeeds, returns 0
   *  matrix.getByte(0);  // throws IllegalRankException
   *
   *  ByteNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.getByte();  // succeeds, returns 0
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to resolve
   * @return value of that scalar
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  byte getByte(long... coordinates);

  /**
   * Assigns the byte value of the scalar found at the given coordinates.
   *
   * <p>To access the scalar element, the number of coordinates provided must be equal to the number
   * of dimensions of this array (i.e. its rank). For example:
   * <pre>{@code
   *  ByteNdArray matrix = NdArrays.ofBytes(shape(2, 2));  // matrix rank = 2
   *  matrix.setByte(10, 0, 1);  // succeeds
   *  matrix.setByte(10, 0);  // throws IllegalRankException
   *
   *  ByteNdArray scalar = matrix.get(0, 1);  // scalar rank = 0
   *  scalar.setByte(10);  // succeeds
   * }</pre>
   *
   * @param coordinates coordinates of the scalar to assign
   * @return this array
   * @throws IndexOutOfBoundsException if some coordinates are outside the limits of their respective dimension
   * @throws IllegalRankException if number of coordinates is not sufficient to access a scalar element
   */
  ByteNdArray setByte(byte value, long... coordinates);

  @Override
  ByteNdArray slice(Index... indices);

  @Override
  ByteNdArray get(long... coordinates);

  @Override
  ByteNdArray set(NdArray<Byte> src, long... coordinates);

  @Override
  default Byte getObject(long... coordinates) {
    return getByte(coordinates);
  }

  @Override
  default ByteNdArray setObject(Byte value, long... coordinates) {
    return setByte(value, coordinates);
  }

  @Override
  NdArraySequence<ByteNdArray> elements(int dimensionIdx);

  @Override
  NdArraySequence<ByteNdArray> scalars();

  @Override
  ByteNdArray copyTo(NdArray<Byte> dst);

  @Override
  ByteNdArray read(DataBuffer<Byte> dst);

  ByteNdArray read(ByteDataBuffer dst);

  @Override
  ByteNdArray write(DataBuffer<Byte> src);

  ByteNdArray write(ByteDataBuffer src);
}
