package org.tensorflow.data.impl;

import org.tensorflow.Operand;
import org.tensorflow.tools.Shape;
import org.tensorflow.data.Dataset;
import org.tensorflow.data.Utils;
import org.tensorflow.op.Ops;

import java.util.List;
import java.util.stream.Collectors;

public class TensorSliceDataset extends Dataset {
    private org.tensorflow.op.data.TensorSliceDataset dataset;
    private List<Shape> outputShapes;
    private List<Class<?>> outputTypes;

    public TensorSliceDataset(Ops tf, List<Operand<?>> components, List<Class<?>> outputTypes) {
        if (!(components.size() == outputTypes.size())) {
            throw new IllegalArgumentException("Lists `tensors` and `dtypes` must have the same number of elements.");
        }

        List<Shape> outputShapes = components.stream().map(c -> Utils.tail(c.asOutput().shape()))
                .collect(Collectors.toList());

        this.outputTypes = outputTypes;
        this.outputShapes = outputShapes;
        this.dataset = tf.data.tensorSliceDataset(components, outputShapes);
    }

    @Override
    public Operand<?> getVariant() {
        return dataset;
    }

    @Override
    public List<Class<?>> getOutputTypes() {
        return outputTypes;
    }

    @Override
    public List<Shape> getOutputShapes() {
        return outputShapes;
    }
}
