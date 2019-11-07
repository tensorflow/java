package org.tensorflow.data;

import org.tensorflow.*;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.core.Slice;

public class GraphModeTensorFrame<T> extends TensorFrame<T> implements GraphLoader<T>, AutoCloseable {
    private Graph graphRef;
    private Class<T> dtype;

    private Tensor<T>[] dataTensors;
    private Placeholder<T>[] dataPlaceholders;
    private Slice<T>[] batchOperands;

    private Placeholder<Long>[] batchStart;
    private Placeholder<Long>[] batchSize;

    private boolean built = false;

    @SafeVarargs
    public GraphModeTensorFrame(Graph graph, Class<T> dtype, Tensor<T> firstTensor, Tensor<T>... tensors) {
        this.graphRef = graph;
        this.dtype = dtype;

        // Check first dimension matches
        long matchDim = firstTensor.shape()[0];

        for (Tensor<T> t : tensors) {
            if (t.shape()[0] != matchDim) {
                throw new IllegalArgumentException(
                        "All dataTensors in a tensor frame must have equal first dimension.");
            }
        }

        // Record Tensor Objects
        this.dataTensors = (Tensor<T>[]) new Tensor[tensors.length + 1];
        this.dataTensors[0] = firstTensor;
        System.arraycopy(tensors, 0, this.dataTensors, 1, tensors.length);
    }

    public int length() {
        return this.dataTensors.length;
    }

    public long size() {
        return this.dataTensors[0].shape()[0];
    }

    public void build(Ops tf) {
        // Create Placeholders (will be filled by dataTensors before graph is run)
        this.dataPlaceholders = new Placeholder[this.length()];
        for (int i = 0; i < this.length(); ++i) {
            this.dataPlaceholders[i] =
                    tf.placeholder(this.dtype, Placeholder.shape(getShape(this.dataTensors[i].shape())));
        }

        // Placeholder representing batch start and size selectors.
        this.batchStart = new Placeholder[this.length()];
        this.batchSize = new Placeholder[this.length()];

        for (int i = 0; i < this.length(); i++) {
            batchStart[i] = tf.placeholder(Long.class, Placeholder.shape(Shape.make(this.dataTensors[i].numDimensions())));
            batchSize[i] = tf.placeholder(Long.class, Placeholder.shape(Shape.make(this.dataTensors[i].numDimensions())));
        }

        // Create batch slice operands
        this.batchOperands = new Slice[this.length()];
        for (int i = 0; i < this.length(); i++) {
            batchOperands[i] = tf.slice(dataPlaceholders[i], batchStart[i], batchSize[i]);
        }

        this.built = true;
    }

    @Override
    public Operand<T>[] getBatchOperands() {
        return this.batchOperands;
    }

    @Override
    public long numTensors() {
        return this.batchOperands.length;
    }

    @Override
    public long numElementsPerTensor() {
        return this.dataTensors[0].shape()[0];
    }

    public void feedBatchToSessionRunner(Ops tf, Session.Runner runner, long batchIndex, boolean fetchBatches) {
        // Feed Data Tensors
        for (int i = 0; i < dataPlaceholders.length; i++) {
            runner.feed(dataPlaceholders[i].asOutput(), dataTensors[i]);
        }

        // Feed Batch Selectors
        for (int i = 0; i < length(); i++) {
            long[] startSelector = Utils.batchStartSelector(tf, batchIndex * getBatchSize(), dataTensors[i].numDimensions());
            long[] sizeSelector = Utils.batchSizeSelector(tf, getBatchSize(), dataTensors[i].numDimensions());

            runner.feed(batchStart[i].asOutput(), Tensors.create(startSelector));
            runner.feed(batchSize[i].asOutput(), Tensors.create(sizeSelector));
        }

        if (fetchBatches) {
            // Fetch Sliced Batch Operands
            for (Operand<T> op : batchOperands) {
                runner.fetch(op);
            }
        }
    }

    public boolean isBuilt() {
        return built;
    }

    /**
     * Utility to construct a Shape from a long[]
     */
    private static Shape getShape(long... dims) {
        assert dims.length > 0;

        long head = dims[0];
        long[] tail = new long[dims.length - 1];
        System.arraycopy(dims, 1, tail, 0, dims.length - 1);

        return Shape.make(head, tail);
    }

    @Override
    public void close() {
        for (Tensor<T> tensor : this.dataTensors) {
            tensor.close();
        }
    }
}
