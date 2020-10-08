package org.tensorflow.framework.losses;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

public class PoissonTest {
    private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

    /**
     * Test of call method, of class Poisson.
     */
    @Test
    public void testUnweighted() {
        for (TestSession.Mode tfMode : tfModes)
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            Poisson instance = new Poisson(tf);
            float[] predArray = {1f, 9f, 2f, 5f, 2f, 6f};
            float[] trueArray = {4f, 8f, 12f, 8f, 1f, 3f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> loss = instance.call(yTrue, yPred);
            float expected = -3.306581945521002f;
            testSession.evaluate(expected, loss);
        }
    }

    /**
     * Test of call method, of class Poisson.
     */
    @Test
    public void testScalarWeighted() {
        for (TestSession.Mode tfMode : tfModes)
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            Poisson instance = new Poisson(tf);
            float[] predArray = {1f, 9f, 2f, 5f, 2f, 6f};
            float[] trueArray = {4f, 8f, 12f, 8f, 1f, 3f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> sampleWeight = tf.constant(2.3f);
            Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
            float expected = -7.605138474698304f;
            testSession.evaluate(expected, loss);
        }
    }

    @Test
    public void testSampleWeighted() {
        for (TestSession.Mode tfMode : tfModes)
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            Poisson instance = new Poisson(tf);
            float[] predArray = {1f, 9f, 2f, 5f, 2f, 6f};
            float[] trueArray = {4f, 8f, 12f, 8f, 1f, 3f};
            float[] sampleArray = {1.2f, 3.4f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> sampleWeight = tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 1)));
            Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
            float expected = -6.147338926788071f;
            testSession.evaluate(expected, loss);
        }
    }

    @Test
    public void testZeroWeighted() {
        for (TestSession.Mode tfMode : tfModes)
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            Poisson instance = new Poisson(tf);
            float[] predArray = {1f, 9f, 2f, 5f, 2f, 6f};
            float[] trueArray = {4f, 8f, 12f, 8f, 1f, 3f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
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
            Poisson instance = new Poisson(tf, Reduction.AUTO);
            float[] predArray = {1f, 9f, 2f, 5f, 2f, 6f};
            float[] trueArray = {4f, 8f, 12f, 8f, 1f, 3f};
            float[] sampleArray = {3f, 6f, 5f, 0f, 4f, 2f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3, 1)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3, 1)));
            Operand<TFloat32> sampleWeight = tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);

            float expected = -12.263126013890561f;
            testSession.evaluate(expected, loss);
        }
    }
}
