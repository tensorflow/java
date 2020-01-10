package org.tensorflow.tools.buffer.impl.adapter;

import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;
import org.tensorflow.tools.buffer.layout.ByteDataLayout;

class ByteDataBufferAdapter<S extends DataBuffer<?>> extends AbstractDataBufferAdapter<S, Byte, ByteDataBuffer>
    implements ByteDataBuffer {

  @Override
  public byte getByte(long index) {
    Validator.getArgs(this, index);
    return layout.readByte(buffer(), index * layout.scale());
  }

  @Override
  public ByteDataBuffer setByte(byte value, long index) {
    Validator.setArgs(this, index);
    layout.writeByte(buffer(), value, index * layout.scale());
    return this;
  }

  @Override
  public ByteDataBuffer read(byte[] dst, int offset, int length) {
    Validator.readArgs(this, dst.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      dst[j] = layout.readByte(buffer(), i * layout.scale());
    }
    return this;
  }

  @Override
  public ByteDataBuffer write(byte[] src, int offset, int length) {
    Validator.writeArgs(this, src.length, offset, length);
    for (int i = 0, j = offset; i < length; ++i, ++j) {
      layout.writeByte(buffer(), src[j], i * layout.scale());
    }
    return this;
  }

  @Override
  @SuppressWarnings("unchecked")
  public ByteDataBuffer offset(long index) {
    return new ByteDataBufferAdapter(buffer().offset(index * layout.scale()), layout);
  }

  @Override
  @SuppressWarnings("unchecked")
  public ByteDataBuffer narrow(long size) {
    return new ByteDataBufferAdapter(buffer().narrow(size * layout.scale()), layout);
  }

  ByteDataBufferAdapter(S buffer, ByteDataLayout<S> layout) {
    super(buffer, layout);
    this.layout = layout;
  }

  private ByteDataLayout<S> layout;
}
