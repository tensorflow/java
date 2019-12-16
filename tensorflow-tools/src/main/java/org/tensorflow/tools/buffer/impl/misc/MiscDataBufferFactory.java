package org.tensorflow.tools.buffer.impl.misc;

import java.util.BitSet;
import org.tensorflow.tools.buffer.BooleanDataBuffer;
import org.tensorflow.tools.buffer.DataBuffer;

/**
 * Factory of miscellaneous data buffers
 */
public class MiscDataBufferFactory {

  public static BooleanDataBuffer create(BitSet bitSet, long numBits, boolean readOnly) {
    return new BitSetDataBuffer(bitSet, numBits, readOnly);
  }

  public static BooleanDataBuffer create(boolean[] array, boolean readOnly) {
    return new BooleanArrayDataBuffer(array, readOnly);
  }

  public static <T>  DataBuffer<T> create(T[] array, boolean readOnly) {
    return new ArrayDataBuffer<>(array, readOnly);
  }
}
