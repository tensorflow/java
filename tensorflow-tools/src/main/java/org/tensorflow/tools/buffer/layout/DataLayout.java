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
 * Converts a value of a given type to/from bytes
 *
 * @param <T> value type
 */
public interface DataLayout<T> {

  /**
   * Writes a value as bytes to the given buffer at its current position.
   *
   * @param buffer buffer that receives the value as bytes
   * @param value value
   * @param index index of the value to write
   */
  void writeValue(ByteDataBuffer buffer, T value, long index);

  /**
   * Reads a value as bytes from the given buffer at its current position.
   *
   * @param buffer buffer that supplies the value as bytes
   * @param index index of the value to read
   * @return value
   */
  T readValue(ByteDataBuffer buffer, long index);

  /**
   * Returns the number of bytes required to represent a single value
   */
  int sizeInBytes();
}
