package org.tensorflow.ndarray.buffer;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.BitSet;

/**
 * Visit the backing storage of {@link DataBuffer} instances.
 *
 * @param <R> value type returned by the visitor
 */
public interface DataStorageVisitor<R> {

  /**
   * Visit the {@link ByteBuffer} backing a given instance of a {@link DataBuffer}
   *
   * @param buffer underlying buffer
   * @return any value
   * @see DataBuffer#accept(DataStorageVisitor)
   */
  default R visit(ByteBuffer buffer) {
    return fallback();
  }

  /**
   * Visit the {@link ShortBuffer} backing a given instance of a {@link DataBuffer}
   *
   * @param buffer underlying buffer
   * @return any value
   * @see DataBuffer#accept(DataStorageVisitor)
   */
  default R visit(ShortBuffer buffer) {
    return fallback();
  }

  /**
   * Visit the {@link IntBuffer} backing a given instance of a {@link DataBuffer}
   *
   * @param buffer underlying buffer
   * @return any value
   * @see DataBuffer#accept(DataStorageVisitor)
   */
  default R visit(IntBuffer buffer) {
    return fallback();
  }

  /**
   * Visit the {@link LongBuffer} backing a given instance of a {@link DataBuffer}
   *
   * @param buffer underlying buffer
   * @return any value
   * @see DataBuffer#accept(DataStorageVisitor)
   */
  default R visit(LongBuffer buffer) {
    return fallback();
  }

  /**
   * Visit the {@link FloatBuffer} backing a given instance of a {@link DataBuffer}
   *
   * @param buffer underlying buffer
   * @return any value
   * @see DataBuffer#accept(DataStorageVisitor)
   */
  default R visit(FloatBuffer buffer) {
    return fallback();
  }

  /**
   * Visit the {@link DoubleBuffer} backing a given instance of a {@link DataBuffer}
   *
   * @param buffer underlying buffer
   * @return any value
   * @see DataBuffer#accept(DataStorageVisitor)
   */
  default R visit(DoubleBuffer buffer) {
    return fallback();
  }

  /**
   * Visit the boolean array backing a given instance of a {@link DataBuffer}
   *
   * @param array underlying array
   * @param offset offset of the buffer within the array
   * @param length length of the buffer within the array
   * @return any value
   * @see DataBuffer#accept(DataStorageVisitor)
   */
  default R visit(boolean[] array, int offset, int length) {
    return fallback();
  }

  /**
   * Visit the bit set backing a given instance of a {@link DataBuffer}
   *
   * @param bitSet underlying bit set
   * @param offset offset of the buffer within the bit set
   * @param numBits number of bits used to represent the buffer within the bit set
   * @return any value
   * @see DataBuffer#accept(DataStorageVisitor)
   */
  default R visit(BitSet bitSet, int offset, long numBits) {
    return fallback();
  }

  /**
   * Visit the object array backing a given instance of a {@link DataBuffer}
   *
   * @param array underlying array
   * @param offset offset of the buffer within the array
   * @param length length of the buffer within the array
   * @return any value
   * @see DataBuffer#accept(DataStorageVisitor)
   */
  default R visit(Object[] array, int offset, int length) {
    return fallback();
  }

  /**
   * Visit the raw memory segment of a given instance of a {@link DataBuffer}
   *
   * @param address native address of the buffer
   * @param length length of the buffer
   * @param scale number of bytes required to store a single value of this buffer
   * @return any value
   * @see DataBuffer#accept(DataStorageVisitor)
   */
  default R visit(long address, long length, long scale) {
    return fallback();
  }

  /**
   * Fallback method called if the visitor implementation does not support the type of backing storage
   * for a given {@link DataBuffer}
   *
   * <p>The implementor of this interface must override the {@code visit} methods for type of storage
   * it supports. If {@link DataBuffer#accept(DataStorageVisitor)} is called on a buffer
   * using a different type of storage, the invocation will fallback to this method.
   *
   * @return any value
   */
  R fallback();
}
