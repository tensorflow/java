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

import org.tensorflow.Operand
import org.tensorflow.ndarray.Shape
import org.tensorflow.op.Scope
import org.tensorflow.op.data.AnonymousIterator
import org.tensorflow.op.data.BatchDataset
import org.tensorflow.op.data.ConcatenateDataset
import org.tensorflow.op.data.DeleteIterator
import org.tensorflow.op.data.DeserializeIterator
import org.tensorflow.op.data.Iterator
import org.tensorflow.op.data.IteratorGetNext
import org.tensorflow.op.data.IteratorGetNextAsOptional
import org.tensorflow.op.data.IteratorGetNextSync
import org.tensorflow.op.data.IteratorToStringHandle
import org.tensorflow.op.data.MakeIterator
import org.tensorflow.op.data.OptionalFromValue
import org.tensorflow.op.data.OptionalGetValue
import org.tensorflow.op.data.OptionalHasValue
import org.tensorflow.op.data.OptionalNone
import org.tensorflow.op.data.RangeDataset
import org.tensorflow.op.data.RepeatDataset
import org.tensorflow.op.data.SerializeIterator
import org.tensorflow.op.data.SkipDataset
import org.tensorflow.op.data.TakeDataset
import org.tensorflow.op.data.TensorSliceDataset
import org.tensorflow.op.data.TextLineDataset
import org.tensorflow.op.data.TfRecordDataset
import org.tensorflow.op.data.ZipDataset
import org.tensorflow.types.TBool
import org.tensorflow.types.TInt64
import org.tensorflow.types.TString
import org.tensorflow.types.family.TType

/**
 * An API for building `data` operations as [Op][org.tensorflow.op.Op]s
 *
 * @see org.tensorflow.op.Ops
 */
