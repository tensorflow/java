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
package org.tensorflow.framework.metrics.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.Result;
import org.tensorflow.Tensor;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

public class AssertBroadcastableTest {

  private final TestSession.Mode tfMode = TestSession.Mode.GRAPH;

  int[][][] valueArrayI =
      new int[][][] {
        {{1, 2, 3, 4}, {5, 6, 7, 8}},
        {{9, 10, 11, 12}, {13, 14, 15, 16}},
        {{17, 18, 19, 20}, {21, 22, 23, 24}}
      };
  long[][][] valueArrayL =
      new long[][][] {
        {{1, 2, 3, 4}, {5, 6, 7, 8}},
        {{9, 10, 11, 12}, {13, 14, 15, 16}},
        {{17, 18, 19, 20}, {21, 22, 23, 24}}
      };
  float[][][] valueArrayF =
      new float[][][] {
        {{1, 2, 3, 4}, {5, 6, 7, 8}},
        {{9, 10, 11, 12}, {13, 14, 15, 16}},
        {{17, 18, 19, 20}, {21, 22, 23, 24}}
      };
  double[][][] valueArrayD =
      new double[][][] {
        {{1, 2, 3, 4}, {5, 6, 7, 8}},
        {{9, 10, 11, 12}, {13, 14, 15, 16}},
        {{17, 18, 19, 20}, {21, 22, 23, 24}}
      };

  private <T extends TNumber> void testValid(
      TestSession testSession, Ops tf, Operand<T> weights, Operand<T> values, Class<T> type) {

    Op staticOp = MetricsHelper.assertBroadcastable(tf, weights, values);
    testSession.run(staticOp);

    // dynamic test
    Operand<T> weightsPlaceholder = tf.placeholder(type);
    Operand<T> valuesPlaceholder = tf.placeholder(type);

    try (Result tensors =
        testSession.getGraphSession().runner().fetch(weights).fetch(values).run()) {
      Tensor weightsTensor = tensors.get(0);
      Tensor valuesTensor = tensors.get(1);
      Op dynamicOp = MetricsHelper.assertBroadcastable(tf, weightsPlaceholder, valuesPlaceholder);

      testSession
          .getGraphSession()
          .runner()
          .feed(weightsPlaceholder, weightsTensor)
          .feed(valuesPlaceholder, valuesTensor)
          .addTarget(dynamicOp)
          .run();
    }
  }

  @Test
  public void testValidScalar() {
    // no exception should be thrown
    try (TestSession testSession = TestSession.createTestSession(tfMode)) {
      Ops tf = testSession.getTF();

      Operand<TFloat32> values = tf.constant(valueArrayF);
      Operand<TFloat32> weights = tf.constant(5f);
      testValid(testSession, tf, weights, values, TFloat32.class);
    }
  }

  @Test
  public void test1x1x1() {
    // no exception should be thrown
    try (TestSession testSession = TestSession.createTestSession(tfMode)) {
      Ops tf = testSession.getTF();

      Operand<TFloat64> values = tf.constant(valueArrayD);
      Operand<TFloat64> weights = tf.constant(new double[][][] {{{5}}});
      testValid(testSession, tf, weights, values, TFloat64.class);
    }
  }

  @Test
  public void test1x1xN() {
    // no exception should be thrown
    try (TestSession testSession = TestSession.createTestSession(tfMode)) {
      Ops tf = testSession.getTF();
      Operand<TInt64> values = tf.constant(valueArrayL);
      Operand<TInt64> weights = tf.constant(new long[][][] {{{5, 7, 11, 3}}});
      testValid(testSession, tf, weights, values, TInt64.class);
    }
  }

  @Test
  public void test1xNx1() {
    // no exception should be thrown
    try (TestSession testSession = TestSession.createTestSession(tfMode)) {
      Ops tf = testSession.getTF();
      Operand<TInt32> values = tf.constant(valueArrayI);
      Operand<TInt32> weights = tf.constant(new int[][][] {{{5}, {11}}});
      testValid(testSession, tf, weights, values, TInt32.class);
    }
  }

  @Test
  public void test1xNxN() {
    // no exception should be thrown
    try (TestSession testSession = TestSession.createTestSession(tfMode)) {
      Ops tf = testSession.getTF();

      Operand<TInt32> values = tf.constant(valueArrayI);
      Operand<TInt32> weights = tf.constant(new int[][][] {{{5, 7, 11, 3}, {2, 13, 7, 5}}});
      testValid(testSession, tf, weights, values, TInt32.class);
    }
  }

