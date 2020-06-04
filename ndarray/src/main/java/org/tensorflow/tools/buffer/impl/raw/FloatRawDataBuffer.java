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

import java.nio.FloatBuffer;

import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.DataStorageVisitor;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

final class FloatRawDataBuffer extends AbstractRawDataBuffer<Float, FloatDataBuffer>
    implements FloatDataBuffer {

  @Override
  public float getFloat(long index) {
    Validator.getArgs(this, index);
    return memory.getFloat(index);
  }

  @Override
  public FloatDataBuffer setFloat(float value, long index) {
    Validator.setArgs(this, index);
    memory.setFloat(value, index);
    return this;
  }

  @Override
  public FloatDataBuffer read(float[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public FloatDataBuffer read(float[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public FloatDataBuffer write(float[] src) {
    return write(src, src.length);
  }

  @Override
  public FloatDataBuffer write(float[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  public FloatDataBuffer copyTo(DataBuffer<Float> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<FloatDataBuffer>() {

      @Override
      public FloatDataBuffer visit(FloatBuffer buffer) {
        if (buffer.hasArray()) {
          memory.copyTo(UnsafeMemoryHandle.fromArray(buffer.array(), buffer.position(), buffer.limit()), size);
        } else if (memory.isArray()) {
          buffer.put(memory.toArrayFloatBuffer());
        } else {
          slowCopyTo(dst, size);
        }
        return FloatRawDataBuffer.this;
      }

      @Override
      public FloatDataBuffer visit(long address, long length, long scale) {
        memory.copyTo(UnsafeMemoryHandle.fromAddress(address, length, scale), size);
        return FloatRawDataBuffer.this;
      }

      @Override
      public FloatDataBuffer fallback() {
        if (dst instanceof FloatDataBuffer) {
          FloatDataBuffer floatDst = (FloatDataBuffer)dst;
          for (long idx = 0L; idx < size; ++idx) {
            floatDst.setFloat(getFloat(idx), idx);
          }
          return FloatRawDataBuffer.this;
        }
        return slowCopyTo(dst, size);
      }
    });
  }

  @Override
  public <R> R accept(DataStorageVisitor<R> visitor) {
    if (memory.isArray()) {
      return visitor.visit(memory.toArrayFloatBuffer());
    }
    return visitor.visit(memory.byteOffset, memory.byteSize, memory.scale);
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
        if (memory.isArray()) {
          return buffer.equals(memory.toArrayFloatBuffer());
        }
        return fallback();
      }

      @Override
      public Boolean fallback() {
        for (long idx = 0L; idx < size(); ++idx) {
          if (other.getFloat(idx) != getFloat(idx)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  @Override
  protected FloatDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new FloatRawDataBuffer(memory, readOnly);
  }

  FloatRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
