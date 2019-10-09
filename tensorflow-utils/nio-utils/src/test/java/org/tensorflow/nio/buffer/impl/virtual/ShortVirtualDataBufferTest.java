package org.tensorflow.nio.buffer.impl.virtual;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffers;
import org.tensorflow.nio.buffer.ShortDataBuffer;
import org.tensorflow.nio.buffer.ShortDataBufferTestBase;
import org.tensorflow.nio.buffer.adapter.ShortDataAdapter;
import org.tensorflow.nio.buffer.impl.jdk.ByteJdkDataBuffer;

public class ShortVirtualDataBufferTest extends ShortDataBufferTestBase {

  @Override
  protected long maxSize() {
    return ByteJdkDataBuffer.MAX_SIZE;
  }

  private static class TestShort8Adapter implements ShortDataAdapter {

    @Override
    public void writeShort(ByteDataBuffer buffer, short value, long index) {
      buffer.set((byte)(((value & 0x8000) >> 8) | (value & 0x7F)), index);
    }

    @Override
    public short readShort(ByteDataBuffer buffer, long index) {
      int b = buffer.get(index);
      return (short)(((b & 0x80) << 8) | (b & 0x7F));
    }

    @Override
    public int sizeInBytes() {
      return 1;
    }
  }

  public ShortDataBuffer allocate(long size) {
    return DataBuffers.ofShorts(size, new TestShort8Adapter());
  }
}