  @Test
  public void testNx1x1() {
    // no exception should be thrown
    try (TestSession testSession = TestSession.createTestSession(tfMode)) {
      Ops tf = testSession.getTF();

      Operand<TInt32> values = tf.constant(valueArrayI);
      Operand<TInt32> weights = tf.constant(new int[][][] {{{5}}, {{7}}, {{11}}});
      testValid(testSession, tf, weights, values, TInt32.class);
    }
  }

  @Test
  public void testNx1xN() {
    // no exception should be thrown
    try (TestSession testSession = TestSession.createTestSession(tfMode)) {
      Ops tf = testSession.getTF();

      Operand<TInt32> values = tf.constant(valueArrayI);
      Operand<TInt32> weights =
          tf.constant(new int[][][] {{{5, 7, 11, 3}}, {{2, 12, 7, 5}}, {{2, 17, 11, 3}}});
      testValid(testSession, tf, weights, values, TInt32.class);
    }
  }

  @Test
  public void testNxNxN() {
    // no exception should be thrown
    try (TestSession testSession = TestSession.createTestSession(tfMode)) {
      Ops tf = testSession.getTF();

      Operand<TInt32> values = tf.constant(valueArrayI);
      Operand<TInt32> weights =
          tf.constant(
              new int[][][] {
                {{5, 7, 11, 3}, {2, 12, 7, 5}},
                {{2, 17, 11, 3}, {2, 17, 11, 3}},
                {{5, 7, 11, 3}, {2, 12, 7, 5}}
              });
      testValid(testSession, tf, weights, values, TInt32.class);
    }
  }

  // Note: For invalid tests, either NotBroadcastableException is thrown for static shapes or
  // TFInvalidInvalidException is thrown for dynamic shapes. Both of these extend
  // IllegalArgumentException,
  // To simply the assertThrows, only IllegalArgumentException is expected.
  // The private method, testValid, tests for both static and dynamic shapes.
  @Test
  public void testInvalid1x1() {

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            Operand<TInt32> values = tf.constant(valueArrayI);
            Operand<TInt32> weights = tf.constant(new int[][] {{5}});
            testValid(testSession, tf, weights, values, TInt32.class);
          }
        });
  }

  @Test
  public void testInvalidPrefixMatch() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            Operand<TInt32> values = tf.constant(valueArrayI);
            Operand<TInt32> weights = tf.constant(new int[][] {{5, 7}, {11, 3}, {2, 12}});
            testValid(testSession, tf, weights, values, TInt32.class);
          }
        });
  }

  @Test
  public void testInvalidSuffixMatch() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            Operand<TInt32> values = tf.constant(valueArrayI);
            Operand<TInt32> weights = tf.constant(new int[][] {{5, 7, 11, 3}, {2, 12, 7, 5}});
            testValid(testSession, tf, weights, values, TInt32.class);
          }
        });
  }

  @Test
  public void testInvalidOnesExtraDim() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            Operand<TInt32> values = tf.constant(valueArrayI);
            Operand<TInt32> weights = tf.constant(new int[][][][] {{{{5}}}});
            testValid(testSession, tf, weights, values, TInt32.class);
          }
        });
  }

  @Test
  public void testInvalidPrefixMatchExtraDim() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            Operand<TInt32> values = tf.constant(valueArrayI);

            Operand<TInt32> weights =
                tf.constant(
                    new int[][][][] {
                      {{{5}, {7}, {11}, {3}}, {{2}, {12}, {7}, {5}}},
                      {{{2}, {17}, {11}, {3}}, {{2}, {17}, {11}, {3}}},
                      {{{5}, {7}, {11}, {3}}, {{2}, {12}, {7}, {5}}}
                    });
            testValid(testSession, tf, weights, values, TInt32.class);
          }
        });
  }

  @Test
  public void testInvalidSuffixMatchExtraDim() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            Operand<TInt32> values = tf.constant(valueArrayI);
            Operand<TInt32> weights =
                tf.constant(
                    new int[][][][] {
                      {
                        {{5, 7, 11, 3}, {2, 12, 7, 5}},
                        {{2, 17, 11, 3}, {2, 17, 11, 3}},
                        {{5, 7, 11, 3}, {2, 12, 7, 5}}
                      }
                    });
            testValid(testSession, tf, weights, values, TInt32.class);
          }
        });
  }
}
