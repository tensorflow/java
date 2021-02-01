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
package org.tensorflow.framework.losses.impl;

import org.tensorflow.Operand;
import org.tensorflow.types.family.TNumber;

/**
 * A helper class for loss methods to return labels, target, and sampleWeights
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
