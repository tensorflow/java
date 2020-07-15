/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================
 */

package org.tensorflow.ndarray.impl.buffer.nio;

import java.nio.DoubleBuffer;
import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataStorageVisitor;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;

/**
 * A buffer of bytes using a JDK {@link DoubleBuffer} for storage.
 */
final class DoubleNioDataBuffer extends AbstractNioDataBuffer<Double>
    implements DoubleDataBuffer {

  @Override
  public double getDouble(long index) {
    return buf.get((int)index);
  }

  @Override
  public DoubleDataBuffer setDouble(double value, long index) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public DoubleDataBuffer read(double[] dst, int offset, int length) {
    buf.duplicate().get(dst, offset, length);
    return this;
  }

  @Override
  public DoubleDataBuffer write(double[] src, int offset, int length) {
    buf.duplicate().put(src, offset, length);
    return this;
  }

  @Override
  public DoubleDataBuffer copyTo(DataBuffer<Double> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<DoubleDataBuffer>() {

      @Override
      public DoubleDataBuffer visit(DoubleBuffer buffer) {
        buffer.duplicate().put((DoubleBuffer)buf.duplicate().limit((int)size));
        return DoubleNioDataBuffer.this;
      }

      @Override
      public DoubleDataBuffer fallback() {
        if (dst instanceof DoubleDataBuffer) {
          DoubleDataBuffer doubleDst = (DoubleDataBuffer)dst;
          for (long idx = 0L; idx < size; ++idx) {
            doubleDst.setDouble(getDouble(idx), idx);
          }
          return DoubleNioDataBuffer.this;
        }
        return slowCopyTo(dst, size);
      }
    });
  }

  @Override
  public DoubleDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new DoubleNioDataBuffer(((DoubleBuffer)buf.duplicate().position((int)index)).slice());
  }

  @Override
  public DoubleDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new DoubleNioDataBuffer(((DoubleBuffer)buf.duplicate().limit((int)size)).slice());
  }

  @Override
  public DoubleDataBuffer slice(long index, long size) {
    Validator.sliceArgs(this, index, size);
    DoubleBuffer sliceBuf = buf.duplicate();
    sliceBuf.position((int)index);
    sliceBuf.limit((int)index + (int)size);
    return new DoubleNioDataBuffer(sliceBuf.slice());
  }

  @Override
  public <R> R accept(DataStorageVisitor<R> visitor) {
    return visitor.visit(buf);
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
    if (size() != other.size()) {
      return false;
    }
    return other.accept(new DataStorageVisitor<Boolean>() {

      @Override
      public Boolean visit(DoubleBuffer buffer) {
        return buf.equals(buffer);
      }

      @Override
      public Boolean fallback() {
        for (int idx = 0; idx < size(); ++idx) {
          if (other.getDouble(idx) != getDouble(idx)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  @Override
  DoubleBuffer buf() {
    return buf;
  }

  DoubleNioDataBuffer(DoubleBuffer buf) {
    this.buf = buf;
  }
  
  private DoubleBuffer buf;
}
