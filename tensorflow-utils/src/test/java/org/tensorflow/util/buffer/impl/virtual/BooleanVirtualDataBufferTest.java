package org.tensorflow.util.buffer.impl.virtual;

import org.tensorflow.util.buffer.BooleanDataBuffer;
import org.tensorflow.util.buffer.ByteDataBuffer;
import org.tensorflow.util.buffer.DataBuffers;
import org.tensorflow.util.buffer.adapter.BooleanDataAdapter;
import org.tensorflow.util.buffer.impl.jdk.ByteJdkDataBuffer;
import org.tensorflow.util.buffer.BooleanDataBufferTestBase;

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
      buffer.setObject((byte)(value ? 1 : 0), index);
    }

    @Override
    public boolean readBoolean(ByteDataBuffer buffer, long index) {
      return buffer.getObject(index) > 0;
    }

    @Override
    public int sizeInBytes() {
      return 1;
    }
  }
}
