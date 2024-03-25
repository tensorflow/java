/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.framework.losses;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Session;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.ndarray.buffer.DataBuffers;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

public class CategoricalCrossentropyTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  /** Test of call method, of class CategoricalCrossentropy. */
  @Test
  public void testAllCorrectUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();

        long[] trueArray = {1L, 0L, 0L, 0L, 1L, 0L, 0L, 0L, 1L};
        float[] predArray = {1.F, 0.F, 0.F, 0.F, 1.F, 0.F, 0.F, 0.F, 1.F};
        Operand<TInt64> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(3, 3)));
        CategoricalCrossentropy instance = new CategoricalCrossentropy();

        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
        float expected = 0F;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] logitsArray = {10.F, 0.F, 0.F, 0.F, 10.F, 0.F, 0.F, 0.F, 10.F};
        yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        instance = new CategoricalCrossentropy(true);

        loss = instance.call(tf, yTrue, logits);
        testSession.setEpsilon(1e-3F);
        testSession.evaluate(0.0F, loss);
      }
  }

  @Test
  public void testInvalidPredictionsRange() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Class<? extends Throwable> catchClass =
            tfMode == TestSession.Mode.EAGER
                ? IllegalArgumentException.class
                : org.tensorflow.exceptions.TFInvalidArgumentException.class;
        assertThrows(
            catchClass,
            () -> {
              Ops tf = testSession.getTF();
              CategoricalCrossentropy instance = new CategoricalCrossentropy();

              float[] trueArray = {1L, 0L, 0L, 0L, 1L, 0L, 0L, 0L, 1L};
              float[] predArray = {-1.F, 0.F, 0.F, 0.F, 1.F, 0.F, 0.F, 0.F, 1.F};
              Operand<TFloat32> yTrue =
                  tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
              Operand<TFloat32> yPred =
                  tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 2)));

              Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
              testSession.run(loss);
            });
      }
  }

  /** Test of call method, of class CategoricalCrossentropy. */
  @Test
  public void testUnweighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        CategoricalCrossentropy instance = new CategoricalCrossentropy();

        int[] trueArray = {1, 0, 0, 0, 1, 0, 0, 0, 1};
        float[] predArray = {.9F, .05F, .05F, .5F, .89F, .6F, .05F, .01F, .94F};
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred);
        float expected = 0.32396814F;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] logitsArray = {8.F, 1.F, 1.F, 0.F, 9.F, 1.F, 2.F, 3.F, 5.F};
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        instance = new CategoricalCrossentropy(true);

        loss = instance.call(tf, yTrue, logits);
        expected = 0.0573755F;
        testSession.evaluate(expected, loss);
      }
  }

  /** Test of call method, of class CategoricalCrossentropy. */
  @Test
  public void testScalarWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();

        int[] trueArray = {1, 0, 0, 0, 1, 0, 0, 0, 1};
        float[] predArray = {.9F, .05F, .05F, .5F, .89F, .6F, .05F, .01F, .94F};
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> sampleWeight = tf.constant(2.3F);

        CategoricalCrossentropy instance = new CategoricalCrossentropy();

        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = .7451267F;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] logitsArray = {8.F, 1.F, 1.F, 0.F, 9.F, 1.F, 2.F, 3.F, 5.F};
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        instance = new CategoricalCrossentropy(true);

        loss = instance.call(tf, yTrue, logits, sampleWeight);
        expected = 0.13196386F;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testSsampleWeighted() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        CategoricalCrossentropy instance = new CategoricalCrossentropy();

        float[] sampeWeightArray = {1.2F, 3.4F, 5.6F};
        int[] trueArray = {1, 0, 0, 0, 1, 0, 0, 0, 1};
        float[] predArray = {.9F, .05F, .05F, .5F, .89F, .6F, .05F, .01F, .94F};
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> sampleWeight =
            tf.reshape(tf.constant(sampeWeightArray), tf.constant(Shape.of(3, 1)));
        Operand<TFloat32> loss = instance.call(tf, yTrue, yPred, sampleWeight);
        float expected = 1.0696F;
        testSession.evaluate(expected, loss);

        // Test with logits.
        float[] logitsArray = {8.F, 1.F, 1.F, 0.F, 9.F, 1.F, 2.F, 3.F, 5.F};
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        instance = new CategoricalCrossentropy(true);

        loss = instance.call(tf, yTrue, logits, sampleWeight);
        expected = 0.31829F;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testNoReduction() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();

        // Test with logits.
        int[] trueArray = {1, 0, 0, 0, 1, 0, 0, 0, 1};
        float[] logitsArray = {8.F, 1.F, 1.F, 0.F, 9.F, 1.F, 2.F, 3.F, 5.F};
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(3, 3)));
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(3, 3)));
        CategoricalCrossentropy instance = new CategoricalCrossentropy(true, 0.0F, Reduction.NONE);

        Operand<TFloat32> loss = instance.call(tf, yTrue, logits);
        Float[] expected = {0.001822F, 0.000459F, 0.169846F};
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testLabelSmoothing() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession testSession = TestSession.createTestSession(tfMode)) {
        Ops tf = testSession.getTF();
        float labelSmoothing = 0.1F;
        int[] trueArray = {1, 0, 0};
        float[] logitsArray = {100.0F, -100.0F, -100.0F};
        Operand<TInt32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(1, 3)));
        Operand<TFloat32> logits =
            tf.reshape(tf.constant(logitsArray), tf.constant(Shape.of(1, 3)));

        CategoricalCrossentropy instance = new CategoricalCrossentropy(true, labelSmoothing);

        Operand<TFloat32> loss = instance.call(tf, yTrue, logits);
        float expected = 400.0F * labelSmoothing / 3.0F;
        testSession.evaluate(expected, loss);
      }
  }

  @Test
  public void testCategoricalCrossEntopyWithDynamicBatchSize() {
    try (Graph graph = new Graph()) {
      Ops tf = Ops.create(graph);
      Operand yPred = tf.placeholder(TFloat32.class, Placeholder.shape(Shape.of(-1, 3)));
      Operand yTrue =
          tf.reshape(tf.constant(new float[] {1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f}), tf.array(3, 3));
      CategoricalCrossentropy instance = new CategoricalCrossentropy(true);
      Operand loss =
          instance.call(tf, yTrue, yPred); // Throw TFInvalidArgument Exception without fix
      try (Session session = new Session(graph);
          TFloat32 result =
              (TFloat32)
                  session
                      .runner()
                      .feed(
                          yPred,
                          TFloat32.tensorOf(
                              Shape.of(3, 3),
                              DataBuffers.of(
                                  new float[] {1.f, 0.f, 0.f, 0.f, 1.f, 0.f, 0.f, 0.f, 1.f})))
                      .fetch(loss)
                      .run()
                      .get(0)) {
        if (Math.abs(0.5514477f - result.getFloat()) > 0.01)
          throw new IllegalStateException("Invalid result :" + result.getFloat());
      }
    }
  }
}
