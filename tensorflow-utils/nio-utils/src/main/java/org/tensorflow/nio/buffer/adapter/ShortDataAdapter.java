package org.tensorflow.nio.buffer.adapter;

import org.tensorflow.nio.buffer.ByteDataBuffer;

/**
 * Converts a short to/from bytes
 */
public interface ShortDataAdapter extends DataAdapter<Short> {

  /**
   * Writes a short as bytes to the given buffer at its current position.
   *  @param buffer buffer that receives the value as bytes
   * @param value value
   * @param index byte index of the value to write
   */
  void writeShort(ByteDataBuffer buffer, short value, long index);

  /**
   * Reads a short as bytes from the given buffer at its current position.
   *
   * @param buffer buffer that supplies the value as bytes
   * @param index byte index of the value to read
   * @return value
   */
  short readShort(ByteDataBuffer buffer, long index);

  @Override
  default void writeValue(ByteDataBuffer buffer, Short value, long index) {
    writeShort(buffer, value, index);
  }

  @Override
  default Short readValue(ByteDataBuffer buffer, long index) {
    return readShort(buffer, index);
  }
}
