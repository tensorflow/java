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
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.buffer.layout.DoubleDataLayout;

class DoubleDataBufferAdapter<S extends DataBuffer<?>> extends AbstractDataBufferAdapter<S, Double, DoubleDataBuffer>
    implements DoubleDataBuffer {

  @Override
  public double getDouble(long index) {
    Validator.getArgs(this, index);
    return layout.readDouble(buffer(), index * layout.scale());
  }

  @Override
  public DoubleDataBuffer setDouble(double value, long index) {
    Validator.setArgs(this, index);
    layout.writeDouble(buffer(), value, index * layout.scale());
    return this;
  }

  @Override
  public DoubleDataBuffer read(double[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = layout.readDouble(buffer(), i * layout.scale());
    }
    return this;
  }

  @Override
  public DoubleDataBuffer write(double[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      layout.writeDouble(buffer(), src[j], i * layout.scale());
    }
    return this;
  }

  @Override
  public DoubleDataBuffer copyTo(DataBuffer<Double> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof DoubleDataBuffer) {
      DoubleDataBuffer doubleDst = (DoubleDataBuffer)dst;
      for (long idx = 0L; idx < size; ++idx) {
        doubleDst.setDouble(getDouble(idx), idx);
      }
      return this;
    }
    return slowCopyTo(dst, size);
  }

  @Override
  @SuppressWarnings("unchecked")
  public DoubleDataBuffer offset(long index) {
    return new DoubleDataBufferAdapter<>((S)buffer().offset(index * layout.scale()), layout);
  }

  @Override
  @SuppressWarnings("unchecked")
  public DoubleDataBuffer narrow(long size) {
    return new DoubleDataBufferAdapter<>((S)buffer().narrow(size * layout.scale()), layout);
  }

  @Override
  @SuppressWarnings("unchecked")
  public DoubleDataBuffer slice(long index, long size) {
    return new DoubleDataBufferAdapter<>((S)buffer().slice(index * layout.scale(), size * layout.scale()), layout);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof DoubleDataBuffer)) {
      return super.equals(obj);
    }
    DoubleDataBuffer other = (DoubleDataBuffer)obj;
    if (other.size() != size()) {
      return false;
    }
    for (long idx = 0L; idx < size(); ++idx) {
      if (other.getDouble(idx) != getDouble(idx)) {
        return false;
      }
    }
    return true;
  }

  DoubleDataBufferAdapter(S buffer, DoubleDataLayout<S> layout) {
    super(buffer, layout);
    this.layout = layout;
  }

  private DoubleDataLayout<S> layout;
}
