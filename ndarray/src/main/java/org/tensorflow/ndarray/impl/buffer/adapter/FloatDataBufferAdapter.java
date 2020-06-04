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
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.layout.FloatDataLayout;

class FloatDataBufferAdapter<S extends DataBuffer<?>> extends AbstractDataBufferAdapter<S, Float, FloatDataBuffer>
    implements FloatDataBuffer {

  @Override
  public float getFloat(long index) {
    Validator.getArgs(this, index);
    return layout.readFloat(buffer(), index * layout.scale());
  }

  @Override
  public FloatDataBuffer setFloat(float value, long index) {
    Validator.setArgs(this, index);
    layout.writeFloat(buffer(), value, index * layout.scale());
    return this;
  }

  @Override
  public FloatDataBuffer read(float[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = layout.readFloat(buffer(), i * layout.scale());
    }
    return this;
  }

  @Override
  public FloatDataBuffer write(float[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      layout.writeFloat(buffer(), src[j], i * layout.scale());
    }
    return this;
  }

  @Override
  public FloatDataBuffer copyTo(DataBuffer<Float> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof FloatDataBuffer) {
      FloatDataBuffer floatDst = (FloatDataBuffer)dst;
      for (long idx = 0L; idx < size; ++idx) {
        floatDst.setFloat(getFloat(idx), idx);
      }
      return this;
    }
    return slowCopyTo(dst, size);
  }

  @Override
  @SuppressWarnings("unchecked")
  public FloatDataBuffer offset(long index) {
    return new FloatDataBufferAdapter<>((S)buffer().offset(index * layout.scale()), layout);
  }

  @Override
  @SuppressWarnings("unchecked")
  public FloatDataBuffer narrow(long size) {
    return new FloatDataBufferAdapter<>((S)buffer().narrow(size * layout.scale()), layout);
  }

  @Override
  @SuppressWarnings("unchecked")
  public FloatDataBuffer slice(long index, long size) {
    return new FloatDataBufferAdapter<>((S)buffer().slice(index * layout.scale(), size * layout.scale()), layout);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof FloatDataBuffer)) {
      return super.equals(obj);
    }
    FloatDataBuffer other = (FloatDataBuffer)obj;
    if (other.size() != size()) {
      return false;
    }
    for (long idx = 0L; idx < size(); ++idx) {
      if (other.getFloat(idx) != getFloat(idx)) {
        return false;
      }
    }
    return true;
  }

  FloatDataBufferAdapter(S buffer, FloatDataLayout<S> layout) {
    super(buffer, layout);
    this.layout = layout;
  }

  private FloatDataLayout<S> layout;
}
