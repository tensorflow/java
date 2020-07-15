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

import java.nio.IntBuffer;
import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataStorageVisitor;
import org.tensorflow.ndarray.buffer.IntDataBuffer;

/**
 * A buffer of bytes using a JDK {@link IntBuffer} for storage.
 */
final class IntNioDataBuffer extends AbstractNioDataBuffer<Integer>
    implements IntDataBuffer {

  @Override
  public int getInt(long index) {
    return buf.get((int)index);
  }

  @Override
  public IntDataBuffer setInt(int value, long index) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public IntDataBuffer read(int[] dst, int offset, int length) {
    buf.duplicate().get(dst, offset, length);
    return this;
  }

  @Override
  public IntDataBuffer write(int[] src, int offset, int length) {
    buf.duplicate().put(src, offset, length);
    return this;
  }

  @Override
  public IntDataBuffer copyTo(DataBuffer<Integer> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<IntDataBuffer>() {

      @Override
      public IntDataBuffer visit(IntBuffer buffer) {
        buffer.duplicate().put((IntBuffer)buf.duplicate().limit((int)size));
        return IntNioDataBuffer.this;
      }

      @Override
      public IntDataBuffer fallback() {
        if (dst instanceof IntDataBuffer) {
          IntDataBuffer intDst = (IntDataBuffer)dst;
          for (long idx = 0L; idx < size; ++idx) {
            intDst.setInt(getInt(idx), idx);
          }
          return IntNioDataBuffer.this;
        }
        return slowCopyTo(dst, size);
      }
    });
  }

  @Override
  public IntDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new IntNioDataBuffer(((IntBuffer)buf.duplicate().position((int)index)).slice());
  }

  @Override
  public IntDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new IntNioDataBuffer(((IntBuffer)buf.duplicate().limit((int)size)).slice());
  }

  @Override
  public IntDataBuffer slice(long index, long size) {
    Validator.sliceArgs(this, index, size);
    IntBuffer sliceBuf = buf.duplicate();
    sliceBuf.position((int)index);
    sliceBuf.limit((int)index + (int)size);
    return new IntNioDataBuffer(sliceBuf.slice());
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
    if (!(obj instanceof IntDataBuffer)) {
      return super.equals(obj);
    }
    IntDataBuffer other = (IntDataBuffer)obj;
    if (size() != other.size()) {
      return false;
    }
    return other.accept(new DataStorageVisitor<Boolean>() {

      @Override
      public Boolean visit(IntBuffer buffer) {
        return buf.equals(buffer);
      }

      @Override
      public Boolean fallback() {
        for (int idx = 0; idx < size(); ++idx) {
          if (other.getInt(idx) != getInt(idx)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  @Override
  IntBuffer buf() {
    return buf;
  }

  IntNioDataBuffer(IntBuffer buf) {
    this.buf = buf;
  }

  private IntBuffer buf;
}
