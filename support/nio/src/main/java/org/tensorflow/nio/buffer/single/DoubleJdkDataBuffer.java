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

import java.nio.DoubleBuffer;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.DoubleDataBuffer;

/**
 * A buffer of bytes using a JDK {@link DoubleBuffer} for storage.
 * <p>
 * Since JDK buffers supports only 32-bits indexation, the capacity of this buffer type cannot exceed
 * 2<sup>32</sup> - 1 (see {@link DoubleJdkDataBuffer.MAX_CAPACITY} for the real maximum value supported).
 */
public final class DoubleJdkDataBuffer extends AbstractJdkDataBuffer<Double, DoubleDataBuffer> implements DoubleDataBuffer {

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
  public static DoubleDataBuffer allocate(long capacity) {
    if (capacity > MAX_CAPACITY) {
      throw new IllegalArgumentException("Capacity of a JDK data buffer cannot exceeds " + MAX_CAPACITY +
          " bytes, use DoubleLargeDataBuffer instead");
    }
    return new DoubleJdkDataBuffer(DoubleBuffer.allocate((int)capacity));
  }

  /**
   * Wraps a JDK {@link DoubleBuffer} into a {@code DoubleDataBuffer}.
   *
   * The new buffer's position, limit and capacity will be the one of the buf passed in parameter, and each of its elements will
   * preserver their values.
   *
   * @param buffer buffer to wrap
   * @return the new byte buffer
   */
  public static DoubleDataBuffer wrap(DoubleBuffer buffer) {
    return new DoubleJdkDataBuffer(buffer);
  }

  @Override
  public Double get() {
    return buf.get();
  }

  @Override
  public Double get(long index) {
    return buf.get((int)index);
  }

  @Override
  public DoubleDataBuffer get(double[] dst, int offset, int length) {
    buf.get(dst, offset, length);
    return this;
  }

  @Override
  public DoubleStream doubleStream() {
    if (!buf.hasArray()) {
      throw new UnsupportedOperationException("Only buffers backed by an array supports streaming");
    }
    return Arrays.stream(buf.array());
  }

  @Override
  public Stream<Double> stream() {
    return doubleStream().boxed();
  }

  @Override
  public DoubleDataBuffer put(Double value) {
    buf.put(value);
    return this;
  }

  @Override
  public DoubleDataBuffer put(long index, Double value) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public DoubleDataBuffer put(double[] src, int offset, int length) {
    buf.put(src, offset, length);
    return this;
  }

  @Override
  public DoubleDataBuffer put(DataBuffer<Double> src) {
    if (src instanceof DoubleJdkDataBuffer) {
      buf.put(((DoubleJdkDataBuffer)src).buf);
      return this;
    }
    return super.put(src);
  }

  @Override
  public DoubleDataBuffer duplicate() {
    return new DoubleJdkDataBuffer(buf.duplicate());
  }

  @Override
  public DoubleDataBuffer slice() {
    return new DoubleJdkDataBuffer(buf.slice());
  }

  @Override
  protected DoubleBuffer buf() {
    return buf;
  }

  private DoubleJdkDataBuffer(DoubleBuffer buf) {
    this.buf = buf;
  }
  
  private DoubleBuffer buf;
}
