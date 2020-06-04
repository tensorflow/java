/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
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

package org.tensorflow.ndarray.impl.buffer.layout;

import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.layout.BooleanDataLayout;

/**
 * Data layout that converts booleans from/to bytes.
 */
public final class BoolLayout implements BooleanDataLayout<ByteDataBuffer> {

  @Override
  public void writeBoolean(ByteDataBuffer buffer, boolean value, long index) {
    buffer.setByte(booleanToByte(value), index);
  }

  @Override
  public boolean readBoolean(ByteDataBuffer buffer, long index) {
    return byteToBoolean(buffer.getByte(index));
  }

  // Visible for testing
  static byte booleanToByte(boolean b) {
    return (byte)(b ? 0x1 : 0x0);
  }

  // Visible for testing
  static boolean byteToBoolean(byte b) {
    return b != 0x0;
  }
}
