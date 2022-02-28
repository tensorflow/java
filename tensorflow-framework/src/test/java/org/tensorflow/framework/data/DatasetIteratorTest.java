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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Result;
import org.tensorflow.Session;
import org.tensorflow.exceptions.TFOutOfRangeException;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TType;

public class DatasetIteratorTest extends DatasetTestBase {

  @Test
  public void testGraphIteration() {
    try (Graph graph = new Graph()) {
      Ops tf = Ops.create(graph);

      List<Operand<?>> tensors = Arrays.asList(tf.constant(testMatrix1), tf.constant(testMatrix2));

      List<Class<? extends TType>> dataTypes = Arrays.asList(TInt32.class, TInt32.class);

      Dataset dataset = Dataset.fromTensorSlices(tf, tensors, dataTypes);
      DatasetIterator iterator = dataset.makeOneShotIterator();

      List<Operand<?>> components = iterator.getNext();
      Operand<?> x = components.get(0);
      Operand<?> y = components.get(1);

      try (Session session = new Session(graph)) {
        session.initialize();

        int batches = 0;
        while (true) {
          try (Result outputs = session.runner().fetch(x).fetch(y).run()) {
            assertEquals(testMatrix1.get(batches), outputs.get(0));
            assertEquals(testMatrix2.get(batches), outputs.get(1));
            batches++;
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

    List<Class<? extends TType>> dataTypes = Arrays.asList(TInt32.class, TInt32.class);

    Dataset dataset = Dataset.fromTensorSlices(tf, tensors, dataTypes);
    int count = 0;
    for (List<Operand<?>> outputs : dataset) {
      try (TInt32 batch1 = (TInt32) outputs.get(0).asTensor();
          TInt32 batch2 = (TInt32) outputs.get(1).asTensor()) {
        assertEquals(testMatrix1.get(count), batch1);
        assertEquals(testMatrix2.get(count), batch2);

        count++;
      }
    }
  }
}
