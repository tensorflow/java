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
package org.tensorflow.nio.buffer.large;

import java.util.stream.IntStream;

import org.tensorflow.nio.buffer.IntDataBuffer;
import org.tensorflow.nio.buffer.single.IntJdkDataBuffer;

public final class IntLargeDataBuffer extends AbstractLargeDataBuffer<Integer, IntDataBuffer> implements IntDataBuffer {
  
  public static long MAX_CAPACITY = IntJdkDataBuffer.MAX_CAPACITY << 1;

  public static IntDataBuffer allocate(long capacity) {
    if (capacity > MAX_CAPACITY) {
      throw new IllegalArgumentException("Capacity for a joined data buffer cannot exceeds " + MAX_CAPACITY + " bytes");
    }
    IntDataBuffer[] buffers = allocateBuffers(IntDataBuffer.class, capacity, IntJdkDataBuffer.MAX_CAPACITY, IntJdkDataBuffer::allocate);
    return new IntLargeDataBuffer(buffers, false);
  }

  public static IntDataBuffer join(IntDataBuffer... buffers) {
    boolean readOnly = Validator.joinBuffers(buffers);
    return new IntLargeDataBuffer(buffers, readOnly);
  }

  @Override
  public IntStream intStream() {
    IntStream stream = buffer(0).intStream();
    for (int i = 1; i < nbBuffers(); ++i) {
      stream = IntStream.concat(stream, buffer(i).intStream());
    }
    return stream;
  }
 
  @Override
  public IntDataBuffer get(int[] dst, int offset, int length) {
    Validator.getArrayArgs(this, dst.length, offset, length);
    copyArray(offset, length, (b, o, l) -> ((IntDataBuffer)b).get(dst, o, l));
    return this;
  }

  @Override
  public IntDataBuffer put(int[] src, int offset, int length) {
    Validator.putArrayArgs(this, src.length, offset, length);
    copyArray(offset, length, (b, o, l) -> ((IntDataBuffer)b).put(src, o, l));
    return this;
  }

  @Override
  protected IntLargeDataBuffer instantiate(IntDataBuffer[] buffers, boolean readOnly, long capacity, long limit, int currentBufferIndex) {
    return new IntLargeDataBuffer(buffers, readOnly, capacity, limit, currentBufferIndex);
  }

  private IntLargeDataBuffer(IntDataBuffer[] buffers, boolean readOnly) {
    super(buffers, readOnly);
  }

  private IntLargeDataBuffer(IntDataBuffer[] buffers, boolean readOnly, long capacity, long limit, int currentBufferIndex) {
    super(buffers, readOnly, capacity, limit, currentBufferIndex);
  }
}
