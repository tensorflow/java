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
package org.tensorflow.framework.metrics;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;

import static org.junit.jupiter.api.Assertions.*;
import static org.tensorflow.framework.utils.CastHelper.cast;

public class AUCTest {
  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  float epsilon = 1e-4F;

  int numThresholds = 3;
  float[] predArray = new float[] {0f, 0.5f, 0.3f, 0.9f};
  int[] trueArray = new int[] {0, 0, 1, 1};
  float[] sampleWeight = new float[] {1, 2, 3, 4};

  @Test
  public void testValueIsIdempotent() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat32> yPred = tf.constant(predArray);
      Operand<TInt32> yTrue = tf.constant(trueArray);
      AUC<TFloat32> instance = new AUC<>(tf, numThresholds, 1001L, TFloat32.class);

      session.run(tf.init());

      Op update = instance.updateState(yTrue, yPred, null);

      for (int i = 0; i < 10; i++) {
        session.run(update);
      }

      Operand<TFloat32> result = instance.result();

      for (int i = 0; i < 10; i++) {
        session.evaluate(result, instance.result());
      }
    }
  }

  @Test
  public void basicTestSampleWeight() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      AUC<TFloat32> instance = new AUC<>(tf, numThresholds, 1001L, TFloat32.class);
      assertEquals(numThresholds, instance.getNumThresholds());
      float[] expectedThresholds = new float[] {-1e-7f, 0.5f, 1 + 1e-7f};
      assertArrayEquals(expectedThresholds, instance.getThresholds(), epsilon);

      instance.resetStates();
      Operand<TFloat32> yPred = tf.constant(new float[] {0, 0, 1, 1});
      Operand<TFloat32> yTrue = tf.constant(new float[] {0f, 0.5f, 0.3f, 0.9f});
      Operand<TFloat32> sampleWeights = tf.constant(new float[] {1, 0, 0, 1});

      Op update = instance.updateState(yTrue, yPred, sampleWeights);
      session.run(update);
      Operand<TFloat32> result = instance.result();
      session.evaluate(1.0f, result);
    }
  }

  @Test
  public void testUnweightedAllCorrect() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat32> yTrue = cast(tf, tf.constant(this.trueArray), TFloat32.class);
      AUC<TFloat32> instance = new AUC<>(tf, this.numThresholds, 1001L, TFloat32.class);
      session.run(tf.init());

      Op update = instance.updateState(yTrue, yTrue, null);
      session.run(update);
      Operand<TFloat32> result = instance.result();

      session.evaluate(1f, result);
    }
  }

  @Test
  public void testUnweighted() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat32> yPred = tf.constant(this.predArray);
      Operand<TInt32> yTrue = tf.constant(this.trueArray);
      AUC<TFloat32> instance = new AUC<>(tf, this.numThresholds, 1001L, TFloat32.class);
      session.run(tf.init());
      Op update = instance.updateState(yTrue, yPred, null);
      session.run(update);
      Operand<TFloat32> result = instance.result();

      // float expectedResult = (0.75f * 1 + 0.25f * 0);
      session.evaluate(0.75f, result);
    }
  }

  @Test
  public void testManualThresholds() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat32> yPred = tf.constant(this.predArray);
      Operand<TInt32> yTrue = tf.constant(this.trueArray);
      AUC<TFloat32> instance = new AUC<>(tf, new float[] {0.5f}, 1001L, TFloat32.class);
      float[] expectedThresholds = new float[] {-AUC.EPSILON, 0.5f, 1 + AUC.EPSILON};
      assertArrayEquals(expectedThresholds, instance.getThresholds(), epsilon);
      session.run(tf.init());
      Op update = instance.updateState(yTrue, yPred, null);
      session.run(update);
      Operand<TFloat32> result = instance.result();

      // float expectedResult = (0.75f * 1 + 0.25f * 0);
      session.evaluate(0.75f, result);
    }
  }

  @Test
  public void testWeightedRocInterpolation() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat32> yPred = tf.constant(this.predArray);
      Operand<TInt32> yTrue = tf.constant(this.trueArray);
      Operand<TFloat32> sampleWights = tf.constant(this.sampleWeight);

      AUC<TFloat32> instance = new AUC<>(tf, this.numThresholds, 1001L, TFloat32.class);
      session.run(tf.init());
      Op update = instance.updateState(yTrue, yPred, sampleWights);
      session.run(update);
      Operand<TFloat32> result = instance.result();

      float expectedResult = (0.78571427f * 1 + 0.2857145f * 0);
      session.evaluate(expectedResult, result);
    }
  }

  @Test
  public void testWeightedRocMajoring() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat32> yPred = tf.constant(this.predArray);
      Operand<TInt32> yTrue = tf.constant(this.trueArray);
      Operand<TFloat32> sampleWights = tf.constant(this.sampleWeight);

      AUC<TFloat32> instance =
          new AUC<>(
              tf,
              this.numThresholds,
              AUCCurve.ROC,
              AUCSummationMethod.MAJORING,
              1001L,
              TFloat32.class);
      session.run(tf.init());
      Op update = instance.updateState(yTrue, yPred, sampleWights);
      session.run(update);
      Operand<TFloat32> result = instance.result();

      float expectedResult = (1.0f + .5714285f * 0f);
      session.evaluate(expectedResult, result);
    }
  }

  @Test
  public void testWeightedRocMinoring() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat32> yPred = tf.constant(this.predArray);
      Operand<TInt32> yTrue = tf.constant(this.trueArray);
      Operand<TFloat32> sampleWights = tf.constant(this.sampleWeight);

      AUC<TFloat32> instance =
          new AUC<>(
              tf,
              this.numThresholds,
              AUCCurve.ROC,
              AUCSummationMethod.MINORING,
              1001L,
              TFloat32.class);
      session.run(tf.init());
      Op update = instance.updateState(yTrue, yPred, sampleWights);
      session.run(update);
      Operand<TFloat32> result = instance.result();

      float expectedResult = ( 0.5714285f + 0f * 0f);
      session.evaluate(expectedResult, result);
    }
  }

  @Test
  public void testWeightedPrMajoring() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat32> yPred = tf.constant(this.predArray);
      Operand<TInt32> yTrue = tf.constant(this.trueArray);
      Operand<TFloat32> sampleWights = tf.constant(this.sampleWeight);

      AUC<TFloat32> instance =
          new AUC<>(
              tf,
              this.numThresholds,
              AUCCurve.PR,
              AUCSummationMethod.MAJORING,
              1001L,
              TFloat32.class);
      session.run(tf.init());
      Op update = instance.updateState(yTrue, yPred, sampleWights);
      session.run(update);
      Operand<TFloat32> result = instance.result();
      float expectedResult = 0.4285715f + 0.5714285f;
      session.evaluate(expectedResult, result);
    }
  }

  @Test
  public void testWeightedPrMinoring() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat32> yPred = tf.constant(this.predArray);
      Operand<TInt32> yTrue = tf.constant(this.trueArray);
      Operand<TFloat32> sampleWights = tf.constant(this.sampleWeight);

      AUC<TFloat32> instance =
          new AUC<>(
              tf,
              this.numThresholds,
              AUCCurve.PR,
              AUCSummationMethod.MINORING,
              1001L,
              TFloat32.class);
      session.run(tf.init());
      Op update = instance.updateState(yTrue, yPred, sampleWights);
      session.run(update);
      Operand<TFloat32> result = instance.result();
      float expectedResult = 0.7f * 0.4285715f + 0f * 0.5714285f;
      session.evaluate(expectedResult, result);
    }
  }

  @Test
  public void testWeightedPrInterpolation() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      Operand<TFloat32> yPred = tf.constant(this.predArray);
      Operand<TInt32> yTrue = tf.constant(this.trueArray);
      Operand<TFloat32> sampleWights = tf.constant(this.sampleWeight);

      AUC<TFloat32> instance =
          new AUC<>(tf, this.numThresholds, AUCCurve.PR, 1001L, TFloat32.class);
      session.run(tf.init());
      Op update = instance.updateState(yTrue, yPred, sampleWights);
      session.run(update);
      Operand<TFloat32> result = instance.result();
      float expectedResult = 0.916613f;
      session.evaluate(expectedResult, result);
    }
  }

  @Test
  public void testInvalidNumThresholds() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          try (TestSession session = TestSession.createTestSession(tfMode)) {
            Ops tf = session.getTF();

            new AUC<>(tf, -1, 1001L, TFloat32.class);
          }
        });
  }

  @Test
  public void testExtraDims() {
    try (TestSession session = TestSession.createTestSession(tfMode)) {
      Ops tf = session.getTF();
      // logits = scipy.special.expit(-np.array([[[-10., 10., -10.], [10., -10., 10.]],
      //                   [[-12., 12., -12.], [12., -12., 12.]]],
      //                  dtype=np.float32))
      float[][][] logitsArray = {
        {
          {9.99954602e-01f, 4.53978687e-05f, 9.99954602e-01f},
          {4.53978687e-05f, 9.99954602e-01f, 4.53978687e-05f}
        },
        {
          {9.99993856e-01f, 6.14417460e-06f, 9.99993856e-01f},
          {6.14417460e-06f, 9.99993856e-01f, 6.14417460e-06f}
        }
      };

      long[][][] labelArray = {
        {{1, 0, 0}, {1, 0, 0}},
        {{0, 1, 1}, {0, 1, 1}}
      };

      Operand<TFloat32> logits = tf.constant(logitsArray);
      Operand<TInt64> labels = tf.constant(labelArray);

      AUC<TFloat32> instance = new AUC<>(tf, 1001L, TFloat32.class);
      session.run(tf.init());
      Op update = instance.updateState(labels, logits, null);
      session.run(update);
      Operand<TFloat32> result = instance.result();
      float expectedResult = 0.5f;
      session.evaluate(expectedResult, result);
    }
  }
}
