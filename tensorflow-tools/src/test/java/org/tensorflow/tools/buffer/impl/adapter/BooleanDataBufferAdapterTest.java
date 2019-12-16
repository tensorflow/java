package org.tensorflow.tools.buffer.impl.adapter;

import org.tensorflow.tools.buffer.BooleanDataBuffer;
import org.tensorflow.tools.buffer.BooleanDataBufferTestBase;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffers;
import org.tensorflow.tools.buffer.layout.BooleanDataLayout;

public class BooleanDataBufferAdapterTest extends BooleanDataBufferTestBase {

  @Override
  protected BooleanDataBuffer allocate(long size) {
    return DataBuffers.ofBooleans(size, new TestBooleanLayout());
  }

  private static class TestBooleanLayout implements BooleanDataLayout {

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
