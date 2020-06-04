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

import java.nio.ShortBuffer;
import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataStorageVisitor;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;

/**
 * A buffer of bytes using a JDK {@link ShortBuffer} for storage.
 */
final class ShortNioDataBuffer extends AbstractNioDataBuffer<Short>
    implements ShortDataBuffer {

  @Override
  public short getShort(long index) {
    return buf.get((int)index);
  }

  @Override
  public ShortDataBuffer setShort(short value, long index) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public ShortDataBuffer read(short[] dst, int offset, int length) {
    buf.duplicate().get(dst, offset, length);
    return this;
  }

  @Override
  public ShortDataBuffer write(short[] src, int offset, int length) {
    buf.duplicate().put(src, offset, length);
    return this;
  }

  @Override
  public ShortDataBuffer copyTo(DataBuffer<Short> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<ShortDataBuffer>() {

      @Override
      public ShortDataBuffer visit(ShortBuffer buffer) {
        buffer.duplicate().put((ShortBuffer)buf.duplicate().limit((int)size));
        return ShortNioDataBuffer.this;
      }

      @Override
      public ShortDataBuffer fallback() {
        if (dst instanceof ShortDataBuffer) {
          ShortDataBuffer shortDst = (ShortDataBuffer)dst;
          for (long idx = 0L; idx < size; ++idx) {
            shortDst.setShort(getShort(idx), idx);
          }
          return ShortNioDataBuffer.this;
        }
        return slowCopyTo(dst, size);
      }
    });
  }

  @Override
  public ShortDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new ShortNioDataBuffer(((ShortBuffer)buf.duplicate().position((int)index)).slice());
  }

  @Override
  public ShortDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new ShortNioDataBuffer(((ShortBuffer)buf.duplicate().limit((int)size)).slice());
  }

  @Override
  public ShortDataBuffer slice(long index, long size) {
    Validator.sliceArgs(this, index, size);
    ShortBuffer sliceBuf = buf.duplicate();
    sliceBuf.position((int)index);
    sliceBuf.limit((int)index + (int)size);
    return new ShortNioDataBuffer(sliceBuf.slice());
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
    if (!(obj instanceof ShortDataBuffer)) {
      return super.equals(obj);
    }
    ShortDataBuffer other = (ShortDataBuffer)obj;
    if (size() != other.size()) {
      return false;
    }
    return other.accept(new DataStorageVisitor<Boolean>() {

      @Override
      public Boolean visit(ShortBuffer buffer) {
        return buf.equals(buffer);
      }

      @Override
      public Boolean fallback() {
        for (int idx = 0; idx < size(); ++idx) {
          if (other.getShort(idx) != getShort(idx)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  @Override
  ShortBuffer buf() {
    return buf;
  }

  ShortNioDataBuffer(ShortBuffer buf) {
    this.buf = buf;
  }

  private ShortBuffer buf;
}
