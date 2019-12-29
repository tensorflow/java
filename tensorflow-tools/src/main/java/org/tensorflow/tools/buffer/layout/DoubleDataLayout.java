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
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.impl.adapter.DataBufferAdapterFactory;

/**
 * A {@link DataLayout} that converts data stored in a buffer to doubles.
 *
 * @param <S> type of buffer this layout can be applied to
 * @see DataLayout
 */
public interface DoubleDataLayout<S extends DataBuffer<?>> extends DataLayout<S, Double> {

  @Override
  default DoubleDataBuffer applyTo(S buffer) {
    return DataBufferAdapterFactory.create(buffer, this);
  }

  /**
   * Writes a double into the buffer at the given index after converting it to the raw data type.
   *
   * @param buffer the buffer to write to
   * @param value the double to convert and write
   * @param index index in the buffer where the converted value should be written
   * @see #writeValue(DataBuffer, Double, long)
   */
  void writeDouble(S buffer, double value, long index);

  /**
   * Reads one or more raw values from the buffer at the given index and to be returned as a double.
   *
   * @param buffer the buffer to read from
   * @param index index in the buffer where the raw value should be read
   * @return the double value
   * @see #readValue(DataBuffer, long)
   */
  double readDouble(S buffer, long index);

  @Override
  default void writeValue(S buffer, Double value, long index) {
    writeDouble(buffer, value, index);
  }

  @Override
  default Double readValue(S buffer, long index) {
    return readDouble(buffer, index);
  }
}
