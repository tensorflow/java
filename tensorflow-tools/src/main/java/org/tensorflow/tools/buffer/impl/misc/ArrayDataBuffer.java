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
package org.tensorflow.tools.buffer.impl.misc;

import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.impl.AbstractDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

class ArrayDataBuffer<T> extends AbstractDataBuffer<T> {

  @Override
  public long size() {
    return length;
  }

  @Override
  public boolean isReadOnly() {
    return readOnly;
  }

  @Override
  public T getObject(long index) {
    Validator.getArgs(this, index);
    return values[(int)index + offset];
  }

  @Override
  public DataBuffer<T> setObject(T value, long index) {
    Validator.setArgs(this, index);
    values[(int)index + offset] = value;
    return this;
  }

  @Override
  public DataBuffer<T> copyTo(DataBuffer<T> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof ArrayDataBuffer) {
      ArrayDataBuffer<T> dstBuffer = (ArrayDataBuffer<T>)dst;
      System.arraycopy(values, offset, dstBuffer.values, dstBuffer.offset, (int)size);
    } else {
      slowCopyTo(dst, size);
    }
    return this;
  }

  @Override
  public DataBuffer<T> offset(long index) {
    Validator.offsetArgs(this, index);
    return new ArrayDataBuffer<>(values, readOnly, offset + (int)index, length - (int)index);
  }

  @Override
  public DataBuffer<T> narrow(long size) {
    Validator.narrowArgs(this, size);
    return new ArrayDataBuffer<>(values, readOnly, offset, (int)size);
  }

  ArrayDataBuffer(T[] values, boolean readOnly) {
    this(values, readOnly, 0, values.length);
  }

  private ArrayDataBuffer(T[] values, boolean readOnly, int offset, int length) {
    this.values = values;
    this.readOnly = readOnly;
    this.offset = offset;
    this.length = length;
  }
 
  private final T[] values;
  private final boolean readOnly;
  private final int offset;
  private final int length;
}
