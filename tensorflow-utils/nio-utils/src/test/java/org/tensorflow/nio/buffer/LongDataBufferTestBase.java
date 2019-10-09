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
  protected abstract LongDataBuffer allocate(long size);

  @Override
  protected Long valueOf(Long val) {
    return val;
  }

  @Test
  public void writeAndReadFromArray() {
    LongDataBuffer buffer = allocate(10L);
    long[] oneToFive = new long[]{ 1L, 2L, 3L, 4L, 5L };

    buffer.write(oneToFive);
    assertEquals(2, buffer.getLong(1));

    buffer.offset(5).write(oneToFive);
    assertEquals(2L, buffer.getLong(1));
    assertEquals(2L, buffer.getLong(6));

    long[] read = new long[5];
    buffer.read(read);
    assertArrayEquals(oneToFive, read);

    buffer.write(oneToFive, 2, 2);
    assertEquals(3L, buffer.getLong(0));
    assertEquals(4L, buffer.getLong(1));
    assertEquals(3L, buffer.getLong(2));

    Arrays.fill(read, valueOf(0L));
    buffer.read(read, 1, 2);
    assertEquals(0L, read[0]);
    assertEquals(3L, read[1]);
    assertEquals(4L, read[2]);
    assertEquals(0L, read[3]);
  }
}
