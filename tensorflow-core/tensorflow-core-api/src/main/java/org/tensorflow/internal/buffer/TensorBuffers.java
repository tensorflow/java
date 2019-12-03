package org.tensorflow.internal.buffer;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorByteSize;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorData;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import org.bytedeco.javacpp.Pointer;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.util.buffer.ByteDataBuffer;
import org.tensorflow.util.buffer.DataBuffers;
import org.tensorflow.util.buffer.DoubleDataBuffer;
import org.tensorflow.util.buffer.FloatDataBuffer;
import org.tensorflow.util.buffer.IntDataBuffer;
import org.tensorflow.util.buffer.LongDataBuffer;
import org.tensorflow.util.buffer.impl.raw.ByteRawDataBuffer;
import org.tensorflow.util.buffer.impl.raw.DoubleRawDataBuffer;
import org.tensorflow.util.buffer.impl.raw.FloatRawDataBuffer;
import org.tensorflow.util.buffer.impl.raw.IntRawDataBuffer;
import org.tensorflow.util.buffer.impl.raw.LongRawDataBuffer;

public final class TensorBuffers {

  public static ByteDataBuffer toBytes(TF_Tensor nativeTensor) {
    Pointer tensorData = tensorData(nativeTensor);
    if (RawBufferHelper.isUnsafeAvailable()) {
      return RawBufferHelper.map(tensorData, ByteRawDataBuffer::map);
    }
    return DataBuffers.wrap(tensorData.asByteBuffer());
  }

  public static IntDataBuffer toInts(TF_Tensor nativeTensor) {
    Pointer tensorData = tensorData(nativeTensor);
    if (RawBufferHelper.isUnsafeAvailable()) {
      return RawBufferHelper.map(tensorData, IntRawDataBuffer::map);
    }
    return DataBuffers.wrap(tensorData.asByteBuffer().asIntBuffer());
  }

  public static LongDataBuffer toLongs(TF_Tensor nativeTensor) {
    Pointer tensorData = tensorData(nativeTensor);
    if (RawBufferHelper.isUnsafeAvailable()) {
      return RawBufferHelper.map(tensorData, LongRawDataBuffer::map);
    }
    return DataBuffers.wrap(tensorData.asByteBuffer().asLongBuffer());
  }

  public static FloatDataBuffer toFloats(TF_Tensor nativeTensor) {
    Pointer tensorData = tensorData(nativeTensor);
    if (RawBufferHelper.isUnsafeAvailable()) {
      return RawBufferHelper.map(tensorData, FloatRawDataBuffer::map);
    }
    return DataBuffers.wrap(tensorData.asByteBuffer().asFloatBuffer());
  }

  public static DoubleDataBuffer toDoubles(TF_Tensor nativeTensor) {
    Pointer tensorData = tensorData(nativeTensor);
    if (RawBufferHelper.isUnsafeAvailable()) {
      return RawBufferHelper.map(tensorData, DoubleRawDataBuffer::map);
    }
    return DataBuffers.wrap(tensorData.asByteBuffer().asDoubleBuffer());
  }

  public static StringTensorBuffer toStrings(TF_Tensor nativeTensor, long numElements) {
    Pointer tensorData = tensorData(nativeTensor);
    if (RawBufferHelper.isUnsafeAvailable()) {
      return RawBufferHelper.mapToStrings(tensorData, numElements);
    }
    if (numElements > Integer.MAX_VALUE) {
      throw new IllegalArgumentException("Cannot map string tensor of " + numElements + " elements");
    }
    ByteBuffer dataBuffer = tensorData.asByteBuffer();

    LongBuffer offsetBuffer = dataBuffer.asLongBuffer();
    offsetBuffer.limit((int)numElements);
    LongDataBuffer offsets = DataBuffers.wrap(offsetBuffer.slice());

    dataBuffer.position((int)numElements * Long.BYTES);
    ByteDataBuffer data = DataBuffers.wrap(dataBuffer.slice());

    return new StringTensorBuffer(offsets, data);
  }

  private static Pointer tensorData(TF_Tensor nativeTensor) {
    return TF_TensorData(nativeTensor).capacity(TF_TensorByteSize(nativeTensor));
  }
}
