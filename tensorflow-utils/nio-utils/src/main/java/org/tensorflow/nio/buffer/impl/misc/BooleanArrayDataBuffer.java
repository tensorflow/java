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
package org.tensorflow.nio.buffer.impl.misc;

import org.tensorflow.nio.buffer.BooleanDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.impl.AbstractDataBuffer;
import org.tensorflow.nio.buffer.impl.Validator;

public class BooleanArrayDataBuffer extends AbstractDataBuffer<Boolean> implements BooleanDataBuffer {

  public static BooleanDataBuffer wrap(boolean[] array, boolean readOnly) {
    return new BooleanArrayDataBuffer(array, readOnly);
  }

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
  public BooleanDataBuffer copyTo(DataBuffer<Boolean> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof BooleanArrayDataBuffer) {
      BooleanArrayDataBuffer dstBuffer = (BooleanArrayDataBuffer)dst;
      System.arraycopy(values, offset, dstBuffer.values, dstBuffer.offset, length);
    } else {
      slowCopyTo(dst, size);
    }
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
  public BooleanDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new BooleanArrayDataBuffer(values, readOnly, offset + (int)index, length - (int)index);
  }

  @Override
  public BooleanDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new BooleanArrayDataBuffer(values, readOnly, offset, (int)size);
  }

  private BooleanArrayDataBuffer(boolean[] values, boolean readOnly) {
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
