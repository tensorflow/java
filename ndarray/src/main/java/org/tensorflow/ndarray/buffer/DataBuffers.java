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
package org.tensorflow.ndarray.buffer;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.BitSet;
import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.impl.buffer.misc.MiscDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.nio.NioDataBufferFactory;
import org.tensorflow.ndarray.impl.buffer.raw.RawDataBufferFactory;

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
    Validator.createArgs(size, MAX_32BITS);
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(new byte[(int)size], false);
    }
    return NioDataBufferFactory.create(ByteBuffer.allocate((int)size));
  }

  /**
   * Creates a buffer of longs that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static LongDataBuffer ofLongs(long size) {
    Validator.createArgs(size, MAX_32BITS);
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(new long[(int)size], false);
    }
    return NioDataBufferFactory.create(LongBuffer.allocate((int)size));
  }

  /**
   * Creates a buffer of integers that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static IntDataBuffer ofInts(long size) {
    Validator.createArgs(size, MAX_32BITS);
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(new int[(int)size], false);
    }
    return NioDataBufferFactory.create(IntBuffer.allocate((int)size));
  }

  /**
   * Creates a buffer of shorts that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static ShortDataBuffer ofShorts(long size) {
    Validator.createArgs(size, MAX_32BITS);
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(new short[(int)size], false);
    }
    return NioDataBufferFactory.create(ShortBuffer.allocate((int)size));
  }

  /**
   * Creates a buffer of doubles that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static DoubleDataBuffer ofDoubles(long size) {
    Validator.createArgs(size, MAX_32BITS);
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(new double[(int)size], false);
    }
    return NioDataBufferFactory.create(DoubleBuffer.allocate((int)size));
  }

  /**
   * Creates a buffer of floats that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static FloatDataBuffer ofFloats(long size) {
    Validator.createArgs(size, MAX_32BITS);
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(new float[(int)size], false);
    }
    return NioDataBufferFactory.create(FloatBuffer.allocate((int)size));
  }

  /**
   * Creates a buffer of booleans that can store up to {@code size} values
   *
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static BooleanDataBuffer ofBooleans(long size) {
    Validator.createArgs(size, MAX_32BITS);
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(new boolean[(int)size], false);
    }
    return MiscDataBufferFactory.create(new BitSet((int)size), size, false);
  }

  /**
   * Creates a buffer of references to objects of type {@code clazz` that can store up to `size}
   * values.
   *
   * @param type the type of object stored in this buffer
   * @param size size of the buffer to allocate
   * @return a new buffer
   */
  public static <T> DataBuffer<T> ofObjects(Class<T> type, long size) {
    Validator.createArgs(size, MAX_32BITS);
    @SuppressWarnings("unchecked")
    T[] array = (T[])Array.newInstance(type, (int)size);
    return MiscDataBufferFactory.create(array, false);
  }

  /**
   * Create a buffer from an array of floats into a data buffer.
   *
   * <p>The returned buffer allows read and write operations and share the memory of the source
   * array, which is equivalent to call {@link #of(float[], boolean, boolean) of(values, false, false}}
   *
   * @param values float values
   * @return a new buffer
   */
  public static FloatDataBuffer of(float... values) {
    return of(values, false, false);
  }

  /**
   * Create a buffer from an array of bytes into a data buffer.
   *
   * <p>The returned buffer allows read and write operations and share the memory of the source
   * array, which is equivalent to call {@link #of(byte[], boolean, boolean) of(values, false, false}}
   *
   * @param values byte values
   * @return a new buffer
   */
  public static ByteDataBuffer of(byte... values) {
    return of(values, false, false);
  }

  /**
   * Create a buffer from an array of longs into a data buffer.
   *
   * <p>The returned buffer allows read and write operations and share the memory of the source
   * array, which is equivalent to call {@link #of(long[], boolean, boolean) of(values, false, false}}
   *
   * @param values long values
   * @return a new buffer
   */
  public static LongDataBuffer of(long... values) {
    return of(values, false, false);
  }

  /**
   * Create a buffer from an array of ints into a data buffer.
   *
   * <p>The returned buffer allows read and write operations and share the memory of the source
   * array, which is equivalent to call {@link #of(int[], boolean, boolean) of(values, false, false}}
   *
   * @param values int values
   * @return a new buffer
   */
  public static IntDataBuffer of(int... values) {
    return of(values, false, false);
  }

  /**
   * Create a buffer from an array of shorts into a data buffer.
   *
   * <p>The returned buffer allows read and write operations and share the memory of the source
   * array, which is equivalent to call {@link #of(short[], boolean, boolean) of(values, false, false}}
   *
   * @param values short values
   * @return a new buffer
   */
  public static ShortDataBuffer of(short... values) {
    return of(values, false, false);
  }

  /**
   * Create a buffer from an array of doubles into a data buffer.
   *
   * <p>The returned buffer allows read and write operations and share the memory of the source
   * array, which is equivalent to call {@link #of(double[], boolean, boolean) of(array, false, false}}
   *
   * @param values double values
   * @return a new buffer
   */
  public static DoubleDataBuffer of(double... values) {
    return of(values, false, false);
  }

  /**
   * Create a buffer from an array of booleans into a data buffer.
   *
   * <p>The returned buffer allows read and write operations and share the memory of the source
   * array, which is equivalent to call {@link #of(boolean[], boolean, boolean) of(values, false, false}}
   *
   * @param values booleans values
   * @return a new buffer
   */
  public static BooleanDataBuffer of(boolean... values) {
    return of(values, false, false);
  }

  /**
   * Create a buffer from an array of objects into a data buffer.
   *
   * <p>The returned buffer allows read and write operations and share the memory of the source
   * array, which is equivalent to call {@link #of(T[], boolean, boolean)} of(values, false, false}}
   *
   * @param values objects values
   * @return a new buffer
   */
  @SafeVarargs
  public static <T> DataBuffer<T> ofObjects(T... values) {
    return of(values, false, false);
  }

  /**
   * Create a buffer from an array of floats into a data buffer.
   *
   * @param array array of floats
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static FloatDataBuffer of(float[] array, boolean readOnly, boolean makeCopy) {
    float[] bufferArray = makeCopy ? Arrays.copyOf(array, array.length) : array;
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(bufferArray, readOnly);
    }
    FloatBuffer buf = FloatBuffer.wrap(bufferArray);
    return NioDataBufferFactory.create(readOnly ? buf.asReadOnlyBuffer() : buf);
  }

  /**
   * Create a buffer from an array of bytes into a data buffer.
   *
   * @param array array of bytes
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static ByteDataBuffer of(byte[] array, boolean readOnly, boolean makeCopy) {
    byte[] bufferArray = makeCopy ? Arrays.copyOf(array, array.length) : array;
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(bufferArray, readOnly);
    }
    ByteBuffer buf = ByteBuffer.wrap(bufferArray);
    return NioDataBufferFactory.create(readOnly ? buf.asReadOnlyBuffer() : buf);
  }

  /**
   * Create a buffer from an array of longs into a data buffer.
   *
   * @param array array of longs
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static LongDataBuffer of(long[] array, boolean readOnly, boolean makeCopy) {
    long[] bufferArray = makeCopy ? Arrays.copyOf(array, array.length) : array;
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(bufferArray, readOnly);
    }
    LongBuffer buf = LongBuffer.wrap(bufferArray);
    return NioDataBufferFactory.create(readOnly ? buf.asReadOnlyBuffer() : buf);
  }

  /**
   * Create a buffer from an array of ints into a data buffer.
   *
   * @param array array of ints
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static IntDataBuffer of(int[] array, boolean readOnly, boolean makeCopy) {
    int[] bufferArray = makeCopy ? Arrays.copyOf(array, array.length) : array;
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(bufferArray, readOnly);
    }
    IntBuffer buf = IntBuffer.wrap(bufferArray);
    return NioDataBufferFactory.create(readOnly ? buf.asReadOnlyBuffer() : buf);
  }

  /**
   * Create a buffer from an array of shorts into a data buffer.
   *
   * @param array array of shorts
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static ShortDataBuffer of(short[] array, boolean readOnly, boolean makeCopy) {
    short[] bufferArray = makeCopy ? Arrays.copyOf(array, array.length) : array;
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(bufferArray, readOnly);
    }
    ShortBuffer buf = ShortBuffer.wrap(bufferArray);
    return NioDataBufferFactory.create(readOnly ? buf.asReadOnlyBuffer() : buf);
  }

  /**
   * Create a buffer from an array of doubles into a data buffer.
   *
   * @param array array of doubles
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static DoubleDataBuffer of(double[] array, boolean readOnly, boolean makeCopy) {
    double[] bufferArray = makeCopy ? Arrays.copyOf(array, array.length) : array;
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(bufferArray, readOnly);
    }
    DoubleBuffer buf = DoubleBuffer.wrap(bufferArray);
    return NioDataBufferFactory.create(readOnly ? buf.asReadOnlyBuffer() : buf);
  }

  /**
   * Create a buffer from an array of booleans into a data buffer.
   *
   * @param array array of booleans
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static BooleanDataBuffer of(boolean[] array, boolean readOnly, boolean makeCopy) {
    boolean[] bufferArray = makeCopy ? Arrays.copyOf(array, array.length) : array;
    if (RawDataBufferFactory.canBeUsed()) {
      return RawDataBufferFactory.create(bufferArray, readOnly);
    }
    return MiscDataBufferFactory.create(bufferArray, readOnly);
  }

  /**
   * Create a buffer from an array of objects into a data buffer.
   *
   * @param array array of objects
   * @param readOnly true if the buffer created must be read-only
   * @param makeCopy true if the array must be copied, false will wrap the provided array
   * @return a new buffer
   */
  public static <T> DataBuffer<T> of(T[] array, boolean readOnly, boolean makeCopy) {
    T[] bufferArray = makeCopy ? Arrays.copyOf(array, array.length) : array;
    return MiscDataBufferFactory.create(bufferArray, readOnly);
  }

  /**
   * Wraps a JDK NIO {@link ByteBuffer} into a data buffer.
   *
   * @param buf buffer to wrap
   * @return a new buffer
   */
  public static ByteDataBuffer of(ByteBuffer buf) {
    return NioDataBufferFactory.create(buf.duplicate());
  }

  /**
   * Wraps a JDK NIO {@link IntBuffer} into a data buffer.
   *
   * @param buf buffer to wrap
   * @return a new buffer
   */
  public static IntDataBuffer of(IntBuffer buf) {
    return NioDataBufferFactory.create(buf.duplicate());
  }

  /**
   * Wraps a JDK NIO {@link ShortBuffer} into a data buffer.
   *
   * @param buf buffer to wrap
   * @return a new buffer
   */
  public static ShortDataBuffer of(ShortBuffer buf) {
    return NioDataBufferFactory.create(buf.duplicate());
  }

  /**
   * Wraps a JDK NIO {@link LongBuffer} into a data buffer.
   *
   * @param buf buffer to wrap
   * @return a new buffer
   */
  public static LongDataBuffer of(LongBuffer buf) {
    return NioDataBufferFactory.create(buf.duplicate());
  }

  /**
   * Wraps a JDK NIO {@link FloatBuffer} into a data buffer.
   *
   * @param buf buffer to wrap
   * @return a new buffer
   */
  public static FloatDataBuffer of(FloatBuffer buf) {
    return NioDataBufferFactory.create(buf.duplicate());
  }

  /**
   * Wraps a JDK NIO {@link DoubleBuffer} into a data buffer.
   *
   * @param buf buffer to wrap
   * @return a new buffer
   */
  public static DoubleDataBuffer of(DoubleBuffer buf) {
    return NioDataBufferFactory.create(buf.duplicate());
  }

  /*
   * The maximum size for a buffer of this type, i.e. the maximum number of bytes it can store.
   * <p>
   * As the maximum size may vary depending on the JVM implementation and on the platform, this
   * property returns a value that is safe for most of them.
   */
  static long MAX_32BITS = Integer.MAX_VALUE - 10;
}
