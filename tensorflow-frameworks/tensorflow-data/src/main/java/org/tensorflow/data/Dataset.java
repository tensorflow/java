package org.tensorflow.data;

import org.tensorflow.*;
import org.tensorflow.tools.Shape;
import org.tensorflow.data.impl.BatchDataset;
import org.tensorflow.data.impl.TensorSliceDataset;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.data.AnonymousIterator;
import org.tensorflow.op.data.MakeIterator;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a potentially large list of independent elements (samples), and
 * allows iteration and transformations to be performed across these elements.
 */
public abstract class Dataset {

    /**
     * Groups elements of this dataset into batches.
     *
     * @param batchSize     The number of desired elements per batch
     * @param dropLastBatch Whether to leave out the final batch if it has fewer
     *                      than `batchSize` elements.
     * @return A batched Dataset
     */
    public final Dataset batch(Ops tf, long batchSize, boolean dropLastBatch) {
        List<Shape> batchOutputShapes = getOutputShapes().stream()
                .map(s -> Shape.make(Utils.array(batchSize, s.asArray())))
                .collect(Collectors.toList());

        return new BatchDataset(tf, this.getVariant(), Constant.create(tf.scope(), batchSize),
                Constant.create(tf.scope(), dropLastBatch), this.getOutputTypes(), batchOutputShapes);
    }

    public final Dataset batch(Ops tf, long batchSize) {
        return batch(tf, batchSize, true);
    }

    // /**
    // * Maps a function over elements of this dataset.
    // *
    // * @param transform A transform function to call on each element of this
    // dataset
    // * @return A new dataset, transformed via `transform`
    // */
    // public abstract <V> Dataset<V> map(Function<U, V> transform);
    //
    // /**
    // * Filters elements of this dataset according to a predicate.
    // * @param predicate A predicate function indicating which elements to keep.
    // * @return A new dataset, filtered via `predicate`
    // */
    // public abstract Dataset<U> filter(Predicate<U> predicate);

    public Iterable<List<Output<?>>> asIterable(Ops tf) {
        return () -> iterator(tf);
    }

    public Iterator<List<Output<?>>> iterator(Ops tf) {

        if (!(tf.scope().env() instanceof EagerSession)) {
            throw new UnsupportedOperationException("Cannot iterate through a dataset in graph mode.");
        }

        Operand<?> dataset = getVariant();
        AnonymousIterator anonymousIterator = tf.data.anonymousIterator(getOutputTypes(), getOutputShapes());

        tf.data.makeIterator(dataset, anonymousIterator);

        return new Iterator<List<Output<?>>>() {
            private List<Output<?>> tryNext = getNext();

            private List<Output<?>> getNext() {
                try {
                    return tf.data.iteratorGetNext(anonymousIterator, getOutputTypes(), getOutputShapes()).components();
                } catch (IndexOutOfBoundsException e) {
                    return null;
                }
            }

            @Override
            public boolean hasNext() {
                return tryNext != null;
            }

            @Override
            public List<Output<?>> next() {
                List<Output<?>> result = tryNext;
                tryNext = getNext();
                return result;
            }
        };
    }

    public Pair<Operation, List<Output<?>>> makeOneShotIterator(Ops tf) {
        if (!(tf.scope().env() instanceof Graph)) {
            throw new UnsupportedOperationException("Use graph iterator components only in Graph mode.");
        }
        List<DataType<?>> outputTypes = getOutputTypes();
        List<Shape> outputShapes = getOutputShapes();
        Operand<?> iterator = tf.data.iterator("graphIteratorSharedName", "graphIteratorContainer", outputTypes, outputShapes);

        MakeIterator makeIterator = tf.data.makeIterator(getVariant(), iterator);
        List<Output<?>> components = tf.data.iteratorGetNext(iterator, outputTypes, outputShapes).components();

        return Pair.of(makeIterator.op(), components);
    }

    public static TensorSliceDataset fromTensorSlices(Ops tf, List<Operand<?>> slices, List<Class<?>> outputTypes) {
        return new TensorSliceDataset(tf, slices, outputTypes);
    }

    /**
     * Get the variant tensor representing this dataset.
     */
    public abstract Operand<?> getVariant();

    /**
     * Get a list of output types for each component of this dataset.
     */
    public abstract List<DataType<?>> getOutputTypes();

    /**
     * Get a list of shapes for each component of this dataset.
     */
    public abstract List<Shape> getOutputShapes();
}