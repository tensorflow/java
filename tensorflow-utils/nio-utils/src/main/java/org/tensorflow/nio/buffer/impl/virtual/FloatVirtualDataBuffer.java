package org.tensorflow.nio.buffer.impl.virtual;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.FloatDataBuffer;
import org.tensorflow.nio.buffer.adapter.FloatDataAdapter;
import org.tensorflow.nio.buffer.impl.Validator;

public class FloatVirtualDataBuffer extends AbstractVirtualDataBuffer<Float, FloatDataBuffer>
    implements FloatDataBuffer {

  public static FloatVirtualDataBuffer create(ByteDataBuffer delegate, FloatDataAdapter floatAdapter) {
    return new FloatVirtualDataBuffer(delegate, floatAdapter);
  }

  @Override
  public float getFloat(long index) {
    Validator.getArgs(this, index);
    return adapter.readFloat(physicalBuffer(), index * adapter.sizeInBytes());
  }

  @Override
  public FloatDataBuffer setFloat(float value, long index) {
    Validator.setArgs(this, index);
    adapter.writeFloat(physicalBuffer(), value, index * adapter.sizeInBytes());
    return this;
  }

  @Override
  public FloatDataBuffer read(float[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = adapter.readFloat(physicalBuffer(), i * adapter.sizeInBytes());
    }
    return this;
  }

  @Override
  public FloatDataBuffer write(float[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      adapter.writeFloat(physicalBuffer(), src[j], i * adapter.sizeInBytes());
    }
    return this;
  }

  @Override
  public FloatDataBuffer offset(long index) {
    return new FloatVirtualDataBuffer(physicalBuffer().offset(index * adapter.sizeInBytes()), adapter);
  }

  @Override
  public FloatDataBuffer narrow(long size) {
    return new FloatVirtualDataBuffer(physicalBuffer().narrow(size * adapter.sizeInBytes()), adapter);
  }

  private FloatVirtualDataBuffer(ByteDataBuffer physicalBuffer, FloatDataAdapter adapter) {
    super(physicalBuffer, adapter);
    this.adapter = adapter;
  }

  private FloatDataAdapter adapter;
}
