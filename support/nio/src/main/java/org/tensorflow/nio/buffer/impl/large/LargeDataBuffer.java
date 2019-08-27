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
package org.tensorflow.nio.buffer.impl.large;

import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.impl.single.ArrayDataBuffer;

public final class LargeDataBuffer<T> extends AbstractLargeDataBuffer<T, DataBuffer<T>> {

  public static long MAX_CAPACITY = ArrayDataBuffer.MAX_CAPACITY * ArrayDataBuffer.MAX_CAPACITY;

  public static <T> LargeDataBuffer<T> allocate(Class<T> clazz, long capacity) {
    if (capacity > MAX_CAPACITY) {
      throw new IllegalArgumentException("Capacity for a joined data buffer cannot exceeds " + MAX_CAPACITY + " elements");
    }
    @SuppressWarnings("unchecked")
    DataBuffer<T>[] buffers = allocateBuffers(DataBuffer.class, capacity, ArrayDataBuffer.MAX_CAPACITY, (c) -> ArrayDataBuffer.allocate(clazz, c));
    return new LargeDataBuffer<>(buffers, false);
  }

  public static <T> LargeDataBuffer<T> join(DataBuffer<T>... buffers) {
    boolean readOnly = Validator.joinBuffers(buffers);
    return new LargeDataBuffer<>(buffers, readOnly);
  }

  @Override
  protected LargeDataBuffer<T> instantiate(DataBuffer<T>[] buffers, boolean readOnly, long capacity, long limit, int currentBufferIndex) {
    return new LargeDataBuffer<>(buffers, readOnly, capacity, limit, currentBufferIndex);
  }

  private LargeDataBuffer(DataBuffer<T>[] buffers, boolean readOnly) {
    super(buffers, readOnly);
  }

  private LargeDataBuffer(DataBuffer<T>[] buffers, boolean readOnly, long capacity, long limit, int currentBufferIndex) {
    super(buffers, readOnly, capacity, limit, currentBufferIndex);
  }
}
