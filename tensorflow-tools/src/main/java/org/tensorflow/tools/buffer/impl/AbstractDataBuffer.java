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
package org.tensorflow.tools.buffer.impl;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import org.tensorflow.tools.buffer.DataBuffer;

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
    Validator.copyToArgs(this, dst, size);
    for (long idx = 0L; idx < size; ++idx) {
      dst.setObject(getObject(idx), idx);
    }
    return this;
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
    Function<Long, Integer> hashCoder;
    if (arrayClass == byte[].class) {
      hashCoder = idx -> Arrays.hashCode((byte[])getObject(idx));
    } else if (arrayClass == int[].class) {
      hashCoder = idx -> Arrays.hashCode((int[])getObject(idx));
    } else if (arrayClass == short[].class) {
      hashCoder = idx -> Arrays.hashCode((short[])getObject(idx));
    } else if (arrayClass == long[].class) {
      hashCoder = idx -> Arrays.hashCode((long[])getObject(idx));
    } else if (arrayClass == float[].class) {
      hashCoder = idx -> Arrays.hashCode((float[])getObject(idx));
    } else if (arrayClass == double[].class) {
      hashCoder = idx -> Arrays.hashCode((double[])getObject(idx));
    } else if (arrayClass == boolean[].class) {
      hashCoder = idx -> Arrays.hashCode((boolean[])getObject(idx));
    } else {
      hashCoder = idx -> Arrays.deepHashCode((Object[])getObject(idx));
    }
    final int prime = 31;
    int result = 1;
    for (long idx = startIdx; idx < size(); ++idx) {
      result = prime * result + hashCoder.apply(idx);
    }
    return result;
  }

  private boolean arrayEquals(long startIdx, Class<?> arrayClass, DataBuffer<?> other) {
    Function<Long, Boolean> comparator;
    if (arrayClass == byte[].class) {
      comparator = idx -> Arrays.equals((byte[])other.getObject(idx), (byte[])getObject(idx));
    } else if (arrayClass == int[].class) {
      comparator = idx -> Arrays.equals((int[])other.getObject(idx), (int[])getObject(idx));
    } else if (arrayClass == short[].class) {
      comparator = idx -> Arrays.equals((short[])other.getObject(idx), (short[])getObject(idx));
    } else if (arrayClass == long[].class) {
      comparator = idx -> Arrays.equals((long[])other.getObject(idx), (long[])getObject(idx));
    } else if (arrayClass == float[].class) {
      comparator = idx -> Arrays.equals((float[])other.getObject(idx), (float[])getObject(idx));
    } else if (arrayClass == double[].class) {
      comparator = idx -> Arrays.equals((double[])other.getObject(idx), (double[])getObject(idx));
    } else if (arrayClass == boolean[].class) {
      comparator = idx -> Arrays.equals((boolean[]) other.getObject(idx), (boolean[]) getObject(idx));
    } else {
      comparator = idx -> Arrays.deepEquals((Object[]) other.getObject(idx), (Object[]) getObject(idx));
    }
    for (long idx = startIdx; idx < size(); ++idx) {
      if (!comparator.apply(idx)) {
        return false;
      }
    }
    return true;
  }
}
