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
package org.tensorflow.util.buffer;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.tensorflow.util.buffer.adapter.BooleanDataAdapter;
import org.tensorflow.util.buffer.adapter.DataAdapter;
import org.tensorflow.util.buffer.adapter.DoubleDataAdapter;
import org.tensorflow.util.buffer.adapter.FloatDataAdapter;
import org.tensorflow.util.buffer.adapter.IntDataAdapter;
import org.tensorflow.util.buffer.adapter.LongDataAdapter;
import org.tensorflow.util.buffer.adapter.ShortDataAdapter;
import org.tensorflow.util.buffer.impl.jdk.ByteJdkDataBuffer;
import org.tensorflow.util.buffer.impl.jdk.DoubleJdkDataBuffer;
import org.tensorflow.util.buffer.impl.jdk.FloatJdkDataBuffer;
import org.tensorflow.util.buffer.impl.jdk.IntJdkDataBuffer;
import org.tensorflow.util.buffer.impl.jdk.LongJdkDataBuffer;
import org.tensorflow.util.buffer.impl.jdk.ShortJdkDataBuffer;
import org.tensorflow.util.buffer.impl.misc.ArrayDataBuffer;
import org.tensorflow.util.buffer.impl.misc.BitSetDataBuffer;
import org.tensorflow.util.buffer.impl.misc.BooleanArrayDataBuffer;
import org.tensorflow.util.buffer.impl.raw.BooleanRawDataBuffer;
import org.tensorflow.util.buffer.impl.raw.ByteRawDataBuffer;
import org.tensorflow.util.buffer.impl.raw.DoubleRawDataBuffer;
import org.tensorflow.util.buffer.impl.raw.FloatRawDataBuffer;
import org.tensorflow.util.buffer.impl.raw.IntRawDataBuffer;
import org.tensorflow.util.buffer.impl.raw.LongRawDataBuffer;
import org.tensorflow.util.buffer.impl.raw.ShortRawDataBuffer;
import org.tensorflow.util.buffer.impl.raw.UnsafeReference;
import org.tensorflow.util.buffer.impl.virtual.BooleanVirtualDataBuffer;
import org.tensorflow.util.buffer.impl.virtual.DoubleVirtualDataBuffer;
import org.tensorflow.util.buffer.impl.virtual.FloatVirtualDataBuffer;
import org.tensorflow.util.buffer.impl.virtual.IntVirtualDataBuffer;
import org.tensorflow.util.buffer.impl.virtual.LongVirtualDataBuffer;
import org.tensorflow.util.buffer.impl.virtual.ShortVirtualDataBuffer;
import org.tensorflow.util.buffer.impl.virtual.VirtualDataBuffer;

/**
 * Helper class for creating {@code DataBuffer} instances.
 */
public final class DataBuffers {

  /**
   * Creates a buffer of bytes that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static ByteDataBuffer ofBytes(long size) {
    return UnsafeReference.isAvailableInternally() ? ByteRawDataBuffer.allocate(size) : ByteJdkDataBuffer.allocate(size);
  }

  /**
   * Wraps an array of bytes into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new buffer
   */
  public static ByteDataBuffer wrap(byte[] array, boolean readOnly) {
    if (UnsafeReference.isAvailableInternally()) {
      return ByteRawDataBuffer.wrap(array, readOnly);
    }
    ByteBuffer buf = ByteBuffer.wrap(array);
    return ByteJdkDataBuffer.wrap(readOnly ? buf.asReadOnlyBuffer() : buf);
  }

  /**
   * Wraps a JDK byte buffers into a data buffer.
   *
   * @param buf buffer to wrap
   * @return a new buffer
   */
  public static ByteDataBuffer wrap(ByteBuffer buf) {
    return ByteJdkDataBuffer.wrap(buf);
  }

