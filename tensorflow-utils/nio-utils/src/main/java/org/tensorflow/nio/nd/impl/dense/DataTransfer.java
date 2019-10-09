package org.tensorflow.nio.nd.impl.dense;

import org.tensorflow.nio.buffer.BooleanDataBuffer;
import org.tensorflow.nio.buffer.ByteDataBuffer;
import org.tensorflow.nio.buffer.DataBuffer;
import org.tensorflow.nio.buffer.DoubleDataBuffer;
import org.tensorflow.nio.buffer.FloatDataBuffer;
import org.tensorflow.nio.buffer.IntDataBuffer;
import org.tensorflow.nio.buffer.LongDataBuffer;
import org.tensorflow.nio.buffer.ShortDataBuffer;
import org.tensorflow.nio.nd.impl.dimension.DimensionalSpace;
import org.tensorflow.nio.nd.impl.sequence.PositionIterator;

final class DataTransfer {

  @FunctionalInterface
  interface OfValue<B extends DataBuffer<?>> {
    void copy(B srcBuffer, long srcIndex, B dstBuffer, long dstIndex);
  }

  static <T, B extends DataBuffer<T>> void ofValue(B srcBuf, long srcIdx, B dstBuf, long dstIdx) {
    dstBuf.set(srcBuf.get(srcIdx), dstIdx);
  }

  static void ofByte(ByteDataBuffer srcBuf, long srcIdx, ByteDataBuffer dstBuf, long dstIdx) {
    dstBuf.setByte(srcBuf.getByte(srcIdx), dstIdx);
  }

  static void ofInt(IntDataBuffer srcBuf, long srcIdx, IntDataBuffer dstBuf, long dstIdx) {
    dstBuf.setInt(srcBuf.getInt(srcIdx), dstIdx);
  }

  static void ofLong(LongDataBuffer srcBuf, long srcIdx, LongDataBuffer dstBuf, long dstIdx) {
    dstBuf.setLong(srcBuf.getLong(srcIdx), dstIdx);
  }

  static void ofDouble(DoubleDataBuffer srcBuf, long srcIdx, DoubleDataBuffer dstBuf, long dstIdx) {
    dstBuf.setDouble(srcBuf.getDouble(srcIdx), dstIdx);
  }

  static void ofFloat(FloatDataBuffer srcBuf, long srcIdx, FloatDataBuffer dstBuf, long dstIdx) {
    dstBuf.setFloat(srcBuf.getFloat(srcIdx), dstIdx);
  }

  static void ofShort(ShortDataBuffer srcBuf, long srcIdx, ShortDataBuffer dstBuf, long dstIdx) {
    dstBuf.setShort(srcBuf.getShort(srcIdx), dstIdx);
  }

  static void ofBoolean(BooleanDataBuffer srcBuf, long srcIdx, BooleanDataBuffer dstBuf, long dstIdx) {
    dstBuf.setBoolean(srcBuf.getBoolean(srcIdx), dstIdx);
  }

  static <T, B extends DataBuffer<T>> void execute(B srcBuffer, DimensionalSpace srcDimensions, B dstBuffer, DimensionalSpace dstDimensions, OfValue<B> valueTransfer) {
    if (srcDimensions.isSegmented() || dstDimensions.isSegmented()) {
      int segmentationIdx = Math.max(srcDimensions.segmentationIdx(), dstDimensions.segmentationIdx());
      copyByElement(
          srcBuffer,
          PositionIterator.create(srcDimensions, segmentationIdx),
          dstBuffer,
          PositionIterator.create(dstDimensions, segmentationIdx),
          srcDimensions.get(segmentationIdx).elementSize(),
          valueTransfer
      );
    } else {
      srcBuffer.copyTo(dstBuffer, srcDimensions.get(0).totalSize());
    }
  }

  static <T, B extends DataBuffer<T>> void execute(B srcBuffer, B dstBuffer, DimensionalSpace dstDimensions, OfValue<B> valueTransfer) {
    if (dstDimensions.isSegmented()) {
      long elementSize = dstDimensions.get(dstDimensions.segmentationIdx()).elementSize();
      copyByElement(
          srcBuffer,
          PositionIterator.sequence(elementSize, srcBuffer.size()),
          dstBuffer,
          PositionIterator.create(dstDimensions, dstDimensions.segmentationIdx()),
          elementSize,
          valueTransfer
      );
    } else {
      srcBuffer.copyTo(dstBuffer, dstDimensions.get(0).totalSize());
    }
  }

  static <T, B extends DataBuffer<T>> void execute(B srcBuffer, DimensionalSpace srcDimensions, B dstBuffer, OfValue<B> valueTransfer) {
    if (srcDimensions.isSegmented()) {
      long elementSize = srcDimensions.get(srcDimensions.segmentationIdx()).elementSize();
      copyByElement(
          srcBuffer,
          PositionIterator.create(srcDimensions, srcDimensions.segmentationIdx()),
          dstBuffer,
          PositionIterator.sequence(elementSize, dstBuffer.size()),
          elementSize,
          valueTransfer
      );
    } else {
      srcBuffer.copyTo(dstBuffer, srcDimensions.get(0).totalSize());
    }
  }

  private static <T, B extends DataBuffer<T>> void copyByElement(
      B srcBuffer,
      PositionIterator srcIterator,
      B dstBuffer,
      PositionIterator dstIterator,
      long elementSize,
      OfValue<B> valueTransfer
  ) {
    if (elementSize == 1) {
      while (srcIterator.hasNext()) {
        valueTransfer.copy(srcBuffer, srcIterator.nextLong(), dstBuffer, dstIterator.nextLong());
      }
    } else {
      while (srcIterator.hasNext()) {
        srcBuffer.offset(srcIterator.nextLong()).copyTo(dstBuffer.offset(dstIterator.nextLong()), elementSize);
      }
    }
  }
}
