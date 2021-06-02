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
 * Computes the cosine similarity between labels and predictions.
 *
 * <p>Note that it is a number between <code>-1</code> and <code>1</code>. When it is a negative
 * number between <code>-1</code> and <code>0</code>, <code>0</code> indicates orthogonality and
 * values closer to <code>-1</code>indicate greater similarity. The values closer to <code>1</code>
 * indicate greater dissimilarity. This makes it usable as a loss function in a setting where you
 * try to maximize the proximity between predictions and targets. If either <code>labels</code> or
 * <code>predictions</code> is a zero vector, cosine similarity will be <code>0</code> regardless of
 * the proximity between predictions and targets.
 *
 * <p><code>loss = -sum(l2Norm(labels) * l2Norm(predictions))</code>
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0.f, 1.f}, {1.f, 1.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{1.f, 0.f}, {1.f, 1.f}});
 *    CosineSimilarity cosineLoss = new CosineSimilarity(tf);
 *    Operand&lt;TFloat32&gt; result = cosineLoss.call(Ops tf, labels, predictions);
 *    // produces -0.5
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {0.8f, 0.2f});
 *    Operand&lt;TFloat32&gt; result = cosineLoss.call(Ops tf, labels, predictions, sampleWeight);
 *    // produces -0.0999f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    CosineSimilarity cosineLoss = new CosineSimilarity(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = cosineLoss.call(Ops tf, labels, predictions);
 *    // produces -0.999f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    CosineSimilarity cosineLoss = new CosineSimilarity(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = cosineLoss.call(Ops tf, labels, predictions);
 *    // produces [-0.f, -0.999f]
 * </pre>
 */
public class CosineSimilarity extends AbstractLoss {
  public static final int DEFAULT_AXIS = -1;
  public static final Reduction DEFAULT_REDUCTION = Reduction.AUTO;

  private final int[] axis;

  /**
   * Creates a Cosine Similarity AbstractLoss using {@link Class#getSimpleName()} as the loss name,
   * an axis of {@link #DEFAULT_AXIS}, and a AbstractLoss Reduction of {@link #DEFAULT_REDUCTION}
   */
  public CosineSimilarity() {

    this(null, DEFAULT_AXIS, DEFAULT_REDUCTION);
  }

  /**
   * Creates a Cosine Similarity AbstractLoss using an axis of {@link #DEFAULT_AXIS}, and a
   * AbstractLoss Reduction of {@link #DEFAULT_REDUCTION}
   *
   * @param name the name of the loss
   */
  public CosineSimilarity(String name) {

    this(name, DEFAULT_AXIS, DEFAULT_REDUCTION);
  }

  /**
   * Creates a Cosine Similarity AbstractLoss using {@link Class#getSimpleName()} as the loss name,
   * and a AbstractLoss Reduction of {@link #DEFAULT_REDUCTION}
   *
   * @param axis The dimension along which the cosine similarity is computed.
   */
  public CosineSimilarity(int axis) {

    this(null, axis, DEFAULT_REDUCTION);
  }
  /**
   * Creates a Cosine Similarity AbstractLoss using {@link Class#getSimpleName()} as the loss name,
   * and a AbstractLoss Reduction of {@link #DEFAULT_REDUCTION}
   *
   * @param axis The dimension along which the cosine similarity is computed.
   */
  public CosineSimilarity(int[] axis) {

    this(null, axis, DEFAULT_REDUCTION);
  }

  /**
   * Creates a Cosine Similarity AbstractLoss using a AbstractLoss Reduction of {@link
   * #DEFAULT_REDUCTION}
   *
   * @param name the name of the loss
   * @param axis The dimension along which the cosine similarity is computed.
   */
  public CosineSimilarity(String name, int axis) {

    this(name, axis, DEFAULT_REDUCTION);
  }

  /**
   * Creates a Cosine Similarity AbstractLoss using a AbstractLoss Reduction of {@link
   * #DEFAULT_REDUCTION}
   *
   * @param name the name of the loss
   * @param axis The dimension along which the cosine similarity is computed.
   */
  public CosineSimilarity(String name, int[] axis) {

    this(name, axis, DEFAULT_REDUCTION);
  }

  /**
   * Creates a Cosine Similarity AbstractLoss using {@link Class#getSimpleName()} as the loss name
   * and an axis of {@link #DEFAULT_AXIS}
   *
   * @param reduction Type of Reduction to apply to the loss.
   */
  public CosineSimilarity(Reduction reduction) {

    this(null, DEFAULT_AXIS, reduction);
  }

  /**
   * Creates a Cosine Similarity AbstractLoss using an axis of {@link #DEFAULT_AXIS}
   *
   * @param name the name of the loss
   * @param reduction Type of Reduction to apply to the loss.
   */
  public CosineSimilarity(String name, Reduction reduction) {

    this(name, DEFAULT_AXIS, reduction);
  }

  /**
   * Creates a Cosine Similarity AbstractLoss using {@link Class#getSimpleName()} as the loss name
   *
   * @param axis The dimension along which the cosine similarity is computed.
   * @param reduction Type of Reduction to apply to the loss.
   */
  public CosineSimilarity(int axis, Reduction reduction) {

    this(null, new int[] {axis}, reduction);
  }

  /**
   * Creates a Cosine Similarity AbstractLoss using {@link Class#getSimpleName()} as the loss name
   *
   * @param axis The dimension along which the cosine similarity is computed.
   * @param reduction Type of Reduction to apply to the loss.
   */
  public CosineSimilarity(int[] axis, Reduction reduction) {

    this(null, axis, reduction);
  }

  /**
   * Creates a Cosine Similarity AbstractLoss
   *
   * @param name the name of the loss
   * @param axis The dimension along which the cosine similarity is computed.
   * @param reduction Type of Reduction to apply to the loss.
   */
  public CosineSimilarity(String name, int axis, Reduction reduction) {
    this(name, new int[] {axis}, reduction);
  }

  /**
   * Creates a Cosine Similarity AbstractLoss
   *
   * @param name the name of the loss
   * @param axis The dimension along which the cosine similarity is computed.
   * @param reduction Type of Reduction to apply to the loss.
   */
  public CosineSimilarity(String name, int[] axis, Reduction reduction) {
    super(name, reduction);
    this.axis = axis;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions, Operand<T> sampleWeights) {

    Operand<T> losses = Losses.cosineSimilarity(tf, labels, predictions, axis);
    losses = tf.math.neg(losses);
    return LossesHelper.computeWeightedLoss(tf, losses, getReduction(), sampleWeights);
  }
}
