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

import org.tensorflow.keras.backend.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.keras.utils.ShapeUtils;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Squeeze;
import org.tensorflow.op.core.Stack;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/** ConfusionMatrix operations */
public class ConfusionMatrix {

  /**
   * Squeeze last dim if ranks differ from expected by exactly 1.
   *
   * @param tf the TensorFlowOps
   * @param labels Label values, a `Tensor` whose dimensions match `predictions`.
   * @param predictions Predicted values, a `Tensor` of arbitrary dimensions.
   * @return `labels` and `predictions`, possibly with last dim squeezed.
   */
  public static Tuple removeSqueezableDimensions(Ops tf, Operand labels, Operand predictions) {
    return removeSqueezableDimensions(tf, labels, predictions, 0);
  }

  /**
   * Squeeze last dim if ranks differ from expected by exactly 1.
   *
   * @param tf the TensorFlowOps
   * @param labels Label values, a `Tensor` whose dimensions match `predictions`.
   * @param predictions Predicted values, a `Tensor` of arbitrary dimensions.
   * @param expectedRankDiff Expected result of `rank(predictions) - rank(labels)`.
   * @return `labels` and `predictions`, possibly with last dim squeezed.
   */
  public static Tuple removeSqueezableDimensions(
      Ops tf, Operand labels, Operand predictions, int expectedRankDiff) {

    tf = tf.withSubScope("removeSqueezableDimensions");
    Shape predictionsShape = predictions.asOutput().shape();
    int predictionsRank = predictionsShape.numDimensions();
    Shape labelsShape = labels.asOutput().shape();
    int labelsRank = labelsShape.numDimensions();

    if (predictionsRank != Shape.UNKNOWN_SIZE && labelsRank != Shape.UNKNOWN_SIZE) {
      // Use static rank.
      int rankDiff = predictionsRank - labelsRank;
      if (rankDiff == expectedRankDiff + 1
          && ShapeUtils.isCompatible(predictionsShape.size(-1), 1)) {
        predictions = tf.squeeze(predictions);
      } else if (rankDiff == expectedRankDiff - 1
          && ShapeUtils.isCompatible(labelsShape.size(-1), 1)) {
        labels = tf.squeeze(labels);
      }
      return new Tuple(labels, predictions);
    }
    // Use dynamic rank.
    Operand rankDiff = tf.math.sub(tf.rank(predictions), tf.rank(labels));
    if (predictionsRank == Shape.UNKNOWN_SIZE
        && ShapeUtils.isCompatible(predictionsShape.size(-1), 1)) {
      /**
       * TODO, if we ever get a select that does lazy evaluation, but for now do the tf.squeeze
       * predictions = tf.select( tf.math.equal(tf.constant(expectedRankDiff+1),rankDiff ),
       * tf.squeeze(predictions, Squeeze.axis(Arrays.asList(-1L))), predictions ); *
       */
      predictions = tf.squeeze(predictions, Squeeze.axis(Arrays.asList(-1L)));
    }
    if (labelsRank == Shape.UNKNOWN_SIZE && ShapeUtils.isCompatible(labelsShape.size(-1), 1)) {
      /**
       * TODO, if we ever get a select that does lazy evaluation labels = tf.select(
       * tf.math.equal(tf.constant(expectedRankDiff+1),rankDiff ), tf.squeeze(labels,
       * Squeeze.axis(Arrays.asList(-1L))), predictions ); *
       */
      labels = tf.squeeze(labels, Squeeze.axis(Arrays.asList(-1L)));
    }
    return new Tuple(labels, predictions);
  }

