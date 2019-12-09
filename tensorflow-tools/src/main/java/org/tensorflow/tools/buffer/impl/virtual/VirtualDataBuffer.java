package org.tensorflow.tools.buffer.impl.virtual;

import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.adapter.DataAdapter;

@SuppressWarnings("unchecked")
public class VirtualDataBuffer<T> extends AbstractVirtualDataBuffer<T, DataBuffer<T>> {

  public static <T> VirtualDataBuffer<T> create(ByteDataBuffer delegate, DataAdapter<T> adapter) {
    return new VirtualDataBuffer<>(delegate, adapter);
  }

  @Override
  public DataBuffer<T> offset(long index) {
    return new VirtualDataBuffer<>(physicalBuffer().offset(index * adapter().sizeInBytes()), adapter());
  }

  @Override
  public DataBuffer<T> narrow(long size) {
    return new VirtualDataBuffer<>(physicalBuffer().narrow(size * adapter().sizeInBytes()), adapter());
  }

  private VirtualDataBuffer(ByteDataBuffer physicalBuffer, DataAdapter<T> adapter) {
    super(physicalBuffer, adapter);
  }
}
