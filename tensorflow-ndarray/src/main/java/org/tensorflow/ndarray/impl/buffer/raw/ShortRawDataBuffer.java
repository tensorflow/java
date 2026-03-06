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

package org.tensorflow.ndarray.impl.buffer.raw;

import java.nio.ShortBuffer;
import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataStorageVisitor;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;

final class ShortRawDataBuffer extends AbstractRawDataBuffer<Short, ShortDataBuffer>
    implements ShortDataBuffer {

  @Override
  public short getShort(long index) {
    Validator.getArgs(this, index);
    return memory.getShort(index);
  }

  @Override
  public ShortDataBuffer setShort(short value, long index) {
    Validator.setArgs(this, index);
    memory.setShort(value, index);
    return this;
  }

  @Override
  public ShortDataBuffer read(short[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public ShortDataBuffer read(short[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public ShortDataBuffer write(short[] src) {
    return write(src, src.length);
  }

  @Override
  public ShortDataBuffer write(short[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  public ShortDataBuffer copyTo(DataBuffer<Short> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<ShortDataBuffer>() {

      @Override
      public ShortDataBuffer visit(ShortBuffer buffer) {
        if (buffer.hasArray()) {
          memory.copyTo(UnsafeMemoryHandle.fromArray(buffer.array(), buffer.position(), buffer.limit()), size);
        } else if (memory.isArray()) {
          buffer.put(memory.toArrayShortBuffer());
        } else {
          slowCopyTo(dst, size);
        }
        return ShortRawDataBuffer.this;
      }

      @Override
      public ShortDataBuffer visit(long address, long length, long scale) {
        memory.copyTo(UnsafeMemoryHandle.fromAddress(address, length, scale), size);
        return ShortRawDataBuffer.this;
      }

      @Override
      public ShortDataBuffer fallback() {
        if (dst instanceof ShortDataBuffer) {
          ShortDataBuffer shortDst = (ShortDataBuffer)dst;
          for (long idx = 0L; idx < size; ++idx) {
            shortDst.setShort(getShort(idx), idx);
          }
          return ShortRawDataBuffer.this;
        }
        return slowCopyTo(dst, size);
      }
    });
  }

  @Override
  public <R> R accept(DataStorageVisitor<R> visitor) {
    if (memory.isArray()) {
      return visitor.visit(memory.toArrayShortBuffer());
    }
    return visitor.visit(memory.byteOffset, memory.byteSize, memory.scale);
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
        if (memory.isArray()) {
          return buffer.equals(memory.toArrayShortBuffer());
        }
        return fallback();
      }

      @Override
      public Boolean fallback() {
        for (long idx = 0L; idx < size(); ++idx) {
          if (other.getShort(idx) != getShort(idx)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  @Override
  protected ShortDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new ShortRawDataBuffer(memory, readOnly);
  }

  ShortRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
