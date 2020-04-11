package org.tensorflow.data;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.Ops;
import org.tensorflow.tools.Shape;
import org.tensorflow.types.TBool;

import java.util.List;

/**
 * An optional represents the result of a dataset getNext
 * operation that may fail, when the end of the dataset
 * has been reached.
 */
public class DatasetOptional {
    Ops tf;
    Operand<?> optionalVariant;
    List<DataType<?>> outputTypes;
    List<Shape> outputShapes;

    DatasetOptional(
            Ops tf,
            Operand<?> optionalVariant,
            List<DataType<?>> outputTypes,
            List<Shape> outputShapes) {
        this.tf = tf;
        this.optionalVariant = optionalVariant;
        this.outputTypes = outputTypes;
        this.outputShapes = outputShapes;
    }

    /**
     * Whether this optional has a value.
     */
    public Operand<TBool> hasValue() {
        return tf.data.optionalHasValue(optionalVariant).hasValue();
    }

    /**
     * Returns the value of the dataset element represented
     * by this optional, if it exists.
     */
    public List<Output<?>> getValue() {
        return tf.data.optionalGetValue(
                optionalVariant,
                outputTypes,
                outputShapes).components();
    }

    public static DatasetOptional fromComponents(
            Ops tf, List<Operand<?>> components,
            List<DataType<?>> outputTypes,
            List<Shape> outputShapes) {
        Operand<?> optionalVariant = tf.data.optionalFromValue(components);
        return new DatasetOptional(
                tf, optionalVariant, outputTypes, outputShapes);
    }
}
