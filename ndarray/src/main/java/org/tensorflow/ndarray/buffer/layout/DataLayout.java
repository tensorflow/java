/*
 *  Copyright 2019 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */
package org.tensorflow.ndarray.buffer.layout;

import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.impl.buffer.adapter.DataBufferAdapterFactory;

/**
 * Converts data stored in a buffer to a given type.
 *
 * <p>{@code DataLayout} instances are used to define a custom format for storing and reading data
 * of a {@link DataBuffer}. They provide a segregation layer between the type of data stored in the
 * buffer (the <i>buffer</i> type) and the type of data manipulated by the end user (the
 * <i>user</i> type).
 *
 * <p>Since the conversion methods are invoked for every value that is written or read, working
 * with data layouts may have a negative impact on the performances so using primitive types directly
 * should be preferred whenever possible.
 *
 * <p>It is also recommended to implement immutable data layouts so they can be reapplied to multiple
 * buffers without reallocating a new instance for each of them. For example:
 *
 * <pre>{@code
 * class BigIntegerBufferAllocator {
 *
 *     public DataBuffer<BigInteger> allocate(long size) {
 *         return LAYOUT.applyTo(DataBuffers.ofLongs(size * LAYOUT.scale()));  // scale is 1 by default
 *     }
 *
 *     private static final DataLayout<LongDataBuffer, BigInteger> LAYOUT = new DataLayout<LongDataBuffer, BigInteger>() {
 *
 *         @Override
 *         public void writeObject(LongDataBuffer buffer, BigInteger value, long index) {
 *             buffer.setLong(value.longValue(), index);
 *         }
 *
 *         @Override
 *         public BigInteger readObject(LongDataBuffer buffer, long index) {
 *             return BigInteger.valueOf(buffer.getLong(index));
 *         }
 *     };
 * }
 * }</pre>
 *
 * @param <S> type of buffer this layout can be applied to
 * @param <T> user data type of this layout
 */
public interface DataLayout<S extends DataBuffer<?>, T> {

  /**
   * Apply this layout to the provided buffer.
   *
   * <p>The returned {@link DataBuffer} instance is simply a wrapper to the original buffer and does
   * not have a backing storage of his own.
   *
   * @param buffer the target buffer to apply this layout to
   * @return a buffer with this layout
   */
  default DataBuffer<T> applyTo(S buffer) {
    return DataBufferAdapterFactory.create(buffer, this);
  }

  /**
   * Writes a user value into the buffer at the given index after converting it to the buffer type.
   *
   * <p>It is the responsibility of the implementors of this interface to write the converted value
   * to the given buffer before this call returns, using the most appropriate method. For example,
   * for a layout converting a {@code BigInteger} to a single {@code long},
   * <pre>{@code
   *  @Override
   *  public void writeObject(LongDataBuffer buffer, BigInteger value, long index) {
   *    buffer.setLong(value.longValue(), index);
   *  }
   * }</pre>
   * If a single user value scales over more than one buffer values, {@code index} indicates the
   * starting position of the sequence to be written to the buffer.
   *
   * @param buffer the buffer to write to
   * @param value the value in the user type to convert and write
   * @param index index in the buffer where the converted value should be written
   */
  void writeObject(S buffer, T value, long index);

  /**
   * Reads {@code n = scale()} values from the buffer at the given index and return them as a single
   * value in the user type.
   *
   * <p>It is the responsibility of the implementors of this interface to read the value to be
   * converted from the given buffer, using the most appropriate method. For example, for a layout
   * that converting a single {@code long} to a {@code BigInteger},
   * <pre>{@code
   *  @Override
   *  public BigInteger readObject(LongDataBuffer buffer, long index) {
   *    return BigInteger.valueOf(buffer.getLong(index));
   *  }
   * }</pre>
   * If a single user value scales over more than one buffer values, {@code index} indicates the
   * starting position of the sequence to be read from the buffer.
   *
   * @param buffer the buffer to read from
   * @param index position of the buffer to read in the buffer
   * @return the converted value
   */
  T readObject(S buffer, long index);

  /**
   * Indicates the number of buffer values are required to represent a single user value, default is 1.
   *
   * <p>Scale must be positive and must be an integer, meaning that a single buffer value in a buffer cannot
   * be used to represent more than one user value.
   */
  default int scale() {
    return 1;
  }
}
