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
   * of {@link Loss#REDUCTION_DEFAULT}
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

  /**
   * Generates an Operand that calculates the loss.
   *
   * <p>If run in Graph mode, the computation will throw {@link
   * org.tensorflow.exceptions.TFInvalidArgumentException} if the label values are not in the set
   * [-1., 0., 1.]. In Eager Mode, this call will throw {@link IllegalArgumentException}, if the
   * label values are not in the set [-1., 0., 1.].
   *
   * @param labels the truth values or labels, must be either -1, 0, or 1. Values are expected to be
   *     -1 or 1. If binary (0 or 1) labels are provided they will be converted  to -1 or 1.
   * @param predictions the predictions, values must be in the range [0. to 1.] inclusive.
   * @param sampleWeights Optional sample_weight acts as a coefficient for the loss. If a scalar is
   *     provided, then the loss is simply scaled by the given value. If sample_weight is a tensor
   *     of size [batch_size], then the total loss for each sample of the batch is rescaled by the
   *     corresponding element in the sample_weight vector. If the shape of sample_weight is
   *     [batch_size, d0, .. dN-1] (or can be broadcasted to this shape), then each loss element of
   *     predictions is scaled by the corresponding value of sample_weight. (Note on dN-1: all loss
   *     functions reduce by 1 dimension, usually axis=-1.)
   * @param <T> The data type of the predictions, sampleWeights and loss.
   * @param <U> The data type of the labels.
   * @return the loss
   * @throws IllegalArgumentException if the predictions are outside the range [0.-1.].
   */
  @Override
  public <T extends TNumber, U extends TNumber> Operand<T> call(
      Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    @SuppressWarnings("unchecked")
    Operand<T> tLabels = predictions.asOutput().dataType() == labels.asOutput().dataType() ?
            (Operand<T>)labels :
            tf.dtypes.cast(labels, predictions.asOutput().dataType());
    tLabels = LossesImpl.valueCheck(
            getTF(),
            "labels value check [-1, 0, 1]",
            tLabels,
            getTF().dtypes.cast(getTF().constant(new int[] { -1, 0, 1}),
                    predictions.asOutput().dataType()));

    Operand<T> losses = Losses.hinge(getTF(), tLabels, predictions);
    return LossesImpl.computeWeightedLoss(getTF(), losses, getReduction(), sampleWeights);
  }
}
