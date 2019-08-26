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

import java.nio.ByteBuffer;
import java.util.stream.Stream;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;

/**
 * A buffer of bytes using a JDK {@link ByteBuffer} for storage.
 * <p>
 * Since JDK buffers supports only 32-bits indexation, the capacity of this buffer type cannot exceed
 * 2<sup>32</sup> - 1 (see {@link ByteJdkDataBuffer.MAX_CAPACITY} for the real maximum value supported).
 */
public final class ByteJdkDataBuffer extends AbstractJdkDataBuffer<Byte, ByteDataBuffer> implements ByteDataBuffer {
  
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
  public static ByteDataBuffer allocate(long capacity) {
    if (capacity > MAX_CAPACITY) {
      throw new IllegalArgumentException("Capacity of a JDK data buffer cannot exceeds " + MAX_CAPACITY + 
          " bytes, use ByteLargeDataBuffer instead");
    }
    return new ByteJdkDataBuffer(ByteBuffer.allocate((int)capacity));
  }

  /**
   * Allocates a new direct byte buffer.
   * <p>
   * The new buffer's position will be zero, its limit will be its capacity, and each of its elements will be initialized to zero. 
   * 
   * @param capacity the new buffer's capacity, in bytes
   * @return the new byte buffer 
   * @throws IllegalArgumentException if the capacity is a negative integer or exceeds {@link MAX_CAPACITY}.
   */
  public static ByteDataBuffer allocateDirect(long capacity) {
    if (capacity > MAX_CAPACITY) {
      throw new IllegalArgumentException("Capacity of a JDK data buffer cannot exceeds " + MAX_CAPACITY + 
          " bytes, use ByteLargeDataBuffer instead");
    }
    return new ByteJdkDataBuffer(ByteBuffer.allocateDirect((int)capacity));
  }

  /**
   * Wraps a JDK {@link ByteBuffer} into a {@code ByteDataBuffer}.
   * 
   * The new buffer's position, limit and capacity will be the one of the buf passed in parameter, and each of its elements will 
   * preserver their values.
   * 
   * @param buffer buffer to wrap
   * @return the new byte buffer
   */
  public static ByteDataBuffer wrap(ByteBuffer buffer) {
    return new ByteJdkDataBuffer(buffer);
  }
  
  @Override
  public Byte get() {
    return buf.get();
  }

  @Override
  public Byte get(long index) {
    return buf.get((int)index);
  }

  @Override
  public ByteDataBuffer get(byte[] dst, int offset, int length) {
    buf.get(dst, offset, length);
    return this;
  }

  @Override
  public Stream<Byte> stream() {
    throw new UnsupportedOperationException("ByteDataBuffer does not support value streaming at the moment");
  }

  @Override
  public ByteDataBuffer put(Byte value) {
    buf.put(value);
    return this;
  }

  @Override
  public ByteDataBuffer put(long index, Byte value) {
    buf.put((int)index, value);
    return this;
  }

  @Override
  public ByteDataBuffer put(byte[] src, int offset, int length) {
    buf.put(src, offset, length);
    return this;
  }

  @Override
  public ByteDataBuffer put(DataBuffer<Byte> src) {
    if (src instanceof ByteJdkDataBuffer) {
      buf.put(((ByteJdkDataBuffer)src).buf);
      return this;
    }
    return super.put(src);
  }

  @Override
  public ByteDataBuffer duplicate() {
    return new ByteJdkDataBuffer(buf.duplicate());
  }

  @Override
  public ByteDataBuffer slice() {
    return new ByteJdkDataBuffer(buf.slice());
  }

  @Override
  protected ByteBuffer buf() {
    return buf;
  }

  private ByteJdkDataBuffer(ByteBuffer buf) {
    this.buf = buf;
  }
  
  private ByteBuffer buf;
}
