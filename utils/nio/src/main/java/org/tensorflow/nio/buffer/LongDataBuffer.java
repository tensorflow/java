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
package org.tensorflow.nio.buffer;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ReadOnlyBufferException;
import java.util.stream.LongStream;

import org.tensorflow.nio.buffer.impl.ByteDataBufferWindow;
import org.tensorflow.nio.buffer.impl.LongDataBufferWindow;

/**
 * A {@link DataBuffer} of longs.
 */
public interface LongDataBuffer extends DataBuffer<Long> {

  /**
   * Retrieve values of this buffer as a stream of longs <i>(optional operation)</i>.
   * 
   * @return values, as a stream
   * @throws UnsupportedOperationException if streaming is not supported by this buffer
   */
  LongStream longStream();
  
  /**
   * Relative bulk <i>get</i> method, using long arrays.
   * <p>
   * This method transfers values from this buffer into the given destination array. If there are 
   * fewer values remaining in the buffer than are required to satisfy the request, that is, if 
   * {@code dst.length > remaining()}, then no values are transferred and a BufferUnderflowException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = dst.length} values from this buffer into the given array, starting at the current 
   * position of this buffer. The position of this buffer is then incremented by {@code n}. 
   * 
   * @param dst the array into which values are to be written
   * @return this buffer
   * @throws BufferUnderflowException if there are fewer than length values remaining in this buffer
   */
  default LongDataBuffer get(long[] dst) { return get(dst, 0, dst.length); }
  
  /**
   * Relative bulk <i>get</i> method, using long arrays.
   * <p>
   * This method transfers values from this buffer into the given destination array. If there are 
   * fewer values remaining in the buffer than are required to satisfy the request, that is, if 
   * {@code length > remaining()}, then no values are transferred and a BufferUnderflowException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = length} values from this buffer into the given array, starting at the current 
   * position of this buffer and at the given offset in the array. The position of this buffer is then incremented by {@code n}. 
   * 
   * @param dst the array into which values are to be written
   * @param offset the offset within the array of the first value to be written; must be non-negative and no larger than {@code dst.length}
   * @param length the maximum number of values to be written to the given array; must be non-negative and no larger than {@code dst.length - offset}
   * @return this buffer
   * @throws BufferUnderflowException if there are fewer than length values remaining in this buffer
   * @throws IndexOutOfBoundsException if the preconditions on the offset and length parameters do not hold
   */
  LongDataBuffer get(long[] dst, int offset, int length);

  /**
   * Relative bulk <i>put</i> method, using long arrays.
   * <p>
   * This method transfers the values in the given source array into this buffer. If there are 
   * more values in the source array than in this buffer, that is, if {@code src.length > remaining()}, 
   * then no values are transferred and a BufferOverflowException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = src.length} values from the given array into this buffer, 
   * starting at this buffer current position. The position of this buffer is then incremented by {@code n}.
   * 
   * @param src the source array from which values are to be read
   * @return this buffer
   * @throws BufferOverflowException if there is insufficient space in this buffer for the remaining values in the source array
   * @throws ReadOnlyBufferException if this buffer is read-only
   */
  default LongDataBuffer put(long[] src) { return put(src, 0, src.length); }
  
  /**
   * Relative bulk <i>put</i> method, using long arrays.
   * <p>
   * This method transfers the values in the given source array into this buffer. If there are 
   * more values in the source array than in this buffer, that is, if {@code length > remaining()}, 
   * then no values are transferred and a BufferOverflowException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = length} values from the given array into this buffer, 
   * starting at the given offset in the array and at this buffer current position. The position of this buffer 
   * is then incremented by {@code n}.
   * 
   * @param src the source array from which values are to be read
   * @param offset the offset within the array of the first value to be read; must be non-negative and no larger than {@code src.length}
   * @param length the number of values to be read from the given array; must be non-negative and no larger than {@code src.length - offset}
   * @return this buffer
   * @throws BufferOverflowException if there is insufficient space in this buffer for the remaining values in the source array
   * @throws IllegalArgumentException if the preconditions on the offset and length parameters do not hold
   * @throws ReadOnlyBufferException if this buffer is read-only
   */
  LongDataBuffer put(long[] src, int offset, int length);

  @Override
  LongDataBuffer limit(long newLimit);

  @Override
  default LongDataBuffer withLimit(long limit) {
    return duplicate().limit(limit);
  }

  @Override
  LongDataBuffer position(long newPosition);

  @Override
  default LongDataBuffer withPosition(long position) {
    return duplicate().position(position);
  }

  @Override
  LongDataBuffer rewind();
  
  @Override
  LongDataBuffer put(Long value);

  @Override
  LongDataBuffer put(long index, Long value);

  @Override
  LongDataBuffer put(DataBuffer<Long> src);
  
  @Override
  LongDataBuffer duplicate();

  @Override
  default LongDataBuffer slice() {
    return new LongDataBufferWindow(duplicate(), position(), limit());
  }
}
