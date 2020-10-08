package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossesImpl;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the cosine similarity between labels and predictions.
 *
 * <p>Note that it is a negative quantity between -1 and 0, where 0 indicates orthogonality and
 * values closer to -1 indicate greater similarity. This makes it usable as a loss function in a
 * setting where you try to maximize the proximity between predictions and targets. If either labels
 * or predictions is a zero vector, cosine similarity will be 0 regardless of the proximity between
 * predictions and targets.
 *
 * <p><code>loss = -sum(l2Norm(labels) * l2Norm(predictions))</code>
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0.f, 1.f}, {1.f, 1.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{1.f, 0.f}, {1.f, 1.f}});
 *    CosineSimilarity cosineLoss = new CosineSimilarity(tf);
 *    Operand&lt;TFloat32&gt; result = cosineLoss.call(labels, predictions);
 *    // produces -0.5
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {0.8f, 0.2f});
 *    Operand&lt;TFloat32&gt; result = cosineLoss.call(labels, predictions, sampleWeight);
 *    // produces -0.0999f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    CosineSimilarity cosineLoss = new CosineSimilarity(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = cosineLoss.call(labels, predictions);
 *    // produces -0.999f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    CosineSimilarity cosineLoss = new CosineSimilarity(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = cosineLoss.call(labels, predictions);
 *    // produces [-0.f, -0.999f]
 * </pre>
 */
public class CosineSimilarity extends Loss {
  public static final int DEFAULT_AXIS = -1;
  public static final Reduction DEFAULT_REDUCTION = Reduction.AUTO;

  private final int axis;

  /**
   * Creates a Cosine Similarity Loss using {@link Class#getSimpleName()} as the loss name, an axis
   * of {@link #DEFAULT_AXIS}, and a Loss Reduction of {@link #DEFAULT_REDUCTION}
   *
   * @param tf the TensorFlow Ops
   */
  public CosineSimilarity(Ops tf) {

    this(tf, null, DEFAULT_AXIS, DEFAULT_REDUCTION);
  }

  /**
   * Creates a Cosine Similarity Loss using an axis of {@link #DEFAULT_AXIS}, and a Loss Reduction
   * of {@link #DEFAULT_REDUCTION}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   */
  public CosineSimilarity(Ops tf, String name) {

    this(tf, name, DEFAULT_AXIS, DEFAULT_REDUCTION);
  }

  /**
   * Creates a Cosine Similarity Loss using {@link Class#getSimpleName()} as the loss name, and a
   * Loss Reduction of {@link #DEFAULT_REDUCTION}
   *
   * @param tf the TensorFlow Ops
   * @param axis The dimension along which the cosine similarity is computed.
   */
  public CosineSimilarity(Ops tf, int axis) {

    this(tf, null, axis, DEFAULT_REDUCTION);
  }

  /**
   * Creates a Cosine Similarity Loss using a Loss Reduction of {@link #DEFAULT_REDUCTION}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param axis The dimension along which the cosine similarity is computed.
   */
  public CosineSimilarity(Ops tf, String name, int axis) {

    this(tf, name, axis, DEFAULT_REDUCTION);
  }

  /**
   * Creates a Cosine Similarity Loss using {@link Class#getSimpleName()} as the loss name and an
   * axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to the loss.
   */
  public CosineSimilarity(Ops tf, Reduction reduction) {

    this(tf, null, DEFAULT_AXIS, reduction);
  }

  /**
   * Creates a Cosine Similarity Loss using an axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param reduction Type of Reduction to apply to the loss.
   */
  public CosineSimilarity(Ops tf, String name, Reduction reduction) {

    this(tf, name, DEFAULT_AXIS, reduction);
  }

  /**
   * Creates a Cosine Similarity Loss using {@link Class#getSimpleName()} as the loss name
   *
   * @param tf the TensorFlow Ops
   * @param axis The dimension along which the cosine similarity is computed.
   * @param reduction Type of Reduction to apply to the loss.
   */
  public CosineSimilarity(Ops tf, int axis, Reduction reduction) {

    this(tf, null, axis, reduction);
  }

  /**
   * Creates a Cosine Similarity Loss
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param axis The dimension along which the cosine similarity is computed.
   * @param reduction Type of Reduction to apply to the loss.
   */
  public CosineSimilarity(Ops tf, String name, int axis, Reduction reduction) {
    super(tf, name, reduction);
    this.axis = axis;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber, U extends TNumber> Operand<T> call(
          Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    Operand<T> losses = Losses.cosineSimilarity(tf, labels, predictions, axis);
    return LossesImpl.computeWeightedLoss(tf, losses, getReduction(), sampleWeights);
  }
}
