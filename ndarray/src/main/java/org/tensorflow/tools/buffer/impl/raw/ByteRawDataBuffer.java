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

import java.nio.ByteBuffer;
import org.tensorflow.tools.buffer.BooleanDataBuffer;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.DataStorageVisitor;
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.ShortDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

final class ByteRawDataBuffer extends AbstractRawDataBuffer<Byte, ByteDataBuffer>
    implements ByteDataBuffer {

  @Override
  public byte getByte(long index) {
    Validator.getArgs(this, index);
    return memory.getByte(index);
  }

  @Override
  public ByteDataBuffer setByte(byte value, long index) {
    Validator.setArgs(this, index);
    memory.setByte(value, index);
    return this;
  }

  @Override
  public ByteDataBuffer read(byte[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public ByteDataBuffer read(byte[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public ByteDataBuffer write(byte[] src) {
    return write(src, src.length);
  }

  @Override
  public ByteDataBuffer write(byte[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  public ByteDataBuffer copyTo(DataBuffer<Byte> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<ByteDataBuffer>() {

      @Override
      public ByteDataBuffer visit(ByteBuffer buffer) {
        if (buffer.hasArray()) {
          memory.copyTo(UnsafeMemoryHandle.fromArray(buffer.array(), buffer.position(), buffer.limit()), size);
        } else if (memory.isArray()) {
          buffer.put(memory.toArrayByteBuffer());
        } else {
          slowCopyTo(dst, size);
        }
        return ByteRawDataBuffer.this;
      }

      @Override
      public ByteDataBuffer visit(long address, long length, long scale) {
        memory.copyTo(UnsafeMemoryHandle.fromAddress(address, length, scale), size);
        return ByteRawDataBuffer.this;
      }

      @Override
      public ByteDataBuffer fallback() {
        if (dst instanceof ByteDataBuffer) {
          ByteDataBuffer byteDst = (ByteDataBuffer)dst;
          for (long idx = 0L; idx < size; ++idx) {
            byteDst.setByte(getByte(idx), idx);
          }
          return ByteRawDataBuffer.this;
        }
        return slowCopyTo(dst, size);
      }
    });
  }

  @Override
  public IntDataBuffer asInts() {
    return new IntRawDataBuffer(memory.rescale(Integer.BYTES), readOnly);
  }

  @Override
  public ShortDataBuffer asShorts() {
    return new ShortRawDataBuffer(memory.rescale(Short.BYTES), readOnly);
  }

  @Override
  public LongDataBuffer asLongs() {
    return new LongRawDataBuffer(memory.rescale(Long.BYTES), readOnly);
  }

  @Override
  public FloatDataBuffer asFloats() {
    return new FloatRawDataBuffer(memory.rescale(Float.BYTES), readOnly);
  }

  @Override
  public DoubleDataBuffer asDoubles() {
    return new DoubleRawDataBuffer(memory.rescale(Double.BYTES), readOnly);
  }

  @Override
  public BooleanDataBuffer asBooleans() {
    return new BooleanRawDataBuffer(memory.rescale(Byte.BYTES), readOnly);
  }

  @Override
  public <R> R accept(DataStorageVisitor<R> visitor) {
    if (memory.isArray()) {
      return visitor.visit(memory.toArrayByteBuffer());
    }
    return visitor.visit(memory.byteOffset, memory.byteSize, memory.scale);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ByteDataBuffer)) {
      return super.equals(obj);
    }
    ByteDataBuffer other = (ByteDataBuffer)obj;
    if (size() != other.size()) {
      return false;
    }
    return other.accept(new DataStorageVisitor<Boolean>() {

      @Override
      public Boolean visit(ByteBuffer buffer) {
        if (memory.isArray()) {
          return buffer.equals(memory.toArrayByteBuffer());
        }
        return fallback();
      }

      @Override
      public Boolean fallback() {
        for (long idx = 0L; idx < size(); ++idx) {
          if (other.getByte(idx) != getByte(idx)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  @Override
  protected ByteDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new ByteRawDataBuffer(memory, readOnly);
  }

  ByteRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
