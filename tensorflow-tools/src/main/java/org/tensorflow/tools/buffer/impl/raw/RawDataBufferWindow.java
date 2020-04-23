package org.tensorflow.tools.buffer.impl.raw;

import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.impl.AbstractDataBufferWindow;

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
