package org.tensorflow.data;

import org.junit.Test;
import org.tensorflow.Output;
import org.tensorflow.Tensor;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.tensorflow.tools.ndarray.index.Indices.range;

public class BatchDatasetTest extends DatasetTestBase {

    @Test
    public void testEagerBatchDataset() {
        Ops tf = Ops.create();

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
            try (Tensor<TInt32> batch1 =
                         components.get(0).tensor().expect(TInt32.DTYPE);
                 Tensor<TInt32> batch2 =
                         components.get(1).tensor().expect(TInt32.DTYPE);) {

                assertEquals(testMatrix1.slice(range(count, count + 2)), batch1.data());
                assertEquals(testMatrix2.slice(range(count, count + 2)), batch2.data());

                count += 2;
            }
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
                .batch(3);

        int count = 0;
        for (List<Output<?>> components : dataset) {

            try (Tensor<TInt32> batch1 =
                         components.get(0).tensor().expect(TInt32.DTYPE);
                 Tensor<TInt32> batch2 =
                         components.get(1).tensor().expect(TInt32.DTYPE);) {

                assertEquals(testMatrix1.slice(range(count, count + 3)), batch1.data());
                assertEquals(testMatrix2.slice(range(count, count + 3)), batch2.data());

                count += 3;
            }
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
            try (Tensor<TInt32> batch1 =
                         components.get(0).tensor().expect(TInt32.DTYPE);
                 Tensor<TInt32> batch2 =
                         components.get(1).tensor().expect(TInt32.DTYPE);) {
                if (count == 0) {
                    assertEquals(testMatrix1.slice(range(count, count + 3)),
                            batch1.data());
                    assertEquals(testMatrix2.slice(range(count, count + 3)),
                            batch2.data());
                    count += 3;
                } else {
                    assertEquals(testMatrix1.slice(range(count, count + 1)),
                            batch1.data());
                    assertEquals(testMatrix2.slice(range(count, count + 1)),
                            batch2.data());
                    foundLastBatch = true;
                }
            }
        }

        assertTrue(foundLastBatch);
    }
}
