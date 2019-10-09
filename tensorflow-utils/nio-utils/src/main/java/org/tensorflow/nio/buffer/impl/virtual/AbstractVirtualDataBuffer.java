package org.tensorflow.nio.buffer.impl.virtual;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.adapter.DataAdapter;
import org.tensorflow.nio.buffer.impl.AbstractDataBuffer;
import org.tensorflow.nio.buffer.impl.Validator;

@SuppressWarnings("unchecked")
abstract class AbstractVirtualDataBuffer<T, B extends DataBuffer<T>> extends AbstractDataBuffer<T> {

  @Override
  public long size() {
    return physicalBuffer.size() / adapter.sizeInBytes();
  }

  @Override
  public boolean isReadOnly() {
    return physicalBuffer.isReadOnly();
  }

  @Override
  public T get(long index) {
    Validator.getArgs(this, index);
    return adapter.readValue(physicalBuffer, index * adapter.sizeInBytes());
  }

  @Override
  public B set(T value, long index) {
    Validator.setArgs(this, index);
    adapter.writeValue(physicalBuffer, value, index * adapter.sizeInBytes());
    return (B)this;
  }

  @Override
  public B copyTo(DataBuffer<T> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    slowCopyTo(dst, size); // FIXME anyway to speed up this?
    return (B)this;
  }

  AbstractVirtualDataBuffer(ByteDataBuffer physicalBuffer, DataAdapter<T> adapter) {
    this.physicalBuffer = physicalBuffer;
    this.adapter = adapter;
  }

  DataAdapter<T> adapter() {
    return adapter;
  }

  ByteDataBuffer physicalBuffer() {
    return physicalBuffer;
  }

  private final ByteDataBuffer physicalBuffer;
  private final DataAdapter<T> adapter;
}
