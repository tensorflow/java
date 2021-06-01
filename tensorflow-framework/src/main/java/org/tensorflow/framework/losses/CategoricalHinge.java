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
 * Computes the categorical hinge loss between labels and predictions.
 *
 * <p><code>loss = maximum(neg - pos + 1, 0)</code> where <code>neg=maximum((1-labels)*predictions)
 * </code> and <code>pos=sum(labels*predictions)</code>
 *
 * <p><code>labels</code> values are expected to be 0 or 1.
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0, 1}, {0, 0}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{0.6f, 0.4f}, {0.4f, 0.6f}});
 *    CategoricalHinge categoricalHinge = new CategoricalHinge(tf);
 *    Operand&lt;TFloat32&gt; result = categoricalHinge.call(Ops tf, labels, predictions);
 *    // produces 1.4
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {1f, 0.f});
 *    Operand&lt;TFloat32&gt; result = categoricalHinge.call(Ops tf, labels, predictions, sampleWeight);
 *    // produces 0.6f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    CategoricalHinge categoricalHinge = new CategoricalHinge(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = categoricalHinge.call(Ops tf, labels, predictions);
 *    // produces 2.8f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    CategoricalHinge categoricalHinge =
 *        new CategoricalHinge(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = categoricalHinge.call(Ops tf, labels, predictions);
 *    // produces [1.2f, 1.6f]
 * </pre>
 */
public class CategoricalHinge extends AbstractLoss {

  /**
   * Creates a Categorical Hinge AbstractLoss using {@link Class#getSimpleName()} as the loss name
   * and a AbstractLoss Reduction of {@link AbstractLoss#REDUCTION_DEFAULT}
   */
  public CategoricalHinge() {
    super();
  }

  /**
   * Creates a Categorical Hinge AbstractLoss using {@link Class#getSimpleName()} as the loss name
   *
   * @param reduction Type of Reduction to apply to the loss.
   */
  public CategoricalHinge(Reduction reduction) {
    super(null, reduction);
  }

  /**
   * Creates a Categorical Hinge
   *
   * @param name the name of the loss
   * @param reduction Type of Reduction to apply to the loss.
   */
  public CategoricalHinge(String name, Reduction reduction) {
    super(name, reduction);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions, Operand<T> sampleWeights) {

    Operand<T> losses = Losses.categoricalHinge(tf, labels, predictions);
    return LossesHelper.computeWeightedLoss(tf, losses, getReduction(), sampleWeights);
  }
}
