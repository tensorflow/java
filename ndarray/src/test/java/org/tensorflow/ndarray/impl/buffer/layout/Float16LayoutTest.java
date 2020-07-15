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

public class Float16LayoutTest {

  @Test
  public void testFloat32to16() {

    // Zero and subnormals
    assertEquals((short)0x0000, Float16Layout.float32to16(0.0f));
    assertEquals((short)0x8000, Float16Layout.float32to16(-0.0f));
    assertEquals((short)0x0001, Float16Layout.float32to16(6e-8f));
    assertEquals((short)0x8200, Float16Layout.float32to16(-3.052e-5f));
    assertEquals((short)0x0000, Float16Layout.float32to16(6e-9f));

    // Infinite and NaN
    assertEquals((short)0x7C00, Float16Layout.float32to16(Float.POSITIVE_INFINITY));
    assertEquals((short)0xFC00, Float16Layout.float32to16(Float.NEGATIVE_INFINITY));
    assertEquals((short)0x7C00, Float16Layout.float32to16(65520.0f));
    assertEquals((short)0x7C00, Float16Layout.float32to16(165536.0f));
    assertEquals((short)0xFC00, Float16Layout.float32to16(-65520.0f));
    assertEquals((short)0x7E00, Float16Layout.float32to16(Float.NaN));
    assertEquals((short)0x7E00, Float16Layout.float32to16(Float.intBitsToFloat(0xFFFFFFFF)));

    // Normalized
    assertEquals((short)0x7BFF, Float16Layout.float32to16(65519.0f));
    assertEquals((short)0x3C00, Float16Layout.float32to16(1.0f));
    assertEquals((short)0xBC00, Float16Layout.float32to16(-1.0f));
    assertEquals((short)0x5640, Float16Layout.float32to16(100.0f));
    assertEquals((short)0xD650, Float16Layout.float32to16(-101.0f));
    assertEquals((short)0x3C7E, Float16Layout.float32to16(1.123f));

    // Rounding up
    assertEquals((short)0x3C7E, Float16Layout.float32to16(1.1235f));   // 1.123
    assertEquals((short)0x3C7F, Float16Layout.float32to16(1.1236f));   // 1.124
    assertEquals((short)0x4000, Float16Layout.float32to16(2.0009f));   // 2.0
    assertEquals((short)0x4001, Float16Layout.float32to16(2.001f));    // 2.002
    assertEquals((short)0x5C00, Float16Layout.float32to16(256.125f));  // 256.0
    assertEquals((short)0x5C01, Float16Layout.float32to16(256.126f));  // 256.3
    assertEquals((short)0x5C01, Float16Layout.float32to16(256.30f));   // 256.3
    assertEquals((short)0x5C01, Float16Layout.float32to16(256.374f));  // 256.3
    assertEquals((short)0x5C02, Float16Layout.float32to16(256.375f));  // 256.5
    assertEquals((short)0x5C02, Float16Layout.float32to16(256.51f));   // 256.5
  }

  @Test
  public void testFloat16to32() {

    // Zero and subnormals
    assertEquals(0.0f, Float16Layout.float16to32((short)0x0000), 0);
    assertEquals(-0.0f, Float16Layout.float16to32((short)0x8000), 0);
    assertEquals(6e-8f, Float16Layout.float16to32((short)0x0001), 1e-8f);
    assertEquals(-3.052e-5f, Float16Layout.float16to32((short)0x8200), 1e-8f);

    // Infinite and NaN
    assertEquals(Float.POSITIVE_INFINITY, Float16Layout.float16to32((short)0x7C00), 0);
    assertEquals(Float.NEGATIVE_INFINITY, Float16Layout.float16to32((short)0xFC00), 0);
    assertEquals(Float.NaN, Float16Layout.float16to32((short)0x7E00), 0);
    assertEquals(Float.intBitsToFloat(0xFFFFFFFF), Float16Layout.float16to32((short)0x7E00), 0);

    // Normalized
    assertEquals(1.0f, Float16Layout.float16to32((short)0x3C00), 1e-1f);
    assertEquals(-1.0f, Float16Layout.float16to32((short)0xBC00), 1e-1f);
    assertEquals(100.0f, Float16Layout.float16to32((short)0x5640), 1e-1f);
    assertEquals(-101.0f, Float16Layout.float16to32((short)0xD650), 1e-1f);
    assertEquals(1.123f, Float16Layout.float16to32((short)0x3C7E), 1e-3f);
    assertEquals(1.123f, Float16Layout.float16to32((short)0x3C7E), 1e-3f);
    assertEquals(-62.34f, Float16Layout.float16to32((short)0xD3CB), 1e-2f);
  }
}
