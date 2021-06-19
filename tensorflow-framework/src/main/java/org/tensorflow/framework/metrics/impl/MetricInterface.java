/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.framework.metrics.impl;

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/** The interface for metrics */
public interface MetricInterface {

  /**
   * Creates a List of Operations to update the metric state based on input values.
   *
   * @param tf the TensorFlow Ops
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @return a List of Operations to update the metric state
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     Initializable#init(Ops)}.
   */
  List<Op> updateStateList(
      Ops tf, Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights);

  /**
   * Creates a List of Operations to update the metric state based on labels and predictions.
   *
   * @param tf the TensorFlow Ops
   * @param labels shape (N, Cx, L1?) where N is the number of examples, Cx is zero or more class
   *     dimensions, and L1 is a potential extra dimension of size 1 that would be squeezed. Will be
   *     cast to {@code <T>}. If {@code multiLabel} or if {@code labelWeights != null}, then Cx must
   *     be a single dimension.
   * @param predictions the predictions shape (N, Cx, P1?). Will be cast to {@code T}.
   * @param sampleWeights sample weights to be applied to values, may be null. Will be cast to
   *     {@code <T>}.
   * @return a List of Operations to update the metric state
   */
  List<Op> updateStateList(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights);

  /**
   * Gets the current result of the metric
   *
   * @param tf the TensorFlow Ops
   * @return the result, possibly with control dependencies
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     Initializable#init(Ops)}.
   *
   */
  Operand<? extends TNumber> result(Ops tf);

  /**
   * Resets any state variables to their initial values
   *
   * @param tf the TensorFlow Ops
   * @return the control operation for doing the reset
   * @throws IllegalStateException if the TensorFlow Ops has nat been initialized with {@link
   *     Initializable#init(Ops)}.
   */
  Op resetStates(Ops tf);
}
