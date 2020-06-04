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
package org.tensorflow.ndarray.impl.buffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.tensorflow.ndarray.buffer.DataBuffer;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {

  @Override
  public DataBuffer<T> read(T[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0; i < length; ++i) {
      dst[i + offset] = getObject(i);
    }
    return this;
  }

  @Override
  public DataBuffer<T> write(T[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0; i < length; ++i) {
      setObject(src[i + offset], i);
    }
    return this;
  }

  @Override
  public DataBuffer<T> copyTo(DataBuffer<T> dst, long size) {
    return slowCopyTo(dst, size);
  }

  @Override
  public int hashCode() {
    // This hash code computation is generic to all types of data buffers and accurate but not optimized
    // for performances, it needs to be improved if there is a present use case for such hash codes.
    return slowHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof DataBuffer)) {
      return false;
    }
    return slowEquals((DataBuffer<?>)obj);
  }

  @SuppressWarnings("unchecked")
  protected <U extends DataBuffer<T>> U slowCopyTo(DataBuffer<T> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    for (long idx = 0L; idx < size; ++idx) {
      dst.setObject(getObject(idx), idx);
    }
    return (U)this;
  }

  protected int slowHashCode() {
    final int prime = 31;
    int result = 1;

    // First check from the first non-null element if we are dealing with a buffer of arrays
    long idx = 0L;
    for (; idx < size(); ++idx) {
      T o = getObject(idx);
      if (o != null) {
        if (o.getClass().isArray()) {
          result = prime * result + arrayHashCode(idx, o.getClass()); // compute hash codes based on array elements
          return result;
        }
        result = prime * result + o.hashCode();
        break;  // continue hash code computation without array type check
      }
      result = prime * result;
    }
    while (++idx < size()) {
      result = prime * result + Objects.hashCode(getObject(idx));
    }
    return result;
  }

  protected boolean slowEquals(DataBuffer<?> other) {
    if (other.size() != size()) {
      return false;
    }
    long idx = 0L;
    for (; idx < size(); ++idx) {
      Object thisObject = getObject(idx);
      if (thisObject != null) {
        if (thisObject.getClass().isArray()) {
          return arrayEquals(idx, thisObject.getClass(), other);
        }
        if (!Objects.equals(other.getObject(idx), thisObject)) {
          return false;
        }
        break;  // continue equality comparison without array type check
      }
      if (other.getObject(idx) != null) {
        return false;
      }
    }
    while (++idx < size()) {
      if (!Objects.equals(other.getObject(idx), getObject(idx))) {
        return false;
      }
    }
    return true;
  }

  private int arrayHashCode(long startIdx, Class<?> arrayClass) {
    ArrayHashCoder hashCoder = ARRAY_HASH_CODERS.getOrDefault(arrayClass, DEFAULT_ARRAY_HASH_CODER);
    final int prime = 31;
    int result = 1;
    for (long idx = startIdx; idx < size(); ++idx) {
      result = prime * result + hashCoder.hashCode(this, idx);
    }
    return result;
  }

  private boolean arrayEquals(long startIdx, Class<?> arrayClass, DataBuffer<?> other) {
    ArrayComparator comparator = ARRAY_COMPARATORS.getOrDefault(arrayClass, DEFAULT_ARRAY_COMPARATOR);
    for (long idx = startIdx; idx < size(); ++idx) {
      if (!comparator.equals(this, other, idx)) {
        return false;
      }
    }
    return true;
  }

  @FunctionalInterface
  private static interface ArrayHashCoder {
    int hashCode(DataBuffer<?> buffer, long index);
  }
  private static final Map<Class<?>, ArrayHashCoder> ARRAY_HASH_CODERS = new HashMap<>();
  private static final ArrayHashCoder DEFAULT_ARRAY_HASH_CODER;

  @FunctionalInterface
  private static interface ArrayComparator {
    boolean equals(DataBuffer<?> buffer, DataBuffer<?> otherBuffer, long index);
  }
  private static final Map<Class<?>, ArrayComparator> ARRAY_COMPARATORS = new HashMap<>();
  private static final ArrayComparator DEFAULT_ARRAY_COMPARATOR;

  static {
    ARRAY_HASH_CODERS.put(byte[].class, (b, idx) -> Arrays.hashCode((byte[])b.getObject(idx)));
    ARRAY_HASH_CODERS.put(int[].class, (b, idx) -> Arrays.hashCode((int[])b.getObject(idx)));
    ARRAY_HASH_CODERS.put(short[].class, (b, idx) -> Arrays.hashCode((short[])b.getObject(idx)));
    ARRAY_HASH_CODERS.put(long[].class, (b, idx) -> Arrays.hashCode((long[])b.getObject(idx)));
    ARRAY_HASH_CODERS.put(float[].class, (b, idx) -> Arrays.hashCode((float[])b.getObject(idx)));
    ARRAY_HASH_CODERS.put(double[].class, (b, idx) -> Arrays.hashCode((double[])b.getObject(idx)));
    ARRAY_HASH_CODERS.put(boolean[].class, (b, idx) -> Arrays.hashCode((boolean[])b.getObject(idx)));
    DEFAULT_ARRAY_HASH_CODER = (b, idx) -> Arrays.deepHashCode((Object[])b.getObject(idx));

    ARRAY_COMPARATORS.put(byte[].class, (b1, b2, idx) -> Arrays.equals((byte[])b1.getObject(idx), (byte[])b2.getObject(idx)));
    ARRAY_COMPARATORS.put(int[].class, (b1, b2, idx) -> Arrays.equals((int[])b1.getObject(idx), (int[])b2.getObject(idx)));
    ARRAY_COMPARATORS.put(short[].class, (b1, b2, idx) -> Arrays.equals((short[])b1.getObject(idx), (short[])b2.getObject(idx)));
    ARRAY_COMPARATORS.put(long[].class, (b1, b2, idx) -> Arrays.equals((long[])b1.getObject(idx), (long[])b2.getObject(idx)));
    ARRAY_COMPARATORS.put(float[].class, (b1, b2, idx) -> Arrays.equals((float[])b1.getObject(idx), (float[])b2.getObject(idx)));
    ARRAY_COMPARATORS.put(double[].class, (b1, b2, idx) -> Arrays.equals((double[])b1.getObject(idx), (double[])b2.getObject(idx)));
    ARRAY_COMPARATORS.put(boolean[].class, (b1, b2, idx) -> Arrays.equals((boolean[])b1.getObject(idx), (boolean[])b2.getObject(idx)));
    DEFAULT_ARRAY_COMPARATOR = (b1, b2, idx) -> Arrays.deepEquals((Object[])b1.getObject(idx), (Object[])b2.getObject(idx));
  }
}
