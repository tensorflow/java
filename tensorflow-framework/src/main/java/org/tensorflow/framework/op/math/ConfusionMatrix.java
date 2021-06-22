/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

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
package org.tensorflow.framework.op.math;

import java.util.Arrays;
import java.util.Collections;
import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.LossTuple;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Scope;
import org.tensorflow.op.core.AssertThat;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Identity;
import org.tensorflow.op.core.OnesLike;
import org.tensorflow.op.core.ReduceAll;
import org.tensorflow.op.core.ReduceMax;
import org.tensorflow.op.core.ScatterNd;
import org.tensorflow.op.core.Squeeze;
import org.tensorflow.op.core.Stack;
import org.tensorflow.op.dtypes.Cast;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.GreaterEqual;
import org.tensorflow.op.math.Less;
import org.tensorflow.op.math.Maximum;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.family.TNumber;

/** Confusion Matrix Operations */
public class ConfusionMatrix {

  /**
   * Computes the confusion matrix from predictions and labels.
   *
   * <p>The matrix columns represent the prediction labels and the rows represent the real labels.
   * The confusion matrix is always a 2-D array of shape {@code [n,n]}, where {@code n} is the
   * number of valid labels for a given classification task. Both prediction and labels must be 1-D
   * arrays of the same shape in order for this function to work.
   *
   * <p>If {@code numClasses} is null, then {@code numClasses} will be set to one plus the maximum
   * value in either predictions or labels. Class labels are expected to start at 0. For example, if
   * {@code numClasses} is 3, then the possible labels would be {@code [0, 1, 2]}.
   *
   * <p>If {@code weights} is not null, then each prediction contributes its corresponding weight to
   * the total value of the confusion matrix cell.
   *
   * <p>For example:
   *
   * <pre>{@code
   * fops.math.confusion_matrix(tf.constant(new int[] {1, 2, 4}), tf.constant(new int[] {2, 2, 4})) ==>
   *     [[0 0 0 0 0]
   *      [0 0 1 0 0]
   *      [0 0 1 0 0]
   *      [0 0 0 0 0]
   *      [0 0 0 0 1]]
   * }</pre>
   *
   * <p>Note that the possible labels are assumed to be {@code [0, 1, 2, 3, 4]}, resulting in a 5x5
   * confusion matrix.
   *
   * @param scope The TensorFlow scope
   * @param labels 1-D Operand of real labels for the classification task.
   * @param predictions 1-D Operand of predictions for a given classification.
   * @param <T> Data type of the confusion matrix.
   * @return An Operand of type {@code type} with shape {@code [n, n]} representing the confusion
   *     matrix, where {@code n} is the number of possible labels in the classification task.
   * @throws IllegalArgumentException If both predictions and labels are not 1-D vectors and have
   *     mismatched shapes, or if {@code weights} is not null and its shape doesn't match {@code
   *     predictions}.
   */
  public static <T extends TNumber> Operand<T> confusionMatrix(
      Scope scope, Operand<T> labels, Operand<T> predictions) {
    return confusionMatrix(scope, labels, predictions, null, null);
  }

  /**
   * Computes the confusion matrix from predictions and labels.
   *
   * <p>The matrix columns represent the prediction labels and the rows represent the real labels.
   * The confusion matrix is always a 2-D array of shape {@code [n,n]}, where {@code n} is the
   * number of valid labels for a given classification task. Both prediction and labels must be 1-D
   * arrays of the same shape in order for this function to work.
   *
   * <p>If {@code numClasses} is null, then {@code numClasses} will be set to one plus the maximum
   * value in either predictions or labels. Class labels are expected to start at 0. For example, if
   * {@code numClasses} is 3, then the possible labels would be {@code [0, 1, 2]}.
   *
   * <p>If {@code weights} is not null, then each prediction contributes its corresponding weight to
   * the total value of the confusion matrix cell.
   *
   * <p>For example:
   *
   * <pre>{@code
   * fops.math.confusion_matrix(tf.constant(new int[] {1, 2, 4}), tf.constant(new int[] {2, 2, 4})) ==>
   *     [[0 0 0 0 0]
   *      [0 0 1 0 0]
   *      [0 0 1 0 0]
   *      [0 0 0 0 0]
   *      [0 0 0 0 1]]
   * }</pre>
   *
   * <p>Note that the possible labels are assumed to be {@code [0, 1, 2, 3, 4]}, resulting in a 5x5
   * confusion matrix.
   *
   * @param scope The TensorFlow scope
   * @param labels 1-D Operand of real labels for the classification task.
   * @param predictions 1-D Operand of predictions for a given classification.
   * @param weights An optional Operand whose shape matches {@code predictions}.
   * @param <T> Data type of the confusion matrix.
   * @return An Operand of type {@code type} with shape {@code [n, n]} representing the confusion
   *     matrix, where {@code n} is the number of possible labels in the classification task.
   * @throws IllegalArgumentException If both predictions and labels are not 1-D vectors and have
   *     mismatched shapes, or if {@code weights} is not null and its shape doesn't match {@code
   *     predictions}.
   */
  public static <T extends TNumber> Operand<T> confusionMatrix(
      Scope scope, Operand<T> labels, Operand<T> predictions, Operand<T> weights) {
    return confusionMatrix(scope, labels, predictions, weights, null);
  }

