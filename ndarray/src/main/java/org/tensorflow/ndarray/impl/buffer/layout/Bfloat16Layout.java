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

import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.buffer.layout.FloatDataLayout;

/**
 * Data layout that converts 32-bit floats from/to 16-bit, truncating their mantissa to 7 bits but
 * preserving the 8-bit exponent with the same bias.
 */
public final class Bfloat16Layout implements FloatDataLayout<ShortDataBuffer> {

  @Override
  public void writeFloat(ShortDataBuffer buffer, float value, long index) {
    buffer.setShort(float32to16(value), index);
  }

  @Override
  public float readFloat(ShortDataBuffer buffer, long index) {
    return float16to32(buffer.getShort(index));
  }

  //
  // FLOAT 32-bit to/from BFLOAT 16-bit conversions
  //
  // We simply shift the value from 32-bit to 16-bit and vice-versa. NaN special case is ignored.
  //

  // VisibleForTesting
  static short float32to16(float f32) {
    return (short)(Float.floatToIntBits(f32) >>> 16);
  }

  // Visible for testing
  static float float16to32(short i16) {
    return Float.intBitsToFloat((int)i16 << 16);
  }
}