  /**
   * Creates a buffer of longs that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static LongDataBuffer ofLongs(long size) {
    return UnsafeReference.isAvailableInternally() ? LongRawDataBuffer.allocate(size) : LongJdkDataBuffer.allocate(size);
  }

  /**
   * Creates a virtual buffer of longs that can store up to {@code size} values.
   *
   * <p>The provided adapter is used to create the long values to/from bytes, allowing custom
   * representation of a long.
   *
   * @param size size of the buffer to allocate
   * @param adapter an object converting buffer data to longs
   * @return a new buffer
   */
  public static LongDataBuffer ofLongs(long size, LongDataAdapter adapter) {
    return toLongs(ofBytes(size * adapter.sizeInBytes()), adapter);
  }

  /**
   * Adapt a physical buffer to a virtual buffer of longs.
   *
   * <p>The provided adapter is used to create the long values to/from bytes, allowing custom
   * representation of a long integer.
   *
   * @param buffer the buffer to adapt
   * @param adapter an object converting buffer data to integers
   * @return a new buffer
   */
  public static LongDataBuffer toLongs(ByteDataBuffer buffer, LongDataAdapter adapter) {
    return LongVirtualDataBuffer.create(buffer, adapter);
  }

  /**
   * Wraps an array of longs into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new buffer
   */
  public static LongDataBuffer wrap(long[] array, boolean readOnly) {
    if (UnsafeReference.isAvailableInternally()) {
      return LongRawDataBuffer.wrap(array, readOnly);
    }
    LongBuffer buf = LongBuffer.wrap(array);
    return LongJdkDataBuffer.wrap(readOnly ? buf.asReadOnlyBuffer() : buf);
  }

  /**
   * Wraps a JDK long buffer into a data buffer.
   *
   * @param buf buffer to wrap
   * @return a new buffer
   */
  public static LongDataBuffer wrap(LongBuffer buf) {
    return LongJdkDataBuffer.wrap(buf);
  }

  /**
   * Creates a buffer of integers that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static IntDataBuffer ofInts(long size) {
    return UnsafeReference.isAvailableInternally() ? IntRawDataBuffer.allocate(size) : IntJdkDataBuffer.allocate(size);
  }

  /**
   * Creates a virtual buffer of integers that can store up to {@code size} values.
   *
   * <p>The provided adapter is used to create the integer values to/from bytes, allowing custom
   * representation of an integer.
   *
   * @param size size of the buffer to allocate
   * @param adapter an object converting buffer data to integers
   * @return a new buffer
   */
  public static IntDataBuffer ofInts(long size, IntDataAdapter adapter) {
    return toInts(ofBytes(size * adapter.sizeInBytes()), adapter);
  }

  /**
   * Adapt a physical buffer to a virtual buffer of integers.
   *
   * <p>The provided adapter is used to create the integer values to/from bytes, allowing custom
   * representation of a integer.
   *
   * @param buffer the buffer to adapt
   * @param adapter an object converting buffer data to integers
   * @return a new buffer
   */
  public static IntDataBuffer toInts(ByteDataBuffer buffer, IntDataAdapter adapter) {
    return IntVirtualDataBuffer.create(buffer, adapter);
  }

  /**
   * Wraps an array of integers into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new buffer
   */
  public static IntDataBuffer wrap(int[] array, boolean readOnly) {
    if (UnsafeReference.isAvailableInternally()) {
      return IntRawDataBuffer.wrap(array, readOnly);
    }
    IntBuffer buf = IntBuffer.wrap(array);
    return IntJdkDataBuffer.wrap(readOnly ? buf.asReadOnlyBuffer() : buf);
  }

  /**
   * Wraps a JDK integer buffer into a data buffer.
   *
   * @param buf buffer to wrap
   * @return a new buffer
   */
  public static IntDataBuffer wrap(IntBuffer buf) {
    return IntJdkDataBuffer.wrap(buf);
  }

