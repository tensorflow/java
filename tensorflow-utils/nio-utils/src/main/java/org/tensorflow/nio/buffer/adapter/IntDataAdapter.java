package org.tensorflow.nio.buffer.adapter;

import org.tensorflow.nio.buffer.ByteDataBuffer;

/**
 * Converts a int to/from bytes
 */
public interface IntDataAdapter extends DataAdapter<Integer> {

  /**
   * Writes a int as bytes to the given buffer at its current position.
   *  @param buffer buffer that receives the value as bytes
   * @param value value
   * @param index byte index of the value to write
   */
  void writeInt(ByteDataBuffer buffer, int value, long index);

  /**
   * Reads a int as bytes from the given buffer at its current position.
   *
   * @param buffer buffer that supplies the value as bytes
   * @param index byte index of the value to read
   * @return value
   */
  int readInt(ByteDataBuffer buffer, long index);

  @Override
  default void writeValue(ByteDataBuffer buffer, Integer value, long index) {
    writeInt(buffer, value, index);
  }

  @Override
  default Integer readValue(ByteDataBuffer buffer, long index) {
    return readInt(buffer, index);
  }
}
