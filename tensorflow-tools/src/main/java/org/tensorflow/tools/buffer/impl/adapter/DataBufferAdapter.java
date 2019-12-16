package org.tensorflow.tools.buffer.impl.adapter;

import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.layout.DataLayout;

@SuppressWarnings("unchecked")
class DataBufferAdapter<T> extends AbstractDataBufferAdapter<T, DataBuffer<T>> {

  @Override
  public DataBuffer<T> offset(long index) {
    return new DataBufferAdapter<>(buffer().offset(index * layout().sizeInBytes()), layout());
  }

  @Override
  public DataBuffer<T> narrow(long size) {
    return new DataBufferAdapter<>(buffer().narrow(size * layout().sizeInBytes()), layout());
  }

  DataBufferAdapter(ByteDataBuffer physicalBuffer, DataLayout<T> layout) {
    super(physicalBuffer, layout);
  }
}
