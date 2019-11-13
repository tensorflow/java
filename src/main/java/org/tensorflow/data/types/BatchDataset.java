package org.tensorflow.data.types;

import org.tensorflow.EagerSession;
import org.tensorflow.op.Ops;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

public abstract class BatchDataset<U> implements Dataset<U> {
    private EagerSession eagerSessionRef;
    private Dataset<U> dataset;
    private long batchSize;

    public BatchDataset(EagerSession eagerSessionRef, Dataset<U> dataset, long batchSize) {
        this.eagerSessionRef = eagerSessionRef;
        this.dataset = dataset;
        this.batchSize = batchSize;
    }

    public abstract U flatten(Ops tf, Iterable<U> batchElements);

    @Override
    public long size() {
        return dataset.size() / batchSize;
    }

    @Override
    public Iterator<U> iterator() {
        return new Iterator<U>() {
            Ops tf = Ops.create(eagerSessionRef);
            Spliterator<U> spliterator = dataset.spliterator();
            long current = 0;

            @Override
            public boolean hasNext() {
                return current < size();
            }

            @Override
            public U next() {
                // Fetch 1 batch of elements
                List<U> batchElements = new ArrayList<>();
                for (int i = 0; i < batchSize; i++) {
                    spliterator.tryAdvance(batchElements::add);
                }

                // Condense batch elements to a single batch
                return flatten(tf, batchElements);
            }
        };
    }
}
