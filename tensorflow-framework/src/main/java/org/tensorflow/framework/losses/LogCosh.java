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
 * Computes Computes the logarithm of the hyperbolic cosine of the prediction error.
 *
 * <p><code>logcosh = log((exp(x) + exp(-x))/2)</code>, where <code>x</code> is the error <code>
 * predictions - labels</code>.
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0.f, 1.f}, {0.f, 0.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{1.f, 1.f}, {0.f, 0.f}});
 *    LogCosh logcosh = new LogCosh(tf);
 *    Operand&lt;TFloat32&gt; result = logcosh.call(Ops tf, labels, predictions);
 *    // produces 0.108
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {0.8f, 0.2f});
 *    Operand&lt;TFloat32&gt; result = logcosh.call(Ops tf, labels, predictions, sampleWeight);
 *    // produces 0.087f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    LogCosh logcosh = new LogCosh(Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = logcosh.call(Ops tf, labels, predictions);
 *    // produces 0.217f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    LogCosh logcosh = new LogCosh(Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = logcosh.call(Ops tf, labels, predictions);
 *    // produces [0.217f, 0f]
 * </pre>
 */
public class LogCosh extends AbstractLoss {

  /**
   * Creates a LogCosh AbstractLoss using {@link Class#getSimpleName()} as the loss name and a
   * AbstractLoss Reduction of {@link AbstractLoss#REDUCTION_DEFAULT}
   */
  public LogCosh() {
    this(null, Reduction.AUTO);
  }

  /**
   * Creates a LogCosh AbstractLoss using a AbstractLoss Reduction of {@link
   * AbstractLoss#REDUCTION_DEFAULT}
   *
   * @param name the name of the loss, if null then {@link Class#getSimpleName()} is used.
   */
  public LogCosh(String name) {
    this(name, Reduction.AUTO);
  }

  /**
   * Creates a LogCosh AbstractLoss using {@link Class#getSimpleName()} as the loss name
   *
   * @param reduction Type of Reduction to apply to the loss.
   */
  public LogCosh(Reduction reduction) {
    this(null, reduction);
  }

  /**
   * Creates a LogCosh AbstractLoss
   *
   * @param name the name of the loss, if null then {@link Class#getSimpleName()} is used.
   * @param reduction Type of Reduction to apply to the loss.
   */
  public LogCosh(String name, Reduction reduction) {
    super(name, reduction);
  }

  /** {@inheritDoc} */
  @Override
  public <T extends TNumber> Operand<T> call(
      Ops tf, Operand<? extends TNumber> labels, Operand<T> predictions, Operand<T> sampleWeights) {

    Operand<T> losses = Losses.logCosh(tf, labels, predictions);
    return LossesHelper.computeWeightedLoss(tf, losses, getReduction(), sampleWeights);
  }
}
