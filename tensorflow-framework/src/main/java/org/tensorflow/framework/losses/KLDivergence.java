package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossesImpl;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes Kullback-Leibler divergence loss between labels and predictions.
 *
 * <p><code>loss = labels * log(labels / predictions)</code>
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0.f, 1.f}, {0.f, 0.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{0.6f, 0.4f}, {0.4f, 0.6f}});
 *    KLDivergence kld = new KLDivergence(tf);
 *    Operand&lt;TFloat32&gt; result = kld.call(labels, predictions);
 *    // produces 0.458
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {0.8f, 0.2f});
 *    Operand&lt;TFloat32&gt; result = kld.call(labels, predictions, sampleWeight);
 *    // produces 0.366f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    KLDivergence kld = new KLDivergence(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = kld.call(labels, predictions);
 *    // produces 0.916f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    KLDivergence kld = new KLDivergence(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = kld.call(labels, predictions);
 *    // produces [0.916f, -3.08e-06f]
 * </pre>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Kullback?Leibler_divergence">Kullback?Leibler
 *     divergence</a>
 */
public class KLDivergence extends Loss {

  /**
   * Creates a Kullback Leibler Divergence Loss using {@link Class#getSimpleName()} as the loss name
   * and a Loss Reduction of {@link Reduction#AUTO}
   *
   * @param tf the TensorFlow Ops
   */
  public KLDivergence(Ops tf) {
    super(tf);
  }

  /**
   * Creates a Kullback Leibler Divergence Loss Loss using {@link Class#getSimpleName()} as the loss
   * name
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to the loss.
   */
  public KLDivergence(Ops tf, Reduction reduction) {
    super(tf, null, reduction);
  }

  /**
   * Creates a Kullback Leibler Divergence Loss
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param reduction Type of Reduction to apply to the loss.
   */
  public KLDivergence(Ops tf, String name, Reduction reduction) {
    super(tf, name, reduction);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber, U extends TNumber> Operand<T> call(
          Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    Operand<T> losses = Losses.kullbackLeiblerDivergence(getTF(), labels, predictions);
    return LossesImpl.computeWeightedLoss(getTF(), losses, getReduction(), sampleWeights);
  }
}
