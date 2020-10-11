package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossesImpl;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the mean absolute percentage error between labels and predictions.
 *
 * <p><code>loss = 100 * abs(labels - predictions) / labels</code>
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{2.f, 1.f}, {2.f, 3.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{1.f, 1.f}, {1.f, 0.f}});
 *    MeanAbsolutePercentageError mape = new MeanAbsolutePercentageError(tf);
 *    Operand&lt;TFloat32&gt; result = mape.call(labels, predictions);
 *    // produces 50f
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {0.7f, 0.3f});
 *    Operand&lt;TFloat32&gt; result = mape.call(labels, predictions, sampleWeight);
 *    // produces 20f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    MeanAbsolutePercentageError mape = new MeanAbsolutePercentageError(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = mape.call(labels, predictions);
 *    // produces 100.0f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    MeanAbsolutePercentageError mape = new MeanAbsolutePercentageError(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = mape.call(labels, predictions);
 *    // produces [25f, 75f]
 * </pre>
 */
public class MeanAbsolutePercentageError extends Loss {

  /**
   * Creates a MeanAbsolutePercentageError Loss using {@link Class#getSimpleName()} as the loss name
   * and a Loss Reduction of {@link Reduction#AUTO}
   *
   * @param tf the TensorFlow Ops
   */
  public MeanAbsolutePercentageError(Ops tf) {
    super(tf);
  }

  /**
   * Creates a MeanAbsolutePercentageError Loss using {@link Class#getSimpleName()} as the loss name
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to the loss.
   */
  public MeanAbsolutePercentageError(Ops tf, Reduction reduction) {
    super(tf, null, reduction);
  }

  /**
   * Creates a MeanAbsolutePercentageError
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param reduction Type of Reduction to apply to the loss.
   */
  public MeanAbsolutePercentageError(Ops tf, String name, Reduction reduction) {
    super(tf, name, reduction);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber, U extends TNumber> Operand<T> call(
          Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    Operand<T> losses = Losses.meanAbsolutePercentageError(getTF(), labels, predictions);
    return LossesImpl.computeWeightedLoss(getTF(), losses, getReduction(), sampleWeights);
  }
}
