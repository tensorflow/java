package org.tensorflow.ndarray.impl.buffer.raw;

import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.impl.buffer.AbstractDataBufferWindow;

final class RawDataBufferWindow<B extends DataBuffer<?>> extends AbstractDataBufferWindow<B> {

  @Override
  public void offset(long offset) {
    windowMemory.rebase(offset);
  }

  <R extends AbstractRawDataBuffer<?, B>> RawDataBufferWindow(R windowBuffer, long bufferLimit) {
    super((B)windowBuffer, bufferLimit);
    this.windowMemory = windowBuffer.memory;
  }

  private final UnsafeMemoryHandle windowMemory;
}
