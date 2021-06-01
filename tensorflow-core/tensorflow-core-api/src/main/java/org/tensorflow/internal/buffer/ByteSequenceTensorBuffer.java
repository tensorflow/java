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

import static org.tensorflow.internal.c_api.global.tensorflow.TF_TString_Assign;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TString_Copy;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TString_GetDataPointer;
import static org.tensorflow.internal.c_api.global.tensorflow.TF_TString_GetSize;

import java.nio.ReadOnlyBufferException;
import java.util.function.Function;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerScope;
import org.tensorflow.TensorFlow;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.internal.c_api.TF_TString;
import org.tensorflow.ndarray.impl.buffer.AbstractDataBuffer;
import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.NdArray;

/**
 * Buffer for storing string tensor data.
 *
 * <p>The values are stored as an array of {@link TF_TString}, internally wrapped with
 * {@code tensorflow::tstring}, which is essentially a portable version of {@code std::string}.
 *
 * <p>The data of the buffer must be initialized only once, by calling {@link #init(NdArray, Function)},
 * and the buffer must have been allocated with enough space (use {@link #computeSize(NdArray, Function)}
 * priory to know exactly how many bytes are required to store the data).
 *
 * <p>After its data has been initialized, the buffer is read-only as it is not possible to change
 * safely a value without reinitializing the whole data.
 */
public class ByteSequenceTensorBuffer extends AbstractDataBuffer<byte[]> {

  /**
   * Computes how many bytes are required to store the given data in a string buffer.
   *
   * @param byteSequenceProvider produces sequences of bytes
   * @return number of bytes required to store the data.
   */
  public static <T> long computeSize(ByteSequenceProvider<?> byteSequenceProvider) {
    // reserve space to store TF_TString objects
    return byteSequenceProvider.numSequences() * Loader.sizeof(TF_TString.class);
  }

  /**
   * Initialize the data of this buffer.
   *
   * <p>While it is not enforced programmatically, it is mandatory that this method is called only
   * once after the creation of the buffer. The buffer must have been allocated according to the
   * same set of data, calling {@link #computeSize(NdArray, Function)} priory to make sure there is
   * enough space to store it.
   *
   * @param byteSequenceProvider produces sequences of bytes to use as the tensor data
   */
  public <T> void init(ByteSequenceProvider<T> byteSequenceProvider) {
    InitDataWriter writer = new InitDataWriter();
    byteSequenceProvider.forEach(writer::writeNext);
  }

  @Override
  public long size() {
    return data.capacity() - data.position();
  }

  @Override
  public byte[] getObject(long index) {
    Validator.getArgs(this, index);
    TF_TString tstring = data.getPointer(index);
    BytePointer ptr = TF_TString_GetDataPointer(tstring).capacity(TF_TString_GetSize(tstring));
    return ptr.getStringBytes();
  }

  @Override
  public DataBuffer<byte[]> setObject(byte[] values, long index) {
    throw new ReadOnlyBufferException();
  }

  @Override
  public boolean isReadOnly() {
    return true;
  }

  @Override
  public DataBuffer<byte[]> copyTo(DataBuffer<byte[]> dst, long size) {
    if (size == size() && dst instanceof ByteSequenceTensorBuffer) {
      ByteSequenceTensorBuffer tensorDst = (ByteSequenceTensorBuffer) dst;
      for (int i = 0; i < size; i++) {
        TF_TString_Assign(tensorDst.data.getPointer(i), data.getPointer(i));
      }
    } else {
      slowCopyTo(dst, size);
    }
    return this;
  }

  @Override
  public DataBuffer<byte[]> slice(long index, long size) {
    return new ByteSequenceTensorBuffer(data.getPointer(index), size);
  }

  ByteSequenceTensorBuffer(Pointer tensorMemory, long numElements) {
    this.data = new TF_TString(tensorMemory).capacity(tensorMemory.position() + numElements);
  }

  private class InitDataWriter {
    long index = 0;

    void writeNext(byte[] bytes) {
      try (PointerScope scope = new PointerScope()) {
        TF_TString tstring = data.getPointer(index++);
        TF_TString_Copy(tstring, new BytePointer(bytes), bytes.length);
      }
    }
  }

  private final TF_TString data;

  static {
    try {
      // Ensure that TensorFlow native library and classes are ready to be used
      Class.forName("org.tensorflow.TensorFlow");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
