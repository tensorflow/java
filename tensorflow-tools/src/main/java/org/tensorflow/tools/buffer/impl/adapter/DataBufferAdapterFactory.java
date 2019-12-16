package org.tensorflow.tools.buffer.impl.adapter;

import org.tensorflow.tools.buffer.BooleanDataBuffer;
import org.tensorflow.tools.buffer.ByteDataBuffer;
import org.tensorflow.tools.buffer.DataBuffer;
import org.tensorflow.tools.buffer.DoubleDataBuffer;
import org.tensorflow.tools.buffer.FloatDataBuffer;
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.buffer.LongDataBuffer;
import org.tensorflow.tools.buffer.ShortDataBuffer;
import org.tensorflow.tools.buffer.layout.BooleanDataLayout;
import org.tensorflow.tools.buffer.layout.DataLayout;
import org.tensorflow.tools.buffer.layout.DoubleDataLayout;
import org.tensorflow.tools.buffer.layout.FloatDataLayout;
import org.tensorflow.tools.buffer.layout.IntDataLayout;
import org.tensorflow.tools.buffer.layout.LongDataLayout;
import org.tensorflow.tools.buffer.layout.ShortDataLayout;

public class DataBufferAdapterFactory {

  public static BooleanDataBuffer create(ByteDataBuffer buffer, BooleanDataLayout layout) {
    return new BooleanDataBufferAdapter(buffer, layout);
  }

  public static DoubleDataBuffer create(ByteDataBuffer buffer, DoubleDataLayout layout) {
    return new DoubleDataBufferAdapter(buffer, layout);
  }

  public static FloatDataBuffer create(ByteDataBuffer buffer, FloatDataLayout layout) {
    return new FloatDataBufferAdapter(buffer, layout);
  }

  public static IntDataBuffer create(ByteDataBuffer buffer, IntDataLayout layout) {
    return new IntDataBufferAdapter(buffer, layout);
  }

  public static LongDataBuffer create(ByteDataBuffer buffer, LongDataLayout layout) {
    return new LongDataBufferAdapter(buffer, layout);
  }

  public static ShortDataBuffer create(ByteDataBuffer buffer, ShortDataLayout layout) {
    return new ShortDataBufferAdapter(buffer, layout);
  }

  public static <T> DataBuffer<T> create(ByteDataBuffer buffer, DataLayout<T> layout) {
    return new DataBufferAdapter<>(buffer, layout);
  }
}
