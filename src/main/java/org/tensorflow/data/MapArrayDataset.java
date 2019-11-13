package org.tensorflow.data;

import org.tensorflow.EagerSession;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Operand;
import org.tensorflow.data.types.OperandArrayDataset;
import org.tensorflow.op.Ops;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.stream.StreamSupport;

public class MapArrayDataset<T, U> extends OperandArrayDataset<U> {
    EagerSession env;
    OperandArrayDataset<T> dataset;
    BiFunction<Ops, Operand<T>[], Operand<U>[]> transform;

    public MapArrayDataset(EagerSession env, OperandArrayDataset<T> dataset, BiFunction<Ops, Operand<T>[], Operand<U>[]> transform) {
        this.env = env;
        this.dataset = dataset;
        this.transform = transform;
    }

    @Override
    public Iterator<Operand<U>[]> iterator() {
        Ops tf = Ops.create(environment());
        return StreamSupport
                .stream(dataset.spliterator(), false)
                .map(x -> transform.apply(tf, x))
                .iterator();
    }

    @Override
    public ExecutionEnvironment environment() {
        return env;
    }

    @Override
    public long size() {
        return dataset.size();
    }

    @Override
    public int numOperands() {
        return dataset.numOperands();
    }
}
