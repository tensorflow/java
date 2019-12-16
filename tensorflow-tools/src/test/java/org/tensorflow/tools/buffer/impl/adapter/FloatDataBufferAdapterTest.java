package org.tensorflow.tools.buffer.impl.adapter;

import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffers;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.FloatDataBufferTestBase;
import org.tensorflow.tools.buffer.layout.FloatDataLayout;

public class FloatDataBufferAdapterTest extends FloatDataBufferTestBase {

  @Override
  protected long maxSize() {
    return super.maxSize() / 2;
  }

  private static class TestFloat16Layout implements FloatDataLayout {

    @Override
    public void writeFloat(ByteDataBuffer buffer, float value, long index) {
      int bits = Float.floatToIntBits(value);
      buffer.setObject((byte)((bits >> 24) & 0xFF), index);
      buffer.setObject((byte)((bits >> 16) & 0xFF), index + 1);
    }

    @Override
    public float readFloat(ByteDataBuffer buffer, long index) {
      int byte3 = buffer.getObject(index);
      int byte2 = buffer.getObject(index + 1);
      return Float.intBitsToFloat(((byte3 & 0xFF) << 24) | ((byte2 & 0xFF) << 16));
    }

    @Override
    public int sizeInBytes() {
      return 2;
    }
  }

  public FloatDataBuffer allocate(long size) {
    return DataBuffers.ofFloats(size, new TestFloat16Layout());
  }
}
