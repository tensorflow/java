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
package org.tensorflow.tools.buffer.layout;

import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.impl.adapter.DataBufferAdapterFactory;

/**
 * Converts data stored in a buffer to a given data type.
 *
 * <p>{@code DataLayout} instances are used to define a custom format for storing and reading data
 * of a {@link DataBuffer}. They provide a segregation layer between the type of data stored in the
 * buffer (called the 'raw' type) and the type of data manipulated by the end user (called the
 * 'virtual' type).
 *
 * <p>Given a virtual data type {@code \u03b1} and a buffer {@code B} of a raw data type <i>{@code
 * \u03b2}</i>, a data layout of type {@code \u03b1} applied to the buffer {@code B} will be invoked
 * for each value of type {@code \u03b1} written the buffer to persist it as a value of type {@code
 * \u03b2}, and vice-versa for each value that is read from the buffer.
 *
 * <p>It is recommended that implementations of the {@code DataLayout} interface (or one of its
 * sub-interface) are immutable so they can be reapplied to multiple buffers instead of reallocating
 * a new instance for each of them. For example:
 *
 * <pre>{@code
 * class BigIntegerBufferAllocator {
 *
 *     public DataBuffer<BigInteger> allocate(long size) {
 *         return LAYOUT.applyTo(bufferOfLongs(size * LAYOUT.scale()));  // scale is 1 by default
 *     }
 *
 *     private static final DataLayout<LongDataBuffer, BigInteger> LAYOUT = new DataLayout<LongDataBuffer, BigInteger>() {
 *
 *         @Override
 *         public void writeValue(LongDataBuffer buffer, BigInteger value, long index) {
 *             buffer.setLong(value.longValue(), index);
 *         }
 *
 *         @Override
 *         public BigInteger readValue(LongDataBuffer buffer, long index) {
 *             return BigInteger.valueOf(buffer.getLong(index));
 *         }
 *     }
 * }
 * }</pre>
 * Since the conversion methods are invoked for every value that is written or read, working
 * with data layouts may have a negative impact on the performances so using primitive types directly
 * should be preferred whenever possible.
 *
 * @param <S> type of buffer this layout can be applied to
 * @param <T> virtual data type of this layout
 */
public interface DataLayout<S extends DataBuffer<?>, T> {

  /**
   * Apply this layout to the provided buffer.
   *
   * <p>The returned {@link DataBuffer} instance is simply a wrapper to the original buffer that applies
   * this layout for each read or write operations and does not have a separate backing storage of his
   * own.
   *
   * @param buffer buffer to apply this layout to
   * @return a virtual buffer with this layout
   */
  default DataBuffer<T> applyTo(S buffer) {
    return DataBufferAdapterFactory.create(buffer, this);
  }

  /**
   * Writes a value into the buffer at the given index after converting it to the raw data type.
   *
   * <p>It is the responsibility of the implementors of this interface to write the transformed value
   * to the given buffer before this call returns, using the most appropriate value. For example,
   * for a layout that transforms a {@code BigInteger} to a single {@code long},
   * <pre>{@code
   *  @Override
   *  public void writeValue(LongDataBuffer buffer, BigInteger value, long index) {
   *    buffer.setLong(value.longValue(), index);
   *  }
   * }</pre>
   * If a single value of the virtual type layout scales over more than one values of the raw type,
   * {@code index} indicates the position of the first raw value to be written and other raw values
   * must be written in the subsequent positions.
   *
   * @param buffer the buffer to write to
   * @param value the value of the virtual type to convert and write
   * @param index index in the buffer where the converted value should be written
   */
  void writeValue(S buffer, T value, long index);

  /**
   * Reads one or more values from the buffer at the given index to be returned as a value of
   * the virtual type of this layout.
   *
   * <p>It is the responsibility of the implementors of this interface to read the value to be
   * transformed from the given buffer, using the most appropriate method. For example, for a layout
   * that transforms a single {@code long} to a {@code BigInteger},
   * <pre>{@code
   *  @Override
   *  public BigInteger readValue(LongDataBuffer buffer, long index) {
   *    return BigInteger.valueOf(buffer.getLong(index));
   *  }
   * }</pre>
   * If a single value of the virtual type scales over more than one values of the raw type, {@code index} indicates
   * the position of the first raw value to be read and other raw values must be read from the subsequent
   * positions.
   *
   * @param buffer the buffer to read from
   * @param index index in the buffer where the raw value should be read
   * @return the converted value
   */
  T readValue(S buffer, long index);

  /**
   * Indicates the number of raw values (i.e. values stored in the buffer) are required to represent a
   * single value of the virtual type (i.e. values as manipulated by users), default is 1.
   *
   * <p>Scale must be positive and must be an integer, meaning that a single raw value in a buffer cannot
   * be used to represent more than one virtual value.
   */
  default int scale() {
    return 1;
  }
}
