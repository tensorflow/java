package org.tensorflow.nio.buffer.impl.virtual;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffers;
import org.tensorflow.nio.buffer.LongDataBuffer;
import org.tensorflow.nio.buffer.LongDataBufferTestBase;
import org.tensorflow.nio.buffer.adapter.LongDataAdapter;
import org.tensorflow.nio.buffer.impl.jdk.ByteJdkDataBuffer;

public class LongVirtualDataBufferTest extends LongDataBufferTestBase {

  @Override
  protected LongDataBuffer allocate(long size) {
    return DataBuffers.ofLongs(size, new TestLongAdapter());
  }

  @Override
  protected long maxSize() {
    return ByteJdkDataBuffer.MAX_SIZE / 3;
  }

  private static class TestLongAdapter implements LongDataAdapter {

    @Override
    public void writeLong(ByteDataBuffer buffer, long value, long index) {
      buffer.set((byte)(((value >> 56) & 0x80) | ((value >> 16) & 0x7F)), index);
      buffer.set((byte)((value >> 8) & 0xFF), index + 1);
      buffer.set((byte)(value & 0xFF), index + 2);
    }

    @Override
    public long readLong(ByteDataBuffer buffer, long index) {
      long msb = buffer.get(index);
      long midb = buffer.get(index + 1);
      long lsb = buffer.get(index + 2);
      return ((msb & 0x80) << 56) | ((msb & 0x7F) << 16) | ((midb & 0xFF) << 8) | (lsb & 0xFF);
    }

    @Override
    public int sizeInBytes() {
      return 3;
    }
  }
}
