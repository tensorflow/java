package org.tensorflow.tools.buffer.impl.virtual;

import org.tensorflow.tools.buffer.BooleanDataBuffer;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffers;
import org.tensorflow.tools.buffer.adapter.BooleanDataAdapter;
import org.tensorflow.tools.buffer.impl.jdk.ByteJdkDataBuffer;
import org.tensorflow.tools.buffer.BooleanDataBufferTestBase;

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
