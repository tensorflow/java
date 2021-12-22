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

import kotlin.Boolean
import kotlin.Long
import kotlin.String
import org.tensorflow.ConcreteFunction
import org.tensorflow.Operand
import org.tensorflow.ndarray.Shape
import org.tensorflow.op.Scope
import org.tensorflow.op.`data`.AnonymousIterator
import org.tensorflow.op.`data`.AssertCardinalityDataset
import org.tensorflow.op.`data`.AssertNextDataset
import org.tensorflow.op.`data`.AutoShardDataset
import org.tensorflow.op.`data`.BatchDataset
import org.tensorflow.op.`data`.BytesProducedStatsDataset
import org.tensorflow.op.`data`.CSVDataset
import org.tensorflow.op.`data`.CacheDataset
import org.tensorflow.op.`data`.ChooseFastestBranchDataset
import org.tensorflow.op.`data`.ChooseFastestDataset
import org.tensorflow.op.`data`.ConcatenateDataset
import org.tensorflow.op.`data`.DataServiceDatasetV2
import org.tensorflow.op.`data`.DatasetCardinality
import org.tensorflow.op.`data`.DatasetFromGraph
import org.tensorflow.op.`data`.DatasetToGraph
import org.tensorflow.op.`data`.DatasetToSingleElement
import org.tensorflow.op.`data`.DatasetToTfRecord
import org.tensorflow.op.`data`.DeleteIterator
import org.tensorflow.op.`data`.DenseToSparseBatchDataset
import org.tensorflow.op.`data`.DeserializeIterator
import org.tensorflow.op.`data`.DirectedInterleaveDataset
import org.tensorflow.op.`data`.FilterByLastComponentDataset
import org.tensorflow.op.`data`.FilterDataset
import org.tensorflow.op.`data`.FinalizeDataset
import org.tensorflow.op.`data`.FixedLengthRecordDataset
import org.tensorflow.op.`data`.FlatMapDataset
import org.tensorflow.op.`data`.GeneratorDataset
import org.tensorflow.op.`data`.GroupByReducerDataset
import org.tensorflow.op.`data`.GroupByWindowDataset
import org.tensorflow.op.`data`.IgnoreErrorsDataset
import org.tensorflow.op.`data`.InitializeTableFromDataset
import org.tensorflow.op.`data`.InterleaveDataset
import org.tensorflow.op.`data`.Iterator
import org.tensorflow.op.`data`.IteratorGetNext
import org.tensorflow.op.`data`.IteratorGetNextAsOptional
import org.tensorflow.op.`data`.IteratorGetNextSync
import org.tensorflow.op.`data`.IteratorToStringHandle
import org.tensorflow.op.`data`.LMDBDataset
import org.tensorflow.op.`data`.LatencyStatsDataset
import org.tensorflow.op.`data`.LegacyParallelInterleaveDataset
import org.tensorflow.op.`data`.LoadDataset
import org.tensorflow.op.`data`.MakeIterator
import org.tensorflow.op.`data`.MapAndBatchDataset
import org.tensorflow.op.`data`.MapDataset
import org.tensorflow.op.`data`.MatchingFilesDataset
import org.tensorflow.op.`data`.MaxIntraOpParallelismDataset
import org.tensorflow.op.`data`.ModelDataset
import org.tensorflow.op.`data`.NonSerializableDataset
import org.tensorflow.op.`data`.OneShotIterator
import org.tensorflow.op.`data`.OptimizeDataset
import org.tensorflow.op.`data`.OptionalFromValue
import org.tensorflow.op.`data`.OptionalGetValue
import org.tensorflow.op.`data`.OptionalHasValue
import org.tensorflow.op.`data`.OptionalNone
import org.tensorflow.op.`data`.OptionsDataset
import org.tensorflow.op.`data`.PaddedBatchDataset
import org.tensorflow.op.`data`.ParallelBatchDataset
import org.tensorflow.op.`data`.ParallelInterleaveDataset
import org.tensorflow.op.`data`.ParallelMapDataset
import org.tensorflow.op.`data`.ParseExampleDataset
import org.tensorflow.op.`data`.PrefetchDataset
import org.tensorflow.op.`data`.PrivateThreadPoolDataset
import org.tensorflow.op.`data`.RandomDataset
import org.tensorflow.op.`data`.RangeDataset
import org.tensorflow.op.`data`.RebatchDatasetV2
import org.tensorflow.op.`data`.ReduceDataset
import org.tensorflow.op.`data`.RegisterDataset
import org.tensorflow.op.`data`.RepeatDataset
import org.tensorflow.op.`data`.SamplingDataset
import org.tensorflow.op.`data`.SaveDataset
import org.tensorflow.op.`data`.ScanDataset
import org.tensorflow.op.`data`.SerializeIterator
import org.tensorflow.op.`data`.SetStatsAggregatorDataset
import org.tensorflow.op.`data`.ShardDataset
import org.tensorflow.op.`data`.ShuffleAndRepeatDataset
import org.tensorflow.op.`data`.ShuffleDataset
import org.tensorflow.op.`data`.SkipDataset
import org.tensorflow.op.`data`.SleepDataset
import org.tensorflow.op.`data`.SlidingWindowDataset
import org.tensorflow.op.`data`.SnapshotDataset
import org.tensorflow.op.`data`.SparseTensorSliceDataset
import org.tensorflow.op.`data`.SqlDataset
import org.tensorflow.op.`data`.TakeDataset
import org.tensorflow.op.`data`.TakeWhileDataset
import org.tensorflow.op.`data`.TensorDataset
import org.tensorflow.op.`data`.TensorSliceDataset
import org.tensorflow.op.`data`.TextLineDataset
import org.tensorflow.op.`data`.TfRecordDataset
import org.tensorflow.op.`data`.ThreadPoolDataset
import org.tensorflow.op.`data`.UnbatchDataset
import org.tensorflow.op.`data`.UniqueDataset
import org.tensorflow.op.`data`.UnwrapDatasetVariant
import org.tensorflow.op.`data`.WindowDataset
import org.tensorflow.op.`data`.WrapDatasetVariant
import org.tensorflow.op.`data`.ZipDataset
import org.tensorflow.types.TBool
import org.tensorflow.types.TFloat32
import org.tensorflow.types.TInt64
import org.tensorflow.types.TString
import org.tensorflow.types.family.TNumber
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
    public val ops: KotlinOps
) {
    public val java: org.tensorflow.op.DataOps = ops.java.data

    /**
     * Returns the current [scope][Scope] of this API
     */
    public val scope: Scope = ops.scope

    /**
     * A container for an iterator resource.
     *
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of AnonymousIterator
     * @see org.tensorflow.op.DataOps.anonymousIterator
     */
    public fun anonymousIterator(outputTypes: List<Class<out TType>>, outputShapes: List<Shape>):
            AnonymousIterator = java.anonymousIterator(    
        outputTypes,
        outputShapes
        )

    /**
     * The AssertCardinalityDataset operation
     *
     * @param inputDataset The inputDataset value
     * @param cardinality The cardinality value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of AssertCardinalityDataset
     * @see org.tensorflow.op.DataOps.assertCardinalityDataset
     */
    public fun assertCardinalityDataset(
        inputDataset: Operand<out TType>,
        cardinality: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): AssertCardinalityDataset = java.assertCardinalityDataset(    
        inputDataset,
        cardinality,
        outputTypes,
        outputShapes
        )

    /**
     * A transformation that asserts which transformations happen next.
     *  This transformation checks whether the camel-case names (i.e. &quot;FlatMap&quot;, not
     *  &quot;flat_map&quot;) of the transformations following this transformation match the list
     *  of names in the `transformations` argument. If there is a mismatch, the
     *  transformation raises an exception.
     *  
     * The check occurs when iterating over the contents of the dataset, which
     *  means that the check happens _after_ any static optimizations are applied
     *  to the dataset graph.
     *
     * @param inputDataset A variant tensor representing the input dataset.
     *  `data.AssertNextDataset` passes through the outputs of its input dataset.
     * @param transformations A `tf.string` vector `tf.Tensor` identifying the transformations that
     * are
     *  expected to happen next.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of AssertNextDataset
     * @see org.tensorflow.op.DataOps.assertNextDataset
     */
    public fun assertNextDataset(
        inputDataset: Operand<out TType>,
        transformations: Operand<TString>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): AssertNextDataset = java.assertNextDataset(    
        inputDataset,
        transformations,
        outputTypes,
        outputShapes
        )

    /**
     * Creates a dataset that shards the input dataset.
     *  Creates a dataset that shards the input dataset by num_workers, returning a
     *  sharded dataset for the index-th worker. This attempts to automatically shard
     *  a dataset by examining the Dataset graph and inserting a shard op before the
     *  inputs to a reader Dataset (e.g. CSVDataset, TFRecordDataset).
     *  
     * This dataset will throw a NotFound error if we cannot shard the dataset
     *  automatically.
     *
     * @param inputDataset A variant tensor representing the input dataset.
     * @param numWorkers A scalar representing the number of workers to distribute this dataset
     * across.
     * @param index A scalar representing the index of the current worker out of num_workers.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of AutoShardDataset
     * @see org.tensorflow.op.DataOps.autoShardDataset
     * @param autoShardPolicy Sets the autoShardPolicy option.
     *
     * @param autoShardPolicy the autoShardPolicy option
     * @return this Options instance.
     * @param numReplicas Sets the numReplicas option.
     *
     * @param numReplicas the numReplicas option
     * @return this Options instance.
     */
    public fun autoShardDataset(
        inputDataset: Operand<out TType>,
        numWorkers: Operand<TInt64>,
        index: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        autoShardPolicy: Long? = null,
        numReplicas: Long? = null
    ): AutoShardDataset = java.autoShardDataset(    
        inputDataset,
        numWorkers,
        index,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            autoShardPolicy?.let{ org.tensorflow.op.data.AutoShardDataset.autoShardPolicy(it) },
            numReplicas?.let{ org.tensorflow.op.data.AutoShardDataset.numReplicas(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that batches `batch_size` elements from `input_dataset`.
     *
     * @param inputDataset The inputDataset value
     * @param batchSize A scalar representing the number of elements to accumulate in a batch.
     * @param dropRemainder A scalar representing whether the last batch should be dropped in case
     * its size
     *  is smaller than desired.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of BatchDataset
     * @see org.tensorflow.op.DataOps.batchDataset
     * @param parallelCopy Sets the parallelCopy option.
     *
     * @param parallelCopy the parallelCopy option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun batchDataset(
        inputDataset: Operand<out TType>,
        batchSize: Operand<TInt64>,
        dropRemainder: Operand<TBool>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        parallelCopy: Boolean? = null,
        metadata: String? = null
    ): BatchDataset = java.batchDataset(    
        inputDataset,
        batchSize,
        dropRemainder,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            parallelCopy?.let{ org.tensorflow.op.data.BatchDataset.parallelCopy(it) },
            metadata?.let{ org.tensorflow.op.data.BatchDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Records the bytes size of each element of `input_dataset` in a StatsAggregator.
     *
     * @param inputDataset The inputDataset value
     * @param tag The tag value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of BytesProducedStatsDataset
     * @see org.tensorflow.op.DataOps.bytesProducedStatsDataset
     */
    public fun bytesProducedStatsDataset(
        inputDataset: Operand<out TType>,
        tag: Operand<TString>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): BytesProducedStatsDataset = java.bytesProducedStatsDataset(    
        inputDataset,
        tag,
        outputTypes,
        outputShapes
        )

    /**
     * The CSVDatasetV2 operation
     *
     * @param filenames The filenames value
     * @param compressionType The compressionType value
     * @param bufferSize The bufferSize value
     * @param header The header value
     * @param fieldDelim The fieldDelim value
     * @param useQuoteDelim The useQuoteDelim value
     * @param naValue The naValue value
     * @param selectCols The selectCols value
     * @param recordDefaults The recordDefaults value
     * @param excludeCols The excludeCols value
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of CSVDataset
     * @see org.tensorflow.op.DataOps.cSVDataset
     */
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
        excludeCols: Operand<TInt64>,
        outputShapes: List<Shape>
    ): CSVDataset = java.cSVDataset(    
        filenames,
        compressionType,
        bufferSize,
        header,
        fieldDelim,
        useQuoteDelim,
        naValue,
        selectCols,
        recordDefaults,
        excludeCols,
        outputShapes
        )

    /**
     * The CacheDatasetV2 operation
     *
     * @param inputDataset The inputDataset value
     * @param filename The filename value
     * @param cache The cache value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of CacheDataset
     * @see org.tensorflow.op.DataOps.cacheDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun cacheDataset(
        inputDataset: Operand<out TType>,
        filename: Operand<TString>,
        cache: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): CacheDataset = java.cacheDataset(    
        inputDataset,
        filename,
        cache,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.CacheDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * The ChooseFastestBranchDataset operation
     *
     * @param inputDataset The inputDataset value
     * @param ratioNumerator The ratioNumerator value
     * @param ratioDenominator The ratioDenominator value
     * @param otherArguments The otherArguments value
     * @param numElementsPerBranch The value of the numElementsPerBranch attribute
     * @param branches The value of the branches attribute
     * @param otherArgumentsLengths The value of the otherArgumentsLengths attribute
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of ChooseFastestBranchDataset
     * @see org.tensorflow.op.DataOps.chooseFastestBranchDataset
     */
    public fun chooseFastestBranchDataset(
        inputDataset: Operand<out TType>,
        ratioNumerator: Operand<TInt64>,
        ratioDenominator: Operand<TInt64>,
        otherArguments: Iterable<Operand<*>>,
        numElementsPerBranch: Long,
        branches: List<ConcreteFunction>,
        otherArgumentsLengths: List<Long>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): ChooseFastestBranchDataset = java.chooseFastestBranchDataset(    
        inputDataset,
        ratioNumerator,
        ratioDenominator,
        otherArguments,
        numElementsPerBranch,
        branches,
        otherArgumentsLengths,
        outputTypes,
        outputShapes
        )

    /**
     * The ChooseFastestDataset operation
     *
     * @param inputDatasets The inputDatasets value
     * @param numExperiments The value of the numExperiments attribute
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of ChooseFastestDataset
     * @see org.tensorflow.op.DataOps.chooseFastestDataset
     */
    public fun chooseFastestDataset(
        inputDatasets: Iterable<Operand<out TType>>,
        numExperiments: Long,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): ChooseFastestDataset = java.chooseFastestDataset(    
        inputDatasets,
        numExperiments,
        outputTypes,
        outputShapes
        )

    /**
     * Creates a dataset that concatenates `input_dataset` with `another_dataset`.
     *
     * @param inputDataset The inputDataset value
     * @param anotherDataset The anotherDataset value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of ConcatenateDataset
     * @see org.tensorflow.op.DataOps.concatenateDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun concatenateDataset(
        inputDataset: Operand<out TType>,
        anotherDataset: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): ConcatenateDataset = java.concatenateDataset(    
        inputDataset,
        anotherDataset,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.ConcatenateDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that reads data from the tf.data service.
     *
     * @param datasetId The datasetId value
     * @param processingMode The processingMode value
     * @param address The address value
     * @param protocol The protocol value
     * @param jobName The jobName value
     * @param consumerIndex The consumerIndex value
     * @param numConsumers The numConsumers value
     * @param maxOutstandingRequests The maxOutstandingRequests value
     * @param iterationCounter The iterationCounter value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of DataServiceDatasetV2
     * @see org.tensorflow.op.DataOps.dataServiceDatasetV2
     * @param taskRefreshIntervalHintMs Sets the taskRefreshIntervalHintMs option.
     *
     * @param taskRefreshIntervalHintMs the taskRefreshIntervalHintMs option
     * @return this Options instance.
     * @param dataTransferProtocol Sets the dataTransferProtocol option.
     *
     * @param dataTransferProtocol the dataTransferProtocol option
     * @return this Options instance.
     * @param targetWorkers Sets the targetWorkers option.
     *
     * @param targetWorkers the targetWorkers option
     * @return this Options instance.
     */
    public fun dataServiceDatasetV2(
        datasetId: Operand<TInt64>,
        processingMode: Operand<TString>,
        address: Operand<TString>,
        protocol: Operand<TString>,
        jobName: Operand<TString>,
        consumerIndex: Operand<TInt64>,
        numConsumers: Operand<TInt64>,
        maxOutstandingRequests: Operand<TInt64>,
        iterationCounter: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        taskRefreshIntervalHintMs: Long? = null,
        dataTransferProtocol: String? = null,
        targetWorkers: String? = null
    ): DataServiceDatasetV2 = java.dataServiceDatasetV2(    
        datasetId,
        processingMode,
        address,
        protocol,
        jobName,
        consumerIndex,
        numConsumers,
        maxOutstandingRequests,
        iterationCounter,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            taskRefreshIntervalHintMs?.let{
            org.tensorflow.op.data.DataServiceDatasetV2.taskRefreshIntervalHintMs(it) },
            dataTransferProtocol?.let{
            org.tensorflow.op.data.DataServiceDatasetV2.dataTransferProtocol(it) },
            targetWorkers?.let{ org.tensorflow.op.data.DataServiceDatasetV2.targetWorkers(it) }
        ).toTypedArray()
        )

    /**
     * Returns the cardinality of `input_dataset`.
     *  Returns the cardinality of `input_dataset`.
     *
     * @param inputDataset A variant tensor representing the dataset to return cardinality for.
     * @return a new instance of DatasetCardinality
     * @see org.tensorflow.op.DataOps.datasetCardinality
     */
    public fun datasetCardinality(inputDataset: Operand<out TType>): DatasetCardinality =
            java.datasetCardinality(    
        inputDataset
        )

    /**
     * Creates a dataset from the given `graph_def`.
     *  Creates a dataset from the provided `graph_def`.
     *
     * @param graphDef The graph representation of the dataset (as serialized GraphDef).
     * @return a new instance of DatasetFromGraph
     * @see org.tensorflow.op.DataOps.datasetFromGraph
     */
    public fun datasetFromGraph(graphDef: Operand<TString>): DatasetFromGraph =
            java.datasetFromGraph(    
        graphDef
        )

    /**
     * Returns a serialized GraphDef representing `input_dataset`.
     *  Returns a graph representation for `input_dataset`.
     *
     * @param inputDataset A variant tensor representing the dataset to return the graph
     * representation for.
     * @param options carries optional attribute values
     * @return a new instance of DatasetToGraph
     * @see org.tensorflow.op.DataOps.datasetToGraph
     * @param externalStatePolicy Sets the externalStatePolicy option.
     *
     * @param externalStatePolicy the externalStatePolicy option
     * @return this Options instance.
     * @param stripDeviceAssignment Sets the stripDeviceAssignment option.
     *
     * @param stripDeviceAssignment the stripDeviceAssignment option
     * @return this Options instance.
     */
    public fun datasetToGraph(
        inputDataset: Operand<out TType>,
        externalStatePolicy: Long? = null,
        stripDeviceAssignment: Boolean? = null
    ): DatasetToGraph = java.datasetToGraph(    
        inputDataset,
        *listOfNotNull(
            externalStatePolicy?.let{ org.tensorflow.op.data.DatasetToGraph.externalStatePolicy(it) },
            stripDeviceAssignment?.let{ org.tensorflow.op.data.DatasetToGraph.stripDeviceAssignment(it) }
        ).toTypedArray()
        )

    /**
     * Outputs the single element from the given dataset.
     *
     * @param dataset A handle to a dataset that contains a single element.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of DatasetToSingleElement
     * @see org.tensorflow.op.DataOps.datasetToSingleElement
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun datasetToSingleElement(
        dataset: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): DatasetToSingleElement = java.datasetToSingleElement(    
        dataset,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.DatasetToSingleElement.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Writes the given dataset to the given file using the TFRecord format.
     *
     * @param inputDataset A variant tensor representing the dataset to write.
     * @param filename A scalar string tensor representing the filename to use.
     * @param compressionType A scalar string tensor containing either (i) the empty string (no
     *  compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
     * @return a new instance of DatasetToTfRecord
     * @see org.tensorflow.op.DataOps.datasetToTfRecord
     */
    public fun datasetToTfRecord(
        inputDataset: Operand<out TType>,
        filename: Operand<TString>,
        compressionType: Operand<TString>
    ): DatasetToTfRecord = java.datasetToTfRecord(    
        inputDataset,
        filename,
        compressionType
        )

    /**
     * A container for an iterator resource.
     *
     * @param handle A handle to the iterator to delete.
     * @param deleter A variant deleter.
     * @return a new instance of DeleteIterator
     * @see org.tensorflow.op.DataOps.deleteIterator
     */
    public fun deleteIterator(handle: Operand<out TType>, deleter: Operand<out TType>):
            DeleteIterator = java.deleteIterator(    
        handle,
        deleter
        )

    /**
     * Creates a dataset that batches input elements into a SparseTensor.
     *
     * @param inputDataset A handle to an input dataset. Must have a single component.
     * @param batchSize A scalar representing the number of elements to accumulate in a
     *  batch.
     * @param rowShape A vector representing the dense shape of each row in the produced
     *  SparseTensor. The shape may be partially specified, using `-1` to indicate
     *  that a particular dimension should use the maximum size of all batch elements.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of DenseToSparseBatchDataset
     * @see org.tensorflow.op.DataOps.denseToSparseBatchDataset
     */
    public fun denseToSparseBatchDataset(
        inputDataset: Operand<out TType>,
        batchSize: Operand<TInt64>,
        rowShape: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): DenseToSparseBatchDataset = java.denseToSparseBatchDataset(    
        inputDataset,
        batchSize,
        rowShape,
        outputTypes,
        outputShapes
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
    public fun deserializeIterator(resourceHandle: Operand<out TType>, serialized: Operand<out
            TType>): DeserializeIterator = java.deserializeIterator(    
        resourceHandle,
        serialized
        )

    /**
     * A substitute for `InterleaveDataset` on a fixed list of `N` datasets.
     *
     * @param selectorInputDataset A dataset of scalar `DT_INT64` elements that determines which of
     * the
     *  `N` data inputs should produce the next output element.
     * @param dataInputDatasets `N` datasets with the same type that will be interleaved according
     * to
     *  the values of `selector_input_dataset`.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of DirectedInterleaveDataset
     * @see org.tensorflow.op.DataOps.directedInterleaveDataset
     * @param stopOnEmptyDataset Sets the stopOnEmptyDataset option.
     *
     * @param stopOnEmptyDataset the stopOnEmptyDataset option
     * @return this Options instance.
     */
    public fun directedInterleaveDataset(
        selectorInputDataset: Operand<out TType>,
        dataInputDatasets: Iterable<Operand<out TType>>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        stopOnEmptyDataset: Boolean? = null
    ): DirectedInterleaveDataset = java.directedInterleaveDataset(    
        selectorInputDataset,
        dataInputDatasets,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            stopOnEmptyDataset?.let{
            org.tensorflow.op.data.DirectedInterleaveDataset.stopOnEmptyDataset(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset containing elements of first component of `input_dataset` having true in
     * the last component.
     *
     * @param inputDataset The inputDataset value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of FilterByLastComponentDataset
     * @see org.tensorflow.op.DataOps.filterByLastComponentDataset
     */
    public fun filterByLastComponentDataset(
        inputDataset: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): FilterByLastComponentDataset = java.filterByLastComponentDataset(    
        inputDataset,
        outputTypes,
        outputShapes
        )

    /**
     * Creates a dataset containing elements of `input_dataset` matching `predicate`.
     *  The `predicate` function must return a scalar boolean and accept the
     *  following arguments:
     *  <ul>
     *  <li>One tensor for each component of an element of `input_dataset`.</li>
     *  <li>One tensor for each value in `other_arguments`.</li>
     *  </ul>
     *
     * @param inputDataset The inputDataset value
     * @param otherArguments A list of tensors, typically values that were captured when
     *  building a closure for `predicate`.
     * @param predicate A function returning a scalar boolean.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of FilterDataset
     * @see org.tensorflow.op.DataOps.filterDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun filterDataset(
        inputDataset: Operand<out TType>,
        otherArguments: Iterable<Operand<*>>,
        predicate: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): FilterDataset = java.filterDataset(    
        inputDataset,
        otherArguments,
        predicate,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.FilterDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset by applying `tf.data.Options` to `input_dataset`.
     *
     * @param inputDataset A variant tensor representing the input dataset.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of FinalizeDataset
     * @see org.tensorflow.op.DataOps.finalizeDataset
     * @param hasCapturedRef Sets the hasCapturedRef option.
     *
     * @param hasCapturedRef the hasCapturedRef option
     * @return this Options instance.
     */
    public fun finalizeDataset(
        inputDataset: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        hasCapturedRef: Boolean? = null
    ): FinalizeDataset = java.finalizeDataset(    
        inputDataset,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            hasCapturedRef?.let{ org.tensorflow.op.data.FinalizeDataset.hasCapturedRef(it) }
        ).toTypedArray()
        )

    /**
     * The FixedLengthRecordDatasetV2 operation
     *
     * @param filenames The filenames value
     * @param headerBytes The headerBytes value
     * @param recordBytes The recordBytes value
     * @param footerBytes The footerBytes value
     * @param bufferSize The bufferSize value
     * @param compressionType The compressionType value
     * @param options carries optional attribute values
     * @return a new instance of FixedLengthRecordDataset
     * @see org.tensorflow.op.DataOps.fixedLengthRecordDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun fixedLengthRecordDataset(
        filenames: Operand<TString>,
        headerBytes: Operand<TInt64>,
        recordBytes: Operand<TInt64>,
        footerBytes: Operand<TInt64>,
        bufferSize: Operand<TInt64>,
        compressionType: Operand<TString>,
        metadata: String? = null
    ): FixedLengthRecordDataset = java.fixedLengthRecordDataset(    
        filenames,
        headerBytes,
        recordBytes,
        footerBytes,
        bufferSize,
        compressionType,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.FixedLengthRecordDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that applies `f` to the outputs of `input_dataset`.
     *  Unlike MapDataset, the `f` in FlatMapDataset is expected to return a
     *  Dataset variant, and FlatMapDataset will flatten successive results
     *  into a single Dataset.
     *
     * @param inputDataset The inputDataset value
     * @param otherArguments The otherArguments value
     * @param f A function mapping elements of `input_dataset`, concatenated with
     *  `other_arguments`, to a Dataset variant that contains elements matching
     *  `output_types` and `output_shapes`.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of FlatMapDataset
     * @see org.tensorflow.op.DataOps.flatMapDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun flatMapDataset(
        inputDataset: Operand<out TType>,
        otherArguments: Iterable<Operand<*>>,
        f: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): FlatMapDataset = java.flatMapDataset(    
        inputDataset,
        otherArguments,
        f,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.FlatMapDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that invokes a function to generate elements.
     *
     * @param initFuncOtherArgs The initFuncOtherArgs value
     * @param nextFuncOtherArgs The nextFuncOtherArgs value
     * @param finalizeFuncOtherArgs The finalizeFuncOtherArgs value
     * @param initFunc The value of the initFunc attribute
     * @param nextFunc The value of the nextFunc attribute
     * @param finalizeFunc The value of the finalizeFunc attribute
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of GeneratorDataset
     * @see org.tensorflow.op.DataOps.generatorDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun generatorDataset(
        initFuncOtherArgs: Iterable<Operand<*>>,
        nextFuncOtherArgs: Iterable<Operand<*>>,
        finalizeFuncOtherArgs: Iterable<Operand<*>>,
        initFunc: ConcreteFunction,
        nextFunc: ConcreteFunction,
        finalizeFunc: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): GeneratorDataset = java.generatorDataset(    
        initFuncOtherArgs,
        nextFuncOtherArgs,
        finalizeFuncOtherArgs,
        initFunc,
        nextFunc,
        finalizeFunc,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.GeneratorDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that computes a group-by on `input_dataset`.
     *  Creates a dataset that computes a group-by on `input_dataset`.
     *
     * @param inputDataset A variant tensor representing the input dataset.
     * @param keyFuncOtherArguments A list of tensors, typically values that were captured when
     *  building a closure for `key_func`.
     * @param initFuncOtherArguments A list of tensors, typically values that were captured when
     *  building a closure for `init_func`.
     * @param reduceFuncOtherArguments A list of tensors, typically values that were captured when
     *  building a closure for `reduce_func`.
     * @param finalizeFuncOtherArguments A list of tensors, typically values that were captured when
     *  building a closure for `finalize_func`.
     * @param keyFunc A function mapping an element of `input_dataset`, concatenated
     *  with `key_func_other_arguments` to a scalar value of type DT_INT64.
     * @param initFunc A function mapping a key of type DT_INT64, concatenated with
     *  `init_func_other_arguments` to the initial reducer state.
     * @param reduceFunc A function mapping the current reducer state and an element of
     * `input_dataset`,
     *  concatenated with `reduce_func_other_arguments` to a new reducer state.
     * @param finalizeFunc A function mapping the final reducer state to an output element.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of GroupByReducerDataset
     * @see org.tensorflow.op.DataOps.groupByReducerDataset
     */
    public fun groupByReducerDataset(
        inputDataset: Operand<out TType>,
        keyFuncOtherArguments: Iterable<Operand<*>>,
        initFuncOtherArguments: Iterable<Operand<*>>,
        reduceFuncOtherArguments: Iterable<Operand<*>>,
        finalizeFuncOtherArguments: Iterable<Operand<*>>,
        keyFunc: ConcreteFunction,
        initFunc: ConcreteFunction,
        reduceFunc: ConcreteFunction,
        finalizeFunc: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): GroupByReducerDataset = java.groupByReducerDataset(    
        inputDataset,
        keyFuncOtherArguments,
        initFuncOtherArguments,
        reduceFuncOtherArguments,
        finalizeFuncOtherArguments,
        keyFunc,
        initFunc,
        reduceFunc,
        finalizeFunc,
        outputTypes,
        outputShapes
        )

    /**
     * Creates a dataset that computes a windowed group-by on `input_dataset`.
     *  // TODO(mrry): Support non-int64 keys.
     *
     * @param inputDataset The inputDataset value
     * @param keyFuncOtherArguments The keyFuncOtherArguments value
     * @param reduceFuncOtherArguments The reduceFuncOtherArguments value
     * @param windowSizeFuncOtherArguments The windowSizeFuncOtherArguments value
     * @param keyFunc A function mapping an element of `input_dataset`, concatenated
     *  with `key_func_other_arguments` to a scalar value of type DT_INT64.
     * @param reduceFunc The value of the reduceFunc attribute
     * @param windowSizeFunc The value of the windowSizeFunc attribute
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of GroupByWindowDataset
     * @see org.tensorflow.op.DataOps.groupByWindowDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun groupByWindowDataset(
        inputDataset: Operand<out TType>,
        keyFuncOtherArguments: Iterable<Operand<*>>,
        reduceFuncOtherArguments: Iterable<Operand<*>>,
        windowSizeFuncOtherArguments: Iterable<Operand<*>>,
        keyFunc: ConcreteFunction,
        reduceFunc: ConcreteFunction,
        windowSizeFunc: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): GroupByWindowDataset = java.groupByWindowDataset(    
        inputDataset,
        keyFuncOtherArguments,
        reduceFuncOtherArguments,
        windowSizeFuncOtherArguments,
        keyFunc,
        reduceFunc,
        windowSizeFunc,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.GroupByWindowDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that contains the elements of `input_dataset` ignoring errors.
     *
     * @param inputDataset The inputDataset value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of IgnoreErrorsDataset
     * @see org.tensorflow.op.DataOps.ignoreErrorsDataset
     * @param logWarning Sets the logWarning option.
     *
     * @param logWarning the logWarning option
     * @return this Options instance.
     */
    public fun ignoreErrorsDataset(
        inputDataset: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        logWarning: Boolean? = null
    ): IgnoreErrorsDataset = java.ignoreErrorsDataset(    
        inputDataset,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            logWarning?.let{ org.tensorflow.op.data.IgnoreErrorsDataset.logWarning(it) }
        ).toTypedArray()
        )

    /**
     * The InitializeTableFromDataset operation
     *
     * @param tableHandle The tableHandle value
     * @param dataset The dataset value
     * @return a new instance of InitializeTableFromDataset
     * @see org.tensorflow.op.DataOps.initializeTableFromDataset
     */
    public fun initializeTableFromDataset(tableHandle: Operand<out TType>, dataset: Operand<out
            TType>): InitializeTableFromDataset = java.initializeTableFromDataset(    
        tableHandle,
        dataset
        )

    /**
     * Creates a dataset that applies `f` to the outputs of `input_dataset`.
     *  Unlike MapDataset, the `f` in InterleaveDataset is expected to return
     *  a Dataset variant, and InterleaveDataset will flatten successive
     *  results into a single Dataset. Unlike FlatMapDataset,
     *  InterleaveDataset will interleave sequences of up to `block_length`
     *  consecutive elements from `cycle_length` input elements.
     *
     * @param inputDataset The inputDataset value
     * @param otherArguments The otherArguments value
     * @param cycleLength The cycleLength value
     * @param blockLength The blockLength value
     * @param f A function mapping elements of `input_dataset`, concatenated with
     *  `other_arguments`, to a Dataset variant that contains elements matching
     *  `output_types` and `output_shapes`.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of InterleaveDataset
     * @see org.tensorflow.op.DataOps.interleaveDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun interleaveDataset(
        inputDataset: Operand<out TType>,
        otherArguments: Iterable<Operand<*>>,
        cycleLength: Operand<TInt64>,
        blockLength: Operand<TInt64>,
        f: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): InterleaveDataset = java.interleaveDataset(    
        inputDataset,
        otherArguments,
        cycleLength,
        blockLength,
        f,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.InterleaveDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * The IteratorV2 operation
     *
     * @param sharedName The value of the sharedName attribute
     * @param container The value of the container attribute
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of Iterator
     * @see org.tensorflow.op.DataOps.iterator
     */
    public fun iterator(
        sharedName: String,
        container: String,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): Iterator = java.iterator(    
        sharedName,
        container,
        outputTypes,
        outputShapes
        )

    /**
     * Gets the next output from the given iterator .
     *
     * @param iterator The iterator value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of IteratorGetNext
     * @see org.tensorflow.op.DataOps.iteratorGetNext
     */
    public fun iteratorGetNext(
        iterator: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): IteratorGetNext = java.iteratorGetNext(    
        iterator,
        outputTypes,
        outputShapes
        )

    /**
     * Gets the next output from the given iterator as an Optional variant.
     *
     * @param iterator The iterator value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of IteratorGetNextAsOptional
     * @see org.tensorflow.op.DataOps.iteratorGetNextAsOptional
     */
    public fun iteratorGetNextAsOptional(
        iterator: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): IteratorGetNextAsOptional = java.iteratorGetNextAsOptional(    
        iterator,
        outputTypes,
        outputShapes
        )

    /**
     * Gets the next output from the given iterator.
     *  This operation is a synchronous version IteratorGetNext. It should only be used
     *  in situations where the iterator does not block the calling thread, or where
     *  the calling thread is not a member of the thread pool used to execute parallel
     *  operations (e.g. in eager mode).
     *
     * @param iterator The iterator value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of IteratorGetNextSync
     * @see org.tensorflow.op.DataOps.iteratorGetNextSync
     */
    public fun iteratorGetNextSync(
        iterator: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
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
    public fun iteratorToStringHandle(resourceHandle: Operand<out TType>): IteratorToStringHandle =
            java.iteratorToStringHandle(    
        resourceHandle
        )

    /**
     * Creates a dataset that emits the key-value pairs in one or more LMDB files.
     *  The Lightning Memory-Mapped Database Manager, or LMDB, is an embedded binary
     *  key-value database. This dataset can read the contents of LMDB database files,
     *  the names of which generally have the `.mdb` suffix.
     *  
     * Each output element consists of a key-value pair represented as a pair of
     *  scalar string `Tensor`s, where the first `Tensor` contains the key and the
     *  second `Tensor` contains the value.
     *  
     * LMDB uses different file formats on big- and little-endian machines.
     *  `data.LMDBDataset` can only read files in the format of the host machine.
     *
     * @param filenames A scalar or a vector containing the name(s) of the binary file(s) to be
     *  read.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of LMDBDataset
     * @see org.tensorflow.op.DataOps.lMDBDataset
     */
    public fun lMDBDataset(
        filenames: Operand<TString>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): LMDBDataset = java.lMDBDataset(    
        filenames,
        outputTypes,
        outputShapes
        )

    /**
     * Records the latency of producing `input_dataset` elements in a StatsAggregator.
     *
     * @param inputDataset The inputDataset value
     * @param tag The tag value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of LatencyStatsDataset
     * @see org.tensorflow.op.DataOps.latencyStatsDataset
     */
    public fun latencyStatsDataset(
        inputDataset: Operand<out TType>,
        tag: Operand<TString>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): LatencyStatsDataset = java.latencyStatsDataset(    
        inputDataset,
        tag,
        outputTypes,
        outputShapes
        )

    /**
     * Creates a dataset that applies `f` to the outputs of `input_dataset`.
     *  The resulting dataset is similar to the `InterleaveDataset`, with the exception
     *  that if retrieving the next value from a dataset would cause the requester to
     *  block, it will skip that input dataset. This dataset is especially useful
     *  when loading data from a variable-latency datastores (e.g. HDFS, GCS), as it
     *  allows the training step to proceed so long as some data is available.
     *  
     * !! WARNING !! This dataset is not deterministic!
     *
     * @param inputDataset The inputDataset value
     * @param otherArguments The otherArguments value
     * @param cycleLength The cycleLength value
     * @param blockLength The blockLength value
     * @param bufferOutputElements The bufferOutputElements value
     * @param prefetchInputElements The prefetchInputElements value
     * @param f A function mapping elements of `input_dataset`, concatenated with
     *  `other_arguments`, to a Dataset variant that contains elements matching
     *  `output_types` and `output_shapes`.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of LegacyParallelInterleaveDataset
     * @see org.tensorflow.op.DataOps.legacyParallelInterleaveDataset
     * @param deterministic Sets the deterministic option.
     *
     * @param deterministic the deterministic option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun legacyParallelInterleaveDataset(
        inputDataset: Operand<out TType>,
        otherArguments: Iterable<Operand<*>>,
        cycleLength: Operand<TInt64>,
        blockLength: Operand<TInt64>,
        bufferOutputElements: Operand<TInt64>,
        prefetchInputElements: Operand<TInt64>,
        f: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        deterministic: String? = null,
        metadata: String? = null
    ): LegacyParallelInterleaveDataset = java.legacyParallelInterleaveDataset(    
        inputDataset,
        otherArguments,
        cycleLength,
        blockLength,
        bufferOutputElements,
        prefetchInputElements,
        f,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            deterministic?.let{ org.tensorflow.op.data.LegacyParallelInterleaveDataset.deterministic(it)
            },
            metadata?.let{ org.tensorflow.op.data.LegacyParallelInterleaveDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * The LoadDataset operation
     *
     * @param path The path value
     * @param readerFuncOtherArgs The readerFuncOtherArgs value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param readerFunc The value of the readerFunc attribute
     * @param options carries optional attribute values
     * @return a new instance of LoadDataset
     * @see org.tensorflow.op.DataOps.loadDataset
     * @param compression Sets the compression option.
     *
     * @param compression the compression option
     * @return this Options instance.
     */
    public fun loadDataset(
        path: Operand<TString>,
        readerFuncOtherArgs: Iterable<Operand<*>>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        readerFunc: ConcreteFunction,
        compression: String? = null
    ): LoadDataset = java.loadDataset(    
        path,
        readerFuncOtherArgs,
        outputTypes,
        outputShapes,
        readerFunc,
        *listOfNotNull(
            compression?.let{ org.tensorflow.op.data.LoadDataset.compression(it) }
        ).toTypedArray()
        )

    /**
     * Makes a new iterator from the given `dataset` and stores it in `iterator`.
     *  This operation may be executed multiple times. Each execution will reset the
     *  iterator in `iterator` to the first element of `dataset`.
     *
     * @param dataset The dataset value
     * @param iterator The iterator value
     * @return a new instance of MakeIterator
     * @see org.tensorflow.op.DataOps.makeIterator
     */
    public fun makeIterator(dataset: Operand<out TType>, iterator: Operand<out TType>): MakeIterator
            = java.makeIterator(    
        dataset,
        iterator
        )

    /**
     * Creates a dataset that fuses mapping with batching.
     *  Creates a dataset that applies `f` to the outputs of `input_dataset` and then
     *  batches `batch_size` of them.
     *  
     * Unlike a &quot;MapDataset&quot;, which applies `f` sequentially, this dataset invokes up
     *  to `batch_size * num_parallel_batches` copies of `f` in parallel.
     *
     * @param inputDataset A variant tensor representing the input dataset.
     * @param otherArguments A list of tensors, typically values that were captured when building a
     * closure
     *  for `f`.
     * @param batchSize A scalar representing the number of elements to accumulate in a
     *  batch. It determines the number of concurrent invocations of `f` that process
     *  elements from `input_dataset` in parallel.
     * @param numParallelCalls A scalar representing the maximum number of parallel invocations of
     * the `map_fn`
     *  function. Applying the `map_fn` on consecutive input elements in parallel has
     *  the potential to improve input pipeline throughput.
     * @param dropRemainder A scalar representing whether the last batch should be dropped in case
     * its size
     *  is smaller than desired.
     * @param f A function to apply to the outputs of `input_dataset`.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of MapAndBatchDataset
     * @see org.tensorflow.op.DataOps.mapAndBatchDataset
     * @param preserveCardinality Sets the preserveCardinality option.
     *
     * @param preserveCardinality the preserveCardinality option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun mapAndBatchDataset(
        inputDataset: Operand<out TType>,
        otherArguments: Iterable<Operand<*>>,
        batchSize: Operand<TInt64>,
        numParallelCalls: Operand<TInt64>,
        dropRemainder: Operand<TBool>,
        f: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        preserveCardinality: Boolean? = null,
        metadata: String? = null
    ): MapAndBatchDataset = java.mapAndBatchDataset(    
        inputDataset,
        otherArguments,
        batchSize,
        numParallelCalls,
        dropRemainder,
        f,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            preserveCardinality?.let{ org.tensorflow.op.data.MapAndBatchDataset.preserveCardinality(it) },
            metadata?.let{ org.tensorflow.op.data.MapAndBatchDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that applies `f` to the outputs of `input_dataset`.
     *
     * @param inputDataset The inputDataset value
     * @param otherArguments The otherArguments value
     * @param f The value of the f attribute
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of MapDataset
     * @see org.tensorflow.op.DataOps.mapDataset
     * @param useInterOpParallelism Sets the useInterOpParallelism option.
     *
     * @param useInterOpParallelism the useInterOpParallelism option
     * @return this Options instance.
     * @param preserveCardinality Sets the preserveCardinality option.
     *
     * @param preserveCardinality the preserveCardinality option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun mapDataset(
        inputDataset: Operand<out TType>,
        otherArguments: Iterable<Operand<*>>,
        f: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        useInterOpParallelism: Boolean? = null,
        preserveCardinality: Boolean? = null,
        metadata: String? = null
    ): MapDataset = java.mapDataset(    
        inputDataset,
        otherArguments,
        f,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            useInterOpParallelism?.let{ org.tensorflow.op.data.MapDataset.useInterOpParallelism(it) },
            preserveCardinality?.let{ org.tensorflow.op.data.MapDataset.preserveCardinality(it) },
            metadata?.let{ org.tensorflow.op.data.MapDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * The MatchingFilesDataset operation
     *
     * @param patterns The patterns value
     * @return a new instance of MatchingFilesDataset
     * @see org.tensorflow.op.DataOps.matchingFilesDataset
     */
    public fun matchingFilesDataset(patterns: Operand<TString>): MatchingFilesDataset =
            java.matchingFilesDataset(    
        patterns
        )

    /**
     * Creates a dataset that overrides the maximum intra-op parallelism.
     *
     * @param inputDataset The inputDataset value
     * @param maxIntraOpParallelism Identifies the maximum intra-op parallelism to use.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of MaxIntraOpParallelismDataset
     * @see org.tensorflow.op.DataOps.maxIntraOpParallelismDataset
     */
    public fun maxIntraOpParallelismDataset(
        inputDataset: Operand<out TType>,
        maxIntraOpParallelism: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): MaxIntraOpParallelismDataset = java.maxIntraOpParallelismDataset(    
        inputDataset,
        maxIntraOpParallelism,
        outputTypes,
        outputShapes
        )

    /**
     * Identity transformation that models performance.
     *  Identity transformation that models performance.
     *
     * @param inputDataset A variant tensor representing the input dataset.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of ModelDataset
     * @see org.tensorflow.op.DataOps.modelDataset
     * @param algorithm Sets the algorithm option.
     *
     * @param algorithm the algorithm option
     * @return this Options instance.
     * @param cpuBudget Sets the cpuBudget option.
     *
     * @param cpuBudget the cpuBudget option
     * @return this Options instance.
     * @param ramBudget Sets the ramBudget option.
     *
     * @param ramBudget the ramBudget option
     * @return this Options instance.
     */
    public fun modelDataset(
        inputDataset: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        algorithm: Long? = null,
        cpuBudget: Long? = null,
        ramBudget: Long? = null
    ): ModelDataset = java.modelDataset(    
        inputDataset,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            algorithm?.let{ org.tensorflow.op.data.ModelDataset.algorithm(it) },
            cpuBudget?.let{ org.tensorflow.op.data.ModelDataset.cpuBudget(it) },
            ramBudget?.let{ org.tensorflow.op.data.ModelDataset.ramBudget(it) }
        ).toTypedArray()
        )

    /**
     * The NonSerializableDataset operation
     *
     * @param inputDataset The inputDataset value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of NonSerializableDataset
     * @see org.tensorflow.op.DataOps.nonSerializableDataset
     */
    public fun nonSerializableDataset(
        inputDataset: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): NonSerializableDataset = java.nonSerializableDataset(    
        inputDataset,
        outputTypes,
        outputShapes
        )

    /**
     * Makes a &quot;one-shot&quot; iterator that can be iterated only once.
     *  A one-shot iterator bundles the logic for defining the dataset and
     *  the state of the iterator in a single op, which allows simple input
     *  pipelines to be defined without an additional initialization
     *  (&quot;MakeIterator&quot;) step.
     *  
     * One-shot iterators have the following limitations:
     *  <ul>
     *  <li>They do not support parameterization: all logic for creating the underlying
     *  dataset must be bundled in the `dataset_factory` function.</li>
     *  <li>They are not resettable. Once a one-shot iterator reaches the end of its
     *  underlying dataset, subsequent &quot;IteratorGetNext&quot; operations on that
     *  iterator will always produce an `OutOfRange` error.</li>
     *  </ul>
     *  
     * For greater flexibility, use &quot;Iterator&quot; and &quot;MakeIterator&quot; to define
     *  an iterator using an arbitrary subgraph, which may capture tensors
     *  (including fed values) as parameters, and which may be reset multiple
     *  times by rerunning &quot;MakeIterator&quot;.
     *
     * @param datasetFactory A function of type `() -> DT_VARIANT`, where the returned
     *  DT_VARIANT is a dataset.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of OneShotIterator
     * @see org.tensorflow.op.DataOps.oneShotIterator
     * @param container Sets the container option.
     *
     * @param container the container option
     * @return this Options instance.
     * @param sharedName Sets the sharedName option.
     *
     * @param sharedName the sharedName option
     * @return this Options instance.
     */
    public fun oneShotIterator(
        datasetFactory: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        container: String? = null,
        sharedName: String? = null
    ): OneShotIterator = java.oneShotIterator(    
        datasetFactory,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            container?.let{ org.tensorflow.op.data.OneShotIterator.container(it) },
            sharedName?.let{ org.tensorflow.op.data.OneShotIterator.sharedName(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset by applying related optimizations to `input_dataset`.
     *  Creates a dataset by applying related optimizations to `input_dataset`.
     *
     * @param inputDataset A variant tensor representing the input dataset.
     * @param optimizationsEnabled A `tf.string` vector `tf.Tensor` identifying user enabled
     * optimizations.
     * @param optimizationsDisabled A `tf.string` vector `tf.Tensor` identifying user disabled
     * optimizations.
     * @param optimizationsDefault A `tf.string` vector `tf.Tensor` identifying optimizations by
     * default.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of OptimizeDataset
     * @see org.tensorflow.op.DataOps.optimizeDataset
     * @param optimizationConfigs Sets the optimizationConfigs option.
     *
     * @param optimizationConfigs the optimizationConfigs option
     * @return this Options instance.
     */
    public fun optimizeDataset(
        inputDataset: Operand<out TType>,
        optimizationsEnabled: Operand<TString>,
        optimizationsDisabled: Operand<TString>,
        optimizationsDefault: Operand<TString>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        optimizationConfigs: List<String>? = null
    ): OptimizeDataset = java.optimizeDataset(    
        inputDataset,
        optimizationsEnabled,
        optimizationsDisabled,
        optimizationsDefault,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            optimizationConfigs?.let{ org.tensorflow.op.data.OptimizeDataset.optimizationConfigs(it) }
        ).toTypedArray()
        )

    /**
     * Constructs an Optional variant from a tuple of tensors.
     *
     * @param components The components value
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
     * @param optional The optional value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of OptionalGetValue
     * @see org.tensorflow.op.DataOps.optionalGetValue
     */
    public fun optionalGetValue(
        optional: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): OptionalGetValue = java.optionalGetValue(    
        optional,
        outputTypes,
        outputShapes
        )

    /**
     * Returns true if and only if the given Optional variant has a value.
     *
     * @param optional The optional value
     * @return a new instance of OptionalHasValue
     * @see org.tensorflow.op.DataOps.optionalHasValue
     */
    public fun optionalHasValue(optional: Operand<out TType>): OptionalHasValue =
            java.optionalHasValue(    
        optional
        )

    /**
     * Creates an Optional variant with no value.
     *
     * @return a new instance of OptionalNone
     * @see org.tensorflow.op.DataOps.optionalNone
     */
    public fun optionalNone(): OptionalNone = java.optionalNone(    
        
        )

    /**
     * Creates a dataset by attaching tf.data.Options to `input_dataset`.
     *
     * @param inputDataset A variant tensor representing the input dataset.
     * @param serializedOptions A `tf.string` scalar `tf.Tensor` of serialized `tf.data.Options`
     * protocol buffer.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of OptionsDataset
     * @see org.tensorflow.op.DataOps.optionsDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun optionsDataset(
        inputDataset: Operand<out TType>,
        serializedOptions: String,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): OptionsDataset = java.optionsDataset(    
        inputDataset,
        serializedOptions,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.OptionsDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that batches and pads `batch_size` elements from the input.
     *
     * @param inputDataset The inputDataset value
     * @param batchSize A scalar representing the number of elements to accumulate in a
     *  batch.
     * @param paddedShapes A list of int64 tensors representing the desired padded shapes
     *  of the corresponding output components. These shapes may be partially
     *  specified, using `-1` to indicate that a particular dimension should be
     *  padded to the maximum size of all batch elements.
     * @param paddingValues A list of scalars containing the padding value to use for
     *  each of the outputs.
     * @param dropRemainder A scalar representing whether the last batch should be dropped in case
     * its size
     *  is smaller than desired.
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of PaddedBatchDataset
     * @see org.tensorflow.op.DataOps.paddedBatchDataset
     * @param parallelCopy Sets the parallelCopy option.
     *
     * @param parallelCopy the parallelCopy option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun paddedBatchDataset(
        inputDataset: Operand<out TType>,
        batchSize: Operand<TInt64>,
        paddedShapes: Iterable<Operand<TInt64>>,
        paddingValues: Iterable<Operand<*>>,
        dropRemainder: Operand<TBool>,
        outputShapes: List<Shape>,
        parallelCopy: Boolean? = null,
        metadata: String? = null
    ): PaddedBatchDataset = java.paddedBatchDataset(    
        inputDataset,
        batchSize,
        paddedShapes,
        paddingValues,
        dropRemainder,
        outputShapes,
        *listOfNotNull(
            parallelCopy?.let{ org.tensorflow.op.data.PaddedBatchDataset.parallelCopy(it) },
            metadata?.let{ org.tensorflow.op.data.PaddedBatchDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * The ParallelBatchDataset operation
     *
     * @param inputDataset The inputDataset value
     * @param batchSize The batchSize value
     * @param numParallelCalls The numParallelCalls value
     * @param dropRemainder The dropRemainder value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of ParallelBatchDataset
     * @see org.tensorflow.op.DataOps.parallelBatchDataset
     * @param parallelCopy Sets the parallelCopy option.
     *
     * @param parallelCopy the parallelCopy option
     * @return this Options instance.
     * @param deterministic Sets the deterministic option.
     *
     * @param deterministic the deterministic option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun parallelBatchDataset(
        inputDataset: Operand<out TType>,
        batchSize: Operand<TInt64>,
        numParallelCalls: Operand<TInt64>,
        dropRemainder: Operand<TBool>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        parallelCopy: Boolean? = null,
        deterministic: String? = null,
        metadata: String? = null
    ): ParallelBatchDataset = java.parallelBatchDataset(    
        inputDataset,
        batchSize,
        numParallelCalls,
        dropRemainder,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            parallelCopy?.let{ org.tensorflow.op.data.ParallelBatchDataset.parallelCopy(it) },
            deterministic?.let{ org.tensorflow.op.data.ParallelBatchDataset.deterministic(it) },
            metadata?.let{ org.tensorflow.op.data.ParallelBatchDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that applies `f` to the outputs of `input_dataset`.
     *  The resulting dataset is similar to the `InterleaveDataset`, except that the
     *  dataset will fetch records from the interleaved datasets in parallel.
     *  
     * The `tf.data` Python API creates instances of this op from
     *  `Dataset.interleave()` when the `num_parallel_calls` parameter of that method
     *  is set to any value other than `None`.
     *  
     * By default, the output of this dataset will be deterministic, which may result
     *  in the dataset blocking if the next data item to be returned isn't available.
     *  In order to avoid head-of-line blocking, one can either set the `deterministic`
     *  attribute to &quot;false&quot;, or leave it as &quot;default&quot; and set the
     *  `experimental_deterministic` parameter of `tf.data.Options` to `False`.
     *  This can improve performance at the expense of non-determinism.
     *
     * @param inputDataset Dataset that produces a stream of arguments for the function `f`.
     * @param otherArguments Additional arguments to pass to `f` beyond those produced by
     * `input_dataset`.
     *  Evaluated once when the dataset is instantiated.
     * @param cycleLength Number of datasets (each created by applying `f` to the elements of
     *  `input_dataset`) among which the `ParallelInterleaveDatasetV2` will cycle in a
     *  round-robin fashion.
     * @param blockLength Number of elements at a time to produce from each interleaved invocation
     * of a
     *  dataset returned by `f`.
     * @param bufferOutputElements The number of elements each iterator being interleaved should
     * buffer (similar
     *  to the `.prefetch()` transformation for each interleaved iterator).
     * @param prefetchInputElements Determines the number of iterators to prefetch, allowing buffers
     * to warm up and
     *  data to be pre-fetched without blocking the main thread.
     * @param numParallelCalls Determines the number of threads that should be used for fetching
     * data from
     *  input datasets in parallel. The Python API `tf.data.experimental.AUTOTUNE`
     *  constant can be used to indicate that the level of parallelism should be autotuned.
     * @param f A function mapping elements of `input_dataset`, concatenated with
     *  `other_arguments`, to a Dataset variant that contains elements matching
     *  `output_types` and `output_shapes`.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of ParallelInterleaveDataset
     * @see org.tensorflow.op.DataOps.parallelInterleaveDataset
     * @param deterministic Sets the deterministic option.
     *
     * @param deterministic A string indicating the op-level determinism to use. Deterministic
     * controls
     *  whether the interleave is allowed to return elements out of order if the next
     *  element to be returned isn't available, but a later element is. Options are
     *  &quot;true&quot;, &quot;false&quot;, and &quot;default&quot;. &quot;default&quot; indicates
     * that determinism should be
     *  decided by the `experimental_deterministic` parameter of `tf.data.Options`.
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun parallelInterleaveDataset(
        inputDataset: Operand<out TType>,
        otherArguments: Iterable<Operand<*>>,
        cycleLength: Operand<TInt64>,
        blockLength: Operand<TInt64>,
        bufferOutputElements: Operand<TInt64>,
        prefetchInputElements: Operand<TInt64>,
        numParallelCalls: Operand<TInt64>,
        f: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        deterministic: String? = null,
        metadata: String? = null
    ): ParallelInterleaveDataset = java.parallelInterleaveDataset(    
        inputDataset,
        otherArguments,
        cycleLength,
        blockLength,
        bufferOutputElements,
        prefetchInputElements,
        numParallelCalls,
        f,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            deterministic?.let{ org.tensorflow.op.data.ParallelInterleaveDataset.deterministic(it) },
            metadata?.let{ org.tensorflow.op.data.ParallelInterleaveDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that applies `f` to the outputs of `input_dataset`.
     *  Unlike a &quot;MapDataset&quot;, which applies `f` sequentially, this dataset invokes up
     *  to `num_parallel_calls` copies of `f` in parallel.
     *
     * @param inputDataset The inputDataset value
     * @param otherArguments The otherArguments value
     * @param numParallelCalls The number of concurrent invocations of `f` that process
     *  elements from `input_dataset` in parallel.
     * @param f The value of the f attribute
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of ParallelMapDataset
     * @see org.tensorflow.op.DataOps.parallelMapDataset
     * @param useInterOpParallelism Sets the useInterOpParallelism option.
     *
     * @param useInterOpParallelism the useInterOpParallelism option
     * @return this Options instance.
     * @param deterministic Sets the deterministic option.
     *
     * @param deterministic the deterministic option
     * @return this Options instance.
     * @param preserveCardinality Sets the preserveCardinality option.
     *
     * @param preserveCardinality the preserveCardinality option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun parallelMapDataset(
        inputDataset: Operand<out TType>,
        otherArguments: Iterable<Operand<*>>,
        numParallelCalls: Operand<TInt64>,
        f: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        useInterOpParallelism: Boolean? = null,
        deterministic: String? = null,
        preserveCardinality: Boolean? = null,
        metadata: String? = null
    ): ParallelMapDataset = java.parallelMapDataset(    
        inputDataset,
        otherArguments,
        numParallelCalls,
        f,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            useInterOpParallelism?.let{
            org.tensorflow.op.data.ParallelMapDataset.useInterOpParallelism(it) },
            deterministic?.let{ org.tensorflow.op.data.ParallelMapDataset.deterministic(it) },
            preserveCardinality?.let{ org.tensorflow.op.data.ParallelMapDataset.preserveCardinality(it) },
            metadata?.let{ org.tensorflow.op.data.ParallelMapDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Transforms `input_dataset` containing `Example` protos as vectors of DT_STRING into a dataset
     * of `Tensor` or `SparseTensor` objects representing the parsed features.
     *
     * @param inputDataset The inputDataset value
     * @param numParallelCalls The numParallelCalls value
     * @param denseDefaults A dict mapping string keys to `Tensor`s.
     *  The keys of the dict must match the dense_keys of the feature.
     * @param sparseKeys A list of string keys in the examples features.
     *  The results for these keys will be returned as `SparseTensor` objects.
     * @param denseKeys A list of Ndense string Tensors (scalars).
     *  The keys expected in the Examples features associated with dense values.
     * @param sparseTypes A list of `DTypes` of the same length as `sparse_keys`.
     *  Only `tf.float32` (`FloatList`), `tf.int64` (`Int64List`),
     *  and `tf.string` (`BytesList`) are supported.
     * @param denseShapes List of tuples with the same length as `dense_keys`.
     *  The shape of the data for each dense feature referenced by `dense_keys`.
     *  Required for any input tensors identified by `dense_keys`.  Must be
     *  either fully defined, or may contain an unknown first dimension.
     *  An unknown first dimension means the feature is treated as having
     *  a variable number of blocks, and the output shape along this dimension
     *  is considered unknown at graph build time.  Padding is applied for
     *  minibatch elements smaller than the maximum number of blocks for the
     *  given feature along this dimension.
     * @param outputTypes The type list for the return values.
     * @param outputShapes The list of shapes being produced.
     * @param raggedValueTypes The value of the raggedValueTypes attribute
     * @param raggedSplitTypes The value of the raggedSplitTypes attribute
     * @param options carries optional attribute values
     * @return a new instance of ParseExampleDataset
     * @see org.tensorflow.op.DataOps.parseExampleDataset
     * @param deterministic Sets the deterministic option.
     *
     * @param deterministic A string indicating the op-level determinism to use. Deterministic
     * controls
     *  whether the dataset is allowed to return elements out of order if the next
     *  element to be returned isn't available, but a later element is. Options are
     *  &quot;true&quot;, &quot;false&quot;, and &quot;default&quot;. &quot;default&quot; indicates
     * that determinism should be
     *  decided by the `experimental_deterministic` parameter of `tf.data.Options`.
     * @return this Options instance.
     * @param raggedKeys Sets the raggedKeys option.
     *
     * @param raggedKeys the raggedKeys option
     * @return this Options instance.
     */
    public fun parseExampleDataset(
        inputDataset: Operand<out TType>,
        numParallelCalls: Operand<TInt64>,
        denseDefaults: Iterable<Operand<*>>,
        sparseKeys: List<String>,
        denseKeys: List<String>,
        sparseTypes: List<Class<out TType>>,
        denseShapes: List<Shape>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        raggedValueTypes: List<Class<out TType>>,
        raggedSplitTypes: List<Class<out TNumber>>,
        deterministic: String? = null,
        raggedKeys: List<String>? = null
    ): ParseExampleDataset = java.parseExampleDataset(    
        inputDataset,
        numParallelCalls,
        denseDefaults,
        sparseKeys,
        denseKeys,
        sparseTypes,
        denseShapes,
        outputTypes,
        outputShapes,
        raggedValueTypes,
        raggedSplitTypes,
        *listOfNotNull(
            deterministic?.let{ org.tensorflow.op.data.ParseExampleDataset.deterministic(it) },
            raggedKeys?.let{ org.tensorflow.op.data.ParseExampleDataset.raggedKeys(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that asynchronously prefetches elements from `input_dataset`.
     *
     * @param inputDataset The inputDataset value
     * @param bufferSize The maximum number of elements to buffer in an iterator over
     *  this dataset.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of PrefetchDataset
     * @see org.tensorflow.op.DataOps.prefetchDataset
     * @param slackPeriod Sets the slackPeriod option.
     *
     * @param slackPeriod the slackPeriod option
     * @return this Options instance.
     * @param legacyAutotune Sets the legacyAutotune option.
     *
     * @param legacyAutotune the legacyAutotune option
     * @return this Options instance.
     * @param bufferSizeMin Sets the bufferSizeMin option.
     *
     * @param bufferSizeMin the bufferSizeMin option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun prefetchDataset(
        inputDataset: Operand<out TType>,
        bufferSize: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        slackPeriod: Long? = null,
        legacyAutotune: Boolean? = null,
        bufferSizeMin: Long? = null,
        metadata: String? = null
    ): PrefetchDataset = java.prefetchDataset(    
        inputDataset,
        bufferSize,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            slackPeriod?.let{ org.tensorflow.op.data.PrefetchDataset.slackPeriod(it) },
            legacyAutotune?.let{ org.tensorflow.op.data.PrefetchDataset.legacyAutotune(it) },
            bufferSizeMin?.let{ org.tensorflow.op.data.PrefetchDataset.bufferSizeMin(it) },
            metadata?.let{ org.tensorflow.op.data.PrefetchDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that uses a custom thread pool to compute `input_dataset`.
     *
     * @param inputDataset The inputDataset value
     * @param numThreads Identifies the number of threads to use for the private threadpool.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of PrivateThreadPoolDataset
     * @see org.tensorflow.op.DataOps.privateThreadPoolDataset
     */
    public fun privateThreadPoolDataset(
        inputDataset: Operand<out TType>,
        numThreads: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): PrivateThreadPoolDataset = java.privateThreadPoolDataset(    
        inputDataset,
        numThreads,
        outputTypes,
        outputShapes
        )

    /**
     * Creates a Dataset that returns pseudorandom numbers.
     *  Creates a Dataset that returns a stream of uniformly distributed
     *  pseudorandom 64-bit signed integers.
     *  
     * In the TensorFlow Python API, you can instantiate this dataset via the
     *  class `tf.data.experimental.RandomDataset`.
     *  
     * Instances of this dataset are also created as a result of the
     *  `hoist_random_uniform` static optimization. Whether this optimization is
     *  performed is determined by the `experimental_optimization.hoist_random_uniform`
     *  option of `tf.data.Options`.
     *
     * @param seed A scalar seed for the random number generator. If either seed or
     *  seed2 is set to be non-zero, the random number generator is seeded
     *  by the given seed.  Otherwise, a random seed is used.
     * @param seed2 A second scalar seed to avoid seed collision.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of RandomDataset
     * @see org.tensorflow.op.DataOps.randomDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun randomDataset(
        seed: Operand<TInt64>,
        seed2: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): RandomDataset = java.randomDataset(    
        seed,
        seed2,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.RandomDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset with a range of values. Corresponds to python's xrange.
     *
     * @param start corresponds to start in python's xrange().
     * @param stop corresponds to stop in python's xrange().
     * @param step corresponds to step in python's xrange().
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of RangeDataset
     * @see org.tensorflow.op.DataOps.rangeDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun rangeDataset(
        start: Operand<TInt64>,
        stop: Operand<TInt64>,
        step: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): RangeDataset = java.rangeDataset(    
        start,
        stop,
        step,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.RangeDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that changes the batch size.
     *  Creates a dataset that rebatches elements from `input_dataset` into new batch
     *  sizes.
     *
     * @param inputDataset A variant tensor representing the input dataset.
     * @param batchSizes A vector of integers representing the size of batches to produce. These
     * values
     *  are cycled through in order.
     * @param dropRemainder The dropRemainder value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of RebatchDatasetV2
     * @see org.tensorflow.op.DataOps.rebatchDatasetV2
     */
    public fun rebatchDatasetV2(
        inputDataset: Operand<out TType>,
        batchSizes: Operand<TInt64>,
        dropRemainder: Operand<TBool>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): RebatchDatasetV2 = java.rebatchDatasetV2(    
        inputDataset,
        batchSizes,
        dropRemainder,
        outputTypes,
        outputShapes
        )

    /**
     * Reduces the input dataset to a singleton using a reduce function.
     *
     * @param inputDataset A variant tensor representing the input dataset.
     * @param initialState A nested structure of tensors, representing the initial state of the
     *  transformation.
     * @param otherArguments The otherArguments value
     * @param f A function that maps `(old_state, input_element)` to `new_state`. It must take
     *  two arguments and return a nested structures of tensors. The structure of
     *  `new_state` must match the structure of `initial_state`.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of ReduceDataset
     * @see org.tensorflow.op.DataOps.reduceDataset
     * @param useInterOpParallelism Sets the useInterOpParallelism option.
     *
     * @param useInterOpParallelism the useInterOpParallelism option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun reduceDataset(
        inputDataset: Operand<out TType>,
        initialState: Iterable<Operand<*>>,
        otherArguments: Iterable<Operand<*>>,
        f: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        useInterOpParallelism: Boolean? = null,
        metadata: String? = null
    ): ReduceDataset = java.reduceDataset(    
        inputDataset,
        initialState,
        otherArguments,
        f,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            useInterOpParallelism?.let{ org.tensorflow.op.data.ReduceDataset.useInterOpParallelism(it) },
            metadata?.let{ org.tensorflow.op.data.ReduceDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Registers a dataset with the tf.data service.
     *
     * @param dataset The dataset value
     * @param address The address value
     * @param protocol The protocol value
     * @param externalStatePolicy The value of the externalStatePolicy attribute
     * @param options carries optional attribute values
     * @return a new instance of RegisterDataset
     * @see org.tensorflow.op.DataOps.registerDataset
     * @param elementSpec Sets the elementSpec option.
     *
     * @param elementSpec the elementSpec option
     * @return this Options instance.
     */
    public fun registerDataset(
        dataset: Operand<out TType>,
        address: Operand<TString>,
        protocol: Operand<TString>,
        externalStatePolicy: Long,
        elementSpec: String? = null
    ): RegisterDataset = java.registerDataset(    
        dataset,
        address,
        protocol,
        externalStatePolicy,
        *listOfNotNull(
            elementSpec?.let{ org.tensorflow.op.data.RegisterDataset.elementSpec(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that emits the outputs of `input_dataset` `count` times.
     *
     * @param inputDataset The inputDataset value
     * @param count A scalar representing the number of times that `input_dataset` should
     *  be repeated. A value of `-1` indicates that it should be repeated infinitely.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of RepeatDataset
     * @see org.tensorflow.op.DataOps.repeatDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun repeatDataset(
        inputDataset: Operand<out TType>,
        count: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): RepeatDataset = java.repeatDataset(    
        inputDataset,
        count,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.RepeatDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that takes a Bernoulli sample of the contents of another dataset.
     *  There is no transformation in the `tf.data` Python API for creating this dataset.
     *  Instead, it is created as a result of the `filter_with_random_uniform_fusion`
     *  static optimization. Whether this optimization is performed is determined by the
     *  `experimental_optimization.filter_with_random_uniform_fusion` option of
     *  `tf.data.Options`.
     *
     * @param inputDataset The inputDataset value
     * @param rate A scalar representing the sample rate. Each element of `input_dataset` is
     *  retained with this probability, independent of all other elements.
     * @param seed A scalar representing seed of random number generator.
     * @param seed2 A scalar representing seed2 of random number generator.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of SamplingDataset
     * @see org.tensorflow.op.DataOps.samplingDataset
     */
    public fun samplingDataset(
        inputDataset: Operand<out TType>,
        rate: Operand<TFloat32>,
        seed: Operand<TInt64>,
        seed2: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): SamplingDataset = java.samplingDataset(    
        inputDataset,
        rate,
        seed,
        seed2,
        outputTypes,
        outputShapes
        )

    /**
     * The SaveDatasetV2 operation
     *
     * @param inputDataset The inputDataset value
     * @param path The path value
     * @param shardFuncOtherArgs The shardFuncOtherArgs value
     * @param shardFunc The value of the shardFunc attribute
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of SaveDataset
     * @see org.tensorflow.op.DataOps.saveDataset
     * @param compression Sets the compression option.
     *
     * @param compression the compression option
     * @return this Options instance.
     * @param useShardFunc Sets the useShardFunc option.
     *
     * @param useShardFunc the useShardFunc option
     * @return this Options instance.
     */
    public fun saveDataset(
        inputDataset: Operand<out TType>,
        path: Operand<TString>,
        shardFuncOtherArgs: Iterable<Operand<*>>,
        shardFunc: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        compression: String? = null,
        useShardFunc: Boolean? = null
    ): SaveDataset = java.saveDataset(    
        inputDataset,
        path,
        shardFuncOtherArgs,
        shardFunc,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            compression?.let{ org.tensorflow.op.data.SaveDataset.compression(it) },
            useShardFunc?.let{ org.tensorflow.op.data.SaveDataset.useShardFunc(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset successively reduces `f` over the elements of `input_dataset`.
     *
     * @param inputDataset The inputDataset value
     * @param initialState The initialState value
     * @param otherArguments The otherArguments value
     * @param f The value of the f attribute
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of ScanDataset
     * @see org.tensorflow.op.DataOps.scanDataset
     * @param preserveCardinality Sets the preserveCardinality option.
     *
     * @param preserveCardinality the preserveCardinality option
     * @return this Options instance.
     * @param useDefaultDevice Sets the useDefaultDevice option.
     *
     * @param useDefaultDevice the useDefaultDevice option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun scanDataset(
        inputDataset: Operand<out TType>,
        initialState: Iterable<Operand<*>>,
        otherArguments: Iterable<Operand<*>>,
        f: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        preserveCardinality: Boolean? = null,
        useDefaultDevice: Boolean? = null,
        metadata: String? = null
    ): ScanDataset = java.scanDataset(    
        inputDataset,
        initialState,
        otherArguments,
        f,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            preserveCardinality?.let{ org.tensorflow.op.data.ScanDataset.preserveCardinality(it) },
            useDefaultDevice?.let{ org.tensorflow.op.data.ScanDataset.useDefaultDevice(it) },
            metadata?.let{ org.tensorflow.op.data.ScanDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Converts the given `resource_handle` representing an iterator to a variant tensor.
     *
     * @param resourceHandle A handle to an iterator resource.
     * @param options carries optional attribute values
     * @return a new instance of SerializeIterator
     * @see org.tensorflow.op.DataOps.serializeIterator
     * @param externalStatePolicy Sets the externalStatePolicy option.
     *
     * @param externalStatePolicy the externalStatePolicy option
     * @return this Options instance.
     */
    public fun serializeIterator(resourceHandle: Operand<out TType>, externalStatePolicy: Long? =
            null): SerializeIterator = java.serializeIterator(    
        resourceHandle,
        *listOfNotNull(
            externalStatePolicy?.let{ org.tensorflow.op.data.SerializeIterator.externalStatePolicy(it) }
        ).toTypedArray()
        )

    /**
     * The SetStatsAggregatorDataset operation
     *
     * @param inputDataset The inputDataset value
     * @param statsAggregator The statsAggregator value
     * @param tag The tag value
     * @param counterPrefix The counterPrefix value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of SetStatsAggregatorDataset
     * @see org.tensorflow.op.DataOps.setStatsAggregatorDataset
     */
    public fun setStatsAggregatorDataset(
        inputDataset: Operand<out TType>,
        statsAggregator: Operand<out TType>,
        tag: Operand<TString>,
        counterPrefix: Operand<TString>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): SetStatsAggregatorDataset = java.setStatsAggregatorDataset(    
        inputDataset,
        statsAggregator,
        tag,
        counterPrefix,
        outputTypes,
        outputShapes
        )

    /**
     * Creates a `Dataset` that includes only 1/`num_shards` of this dataset.
     *
     * @param inputDataset The inputDataset value
     * @param numShards An integer representing the number of shards operating in parallel.
     * @param index An integer representing the current worker index.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of ShardDataset
     * @see org.tensorflow.op.DataOps.shardDataset
     * @param requireNonEmpty Sets the requireNonEmpty option.
     *
     * @param requireNonEmpty the requireNonEmpty option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun shardDataset(
        inputDataset: Operand<out TType>,
        numShards: Operand<TInt64>,
        index: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        requireNonEmpty: Boolean? = null,
        metadata: String? = null
    ): ShardDataset = java.shardDataset(    
        inputDataset,
        numShards,
        index,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            requireNonEmpty?.let{ org.tensorflow.op.data.ShardDataset.requireNonEmpty(it) },
            metadata?.let{ org.tensorflow.op.data.ShardDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * The ShuffleAndRepeatDatasetV2 operation
     *
     * @param inputDataset The inputDataset value
     * @param bufferSize The bufferSize value
     * @param seed The seed value
     * @param seed2 The seed2 value
     * @param count The count value
     * @param seedGenerator The seedGenerator value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of ShuffleAndRepeatDataset
     * @see org.tensorflow.op.DataOps.shuffleAndRepeatDataset
     * @param reshuffleEachIteration Sets the reshuffleEachIteration option.
     *
     * @param reshuffleEachIteration the reshuffleEachIteration option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun shuffleAndRepeatDataset(
        inputDataset: Operand<out TType>,
        bufferSize: Operand<TInt64>,
        seed: Operand<TInt64>,
        seed2: Operand<TInt64>,
        count: Operand<TInt64>,
        seedGenerator: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        reshuffleEachIteration: Boolean? = null,
        metadata: String? = null
    ): ShuffleAndRepeatDataset = java.shuffleAndRepeatDataset(    
        inputDataset,
        bufferSize,
        seed,
        seed2,
        count,
        seedGenerator,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            reshuffleEachIteration?.let{
            org.tensorflow.op.data.ShuffleAndRepeatDataset.reshuffleEachIteration(it) },
            metadata?.let{ org.tensorflow.op.data.ShuffleAndRepeatDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * The ShuffleDatasetV3 operation
     *
     * @param inputDataset The inputDataset value
     * @param bufferSize The bufferSize value
     * @param seed The seed value
     * @param seed2 The seed2 value
     * @param seedGenerator The seedGenerator value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of ShuffleDataset
     * @see org.tensorflow.op.DataOps.shuffleDataset
     * @param reshuffleEachIteration Sets the reshuffleEachIteration option.
     *
     * @param reshuffleEachIteration the reshuffleEachIteration option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun shuffleDataset(
        inputDataset: Operand<out TType>,
        bufferSize: Operand<TInt64>,
        seed: Operand<TInt64>,
        seed2: Operand<TInt64>,
        seedGenerator: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        reshuffleEachIteration: Boolean? = null,
        metadata: String? = null
    ): ShuffleDataset = java.shuffleDataset(    
        inputDataset,
        bufferSize,
        seed,
        seed2,
        seedGenerator,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            reshuffleEachIteration?.let{ org.tensorflow.op.data.ShuffleDataset.reshuffleEachIteration(it)
            },
            metadata?.let{ org.tensorflow.op.data.ShuffleDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that skips `count` elements from the `input_dataset`.
     *
     * @param inputDataset The inputDataset value
     * @param count A scalar representing the number of elements from the `input_dataset`
     *  that should be skipped.  If count is -1, skips everything.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of SkipDataset
     * @see org.tensorflow.op.DataOps.skipDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun skipDataset(
        inputDataset: Operand<out TType>,
        count: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): SkipDataset = java.skipDataset(    
        inputDataset,
        count,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.SkipDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * The SleepDataset operation
     *
     * @param inputDataset The inputDataset value
     * @param sleepMicroseconds The sleepMicroseconds value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of SleepDataset
     * @see org.tensorflow.op.DataOps.sleepDataset
     */
    public fun sleepDataset(
        inputDataset: Operand<out TType>,
        sleepMicroseconds: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): SleepDataset = java.sleepDataset(    
        inputDataset,
        sleepMicroseconds,
        outputTypes,
        outputShapes
        )

    /**
     * Creates a dataset that passes a sliding window over `input_dataset`.
     *
     * @param inputDataset The inputDataset value
     * @param windowSize A scalar representing the number of elements in the
     *  sliding window.
     * @param windowShift A scalar representing the steps moving the sliding window
     *  forward in one iteration. It must be positive.
     * @param windowStride A scalar representing the stride of the input elements of the sliding
     * window.
     *  It must be positive.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of SlidingWindowDataset
     * @see org.tensorflow.op.DataOps.slidingWindowDataset
     */
    public fun slidingWindowDataset(
        inputDataset: Operand<out TType>,
        windowSize: Operand<TInt64>,
        windowShift: Operand<TInt64>,
        windowStride: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): SlidingWindowDataset = java.slidingWindowDataset(    
        inputDataset,
        windowSize,
        windowShift,
        windowStride,
        outputTypes,
        outputShapes
        )

    /**
     * Creates a dataset that will write to / read from a snapshot.
     *  This dataset attempts to determine whether a valid snapshot exists at the
     *  `snapshot_path`, and reads from the snapshot in lieu of using `input_dataset`.
     *  If not, it will run the preprocessing pipeline as usual, and write out a
     *  snapshot of the data processed for future use.
     *
     * @param inputDataset A variant tensor representing the input dataset.
     * @param path The path we should write snapshots to / read snapshots from.
     * @param readerFuncOtherArgs The readerFuncOtherArgs value
     * @param shardFuncOtherArgs The shardFuncOtherArgs value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param readerFunc Optional. A function to control how to read data from snapshot shards.
     * @param shardFunc Optional. A function to control how to shard data when writing a snapshot.
     * @param options carries optional attribute values
     * @return a new instance of SnapshotDataset
     * @see org.tensorflow.op.DataOps.snapshotDataset
     * @param compression Sets the compression option.
     *
     * @param compression The type of compression to be applied to the saved snapshot files.
     * @return this Options instance.
     * @param readerPrefix Sets the readerPrefix option.
     *
     * @param readerPrefix the readerPrefix option
     * @return this Options instance.
     * @param writerPrefix Sets the writerPrefix option.
     *
     * @param writerPrefix the writerPrefix option
     * @return this Options instance.
     * @param hashValid Sets the hashValid option.
     *
     * @param hashValid the hashValid option
     * @return this Options instance.
     * @param hash Sets the hash option.
     *
     * @param hash the hash option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun snapshotDataset(
        inputDataset: Operand<out TType>,
        path: Operand<TString>,
        readerFuncOtherArgs: Iterable<Operand<*>>,
        shardFuncOtherArgs: Iterable<Operand<*>>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        readerFunc: ConcreteFunction,
        shardFunc: ConcreteFunction,
        compression: String? = null,
        readerPrefix: String? = null,
        writerPrefix: String? = null,
        hashValid: Boolean? = null,
        hash: Long? = null,
        metadata: String? = null
    ): SnapshotDataset = java.snapshotDataset(    
        inputDataset,
        path,
        readerFuncOtherArgs,
        shardFuncOtherArgs,
        outputTypes,
        outputShapes,
        readerFunc,
        shardFunc,
        *listOfNotNull(
            compression?.let{ org.tensorflow.op.data.SnapshotDataset.compression(it) },
            readerPrefix?.let{ org.tensorflow.op.data.SnapshotDataset.readerPrefix(it) },
            writerPrefix?.let{ org.tensorflow.op.data.SnapshotDataset.writerPrefix(it) },
            hashValid?.let{ org.tensorflow.op.data.SnapshotDataset.hashValid(it) },
            hash?.let{ org.tensorflow.op.data.SnapshotDataset.hash(it) },
            metadata?.let{ org.tensorflow.op.data.SnapshotDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that splits a SparseTensor into elements row-wise.
     *
     * @param indices The indices value
     * @param values The values value
     * @param denseShape The denseShape value
     * @return a new instance of SparseTensorSliceDataset
     * @see org.tensorflow.op.DataOps.sparseTensorSliceDataset
     */
    public fun sparseTensorSliceDataset(
        indices: Operand<TInt64>,
        values: Operand<out TType>,
        denseShape: Operand<TInt64>
    ): SparseTensorSliceDataset = java.sparseTensorSliceDataset(    
        indices,
        values,
        denseShape
        )

    /**
     * Creates a dataset that executes a SQL query and emits rows of the result set.
     *
     * @param driverName The database type. Currently, the only supported type is 'sqlite'.
     * @param dataSourceName A connection string to connect to the database.
     * @param query A SQL query to execute.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of SqlDataset
     * @see org.tensorflow.op.DataOps.sqlDataset
     */
    public fun sqlDataset(
        driverName: Operand<TString>,
        dataSourceName: Operand<TString>,
        query: Operand<TString>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): SqlDataset = java.sqlDataset(    
        driverName,
        dataSourceName,
        query,
        outputTypes,
        outputShapes
        )

    /**
     * Creates a dataset that contains `count` elements from the `input_dataset`.
     *
     * @param inputDataset The inputDataset value
     * @param count A scalar representing the number of elements from the `input_dataset`
     *  that should be taken. A value of `-1` indicates that all of `input_dataset`
     *  is taken.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of TakeDataset
     * @see org.tensorflow.op.DataOps.takeDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun takeDataset(
        inputDataset: Operand<out TType>,
        count: Operand<TInt64>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): TakeDataset = java.takeDataset(    
        inputDataset,
        count,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.TakeDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that stops iteration when predicate` is false.
     *  The `predicate` function must return a scalar boolean and accept the
     *  following arguments:
     *  <ul>
     *  <li>One tensor for each component of an element of `input_dataset`.</li>
     *  <li>One tensor for each value in `other_arguments`.</li>
     *  </ul>
     *
     * @param inputDataset The inputDataset value
     * @param otherArguments A list of tensors, typically values that were captured when
     *  building a closure for `predicate`.
     * @param predicate A function returning a scalar boolean.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of TakeWhileDataset
     * @see org.tensorflow.op.DataOps.takeWhileDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun takeWhileDataset(
        inputDataset: Operand<out TType>,
        otherArguments: Iterable<Operand<*>>,
        predicate: ConcreteFunction,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): TakeWhileDataset = java.takeWhileDataset(    
        inputDataset,
        otherArguments,
        predicate,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.TakeWhileDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that emits `components` as a tuple of tensors once.
     *
     * @param components The components value
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of TensorDataset
     * @see org.tensorflow.op.DataOps.tensorDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun tensorDataset(
        components: Iterable<Operand<*>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): TensorDataset = java.tensorDataset(    
        components,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.TensorDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that emits each dim-0 slice of `components` once.
     *
     * @param components The components value
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of TensorSliceDataset
     * @see org.tensorflow.op.DataOps.tensorSliceDataset
     * @param isFiles Sets the isFiles option.
     *
     * @param isFiles the isFiles option
     * @return this Options instance.
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun tensorSliceDataset(
        components: Iterable<Operand<*>>,
        outputShapes: List<Shape>,
        isFiles: Boolean? = null,
        metadata: String? = null
    ): TensorSliceDataset = java.tensorSliceDataset(    
        components,
        outputShapes,
        *listOfNotNull(
            isFiles?.let{ org.tensorflow.op.data.TensorSliceDataset.isFiles(it) },
            metadata?.let{ org.tensorflow.op.data.TensorSliceDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that emits the lines of one or more text files.
     *
     * @param filenames A scalar or a vector containing the name(s) of the file(s) to be
     *  read.
     * @param compressionType A scalar containing either (i) the empty string (no
     *  compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
     * @param bufferSize A scalar containing the number of bytes to buffer.
     * @param options carries optional attribute values
     * @return a new instance of TextLineDataset
     * @see org.tensorflow.op.DataOps.textLineDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun textLineDataset(
        filenames: Operand<TString>,
        compressionType: Operand<TString>,
        bufferSize: Operand<TInt64>,
        metadata: String? = null
    ): TextLineDataset = java.textLineDataset(    
        filenames,
        compressionType,
        bufferSize,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.TextLineDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that emits the records from one or more TFRecord files.
     *
     * @param filenames A scalar or vector containing the name(s) of the file(s) to be
     *  read.
     * @param compressionType A scalar containing either (i) the empty string (no
     *  compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
     * @param bufferSize A scalar representing the number of bytes to buffer. A value of
     *  0 means no buffering will be performed.
     * @param options carries optional attribute values
     * @return a new instance of TfRecordDataset
     * @see org.tensorflow.op.DataOps.tfRecordDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun tfRecordDataset(
        filenames: Operand<TString>,
        compressionType: Operand<TString>,
        bufferSize: Operand<TInt64>,
        metadata: String? = null
    ): TfRecordDataset = java.tfRecordDataset(    
        filenames,
        compressionType,
        bufferSize,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.TfRecordDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that uses a custom thread pool to compute `input_dataset`.
     *
     * @param inputDataset The inputDataset value
     * @param threadPool A resource produced by the ThreadPoolHandle op.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @return a new instance of ThreadPoolDataset
     * @see org.tensorflow.op.DataOps.threadPoolDataset
     */
    public fun threadPoolDataset(
        inputDataset: Operand<out TType>,
        threadPool: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>
    ): ThreadPoolDataset = java.threadPoolDataset(    
        inputDataset,
        threadPool,
        outputTypes,
        outputShapes
        )

    /**
     * A dataset that splits the elements of its input into multiple elements.
     *
     * @param inputDataset The inputDataset value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of UnbatchDataset
     * @see org.tensorflow.op.DataOps.unbatchDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun unbatchDataset(
        inputDataset: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): UnbatchDataset = java.unbatchDataset(    
        inputDataset,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.UnbatchDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * Creates a dataset that contains the unique elements of `input_dataset`.
     *
     * @param inputDataset The inputDataset value
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of UniqueDataset
     * @see org.tensorflow.op.DataOps.uniqueDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun uniqueDataset(
        inputDataset: Operand<out TType>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): UniqueDataset = java.uniqueDataset(    
        inputDataset,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.UniqueDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * The UnwrapDatasetVariant operation
     *
     * @param inputHandle The inputHandle value
     * @return a new instance of UnwrapDatasetVariant
     * @see org.tensorflow.op.DataOps.unwrapDatasetVariant
     */
    public fun unwrapDatasetVariant(inputHandle: Operand<out TType>): UnwrapDatasetVariant =
            java.unwrapDatasetVariant(    
        inputHandle
        )

    /**
     * Combines (nests of) input elements into a dataset of (nests of) windows.
     *  
     * A &quot;window&quot; is a finite dataset of flat elements of size `size` (or possibly
     *  fewer if there are not enough input elements to fill the window and
     *  `drop_remainder` evaluates to false).
     *  
     * The `shift` argument determines the number of input elements by which
     *  the window moves on each iteration.  The first element in the `k`th window
     *  will be element
     *  ```
     * 1 + (k-1) * shift
     *  
     * ```
     *  
     * of the input dataset. In particular, the first element of the first window
     *  will always be the first element of the input dataset.
     *  
     * If the `stride` parameter is greater than 1, then each window will skip
     *  `(stride - 1)` input elements between each element that appears in the
     *  window. Output windows will still contain `size` elements regardless of
     *  the value of `stride`.
     *  
     * The `stride` argument determines the stride of the input elements, and the
     *  `shift` argument determines the shift of the window.
     *  
     * For example, letting `{...`} to represent a Dataset:
     *  <ul>
     *  <li>`tf.data.Dataset.range(7).window(2)` produces
     *  `{{0, 1`, {2, 3}, {4, 5}, {6}}}</li>
     *  <li>`tf.data.Dataset.range(7).window(3, 2, 1, True)` produces
     *  `{{0, 1, 2`, {2, 3, 4}, {4, 5, 6}}}</li>
     *  <li>`tf.data.Dataset.range(7).window(3, 1, 2, True)` produces
     *  `{{0, 2, 4`, {1, 3, 5}, {2, 4, 6}}}</li>
     *  </ul>
     *  
     * Note that when the `window` transformation is applied to a dataset of
     *  nested elements, it produces a dataset of nested windows.
     *  
     * For example:
     *  <ul>
     *  <li>`tf.data.Dataset.from_tensor_slices((range(4), range(4))).window(2)`
     *  produces `{({0, 1`, {0, 1}), ({2, 3}, {2, 3})}}</li>
     *  <li>`tf.data.Dataset.from_tensor_slices({"a": range(4)`).window(2)}
     *  produces `{{"a": {0, 1`}, {"a": {2, 3}}}}</li>
     *  </ul>
     *
     * @param inputDataset The inputDataset value
     * @param sizeOutput An integer scalar, representing the number of elements
     *  of the input dataset to combine into a window. Must be positive.
     * @param shift An integer scalar, representing the number of input elements
     *  by which the window moves in each iteration.  Defaults to `size`.
     *  Must be positive.
     * @param stride An integer scalar, representing the stride of the input elements
     *  in the sliding window. Must be positive. The default value of 1 means
     *  &quot;retain every input element&quot;.
     * @param dropRemainder A Boolean scalar, representing whether the last window should be
     *  dropped if its size is smaller than `window_size`.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of WindowDataset
     * @see org.tensorflow.op.DataOps.windowDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun windowDataset(
        inputDataset: Operand<out TType>,
        sizeOutput: Operand<TInt64>,
        shift: Operand<TInt64>,
        stride: Operand<TInt64>,
        dropRemainder: Operand<TBool>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): WindowDataset = java.windowDataset(    
        inputDataset,
        sizeOutput,
        shift,
        stride,
        dropRemainder,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.WindowDataset.metadata(it) }
        ).toTypedArray()
        )

    /**
     * The WrapDatasetVariant operation
     *
     * @param inputHandle The inputHandle value
     * @return a new instance of WrapDatasetVariant
     * @see org.tensorflow.op.DataOps.wrapDatasetVariant
     */
    public fun wrapDatasetVariant(inputHandle: Operand<out TType>): WrapDatasetVariant =
            java.wrapDatasetVariant(    
        inputHandle
        )

    /**
     * Creates a dataset that zips together `input_datasets`.
     *  The elements of the resulting dataset are created by zipping corresponding
     *  elements from each of the input datasets.
     *  
     * The size of the resulting dataset will match the size of the smallest input
     *  dataset, and no error will be raised if input datasets have different sizes.
     *
     * @param inputDatasets List of `N` variant Tensors representing datasets to be zipped together.
     * @param outputTypes The value of the outputTypes attribute
     * @param outputShapes The value of the outputShapes attribute
     * @param options carries optional attribute values
     * @return a new instance of ZipDataset
     * @see org.tensorflow.op.DataOps.zipDataset
     * @param metadata Sets the metadata option.
     *
     * @param metadata the metadata option
     * @return this Options instance.
     */
    public fun zipDataset(
        inputDatasets: Iterable<Operand<out TType>>,
        outputTypes: List<Class<out TType>>,
        outputShapes: List<Shape>,
        metadata: String? = null
    ): ZipDataset = java.zipDataset(    
        inputDatasets,
        outputTypes,
        outputShapes,
        *listOfNotNull(
            metadata?.let{ org.tensorflow.op.data.ZipDataset.metadata(it) }
        ).toTypedArray()
        )
}
