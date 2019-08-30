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
import java.util.stream.Stream;

import org.tensorflow.nio.buffer.impl.DataBufferWindow;

/**
 * A container of data of a specific type.
 * <p>
 * Instances of {@code DataBuffer} presents a contract very similar to the buffer classes found
 * under the {@link java.nio.Buffer} package, with the following differences:
 * <ul>
 *  <li>64-bits indexation is supported, allowing to work with a single buffer with a capacity up to 
 *      approximately 2<sup>63</sup> bytes</li>
 *  <li>Can be used to store object of any types and not only primitives</li>
 *  <li>Use of generics allows to work directly with boxed types as well, which does not require explicit
 *      buffer types as with the standard JDK buffers.
 * </ul> 
 * It is important to note that, as opposed to the JDK buffers, there is no guarantee that the buffers are linear, specially
 * when dealing with non-primitive types or large buffers. 
 * 
 * @param <T> type of data stored in this buffer
 */
public interface DataBuffer<T> {
  
  /**
   * Size of the buffer, in elements.
   * <p>
   * For exemple, in case of a byte buffer, this value is equal to the number of bytes this buffer can hold. For an
   * integer buffer, it is equal to the number of integers, therefore the size in bytes of this buffer is 
   * {@code capacity() * Integer.BYTES}.
   * 
   * @return the buffer capacity
   */
  long capacity();
  
  /**
   * Returns this buffer's limit.
   * 
   * The limits sets the end boundary of this buffer's position.
   * 
   * @return the buffer limit
   */
  long limit();
  
  /**
   * Sets this buffer's limit.
   * 
   * If the position is larger than the new limit then it is set to the new limit.
   * 
   * @param newLimit the new limit value; must be non-negative and no larger than this buffer's capacity
   * @return this buffer
   * @throws IllegalArgumentException if the preconditions on newLimit do not hold
   */
  DataBuffer<T> limit(long newLimit);

  /**
   * Returns a copy of this buffer with the given limit.
   * 
   * This is equivalent to {@code duplicate().limit(limit)}.
   * 
   * @param limit the limit of the buffer copy; must be non-negative and no larger than this buffer's capacity 
   * @return buffer copy with the new limit
   * @throws IllegalArgumentException if the preconditions on newLimit do not hold
   */
  default DataBuffer<T> withLimit(long limit) {
    return duplicate().limit(limit);
  }
  
  /**
   * Tells whether there are any elements between the current position and the limit.
   * 
   * @return true if, and only if, there is at least one element remaining in this buffer
   */
  boolean hasRemaining();
  
  /**
   * Returns the number of elements between the current position and the limit.
   * 
   * @return number of elements
   */
  long remaining();
  
  /**
   * The index where the cursor to this buffer is currently at.
   * <p>
   * Each buffer as an internal cursor that automatically moves forward after a read ({@code get}) or
   * write ({@code put}) operation. The position indicates the current index of this cursor.
   */
  long position();
  
  /**
   * Sets this buffer's position. 
   * 
   * @param newPosition the new position value; must be non-negative and no larger than the current limit
   * @return this buffer
   * @throws IllegalArgumentException if the preconditions on position do not hold
   */
  DataBuffer<T> position(long newPosition);

  /**
   * Returns a copy of this buffer with the given limit.
   * 
   * This is equivalent to {@code duplicate().position(position)}.
   * 
   * @param position the new position value; must be non-negative and no larger than the current limit
   * @return buffer copy with the new position
   * @throws IllegalArgumentException if the preconditions on position do not hold
   */
  default DataBuffer<T> withPosition(long position) {
    return duplicate().position(position);
  }
  
  /**
   * Resets the position of this buffer to 0.
   * 
   * @return this buffer
   */
  DataBuffer<T> rewind();
  
  /**
   * Tells whether or not this buffer is backed by an accessible array.
   * 
   * @return true if, and only if, this buffer is read-only
   */
  boolean isReadOnly();
  
  /**
   * Relative <i>get</i> method. 
   * 
   * Reads the value at this buffer's current position, and then increments the position. 
   * 
   * @return the value at the buffer's current position
   * @throws BufferUnderflowException if the buffer's current position is not smaller than its limit
   */
  T get();
  
  /**
   * Absolute <i>get</i> method. 
   * 
   * Reads the value at the given index.
   * 
   * @param index the value from which the float will be read
   * @return the value at the given index
   * @throws IndexOutOfBoundsException if index is negative or not smaller than the buffer's limit
   */
  T get(long index);
  
  /**
   * Retrieve values of this buffer as a Java stream <i>(optional operation)</i>.
   * 
   * @return values, as a stream
   * @throws UnsupportedOperationException if streaming is not supported by this buffer
   */
  Stream<T> stream();
  
  /**
   * Relative <i>put</i> method.
   * 
   * Writes the given value into this buffer at the current position, and then increments the position.

   * @param value value to be written
   * @return this buffer
   * @throws BufferOverflowException if this buffer's current position is not smaller than its limit
   * @throws ReadOnlyBufferException if this buffer is read-only
   */
  DataBuffer<T> put(T value);
  
  /**
   * Absolute <i>put</i> method.
   * 
   * Writes the given value into this buffer at the given index.
   * 
   * @param index the index at which the value will be written
   * @param value the value to be written
   * @return this buffer
   * @throws IndexOutOfBoundsException if index is negative or not smaller than the buffer's limit
   * @throws ReadOnlyBufferException if this buffer is read-only
   */
  DataBuffer<T> put(long index, T value);
  
  /**
   * Relative bulk <i>put</i> method.
   * <p>
   * This method transfers the values remaining in the given source buffer into this buffer. If there are 
   * more values remaining in the source buffer than in this buffer, that is, if {@code src.remaining() > remaining()}, 
   * then no values are transferred and a BufferOverflowException is thrown.
   * <p>
   * Otherwise, this method copies {@code n = src.remaining()} values from the given buffer into this buffer, 
   * starting at each buffer's current position. The positions of both buffers are then incremented by {@code n}.
   * 
   * @param src the source buffer from which values are to be read; must not be this buffer
   * @return this buffer
   * @throws BufferOverflowException if there is insufficient space in this buffer for the remaining values in the source buffer
   * @throws IllegalArgumentException if the source buffer is this buffer
   * @throws ReadOnlyBufferException if this buffer is read-only
   */
  DataBuffer<T> put(DataBuffer<T> src);
  
  /**
   * Creates a new buffer that shares this buffer's content.
   * <p>
   * The content of the new buffer will be that of this buffer. Changes to this buffer's content will be visible in the new buffer, 
   * and vice versa; the two buffers' position and limit values will be independent.
   * <p>
   * The new buffer's capacity, limit and position will be identical to those of this buffer. 
   * The new buffer will be read-only if, and only if, this buffer is read-only.
   * 
   * @return the new buffer
   */
  DataBuffer<T> duplicate();

  /**
   * Creates a new buffer whose content is a shared subsequence of this buffer's content.
   * <p>
   * The content of the new buffer will start at this buffer's current position. Changes to this buffer's content will be visible in the new buffer,
   * and vice versa; the two buffers' position and limit values will be independent.
   * <p>
   * The new buffer's position will be zero, its capacity and its limit will be the number of chars remaining in this buffer.
   * The new buffer will be read-only if, and only if, this buffer is read-only.
   *
   * @return the new buffer
   */
  default DataBuffer<T> slice() {
    return new DataBufferWindow<>(duplicate(), position(), limit());
  }
}
