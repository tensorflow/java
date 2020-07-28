/* Copyright 2020 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/
package org.tensorflow.keras.backend.tf;

import org.tensorflow.Operand;
import org.tensorflow.types.family.TType;

/**
 * Returns labels, losses or predictions and sample weights as a Tuple
 *
 * @param <T> the type of Operand
 */
public class Tuple<T extends TType> {

  private final Operand<T> labels;
  private final Operand<T> lossesOrPredictions;
  private final Operand<T> sampleWeights;

  /**
   * Creates a Tuple of Operands for labels, predictions, and sampleWeights
   *
   * @param labels the labels
   * @param lossesOrPredictions the losses or predictions
   */
  public Tuple(Operand<T> labels, Operand<T> lossesOrPredictions) {
    this(labels, lossesOrPredictions, null);
  }

  /**
   * Creates a Tuple of Operands for labels, predictions, and sampleWeights
   *
   * @param labels the labels
   * @param lossesOrPredictions the losses or predictions
   * @param sampleWeights the sample weights
   */
  public Tuple(Operand<T> labels, Operand<T> lossesOrPredictions, Operand<T> sampleWeights) {
    this.labels = labels;
    this.lossesOrPredictions = lossesOrPredictions;
    this.sampleWeights = sampleWeights;
  }

  /**
   * Indicates whether this Tuple contains Labels
   *
   * @return true is this Tuple contains Labels
   */
  public boolean containsLabels() {
    return this.labels != null;
  }

  /**
   * Indicates whether this Tuple contains Labels
   *
   * @return true is this Tuple contains Labels
   */
  public boolean containsPredictions() {
    return this.lossesOrPredictions != null;
  }

  /**
   * Indicates whether this Tuple contains Labels
   *
   * @return true is this Tuple contains Labels
   */
  public boolean containsLosses() {
    return this.lossesOrPredictions != null;
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
    return lossesOrPredictions;
  }

  /** @return the predictions */
  public Operand<T> getLosses() {
    return lossesOrPredictions;
  }

  /** @return the sampleWeights */
  public Operand<T> getSampleWeights() {
    return sampleWeights;
  }
}
