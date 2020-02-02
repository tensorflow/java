package org.tensorflow.data;

import org.junit.Test;
import org.tensorflow.Output;
import org.tensorflow.Tensor;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class BatchDatasetTest extends DatasetTestBase {

  @Test
  public void testEagerBatchDataset() {
    Ops tf = Ops.create();

Ops tf = // ... TensorFlow Ops Accessor (either graph or eager).

// Construct dataset with two components, batchSize=2.
Dataset dataset = Dataset.fromTensorSlices(tf,
    // List of array components
    Arrays.asList(tf.constant(m1), tf.constant(m2)),
    // List of 
    // EVEN BATCH SIZES
    Dataset dataset = Dataset
        .fromTensorSlices(tf,
            Arrays.asList(
                tf.constant(testMatrix1),
                tf.constant(testMatrix2)),
            Arrays.asList(TInt32.DTYPE, TInt32.DTYPE))
        .batch(2);

    int count = 0;

    for (List<Output<?>> components : dataset) {
      Tensor<TInt32> batch1 = components.get(0).tensor().expect(TInt32.DTYPE);
      Tensor<TInt32> batch2 = components.get(1).tensor().expect(TInt32.DTYPE);

      assertArrayEquals(concat(testMatrix1[count], testMatrix1[count + 1]), getIntTensorAsArray(batch1));
      assertArrayEquals(concat(testMatrix2[count], testMatrix2[count + 1]), getIntTensorAsArray(batch2));

      count += 2;
    }
  }

  @Test
  public void testDropLastBatch() {
    Ops tf = Ops.create();
    Dataset dataset = Dataset
        .fromTensorSlices(tf,
            Arrays.asList(
                tf.constant(testMatrix1),
                tf.constant(testMatrix2)),
            Arrays.asList(TInt32.DTYPE, TInt32.DTYPE))
        .batch(3, true);

    int count = 0;
    for (List<Output<?>> components : dataset) {
      Tensor<TInt32> batch1 = components.get(0).tensor().expect(TInt32.DTYPE);
      Tensor<TInt32> batch2 = components.get(1).tensor().expect(TInt32.DTYPE);

      assertArrayEquals(concat(concat(testMatrix1[count], testMatrix1[count + 1]), testMatrix1[count + 2]), getIntTensorAsArray(batch1));
      assertArrayEquals(concat(concat(testMatrix2[count], testMatrix2[count + 1]), testMatrix2[count + 2]), getIntTensorAsArray(batch2));

      count += 3;
    }
  }

  @Test
  public void testKeepLastBatch() {
    Ops tf = Ops.create();
    Dataset dataset = Dataset
        .fromTensorSlices(tf,
            Arrays.asList(
                tf.constant(testMatrix1),
                tf.constant(testMatrix2)),
            Arrays.asList(TInt32.DTYPE, TInt32.DTYPE))
        .batch(3, false);

    int count = 0;
    boolean foundLastBatch = false;
    for (List<Output<?>> components : dataset) {
      Tensor<TInt32> batch1 = components.get(0).tensor().expect(TInt32.DTYPE);
      Tensor<TInt32> batch2 = components.get(1).tensor().expect(TInt32.DTYPE);

      if (count == 0) {
        assertArrayEquals(concat(concat(testMatrix1[count], testMatrix1[count + 1]), testMatrix1[count + 2]), getIntTensorAsArray(batch1));
        assertArrayEquals(concat(concat(testMatrix2[count], testMatrix2[count + 1]), testMatrix2[count + 2]), getIntTensorAsArray(batch2));
      } else {
        assertArrayEquals(testMatrix1[count], getIntTensorAsArray(batch1));
        assertArrayEquals(testMatrix2[count], getIntTensorAsArray(batch2));
        foundLastBatch = true;
      }
      count += 3;
    }

    assertTrue(foundLastBatch);
  }


}
