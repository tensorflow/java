package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.impl.AbstractDataBuffer;
import org.tensorflow.tools.buffer.impl.Validator;

@SuppressWarnings("unchecked")
abstract class AbstractRawDataBuffer<T, B extends DataBuffer<T>> extends AbstractDataBuffer<T> {

  public long size() {
    return memory.size();
  }

  @Override
  public boolean isReadOnly() {
    return readOnly;
  }

  public B read(Object dst, int dstLength) {
    Validator.readArgs(this, dstLength, 0, dstLength);
    memory.copyTo(UnsafeMemoryHandle.fromArray(dst, dstLength), dstLength);
    return (B)this;
  }

  public B read(Object dst, int dstLength, int offset, int length) {
    Validator.readArgs(this, dstLength, offset, length);
    memory.copyTo(UnsafeMemoryHandle.fromArray(dst, dstLength).offset(offset), length);
    return (B)this;
  }

  public B write(Object src, int srcLength) {
    Validator.writeArgs(this, srcLength, 0, srcLength);
    UnsafeMemoryHandle.fromArray(src, srcLength).copyTo(memory, srcLength);
    return (B)this;
  }

  public B write(Object src, int srcLength, int offset, int length) {
    Validator.writeArgs(this, srcLength, offset, length);
    UnsafeMemoryHandle.fromArray(src, srcLength).offset(offset).copyTo(memory, length);
    return (B)this;
  }

  @Override
  public B copyTo(DataBuffer<T> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof AbstractRawDataBuffer) {
      AbstractRawDataBuffer<?, ?> unsafeDst = (AbstractRawDataBuffer<?, ?>)dst;
      memory.copyTo(unsafeDst.memory, size);
    } else {
      slowCopyTo(dst, size);
    }
    return (B)this;
  }

  @Override
  public B offset(long index) {
    Validator.offsetArgs(this, index);
    return instantiate(memory.offset(index));
  }

  @Override
  public B narrow(long size) {
    Validator.narrowArgs(this, size);
    return instantiate(memory.narrow(size));
  }

  protected final UnsafeMemoryHandle memory;
  protected final boolean readOnly;

  protected abstract B instantiate(UnsafeMemoryHandle region);

  AbstractRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    this.memory = memory;
    this.readOnly = readOnly;
  }
}
