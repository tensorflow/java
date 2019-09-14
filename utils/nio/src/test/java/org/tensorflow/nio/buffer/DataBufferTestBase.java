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

  protected abstract long maxCapacity();

  protected abstract DataBuffer<T> allocate(long capacity);

  protected abstract T valueOf(Long val);

  @Test
  public void capacities() {
    DataBuffer<T> buffer = allocate(10L);
    assertEquals(10L, buffer.capacity());

    buffer = allocate(0L);
    assertEquals(0L, buffer.capacity());

    try {
      allocate(-1L);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      allocate(maxCapacity() + 1);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    if (enableLargeBufferTests) {
      buffer = allocate(maxCapacity());
      assertEquals(maxCapacity(), buffer.capacity());
    }
  }

  @Test
  public void limitsAndPositions() {
    DataBuffer<T> buffer = allocate(10L);
    assertEquals(10L, buffer.limit());
    assertEquals(0L, buffer.position());
    assertTrue(buffer.hasRemaining());
    assertEquals(10L, buffer.remaining());

    buffer.position(8L);
    assertEquals(10L, buffer.limit());
    assertEquals(8L, buffer.position());
    assertTrue(buffer.hasRemaining());
    assertEquals(2L, buffer.remaining());

    buffer.limit(5L);
    assertEquals(5L, buffer.limit());
    assertEquals(5L, buffer.position());
    assertFalse(buffer.hasRemaining());
    assertEquals(0L, buffer.remaining());

    buffer.rewind();
    assertEquals(5L, buffer.limit());
    assertEquals(0L, buffer.position());
    assertTrue(buffer.hasRemaining());
    assertEquals(5L, buffer.remaining());

    buffer.limit(0L);
    assertEquals(0L, buffer.limit());
    assertEquals(0L, buffer.position());
    assertFalse(buffer.hasRemaining());
    assertEquals(0L, buffer.remaining());

    buffer.limit(10L);
    assertEquals(10L, buffer.limit());
    assertEquals(0L, buffer.position());
    assertTrue(buffer.hasRemaining());
    assertEquals(10L, buffer.remaining());

    buffer.position(10L);
    assertEquals(10L, buffer.limit());
    assertEquals(10L, buffer.position());
    assertFalse(buffer.hasRemaining());
    assertEquals(0L, buffer.remaining());

    try {
      buffer.limit(-1L);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      buffer.limit(11L);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      buffer.position(11L);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    try {
      buffer.position(-1L);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
  }

  @Test
  public void duplicates() {
    DataBuffer<T> buffer = allocate(10L);
    buffer.limit(8L);
    buffer.position(3L);

    DataBuffer<T> duplicate = buffer.duplicate();
    assertEquals(buffer.capacity(), duplicate.capacity());
    assertEquals(buffer.limit(), duplicate.limit());
    assertEquals(buffer.position(), duplicate.position());

    duplicate = buffer.withLimit(2L);
    assertEquals(buffer.capacity(), duplicate.capacity());
    assertEquals(8L, buffer.limit());
    assertEquals(3L, buffer.position());
    assertEquals(2L, duplicate.limit());
    assertEquals(2L, duplicate.position());

    duplicate = buffer.withPosition(2L);
    assertEquals(buffer.capacity(), duplicate.capacity());
    assertEquals(8L, buffer.limit());
    assertEquals(3L, buffer.position());
    assertEquals(8L, duplicate.limit());
    assertEquals(2L, duplicate.position());
  }

  @Test
  public void slices() {
    DataBuffer<T> buffer = allocate(12L);

    DataBuffer<T> fullSlice = buffer.slice();
    assertEquals(12L, fullSlice.capacity());
    assertEquals(12L, fullSlice.limit());
    assertEquals(0L, fullSlice.position());

    fullSlice.position(1L);
    assertEquals(1L, fullSlice.position());
    assertEquals(0L, buffer.position());

    buffer.position(6L);
    assertEquals(1L, fullSlice.position());

    DataBuffer<T> secondHalfSlice = buffer.slice();
    assertEquals(6L, secondHalfSlice.capacity());
    assertEquals(6L, secondHalfSlice.limit());
    assertEquals(0L, secondHalfSlice.position());

    fullSlice.position(3L).limit(6L);
    DataBuffer<T> secondQuarterSlice = fullSlice.slice();
    assertEquals(3L, secondQuarterSlice.capacity());
    assertEquals(3L, secondQuarterSlice.limit());
    assertEquals(0L, secondQuarterSlice.position());

    fullSlice.put(3L, valueOf(3L));
    assertEquals(valueOf(3L), fullSlice.get(3L));
    assertEquals(valueOf(3L), secondQuarterSlice.get()); // increments position
    assertEquals(valueOf(3L), buffer.get(3L));

    secondHalfSlice.put(valueOf(6L));
    assertEquals(valueOf(6L), secondHalfSlice.get(0L));
    assertEquals(valueOf(6L), buffer.get(6L));

    secondQuarterSlice.put(valueOf(9L));
    assertEquals(valueOf(9L), fullSlice.get(4L));
    assertEquals(valueOf(9L), buffer.get(4L));
  }

  @Test
  public void writeAndReadFromPosition() {
    DataBuffer<T> buffer = allocate(10L);

    long val = 0L;
    while (buffer.hasRemaining()) {
      buffer.put(valueOf(val++));
    }
    assertEquals(10L, val);
    assertEquals(0L, buffer.remaining());
    try {
      buffer.put(valueOf(val));
      fail();
    } catch (BufferOverflowException e) {
      // as expected
    }
    val = 0L;
    buffer.rewind();
    while (buffer.hasRemaining()) {
      assertEquals(valueOf(val++), buffer.get());
    }
    try {
      buffer.get();
      fail();
    } catch (BufferUnderflowException e) {
      // as expected
    }
  }

  @Test
  public void writeAndReadWithIndex() {
    DataBuffer<T> buffer = allocate(10L);

    buffer.put(5L, valueOf(5L));
    assertEquals(0L, buffer.position());
    assertEquals(valueOf(5L), buffer.get(5L));
    try {
      buffer.put(10L, valueOf(10L));
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
      buffer.put(-1L, valueOf(-1L));
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
  public void writeFromSourceBuffer() {
    DataBuffer<T> buffer = allocate(20L);
    try {
      buffer.put(buffer);
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    DataBuffer<T> srcBuffer = allocate(25L);
    srcBuffer.put(5L, valueOf(5L));
    srcBuffer.put(10L, valueOf(10L));
    srcBuffer.put(15L, valueOf(15L));
    srcBuffer.put(20L, valueOf(20L));

    srcBuffer.limit(10L);
    buffer.put(srcBuffer);
    assertEquals(valueOf(5L), buffer.get(5L));
    assertEquals(10L, srcBuffer.position());
    assertEquals(10L, buffer.position());

    srcBuffer.rewind();
    buffer.put(srcBuffer);
    assertEquals(valueOf(5L), buffer.get(15L));
    assertEquals(10L, srcBuffer.position());
    assertEquals(20L, buffer.position());

    assertFalse(srcBuffer.hasRemaining());
    buffer.put(srcBuffer); // should be allowed because srcBuffer is now empty

    srcBuffer.rewind();
    try {
      buffer.put(srcBuffer);
      fail();
    } catch (BufferOverflowException e) {
      // as expected
    }
    buffer.rewind();
    srcBuffer.rewind();
    srcBuffer.limit(20L);
    buffer.put(srcBuffer);
    assertEquals(valueOf(5L), buffer.get(5L));
    assertEquals(valueOf(10L), buffer.get(10L));
    assertEquals(valueOf(15L), buffer.get(15L));

    buffer.rewind();
    srcBuffer.rewind();
    srcBuffer.limit(25L);
    try {
      buffer.put(srcBuffer);
      fail();
    } catch (BufferOverflowException e) {
      // as expected
    }
  }
}
