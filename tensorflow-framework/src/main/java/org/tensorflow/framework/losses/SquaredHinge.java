package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossesImpl;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the squared hinge loss between labels and predictions.
 *
 * <p><code>loss = square(maximum(1 - labels * predictions, 0))</code>
 *
 * <p><code>labels</code> values are expected to be -1 or 1. If binary (0 or 1) labels are provided, they will be
 * converted to -1 or 1.
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0., 1.}, {0., 0.}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{0.6f, 0.4f}, {0.4f, 0.6f}});
 *    SquaredHinge squaredHinge = new SquaredHinge(tf);
 *    Operand&lt;TFloat32&gt; result = squaredHinge.call(labels, predictions);
 *    // produces 1.86f
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {1.f, 0.f});
 *    Operand&lt;TFloat32&gt; result = squaredHinge.call(labels, predictions,
 *                                                  sampleWeight);
 *    // produces 0.73f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    SquaredHinge squaredHinge = new SquaredHinge(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = squaredHinge.call(labels, predictions);
 *    // produces 3.72f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    SquaredHinge squaredHinge = new SquaredHinge(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = squaredHinge.call(labels, predictions);
 *    // produces [1.46f, 2.26f]
 * </pre>
 */
public class SquaredHinge extends Loss {

  /**
   * Creates a Squared Hinge Loss using {@link Class#getSimpleName()} as the loss name and a Loss
   * Reduction of {@link Reduction#AUTO}
   *
   * @param tf the TensorFlow Ops
   */
  public SquaredHinge(Ops tf) {
    super(tf);
  }

  /**
   * Creates a Squared Hinge Loss using {@link Class#getSimpleName()} as the loss name
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to the loss.
   */
  public SquaredHinge(Ops tf, Reduction reduction) {
    super(tf, null, reduction);
  }

  /**
   * Creates a Squared Hinge
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param reduction Type of Reduction to apply to the loss.
   */
  public SquaredHinge(Ops tf, String name, Reduction reduction) {
    super(tf, name, reduction);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber, U extends TNumber> Operand<T> call(
      Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    Operand<T> losses = Losses.squaredHinge(getTF(), labels, predictions);
    return LossesImpl.computeWeightedLoss(getTF(), losses, getReduction(), sampleWeights);
  }
}
