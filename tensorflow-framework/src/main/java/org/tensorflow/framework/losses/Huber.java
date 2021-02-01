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
 * Computes the Huber loss between labels and predictions.
 *
 * <p>For each value x in <code>error = labels - predictions</code>:
 *
 * <pre>
 *     loss = 0.5 * x^2                  if |x| &lt;= d
 *     loss = 0.5 * d^2 + d * (|x| - d)  if |x| &gt; d
 * </pre>
 *
 * <p>where d is delta.
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0.f, 1.f}, {0.f, 0.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{0.6f, 0.4f}, {0.4f, 0.6f}});
 *    Huber huberLoss = new Huber(tf);
 *    Operand&lt;TFloat32&gt; result = huberLoss.call(labels, predictions);
 *    // produces 0.155
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {1.f, 0.f});
 *    Operand&lt;TFloat32&gt; result = huberLoss.call(labels, predictions, sampleWeight);
 *    // produces 0.09f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    Huber huberLoss = new Huber(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = huberLoss.call(labels, predictions);
 *    // produces 0.32f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    Huber huberLoss = new Huber(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = huberLoss.call(labels, predictions);
 *    // produces [0.18f, 0.13f]
 * </pre>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Huber_loss">Huber loss</a>
 */
public class Huber extends Loss {
  public static final float DELTA_DEFAULT = 1.0f;

  private final float delta;

  /**
   * Creates a Huber Loss using {@link Class#getSimpleName()} as the loss name, {@link
   * #DELTA_DEFAULT} as the delta and a Loss Reduction of {@link Loss#REDUCTION_DEFAULT}
   *
   * @param tf the TensorFlow Ops
   */
  public Huber(Ops tf) {
    this(tf, null, DELTA_DEFAULT, Reduction.AUTO);
  }

  /**
   * Creates a Huber Loss using {@link #DELTA_DEFAULT} as the delta and a Loss Reduction of {@link
   * Loss#REDUCTION_DEFAULT}
   *
   * @param tf the TensorFlow Ops
   */
  public Huber(Ops tf, String name) {
    this(tf, name, DELTA_DEFAULT, Reduction.AUTO);
  }

  /**
   * Creates a Huber Loss using {@link Class#getSimpleName()} as the loss name and and {@link
   * #DELTA_DEFAULT} as the delta
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to the loss.
   */
  public Huber(Ops tf, Reduction reduction) {
    this(tf, null, DELTA_DEFAULT, reduction);
  }

  /**
   * Creates a Huber Loss using {@link #DELTA_DEFAULT} as the delta
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to the loss.
   */
  public Huber(Ops tf, String name, Reduction reduction) {
    this(tf, name, DELTA_DEFAULT, reduction);
  }

  /**
   * Creates a Huber Loss
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param delta the point where the Huber loss function changes from quadratic to linear.
   * @param reduction Type of Reduction to apply to the loss.
   */
  public Huber(Ops tf, String name, float delta, Reduction reduction) {
    super(tf, name, reduction);
    this.delta = delta;
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(
      Operand<? extends TNumber> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    Operand<T> losses = Losses.huber(getTF(), labels, predictions, delta);
    return LossesHelper.computeWeightedLoss(getTF(), losses, getReduction(), sampleWeights);
  }
}
