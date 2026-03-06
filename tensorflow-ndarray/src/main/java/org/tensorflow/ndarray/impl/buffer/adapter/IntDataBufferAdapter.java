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
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.buffer.layout.IntDataLayout;

class IntDataBufferAdapter<S extends DataBuffer<?>> extends AbstractDataBufferAdapter<S, Integer, IntDataBuffer>
    implements IntDataBuffer {

  @Override
  public int getInt(long index) {
    Validator.getArgs(this, index);
    return layout.readInt(buffer(), index * layout.scale());
  }

  @Override
  public IntDataBuffer setInt(int value, long index) {
    Validator.setArgs(this, index);
    layout.writeInt(buffer(), value, index * layout.scale());
    return this;
  }

  @Override
  public IntDataBuffer read(int[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = layout.readInt(buffer(), i * layout.scale());
    }
    return this;
  }

  @Override
  public IntDataBuffer write(int[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      layout.writeInt(buffer(), src[j], i * layout.scale());
    }
    return this;
  }

  @Override
  public IntDataBuffer copyTo(DataBuffer<Integer> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof IntDataBuffer) {
      IntDataBuffer intDst = (IntDataBuffer)dst;
      for (long idx = 0L; idx < size; ++idx) {
        intDst.setInt(getInt(idx), idx);
      }
      return this;
    }
    return slowCopyTo(dst, size);
  }

  @Override
  @SuppressWarnings("unchecked")
  public IntDataBuffer offset(long index) {
    return new IntDataBufferAdapter<>((S)buffer().offset(index * layout.scale()), layout);
  }

  @Override
  @SuppressWarnings("unchecked")
  public IntDataBuffer narrow(long size) {
    return new IntDataBufferAdapter<>((S)buffer().narrow(size * layout.scale()), layout);
  }

  @Override
  @SuppressWarnings("unchecked")
  public IntDataBuffer slice(long index, long size) {
    return new IntDataBufferAdapter<>((S)buffer().slice(index * layout.scale(), size * layout.scale()), layout);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof IntDataBuffer)) {
      return super.equals(obj);
    }
    IntDataBuffer other = (IntDataBuffer)obj;
    if (other.size() != size()) {
      return false;
    }
    for (long idx = 0L; idx < size(); ++idx) {
      if (other.getInt(idx) != getInt(idx)) {
        return false;
      }
    }
    return true;
  }

  IntDataBufferAdapter(S buffer, IntDataLayout<S> layout) {
    super(buffer, layout);
    this.layout = layout;
  }

  private IntDataLayout<S> layout;
}
