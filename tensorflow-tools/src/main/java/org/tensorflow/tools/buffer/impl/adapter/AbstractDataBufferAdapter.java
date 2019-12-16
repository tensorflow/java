package org.tensorflow.tools.buffer.impl.adapter;

import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.impl.AbstractDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;
import org.tensorflow.tools.buffer.layout.DataLayout;

@SuppressWarnings("unchecked")
abstract class AbstractDataBufferAdapter<T, B extends DataBuffer<T>> extends AbstractDataBuffer<T> {

  @Override
  public long size() {
    return buffer.size() / layout.sizeInBytes();
  }

  @Override
  public boolean isReadOnly() {
    return buffer.isReadOnly();
  }

  @Override
  public T getObject(long index) {
    Validator.getArgs(this, index);
    return layout.readValue(buffer, index * layout.sizeInBytes());
  }

  @Override
  public B setObject(T value, long index) {
    Validator.setArgs(this, index);
    layout.writeValue(buffer, value, index * layout.sizeInBytes());
    return (B)this;
  }

  @Override
  public B copyTo(DataBuffer<T> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    slowCopyTo(dst, size); // FIXME anyway to speed up this?
    return (B)this;
  }

  AbstractDataBufferAdapter(ByteDataBuffer buffer, DataLayout<T> layout) {
    this.buffer = buffer;
    this.layout = layout;
  }

  DataLayout<T> layout() {
    return layout;
  }

  ByteDataBuffer buffer() {
    return buffer;
  }

  private final ByteDataBuffer buffer;
  private final DataLayout<T> layout;
}
