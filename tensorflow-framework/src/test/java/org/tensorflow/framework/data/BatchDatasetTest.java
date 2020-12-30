/*
 * Copyright 2020 The TensorFlow Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow.framework.data;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.tensorflow.ndarray.index.Indices.range;

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
            Arrays.asList(TInt32.class, TInt32.class))
        .batch(2);

    int count = 0;
    for (List<Operand<?>> components : dataset) {
      try (TInt32 batch1 =
               (TInt32)components.get(0).asTensor();
           TInt32 batch2 = (TInt32)components.get(1).asTensor()) {
        assertEquals(testMatrix1.slice(range(count, count + 2)), batch1);
        assertEquals(testMatrix2.slice(range(count, count + 2)), batch2);

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
            Arrays.asList(TInt32.class, TInt32.class))
        .batch(3, true);

    int count = 0;
    for (List<Operand<?>> components : dataset) {

      try (TInt32 batch1 =
               (TInt32)components.get(0).asTensor();
          TInt32 batch2 = (TInt32)components.get(1).asTensor()) {
        assertEquals(testMatrix1.slice(range(count, count + 3)), batch1);
        assertEquals(testMatrix2.slice(range(count, count + 3)), batch2);

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
            Arrays.asList(TInt32.class, TInt32.class))
        .batch(3, false);

    int count = 0;
    boolean foundLastBatch = false;

    for (List<Operand<?>> components : dataset) {
      try (TInt32 batch1 =
               (TInt32)components.get(0).asTensor();
           TInt32 batch2 =
               (TInt32)components.get(1).asTensor();) {
        if (count == 0) {
          assertEquals(testMatrix1.slice(range(count, count + 3)),
              batch1);
          assertEquals(testMatrix2.slice(range(count, count + 3)),
              batch2);
          count += 3;
        } else {
          assertEquals(testMatrix1.slice(range(count, count + 1)),
              batch1);
          assertEquals(testMatrix2.slice(range(count, count + 1)),
              batch2);
          foundLastBatch = true;
        }
      }
    }

    assertTrue(foundLastBatch);
  }
}
