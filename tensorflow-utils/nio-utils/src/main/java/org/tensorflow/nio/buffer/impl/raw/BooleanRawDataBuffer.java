package org.tensorflow.nio.buffer.impl.raw;

import org.tensorflow.nio.buffer.BooleanDataBuffer;
import org.tensorflow.nio.buffer.impl.Validator;
import sun.misc.Unsafe;

public final class BooleanRawDataBuffer extends AbstractRawDataBuffer<Boolean, BooleanDataBuffer>
    implements BooleanDataBuffer {

  public static BooleanDataBuffer allocate(long size) {
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new boolean[(int)size], false);
  }

  public static BooleanDataBuffer wrap(boolean[] array, boolean readOnly) {
    return new BooleanRawDataBuffer(UnsafeMemoryHandle.of(UNSAFE, array), readOnly);
  }

  public static BooleanDataBuffer map(Unsafe unsafe, long address, long size, boolean readOnly) {
    Validator.createArgs(size, MAX_64BITS);
    return new BooleanRawDataBuffer(UnsafeMemoryHandle.of(unsafe, address, size, Byte.BYTES), readOnly);
  }

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
    Validator.readArgs(this, dst.length, 0, dst.length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst), dst.length);
    return this;
  }

  @Override
  public BooleanDataBuffer read(boolean[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst).offset(offset), length);
    return this;
  }

  @Override
  public BooleanDataBuffer write(boolean[] src) {
    Validator.writeArgs(this, src.length, 0, src.length);
    UnsafeMemoryHandle.of(memory.unsafe, src).copyTo(memory, src.length);
    return this;
  }

  @Override
  public BooleanDataBuffer write(boolean[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    UnsafeMemoryHandle.of(memory.unsafe, src).offset(offset).copyTo(memory, length);
    return this;
  }

  @Override
  protected BooleanDataBuffer instantiate(UnsafeMemoryHandle memory, boolean readOnly) {
    return new BooleanRawDataBuffer(memory, readOnly);
  }

  private BooleanRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
