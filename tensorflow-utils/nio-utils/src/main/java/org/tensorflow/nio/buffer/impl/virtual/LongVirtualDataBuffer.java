package org.tensorflow.nio.buffer.impl.virtual;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.LongDataBuffer;
import org.tensorflow.nio.buffer.adapter.LongDataAdapter;
import org.tensorflow.nio.buffer.impl.Validator;

public class LongVirtualDataBuffer extends AbstractVirtualDataBuffer<Long, LongDataBuffer>
    implements LongDataBuffer {

  public static LongVirtualDataBuffer create(ByteDataBuffer delegate, LongDataAdapter longAdapter) {
    return new LongVirtualDataBuffer(delegate, longAdapter);
  }

  @Override
  public long getLong(long index) {
    Validator.getArgs(this, index);
    return adapter.readLong(physicalBuffer(), index * adapter.sizeInBytes());
  }

  @Override
  public LongDataBuffer setLong(long value, long index) {
    Validator.setArgs(this, index);
    adapter.writeLong(physicalBuffer(), value, index * adapter.sizeInBytes());
    return this;
  }

  @Override
  public LongDataBuffer read(long[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = adapter.readLong(physicalBuffer(), i * adapter.sizeInBytes());
    }
    return this;
  }

  @Override
  public LongDataBuffer write(long[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      adapter.writeLong(physicalBuffer(), src[j], i * adapter.sizeInBytes());
    }
    return this;
  }

  @Override
  public LongDataBuffer offset(long index) {
    return new LongVirtualDataBuffer(physicalBuffer().offset(index * adapter.sizeInBytes()), adapter);
  }

  @Override
  public LongDataBuffer narrow(long size) {
    return new LongVirtualDataBuffer(physicalBuffer().narrow(size * adapter.sizeInBytes()), adapter);
  }

  private LongVirtualDataBuffer(ByteDataBuffer physicalBuffer, LongDataAdapter adapter) {
    super(physicalBuffer, adapter);
    this.adapter = adapter;
  }

  private LongDataAdapter adapter;
}
