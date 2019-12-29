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
package org.tensorflow.tools.buffer.layout;

import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.impl.adapter.DataBufferAdapterFactory;

/**
 * A {@link DataLayout} that converts data stored in a buffer to bytes.
 *
 * @param <S> type of buffer this layout can be applied to
 * @see DataLayout
 */
public interface ByteDataLayout<S extends DataBuffer<?>> extends DataLayout<S, Byte> {

  @Override
  default ByteDataBuffer applyTo(S buffer) {
    return DataBufferAdapterFactory.create(buffer, this);
  }

  /**
   * Writes a byte into the buffer at the given index after converting it to the raw data type.
   *
   * @param buffer the buffer to write to
   * @param value the byte to convert and write
   * @param index index in the buffer where the converted value should be written
   * @see #writeValue(DataBuffer, Byte, long)
   */
  void writeByte(S buffer, byte value, long index);

  /**
   * Reads one or more raw values from the buffer at the given index and to be returned as a byte.
   *
   * @param buffer the buffer to read from
   * @param index index in the buffer where the raw value should be read
   * @return the byte value
   * @see #readValue(DataBuffer, long)
   */
  byte readByte(S buffer, long index);

  @Override
  default void writeValue(S buffer, Byte value, long index) {
    writeByte(buffer, value, index);
  }

  @Override
  default Byte readValue(S buffer, long index) {
    return readByte(buffer, index);
  }
}
