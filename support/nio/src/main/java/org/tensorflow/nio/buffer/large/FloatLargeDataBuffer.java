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

import org.tensorflow.nio.buffer.FloatDataBuffer;
import org.tensorflow.nio.buffer.single.FloatJdkDataBuffer;

public final class FloatLargeDataBuffer extends AbstractLargeDataBuffer<Float, FloatDataBuffer> implements FloatDataBuffer {

  public static long MAX_CAPACITY = FloatJdkDataBuffer.MAX_CAPACITY * FloatJdkDataBuffer.MAX_CAPACITY;

  public static FloatDataBuffer allocate(long capacity) {
    if (capacity > MAX_CAPACITY) {
      throw new IllegalArgumentException("Capacity for a joined data buffer cannot exceeds " + MAX_CAPACITY + " bytes");
    }
    FloatDataBuffer[] buffers = allocateBuffers(FloatDataBuffer.class, capacity, FloatJdkDataBuffer.MAX_CAPACITY, FloatJdkDataBuffer::allocate);
    return new FloatLargeDataBuffer(buffers, false);
  }

  public static FloatDataBuffer join(FloatDataBuffer... buffers) {
    boolean readOnly = Validator.joinBuffers(buffers);
    return new FloatLargeDataBuffer(buffers, readOnly);
  }

  @Override
  public FloatDataBuffer get(float[] dst, int offset, int length) {
    Validator.getArrayArgs(this, dst.length, offset, length);
    copyArray(offset, length, (b, o, l) -> ((FloatDataBuffer)b).get(dst, o, l));
    return this;
  }

  @Override
  public FloatDataBuffer put(float[] src, int offset, int length) {
    Validator.putArrayArgs(this, src.length, offset, length);
    copyArray(offset, length, (b, o, l) -> ((FloatDataBuffer)b).put(src, o, l));
    return this;
  }

  @Override
  protected FloatLargeDataBuffer instantiate(FloatDataBuffer[] buffers, boolean readOnly, long capacity, long limit, int currentBufferIndex) {
    return new FloatLargeDataBuffer(buffers, readOnly, capacity, limit, currentBufferIndex);
  }

  private FloatLargeDataBuffer(FloatDataBuffer[] buffers, boolean readOnly) {
    super(buffers, readOnly);
  }

  private FloatLargeDataBuffer(FloatDataBuffer[] buffers, boolean readOnly, long capacity, long limit, int currentBufferIndex) {
    super(buffers, readOnly, capacity, limit, currentBufferIndex);
  }
}
