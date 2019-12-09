package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

public final class ByteRawDataBuffer extends AbstractRawDataBuffer<Byte, ByteDataBuffer>
    implements ByteDataBuffer {

  public static ByteDataBuffer allocate(long size) {
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new byte[(int)size], false);
  }

  public static ByteDataBuffer wrap(byte[] array, boolean readOnly) {
    return new ByteRawDataBuffer(UnsafeMemoryHandle.of(UnsafeReference.get(), array), readOnly);
  }

  public static ByteDataBuffer map(UnsafeReference unsafe, long address, long size, boolean readOnly) {
    Validator.createArgs(size, MAX_64BITS);
    return new ByteRawDataBuffer(UnsafeMemoryHandle.of(unsafe, address, size, Byte.BYTES), readOnly);
  }

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
    Validator.readArgs(this, dst.length, 0, dst.length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst), dst.length);
    return this;
  }

  @Override
  public ByteDataBuffer read(byte[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst).offset(offset), length);
    return this;
  }

  @Override
  public ByteDataBuffer write(byte[] src) {
    Validator.writeArgs(this, src.length, 0, src.length);
    UnsafeMemoryHandle.of(memory.unsafe, src).copyTo(memory, src.length);
    return this;
  }

  @Override
  public ByteDataBuffer write(byte[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    UnsafeMemoryHandle.of(memory.unsafe, src).offset(offset).copyTo(memory, length);
    return this;
  }

  @Override
  protected ByteDataBuffer instantiate(UnsafeMemoryHandle memory, boolean readOnly) {
    return new ByteRawDataBuffer(memory, readOnly);
  }

  private ByteRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
