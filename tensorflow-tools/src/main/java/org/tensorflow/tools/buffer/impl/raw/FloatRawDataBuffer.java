package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

public final class FloatRawDataBuffer extends AbstractRawDataBuffer<Float, FloatDataBuffer>
    implements FloatDataBuffer {

  public static FloatDataBuffer allocate(long size) {
    Validator.createArgs(size, MAX_32BITS);
    return wrap(new float[(int)size], false);
  }

  public static FloatDataBuffer wrap(float[] array, boolean readOnly) {
    return new FloatRawDataBuffer(UnsafeMemoryHandle.of(UnsafeReference.get(), array), readOnly);
  }

  public static FloatDataBuffer map(UnsafeReference unsafe, long address, long size, boolean readOnly) {
    Validator.createArgs(size, MAX_64BITS);
    return new FloatRawDataBuffer(UnsafeMemoryHandle.of(unsafe, address, size, Float.BYTES), readOnly);
  }

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
    Validator.readArgs(this, dst.length, 0, dst.length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst), dst.length);
    return this;
  }

  @Override
  public FloatDataBuffer read(float[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    memory.copyTo(UnsafeMemoryHandle.of(memory.unsafe, dst).offset(offset), length);
    return this;
  }

  @Override
  public FloatDataBuffer write(float[] src) {
    Validator.writeArgs(this, src.length, 0, src.length);
    UnsafeMemoryHandle.of(memory.unsafe, src).copyTo(memory, src.length);
    return this;
  }

  @Override
  public FloatDataBuffer write(float[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    UnsafeMemoryHandle.of(memory.unsafe, src).offset(offset).copyTo(memory, length);
    return this;
  }

  @Override
  protected FloatDataBuffer instantiate(UnsafeMemoryHandle memory, boolean readOnly) {
    return new FloatRawDataBuffer(memory, readOnly);
  }

  private FloatRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    super(memory, readOnly);
  }
}
