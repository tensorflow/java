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
package org.tensorflow.nio.buffer.impl.single;

import java.lang.reflect.Array;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ReadOnlyBufferException;
import java.util.Arrays;
import java.util.stream.Stream;

import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.impl.AbstractDataBuffer;
import org.tensorflow.nio.buffer.impl.Validator;

public class ArrayDataBuffer<T> extends AbstractDataBuffer<T, DataBuffer<T>> {

  public static long MAX_CAPACITY = Integer.MAX_VALUE - 2;
  
  public static <T> DataBuffer<T> allocate(Class<T> clazz, long capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Capacity must be non-negative");
    }
    if (capacity > MAX_CAPACITY) {
      throw new IllegalArgumentException("Size for an array-based data buffer cannot exceeds " + MAX_CAPACITY +
          " elements, use a LargeDataBuffer instead");
    }
    @SuppressWarnings("unchecked")
    T[] array = (T[])Array.newInstance(clazz, (int)capacity);
    return new ArrayDataBuffer<T>(array, false);
  }

  public static <T> DataBuffer<T> wrap(T[] array, boolean readOnly) {
    return new ArrayDataBuffer<T>(array, readOnly);
  }

  @Override
  public long capacity() {
    return values.length;
  }

  @Override
  public long limit() {
    return limitIndex;
  }

  @Override
  public DataBuffer<T> limit(long newLimit) {
    Validator.newLimit(this, newLimit);
    limitIndex = (int)newLimit;
    if (positionIndex > limitIndex) {
      positionIndex = limitIndex;
    }
    return this;
  }

  @Override
  public boolean hasRemaining() {
    return positionIndex < limitIndex;
  }

  @Override
  public long remaining() {
    return limitIndex - positionIndex;
  }

  @Override
  public long position() {
    return positionIndex;
  }

  @Override
  public DataBuffer<T> position(long newPosition) {
    Validator.newPosition(this, newPosition);
    positionIndex = (int)newPosition;
    return this;
  }

  @Override
  public DataBuffer<T> rewind() {
    positionIndex = 0;
    return this;
  }

  @Override
  public boolean isReadOnly() {
    return readOnly;
  }

  @Override
  public T get() {
    if (!hasRemaining()) {
      throw new BufferUnderflowException();
    }
    return values[positionIndex++];
  }

  @Override
  public T get(long index) {
    Validator.getArgs(this, index);
    return values[(int)index];
  }

  @Override
  public Stream<T> stream() {
    return Arrays.stream(values);
  }

  @Override
  public DataBuffer<T> put(T value) {
    if (!hasRemaining()) {
      throw new BufferOverflowException();
    }
    if (isReadOnly()) {
      throw new ReadOnlyBufferException();
    }
    values[positionIndex++] = value;
    return this;
  }

  @Override
  public DataBuffer<T> put(long index, T value) {
    Validator.putArgs(this, index);
    values[(int)index] = value;
    return this;
  }

  @Override
  public DataBuffer<T> put(DataBuffer<T> src) {
    Validator.putArgs(this, src);
    if (src instanceof ArrayDataBuffer) {
      ArrayDataBuffer<T> srcArrayBuffer = (ArrayDataBuffer<T>)src;
      int length = (int)src.remaining();
      System.arraycopy(srcArrayBuffer.values, srcArrayBuffer.positionIndex, values, positionIndex, length);
      srcArrayBuffer.positionIndex += length;
      positionIndex += length;
      return this;
    }
    return super.put(src);
  }

  @Override
  public DataBuffer<T> duplicate() {
    return new ArrayDataBuffer<T>(values, readOnly, positionIndex, limitIndex);
  }

  private ArrayDataBuffer(T[] values, boolean readOnly) {
    this(values, readOnly, 0, values.length);
  }

  private ArrayDataBuffer(T[] values, boolean readOnly, int positionIndex, int limitIndex) {
    this.values = values;  
    this.readOnly = readOnly;
    this.positionIndex = positionIndex;
    this.limitIndex = limitIndex;
  }
 
  private final T[] values;
  private final boolean readOnly;
  private int positionIndex;
  private int limitIndex;
}
