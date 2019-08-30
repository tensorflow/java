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

import org.tensorflow.nio.buffer.impl.ByteDataBufferWindow;
import org.tensorflow.nio.buffer.impl.DataBufferWindow;

/**
 * A {@link DataBuffer} of bytes.
 */
public interface ByteDataBuffer extends DataBuffer<Byte> {

  /**
   * Relative bulk <i>get</i> method, using byte arrays.
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
  default ByteDataBuffer get(byte[] dst) { return get(dst, 0, dst.length); }
  
  /**
   * Relative bulk <i>get</i> method, using byte arrays.
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
  ByteDataBuffer get(byte[] dst, int offset, int length);
  
  /**
   * Relative bulk <i>put</i> method, using byte arrays.
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
  default ByteDataBuffer put(byte[] src) { return put(src, 0, src.length); }
  
  /**
   * Relative bulk <i>put</i> method, using byte arrays.
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
  ByteDataBuffer put(byte[] src, int offset, int length);

  @Override
  ByteDataBuffer limit(long newLimit);

  @Override
  default ByteDataBuffer withLimit(long limit) {
    return duplicate().limit(limit);
  }

  @Override
  ByteDataBuffer position(long newPosition);

  @Override
  default ByteDataBuffer withPosition(long position) {
    return duplicate().position(position);
  }

  @Override
  ByteDataBuffer rewind();
  
  @Override
  ByteDataBuffer put(Byte value);

  @Override
  ByteDataBuffer put(long index, Byte value);

  @Override
  ByteDataBuffer put(DataBuffer<Byte> src);
  
  @Override
  ByteDataBuffer duplicate();

  @Override
  default ByteDataBuffer slice() {
    return new ByteDataBufferWindow(duplicate(), position(), limit());
  }
}
