package org.tensorflow.data;

import org.junit.Test;
import org.tensorflow.Output;
import org.tensorflow.Tensor;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TakeDatasetTest extends DatasetTestBase {

  @Test
  public void testEagerTakeDataset() {
    Ops tf = Ops.create();

    Dataset dataset = Dataset
        .fromTensorSlices(tf,
            Arrays.asList(
                tf.constant(testMatrix1),
                tf.constant(testMatrix2)),
            Arrays.asList(TInt32.DTYPE, TInt32.DTYPE))
        .take(4);

    int count = 0;
    for (List<Output<?>> components : dataset) {
      try (Tensor<TInt32> batch1 =
               components.get(0).tensor().expect(TInt32.DTYPE);
           Tensor<TInt32> batch2 = components.get(1).tensor().expect(TInt32.DTYPE);) {

        assertEquals(testMatrix1.get(count), batch1.data());
        assertEquals(testMatrix2.get(count), batch2.data());
        count++;
      }
    }

    assertEquals(4, count);
  }

}
