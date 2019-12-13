package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.BooleanDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

final class BooleanRawDataBuffer extends AbstractRawDataBuffer<Boolean, BooleanDataBuffer>
    implements BooleanDataBuffer {

  @Override
  public boolean getBoolean(long index) {
    Validator.getArgs(this, index);
    return memory.getBoolean(index);
  }

  @Override
  public BooleanDataBuffer setBoolean(boolean value, long index) {
    Validator.setArgs(this, index);
    memory.setBoolean(value, index);
    return this;
  }

  @Override
  public BooleanDataBuffer read(boolean[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public BooleanDataBuffer read(boolean[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public BooleanDataBuffer write(boolean[] src) {
    return write(src, src.length);
  }

  @Override
  public BooleanDataBuffer write(boolean[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  protected BooleanDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new BooleanRawDataBuffer(memory, readOnly);
  }

  BooleanRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
