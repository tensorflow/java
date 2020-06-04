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

package org.tensorflow.ndarray.impl.buffer.adapter;

import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.buffer.layout.LongDataLayout;

class LongDataBufferAdapter<S extends DataBuffer<?>> extends AbstractDataBufferAdapter<S, Long, LongDataBuffer>
    implements LongDataBuffer {

  @Override
  public long getLong(long index) {
    Validator.getArgs(this, index);
    return layout.readLong(buffer(), index * layout.scale());
  }

  @Override
  public LongDataBuffer setLong(long value, long index) {
    Validator.setArgs(this, index);
    layout.writeLong(buffer(), value, index * layout.scale());
    return this;
  }

  @Override
  public LongDataBuffer read(long[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = layout.readLong(buffer(), i * layout.scale());
    }
    return this;
  }

  @Override
  public LongDataBuffer write(long[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      layout.writeLong(buffer(), src[j], i * layout.scale());
    }
    return this;
  }

  @Override
  public LongDataBuffer copyTo(DataBuffer<Long> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof LongDataBuffer) {
      LongDataBuffer longDst = (LongDataBuffer)dst;
      for (long idx = 0L; idx < size; ++idx) {
        longDst.setLong(getLong(idx), idx);
      }
      return this;
    }
    return slowCopyTo(dst, size);
  }

  @Override
  @SuppressWarnings("unchecked")
  public LongDataBuffer offset(long index) {
    return new LongDataBufferAdapter<>((S)buffer().offset(index * layout.scale()), layout);
  }

  @Override
  @SuppressWarnings("unchecked")
  public LongDataBuffer narrow(long size) {
    return new LongDataBufferAdapter<>((S)buffer().narrow(size * layout.scale()), layout);
  }

  @Override
  @SuppressWarnings("unchecked")
  public LongDataBuffer slice(long index, long size) {
    return new LongDataBufferAdapter<>((S)buffer().slice(index * layout.scale(), size * layout.scale()), layout);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof LongDataBuffer)) {
      return super.equals(obj);
    }
    LongDataBuffer other = (LongDataBuffer)obj;
    if (other.size() != size()) {
      return false;
    }
    for (long idx = 0L; idx < size(); ++idx) {
      if (other.getLong(idx) != getLong(idx)) {
        return false;
      }
    }
    return true;
  }

  LongDataBufferAdapter(S buffer, LongDataLayout<S> layout) {
    super(buffer, layout);
    this.layout = layout;
  }

  private LongDataLayout<S> layout;
}
