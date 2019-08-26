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

package org.tensorflow.nio.buffer.single;

import java.nio.FloatBuffer;
import java.util.stream.Stream;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.FloatDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;

/**
 * A buffer of bytes using a JDK {@link FloatBuffer} for storage.
 * <p>
 * Since JDK buffers supports only 32-bits indexation, the capacity of this buffer type cannot exceed
 * 2<sup>32</sup> - 1 (see {@link FloatJdkDataBuffer.MAX_CAPACITY} for the real maximum value supported).
 */
public final class FloatJdkDataBuffer extends AbstractJdkDataBuffer<Float, FloatDataBuffer> implements FloatDataBuffer {

  /**
   * The maximum capacity for a buffer of this type, i.e. the maximum number of bytes it can store.
   * <p>
   * As the maximum capacity may vary depending on the JVM implementation and on the platform, this property returns
   * a value that is safe for most of them.
   */
  public static long MAX_CAPACITY = AbstractJdkDataBuffer.MAX_CAPACITY;

  /**
   * Allocates a new byte buffer.
   * <p>
   * The new buffer's position will be zero, its limit will be its capacity, and each of its elements will be initialized to zero.
   *
   * @param capacity the new buffer's capacity, in bytes
   * @return the new byte buffer
   * @throws IllegalArgumentException if the capacity is a negative integer or exceeds {@link MAX_CAPACITY}.
   */
  public static FloatDataBuffer allocate(long capacity) {
    if (capacity > MAX_CAPACITY) {
      throw new IllegalArgumentException("Capacity of a JDK data buffer cannot exceeds " + MAX_CAPACITY +
          " bytes, use FloatLargeDataBuffer instead");
    }
    return new FloatJdkDataBuffer(FloatBuffer.allocate((int)capacity));
  }

  /**
   * Wraps a JDK {@link FloatBuffer} into a {@code FloatDataBuffer}.
   *
   * The new buffer's position, limit and capacity will be the one of the buf passed in parameter, and each of its elements will
   * preserver their values.
   *
   * @param buffer buffer to wrap
   * @return the new byte buffer
   */
  public static FloatDataBuffer wrap(FloatBuffer buffer) {
    return new FloatJdkDataBuffer(buffer);
  }

  @Override
  public Float get() {
    return buf.get();
  }

  @Override
  public Float get(long index) {
    return buf.get((int)index);
  }

  @Override
  public FloatDataBuffer get(float[] dst, int offset, int length) {
    buf.get(dst, offset, length);
    return this;
  }

  @Override
  public Stream<Float> stream() {
    throw new UnsupportedOperationException("FloatDataBuffer does not support value streaming at the moment");
  }

  @Override
  public FloatDataBuffer put(Float value) {
    buf.put(value);
    return this;
  }

  @Override
  public FloatDataBuffer put(long index, Float value) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public FloatDataBuffer put(float[] src, int offset, int length) {
    buf.put(src, offset, length);
    return this;
  }

  @Override
  public FloatDataBuffer put(DataBuffer<Float> src) {
    if (src instanceof FloatJdkDataBuffer) {
      buf.put(((FloatJdkDataBuffer)src).buf);
      return this;
    }
    return super.put(src);
  }

  @Override
  public FloatDataBuffer duplicate() {
    return new FloatJdkDataBuffer(buf.duplicate());
  }

  @Override
  public FloatDataBuffer slice() {
    return new FloatJdkDataBuffer(buf.slice());
  }

  @Override
  protected FloatBuffer buf() {
    return buf;
  }

  private FloatJdkDataBuffer(FloatBuffer buf) {
    this.buf = buf;
  }
  
  private FloatBuffer buf;
}
