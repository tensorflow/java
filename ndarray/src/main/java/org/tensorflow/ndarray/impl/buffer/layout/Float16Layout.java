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
 * Data layout that converts 32-bit floats from/to 16-bit, accordingly to the IEEE-754 half-precision
 * floating point specification.
 */
public final class Float16Layout implements FloatDataLayout<ShortDataBuffer> {

  @Override
  public void writeFloat(ShortDataBuffer buffer, float value, long index) {
    buffer.setShort(float32to16(value), index);
  }

  @Override
  public float readFloat(ShortDataBuffer buffer, long index) {
    return float16to32(buffer.getShort(index));
  }

  //
  // FLOAT 32-bit to/from 16-bit conversions
  //
  // The following conversion algorithms are issued from the C++ implementation found in the
  // Eigen library used by TensorFlow native library.
  // See https://eigen.tuxfamily.org/dox-devel/Half_8h_source.html for more details.
  //

  // VisibleForTesting
  static short float32to16(float f32) {
    int i16;
    int i32 = Float.floatToIntBits(f32);
    short sign16 = (short) ((i32 >>> 16) & 0x8000);
    i32 &= 0x7FFFFFFF; // remove sign

    if (i32 >= (E32BIAS + E16MAX + 1) << E32SHIFT) {
      // float32 value is higher than float16 max value (max16 -> 2^15 * 2 -> 2^16)
      // - if float32 value is higher than infinite (i.e. s32 > 0), then it is NaN and should also
      //   be NaN in float16 (0x7e00)
      // - else, float16 value is forced to infinite (0x7c00)
      i16 = i32 > E32MASK ? 0x7E00 : 0x7C00;

    } else if (i32 < (E32BIAS + E16MIN) << E32SHIFT){
      // float32 abs value is smaller than float16 min abs value (min16 = 2^-14), could also be 0
      // - apply magic number to align significand 10 bits at the bottom on the float and subtract bias
      i16 = Float.floatToIntBits(Float.intBitsToFloat(i32) + MAGIC_32_16_FLOAT) - MAGIC_32_16;

    } else {
      // float32 value can be rounded up to a normalized float16 value (i.e. exp32 = [113(-14), 142(15)])
      // - rebase exponent to float16
      // - round up significand to the 13nd bit if s16 is even, on the 12nd bit if it is odd
      int round = 0xFFF + ((i32 >>> 13) & 0x1);
      i16 = (i32 + ((E16BIAS - E32BIAS) << E32SHIFT) + round) >>> 13;
    }
    return (short)(i16 | sign16);
  }

  // Visible for testing
  static float float16to32(short i16) {
    int i32 = (i16 & 0x7FFF) << (S32BITS - S16BITS);  // remove sign and align in float32
    i32 += (E32BIAS - E16BIAS) << E32SHIFT;  // rebase exponent to float32

    // Handle float16 exponent special cases
    switch (i16 & E16MASK) {
      case E16MASK:
        // float16 value is infinite or NaN
        // - adjust float32 exponent one more time
        i32 += (E32BIAS - E16BIAS) << E32SHIFT;
        break;
      case 0x0:
        // float16 value is zero or subnormal
        // - adjust float32 exponent
        // - renormalize using magic number
        i32 = Float.floatToIntBits(Float.intBitsToFloat(i32 + (1 << E32SHIFT)) - MAGIC_16_32_FLOAT);
        break;
      default:
        break;
    }
    return Float.intBitsToFloat(i32 | ((i16 & 0x8000) << 16));  // reapply sign
  }

  // float32 format
  private static final int E32SHIFT = 23;  // position of the exponent in float32
  private static final int E32MASK = 0xFF << E32SHIFT;  // mask for float32 exponent (== Infinity)
  private static final int E32BIAS = 127;  // exponent bias for float32
  private static final int S32BITS = 23;  // number of bits in float32 significand

  // float16 format
  private static final int E16SHIFT = 10;  // position of the exponent in float16
  private static final int E16MASK = 0x1F << E16SHIFT;  // mask for float16 exponent (== Infinity)
  private static final int E16BIAS = 15;  // exponent bias for float16
  private static final int E16MAX = 15;  // max value for float16 exponent
  private static final int E16MIN = -14;  // min value for float16 exponent
  private static final int S16BITS = 10;  // number of bits in float16 significand

  // magic numbers used when converting denormalized values
  private static final int MAGIC_32_16 = ((E32BIAS - E16BIAS) + (S32BITS - S16BITS) + 1) << E32SHIFT;
  private static final float MAGIC_32_16_FLOAT = Float.intBitsToFloat(MAGIC_32_16);
  private static final int MAGIC_16_32 = (E32BIAS - E16BIAS + 1) << E32SHIFT;
  private static final float MAGIC_16_32_FLOAT = Float.intBitsToFloat(MAGIC_16_32);
}
