package org.tensorflow.internal.buffer;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffers;
import org.tensorflow.nio.buffer.DoubleDataBuffer;
import org.tensorflow.nio.buffer.FloatDataBuffer;
import org.tensorflow.nio.buffer.IntDataBuffer;
import org.tensorflow.nio.buffer.LongDataBuffer;
import org.tensorflow.nio.buffer.impl.raw.ByteRawDataBuffer;
import org.tensorflow.nio.buffer.impl.raw.DoubleRawDataBuffer;
import org.tensorflow.nio.buffer.impl.raw.FloatRawDataBuffer;
import org.tensorflow.nio.buffer.impl.raw.IntRawDataBuffer;
import org.tensorflow.nio.buffer.impl.raw.LongRawDataBuffer;

public final class TensorBuffers {

  public static ByteDataBuffer toBytes(TF_Tensor nativeTensor) {
    if (unsafeAvailable) {
      return RawBufferHelper.map(nativeTensor, ByteRawDataBuffer::map);
    }
    return DataBuffers.wrap(directBuffer(nativeTensor.address()));
  }

  public static IntDataBuffer toInts(TF_Tensor nativeTensor) {
    if (unsafeAvailable) {
      return RawBufferHelper.map(nativeTensor, IntRawDataBuffer::map);
    }
    return DataBuffers.wrap(directBuffer(nativeTensor.address()).asIntBuffer());
  }

  public static LongDataBuffer toLongs(TF_Tensor nativeTensor) {
    if (unsafeAvailable) {
      return RawBufferHelper.map(nativeTensor, LongRawDataBuffer::map);
    }
    return DataBuffers.wrap(directBuffer(nativeTensor.address()).asLongBuffer());
  }

  public static FloatDataBuffer toFloats(TF_Tensor nativeTensor) {
    if (unsafeAvailable) {
      return RawBufferHelper.map(nativeTensor, FloatRawDataBuffer::map);
    }
    return DataBuffers.wrap(directBuffer(nativeTensor.address()).asFloatBuffer());
  }

  public static DoubleDataBuffer toDoubles(TF_Tensor nativeTensor) {
    if (unsafeAvailable) {
      return RawBufferHelper.map(nativeTensor, DoubleRawDataBuffer::map);
    }
    return DataBuffers.wrap(directBuffer(nativeTensor.address()).asDoubleBuffer());
  }

  public static StringTensorBuffer toStrings(TF_Tensor nativeTensor, long numElements) {
    if (unsafeAvailable) {
      return RawBufferHelper.mapToStrings(nativeTensor, numElements);
    }
    if (numElements > Integer.MAX_VALUE) {
      throw new IllegalArgumentException("Cannot map string tensor of " + numElements + " elements");
    }
    ByteBuffer bytes = directBuffer(nativeTensor.address());

    LongBuffer offsetBuffer = bytes.asLongBuffer();
    offsetBuffer.limit((int)numElements);
    LongDataBuffer offsets = DataBuffers.wrap(offsetBuffer.slice());

    ByteBuffer dataBuffer = bytes.duplicate();
    dataBuffer.position((int)numElements * Long.BYTES);
    ByteDataBuffer data = DataBuffers.wrap(dataBuffer.slice());

    return new StringTensorBuffer(offsets, data);
  }

  private static boolean unsafeAvailable;

  static {
    try {
      Class.forName("sun.misc.Unsafe");
      unsafeAvailable = true;
    } catch (ClassNotFoundException e) {
      unsafeAvailable = false;
    }
  }

  private static native ByteBuffer directBuffer(long handle);
}
