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
package org.tensorflow.ndarray.impl.buffer.misc;

import java.util.Arrays;
import java.util.BitSet;
import org.tensorflow.ndarray.impl.buffer.AbstractDataBuffer;
import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataStorageVisitor;

class BooleanArrayDataBuffer extends AbstractDataBuffer<Boolean> implements
    BooleanDataBuffer {

  @Override
  public long size() {
    return length;
  }

  @Override
  public boolean isReadOnly() {
    return readOnly;
  }

  @Override
  public boolean getBoolean(long index) {
    Validator.getArgs(this, index);
    return values[(int)index + offset];
  }

  @Override
  public BooleanDataBuffer setBoolean(boolean value, long index) {
    Validator.setArgs(this, index);
    values[(int)index + offset] = value;
    return this;
  }

  @Override
  public BooleanDataBuffer read(boolean[] dst, int offset, int length) {
    System.arraycopy(values, this.offset, dst, offset, length);
    return this;
  }

  @Override
  public BooleanDataBuffer write(boolean[] src, int offset, int length) {
    System.arraycopy(src, offset, values, this.offset, length);
    return null;
  }

  @Override
  public BooleanDataBuffer copyTo(DataBuffer<Boolean> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<BooleanDataBuffer>() {

      @Override
      public BooleanDataBuffer visit(boolean[] array, int arrayOffset, int arrayLength) {
        System.arraycopy(values, offset, array, arrayOffset, (int)size);
        return BooleanArrayDataBuffer.this;
      }

      @Override
      public BooleanDataBuffer visit(BitSet bitSet, int bitSetOffset, long numBits) {
        for (int idx = 0; idx < size; ++idx) {
          bitSet.set(idx + bitSetOffset, values[idx + offset]);
        }
        return BooleanArrayDataBuffer.this;
      }

      @Override
      public BooleanDataBuffer fallback() {
        if (dst instanceof BooleanDataBuffer) {
          BooleanDataBuffer booleanDst = (BooleanDataBuffer)dst;
          for (int idx = 0; idx < size; ++idx) {
            booleanDst.setBoolean(values[idx + offset], idx);
          }
        } else {
          for (int idx = 0; idx < size; ++idx) {
            dst.setObject(values[idx + offset], idx);
          }
        }
        return BooleanArrayDataBuffer.this;
      }
    });
  }

  @Override
  public BooleanDataBuffer slice(long index, long size) {
    Validator.sliceArgs(this, index, size);
    return new BooleanArrayDataBuffer(values, readOnly, offset + (int)index, (int)size);
  }

  @Override
  public <R> R accept(DataStorageVisitor<R> visitor) {
    return visitor.visit(values, offset, length);
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
      public Boolean visit(boolean[] array, int arrayOffset, int arrayLength) {
        if (offset == 0 && values.length == length && arrayOffset == 0 && array.length == arrayLength) {
          return Arrays.equals(array, values);
        }
        for (int idx = 0; idx < size(); ++idx) {
          if (array[idx + arrayOffset] != values[idx + offset]) {
            return false;
          }
        }
        return true;
      }

      @Override
      public Boolean visit(BitSet bitSet, int bitSetOffset, long numBits) {
        for (int idx = 0; idx < size(); ++idx) {
          if (bitSet.get(idx + bitSetOffset) != values[idx + offset]) {
            return false;
          }
        }
        return true;
      }

      @Override
      public Boolean fallback() {
        for (int idx = 0; idx < size(); ++idx) {
          if (other.getBoolean(idx) != values[idx + offset]) {
            return false;
          }
        }
        return true;
      }
    });
  }

  BooleanArrayDataBuffer(boolean[] values, boolean readOnly) {
    this(values, readOnly, 0, values.length);
  }

  private BooleanArrayDataBuffer(boolean[] values, boolean readOnly, int offset, int length) {
    this.values = values;
    this.readOnly = readOnly;
    this.offset = offset;
    this.length = length;
  }
 
  private final boolean[] values;
  private final boolean readOnly;
  private final int offset;
  private final int length;
}
