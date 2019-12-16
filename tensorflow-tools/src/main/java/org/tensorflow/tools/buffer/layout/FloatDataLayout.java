package org.tensorflow.tools.buffer.layout;

import org.tensorflow.tools.buffer.ByteDataBuffer;

/**
 * Converts a float to/from bytes
 */
public interface FloatDataLayout extends DataLayout<Float> {

  /**
   * Writes a float as bytes to the given buffer at its current position.
   *  @param buffer buffer that receives the value as bytes
   * @param value value
   * @param index byte index of the value to write
   */
  void writeFloat(ByteDataBuffer buffer, float value, long index);

  /**
   * Reads a float as bytes from the given buffer at its current position.
   *
   * @param buffer buffer that supplies the value as bytes
   * @param index byte index of the value to read
   * @return value
   */
  float readFloat(ByteDataBuffer buffer, long index);

  @Override
  default void writeValue(ByteDataBuffer buffer, Float value, long index) {
    writeFloat(buffer, value, index);
  }

  @Override
  default Float readValue(ByteDataBuffer buffer, long index) {
    return readFloat(buffer, index);
  }
}
