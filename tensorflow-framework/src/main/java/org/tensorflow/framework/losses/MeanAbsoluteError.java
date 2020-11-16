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
package org.tensorflow.framework.losses;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossesHelper;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the mean of absolute difference between labels and predictions.
 *
 * <p><code>loss = abs(labels - predictions)</code>
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0.f, 1.f}, {0.f, 0.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{1.f, 1.f}, {1.f, 0.f}});
 *    MeanAbsoluteError mae = new MeanAbsoluteError(tf);
 *    Operand&lt;TFloat32&gt; result = mae.call(labels, predictions);
 *    // produces 0.5f
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {0.7f, 0.3f});
 *    Operand&lt;TFloat32&gt; result = mae.call(labels, predictions, sampleWeight);
 *    // produces 0.25f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    MeanAbsoluteError mae = new MeanAbsoluteError(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = mae.call(labels, predictions);
 *    // produces 1.0f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    MeanAbsoluteError mae = new MeanAbsoluteError(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = mae.call(labels, predictions);
 *    // produces [0.5f, 0.5f]
 * </pre>
 */
public class MeanAbsoluteError extends Loss {

  /**
   * Creates a MeanAbsoluteError Loss using {@link Class#getSimpleName()} as the loss name and a
   * Loss Reduction of {@link Loss#REDUCTION_DEFAULT}
   *
   * @param tf the TensorFlow Ops
   */
  public MeanAbsoluteError(Ops tf) {
    super(tf);
  }

  /**
   * Creates a MeanAbsoluteError Loss using {@link Class#getSimpleName()} as the loss name
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to the loss.
   */
  public MeanAbsoluteError(Ops tf, Reduction reduction) {
    super(tf, null, reduction);
  }

  /**
   * Creates a MeanAbsoluteError
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param reduction Type of Reduction to apply to the loss.
   */
  public MeanAbsoluteError(Ops tf, String name, Reduction reduction) {
    super(tf, name, reduction);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber, U extends TNumber> Operand<T> call(
          Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    Operand<T> losses = Losses.meanAbsoluteError(getTF(), labels, predictions);
    return LossesHelper.computeWeightedLoss(getTF(), losses, getReduction(), sampleWeights);
  }
}
