package org.tensorflow.data;

import org.junit.Test;
import org.tensorflow.Output;
import org.tensorflow.Tensor;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class SkipDatasetTest extends DatasetTestBase {
  @Test
  public void testEagerSkipDataset() {
    Ops tf = Ops.create();

    Dataset dataset = Dataset
        .fromTensorSlices(tf,
            Arrays.asList(
                tf.constant(testMatrix1),
                tf.constant(testMatrix2)),
            Arrays.asList(TInt32.DTYPE, TInt32.DTYPE))
        .skip(2);

    int count = 2;
    for (List<Output<?>> components : dataset) {
      Tensor<TInt32> batch1 = components.get(0).tensor().expect(TInt32.DTYPE);
      Tensor<TInt32> batch2 = components.get(1).tensor().expect(TInt32.DTYPE);

      assertArrayEquals(testMatrix1[count], getIntTensorAsArray(batch1));
      assertArrayEquals(testMatrix2[count], getIntTensorAsArray(batch2));
      count++;
    }
  }

}
