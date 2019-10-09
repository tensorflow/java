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

import org.tensorflow.nio.buffer.BooleanDataBuffer;
import org.tensorflow.nio.buffer.BooleanDataBufferTestBase;

public class BitSetDataBufferTest extends BooleanDataBufferTestBase {

  @Override
  protected long maxSize() {
    return BitSetDataBuffer.MAX_CAPACITY;
  }

  @Override
  protected BooleanDataBuffer allocate(long size) {
    return BitSetDataBuffer.allocate(size);
  }

  @Override
  protected Boolean valueOf(Long val) {
    return val != 0;
  }
}
