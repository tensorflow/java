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
package org.tensorflow.op.kotlin

import org.tensorflow.DataType
import org.tensorflow.Operand
import org.tensorflow.ndarray.Shape
import org.tensorflow.op.Scope
import org.tensorflow.op.`data`.AnonymousIterator
import org.tensorflow.op.`data`.BatchDataset
import org.tensorflow.op.`data`.CSVDataset
import org.tensorflow.op.`data`.ConcatenateDataset
import org.tensorflow.op.`data`.DeleteIterator
import org.tensorflow.op.`data`.DeserializeIterator
import org.tensorflow.op.`data`.Iterator
import org.tensorflow.op.`data`.IteratorGetNext
import org.tensorflow.op.`data`.IteratorGetNextAsOptional
import org.tensorflow.op.`data`.IteratorGetNextSync
import org.tensorflow.op.`data`.IteratorToStringHandle
import org.tensorflow.op.`data`.MakeIterator
import org.tensorflow.op.`data`.OptionalFromValue
import org.tensorflow.op.`data`.OptionalGetValue
import org.tensorflow.op.`data`.OptionalHasValue
import org.tensorflow.op.`data`.OptionalNone
import org.tensorflow.op.`data`.RangeDataset
import org.tensorflow.op.`data`.RepeatDataset
import org.tensorflow.op.`data`.SerializeIterator
import org.tensorflow.op.`data`.SkipDataset
import org.tensorflow.op.`data`.TakeDataset
import org.tensorflow.op.`data`.TensorSliceDataset
import org.tensorflow.op.`data`.TextLineDataset
import org.tensorflow.op.`data`.TfRecordDataset
import org.tensorflow.op.`data`.ZipDataset
import org.tensorflow.types.TBool
import org.tensorflow.types.TInt64
import org.tensorflow.types.TString

