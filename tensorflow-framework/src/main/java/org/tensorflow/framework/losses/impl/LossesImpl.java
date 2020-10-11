package org.tensorflow.framework.losses.impl;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.framework.losses.Reduction;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.ReduceSum;
import org.tensorflow.op.core.Squeeze;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

import java.util.Collections;

public class LossesImpl {

  /**
   * Squeeze or expand last dimension if needed with a sampleWeights of one.
   *
   * <ol type="1">
   *   <li>Squeezes last dim of <code>predictions</code> or <code>labels</code> if their rank
   *       differs by 1 (using {@link #removeSqueezableDimensions}).
   *   <li>Squeezes or expands last dim of <code>sampleWeight</code> if its rank differs by 1 from
   *       the new rank of <code>predictions</code>. If <code>sampleWeight</code> is scalar, it is
   *       kept scalar./li>
   * </ol>
   *
   * @param tf the TensorFlow Ops
   * @param predictions Predicted values, a <code>Operand</code> of arbitrary dimensions.
   * @param labels Optional label <code>Operand</code> whose dimensions match <code>prediction
   *     </code>.
   * @return LossTuple of <code>prediction</code>, <code>label</code>,<code>sampleWeight</code> will
   *     be null. Each of them possibly has the last dimension squeezed, <code>sampleWeight</code>
   *     could be extended by one dimension. If <code>sampleWeight</code> is null, (prediction,
   *     label) is returned.
   */
  public static <T extends TNumber> LossTuple<T> squeezeOrExpandDimensions(
      Ops tf, Operand<T> labels, Operand<T> predictions) {
    return squeezeOrExpandDimensions(tf, labels, predictions, null);
  }

  /**
   * Squeeze or expand last dimension if needed.
   *
   * <ol type="1">
   *   <li>Squeezes last dim of <code>predictions</code> or <code>labels</code> if their rank do not
   *       differ by 1.
   *   <li>Squeezes or expands last dim of <code>sampleWeight</code> if its rank differs by 1 from
   *       the new rank of <code>predictions</code>. If <code>sampleWeight</code> is scalar, it is
   *       kept scalar.
   * </ol>
   *
   * @param tf the TensorFlow Ops
   * @param predictions Predicted values, a <code>Operand</code> of arbitrary dimensions.
   * @param labels Optional label <code>Operand</code> whose dimensions match <code>prediction
   *     </code>.
   * @param sampleWeights Optional sample weight(s) <code>Operand</code> whose dimensions match<code>
   *     prediction</code>.
   * @return LossTuple of <code>prediction<s/code>, <code>labels</code> and <code>sampleWeight</code>.
   *     Each of them possibly has the last dimension squeezed, <code>sampleWeight</code> could be
   *     extended by one dimension. If <code>sampleWeight</code> is null, only the possibly shape modified <code>predictions</code> and <code>labels</code> are
   *     returned.
   */
  public static <T extends TNumber> LossTuple<T> squeezeOrExpandDimensions(
      Ops tf, Operand<T> labels, Operand<T> predictions, Operand<T> sampleWeights) {

    Shape predictionsShape = predictions.asOutput().shape();
    long predictionsRank = predictionsShape.numDimensions();

    // Default case when no modifications are made.
    LossTuple<T> lossTuple = new LossTuple<>(labels, predictions, sampleWeights);
    if (labels != null) {
      Shape labelsShape = labels.asOutput().shape();
      long labelsRank = labelsShape.numDimensions();
      if (labelsRank != Shape.UNKNOWN_SIZE && predictionsRank != Shape.UNKNOWN_SIZE) {
        // Use static rank for 'label' and 'prediction'.
        if (predictionsRank - labelsRank != 1 || predictionsShape.size(-1) == 1) {
          // label, prediction = confusion_matrix.remove_squeezable_dimensions(label, prediction)
          lossTuple = removeSqueezableDimensions(tf, labels, predictions);
        }
      } else { // use dynamic rank
        lossTuple = removeSqueezableDimensions(tf, labels, predictions);
      }
    }
    if (sampleWeights == null) { // nothing more to do.
      return lossTuple;
    }
    Shape weightsShape = sampleWeights.asOutput().shape();
    long weightsRank = weightsShape.numDimensions();
    if (weightsRank == 0) { // scalar
      return new LossTuple<>(labels, predictions, sampleWeights);
    }

    if (predictionsRank != Shape.UNKNOWN_SIZE && weightsRank != Shape.UNKNOWN_SIZE) {

      if (weightsRank - predictionsRank == 1) {
        sampleWeights = tf.squeeze(sampleWeights);
      } else if (predictionsRank - weightsRank == 1) {
        sampleWeights = tf.expandDims(sampleWeights, tf.constant(-1L));
      }
      return new LossTuple<>(labels, predictions, sampleWeights);
    }
    // Use dynamic rank.
    Operand<TInt32> weightsRankTensor = tf.rank(sampleWeights);
    Operand<TInt32> rankDiff = tf.math.sub(weightsRankTensor, tf.rank(predictions));
    sampleWeights =
        tf.select(
            tf.math.equal(weightsRankTensor, tf.constant(0)),
            sampleWeights,
            maybeAdjustWeights(tf, sampleWeights, rankDiff));
    return new LossTuple<>(labels, predictions, sampleWeights);
  }

