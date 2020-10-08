package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossesImpl;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the cross-entropy loss between true labels and predicted labels.
 *
 * <p>Use this cross-entropy loss when there are only two label classes (assumed to be 0 and 1). For
 * each example, there should be a single floating-point value per prediction.
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0.f, 1.f}, {0.f, 0.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{0.6f, 0.4f}, {0.4f, 0.6f}});
 *    BinaryCrossentropy bce = new BinaryCrossentropy(tf);
 *    Operand&lt;TFloat32&gt; result = bce.call(labels, predictions);
 *    // produces 0.815
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {1.f, 0.f});
 *    Operand&lt;TFloat32&gt; result = bce.call(labels, predictions, sampleWeight);
 *    // produces 0.458f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    BinaryCrossentropy bce = new BinaryCrossentropy(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = bce.call(labels, predictions);
 *    // produces 1.630f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    BinaryCrossentropy bce = new BinaryCrossentropy(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = bce.call(labels, predictions);
 *    // produces [0.916f, 0.714f]
 * </pre>
 *
 */
public class BinaryCrossentropy extends Loss {
  public static final boolean FROM_LOGITS_DEFAULT = false;
  public static final float LABEL_SMOOTHING_DEFAULT = 0.0f;
  public static final Reduction REDUCTION_DEFAULT = Reduction.AUTO;

  private final boolean fromLogits;
  private final float labelSmoothing;

  /**
   * Creates a Binary Crossentropy Loss using {@link Class#getSimpleName()} as the loss name, {@link
   * #FROM_LOGITS_DEFAULT} for fromLogits, {@link #LABEL_SMOOTHING_DEFAULT} for labelSmoothing and a
   * Loss Reduction of {@link * Reduction#AUTO}
   *
   *
   *
   * @param tf the TensorFlow Ops
   */
  public BinaryCrossentropy(Ops tf) {
    this(tf, null, FROM_LOGITS_DEFAULT, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT);
  }

  /**
   * Creates a Binary Crossentropy loss using {@link Class#getSimpleName()} as the loss name, {@link
   * #FROM_LOGITS_DEFAULT} for fromLogits, and {@link #LABEL_SMOOTHING_DEFAULT} for labelSmoothing
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to the loss.
   */
  public BinaryCrossentropy(Ops tf, Reduction reduction) {
    this(tf, null, FROM_LOGITS_DEFAULT, LABEL_SMOOTHING_DEFAULT, reduction);
  }

  /**
   * Creates a Binary Crossentropy loss using using {@link Class#getSimpleName()} as the loss name,
   * labelSmoothing of {@link #LABEL_SMOOTHING_DEFAULT}, a reduction of {@link #REDUCTION_DEFAULT},
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   */
  public BinaryCrossentropy(Ops tf, boolean fromLogits) {
    this(tf, null, fromLogits, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT);
  }

  /**
   * Creates a Binary Crossentropy loss using labelSmoothing of {@link #LABEL_SMOOTHING_DEFAULT} a
   * reduction of {@link #REDUCTION_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   */
  public BinaryCrossentropy(Ops tf, String name, boolean fromLogits) {
    this(tf, name, fromLogits, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT);
  }

  /**
   * Creates a Binary Crossentropy loss using using {@link Class#getSimpleName()} as the loss name,
   * and a reduction of {@link #REDUCTION_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing A number in the range, [0, 1]. When 0, no smoothing occurs. When &gt; 0,
   *     compute the loss between the predicted labels and a smoothed version of the true labels,
   *     where the smoothing squeezes the labels towards 0.5. Larger values of label_smoothing
   *     correspond to heavier smoothing.
   */
  public BinaryCrossentropy(Ops tf, boolean fromLogits, float labelSmoothing) {
    this(tf, null, fromLogits, labelSmoothing, REDUCTION_DEFAULT);
  }

  /**
   * Creates a Binary Crossentropy loss using a reduction of {@link #REDUCTION_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing A number in the range, [0, 1]. When 0, no smoothing occurs. When &gt; 0,
   *     compute the loss between the predicted labels and a smoothed version of the true labels,
   *     where the smoothing squeezes the labels towards 0.5. Larger values of label_smoothing
   *     correspond to heavier smoothing.
   */
  public BinaryCrossentropy(Ops tf, String name, boolean fromLogits, float labelSmoothing) {
    this(tf, name, fromLogits, labelSmoothing, REDUCTION_DEFAULT);
  }

  /**
   * Creates a Binary Crossentropy loss
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing A number in the range, [0, 1]. When 0, no smoothing occurs. When &gt; 0,
   *     compute the loss between the predicted labels and a smoothed version of the true labels,
   *     where the smoothing squeezes the labels towards 0.5. Larger values of label_smoothing
   *     correspond to heavier smoothing.
   * @param reduction Type of Reduction to apply to the loss.
   */
  public BinaryCrossentropy(
          Ops tf,  boolean fromLogits, float labelSmoothing, Reduction reduction) {
      this(tf, null, fromLogits, labelSmoothing, reduction);
  }

  /**
   * Creates a Binary Crossentropy loss
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing A number in the range, [0, 1]. When 0, no smoothing occurs. When &gt; 0,
   *     compute the loss between the predicted labels and a smoothed version of the true labels,
   *     where the smoothing squeezes the labels towards 0.5. Larger values of label_smoothing
   *     correspond to heavier smoothing.
   * @param reduction Type of Reduction to apply to the loss.
   */
  public BinaryCrossentropy(
      Ops tf, String name, boolean fromLogits, float labelSmoothing, Reduction reduction) {
    super(tf, name, reduction);
    this.fromLogits = fromLogits;
    this.labelSmoothing = labelSmoothing;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber, U extends TNumber> Operand<T> call(
      Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    Operand<T> losses =
        Losses.binaryCrossentropy(tf, labels, predictions, fromLogits, labelSmoothing);
    return LossesImpl.computeWeightedLoss(tf, losses, getReduction(), sampleWeights);
  }
}
