// Copyright 2020 The TensorFlow Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ==============================================================================
//
// This class has been generated, DO NOT EDIT!
//
package org.tensorflow.op;

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.data.AnonymousIterator;
import org.tensorflow.op.data.BatchDataset;
import org.tensorflow.op.data.ConcatenateDataset;
import org.tensorflow.op.data.DeleteIterator;
import org.tensorflow.op.data.DeserializeIterator;
import org.tensorflow.op.data.Iterator;
import org.tensorflow.op.data.IteratorGetNext;
import org.tensorflow.op.data.IteratorGetNextAsOptional;
import org.tensorflow.op.data.IteratorGetNextSync;
import org.tensorflow.op.data.IteratorToStringHandle;
import org.tensorflow.op.data.MakeIterator;
import org.tensorflow.op.data.OptionalFromValue;
import org.tensorflow.op.data.OptionalGetValue;
import org.tensorflow.op.data.OptionalHasValue;
import org.tensorflow.op.data.OptionalNone;
import org.tensorflow.op.data.RangeDataset;
import org.tensorflow.op.data.RepeatDataset;
import org.tensorflow.op.data.SerializeIterator;
import org.tensorflow.op.data.SkipDataset;
import org.tensorflow.op.data.TakeDataset;
import org.tensorflow.op.data.TensorSliceDataset;
import org.tensorflow.op.data.TextLineDataset;
import org.tensorflow.op.data.TfRecordDataset;
import org.tensorflow.op.data.ZipDataset;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code data} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class DataOps {
  private final Scope scope;

  private final Ops ops;

  DataOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * A container for an iterator resource.
   *
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of AnonymousIterator
   */
  public AnonymousIterator anonymousIterator(List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    return AnonymousIterator.create(scope, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that batches {@code batch_size} elements from {@code input_dataset}.
   *
   * @param inputDataset the inputDataset value
   * @param batchSize A scalar representing the number of elements to accumulate in a batch.
   * @param dropRemainder A scalar representing whether the last batch should be dropped in case its size
   *  is smaller than desired.
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @param options carries optional attribute values
   * @return a new instance of BatchDataset
   */
  public BatchDataset batchDataset(Operand<? extends TType> inputDataset, Operand<TInt64> batchSize,
      Operand<TBool> dropRemainder, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, BatchDataset.Options... options) {
    return BatchDataset.create(scope, inputDataset, batchSize, dropRemainder, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that concatenates {@code input_dataset} with {@code another_dataset}.
   *
   * @param inputDataset the inputDataset value
   * @param anotherDataset the anotherDataset value
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of ConcatenateDataset
   */
  public ConcatenateDataset concatenateDataset(Operand<? extends TType> inputDataset,
      Operand<? extends TType> anotherDataset, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    return ConcatenateDataset.create(scope, inputDataset, anotherDataset, outputTypes, outputShapes);
  }

  /**
   * A container for an iterator resource.
   *
   * @param handle A handle to the iterator to delete.
   * @param deleter A variant deleter.
   * @return a new instance of DeleteIterator
   */
  public DeleteIterator deleteIterator(Operand<? extends TType> handle,
      Operand<? extends TType> deleter) {
    return DeleteIterator.create(scope, handle, deleter);
  }

  /**
   * Converts the given variant tensor to an iterator and stores it in the given resource.
   *
   * @param resourceHandle A handle to an iterator resource.
   * @param serialized A variant tensor storing the state of the iterator contained in the
   *  resource.
   * @return a new instance of DeserializeIterator
   */
  public DeserializeIterator deserializeIterator(Operand<? extends TType> resourceHandle,
      Operand<? extends TType> serialized) {
    return DeserializeIterator.create(scope, resourceHandle, serialized);
  }

  /**
   * The IteratorV2 operation
   *
   * @param sharedName the value of the sharedName property
   * @param container the value of the container property
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of Iterator
   */
  public Iterator iterator(String sharedName, String container,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return Iterator.create(scope, sharedName, container, outputTypes, outputShapes);
  }

  /**
   * Gets the next output from the given iterator .
   *
   * @param iterator the iterator value
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of IteratorGetNext
   */
  public IteratorGetNext iteratorGetNext(Operand<? extends TType> iterator,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return IteratorGetNext.create(scope, iterator, outputTypes, outputShapes);
  }

  /**
   * Gets the next output from the given iterator as an Optional variant.
   *
   * @param iterator the iterator value
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of IteratorGetNextAsOptional
   */
  public IteratorGetNextAsOptional iteratorGetNextAsOptional(Operand<? extends TType> iterator,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return IteratorGetNextAsOptional.create(scope, iterator, outputTypes, outputShapes);
  }

  /**
   * Gets the next output from the given iterator.
   *  This operation is a synchronous version IteratorGetNext. It should only be used
   *  in situations where the iterator does not block the calling thread, or where
   *  the calling thread is not a member of the thread pool used to execute parallel
   *  operations (e.g. in eager mode).
   *
   * @param iterator the iterator value
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of IteratorGetNextSync
   */
  public IteratorGetNextSync iteratorGetNextSync(Operand<? extends TType> iterator,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return IteratorGetNextSync.create(scope, iterator, outputTypes, outputShapes);
  }

  /**
   * Converts the given {@code resource_handle} representing an iterator to a string.
   *
   * @param resourceHandle A handle to an iterator resource.
   * @return a new instance of IteratorToStringHandle
   */
  public IteratorToStringHandle iteratorToStringHandle(Operand<? extends TType> resourceHandle) {
    return IteratorToStringHandle.create(scope, resourceHandle);
  }

  /**
   * Makes a new iterator from the given {@code dataset} and stores it in {@code iterator}.
   *  This operation may be executed multiple times. Each execution will reset the
   *  iterator in {@code iterator} to the first element of {@code dataset}.
   *
   * @param dataset the dataset value
   * @param iterator the iterator value
   * @return a new instance of MakeIterator
   */
  public MakeIterator makeIterator(Operand<? extends TType> dataset,
      Operand<? extends TType> iterator) {
    return MakeIterator.create(scope, dataset, iterator);
  }

  /**
   * Constructs an Optional variant from a tuple of tensors.
   *
   * @param components the components value
   * @return a new instance of OptionalFromValue
   */
  public OptionalFromValue optionalFromValue(Iterable<Operand<?>> components) {
    return OptionalFromValue.create(scope, components);
  }

  /**
   * Returns the value stored in an Optional variant or raises an error if none exists.
   *
   * @param optional the optional value
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of OptionalGetValue
   */
  public OptionalGetValue optionalGetValue(Operand<? extends TType> optional,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return OptionalGetValue.create(scope, optional, outputTypes, outputShapes);
  }

  /**
   * Returns true if and only if the given Optional variant has a value.
   *
   * @param optional the optional value
   * @return a new instance of OptionalHasValue
   */
  public OptionalHasValue optionalHasValue(Operand<? extends TType> optional) {
    return OptionalHasValue.create(scope, optional);
  }

  /**
   * Creates an Optional variant with no value.
   *
   * @return a new instance of OptionalNone
   */
  public OptionalNone optionalNone() {
    return OptionalNone.create(scope);
  }

  /**
   * Creates a dataset with a range of values. Corresponds to python's xrange.
   *
   * @param start corresponds to start in python's xrange().
   * @param stop corresponds to stop in python's xrange().
   * @param step corresponds to step in python's xrange().
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of RangeDataset
   */
  public RangeDataset rangeDataset(Operand<TInt64> start, Operand<TInt64> stop,
      Operand<TInt64> step, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return RangeDataset.create(scope, start, stop, step, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that emits the outputs of {@code input_dataset} {@code count} times.
   *
   * @param inputDataset the inputDataset value
   * @param count A scalar representing the number of times that {@code input_dataset} should
   *  be repeated. A value of {@code -1} indicates that it should be repeated infinitely.
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of RepeatDataset
   */
  public RepeatDataset repeatDataset(Operand<? extends TType> inputDataset, Operand<TInt64> count,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return RepeatDataset.create(scope, inputDataset, count, outputTypes, outputShapes);
  }

  /**
   * Converts the given {@code resource_handle} representing an iterator to a variant tensor.
   *
   * @param resourceHandle A handle to an iterator resource.
   * @param options carries optional attribute values
   * @return a new instance of SerializeIterator
   */
  public SerializeIterator serializeIterator(Operand<? extends TType> resourceHandle,
      SerializeIterator.Options... options) {
    return SerializeIterator.create(scope, resourceHandle, options);
  }

  /**
   * Creates a dataset that skips {@code count} elements from the {@code input_dataset}.
   *
   * @param inputDataset the inputDataset value
   * @param count A scalar representing the number of elements from the {@code input_dataset}
   *  that should be skipped.  If count is -1, skips everything.
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of SkipDataset
   */
  public SkipDataset skipDataset(Operand<? extends TType> inputDataset, Operand<TInt64> count,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return SkipDataset.create(scope, inputDataset, count, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that contains {@code count} elements from the {@code input_dataset}.
   *
   * @param inputDataset the inputDataset value
   * @param count A scalar representing the number of elements from the {@code input_dataset}
   *  that should be taken. A value of {@code -1} indicates that all of {@code input_dataset}
   *  is taken.
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of TakeDataset
   */
  public TakeDataset takeDataset(Operand<? extends TType> inputDataset, Operand<TInt64> count,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return TakeDataset.create(scope, inputDataset, count, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that emits each dim-0 slice of {@code components} once.
   *
   * @param components the components value
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of TensorSliceDataset
   */
  public TensorSliceDataset tensorSliceDataset(Iterable<Operand<?>> components,
      List<Shape> outputShapes) {
    return TensorSliceDataset.create(scope, components, outputShapes);
  }

  /**
   * Creates a dataset that emits the lines of one or more text files.
   *
   * @param filenames A scalar or a vector containing the name(s) of the file(s) to be
   *  read.
   * @param compressionType A scalar containing either (i) the empty string (no
   *  compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
   * @param bufferSize A scalar containing the number of bytes to buffer.
   * @return a new instance of TextLineDataset
   */
  public TextLineDataset textLineDataset(Operand<TString> filenames,
      Operand<TString> compressionType, Operand<TInt64> bufferSize) {
    return TextLineDataset.create(scope, filenames, compressionType, bufferSize);
  }

  /**
   * Creates a dataset that emits the records from one or more TFRecord files.
   *
   * @param filenames A scalar or vector containing the name(s) of the file(s) to be
   *  read.
   * @param compressionType A scalar containing either (i) the empty string (no
   *  compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
   * @param bufferSize A scalar representing the number of bytes to buffer. A value of
   *  0 means no buffering will be performed.
   * @return a new instance of TfRecordDataset
   */
  public TfRecordDataset tfRecordDataset(Operand<TString> filenames,
      Operand<TString> compressionType, Operand<TInt64> bufferSize) {
    return TfRecordDataset.create(scope, filenames, compressionType, bufferSize);
  }

  /**
   * Creates a dataset that zips together {@code input_datasets}.
   *  The elements of the resulting dataset are created by zipping corresponding
   *  elements from each of the input datasets.
   *  <p>The size of the resulting dataset will match the size of the smallest input
   *  dataset, and no error will be raised if input datasets have different sizes.
   *
   * @param inputDatasets List of {@code N} variant Tensors representing datasets to be zipped together.
   * @param outputTypes the value of the outputTypes property
   * @param outputShapes the value of the outputShapes property
   * @return a new instance of ZipDataset
   */
  public ZipDataset zipDataset(Iterable<Operand<? extends TType>> inputDatasets,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return ZipDataset.create(scope, inputDatasets, outputTypes, outputShapes);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