/**
 * An API for building {@code data} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class DataOps(
  /**
   * Get the parent {@link KotlinOps} object.
   */
  public val ops: KotlinOps
) {
  public val java: org.tensorflow.op.DataOps = ops.java.data

  /**
   * Returns the current {@link Scope scope} of this API
   */
  public val scope: Scope = ops.scope

  public val experimental: DataExperimentalOps = DataExperimentalOps(ops)

  public fun anonymousIterator(outputTypes: List<DataType<*>>, outputShapes: List<Shape>):
      AnonymousIterator = java.anonymousIterator(outputTypes, outputShapes)

  public fun batchDataset(
    inputDataset: Operand<*>,
    batchSize: Operand<TInt64>,
    dropRemainder: Operand<TBool>,
    outputTypes: List<DataType<*>>,
    outputShapes: List<Shape>,
    vararg options: BatchDataset.Options
  ): BatchDataset = java.batchDataset(inputDataset, batchSize, dropRemainder, outputTypes,
      outputShapes, *options)

  public fun cSVDataset(
    filenames: Operand<TString>,
    compressionType: Operand<TString>,
    bufferSize: Operand<TInt64>,
    header: Operand<TBool>,
    fieldDelim: Operand<TString>,
    useQuoteDelim: Operand<TBool>,
    naValue: Operand<TString>,
    selectCols: Operand<TInt64>,
    recordDefaults: Iterable<Operand<*>>,
    outputShapes: List<Shape>
  ): CSVDataset = java.cSVDataset(filenames, compressionType, bufferSize, header, fieldDelim,
      useQuoteDelim, naValue, selectCols, recordDefaults, outputShapes)

  public fun concatenateDataset(
    inputDataset: Operand<*>,
    anotherDataset: Operand<*>,
    outputTypes: List<DataType<*>>,
    outputShapes: List<Shape>
  ): ConcatenateDataset = java.concatenateDataset(inputDataset, anotherDataset, outputTypes,
      outputShapes)

  public fun deleteIterator(handle: Operand<*>, deleter: Operand<*>): DeleteIterator =
      java.deleteIterator(handle, deleter)

  public fun deserializeIterator(resourceHandle: Operand<*>, serialized: Operand<*>):
      DeserializeIterator = java.deserializeIterator(resourceHandle, serialized)

  public fun iterator(
    sharedName: String,
    container: String,
    outputTypes: List<DataType<*>>,
    outputShapes: List<Shape>
  ): Iterator = java.iterator(sharedName, container, outputTypes, outputShapes)

  public fun iteratorGetNext(
    iterator: Operand<*>,
    outputTypes: List<DataType<*>>,
    outputShapes: List<Shape>
  ): IteratorGetNext = java.iteratorGetNext(iterator, outputTypes, outputShapes)

  public fun iteratorGetNextAsOptional(
    iterator: Operand<*>,
    outputTypes: List<DataType<*>>,
    outputShapes: List<Shape>
  ): IteratorGetNextAsOptional = java.iteratorGetNextAsOptional(iterator, outputTypes, outputShapes)

  public fun iteratorGetNextSync(
    iterator: Operand<*>,
    outputTypes: List<DataType<*>>,
    outputShapes: List<Shape>
  ): IteratorGetNextSync = java.iteratorGetNextSync(iterator, outputTypes, outputShapes)

  public fun iteratorToStringHandle(resourceHandle: Operand<*>): IteratorToStringHandle =
      java.iteratorToStringHandle(resourceHandle)

  public fun makeIterator(dataset: Operand<*>, iterator: Operand<*>): MakeIterator =
      java.makeIterator(dataset, iterator)

  public fun optionalFromValue(components: Iterable<Operand<*>>): OptionalFromValue =
      java.optionalFromValue(components)

  public fun optionalGetValue(
    optional: Operand<*>,
    outputTypes: List<DataType<*>>,
    outputShapes: List<Shape>
  ): OptionalGetValue = java.optionalGetValue(optional, outputTypes, outputShapes)

  public fun optionalHasValue(optional: Operand<*>): OptionalHasValue =
      java.optionalHasValue(optional)

  public fun optionalNone(): OptionalNone = java.optionalNone()

  public fun rangeDataset(
    start: Operand<TInt64>,
    stop: Operand<TInt64>,
    step: Operand<TInt64>,
    outputTypes: List<DataType<*>>,
    outputShapes: List<Shape>
  ): RangeDataset = java.rangeDataset(start, stop, step, outputTypes, outputShapes)

  public fun repeatDataset(
    inputDataset: Operand<*>,
    count: Operand<TInt64>,
    outputTypes: List<DataType<*>>,
    outputShapes: List<Shape>
  ): RepeatDataset = java.repeatDataset(inputDataset, count, outputTypes, outputShapes)

  public fun serializeIterator(resourceHandle: Operand<*>, vararg
      options: SerializeIterator.Options): SerializeIterator =
      java.serializeIterator(resourceHandle, *options)

  public fun skipDataset(
    inputDataset: Operand<*>,
    count: Operand<TInt64>,
    outputTypes: List<DataType<*>>,
    outputShapes: List<Shape>
  ): SkipDataset = java.skipDataset(inputDataset, count, outputTypes, outputShapes)

  public fun takeDataset(
    inputDataset: Operand<*>,
    count: Operand<TInt64>,
    outputTypes: List<DataType<*>>,
    outputShapes: List<Shape>
  ): TakeDataset = java.takeDataset(inputDataset, count, outputTypes, outputShapes)

  public fun tensorSliceDataset(components: Iterable<Operand<*>>, outputShapes: List<Shape>):
      TensorSliceDataset = java.tensorSliceDataset(components, outputShapes)

  public fun textLineDataset(
    filenames: Operand<TString>,
    compressionType: Operand<TString>,
    bufferSize: Operand<TInt64>
  ): TextLineDataset = java.textLineDataset(filenames, compressionType, bufferSize)

  public fun tfRecordDataset(
    filenames: Operand<TString>,
    compressionType: Operand<TString>,
    bufferSize: Operand<TInt64>
  ): TfRecordDataset = java.tfRecordDataset(filenames, compressionType, bufferSize)

  public fun zipDataset(
    inputDatasets: Iterable<Operand<*>>,
    outputTypes: List<DataType<*>>,
    outputShapes: List<Shape>
  ): ZipDataset = java.zipDataset(inputDatasets, outputTypes, outputShapes)
}
