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

import java.nio.IntBuffer;
import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataStorageVisitor;
import org.tensorflow.ndarray.buffer.IntDataBuffer;

final class IntRawDataBuffer extends AbstractRawDataBuffer<Integer, IntDataBuffer>
    implements IntDataBuffer {

  @Override
  public int getInt(long index) {
    Validator.getArgs(this, index);
    return memory.getInt(index);
  }

  @Override
  public IntDataBuffer setInt(int value, long index) {
    Validator.setArgs(this, index);
    memory.setInt(value, index);
    return this;
  }

  @Override
  public IntDataBuffer read(int[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public IntDataBuffer read(int[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public IntDataBuffer write(int[] src) {
    return write(src, src.length);
  }

  @Override
  public IntDataBuffer write(int[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  public IntDataBuffer copyTo(DataBuffer<Integer> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<IntDataBuffer>() {

      @Override
      public IntDataBuffer visit(IntBuffer buffer) {
        if (buffer.hasArray()) {
          memory.copyTo(UnsafeMemoryHandle.fromArray(buffer.array(), buffer.position(), buffer.limit()), size);
        } else if (memory.isArray()) {
          buffer.put(memory.toArrayIntBuffer());
        } else {
          slowCopyTo(dst, size);
        }
        return IntRawDataBuffer.this;
      }

      @Override
      public IntDataBuffer visit(long address, long length, long scale) {
        memory.copyTo(UnsafeMemoryHandle.fromAddress(address, length, scale), size);
        return IntRawDataBuffer.this;
      }

      @Override
      public IntDataBuffer fallback() {
        if (dst instanceof IntDataBuffer) {
          IntDataBuffer intDst = (IntDataBuffer)dst;
          for (long idx = 0L; idx < size; ++idx) {
            intDst.setInt(getInt(idx), idx);
          }
          return IntRawDataBuffer.this;
        }
        return slowCopyTo(dst, size);
      }
    });
  }

  @Override
  public <R> R accept(DataStorageVisitor<R> visitor) {
    if (memory.isArray()) {
      return visitor.visit(memory.toArrayIntBuffer());
    }
    return visitor.visit(memory.byteOffset, memory.byteSize, memory.scale);
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
        if (memory.isArray()) {
          return buffer.equals(memory.toArrayIntBuffer());
        }
        return fallback();
      }

      @Override
      public Boolean fallback() {
        for (long idx = 0L; idx < size(); ++idx) {
          if (other.getInt(idx) != getInt(idx)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  @Override
  protected IntDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new IntRawDataBuffer(memory, readOnly);
  }

  IntRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
