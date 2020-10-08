package org.tensorflow.framework.losses;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

public class KLDivergenceTest {
    private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

    /**
     * Test of call method, of class KLDivergence.
     */
    @Test
    public void testUnweighted() {
        for (TestSession.Mode tfMode : tfModes)
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            KLDivergence instance = new KLDivergence(tf);
            float[] predArray = {.4f, .9f, .12f, .36f, .3f, .4f};
            float[] trueArray = {.5f, .8f, .12f, .7f, .43f, .8f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> loss = instance.call(yTrue, yPred);
            float expected = 0.5960738398643668f;
            testSession.evaluate(expected, loss);
        }
    }

    /**
     * Test of call method, of class KLDivergence.
     */
    @Test
    public void testScalarWeighted() {
        for (TestSession.Mode tfMode : tfModes)
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            KLDivergence instance = new KLDivergence(tf);
            float[] predArray = {.4f, .9f, .12f, .36f, .3f, .4f};
            float[] trueArray = {.5f, .8f, .12f, .7f, .43f, .8f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> sampleWeight = tf.constant(2.3f);
            Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
            float expected = 1.3709698316880434f;
            testSession.evaluate(expected, loss);
        }
    }

    @Test
    public void testSampleWeighted() {
        for (TestSession.Mode tfMode : tfModes)
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            KLDivergence instance = new KLDivergence(tf);
            float[] predArray = {.4f, .9f, .12f, .36f, .3f, .4f};
            float[] trueArray = {.5f, .8f, .12f, .7f, .43f, .8f};
            float[] sampleArray = {1.2f, 3.4f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> sampleWeight = tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 1)));
            Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);
            float expected = 2.0075711736936492f;
            testSession.evaluate(expected, loss);
        }
    }

    @Test
    public void testZeroWeighted() {
        for (TestSession.Mode tfMode : tfModes)
            try (TestSession testSession = TestSession.createTestSession(tfMode)) {
            Ops tf = testSession.getTF();
            KLDivergence instance = new KLDivergence(tf);
            float[] predArray = {.4f, .9f, .12f, .36f, .3f, .4f};
            float[] trueArray = {.5f, .8f, .12f, .7f, .43f, .8f};
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
            KLDivergence instance = new KLDivergence(tf, Reduction.AUTO);
            float[] predArray = {.4f, .9f, .12f, .36f, .3f, .4f};
            float[] trueArray = {.5f, .8f, .12f, .7f, .43f, .8f};
            float[] sampleArray = {3f, 6f, 5f, 0f, 4f, 2f};
            Operand<TFloat32> yTrue = tf.reshape(tf.constant(trueArray), tf.constant(Shape.of(2, 3, 1)));
            Operand<TFloat32> yPred = tf.reshape(tf.constant(predArray), tf.constant(Shape.of(2, 3, 1)));
            Operand<TFloat32> sampleWeight = tf.reshape(tf.constant(sampleArray), tf.constant(Shape.of(2, 3)));
            Operand<TFloat32> loss = instance.call(yTrue, yPred, sampleWeight);

            float expected = 0.2495994912084345f;
            testSession.evaluate(expected, loss);
        }
    }

}
