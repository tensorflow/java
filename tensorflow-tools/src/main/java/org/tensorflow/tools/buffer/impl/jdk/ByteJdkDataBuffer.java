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

import java.nio.ByteBuffer;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

/**
 * A buffer of bytes using a JDK {@link ByteBuffer} for storage.
 * <p>
 * Since JDK buffers supports only 32-bits indexing, the size of this buffer type cannot
 * exceed 2<sup>32</sup> - 1 (see {@link ByteJdkDataBuffer#MAX_SIZE} for the real maximum
 * value supported).
 */
public final class ByteJdkDataBuffer extends AbstractJdkDataBuffer<Byte>
    implements ByteDataBuffer {

  /**
   * Allocates a new byte buffer, initialized with zeroes.
   *
   * @param size the new buffer's size, in bytes
   * @return the new byte buffer
   * @throws IllegalArgumentException if the size is a negative integer or exceeds
   *                                  {@link #MAX_SIZE}.
   */
  public static ByteDataBuffer allocate(long size) {
    Validator.createArgs(size, MAX_SIZE);
    return new ByteJdkDataBuffer(ByteBuffer.allocate((int)size));
  }

  /**
   * Wraps a JDK {@link ByteBuffer} into a {@code ByteDataBuffer}.
   *
   * @param buffer buffer to wrap
   * @return the new byte buffer
   */
  public static ByteDataBuffer wrap(ByteBuffer buffer) {
    return new ByteJdkDataBuffer(buffer);
  }

  @Override
  public byte getByte(long index) {
    return buf.get((int)index);
  }

  @Override
  public ByteDataBuffer setByte(byte value, long index) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public ByteDataBuffer read(byte[] dst, int offset, int length) {
    buf.duplicate().get(dst, offset, length);
    return this;
  }

  @Override
  public ByteDataBuffer write(byte[] src, int offset, int length) {
    buf.duplicate().put(src, offset, length);
    return this;
  }

  @Override
  public ByteDataBuffer copyTo(DataBuffer<Byte> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof ByteJdkDataBuffer) {
      ByteBuffer dstBuf = ((ByteJdkDataBuffer)dst).buf.duplicate();
      ByteBuffer srcBuf = (ByteBuffer)buf.duplicate().limit((int)size);
      dstBuf.put(srcBuf);
    } else {
      slowCopyTo(dst, size);
    }
    return this;
  }

  @Override
  public ByteDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new ByteJdkDataBuffer(((ByteBuffer)buf.duplicate().position((int)index)).slice());
  }

  @Override
  public ByteDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new ByteJdkDataBuffer(((ByteBuffer)buf.duplicate().limit((int)size)).slice());
  }

  @Override
  ByteBuffer buf() {
    return buf;
  }

  private ByteJdkDataBuffer(ByteBuffer buf) {
    this.buf = buf;
  }

  private ByteBuffer buf;
}
