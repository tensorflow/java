package org.tensorflow.nio.buffer.adapter;

import org.tensorflow.nio.buffer.ByteDataBuffer;

/**
 * Converts a long to/from bytes
 */
public interface LongDataAdapter extends DataAdapter<Long> {

  /**
   * Writes a long as bytes to the given buffer at its current position.
   *  @param buffer buffer that receives the value as bytes
   * @param value value
   * @param index byte index of the value to write
   */
  void writeLong(ByteDataBuffer buffer, long value, long index);

  /**
   * Reads a long as bytes from the given buffer at its current position.
   *
   * @param buffer buffer that supplies the value as bytes
   * @param index byte index of the value to read
   * @return value
   */
  long readLong(ByteDataBuffer buffer, long index);

  @Override
  default void writeValue(ByteDataBuffer buffer, Long value, long index) {
    writeLong(buffer, value, index);
  }

  @Override
  default Long readValue(ByteDataBuffer buffer, long index) {
    return readLong(buffer, index);
  }
}
