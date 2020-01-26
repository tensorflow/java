package org.tensorflow.data;

import org.tensorflow.*;
import org.tensorflow.data.impl.*;
import org.tensorflow.op.Ops;
import org.tensorflow.op.data.AnonymousIterator;
import org.tensorflow.op.data.MakeIterator;
import org.tensorflow.tools.Shape;
import org.tensorflow.utils.Tuple2;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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
        .map(s -> Shape.make(Utils.array(batchSize, s.asArray())))
        .collect(Collectors.toList());
    return new BatchDataset(tf, this.getVariant(), tf.constant(batchSize),
        tf.constant(dropLastBatch), this.getOutputTypes(), batchOutputShapes);
  }

  /**
   * Groups elements of this dataset into batches.
   * Leaves out the last batch if it has fewer than `batchSize` elements.
   *
   * @param batchSize The number of desired elements per batch
   * @return A batched Dataset
   */
  public final Dataset batch(long batchSize) {
    return batch(batchSize, true);
  }

  /**
   * Creates new `Dataset` skips `count` initial elements from this dataset
   *
   * @param count The number of elements to `skip` to form the new dataset.
   * @return A new Dataset with `count` elements removed.
   */
  public final Dataset skip(long count) {
    return new SkipDataset(tf, this.getVariant(), tf.constant(count), this.getOutputTypes(), this.getOutputShapes());
  }

  /**
   * Creates new `Dataset` with the first `count` elements from this dataset.
   *
   * @param count The number of elements to "take" from this dataset.
   * @return A new Dataset containing the first `count` elements from this dataset.
   */tf
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

    Operand<?> dataset = getVariant();
    AnonymousIterator anonymousIterator = tf.data.anonymousIterator(getOutputTypes(), getOutputShapes());

    tf.data.makeIterator(dataset, anonymousIterator.handle());

    return new Iterator<List<Output<?>>>() {
      private List<Output<?>> tryNext = getNext();

      private List<Output<?>> getNext() {
        try {
          return tf.data.iteratorGetNext(anonymousIterator.handle(), getOutputTypes(), getOutputShapes()).components();
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

  /**
   * Return the necessary components to iterate through batches of this
   * dataset in Graph mode.
   * <p>
   * This method returns a Pair whose first element is a MakeIterator operation
   * that must be run first in its own session to create the iterator internally.
   * <p>
   * The second element in the pair is a list of Output objects. In sequential
   * calls to session.run() in which these (or child) nodes are fetched, the batches
   * are already loaded into these objects.
   *
   * @return A Pair whose first element is a MakeIterator Operation, and whose
   * second element is a list batch components.
   */
  public OneShotIterator makeOneShotIterator() {
    if (!(tf.scope().env() instanceof Graph)) {
      throw new UnsupportedOperationException("One shot iterator should only be used in Graph mode.");
    }
    List<DataType<?>> outputTypes = getOutputTypes();
    List<Shape> outputShapes = getOutputShapes();
    Operand<?> iterator = tf.data.iterator("graphIteratorSharedName", "graphIteratorContainer", outputTypes, outputShapes);

    MakeIterator makeIterator = tf.data.makeIterator(getVariant(), iterator);
    List<Output<?>> components = tf.data.iteratorGetNext(iterator, outputTypes, outputShapes).components();

    return new OneShotIterator(makeIterator, components);
  }

  public static TensorSliceDataset fromTensorSlices(Ops tf, List<Operand<?>> slices, List<DataType<?>> outputTypes) {
    return new TensorSliceDataset(tf, slices, outputTypes);
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