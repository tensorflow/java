package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

public final class IntRawDataBuffer extends AbstractRawDataBuffer<Integer, IntDataBuffer>
    implements IntDataBuffer {

  public static IntDataBuffer allocate(long size) {
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new int[(int)size], false);
  }

  public static IntDataBuffer wrap(int[] array, boolean readOnly) {
    return new IntRawDataBuffer(UnsafeMemoryHandle.of(UnsafeReference.get(), array), readOnly);
  }

  public static IntDataBuffer map(UnsafeReference unsafe, long address, long size, boolean readOnly) {
    Validator.createArgs(size, MAX_64BITS);
    return new IntRawDataBuffer(UnsafeMemoryHandle.of(unsafe, address, size, Integer.BYTES), readOnly);
  }

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
    Validator.readArgs(this, dst.length, 0, dst.length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst), dst.length);
    return this;
  }

  @Override
  public IntDataBuffer read(int[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst).offset(offset), length);
    return this;
  }

  @Override
  public IntDataBuffer write(int[] src) {
    Validator.writeArgs(this, src.length, 0, src.length);
    UnsafeMemoryHandle.of(memory.unsafe, src).copyTo(memory, src.length);
    return this;
  }

  @Override
  public IntDataBuffer write(int[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    UnsafeMemoryHandle.of(memory.unsafe, src).offset(offset).copyTo(memory, length);
    return this;
  }

  @Override
  protected IntDataBuffer instantiate(UnsafeMemoryHandle memory, boolean readOnly) {
    return new IntRawDataBuffer(memory, readOnly);
  }

  private IntRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
