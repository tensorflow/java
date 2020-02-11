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
package org.tensorflow.tools.buffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import org.junit.Test;

public abstract class DataBufferTestBase<T> {

  protected final boolean enableLargeBufferTests = System.getProperty("testLargeBuffers") != null;

  protected long maxSize() {
    return DataBuffers.MAX_32BITS;
  }

  protected abstract DataBuffer<T> allocate(long size);

  protected abstract T valueOf(Long val);

  @Test
  public void bufferSize() {
    DataBuffer<T> buffer = allocate(10L);
    assertEquals(10L, buffer.size());

    buffer = allocate(0L);
    assertEquals(0L, buffer.size());

    if (enableLargeBufferTests) {
      buffer = allocate(maxSize());
      assertEquals(maxSize(), buffer.size());
    }
  }

  @Test
  public void offsetAndNarrow() {
    DataBuffer<T> buffer = allocate(10L);
    buffer.setObject(valueOf(100L), 6);
    assertEquals(10L, buffer.size());
    assertEquals(valueOf(100L), buffer.getObject(6));

    DataBuffer<T> subBuffer = buffer.offset(3L);
    assertEquals(7L, subBuffer.size());
    assertEquals(valueOf(100L), subBuffer.getObject(3));

    subBuffer = subBuffer.narrow(2L);
    assertEquals(2L, subBuffer.size());
    try {
      subBuffer.getObject(3);
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

    buffer.setObject(valueOf(5L), 5L);
    assertEquals(valueOf(5L), buffer.getObject(5L));
    try {
      buffer.setObject(valueOf(10L), 10L);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }
    try {
      buffer.getObject(10L);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }
    try {
      buffer.setObject(valueOf(-1L), -1L);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }
    try {
      buffer.getObject(-1L);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // as expected
    }
  }

  @Test
  public void copyToBuffer() {
    DataBuffer<T> srcBuffer = allocate(25L);
    srcBuffer.setObject(valueOf(5L), 5L);
    srcBuffer.setObject(valueOf(10L), 10L);
    srcBuffer.setObject(valueOf(15L), 15L);
    srcBuffer.setObject(valueOf(20L), 20L);
    try {
      srcBuffer.copyTo(srcBuffer, srcBuffer.size());
      fail();
    } catch (IllegalArgumentException e) {
      // as expected
    }
    DataBuffer<T> dstBuffer = allocate(30L);
    srcBuffer.copyTo(dstBuffer, srcBuffer.size());
    assertEquals(valueOf(5L), dstBuffer.getObject(5L));
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

  @Test
  public void createFromVarargs() {
    DataBuffer<T> buffer = DataBuffers.ofObjects(valueOf(1L), valueOf(2L), valueOf(3L));
    assertEquals(3, buffer.size());
    assertEquals(valueOf(1L), buffer.getObject(0));
    assertEquals(valueOf(2L), buffer.getObject(1));
    assertEquals(valueOf(3L), buffer.getObject(2));
  }

  @Test
  public void equalWithObjectBuffer() {
    DataBuffer<T> buffer1 = allocate(2)
        .setObject(valueOf(0L), 0)
        .setObject(valueOf(1L), 1);
    DataBuffer<T> buffer2 = allocate(2)
        .setObject(valueOf(0L), 0)
        .setObject(valueOf(1L), 1);
    DataBuffer<T> buffer3 = allocate(2)
        .setObject(valueOf(1L), 0)
        .setObject(valueOf(0L), 1);
    DataBuffer<T> buffer4 = allocate(1)
        .setObject(valueOf(0L), 0);
    DataBuffer<T> buffer5 = allocate(3)
        .setObject(valueOf(0L), 0)
        .setObject(valueOf(1L), 1)
        .setObject(valueOf(2L), 2);

    assertTrue(buffer1.equals(buffer2));
    assertTrue(buffer2.equals(buffer1));
    assertEquals(buffer1.hashCode(), buffer1.hashCode());
    assertEquals(buffer1.hashCode(), buffer2.hashCode());

    assertFalse(buffer3.equals(buffer1));
    assertFalse(buffer1.equals(buffer3));
    assertNotEquals(buffer3.hashCode(), buffer1.hashCode());

    assertFalse(buffer4.equals(buffer1));
    assertFalse(buffer1.equals(buffer4));
    assertNotEquals(buffer4.hashCode(), buffer1.hashCode());

    assertFalse(buffer5.equals(buffer1));
    assertFalse(buffer1.equals(buffer5));
    assertNotEquals(buffer5.hashCode(), buffer1.hashCode());
  }
}
