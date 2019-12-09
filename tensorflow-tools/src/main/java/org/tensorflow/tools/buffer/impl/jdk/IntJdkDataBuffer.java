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

package org.tensorflow.tools.buffer.impl.jdk;

import java.nio.IntBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

/**
 * A buffer of bytes using a JDK {@link IntBuffer} for storage.
 * <p>
 * Since JDK buffers supports only 32-bits indexation, the size of this buffer type cannot
 * exceed 2<sup>32</sup> - 1 (see {@link IntJdkDataBuffer#MAX_SIZE} for the real maximum
 * value supported).
 */
public final class IntJdkDataBuffer extends AbstractJdkDataBuffer<Integer>
    implements IntDataBuffer {

  /**
   * Allocates a new byte buffer, initialized with zeroes.
   *
   * @param size the new buffer's size, in bytes
   * @return the new byte buffer
   * @throws IllegalArgumentException if the size is a negative integer or exceeds
   *                                  {@link #MAX_SIZE}.
   */
  public static IntDataBuffer allocate(long size) {
    Validator.createArgs(size, MAX_SIZE);
    return new IntJdkDataBuffer(IntBuffer.allocate((int)size));
  }

  /**
   * Wraps a JDK {@link IntBuffer} into a {@code IntDataBuffer}.
   *
   * @param buffer buffer to wrap
   * @return the new byte buffer
   */
  public static IntDataBuffer wrap(IntBuffer buffer) {
    return new IntJdkDataBuffer(buffer);
  }

  @Override
  public int getInt(long index) {
    return buf.get((int)index);
  }

  @Override
  public IntDataBuffer setInt(int value, long index) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public IntDataBuffer read(int[] dst, int offset, int length) {
    buf.duplicate().get(dst, offset, length);
    return this;
  }

  @Override
  public IntDataBuffer write(int[] src, int offset, int length) {
    buf.duplicate().put(src, offset, length);
    return this;
  }

  @Override
  public IntDataBuffer copyTo(DataBuffer<Integer> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof IntJdkDataBuffer) {
      IntBuffer dstBuf = ((IntJdkDataBuffer)dst).buf.duplicate();
      IntBuffer srcBuf = (IntBuffer)buf.duplicate().limit((int)size);
      dstBuf.put(srcBuf);
    } else {
      slowCopyTo(dst, size);
    }
    return this;
  }

  @Override
  public IntDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new IntJdkDataBuffer(((IntBuffer)buf.duplicate().position((int)index)).slice());
  }

  @Override
  public IntDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new IntJdkDataBuffer(((IntBuffer)buf.duplicate().limit((int)size)).slice());
  }

  @Override
  IntBuffer buf() {
    return buf;
  }

  private IntJdkDataBuffer(IntBuffer buf) {
    this.buf = buf;
  }

  private IntBuffer buf;
}
