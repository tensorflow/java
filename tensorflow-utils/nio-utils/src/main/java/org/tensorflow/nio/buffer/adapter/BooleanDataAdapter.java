package org.tensorflow.nio.buffer.adapter;

import org.tensorflow.nio.buffer.ByteDataBuffer;

/**
 * Converts a boolean to/from bytes
 */
public interface BooleanDataAdapter extends DataAdapter<Boolean> {

  /**
   * Writes a boolean as bytes to the given buffer at its current position.
   *  @param buffer buffer that receives the value as bytes
   * @param value value
   * @param index byte index of the value to write
   */
  void writeBoolean(ByteDataBuffer buffer, boolean value, long index);

  /**
   * Reads a boolean as bytes from the given buffer at its current position.
   *
   * @param buffer buffer that supplies the value as bytes
   * @param index byte index of the value to read
   * @return value
   */
  boolean readBoolean(ByteDataBuffer buffer, long index);

  @Override
  default void writeValue(ByteDataBuffer buffer, Boolean value, long index) {
    writeBoolean(buffer, value, index);
  }

  @Override
  default Boolean readValue(ByteDataBuffer buffer, long index) {
    return readBoolean(buffer, index);
  }
}
