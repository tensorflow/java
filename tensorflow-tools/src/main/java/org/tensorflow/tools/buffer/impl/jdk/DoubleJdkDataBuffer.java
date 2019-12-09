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

import java.nio.DoubleBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

/**
 * A buffer of bytes using a JDK {@link DoubleBuffer} for storage.
 * <p>
 * Since JDK buffers supports only 32-bits indexation, the size of this buffer type cannot
 * exceed 2<sup>32</sup> - 1 (see {@link DoubleJdkDataBuffer#MAX_SIZE} for the real maximum
 * value supported).
 */
public final class DoubleJdkDataBuffer extends AbstractJdkDataBuffer<Double>
    implements DoubleDataBuffer {

  /**
   * Allocates a new byte buffer, initialized with zeroes.
   *
   * @param size the new buffer's size, in bytes
   * @return the new byte buffer
   * @throws IllegalArgumentException if the size is a negative integer or exceeds
   *                                  {@link #MAX_SIZE}.
   */
  public static DoubleDataBuffer allocate(long size) {
    Validator.createArgs(size, MAX_SIZE);
    return new DoubleJdkDataBuffer(DoubleBuffer.allocate((int)size));
  }

  /**
   * Wraps a JDK {@link DoubleBuffer} into a {@code DoubleDataBuffer}.
   *
   * @param buffer buffer to wrap
   * @return the new byte buffer
   */
  public static DoubleDataBuffer wrap(DoubleBuffer buffer) {
    return new DoubleJdkDataBuffer(buffer);
  }

  @Override
  public double getDouble(long index) {
    return buf.get((int)index);
  }

  @Override
  public DoubleDataBuffer setDouble(double value, long index) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public DoubleDataBuffer read(double[] dst, int offset, int length) {
    buf.duplicate().get(dst, offset, length);
    return this;
  }

  @Override
  public DoubleDataBuffer write(double[] src, int offset, int length) {
    buf.duplicate().put(src, offset, length);
    return this;
  }

  @Override
  public DoubleDataBuffer copyTo(DataBuffer<Double> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof DoubleJdkDataBuffer) {
      DoubleBuffer dstBuf = ((DoubleJdkDataBuffer)dst).buf.duplicate();
      DoubleBuffer srcBuf = (DoubleBuffer)buf.duplicate().limit((int)size);
      dstBuf.put(srcBuf);
    } else {
      slowCopyTo(dst, size);
    }
    return this;
  }

  @Override
  public DoubleDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new DoubleJdkDataBuffer(((DoubleBuffer)buf.duplicate().position((int)index)).slice());
  }

  @Override
  public DoubleDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new DoubleJdkDataBuffer(((DoubleBuffer)buf.duplicate().limit((int)size)).slice());
  }

  @Override
  DoubleBuffer buf() {
    return buf;
  }

  private DoubleJdkDataBuffer(DoubleBuffer buf) {
    this.buf = buf;
  }
  
  private DoubleBuffer buf;
}
