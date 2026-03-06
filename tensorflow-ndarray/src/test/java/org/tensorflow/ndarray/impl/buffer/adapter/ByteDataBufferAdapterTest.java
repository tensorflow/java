package org.tensorflow.ndarray.impl.buffer.adapter;

import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBufferTestBase;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.buffer.layout.ByteDataLayout;

public class ByteDataBufferAdapterTest extends ByteDataBufferTestBase {

  public ByteDataBuffer allocate(long size) {
    return LAYOUT.applyTo(DataBuffers.ofShorts(size * LAYOUT.scale()));
  }

  private static ByteDataLayout<ShortDataBuffer> LAYOUT = new ByteDataLayout<ShortDataBuffer>() {

    @Override
    public void writeByte(ShortDataBuffer buffer, byte value, long index) {
      buffer.setShort(value, index);
    }

    @Override
    public byte readByte(ShortDataBuffer buffer, long index) {
      return (byte)buffer.getShort(index);
    }
  };
}
