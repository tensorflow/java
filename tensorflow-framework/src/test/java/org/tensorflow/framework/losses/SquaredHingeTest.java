package org.tensorflow.framework.losses;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

public class SquaredHingeTest {
    private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

    /**
     * Test of call method, of class SquaredHinge.
     */
    @Test
    public void testUnweighted() {
        for (TestSession.Mode tfMode : tfModes)
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            SquaredHinge instance = new SquaredHinge(tf);
            float[] trueArray = {0, 1, 0, 1, 0, 0, 1, 1};
            float[] predArray = {-0.3f, 0.2f, -0.1f, 1.6f, -0.25f, -1.f, 0.5f, 0.6f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 4)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 4)));
            Operand<TFloat32> loss = instance.call(yTrue, yPred);
            float expected = 0.364062f;
            testSession.evaluate(expected, loss);
        }
    }

    /**
     * Test of call method, of class SquaredHinge.
     */
    @Test
    public void testScalarWeighted() {
        for (TestSession.Mode tfMode : tfModes)
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            SquaredHinge instance = new SquaredHinge(tf);
            float[] trueArray = {0, 1, 0, 1, 0, 0, 1, 1};
            float[] predArray = {-0.3f, 0.2f, -0.1f, 1.6f, -0.25f, -1.f, 0.5f, 0.6f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 4)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 4)));
            Operand<TFloat32> sampleWeight = tf.constant(2.3f);
            Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
            float expected = 0.8373437f;
            testSession.evaluate(expected, loss);
        }
    }

    @Test
    public void testSampleWeighted() {
        for (TestSession.Mode tfMode : tfModes)
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            SquaredHinge instance = new SquaredHinge(tf);
            float[] sampleArray = {1.2f, 3.4f};
            float[] trueArray = {0, 1, 0, 1, 0, 0, 1, 1};
            float[] predArray = {-0.3f, 0.2f, -0.1f, 1.6f, -0.25f, -1.f, 0.5f, 0.6f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 4)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 4)));
            Operand<TFloat32> sampleWeight = tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 1)));
            Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
            float expected = 0.7043125f;
            testSession.evaluate(expected, loss);
        }
    }

    @Test
    public void testZeroWeighted() {
        for (TestSession.Mode tfMode : tfModes)
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            SquaredHinge instance = new SquaredHinge(tf);
            float[] trueArray = {0, 1, 0, 1, 0, 0, 1, 1};
            float[] predArray = {-0.3f, 0.2f, -0.1f, 1.6f, -0.25f, -1.f, 0.5f, 0.6f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 4)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 4)));
            Operand<TFloat32> sampleWeight = tf.constant(0.F);
            Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
            float expected = 0f;
            testSession.evaluate(expected, loss);
        }
    }

    @Test
    public void testTimestepWeighted() {
        for (TestSession.Mode tfMode : tfModes)
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            SquaredHinge instance = new SquaredHinge(tf, Reduction.AUTO);
            float[] trueArray = {0, 1, 0, 1, 0, 0, 1, 1};
            float[] predArray = {-0.3f, 0.2f, -0.1f, 1.6f, -0.25f, -1.f, 0.5f, 0.6f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 4, 1)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 4, 1)));
            float[] sampleArray = {3f, 6f, 5f, 0f, 4f, 2f, 1f, 3f};
            Operand<TFloat32> sampleWeight = tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 4)));
            Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);

            float expected = 1.54250000f;
            testSession.evaluate(expected, loss);
        }
    }
}
