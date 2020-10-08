package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossesImpl;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the hinge loss between labels and predictions.
 *
 * <p><code>loss = maximum(1 - labels * predictions, 0)</code></p>.
 *
 * <p><code>labels/code> values are expected to be -1 or 1.
 * If binary (0 or 1) labels are provided, they will be converted to -1 or 1.</p>
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0.f, 1.f}, {0.f, 0.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{0.6f, 0.4f}, {0.4f, 0.6f}});
 *    Hinge hingeLoss = new Hinge(tf);
 *    Operand&lt;TFloat32&gt; result = hingeLoss.call(labels, predictions);
 *    // produces 1.3f
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {1.f, 0.f});
 *    Operand&lt;TFloat32&gt; result = hingeLoss.call(labels, predictions, sampleWeight);
 *    // produces 0.55f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    Hinge hingeLoss = new Hinge(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = hingeLoss.call(labels, predictions);
 *    // produces 2.6f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    Hinge hingeLoss = new Hinge(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = hingeLoss.call(labels, predictions);
 *    // produces [1.1f, 1.5f]
 * </pre>
 */
public class Hinge extends Loss {

  /**
   * Creates a Hinge Loss using {@link Class#getSimpleName()} as the loss name and a Loss Reduction
   * of {@link * Reduction#AUTO}
   *
   * @param tf the TensorFlow Ops
   */
  public Hinge(Ops tf) {
    this(tf, null, Reduction.AUTO);
  }

  /**
   * Creates a Hinge Loss using {@link Class#getSimpleName()} as the loss name
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to the loss.
   */
  public Hinge(Ops tf, Reduction reduction) {
    super(tf, null, reduction);
  }

  /**
   * Creates a Hinge
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param reduction Type of Reduction to apply to the loss.
   */
  public Hinge(Ops tf, String name, Reduction reduction) {
    super(tf, name, reduction);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber, U extends TNumber> Operand<T> call(
          Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    Operand<T> losses = Losses.hinge(tf, labels, predictions);
    return LossesImpl.computeWeightedLoss(tf, losses, getReduction(), sampleWeights);
  }
}
