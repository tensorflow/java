package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossesImpl;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the crossentropy loss between the labels and predictions.
 *
 * <p>Use this crossentropy loss function when there are two or more label classes. We expect labels
 * to be provided in a one_hot representation. If you want to provide labels as integers, please use
 * {@link SparseCategoricalCrossentropy} loss. There should be <code># classes</code> floating point
 * values per feature.
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0, 1, 0}, {0, 0, 1}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{0.05f, 0.95f, 0f}, {0.1f, 0.8f, 0.1f}});
 *    CategoricalCrossentropy cce = new CategoricalCrossentropy(tf);
 *    Operand&lt;TFloat32&gt; result = cce.call(labels, predictions);
 *    // produces 1.177
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {0.3f, 0.7f});
 *    Operand&lt;TFloat32&gt; result = cce.call(labels, predictions, sampleWeight);
 *    // produces 0.814f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    CategoricalCrossentropy cce = new CategoricalCrossentropy(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = cce.call(labels, predictions);
 *    // produces 2.354f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    CategoricalCrossentropy cce =
 *        new CategoricalCrossentropy(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = cce.call(labels, predictions);
 *    // produces [0.0513f, 2.303f]
 * </pre>
 */
public class CategoricalCrossentropy extends Loss {
  public static final boolean FROM_LOGITS_DEFAULT = false;
  public static final float LABEL_SMOOTHING_DEFAULT = 0.0f;
  public static final Reduction REDUCTION_DEFAULT = Reduction.AUTO;
  public static final int DEFAULT_AXIS = -1;

  private final boolean fromLogits;
  private final float labelSmoothing;
  private final int axis;

  /**
   * Creates a categorical cross entropy Loss using {@link Class#getSimpleName()} as the loss name,
   * {@link #FROM_LOGITS_DEFAULT} for fromLogits, {@link #LABEL_SMOOTHING_DEFAULT} for
   * labelSmoothing, a Loss Reduction of {@link * Reduction#AUTO}, and an axis of {@link
   * #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   */
  public CategoricalCrossentropy(Ops tf) {
    this(tf, null, FROM_LOGITS_DEFAULT, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using {@link #FROM_LOGITS_DEFAULT} for fromLogits,
   * {@link #LABEL_SMOOTHING_DEFAULT} for labelSmoothing, a Loss Reduction of {@link *
   * Reduction#AUTO}, and an axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss
   */
  public CategoricalCrossentropy(Ops tf, String name) {
    this(tf, name, FROM_LOGITS_DEFAULT, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using {@link Class#getSimpleName()} as the loss name,
   * {@link #FROM_LOGITS_DEFAULT} for fromLogits, {@link #LABEL_SMOOTHING_DEFAULT} for
   * labelSmoothing and an axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to loss.
   */
  public CategoricalCrossentropy(Ops tf, Reduction reduction) {
    this(tf, null, FROM_LOGITS_DEFAULT, LABEL_SMOOTHING_DEFAULT, reduction, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss {@link #FROM_LOGITS_DEFAULT} for fromLogits, {@link
   * #LABEL_SMOOTHING_DEFAULT} for labelSmoothing, and an axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss
   * @param reduction Type of Reduction to apply to loss.
   */
  public CategoricalCrossentropy(Ops tf, String name, Reduction reduction) {
    this(tf, name, FROM_LOGITS_DEFAULT, LABEL_SMOOTHING_DEFAULT, reduction, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using {@link Class#getSimpleName()} as the loss name,
   * {@link #LABEL_SMOOTHING_DEFAULT} for labelSmoothing, a Loss Reduction of {@link *
   * Reduction#AUTO}, and an axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   */
  public CategoricalCrossentropy(Ops tf, boolean fromLogits) {
    this(tf, null, fromLogits, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using {@link #LABEL_SMOOTHING_DEFAULT} for
   * labelSmoothing, a Loss Reduction of {@link * Reduction#AUTO}, and a channel axis of {@link
   * #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   */
  public CategoricalCrossentropy(Ops tf, String name, boolean fromLogits) {
    this(tf, name, fromLogits, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using {@link Class#getSimpleName()} as the loss name,
   * a Loss Reduction of {@link * Reduction#AUTO}, and a channel axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing Float in [0, 1]. When 0, no smoothing occurs. When > 0, we compute the
   *     loss between the predicted labels and a smoothed version of the true labels, where the
   *     smoothing squeezes the labels towards 0.5. Larger values of label_smoothing correspond to
   *     heavier smoothing.
   */
  public CategoricalCrossentropy(Ops tf, boolean fromLogits, float labelSmoothing) {
    this(tf, null, fromLogits, labelSmoothing, REDUCTION_DEFAULT, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using a Loss Reduction of {@link * Reduction#AUTO},
   * and a channel axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing Float in [0, 1]. When 0, no smoothing occurs. When > 0, we compute the
   *     loss between the predicted labels and a smoothed version of the true labels, where the
   *     smoothing squeezes the labels towards 0.5. Larger values of label_smoothing correspond to
   *     heavier smoothing.
   */
  public CategoricalCrossentropy(Ops tf, String name, boolean fromLogits, float labelSmoothing) {
    this(tf, name, fromLogits, labelSmoothing, REDUCTION_DEFAULT, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using {@link Class#getSimpleName()} as the loss name
   * and a channel axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing Float in [0, 1]. When 0, no smoothing occurs. When > 0, we compute the
   *     loss between the predicted labels and a smoothed version of the true labels, where the
   *     smoothing squeezes the labels towards 0.5. Larger values of label_smoothing correspond to
   *     heavier smoothing.
   * @param reduction Type of Reduction to apply to loss.
   */
  public CategoricalCrossentropy(
      Ops tf, boolean fromLogits, float labelSmoothing, Reduction reduction) {
    this(tf, null, fromLogits, labelSmoothing, reduction, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing Float in [0, 1]. When 0, no smoothing occurs. When > 0, we compute the
   *     loss between the predicted labels and a smoothed version of the true labels, where the
   *     smoothing squeezes the labels towards 0.5. Larger values of label_smoothing correspond to
   *     heavier smoothing.
   * @param reduction Type of Reduction to apply to loss.
   * @param axis The channels axis. <code>axis=-1</code> corresponds to data format `Channels Last'
   *     and <code>axis=1</code> corresponds to data format 'Channels First'.
   */
  public CategoricalCrossentropy(
      Ops tf,
      String name,
      boolean fromLogits,
      float labelSmoothing,
      Reduction reduction,
      int axis) {
    super(tf, name, reduction);
    this.fromLogits = fromLogits;
    this.labelSmoothing = labelSmoothing;
    this.axis = axis;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber, U extends TNumber> Operand<T> call(
          Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    Operand<T> losses =
        Losses.categoricalCrossentropy(tf, labels, predictions, fromLogits, labelSmoothing, axis);
    return LossesImpl.computeWeightedLoss(tf, losses, getReduction(), sampleWeights);
  }
}
