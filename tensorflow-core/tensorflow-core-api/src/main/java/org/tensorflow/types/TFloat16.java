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

package org.tensorflow.types;

import org.tensorflow.DataType;
import org.tensorflow.Tensor;
import org.tensorflow.internal.buffer.TensorBuffers;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.tools.Shape;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.ShortDataBuffer;
import org.tensorflow.tools.buffer.layout.FloatDataLayout;
import org.tensorflow.tools.ndarray.FloatNdArray;
import org.tensorflow.tools.ndarray.NdArray;
import org.tensorflow.tools.ndarray.impl.dense.FloatDenseNdArray;
import org.tensorflow.types.family.TDecimal;

/**
 * 16-bits float type.
 *
 * <p>Since there is no floating-point type that fits in 16 bits in Java, a conversion (with potentially
 * a precision loss) is required for each 32 bits value written or read on a tensor of this type from
 * the JVM.
 *
 * <p>Therefore, if a lot of I/O operations are to be expected on a tensor, performances will be
 * improved by working with {@link TFloat32} or {@link TFloat64} data types whenever possible. Also,
 * {@code TFloat16} tensors always perform better if they are located in GPU memory since most
 * CPUs do not support 16 bits floating-point computation.
 */
public interface TFloat16 extends FloatNdArray, TDecimal {

  /** Type metadata */
  DataType<TFloat16> DTYPE = DataType.create("FLOAT16", 19, 2, TFloat16Impl::mapTensor);

  /**
   * Allocates a new tensor for storing a float scalar.
   *
   * @param value float to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat16> scalarOf(float value) {
    Tensor<TFloat16> t = ofShape();
    t.data().setFloat(value);
    return t;
  }

  /**
   * Allocates a new tensor for storing a float vector.
   *
   * @param values floats to store in the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat16> vectorOf(float... values) {
    Tensor<TFloat16> t = ofShape(values.length);
    t.data().write(values);
    return t;
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * @param shape shape of the tensor to allocate
   * @return the new tensor
   */
  static Tensor<TFloat16> ofShape(Shape shape) {
    return Tensor.allocate(DTYPE, shape);
  }

  /**
   * Allocates a new tensor of the given shape.
   *
   * <p>Invoking {@code ofShape(x, y, z)} is equivalent to {@code ofShape(Shape.make(x, y, z))}
   *
   * @param dimensionSizes dimension sizes that defines the shape of the tensor to allocate
   * @return the new tensor
   */
  static Tensor<TFloat16> ofShape(long... dimensionSizes) {
    return Tensor.allocate(DTYPE, Shape.make(dimensionSizes));
  }

  /**
   * Allocates a new tensor which is a copy of a given array of floats.
   *
   * <p>The tensor will have the same shape as the source array and its data will be copied.
   *
   * @param src the source array giving the shape and data to the new tensor
   * @return the new tensor
   */
  static Tensor<TFloat16> copyOf(NdArray<Float> src) {
    Tensor<TFloat16> t = Tensor.allocate(DTYPE, src.shape());
    src.copyTo(t.data());
    return t;
  }
}

class TFloat16Impl extends FloatDenseNdArray implements TFloat16 {

  static TFloat16 mapTensor(TF_Tensor nativeTensor, Shape shape) {
    return new TFloat16Impl(LAYOUT.applyTo(TensorBuffers.toShorts(nativeTensor)), shape);
  }

  private TFloat16Impl(FloatDataBuffer buffer, Shape shape) {
    super(buffer, shape);
  }

  private static FloatDataLayout<ShortDataBuffer> LAYOUT = new FloatDataLayout<ShortDataBuffer>() {

    @Override
    public void writeFloat(ShortDataBuffer buffer, float value, long index) {
      buffer.setShort(float32to16(value), index);
    }

    @Override
    public float readFloat(ShortDataBuffer buffer, long index) {
      return float16to32(buffer.getShort(index));
    }
  };

  //
  // FLOAT 32 bits to/from 16 bits conversions
  //
  // Following conversion algorithms are based on those found in the C++ Eigen library,
  // actually being used by TensorFlow native library.
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

