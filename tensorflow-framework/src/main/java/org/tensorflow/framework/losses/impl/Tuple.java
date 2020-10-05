package org.tensorflow.framework.losses.impl;

import org.tensorflow.Operand;
import org.tensorflow.types.family.TNumber;

/**
 * A helper class for loss methods to return multiple responses
 *
 * @param <T> the data type of the Tuple entries.
 */
public class Tuple<T extends TNumber> {
  private final Operand<T> labels;
  private final Operand<T> losses;
  private final Operand<T> predictions;
  private final Operand<T> sampleWeights;

  /**
   * Creates a Tuple of Operands for labels, predictions, and sampleWeights
   *
   * @param labels the labels
   * @param lossesOrPredictions the losses or predictions
   * @param isPredictions flag indicating that this Tuple will contain predictions or losses
   */
  public Tuple(Operand<T> labels, Operand<T> lossesOrPredictions, boolean isPredictions) {
    this(labels, lossesOrPredictions, null, isPredictions);
  }

  /**
   * Creates a Tuple of Operands for labels, predictions, and sampleWeights
   *
   * @param labels the labels
   * @param lossesOrPredictions the losses or predictions
   * @param sampleWeights the sample weights
   * @param isPredictions flag indicating that this Tuple will contain predictions or losses
   */
  public Tuple(
      Operand<T> labels,
      Operand<T> lossesOrPredictions,
      Operand<T> sampleWeights,
      boolean isPredictions) {
    this.labels = labels;
    if (isPredictions) {
      this.predictions = lossesOrPredictions;
      this.losses = null;
    } else {
      this.predictions = null;
      this.losses = lossesOrPredictions;
    }
    this.sampleWeights = sampleWeights;
  }

  /**
   * Indicates whether this Tuple contains Labels
   *
   * @return true is this Tuple contains Labels
   */
  public boolean containsLabels() {
    return labels != null;
  }

  /**
   * Indicates whether this Tuple contains Labels
   *
   * @return true is this Tuple contains Labels
   */
  public boolean containsPredictions() {
    return predictions != null;
  }

  /**
   * Indicates whether this Tuple contains Labels
   *
   * @return true is this Tuple contains Labels
   */
  public boolean containsLosses() {
    return losses != null;
  }

  /**
   * Indicates whether this Tuple contains Labels
   *
   * @return true is this Tuple contains Labels
   */
  public boolean containsSampleWeights() {
    return this.sampleWeights != null;
  }

  /** @return the labels */
  public Operand<T> getLabels() {
    return labels;
  }

  /** @return the predictions */
  public Operand<T> getPredictions() {
    return predictions;
  }

  /** @return the predictions */
  public Operand<T> getLosses() {
    return losses;
  }

  /** @return the sampleWeights */
  public Operand<T> getSampleWeights() {
    return sampleWeights;
  }
}
