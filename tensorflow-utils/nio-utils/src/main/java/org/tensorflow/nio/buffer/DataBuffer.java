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

import java.nio.ReadOnlyBufferException;

/**
 * A container of data of a specific type.
 *
 * <p>Instances of {@code DataBuffer} map native or heap memory segments to a linear view that
 * supports:
 * <ul>
 *  <li>64-bits indexation, allowing to work with a single buffer with a size up to
 *      approximately 2<sup>63</sup> bytes</li>
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
  T get(long index);

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
  DataBuffer<T> set(T value, long index);
  
  /**
   * Copy data of this buffer in the given buffer.
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
