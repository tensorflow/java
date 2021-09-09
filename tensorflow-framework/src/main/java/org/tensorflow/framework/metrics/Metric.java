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
package org.tensorflow.framework.metrics;

import java.util.List;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/** Interface for metrics */
interface Metric {

  /**
   * Creates a List of Operations to update the metric state based on input values.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment. encapsulating a {@link
   *     Graph} environment.
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not have a Graph environment.
   * @return a List of Operations to update the metric state
   */
  List<Op> updateStateList(
      Ops tf, Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights);

  /**
   * Creates a List of Operations to update the metric state based on labels and predictions.
   *
   * <p>This is an empty implementation that should be overridden in a sub class, if needed.
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not have a Graph environment.
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
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param type the data type for the result
   * @param <T> the date type for the result
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not have a Graph environment.
   * @return the result, possibly with control dependencies
   */
  <T extends TNumber> Operand<T> result(Ops tf, Class<T> type);

  /**
   * Resets any state variables to their initial values
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not have a Graph environment.
   * @return the operation for doing the reset
   */
  Op resetStates(Ops tf);

  /**
   * Creates a NoOp Operation with control dependencies to update the metric state
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not have a Graph environment.
   * @return the Operation to update the metric state
   */
  Op updateState(
      Ops tf, Operand<? extends TNumber> values, Operand<? extends TNumber> sampleWeights);

  /**
   * Creates a NoOp Operation with control dependencies to update the metric state
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param labels the labels
   * @param predictions the predictions
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not have a Graph environment.
   * @return the Operation to update the metric state
   */
  Op updateState(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Operand<? extends TNumber> sampleWeights);

  /**
   * Calls update state once, followed by a call to get the result
   *
   * @param tf the TensorFlow Ops encapsulating a {@link Graph} environment.
   * @param values the inputs to be passed to update state, this may not be null
   * @param sampleWeights sample weights to be applied to values, may be null.
   * @param type the data type for the result
   * @param <T> the date type for the result
   * @throws IllegalArgumentException if the TensorFlow Ops scope does not have a Graph environment.
   * @return the result, possibly with control dependencies
   */
  <T extends TNumber> Operand<T> callOnce(
      Ops tf,
      Operand<? extends TNumber> values,
      Operand<? extends TNumber> sampleWeights,
      Class<T> type);
}
