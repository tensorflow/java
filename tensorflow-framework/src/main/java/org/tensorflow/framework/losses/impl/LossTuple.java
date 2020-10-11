package org.tensorflow.framework.losses.impl;

import org.tensorflow.Operand;
import org.tensorflow.types.family.TNumber;

/**
 * A helper class for loss methods to return  labels, target, and sampleWeights
 *
 * @param <T> the data type of the LossTuple entries.
 */
public class LossTuple<T extends TNumber> {
  private final Operand<T> labels;
  private final Operand<T> target;
  private final Operand<T> sampleWeights;

  /**
   * Creates a LossTuple of Operands for labels, target, and sampleWeights
   *
   * @param labels the labels
   * @param target the losses or target
   */
  public LossTuple(Operand<T> labels, Operand<T> target) {
    this(labels, target, null);
  }

  /**
   * Creates a LossTuple of Operands for labels, target, and sampleWeights
   *
   * @param labels the labels
   * @param target the losses or target
   * @param sampleWeights the sample weights
   */
  public LossTuple(Operand<T> labels, Operand<T> target, Operand<T> sampleWeights) {
    this.labels = labels;
    this.target = target;
    this.sampleWeights = sampleWeights;
  }

  /** @return the labels */
  public Operand<T> getLabels() {
    return labels;
  }

  /** @return the target */
  public Operand<T> getTarget() {
    return target;
  }

  /** @return the sampleWeights */
  public Operand<T> getSampleWeights() {
    return sampleWeights;
  }
}
