package org.tensorflow.internal.buffer;

import org.bytedeco.javacpp.Pointer;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.impl.raw.RawDataBufferFactory;

class TensorRawDataBufferFactory extends RawDataBufferFactory {

  static ByteDataBuffer mapTensorToBytes(Pointer tensorMemory) {
    return mapNativeBytes(tensorMemory.address(), tensorMemory.capacity(), false);
  }

  static IntDataBuffer mapTensorToInts(Pointer tensorMemory) {
    return mapNativeInts(tensorMemory.address(), tensorMemory.capacity(), false);
  }

  static LongDataBuffer mapTensorToLongs(Pointer tensorMemory) {
    return mapNativeLongs(tensorMemory.address(), tensorMemory.capacity(), false);
  }

  static FloatDataBuffer mapTensorToFloats(Pointer tensorMemory) {
    return mapNativeFloats(tensorMemory.address(), tensorMemory.capacity(), false);
  }

  static DoubleDataBuffer mapTensorToDoubles(Pointer tensorMemory) {
    return mapNativeDoubles(tensorMemory.address(), tensorMemory.capacity(), false);
  }

  static StringTensorBuffer mapTensorToStrings(Pointer tensorMemory, long numElements) {
    long offsetByteSize = numElements * Long.BYTES;
    LongDataBuffer offsets = mapNativeLongs(tensorMemory.address(), numElements, false);
    ByteDataBuffer data = mapNativeBytes(
        tensorMemory.address() + offsetByteSize,
        tensorMemory.capacity() - offsetByteSize,
        false);
    return new StringTensorBuffer(offsets, data);
  }
}
