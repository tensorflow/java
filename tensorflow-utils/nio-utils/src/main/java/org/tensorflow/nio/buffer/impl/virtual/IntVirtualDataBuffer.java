package org.tensorflow.nio.buffer.impl.virtual;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.IntDataBuffer;
import org.tensorflow.nio.buffer.adapter.IntDataAdapter;
import org.tensorflow.nio.buffer.impl.Validator;

public class IntVirtualDataBuffer extends AbstractVirtualDataBuffer<Integer, IntDataBuffer>
    implements IntDataBuffer {

  public static IntVirtualDataBuffer create(ByteDataBuffer delegate, IntDataAdapter intAdapter) {
    return new IntVirtualDataBuffer(delegate, intAdapter);
  }

  @Override
  public int getInt(long index) {
    Validator.getArgs(this, index);
    return adapter.readInt(physicalBuffer(), index * adapter.sizeInBytes());
  }

  @Override
  public IntDataBuffer setInt(int value, long index) {
    Validator.setArgs(this, index);
    adapter.writeInt(physicalBuffer(), value, index * adapter.sizeInBytes());
    return this;
  }

  @Override
  public IntDataBuffer read(int[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = adapter.readInt(physicalBuffer(), i * adapter.sizeInBytes());
    }
    return this;
  }

  @Override
  public IntDataBuffer write(int[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      adapter.writeInt(physicalBuffer(), src[j], i * adapter.sizeInBytes());
    }
    return this;
  }

  @Override
  public IntDataBuffer offset(long index) {
    return new IntVirtualDataBuffer(physicalBuffer().offset(index * adapter.sizeInBytes()), adapter);
  }

  @Override
  public IntDataBuffer narrow(long size) {
    return new IntVirtualDataBuffer(physicalBuffer().narrow(size * adapter.sizeInBytes()), adapter);
  }

  private IntVirtualDataBuffer(ByteDataBuffer physicalBuffer, IntDataAdapter adapter) {
    super(physicalBuffer, adapter);
    this.adapter = adapter;
  }

  private IntDataAdapter adapter;
}
