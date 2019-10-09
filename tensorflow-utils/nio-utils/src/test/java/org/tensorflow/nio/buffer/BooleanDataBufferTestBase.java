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

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import org.junit.Test;

public abstract class BooleanDataBufferTestBase extends DataBufferTestBase<Boolean> {

  @Override
  protected abstract BooleanDataBuffer allocate(long size);

  @Override
  protected Boolean valueOf(Long val) {
    return val != 0;
  }

  @Test
  public void writeAndReadFromArray() {
    BooleanDataBuffer buffer = allocate(10L);
    boolean[] values = new boolean[]{true, false, false, true, false};

    buffer.write(values);
    assertTrue(buffer.get(0));
    assertFalse(buffer.get(1));

    buffer.offset(5).write(values);
    assertTrue(buffer.get(5));

    boolean[] read = new boolean[5];
    buffer.read(read);
    assertArrayEquals(values, read);

    buffer.write(values, 2, 3);
    assertFalse(buffer.get(0));
    assertTrue(buffer.get(1));
    assertFalse(buffer.get(2));

    Arrays.fill(read, false);
    buffer.read(read, 1, 2);
    assertFalse(read[0]);
    assertFalse(read[1]);
    assertTrue(read[2]);
    assertFalse(read[3]);
  }
}
