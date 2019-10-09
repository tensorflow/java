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
package org.tensorflow.nio.buffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import org.junit.Test;

abstract public class DataBufferTestBase<T> {

  protected final boolean enableLargeBufferTests = System.getProperty("testLargeBuffers") != null;

  protected abstract long maxSize();

  protected abstract DataBuffer<T> allocate(long size);

  protected abstract T valueOf(Long val);

  @Test
  public void bufferSize() {
    DataBuffer<T> buffer = allocate(10L);
    assertEquals(10L, buffer.size());

    buffer = allocate(0L);
    assertEquals(0L, buffer.size());

    try {
      allocate(-1L);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      allocate(maxSize() + 1);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    if (enableLargeBufferTests) {
      buffer = allocate(maxSize());
      assertEquals(maxSize(), buffer.size());
    }
  }

  @Test
  public void offsetAndNarrow() {
    DataBuffer<T> buffer = allocate(10L);
    buffer.set(valueOf(100L), 6);
    assertEquals(10L, buffer.size());
    assertEquals(valueOf(100L), buffer.get(6));

    DataBuffer<T> subBuffer = buffer.offset(3L);
    assertEquals(7L, subBuffer.size());
    assertEquals(valueOf(100L), subBuffer.get(3));

    subBuffer = subBuffer.narrow(2L);
    assertEquals(2L, subBuffer.size());
    try {
      subBuffer.get(3);
      fail();
    } catch (IndexOutOfBoundsException e) {
      //as expected
    }
    try {
      buffer.offset(-1L);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      buffer.offset(11L);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      buffer.narrow(-1L);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      buffer.narrow(11L);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
  }

  @Test
  public void putAndGet() {
    DataBuffer<T> buffer = allocate(10L);

    buffer.set(valueOf(5L), 5L);
    assertEquals(valueOf(5L), buffer.get(5L));
    try {
      buffer.set(valueOf(10L), 10L);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }
    try {
      buffer.get(10L);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }
    try {
      buffer.set(valueOf(-1L), -1L);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }
    try {
      buffer.get(-1L);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }
  }

  @Test
  public void copyToBuffer() {
    DataBuffer<T> srcBuffer = allocate(25L);
    srcBuffer.set(valueOf(5L), 5L);
    srcBuffer.set(valueOf(10L), 10L);
    srcBuffer.set(valueOf(15L), 15L);
    srcBuffer.set(valueOf(20L), 20L);
    try {
      srcBuffer.copyTo(srcBuffer, srcBuffer.size());
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    DataBuffer<T> dstBuffer = allocate(30L);
    srcBuffer.copyTo(dstBuffer, srcBuffer.size());
    assertEquals(valueOf(5L), dstBuffer.get(5L));
    try {
      srcBuffer.copyTo(dstBuffer, dstBuffer.size());
      fail();
    } catch (BufferUnderflowException e) {
      // as expected
    }
    try {
      dstBuffer.copyTo(srcBuffer, dstBuffer.size());
      fail();
    } catch (BufferOverflowException e) {
      // as expected
    }
  }
}
