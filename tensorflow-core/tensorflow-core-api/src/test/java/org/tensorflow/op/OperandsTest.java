/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.op;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.op.core.Split;

/** Unit tests for {@link org.tensorflow.op.Operands}. */
public class OperandsTest {

  @Test
  public void createOutputArrayFromOperandList() {
    try (Graph g = new Graph()) {
      Ops tf = Ops.create(g);
      Split<?> split = tf.split(tf.constant(0), tf.array(0, 1, 2), 3L);
      Output<?>[] array = Operands.asOutputs(split.output());
      assertEquals(split.output().size(), array.length);
      assertSame(array[0], split.output().get(0));
      assertSame(array[1], split.output().get(1));
    }
  }
}
