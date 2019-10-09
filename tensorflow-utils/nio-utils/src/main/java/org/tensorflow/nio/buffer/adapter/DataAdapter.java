package org.tensorflow.nio.buffer.adapter;

import org.tensorflow.nio.buffer.ByteDataBuffer;

/**
 * Converts a value of a given type to/from bytes
 *
 * @param <T> value type
 */
public interface DataAdapter<T> {

  /**
   * Writes a value as bytes to the given buffer at its current position.
   *
   * @param buffer buffer that receives the value as bytes
   * @param value value
   * @param index index of the value to write
   */
  void writeValue(ByteDataBuffer buffer, T value, long index);

  /**
   * Reads a value as bytes from the given buffer at its current position.
   *
   * @param buffer buffer that supplies the value as bytes
   * @param index index of the value to read
   * @return value
   */
  T readValue(ByteDataBuffer buffer, long index);

  /**
   * Returns the number of bytes required to represent a single value
   */
  int sizeInBytes();
}
