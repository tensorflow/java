/*
 *  Copyright 2019 The TensorFlow Authors. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  =======================================================================
 */

package org.tensorflow.ndarray.impl.dense;

import org.tensorflow.ndarray.buffer.BooleanDataBuffer;
import org.tensorflow.ndarray.buffer.ByteDataBuffer;
import org.tensorflow.ndarray.buffer.DataBuffer;
import org.tensorflow.ndarray.buffer.DoubleDataBuffer;
import org.tensorflow.ndarray.buffer.FloatDataBuffer;
import org.tensorflow.ndarray.buffer.IntDataBuffer;
import org.tensorflow.ndarray.buffer.LongDataBuffer;
import org.tensorflow.ndarray.buffer.ShortDataBuffer;
import org.tensorflow.ndarray.impl.dimension.DimensionalSpace;
import org.tensorflow.ndarray.impl.sequence.PositionIterator;

final class DataTransfer {

  @FunctionalInterface
  interface OfValue<B extends DataBuffer<?>> {
    void copy(B srcBuffer, long srcIndex, B dstBuffer, long dstIndex);
  }

  static <T, B extends DataBuffer<T>> void ofValue(B srcBuf, long srcIdx, B dstBuf, long dstIdx) {
    dstBuf.setObject(srcBuf.getObject(srcIdx), dstIdx);
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
      srcBuffer.copyTo(dstBuffer, srcDimensions.physicalSize());
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
      srcBuffer.copyTo(dstBuffer, dstDimensions.physicalSize());
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
      srcBuffer.copyTo(dstBuffer, srcDimensions.physicalSize());
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
