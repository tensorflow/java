package org.tensorflow.framework.metrics.impl;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TUint8;
import org.tensorflow.types.family.TType;

import java.util.Arrays;
import java.util.List;

import static org.tensorflow.framework.utils.CastHelper.cast;

class SetsOpsTest {

  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  List<Class<? extends TType>> types = Arrays.asList(TInt32.class, TInt64.class, TUint8.class);

  @Test
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void testSetIntersectionMultirow2() {

    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Operand<TInt32> a = tf.constant(new int[][] {{9, 1, 5}, {2, 4, 3}});
        Operand<TInt32> b = tf.constant(new int[][] {{1, 9}, {1, 5}});
        int[][] expected = new int[][] {{1, 9}, {0, 0}};
        Shape expectedShape = Shape.of(2, 2);
        for (Class<? extends TType> type : types) {
          Operand aa = cast(tf, a, type);
          Operand bb = cast(tf, b, type);
          Operand<? extends TType> intersection = SetsOps.intersection(tf, aa, bb);
          session.evaluate(cast(tf, tf.constant(expected), type), intersection);
          session.evaluate(tf.constant(expectedShape), tf.shape(intersection, TInt64.class));
        }
      }
  }

  @Test
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void testSetIntersectionDuplicates2d() {

    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Operand<TInt32> a = tf.constant(new int[][] {{1, 1, 3}});
        Operand<TInt32> b = tf.constant(new int[][] {{1, 1}});
        int[][] expected = {{1}};
        Shape expectedShape = Shape.of(1, 1);
        for (Class<? extends TType> type : types) {
          Operand aa = cast(tf, a, type);
          Operand bb = cast(tf, b, type);
          Operand intersection = SetsOps.intersection(tf, aa, bb);

          session.evaluate(cast(tf, tf.constant(expected), type), intersection);

          session.evaluate(tf.constant(expectedShape), tf.shape(intersection, TInt64.class));
        }
      }
  }

  @Test
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void testDenseSetDifferenceMultirow2d() {

    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Operand<TInt32> a = tf.constant(new int[][] {{1, 5, 9}, {4, 5, 3}});
        Operand<TInt32> b = tf.constant(new int[][] {{1, 2, 6}, {1, 2, 2}});

        for (Class<? extends TType> type : types) {
          Operand aa = cast(tf, a, type);
          Operand bb = cast(tf, b, type);
          int[][] expected = {{5, 9, 0}, {3, 4, 5}};
          // a- b
          Shape expectedShape = Shape.of(2, 3);
          Operand intersection = SetsOps.difference(tf, aa, bb);
          session.evaluate(cast(tf, tf.constant(expected), type), intersection);
          session.evaluate(tf.constant(expectedShape), tf.shape(intersection, TInt64.class));

          // b - a
          expected = new int[][] {{2, 6}, {1, 2}};
          expectedShape = Shape.of(2, 2);
          intersection = SetsOps.difference(tf, aa, bb, false);

          session.evaluate(cast(tf, tf.constant(expected), type), intersection);
          session.evaluate(tf.constant(expectedShape), tf.shape(intersection, TInt64.class));
        }
      }
  }

  @Test
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void testDenseUnionMultirow2d() {

    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        Operand<TInt32> a = tf.constant(new int[][] {{9, 1, 5}, {2, 4, 3}});
        Operand<TInt32> b = tf.constant(new int[][] {{1, 9}, {1, 2}});
        int[][] expected = new int[][] {{5, 0}, {3, 4}};
        for (Class<? extends TType> type : types) {
          Operand aa = cast(tf, a, type);
          Operand bb = cast(tf, b, type);
          Shape expectedShape = Shape.of(2, 2);
          // a- b
          Operand intersection = SetsOps.difference(tf, aa, bb);
          session.evaluate(cast(tf, tf.constant(expected), type), intersection);
          session.evaluate(tf.constant(expectedShape), tf.shape(intersection, TInt64.class));
        }
      }
  }
}
