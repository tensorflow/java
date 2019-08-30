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
package org.tensorflow.nio.buffer.impl;

import java.nio.BufferOverflowException;
import java.nio.ReadOnlyBufferException;

import org.tensorflow.nio.buffer.DataBuffer;

public class Validator {

  public static <T> void newLimit(DataBuffer<T> buffer, long limit) {
    if (limit < 0) {
      throw new IllegalArgumentException("Buffer limit must be non-negative");
    }
    if (limit > buffer.capacity()) {
      throw new IllegalArgumentException("Buffer limit must not exceed its capacity");
    }
  }

  public static <T> void newPosition(DataBuffer<T> buffer, long newPosition) {
    if (newPosition < 0) {
      throw new IllegalArgumentException("Buffer position must be non-negative");
    }
    if (newPosition > buffer.limit()) {
      throw new IllegalArgumentException("Buffer position must not exceed its limit");
    }
  }

  public static <T> void getArgs(DataBuffer<T> buffer, long index) {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index must be non-negative");
    }
    if (index >= buffer.limit()) {
      throw new IndexOutOfBoundsException("Index must be smaller than the buffer limit");
    }
  }

  public static <T> void putArgs(DataBuffer<T> buffer, long index) {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index must be non-negative");
    }
    if (index >= buffer.limit()) {
      throw new IndexOutOfBoundsException("Index must be smaller than the buffer limit");
    }
    if (buffer.isReadOnly()) {
      throw new ReadOnlyBufferException();
    }
  }

  public static <T> void putArgs(DataBuffer<T> buffer, DataBuffer<T> src) {
    if (src == buffer) {
      throw new IllegalArgumentException("Source cannot be the same buffer as destination");
    }
    if (src.remaining() > buffer.remaining()) {
      throw new BufferOverflowException();
    }
    if (buffer.isReadOnly()) {
      throw new ReadOnlyBufferException();
    }
  }
}
