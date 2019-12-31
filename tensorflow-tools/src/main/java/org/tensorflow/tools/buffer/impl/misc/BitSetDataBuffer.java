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

package org.tensorflow.tools.buffer.impl.misc;

import java.util.BitSet;
import org.tensorflow.tools.buffer.BooleanDataBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.impl.AbstractDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

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
  public BooleanDataBuffer copyTo(DataBuffer<Boolean> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    slowCopyTo(dst, size);
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
  public BooleanDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new BitSetDataBuffer(bitSet, numBits - index, readOnly, offset + (int)index);
  }

  @Override
  public BooleanDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new BitSetDataBuffer(bitSet, size, readOnly, offset);
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
