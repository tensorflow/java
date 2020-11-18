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

import static org.tensorflow.framework.utils.CastHelper.cast;

/**
 * Computes the crossentropy loss between the labels and predictions.
 *
 * <p>Use this crossentropy loss function when there are two or more label classes. We expect labels
 * to be provided in a one_hot representation. If you want to provide labels as integers, please use
 * {@link SparseCategoricalCrossentropy} loss. There should be <code># classes</code> floating point
 * values per feature.
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0, 1, 0}, {0, 0, 1}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{0.05f, 0.95f, 0f}, {0.1f, 0.8f, 0.1f}});
 *    CategoricalCrossentropy cce = new CategoricalCrossentropy(tf);
 *    Operand&lt;TFloat32&gt; result = cce.call(labels, predictions);
 *    // produces 1.177
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {0.3f, 0.7f});
 *    Operand&lt;TFloat32&gt; result = cce.call(labels, predictions, sampleWeight);
 *    // produces 0.814f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    CategoricalCrossentropy cce = new CategoricalCrossentropy(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = cce.call(labels, predictions);
 *    // produces 2.354f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    CategoricalCrossentropy cce =
 *        new CategoricalCrossentropy(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = cce.call(labels, predictions);
 *    // produces [0.0513f, 2.303f]
 * </pre>
 */
public class CategoricalCrossentropy extends Loss {
  public static final boolean FROM_LOGITS_DEFAULT = false;
  public static final float LABEL_SMOOTHING_DEFAULT = 0.0f;
  public static final int DEFAULT_AXIS = -1;

  private final boolean fromLogits;
  private final float labelSmoothing;
  private final int axis;

