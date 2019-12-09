package org.tensorflow.tools.buffer.impl.virtual;

import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffers;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.adapter.LongDataAdapter;
import org.tensorflow.tools.buffer.impl.jdk.ByteJdkDataBuffer;
import org.tensorflow.tools.buffer.LongDataBufferTestBase;

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
      buffer.setObject((byte)(((value >> 56) & 0x80) | ((value >> 16) & 0x7F)), index);
      buffer.setObject((byte)((value >> 8) & 0xFF), index + 1);
      buffer.setObject((byte)(value & 0xFF), index + 2);
    }

    @Override
    public long readLong(ByteDataBuffer buffer, long index) {
      long msb = buffer.getObject(index);
      long midb = buffer.getObject(index + 1);
      long lsb = buffer.getObject(index + 2);
      return ((msb & 0x80) << 56) | ((msb & 0x7F) << 16) | ((midb & 0xFF) << 8) | (lsb & 0xFF);
    }

    @Override
    public int sizeInBytes() {
      return 3;
    }
  }
}
