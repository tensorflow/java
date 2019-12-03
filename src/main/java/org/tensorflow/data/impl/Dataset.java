package org.tensorflow.data.impl;

import org.tensorflow.*;
import org.tensorflow.data.Pair;
import org.tensorflow.data.Utils;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.data.AnonymousIterator;
import org.tensorflow.op.data.IteratorGetNext;
import org.tensorflow.op.data.MakeIterator;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a potentially large list of independent elements (samples),
 * and allows iteration and transformations to be performed across
 * these elements.
 */
public abstract class Dataset implements Iterable<List<Output<?>>> {
    private Ops opRef;

    public Dataset(Ops tf) {
        this.opRef = tf;
    }

    /**
     * Groups elements of this dataset into batches.
     *
     * @param batchSize     The number of desired elements per batch
     * @param dropLastBatch Whether to leave out the final batch if
     *                      it has fewer than `batchSize` elements.
     * @return A batched Dataset
     */
    public final Dataset batch(long batchSize, boolean dropLastBatch) {

        List<Shape> batchOutputShapes = outputShapes().stream()
                .map(s -> Shape.make(batchSize, Utils.shapeArray(s)))
                .collect(Collectors.toList());

        return new BatchDataset(this.opRef,
                this.variant(),
                Constant.create(opRef.scope(), batchSize),
                Constant.create(opRef.scope(), dropLastBatch),
                this.outputTypes(),
                batchOutputShapes);
    }

    public final Dataset batch(long batchSize) {
        return batch(batchSize, true);
    }

//    /**
//     * Maps a function over elements of this dataset.
//     *
//     * @param transform A transform function to call on each element of this dataset
//     * @return A new dataset, transformed via `transform`
//     */
//    public abstract <V> Dataset<V> map(Function<U, V> transform);
//
//    /**
//     * Filters elements of this dataset according to a predicate.
//     * @param predicate A predicate function indicating which elements to keep.
//     * @return A new dataset, filtered via `predicate`
//     */
//    public abstract Dataset<U> filter(Predicate<U> predicate);


    @Override
    public Iterator<List<Output<?>>> iterator() {

        if (!(opRef.scope().env() instanceof EagerSession)) {
            throw new UnsupportedOperationException("Cannot iterate through a dataset in graph mode.");
        }

        Operand<?> dataset = variant();
        AnonymousIterator anonymousIterator = AnonymousIterator.create(opRef.scope(), outputTypes(), outputShapes());

        MakeIterator.create(opRef.scope(), dataset, anonymousIterator);

        return new Iterator<List<Output<?>>>() {
            private List<Output<?>> tryNext = getNext();

            private List<Output<?>> getNext() {
                try {
                    return IteratorGetNext.create(opRef.scope(),
                            anonymousIterator,
                            outputTypes(),
                            outputShapes())
                            .components();
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

    public Pair<MakeIterator, List<Output<?>>> makeOneShotIterator() {
        if (!(opRef.scope().env() instanceof Graph)) {
            throw new UnsupportedOperationException("Use graph iterator components only in Graph mode.");
        }
        List<Class<?>> outputTypes = outputTypes();
        List<Shape> outputShapes = outputShapes();
        Operand<?> iterator = iterator = org.tensorflow.op.core.Iterator.create(
                opRef.scope(),
                "graphIteratorSharedName",
                "graphIteratorContainer",
                outputTypes,
                outputShapes);

        MakeIterator makeIterator = MakeIterator.create(
                opRef.scope(), variant(), iterator);
        List<Output<?>> components = IteratorGetNext.create(
                opRef.scope(), iterator, outputTypes, outputShapes).components();

        return Pair.of(makeIterator, components);
    }

    public static TensorSliceDataset fromTensorSlices(Ops tf, List<Operand<?>> slices, List<Class<?>> outputTypes) {
        return new TensorSliceDataset(tf, slices, outputTypes);
    }

    /**
     * Get the variant tensor representing this dataset.
     */
    public abstract Operand<?> variant();

    /**
     * Get a list of output types for each component of this dataset.
     */
    public abstract List<Class<?>> outputTypes();

    /**
     * Get a list of shapes for each component of this dataset.
     */
    public abstract List<Shape> outputShapes();
}