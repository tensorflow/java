package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

final class FloatRawDataBuffer extends AbstractRawDataBuffer<Float, FloatDataBuffer>
    implements FloatDataBuffer {

  @Override
  public float getFloat(long index) {
    Validator.getArgs(this, index);
    return memory.getFloat(index);
  }

  @Override
  public FloatDataBuffer setFloat(float value, long index) {
    Validator.setArgs(this, index);
    memory.setFloat(value, index);
    return this;
  }

  @Override
  public FloatDataBuffer read(float[] dst) {
    return read(dst, dst.length);
  }

  @Override
  public FloatDataBuffer read(float[] dst, int offset, int length) {
    return read(dst, dst.length, offset, length);
  }

  @Override
  public FloatDataBuffer write(float[] src) {
    return write(src, src.length);
  }

  @Override
  public FloatDataBuffer write(float[] src, int offset, int length) {
    return write(src, src.length, offset, length);
  }

  @Override
  protected FloatDataBuffer instantiate(UnsafeMemoryHandle memory) {
    return new FloatRawDataBuffer(memory, readOnly);
  }

  FloatRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
