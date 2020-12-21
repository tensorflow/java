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
 * Computes the crossentropy loss between labels and predictions.
 *
 * <p>Use this crossentropy loss function when there are two or more label classes. The labels are
 * expected to be provided as integers. If you want to provide labels using <code>one-hot</code>
 * representation, please use {@link CategoricalCrossentropy} loss. There should be <code># classes
 * </code> floating point values per feature for <code>predictions</code> and a single floating
 * point value per feature for <code>label</code>.
 *
 * <p>In the snippet below, there is a single floating point value per example for <code>labels
 * </code> and <code># classes</code> floating pointing values per example for <code>predictions
 * </code>. The shape of <code>labels</code> is <code>[batch_size]</code> and the shape of <code>
 * predictions</code> is <code>[batch_size, num_classes]</code>.
 *
 * <p>Standalone usage:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; labels =
 *        tf.constant(new float[] {1, 2});
 *    Operand&lt;TFloat32&gt; predictions =
 *        tf.constant(new float[][] {{0.05f, 0.95f, 0f}, {0.1f, 0.8f, 0.1f}});
 *    SparseCategoricalCrossentropy sparseCCE = new SparseCategoricalCrossentropy(tf);
 *    Operand&lt;TFloat32&gt; result = sparseCCE.call(labels, predictions);
 *    // produces 1.177f
 * </pre>
 *
 * <p>Calling with sample weight:
 *
 * <pre>
 *    Operand&lt;TFloat32&gt; sampleWeight = tf.constant(new float[] {0.3f, 0.7f});
 *    Operand&lt;TFloat32&gt; result = sparseCCE.call(labels, predictions, sampleWeight);
 *    // produces 0.814f
 * </pre>
 *
 * <p>Using <code>SUM</code> reduction type:
 *
 * <pre>
 *    SparseCategoricalCrossentropy sparseCCE = new SparseCategoricalCrossentropy(tf, Reduction.SUM);
 *    Operand&lt;TFloat32&gt; result = sparseCCE.call(labels, predictions);
 *    // produces 2.354f
 * </pre>
 *
 * <p>Using <code>NONE</code> reduction type:
 *
 * <pre>
 *    SparseCategoricalCrossentropy sparseCCE = new SparseCategoricalCrossentropy(tf, Reduction.NONE);
 *    Operand&lt;TFloat32&gt; result = sparseCCE.call(labels, predictions);
 *    // produces [0.0513f, 2.303f]
 * </pre>
 */
public class SparseCategoricalCrossentropy extends Loss {
  public static final boolean FROM_LOGITS_DEFAULT = false;
  public static final int AXIS_DEFAULT = -1;

  private final boolean fromLogits;
  private final int axis;

  /**
   * Creates a SparseCategoricalCrossentropy loss using {@link Class#getSimpleName()} as the loss
   * name, a Loss Reduction of {@link Loss#REDUCTION_DEFAULT}, and fromLogits={@link #FROM_LOGITS_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   */
  public SparseCategoricalCrossentropy(Ops tf) {
    this(tf, null, FROM_LOGITS_DEFAULT, REDUCTION_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy loss using a Loss Reduction of {@link Loss#REDUCTION_DEFAULT},
   * and fromLogits={@link #FROM_LOGITS_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss function
   */
  public SparseCategoricalCrossentropy(Ops tf, String name) {
    this(tf, name, FROM_LOGITS_DEFAULT, REDUCTION_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy loss using {@link Class#getSimpleName()} as the loss
   * name, with Reduction.AUTO and fromLogits={@link #FROM_LOGITS_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param reduction Type of Reduction to apply to loss.
   */
  public SparseCategoricalCrossentropy(Ops tf, Reduction reduction) {
    this(tf, null, FROM_LOGITS_DEFAULT, reduction, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy loss with Reduction.AUTO and fromLogits={@link
   * #FROM_LOGITS_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss function
   * @param reduction Type of Reduction to apply to loss.
   */
  public SparseCategoricalCrossentropy(Ops tf, String name, Reduction reduction) {
    this(tf, name, FROM_LOGITS_DEFAULT, reduction, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy using a Loss Reduction of {@link Loss#REDUCTION_DEFAULT}, and
   * fromLogits={@link #FROM_LOGITS_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss function
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   */
  public SparseCategoricalCrossentropy(Ops tf, String name, boolean fromLogits) {
    this(tf, name, fromLogits, REDUCTION_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy loss using {@link Class#getSimpleName()} as the loss
   * name, a Loss Reduction of {@link Loss#REDUCTION_DEFAULT} and fromLogits={@link #FROM_LOGITS_DEFAULT}.
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   */
  public SparseCategoricalCrossentropy(Ops tf, boolean fromLogits) {
    this(tf, null, fromLogits, REDUCTION_DEFAULT, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy loss using {@link Class#getSimpleName()} as the loss
   * name,
   *
   * @param tf the TensorFlow Ops
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param reduction Type of Reduction to apply to loss.
   */
  public SparseCategoricalCrossentropy(Ops tf, boolean fromLogits, Reduction reduction) {
    this(tf, null, fromLogits, reduction, AXIS_DEFAULT);
  }

  /**
   * Creates a SparseCategoricalCrossentropy
   *
   * @param tf the TensorFlow Ops
   * @param name the name of this loss function
   * @param fromLogits Whether to interpret predictions as a tensor of logit values
   * @param reduction Type of Reduction to apply to loss.
   * @param axis The channels axis. <code>axis=-1</code> corresponds to data format `Channels Last'
   *     and <code>axis=1</code> corresponds to data format 'Channels First'.
   */
  public SparseCategoricalCrossentropy(
      Ops tf, String name, boolean fromLogits, Reduction reduction, int axis) {
    super(tf, name, reduction);
    this.fromLogits = fromLogits;
    this.axis = axis;
  }

  /**
   * Generates an Operand the calculates the loss.
   *
   * If run in Graph mode, the computation will throw {@link org.tensorflow.exceptions.TFInvalidArgumentException}
   * if the predictions values are outside the range o [0. to 1.]. In Eager Mode, this call
   * will throw {@link IllegalArgumentException}, if the predictions values are outside the range o [0. to 1.]
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
        Losses.sparseCategoricalCrossentropy(getTF(), labels, lPredictions, fromLogits, axis);
    return LossesHelper.computeWeightedLoss(getTF(), losses, getReduction(), sampleWeights);
  }
}
