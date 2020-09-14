package org.tensorflow.framework.optimizers.schedules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialDecayTest {

    public PolynomialDecayTest() {}

    @Test
    public void testBeginWithCycle() {
        float initialLearningRate = 0.1f;
        int decaySteps = 10;
        float decayRate = 0.96f;
        float epsilon = 1e-6f;
        PolynomialDecay instance = new PolynomialDecay(initialLearningRate, decaySteps, true);
        float expected = initialLearningRate;
        float actual = instance.call(0);
        assertEquals(expected, actual, epsilon);

    }

}