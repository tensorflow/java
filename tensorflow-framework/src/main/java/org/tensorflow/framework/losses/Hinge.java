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

import static org.tensorflow.framework.utils.CastHelper.cast;

import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.AbstractLoss;
import org.tensorflow.framework.losses.impl.LossesHelper;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;

/**
 * Computes the hinge loss between labels and predictions.
 *
 * <p><code>loss = maximum(1 - labels * predictions, 0)</code>.
 *
 * <p><code>labels</code> values are expected to be -1 or 1. If binary (0 or 1) labels are provided,
 * they will be converted to -1 or 1.
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0.f, 1.f}, {0.f, 0.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{0.6f, 0.4f}, {0.4f, 0.6f}});
 *    Hinge hingeLoss = new Hinge(tf);
 *    Operand&lt;TFloat32&gt; result = hingeLoss.call(Ops tf, labels, predictions);
 *    // produces 1.3f
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {1.f, 0.f});
 *    Operand&lt;TFloat32&gt; result = hingeLoss.call(Ops tf, labels, predictions, sampleWeight);
 *    // produces 0.55f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    Hinge hingeLoss = new Hinge(Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = hingeLoss.call(Ops tf, labels, predictions);
 *    // produces 2.6f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    Hinge hingeLoss = new Hinge(Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = hingeLoss.call(Ops tf, labels, predictions);
 *    // produces [1.1f, 1.5f]
 * </pre>
 */
public class Hinge extends AbstractLoss {

  /**
   * Creates a Hinge AbstractLoss using {@link Class#getSimpleName()} as the loss name and a
   * AbstractLoss Reduction of {@link AbstractLoss#REDUCTION_DEFAULT}
   */
  public Hinge() {
    this(null, Reduction.AUTO);
  }

  /**
   * Creates a Hinge AbstractLoss using {@link Class#getSimpleName()} as the loss name
   *
   * @param reduction Type of Reduction to apply to the loss.
   */
  public Hinge(Reduction reduction) {
    super(null, reduction);
  }

  /**
   * Creates a Hinge
   *
   * @param name the name of the loss
   * @param reduction Type of Reduction to apply to the loss.
   */
  public Hinge(String name, Reduction reduction) {
    super(name, reduction);
  }

  /**
   * Generates an Operand that calculates the loss.
   *
   * <p>If run in Graph mode, the computation will throw {@link
   * org.tensorflow.exceptions.TFInvalidArgumentException} if the label values are not in the set
   * [-1., 0., 1.]. In Eager Mode, this call will throw {@link IllegalArgumentException}, if the
   * label values are not in the set [-1., 0., 1.].
   *
   * @param labels the truth values or labels, must be either -1, 0, or 1. Values are expected to be
   *     -1 or 1. If binary (0 or 1) labels are provided they will be converted to -1 or 1.
   * @param predictions the predictions, values must be in the range [0. to 1.] inclusive.
   * @param sampleWeights Optional sampleWeights acts as a coefficient for the loss. If a scalar is
   *     provided, then the loss is simply scaled by the given value. If sampleWeights is a tensor
   *     of size [batch_size], then the total loss for each sample of the batch is rescaled by the
   *     corresponding element in the SampleWeights vector. If the shape of SampleWeights is
   *     [batch_size, d0, .. dN-1] (or can be broadcast to this shape), then each loss element of
   *     predictions is scaled by the corresponding value of SampleWeights. (Note on dN-1: all loss
   *     functions reduce by 1 dimension, usually axis=-1.)
   * @param <T> The data type of the predictions, sampleWeights and loss.
   * @return the loss
   * @throws IllegalArgumentException if the predictions are outside the range [0.-1.].
   */
  @Override
  public <T extends TNumber> Operand<T> call(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions, Operand<T> sampleWeights) {

    Operand<T> tLabels = cast(tf, labels, predictions.type());
    tLabels =
        LossesHelper.valueCheck(
            tf,
            "labels value check [-1, 0, 1]",
            tLabels,
            cast(tf, tf.constant(new int[] {-1, 0, 1}), predictions.type()));
    Operand<T> losses = Losses.hinge(tf, tLabels, predictions);
    return LossesHelper.computeWeightedLoss(tf, losses, getReduction(), sampleWeights);
  }
}
