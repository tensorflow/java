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
package org.tensorflow.framework.metrics;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.Losses;
import org.tensorflow.framework.metrics.impl.LossInterface;
import org.tensorflow.framework.metrics.impl.MeanMetricWrapper;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/** Computes the hinge loss metric between labels and predictions. */
public class Hinge<U extends TNumber, T extends TNumber> extends MeanMetricWrapper<U, T>
    implements LossInterface<T> {

  /**
   * Creates a Hinge metric
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this metric, if null then metric name is {@link Class#getSimpleName()}.
   * @param seed the seed for random number generation. An initializer created with a given seed
   *     will always produce the same random tensor for a given shape and data type.
   */
  public Hinge(Ops tf, String name, long seed, Class<T> type) {
    super(tf, name, seed, type);
    setLoss(this);
  }

  /** {@inheritDoc} */
  @Override
  public <V extends TNumber> Operand<T> call(Operand<V> labels, Operand<T> predictions) {
    return Losses.hinge(getTF(), labels, predictions);
  }
}
