package org.tensorflow.nio.buffer.impl.virtual;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.ShortDataBuffer;
import org.tensorflow.nio.buffer.adapter.ShortDataAdapter;
import org.tensorflow.nio.buffer.impl.Validator;

public class ShortVirtualDataBuffer extends AbstractVirtualDataBuffer<Short, ShortDataBuffer>
    implements ShortDataBuffer {

  public static ShortVirtualDataBuffer create(ByteDataBuffer delegate, ShortDataAdapter shortAdapter) {
    return new ShortVirtualDataBuffer(delegate, shortAdapter);
  }

  @Override
  public short getShort(long index) {
    Validator.getArgs(this, index);
    return adapter.readShort(physicalBuffer(), index * adapter.sizeInBytes());
  }

  @Override
  public ShortDataBuffer setShort(short value, long index) {
    Validator.setArgs(this, index);
    adapter.writeShort(physicalBuffer(), value, index * adapter.sizeInBytes());
    return this;
  }

  @Override
  public ShortDataBuffer read(short[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = adapter.readShort(physicalBuffer(), i * adapter.sizeInBytes());
    }
    return this;
  }

  @Override
  public ShortDataBuffer write(short[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      adapter.writeShort(physicalBuffer(), src[j], i * adapter.sizeInBytes());
    }
    return this;
  }

  @Override
  public ShortDataBuffer offset(long index) {
    return new ShortVirtualDataBuffer(physicalBuffer().offset(index * adapter.sizeInBytes()), adapter);
  }

  @Override
  public ShortDataBuffer narrow(long size) {
    return new ShortVirtualDataBuffer(physicalBuffer().narrow(size * adapter.sizeInBytes()), adapter);
  }

  private ShortVirtualDataBuffer(ByteDataBuffer physicalBuffer, ShortDataAdapter adapter) {
    super(physicalBuffer, adapter);
    this.adapter = adapter;
  }

  private ShortDataAdapter adapter;
}
