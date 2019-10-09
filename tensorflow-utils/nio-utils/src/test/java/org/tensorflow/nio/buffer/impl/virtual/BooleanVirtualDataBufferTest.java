package org.tensorflow.nio.buffer.impl.virtual;

import org.tensorflow.nio.buffer.BooleanDataBuffer;
import org.tensorflow.nio.buffer.BooleanDataBufferTestBase;
import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffers;
import org.tensorflow.nio.buffer.adapter.BooleanDataAdapter;
import org.tensorflow.nio.buffer.impl.jdk.ByteJdkDataBuffer;

public class BooleanVirtualDataBufferTest extends BooleanDataBufferTestBase {

  @Override
  protected BooleanDataBuffer allocate(long size) {
    return DataBuffers.ofBooleans(size, new TestBooleanAdapter());
  }

  @Override
  protected long maxSize() {
    return ByteJdkDataBuffer.MAX_SIZE;
  }

  private static class TestBooleanAdapter implements BooleanDataAdapter {

    @Override
    public void writeBoolean(ByteDataBuffer buffer, boolean value, long index) {
      buffer.set((byte)(value ? 1 : 0), index);
    }

    @Override
    public boolean readBoolean(ByteDataBuffer buffer, long index) {
      return buffer.get(index) > 0;
    }

    @Override
    public int sizeInBytes() {
      return 1;
    }
  }
}
