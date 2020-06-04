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

import java.nio.DoubleBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.DataStorageVisitor;
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

final class DoubleRawDataBuffer extends AbstractRawDataBuffer<Double, DoubleDataBuffer>
    implements DoubleDataBuffer {

  @Override
  public double getDouble(long index) {
    Validator.getArgs(this, index);
    return memory.getDouble(index);
  }

  @Override
  public DoubleDataBuffer setDouble(double value, long index) {
    Validator.setArgs(this, index);
    memory.setDouble(value, index);
    return this;
  }

  @Override
  public DoubleDataBuffer read(double[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public DoubleDataBuffer read(double[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public DoubleDataBuffer write(double[] src) {
    return write(src, src.length);
  }

  @Override
  public DoubleDataBuffer write(double[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  public DoubleDataBuffer copyTo(DataBuffer<Double> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<DoubleDataBuffer>() {

      @Override
      public DoubleDataBuffer visit(DoubleBuffer buffer) {
        if (buffer.hasArray()) {
          memory.copyTo(UnsafeMemoryHandle.fromArray(buffer.array(), buffer.position(), buffer.limit()), size);
        } else if (memory.isArray()) {
          buffer.put(memory.toArrayDoubleBuffer());
        } else {
          slowCopyTo(dst, size);
        }
        return DoubleRawDataBuffer.this;
      }

      @Override
      public DoubleDataBuffer visit(long address, long length, long scale) {
        memory.copyTo(UnsafeMemoryHandle.fromAddress(address, length, scale), size);
        return DoubleRawDataBuffer.this;
      }

      @Override
      public DoubleDataBuffer fallback() {
        if (dst instanceof DoubleDataBuffer) {
          DoubleDataBuffer doubleDst = (DoubleDataBuffer)dst;
          for (long idx = 0L; idx < size; ++idx) {
            doubleDst.setDouble(getDouble(idx), idx);
          }
          return DoubleRawDataBuffer.this;
        }
        return slowCopyTo(dst, size);
      }
    });
  }

  @Override
  public <R> R accept(DataStorageVisitor<R> visitor) {
    if (memory.isArray()) {
      return visitor.visit(memory.toArrayDoubleBuffer());
    }
    return visitor.visit(memory.byteOffset, memory.byteSize, memory.scale);
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
        if (memory.isArray()) {
          return buffer.equals(memory.toArrayDoubleBuffer());
        }
        return fallback();
      }

      @Override
      public Boolean fallback() {
        for (long idx = 0L; idx < size(); ++idx) {
          if (other.getDouble(idx) != getDouble(idx)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  @Override
  protected DoubleDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new DoubleRawDataBuffer(memory, readOnly);
  }

  DoubleRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
