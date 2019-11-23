//package org.tensorflow.data.impl;
//
//import org.tensorflow.ExecutionEnvironment;
//
//import java.util.function.Function;
//import java.util.function.Predicate;
//
///**
// * Represents a potentially large list of independent elements (samples),
// * and allows iteration and transformations to be performed across
// * these elements.
// */
//public abstract class Dataset<U> {
//    private ExecutionEnvironment env;
//
//    public Dataset(ExecutionEnvironment env) {
//        this.env = env;
//    }
//
//    public final ExecutionEnvironment environment() {
//        return env;
//    }
//
//    /**
//     * Groups elements of this dataset into batches.
//     *
//     * @param batchSize     The number of desired elements per batch
//     * @param dropLastBatch Whether to leave out the final batch if
//     *                      it has fewer than `batchSize` elements.
//     * @return A batched Dataset
//     */
//    public abstract Dataset<U> batch(long batchSize, boolean dropLastBatch);
//    public Dataset<U> batch(long batchSize) {
//        return batch(batchSize, true);
//    }
//
//    /**
//     * Maps a function over elements of this dataset.
//     *
//     * @param transform A transform function to call on each element of this dataset
//     * @return A new dataset, transformed via `transform`
//     */
//    public abstract  <V> Dataset<V> map(Function<U, V> transform);
//
//    /**
//     * Filters elements of this dataset according to a predicate.
//     * @param predicate A predicate function indicating which elements to keep.
//     * @return A new dataset, filtered via `predicate`
//     */
//    public abstract Dataset<U> filter(Predicate<U> predicate);
//
//    public abstract long size();
//}