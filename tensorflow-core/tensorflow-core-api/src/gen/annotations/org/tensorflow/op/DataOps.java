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
import org.tensorflow.op.data.CSVDataset;
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
  public final DataExperimentalOps experimental;

  private final Scope scope;

  private final Ops ops;

  DataOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
    experimental = new DataExperimentalOps(ops);
  }

  /**
   * A container for an iterator resource.
   *
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of AnonymousIterator
   */
  public AnonymousIterator anonymousIterator(List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    return AnonymousIterator.create(scope, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that batches `batch_size` elements from `input_dataset`.
   *
   * @param inputDataset
   * @param batchSize A scalar representing the number of elements to accumulate in a batch.
   * @param dropRemainder A scalar representing whether the last batch should be dropped in case its size
   *  is smaller than desired.
   * @param outputTypes
   * @param outputShapes
   * @param options carries optional attributes values
   * @return a new instance of BatchDataset
   */
  public BatchDataset batchDataset(Operand<?> inputDataset, Operand<TInt64> batchSize,
      Operand<TBool> dropRemainder, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, BatchDataset.Options... options) {
    return BatchDataset.create(scope, inputDataset, batchSize, dropRemainder, outputTypes, outputShapes, options);
  }

  /**
   *
   * @param filenames
   * @param compressionType
   * @param bufferSize
   * @param header
   * @param fieldDelim
   * @param useQuoteDelim
   * @param naValue
   * @param selectCols
   * @param recordDefaults
   * @param outputShapes
   * @return a new instance of CSVDataset
   */
  public CSVDataset cSVDataset(Operand<TString> filenames, Operand<TString> compressionType,
      Operand<TInt64> bufferSize, Operand<TBool> header, Operand<TString> fieldDelim,
      Operand<TBool> useQuoteDelim, Operand<TString> naValue, Operand<TInt64> selectCols,
      Iterable<Operand<?>> recordDefaults, List<Shape> outputShapes) {
    return CSVDataset.create(scope, filenames, compressionType, bufferSize, header, fieldDelim, useQuoteDelim, naValue, selectCols, recordDefaults, outputShapes);
  }

  /**
   * Creates a dataset that concatenates `input_dataset` with `another_dataset`.
   *
   * @param inputDataset
   * @param anotherDataset
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of ConcatenateDataset
   */
  public ConcatenateDataset concatenateDataset(Operand<?> inputDataset, Operand<?> anotherDataset,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return ConcatenateDataset.create(scope, inputDataset, anotherDataset, outputTypes, outputShapes);
  }

  /**
   * A container for an iterator resource.
   *
   * @param handle A handle to the iterator to delete.
   * @param deleter A variant deleter.
   * @return a new instance of DeleteIterator
   */
  public DeleteIterator deleteIterator(Operand<?> handle, Operand<?> deleter) {
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
  public DeserializeIterator deserializeIterator(Operand<?> resourceHandle, Operand<?> serialized) {
    return DeserializeIterator.create(scope, resourceHandle, serialized);
  }

  /**
   *
   * @param sharedName
   * @param container
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of Iterator
   */
  public Iterator iterator(String sharedName, String container,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return Iterator.create(scope, sharedName, container, outputTypes, outputShapes);
  }

  /**
   * Gets the next output from the given iterator .
   *
   * @param iterator
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of IteratorGetNext
   */
  public IteratorGetNext iteratorGetNext(Operand<?> iterator,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return IteratorGetNext.create(scope, iterator, outputTypes, outputShapes);
  }

  /**
   * Gets the next output from the given iterator as an Optional variant.
   *
   * @param iterator
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of IteratorGetNextAsOptional
   */
  public IteratorGetNextAsOptional iteratorGetNextAsOptional(Operand<?> iterator,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return IteratorGetNextAsOptional.create(scope, iterator, outputTypes, outputShapes);
  }

  /**
   * Gets the next output from the given iterator.
   *  <p>
   *  This operation is a synchronous version IteratorGetNext. It should only be used
   *  in situations where the iterator does not block the calling thread, or where
   *  the calling thread is not a member of the thread pool used to execute parallel
   *  operations (e.g. in eager mode).
   *
   * @param iterator
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of IteratorGetNextSync
   */
  public IteratorGetNextSync iteratorGetNextSync(Operand<?> iterator,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return IteratorGetNextSync.create(scope, iterator, outputTypes, outputShapes);
  }

  /**
   * Converts the given `resource_handle` representing an iterator to a string.
   *
   * @param resourceHandle A handle to an iterator resource.
   * @return a new instance of IteratorToStringHandle
   */
  public IteratorToStringHandle iteratorToStringHandle(Operand<?> resourceHandle) {
    return IteratorToStringHandle.create(scope, resourceHandle);
  }

  /**
   * Makes a new iterator from the given `dataset` and stores it in `iterator`.
   *  <p>
   *  This operation may be executed multiple times. Each execution will reset the
   *  iterator in `iterator` to the first element of `dataset`.
   *
   * @param dataset
   * @param iterator
   * @return a new instance of MakeIterator
   */
  public MakeIterator makeIterator(Operand<?> dataset, Operand<?> iterator) {
    return MakeIterator.create(scope, dataset, iterator);
  }

  /**
   * Constructs an Optional variant from a tuple of tensors.
   *
   * @param components
   * @return a new instance of OptionalFromValue
   */
  public OptionalFromValue optionalFromValue(Iterable<Operand<?>> components) {
    return OptionalFromValue.create(scope, components);
  }

  /**
   * Returns the value stored in an Optional variant or raises an error if none exists.
   *
   * @param optional
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of OptionalGetValue
   */
  public OptionalGetValue optionalGetValue(Operand<?> optional,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return OptionalGetValue.create(scope, optional, outputTypes, outputShapes);
  }

  /**
   * Returns true if and only if the given Optional variant has a value.
   *
   * @param optional
   * @return a new instance of OptionalHasValue
   */
  public OptionalHasValue optionalHasValue(Operand<?> optional) {
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
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of RangeDataset
   */
  public RangeDataset rangeDataset(Operand<TInt64> start, Operand<TInt64> stop,
      Operand<TInt64> step, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return RangeDataset.create(scope, start, stop, step, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that emits the outputs of `input_dataset` `count` times.
   *
   * @param inputDataset
   * @param count A scalar representing the number of times that `input_dataset` should
   *  be repeated. A value of `-1` indicates that it should be repeated infinitely.
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of RepeatDataset
   */
  public RepeatDataset repeatDataset(Operand<?> inputDataset, Operand<TInt64> count,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return RepeatDataset.create(scope, inputDataset, count, outputTypes, outputShapes);
  }

  /**
   * Converts the given `resource_handle` representing an iterator to a variant tensor.
   *
   * @param resourceHandle A handle to an iterator resource.
   * @param options carries optional attributes values
   * @return a new instance of SerializeIterator
   */
  public SerializeIterator serializeIterator(Operand<?> resourceHandle,
      SerializeIterator.Options... options) {
    return SerializeIterator.create(scope, resourceHandle, options);
  }

  /**
   * Creates a dataset that skips `count` elements from the `input_dataset`.
   *
   * @param inputDataset
   * @param count A scalar representing the number of elements from the `input_dataset`
   *  that should be skipped.  If count is -1, skips everything.
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of SkipDataset
   */
  public SkipDataset skipDataset(Operand<?> inputDataset, Operand<TInt64> count,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return SkipDataset.create(scope, inputDataset, count, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that contains `count` elements from the `input_dataset`.
   *
   * @param inputDataset
   * @param count A scalar representing the number of elements from the `input_dataset`
   *  that should be taken. A value of `-1` indicates that all of `input_dataset`
   *  is taken.
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of TakeDataset
   */
  public TakeDataset takeDataset(Operand<?> inputDataset, Operand<TInt64> count,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return TakeDataset.create(scope, inputDataset, count, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that emits each dim-0 slice of `components` once.
   *
   * @param components
   * @param outputShapes
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
   *  compression), (ii) "ZLIB", or (iii) "GZIP".
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
   *  compression), (ii) "ZLIB", or (iii) "GZIP".
   * @param bufferSize A scalar representing the number of bytes to buffer. A value of
   *  0 means no buffering will be performed.
   * @return a new instance of TfRecordDataset
   */
  public TfRecordDataset tfRecordDataset(Operand<TString> filenames,
      Operand<TString> compressionType, Operand<TInt64> bufferSize) {
    return TfRecordDataset.create(scope, filenames, compressionType, bufferSize);
  }

  /**
   * Creates a dataset that zips together `input_datasets`.
   *  <p>
   *  The elements of the resulting dataset are created by zipping corresponding
   *  elements from each of the input datasets.
   *  <p>
   *  The size of the resulting dataset will match the size of the smallest input
   *  dataset, and no error will be raised if input datasets have different sizes.
   *
   * @param inputDatasets List of `N` variant Tensors representing datasets to be zipped together.
   * @param outputTypes
   * @param outputShapes
   * @return a new instance of ZipDataset
   */
  public ZipDataset zipDataset(Iterable<Operand<?>> inputDatasets,
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
