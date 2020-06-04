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

import java.nio.FloatBuffer;
import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataStorageVisitor;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;

/**
 * A buffer of bytes using a JDK {@link FloatBuffer} for storage.
 */
final class FloatNioDataBuffer extends AbstractNioDataBuffer<Float>
    implements FloatDataBuffer {

  @Override
  public float getFloat(long index) {
    return buf.get((int)index);
  }

  @Override
  public FloatDataBuffer setFloat(float value, long index) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public FloatDataBuffer read(float[] dst, int offset, int length) {
    buf.duplicate().get(dst, offset, length);
    return this;
  }

  @Override
  public FloatDataBuffer write(float[] src, int offset, int length) {
    buf.duplicate().put(src, offset, length);
    return this;
  }

  @Override
  public FloatDataBuffer copyTo(DataBuffer<Float> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<FloatDataBuffer>() {

      @Override
      public FloatDataBuffer visit(FloatBuffer buffer) {
        buffer.duplicate().put((FloatBuffer)buf.duplicate().limit((int)size));
        return FloatNioDataBuffer.this;
      }

      @Override
      public FloatDataBuffer fallback() {
        if (dst instanceof FloatDataBuffer) {
          FloatDataBuffer floatDst = (FloatDataBuffer)dst;
          for (long idx = 0L; idx < size; ++idx) {
            floatDst.setFloat(getFloat(idx), idx);
          }
          return FloatNioDataBuffer.this;
        }
        return slowCopyTo(dst, size);
      }
    });
  }

  @Override
  public FloatDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new FloatNioDataBuffer(((FloatBuffer)buf.duplicate().position((int)index)).slice());
  }

  @Override
  public FloatDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new FloatNioDataBuffer(((FloatBuffer)buf.duplicate().limit((int)size)).slice());
  }

  @Override
  public FloatDataBuffer slice(long index, long size) {
    Validator.sliceArgs(this, index, size);
    FloatBuffer sliceBuf = buf.duplicate();
    sliceBuf.position((int)index);
    sliceBuf.limit((int)index + (int)size);
    return new FloatNioDataBuffer(sliceBuf.slice());
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
    if (!(obj instanceof FloatDataBuffer)) {
      return super.equals(obj);
    }
    FloatDataBuffer other = (FloatDataBuffer)obj;
    if (size() != other.size()) {
      return false;
    }
    return other.accept(new DataStorageVisitor<Boolean>() {

      @Override
      public Boolean visit(FloatBuffer buffer) {
        return buf.equals(buffer);
      }

      @Override
      public Boolean fallback() {
        for (int idx = 0; idx < size(); ++idx) {
          if (other.getFloat(idx) != getFloat(idx)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  @Override
  FloatBuffer buf() {
    return buf;
  }

  FloatNioDataBuffer(FloatBuffer buf) {
    this.buf = buf;
  }

  private FloatBuffer buf;
}
