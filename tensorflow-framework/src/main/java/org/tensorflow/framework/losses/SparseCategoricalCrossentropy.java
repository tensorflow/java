package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossesImpl;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the crossentropy loss between labels and predictions.
 *
 * <p>Use this crossentropy loss function when there are two or more label classes. The labels are
 * expected to be provided as integers. If you want to provide labels using <code>one-hot</code>
 * representation, please use {@link CategoricalCrossentropy} loss. There should be <code># classes
 * </code> floating point values per feature for <code>predictions</code> and a single floating
 * point value per feature for <code>label</code>.
 *
 * <p>In the snippet below, there is a single floating point value per example for <code>labels
 * </code> and <code># classes</code> floating pointing values per example for <code>predictions
 * </code>. The shape of <code>labels</code> is <code>[batch_size]</code> and the shape of <code>
 * predictions</code> is <code>[batch_size, num_classes]</code>.
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[] {1, 2});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{0.05f, 0.95f, 0f}, {0.1f, 0.8f, 0.1f}});
 *    SparseCategoricalCrossentropy sparseCCE = new SparseCategoricalCrossentropy(tf);
 *    Operand&lt;TFloat32&gt; result = sparseCCE.call(labels, predictions);
 *    // produces 1.177f
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {0.3f, 0.7f});
 *    Operand&lt;TFloat32&gt; result = sparseCCE.call(labels, predictions, sampleWeight);
 *    // produces 0.814f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    SparseCategoricalCrossentropy sparseCCE = new SparseCategoricalCrossentropy(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = sparseCCE.call(labels, predictions);
 *    // produces 2.354f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    SparseCategoricalCrossentropy sparseCCE = new SparseCategoricalCrossentropy(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = sparseCCE.call(labels, predictions);
 *    // produces [0.0513f, 2.303f]
 * </pre>
 */
public class SparseCategoricalCrossentropy extends Loss {
  public static final boolean FROM_LOGITS_DEFAULT = false;
  public static final int AXIS_DEFAULT = -1;
  public static final Reduction REDUCTION_DEFAULT = Reduction.AUTO;

  private final boolean fromLogits;
  private final int axis;

  /**
   * Creates a SparseCategoricalCrossentropy loss using {@link Class#getSimpleName()} as the loss
   * name, a Loss Reduction of {@link Reduction#AUTO}, and fromLogits={@link #FROM_LOGITS_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   */
  public SparseCategoricalCrossentropy(Ops tf) {
    this(tf, null, FROM_LOGITS_DEFAULT, REDUCTION_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy loss using a Loss Reduction of {@link Reduction#AUTO},
   * and fromLogits={@link #FROM_LOGITS_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss function
   */
  public SparseCategoricalCrossentropy(Ops tf, String name) {
    this(tf, name, FROM_LOGITS_DEFAULT, REDUCTION_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy loss using {@link Class#getSimpleName()} as the loss
   * name, with Reduction.AUTO and fromLogits={@link #FROM_LOGITS_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to loss.
   */
  public SparseCategoricalCrossentropy(Ops tf, Reduction reduction) {
    this(tf, null, FROM_LOGITS_DEFAULT, reduction, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy loss with Reduction.AUTO and fromLogits={@link
   * #FROM_LOGITS_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss function
   * @param reduction Type of Reduction to apply to loss.
   */
  public SparseCategoricalCrossentropy(Ops tf, String name, Reduction reduction) {
    this(tf, name, FROM_LOGITS_DEFAULT, reduction, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy using a Loss Reduction of {@link Reduction#AUTO}, and
   * fromLogits={@link #FROM_LOGITS_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss function
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   */
  public SparseCategoricalCrossentropy(Ops tf, String name, boolean fromLogits) {
    this(tf, name, fromLogits, REDUCTION_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy loss using {@link Class#getSimpleName()} as the loss
   * name, a Loss Reduction of {@link Reduction#AUTO} and fromLogits={@link #FROM_LOGITS_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   */
  public SparseCategoricalCrossentropy(Ops tf, boolean fromLogits) {
    this(tf, null, fromLogits, REDUCTION_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy loss using {@link Class#getSimpleName()} as the loss
   * name,
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param reduction Type of Reduction to apply to loss.
   */
  public SparseCategoricalCrossentropy(Ops tf, boolean fromLogits, Reduction reduction) {
    this(tf, null, fromLogits, reduction, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss function
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param reduction Type of Reduction to apply to loss.
   * @param axis The channels axis. <code>axis=-1</code> corresponds to data format `Channels Last'
   *     and <code>axis=1</code> corresponds to data format 'Channels First'.
   */
  public SparseCategoricalCrossentropy(
      Ops tf, String name, boolean fromLogits, Reduction reduction, int axis) {
    super(tf, name, reduction);
    this.fromLogits = fromLogits;
    this.axis = axis;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber, U extends TNumber> Operand<T> call(
      Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    Operand<T> losses =
        Losses.sparseCategoricalCrossentropy(tf, labels, predictions, fromLogits, axis);
    return LossesImpl.computeWeightedLoss(tf, losses, getReduction(), sampleWeights);
  }
}
