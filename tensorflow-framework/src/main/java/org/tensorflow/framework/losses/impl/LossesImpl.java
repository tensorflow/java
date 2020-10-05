package org.tensorflow.framework.losses.impl;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Squeeze;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.family.TNumber;

import java.util.Arrays;

public class LossesImpl {

    /**
     * Squeeze or expand last dimension if needed.
     *
     * <ol type="1">
     *   <li>Squeezes last dim of `yPred` or `yTrue` if their rank differs by 1 (using
     *       `confusion_matrix.remove_squeezable_dimensions`).
     *   <li>Squeezes or expands last dim of `sample_weight` if its rank differs by 1 from the new
     *       rank of `yPred`. If `sample_weight` is scalar, it is kept scalar./li>
     * </ol>
     *
     * @param tf the TensorFlow Ops
     * @param predictions Predicted values, a `Tensor` of arbitrary dimensions.
     * @param predictions Optional label `Tensor` whose dimensions match `y_pred`.
     * @return Tuple of `y_pred`, `y_true` and `sample_weight`. Each of them possibly has the last
     *     dimension squeezed, `sample_weight` could be extended by one dimension. If `sample_weight`
     *     is null, (y_pred, y_true) is returned.
     */
    /**********  TODO need to move ConfusionMatrix to MathOps */
    public static <T extends TNumber> Tuple<T> squeezeOrExpandDimensions(Ops tf, Operand<T> labels, Operand<T> predictions) {
        return squeezeOrExpandDimensions(tf, labels, predictions, null);
    }

    /**
     * Squeeze or expand last dimension if needed. * *
     *
     * <ol type="1">
     *   <li>Squeezes last dim of `yPred` or `yTrue` if their rank differs by 1 (using *
     *       `confusion_matrix.remove_squeezable_dimensions`). *
     *   <li>Squeezes or expands last dim of `sample_weight` if its rank differs by 1 from the new *
     *       rank of `yPred`. If `sample_weight` is scalar, it is kept scalar./li> *
     * </ol>
     *
     * @param tf the TensorFlow Ops
     * @param predictions Predicted values, a `Tensor` of arbitrary dimensions.
     * @param labels Optional label `Tensor` whose dimensions match `y_pred`.
     * @param sampleWeight Optional weight scalar or `Tensor` whose dimensions match `y_pred`.
     * @return Tuple of `y_pred`, `y_true` and `sample_weight`. Each of them possibly has the last
     *     dimension squeezed, `sample_weight` could be extended by one dimension. If `sample_weight`
     *     is null, (y_pred, y_true) is returned.
     */
    /**********  TODO need to move ConfusionMatrix to MathOps  **/
    public static <T extends TNumber> Tuple<T> squeezeOrExpandDimensions(
            Ops tf, Operand<T> labels, Operand<T> predictions, Operand<T> sampleWeight) {
        Tuple<T> tuple = new Tuple<>(labels, predictions, true);
        Shape predictionsShape = predictions.asOutput().shape();
        long ypredRank = predictionsShape.numDimensions();

        if (labels != null) {
            Shape labelsShape = labels.asOutput().shape();
            long ytrueRank = labelsShape.numDimensions();
            if (ytrueRank != Shape.UNKNOWN_SIZE && ypredRank != Shape.UNKNOWN_SIZE) {
                // Use static rank for `y_true` and `y_pred`.
                if (ypredRank - ytrueRank != 1 || predictionsShape.size(-1) == 1) {
                    // y_true, y_pred = confusion_matrix.remove_squeezable_dimensions(y_true, y_pred)
                    tuple = ConfusionMatrix.removeSqueezableDimensions(tf, labels, predictions);
                }
            } else { // use dynamic rank
                tuple = ConfusionMatrix.removeSqueezableDimensions(tf, labels, predictions);
            }
        }
        if (sampleWeight == null) {
            return tuple;
        }
        Shape weightsShape = sampleWeight.asOutput().shape();
        long weightsRank = weightsShape.numDimensions();
        if (weightsRank == 0) { // scalar
            return new Tuple(labels, predictions, sampleWeight, true);
        }

        if (ypredRank != Shape.UNKNOWN_SIZE && weightsRank != Shape.UNKNOWN_SIZE) {

            if (weightsRank - ypredRank == 1) {
                sampleWeight = tf.squeeze(sampleWeight);
            } else if (ypredRank - weightsRank == 1) {
                sampleWeight = tf.expandDims(sampleWeight, tf.constant(-1L));
            }
            return new Tuple(labels, predictions, sampleWeight, true);
        }
        // Use dynamic rank.
        Operand<TInt32> weightsRankTensor = tf.rank(sampleWeight);
        Operand<TInt32> rankDiff = tf.math.sub(weightsRankTensor, tf.rank(predictions));
        sampleWeight =
                tf.select(
                        tf.math.equal(weightsRankTensor, tf.constant(0)),
                        sampleWeight,
                        maybeAdjustWeights(tf, sampleWeight, rankDiff));
        return new Tuple(labels, predictions, sampleWeight, true);
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
                tf.squeeze(sampleWeight, Squeeze.axis(Arrays.asList(-1L))),
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

}
