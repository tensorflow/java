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
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.buffer.layout.ShortDataLayout;

class ShortDataBufferAdapter<S extends DataBuffer<?>> extends AbstractDataBufferAdapter<S, Short, ShortDataBuffer>
    implements ShortDataBuffer {

  @Override
  public short getShort(long index) {
    Validator.getArgs(this, index);
    return layout.readShort(buffer(), index * layout.scale());
  }

  @Override
  public ShortDataBuffer setShort(short value, long index) {
    Validator.setArgs(this, index);
    layout.writeShort(buffer(), value, index * layout.scale());
    return this;
  }

  @Override
  public ShortDataBuffer read(short[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = layout.readShort(buffer(), i * layout.scale());
    }
    return this;
  }

  @Override
  public ShortDataBuffer write(short[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      layout.writeShort(buffer(), src[j], i * layout.scale());
    }
    return this;
  }

  @Override
  public ShortDataBuffer copyTo(DataBuffer<Short> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof ShortDataBuffer) {
      ShortDataBuffer shortDst = (ShortDataBuffer)dst;
      for (long idx = 0L; idx < size; ++idx) {
        shortDst.setShort(getShort(idx), idx);
      }
      return this;
    }
    return slowCopyTo(dst, size);
  }

  @Override
  @SuppressWarnings("unchecked")
  public ShortDataBuffer offset(long index) {
    return new ShortDataBufferAdapter<>((S)buffer().offset(index * layout.scale()), layout);
  }

  @Override
  @SuppressWarnings("unchecked")
  public ShortDataBuffer narrow(long size) {
    return new ShortDataBufferAdapter<>((S)buffer().narrow(size * layout.scale()), layout);
  }

  @Override
  @SuppressWarnings("unchecked")
  public ShortDataBuffer slice(long index, long size) {
    return new ShortDataBufferAdapter<>((S)buffer().slice(index * layout.scale(), size * layout.scale()), layout);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ShortDataBuffer)) {
      return super.equals(obj);
    }
    ShortDataBuffer other = (ShortDataBuffer)obj;
    if (other.size() != size()) {
      return false;
    }
    for (long idx = 0L; idx < size(); ++idx) {
      if (other.getShort(idx) != getShort(idx)) {
        return false;
      }
    }
    return true;
  }

  ShortDataBufferAdapter(S buffer, ShortDataLayout<S> layout) {
    super(buffer, layout);
    this.layout = layout;
  }

  private ShortDataLayout<S> layout;
}
