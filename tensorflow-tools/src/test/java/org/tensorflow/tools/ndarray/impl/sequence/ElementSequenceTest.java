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

package org.tensorflow.tools.ndarray.impl.sequence;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.tensorflow.tools.Shape;
import org.tensorflow.tools.buffer.DataBufferWindow;
import org.tensorflow.tools.buffer.DataBuffers;
import org.tensorflow.tools.buffer.IntDataBuffer;
import org.tensorflow.tools.ndarray.IntNdArray;
import org.tensorflow.tools.ndarray.NdArraySequence;
import org.tensorflow.tools.ndarray.NdArrays;
import org.tensorflow.tools.ndarray.impl.AbstractNdArray;

public class ElementSequenceTest {

  @Test
  public void iterateVectorsWithIndex() {
    IntNdArray array = NdArrays.ofInts(Shape.of(2, 3, 2));

    NdArraySequence<IntNdArray> sequence = new SlicingElementSequence(
        (AbstractNdArray<Integer, IntNdArray>)array, 1);
    List<long[]> coords = new ArrayList<>((int)array.shape().size());
    sequence.forEachIndexed((c, e) -> coords.add(Arrays.copyOf(c, c.length)));

    assertEquals(6, coords.size());
    assertArrayEquals(new long[] {0, 0}, coords.get(0));
    assertArrayEquals(new long[] {0, 1}, coords.get(1));
    assertArrayEquals(new long[] {0, 2}, coords.get(2));
    assertArrayEquals(new long[] {1, 0}, coords.get(3));
    assertArrayEquals(new long[] {1, 1}, coords.get(4));
    assertArrayEquals(new long[] {1, 2}, coords.get(5));
  }

  @Test
  public void iterateScalarsWithIndex() {
    IntNdArray array = NdArrays.ofInts(Shape.of(2, 3, 2));

    NdArraySequence<IntNdArray> cursor = new SlicingElementSequence(
        (AbstractNdArray<Integer, IntNdArray>)array, 2);
    List<long[]> coords = new ArrayList<>((int)array.shape().size());
    cursor.forEachIndexed((c, e) -> coords.add(Arrays.copyOf(c, c.length)));

    assertEquals(12, coords.size());
    assertArrayEquals(new long[] {0, 0, 0}, coords.get(0));
    assertArrayEquals(new long[] {0, 0, 1}, coords.get(1));
    assertArrayEquals(new long[] {0, 1, 0}, coords.get(2));
    assertArrayEquals(new long[] {0, 1, 1}, coords.get(3));
    assertArrayEquals(new long[] {0, 2, 0}, coords.get(4));
    assertArrayEquals(new long[] {0, 2, 1}, coords.get(5));
    assertArrayEquals(new long[] {1, 0, 0}, coords.get(6));
    assertArrayEquals(new long[] {1, 0, 1}, coords.get(7));
    assertArrayEquals(new long[] {1, 1, 0}, coords.get(8));
    assertArrayEquals(new long[] {1, 1, 1}, coords.get(9));
    assertArrayEquals(new long[] {1, 2, 0}, coords.get(10));
    assertArrayEquals(new long[] {1, 2, 1}, coords.get(11));
  }

  @Test
  public void slicingElementSequenceReturnsUniqueInstances() {
    IntNdArray array = NdArrays.ofInts(Shape.of(2, 3, 2));
    NdArraySequence<IntNdArray> sequence = new SlicingElementSequence(
        (AbstractNdArray<Integer, IntNdArray>) array, 1);
    List<IntNdArray> elements = new ArrayList<>();
    sequence.forEach(e -> {
      elements.forEach(tmp -> {
        if (tmp == e) {
          fail();
        }
      });
      elements.add(e);
    });
  }

  @Test
  public void fastElementSequenceReturnsSameInstance() {
    IntNdArray array = NdArrays.ofInts(Shape.of(2, 3, 2));
    IntNdArray element = array.get(0);
    NdArraySequence<IntNdArray> sequence = new FastElementSequence(
        (AbstractNdArray<Integer, IntNdArray>) array, 1, element, mockDataBufferWindow(2));
    sequence.forEach(e -> {
      if (e != element) {
        fail();
      }
    });
  }

  private DataBufferWindow<IntDataBuffer> mockDataBufferWindow(long size) {
    return new DataBufferWindow<IntDataBuffer>() {

      @Override
      public long offset() {
        return offset;
      }

      @Override
      public long size() {
        return size;
      }

      @Override
      public DataBufferWindow<IntDataBuffer> slideTo(long index) {
        offset = index;
        return this;
      }

      @Override
      public DataBufferWindow<IntDataBuffer> slide(long step) {
        offset += step;
        return this;
      }

      @Override
      public IntDataBuffer buffer() {
        return buffer;
      }

      private long offset;
      private final long size = 2;
      private final IntDataBuffer buffer = DataBuffers.ofInts(2);
    };
  }
}
