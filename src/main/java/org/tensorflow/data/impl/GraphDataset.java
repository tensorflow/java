package org.tensorflow.data.impl;

import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Graph;
import org.tensorflow.Operand;

public abstract class GraphDataset<U extends Operand<?>> extends Dataset<U> {
    public GraphDataset(Graph graph) {
        super(graph);
    }

    public abstract Iterable<U> makeOneShotIterator();
}
