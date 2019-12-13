package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

final class LongRawDataBuffer extends AbstractRawDataBuffer<Long, LongDataBuffer>
    implements LongDataBuffer {

  @Override
  public long getLong(long index) {
    Validator.getArgs(this, index);
    return memory.getLong(index);
  }

  @Override
  public LongDataBuffer setLong(long value, long index) {
    Validator.setArgs(this, index);
    memory.setLong(value, index);
    return this;
  }

  @Override
  public LongDataBuffer read(long[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public LongDataBuffer read(long[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public LongDataBuffer write(long[] src) {
    return write(src, src.length);
  }

  @Override
  public LongDataBuffer write(long[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  protected LongDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new LongRawDataBuffer(memory, readOnly);
  }

  LongRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
