package org.tensorflow.internal.buffer;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorByteSize;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorData;

import java.lang.reflect.Field;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.LongDataBuffer;
import org.tensorflow.nio.buffer.impl.raw.ByteRawDataBuffer;
import org.tensorflow.nio.buffer.impl.raw.LongRawDataBuffer;
import sun.misc.Unsafe;

final class RawBufferHelper {

  interface Mapper<T> {
    T map(Unsafe unsafe, long address, long size, boolean readOnly);
  }

  static <T> T map(TF_Tensor nativeTensor, Mapper<T> mapper) {
    long address = TF_TensorData(nativeTensor).address();
    long byteSize = TF_TensorByteSize(nativeTensor);
    return mapper.map(UNSAFE, address, byteSize, false);
  }

  static StringTensorBuffer mapToStrings(TF_Tensor nativeTensor, long numElements) {
    long address = TF_TensorData(nativeTensor).address();
    long byteSize = TF_TensorByteSize(nativeTensor);
    long offsetByteSize = numElements * Long.BYTES;
    LongDataBuffer offsets = LongRawDataBuffer.map(UNSAFE, address, numElements, false);
    ByteDataBuffer data = ByteRawDataBuffer.map(UNSAFE, address + offsetByteSize, byteSize - offsetByteSize, false);
    return new StringTensorBuffer(offsets, data);
  }

  static final Unsafe UNSAFE;

  static {
    try {
      Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
      theUnsafe.setAccessible(true);
      UNSAFE = (Unsafe) theUnsafe.get(null);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}
