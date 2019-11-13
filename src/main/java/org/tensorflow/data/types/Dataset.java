package org.tensorflow.data.types;

import org.tensorflow.EagerSession;
import org.tensorflow.ExecutionEnvironment;
import org.tensorflow.Graph;
import org.tensorflow.op.Ops;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;

/**
 * Represents a potentially large list of independent elements (samples),
 * and allows iteration and transformations to be performed across
 * these elements.
 */
public interface Dataset<U> extends Iterable<U> {
    /**
     * Groups elements of this dataset into batches.
     *
     * @param batchSize     The number of desired elements per batch
     * @param dropLastBatch Whether to leave out the final batch if
     *                      it has fewer than `batchSize` elements.
     * @return A batched Dataset
     */
    BatchDataset<U> batch(long batchSize, boolean dropLastBatch);
    default Dataset<U> batch(long batchSize) {
        return batch(batchSize, true);
    }

    /**
     * Maps a function over elements of this dataset.
     * @param transform A transform function to call on each element of this dataset
     * @return A new dataset, transformed via `transform`
     */
    <V> Dataset<V> map(BiFunction<Ops, U, V> transform);

    /**
     * Filters elements of this dataset according to a predicate.
     * @param predicate A predicate function indicating which elements to keep.
     * @return A new dataset, filtered via `predicate`
     */
    Dataset<U> filter(BiFunction<Ops, U, Boolean> predicate);

    long size();
    int numOperands();
    ExecutionEnvironment environment();

    @Override
    default Spliterator<U> spliterator() {
        return Spliterators.spliterator(iterator(), size(), 0);
    }

    default boolean isEager() {
        return environment() instanceof EagerSession;
    }

    default boolean isGraph() {
        return environment() instanceof Graph;
    }
}