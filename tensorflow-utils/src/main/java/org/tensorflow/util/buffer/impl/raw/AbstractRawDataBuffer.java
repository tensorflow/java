package org.tensorflow.util.buffer.impl.raw;

import org.tensorflow.util.buffer.DataBuffer;
import org.tensorflow.util.buffer.impl.AbstractDataBuffer;
import org.tensorflow.util.buffer.impl.Validator;

abstract class AbstractRawDataBuffer<T, B extends DataBuffer<T>> extends AbstractDataBuffer<T> {

  /*
   * The maximum size for a buffer of this type, i.e. the maximum number of bytes it can store.
   * <p>
   * As the maximum size may vary depending on the JVM implementation and on the platform, this
   * property returns a value that is safe for most of them.
   */
  static long MAX_32BITS = Integer.MAX_VALUE - 10;
  static long MAX_64BITS = Long.MAX_VALUE - 10;

  public long size() {
    return memory.size();
  }

  @Override
  public boolean isReadOnly() {
    return readOnly;
  }

  @Override
  @SuppressWarnings("unchecked")
  public B copyTo(DataBuffer<T> dst, long size) {
    Validator.copyToArgs(this, dst, size);
    if (dst instanceof AbstractRawDataBuffer) {
      AbstractRawDataBuffer unsafeDst = (AbstractRawDataBuffer)dst;
      memory.copyTo(unsafeDst.memory, size);
    } else {
      slowCopyTo(dst, size);
    }
    return (B)this;
  }

  @Override
  public B offset(long index) {
    Validator.offsetArgs(this, index);
    return instantiate(memory.offset(index), isReadOnly());
  }

  @Override
  public B narrow(long size) {
    Validator.narrowArgs(this, size);
    return instantiate(memory.narrow(size), isReadOnly());
  }

  protected abstract B instantiate(UnsafeMemoryHandle region, boolean readOnly);

  final UnsafeMemoryHandle memory;

  AbstractRawDataBuffer(UnsafeMemoryHandle memory, boolean readOnly) {
    this.memory = memory;
    this.readOnly = readOnly;
  }

  private final boolean readOnly;
}
