package org.tensorflow.nio.buffer.impl.raw;

import org.tensorflow.nio.buffer.DoubleDataBuffer;
import org.tensorflow.nio.buffer.impl.Validator;
import sun.misc.Unsafe;

public final class DoubleRawDataBuffer extends AbstractRawDataBuffer<Double, DoubleDataBuffer>
    implements DoubleDataBuffer {

  public static DoubleDataBuffer allocate(long size) {
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new double[(int)size], false);
  }

  public static DoubleDataBuffer wrap(double[] array, boolean readOnly) {
    return new DoubleRawDataBuffer(UnsafeMemoryHandle.of(UNSAFE, array), readOnly);
  }

  public static DoubleDataBuffer map(Unsafe unsafe, long address, long size, boolean readOnly) {
    Validator.createArgs(size, MAX_64BITS);
    return new DoubleRawDataBuffer(UnsafeMemoryHandle.of(unsafe, address, size, Double.BYTES), readOnly);
  }

  @Override
  public double getDouble(long index) {
    Validator.getArgs(this, index);
    return memory.getDouble(index);
  }

  @Override
  public DoubleDataBuffer setDouble(double value, long index) {
    Validator.setArgs(this, index);
    memory.setDouble(value, index);
    return this;
  }

  @Override
  public DoubleDataBuffer read(double[] dst) {
    Validator.readArgs(this, dst.length, 0, dst.length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst), dst.length);
    return this;
  }

  @Override
  public DoubleDataBuffer read(double[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst).offset(offset), length);
    return this;
  }

  @Override
  public DoubleDataBuffer write(double[] src) {
    Validator.writeArgs(this, src.length, 0, src.length);
    UnsafeMemoryHandle.of(memory.unsafe, src).copyTo(memory, src.length);
    return this;
  }

  @Override
  public DoubleDataBuffer write(double[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    UnsafeMemoryHandle.of(memory.unsafe, src).offset(offset).copyTo(memory, length);
    return this;
  }

  @Override
  protected DoubleDataBuffer instantiate(UnsafeMemoryHandle memory, boolean readOnly) {
    return new DoubleRawDataBuffer(memory, readOnly);
  }

  private DoubleRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