  /**
   * Squeeze or expand the sampleWeight based on the rank difference
   *
   * <p>If the rank difference is +1, squeeze the last dimension of sampleWeight, If the rank
   * difference is -1, expand the last dimension of sampleWeight. Otherwise, leave the shape of
   * sampleWeight as is.
   *
   * @param tf the TensorFlow Ops
   * @param sampleWeight the sample weights
   * @param rankDiff the difference in rank
   * @param <T> the data type for the Operands.
   * @return the adjusted sampleWeight
   */
  private static <T extends TNumber> Operand<T> maybeAdjustWeights(
      Ops tf, Operand<T> sampleWeight, Operand<TInt32> rankDiff) {
    return tf.select(
        tf.math.equal(rankDiff, tf.constant(1)),
        tf.squeeze(sampleWeight, Squeeze.axis(Collections.singletonList(-1L))),
        maybeExpandWeights(tf, sampleWeight, rankDiff));
  }

  /**
   * Expand the last dimension of sampleWeight. if the rank difference is -1.
   *
   * @param tf the TensorFlow Ops
   * @param sampleWeight the sample weights
   * @param rankDiff the difference in rank
   * @param <T> the data type for the Operands.
   * @return the adjusted sampleWeight
   */
  private static <T extends TNumber> Operand<T> maybeExpandWeights(
      Ops tf, Operand<T> sampleWeight, Operand<TInt32> rankDiff) {
    return tf.select(
        tf.math.equal(rankDiff, tf.constant(-1)),
        tf.expandDims(sampleWeight, tf.constant(-1)),
        sampleWeight);
  }

  /**
   * Squeeze last dim if ranks differ from expected by exactly 1.
   *
   * @param tf the TensorFlowOps
   * @param labels Label values, a <code>Tensor</code> whose dimensions match <code>predictions
   *     </code>.
   * @param predictions Predicted values, a <code>Tensor</code> of arbitrary dimensions.
   * @return <code>labels</code> and <code>predictions</code>, possibly with last dim squeezed.
   */
  public static <T extends TNumber> LossTuple<T> removeSqueezableDimensions(
      Ops tf, Operand<T> labels, Operand<T> predictions) {
    return removeSqueezableDimensions(tf, labels, predictions, 0);
  }

  /**
   * Squeeze last dim if ranks differ from expected by exactly 1.
   *
   * @param tf the TensorFlowOps
   * @param labels Label values, a <code>Operand</code> whose dimensions match <code>predictions
   *     </code>.
   * @param predictions Predicted values, a <code>Tensor</code> of arbitrary dimensions.
   * @param expectedRankDiff Expected result of <code>rank(predictions) - rank(labels)</code>.
   * @return <code>labels</code> and <code>predictions</code>, possibly with last dim squeezed.
   */
  public static <T extends TNumber> LossTuple<T> removeSqueezableDimensions(
      Ops tf, Operand<T> labels, Operand<T> predictions, int expectedRankDiff) {

    tf = tf.withSubScope("removeSqueezableDimensions");
    Shape predictionsShape = predictions.asOutput().shape();
    int predictionsRank = predictionsShape.numDimensions();
    Shape labelsShape = labels.asOutput().shape();
    int labelsRank = labelsShape.numDimensions();

    if (predictionsRank != Shape.UNKNOWN_SIZE || labelsRank != Shape.UNKNOWN_SIZE) {
      // Use static rank.
      int rankDiff = predictionsRank - labelsRank;
      if (rankDiff == expectedRankDiff + 1 && Shape.isCompatible(predictionsShape.size(-1), 1)) {
        predictions = tf.squeeze(predictions);
      } else if (rankDiff == expectedRankDiff - 1 && Shape.isCompatible(labelsShape.size(-1), 1)) {
        labels = tf.squeeze(labels);
      }
      return new LossTuple<>(labels, predictions);
    }
    // Use dynamic rank.

    // TODO Operand<TInt32> rankDiff = tf.math.sub(tf.rank(predictions), tf.rank(labels));
    if (predictionsRank == Shape.UNKNOWN_SIZE && Shape.isCompatible(predictionsShape.size(-1), 1)) {
      /*
       * TODO, if we ever get a select that does lazy evaluation, but for now do the tf.squeeze
       * predictions = tf.select( tf.math.equal(tf.constant(expectedRankDiff+1),rankDiff ),
       * tf.squeeze(predictions, Squeeze.axis(Arrays.asList(-1L))), predictions ); *
       */
      predictions = tf.squeeze(predictions, Squeeze.axis(Collections.singletonList(-1L)));
    }
    if (labelsRank == Shape.UNKNOWN_SIZE && Shape.isCompatible(labelsShape.size(-1), 1)) {
      /*
       * TODO, if we ever get a select that does lazy evaluation labels = tf.select(
       * tf.math.equal(tf.constant(expectedRankDiff+1),rankDiff ), tf.squeeze(labels,
       * Squeeze.axis(Arrays.asList(-1L))), predictions ); *
       */
      labels = tf.squeeze(labels, Squeeze.axis(Collections.singletonList(-1L)));
    }
    return new LossTuple<>(labels, predictions);
  }

