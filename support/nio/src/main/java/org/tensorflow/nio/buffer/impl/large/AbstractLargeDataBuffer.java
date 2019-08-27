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
package org.tensorflow.nio.buffer.impl.large;

import java.lang.reflect.Array;
import java.nio.ReadOnlyBufferException;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

import org.tensorflow.nio.buffer.impl.AbstractDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;

@SuppressWarnings("unchecked")
abstract class AbstractLargeDataBuffer<T, B extends DataBuffer<T>> extends AbstractDataBuffer<T, B> {

  @Override
  public long capacity() {
    return capacity;
  }

  @Override
  public long limit() {
    return limit;
  }

  @Override
  public B limit(long newLimit) {
    Validator.newLimit(this, newLimit);
    resetBuffers(newLimit, B::limit);
    if (newLimit < position()) {
      onPositionChange(newLimit);
    }
    limit = newLimit;
    return (B)this;
  }

  @Override
  public boolean hasRemaining() {
    return currentBuffer().hasRemaining();
  }

  @Override
  public long remaining() {
    return limit - position();
  }

  @Override
  public long position() {
    return (currentBufferIndex * bufferMaxCapacity) + currentBuffer().position();
  }

  @Override
  public B position(long newPosition) {
    Validator.newPosition(this, newPosition);
    resetBuffers(newPosition, B::position);
    onPositionChange(newPosition);
    return (B)this;
  }

  @Override
  public B rewind() {
    currentBufferIndex = 0;
    for (B buffer: buffers) {
      buffer.rewind();
    }
    return (B)this;
  }

  @Override
  public boolean isReadOnly() {
    return readOnly;
  }

  @Override
  public T get() {
    T value = currentBuffer().get();
    onPositionChange(position());
    return value;
  }

  @Override
  public T get(long index) {
    Validator.getArgs(this, index);
    return buffers[(int)(index / bufferMaxCapacity)].get(index % bufferMaxCapacity);
  }

  @Override
  public Stream<T> stream() {
    Stream<T> stream = buffers[0].stream();
    for (int i = 1; i < buffers.length; ++i) {
      stream = Stream.concat(stream, buffers[i].stream());
    }
    return stream;
  }

  @Override
  public B put(T value) {
    if (isReadOnly()) {
      throw new ReadOnlyBufferException();
    }
    currentBuffer().put(value);
    onPositionChange(position());
    return (B)this;
  }

  @Override
  public B put(long index, T value) {
    Validator.putArgs(this, index);
    buffers[(int)(index / bufferMaxCapacity)].put(index % bufferMaxCapacity, value);
    return (B)this;
  }
  
  @Override
  public B put(DataBuffer<T> src) {
    Validator.putArgs(this, src);
    long srcOriginalLimit = src.limit();
    long srcRemaining = src.remaining();
    while (srcRemaining > 0) {
      B buffer = currentBuffer();
      long length = buffer.remaining();
      if (length < srcRemaining) {
        buffer.put(src.limit(src.position() + length));
        srcRemaining -= length;
        ++currentBufferIndex;
      } else {
        buffer.put(src.limit(srcOriginalLimit));
        srcRemaining = 0;
      }
    }
    return (B)this;
  }

  @Override
  public B duplicate() {
    B[] duplicateBuffers = Arrays.stream(buffers).map(b -> (B)b.duplicate()).toArray(i -> Arrays.copyOf(buffers, i));
    return instantiate(duplicateBuffers, readOnly, capacity, limit, currentBufferIndex);
  }

  abstract B instantiate(B[] buffers, boolean readOnly, long capacity, long limit, int currentBufferIndex);

  static <B extends DataBuffer<?>> B[] allocateBuffers(Class<B> bufferClazz, long capacity, long bufferMaxCapacity, Function<Long, B> allocator) {
    int nbMaxedBuffers = (int)(capacity / bufferMaxCapacity);
    long remaining = capacity % bufferMaxCapacity;
    B[] buffers = (B[]) Array.newInstance(bufferClazz, (remaining > 0 || nbMaxedBuffers == 0) ? nbMaxedBuffers + 1 : nbMaxedBuffers);
    int bufferIdx = 0;
    while (bufferIdx < nbMaxedBuffers) {
      buffers[bufferIdx++] = allocator.apply(bufferMaxCapacity);
    }
    if (bufferIdx < buffers.length) {
      buffers[bufferIdx] = allocator.apply(remaining);
    }
    return buffers;
  }

  AbstractLargeDataBuffer(B[] buffers, boolean readOnly) {
    this(buffers, readOnly, (buffers[0].capacity() * (buffers.length - 1)) + buffers[buffers.length - 1].capacity(), 0, 0);
    limit = capacity;
  }

  AbstractLargeDataBuffer(B[] buffers, boolean readOnly, long capacity, long limit, int currentBufferIndex) {
    if (buffers.length == 0) {
      throw new IllegalArgumentException("Buffers list cannot be empty");
    }
    this.buffers = buffers;
    this.bufferMaxCapacity = buffers[0].capacity();
    this.readOnly = readOnly;
    this.capacity = capacity;
    this.limit = limit;
    this.currentBufferIndex = currentBufferIndex;
  }

  B currentBuffer() {
    return buffers[currentBufferIndex];
  }

  int nbBuffers() {
    return buffers.length;
  }

  B buffer(int index) {
    return buffers[index];
  }

  interface ArrayCopy<T> {
    void accept(DataBuffer<T> buf, int offset, int length);
  }

  void copyArray(int offset, int length, ArrayCopy<T> arrayCopy) {
    final int endIndex = offset + length;
    for (int index = offset; index < endIndex;) {
      int copyLength = endIndex - index;
      B buffer = buffers[currentBufferIndex];
      int bufferRemaining = (int)buffer.remaining();
      if (bufferRemaining < copyLength) {
        copyLength = bufferRemaining;
        ++currentBufferIndex;
      }
      arrayCopy.accept(buffer, index, copyLength);
      index += copyLength;
    }
  }

  private final B[] buffers;
  private final long bufferMaxCapacity;
  private final long capacity;
  private final boolean readOnly;
  private long limit;
  private int currentBufferIndex;

  private void resetBuffers(long start, BiConsumer<B, Long> resetAction) {
    long remaining = start;
    for (B buffer: buffers) {
      long resetValue;
      if (remaining > bufferMaxCapacity) {
        resetValue = bufferMaxCapacity;
      } else {
        resetValue = remaining;
      }
      resetAction.accept(buffer, resetValue);
      remaining -= resetValue;
    }
  }

  private void onPositionChange(long position) {
    currentBufferIndex = Math.min((int)(position / bufferMaxCapacity), buffers.length - 1);
  }
}
