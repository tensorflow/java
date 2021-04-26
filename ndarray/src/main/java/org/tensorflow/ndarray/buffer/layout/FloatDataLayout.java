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
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.impl.buffer.adapter.DataBufferAdapterFactory;

/**
 * A {@link DataLayout} that converts data stored in a buffer to floats.
 *
 * @param <S> type of buffer this layout can be applied to
 * @see DataLayout
 */
public interface FloatDataLayout<S extends DataBuffer<?>> extends DataLayout<S, Float> {

  @Override
  default FloatDataBuffer applyTo(S buffer) {
    return DataBufferAdapterFactory.create(buffer, this);
  }

  /**
   * Writes a float into the buffer at the given index after converting it to the buffer type.
   *
   * @param buffer the buffer to write to
   * @param value the float to convert and write
   * @param index index in the buffer where the converted value should be written
   * @see #writeObject(DataBuffer, Float, long)
   */
  void writeFloat(S buffer, float value, long index);

  /**
   * Reads {@code n = scale()} values from the buffer at the given index and return them as a float.
   *
   * @param buffer the buffer to read from
   * @param index position of the buffer to read in the buffer
   * @return the float value
   * @see #readObject(DataBuffer, long)
   */
  float readFloat(S buffer, long index);

  @Override
  default void writeObject(S buffer, Float value, long index) {
    writeFloat(buffer, value, index);
  }

  @Override
  default Float readObject(S buffer, long index) {
    return readFloat(buffer, index);
  }
}
