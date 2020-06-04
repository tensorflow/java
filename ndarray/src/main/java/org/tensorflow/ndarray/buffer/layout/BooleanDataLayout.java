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

import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.impl.buffer.adapter.DataBufferAdapterFactory;

/**
 * A {@link DataLayout} that converts data stored in a buffer to booleans.
 *
 * @param <S> type of buffer this layout can be applied to
 * @see DataLayout
 */
public interface BooleanDataLayout<S extends DataBuffer<?>> extends DataLayout<S, Boolean> {

  @Override
  default BooleanDataBuffer applyTo(S buffer) {
    return DataBufferAdapterFactory.create(buffer, this);
  }

  /**
   * Writes a boolean into the buffer at the given index after converting it to the buffer type.
   *
   * @param buffer the buffer to write to
   * @param value the boolean to convert and write
   * @param index index in the buffer where the converted value should be written
   * @see #writeObject(DataBuffer, Boolean, long)
   */
  void writeBoolean(S buffer, boolean value, long index);

  /**
   * Reads {@code n = scale()} values from the buffer at the given index and return them as a boolean.
   *
   * @param buffer the buffer to read from
   * @param index position of the buffer to read in the buffer
   * @return the boolean value
   * @see #readObject(DataBuffer, long)
   */
  boolean readBoolean(S buffer, long index);

  @Override
  default void writeObject(S buffer, Boolean value, long index) {
    writeBoolean(buffer, value, index);
  }

  @Override
  default Boolean readObject(S buffer, long index) {
    return readBoolean(buffer, index);
  }
}
