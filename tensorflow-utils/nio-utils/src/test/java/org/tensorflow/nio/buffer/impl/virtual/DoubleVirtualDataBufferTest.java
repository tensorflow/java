package org.tensorflow.nio.buffer.impl.virtual;

import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffers;
import org.tensorflow.nio.buffer.DoubleDataBuffer;
import org.tensorflow.nio.buffer.DoubleDataBufferTestBase;
import org.tensorflow.nio.buffer.adapter.DoubleDataAdapter;
import org.tensorflow.nio.buffer.impl.jdk.ByteJdkDataBuffer;

public class DoubleVirtualDataBufferTest extends DoubleDataBufferTestBase {

  @Override
  protected DoubleDataBuffer allocate(long size) {
    return DataBuffers.ofDoubles(size, new TestDoubleAdapter());
  }

  @Override
  protected long maxSize() {
    return ByteJdkDataBuffer.MAX_SIZE / 3;
  }

  private static class TestDoubleAdapter implements DoubleDataAdapter {

    @Override
    public void writeDouble(ByteDataBuffer buffer, double value, long index) {
      long bits = Double.doubleToLongBits(value);
      buffer.set((byte)((bits >> 56) & 0xFF), index);
      buffer.set((byte)((bits >> 48) & 0xFF), index + 1);
      buffer.set((byte)((bits >> 40) & 0xFF), index + 2);
    }

    @Override
    public double readDouble(ByteDataBuffer buffer, long index) {
      long byte7 = buffer.get(index);
      long byte6 = buffer.get(index + 1);
      long byte5 = buffer.get(index + 2);
      return Double.longBitsToDouble(((byte7 & 0xFF) << 56) | ((byte6 & 0xFF) << 48) | ((byte5 & 0xFF) << 40));
    }

    @Override
    public int sizeInBytes() {
      return 3;
    }
  }
}