  /**
   * Computes the weighted loss
   *
   * @param tf the TensorFlow Ops
   * @param loss the unweighted loss
   * @param reduction the type of reduction
   * @param sampleWeight the sample weight, if null then this defaults to one.
   * @param <T> the data type of the loss
   * @return the weighted loss
   */
  public static <T extends TNumber> Operand<T> computeWeightedLoss(
      Ops tf, Operand<T> loss, Reduction reduction, Operand<T> sampleWeight) {
    DataType<T> dataType = loss.asOutput().dataType();
    if (sampleWeight == null) {
      sampleWeight = tf.dtypes.cast(tf.constant(1), dataType);
    }
    LossTuple<T> result = squeezeOrExpandDimensions(tf, null, loss, sampleWeight);
    loss = result.getTarget();
    sampleWeight = result.getSampleWeights();

    Operand<T> weighted_losses = tf.math.mul(loss, tf.dtypes.cast(sampleWeight, dataType));
    loss = reduceWeightedLoss(tf, weighted_losses, reduction);
    return tf.dtypes.cast(loss, dataType);
  }

  /**
   * Reduces the weighted loss based on the reduction type
   *
   * @param tf the TensorFlow Ops
   * @param weightedLoss the weighted loss
   * @param reduction the type of reduction
   * @param <T> the data type of the weighted loss
   * @return the reduced weighted loss
   */
  private static <T extends TNumber> Operand<T> reduceWeightedLoss(
      Ops tf, Operand<T> weightedLoss, Reduction reduction) {
    Operand<T> loss;
    if (reduction == Reduction.NONE) {
      loss = weightedLoss;
    } else {
      loss =
          tf.reduceSum(weightedLoss, allAxis(tf, weightedLoss), ReduceSum.keepDims(Boolean.FALSE));
      if (reduction == Reduction.AUTO || reduction == Reduction.SUM_OVER_BATCH_SIZE) {
        loss = safeMean(tf, loss, weightedLoss.asOutput().shape().size());
      }
    }
    return loss;
  }

  /**
   * Computes a safe mean of the losses.
   *
   * @param tf the TensorFlow Ops
   * @param losses </code>Operand</code> whose elements contain individual loss measurements.
   * @param numElements The number of measurable elements in <code>losses</code>.
   * @param <T> the data type of the losses
   * @return A scalar representing the mean of <code>losses</code>. If <code>numElements</code> is
   *     zero, then zero is returned.
   */
  public static <T extends TNumber> Operand<T> safeMean(
      Ops tf, Operand<T> losses, long numElements) {
    Operand<T> totalLoss = tf.reduceSum(losses, allAxis(tf, losses));
    return tf.math.divNoNan(
        totalLoss, tf.dtypes.cast(tf.constant(numElements), losses.asOutput().dataType()));
  }

  /**
   * Gets a Constant integer array representing all the axes of the operand.
   *
   * @param tf the TensorFlow Ops
   * @param op the TensorFlow Ops
   * @param <T> the type of Operand
   * @return a Constant that represents all the axes of the operand.
   */
  public static <T extends TNumber> Operand<TInt32> allAxis(Ops tf, Operand<T> op) {
    int rank = op.asOutput().shape().numDimensions();
    if (rank != Shape.UNKNOWN_SIZE) {
      int[] axes = new int[rank];
      for (int i = 0; i < rank; i++) {
        axes[i] = i;
      }
      return tf.constant(axes);
    } else {
      return tf.range(tf.constant(0), tf.rank(op), tf.constant(1));
    }
  }
}