public class DataOps(
    /**
     * Get the parent [KotlinOps] object.
     */
    public val ops: KotlinOps,
) {
    public val java: org.tensorflow.op.DataOps = ops.java.data

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    public val experimental: DataExperimentalOps = DataExperimentalOps(ops)

    /**
     * A container for an iterator resource.
     *
     * @param outputTypes
     * @param outputShapes
     * @return a new instance of AnonymousIterator
     * @see org.tensorflow.op.DataOps.anonymousIterator
     */
    public fun anonymousIterator(outputTypes: List<Class<out TType>>, outputShapes: List<Shape>):
        AnonymousIterator = java.anonymousIterator(
        outputTypes,
        outputShapes
    )

    /**
     * Creates a dataset that batches `batch_size` elements from `input_dataset`.
     *
     * @param inputDataset
     * @param batchSize A scalar representing the number of elements to accumulate in a batch.
     * @param dropRemainder A scalar representing whether the last batch should be dropped in case
     * its size
     *  is smaller than desired.
     * @param outputTypes
     * @param outputShapes
     * @param options carries optional attributes values
     * @return a new instance of BatchDataset
     * @see org.tensorflow.op.DataOps.batchDataset
     * @param parallelCopy @param parallelCopy
     */
    public fun batchDataset(
        inputDataset: Operand<*>,
        batchSize: Operand<TInt64>,
        dropRemainder: Operand<TBool>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        parallelCopy: Boolean? = null,
    ): BatchDataset = java.batchDataset(
        inputDataset,
        batchSize,
        dropRemainder,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            parallelCopy?.let { org.tensorflow.op.data.BatchDataset.parallelCopy(it) }
        ).toTypedArray()
    )

    /**
     * Creates a dataset that concatenates `input_dataset` with `another_dataset`.
     *
     * @param inputDataset
     * @param anotherDataset
     * @param outputTypes
     * @param outputShapes
     * @return a new instance of ConcatenateDataset
     * @see org.tensorflow.op.DataOps.concatenateDataset
     */
    public fun concatenateDataset(
        inputDataset: Operand<*>,
        anotherDataset: Operand<*>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
    ): ConcatenateDataset = java.concatenateDataset(
        inputDataset,
        anotherDataset,
        outputTypes,
        outputShapes
    )

    /**
     * A container for an iterator resource.
     *
     * @param handle A handle to the iterator to delete.
     * @param deleter A variant deleter.
     * @return a new instance of DeleteIterator
     * @see org.tensorflow.op.DataOps.deleteIterator
     */
    public fun deleteIterator(handle: Operand<*>, deleter: Operand<*>): DeleteIterator =
        java.deleteIterator(
            handle,
            deleter
        )

    /**
     * Converts the given variant tensor to an iterator and stores it in the given resource.
     *
     * @param resourceHandle A handle to an iterator resource.
     * @param serialized A variant tensor storing the state of the iterator contained in the
     *  resource.
     * @return a new instance of DeserializeIterator
     * @see org.tensorflow.op.DataOps.deserializeIterator
     */
    public fun deserializeIterator(resourceHandle: Operand<*>, serialized: Operand<*>):
        DeserializeIterator = java.deserializeIterator(
        resourceHandle,
        serialized
    )

    /**
     *
     * @param sharedName
     * @param container
     * @param outputTypes
     * @param outputShapes
     * @return a new instance of Iterator
     * @see org.tensorflow.op.DataOps.iterator
     */
    public fun iterator(
        sharedName: String,
        container: String,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
    ): Iterator = java.iterator(
        sharedName,
        container,
        outputTypes,
        outputShapes
    )

    /**
     * Gets the next output from the given iterator .
     *
     * @param iterator
     * @param outputTypes
     * @param outputShapes
     * @return a new instance of IteratorGetNext
     * @see org.tensorflow.op.DataOps.iteratorGetNext
     */
    public fun iteratorGetNext(
        iterator: Operand<*>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
    ): IteratorGetNext = java.iteratorGetNext(
        iterator,
        outputTypes,
        outputShapes
    )

    /**
     * Gets the next output from the given iterator as an Optional variant.
     *
     * @param iterator
     * @param outputTypes
     * @param outputShapes
     * @return a new instance of IteratorGetNextAsOptional
     * @see org.tensorflow.op.DataOps.iteratorGetNextAsOptional
     */
    public fun iteratorGetNextAsOptional(
        iterator: Operand<*>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
    ): IteratorGetNextAsOptional = java.iteratorGetNextAsOptional(
        iterator,
        outputTypes,
        outputShapes
    )

    /**
     * Gets the next output from the given iterator.
     *
     *  This operation is a synchronous version IteratorGetNext. It should only be used
     *  in situations where the iterator does not block the calling thread, or where
     *  the calling thread is not a member of the thread pool used to execute parallel
     *  operations (e.g. in eager mode).
     *
     * @param iterator
     * @param outputTypes
     * @param outputShapes
     * @return a new instance of IteratorGetNextSync
     * @see org.tensorflow.op.DataOps.iteratorGetNextSync
     */
    public fun iteratorGetNextSync(
        iterator: Operand<*>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
    ): IteratorGetNextSync = java.iteratorGetNextSync(
        iterator,
        outputTypes,
        outputShapes
    )

    /**
     * Converts the given `resource_handle` representing an iterator to a string.
     *
     * @param resourceHandle A handle to an iterator resource.
     * @return a new instance of IteratorToStringHandle
     * @see org.tensorflow.op.DataOps.iteratorToStringHandle
     */
    public fun iteratorToStringHandle(resourceHandle: Operand<*>): IteratorToStringHandle =
        java.iteratorToStringHandle(
            resourceHandle
        )

    /**
     * Makes a new iterator from the given `dataset` and stores it in `iterator`.
     *
     *  This operation may be executed multiple times. Each execution will reset the
     *  iterator in `iterator` to the first element of `dataset`.
     *
     * @param dataset
     * @param iterator
     * @return a new instance of MakeIterator
     * @see org.tensorflow.op.DataOps.makeIterator
     */
    public fun makeIterator(dataset: Operand<*>, iterator: Operand<*>): MakeIterator =
        java.makeIterator(
            dataset,
            iterator
        )

    /**
     * Constructs an Optional variant from a tuple of tensors.
     *
     * @param components
     * @return a new instance of OptionalFromValue
     * @see org.tensorflow.op.DataOps.optionalFromValue
     */
    public fun optionalFromValue(components: Iterable<Operand<*>>): OptionalFromValue =
        java.optionalFromValue(
            components
        )

    /**
     * Returns the value stored in an Optional variant or raises an error if none exists.
     *
     * @param optional
     * @param outputTypes
     * @param outputShapes
     * @return a new instance of OptionalGetValue
     * @see org.tensorflow.op.DataOps.optionalGetValue
     */
    public fun optionalGetValue(
        optional: Operand<*>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
    ): OptionalGetValue = java.optionalGetValue(
        optional,
        outputTypes,
        outputShapes
    )

    /**
     * Returns true if and only if the given Optional variant has a value.
     *
     * @param optional
     * @return a new instance of OptionalHasValue
     * @see org.tensorflow.op.DataOps.optionalHasValue
     */
    public fun optionalHasValue(optional: Operand<*>): OptionalHasValue = java.optionalHasValue(
        optional
    )

    /**
     * Creates an Optional variant with no value.
     *
     * @return a new instance of OptionalNone
     * @see org.tensorflow.op.DataOps.optionalNone
     */
    public fun optionalNone(): OptionalNone = java.optionalNone()

    /**
     * Creates a dataset with a range of values. Corresponds to python's xrange.
     *
     * @param start corresponds to start in python's xrange().
     * @param stop corresponds to stop in python's xrange().
     * @param step corresponds to step in python's xrange().
     * @param outputTypes
     * @param outputShapes
     * @return a new instance of RangeDataset
     * @see org.tensorflow.op.DataOps.rangeDataset
     */
    public fun rangeDataset(
        start: Operand<TInt64>,
        stop: Operand<TInt64>,
        step: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
    ): RangeDataset = java.rangeDataset(
        start,
        stop,
        step,
        outputTypes,
        outputShapes
    )

    /**
     * Creates a dataset that emits the outputs of `input_dataset` `count` times.
     *
     * @param inputDataset
     * @param count A scalar representing the number of times that `input_dataset` should
     *  be repeated. A value of `-1` indicates that it should be repeated infinitely.
     * @param outputTypes
     * @param outputShapes
     * @return a new instance of RepeatDataset
     * @see org.tensorflow.op.DataOps.repeatDataset
     */
    public fun repeatDataset(
        inputDataset: Operand<*>,
        count: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
    ): RepeatDataset = java.repeatDataset(
        inputDataset,
        count,
        outputTypes,
        outputShapes
    )

    /**
     * Converts the given `resource_handle` representing an iterator to a variant tensor.
     *
     * @param resourceHandle A handle to an iterator resource.
     * @param options carries optional attributes values
     * @return a new instance of SerializeIterator
     * @see org.tensorflow.op.DataOps.serializeIterator
     * @param externalStatePolicy @param externalStatePolicy
     */
    public fun serializeIterator(resourceHandle: Operand<*>, externalStatePolicy: Long? = null):
        SerializeIterator = java.serializeIterator(
        resourceHandle,
        *listOfNotNull(
            externalStatePolicy?.let { org.tensorflow.op.data.SerializeIterator.externalStatePolicy(it) }
        ).toTypedArray()
    )

    /**
     * Creates a dataset that skips `count` elements from the `input_dataset`.
     *
     * @param inputDataset
     * @param count A scalar representing the number of elements from the `input_dataset`
     *  that should be skipped.  If count is -1, skips everything.
     * @param outputTypes
     * @param outputShapes
     * @return a new instance of SkipDataset
     * @see org.tensorflow.op.DataOps.skipDataset
     */
    public fun skipDataset(
        inputDataset: Operand<*>,
        count: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
    ): SkipDataset = java.skipDataset(
        inputDataset,
        count,
        outputTypes,
        outputShapes
    )

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
     * @see org.tensorflow.op.DataOps.takeDataset
     */
    public fun takeDataset(
        inputDataset: Operand<*>,
        count: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
    ): TakeDataset = java.takeDataset(
        inputDataset,
        count,
        outputTypes,
        outputShapes
    )

    /**
     * Creates a dataset that emits each dim-0 slice of `components` once.
     *
     * @param components
     * @param outputShapes
     * @return a new instance of TensorSliceDataset
     * @see org.tensorflow.op.DataOps.tensorSliceDataset
     */
    public fun tensorSliceDataset(components: Iterable<Operand<*>>, outputShapes: List<Shape>):
        TensorSliceDataset = java.tensorSliceDataset(
        components,
        outputShapes
    )

    /**
     * Creates a dataset that emits the lines of one or more text files.
     *
     * @param filenames A scalar or a vector containing the name(s) of the file(s) to be
     *  read.
     * @param compressionType A scalar containing either (i) the empty string (no
     *  compression), (ii) "ZLIB", or (iii) "GZIP".
     * @param bufferSize A scalar containing the number of bytes to buffer.
     * @return a new instance of TextLineDataset
     * @see org.tensorflow.op.DataOps.textLineDataset
     */
    public fun textLineDataset(
        filenames: Operand<TString>,
        compressionType: Operand<TString>,
        bufferSize: Operand<TInt64>,
    ): TextLineDataset = java.textLineDataset(
        filenames,
        compressionType,
        bufferSize
    )

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
     * @see org.tensorflow.op.DataOps.tfRecordDataset
     */
    public fun tfRecordDataset(
        filenames: Operand<TString>,
        compressionType: Operand<TString>,
        bufferSize: Operand<TInt64>,
    ): TfRecordDataset = java.tfRecordDataset(
        filenames,
        compressionType,
        bufferSize
    )

    /**
     * Creates a dataset that zips together `input_datasets`.
     *
     *  The elements of the resulting dataset are created by zipping corresponding
     *  elements from each of the input datasets.
     *
     *  The size of the resulting dataset will match the size of the smallest input
     *  dataset, and no error will be raised if input datasets have different sizes.
     *
     * @param inputDatasets List of `N` variant Tensors representing datasets to be zipped
     * together.
     * @param outputTypes
     * @param outputShapes
     * @return a new instance of ZipDataset
     * @see org.tensorflow.op.DataOps.zipDataset
     */
    public fun zipDataset(
        inputDatasets: Iterable<Operand<*>>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
    ): ZipDataset = java.zipDataset(
        inputDatasets,
        outputTypes,
        outputShapes
    )
}
