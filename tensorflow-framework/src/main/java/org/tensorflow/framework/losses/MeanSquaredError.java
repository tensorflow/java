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
import org.tensorflow.framework.losses.impl.AbstractLoss;
import org.tensorflow.framework.losses.impl.LossesHelper;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the mean of squares of errors between labels and predictions.
 *
 * <p><code>loss = loss = square(labels - predictions)</code>
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0.f, 1.f}, {0.f, 0.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{1.f, 1.f}, {1.f, 0.f}});
 *    MeanSquaredError mse = new MeanSquaredError(tf);
 *    Operand&lt;TFloat32&gt; result = mse.call(Ops tf, labels, predictions);
 *    // produces 0.5f
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {0.7f, 0.3f});
 *    Operand&lt;TFloat32&gt; result = mse.call(Ops tf, labels, predictions, sampleWeight);
 *    // produces 0.25f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    MeanSquaredError mse = new MeanSquaredError(Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = mse.call(Ops tf, labels, predictions);
 *    // produces 1.0f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    MeanSquaredError mse = new MeanSquaredError(Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = mse.call(Ops tf, labels, predictions);
 *    // produces [0.5f, 0.5f]
 * </pre>
 */
public class MeanSquaredError extends AbstractLoss {

  /**
   * Creates a MeanSquaredError AbstractLoss using {@link Class#getSimpleName()} as the loss name
   * and a AbstractLoss Reduction of {@link AbstractLoss#REDUCTION_DEFAULT}
   */
  public MeanSquaredError() {
    super();
  }

  /**
   * Creates a MeanSquaredError AbstractLoss using {@link Class#getSimpleName()} as the loss name
   *
   * @param reduction Type of Reduction to apply to the loss.
   */
  public MeanSquaredError(Reduction reduction) {
    super(null, reduction);
  }

  /**
   * Creates a MeanSquaredError
   *
   * @param name the name of the loss
   * @param reduction Type of Reduction to apply to the loss.
   */
  public MeanSquaredError(String name, Reduction reduction) {
    super(name, reduction);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions, Operand<T> sampleWeights) {

    Operand<T> losses = Losses.meanSquaredError(tf, labels, predictions);
    return LossesHelper.computeWeightedLoss(tf, losses, getReduction(), sampleWeights);
  }
}
