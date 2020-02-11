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

import java.util.Arrays;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.DataStorageVisitor;
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
    return dst.accept(new DataStorageVisitor<DataBuffer<T>>() {

      @Override
      public DataBuffer<T> visit(Object[] array, int arrayOffset, int arrayLength) {
        System.arraycopy(values, offset, array, arrayOffset, (int)size);
        return ArrayDataBuffer.this;
      }

      @Override
      public DataBuffer<T> otherwise() {
        for (int idx = 0; idx < size; ++idx) {
          dst.setObject(values[idx + offset], idx);
        }
        return ArrayDataBuffer.this;
      }
    });
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

  @Override
  public <R> R accept(DataStorageVisitor<R> visitor) {
    return visitor.visit(values, offset, length);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof DataBuffer)) {
      return false;
    }
    DataBuffer<?> other = (DataBuffer<?>)obj;
    if (size() != other.size()) {
      return false;
    }
    return other.accept(new DataStorageVisitor<Boolean>() {

      @Override
      public Boolean visit(Object[] array, int arrayOffset, int arrayLength) {
        if (offset == 0 && values.length == length && arrayOffset == 0 && array.length == arrayLength) {
          return Arrays.deepEquals(array, values);
        }
        return slowEquals(other);
      }

      @Override
      public Boolean otherwise() {
        return slowEquals(other);
      }
    });
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
