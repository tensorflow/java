package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

public abstract class Loss {
  protected final Ops tf;
  protected final Reduction reduction;

  /**
   * Creates a Loss using {@link Class#getSimpleName()}  as the name and a Loss Reduction of {@link
   * Reduction#AUTO}
   *
   * @param tf the TensorFlow Ops
   */
  protected Loss(Ops tf) {
    this(tf, null, Reduction.AUTO);
  }

  /**
   * Creates a Loss using a Loss Reduction of {@link Reduction#AUTO}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this Loss
   */
  protected Loss(Ops tf, String name) {
    this(tf, name, Reduction.AUTO);
  }

  /**
   * Creates a Loss
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss
   * @param reduction Type of Reduction to apply to the loss.
   */
  protected Loss(Ops tf, String name, Reduction reduction) {
    this.tf = name != null ? tf.withSubScope(name) : tf.withSubScope(getClass().getSimpleName());
    this.reduction = reduction;
  }

  /**
   * Calculates the loss
   *
   * @param labels the truth values or labels
   * @param predictions the predictions
   * @param <T> The data type of the labels, predictions and loss.
   * @return the loss
   */
  public <T extends TNumber, U extends TNumber> Operand<T> call(Operand<U> labels, Operand<T> predictions) {
    return call(labels, predictions, null);
  }

  /**
   * Calculates the loss
   *
   * @param labels the truth values or labels
   * @param predictions the predictions
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
   */
  public abstract <T extends TNumber, U extends TNumber> Operand<T> call(
      Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights);

  /**
   * Gets the TensorFlow Ops
   *
   * @return the TensorFlow Ops
   */
  public Ops getTF() {
    return tf;
  }

  /**
   * Gets the loss reduction
   *
   * @return the loss reduction
   */
  public Reduction getReduction() {
    return reduction;
  }
}
