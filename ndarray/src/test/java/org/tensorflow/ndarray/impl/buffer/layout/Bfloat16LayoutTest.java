/*
 *  Copyright 2020 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */

package org.tensorflow.ndarray.impl.buffer.layout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Bfloat16LayoutTest {

  @Test
  public void testFloat32to16() {

    // Zero and subnormals
    assertEquals((short)0x0000, Bfloat16Layout.float32to16(0.0f));
    assertEquals((short)0x8000, Bfloat16Layout.float32to16(-0.0f));
    assertEquals((short)0x0001, Bfloat16Layout.float32to16(1e-40f));
    assertEquals((short)0xC000, Bfloat16Layout.float32to16(-2.0f));
    assertEquals((short)0x0000, Bfloat16Layout.float32to16(4.59e-41f));

    // Infinite and NaN
    assertEquals((short)0x7F80, Bfloat16Layout.float32to16(Float.POSITIVE_INFINITY));
    assertEquals((short)0xFF80, Bfloat16Layout.float32to16(Float.NEGATIVE_INFINITY));
    assertEquals((short)0x7FC0, Bfloat16Layout.float32to16(Float.NaN));
    assertEquals((short)0x7FC0, Bfloat16Layout.float32to16(Float.intBitsToFloat(0xFFFFFFFF)));

    // Normalized
    assertEquals((short)0x3F80, Bfloat16Layout.float32to16(1.0f));
    assertEquals((short)0xBF80, Bfloat16Layout.float32to16(-1.0f));
    assertEquals((short)0x42C8, Bfloat16Layout.float32to16(100.0f));
    assertEquals((short)0xC2CA, Bfloat16Layout.float32to16(-101.0f));
    assertEquals((short)0x3F8F, Bfloat16Layout.float32to16(1.1171875f));
    assertEquals((short)0x4800, Bfloat16Layout.float32to16(131072f));
    assertEquals((short)0x7F7F, Bfloat16Layout.float32to16(3.3895314e38f));
    assertEquals((short)0xFF7F, Bfloat16Layout.float32to16(-3.3895314e38f));

    // Rounding up
    assertEquals((short)0x3FCF, Bfloat16Layout.float32to16(1.6191406f));   // 1.6171875
    assertEquals((short)0x4780, Bfloat16Layout.float32to16(65600.0f));   // 65536.0
  }

  @Test
  public void testFloat16to32() {

    // Zero and subnormals
    assertEquals(0.0f, Bfloat16Layout.float16to32((short)0x0000), 0);
    assertEquals(-0.0f, Bfloat16Layout.float16to32((short)0x8000), 0);
    assertEquals(9.18355E-41f, Bfloat16Layout.float16to32((short)0x0001), 1e-8f);
    assertEquals(-9.403955E-38, Bfloat16Layout.float16to32((short)0x8200), 1e-8f);

    // Infinite and NaN
    assertEquals(Float.POSITIVE_INFINITY, Bfloat16Layout.float16to32((short)0x7F80), 0);
    assertEquals(Float.NEGATIVE_INFINITY, Bfloat16Layout.float16to32((short)0xFF80), 0);
    assertEquals(Float.NaN, Bfloat16Layout.float16to32((short)0x7FC0), 0);
    assertEquals(Float.intBitsToFloat(0xFFFFFFFF), Bfloat16Layout.float16to32((short)0x7FC0), 0);

    // Normalized
    assertEquals(1.0f, Bfloat16Layout.float16to32((short)0x3F80), 0);
    assertEquals(-1.0f, Bfloat16Layout.float16to32((short)0xBF80), 0);
    assertEquals(100.0f, Bfloat16Layout.float16to32((short)0x42C8), 0);
    assertEquals(-101.0f, Bfloat16Layout.float16to32((short)0xC2CA), 0);
    assertEquals(1.1171875f, Bfloat16Layout.float16to32((short)0x3F8F), 0);
    assertEquals(131072f, Bfloat16Layout.float16to32((short)0x4800), 0);
    assertEquals(3.3895314e38f, Bfloat16Layout.float16to32((short)0x7F7F), 0);
    assertEquals(-3.3895314e38f, Bfloat16Layout.float16to32((short)0xFF7F), 0);
    assertEquals(1.6171875f, Bfloat16Layout.float16to32((short)0x3FCF), 0);
    assertEquals(65536.0, Bfloat16Layout.float16to32((short)0x4780), 0);
  }
}
