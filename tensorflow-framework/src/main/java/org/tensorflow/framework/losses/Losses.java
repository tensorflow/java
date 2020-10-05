package org.tensorflow.framework.losses;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.framework.losses.impl.Tuple;
import org.tensorflow.op.Ops;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

public class Losses {

    /**
     *
     * @param tf
     * @param yTrue
     * @param yPred
     * @param <T>
     * @return
     */
    public static <T extends TNumber, U extends TType> Operand<T> mean_absolute_error(Ops tf, Operand<U> labels, Operand<T> predictions) {
        Operand<T> tLabels = tf.dtypes.cast(labels, predictions.asOutput().dataType());
        Tuple<T> ops = squeezeOrExpandDimensions(tf, labels, predictions, null);
        predictions = ops.getPredictions();
        tLabels = ops.getLabels();
        return mean(tf, tf.math.abs(tf.math.sub(yPred, yTrue)), tf.constant(-1));
    }


}
