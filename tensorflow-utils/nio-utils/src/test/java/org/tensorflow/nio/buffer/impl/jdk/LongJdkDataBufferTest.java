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
package org.tensorflow.nio.buffer.impl.jdk;

import org.tensorflow.nio.buffer.LongDataBuffer;
import org.tensorflow.nio.buffer.LongDataBufferTestBase;

public class LongJdkDataBufferTest extends LongDataBufferTestBase {

  @Override
  protected long maxSize() {
    return LongJdkDataBuffer.MAX_SIZE;
  }

  @Override
  protected LongDataBuffer allocate(long size) {
    return LongJdkDataBuffer.allocate(size);
  }
}
