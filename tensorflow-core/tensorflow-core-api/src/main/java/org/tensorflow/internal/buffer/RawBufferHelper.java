package org.tensorflow.internal.buffer;

import java.lang.reflect.Field;
import org.bytedeco.javacpp.Pointer;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.impl.raw.ByteRawDataBuffer;
import org.tensorflow.tools.buffer.impl.raw.LongRawDataBuffer;
import org.tensorflow.tools.buffer.impl.raw.UnsafeReference;

final class RawBufferHelper {

  interface Mapper<T> {
    T map(UnsafeReference unsafe, long address, long size, boolean readOnly);
  }

  static <T> T map(Pointer tensorData, Mapper<T> mapper) {
    if (UNSAFE == null) {
      throw new RuntimeException("No valid reference to an instance of 'sun.misc.Unsafe'");
    }
    return mapper.map(UNSAFE, tensorData.address(), tensorData.capacity(), false);
  }

  static StringTensorBuffer mapToStrings(Pointer tensorData, long numElements) {
    if (UNSAFE == null) {
      throw new RuntimeException("No valid reference to an instance of 'sun.misc.Unsafe'");
    }
    long offsetByteSize = numElements * Long.BYTES;
    LongDataBuffer offsets =
        LongRawDataBuffer.map(UNSAFE, tensorData.address(), numElements, false);
    ByteDataBuffer data =
        ByteRawDataBuffer.map(UNSAFE, tensorData.address() + offsetByteSize, tensorData.capacity() - offsetByteSize, false);
    return new StringTensorBuffer(offsets, data);
  }

  static boolean isUnsafeAvailable() {
    return UNSAFE != null;
  }

  private static final UnsafeReference UNSAFE;

  static {
    UnsafeReference ref = null;
    try {
      Class<?> clazz = Class.forName("sun.misc.Unsafe");
      Field theUnsafe = clazz.getDeclaredField("theUnsafe");
      theUnsafe.setAccessible(true);
      ref = UnsafeReference.from(theUnsafe.get(null));
    } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
      // Do nothing and leave unsafe reference as null
    }
    UNSAFE = ref;
  }
}
