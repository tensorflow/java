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

public abstract class DoubleDataBufferTestBase extends DataBufferTestBase<Double> {

  @Override
  protected abstract DoubleDataBuffer allocate(long capacity);

  @Override
  protected Double valueOf(Long val) {
    return val.doubleValue();
  }

  @Test
  public void writeAndReadFromArray() {
    DoubleDataBuffer buffer = allocate(10L);
    double[] oneToFive = new double[]{valueOf(1L), valueOf(2L), valueOf(3L), valueOf(4L),
        valueOf(5L)};

    buffer.put(oneToFive);
    assertEquals(valueOf(2L), buffer.get(1));
    assertEquals(5L, buffer.position());

    buffer.put(oneToFive);
    assertEquals(valueOf(2L), buffer.get(6));
    assertEquals(10L, buffer.position());

    buffer.rewind();
    double[] read = new double[5];
    buffer.get(read);
    assertArrayEquals(oneToFive, read, 0.0);
    assertEquals(5L, buffer.position());

    buffer.rewind();
    buffer.put(oneToFive, 2, 2);
    assertEquals(valueOf(3L), buffer.get(0));
    assertEquals(valueOf(4L), buffer.get(1));
    assertEquals(valueOf(3L), buffer.get(2));
    assertEquals(2L, buffer.position());

    Arrays.fill(read, valueOf(0L));
    buffer.get(read, 1, 2);
    assertEquals(valueOf(0L), (Double) read[0]);
    assertEquals(valueOf(3L), (Double) read[1]);
    assertEquals(valueOf(4L), (Double) read[2]);
    assertEquals(valueOf(0L), (Double) read[3]);
    assertEquals(4L, buffer.position());
  }
}
