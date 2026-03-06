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

package org.tensorflow.ndarray.impl.buffer.misc;

import java.util.BitSet;
import org.tensorflow.ndarray.impl.buffer.AbstractDataBuffer;
import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DataStorageVisitor;

class BitSetDataBuffer extends AbstractDataBuffer<Boolean> implements BooleanDataBuffer {

  @Override
  public long size() {
    return numBits;
  }

  @Override
  public boolean isReadOnly() {
    return readOnly;
  }

  @Override
  public boolean getBoolean(long index) {
    Validator.getArgs(this, index);
    return bitSet.get((int)index + offset);
  }

  @Override
  public BooleanDataBuffer setBoolean(boolean value, long index) {
    Validator.setArgs(this, index);
    bitSet.set((int)index + offset, value);
    return this;
  }

  @Override
  public BooleanDataBuffer read(boolean[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = this.offset, j = offset; i < this.offset + length; ++i, ++j) {
      dst[j] = bitSet.get(i);
    }
    return this;
  }

  @Override
  public BooleanDataBuffer write(boolean[] src, int offset, int length) {
    Validator.readArgs(this, src.length, offset, length);
    for (int i = this.offset, j = offset; i < this.offset + length; ++i, ++j) {
      bitSet.set(i, src[j]);
    }
    return this;
  }

  @Override
  public BooleanDataBuffer copyTo(DataBuffer<Boolean> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    return dst.accept(new DataStorageVisitor<BooleanDataBuffer>() {

      @Override
      public BooleanDataBuffer visit(boolean[] array, int arrayOffset, int arrayLength) {
        for (int idx = 0; idx < size; ++idx) {
          array[idx + arrayOffset] = bitSet.get(idx + offset);
        }
        return BitSetDataBuffer.this;
      }

      @Override
      public BooleanDataBuffer visit(BitSet dstBitSet, int dstOffset, long numBits) {
        for (int idx = 0; idx < size; ++idx) {
          dstBitSet.set(idx + dstOffset, bitSet.get(idx + offset));
        }
        return BitSetDataBuffer.this;
      }

      @Override
      public BooleanDataBuffer fallback() {
        if (dst instanceof BooleanDataBuffer) {
          BooleanDataBuffer booleanDst = (BooleanDataBuffer)dst;
          for (int idx = 0; idx < size; ++idx) {
            booleanDst.setBoolean(bitSet.get(idx + offset), idx);
          }
        } else {
          for (int idx = 0; idx < size; ++idx) {
            dst.setObject(bitSet.get(idx + offset), idx);
          }
        }
        return BitSetDataBuffer.this;
      }
    });
  }

  @Override
  public BooleanDataBuffer slice(long index, long size) {
    Validator.sliceArgs(this, index, size);
    return new BitSetDataBuffer(bitSet, size, readOnly, offset + (int)index);
  }

  @Override
  public <R> R accept(DataStorageVisitor<R> visitor) {
    return visitor.visit(bitSet, offset, numBits);
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
      public Boolean visit(boolean[] array, int arrayOffset, int length) {
        for (int idx = 0; idx < size(); ++idx) {
          if (array[idx + arrayOffset] != bitSet.get(idx + offset)) {
            return false;
          }
        }
        return true;
      }

      @Override
      public Boolean visit(BitSet otherBitSet, int otherOffset, long otherNumBits) {
        if (offset == 0 && otherOffset == 0 && numBits == otherNumBits) {
          return bitSet.equals(otherBitSet);
        }
        for (int idx = 0; idx < size(); ++idx) {
          if (otherBitSet.get(idx + otherOffset) != bitSet.get(idx + offset)) {
            return false;
          }
        }
        return true;
      }

      @Override
      public Boolean fallback() {
        for (int idx = 0; idx < size(); ++idx) {
          if (other.getBoolean(idx) != bitSet.get(idx + offset)) {
            return false;
          }
        }
        return true;
      }
    });
  }

  BitSetDataBuffer(BitSet bitSet, long numBits, boolean readOnly) {
    this(bitSet, numBits, readOnly, 0);
  }

  private BitSetDataBuffer(BitSet bitSet, long numBits, boolean readOnly, int offset) {
    this.bitSet = bitSet;
    this.numBits = numBits;
    this.readOnly = readOnly;
    this.offset = offset;
  }

  private final BitSet bitSet;
  private final long numBits;
  private final boolean readOnly;
  private final int offset;
}
