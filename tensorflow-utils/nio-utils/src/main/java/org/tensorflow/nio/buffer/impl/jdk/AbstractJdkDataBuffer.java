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

import java.nio.Buffer;
import org.tensorflow.nio.buffer.impl.AbstractDataBuffer;

/**
 * Base class for all JDK-based data buffers.
 * 
 * @param <T> type of elements (or values) stored in this buffer
 */
abstract class AbstractJdkDataBuffer<T> extends AbstractDataBuffer<T> {

  /**
   * The maximum size for a buffer of this type, i.e. the maximum number of bytes it can store.
   * <p>
   * As the maximum size may vary depending on the JVM implementation and on the platform, this
   * property returns a value that is safe for most of them.
   */
  public static long MAX_SIZE = Integer.MAX_VALUE - 10;

  @Override
  public long size() {
    return buf().capacity();
  }

  @Override
  public boolean isReadOnly() {
    return buf().isReadOnly();
  }

  abstract Buffer buf();
}
