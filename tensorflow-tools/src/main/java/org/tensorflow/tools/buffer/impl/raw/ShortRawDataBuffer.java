package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.ShortDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

final class ShortRawDataBuffer extends AbstractRawDataBuffer<Short, ShortDataBuffer>
    implements ShortDataBuffer {

  @Override
  public short getShort(long index) {
    Validator.getArgs(this, index);
    return memory.getShort(index);
  }

  @Override
  public ShortDataBuffer setShort(short value, long index) {
    Validator.setArgs(this, index);
    memory.setShort(value, index);
    return this;
  }

  @Override
  public ShortDataBuffer read(short[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public ShortDataBuffer read(short[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public ShortDataBuffer write(short[] src) {
    return write(src, src.length);
  }

  @Override
  public ShortDataBuffer write(short[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  protected ShortDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new ShortRawDataBuffer(memory, readOnly);
  }

  ShortRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
