package org.tensorflow.framework.regularizers;

import org.junit.jupiter.api.Test;
import org.tensorflow.Operand;
import org.tensorflow.framework.utils.TestSession;
import org.tensorflow.op.Ops;
import org.tensorflow.types.TFloat32;

class RegularizerLossTest {
  private final TestSession.Mode[] tfModes = {TestSession.Mode.EAGER, TestSession.Mode.GRAPH};

  @Test
  public void testCreate() {
    for (TestSession.Mode tfMode : tfModes)
      try (TestSession session = TestSession.createTestSession(tfMode)) {
        Ops tf = session.getTF();
        L1L2 regularizer = new L1L2(0.01f, 0f);
        float[][] w = {{1.0f, 0.9f, 0.8f}, {1.2f, 0.7f, 1.1f}};
        Operand<TFloat32> weights = tf.constant(w);
        Operand<TFloat32> regularizerResult = regularizer.call(tf, weights);
        RegularizerLoss lossInstance = new RegularizerLoss(regularizer);

        Operand<TFloat32> loss = lossInstance.call(tf, null, null, weights);
        session.evaluate(regularizerResult, loss);
      }
  }
}