  /**
   * Creates a buffer of shorts that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static ShortDataBuffer ofShorts(long size) {
    return UnsafeReference.isAvailableInternally() ? ShortRawDataBuffer.allocate(size) : ShortJdkDataBuffer.allocate(size);
  }

  /**
   * Creates a virtual buffer of shorts that can store up to {@code size} values.
   *
   * <p>The provided adapter is used to create the short values to/from bytes, allowing custom
   * representation of a short.
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static ShortDataBuffer ofShorts(long size, ShortDataAdapter adapter) {
    return toShorts(ofBytes(size * adapter.sizeInBytes()), adapter);
  }

  /**
   * Adapt a physical buffer to a virtual buffer of shorts.
   *
   * <p>The provided adapter is used to create the short values to/from bytes, allowing custom
   * representation of a short.
   *
   * @param buffer the buffer to adapt
   * @param adapter an object converting buffer data to shorts
   * @return a new buffer
   */
  public static ShortDataBuffer toShorts(ByteDataBuffer buffer, ShortDataAdapter adapter) {
    return ShortVirtualDataBuffer.create(buffer, adapter);
  }

  /**
   * Wraps an array of shorts into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new buffer
   */
  public static ShortDataBuffer wrap(short[] array, boolean readOnly) {
    if (UnsafeReference.isAvailableInternally()) {
      return ShortRawDataBuffer.wrap(array, readOnly);
    }
    ShortBuffer buf = ShortBuffer.wrap(array);
    return ShortJdkDataBuffer.wrap(readOnly ? buf.asReadOnlyBuffer() : buf);
  }

  /**
   * Wraps a JDK short buffer into a data buffer.
   *
   * @param buf buffer to wrap
   * @return a new buffer
   */
  public static ShortDataBuffer wrap(ShortBuffer buf) {
    return ShortJdkDataBuffer.wrap(buf);
  }

  /**
   * Creates a buffer of doubles that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static DoubleDataBuffer ofDoubles(long size) {
    return UnsafeReference.isAvailableInternally() ? DoubleRawDataBuffer.allocate(size) : DoubleJdkDataBuffer.allocate(size);
  }

  /**
   * Creates a virtual buffer of doubles that can store up to {@code size} values.
   *
   * <p>The provided adapter is used to create the double values to/from bytes, allowing custom
   * representation of a double.
   *
   * @param size size of the buffer to allocate
   * @param adapter an object converting buffer data to doubles
   * @return a new buffer
   */
  public static DoubleDataBuffer ofDoubles(long size, DoubleDataAdapter adapter) {
    return toDoubles(ofBytes(size * adapter.sizeInBytes()), adapter);
  }

  /**
   * Adapt a physical buffer to a virtual buffer of doubles.
   *
   * <p>The provided adapter is used to create the double values to/from bytes, allowing custom
   * representation of a double.
   *
   * @param buffer the buffer to adapt
   * @param adapter an object converting buffer data to doubles
   * @return a new buffer
   */
  public static DoubleDataBuffer toDoubles(ByteDataBuffer buffer, DoubleDataAdapter adapter) {
    return DoubleVirtualDataBuffer.create(buffer, adapter);
  }

  /**
   * Wraps an array of doubles into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new buffer
   */
  public static DoubleDataBuffer wrap(double[] array, boolean readOnly) {
    if (UnsafeReference.isAvailableInternally()) {
      return DoubleRawDataBuffer.wrap(array, readOnly);
    }
    DoubleBuffer buf = DoubleBuffer.wrap(array);
    return DoubleJdkDataBuffer.wrap(readOnly ? buf.asReadOnlyBuffer() : buf);
  }

  /**
   * Wraps a JDK double buffer into a data buffer.
   *
   * @param buf buffer to wrap
   * @return a new buffer
   */
  public static DoubleDataBuffer wrap(DoubleBuffer buf) {
    return DoubleJdkDataBuffer.wrap(buf);
  }

  /**
   * Creates a buffer of floats that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static FloatDataBuffer ofFloats(long size) {
    return UnsafeReference.isAvailableInternally() ? FloatRawDataBuffer.allocate(size) : FloatJdkDataBuffer.allocate(size);
  }

  /**
   * Creates a virtual buffer of floats that can store up to {@code size} values.
   *
   * <p>The provided adapter is used to create the float values to/from bytes, allowing custom
   * representation of a float.
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static FloatDataBuffer ofFloats(long size, FloatDataAdapter adapter) {
    return toFloats(ofBytes(size * adapter.sizeInBytes()), adapter);
  }

  /**
   * Adapt a physical buffer to a virtual buffer of floats.
   *
   * <p>The provided adapter is used to create the float values to/from bytes, allowing custom
   * representation of a float.
   *
   * @param buffer the buffer to adapt
   * @param adapter an object converting buffer data to floats
   * @return a new buffer
   */
  public static FloatDataBuffer toFloats(ByteDataBuffer buffer, FloatDataAdapter adapter) {
    return FloatVirtualDataBuffer.create(buffer, adapter);
  }

