package org.tensorflow.tools.buffer.impl;

import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.DataBufferWindow;

public abstract class AbstractDataBufferWindow<B extends DataBuffer<?>> implements DataBufferWindow<B> {

  @Override
  public final long offset() {
    return offset;
  }

  @Override
  public final long size() {
    return windowBuffer.size();
  }

  @Override
  public final DataBufferWindow<B> slideTo(long index) {
    if (index < 0 || index > maxOffset) {
      throw new IndexOutOfBoundsException();
    }
    offset(index);
    offset = index;
    return this;
  }

  @Override
  public final DataBufferWindow<B> slideOf(long step) {
    return slideTo(offset + step);
  }

  @Override
  public final B buffer() {
    return windowBuffer;
  }

  protected abstract void offset(long offset);

  protected AbstractDataBufferWindow(B windowBuffer, long bufferLimit) {
    this.windowBuffer = windowBuffer;
    maxOffset = bufferLimit - windowBuffer.size();
  }

  private final B windowBuffer;
  private final long maxOffset;
  private long offset = 0;
}
