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

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ReadOnlyBufferException;
import org.tensorflow.ndarray.buffer.DataBuffer;

public class Validator {

  public static void createArgs(long size, long maxSize) {
    if (size < 0) {
      throw new IllegalArgumentException("Size must be non-negative");
    }
    if (size > maxSize) {
      throw new IllegalArgumentException("Buffer size must be no greater than maximum size allowed (" + maxSize + ")");
    }
  }

  public static <T> void getArgs(DataBuffer<T> buffer, long index) {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index must be non-negative");
    }
    if (index >= buffer.size()) {
      throw new IndexOutOfBoundsException("Index must be smaller than the buffer size");
    }
  }

  public static <T> void setArgs(DataBuffer<T> buffer, long index) {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index must be non-negative");
    }
    if (index >= buffer.size()) {
      throw new IndexOutOfBoundsException("Index must be smaller than the buffer size");
    }
    if (buffer.isReadOnly()) {
      throw new ReadOnlyBufferException();
    }
  }

  public static <T> void copyToArgs(DataBuffer<T> src, DataBuffer<T> dst, long size) {
    if (dst == src) {
      throw new IllegalArgumentException("Source cannot be the same buffer as destination");
    }
    if (size > dst.size()) {
      throw new BufferOverflowException();
    }
    if (size > src.size()) {
      throw new BufferUnderflowException();
    }
    if (dst.isReadOnly()) {
      throw new ReadOnlyBufferException();
    }
  }

  public static <T> void readArgs(DataBuffer<T> buffer, int arrayLength, int offset, int length) {
    if (length > buffer.size()) {
      throw new BufferUnderflowException();
    }
    arrayArgs(arrayLength, offset, length);
  }

  public static <T> void writeArgs(DataBuffer<T> buffer, int arrayLength, int offset, int length) {
    if (length > buffer.size()) {
      throw new BufferOverflowException();
    }
    if (buffer.isReadOnly()) {
      throw new ReadOnlyBufferException();
    }
    arrayArgs(arrayLength, offset, length);
  }

  public static <T> void offsetArgs(DataBuffer<T> buffer, long index) {
    if (index < 0) {
      throw new IllegalArgumentException("Index must be non-negative");
    }
    if (index > buffer.size()) {
      throw new IllegalArgumentException("Index must not exceed buffer size");
    }
  }

  public static <T> void narrowArgs(DataBuffer<T> buffer, long size) {
    if (size < 0) {
      throw new IllegalArgumentException("Size must be non-negative");
    }
    if (size > buffer.size()) {
      throw new IllegalArgumentException("Cannot narrow a buffer of size " + buffer.size() + " to " + size);
    }
  }

  public static <T> void sliceArgs(DataBuffer<T> buffer, long index, long size) {
    if (index < 0) {
      throw new IllegalArgumentException("Index must be non-negative");
    }
    if (size < 0) {
      throw new IllegalArgumentException("Size must be non-negative");
    }
    if (index + size > buffer.size()) {
      throw new IllegalArgumentException("Buffer view must not exceed original buffer limits");
    }
  }

  private static void arrayArgs(int arrayLength, int offset, int length) {
    if (offset < 0) {
      throw new IndexOutOfBoundsException("Offset must be non-negative");
    }
    if (offset > arrayLength) {
      throw new IndexOutOfBoundsException("Offset must be no larger than array length");
    }
    if (length < 0) {
      throw new IndexOutOfBoundsException("Length must be non-negative");
    }
    if (length > arrayLength - offset) {
      throw new IndexOutOfBoundsException("Length must be no larger than array length minus the offset");
    }
  }
}
