//package org.tensorflow.data.impl;
//
//import org.tensorflow.ExecutionEnvironment;
//import org.tensorflow.Graph;
//import org.tensorflow.Operand;
//import org.tensorflow.Session;
//
//import java.util.List;
//
//public abstract class GraphDataset<U extends Operand<?>> extends Dataset<U> implements AutoCloseable {
//    public GraphDataset(Graph graph) {
//        super(graph);
//    }
//    public abstract Iterable<U> makeOneShotIterator();
//
//    // Configure Session Runner
//}