  /**
   * Computes the confusion matrix from predictions and labels.
   *
   * <p>The matrix columns represent the prediction labels and the rows represent the real labels.
   * The confusion matrix is always a 2-D array of shape {@code [n,n]}, where {@code n} is the
   * number of valid labels for a given classification task. Both prediction and labels must be 1-D
   * arrays of the same shape in order for this function to work.
   *
   * <p>If {@code numClasses} is null, then {@code numClasses} will be set to one plus the maximum
   * value in either predictions or labels. Class labels are expected to start at 0. For example, if
   * {@code numClasses} is 3, then the possible labels would be {@code [0, 1, 2]}.
   *
   * <p>If {@code weights} is not null, then each prediction contributes its corresponding weight to
   * the total value of the confusion matrix cell.
   *
   * <p>For example:
   *
   * <pre>{@code
   * fops.math.confusion_matrix(tf.constant(new int[] {1, 2, 4}), tf.constant(new int[] {2, 2, 4})) ==>
   *     [[0 0 0 0 0]
   *      [0 0 1 0 0]
   *      [0 0 1 0 0]
   *      [0 0 0 0 0]
   *      [0 0 0 0 1]]
   * }</pre>
   *
   * <p>Note that the possible labels are assumed to be {@code [0, 1, 2, 3, 4]}, resulting in a 5x5
   * confusion matrix.
   *
   * @param scope The TensorFlow scope
   * @param labels 1-D Operand of real labels for the classification task.
   * @param predictions 1-D Operand of predictions for a given classification.
   * @param weights An optional Operand whose shape matches {@code predictions}.
   * @param numClasses The possible number of labels the classification task can have. If this value
   *     is null, it will be calculated using both predictions and labels.
   * @param <T> Data type of the confusion matrix.
   * @return An Operand of type {@code type} with shape {@code [n, n]} representing the confusion
   *     matrix, where {@code n} is the number of possible labels in the classification task.
   * @throws IllegalArgumentException If both predictions and labels are not 1-D vectors and have
   *     mismatched shapes, or if {@code weights} is not null and its shape doesn't match {@code
   *     predictions}.
   */
  public static <T extends TNumber> Operand<T> confusionMatrix(
      Scope scope,
      Operand<T> labels,
      Operand<T> predictions,
      Operand<T> weights,
      Operand<TInt64> numClasses) {
    Scope lScope = scope.withSubScope("confusionMatrix");
    LossTuple<T> tuple = removeSqueezableDimensions(scope, labels, predictions, 0);
    Operand<TInt64> lLabels = Cast.create(lScope, tuple.getLabels(), TInt64.class);
    Operand<TInt64> lPredictions = Cast.create(lScope, tuple.getTarget(), TInt64.class);

    Operand<TInt64> zero = Constant.scalarOf(lScope, 0L);
    Operand<TInt64> one = Constant.scalarOf(lScope, 1L);

    AssertThat labelsNonNegative =
        AssertThat.create(
            lScope,
            ReduceAll.create(
                lScope, GreaterEqual.create(lScope, lLabels, zero), Axes.allAxes(scope, lLabels)),
            Collections.singletonList(
                Constant.scalarOf(lScope, "labels contains negative values")));
    lLabels =
        Identity.create(
            lScope.withControlDependencies(Collections.singletonList(labelsNonNegative)), lLabels);

    AssertThat predictionsNonNegative =
        AssertThat.create(
            lScope,
            ReduceAll.create(
                lScope,
                GreaterEqual.create(lScope, lPredictions, zero),
                Axes.allAxes(scope, lPredictions)),
            Collections.singletonList(
                Constant.scalarOf(lScope, "predictions contains negative values")));
    lPredictions =
        Identity.create(
            lScope.withControlDependencies(Collections.singletonList(predictionsNonNegative)),
            lPredictions);

    Operand<TInt64> lNumClasses;
    if (numClasses == null) {
      lNumClasses =
          Add.create(
              lScope,
              Maximum.create(
                  lScope,
                  ReduceMax.create(lScope, lPredictions, zero),
                  ReduceMax.create(lScope, lLabels, zero)),
              one);
    } else {
      lNumClasses = Cast.create(lScope, numClasses, TInt64.class);
      Operand<TBool> less = Less.create(lScope, lLabels, lNumClasses);
      AssertThat labelsLess =
          AssertThat.create(
              lScope,
              ReduceAll.create(scope, less, Axes.allAxes(scope, less), ReduceAll.keepDims(false)),
              Collections.singletonList(Constant.scalarOf(lScope, "labels out of bounds")));
      lLabels =
          Identity.create(
              lScope.withControlDependencies(Collections.singletonList(labelsLess)), lLabels);

      less = Less.create(lScope, lPredictions, lNumClasses);
      AssertThat predictionsLess =
          AssertThat.create(
              lScope,
              ReduceAll.create(scope, less, Axes.allAxes(scope, less), ReduceAll.keepDims(false)),
              Collections.singletonList(Constant.scalarOf(lScope, "predictions  out of bounds")));
      lPredictions =
          Identity.create(
              lScope.withControlDependencies(Collections.singletonList(predictionsLess)),
              lPredictions);
    }

    if (weights != null) {
      if (!predictions.shape().isCompatibleWith(weights.shape())) {
        throw new IllegalArgumentException(
            String.format(
                "predictions.shape() [%s], is not compatible with weights.shape() [ %s].",
                predictions.shape(), weights.shape()));
      }
    }

    Operand<TInt64> shape = Stack.create(lScope, Arrays.asList(lNumClasses, lNumClasses));
    Operand<TInt64> indices =
        Stack.create(lScope, Arrays.asList(lLabels, lPredictions), Stack.axis(1L));
    Operand<T> values = weights == null ? OnesLike.create(lScope, predictions) : weights;
    /// Operand<T> zeroMatrix = Zeros.create(lScope, Cast.create(lScope, shape, TInt32.class),
    // type);

    return ScatterNd.create(lScope, indices, values, shape);
  }

