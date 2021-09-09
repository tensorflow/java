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
package org.tensorflow.framework.metrics.impl;

import org.tensorflow.Operand;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/** Interface for Metrics that wrap AbstractLoss functions. */
public interface LossMetric {

  /**
   * Calculates the weighted loss between {@code labels} and {@code predictions}
   *
   * @param tf the TensorFlow Ops
   * @param labels the truth values or labels
   * @param predictions the predictions
   * @param resultType the data type for the result
   * @param <T> The data type of the predictions.
   * @return the loss
   */
  <T extends TNumber> Operand<T> call(
      Ops tf,
      Operand<? extends TNumber> labels,
      Operand<? extends TNumber> predictions,
      Class<T> resultType);
}
