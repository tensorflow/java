package org.tensorflow.nio.buffer.impl.misc;

import java.util.BitSet;
import org.tensorflow.nio.buffer.BooleanDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.impl.AbstractDataBuffer;
import org.tensorflow.nio.buffer.impl.Validator;

public class BitSetDataBuffer extends AbstractDataBuffer<Boolean> implements BooleanDataBuffer  {

  public static long MAX_CAPACITY = Integer.MAX_VALUE - 2;

  public static BooleanDataBuffer allocate(long size) {
    if (size < 0) {
      throw new IllegalArgumentException("Capacity must be non-negative");
    }
    if (size > MAX_CAPACITY) {
      throw new IllegalArgumentException("Size for an bit-set data buffer cannot exceeds " + MAX_CAPACITY +
          " elements, use a JoinDataBuffer instead");
    }
    return new BitSetDataBuffer(new BitSet((int)size), false, 0, (int)size);
  }

  @Override
  public long size() {
    return length;
  }

  @Override
  public boolean isReadOnly() {
    return readOnly;
  }

  @Override
  public boolean getBoolean(long index) {
    Validator.getArgs(this, index);
    return bitSet.get((int)index + offset);
  }

  @Override
  public BooleanDataBuffer setBoolean(boolean value, long index) {
    Validator.setArgs(this, index);
    bitSet.set((int)index + offset, value);
    return this;
  }

  @Override
  public BooleanDataBuffer copyTo(DataBuffer<Boolean> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    slowCopyTo(dst, size);
    return this;
  }

  @Override
  public BooleanDataBuffer read(boolean[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = this.offset, j = offset; i < this.offset + length; ++i, ++j) {
      dst[j] = bitSet.get(i);
    }
    return this;
  }

  @Override
  public BooleanDataBuffer write(boolean[] src, int offset, int length) {
    Validator.readArgs(this, src.length, offset, length);
    for (int i = this.offset, j = offset; i < this.offset + length; ++i, ++j) {
      bitSet.set(i, src[j]);
    }
    return this;
  }

  @Override
  public BooleanDataBuffer offset(long index) {
    Validator.offsetArgs(this, index);
    return new BitSetDataBuffer(bitSet, readOnly, offset + (int)index, length - (int)index);
  }

  @Override
  public BooleanDataBuffer narrow(long size) {
    Validator.narrowArgs(this, size);
    return new BitSetDataBuffer(bitSet, readOnly, offset, (int)size);
  }

  private BitSetDataBuffer(BitSet bitSet, boolean readOnly, int offset, int length) {
    this.bitSet = bitSet;
    this.readOnly = readOnly;
    this.offset = offset;
    this.length = length;
  }

  private final BitSet bitSet;
  private final boolean readOnly;
  private final int offset;
  private final int length;
}
