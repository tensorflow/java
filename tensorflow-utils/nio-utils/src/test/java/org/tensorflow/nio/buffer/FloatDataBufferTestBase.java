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

public abstract class FloatDataBufferTestBase extends DataBufferTestBase<Float> {

  @Override
  protected abstract FloatDataBuffer allocate(long size);

  @Override
  protected Float valueOf(Long val) {
    return val.floatValue();
  }

  @Test
  public void writeAndReadFromArray() {
    FloatDataBuffer buffer = allocate(10L);
    float[] oneToFive = new float[]{ 1.0f, 2.0f, 3.0f, 4.0f, 5.0f };

    buffer.write(oneToFive);
    assertEquals(2.0f, buffer.getFloat(1), 0.0f);

    buffer.offset(5).write(oneToFive);
    assertEquals(2.0f, buffer.getFloat(1), 0.0f);
    assertEquals(2.0f, buffer.getFloat(6), 0.0f);

    float[] read = new float[5];
    buffer.read(read);
    assertArrayEquals(oneToFive, read, 0.0f);

    buffer.write(oneToFive, 2, 2);
    assertEquals(3.0f, buffer.getFloat(0), 0.0f);
    assertEquals(4.0f, buffer.getFloat(1), 0.0f);
    assertEquals(3.0f, buffer.getFloat(2), 0.0f);

    Arrays.fill(read, valueOf(0L));
    buffer.read(read, 1, 2);
    assertEquals(0.0f, read[0], 0.0f);
    assertEquals(3.0f, read[1], 0.0f);
    assertEquals(4.0f, read[2], 0.0f);
    assertEquals(0.0f, read[3], 0.0f);
  }
}
