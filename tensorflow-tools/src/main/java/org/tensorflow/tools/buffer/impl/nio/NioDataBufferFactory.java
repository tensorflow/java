package org.tensorflow.tools.buffer.impl.nio;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.ShortDataBuffer;

/**
 * Factory of JDK NIO-based data buffers
 */
public class NioDataBufferFactory {

  public static ByteDataBuffer create(ByteBuffer buffer) {
    return new ByteNioDataBuffer(buffer);
  }

  public static DoubleDataBuffer create(DoubleBuffer buffer) {
    return new DoubleNioDataBuffer(buffer);
  }

  public static FloatDataBuffer create(FloatBuffer buffer) {
    return new FloatNioDataBuffer(buffer);
  }

  public static IntDataBuffer create(IntBuffer buffer) {
    return new IntNioDataBuffer(buffer);
  }

  public static LongDataBuffer create(LongBuffer buffer) {
    return new LongNioDataBuffer(buffer);
  }

  public static ShortDataBuffer create(ShortBuffer buffer) {
    return new ShortNioDataBuffer(buffer);
  }
}
