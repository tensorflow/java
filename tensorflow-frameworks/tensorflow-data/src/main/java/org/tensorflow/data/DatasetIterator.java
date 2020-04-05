package org.tensorflow.data;

import org.tensorflow.DataType;
import org.tensorflow.Graph;
import org.tensorflow.Operand;
import org.tensorflow.Output;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.tools.Shape;

import java.util.Iterator;
import java.util.List;

class DatasetIterator {
    public static final String EMPTY_SHARED_NAME = "";

    private Ops tf;

    private Operand<?> iteratorResource;
    private Op initializer;

    private List<DataType<?>> outputTypes;
    private List<Shape> outputShapes;

    public DatasetIterator(Ops tf, Operand<?> iteratorResource,
                           Op initializer,
                           List<DataType<?>> outputTypes,
                           List<Shape> outputShapes) {

        this.tf = tf;
        this.iteratorResource = iteratorResource;
        this.initializer = initializer;
        this.outputTypes = outputTypes;
        this.outputShapes = outputShapes;
    }

    public DatasetIterator(Ops tf, Operand<?> iteratorResource,
                           List<DataType<?>> outputTypes,
                           List<Shape> outputShapes) {
        this.tf = tf;
        this.iteratorResource = iteratorResource;
        this.outputTypes = outputTypes;
        this.outputShapes = outputShapes;
    }

    public List<Output<?>> getNext() {
        return tf.data.iteratorGetNext(getIteratorResource(),
                getOutputTypes(), getOutputShapes()).components();
    }

    public Op makeInitializer(Dataset dataset) {

        if (tf.scope() != dataset.tf.scope()) {
            throw new IllegalArgumentException("Dataset must share the same" +
                    "scope as this iterator.");
        }

        if (!dataset.getOutputShapes().equals(getOutputShapes())
                || !dataset.getOutputTypes().equals(getOutputTypes())) {

            throw new IllegalArgumentException("Dataset structure (types, " +
                    "output shapes) must match this iterator.");
        }

        this.initializer = tf.data.makeIterator(dataset.getVariant(),
                getIteratorResource());

        return this.initializer;
    }

    public static DatasetIterator fromStructure(Ops tf,
                                                List<Shape> outputShapes,
                                                List<DataType<?>> outputTypes) {

        Operand<?> iteratorResource = tf.scope().env() instanceof Graph
                ? tf.data.iterator(EMPTY_SHARED_NAME, "", outputTypes, outputShapes)
                : tf.data.anonymousIterator(outputTypes, outputShapes).handle();

        return new DatasetIterator(tf, iteratorResource, outputTypes, outputShapes);
    }

    public Operand<?> getIteratorResource() {
        return iteratorResource;
    }

    public Op getInitializer() {
        return initializer;
    }

    public List<DataType<?>> getOutputTypes() {
        return outputTypes;
    }

    public List<Shape> getOutputShapes() {
        return outputShapes;
    }
}
