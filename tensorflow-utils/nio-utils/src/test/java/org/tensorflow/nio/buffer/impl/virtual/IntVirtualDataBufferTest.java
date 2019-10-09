package org.tensorflow.nio.buffer.impl.virtual;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffers;
import org.tensorflow.nio.buffer.IntDataBuffer;
import org.tensorflow.nio.buffer.IntDataBufferTestBase;
import org.tensorflow.nio.buffer.adapter.IntDataAdapter;
import org.tensorflow.nio.buffer.impl.jdk.ByteJdkDataBuffer;

public class IntVirtualDataBufferTest extends IntDataBufferTestBase {

  @Override
  protected IntDataBuffer allocate(long size) {
    return DataBuffers.ofInts(size, new TestIntAdapter());
  }

  @Override
  protected long maxSize() {
    return ByteJdkDataBuffer.MAX_SIZE / 2;
  }

  private static class TestIntAdapter implements IntDataAdapter {

    @Override
    public void writeInt(ByteDataBuffer buffer, int value, long index) {
      buffer.set((byte)(((value & 0x80000000) >> 24) | ((value & 0x7F) >> 7)), index);
      buffer.set((byte)(value), index + 1);
    }

    @Override
    public int readInt(ByteDataBuffer buffer, long index) {
      int msb = buffer.get(index);
      int lsb = buffer.get(index + 1);
      return ((msb & 0x80) << 24) | ((msb & 0x7F) << 7) | lsb;
    }

    @Override
    public int sizeInBytes() {
      return 2;
    }
  }
}
