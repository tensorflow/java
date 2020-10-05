package org.tensorflow.framework.losses.impl;

import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Squeeze;
import org.tensorflow.types.family.TNumber;

import java.util.Arrays;

public class ConfusionMatrix {

    /**
     * Squeeze last dim if ranks differ from expected by exactly 1.
     *
     * @param tf the TensorFlowOps
     * @param labels Label values, a `Tensor` whose dimensions match
     * `predictions`.
     * @param predictions Predicted values, a `Tensor` of arbitrary dimensions.
     * @return `labels` and `predictions`, possibly with last dim squeezed.
     */
    public static <T extends TNumber> Tuple removeSqueezableDimensions(Ops tf, Operand<T> labels,
                                                   Operand<T> predictions) {
        return removeSqueezableDimensions(tf, labels, predictions, 0);
    }

    /**
     * Squeeze last dim if ranks differ from expected by exactly 1.
     *
     * @param tf the TensorFlowOps
     * @param labels Label values, a `Tensor` whose dimensions match
     * `predictions`.
     * @param predictions Predicted values, a `Tensor` of arbitrary dimensions.
     * @param expectedRankDiff Expected result of `rank(predictions) -
     * rank(labels)`.
     * @return `labels` and `predictions`, possibly with last dim squeezed.
     */
    public static <T extends TNumber> Tuple<T> removeSqueezableDimensions(Ops tf, Operand<T> labels,
                                                                          Operand<T> predictions, int expectedRankDiff) {

        tf = tf.withSubScope("removeSqueezableDimensions");
        Shape predictionsShape = predictions.asOutput().shape();
        int predictionsRank = predictionsShape.numDimensions();
        Shape labelsShape = labels.asOutput().shape();
        int labelsRank = labelsShape.numDimensions();

        if (predictionsRank != Shape.UNKNOWN_SIZE && labelsRank != Shape.UNKNOWN_SIZE) {
            // Use static rank.
            int rankDiff = predictionsRank - labelsRank;
            if (rankDiff == expectedRankDiff + 1 && Shape.isCompatible(predictionsShape.size(-1), 1)) {
                predictions = tf.squeeze(predictions);
            } else if (rankDiff == expectedRankDiff - 1 && Shape.isCompatible(labelsShape.size(-1), 1)) {
                labels = tf.squeeze(labels);
            }
            return new Tuple(labels, predictions);
        }
        // Use dynamic rank.
        Operand rankDiff = tf.math.sub(tf.rank(predictions), tf.rank(labels));
        if (predictionsRank == Shape.UNKNOWN_SIZE && Shape.isCompatible(predictionsShape.size(-1), 1)) {
            /**
             * TODO, if we ever get a select that does lazy evaluation, but for
             * now do the tf.squeeze predictions = tf.select(
             * tf.math.equal(tf.constant(expectedRankDiff+1),rankDiff ),
             * tf.squeeze(predictions, Squeeze.axis(Arrays.asList(-1L))),
             * predictions ); *
             */
            predictions = tf.squeeze(predictions, Squeeze.axis(Arrays.asList(-1L)));
        }
        if (labelsRank == Shape.UNKNOWN_SIZE && Shape.isCompatible(labelsShape.size(-1), 1)) {
            /**
             * TODO, if we ever get a select that does lazy evaluation labels =
             * tf.select( tf.math.equal(tf.constant(expectedRankDiff+1),rankDiff
             * ), tf.squeeze(labels, Squeeze.axis(Arrays.asList(-1L))),
             * predictions ); *
             */
            labels = tf.squeeze(labels, Squeeze.axis(Arrays.asList(-1L)));
        }
        return new Tuple<T>(labels, predictions,true);
    }

}
