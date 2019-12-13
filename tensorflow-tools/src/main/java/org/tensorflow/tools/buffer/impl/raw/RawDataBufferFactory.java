package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.BooleanDataBuffer;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.ShortDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

/**
 * Factory of raw data buffers
 */
public class RawDataBufferFactory {

  public static boolean canBeUsed() {
    return UnsafeReference.isAvailable();
  }

  public static BooleanDataBuffer allocateBooleans(long size) {
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new boolean[(int)size], false);
  }

  public static ByteDataBuffer allocateBytes(long size) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new byte[(int)size], false);
  }

  public static DoubleDataBuffer allocateDoubles(long size) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new double[(int)size], false);
  }

  public static FloatDataBuffer allocateFloats(long size) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new float[(int)size], false);
  }

  public static IntDataBuffer allocateInts(long size) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new int[(int)size], false);
  }

  public static LongDataBuffer allocateLongs(long size) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new long[(int)size], false);
  }

  public static ShortDataBuffer allocateShorts(long size) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new short[(int)size], false);
  }

  public static BooleanDataBuffer wrap(boolean[] array, boolean readOnly) {
    return new BooleanRawDataBuffer(UnsafeMemoryHandle.fromArray(array, array.length), readOnly);
  }

  public static ByteDataBuffer wrap(byte[] array, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    return new ByteRawDataBuffer(UnsafeMemoryHandle.fromArray(array, array.length), readOnly);
  }

  public static DoubleDataBuffer wrap(double[] array, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    return new DoubleRawDataBuffer(UnsafeMemoryHandle.fromArray(array, array.length), readOnly);
  }

  public static FloatDataBuffer wrap(float[] array, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    return new FloatRawDataBuffer(UnsafeMemoryHandle.fromArray(array, array.length), readOnly);
  }

  public static IntDataBuffer wrap(int[] array, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    return new IntRawDataBuffer(UnsafeMemoryHandle.fromArray(array, array.length), readOnly);
  }

  public static LongDataBuffer wrap(long[] array, boolean readOnly) {
    if (!canBeUsed()) {
      throw new IllegalStateException("Raw data buffers are not available");
    }
    return new LongRawDataBuffer(UnsafeMemoryHandle.fromArray(array, array.length), readOnly);
  }

  public static ShortDataBuffer wrap(short[] array, boolean readOnly) {
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
