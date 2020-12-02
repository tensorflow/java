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
import org.tensorflow.op.io.DecodeBase64
import org.tensorflow.op.io.DecodeCompressed
import org.tensorflow.op.io.DecodeCsv
import org.tensorflow.op.io.DecodeJsonExample
import org.tensorflow.op.io.DecodePaddedRaw
import org.tensorflow.op.io.DecodeRaw
import org.tensorflow.op.io.DeserializeManySparse
import org.tensorflow.op.io.EncodeBase64
import org.tensorflow.op.io.FifoQueue
import org.tensorflow.op.io.FixedLengthRecordReader
import org.tensorflow.op.io.IdentityReader
import org.tensorflow.op.io.LmdbReader
import org.tensorflow.op.io.MatchingFiles
import org.tensorflow.op.io.PaddingFifoQueue
import org.tensorflow.op.io.ParseExample
import org.tensorflow.op.io.ParseSequenceExample
import org.tensorflow.op.io.ParseSingleExample
import org.tensorflow.op.io.ParseSingleSequenceExample
import org.tensorflow.op.io.ParseTensor
import org.tensorflow.op.io.PriorityQueue
import org.tensorflow.op.io.QueueClose
import org.tensorflow.op.io.QueueDequeue
import org.tensorflow.op.io.QueueDequeueMany
import org.tensorflow.op.io.QueueDequeueUpTo
import org.tensorflow.op.io.QueueEnqueue
import org.tensorflow.op.io.QueueEnqueueMany
import org.tensorflow.op.io.QueueIsClosed
import org.tensorflow.op.io.QueueSize
import org.tensorflow.op.io.RandomShuffleQueue
import org.tensorflow.op.io.ReadFile
import org.tensorflow.op.io.ReaderNumRecordsProduced
import org.tensorflow.op.io.ReaderNumWorkUnitsCompleted
import org.tensorflow.op.io.ReaderRead
import org.tensorflow.op.io.ReaderReadUpTo
import org.tensorflow.op.io.ReaderReset
import org.tensorflow.op.io.ReaderRestoreState
import org.tensorflow.op.io.ReaderSerializeState
import org.tensorflow.op.io.SerializeManySparse
import org.tensorflow.op.io.SerializeSparse
import org.tensorflow.op.io.SerializeTensor
import org.tensorflow.op.io.ShardedFilename
import org.tensorflow.op.io.ShardedFilespec
import org.tensorflow.op.io.TextLineReader
import org.tensorflow.op.io.TfRecordReader
import org.tensorflow.op.io.WholeFileReader
import org.tensorflow.op.io.WriteFile
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

  public fun decodeBase64(input: Operand<TString>): DecodeBase64 = java.decodeBase64(input)

  public fun decodeCompressed(bytes: Operand<TString>, vararg options: DecodeCompressed.Options):
      DecodeCompressed = java.decodeCompressed(bytes, *options)

  public fun decodeCsv(
    records: Operand<TString>,
    recordDefaults: Iterable<Operand<*>>,
    vararg options: DecodeCsv.Options
  ): DecodeCsv = java.decodeCsv(records, recordDefaults, *options)

  public fun decodeJsonExample(jsonExamples: Operand<TString>): DecodeJsonExample =
      java.decodeJsonExample(jsonExamples)

  public fun <T : TNumber> decodePaddedRaw(
    inputBytes: Operand<TString>,
    fixedLength: Operand<TInt32>,
    outType: DataType<T>,
    vararg options: DecodePaddedRaw.Options
  ): DecodePaddedRaw<T> = java.decodePaddedRaw<T>(inputBytes, fixedLength, outType, *options)

  public fun <T : TType> decodeRaw(
    bytes: Operand<TString>,
    outType: DataType<T>,
    vararg options: DecodeRaw.Options
  ): DecodeRaw<T> = java.decodeRaw<T>(bytes, outType, *options)

  public fun <T : TType> deserializeManySparse(serializedSparse: Operand<TString>,
      dtype: DataType<T>): DeserializeManySparse<T> =
      java.deserializeManySparse<T>(serializedSparse, dtype)

  public fun encodeBase64(input: Operand<TString>, vararg options: EncodeBase64.Options):
      EncodeBase64 = java.encodeBase64(input, *options)

  public fun fifoQueue(componentTypes: List<DataType<*>>, vararg options: FifoQueue.Options):
      FifoQueue = java.fifoQueue(componentTypes, *options)

  public fun fixedLengthRecordReader(recordBytes: Long, vararg
      options: FixedLengthRecordReader.Options): FixedLengthRecordReader =
      java.fixedLengthRecordReader(recordBytes, *options)

  public fun identityReader(vararg options: IdentityReader.Options): IdentityReader =
      java.identityReader(*options)

  public fun lmdbReader(vararg options: LmdbReader.Options): LmdbReader = java.lmdbReader(*options)

  public fun matchingFiles(pattern: Operand<TString>): MatchingFiles = java.matchingFiles(pattern)

  public fun paddingFifoQueue(componentTypes: List<DataType<*>>, vararg
      options: PaddingFifoQueue.Options): PaddingFifoQueue = java.paddingFifoQueue(componentTypes,
      *options)

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
  ): ParseExample = java.parseExample(serialized, names, sparseKeys, denseKeys, raggedKeys,
      denseDefaults, numSparse, sparseTypes, raggedValueTypes, raggedSplitTypes, denseShapes)

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
    vararg options: ParseSequenceExample.Options
  ): ParseSequenceExample = java.parseSequenceExample(serialized, debugName, contextSparseKeys,
      contextDenseKeys, contextRaggedKeys, featureListSparseKeys, featureListDenseKeys,
      featureListRaggedKeys, featureListDenseMissingAssumedEmpty, contextDenseDefaults,
      contextSparseTypes, contextRaggedValueTypes, contextRaggedSplitTypes, featureListDenseTypes,
      featureListSparseTypes, featureListRaggedValueTypes, featureListRaggedSplitTypes, *options)

  public fun parseSingleExample(
    serialized: Operand<TString>,
    denseDefaults: Iterable<Operand<*>>,
    numSparse: Long,
    sparseKeys: List<String>,
    denseKeys: List<String>,
    sparseTypes: List<DataType<*>>,
    denseShapes: List<Shape>
  ): ParseSingleExample = java.parseSingleExample(serialized, denseDefaults, numSparse, sparseKeys,
      denseKeys, sparseTypes, denseShapes)

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
    vararg options: ParseSingleSequenceExample.Options
  ): ParseSingleSequenceExample = java.parseSingleSequenceExample(serialized,
      featureListDenseMissingAssumedEmpty, contextSparseKeys, contextDenseKeys,
      featureListSparseKeys, featureListDenseKeys, contextDenseDefaults, debugName,
      contextSparseTypes, featureListDenseTypes, featureListSparseTypes, *options)

  public fun <T : TType> parseTensor(serialized: Operand<TString>, outType: DataType<T>):
      ParseTensor<T> = java.parseTensor<T>(serialized, outType)

  public fun priorityQueue(
    componentTypes: List<DataType<*>>,
    shapes: List<Shape>,
    vararg options: PriorityQueue.Options
  ): PriorityQueue = java.priorityQueue(componentTypes, shapes, *options)

  public fun queueClose(handle: Operand<*>, vararg options: QueueClose.Options): QueueClose =
      java.queueClose(handle, *options)

  public fun queueDequeue(
    handle: Operand<*>,
    componentTypes: List<DataType<*>>,
    vararg options: QueueDequeue.Options
  ): QueueDequeue = java.queueDequeue(handle, componentTypes, *options)

  public fun queueDequeueMany(
    handle: Operand<*>,
    n: Operand<TInt32>,
    componentTypes: List<DataType<*>>,
    vararg options: QueueDequeueMany.Options
  ): QueueDequeueMany = java.queueDequeueMany(handle, n, componentTypes, *options)

  public fun queueDequeueUpTo(
    handle: Operand<*>,
    n: Operand<TInt32>,
    componentTypes: List<DataType<*>>,
    vararg options: QueueDequeueUpTo.Options
  ): QueueDequeueUpTo = java.queueDequeueUpTo(handle, n, componentTypes, *options)

  public fun queueEnqueue(
    handle: Operand<*>,
    components: Iterable<Operand<*>>,
    vararg options: QueueEnqueue.Options
  ): QueueEnqueue = java.queueEnqueue(handle, components, *options)

  public fun queueEnqueueMany(
    handle: Operand<*>,
    components: Iterable<Operand<*>>,
    vararg options: QueueEnqueueMany.Options
  ): QueueEnqueueMany = java.queueEnqueueMany(handle, components, *options)

  public fun queueIsClosed(handle: Operand<*>): QueueIsClosed = java.queueIsClosed(handle)

  public fun queueSize(handle: Operand<*>): QueueSize = java.queueSize(handle)

  public fun randomShuffleQueue(componentTypes: List<DataType<*>>, vararg
      options: RandomShuffleQueue.Options): RandomShuffleQueue =
      java.randomShuffleQueue(componentTypes, *options)

  public fun readFile(filename: Operand<TString>): ReadFile = java.readFile(filename)

  public fun readerNumRecordsProduced(readerHandle: Operand<*>): ReaderNumRecordsProduced =
      java.readerNumRecordsProduced(readerHandle)

  public fun readerNumWorkUnitsCompleted(readerHandle: Operand<*>): ReaderNumWorkUnitsCompleted =
      java.readerNumWorkUnitsCompleted(readerHandle)

  public fun readerRead(readerHandle: Operand<*>, queueHandle: Operand<*>): ReaderRead =
      java.readerRead(readerHandle, queueHandle)

  public fun readerReadUpTo(
    readerHandle: Operand<*>,
    queueHandle: Operand<*>,
    numRecords: Operand<TInt64>
  ): ReaderReadUpTo = java.readerReadUpTo(readerHandle, queueHandle, numRecords)

  public fun readerReset(readerHandle: Operand<*>): ReaderReset = java.readerReset(readerHandle)

  public fun readerRestoreState(readerHandle: Operand<*>, state: Operand<TString>):
      ReaderRestoreState = java.readerRestoreState(readerHandle, state)

  public fun readerSerializeState(readerHandle: Operand<*>): ReaderSerializeState =
      java.readerSerializeState(readerHandle)

  public fun <T : TType> serializeManySparse(
    sparseIndices: Operand<TInt64>,
    sparseValues: Operand<T>,
    sparseShape: Operand<TInt64>
  ): SerializeManySparse<TString> = java.serializeManySparse<T>(sparseIndices, sparseValues,
      sparseShape)

  public fun <U : TType, T : TType> serializeManySparse(
    sparseIndices: Operand<TInt64>,
    sparseValues: Operand<T>,
    sparseShape: Operand<TInt64>,
    outType: DataType<U>
  ): SerializeManySparse<U> = java.serializeManySparse<U, T>(sparseIndices, sparseValues,
      sparseShape, outType)

  public fun <T : TType> serializeSparse(
    sparseIndices: Operand<TInt64>,
    sparseValues: Operand<T>,
    sparseShape: Operand<TInt64>
  ): SerializeSparse<TString> = java.serializeSparse<T>(sparseIndices, sparseValues, sparseShape)

  public fun <U : TType, T : TType> serializeSparse(
    sparseIndices: Operand<TInt64>,
    sparseValues: Operand<T>,
    sparseShape: Operand<TInt64>,
    outType: DataType<U>
  ): SerializeSparse<U> = java.serializeSparse<U, T>(sparseIndices, sparseValues, sparseShape,
      outType)

  public fun <T : TType> serializeTensor(tensor: Operand<T>): SerializeTensor =
      java.serializeTensor<T>(tensor)

  public fun shardedFilename(
    basename: Operand<TString>,
    shard: Operand<TInt32>,
    numShards: Operand<TInt32>
  ): ShardedFilename = java.shardedFilename(basename, shard, numShards)

  public fun shardedFilespec(basename: Operand<TString>, numShards: Operand<TInt32>):
      ShardedFilespec = java.shardedFilespec(basename, numShards)

  public fun textLineReader(vararg options: TextLineReader.Options): TextLineReader =
      java.textLineReader(*options)

  public fun tfRecordReader(vararg options: TfRecordReader.Options): TfRecordReader =
      java.tfRecordReader(*options)

  public fun wholeFileReader(vararg options: WholeFileReader.Options): WholeFileReader =
      java.wholeFileReader(*options)

  public fun writeFile(filename: Operand<TString>, contents: Operand<TString>): WriteFile =
      java.writeFile(filename, contents)
}
