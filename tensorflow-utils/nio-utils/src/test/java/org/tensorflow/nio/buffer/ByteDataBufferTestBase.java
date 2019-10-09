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

public abstract class ByteDataBufferTestBase extends DataBufferTestBase<Byte> {

  @Override
  protected abstract ByteDataBuffer allocate(long size);

  @Override
  protected Byte valueOf(Long val) {
    return val.byteValue();
  }

  @Test
  public void writeAndReadFromArray() {
    ByteDataBuffer buffer = allocate(10L);
    byte[] oneToFive = new byte[]{ 1, 2, 3, 4, 5 };

    buffer.write(oneToFive);
    assertEquals(2, buffer.getByte(1));

    buffer.offset(5).write(oneToFive);
    assertEquals(2, buffer.getByte(1));
    assertEquals(2, buffer.getByte(6));

    byte[] read = new byte[5];
    buffer.read(read);
    assertArrayEquals(oneToFive, read);

    buffer.write(oneToFive, 2, 2);
    assertEquals(3, buffer.getByte(0));
    assertEquals(4, buffer.getByte(1));
    assertEquals(3, buffer.getByte(2));

    Arrays.fill(read, valueOf(0L));
    buffer.read(read, 1, 2);
    assertEquals(0, read[0]);
    assertEquals(3, read[1]);
    assertEquals(4, read[2]);
    assertEquals(0, read[3]);
  }
}
