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

package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.DataBufferWindow;
import org.tensorflow.tools.buffer.impl.AbstractDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

@SuppressWarnings("unchecked")
abstract class AbstractRawDataBuffer<T, B extends DataBuffer<T>> extends AbstractDataBuffer<T> {

  public long size() {
    return memory.size();
  }

  @Override
  public boolean isReadOnly() {
    return readOnly;
  }

  public B read(Object dst, int dstLength) {
    Validator.readArgs(this, dstLength, 0, dstLength);
    memory.copyTo(UnsafeMemoryHandle.fromArray(dst, dstLength), dstLength);
    return (B)this;
  }

  public B read(Object dst, int dstLength, int offset, int length) {
    Validator.readArgs(this, dstLength, offset, length);
    memory.copyTo(UnsafeMemoryHandle.fromArray(dst, dstLength).offset(offset), length);
    return (B)this;
  }

  public B write(Object src, int srcLength) {
    Validator.writeArgs(this, srcLength, 0, srcLength);
    UnsafeMemoryHandle.fromArray(src, srcLength).copyTo(memory, srcLength);
    return (B)this;
  }

  public B write(Object src, int srcLength, int offset, int length) {
    Validator.writeArgs(this, srcLength, offset, length);
    UnsafeMemoryHandle.fromArray(src, srcLength).offset(offset).copyTo(memory, length);
    return (B)this;
  }

  @Override
  public B copyTo(DataBuffer<T> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof AbstractRawDataBuffer) {
      AbstractRawDataBuffer<?, ?> unsafeDst = (AbstractRawDataBuffer<?, ?>)dst;
      memory.copyTo(unsafeDst.memory, size);
    } else {
      super.copyTo(dst, size);
    }
    return (B)this;
  }

  @Override
  public B slice(long index, long size) {
    Validator.sliceArgs(this, index, size);
    return instantiate(memory.slice(index, size));
  }

  @Override
  public DataBufferWindow<B> window(long size) {
    B windowBuffer = instantiate(memory.slice(0, size));
    return new RawDataBufferWindow<>((AbstractRawDataBuffer<?, B>)windowBuffer, size());
  }

  protected final UnsafeMemoryHandle memory;
  protected final boolean readOnly;

  protected abstract B instantiate(UnsafeMemoryHandle region);

  AbstractRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    this.memory = memory;
    this.readOnly = readOnly;
  }
}
