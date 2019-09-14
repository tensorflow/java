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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public abstract class LongDataBufferTestBase extends DataBufferTestBase<Long> {

  @Override
  protected abstract LongDataBuffer allocate(long capacity);

  @Override
  protected Long valueOf(Long val) {
    return val;
  }

  @Test
  public void writeAndReadFromArray() {
    LongDataBuffer buffer = allocate(10L);
    long[] oneToFive = new long[]{valueOf(1L), valueOf(2L), valueOf(3L), valueOf(4L), valueOf(5L)};

    buffer.put(oneToFive);
    assertEquals(valueOf(2L), buffer.get(1));
    assertEquals(5L, buffer.position());

    buffer.put(oneToFive);
    assertEquals(valueOf(2L), buffer.get(6));
    assertEquals(10L, buffer.position());

    buffer.rewind();
    long[] read = new long[5];
    buffer.get(read);
    assertArrayEquals(oneToFive, read);
    assertEquals(5L, buffer.position());

    buffer.rewind();
    buffer.put(oneToFive, 2, 2);
    assertEquals(valueOf(3L), buffer.get(0));
    assertEquals(valueOf(4L), buffer.get(1));
    assertEquals(valueOf(3L), buffer.get(2));
    assertEquals(2L, buffer.position());

    Arrays.fill(read, valueOf(0L));
    buffer.get(read, 1, 2);
    assertEquals(valueOf(0L), (Long) read[0]);
    assertEquals(valueOf(3L), (Long) read[1]);
    assertEquals(valueOf(4L), (Long) read[2]);
    assertEquals(valueOf(0L), (Long) read[3]);
    assertEquals(4L, buffer.position());
  }
}
