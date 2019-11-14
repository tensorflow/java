package org.tensorflow.data.impl;

import org.tensorflow.EagerSession;
import org.tensorflow.Operand;
import org.tensorflow.data.Utils;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EagerArrayDataset<T> extends EagerDataset<Operand<T>[]> {
    private Operand<T>[] data;
    private final long batchSize;

    EagerArrayDataset(EagerArrayDataset<T> other, long newBatchSize) {
        super((EagerSession) other.environment());
        this.data = other.data;
        this.batchSize = newBatchSize;
    }

    public EagerArrayDataset(EagerSession session, Constant<T> firstTensor, Constant<T>... tensors) {
        super(session);
        batchSize = 1;
        Utils.verifyTensorFirstDimensionsMatch(firstTensor, tensors);
        this.data = (Operand<T>[]) new Operand[tensors.length + 1];
        this.data[0] = firstTensor;
        System.arraycopy(tensors, 0, data, 1, tensors.length);
    }

    @Override
    public EagerArrayDataset<T> batch(long batchSize, boolean dropLastBatch) {
        return new EagerArrayDataset<>(this, batchSize);
    }

    @Override
    public <V> Dataset<V> map(Function<Operand<T>[], V> transform) {
        return null;
    }

    @Override
    public Dataset<Operand<T>[]> filter(Predicate<Operand<T>[]> predicate) {
        return null;
    }

    @Override
    public long size() {
        return data[0].asOutput().shape().size(0);
    }

    @Override
    public Iterator<Operand<T>[]> iterator() {
        Ops tf = Ops.create(environment());
        return new Iterator<Operand<T>[]>() {
            long current = 0;

            @Override
            public boolean hasNext() {
                return current < (size() / batchSize);
            }

            @Override
            public Operand<T>[] next() {
                Operand<T>[] next = Arrays.stream(data)
                        .map(t -> Utils.sliceOperand(tf, t, current, batchSize))
                        .collect(Collectors.toList())
                        .toArray((Operand<T>[]) new Operand[data.length]);
                current++;
                return next;
            }
        };
    }
}
