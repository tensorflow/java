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

import org.tensorflow.tools.buffer.ByteDataBuffer;

/**
 * Converts a boolean to/from bytes
 */
public interface BooleanDataLayout extends DataLayout<Boolean> {

  /**
   * Writes a boolean as bytes to the given buffer at its current position.
   *  @param buffer buffer that receives the value as bytes
   * @param value value
   * @param index byte index of the value to write
   */
  void writeBoolean(ByteDataBuffer buffer, boolean value, long index);

  /**
   * Reads a boolean as bytes from the given buffer at its current position.
   *
   * @param buffer buffer that supplies the value as bytes
   * @param index byte index of the value to read
   * @return value
   */
  boolean readBoolean(ByteDataBuffer buffer, long index);

  @Override
  default void writeValue(ByteDataBuffer buffer, Boolean value, long index) {
    writeBoolean(buffer, value, index);
  }

  @Override
  default Boolean readValue(ByteDataBuffer buffer, long index) {
    return readBoolean(buffer, index);
  }
}
