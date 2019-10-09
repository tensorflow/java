package org.tensorflow.nio.buffer.adapter;

import org.tensorflow.nio.buffer.ByteDataBuffer;

/**
 * Converts a double to/from bytes
 */
public interface DoubleDataAdapter extends DataAdapter<Double> {

  /**
   * Writes a double as bytes to the given buffer at its current position.
   *  @param buffer buffer that receives the value as bytes
   * @param value value
   * @param index byte index of the value to write
   */
  void writeDouble(ByteDataBuffer buffer, double value, long index);

  /**
   * Reads a double as bytes from the given buffer at its current position.
   *
   * @param buffer buffer that supplies the value as bytes
   * @param index byte index of the value to read
   * @return value
   */
  double readDouble(ByteDataBuffer buffer, long index);

  @Override
  default void writeValue(ByteDataBuffer buffer, Double value, long index) {
    writeDouble(buffer, value, index);
  }

  @Override
  default Double readValue(ByteDataBuffer buffer, long index) {
    return readDouble(buffer, index);
  }
}
