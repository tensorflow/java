package org.tensorflow.nio.nd.impl.sequence;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.tensorflow.nio.nd.IntNdArray;
import org.tensorflow.nio.nd.NdArraySequence;
import org.tensorflow.nio.nd.NdArrays;
import org.tensorflow.nio.nd.Shape;
import org.tensorflow.nio.nd.impl.AbstractNdArray;

public class ElementSequenceTest {

  @Test
  public void iterateVectorsWithIndex() {
    IntNdArray array = NdArrays.ofInts(Shape.make(2, 3, 2));

    @SuppressWarnings("unchecked")
    NdArraySequence<IntNdArray> sequence = ElementSequence.create((AbstractNdArray<Integer, IntNdArray>)array, 1);
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
    IntNdArray array = NdArrays.ofInts(Shape.make(2, 3, 2));

    @SuppressWarnings("unchecked")
    NdArraySequence<IntNdArray> cursor = ElementSequence.create((AbstractNdArray<Integer, IntNdArray>)array, 2);
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
}
