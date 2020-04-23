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

package org.tensorflow.tools.buffer.impl.raw;

import java.nio.LongBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.DataStorageVisitor;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

final class LongRawDataBuffer extends AbstractRawDataBuffer<Long, LongDataBuffer>
    implements LongDataBuffer {

  @Override
  public long getLong(long index) {
    Validator.getArgs(this, index);
    return memory.getLong(index);
  }

  @Override
  public LongDataBuffer setLong(long value, long index) {
    Validator.setArgs(this, index);
    memory.setLong(value, index);
    return this;
  }

  @Override
  public LongDataBuffer read(long[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public LongDataBuffer read(long[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public LongDataBuffer write(long[] src) {
    return write(src, src.length);
  }

  @Override
  public LongDataBuffer write(long[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  public LongDataBuffer copyTo(DataBuffer<Long> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<LongDataBuffer>() {

      @Override
      public LongDataBuffer visit(LongBuffer buffer) {
        if (buffer.hasArray()) {
          memory.copyTo(UnsafeMemoryHandle.fromArray(buffer.array(), buffer.position(), buffer.limit()), size);
        } else if (memory.isArray()) {
          buffer.put(memory.toArrayLongBuffer());
        } else {
          slowCopyTo(dst, size);
        }
        return LongRawDataBuffer.this;
      }

      @Override
      public LongDataBuffer visit(long address, long length, long scale) {
        memory.copyTo(UnsafeMemoryHandle.fromAddress(address, length, scale), size);
        return LongRawDataBuffer.this;
      }

      @Override
      public LongDataBuffer fallback() {
        if (dst instanceof LongDataBuffer) {
          LongDataBuffer longDst = (LongDataBuffer)dst;
          for (long idx = 0L; idx < size; ++idx) {
            longDst.setLong(getLong(idx), idx);
          }
          return LongRawDataBuffer.this;
        }
        return slowCopyTo(dst, size);
      }
    });
  }

  @Override
  public <R> R accept(DataStorageVisitor<R> visitor) {
    if (memory.isArray()) {
      return visitor.visit(memory.toArrayLongBuffer());
    }
    return visitor.visit(memory.byteOffset, memory.byteSize, memory.scale);
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
        if (memory.isArray()) {
          return buffer.equals(memory.toArrayLongBuffer());
        }
        return fallback();
      }

      @Override
      public Boolean fallback() {
        for (long idx = 0L; idx < size(); ++idx) {
          if (other.getLong(idx) != getLong(idx)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  @Override
  protected LongDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new LongRawDataBuffer(memory, readOnly);
  }

  LongRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
