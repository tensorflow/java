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

package org.tensorflow.ndarray.impl.buffer.raw;

import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;

/**
 * Factory of raw data buffers
 */
public class RawDataBufferFactory {

  public static boolean canBeUsed() {
    return UnsafeReference.isAvailable();
  }

  public static BooleanDataBuffer create(boolean[] array, boolean readOnly) {
    return new BooleanRawDataBuffer(UnsafeMemoryHandle.fromArray(array, array.length), readOnly);
  }

  public static ByteDataBuffer create(byte[] array, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    return new ByteRawDataBuffer(UnsafeMemoryHandle.fromArray(array, array.length), readOnly);
  }

  public static DoubleDataBuffer create(double[] array, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    return new DoubleRawDataBuffer(UnsafeMemoryHandle.fromArray(array, array.length), readOnly);
  }

  public static FloatDataBuffer create(float[] array, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    return new FloatRawDataBuffer(UnsafeMemoryHandle.fromArray(array, array.length), readOnly);
  }

  public static IntDataBuffer create(int[] array, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    return new IntRawDataBuffer(UnsafeMemoryHandle.fromArray(array, array.length), readOnly);
  }

  public static LongDataBuffer create(long[] array, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    return new LongRawDataBuffer(UnsafeMemoryHandle.fromArray(array, array.length), readOnly);
  }

  public static ShortDataBuffer create(short[] array, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    return new ShortRawDataBuffer(UnsafeMemoryHandle.fromArray(array, array.length), readOnly);
  }

  protected static BooleanDataBuffer mapNativeBooleans(long address, long size, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    Validator.createArgs(size, MAX_64BITS);
    return new BooleanRawDataBuffer(UnsafeMemoryHandle.fromAddress(address, size, Byte.BYTES), readOnly);
  }

  protected static ByteDataBuffer mapNativeBytes(long address, long size, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    Validator.createArgs(size, MAX_64BITS);
    return new ByteRawDataBuffer(UnsafeMemoryHandle.fromAddress(address, size, Byte.BYTES), readOnly);
  }

  protected static DoubleDataBuffer mapNativeDoubles(long address, long size, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    Validator.createArgs(size, MAX_64BITS);
    return new DoubleRawDataBuffer(UnsafeMemoryHandle.fromAddress(address, size, Double.BYTES), readOnly);
  }

  protected static FloatDataBuffer mapNativeFloats(long address, long size, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    Validator.createArgs(size, MAX_64BITS);
    return new FloatRawDataBuffer(UnsafeMemoryHandle.fromAddress(address, size, Float.BYTES), readOnly);
  }

  protected static IntDataBuffer mapNativeInts(long address, long size, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    Validator.createArgs(size, MAX_64BITS);
    return new IntRawDataBuffer(UnsafeMemoryHandle.fromAddress(address, size, Integer.BYTES), readOnly);
  }

  protected static LongDataBuffer mapNativeLongs(long address, long size, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    Validator.createArgs(size, MAX_64BITS);
    return new LongRawDataBuffer(UnsafeMemoryHandle.fromAddress(address, size, Long.BYTES), readOnly);
  }

  protected static ShortDataBuffer mapNativeShorts(long address, long size, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    Validator.createArgs(size, MAX_64BITS);
    return new ShortRawDataBuffer(UnsafeMemoryHandle.fromAddress(address, size, Short.BYTES), readOnly);
  }

  /*
   * The maximum size for a buffer of this type, i.e. the maximum number of bytes it can store.
   * <p>
   * As the maximum size may vary depending on the JVM implementation and on the platform, this
   * property returns a value that is safe for most of them.
   */
  static long MAX_32BITS = Integer.MAX_VALUE - 10;
  static long MAX_64BITS = Long.MAX_VALUE - 10;
}
