package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossesImpl;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes Computes the logarithm of the hyperbolic cosine of the prediction error.
 *
 * <p><code>logcosh = log((exp(x) + exp(-x))/2)</code>, where <code>x</code> is the error <code>
 * predictions - y_true</code>.
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0.f, 1.f}, {0.f, 0.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{1.f, 1.f}, {0.f, 0.f}});
 *    LogCosh logcosh = new LogCosh(tf);
 *    Operand&lt;TFloat32&gt; result = logcosh.call(labels, predictions);
 *    // produces 0.108
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {0.8f, 0.2f});
 *    Operand&lt;TFloat32&gt; result = logcosh.call(labels, predictions, sampleWeight);
 *    // produces 0.087f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    LogCosh logcosh = new LogCosh(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = logcosh.call(labels, predictions);
 *    // produces 0.217f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    LogCosh logcosh = new LogCosh(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = logcosh.call(labels, predictions);
 *    // produces [0.217f, 0f]
 * </pre>
 */
public class LogCosh extends Loss {

  /**
   * Creates a LogCosh Loss using {@link Class#getSimpleName()} as the loss name and a Loss
   * Reduction of {@link Loss#REDUCTION_DEFAULT}
   *
   * @param tf the TensorFlow Ops
   */
  public LogCosh(Ops tf) {
    this(tf, null, Reduction.AUTO);
  }

  /**
   * Creates a LogCosh Loss using a Loss Reduction of {@link Loss#REDUCTION_DEFAULT}
   *
   * @param tf the TensorFlow Ops
   */
  public LogCosh(Ops tf, String name) {
    this(tf, name, Reduction.AUTO);
  }

  /**
   * Creates a LogCosh Loss using {@link Class#getSimpleName()} as the loss name
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to the loss.
   */
  public LogCosh(Ops tf, Reduction reduction) {
    this(tf, null, reduction);
  }

  /**
   * Creates a LogCosh Loss
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param reduction Type of Reduction to apply to the loss.
   */
  public LogCosh(Ops tf, String name, Reduction reduction) {
    super(tf, name, reduction);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber, U extends TNumber> Operand<T> call(
          Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    Operand<T> losses = Losses.logCosh(getTF(), labels, predictions);
    return LossesImpl.computeWeightedLoss(getTF(), losses, getReduction(), sampleWeights);
  }
}
