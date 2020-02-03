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

package org.tensorflow.tools.buffer;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ReadOnlyBufferException;

/**
 * A container of data of a specific type.
 *
 * <p>Instances of {@code DataBuffer} map native or heap memory segments to a linear view that
 * supports:
 * <ul>
 *  <li>64-bits indexing, allowing to work with buffer larger than 2<sup>31</sup> bytes</li>
 *  <li>Storage of object of any types and not only primitives</li>
 *  <li>Generic types allows to work directly with boxed types as well, which does not require
 *      explicit buffer types as with the standard JDK buffers.
 * </ul> 
 * It is important to note that there is no guarantee the memory managed by a {@code DataBuffer}
 * is linear, specially when dealing with non-primitive types or large buffers.
 * 
 * @param <T> type of data stored in this buffer
 */
public interface DataBuffer<T> {

  /**
   * Size of the buffer, in elements.
   * <p>
   * For exemple, in case of a byte buffer, this value is equal to the number of bytes this buffer
   * can hold. For an integer buffer, it is equal to the number of integers, therefore the size
   * in bytes of this buffer is {@code size() * Integer.BYTES}.
   * 
   * @return the buffer size
   */
  long size();

  /**
   * Tells whether or not this buffer is backed by an accessible array.
   * 
   * @return true if, and only if, this buffer is read-only
   */
  boolean isReadOnly();

  /**
   * Reads the value at the given index.
   *
   * <b>Important: </b>Usage of this method should be limited to buffers of non-primitive types or
   * when the data type is not deterministically known by the caller. In any other case, prefer
   * the usage of its primitive variant which will significantly improve performances
   * (e.g. {@code IntDataBuffer.getInt(idx)}
   * 
   * @param index the index from which the float will be read
   * @return the value at the given index
   * @throws IndexOutOfBoundsException if index is negative or not smaller than the buffer size
   */
  T getObject(long index);

  /**
   * Writes the given value into this buffer at the given index.
   *
   * <b>Important: </b>Usage of this method should be limited to buffers of non-primitive types or
   * when the data type is not deterministically known by the caller. In any other case, prefer
   * the usage of its primitive variant which will significantly improve performances
   * (e.g. {@code IntDataBuffer.setInt(idx)}
   *
   * @param value the value to be written
   * @param index the index at which the value will be written
   * @return this buffer
   * @throws IndexOutOfBoundsException if index is negative or not smaller than the buffer size
   * @throws ReadOnlyBufferException if this buffer is read-only
   */
  DataBuffer<T> setObject(T value, long index);
  
  /**
   * Read the references of the objects in this buffer into the destination array.
   * <p>
   * This method transfers values from this buffer into the given destination array. If there are
   * fewer values in the buffer than are required to satisfy the request, that is, if
   * {@code dst.length > size()}, then no values are transferred and a
   * BufferUnderflowException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = dst.length} values from this buffer into the given
   * array.
   *
   * @param dst the array into which values are to be written
   * @return this buffer
   * @throws BufferUnderflowException if there are not enough values to copy from this buffer
   */
  default DataBuffer<T> read(T[] dst) {
    return read(dst, 0, dst.length);
  }

  /**
   * Read the references of the objects in this buffer into the destination array.
   * <p>
   * This method transfers values from this buffer into the given destination array. If there are
   * fewer values in the buffer than are required to satisfy the request, that is, if
   * {@code length > size()}, then no values are transferred and a
   * BufferUnderflowException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = length} values from this buffer into the given array
   * starting at the given offset.
   *
   * @param dst the array into which values are to be written
   * @param offset the offset within the array of the first value to be written; must be
   *               non-negative and no larger than {@code dst.length}
   * @param length the maximum number of values to be written to the given array; must be
   *               non-negative and no larger than {@code dst.length - offset}
   * @return this buffer
   * @throws BufferUnderflowException if there are fewer than length values remaining in this buffer
   * @throws IndexOutOfBoundsException if the preconditions on the offset and length parameters do
   *                                   not hold
   */
  DataBuffer<T> read(T[] dst, int offset, int length);

  /**
   * Write the references of the objects in the source array into this buffer.
   * <p>
   * This method transfers the values in the given source array into this buffer. If there are
   * more values in the source array than in this buffer, that is, if
   * {@code src.length > size()}, then no values are transferred and a
   * BufferOverflowException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = src.length} values from the given array.
   *
   * @param src the source array from which values are to be read
   * @return this buffer
   * @throws BufferOverflowException if there is insufficient space in this buffer for the values in
   *                                 the source array
   * @throws ReadOnlyBufferException if this buffer is read-only
   */
  default DataBuffer<T> write(T[] src) {
    return write(src, 0, src.length);
  }

  /**
   * Bulk <i>put</i> method, using int arrays.
   * <p>
   * This method transfers the values in the given source array into this buffer. If there are
   * more values in the source array than in this buffer, that is, if
   * {@code length > size()}, then no values are transferred and a
   * BufferOverflowException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = length} values from the given array into this buffer,
   * starting at the given offset.
   *
   * @param src the source array from which values are to be read
   * @param offset the offset within the array of the first value to be read; must be non-negative
   *               and no larger than {@code src.length}
   * @param length the number of values to be read from the given array; must be non-negative and no
   *               larger than {@code src.length - offset}
   * @return this buffer
   * @throws BufferOverflowException if there is insufficient space in this buffer for the values in
   *                                 the source array
   * @throws IndexOutOfBoundsException if the preconditions on the offset and length parameters do
   *                                   not hold
   * @throws ReadOnlyBufferException if this buffer is read-only
   */
  DataBuffer<T> write(T[] src, int offset, int length);

  /**
   * Write the references of the objects in the source array into this buffer.
   * <p>
   * If there are more values to copy than the destination buffer size, i.e.
   * {@code size > dst.size()}, then no values are transferred and a
   * BufferOverflowException is thrown. On the other hand, if there are more values to copy that
   * the source buffer size, i.e. {@code > src.size()}, then a BufferUnderfloatException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = size} values from this buffer into
   * the destination buffer.
   *
   * @param dst the destination buffer into which values are copied; must not be this buffer
   * @param size number of values to copy to the destination buffer
   * @return this buffer
   * @throws IllegalArgumentException if the destination buffer is this buffer
   * @throws ReadOnlyBufferException if the destination buffer is read-only
   * @throws java.nio.BufferOverflowException if there is not enough space in destination buffer
   * @throws java.nio.BufferUnderflowException if there are not enough values in the source buffer
   */
  DataBuffer<T> copyTo(DataBuffer<T> dst, long size);

  /**
   * Creates a new buffer whose content is a shared subsequence of this buffer's content, starting
   * at the given index.
   * <p>
   * The index must not be greater than this buffer size. Changes to this buffer's content will
   * be visible in the new buffer and vice versa. The new buffer will be read-only if, and only if,
   * this buffer is read-only.
   *
   * @param index index of the first value of the new buffer created, must not be greater than
   *              {@code size()}
   * @return the new buffer
   * @throws IllegalArgumentException if index do not pass validation checks
   */
  DataBuffer<T> offset(long index);

  /**
   * Creates a new buffer whose content is a shared subsequence of this buffer's content, whose
   * size is set to the given value.
   * <p>
   * The new size must not be greater than this buffer size. Changes to this buffer's
   * content will be visible in the new buffer and vice versa. The new buffer will be read-only if,
   * and only if, this buffer is read-only.
   *
   * @param size size of this new buffer, must not be greater than {@code size()}
   * @return the new buffer
   * @throws IllegalArgumentException if size value do not pass validation checks
   */
  DataBuffer<T> narrow(long size);
}
