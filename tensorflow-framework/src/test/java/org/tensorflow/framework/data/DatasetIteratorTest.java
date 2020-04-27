package org.tensorflow.framework.data;

import org.junit.Test;
import org.tensorflow.*;
import org.tensorflow.exceptions.TFOutOfRangeException;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DatasetIteratorTest extends DatasetTestBase {

  @Test
  public void testGraphIteration() {
    try (Graph graph = new Graph()) {
      Ops tf = Ops.create(graph);

      List<Operand<?>> tensors = Arrays.asList(tf.constant(testMatrix1), tf.constant(testMatrix2));

      List<DataType<?>> dataTypes = Arrays.asList(TInt32.DTYPE, TInt32.DTYPE);

      Dataset dataset = Dataset.fromTensorSlices(tf, tensors, dataTypes);
      DatasetIterator iterator = dataset.makeOneShotIterator();

      List<Operand<?>> components = iterator.getNext();
      Operand<?> x = components.get(0);
      Operand<?> y = components.get(1);

      try (Session session = new Session(graph)) {
        session.run(tf.init());

        int batches = 0;
        while (true) {
          try {
            List<Tensor<?>> outputs = session.runner().fetch(x).fetch(y).run();

            try (Tensor<TInt32> xBatch = outputs.get(0).expect(TInt32.DTYPE);
                Tensor<TInt32> yBatch = outputs.get(1).expect(TInt32.DTYPE)) {
              assertEquals(testMatrix1.get(batches), xBatch.data());
              assertEquals(testMatrix2.get(batches), yBatch.data());
              batches++;
            }
          } catch (TFOutOfRangeException e) {
            break;
          }
        }
      }
    }
  }

  @Test
  public void testEagerIteration() {

    Ops tf = Ops.create();

    List<Operand<?>> tensors = Arrays.asList(tf.constant(testMatrix1), tf.constant(testMatrix2));

    List<DataType<?>> dataTypes = Arrays.asList(TInt32.DTYPE, TInt32.DTYPE);

    Dataset dataset = Dataset.fromTensorSlices(tf, tensors, dataTypes);
    int count = 0;
    for (List<Operand<?>> outputs : dataset) {
      try (Tensor<TInt32> batch1 = outputs.get(0).asTensor().expect(TInt32.DTYPE);
          Tensor<TInt32> batch2 = outputs.get(1).asTensor().expect(TInt32.DTYPE); ) {

        assertEquals(testMatrix1.get(count), batch1.data());
        assertEquals(testMatrix2.get(count), batch2.data());

        count++;
      }
    }
  }
}
