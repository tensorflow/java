package org.tensorflow.util.buffer.impl.virtual;

import org.tensorflow.util.buffer.ByteDataBuffer;
import org.tensorflow.util.buffer.DataBuffers;
import org.tensorflow.util.buffer.DoubleDataBuffer;
import org.tensorflow.util.buffer.adapter.DoubleDataAdapter;
import org.tensorflow.util.buffer.impl.jdk.ByteJdkDataBuffer;
import org.tensorflow.util.buffer.DoubleDataBufferTestBase;

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
      buffer.setObject((byte)((bits >> 56) & 0xFF), index);
      buffer.setObject((byte)((bits >> 48) & 0xFF), index + 1);
      buffer.setObject((byte)((bits >> 40) & 0xFF), index + 2);
    }

    @Override
    public double readDouble(ByteDataBuffer buffer, long index) {
      long byte7 = buffer.getObject(index);
      long byte6 = buffer.getObject(index + 1);
      long byte5 = buffer.getObject(index + 2);
      return Double.longBitsToDouble(((byte7 & 0xFF) << 56) | ((byte6 & 0xFF) << 48) | ((byte5 & 0xFF) << 40));
    }

    @Override
    public int sizeInBytes() {
      return 3;
    }
  }
}
