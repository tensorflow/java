package org.tensorflow.data;

import org.junit.Test;
import org.tensorflow.*;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class DatasetTest extends DatasetTestBase {
  private static void main(String[] args) {
    new DatasetTest().testEagerDatasetIterator();
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
    for (List<Output<?>> components : dataset) {
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

      OneShotIterator oneShotIterator = dataset.makeOneShotIterator();
      Operation makeIterator = oneShotIterator.getMakeIteratorOp();
      List<Output<?>> components = oneShotIterator.getComponents();

      try (Session session = new Session(graph)) {
        session.runner()
            .addTarget(makeIterator)
            .run();

        int count = 0;
        while (true) {
          try {
            List<Tensor<?>> outputs = session.runner()
                .fetch(components.get(0))
                .fetch(components.get(1))
                .run();

            Tensor<TInt32> matrix1 = outputs.get(0).expect(TInt32.DTYPE);
            Tensor<TInt32> matrix2 = outputs.get(1).expect(TInt32.DTYPE);

            assertArrayEquals(testMatrix1[count], getIntTensorAsArray(matrix1));
            assertArrayEquals(testMatrix2[count], getIntTensorAsArray(matrix2));

            count++;
          } catch (IndexOutOfBoundsException e) {
            break;
          }
        }
      }
    }
  }
}
