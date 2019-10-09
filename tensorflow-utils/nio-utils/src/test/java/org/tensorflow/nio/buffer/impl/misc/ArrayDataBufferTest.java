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
package org.tensorflow.nio.buffer.impl.misc;

import java.math.BigDecimal;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.DataBufferTestBase;

public class ArrayDataBufferTest extends DataBufferTestBase<BigDecimal> {

  @Override
  protected long maxSize() {
    return ArrayDataBuffer.MAX_CAPACITY;
  }

  @Override
  protected DataBuffer<BigDecimal> allocate(long size) {
    return ArrayDataBuffer.allocate(BigDecimal.class, size);
  }

  @Override
  protected BigDecimal valueOf(Long val) {
    return BigDecimal.valueOf(val);
  }
}
