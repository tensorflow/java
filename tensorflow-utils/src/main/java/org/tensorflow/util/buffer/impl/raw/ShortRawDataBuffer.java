package org.tensorflow.util.buffer.impl.raw;

import org.tensorflow.util.buffer.ShortDataBuffer;
import org.tensorflow.util.buffer.impl.Validator;
import sun.misc.Unsafe;

public final class ShortRawDataBuffer extends AbstractRawDataBuffer<Short, ShortDataBuffer>
    implements ShortDataBuffer {

  public static ShortDataBuffer allocate(long size) {
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new short[(int)size], false);
  }

  public static ShortDataBuffer wrap(short[] array, boolean readOnly) {
    return new ShortRawDataBuffer(UnsafeMemoryHandle.of(UnsafeReference.get(), array), readOnly);
  }

  public static ShortDataBuffer map(UnsafeReference unsafe, long address, long size, boolean readOnly) {
    Validator.createArgs(size, MAX_64BITS);
    return new ShortRawDataBuffer(UnsafeMemoryHandle.of(unsafe, address, size, Short.BYTES), readOnly);
  }

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
    Validator.readArgs(this, dst.length, 0, dst.length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst), dst.length);
    return this;
  }

  @Override
  public ShortDataBuffer read(short[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst).offset(offset), length);
    return this;
  }

  @Override
  public ShortDataBuffer write(short[] src) {
    Validator.writeArgs(this, src.length, 0, src.length);
    UnsafeMemoryHandle.of(memory.unsafe, src).copyTo(memory, src.length);
    return this;
  }

  @Override
  public ShortDataBuffer write(short[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    UnsafeMemoryHandle.of(memory.unsafe, src).offset(offset).copyTo(memory, length);
    return this;
  }

  @Override
  protected ShortDataBuffer instantiate(UnsafeMemoryHandle memory, boolean readOnly) {
    return new ShortRawDataBuffer(memory, readOnly);
  }

  private ShortRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
