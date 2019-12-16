package org.tensorflow.tools.buffer.impl.adapter;

import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;
import org.tensorflow.tools.buffer.layout.IntDataLayout;

class IntDataBufferAdapter extends AbstractDataBufferAdapter<Integer, IntDataBuffer>
    implements IntDataBuffer {

  @Override
  public int getInt(long index) {
    Validator.getArgs(this, index);
    return layout.readInt(buffer(), index * layout.sizeInBytes());
  }

  @Override
  public IntDataBuffer setInt(int value, long index) {
    Validator.setArgs(this, index);
    layout.writeInt(buffer(), value, index * layout.sizeInBytes());
    return this;
  }

  @Override
  public IntDataBuffer read(int[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = layout.readInt(buffer(), i * layout.sizeInBytes());
    }
    return this;
  }

  @Override
  public IntDataBuffer write(int[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      layout.writeInt(buffer(), src[j], i * layout.sizeInBytes());
    }
    return this;
  }

  @Override
  public IntDataBuffer offset(long index) {
    return new IntDataBufferAdapter(buffer().offset(index * layout.sizeInBytes()), layout);
  }

  @Override
  public IntDataBuffer narrow(long size) {
    return new IntDataBufferAdapter(buffer().narrow(size * layout.sizeInBytes()), layout);
  }

  IntDataBufferAdapter(ByteDataBuffer physicalBuffer, IntDataLayout layout) {
    super(physicalBuffer, layout);
    this.layout = layout;
  }

  private IntDataLayout layout;
}