  /**
   * Computes the confusion matrix from predictions and labels.
   *
   * @param tf the TensorFlow Ops
   * @param labels 1-D `Tensor` of real labels for the classification task.
   * @param predictions 1-D `Tensor` of predictions for a given classification.
   * @param numClasses The possible number of labels the classification task can have.
   * @param weights optional weights to be applied to the confusion matris
   * @param dtype Data type of the confusion matrix.
   * @param <T> the type of Operands
   * @param <U> the data type.
   * @return A `Tensor` of type `dtype` with shape `[n, n]` representing the confusion matrix, where
   *     `n` is the number of possible labels in the classification task.
   * @throws IllegalArgumentException If both predictions and labels are not 1-D vectors and have
   *     mismatched shapes, or if `weights` is not `None` and its shape doesn't match `predictions`.
   */
  public static <T extends TType, U extends TNumber> Operand confusionMatrix(
      Ops tf,
      Operand<T> labels,
      Operand<T> predictions,
      Operand<TInt64> numClasses,
      Operand<T> weights,
      DataType<U> dtype) {
    tf = tf.withSubScope("confusion_matrix");
    Tuple ops = K.squeezeOrExpandDimensions(tf, predictions, labels, null);
    predictions = tf.dtypes.cast(ops.getPredictions(), TInt64.DTYPE);
    labels = tf.dtypes.cast(ops.getLabels(), TInt64.DTYPE);

    List<Op> labelControls = new ArrayList<>();
    List<Op> predictionControls = new ArrayList<>();

    labelControls.add(
        tf.assertThat(
            tf.reduceAny(
                tf.math.greaterEqual((Operand<TInt64>) labels, tf.constant(0L)),
                K.allAxis(tf, labels)),
            Arrays.asList(tf.constant("`labels` contains negative values"))));

    predictionControls.add(
        tf.assertThat(
            tf.reduceAny(
                tf.math.greaterEqual((Operand<TInt64>) predictions, tf.constant(0L)),
                K.allAxis(tf, labels)),
            Arrays.asList(tf.constant("`predictions` contains negative values"))));
    if (numClasses == null) {
      numClasses =
          tf.math.maximum(
              tf.reduceMax(predictions, K.allAxis(tf, predictions)),
              tf.reduceMax(labels, K.allAxis(tf, labels)));
    } else {
      labelControls.add(
          tf.assertThat(
              tf.reduceAny(
                  tf.math.less((Operand<TInt64>) labels, numClasses), K.allAxis(tf, labels)),
              Arrays.asList(tf.constant("``labels` out of bound"))));
      predictionControls.add(
          tf.assertThat(
              tf.reduceAny(
                  tf.math.less((Operand<TInt64>) predictions, numClasses),
                  K.allAxis(tf, predictions)),
              Arrays.asList(tf.constant("``predictions` out of bound"))));
    }

    if (weights != null) {
      if (!ShapeUtils.isCompatibleWith(
          predictions.asOutput().shape(), weights.asOutput().shape())) {
        throw new IllegalArgumentException(
            String.format(
                "Prediction shape %s is not compatible with weights shaope %s",
                predictions.asOutput().shape(), weights.asOutput().shape()));
      }
    }
    final Operand labelsFinal = labels;
    labels =
        ControlDependencies.addControlDependencies(
            tf, tfc -> tfc.identity(labelsFinal), "confusionMatrix", labelControls);
    final Operand predictionsFinal = predictions;
    predictions =
        ControlDependencies.addControlDependencies(
            tf, tfc -> tfc.identity(predictionsFinal), "confusionMatrix", labelControls);

    Operand<TInt64> shape = tf.stack(Arrays.asList(numClasses, numClasses));
    Operand indices = tf.stack(Arrays.asList(labels, predictions), Stack.axis(1L));
    Operand values =
        weights == null
            ? tf.dtypes.cast(tf.onesLike(predictions), dtype)
            : tf.dtypes.cast(weights, dtype);
    SparseTensor cmSparse = new SparseTensor(indices, values, shape);
    Operand zeroMatrix = tf.zeros(shape, dtype);

    return tf.sparse.sparseTensorDenseAdd(
        cmSparse.getIndices(), cmSparse.getValues(), cmSparse.getDenseShape(), zeroMatrix);
  }
}
