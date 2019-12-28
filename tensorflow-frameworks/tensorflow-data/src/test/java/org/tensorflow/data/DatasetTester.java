package org.tensorflow.data;

import org.junit.Before;
import org.junit.Test;
import org.tensorflow.*;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;

import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DatasetTester {
  int[][] testMatrix1;
  int[][] testMatrix2;
  @Before
  public void setUp() {
    testMatrix1 = new int[][]{
        {1, 2, 3, 4, 5},
        {2, 4, 6, 8, 10},
        {3, 6, 8, 12, 15},
        {4, 8, 12, 16, 20}
    };

    testMatrix2 = new int[][] {
        {1}, {0}, {1}, {1}
    };
  }

  @Test
  public void testEagerDatasetIterator() {
    Ops tf = Ops.create();
    Dataset dataset = Dataset
        .fromTensorSlices(tf,
            Arrays.asList(
                tf.constant(testMatrix1),
                tf.constant(testMatrix2)),
            Arrays.asList(TInt32.DTYPE, TInt32.DTYPE));

    int count = 0;
    for(List<Output<?>> components : dataset) {
      Tensor<TInt32> batch1 = components.get(0).tensor().expect(TInt32.DTYPE);
      Tensor<TInt32> batch2 = components.get(1).tensor().expect(TInt32.DTYPE);

      assertArrayEquals(testMatrix1[count], getIntTensorAsArray(batch1));
      assertArrayEquals(testMatrix2[count], getIntTensorAsArray(batch2));

      count++;
    }
  }

  @Test
  public void testGraphDatasetIterator() {
    try (Graph graph = new Graph()) {
      Ops tf = Ops.create(graph);
      Dataset dataset = Dataset
          .fromTensorSlices(tf,
              Arrays.asList(
                  tf.constant(testMatrix1),
                  tf.constant(testMatrix2)),
              Arrays.asList(TInt32.DTYPE, TInt32.DTYPE));

      Pair<Operation, List<Output<?>>> graphIteratorComponents = dataset.makeOneShotIterator();
      Operation makeIterator = graphIteratorComponents.first();
      List<Output<?>> components = graphIteratorComponents.second();

      try (Session session = new Session(graph)) {
        session.runner()
            .addTarget(makeIterator)
            .run();

        int count = 0;
        while (true) {
          try  {
            List<Tensor<?>> outputs = session.runner()
              .fetch(components.get(0))
              .fetch(components.get(1))
              .run();

            Tensor<TInt32> matrix1 = outputs.get(0).expect(TInt32.DTYPE);
            Tensor<TInt32> matrix2 = outputs.get(1).expect(TInt32.DTYPE);

            assertArrayEquals(testMatrix1[count], getIntTensorAsArray(matrix1));
            assertArrayEquals(testMatrix2[count], getIntTensorAsArray(matrix2));

            count++;
          } catch (IndexOutOfBoundsException e) { break; }
        }
      }

    }
  }

  public static int[] getIntTensorAsArray(Tensor<TInt32> intTensor) {
    IntBuffer buffer = IntBuffer.allocate((int) intTensor.shape().size());
    intTensor.writeTo(buffer);
    return buffer.array();
  }
}
