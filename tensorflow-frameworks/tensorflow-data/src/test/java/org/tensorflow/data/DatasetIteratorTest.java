package org.tensorflow.data;

import org.junit.Test;
import org.tensorflow.*;
import org.tensorflow.op.Ops;
import org.tensorflow.tools.ndarray.FloatNdArray;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DatasetIteratorTest extends DatasetTestBase {

  static class Logs {
    int batches = 0;
  }

  @Test
  public void testGraphIteration() {
    try (Graph graph = new Graph()) {
      Ops tf = Ops.create(graph);

      List<Operand<?>> tensors = Arrays.asList(
          tf.constant(testMatrix1),
          tf.constant(testMatrix2)
      );

      List<DataType<?>> dataTypes = Arrays.asList(
          TInt32.DTYPE, TInt32.DTYPE
      );

      Dataset dataset = Dataset.fromTensorSlices(tf, tensors, dataTypes);
      DatasetIterator iterator = dataset.makeOneShotIterator();

      List<Output<?>> components = iterator.getNext();
      Operand<?> X = components.get(0);
      Operand<?> y = components.get(1);

      try (Session session = new Session(graph)) {
        session.run(tf.init());

        Logs logs = new Logs();
        session.runner()
            .fetch(X)
            .fetch(y)
            .repeat()
            .forEach(outputs -> {
              Tensor<TInt32> XBatch = outputs.pop(TInt32.DTYPE);
              Tensor<TInt32> yBatch = outputs.pop(TInt32.DTYPE);

              assertEquals(testMatrix1.get(logs.batches), XBatch.data());
              assertEquals(testMatrix2.get(logs.batches), yBatch.data());
              logs.batches++;
            });
      }
    }
  }

  @Test
  public void testEagerIteration() {

    Ops tf = Ops.create();

    List<Operand<?>> tensors = Arrays.asList(
        tf.constant(testMatrix1),
        tf.constant(testMatrix2)
    );

    List<DataType<?>> dataTypes = Arrays.asList(
        TInt32.DTYPE, TInt32.DTYPE
    );

    Dataset dataset = Dataset.fromTensorSlices(tf, tensors, dataTypes);
    int count = 0;
    for (List<Output<?>> outputs : dataset) {
      try (Tensor<TInt32> XBatch =
               outputs.get(0).tensor().expect(TInt32.DTYPE);
           Tensor<TInt32> yBatch =
               outputs.get(1).tensor().expect(TInt32.DTYPE);) {

        assertEquals(testMatrix1.get(count), XBatch.data());
        assertEquals(testMatrix2.get(count), yBatch.data());

        count++;
      }
    }
  }
}