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
 * Computes the cross-entropy loss between true labels and predicted labels.
 *
 * <p>Use this cross-entropy loss when there are only two label classes (assumed to be 0 and 1). For
 * each example, there should be a single floating-point value per prediction.
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[][] {{0.f, 1.f}, {0.f, 0.f}});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{0.6f, 0.4f}, {0.4f, 0.6f}});
 *    BinaryCrossentropy bce = new BinaryCrossentropy(tf);
 *    Operand&lt;TFloat32&gt; result = bce.call(labels, predictions);
 *    // produces 0.815
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {1.f, 0.f});
 *    Operand&lt;TFloat32&gt; result = bce.call(labels, predictions, sampleWeight);
 *    // produces 0.458f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    BinaryCrossentropy bce = new BinaryCrossentropy(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = bce.call(labels, predictions);
 *    // produces 1.630f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    BinaryCrossentropy bce = new BinaryCrossentropy(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = bce.call(labels, predictions);
 *    // produces [0.916f, 0.714f]
 * </pre>
 */
public class BinaryCrossentropy extends Loss {
  public static final boolean FROM_LOGITS_DEFAULT = false;
  public static final float LABEL_SMOOTHING_DEFAULT = 0.0f;

  private final boolean fromLogits;
  private final float labelSmoothing;

  /**
   * Creates a Binary Crossentropy Loss using {@link Class#getSimpleName()} as the loss name, {@link
   * #FROM_LOGITS_DEFAULT} for fromLogits, {@link #LABEL_SMOOTHING_DEFAULT} for labelSmoothing and a
   * Loss Reduction of {@link Loss#REDUCTION_DEFAULT}
   *
   * @param tf the TensorFlow Ops
   */
  public BinaryCrossentropy(Ops tf) {
    this(tf, null, FROM_LOGITS_DEFAULT, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT);
  }

  /**
   * Creates a Binary Crossentropy loss using {@link Class#getSimpleName()} as the loss name, {@link
   * #FROM_LOGITS_DEFAULT} for fromLogits, and {@link #LABEL_SMOOTHING_DEFAULT} for labelSmoothing
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to the loss.
   */
  public BinaryCrossentropy(Ops tf, Reduction reduction) {
    this(tf, null, FROM_LOGITS_DEFAULT, LABEL_SMOOTHING_DEFAULT, reduction);
  }

  /**
   * Creates a Binary Crossentropy loss using using {@link Class#getSimpleName()} as the loss name,
   * labelSmoothing of {@link #LABEL_SMOOTHING_DEFAULT}, a reduction of {@link
   * Loss#REDUCTION_DEFAULT},
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   */
  public BinaryCrossentropy(Ops tf, boolean fromLogits) {
    this(tf, null, fromLogits, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT);
  }

  /**
   * Creates a Binary Crossentropy loss using labelSmoothing of {@link #LABEL_SMOOTHING_DEFAULT} a
   * reduction of {@link Loss#REDUCTION_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   */
  public BinaryCrossentropy(Ops tf, String name, boolean fromLogits) {
    this(tf, name, fromLogits, LABEL_SMOOTHING_DEFAULT, REDUCTION_DEFAULT);
  }

  /**
   * Creates a Binary Crossentropy loss using using {@link Class#getSimpleName()} as the loss name,
   * and a reduction of {@link Loss#REDUCTION_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing A number in the range, [0, 1]. When 0, no smoothing occurs. When &gt; 0,
   *     compute the loss between the predicted labels and a smoothed version of the true labels,
   *     where the smoothing squeezes the labels towards 0.5. Larger values of labelSmoothing
   *     correspond to heavier smoothing.
   */
  public BinaryCrossentropy(Ops tf, boolean fromLogits, float labelSmoothing) {
    this(tf, null, fromLogits, labelSmoothing, REDUCTION_DEFAULT);
  }

  /**
   * Creates a Binary Crossentropy loss using a reduction of {@link Loss#REDUCTION_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing A number in the range, [0, 1]. When 0, no smoothing occurs. When &gt; 0,
   *     compute the loss between the predicted labels and a smoothed version of the true labels,
   *     where the smoothing squeezes the labels towards 0.5. Larger values of labelSmoothing
   *     correspond to heavier smoothing.
   */
  public BinaryCrossentropy(Ops tf, String name, boolean fromLogits, float labelSmoothing) {
    this(tf, name, fromLogits, labelSmoothing, REDUCTION_DEFAULT);
  }

  /**
   * Creates a Binary Crossentropy loss
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing A number in the range, [0, 1]. When 0, no smoothing occurs. When &gt; 0,
   *     compute the loss between the predicted labels and a smoothed version of the true labels,
   *     where the smoothing squeezes the labels towards 0.5. Larger values of labelSmoothing
   *     correspond to heavier smoothing.
   * @param reduction Type of Reduction to apply to the loss.
   */
  public BinaryCrossentropy(Ops tf, boolean fromLogits, float labelSmoothing, Reduction reduction) {
    this(tf, null, fromLogits, labelSmoothing, reduction);
  }

  /**
   * Creates a Binary Crossentropy loss
   *
   * @param tf the TensorFlow Ops
   * @param name the name of the loss
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param labelSmoothing A number in the range, [0, 1]. When 0, no smoothing occurs. When &gt; 0,
   *     compute the loss between the predicted labels and a smoothed version of the true labels,
   *     where the smoothing squeezes the labels towards 0.5. Larger values of labelSmoothing
   *     correspond to heavier smoothing.
   * @param reduction Type of Reduction to apply to the loss.
   * @throws IllegalArgumentException if labelSmoothing is not in the inclusive range of 0. - 1.
   */
  public BinaryCrossentropy(
      Ops tf, String name, boolean fromLogits, float labelSmoothing, Reduction reduction) {
    super(tf, name, reduction);
    if (labelSmoothing < 0 || labelSmoothing > 1)
      throw new IllegalArgumentException(
          "labelSmoothing must be >= 0. and <= 1, found " + labelSmoothing);
    this.fromLogits = fromLogits;
    this.labelSmoothing = labelSmoothing;
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
              cast(getTF(), getTF().constant(0), predictions.type()),
              cast(getTF(), getTF().constant(1), predictions.type()));

    } else {
      lPredictions = predictions;
    }

    Operand<T> losses =
        Losses.binaryCrossentropy(getTF(), labels, lPredictions, fromLogits, labelSmoothing);
    return LossesHelper.computeWeightedLoss(getTF(), losses, getReduction(), sampleWeights);
  }
}
