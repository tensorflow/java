package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossesImpl;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the mean of squares of errors between labels and predictions.
 *
 * <p><code>loss = loss = square(labels - predictions)</code>
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0.f, 1.f}, {0.f, 0.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{1.f, 1.f}, {1.f, 0.f}});
 *    MeanSquaredError mse = new MeanSquaredError(tf);
 *    Operand&lt;TFloat32&gt; result = mse.call(labels, predictions);
 *    // produces 0.5f
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {0.7f, 0.3f});
 *    Operand&lt;TFloat32&gt; result = mse.call(labels, predictions, sampleWeight);
 *    // produces 0.25f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    MeanSquaredError mse = new MeanSquaredError(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = mse.call(labels, predictions);
 *    // produces 1.0f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    MeanSquaredError mse = new MeanSquaredError(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = mse.call(labels, predictions);
 *    // produces [0.5f, 0.5f]
 * </pre>
 */
public class MeanSquaredError extends Loss {

  /**
   * Creates a MeanSquaredError Loss using {@link Class#getSimpleName()} as the loss name and a Loss
   * Reduction of {@link * Reduction#AUTO}
   *
   * @param tf the TensorFlow Ops
   */
  public MeanSquaredError(Ops tf) {
    super(tf);
  }

  /**
   * Creates a MeanSquaredError Loss using {@link Class#getSimpleName()} as the loss name
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to the loss.
   */
  public MeanSquaredError(Ops tf, Reduction reduction) {
    super(tf, null, reduction);
  }

  /**
   * Creates a MeanSquaredError
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param reduction Type of Reduction to apply to the loss.
   */
  public MeanSquaredError(Ops tf, String name, Reduction reduction) {
    super(tf, name, reduction);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber, U extends TNumber> Operand<T> call(
          Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    Operand<T> losses = Losses.meanSquaredError(tf, labels, predictions);
    return LossesImpl.computeWeightedLoss(tf, losses, getReduction(), sampleWeights);
  }
}