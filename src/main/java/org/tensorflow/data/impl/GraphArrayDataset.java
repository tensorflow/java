//package org.tensorflow.data.impl;
//
//import org.tensorflow.*;
//import org.tensorflow.op.Ops;
//import org.tensorflow.op.core.Placeholder;
//import org.tensorflow.op.core.Slice;
//import org.tensorflow.op.core.Variable;
//
//import java.util.Arrays;
//
//public class GraphArrayDataset<T> extends GraphDataset<T> implements AutoCloseable {
//    private Class<T> dtype;
//
//    private Tensor<T>[] dataTensors;
//    private Placeholder<T>[] dataPlaceholders;
//    private Slice<T>[] batchOperands;
//
//    private Placeholder<Long>[] batchStart;
//    private Placeholder<Long>[] batchSize;
//
//    private boolean built = false;
//
//    @SafeVarargs
//    public GraphArrayDataset(Graph graph, Class<T> dtype, Tensor<T> firstTensor, Tensor<T>... tensors) {
//        super(graph);
//        this.dtype = dtype;
//
//        // Check first dimension matches
//        long matchDim = firstTensor.shape()[0];
//
//        for (Tensor<T> t : tensors) {
//            if (t.shape()[0] != matchDim) {
//                throw new IllegalArgumentException(
//                        "All dataTensors in a tensor frame must have equal first dimension.");
//            }
//        }
//
//        // Record Tensor Objects
//        this.dataTensors = (Tensor<T>[]) new Tensor[tensors.length + 1];
//        this.dataTensors[0] = firstTensor;
//        System.arraycopy(tensors, 0, this.dataTensors, 1, tensors.length);
//    }
//
//    public int length() {
//        return this.dataTensors.length;
//    }
//
//    public long size() {
//        return this.dataTensors[0].shape()[0];
//    }
//
//    public void build(Ops tf) {
//        // Create Placeholders (will be filled by dataTensors before graph is run)
//        this.dataPlaceholders = new Placeholder[this.length()];
//        for (int i = 0; i < this.length(); ++i) {
//            this.dataPlaceholders[i] =
//                    tf.placeholder(this.dtype, Placeholder.shape(getShape(this.dataTensors[i].shape())));
//        }
//
//        // Placeholder representing batch start and size selectors.
//        this.batchStart = new Placeholder[this.length()];
//        this.batchSize = new Placeholder[this.length()];
//
//        for (int i = 0; i < this.length(); i++) {
//            batchStart[i] = tf.placeholder(Long.class, Placeholder.shape(Shape.make(this.dataTensors[i].numDimensions())));
//            batchSize[i] = tf.placeholder(Long.class, Placeholder.shape(Shape.make(this.dataTensors[i].numDimensions())));
//        }
//
//        // Create batch slice operands
//        this.batchOperands = new Slice[this.length()];
//        for (int i = 0; i < this.length(); i++) {
//            batchOperands[i] = tf.slice(dataPlaceholders[i], batchStart[i], batchSize[i]);
//        }
//
//        this.built = true;
//    }
//
//    @Override
//    public Session.Runner feedSessionRunner(Session.Runner runner, long batch) {
//        // Feed Data Tensors
//        for (int i = 0; i < dataPlaceholders.length; i++) {
//            runner.feed(dataPlaceholders[i].asOutput(), dataTensors[i]);
//        }
//
//        // Feed Batch Selectors
//        for (int i = 0; i < this.length(); i++) {
//            long[] start = new long[dataTensors[i].numDimensions()];
//            Arrays.fill(start, 0);
//            start[0] = batch * this.batchSize;
//
//            long[] size = new long[dataTensors[i].numDimensions()];
//            Arrays.fill(size, -1);
//            size[0] = this.batchSize;
//
//            runner.feed(this.batchStart[i].asOutput(), Tensors.create(start));
//            runner.feed(this.batchSize[i].asOutput(), Tensors.create(size));
//        }
//
//        return runner;
//    }
//
//    public boolean isBuilt() {
//        return built;
//    }
//
//    public Tensor<T>[] getDataTensors() {
//        return dataTensors;
//    }
//
//    public Placeholder<T>[] getDataPlaceholders() {
//        return dataPlaceholders;
//    }
//
//    public Operand<T>[] getBatchOperands() {
//        return batchOperands;
//    }
//
//    /**
//     * Utility to construct a Shape from a long[]
//     */
//    private static Shape getShape(long... dims) {
//        assert dims.length > 0;
//
//        long head = dims[0];
//        long[] tail = new long[dims.length - 1];
//        System.arraycopy(dims, 1, tail, 0, dims.length - 1);
//
//        return Shape.make(head, tail);
//    }
//
//    @Override
//    public void close() {
//        for (Tensor<T> tensor : this.dataTensors) {
//            tensor.close();
//        }
//    }
//}