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

package org.tensorflow.nio.buffer.impl.single;

import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.IntDataBuffer;

/**
 * A buffer of bytes using a JDK {@link IntBuffer} for storage.
 * <p>
 * Since JDK buffers supports only 32-bits indexation, the capacity of this buffer type cannot exceed
 * 2<sup>32</sup> - 1 (see {@link IntJdkDataBuffer.MAX_CAPACITY} for the real maximum value supported).
 */
public final class IntJdkDataBuffer extends AbstractJdkDataBuffer<Integer, IntDataBuffer> implements IntDataBuffer {

  /**
   * The maximum capacity for a buffer of this type, i.e. the maximum number of integers it can store.
   * <p>
   * As the maximum capacity may vary depending on the JVM implementation and on the platform, this property returns
   * a value that is safe for most of them.
   */
  public static long MAX_CAPACITY = AbstractJdkDataBuffer.MAX_CAPACITY;
  
  /**
   * Allocates a new integer buffer.
   * <p>
   * The new buffer's position will be zero, its limit will be its capacity, and each of its elements will be initialized to zero. 
   * 
   * @param capacity the new buffer's capacity, in integers
   * @return the new integer buffer 
   * @throws IllegalArgumentException if the capacity is a negative integer or exceeds {@link MAX_CAPACITY}.
   */
  public static IntDataBuffer allocate(long capacity) {
    if (capacity > MAX_CAPACITY) {
      throw new IllegalArgumentException("Capacity of a JDK data buffer cannot exceeds " + MAX_CAPACITY + 
          " integers, use IntLargeDataBuffer instead");
    }
    return new IntJdkDataBuffer(IntBuffer.allocate((int)capacity));
  }

  /**
   * Wraps a JDK {@link IntBuffer} into a {@code IntDataBuffer}.
   * 
   * The new buffer's position, limit and capacity will be the one of the buf passed in parameter, and each of its elements will 
   * preserver their values.
   * 
   * @param buffer buffer to wrap
   * @return the new integer buffer
   */
  public static IntDataBuffer wrap(IntBuffer buf) {
    return new IntJdkDataBuffer(buf);
  }

  @Override
  public Integer get() {
    return buf.get();
  }

  @Override
  public Integer get(long index) {
    return buf.get((int)index);
  }

  @Override
  public IntDataBuffer get(int[] dst, int offset, int length) {
    buf.get(dst, offset, length);
    return this;
  }

  @Override
  public IntDataBuffer put(Integer value) {
    buf.put(value);
    return this;
  }

  @Override
  public IntDataBuffer put(long index, Integer value) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public IntDataBuffer put(int[] src, int offset, int length) {
    buf.put(src, offset, length);
    return this;
  }

  @Override
  public IntDataBuffer put(DataBuffer<Integer> src) {
    if (src instanceof IntJdkDataBuffer) {
      buf.put(((IntJdkDataBuffer)src).buf);
      return this;
    }
    return super.put(src);
  }

  @Override
  public IntStream intStream() {
    if (!buf.hasArray()) {
      throw new UnsupportedOperationException("Only buffers backed by an array supports streaming");
    }
    return Arrays.stream(buf.array());
  }

  @Override
  public Stream<Integer> stream() {
    if (!buf.hasArray()) {
      throw new UnsupportedOperationException("Only buffers backed by an array supports streaming");
    }
    return Arrays.stream(buf.array()).boxed();
  }

  @Override
  public IntDataBuffer duplicate() {
    return new IntJdkDataBuffer(buf.duplicate());
  }

  @Override
  public IntDataBuffer slice() {
    return new IntJdkDataBuffer(buf.slice());
  }

  @Override
  protected IntBuffer buf() {
    return buf;
  }

  private IntJdkDataBuffer(IntBuffer buf) {
    this.buf = buf;
  }
  
  private IntBuffer buf;
}
