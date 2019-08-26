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

import java.nio.LongBuffer;
import java.util.Arrays;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.LongDataBuffer;

/**
 * A buffer of longs using a JDK {@link LongBuffer} for storage.
 * <p>
 * Since JDK buffers supports only 32-bits indexation, the capacity of this buffer type cannot exceed
 * 2<sup>32</sup> - 1 (see {@link IntJdkDataBuffer.MAX_CAPACITY} for the real maximum value supported).
 */
public final class LongJdkDataBuffer extends AbstractJdkDataBuffer<Long, LongDataBuffer> implements LongDataBuffer {

  /**
   * The maximum capacity for a buffer of this type, i.e. the maximum number of longs it can store.
   * <p>
   * As the maximum capacity may vary depending on the JVM implementation and on the platform, this property returns
   * a value that is safe for most of them.
   */
  public static long MAX_CAPACITY = AbstractJdkDataBuffer.MAX_CAPACITY;

  /**
   * Allocates a new long buffer.
   * <p>
   * The new buffer's position will be zero, its limit will be its capacity, and each of its elements will be initialized to zero. 
   * 
   * @param capacity the new buffer's capacity, in longs
   * @return the new long buffer 
   * @throws IllegalArgumentException if the capacity is a negative integer or exceeds {@link MAX_CAPACITY}.
   */
  public static LongDataBuffer allocate(long capacity) {
    if (capacity > MAX_CAPACITY) {
      throw new IllegalArgumentException("Capacity of a JDK data buffer cannot exceeds " + MAX_CAPACITY + 
          " longs, use LongLargeDataBuffer instead");
    }
    return new LongJdkDataBuffer(LongBuffer.allocate((int)capacity));
  }

  /**
   * Wraps a JDK {@link LongBuffer} into a {@code LongDataBuffer}.
   * 
   * The new buffer's position, limit and capacity will be the one of the buf passed in parameter, and each of its elements will 
   * preserver their values.
   * 
   * @param buffer buffer to wrap
   * @return the new long buffer
   */
  public static LongDataBuffer wrap(LongBuffer buf) {
    return new LongJdkDataBuffer(buf);
  }

  @Override
  public Long get() {
    return buf.get();
  }

  @Override
  public Long get(long index) {
    return buf.get((int)index);
  }

  @Override
  public LongDataBuffer get(long[] dst, int offset, int length) {
    buf.get(dst, offset, length);
    return this;
  }

  @Override
  public LongDataBuffer put(Long value) {
    buf.put(value);
    return this;
  }

  @Override
  public LongDataBuffer put(long index, Long value) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public LongDataBuffer put(long[] src, int offset, int length) {
    buf.put(src, offset, length);
    return this;
  }

  @Override
  public LongDataBuffer put(DataBuffer<Long> src) {
    if (src instanceof LongJdkDataBuffer) {
      buf.put(((LongJdkDataBuffer)src).buf);
      return this;
    }
    return super.put(src);
  }

  @Override
  public LongStream longStream() {
    if (!buf.hasArray()) {
      throw new UnsupportedOperationException("Only buffers backed by an array supports streaming");
    }
    return Arrays.stream(buf.array());
  }

  @Override
  public Stream<Long> stream() {
    return longStream().boxed();
  }

  @Override
  public LongDataBuffer duplicate() {
    return new LongJdkDataBuffer(buf.duplicate());
  }

  @Override
  public LongDataBuffer slice() {
    return new LongJdkDataBuffer(buf.slice());
  }

  @Override
  protected LongBuffer buf() {
    return buf;
  }

  private LongJdkDataBuffer(LongBuffer buf) {
    this.buf = buf;
  }
  
  private LongBuffer buf;
}
