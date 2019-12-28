package org.tensorflow.data;

import org.tensorflow.*;
import org.tensorflow.data.impl.BatchDataset;
import org.tensorflow.data.impl.TensorSliceDataset;
import org.tensorflow.op.Ops;
import org.tensorflow.op.data.AnonymousIterator;
import org.tensorflow.op.data.MakeIterator;
import org.tensorflow.tools.Shape;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a potentially large list of independent elements (samples), and
 * allows iteration and transformations to be performed across these elements.
 */
public abstract class Dataset implements Iterable<List<Output<?>>> {

  public static void main(String[] args) {

  }
  protected Ops tf;

  public Dataset(Ops tf) {
    if (Objects.isNull(tf)) throw new IllegalArgumentException("Ops accessor cannot be null.");
    this.tf = tf;
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
  public Pair<Operation, List<Output<?>>> makeOneShotIterator() {
    if (!(tf.scope().env() instanceof Graph)) {
      throw new UnsupportedOperationException("One shot iterator should only be used in Graph mode.");
    }
    List<DataType<?>> outputTypes = getOutputTypes();
    List<Shape> outputShapes = getOutputShapes();
    Operand<?> iterator = tf.data.iterator(
        "graphIteratorSharedName", "graphIteratorContainer", outputTypes, outputShapes);

    MakeIterator makeIterator = tf.data.makeIterator(getVariant(), iterator);
    List<Output<?>> components = tf.data.iteratorGetNext(iterator, outputTypes, outputShapes).components();

    return Pair.of(makeIterator.op(), components);
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
  public abstract List<DataType<?>> getOutputTypes();

  /**
   * Get a list of shapes for each component of this dataset.
   */
  public abstract List<Shape> getOutputShapes();


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
}