package org.tensorflow.util.buffer.impl.raw;

import org.tensorflow.util.buffer.LongDataBuffer;
import org.tensorflow.util.buffer.impl.Validator;
import sun.misc.Unsafe;

public final class LongRawDataBuffer extends AbstractRawDataBuffer<Long, LongDataBuffer>
    implements LongDataBuffer {

  public static LongDataBuffer allocate(long size) {
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new long[(int)size], false);
  }

  public static LongDataBuffer wrap(long[] array, boolean readOnly) {
    return new LongRawDataBuffer(UnsafeMemoryHandle.of(UnsafeReference.get(), array), readOnly);
  }

  public static LongDataBuffer map(UnsafeReference unsafe, long address, long size, boolean readOnly) {
    Validator.createArgs(size, MAX_64BITS);
    return new LongRawDataBuffer(UnsafeMemoryHandle.of(unsafe, address, size, Long.BYTES), readOnly);
  }

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
    Validator.readArgs(this, dst.length, 0, dst.length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst), dst.length);
    return this;
  }

  @Override
  public LongDataBuffer read(long[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst).offset(offset), length);
    return this;
  }

  @Override
  public LongDataBuffer write(long[] src) {
    Validator.writeArgs(this, src.length, 0, src.length);
    UnsafeMemoryHandle.of(memory.unsafe, src).copyTo(memory, src.length);
    return this;
  }

  @Override
  public LongDataBuffer write(long[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    UnsafeMemoryHandle.of(memory.unsafe, src).offset(offset).copyTo(memory, length);
    return this;
  }

  @Override
  protected LongDataBuffer instantiate(UnsafeMemoryHandle memory, boolean readOnly) {
    return new LongRawDataBuffer(memory, readOnly);
  }

  private LongRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
