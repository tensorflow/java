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
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.impl.adapter.DataBufferAdapterFactory;

/**
 * A {@link DataLayout} that converts data stored in a buffer to ints.
 *
 * @param <S> type of buffer this layout can be applied to
 * @see DataLayout
 */
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
public interface IntDataLayout<S extends DataBuffer<?>> extends DataLayout<S, Integer> {

  @Override
  default IntDataBuffer applyTo(S buffer) {
    return DataBufferAdapterFactory.create(buffer, this);
  }

  /**
   * Writes a int into the buffer at the given index after converting it to the raw data type.
   *
   * @param buffer the buffer to write to
   * @param value the int to convert and write
   * @param index index in the buffer where the converted value should be written
   * @see #writeValue(DataBuffer, Integer, long)
   */
  void writeInt(S buffer, int value, long index);

  /**
   * Reads one or more raw values from the buffer at the given index and to be returned as a int.
   *
   * @param buffer the buffer to read from
   * @param index index in the buffer where the raw value should be read
   * @return the int value
   * @see #readValue(DataBuffer, long)
   */
  int readInt(S buffer, long index);

  @Override
  default void writeValue(S buffer, Integer value, long index) {
    writeInt(buffer, value, index);
  }

  @Override
  default Integer readValue(S buffer, long index) {
    return readInt(buffer, index);
  }
}
