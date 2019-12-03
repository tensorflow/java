package org.tensorflow.util.buffer.impl.virtual;

import java.math.BigInteger;
import org.tensorflow.util.buffer.ByteDataBuffer;
import org.tensorflow.util.buffer.DataBuffer;
import org.tensorflow.util.buffer.DataBuffers;
import org.tensorflow.util.buffer.adapter.DataAdapter;
import org.tensorflow.util.buffer.impl.jdk.ByteJdkDataBuffer;
import org.tensorflow.util.buffer.DataBufferTestBase;

public class BigIntegerVirtualDataBufferTest extends DataBufferTestBase<BigInteger> {

  @Override
  protected DataBuffer<BigInteger> allocate(long size) {
    return DataBuffers.of(size, new TestBigIntegerAdapter());
  }

  @Override
  protected long maxSize() {
    return ByteJdkDataBuffer.MAX_SIZE / 3;
  }

  @Override
  protected BigInteger valueOf(Long val) {
    return BigInteger.valueOf(val);
  }

  private static class TestBigIntegerAdapter implements DataAdapter<BigInteger> {

    @Override
    public void writeValue(ByteDataBuffer buffer, BigInteger value, long index) {
      byte[] bytes = value.toByteArray();
      buffer.setObject(bytes.length > 2 ? bytes[2] : 0, index);
      buffer.setObject(bytes.length > 1 ? bytes[1] : 0, index + 1);
      buffer.setObject(bytes[0], index + 2);
    }

    @Override
    public BigInteger readValue(ByteDataBuffer buffer, long index) {
      byte byte2 = buffer.getObject(index);
      byte byte1 = buffer.getObject(index + 1);
      byte byte0 = buffer.getObject(index + 2);
      return new BigInteger(new byte[] { byte2, byte1, byte0 });
    }

    @Override
    public int sizeInBytes() {
      return 3;
    }
  }
}