  /**
   * Creates a categorical cross entropy Loss using {@link Class#getSimpleName()} as the loss name,
   * {@link #FROM_LOGITS_DEFAULT} for fromLogits, {@link #LABEL_SMOOTHING_DEFAULT} for
   * labelSmoothing, a Loss Reduction of {@link Loss#REDUCTION_DEFAULT}, and an axis of {@link
   * #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   */
  public CategoricalCrossentropy(Ops tf) {
    this(tf, null, FROM_LOGITS_DEFAULT, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using {@link #FROM_LOGITS_DEFAULT} for fromLogits,
   * {@link #LABEL_SMOOTHING_DEFAULT} for labelSmoothing, a Loss Reduction of {@link
   * Loss#REDUCTION_DEFAULT}, and an axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss
   */
  public CategoricalCrossentropy(Ops tf, String name) {
    this(tf, name, FROM_LOGITS_DEFAULT, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using {@link Class#getSimpleName()} as the loss name,
   * {@link #FROM_LOGITS_DEFAULT} for fromLogits, {@link #LABEL_SMOOTHING_DEFAULT} for
   * labelSmoothing and an axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to loss.
   */
  public CategoricalCrossentropy(Ops tf, Reduction reduction) {
    this(tf, null, FROM_LOGITS_DEFAULT, LABEL_SMOOTHING_DEFAULT, reduction, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss {@link #FROM_LOGITS_DEFAULT} for fromLogits, {@link
   * #LABEL_SMOOTHING_DEFAULT} for labelSmoothing, and an axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss
   * @param reduction Type of Reduction to apply to loss.
   */
  public CategoricalCrossentropy(Ops tf, String name, Reduction reduction) {
    this(tf, name, FROM_LOGITS_DEFAULT, LABEL_SMOOTHING_DEFAULT, reduction, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using {@link Class#getSimpleName()} as the loss name,
   * {@link #LABEL_SMOOTHING_DEFAULT} for labelSmoothing, a Loss Reduction of {@link
   * Loss#REDUCTION_DEFAULT}, and an axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   */
  public CategoricalCrossentropy(Ops tf, boolean fromLogits) {
    this(tf, null, fromLogits, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using {@link #LABEL_SMOOTHING_DEFAULT} for
   * labelSmoothing, a Loss Reduction of {@link Loss#REDUCTION_DEFAULT}, and a channel axis of
   * {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   */
  public CategoricalCrossentropy(Ops tf, String name, boolean fromLogits) {
    this(tf, name, fromLogits, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using {@link Class#getSimpleName()} as the loss name,
   * a Loss Reduction of {@link Loss#REDUCTION_DEFAULT}, and a channel axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing Float in <code>[0, 1]</code>. When <code>&gt; 0</code>, label values are smoothed, meaning the
   *    confidence on label values are relaxed. e.g. <code>labelSmoothing=0.2</code> means that we will use a
   *    value of <code>0.1</code> for label <code>0</code> and <code>0.9</code> for label <code>1</code>
   */
  public CategoricalCrossentropy(Ops tf, boolean fromLogits, float labelSmoothing) {
    this(tf, null, fromLogits, labelSmoothing, REDUCTION_DEFAULT, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using a Loss Reduction of {@link Loss#REDUCTION_DEFAULT},
   * and a channel axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing Float in <code>[0, 1]</code>. When <code>&gt; 0</code>, label values are smoothed, meaning the
   *    confidence on label values are relaxed. e.g. <code>labelSmoothing=0.2</code> means that we will use a
   *    value of <code>0.1</code> for label <code>0</code> and <code>0.9</code> for label <code>1</code>
   */
  public CategoricalCrossentropy(Ops tf, String name, boolean fromLogits, float labelSmoothing) {
    this(tf, name, fromLogits, labelSmoothing, REDUCTION_DEFAULT, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss using {@link Class#getSimpleName()} as the loss name
   * and a channel axis of {@link #DEFAULT_AXIS}
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing Float in <code>[0, 1]</code>. When <code>&gt; 0</code>, label values are smoothed, meaning the
   *    confidence on label values are relaxed. e.g. <code>x=0.2</code> means that we will use a
   *    value of <code>0.1</code> for label <code>0</code> and <code>0.9</code> for label <code>1</code>
   * @param reduction Type of Reduction to apply to loss.
   */
  public CategoricalCrossentropy(
      Ops tf, boolean fromLogits, float labelSmoothing, Reduction reduction) {
    this(tf, null, fromLogits, labelSmoothing, reduction, DEFAULT_AXIS);
  }

  /**
   * Creates a categorical cross entropy Loss
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing Float in <code>[0, 1]</code>. When <code>&gt; 0</code>, label values are smoothed, meaning the
   *    confidence on label values are relaxed. e.g. <code>labelSmoothing=0.2</code> means that we will use a
   *    value of <code>0.1</code> for label <code>0</code> and <code>0.9</code> for label <code>1</code>
   * @param reduction Type of Reduction to apply to loss.
   * @param axis The channels axis. <code>axis=-1</code> corresponds to data format `Channels Last'
   *     and <code>axis=1</code> corresponds to data format 'Channels First'.
   * @throws IllegalArgumentException if labelSmoothing is not in the inclusive range of 0. - 1.
   */
  public CategoricalCrossentropy(
      Ops tf,
      String name,
      boolean fromLogits,
      float labelSmoothing,
      Reduction reduction,
      int axis) {
    super(tf, name, reduction);
    if (labelSmoothing < 0 || labelSmoothing > 1)
      throw new IllegalArgumentException(
          "labelSmoothing must be >= 0. and <= 1, found " + labelSmoothing);
    this.fromLogits = fromLogits;
    this.labelSmoothing = labelSmoothing;
    this.axis = axis;
  }

  /**
   * Generates an Operand that calculates the loss.
   *
   * <p>If run in Graph mode, the computation will throw {@link
   * org.tensorflow.exceptions.TFInvalidArgumentException} if the predictions values are outside the
   * range o [0. to 1.]. In Eager Mode, this call will throw {@link IllegalArgumentException}, if
   * the predictions values are outside the range o [0. to 1.]
   *
   * @param labels the truth values or labels
   * @param predictions the predictions, values must be in the range [0. to 1.] inclusive.
   * @param sampleWeights Optional SampleWeights acts as a coefficient for the loss. If a scalar is
   *     provided, then the loss is simply scaled by the given value. If SampleWeights is a tensor
   *     of size [batch_size], then the total loss for each sample of the batch is rescaled by the
   *     corresponding element in the SampleWeights vector. If the shape of SampleWeights is
   *     [batch_size, d0, .. dN-1] (or can be broadcast to this shape), then each loss element of
   *     predictions is scaled by the corresponding value of SampleWeights. (Note on dN-1: all loss
   *     functions reduce by 1 dimension, usually axis=-1.)
   * @param <T> The data type of the predictions, sampleWeights and loss.
   * @param <U> The data type of the labels.
   * @return the loss
   * @throws IllegalArgumentException if the predictions are outside the range [0.-1.].
   */
  @Override
  public <T extends TNumber, U extends TNumber> Operand<T> call(
      Operand<U> labels, Operand<T> predictions, Operand<T> sampleWeights) {
    Operand<T> lPredictions;
    if (!fromLogits) {
      // add predictions range check for 0 - 1
      lPredictions =
          LossesHelper.rangeCheck(
              getTF(),
              "predictions range check [0-1]",
              predictions,
              cast(getTF(), getTF().constant(0), predictions.asOutput().dataType()),
              cast(getTF(), getTF().constant(1), predictions.asOutput().dataType()));

    } else {
      lPredictions = predictions;
    }
    Operand<T> losses =
        Losses.categoricalCrossentropy(
            getTF(), labels, lPredictions, fromLogits, labelSmoothing, axis);
    return LossesHelper.computeWeightedLoss(getTF(), losses, getReduction(), sampleWeights);
  }
}
