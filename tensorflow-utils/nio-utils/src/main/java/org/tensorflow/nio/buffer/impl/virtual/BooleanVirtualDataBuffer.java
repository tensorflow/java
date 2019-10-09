package org.tensorflow.nio.buffer.impl.virtual;

import org.tensorflow.nio.buffer.BooleanDataBuffer;
import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.adapter.BooleanDataAdapter;
import org.tensorflow.nio.buffer.impl.Validator;

public class BooleanVirtualDataBuffer extends AbstractVirtualDataBuffer<Boolean, BooleanDataBuffer>
    implements BooleanDataBuffer {

  public static BooleanVirtualDataBuffer create(ByteDataBuffer delegate, BooleanDataAdapter booleanAdapter) {
    return new BooleanVirtualDataBuffer(delegate, booleanAdapter);
  }

  @Override
  public boolean getBoolean(long index) {
    Validator.getArgs(this, index);
    return adapter.readBoolean(physicalBuffer(), index * adapter.sizeInBytes());
  }

  @Override
  public BooleanDataBuffer setBoolean(boolean value, long index) {
    Validator.setArgs(this, index);
    adapter.writeBoolean(physicalBuffer(), value, index * adapter.sizeInBytes());
    return this;
  }

  @Override
  public BooleanDataBuffer read(boolean[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = adapter.readBoolean(physicalBuffer(), i * adapter.sizeInBytes());
    }
    return this;
  }

  @Override
  public BooleanDataBuffer write(boolean[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      adapter.writeBoolean(physicalBuffer(), src[j], i * adapter.sizeInBytes());
    }
    return this;
  }

  @Override
  public BooleanDataBuffer offset(long index) {
    return new BooleanVirtualDataBuffer(physicalBuffer().offset(index * adapter.sizeInBytes()), adapter);
  }

  @Override
  public BooleanDataBuffer narrow(long size) {
    return new BooleanVirtualDataBuffer(physicalBuffer().narrow(size * adapter.sizeInBytes()), adapter);
  }

  private BooleanVirtualDataBuffer(ByteDataBuffer physicalBuffer, BooleanDataAdapter adapter) {
    super(physicalBuffer, adapter);
    this.adapter = adapter;
  }

  private BooleanDataAdapter adapter;
}