  /**
   * Wraps an array of floats into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new buffer
   */
  public static FloatDataBuffer wrap(float[] array, boolean readOnly) {
    if (UnsafeReference.isAvailableInternally()) {
      return FloatRawDataBuffer.wrap(array, readOnly);
    }
    FloatBuffer buf = FloatBuffer.wrap(array);
    return FloatJdkDataBuffer.wrap(readOnly ? buf.asReadOnlyBuffer() : buf);
  }

  /**
   * Wraps a JDK float buffer into a data buffer.
   *
   * @param buf buffer to wrap
   * @return a new buffer
   */
  public static FloatDataBuffer wrap(FloatBuffer buf) {
    return FloatJdkDataBuffer.wrap(buf);
  }

  /**
   * Creates a buffer of booleans that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static BooleanDataBuffer ofBooleans(long size) {
    return UnsafeReference.isAvailableInternally() ? BooleanRawDataBuffer.allocate(size) : BitSetDataBuffer.allocate(size);
  }

  /**
   * Creates a virtual buffer of booleans that can store up to {@code size} values.
   *
   * <p>The provided adapter is used to create the boolean values to/from bytes, allowing custom
   * representation of a boolean.
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static BooleanDataBuffer ofBooleans(long size, BooleanDataAdapter adapter) {
    return toBooleans(ofBytes(size * adapter.sizeInBytes()), adapter);
  }

  /**
   * Adapt a physical buffer to a virtual buffer of booleans.
   *
   * <p>The provided adapter is used to create the boolean values to/from bytes, allowing custom
   * representation of a boolean.
   *
   * @param buffer the buffer to adapt
   * @param adapter an object converting buffer data to booleans
   * @return a new buffer
   */
  public static BooleanDataBuffer toBooleans(ByteDataBuffer buffer, BooleanDataAdapter adapter) {
    return BooleanVirtualDataBuffer.create(buffer, adapter);
  }

  /**
   * Wraps an array of booleans into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new buffer
   */
  public static BooleanDataBuffer wrap(boolean[] array, boolean readOnly) {
    if (UnsafeReference.isAvailableInternally()) {
      return BooleanRawDataBuffer.wrap(array, readOnly);
    }
    return BooleanArrayDataBuffer.wrap(array, readOnly);
  }

  /**
   * Creates a buffer of objects of type {@code clazz` that can store up to `size} values
   *
   * @param type the type of object stored in this buffer
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static <T> DataBuffer<T> of(Class<T> type, long size) {
    return ArrayDataBuffer.allocate(type, size);
  }

  /**
   * Creates a virtual buffer that can store up to {@code size} values.
   *
   * <p>The provided adapter is used to create the values to/from bytes, allowing custom
   * representation of this buffer type.
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static <T> DataBuffer<T> of(long size, DataAdapter<T> adapter) {
    return to(ofBytes(size * adapter.sizeInBytes()), adapter);
  }

  /**
   * Adapt a physical buffer to a virtual buffer.
   *
   * <p>The provided adapter is used to create the values to/from bytes, allowing custom
   * representation of this buffer type.
   *
   * @param buffer the buffer to adapt
   * @param adapter an object converting buffer data to booleans
   * @return a new buffer
   */
  public static <T> DataBuffer<T> to(ByteDataBuffer buffer, DataAdapter<T> adapter) {
    return VirtualDataBuffer.create(buffer, adapter);
  }

  /**
   * Wraps an array of objects into a data buffer.
   *
   * @param array array to wrap
   * @param readOnly true if the buffer created must be read-only
   * @return a new buffer
   */
  public static <T> DataBuffer<T> wrap(T[] array, boolean readOnly) {
    return ArrayDataBuffer.wrap(array, readOnly);
  }
}
