package org.tensorflow.nio.buffer.impl.virtual;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DoubleDataBuffer;
import org.tensorflow.nio.buffer.adapter.DoubleDataAdapter;
import org.tensorflow.nio.buffer.impl.Validator;

public class DoubleVirtualDataBuffer extends AbstractVirtualDataBuffer<Double, DoubleDataBuffer>
    implements DoubleDataBuffer {

  public static DoubleVirtualDataBuffer create(ByteDataBuffer delegate, DoubleDataAdapter doubleAdapter) {
    return new DoubleVirtualDataBuffer(delegate, doubleAdapter);
  }

  @Override
  public double getDouble(long index) {
    Validator.getArgs(this, index);
    return adapter.readDouble(physicalBuffer(), index * adapter.sizeInBytes());
  }

  @Override
  public DoubleDataBuffer setDouble(double value, long index) {
    Validator.setArgs(this, index);
    adapter.writeDouble(physicalBuffer(), value, index * adapter.sizeInBytes());
    return this;
  }

  @Override
  public DoubleDataBuffer read(double[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = adapter.readDouble(physicalBuffer(), i * adapter.sizeInBytes());
    }
    return this;
  }

  @Override
  public DoubleDataBuffer write(double[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      adapter.writeDouble(physicalBuffer(), src[j], i * adapter.sizeInBytes());
    }
    return this;
  }

  @Override
  public DoubleDataBuffer offset(long index) {
    return new DoubleVirtualDataBuffer(physicalBuffer().offset(index * adapter.sizeInBytes()), adapter);
  }

  @Override
  public DoubleDataBuffer narrow(long size) {
    return new DoubleVirtualDataBuffer(physicalBuffer().narrow(size * adapter.sizeInBytes()), adapter);
  }

  private DoubleVirtualDataBuffer(ByteDataBuffer physicalBuffer, DoubleDataAdapter adapter) {
    super(physicalBuffer, adapter);
    this.adapter = adapter;
  }

  private DoubleDataAdapter adapter;
}
