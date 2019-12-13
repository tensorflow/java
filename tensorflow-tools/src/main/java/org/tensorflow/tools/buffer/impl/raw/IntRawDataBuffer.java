package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

final class IntRawDataBuffer extends AbstractRawDataBuffer<Integer, IntDataBuffer>
    implements IntDataBuffer {

  @Override
  public int getInt(long index) {
    Validator.getArgs(this, index);
    return memory.getInt(index);
  }

  @Override
  public IntDataBuffer setInt(int value, long index) {
    Validator.setArgs(this, index);
    memory.setInt(value, index);
    return this;
  }

  @Override
  public IntDataBuffer read(int[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public IntDataBuffer read(int[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public IntDataBuffer write(int[] src) {
    return write(src, src.length);
  }

  @Override
  public IntDataBuffer write(int[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  protected IntDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new IntRawDataBuffer(memory, readOnly);
  }

  IntRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
