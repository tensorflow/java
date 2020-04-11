package org.tensorflow.data;

import org.tensorflow.*;
import org.tensorflow.data.impl.BatchDataset;
import org.tensorflow.data.impl.SkipDataset;
import org.tensorflow.data.impl.TakeDataset;
import org.tensorflow.data.impl.TensorSliceDataset;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.tools.Shape;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a potentially large list of independent elements (samples), and
 * allows iteration and transformations to be performed across these elements.
 */
public abstract class Dataset implements Iterable<List<Output<?>>> {
    protected Ops tf;
    private List<DataType<?>> outputTypes;
    private List<Shape> outputShapes;

    public Dataset(Ops tf, List<DataType<?>> outputTypes, List<Shape> outputShapes) {
        if (Objects.isNull(tf)) {
            throw new IllegalArgumentException("Ops accessor cannot be null.");
        } else if (outputTypes.size() != outputShapes.size()) {
            throw new IllegalArgumentException("`outputTypes` and `outputShapes` must have the same size.");
        }

        this.tf = tf;
        this.outputTypes = outputTypes;
        this.outputShapes = outputShapes;
    }

    /**
     * Groups elements of this dataset into batches.
     *
     * @param batchSize     The number of desired elements per batch
     * @param dropLastBatch Whether to leave out the final batch if it has fewer
     *                      than `batchSize` elements.
     * @return A batched Dataset
     */
    public final Dataset batch(long batchSize, boolean dropLastBatch) {
        List<Shape> batchOutputShapes = getOutputShapes().stream()
                .map(s -> Shape.of(batchSize, s.asArray()))
                .collect(Collectors.toList());


        return new BatchDataset(tf, this.getVariant(), tf.constant(batchSize),
                tf.constant(dropLastBatch), this.getOutputTypes(), batchOutputShapes);
    }

    /**
     * Groups elements of this dataset into batches.
     * Includes the last batch, even if it has fewer than `batchSize` elements.
     *
     * @param batchSize The number of desired elements per batch
     * @return A batched Dataset
     */
    public final Dataset batch(long batchSize) {
        return batch(batchSize, false);
    }

    /**
     * Returns a new `Dataset` which skips `count` initial elements from this
     * dataset
     *
     * @param count The number of elements to `skip` to form the new dataset.
     * @return A new Dataset with `count` elements removed.
     */
    public final Dataset skip(long count) {
        return new SkipDataset(tf, this.getVariant(), tf.constant(count), this.getOutputTypes(), this.getOutputShapes());
    }

    /**
     * Returns a new `Dataset` with only the first `count` elements from this
     * dataset.
     *
     * @param count The number of elements to "take" from this dataset.
     * @return A new Dataset containing the first `count` elements from this dataset.
     */
    public final Dataset take(long count) {
        return new TakeDataset(tf, this.getVariant(), tf.constant(count), this.getOutputTypes(), this.getOutputShapes());
    }

    /**
     * Creates an iterator which iterates through all batches of this Dataset in an eager fashion.
     * Each batch is a list of components, returned as `Output` objects.
     * <p>
     * This method enables for-each iteration through batches when running
     * in eager mode. For Graph mode batch iteration, see `makeOneShotIterator`.
     *
     * @return an Iterator through batches of this dataset.
     */
    @Override
    public Iterator<List<Output<?>>> iterator() {

        if (!(tf.scope().env() instanceof EagerSession)) {
            throw new UnsupportedOperationException("Cannot iterate through a dataset in graph mode.");
        }

        DatasetIterator iterator = makeOneShotIterator();

        return new Iterator<List<Output<?>>>() {
            private DatasetOptional nextOptional = iterator.getNextAsOptional();

            @Override
            public boolean hasNext() {
                return nextOptional.hasValue().data().getBoolean();
            }

            @Override
            public List<Output<?>> next() {
                List<Output<?>> result = nextOptional.getValue();
                nextOptional = iterator.getNextAsOptional();
                return result;
            }
        };
    }

    /**
     * Creates a `DatasetIterator` that can be used to iterate
     * over elements of this dataset.
     *
     * This iterator will have to be initialized with a call
     * to `iterator.makeInitializer(Dataset)` before elements
     * can be retreived in a loop.
     *
     * @return A new `DatasetIterator` based on this dataset's structure.
     */
    public DatasetIterator makeInitializeableIterator() {
        return DatasetIterator
                .fromStructure(tf, outputTypes, outputShapes);
    }

    /**
     * Creates a `DatasetIterator` that can be used to iterate over
     * elements of this dataset. Using `makeOneShotIterator` ensures
     * that the iterator is
     * automatically initialized on this dataset.
     *skips
     * In graph mode, the initializer op will be added to the Graph's
     * intitializer list, which must be run via `tf.init()`:
     *
     * Ex:
     * <pre>
     *     try (Session session = new Session(graph) {
     *         // Immediately run initializers
     *         session.run(tf.init());
     *     }
     * </pre>
     *
     * In eager mode, the initializer will be run automatically as a result
     * of this call.
     *
     * @return A new `DatasetIterator` based on this dataset's structure.
     */
    public DatasetIterator makeOneShotIterator() {
        DatasetIterator iterator = makeInitializeableIterator();
        Op initializer = iterator.makeInitializer(this);
        if (tf.scope().env() instanceof Graph) tf.initAdd(initializer);
        return iterator;
    }

    /**
     * Creates an in-memory `Dataset` whose elements are slices of the given
     * tensors. Each element of this dataset will be a List<Output<?>>,
     * representing slices (e.g. batches) of the provided tensors.
     *
     * @param tf Ops Accessor
     * @param tensors A list of Operand<?> representing components of this
     *                dataset (e.g. features, labels)
     * @param outputTypes A list of `DataType` objects representing the data
     *                    type of each component of this dataset.
     * @return A new `Dataset`
     */
    public static Dataset fromTensorSlices(Ops tf, List<Operand<?>> tensors, List<DataType<?>> outputTypes) {
        return new TensorSliceDataset(tf, tensors, outputTypes);
    }

    /**
     * Get the variant tensor representing this dataset.
     */
    public abstract Operand<?> getVariant();

    /**
     * Get a list of output types for each component of this dataset.
     */
    public List<DataType<?>> getOutputTypes() {
        return this.outputTypes;
    }

    /**
     * Get a list of shapes for each component of this dataset.
     */
    public List<Shape> getOutputShapes() {
        return this.outputShapes;
    }
}