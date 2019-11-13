package org.tensorflow.data.types;

import org.tensorflow.EagerSession;
import org.tensorflow.op.Ops;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.stream.StreamSupport;

public abstract class MapDataset<T, U> implements Dataset<U> {
    EagerSession eagerSessionRef;
    Dataset<T> dataset;
    BiFunction<Ops, T, U> transform;

    public MapDataset(EagerSession eagerSessionRef, Dataset<T> dataset, BiFunction<Ops, T, U> transform) {
        this.eagerSessionRef = eagerSessionRef;
        this.dataset = dataset;
        this.transform = transform;
    }

    @Override
    public Iterator<U> iterator() {
        Ops tf = Ops.create(eagerSessionRef);
        return StreamSupport.stream(dataset.spliterator(), false)
                .map(x -> transform.apply(tf, x))
                .iterator();
    }
}
