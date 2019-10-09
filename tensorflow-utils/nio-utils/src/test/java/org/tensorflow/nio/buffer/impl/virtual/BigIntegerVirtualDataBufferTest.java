package org.tensorflow.nio.buffer.impl.virtual;

import java.math.BigInteger;
import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.DataBufferTestBase;
import org.tensorflow.nio.buffer.DataBuffers;
import org.tensorflow.nio.buffer.adapter.DataAdapter;
import org.tensorflow.nio.buffer.impl.jdk.ByteJdkDataBuffer;

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
      buffer.set(bytes.length > 2 ? bytes[2] : 0, index);
      buffer.set(bytes.length > 1 ? bytes[1] : 0, index + 1);
      buffer.set(bytes[0], index + 2);
    }

    @Override
    public BigInteger readValue(ByteDataBuffer buffer, long index) {
      byte byte2 = buffer.get(index);
      byte byte1 = buffer.get(index + 1);
      byte byte0 = buffer.get(index + 2);
      return new BigInteger(new byte[] { byte2, byte1, byte0 });
    }

    @Override
    public int sizeInBytes() {
      return 3;
    }
  }
}
