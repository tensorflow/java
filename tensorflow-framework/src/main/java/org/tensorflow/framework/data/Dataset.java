/*
 * Copyright 2020 The TensorFlow Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tensorflow.framework.data;

import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.framework.data.impl.*;
import org.tensorflow.op.Op;
import org.tensorflow.op.Ops;
import org.tensorflow.tools.Shape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a potentially large list of independent elements (samples), and allows iteration and
 * transformations to be performed across these elements.
 */
public abstract class Dataset implements Iterable<List<Operand<?>>> {
  protected Ops tf;
  private List<DataType<?>> outputTypes;
  private List<Shape> outputShapes;

  public Dataset(Ops tf, List<DataType<?>> outputTypes, List<Shape> outputShapes) {
    if (tf == null) {
      throw new IllegalArgumentException("Ops accessor cannot be null.");
    }

    if (outputTypes.size() != outputShapes.size()) {
      throw new IllegalArgumentException(
          "`outputTypes` and " + "`outputShapes` must have the same size.");
    }

    this.tf = tf;
    this.outputTypes = outputTypes;
    this.outputShapes = outputShapes;
  }

  /**
   * Groups elements of this dataset into batches.
   *
   * @param batchSize The number of desired elements per batch
   * @param dropLastBatch Whether to leave out the final batch if it has fewer than `batchSize`
   *     elements.
   * @return A batched Dataset
   */
  public final Dataset batch(long batchSize, boolean dropLastBatch) {

    List<Shape> batchOutputShapes = new ArrayList<>();
    outputShapes.forEach(s -> batchOutputShapes.add(s.prepend(-1)));

    return new BatchDataset(
        tf,
        this.getVariant(),
        tf.constant(batchSize),
        tf.constant(dropLastBatch),
        outputTypes,
        batchOutputShapes);
  }

  /**
   * Groups elements of this dataset into batches. Includes the last batch, even if it has fewer
   * than `batchSize` elements.
   *
   * @param batchSize The number of desired elements per batch
   * @return A batched Dataset
   */
  public final Dataset batch(long batchSize) {
    return batch(batchSize, false);
  }

  /**
   * Returns a new `Dataset` which skips `count` initial elements from this dataset
   *
   * @param count The number of elements to `skip` to form the new dataset.
   * @return A new Dataset with `count` elements removed.
   */
  public final Dataset skip(long count) {
    return new SkipDataset(
        tf, this.getVariant(), tf.constant(count), this.getOutputTypes(), this.getOutputShapes());
  }

  /**
   * Returns a new `Dataset` with only the first `count` elements from this dataset.
   *
   * @param count The number of elements to "take" from this dataset.
   * @return A new Dataset containing the first `count` elements from this dataset.
   */
  public final Dataset take(long count) {
    return new TakeDataset(
        tf, this.getVariant(), tf.constant(count), this.getOutputTypes(), this.getOutputShapes());
  }

  /**
   * Creates an iterator which iterates through all batches of this Dataset in an eager fashion.
   * Each batch is a list of components, returned as `Output` objects.
   *
   * <p>This method enables for-each iteration through batches when running in eager mode. For Graph
   * mode batch iteration, see `makeOneShotIterator`.
   *
   * @return an Iterator through batches of this dataset.
   */
  @Override
  public Iterator<List<Operand<?>>> iterator() {
    return makeOneShotIterator().iterator();
  }

  /**
   * Creates a `DatasetIterator` that can be used to iterate over elements of this dataset.
   *
   * <p>This iterator will have to be initialized with a call to `iterator.makeInitializer(Dataset)`
   * before elements can be retreived in a loop.
   *
   * @return A new `DatasetIterator` based on this dataset's structure.
   */
  public DatasetIterator makeInitializeableIterator() {
    DatasetIterator iterator = DatasetIterator.fromStructure(tf, outputTypes, outputShapes);
    iterator.makeInitializer(this);
    return iterator;
  }

  /**
   * Creates a `DatasetIterator` that can be used to iterate over elements of this dataset. Using
   * `makeOneShotIterator` ensures that the iterator is automatically initialized on this dataset.
   * skips In graph mode, the initializer op will be added to the Graph's intitializer list, which
   * must be run via `tf.init()`:
   *
   * <p>Ex:
   *
   * <pre>
   *     try (Session session = new Session(graph) {
   *         // Immediately run initializers
   *         session.run(tf.init());
   *     }
   * </pre>
   *
   * <p>In eager mode, the initializer will be run automatically as a result of this call.
   *
   * @return A new `DatasetIterator` based on this dataset's structure.
   */
  public DatasetIterator makeOneShotIterator() {
    DatasetIterator iterator = makeInitializeableIterator();
    Op initializer = iterator.makeInitializer(this);
    if (tf.scope().env().isGraph()) tf.initAdd(initializer);
    return iterator;
  }

  /**
   * Creates an in-memory `Dataset` whose elements are slices of the given tensors. Each element of
   * this dataset will be a List<Operand<?>>, representing slices (e.g. batches) of the provided
   * tensors.
   *
   * @param tf Ops Accessor
   * @param tensors A list of Operand<?> representing components of this dataset (e.g. features,
   *     labels)
   * @param outputTypes A list of `DataType` objects representing the data type of each component of
   *     this dataset.
   * @return A new `Dataset`
   */
  public static Dataset fromTensorSlices(
      Ops tf, List<Operand<?>> tensors, List<DataType<?>> outputTypes) {
    return new TensorSliceDataset(tf, tensors, outputTypes);
  }

  public static Dataset tfRecordDataset(
      Ops tf, String filename, String compressionType, long bufferSize) {
    return new TFRecordDataset(
        tf, tf.constant(filename), tf.constant(compressionType), tf.constant(bufferSize));
  }

  public static Dataset textLineDataset(
      Ops tf, String filename, String compressionType, long bufferSize) {
    return new TextLineDataset(
        tf, tf.constant(filename), tf.constant(compressionType), tf.constant(bufferSize));
  }

  /** Get the variant tensor representing this dataset. */
  public abstract Operand<?> getVariant();

  /** Get a list of output types for each component of this dataset. */
  public List<DataType<?>> getOutputTypes() {
    return this.outputTypes;
  }

  /** Get a list of shapes for each component of this dataset. */
  public List<Shape> getOutputShapes() {
    return this.outputShapes;
  }

  public Ops getOpsInstance() {
    return this.tf;
  }
}
