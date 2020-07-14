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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tensorflow.DataType;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.exceptions.TFOutOfRangeException;
import org.tensorflow.op.Ops;
import org.tensorflow.ndarray.IntNdArray;
import org.tensorflow.ndarray.StdArrays;
import org.tensorflow.types.TInt32;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDatasetTest extends DatasetTestBase {
  IntNdArray mapped1;
  IntNdArray mapped2;

  @BeforeEach
  public void setUp() {
    super.setUp();
    mapped1 =
        StdArrays.ndCopyOf(
            new int[][] {
              {2, 4, 6, 8, 10},
              {4, 8, 12, 16, 20},
              {6, 12, 18, 24, 30},
              {8, 16, 24, 32, 40}
            });

    mapped2 = StdArrays.ndCopyOf(new int[][] {{2}, {0}, {2}, {2}});
  }

  @Test
  public void testGraphIteration() {
    setUp();
    try (Graph graph = new Graph()) {
      Ops tf = Ops.create(graph);

      List<Operand<?>> tensors = Arrays.asList(tf.constant(testMatrix1), tf.constant(testMatrix2));

      List<DataType<?>> dataTypes = Arrays.asList(TInt32.DTYPE, TInt32.DTYPE);

      Dataset dataset =
          Dataset.fromTensorSlices(tf, tensors, dataTypes)
              .mapAllComponents(
                  component ->
                      tf.math.mul(component.asOutput().expect(TInt32.DTYPE), tf.constant(2)));

      DatasetIterator iterator = dataset.makeOneShotIterator();
      List<Operand<?>> components = iterator.getNext();
      Operand<?> X = components.get(0);
      Operand<?> y = components.get(1);

      try (Session session = new Session(graph)) {
        session.run(tf.init());

        int batches = 0;
        while (true) {
          try {
            List<Tensor<?>> outputs = session.runner().fetch(X).fetch(y).run();

            try (Tensor<TInt32> XBatch = outputs.get(0).expect(TInt32.DTYPE);
                Tensor<TInt32> yBatch = outputs.get(1).expect(TInt32.DTYPE)) {

              assertEquals(mapped1.get(batches), XBatch.data());
              assertEquals(mapped2.get(batches), yBatch.data());

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
    setUp();

    Ops tf = Ops.create();

    List<Operand<?>> tensors = Arrays.asList(tf.constant(testMatrix1), tf.constant(testMatrix2));

    List<DataType<?>> dataTypes = Arrays.asList(TInt32.DTYPE, TInt32.DTYPE);

    Dataset dataset =
        Dataset.fromTensorSlices(tf, tensors, dataTypes)
            .mapAllComponents(
                op -> tf.math.mul(op.asOutput().expect(TInt32.DTYPE), tf.constant(2)));

    int count = 0;
    for (List<Operand<?>> outputs : dataset) {
      try (Tensor<TInt32> XBatch = outputs.get(0).asTensor().expect(TInt32.DTYPE);
          Tensor<TInt32> yBatch = outputs.get(1).asTensor().expect(TInt32.DTYPE); ) {

        assertEquals(mapped1.get(count), XBatch.data());
        assertEquals(mapped2.get(count), yBatch.data());

        count++;
      }
    }
  }
}
