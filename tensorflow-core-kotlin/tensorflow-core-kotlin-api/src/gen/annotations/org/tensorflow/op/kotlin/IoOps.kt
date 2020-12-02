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
import org.tensorflow.op.io.*
import org.tensorflow.types.TBool
import org.tensorflow.types.TInt32
import org.tensorflow.types.TInt64
import org.tensorflow.types.TString
import org.tensorflow.types.family.TNumber
import org.tensorflow.types.family.TType

/**
 * An API for building {@code io} operations as {@link org.tensorflow.op.Op Op}s
 *
 * @see {@link org.tensorflow.op.Ops}
 */
public class IoOps(
    /**
     * Get the parent {@link KotlinOps} object.
     */
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.IoOps = ops.java.io

    /**
     * Returns the current {@link Scope scope} of this API
     */
    public val scope: Scope = ops.scope

    public fun decodeBase64(input: Operand<TString>): DecodeBase64 = java.decodeBase64(
        input
    )

    public fun decodeCompressed(bytes: Operand<TString>, compressionType: String? = null):
            DecodeCompressed = java.decodeCompressed(
        bytes,
        *listOfNotNull(
            compressionType?.let { org.tensorflow.op.io.DecodeCompressed.compressionType(it) }
        ).toTypedArray()
    )

    public fun decodeCsv(
        records: Operand<TString>,
        recordDefaults: Iterable<Operand<*>>,
        fieldDelim: String? = null,
        useQuoteDelim: Boolean? = null,
        naValue: String? = null,
        selectCols: List<Long>? = null
    ): DecodeCsv = java.decodeCsv(
        records,
        recordDefaults,
        *listOfNotNull(
            fieldDelim?.let { org.tensorflow.op.io.DecodeCsv.fieldDelim(it) },
            useQuoteDelim?.let { org.tensorflow.op.io.DecodeCsv.useQuoteDelim(it) },
            naValue?.let { org.tensorflow.op.io.DecodeCsv.naValue(it) },
            selectCols?.let { org.tensorflow.op.io.DecodeCsv.selectCols(it) }
        ).toTypedArray()
    )

    public fun decodeJsonExample(jsonExamples: Operand<TString>): DecodeJsonExample =
        java.decodeJsonExample(
            jsonExamples
        )

    public fun <T : TNumber> decodePaddedRaw(
        inputBytes: Operand<TString>,
        fixedLength: Operand<TInt32>,
        outType: DataType<T>,
        littleEndian: Boolean? = null
    ): DecodePaddedRaw<T> = java.decodePaddedRaw<T>(
        inputBytes,
        fixedLength,
        outType,
        *listOfNotNull(
            littleEndian?.let { org.tensorflow.op.io.DecodePaddedRaw.littleEndian(it) }
        ).toTypedArray()
    )

    public fun <T : TType> decodeRaw(
        bytes: Operand<TString>,
        outType: DataType<T>,
        littleEndian: Boolean? = null
    ): DecodeRaw<T> = java.decodeRaw<T>(
        bytes,
        outType,
        *listOfNotNull(
            littleEndian?.let { org.tensorflow.op.io.DecodeRaw.littleEndian(it) }
        ).toTypedArray()
    )

    public fun <T : TType> deserializeManySparse(
        serializedSparse: Operand<TString>,
        dtype: DataType<T>
    ): DeserializeManySparse<T> = java.deserializeManySparse<T>(
        serializedSparse,
        dtype
    )

    public fun encodeBase64(input: Operand<TString>, pad: Boolean? = null): EncodeBase64 =
        java.encodeBase64(
            input,
            *listOfNotNull(
                pad?.let { org.tensorflow.op.io.EncodeBase64.pad(it) }
            ).toTypedArray()
        )

    public fun fifoQueue(
        componentTypes: List<DataType<*>>,
        shapes: List<Shape>? = null,
        capacity: Long? = null,
        container: String? = null,
        sharedName: String? = null
    ): FifoQueue = java.fifoQueue(
        componentTypes,
        *listOfNotNull(
            shapes?.let { org.tensorflow.op.io.FifoQueue.shapes(it) },
            capacity?.let { org.tensorflow.op.io.FifoQueue.capacity(it) },
            container?.let { org.tensorflow.op.io.FifoQueue.container(it) },
            sharedName?.let { org.tensorflow.op.io.FifoQueue.sharedName(it) }
        ).toTypedArray()
    )

    public fun fixedLengthRecordReader(
        recordBytes: Long,
        headerBytes: Long? = null,
        footerBytes: Long? = null,
        hopBytes: Long? = null,
        container: String? = null,
        sharedName: String? = null,
        encoding: String? = null
    ): FixedLengthRecordReader = java.fixedLengthRecordReader(
        recordBytes,
        *listOfNotNull(
            headerBytes?.let { org.tensorflow.op.io.FixedLengthRecordReader.headerBytes(it) },
            footerBytes?.let { org.tensorflow.op.io.FixedLengthRecordReader.footerBytes(it) },
            hopBytes?.let { org.tensorflow.op.io.FixedLengthRecordReader.hopBytes(it) },
            container?.let { org.tensorflow.op.io.FixedLengthRecordReader.container(it) },
            sharedName?.let { org.tensorflow.op.io.FixedLengthRecordReader.sharedName(it) },
            encoding?.let { org.tensorflow.op.io.FixedLengthRecordReader.encoding(it) }
        ).toTypedArray()
    )

    public fun identityReader(container: String? = null, sharedName: String? = null): IdentityReader =
        java.identityReader(
            *listOfNotNull(
                container?.let { org.tensorflow.op.io.IdentityReader.container(it) },
                sharedName?.let { org.tensorflow.op.io.IdentityReader.sharedName(it) }
            ).toTypedArray()
        )

    public fun lmdbReader(container: String? = null, sharedName: String? = null): LmdbReader =
        java.lmdbReader(
            *listOfNotNull(
                container?.let { org.tensorflow.op.io.LmdbReader.container(it) },
                sharedName?.let { org.tensorflow.op.io.LmdbReader.sharedName(it) }
            ).toTypedArray()
        )

    public fun matchingFiles(pattern: Operand<TString>): MatchingFiles = java.matchingFiles(
        pattern
    )

    public fun paddingFifoQueue(
        componentTypes: List<DataType<*>>,
        shapes: List<Shape>? = null,
        capacity: Long? = null,
        container: String? = null,
        sharedName: String? = null
    ): PaddingFifoQueue = java.paddingFifoQueue(
        componentTypes,
        *listOfNotNull(
            shapes?.let { org.tensorflow.op.io.PaddingFifoQueue.shapes(it) },
            capacity?.let { org.tensorflow.op.io.PaddingFifoQueue.capacity(it) },
            container?.let { org.tensorflow.op.io.PaddingFifoQueue.container(it) },
            sharedName?.let { org.tensorflow.op.io.PaddingFifoQueue.sharedName(it) }
        ).toTypedArray()
    )

    public fun parseExample(
        serialized: Operand<TString>,
        names: Operand<TString>,
        sparseKeys: Operand<TString>,
        denseKeys: Operand<TString>,
        raggedKeys: Operand<TString>,
        denseDefaults: Iterable<Operand<*>>,
        numSparse: Long,
        sparseTypes: List<DataType<*>>,
        raggedValueTypes: List<DataType<*>>,
        raggedSplitTypes: List<DataType<*>>,
        denseShapes: List<Shape>
    ): ParseExample = java.parseExample(
        serialized,
        names,
        sparseKeys,
        denseKeys,
        raggedKeys,
        denseDefaults,
        numSparse,
        sparseTypes,
        raggedValueTypes,
        raggedSplitTypes,
        denseShapes
    )

    public fun parseSequenceExample(
        serialized: Operand<TString>,
        debugName: Operand<TString>,
        contextSparseKeys: Operand<TString>,
        contextDenseKeys: Operand<TString>,
        contextRaggedKeys: Operand<TString>,
        featureListSparseKeys: Operand<TString>,
        featureListDenseKeys: Operand<TString>,
        featureListRaggedKeys: Operand<TString>,
        featureListDenseMissingAssumedEmpty: Operand<TBool>,
        contextDenseDefaults: Iterable<Operand<*>>,
        contextSparseTypes: List<DataType<*>>,
        contextRaggedValueTypes: List<DataType<*>>,
        contextRaggedSplitTypes: List<DataType<*>>,
        featureListDenseTypes: List<DataType<*>>,
        featureListSparseTypes: List<DataType<*>>,
        featureListRaggedValueTypes: List<DataType<*>>,
        featureListRaggedSplitTypes: List<DataType<*>>,
        NcontextSparse: Long? = null,
        contextDenseShapes: List<Shape>? = null,
        NfeatureListSparse: Long? = null,
        NfeatureListDense: Long? = null,
        featureListDenseShapes: List<Shape>? = null
    ): ParseSequenceExample = java.parseSequenceExample(
        serialized,
        debugName,
        contextSparseKeys,
        contextDenseKeys,
        contextRaggedKeys,
        featureListSparseKeys,
        featureListDenseKeys,
        featureListRaggedKeys,
        featureListDenseMissingAssumedEmpty,
        contextDenseDefaults,
        contextSparseTypes,
        contextRaggedValueTypes,
        contextRaggedSplitTypes,
        featureListDenseTypes,
        featureListSparseTypes,
        featureListRaggedValueTypes,
        featureListRaggedSplitTypes,
        *listOfNotNull(
            NcontextSparse?.let { org.tensorflow.op.io.ParseSequenceExample.NcontextSparse(it) },
            contextDenseShapes?.let { org.tensorflow.op.io.ParseSequenceExample.contextDenseShapes(it) },
            NfeatureListSparse?.let { org.tensorflow.op.io.ParseSequenceExample.NfeatureListSparse(it) },
            NfeatureListDense?.let { org.tensorflow.op.io.ParseSequenceExample.NfeatureListDense(it) },
            featureListDenseShapes?.let {
                org.tensorflow.op.io.ParseSequenceExample.featureListDenseShapes(it)
            }
        ).toTypedArray()
    )

    public fun parseSingleExample(
        serialized: Operand<TString>,
        denseDefaults: Iterable<Operand<*>>,
        numSparse: Long,
        sparseKeys: List<String>,
        denseKeys: List<String>,
        sparseTypes: List<DataType<*>>,
        denseShapes: List<Shape>
    ): ParseSingleExample = java.parseSingleExample(
        serialized,
        denseDefaults,
        numSparse,
        sparseKeys,
        denseKeys,
        sparseTypes,
        denseShapes
    )

    public fun parseSingleSequenceExample(
        serialized: Operand<TString>,
        featureListDenseMissingAssumedEmpty: Operand<TString>,
        contextSparseKeys: Iterable<Operand<TString>>,
        contextDenseKeys: Iterable<Operand<TString>>,
        featureListSparseKeys: Iterable<Operand<TString>>,
        featureListDenseKeys: Iterable<Operand<TString>>,
        contextDenseDefaults: Iterable<Operand<*>>,
        debugName: Operand<TString>,
        contextSparseTypes: List<DataType<*>>,
        featureListDenseTypes: List<DataType<*>>,
        featureListSparseTypes: List<DataType<*>>,
        contextDenseShapes: List<Shape>? = null,
        featureListDenseShapes: List<Shape>? = null
    ): ParseSingleSequenceExample = java.parseSingleSequenceExample(
        serialized,
        featureListDenseMissingAssumedEmpty,
        contextSparseKeys,
        contextDenseKeys,
        featureListSparseKeys,
        featureListDenseKeys,
        contextDenseDefaults,
        debugName,
        contextSparseTypes,
        featureListDenseTypes,
        featureListSparseTypes,
        *listOfNotNull(
            contextDenseShapes?.let {
                org.tensorflow.op.io.ParseSingleSequenceExample.contextDenseShapes(it)
            },
            featureListDenseShapes?.let {
                org.tensorflow.op.io.ParseSingleSequenceExample.featureListDenseShapes(it)
            }
        ).toTypedArray()
    )

    public fun <T : TType> parseTensor(serialized: Operand<TString>, outType: DataType<T>):
            ParseTensor<T> = java.parseTensor<T>(
        serialized,
        outType
    )

    public fun priorityQueue(
        componentTypes: List<DataType<*>>,
        shapes: List<Shape>,
        capacity: Long? = null,
        container: String? = null,
        sharedName: String? = null
    ): PriorityQueue = java.priorityQueue(
        componentTypes,
        shapes,
        *listOfNotNull(
            capacity?.let { org.tensorflow.op.io.PriorityQueue.capacity(it) },
            container?.let { org.tensorflow.op.io.PriorityQueue.container(it) },
            sharedName?.let { org.tensorflow.op.io.PriorityQueue.sharedName(it) }
        ).toTypedArray()
    )

    public fun queueClose(handle: Operand<*>, cancelPendingEnqueues: Boolean? = null): QueueClose =
        java.queueClose(
            handle,
            *listOfNotNull(
                cancelPendingEnqueues?.let { org.tensorflow.op.io.QueueClose.cancelPendingEnqueues(it) }
            ).toTypedArray()
        )

    public fun queueDequeue(
        handle: Operand<*>,
        componentTypes: List<DataType<*>>,
        timeoutMs: Long? = null
    ): QueueDequeue = java.queueDequeue(
        handle,
        componentTypes,
        *listOfNotNull(
            timeoutMs?.let { org.tensorflow.op.io.QueueDequeue.timeoutMs(it) }
        ).toTypedArray()
    )

    public fun queueDequeueMany(
        handle: Operand<*>,
        n: Operand<TInt32>,
        componentTypes: List<DataType<*>>,
        timeoutMs: Long? = null
    ): QueueDequeueMany = java.queueDequeueMany(
        handle,
        n,
        componentTypes,
        *listOfNotNull(
            timeoutMs?.let { org.tensorflow.op.io.QueueDequeueMany.timeoutMs(it) }
        ).toTypedArray()
    )

    public fun queueDequeueUpTo(
        handle: Operand<*>,
        n: Operand<TInt32>,
        componentTypes: List<DataType<*>>,
        timeoutMs: Long? = null
    ): QueueDequeueUpTo = java.queueDequeueUpTo(
        handle,
        n,
        componentTypes,
        *listOfNotNull(
            timeoutMs?.let { org.tensorflow.op.io.QueueDequeueUpTo.timeoutMs(it) }
        ).toTypedArray()
    )

    public fun queueEnqueue(
        handle: Operand<*>,
        components: Iterable<Operand<*>>,
        timeoutMs: Long? = null
    ): QueueEnqueue = java.queueEnqueue(
        handle,
        components,
        *listOfNotNull(
            timeoutMs?.let { org.tensorflow.op.io.QueueEnqueue.timeoutMs(it) }
        ).toTypedArray()
    )

    public fun queueEnqueueMany(
        handle: Operand<*>,
        components: Iterable<Operand<*>>,
        timeoutMs: Long? = null
    ): QueueEnqueueMany = java.queueEnqueueMany(
        handle,
        components,
        *listOfNotNull(
            timeoutMs?.let { org.tensorflow.op.io.QueueEnqueueMany.timeoutMs(it) }
        ).toTypedArray()
    )

    public fun queueIsClosed(handle: Operand<*>): QueueIsClosed = java.queueIsClosed(
        handle
    )

    public fun queueSize(handle: Operand<*>): QueueSize = java.queueSize(
        handle
    )

    public fun randomShuffleQueue(
        componentTypes: List<DataType<*>>,
        shapes: List<Shape>? = null,
        capacity: Long? = null,
        minAfterDequeue: Long? = null,
        seed: Long? = null,
        seed2: Long? = null,
        container: String? = null,
        sharedName: String? = null
    ): RandomShuffleQueue = java.randomShuffleQueue(
        componentTypes,
        *listOfNotNull(
            shapes?.let { org.tensorflow.op.io.RandomShuffleQueue.shapes(it) },
            capacity?.let { org.tensorflow.op.io.RandomShuffleQueue.capacity(it) },
            minAfterDequeue?.let { org.tensorflow.op.io.RandomShuffleQueue.minAfterDequeue(it) },
            seed?.let { org.tensorflow.op.io.RandomShuffleQueue.seed(it) },
            seed2?.let { org.tensorflow.op.io.RandomShuffleQueue.seed2(it) },
            container?.let { org.tensorflow.op.io.RandomShuffleQueue.container(it) },
            sharedName?.let { org.tensorflow.op.io.RandomShuffleQueue.sharedName(it) }
        ).toTypedArray()
    )

    public fun readFile(filename: Operand<TString>): ReadFile = java.readFile(
        filename
    )

    public fun readerNumRecordsProduced(readerHandle: Operand<*>): ReaderNumRecordsProduced =
        java.readerNumRecordsProduced(
            readerHandle
        )

    public fun readerNumWorkUnitsCompleted(readerHandle: Operand<*>): ReaderNumWorkUnitsCompleted =
        java.readerNumWorkUnitsCompleted(
            readerHandle
        )

    public fun readerRead(readerHandle: Operand<*>, queueHandle: Operand<*>): ReaderRead =
        java.readerRead(
            readerHandle,
            queueHandle
        )

    public fun readerReadUpTo(
        readerHandle: Operand<*>,
        queueHandle: Operand<*>,
        numRecords: Operand<TInt64>
    ): ReaderReadUpTo = java.readerReadUpTo(
        readerHandle,
        queueHandle,
        numRecords
    )

    public fun readerReset(readerHandle: Operand<*>): ReaderReset = java.readerReset(
        readerHandle
    )

    public fun readerRestoreState(readerHandle: Operand<*>, state: Operand<TString>):
            ReaderRestoreState = java.readerRestoreState(
        readerHandle,
        state
    )

    public fun readerSerializeState(readerHandle: Operand<*>): ReaderSerializeState =
        java.readerSerializeState(
            readerHandle
        )

    public fun <T : TType> serializeManySparse(
        sparseIndices: Operand<TInt64>,
        sparseValues: Operand<T>,
        sparseShape: Operand<TInt64>
    ): SerializeManySparse<TString> = java.serializeManySparse<T>(
        sparseIndices,
        sparseValues,
        sparseShape
    )

    public fun <U : TType, T : TType> serializeManySparse(
        sparseIndices: Operand<TInt64>,
        sparseValues: Operand<T>,
        sparseShape: Operand<TInt64>,
        outType: DataType<U>
    ): SerializeManySparse<U> = java.serializeManySparse<U, T>(
        sparseIndices,
        sparseValues,
        sparseShape,
        outType
    )

    public fun <T : TType> serializeSparse(
        sparseIndices: Operand<TInt64>,
        sparseValues: Operand<T>,
        sparseShape: Operand<TInt64>
    ): SerializeSparse<TString> = java.serializeSparse<T>(
        sparseIndices,
        sparseValues,
        sparseShape
    )

    public fun <U : TType, T : TType> serializeSparse(
        sparseIndices: Operand<TInt64>,
        sparseValues: Operand<T>,
        sparseShape: Operand<TInt64>,
        outType: DataType<U>
    ): SerializeSparse<U> = java.serializeSparse<U, T>(
        sparseIndices,
        sparseValues,
        sparseShape,
        outType
    )

    public fun <T : TType> serializeTensor(tensor: Operand<T>): SerializeTensor =
        java.serializeTensor<T>(
            tensor
        )

    public fun shardedFilename(
        basename: Operand<TString>,
        shard: Operand<TInt32>,
        numShards: Operand<TInt32>
    ): ShardedFilename = java.shardedFilename(
        basename,
        shard,
        numShards
    )

    public fun shardedFilespec(basename: Operand<TString>, numShards: Operand<TInt32>): ShardedFilespec = java.shardedFilespec(
        basename,
        numShards
    )

    public fun textLineReader(
        skipHeaderLines: Long? = null,
        container: String? = null,
        sharedName: String? = null
    ): TextLineReader = java.textLineReader(
        *listOfNotNull(
            skipHeaderLines?.let { org.tensorflow.op.io.TextLineReader.skipHeaderLines(it) },
            container?.let { org.tensorflow.op.io.TextLineReader.container(it) },
            sharedName?.let { org.tensorflow.op.io.TextLineReader.sharedName(it) }
        ).toTypedArray()
    )

    public fun tfRecordReader(
        container: String? = null,
        sharedName: String? = null,
        compressionType: String? = null
    ): TfRecordReader = java.tfRecordReader(
        *listOfNotNull(
            container?.let { org.tensorflow.op.io.TfRecordReader.container(it) },
            sharedName?.let { org.tensorflow.op.io.TfRecordReader.sharedName(it) },
            compressionType?.let { org.tensorflow.op.io.TfRecordReader.compressionType(it) }
        ).toTypedArray()
    )

    public fun wholeFileReader(container: String? = null, sharedName: String? = null): WholeFileReader = java.wholeFileReader(
        *listOfNotNull(
            container?.let { org.tensorflow.op.io.WholeFileReader.container(it) },
            sharedName?.let { org.tensorflow.op.io.WholeFileReader.sharedName(it) }
        ).toTypedArray()
    )

    public fun writeFile(filename: Operand<TString>, contents: Operand<TString>): WriteFile =
        java.writeFile(
            filename,
            contents
        )
}
