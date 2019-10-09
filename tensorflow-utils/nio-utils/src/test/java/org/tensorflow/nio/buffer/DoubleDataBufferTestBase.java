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
  protected abstract DoubleDataBuffer allocate(long size);

  @Override
  protected Double valueOf(Long val) {
    return val.doubleValue();
  }

  @Test
  public void writeAndReadFromArray() {
    DoubleDataBuffer buffer = allocate(10L);
    double[] oneToFive = new double[]{ 1.0, 2.0, 3.0, 4.0, 5.0 };

    buffer.write(oneToFive);
    assertEquals(2.0, buffer.getDouble(1), 0.0);

    buffer.offset(5).write(oneToFive);
    assertEquals(2.0, buffer.getDouble(1), 0.0);
    assertEquals(2.0, buffer.getDouble(6), 0.0);

    double[] read = new double[5];
    buffer.read(read);
    assertArrayEquals(oneToFive, read, 0.0);

    buffer.write(oneToFive, 2, 2);
    assertEquals(3.0, buffer.getDouble(0), 0.0);
    assertEquals(4.0, buffer.getDouble(1), 0.0);
    assertEquals(3.0, buffer.getDouble(2), 0.0);

    Arrays.fill(read, valueOf(0L));
    buffer.read(read, 1, 2);
    assertEquals(0.0, read[0], 0.0);
    assertEquals(3.0, read[1], 0.0);
    assertEquals(4.0, read[2], 0.0);
    assertEquals(0.0, read[3], 0.0);
  }
}
