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
package org.tensorflow.nio.nd;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.DataBuffers;
import org.tensorflow.nio.buffer.DoubleDataBuffer;
import org.tensorflow.nio.buffer.FloatDataBuffer;
import org.tensorflow.nio.buffer.IntDataBuffer;
import org.tensorflow.nio.buffer.LongDataBuffer;
import org.tensorflow.nio.nd.dense.ByteDenseNdArray;
import org.tensorflow.nio.nd.dense.DenseNdArray;
import org.tensorflow.nio.nd.dense.DoubleDenseNdArray;
import org.tensorflow.nio.nd.dense.FloatDenseNdArray;
import org.tensorflow.nio.nd.dense.IntDenseNdArray;
import org.tensorflow.nio.nd.dense.LongDenseNdArray;

public final class NdArrays {
  
  public static ByteNdArray ofBytes(Shape shape) {
    return wrap(DataBuffers.ofBytes(shape.size()), shape);
  }

  public static ByteNdArray wrap(byte[] values, Shape shape) {
    return wrap(DataBuffers.wrap(values, false), shape);
  }

  public static ByteNdArray wrap(ByteDataBuffer buffer, Shape shape) {
    return ByteDenseNdArray.wrap(buffer, shape);
  }

  public static LongNdArray ofLongs(Shape shape) {
    return wrap(DataBuffers.ofLongs(shape.size()), shape);
  }
  
  public static LongNdArray wrap(long[] values, Shape shape) {
    return wrap(DataBuffers.wrap(values, false), shape);
  }

  public static LongNdArray wrap(LongDataBuffer buffer, Shape shape) {
    return LongDenseNdArray.wrap(buffer, shape);
  }

  public static IntNdArray ofIntegers(Shape shape) {
    return wrap(DataBuffers.ofIntegers(shape.size()), shape);
  }

  public static IntNdArray wrap(int[] values, Shape shape) {
    return wrap(DataBuffers.wrap(values, false), shape);
  }

  public static IntNdArray wrap(IntDataBuffer buffer, Shape shape) {
    return IntDenseNdArray.wrap(buffer, shape);
  }

  public static FloatNdArray ofFloats(Shape shape) {
    return wrap(DataBuffers.ofFloats(shape.size()), shape);
  }

  public static FloatNdArray wrap(float[] values, Shape shape) {
    return wrap(DataBuffers.wrap(values, false), shape);
  }

  public static FloatNdArray wrap(FloatDataBuffer buffer, Shape shape) {
    return FloatDenseNdArray.wrap(buffer, shape);
  }

  public static DoubleNdArray ofDoubles(Shape shape) {
    return wrap(DataBuffers.ofDoubles(shape.size()), shape);
  }

  public static DoubleNdArray wrap(double[] values, Shape shape) {
    return wrap(DataBuffers.wrap(values, false), shape);
  }

  public static DoubleNdArray wrap(DoubleDataBuffer buffer, Shape shape) {
    return DoubleDenseNdArray.wrap(buffer, shape);
  }

  public static <T> NdArray<T> of(Class<T> clazz, Shape shape) {
    return wrap(DataBuffers.of(clazz, shape.size()), shape);
  }

  public static <T> NdArray<T> wrap(T[] values, Shape shape) {
    return wrap(DataBuffers.wrap(values, false), shape);
  }

  public static <T> NdArray<T> wrap(DataBuffer<T> buffer, Shape shape) {
    return DenseNdArray.wrap(buffer, shape);
  }
}

