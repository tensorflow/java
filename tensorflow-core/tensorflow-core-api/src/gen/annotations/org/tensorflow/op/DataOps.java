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
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Operand;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.data.AnonymousIterator;
import org.tensorflow.op.data.AssertCardinalityDataset;
import org.tensorflow.op.data.AssertNextDataset;
import org.tensorflow.op.data.AutoShardDataset;
import org.tensorflow.op.data.BatchDataset;
import org.tensorflow.op.data.BytesProducedStatsDataset;
import org.tensorflow.op.data.CSVDataset;
import org.tensorflow.op.data.CacheDataset;
import org.tensorflow.op.data.ChooseFastestBranchDataset;
import org.tensorflow.op.data.ChooseFastestDataset;
import org.tensorflow.op.data.ConcatenateDataset;
import org.tensorflow.op.data.DataServiceDataset;
import org.tensorflow.op.data.DatasetCardinality;
import org.tensorflow.op.data.DatasetFromGraph;
import org.tensorflow.op.data.DatasetToGraph;
import org.tensorflow.op.data.DatasetToSingleElement;
import org.tensorflow.op.data.DatasetToTfRecord;
import org.tensorflow.op.data.DeleteIterator;
import org.tensorflow.op.data.DenseToSparseBatchDataset;
import org.tensorflow.op.data.DeserializeIterator;
import org.tensorflow.op.data.DirectedInterleaveDataset;
import org.tensorflow.op.data.FilterByLastComponentDataset;
import org.tensorflow.op.data.FilterDataset;
import org.tensorflow.op.data.FinalizeDataset;
import org.tensorflow.op.data.FixedLengthRecordDataset;
import org.tensorflow.op.data.FlatMapDataset;
import org.tensorflow.op.data.GeneratorDataset;
import org.tensorflow.op.data.GroupByReducerDataset;
import org.tensorflow.op.data.GroupByWindowDataset;
import org.tensorflow.op.data.IgnoreErrorsDataset;
import org.tensorflow.op.data.InitializeTableFromDataset;
import org.tensorflow.op.data.InterleaveDataset;
import org.tensorflow.op.data.Iterator;
import org.tensorflow.op.data.IteratorGetNext;
import org.tensorflow.op.data.IteratorGetNextAsOptional;
import org.tensorflow.op.data.IteratorGetNextSync;
import org.tensorflow.op.data.IteratorToStringHandle;
import org.tensorflow.op.data.LMDBDataset;
import org.tensorflow.op.data.LatencyStatsDataset;
import org.tensorflow.op.data.LegacyParallelInterleaveDataset;
import org.tensorflow.op.data.LoadDataset;
import org.tensorflow.op.data.MakeIterator;
import org.tensorflow.op.data.MapAndBatchDataset;
import org.tensorflow.op.data.MapDataset;
import org.tensorflow.op.data.MatchingFilesDataset;
import org.tensorflow.op.data.MaxIntraOpParallelismDataset;
import org.tensorflow.op.data.ModelDataset;
import org.tensorflow.op.data.NonSerializableDataset;
import org.tensorflow.op.data.OneShotIterator;
import org.tensorflow.op.data.OptimizeDataset;
import org.tensorflow.op.data.OptionalFromValue;
import org.tensorflow.op.data.OptionalGetValue;
import org.tensorflow.op.data.OptionalHasValue;
import org.tensorflow.op.data.OptionalNone;
import org.tensorflow.op.data.OptionsDataset;
import org.tensorflow.op.data.PaddedBatchDataset;
import org.tensorflow.op.data.ParallelBatchDataset;
import org.tensorflow.op.data.ParallelInterleaveDataset;
import org.tensorflow.op.data.ParallelMapDataset;
import org.tensorflow.op.data.ParseExampleDataset;
import org.tensorflow.op.data.PrefetchDataset;
import org.tensorflow.op.data.PrivateThreadPoolDataset;
import org.tensorflow.op.data.RandomDataset;
import org.tensorflow.op.data.RangeDataset;
import org.tensorflow.op.data.RebatchDatasetV2;
import org.tensorflow.op.data.ReduceDataset;
import org.tensorflow.op.data.RegisterDataset;
import org.tensorflow.op.data.RepeatDataset;
import org.tensorflow.op.data.SamplingDataset;
import org.tensorflow.op.data.SaveDataset;
import org.tensorflow.op.data.ScanDataset;
import org.tensorflow.op.data.SerializeIterator;
import org.tensorflow.op.data.SetStatsAggregatorDataset;
import org.tensorflow.op.data.ShardDataset;
import org.tensorflow.op.data.ShuffleAndRepeatDataset;
import org.tensorflow.op.data.ShuffleDataset;
import org.tensorflow.op.data.SkipDataset;
import org.tensorflow.op.data.SleepDataset;
import org.tensorflow.op.data.SlidingWindowDataset;
import org.tensorflow.op.data.SnapshotDataset;
import org.tensorflow.op.data.SparseTensorSliceDataset;
import org.tensorflow.op.data.SqlDataset;
import org.tensorflow.op.data.TakeDataset;
import org.tensorflow.op.data.TakeWhileDataset;
import org.tensorflow.op.data.TensorDataset;
import org.tensorflow.op.data.TensorSliceDataset;
import org.tensorflow.op.data.TextLineDataset;
import org.tensorflow.op.data.TfRecordDataset;
import org.tensorflow.op.data.ThreadPoolDataset;
import org.tensorflow.op.data.UnbatchDataset;
import org.tensorflow.op.data.UniqueDataset;
import org.tensorflow.op.data.UnwrapDatasetVariant;
import org.tensorflow.op.data.WindowDataset;
import org.tensorflow.op.data.WrapDatasetVariant;
import org.tensorflow.op.data.ZipDataset;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
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
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of AnonymousIterator
   */
  public AnonymousIterator anonymousIterator(List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    return AnonymousIterator.create(scope, outputTypes, outputShapes);
  }

  /**
   * The AssertCardinalityDataset operation
   *
   * @param inputDataset The inputDataset value
   * @param cardinality The cardinality value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of AssertCardinalityDataset
   */
  public AssertCardinalityDataset assertCardinalityDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> cardinality, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    return AssertCardinalityDataset.create(scope, inputDataset, cardinality, outputTypes, outputShapes);
  }

  /**
   * A transformation that asserts which transformations happen next.
   *  This transformation checks whether the camel-case names (i.e. &quot;FlatMap&quot;, not
   *  &quot;flat_map&quot;) of the transformations following this transformation match the list
   *  of names in the {@code transformations} argument. If there is a mismatch, the
   *  transformation raises an exception.
   *  <p>The check occurs when iterating over the contents of the dataset, which
   *  means that the check happens <em>after</em> any static optimizations are applied
   *  to the dataset graph.
   *
   * @param inputDataset A variant tensor representing the input dataset.
   *  {@code data.AssertNextDataset} passes through the outputs of its input dataset.
   * @param transformations A {@code tf.string} vector {@code tf.Tensor} identifying the transformations that are
   *  expected to happen next.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of AssertNextDataset
   */
  public AssertNextDataset assertNextDataset(Operand<? extends TType> inputDataset,
      Operand<TString> transformations, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    return AssertNextDataset.create(scope, inputDataset, transformations, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that shards the input dataset.
   *  Creates a dataset that shards the input dataset by num_workers, returning a
   *  sharded dataset for the index-th worker. This attempts to automatically shard
   *  a dataset by examining the Dataset graph and inserting a shard op before the
   *  inputs to a reader Dataset (e.g. CSVDataset, TFRecordDataset).
   *  <p>This dataset will throw a NotFound error if we cannot shard the dataset
   *  automatically.
   *
   * @param inputDataset A variant tensor representing the input dataset.
   * @param numWorkers A scalar representing the number of workers to distribute this dataset across.
   * @param index A scalar representing the index of the current worker out of num_workers.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of AutoShardDataset
   */
  public AutoShardDataset autoShardDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> numWorkers, Operand<TInt64> index, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, AutoShardDataset.Options... options) {
    return AutoShardDataset.create(scope, inputDataset, numWorkers, index, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that batches {@code batch_size} elements from {@code input_dataset}.
   *
   * @param inputDataset The inputDataset value
   * @param batchSize A scalar representing the number of elements to accumulate in a batch.
   * @param dropRemainder A scalar representing whether the last batch should be dropped in case its size
   *  is smaller than desired.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of BatchDataset
   */
  public BatchDataset batchDataset(Operand<? extends TType> inputDataset, Operand<TInt64> batchSize,
      Operand<TBool> dropRemainder, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, BatchDataset.Options... options) {
    return BatchDataset.create(scope, inputDataset, batchSize, dropRemainder, outputTypes, outputShapes, options);
  }

  /**
   * Records the bytes size of each element of {@code input_dataset} in a StatsAggregator.
   *
   * @param inputDataset The inputDataset value
   * @param tag The tag value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of BytesProducedStatsDataset
   */
  public BytesProducedStatsDataset bytesProducedStatsDataset(Operand<? extends TType> inputDataset,
      Operand<TString> tag, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return BytesProducedStatsDataset.create(scope, inputDataset, tag, outputTypes, outputShapes);
  }

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
   */
  public CSVDataset cSVDataset(Operand<TString> filenames, Operand<TString> compressionType,
      Operand<TInt64> bufferSize, Operand<TBool> header, Operand<TString> fieldDelim,
      Operand<TBool> useQuoteDelim, Operand<TString> naValue, Operand<TInt64> selectCols,
      Iterable<Operand<?>> recordDefaults, Operand<TInt64> excludeCols, List<Shape> outputShapes) {
    return CSVDataset.create(scope, filenames, compressionType, bufferSize, header, fieldDelim, useQuoteDelim, naValue, selectCols, recordDefaults, excludeCols, outputShapes);
  }

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
   */
  public CacheDataset cacheDataset(Operand<? extends TType> inputDataset, Operand<TString> filename,
      Operand<? extends TType> cache, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, CacheDataset.Options... options) {
    return CacheDataset.create(scope, inputDataset, filename, cache, outputTypes, outputShapes, options);
  }

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
   */
  public ChooseFastestBranchDataset chooseFastestBranchDataset(
      Operand<? extends TType> inputDataset, Operand<TInt64> ratioNumerator,
      Operand<TInt64> ratioDenominator, Iterable<Operand<?>> otherArguments,
      Long numElementsPerBranch, List<ConcreteFunction> branches, List<Long> otherArgumentsLengths,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return ChooseFastestBranchDataset.create(scope, inputDataset, ratioNumerator, ratioDenominator, otherArguments, numElementsPerBranch, branches, otherArgumentsLengths, outputTypes, outputShapes);
  }

  /**
   * The ChooseFastestDataset operation
   *
   * @param inputDatasets The inputDatasets value
   * @param numExperiments The value of the numExperiments attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of ChooseFastestDataset
   */
  public ChooseFastestDataset chooseFastestDataset(Iterable<Operand<? extends TType>> inputDatasets,
      Long numExperiments, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return ChooseFastestDataset.create(scope, inputDatasets, numExperiments, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that concatenates {@code input_dataset} with {@code another_dataset}.
   *
   * @param inputDataset The inputDataset value
   * @param anotherDataset The anotherDataset value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ConcatenateDataset
   */
  public ConcatenateDataset concatenateDataset(Operand<? extends TType> inputDataset,
      Operand<? extends TType> anotherDataset, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, ConcatenateDataset.Options... options) {
    return ConcatenateDataset.create(scope, inputDataset, anotherDataset, outputTypes, outputShapes, options);
  }

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
   * @param uncompressFn The value of the uncompressFn attribute
   * @param options carries optional attribute values
   * @return a new instance of DataServiceDataset
   */
  public DataServiceDataset dataServiceDataset(Operand<TInt64> datasetId,
      Operand<TString> processingMode, Operand<TString> address, Operand<TString> protocol,
      Operand<TString> jobName, Operand<TInt64> consumerIndex, Operand<TInt64> numConsumers,
      Operand<TInt64> maxOutstandingRequests, Operand<? extends TType> iterationCounter,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      ConcreteFunction uncompressFn, DataServiceDataset.Options... options) {
    return DataServiceDataset.create(scope, datasetId, processingMode, address, protocol, jobName, consumerIndex, numConsumers, maxOutstandingRequests, iterationCounter, outputTypes, outputShapes, uncompressFn, options);
  }

  /**
   * Returns the cardinality of {@code input_dataset}.
   *  Returns the cardinality of {@code input_dataset}.
   *
   * @param inputDataset A variant tensor representing the dataset to return cardinality for.
   * @return a new instance of DatasetCardinality
   */
  public DatasetCardinality datasetCardinality(Operand<? extends TType> inputDataset) {
    return DatasetCardinality.create(scope, inputDataset);
  }

  /**
   * Creates a dataset from the given {@code graph_def}.
   *  Creates a dataset from the provided {@code graph_def}.
   *
   * @param graphDef The graph representation of the dataset (as serialized GraphDef).
   * @return a new instance of DatasetFromGraph
   */
  public DatasetFromGraph datasetFromGraph(Operand<TString> graphDef) {
    return DatasetFromGraph.create(scope, graphDef);
  }

  /**
   * Returns a serialized GraphDef representing {@code input_dataset}.
   *  Returns a graph representation for {@code input_dataset}.
   *
   * @param inputDataset A variant tensor representing the dataset to return the graph representation for.
   * @param options carries optional attribute values
   * @return a new instance of DatasetToGraph
   */
  public DatasetToGraph datasetToGraph(Operand<? extends TType> inputDataset,
      DatasetToGraph.Options... options) {
    return DatasetToGraph.create(scope, inputDataset, options);
  }

  /**
   * Outputs the single element from the given dataset.
   *
   * @param dataset A handle to a dataset that contains a single element.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of DatasetToSingleElement
   */
  public DatasetToSingleElement datasetToSingleElement(Operand<? extends TType> dataset,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      DatasetToSingleElement.Options... options) {
    return DatasetToSingleElement.create(scope, dataset, outputTypes, outputShapes, options);
  }

  /**
   * Writes the given dataset to the given file using the TFRecord format.
   *
   * @param inputDataset A variant tensor representing the dataset to write.
   * @param filename A scalar string tensor representing the filename to use.
   * @param compressionType A scalar string tensor containing either (i) the empty string (no
   *  compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
   * @return a new instance of DatasetToTfRecord
   */
  public DatasetToTfRecord datasetToTfRecord(Operand<? extends TType> inputDataset,
      Operand<TString> filename, Operand<TString> compressionType) {
    return DatasetToTfRecord.create(scope, inputDataset, filename, compressionType);
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
   * Creates a dataset that batches input elements into a SparseTensor.
   *
   * @param inputDataset A handle to an input dataset. Must have a single component.
   * @param batchSize A scalar representing the number of elements to accumulate in a
   *  batch.
   * @param rowShape A vector representing the dense shape of each row in the produced
   *  SparseTensor. The shape may be partially specified, using {@code -1} to indicate
   *  that a particular dimension should use the maximum size of all batch elements.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of DenseToSparseBatchDataset
   */
  public DenseToSparseBatchDataset denseToSparseBatchDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> batchSize, Operand<TInt64> rowShape, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    return DenseToSparseBatchDataset.create(scope, inputDataset, batchSize, rowShape, outputTypes, outputShapes);
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
   * A substitute for {@code InterleaveDataset} on a fixed list of {@code N} datasets.
   *
   * @param selectorInputDataset A dataset of scalar {@code DT_INT64} elements that determines which of the
   *  {@code N} data inputs should produce the next output element.
   * @param dataInputDatasets {@code N} datasets with the same type that will be interleaved according to
   *  the values of {@code selector_input_dataset}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of DirectedInterleaveDataset
   */
  public DirectedInterleaveDataset directedInterleaveDataset(
      Operand<? extends TType> selectorInputDataset,
      Iterable<Operand<? extends TType>> dataInputDatasets,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      DirectedInterleaveDataset.Options... options) {
    return DirectedInterleaveDataset.create(scope, selectorInputDataset, dataInputDatasets, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset containing elements of first component of {@code input_dataset} having true in the last component.
   *
   * @param inputDataset The inputDataset value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of FilterByLastComponentDataset
   */
  public FilterByLastComponentDataset filterByLastComponentDataset(
      Operand<? extends TType> inputDataset, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    return FilterByLastComponentDataset.create(scope, inputDataset, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset containing elements of {@code input_dataset} matching {@code predicate}.
   *  The {@code predicate} function must return a scalar boolean and accept the
   *  following arguments:
   *  <ul>
   *  <li>One tensor for each component of an element of {@code input_dataset}.</li>
   *  <li>One tensor for each value in {@code other_arguments}.</li>
   *  </ul>
   *
   * @param inputDataset The inputDataset value
   * @param otherArguments A list of tensors, typically values that were captured when
   *  building a closure for {@code predicate}.
   * @param predicate A function returning a scalar boolean.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of FilterDataset
   */
  public FilterDataset filterDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, ConcreteFunction predicate,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      FilterDataset.Options... options) {
    return FilterDataset.create(scope, inputDataset, otherArguments, predicate, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset by applying {@code tf.data.Options} to {@code input_dataset}.
   *
   * @param inputDataset A variant tensor representing the input dataset.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of FinalizeDataset
   */
  public FinalizeDataset finalizeDataset(Operand<? extends TType> inputDataset,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      FinalizeDataset.Options... options) {
    return FinalizeDataset.create(scope, inputDataset, outputTypes, outputShapes, options);
  }

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
   */
  public FixedLengthRecordDataset fixedLengthRecordDataset(Operand<TString> filenames,
      Operand<TInt64> headerBytes, Operand<TInt64> recordBytes, Operand<TInt64> footerBytes,
      Operand<TInt64> bufferSize, Operand<TString> compressionType,
      FixedLengthRecordDataset.Options... options) {
    return FixedLengthRecordDataset.create(scope, filenames, headerBytes, recordBytes, footerBytes, bufferSize, compressionType, options);
  }

  /**
   * Creates a dataset that applies {@code f} to the outputs of {@code input_dataset}.
   *  Unlike MapDataset, the {@code f} in FlatMapDataset is expected to return a
   *  Dataset variant, and FlatMapDataset will flatten successive results
   *  into a single Dataset.
   *
   * @param inputDataset The inputDataset value
   * @param otherArguments The otherArguments value
   * @param f A function mapping elements of {@code input_dataset}, concatenated with
   *  {@code other_arguments}, to a Dataset variant that contains elements matching
   *  {@code output_types} and {@code output_shapes}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of FlatMapDataset
   */
  public FlatMapDataset flatMapDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      FlatMapDataset.Options... options) {
    return FlatMapDataset.create(scope, inputDataset, otherArguments, f, outputTypes, outputShapes, options);
  }

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
   */
  public GeneratorDataset generatorDataset(Iterable<Operand<?>> initFuncOtherArgs,
      Iterable<Operand<?>> nextFuncOtherArgs, Iterable<Operand<?>> finalizeFuncOtherArgs,
      ConcreteFunction initFunc, ConcreteFunction nextFunc, ConcreteFunction finalizeFunc,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      GeneratorDataset.Options... options) {
    return GeneratorDataset.create(scope, initFuncOtherArgs, nextFuncOtherArgs, finalizeFuncOtherArgs, initFunc, nextFunc, finalizeFunc, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that computes a group-by on {@code input_dataset}.
   *  Creates a dataset that computes a group-by on {@code input_dataset}.
   *
   * @param inputDataset A variant tensor representing the input dataset.
   * @param keyFuncOtherArguments A list of tensors, typically values that were captured when
   *  building a closure for {@code key_func}.
   * @param initFuncOtherArguments A list of tensors, typically values that were captured when
   *  building a closure for {@code init_func}.
   * @param reduceFuncOtherArguments A list of tensors, typically values that were captured when
   *  building a closure for {@code reduce_func}.
   * @param finalizeFuncOtherArguments A list of tensors, typically values that were captured when
   *  building a closure for {@code finalize_func}.
   * @param keyFunc A function mapping an element of {@code input_dataset}, concatenated
   *  with {@code key_func_other_arguments} to a scalar value of type DT_INT64.
   * @param initFunc A function mapping a key of type DT_INT64, concatenated with
   *  {@code init_func_other_arguments} to the initial reducer state.
   * @param reduceFunc A function mapping the current reducer state and an element of {@code input_dataset},
   *  concatenated with {@code reduce_func_other_arguments} to a new reducer state.
   * @param finalizeFunc A function mapping the final reducer state to an output element.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of GroupByReducerDataset
   */
  public GroupByReducerDataset groupByReducerDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> keyFuncOtherArguments, Iterable<Operand<?>> initFuncOtherArguments,
      Iterable<Operand<?>> reduceFuncOtherArguments,
      Iterable<Operand<?>> finalizeFuncOtherArguments, ConcreteFunction keyFunc,
      ConcreteFunction initFunc, ConcreteFunction reduceFunc, ConcreteFunction finalizeFunc,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return GroupByReducerDataset.create(scope, inputDataset, keyFuncOtherArguments, initFuncOtherArguments, reduceFuncOtherArguments, finalizeFuncOtherArguments, keyFunc, initFunc, reduceFunc, finalizeFunc, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that computes a windowed group-by on {@code input_dataset}.
   *  // TODO(mrry): Support non-int64 keys.
   *
   * @param inputDataset The inputDataset value
   * @param keyFuncOtherArguments The keyFuncOtherArguments value
   * @param reduceFuncOtherArguments The reduceFuncOtherArguments value
   * @param windowSizeFuncOtherArguments The windowSizeFuncOtherArguments value
   * @param keyFunc A function mapping an element of {@code input_dataset}, concatenated
   *  with {@code key_func_other_arguments} to a scalar value of type DT_INT64.
   * @param reduceFunc The value of the reduceFunc attribute
   * @param windowSizeFunc The value of the windowSizeFunc attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of GroupByWindowDataset
   */
  public GroupByWindowDataset groupByWindowDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> keyFuncOtherArguments, Iterable<Operand<?>> reduceFuncOtherArguments,
      Iterable<Operand<?>> windowSizeFuncOtherArguments, ConcreteFunction keyFunc,
      ConcreteFunction reduceFunc, ConcreteFunction windowSizeFunc,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      GroupByWindowDataset.Options... options) {
    return GroupByWindowDataset.create(scope, inputDataset, keyFuncOtherArguments, reduceFuncOtherArguments, windowSizeFuncOtherArguments, keyFunc, reduceFunc, windowSizeFunc, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that contains the elements of {@code input_dataset} ignoring errors.
   *
   * @param inputDataset The inputDataset value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of IgnoreErrorsDataset
   */
  public IgnoreErrorsDataset ignoreErrorsDataset(Operand<? extends TType> inputDataset,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      IgnoreErrorsDataset.Options... options) {
    return IgnoreErrorsDataset.create(scope, inputDataset, outputTypes, outputShapes, options);
  }

  /**
   * The InitializeTableFromDataset operation
   *
   * @param tableHandle The tableHandle value
   * @param dataset The dataset value
   * @return a new instance of InitializeTableFromDataset
   */
  public InitializeTableFromDataset initializeTableFromDataset(Operand<? extends TType> tableHandle,
      Operand<? extends TType> dataset) {
    return InitializeTableFromDataset.create(scope, tableHandle, dataset);
  }

  /**
   * Creates a dataset that applies {@code f} to the outputs of {@code input_dataset}.
   *  Unlike MapDataset, the {@code f} in InterleaveDataset is expected to return
   *  a Dataset variant, and InterleaveDataset will flatten successive
   *  results into a single Dataset. Unlike FlatMapDataset,
   *  InterleaveDataset will interleave sequences of up to {@code block_length}
   *  consecutive elements from {@code cycle_length} input elements.
   *
   * @param inputDataset The inputDataset value
   * @param otherArguments The otherArguments value
   * @param cycleLength The cycleLength value
   * @param blockLength The blockLength value
   * @param f A function mapping elements of {@code input_dataset}, concatenated with
   *  {@code other_arguments}, to a Dataset variant that contains elements matching
   *  {@code output_types} and {@code output_shapes}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of InterleaveDataset
   */
  public InterleaveDataset interleaveDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, Operand<TInt64> cycleLength, Operand<TInt64> blockLength,
      ConcreteFunction f, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      InterleaveDataset.Options... options) {
    return InterleaveDataset.create(scope, inputDataset, otherArguments, cycleLength, blockLength, f, outputTypes, outputShapes, options);
  }

  /**
   * The IteratorV2 operation
   *
   * @param sharedName The value of the sharedName attribute
   * @param container The value of the container attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of Iterator
   */
  public Iterator iterator(String sharedName, String container,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return Iterator.create(scope, sharedName, container, outputTypes, outputShapes);
  }

  /**
   * Gets the next output from the given iterator .
   *
   * @param iterator The iterator value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of IteratorGetNext
   */
  public IteratorGetNext iteratorGetNext(Operand<? extends TType> iterator,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return IteratorGetNext.create(scope, iterator, outputTypes, outputShapes);
  }

  /**
   * Gets the next output from the given iterator as an Optional variant.
   *
   * @param iterator The iterator value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
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
   * @param iterator The iterator value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
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
   * Creates a dataset that emits the key-value pairs in one or more LMDB files.
   *  The Lightning Memory-Mapped Database Manager, or LMDB, is an embedded binary
   *  key-value database. This dataset can read the contents of LMDB database files,
   *  the names of which generally have the {@code .mdb} suffix.
   *  <p>Each output element consists of a key-value pair represented as a pair of
   *  scalar string {@code Tensor}s, where the first {@code Tensor} contains the key and the
   *  second {@code Tensor} contains the value.
   *  <p>LMDB uses different file formats on big- and little-endian machines.
   *  {@code data.LMDBDataset} can only read files in the format of the host machine.
   *
   * @param filenames A scalar or a vector containing the name(s) of the binary file(s) to be
   *  read.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of LMDBDataset
   */
  public LMDBDataset lMDBDataset(Operand<TString> filenames,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return LMDBDataset.create(scope, filenames, outputTypes, outputShapes);
  }

  /**
   * Records the latency of producing {@code input_dataset} elements in a StatsAggregator.
   *
   * @param inputDataset The inputDataset value
   * @param tag The tag value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of LatencyStatsDataset
   */
  public LatencyStatsDataset latencyStatsDataset(Operand<? extends TType> inputDataset,
      Operand<TString> tag, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return LatencyStatsDataset.create(scope, inputDataset, tag, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that applies {@code f} to the outputs of {@code input_dataset}.
   *  The resulting dataset is similar to the {@code InterleaveDataset}, with the exception
   *  that if retrieving the next value from a dataset would cause the requester to
   *  block, it will skip that input dataset. This dataset is especially useful
   *  when loading data from a variable-latency datastores (e.g. HDFS, GCS), as it
   *  allows the training step to proceed so long as some data is available.
   *  <p>!! WARNING !! This dataset is not deterministic!
   *
   * @param inputDataset The inputDataset value
   * @param otherArguments The otherArguments value
   * @param cycleLength The cycleLength value
   * @param blockLength The blockLength value
   * @param bufferOutputElements The bufferOutputElements value
   * @param prefetchInputElements The prefetchInputElements value
   * @param f A function mapping elements of {@code input_dataset}, concatenated with
   *  {@code other_arguments}, to a Dataset variant that contains elements matching
   *  {@code output_types} and {@code output_shapes}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of LegacyParallelInterleaveDataset
   */
  public LegacyParallelInterleaveDataset legacyParallelInterleaveDataset(
      Operand<? extends TType> inputDataset, Iterable<Operand<?>> otherArguments,
      Operand<TInt64> cycleLength, Operand<TInt64> blockLength,
      Operand<TInt64> bufferOutputElements, Operand<TInt64> prefetchInputElements,
      ConcreteFunction f, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      LegacyParallelInterleaveDataset.Options... options) {
    return LegacyParallelInterleaveDataset.create(scope, inputDataset, otherArguments, cycleLength, blockLength, bufferOutputElements, prefetchInputElements, f, outputTypes, outputShapes, options);
  }

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
   */
  public LoadDataset loadDataset(Operand<TString> path, Iterable<Operand<?>> readerFuncOtherArgs,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      ConcreteFunction readerFunc, LoadDataset.Options... options) {
    return LoadDataset.create(scope, path, readerFuncOtherArgs, outputTypes, outputShapes, readerFunc, options);
  }

  /**
   * Makes a new iterator from the given {@code dataset} and stores it in {@code iterator}.
   *  This operation may be executed multiple times. Each execution will reset the
   *  iterator in {@code iterator} to the first element of {@code dataset}.
   *
   * @param dataset The dataset value
   * @param iterator The iterator value
   * @return a new instance of MakeIterator
   */
  public MakeIterator makeIterator(Operand<? extends TType> dataset,
      Operand<? extends TType> iterator) {
    return MakeIterator.create(scope, dataset, iterator);
  }

  /**
   * Creates a dataset that fuses mapping with batching.
   *  Creates a dataset that applies {@code f} to the outputs of {@code input_dataset} and then
   *  batches {@code batch_size} of them.
   *  <p>Unlike a &quot;MapDataset&quot;, which applies {@code f} sequentially, this dataset invokes up
   *  to {@code batch_size * num_parallel_batches} copies of {@code f} in parallel.
   *
   * @param inputDataset A variant tensor representing the input dataset.
   * @param otherArguments A list of tensors, typically values that were captured when building a closure
   *  for {@code f}.
   * @param batchSize A scalar representing the number of elements to accumulate in a
   *  batch. It determines the number of concurrent invocations of {@code f} that process
   *  elements from {@code input_dataset} in parallel.
   * @param numParallelCalls A scalar representing the maximum number of parallel invocations of the {@code map_fn}
   *  function. Applying the {@code map_fn} on consecutive input elements in parallel has
   *  the potential to improve input pipeline throughput.
   * @param dropRemainder A scalar representing whether the last batch should be dropped in case its size
   *  is smaller than desired.
   * @param f A function to apply to the outputs of {@code input_dataset}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of MapAndBatchDataset
   */
  public MapAndBatchDataset mapAndBatchDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, Operand<TInt64> batchSize,
      Operand<TInt64> numParallelCalls, Operand<TBool> dropRemainder, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      MapAndBatchDataset.Options... options) {
    return MapAndBatchDataset.create(scope, inputDataset, otherArguments, batchSize, numParallelCalls, dropRemainder, f, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that applies {@code f} to the outputs of {@code input_dataset}.
   *
   * @param inputDataset The inputDataset value
   * @param otherArguments The otherArguments value
   * @param f The value of the f attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of MapDataset
   */
  public MapDataset mapDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      MapDataset.Options... options) {
    return MapDataset.create(scope, inputDataset, otherArguments, f, outputTypes, outputShapes, options);
  }

  /**
   * The MatchingFilesDataset operation
   *
   * @param patterns The patterns value
   * @return a new instance of MatchingFilesDataset
   */
  public MatchingFilesDataset matchingFilesDataset(Operand<TString> patterns) {
    return MatchingFilesDataset.create(scope, patterns);
  }

  /**
   * Creates a dataset that overrides the maximum intra-op parallelism.
   *
   * @param inputDataset The inputDataset value
   * @param maxIntraOpParallelism Identifies the maximum intra-op parallelism to use.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of MaxIntraOpParallelismDataset
   */
  public MaxIntraOpParallelismDataset maxIntraOpParallelismDataset(
      Operand<? extends TType> inputDataset, Operand<TInt64> maxIntraOpParallelism,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return MaxIntraOpParallelismDataset.create(scope, inputDataset, maxIntraOpParallelism, outputTypes, outputShapes);
  }

  /**
   * Identity transformation that models performance.
   *  Identity transformation that models performance.
   *
   * @param inputDataset A variant tensor representing the input dataset.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ModelDataset
   */
  public ModelDataset modelDataset(Operand<? extends TType> inputDataset,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      ModelDataset.Options... options) {
    return ModelDataset.create(scope, inputDataset, outputTypes, outputShapes, options);
  }

  /**
   * The NonSerializableDataset operation
   *
   * @param inputDataset The inputDataset value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of NonSerializableDataset
   */
  public NonSerializableDataset nonSerializableDataset(Operand<? extends TType> inputDataset,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return NonSerializableDataset.create(scope, inputDataset, outputTypes, outputShapes);
  }

  /**
   * Makes a &quot;one-shot&quot; iterator that can be iterated only once.
   *  A one-shot iterator bundles the logic for defining the dataset and
   *  the state of the iterator in a single op, which allows simple input
   *  pipelines to be defined without an additional initialization
   *  (&quot;MakeIterator&quot;) step.
   *  <p>One-shot iterators have the following limitations:
   *  <ul>
   *  <li>They do not support parameterization: all logic for creating the underlying
   *  dataset must be bundled in the {@code dataset_factory} function.</li>
   *  <li>They are not resettable. Once a one-shot iterator reaches the end of its
   *  underlying dataset, subsequent &quot;IteratorGetNext&quot; operations on that
   *  iterator will always produce an {@code OutOfRange} error.</li>
   *  </ul>
   *  <p>For greater flexibility, use &quot;Iterator&quot; and &quot;MakeIterator&quot; to define
   *  an iterator using an arbitrary subgraph, which may capture tensors
   *  (including fed values) as parameters, and which may be reset multiple
   *  times by rerunning &quot;MakeIterator&quot;.
   *
   * @param datasetFactory A function of type {@code () -> DT_VARIANT}, where the returned
   *  DT_VARIANT is a dataset.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of OneShotIterator
   */
  public OneShotIterator oneShotIterator(ConcreteFunction datasetFactory,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      OneShotIterator.Options... options) {
    return OneShotIterator.create(scope, datasetFactory, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset by applying related optimizations to {@code input_dataset}.
   *  Creates a dataset by applying related optimizations to {@code input_dataset}.
   *
   * @param inputDataset A variant tensor representing the input dataset.
   * @param optimizationsEnabled A {@code tf.string} vector {@code tf.Tensor} identifying user enabled optimizations.
   * @param optimizationsDisabled A {@code tf.string} vector {@code tf.Tensor} identifying user disabled optimizations.
   * @param optimizationsDefault A {@code tf.string} vector {@code tf.Tensor} identifying optimizations by default.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of OptimizeDataset
   */
  public OptimizeDataset optimizeDataset(Operand<? extends TType> inputDataset,
      Operand<TString> optimizationsEnabled, Operand<TString> optimizationsDisabled,
      Operand<TString> optimizationsDefault, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, OptimizeDataset.Options... options) {
    return OptimizeDataset.create(scope, inputDataset, optimizationsEnabled, optimizationsDisabled, optimizationsDefault, outputTypes, outputShapes, options);
  }

  /**
   * Constructs an Optional variant from a tuple of tensors.
   *
   * @param components The components value
   * @return a new instance of OptionalFromValue
   */
  public OptionalFromValue optionalFromValue(Iterable<Operand<?>> components) {
    return OptionalFromValue.create(scope, components);
  }

  /**
   * Returns the value stored in an Optional variant or raises an error if none exists.
   *
   * @param optional The optional value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of OptionalGetValue
   */
  public OptionalGetValue optionalGetValue(Operand<? extends TType> optional,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return OptionalGetValue.create(scope, optional, outputTypes, outputShapes);
  }

  /**
   * Returns true if and only if the given Optional variant has a value.
   *
   * @param optional The optional value
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
   * Creates a dataset by attaching tf.data.Options to {@code input_dataset}.
   *
   * @param inputDataset A variant tensor representing the input dataset.
   * @param serializedOptions A {@code tf.string} scalar {@code tf.Tensor} of serialized {@code tf.data.Options} protocol buffer.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of OptionsDataset
   */
  public OptionsDataset optionsDataset(Operand<? extends TType> inputDataset,
      String serializedOptions, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      OptionsDataset.Options... options) {
    return OptionsDataset.create(scope, inputDataset, serializedOptions, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that batches and pads {@code batch_size} elements from the input.
   *
   * @param inputDataset The inputDataset value
   * @param batchSize A scalar representing the number of elements to accumulate in a
   *  batch.
   * @param paddedShapes A list of int64 tensors representing the desired padded shapes
   *  of the corresponding output components. These shapes may be partially
   *  specified, using {@code -1} to indicate that a particular dimension should be
   *  padded to the maximum size of all batch elements.
   * @param paddingValues A list of scalars containing the padding value to use for
   *  each of the outputs.
   * @param dropRemainder A scalar representing whether the last batch should be dropped in case its size
   *  is smaller than desired.
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of PaddedBatchDataset
   */
  public PaddedBatchDataset paddedBatchDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> batchSize, Iterable<Operand<TInt64>> paddedShapes,
      Iterable<Operand<?>> paddingValues, Operand<TBool> dropRemainder, List<Shape> outputShapes,
      PaddedBatchDataset.Options... options) {
    return PaddedBatchDataset.create(scope, inputDataset, batchSize, paddedShapes, paddingValues, dropRemainder, outputShapes, options);
  }

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
   */
  public ParallelBatchDataset parallelBatchDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> batchSize, Operand<TInt64> numParallelCalls, Operand<TBool> dropRemainder,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      ParallelBatchDataset.Options... options) {
    return ParallelBatchDataset.create(scope, inputDataset, batchSize, numParallelCalls, dropRemainder, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that applies {@code f} to the outputs of {@code input_dataset}.
   *  The resulting dataset is similar to the {@code InterleaveDataset}, except that the
   *  dataset will fetch records from the interleaved datasets in parallel.
   *  <p>The {@code tf.data} Python API creates instances of this op from
   *  {@code Dataset.interleave()} when the {@code num_parallel_calls} parameter of that method
   *  is set to any value other than {@code None}.
   *  <p>By default, the output of this dataset will be deterministic, which may result
   *  in the dataset blocking if the next data item to be returned isn't available.
   *  In order to avoid head-of-line blocking, one can either set the {@code deterministic}
   *  attribute to &quot;false&quot;, or leave it as &quot;default&quot; and set the
   *  {@code experimental_deterministic} parameter of {@code tf.data.Options} to {@code False}.
   *  This can improve performance at the expense of non-determinism.
   *
   * @param inputDataset Dataset that produces a stream of arguments for the function {@code f}.
   * @param otherArguments Additional arguments to pass to {@code f} beyond those produced by {@code input_dataset}.
   *  Evaluated once when the dataset is instantiated.
   * @param cycleLength Number of datasets (each created by applying {@code f} to the elements of
   *  {@code input_dataset}) among which the {@code ParallelInterleaveDatasetV2} will cycle in a
   *  round-robin fashion.
   * @param blockLength Number of elements at a time to produce from each interleaved invocation of a
   *  dataset returned by {@code f}.
   * @param bufferOutputElements The number of elements each iterator being interleaved should buffer (similar
   *  to the {@code .prefetch()} transformation for each interleaved iterator).
   * @param prefetchInputElements Determines the number of iterators to prefetch, allowing buffers to warm up and
   *  data to be pre-fetched without blocking the main thread.
   * @param numParallelCalls Determines the number of threads that should be used for fetching data from
   *  input datasets in parallel. The Python API {@code tf.data.experimental.AUTOTUNE}
   *  constant can be used to indicate that the level of parallelism should be autotuned.
   * @param f A function mapping elements of {@code input_dataset}, concatenated with
   *  {@code other_arguments}, to a Dataset variant that contains elements matching
   *  {@code output_types} and {@code output_shapes}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ParallelInterleaveDataset
   */
  public ParallelInterleaveDataset parallelInterleaveDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, Operand<TInt64> cycleLength, Operand<TInt64> blockLength,
      Operand<TInt64> bufferOutputElements, Operand<TInt64> prefetchInputElements,
      Operand<TInt64> numParallelCalls, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      ParallelInterleaveDataset.Options... options) {
    return ParallelInterleaveDataset.create(scope, inputDataset, otherArguments, cycleLength, blockLength, bufferOutputElements, prefetchInputElements, numParallelCalls, f, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that applies {@code f} to the outputs of {@code input_dataset}.
   *  Unlike a &quot;MapDataset&quot;, which applies {@code f} sequentially, this dataset invokes up
   *  to {@code num_parallel_calls} copies of {@code f} in parallel.
   *
   * @param inputDataset The inputDataset value
   * @param otherArguments The otherArguments value
   * @param numParallelCalls The number of concurrent invocations of {@code f} that process
   *  elements from {@code input_dataset} in parallel.
   * @param f The value of the f attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ParallelMapDataset
   */
  public ParallelMapDataset parallelMapDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, Operand<TInt64> numParallelCalls, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      ParallelMapDataset.Options... options) {
    return ParallelMapDataset.create(scope, inputDataset, otherArguments, numParallelCalls, f, outputTypes, outputShapes, options);
  }

  /**
   * Transforms {@code input_dataset} containing {@code Example} protos as vectors of DT_STRING into a dataset of {@code Tensor} or {@code SparseTensor} objects representing the parsed features.
   *
   * @param inputDataset The inputDataset value
   * @param numParallelCalls The numParallelCalls value
   * @param denseDefaults A dict mapping string keys to {@code Tensor}s.
   *  The keys of the dict must match the dense_keys of the feature.
   * @param sparseKeys A list of string keys in the examples features.
   *  The results for these keys will be returned as {@code SparseTensor} objects.
   * @param denseKeys A list of Ndense string Tensors (scalars).
   *  The keys expected in the Examples features associated with dense values.
   * @param sparseTypes A list of {@code DTypes} of the same length as {@code sparse_keys}.
   *  Only {@code tf.float32} ({@code FloatList}), {@code tf.int64} ({@code Int64List}),
   *  and {@code tf.string} ({@code BytesList}) are supported.
   * @param denseShapes List of tuples with the same length as {@code dense_keys}.
   *  The shape of the data for each dense feature referenced by {@code dense_keys}.
   *  Required for any input tensors identified by {@code dense_keys}.  Must be
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
   */
  public ParseExampleDataset parseExampleDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> numParallelCalls, Iterable<Operand<?>> denseDefaults, List<String> sparseKeys,
      List<String> denseKeys, List<Class<? extends TType>> sparseTypes, List<Shape> denseShapes,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      List<Class<? extends TType>> raggedValueTypes,
      List<Class<? extends TNumber>> raggedSplitTypes, ParseExampleDataset.Options... options) {
    return ParseExampleDataset.create(scope, inputDataset, numParallelCalls, denseDefaults, sparseKeys, denseKeys, sparseTypes, denseShapes, outputTypes, outputShapes, raggedValueTypes, raggedSplitTypes, options);
  }

  /**
   * Creates a dataset that asynchronously prefetches elements from {@code input_dataset}.
   *
   * @param inputDataset The inputDataset value
   * @param bufferSize The maximum number of elements to buffer in an iterator over
   *  this dataset.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of PrefetchDataset
   */
  public PrefetchDataset prefetchDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> bufferSize, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, PrefetchDataset.Options... options) {
    return PrefetchDataset.create(scope, inputDataset, bufferSize, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that uses a custom thread pool to compute {@code input_dataset}.
   *
   * @param inputDataset The inputDataset value
   * @param numThreads Identifies the number of threads to use for the private threadpool.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of PrivateThreadPoolDataset
   */
  public PrivateThreadPoolDataset privateThreadPoolDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> numThreads, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    return PrivateThreadPoolDataset.create(scope, inputDataset, numThreads, outputTypes, outputShapes);
  }

  /**
   * Creates a Dataset that returns pseudorandom numbers.
   *  Creates a Dataset that returns a stream of uniformly distributed
   *  pseudorandom 64-bit signed integers.
   *  <p>In the TensorFlow Python API, you can instantiate this dataset via the
   *  class {@code tf.data.experimental.RandomDataset}.
   *  <p>Instances of this dataset are also created as a result of the
   *  {@code hoist_random_uniform} static optimization. Whether this optimization is
   *  performed is determined by the {@code experimental_optimization.hoist_random_uniform}
   *  option of {@code tf.data.Options}.
   *
   * @param seed A scalar seed for the random number generator. If either seed or
   *  seed2 is set to be non-zero, the random number generator is seeded
   *  by the given seed.  Otherwise, a random seed is used.
   * @param seed2 A second scalar seed to avoid seed collision.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of RandomDataset
   */
  public RandomDataset randomDataset(Operand<TInt64> seed, Operand<TInt64> seed2,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      RandomDataset.Options... options) {
    return RandomDataset.create(scope, seed, seed2, outputTypes, outputShapes, options);
  }

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
   */
  public RangeDataset rangeDataset(Operand<TInt64> start, Operand<TInt64> stop,
      Operand<TInt64> step, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      RangeDataset.Options... options) {
    return RangeDataset.create(scope, start, stop, step, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that changes the batch size.
   *  Creates a dataset that rebatches elements from {@code input_dataset} into new batch
   *  sizes.
   *
   * @param inputDataset A variant tensor representing the input dataset.
   * @param batchSizes A vector of integers representing the size of batches to produce. These values
   *  are cycled through in order.
   * @param dropRemainder The dropRemainder value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of RebatchDatasetV2
   */
  public RebatchDatasetV2 rebatchDatasetV2(Operand<? extends TType> inputDataset,
      Operand<TInt64> batchSizes, Operand<TBool> dropRemainder,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return RebatchDatasetV2.create(scope, inputDataset, batchSizes, dropRemainder, outputTypes, outputShapes);
  }

  /**
   * Reduces the input dataset to a singleton using a reduce function.
   *
   * @param inputDataset A variant tensor representing the input dataset.
   * @param initialState A nested structure of tensors, representing the initial state of the
   *  transformation.
   * @param otherArguments The otherArguments value
   * @param f A function that maps {@code (old_state, input_element)} to {@code new_state}. It must take
   *  two arguments and return a nested structures of tensors. The structure of
   *  {@code new_state} must match the structure of {@code initial_state}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ReduceDataset
   */
  public ReduceDataset reduceDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> initialState, Iterable<Operand<?>> otherArguments, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      ReduceDataset.Options... options) {
    return ReduceDataset.create(scope, inputDataset, initialState, otherArguments, f, outputTypes, outputShapes, options);
  }

  /**
   * Registers a dataset with the tf.data service.
   *
   * @param dataset The dataset value
   * @param address The address value
   * @param protocol The protocol value
   * @param externalStatePolicy The value of the externalStatePolicy attribute
   * @param options carries optional attribute values
   * @return a new instance of RegisterDataset
   */
  public RegisterDataset registerDataset(Operand<? extends TType> dataset, Operand<TString> address,
      Operand<TString> protocol, Long externalStatePolicy, RegisterDataset.Options... options) {
    return RegisterDataset.create(scope, dataset, address, protocol, externalStatePolicy, options);
  }

  /**
   * Creates a dataset that emits the outputs of {@code input_dataset} {@code count} times.
   *
   * @param inputDataset The inputDataset value
   * @param count A scalar representing the number of times that {@code input_dataset} should
   *  be repeated. A value of {@code -1} indicates that it should be repeated infinitely.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of RepeatDataset
   */
  public RepeatDataset repeatDataset(Operand<? extends TType> inputDataset, Operand<TInt64> count,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      RepeatDataset.Options... options) {
    return RepeatDataset.create(scope, inputDataset, count, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that takes a Bernoulli sample of the contents of another dataset.
   *  There is no transformation in the {@code tf.data} Python API for creating this dataset.
   *  Instead, it is created as a result of the {@code filter_with_random_uniform_fusion}
   *  static optimization. Whether this optimization is performed is determined by the
   *  {@code experimental_optimization.filter_with_random_uniform_fusion} option of
   *  {@code tf.data.Options}.
   *
   * @param inputDataset The inputDataset value
   * @param rate A scalar representing the sample rate. Each element of {@code input_dataset} is
   *  retained with this probability, independent of all other elements.
   * @param seed A scalar representing seed of random number generator.
   * @param seed2 A scalar representing seed2 of random number generator.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of SamplingDataset
   */
  public SamplingDataset samplingDataset(Operand<? extends TType> inputDataset,
      Operand<TFloat32> rate, Operand<TInt64> seed, Operand<TInt64> seed2,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return SamplingDataset.create(scope, inputDataset, rate, seed, seed2, outputTypes, outputShapes);
  }

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
   */
  public SaveDataset saveDataset(Operand<? extends TType> inputDataset, Operand<TString> path,
      Iterable<Operand<?>> shardFuncOtherArgs, ConcreteFunction shardFunc,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      SaveDataset.Options... options) {
    return SaveDataset.create(scope, inputDataset, path, shardFuncOtherArgs, shardFunc, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset successively reduces {@code f} over the elements of {@code input_dataset}.
   *
   * @param inputDataset The inputDataset value
   * @param initialState The initialState value
   * @param otherArguments The otherArguments value
   * @param f The value of the f attribute
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ScanDataset
   */
  public ScanDataset scanDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> initialState, Iterable<Operand<?>> otherArguments, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      ScanDataset.Options... options) {
    return ScanDataset.create(scope, inputDataset, initialState, otherArguments, f, outputTypes, outputShapes, options);
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
   * The SetStatsAggregatorDataset operation
   *
   * @param inputDataset The inputDataset value
   * @param statsAggregator The statsAggregator value
   * @param tag The tag value
   * @param counterPrefix The counterPrefix value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of SetStatsAggregatorDataset
   */
  public SetStatsAggregatorDataset setStatsAggregatorDataset(Operand<? extends TType> inputDataset,
      Operand<? extends TType> statsAggregator, Operand<TString> tag,
      Operand<TString> counterPrefix, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    return SetStatsAggregatorDataset.create(scope, inputDataset, statsAggregator, tag, counterPrefix, outputTypes, outputShapes);
  }

  /**
   * Creates a {@code Dataset} that includes only 1/{@code num_shards} of this dataset.
   *
   * @param inputDataset The inputDataset value
   * @param numShards An integer representing the number of shards operating in parallel.
   * @param index An integer representing the current worker index.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ShardDataset
   */
  public ShardDataset shardDataset(Operand<? extends TType> inputDataset, Operand<TInt64> numShards,
      Operand<TInt64> index, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      ShardDataset.Options... options) {
    return ShardDataset.create(scope, inputDataset, numShards, index, outputTypes, outputShapes, options);
  }

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
   */
  public ShuffleAndRepeatDataset shuffleAndRepeatDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> bufferSize, Operand<TInt64> seed, Operand<TInt64> seed2,
      Operand<TInt64> count, Operand<? extends TType> seedGenerator,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      ShuffleAndRepeatDataset.Options... options) {
    return ShuffleAndRepeatDataset.create(scope, inputDataset, bufferSize, seed, seed2, count, seedGenerator, outputTypes, outputShapes, options);
  }

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
   */
  public ShuffleDataset shuffleDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> bufferSize, Operand<TInt64> seed, Operand<TInt64> seed2,
      Operand<? extends TType> seedGenerator, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, ShuffleDataset.Options... options) {
    return ShuffleDataset.create(scope, inputDataset, bufferSize, seed, seed2, seedGenerator, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that skips {@code count} elements from the {@code input_dataset}.
   *
   * @param inputDataset The inputDataset value
   * @param count A scalar representing the number of elements from the {@code input_dataset}
   *  that should be skipped.  If count is -1, skips everything.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of SkipDataset
   */
  public SkipDataset skipDataset(Operand<? extends TType> inputDataset, Operand<TInt64> count,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      SkipDataset.Options... options) {
    return SkipDataset.create(scope, inputDataset, count, outputTypes, outputShapes, options);
  }

  /**
   * The SleepDataset operation
   *
   * @param inputDataset The inputDataset value
   * @param sleepMicroseconds The sleepMicroseconds value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of SleepDataset
   */
  public SleepDataset sleepDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> sleepMicroseconds, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    return SleepDataset.create(scope, inputDataset, sleepMicroseconds, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that passes a sliding window over {@code input_dataset}.
   *
   * @param inputDataset The inputDataset value
   * @param windowSize A scalar representing the number of elements in the
   *  sliding window.
   * @param windowShift A scalar representing the steps moving the sliding window
   *  forward in one iteration. It must be positive.
   * @param windowStride A scalar representing the stride of the input elements of the sliding window.
   *  It must be positive.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of SlidingWindowDataset
   */
  public SlidingWindowDataset slidingWindowDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> windowSize, Operand<TInt64> windowShift, Operand<TInt64> windowStride,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      SlidingWindowDataset.Options... options) {
    return SlidingWindowDataset.create(scope, inputDataset, windowSize, windowShift, windowStride, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that will write to / read from a snapshot.
   *  This dataset attempts to determine whether a valid snapshot exists at the
   *  {@code snapshot_path}, and reads from the snapshot in lieu of using {@code input_dataset}.
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
   */
  public SnapshotDataset snapshotDataset(Operand<? extends TType> inputDataset,
      Operand<TString> path, Iterable<Operand<?>> readerFuncOtherArgs,
      Iterable<Operand<?>> shardFuncOtherArgs, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, ConcreteFunction readerFunc, ConcreteFunction shardFunc,
      SnapshotDataset.Options... options) {
    return SnapshotDataset.create(scope, inputDataset, path, readerFuncOtherArgs, shardFuncOtherArgs, outputTypes, outputShapes, readerFunc, shardFunc, options);
  }

  /**
   * Creates a dataset that splits a SparseTensor into elements row-wise.
   *
   * @param indices The indices value
   * @param values The values value
   * @param denseShape The denseShape value
   * @return a new instance of SparseTensorSliceDataset
   */
  public SparseTensorSliceDataset sparseTensorSliceDataset(Operand<TInt64> indices,
      Operand<? extends TType> values, Operand<TInt64> denseShape) {
    return SparseTensorSliceDataset.create(scope, indices, values, denseShape);
  }

  /**
   * Creates a dataset that executes a SQL query and emits rows of the result set.
   *
   * @param driverName The database type. Currently, the only supported type is 'sqlite'.
   * @param dataSourceName A connection string to connect to the database.
   * @param query A SQL query to execute.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of SqlDataset
   */
  public SqlDataset sqlDataset(Operand<TString> driverName, Operand<TString> dataSourceName,
      Operand<TString> query, List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return SqlDataset.create(scope, driverName, dataSourceName, query, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that contains {@code count} elements from the {@code input_dataset}.
   *
   * @param inputDataset The inputDataset value
   * @param count A scalar representing the number of elements from the {@code input_dataset}
   *  that should be taken. A value of {@code -1} indicates that all of {@code input_dataset}
   *  is taken.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of TakeDataset
   */
  public TakeDataset takeDataset(Operand<? extends TType> inputDataset, Operand<TInt64> count,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      TakeDataset.Options... options) {
    return TakeDataset.create(scope, inputDataset, count, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that stops iteration when predicate` is false.
   *  The {@code predicate} function must return a scalar boolean and accept the
   *  following arguments:
   *  <ul>
   *  <li>One tensor for each component of an element of {@code input_dataset}.</li>
   *  <li>One tensor for each value in {@code other_arguments}.</li>
   *  </ul>
   *
   * @param inputDataset The inputDataset value
   * @param otherArguments A list of tensors, typically values that were captured when
   *  building a closure for {@code predicate}.
   * @param predicate A function returning a scalar boolean.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of TakeWhileDataset
   */
  public TakeWhileDataset takeWhileDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, ConcreteFunction predicate,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      TakeWhileDataset.Options... options) {
    return TakeWhileDataset.create(scope, inputDataset, otherArguments, predicate, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that emits {@code components} as a tuple of tensors once.
   *
   * @param components The components value
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of TensorDataset
   */
  public TensorDataset tensorDataset(Iterable<Operand<?>> components, List<Shape> outputShapes,
      TensorDataset.Options... options) {
    return TensorDataset.create(scope, components, outputShapes, options);
  }

  /**
   * Creates a dataset that emits each dim-0 slice of {@code components} once.
   *
   * @param components The components value
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of TensorSliceDataset
   */
  public TensorSliceDataset tensorSliceDataset(Iterable<Operand<?>> components,
      List<Shape> outputShapes, TensorSliceDataset.Options... options) {
    return TensorSliceDataset.create(scope, components, outputShapes, options);
  }

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
   */
  public TextLineDataset textLineDataset(Operand<TString> filenames,
      Operand<TString> compressionType, Operand<TInt64> bufferSize,
      TextLineDataset.Options... options) {
    return TextLineDataset.create(scope, filenames, compressionType, bufferSize, options);
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
   * @param options carries optional attribute values
   * @return a new instance of TfRecordDataset
   */
  public TfRecordDataset tfRecordDataset(Operand<TString> filenames,
      Operand<TString> compressionType, Operand<TInt64> bufferSize,
      TfRecordDataset.Options... options) {
    return TfRecordDataset.create(scope, filenames, compressionType, bufferSize, options);
  }

  /**
   * Creates a dataset that uses a custom thread pool to compute {@code input_dataset}.
   *
   * @param inputDataset The inputDataset value
   * @param threadPool A resource produced by the ThreadPoolHandle op.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of ThreadPoolDataset
   */
  public ThreadPoolDataset threadPoolDataset(Operand<? extends TType> inputDataset,
      Operand<? extends TType> threadPool, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes) {
    return ThreadPoolDataset.create(scope, inputDataset, threadPool, outputTypes, outputShapes);
  }

  /**
   * A dataset that splits the elements of its input into multiple elements.
   *
   * @param inputDataset The inputDataset value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of UnbatchDataset
   */
  public UnbatchDataset unbatchDataset(Operand<? extends TType> inputDataset,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      UnbatchDataset.Options... options) {
    return UnbatchDataset.create(scope, inputDataset, outputTypes, outputShapes, options);
  }

  /**
   * Creates a dataset that contains the unique elements of {@code input_dataset}.
   *
   * @param inputDataset The inputDataset value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of UniqueDataset
   */
  public UniqueDataset uniqueDataset(Operand<? extends TType> inputDataset,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      UniqueDataset.Options... options) {
    return UniqueDataset.create(scope, inputDataset, outputTypes, outputShapes, options);
  }

  /**
   * The UnwrapDatasetVariant operation
   *
   * @param inputHandle The inputHandle value
   * @return a new instance of UnwrapDatasetVariant
   */
  public UnwrapDatasetVariant unwrapDatasetVariant(Operand<? extends TType> inputHandle) {
    return UnwrapDatasetVariant.create(scope, inputHandle);
  }

  /**
   * Combines (nests of) input elements into a dataset of (nests of) windows.
   *  <p>A &quot;window&quot; is a finite dataset of flat elements of size {@code size} (or possibly
   *  fewer if there are not enough input elements to fill the window and
   *  {@code drop_remainder} evaluates to false).
   *  <p>The {@code shift} argument determines the number of input elements by which
   *  the window moves on each iteration.  The first element in the {@code k}th window
   *  will be element
   *  <pre>
   *  1 + (k-1) * shift
   *  </pre>
   *  <p>of the input dataset. In particular, the first element of the first window
   *  will always be the first element of the input dataset.
   *  <p>If the {@code stride} parameter is greater than 1, then each window will skip
   *  {@code (stride - 1)} input elements between each element that appears in the
   *  window. Output windows will still contain {@code size} elements regardless of
   *  the value of {@code stride}.
   *  <p>The {@code stride} argument determines the stride of the input elements, and the
   *  {@code shift} argument determines the shift of the window.
   *  <p>For example, letting {@code {...}} to represent a Dataset:
   *  <ul>
   *  <li>{@code tf.data.Dataset.range(7).window(2)} produces
   *  {@code {{0, 1}, {2, 3}, {4, 5}, {6}}}</li>
   *  <li>{@code tf.data.Dataset.range(7).window(3, 2, 1, True)} produces
   *  {@code {{0, 1, 2}, {2, 3, 4}, {4, 5, 6}}}</li>
   *  <li>{@code tf.data.Dataset.range(7).window(3, 1, 2, True)} produces
   *  {@code {{0, 2, 4}, {1, 3, 5}, {2, 4, 6}}}</li>
   *  </ul>
   *  <p>Note that when the {@code window} transformation is applied to a dataset of
   *  nested elements, it produces a dataset of nested windows.
   *  <p>For example:
   *  <ul>
   *  <li>{@code tf.data.Dataset.from_tensor_slices((range(4), range(4))).window(2)}
   *  produces {@code {({0, 1}, {0, 1}), ({2, 3}, {2, 3})}}</li>
   *  <li>{@code tf.data.Dataset.from_tensor_slices({"a": range(4)}).window(2)}
   *  produces {@code {{"a": {0, 1}}, {"a": {2, 3}}}}</li>
   *  </ul>
   *
   * @param inputDataset The inputDataset value
   * @param sizeOutput An integer scalar, representing the number of elements
   *  of the input dataset to combine into a window. Must be positive.
   * @param shift An integer scalar, representing the number of input elements
   *  by which the window moves in each iteration.  Defaults to {@code size}.
   *  Must be positive.
   * @param stride An integer scalar, representing the stride of the input elements
   *  in the sliding window. Must be positive. The default value of 1 means
   *  &quot;retain every input element&quot;.
   * @param dropRemainder A Boolean scalar, representing whether the last window should be
   *  dropped if its size is smaller than {@code window_size}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of WindowDataset
   */
  public WindowDataset windowDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> sizeOutput, Operand<TInt64> shift, Operand<TInt64> stride,
      Operand<TBool> dropRemainder, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, WindowDataset.Options... options) {
    return WindowDataset.create(scope, inputDataset, sizeOutput, shift, stride, dropRemainder, outputTypes, outputShapes, options);
  }

  /**
   * The WrapDatasetVariant operation
   *
   * @param inputHandle The inputHandle value
   * @return a new instance of WrapDatasetVariant
   */
  public WrapDatasetVariant wrapDatasetVariant(Operand<? extends TType> inputHandle) {
    return WrapDatasetVariant.create(scope, inputHandle);
  }

  /**
   * Creates a dataset that zips together {@code input_datasets}.
   *  The elements of the resulting dataset are created by zipping corresponding
   *  elements from each of the input datasets.
   *  <p>The size of the resulting dataset will match the size of the smallest input
   *  dataset, and no error will be raised if input datasets have different sizes.
   *
   * @param inputDatasets List of {@code N} variant Tensors representing datasets to be zipped together.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of ZipDataset
   */
  public ZipDataset zipDataset(Iterable<Operand<? extends TType>> inputDatasets,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      ZipDataset.Options... options) {
    return ZipDataset.create(scope, inputDatasets, outputTypes, outputShapes, options);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
