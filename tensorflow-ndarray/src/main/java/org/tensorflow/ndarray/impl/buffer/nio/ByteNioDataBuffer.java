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

import java.nio.ByteBuffer;
import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.impl.buffer.adapter.DataBufferAdapterFactory;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataStorageVisitor;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.buffer.layout.DataLayouts;

/**
 * A buffer of bytes using a JDK {@link ByteBuffer} for storage.
 */
final class ByteNioDataBuffer extends AbstractNioDataBuffer<Byte>
    implements ByteDataBuffer {

  @Override
  public byte getByte(long index) {
    return buf.get((int)index);
  }

  @Override
  public ByteDataBuffer setByte(byte value, long index) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public ByteDataBuffer read(byte[] dst, int offset, int length) {
    buf.duplicate().get(dst, offset, length);
    return this;
  }

  @Override
  public ByteDataBuffer write(byte[] src, int offset, int length) {
    buf.duplicate().put(src, offset, length);
    return this;
  }

  @Override
  public ByteDataBuffer copyTo(DataBuffer<Byte> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<ByteDataBuffer>() {

      @Override
      public ByteDataBuffer visit(ByteBuffer buffer) {
        buffer.duplicate().put((ByteBuffer)buf.duplicate().limit((int)size));
        return ByteNioDataBuffer.this;
      }

      @Override
      public ByteDataBuffer fallback() {
        if (dst instanceof ByteDataBuffer) {
          ByteDataBuffer byteDst = (ByteDataBuffer)dst;
          for (long idx = 0L; idx < size; ++idx) {
            byteDst.setByte(getByte(idx), idx);
          }
          return ByteNioDataBuffer.this;
        }
        return slowCopyTo(dst, size);
      }
    });
  }

  @Override
  public IntDataBuffer asInts() {
    return new IntNioDataBuffer(buf.asIntBuffer());
  }

  @Override
  public ShortDataBuffer asShorts() {
    return new ShortNioDataBuffer(buf.asShortBuffer());
  }

  @Override
  public LongDataBuffer asLongs() {
    return new LongNioDataBuffer(buf.asLongBuffer());
  }

  @Override
  public FloatDataBuffer asFloats() {
    return new FloatNioDataBuffer(buf.asFloatBuffer());
  }

  @Override
  public DoubleDataBuffer asDoubles() {
    return new DoubleNioDataBuffer(buf.asDoubleBuffer());
  }

  @Override
  public BooleanDataBuffer asBooleans() {
    return DataBufferAdapterFactory.create(this, DataLayouts.BOOL);
  }

  @Override
  public ByteDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new ByteNioDataBuffer(((ByteBuffer)buf.duplicate().position((int)index)).slice());
  }

  @Override
  public ByteDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new ByteNioDataBuffer(((ByteBuffer)buf.duplicate().limit((int)size)).slice());
  }

  @Override
  public ByteDataBuffer slice(long index, long size) {
    Validator.sliceArgs(this, index, size);
    ByteBuffer sliceBuf = buf.duplicate();
    sliceBuf.position((int)index);
    sliceBuf.limit((int)index + (int)size);
    return new ByteNioDataBuffer(sliceBuf.slice());
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
        return buf.equals(buffer);
      }

      @Override
      public Boolean fallback() {
        for (int idx = 0; idx < size(); ++idx) {
          if (other.getByte(idx) != getByte(idx)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  @Override
  ByteBuffer buf() {
    return buf;
  }

  ByteNioDataBuffer(ByteBuffer buf) {
    this.buf = buf;
  }

  private ByteBuffer buf;
}
