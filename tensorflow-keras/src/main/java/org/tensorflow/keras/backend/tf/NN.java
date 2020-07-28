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
package org.tensorflow.keras.backend.tf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.nn.SparseSoftmaxCrossEntropyWithLogits;
import org.tensorflow.types.TBfloat16;
import org.tensorflow.types.TFloat16;
import org.tensorflow.types.TFloat32;

/** NN Operations */
public class NN {

  /**
   * Computes sparse softmax cross entropy between `logits` and `labels`.
   *
   * @param tf
   * @param labels `Tensor` of shape `[d_0, d_1, ..., d_{r-1}]` (where `r` is rank of `labels` and
   *     result) and dtype `int32` or `int64`. Each entry in `labels` must be an index in `[0,
   *     num_classes)`. Other values will raise an exception when this op is run on CPU, and return
   *     `NaN` for corresponding loss and gradient rows on GPU.
   * @param logits Per-label activations (typically a linear output) of shape `[d_0, d_1, ...,
   *     d_{r-1}, num_classes]` and dtype `float16`, `float32`, or `float64`. These activation
   *     energies are interpreted as unnormalized log probabilities.
   * @return A `Tensor` of the same shape as `labels` and of the same type as `logits` with the
   *     softmax cross entropy loss.
   */
  public static Operand sparse_softmax_cross_entropy_with_logits(
      Ops tf, Operand labels, Operand logits) {
    // assert shapeIsCompatible(labels.asOutput().shape(), logits.asOutput().shape()):
    //        String.format("Shapes %s and %s are incompatible",
    //                labels.asOutput().shape(), logits.asOutput().shape());
    tf = tf.withSubScope("SparseSoftmaxCrossEntropyWithLogits");
    Operand precise_logits = logits;
    boolean convertToFloat32 =
        logits.asOutput().dataType() == TFloat16.DTYPE
            || logits.asOutput().dataType() == TBfloat16.DTYPE;
    if (convertToFloat32) {
      precise_logits = tf.dtypes.cast(logits, TFloat32.DTYPE);
    }
    Shape labelsStaticShape = labels.asOutput().shape();
    org.tensorflow.op.core.Shape labelsShape = tf.shape(labels);
    Shape logitsShape = logits.asOutput().shape();
    Operand labels_shape = tf.shape(labels);
    Shape logitsShortened = logitsShape.take(logitsShape.numDimensions() - 1);

    boolean staticShapesFullyDefined =
        !labelsStaticShape.hasUnknownDimension() && !logitsShortened.hasUnknownDimension();
    if (logitsShape.numDimensions() == 0) {
      throw new IllegalArgumentException(
          String.format("Logits cannot be scalars - received shape %s.", logitsShape));
    }
    if (!logitsShape.hasUnknownDimension()
        && !labelsStaticShape.hasUnknownDimension()
        && labelsStaticShape.numDimensions() != logitsShape.numDimensions() - 1) {
      throw new IllegalArgumentException(
          String.format(
              "Rank mismatch: Rank of labels (received %s) should equal rank of logits minus 1 (received %s).",
              labelsStaticShape.toString(), logitsShape.toString()));
    }

    if (staticShapesFullyDefined && !labelsStaticShape.equals(logitsShortened)) {
      throw new IllegalArgumentException(
          String.format(
              "Shape mismatch: The shape of labels (received %s) "
                  + "should equal the shape of logits except for the last "
                  + "dimension (received %s).",
              labelsStaticShape.toString(), logitsShape.toString()));
    }
    // Check if no reshapes are required.
    if (logitsShape.numDimensions() == 2) {
      SparseSoftmaxCrossEntropyWithLogits smax =
          tf.nn.sparseSoftmaxCrossEntropyWithLogits(precise_logits, labels);
      Operand loss = smax.loss();
      if (logits.asOutput().dataType() == TFloat16.DTYPE) {
        loss = tf.dtypes.cast(loss, TFloat16.DTYPE);
      }
      return loss;
    }

    List<Op> shapeChecks = new ArrayList<>();

    if (!staticShapesFullyDefined) {
      shapeChecks.add(
          tf.assertThat(
              tf.math.equal(tf.shape(labels), tf.shape.take(tf.shape(logits), tf.constant(-1))),
              Arrays.asList(
                  tf.constant(
                      "Shape mismatch: The shape of labels  "
                          + "should equal the shape of logits except for the last "
                          + "dimension "))));
    }

    // Reshape logits to 2 dim, labels to 1 dim.
    long numClassses = logitsShape.size(logitsShape.numDimensions() - 1);

    precise_logits = tf.reshape(precise_logits, tf.constant(new long[] {-1, numClassses}));
    labels = tf.reshape(labels, tf.constant(-1));
    SparseSoftmaxCrossEntropyWithLogits smax =
        tf.nn.sparseSoftmaxCrossEntropyWithLogits(precise_logits, labels);

    return ControlDependencies.addControlDependencies(
        tf,
        tfc -> {
          Operand cost = smax.loss();

          cost = tfc.reshape(cost, labels_shape);
          if (logits.asOutput().dataType() == TFloat16.DTYPE) {
            cost = tfc.dtypes.cast(cost, TFloat16.DTYPE);
          }
          return cost;
        },
        "sparse_softmax_cross_entropy_with_logits",
        shapeChecks);
  }
}
