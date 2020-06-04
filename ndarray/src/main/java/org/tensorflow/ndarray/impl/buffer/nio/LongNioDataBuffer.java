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

import java.nio.LongBuffer;
import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataStorageVisitor;
import org.tensorflow.ndarray.buffer.LongDataBuffer;

/**
 * A buffer of bytes using a JDK {@link LongBuffer} for storage.
 */
final class LongNioDataBuffer extends AbstractNioDataBuffer<Long>
    implements LongDataBuffer {

  @Override
  public long getLong(long index) {
    return buf.get((int)index);
  }

  @Override
  public LongDataBuffer setLong(long value, long index) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public LongDataBuffer read(long[] dst, int offset, int length) {
    buf.duplicate().get(dst, offset, length);
    return this;
  }

  @Override
  public LongDataBuffer write(long[] src, int offset, int length) {
    buf.duplicate().put(src, offset, length);
    return this;
  }

  @Override
  public LongDataBuffer copyTo(DataBuffer<Long> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<LongDataBuffer>() {

      @Override
      public LongDataBuffer visit(LongBuffer buffer) {
        buffer.duplicate().put((LongBuffer)buf.duplicate().limit((int)size));
        return LongNioDataBuffer.this;
      }

      @Override
      public LongDataBuffer fallback() {
        if (dst instanceof LongDataBuffer) {
          LongDataBuffer longDst = (LongDataBuffer)dst;
          for (long idx = 0L; idx < size; ++idx) {
            longDst.setLong(getLong(idx), idx);
          }
          return LongNioDataBuffer.this;
        }
        return slowCopyTo(dst, size);
      }
    });
  }

  @Override
  public LongDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new LongNioDataBuffer(((LongBuffer)buf.duplicate().position((int)index)).slice());
  }

  @Override
  public LongDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new LongNioDataBuffer(((LongBuffer)buf.duplicate().limit((int)size)).slice());
  }

  @Override
  public LongDataBuffer slice(long index, long size) {
    Validator.sliceArgs(this, index, size);
    LongBuffer sliceBuf = buf.duplicate();
    sliceBuf.position((int)index);
    sliceBuf.limit((int)index + (int)size);
    return new LongNioDataBuffer(sliceBuf.slice());
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
    if (!(obj instanceof LongDataBuffer)) {
      return super.equals(obj);
    }
    LongDataBuffer other = (LongDataBuffer)obj;
    if (size() != other.size()) {
      return false;
    }
    return other.accept(new DataStorageVisitor<Boolean>() {

      @Override
      public Boolean visit(LongBuffer buffer) {
        return buf.equals(buffer);
      }

      @Override
      public Boolean fallback() {
        for (int idx = 0; idx < size(); ++idx) {
          if (other.getLong(idx) != getLong(idx)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  @Override
  LongBuffer buf() {
    return buf;
  }

  LongNioDataBuffer(LongBuffer buf) {
    this.buf = buf;
  }

  private LongBuffer buf;
}
