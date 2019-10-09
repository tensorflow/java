package org.tensorflow.nio.buffer.impl.virtual;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffers;
import org.tensorflow.nio.buffer.FloatDataBuffer;
import org.tensorflow.nio.buffer.FloatDataBufferTestBase;
import org.tensorflow.nio.buffer.adapter.FloatDataAdapter;
import org.tensorflow.nio.buffer.impl.jdk.ByteJdkDataBuffer;

public class FloatVirtualDataBufferTest extends FloatDataBufferTestBase {

  @Override
  protected long maxSize() {
    return ByteJdkDataBuffer.MAX_SIZE / 2;
  }

  private static class TestFloat16Adapter implements FloatDataAdapter {

    @Override
    public void writeFloat(ByteDataBuffer buffer, float value, long index) {
      int bits = Float.floatToIntBits(value);
      buffer.set((byte)((bits >> 24) & 0xFF), index);
      buffer.set((byte)((bits >> 16) & 0xFF), index + 1);
    }

    @Override
    public float readFloat(ByteDataBuffer buffer, long index) {
      int byte3 = buffer.get(index);
      int byte2 = buffer.get(index + 1);
      return Float.intBitsToFloat(((byte3 & 0xFF) << 24) | ((byte2 & 0xFF) << 16));
    }

    @Override
    public int sizeInBytes() {
      return 2;
    }
  }

  public FloatDataBuffer allocate(long size) {
    return DataBuffers.ofFloats(size, new TestFloat16Adapter());
  }
}
