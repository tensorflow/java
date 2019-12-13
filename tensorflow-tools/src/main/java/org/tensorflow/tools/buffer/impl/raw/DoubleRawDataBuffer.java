package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

final class DoubleRawDataBuffer extends AbstractRawDataBuffer<Double, DoubleDataBuffer>
    implements DoubleDataBuffer {

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
    return read(dst, dst.length);
  }

  @Override
  public DoubleDataBuffer read(double[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public DoubleDataBuffer write(double[] src) {
    return write(src, src.length);
  }

  @Override
  public DoubleDataBuffer write(double[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  protected DoubleDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new DoubleRawDataBuffer(memory, readOnly);
  }

  DoubleRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
