package org.tensorflow.ndarray.impl.buffer.adapter;

import org.tensorflow.ndarray.impl.buffer.Validator;
import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.buffer.layout.ByteDataLayout;

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
  public ByteDataBuffer copyTo(DataBuffer<Byte> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof ByteDataBuffer) {
      ByteDataBuffer byteDst = (ByteDataBuffer)dst;
      for (long idx = 0L; idx < size; ++idx) {
        byteDst.setByte(getByte(idx), idx);
      }
      return this;
    }
    return slowCopyTo(dst, size);
  }

  @Override
  public IntDataBuffer asInts() {
    throw new IllegalStateException("Byte buffers with layout cannot be converted");
  }

  @Override
  public ShortDataBuffer asShorts() {
    throw new IllegalStateException("Byte buffers with layout cannot be converted");
  }

  @Override
  public LongDataBuffer asLongs() {
    throw new IllegalStateException("Byte buffers with layout cannot be converted");
  }

  @Override
  public FloatDataBuffer asFloats() {
    throw new IllegalStateException("Byte buffers with layout cannot be converted");
  }

  @Override
  public DoubleDataBuffer asDoubles() {
    throw new IllegalStateException("Byte buffers with layout cannot be converted");
  }

  @Override
  public BooleanDataBuffer asBooleans() {
    throw new IllegalStateException("Byte buffers with layout cannot be converted");
  }

  @Override
  @SuppressWarnings("unchecked")
  public ByteDataBuffer offset(long index) {
    return new ByteDataBufferAdapter<>((S)buffer().offset(index * layout.scale()), layout);
  }

  @Override
  @SuppressWarnings("unchecked")
  public ByteDataBuffer narrow(long size) {
    return new ByteDataBufferAdapter<>((S)buffer().narrow(size * layout.scale()), layout);
  }

  @Override
  @SuppressWarnings("unchecked")
  public ByteDataBuffer slice(long index, long size) {
    return new ByteDataBufferAdapter<>((S)buffer().slice(index * layout.scale(), size * layout.scale()), layout);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ByteDataBuffer)) {
      return super.equals(obj);
    }
    ByteDataBuffer other = (ByteDataBuffer)obj;
    if (other.size() != size()) {
      return false;
    }
    for (long idx = 0L; idx < size(); ++idx) {
      if (other.getByte(idx) != getByte(idx)) {
        return false;
      }
    }
    return true;
  }

  ByteDataBufferAdapter(S buffer, ByteDataLayout<S> layout) {
    super(buffer, layout);
    this.layout = layout;
  }

  private ByteDataLayout<S> layout;
}
