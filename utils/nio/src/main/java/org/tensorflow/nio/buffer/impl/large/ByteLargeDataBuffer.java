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

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.impl.single.ByteJdkDataBuffer;

public final class ByteLargeDataBuffer extends AbstractLargeDataBuffer<Byte, ByteDataBuffer> implements ByteDataBuffer {
  
  public static long MAX_CAPACITY = ByteJdkDataBuffer.MAX_CAPACITY * ByteJdkDataBuffer.MAX_CAPACITY;

  public static ByteDataBuffer allocate(long capacity) {
    if (capacity > MAX_CAPACITY) {
      throw new IllegalArgumentException("Capacity for a joined data buffer cannot exceeds " + MAX_CAPACITY + " bytes");
    }
    ByteDataBuffer[] buffers = allocateBuffers(ByteDataBuffer.class, capacity, ByteJdkDataBuffer.MAX_CAPACITY, ByteJdkDataBuffer::allocate);
    return new ByteLargeDataBuffer(buffers, false);
  }

  public static ByteDataBuffer join(ByteDataBuffer... buffers) {
    boolean readOnly = Validator.joinBuffers(buffers);
    return new ByteLargeDataBuffer(buffers, readOnly);
  }
 
  @Override
  public ByteDataBuffer get(byte[] dst, int offset, int length) {
    Validator.getArrayArgs(this, dst.length, offset, length);
    copyArray(offset, length, (b, o, l) -> ((ByteDataBuffer)b).get(dst, o, l));
    return this;
  }

  @Override
  public ByteDataBuffer put(byte[] src, int offset, int length) {
    Validator.putArrayArgs(this, src.length, offset, length);
    copyArray(offset, length, (b, o, l) -> ((ByteDataBuffer)b).put(src, o, l));
    return this;
  }

  @Override
  protected ByteLargeDataBuffer instantiate(ByteDataBuffer[] buffers, boolean readOnly, long capacity, long limit, int currentBufferIndex) {
    return new ByteLargeDataBuffer(buffers, readOnly, capacity, limit, currentBufferIndex);
  }

  private ByteLargeDataBuffer(ByteDataBuffer[] buffers, boolean readOnly) {
    super(buffers, readOnly);
  }

  private ByteLargeDataBuffer(ByteDataBuffer[] buffers, boolean readOnly, long capacity, long limit, int currentBufferIndex) {
    super(buffers, readOnly, capacity, limit, currentBufferIndex);
  }
}
