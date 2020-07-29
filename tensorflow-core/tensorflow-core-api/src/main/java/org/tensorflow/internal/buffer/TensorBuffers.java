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
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayouts;

/**
 * Maps native tensor memory into {@link DataBuffers}, allowing I/O operations from the JVM.
 */
public final class TensorBuffers {

  /**
   * Maps tensor memory as a buffer of bytes.
   *
   * @param nativeTensor native reference to the tensor
   * @return a byte buffer
   */
  public static ByteDataBuffer toBytes(TF_Tensor nativeTensor) {
    return toBytes(nativeTensor, false);
  }

  /**
   * Maps tensor memory as a buffer of bytes.
   *
   * @param nativeTensor native reference to the tensor
   * @param readOnly true to return a read-only buffer
   * @return a byte buffer
   */
  public static ByteDataBuffer toBytes(TF_Tensor nativeTensor, boolean readOnly) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToBytes(tensorMemory, readOnly);
    }
    return DataBuffers.of(tensorMemory.asByteBuffer().asReadOnlyBuffer());
  }

  /**
   * Maps tensor memory as a buffer of integers.
   *
   * @param nativeTensor native reference to the tensor
   * @return an int buffer
   */
  public static IntDataBuffer toInts(TF_Tensor nativeTensor) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToInts(tensorMemory);
    }
    return DataBuffers.of(tensorMemory.asByteBuffer().asIntBuffer());
  }

  /**
   * Maps tensor memory as a buffer of longs.
   *
   * @param nativeTensor native reference to the tensor
   * @return a long buffer
   */
  public static LongDataBuffer toLongs(TF_Tensor nativeTensor) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToLongs(tensorMemory);
    }
    return DataBuffers.of(tensorMemory.asByteBuffer().asLongBuffer());
  }

  /**
   * Maps tensor memory as a buffer of floats.
   *
   * @param nativeTensor native reference to the tensor
   * @return a float buffer
   */
  public static FloatDataBuffer toFloats(TF_Tensor nativeTensor) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToFloats(tensorMemory);
    }
    return DataBuffers.of(tensorMemory.asByteBuffer().asFloatBuffer());
  }

  /**
   * Maps tensor memory as a buffer of doubles.
   *
   * @param nativeTensor native reference to the tensor
   * @return a double buffer
   */
  public static DoubleDataBuffer toDoubles(TF_Tensor nativeTensor) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToDoubles(tensorMemory);
    }
    return DataBuffers.of(tensorMemory.asByteBuffer().asDoubleBuffer());
  }

  /**
   * Maps tensor memory as a buffer of shorts.
   *
   * @param nativeTensor native reference to the tensor
   * @return a short buffer
   */
  public static ShortDataBuffer toShorts(TF_Tensor nativeTensor) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToShorts(tensorMemory);
    }
    return DataBuffers.of(tensorMemory.asByteBuffer().asShortBuffer());
  }

  /**
   * Maps tensor memory as a buffer of booleans.
   *
   * @param nativeTensor native reference to the tensor
   * @return a boolean buffer
   */
  public static BooleanDataBuffer toBooleans(TF_Tensor nativeTensor) {
    Pointer tensorMemory = tensorMemory(nativeTensor);
    if (TensorRawDataBufferFactory.canBeUsed()) {
      return TensorRawDataBufferFactory.mapTensorToBooleans(tensorMemory);
    }
    // There is no boolean buffers in Java NIO, so apply a layout that converts booleans
    // from/to bytes when raw memory mapping is not available.
    return DataLayouts.BOOL.applyTo(DataBuffers.of(tensorMemory.asByteBuffer()));
  }

  /**
   * Maps tensor memory as a buffer of byte sequences, often used to store string values.
   *
   * @param nativeTensor native reference to the tensor
   * @return a string buffer
   */
  public static ByteSequenceTensorBuffer toStrings(TF_Tensor nativeTensor, long numElements) {
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
    LongDataBuffer offsets = DataBuffers.of(offsetBuffer.slice());

    dataBuffer.position((int)numElements * Long.BYTES);
    ByteDataBuffer data = DataBuffers.of(dataBuffer.slice());

    return new ByteSequenceTensorBuffer(offsets, data);
  }

  private static Pointer tensorMemory(TF_Tensor nativeTensor) {
    return TF_TensorData(nativeTensor).capacity(TF_TensorByteSize(nativeTensor));
  }
}
