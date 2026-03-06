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

import java.util.Arrays;
import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataStorageVisitor;

final class BooleanRawDataBuffer extends AbstractRawDataBuffer<Boolean, BooleanDataBuffer>
    implements BooleanDataBuffer {

  @Override
  public boolean getBoolean(long index) {
    Validator.getArgs(this, index);
    return memory.getBoolean(index);
  }

  @Override
  public BooleanDataBuffer setBoolean(boolean value, long index) {
    Validator.setArgs(this, index);
    memory.setBoolean(value, index);
    return this;
  }

  @Override
  public BooleanDataBuffer read(boolean[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public BooleanDataBuffer read(boolean[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public BooleanDataBuffer write(boolean[] src) {
    return write(src, src.length);
  }

  @Override
  public BooleanDataBuffer write(boolean[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  public BooleanDataBuffer copyTo(DataBuffer<Boolean> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<BooleanDataBuffer>() {

      @Override
      public BooleanDataBuffer visit(boolean[] array, int offset, int length) {
        memory.copyTo(UnsafeMemoryHandle.fromArray(array, offset, length), size);
        return BooleanRawDataBuffer.this;
      }

      @Override
      public BooleanDataBuffer visit(long address, long length, long scale) {
        memory.copyTo(UnsafeMemoryHandle.fromAddress(address, length, scale), size);
        return BooleanRawDataBuffer.this;
      }

      @Override
      public BooleanDataBuffer fallback() {
        if (dst instanceof BooleanDataBuffer) {
          BooleanDataBuffer booleanDst = (BooleanDataBuffer)dst;
          for (long idx = 0L; idx < size; ++idx) {
            booleanDst.setBoolean(getBoolean(idx), idx);
          }
          return BooleanRawDataBuffer.this;
        }
        return slowCopyTo(dst, size);
      }
    });
  }

  @Override
  public <R> R accept(DataStorageVisitor<R> visitor) {
    if (memory.isArray()) {
      return visitor.visit((boolean[])memory.object, memory.arrayOffset(boolean[].class), (int)memory.size());
    }
    return visitor.visit(memory.byteOffset, memory.byteSize, memory.scale);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof BooleanDataBuffer)) {
      return super.equals(obj);
    }
    BooleanDataBuffer other = (BooleanDataBuffer)obj;
    if (size() != other.size()) {
      return false;
    }
    return other.accept(new DataStorageVisitor<Boolean>() {

      @Override
      public Boolean visit(boolean[] array, int offset, int length) {
        if (memory.isArray() && memory.arrayOffset(boolean[].class) == 0 && offset == 0) {
          boolean[] thisArray = memory.array();
          if (thisArray.length == array.length) {
            return Arrays.equals(thisArray, array);
          }
        }
        return fallback();
      }

      @Override
      public Boolean fallback() {
        for (long idx = 0L; idx < size(); ++idx) {
          if (other.getBoolean(idx) != getBoolean(idx)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  @Override
  protected BooleanDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new BooleanRawDataBuffer(memory, readOnly);
  }

  BooleanRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