  /**
   * Squeeze last dim if ranks differ from expected by exactly 1.
   *
   * @param scope The TensorFlow scope
   * @param labels Label values, a {@code Operand} whose dimensions match {@code predictions }.
   * @param predictions Predicted values, a {@code Tensor} of arbitrary dimensions.
   * @param expectedRankDiff Expected result of {@code rank(predictions) - rank(labels)}.
   * @param <T> the data type for the labels, predictions and result
   * @return {@code labels} and {@code predictions}, possibly with last dim squeezed.
   */
  private static <T extends TNumber> LossTuple<T> removeSqueezableDimensions(
      Scope scope, Operand<T> labels, Operand<T> predictions, int expectedRankDiff) {
    Scope lScope = scope.withSubScope("removeSqueezableDimensions");
    Shape predictionsShape = predictions.shape();
    int predictionsRank = predictionsShape.numDimensions();
    Shape labelsShape = labels.shape();
    int labelsRank = labelsShape.numDimensions();

    if (predictionsRank != Shape.UNKNOWN_SIZE || labelsRank != Shape.UNKNOWN_SIZE) {
      // Use rank.
      int rankDiff = predictionsRank - labelsRank;
      if (rankDiff == expectedRankDiff + 1 && Shape.isCompatible(predictionsShape.size(-1), 1)) {
        predictions = Squeeze.create(lScope, predictions);
      } else if (rankDiff == expectedRankDiff - 1 && Shape.isCompatible(labelsShape.size(-1), 1)) {
        labels = Squeeze.create(lScope, labels);
      }
      return new LossTuple<>(labels, predictions);
    }
    // Use dynamic rank.

    // TODO: hold for lazy select feature,
    //  Operand<TInt32> rankDiff = tf.math.sub(tf.rank(predictions), tf.rank(labels));
    if (predictionsRank == Shape.UNKNOWN_SIZE && Shape.isCompatible(predictionsShape.size(-1), 1)) {
      /*
       * TODO, if we ever get a select that does lazy evaluation, but for now do the tf.squeeze
       * predictions = tf.select( tf.math.equal(tf.constant(expectedRankDiff+1),rankDiff ),
       * tf.squeeze(predictions, Squeeze.axis(Arrays.asList(-1L))), predictions ); *
       */
      predictions =
          Squeeze.create(lScope, predictions, Squeeze.axis(Collections.singletonList(-1L)));
    }
    if (labelsRank == Shape.UNKNOWN_SIZE && Shape.isCompatible(labelsShape.size(-1), 1)) {
      /*
       * TODO, if we ever get a select that does lazy evaluation labels = tf.select(
       * tf.math.equal(tf.constant(expectedRankDiff+1),rankDiff ), tf.squeeze(labels,
       * Squeeze.axis(Arrays.asList(-1L))), predictions ); *
       */
      labels = Squeeze.create(lScope, labels, Squeeze.axis(Collections.singletonList(-1L)));
    }
    return new LossTuple<>(labels, predictions);
  }
}
