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

package org.tensorflow.internal.buffer;

import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorByteSize;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TensorData;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import org.bytedeco.javacpp.Pointer;
import org.tensorflow.internal.c_api.TF_Tensor;
import org.tensorflow.tools.buffer.BooleanDataBuffer;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffers;
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.ShortDataBuffer;
import org.tensorflow.tools.buffer.layout.DataLayouts;

public final class TensorBuffers {

  public static ByteDataBuffer toBytes(TF_Tensor nativeTensor) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToBytes(tensorMemory);
    }
    return DataBuffers.from(tensorMemory.asByteBuffer());
  }

  public static IntDataBuffer toInts(TF_Tensor nativeTensor) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToInts(tensorMemory);
    }
    return DataBuffers.from(tensorMemory.asByteBuffer().asIntBuffer());
  }

  public static LongDataBuffer toLongs(TF_Tensor nativeTensor) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToLongs(tensorMemory);
    }
    return DataBuffers.from(tensorMemory.asByteBuffer().asLongBuffer());
  }

  public static FloatDataBuffer toFloats(TF_Tensor nativeTensor) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToFloats(tensorMemory);
    }
    return DataBuffers.from(tensorMemory.asByteBuffer().asFloatBuffer());
  }

  public static DoubleDataBuffer toDoubles(TF_Tensor nativeTensor) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToDoubles(tensorMemory);
    }
    return DataBuffers.from(tensorMemory.asByteBuffer().asDoubleBuffer());
  }

  public static ShortDataBuffer toShorts(TF_Tensor nativeTensor) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToShorts(tensorMemory);
    }
    return DataBuffers.from(tensorMemory.asByteBuffer().asShortBuffer());
  }

  public static BooleanDataBuffer toBooleans(TF_Tensor nativeTensor) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToBooleans(tensorMemory);
    }
    // There is no boolean buffers in Java NIO, so apply a layout that converts booleans
    // from/to bytes when raw memory mapping is not available.
    return DataLayouts.BOOL.applyTo(DataBuffers.from(tensorMemory.asByteBuffer()));
  }

  public static StringTensorBuffer toStrings(TF_Tensor nativeTensor, long numElements) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToStrings(tensorMemory, numElements);
    }
    if (numElements > Integer.MAX_VALUE) {
      throw new IllegalArgumentException("Cannot map string tensor of " + numElements + " elements");
    }
    ByteBuffer dataBuffer = tensorMemory.asByteBuffer();

    LongBuffer offsetBuffer = dataBuffer.asLongBuffer();
    offsetBuffer.limit((int)numElements);
    LongDataBuffer offsets = DataBuffers.from(offsetBuffer.slice());

    dataBuffer.position((int)numElements * Long.BYTES);
    ByteDataBuffer data = DataBuffers.from(dataBuffer.slice());

    return new StringTensorBuffer(offsets, data);
  }

  private static Pointer tensorMemory(TF_Tensor nativeTensor) {
    return TF_TensorData(nativeTensor).capacity(TF_TensorByteSize(nativeTensor));
  }
}
