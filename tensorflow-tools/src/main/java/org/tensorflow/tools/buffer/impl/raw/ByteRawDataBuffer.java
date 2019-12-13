package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

final class ByteRawDataBuffer extends AbstractRawDataBuffer<Byte, ByteDataBuffer>
    implements ByteDataBuffer {

  @Override
  public byte getByte(long index) {
    Validator.getArgs(this, index);
    return memory.getByte(index);
  }

  @Override
  public ByteDataBuffer setByte(byte value, long index) {
    Validator.setArgs(this, index);
    memory.setByte(value, index);
    return this;
  }

  @Override
  public ByteDataBuffer read(byte[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public ByteDataBuffer read(byte[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public ByteDataBuffer write(byte[] src) {
    return write(src, src.length);
  }

  @Override
  public ByteDataBuffer write(byte[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  protected ByteDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new ByteRawDataBuffer(memory, readOnly);
  }

  ByteRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
