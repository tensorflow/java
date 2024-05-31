// Copyright 2020-2022 The TensorFlow Authors. All Rights Reserved.
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
import org.tensorflow.op.data.experimental.AssertNextDataset;
import org.tensorflow.op.data.experimental.AutoShardDataset;
import org.tensorflow.op.data.experimental.BytesProducedStatsDataset;
import org.tensorflow.op.data.experimental.CSVDataset;
import org.tensorflow.op.data.experimental.ChooseFastestDataset;
import org.tensorflow.op.data.experimental.DatasetCardinality;
import org.tensorflow.op.data.experimental.DatasetToTFRecord;
import org.tensorflow.op.data.experimental.DenseToSparseBatchDataset;
import org.tensorflow.op.data.experimental.DirectedInterleaveDataset;
import org.tensorflow.op.data.experimental.GroupByReducerDataset;
import org.tensorflow.op.data.experimental.GroupByWindowDataset;
import org.tensorflow.op.data.experimental.IgnoreErrorsDataset;
import org.tensorflow.op.data.experimental.IteratorGetDevice;
import org.tensorflow.op.data.experimental.LatencyStatsDataset;
import org.tensorflow.op.data.experimental.LmdbDataset;
import org.tensorflow.op.data.experimental.MapAndBatchDataset;
import org.tensorflow.op.data.experimental.MapDataset;
import org.tensorflow.op.data.experimental.MatchingFilesDataset;
import org.tensorflow.op.data.experimental.MaxIntraOpParallelismDataset;
import org.tensorflow.op.data.experimental.NonSerializableDataset;
import org.tensorflow.op.data.experimental.ParallelInterleaveDataset;
import org.tensorflow.op.data.experimental.ParseExampleDataset;
import org.tensorflow.op.data.experimental.PrivateThreadPoolDataset;
import org.tensorflow.op.data.experimental.RandomDataset;
import org.tensorflow.op.data.experimental.RebatchDataset;
import org.tensorflow.op.data.experimental.ScanDataset;
import org.tensorflow.op.data.experimental.SetStatsAggregatorDataset;
import org.tensorflow.op.data.experimental.SleepDataset;
import org.tensorflow.op.data.experimental.SlidingWindowDataset;
import org.tensorflow.op.data.experimental.SqlDataset;
import org.tensorflow.op.data.experimental.StatsAggregatorHandle;
import org.tensorflow.op.data.experimental.StatsAggregatorSummary;
import org.tensorflow.op.data.experimental.TakeWhileDataset;
import org.tensorflow.op.data.experimental.ThreadPoolDataset;
import org.tensorflow.op.data.experimental.ThreadPoolHandle;
import org.tensorflow.op.data.experimental.UnbatchDataset;
import org.tensorflow.op.data.experimental.UniqueDataset;
import org.tensorflow.types.TBool;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code data.experimental} operations as {@link Op Op}s
 *
 * @see Ops
 */
public final class DataExperimentalOps {
  private final Scope scope;

  private final Ops ops;

  DataExperimentalOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * The ExperimentalAssertNextDataset operation
   *
   * @param inputDataset The inputDataset value
   * @param transformations The transformations value
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
   * The ExperimentalCSVDataset operation
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
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of CSVDataset
   */
  public CSVDataset cSVDataset(Operand<TString> filenames, Operand<TString> compressionType,
      Operand<TInt64> bufferSize, Operand<TBool> header, Operand<TString> fieldDelim,
      Operand<TBool> useQuoteDelim, Operand<TString> naValue, Operand<TInt64> selectCols,
      Iterable<Operand<?>> recordDefaults, List<Shape> outputShapes) {
    return CSVDataset.create(scope, filenames, compressionType, bufferSize, header, fieldDelim, useQuoteDelim, naValue, selectCols, recordDefaults, outputShapes);
  }

  /**
   * The ExperimentalChooseFastestDataset operation
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
   * Writes the given dataset to the given file using the TFRecord format.
   *
   * @param inputDataset A variant tensor representing the dataset to write.
   * @param filename A scalar string tensor representing the filename to use.
   * @param compressionType A scalar string tensor containing either (i) the empty string (no
   *  compression), (ii) &quot;ZLIB&quot;, or (iii) &quot;GZIP&quot;.
   * @return a new instance of DatasetToTFRecord
   */
  public DatasetToTFRecord datasetToTFRecord(Operand<? extends TType> inputDataset,
      Operand<TString> filename, Operand<TString> compressionType) {
    return DatasetToTFRecord.create(scope, inputDataset, filename, compressionType);
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
   * A substitute for {@code InterleaveDataset} on a fixed list of {@code N} datasets.
   *
   * @param selectorInputDataset A dataset of scalar {@code DT_INT64} elements that determines which of the
   *  {@code N} data inputs should produce the next output element.
   * @param dataInputDatasets {@code N} datasets with the same type that will be interleaved according to
   *  the values of {@code selector_input_dataset}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of DirectedInterleaveDataset
   */
  public DirectedInterleaveDataset directedInterleaveDataset(
      Operand<? extends TType> selectorInputDataset,
      Iterable<Operand<? extends TType>> dataInputDatasets,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return DirectedInterleaveDataset.create(scope, selectorInputDataset, dataInputDatasets, outputTypes, outputShapes);
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
   * @return a new instance of GroupByWindowDataset
   */
  public GroupByWindowDataset groupByWindowDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> keyFuncOtherArguments, Iterable<Operand<?>> reduceFuncOtherArguments,
      Iterable<Operand<?>> windowSizeFuncOtherArguments, ConcreteFunction keyFunc,
      ConcreteFunction reduceFunc, ConcreteFunction windowSizeFunc,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return GroupByWindowDataset.create(scope, inputDataset, keyFuncOtherArguments, reduceFuncOtherArguments, windowSizeFuncOtherArguments, keyFunc, reduceFunc, windowSizeFunc, outputTypes, outputShapes);
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
   * Returns the name of the device on which {@code resource} has been placed.
   *
   * @param resource The resource value
   * @return a new instance of IteratorGetDevice
   */
  public IteratorGetDevice iteratorGetDevice(Operand<? extends TType> resource) {
    return IteratorGetDevice.create(scope, resource);
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
   * The ExperimentalLMDBDataset operation
   *
   * @param filenames The filenames value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of LmdbDataset
   */
  public LmdbDataset lmdbDataset(Operand<TString> filenames,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return LmdbDataset.create(scope, filenames, outputTypes, outputShapes);
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
   * The ExperimentalMatchingFilesDataset operation
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
   * The ExperimentalNonSerializableDataset operation
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
   * @param sloppy The sloppy value
   * @param bufferOutputElements The bufferOutputElements value
   * @param prefetchInputElements The prefetchInputElements value
   * @param f A function mapping elements of {@code input_dataset}, concatenated with
   *  {@code other_arguments}, to a Dataset variant that contains elements matching
   *  {@code output_types} and {@code output_shapes}.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of ParallelInterleaveDataset
   */
  public ParallelInterleaveDataset parallelInterleaveDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, Operand<TInt64> cycleLength, Operand<TInt64> blockLength,
      Operand<TBool> sloppy, Operand<TInt64> bufferOutputElements,
      Operand<TInt64> prefetchInputElements, ConcreteFunction f,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return ParallelInterleaveDataset.create(scope, inputDataset, otherArguments, cycleLength, blockLength, sloppy, bufferOutputElements, prefetchInputElements, f, outputTypes, outputShapes);
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
   * @param options carries optional attribute values
   * @return a new instance of ParseExampleDataset
   */
  public ParseExampleDataset parseExampleDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> numParallelCalls, Iterable<Operand<?>> denseDefaults, List<String> sparseKeys,
      List<String> denseKeys, List<Class<? extends TType>> sparseTypes, List<Shape> denseShapes,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes,
      ParseExampleDataset.Options... options) {
    return ParseExampleDataset.create(scope, inputDataset, numParallelCalls, denseDefaults, sparseKeys, denseKeys, sparseTypes, denseShapes, outputTypes, outputShapes, options);
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
   *
   * @param seed A scalar seed for the random number generator. If either seed or
   *  seed2 is set to be non-zero, the random number generator is seeded
   *  by the given seed.  Otherwise, a random seed is used.
   * @param seed2 A second scalar seed to avoid seed collision.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of RandomDataset
   */
  public RandomDataset randomDataset(Operand<TInt64> seed, Operand<TInt64> seed2,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return RandomDataset.create(scope, seed, seed2, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that changes the batch size.
   *  Creates a dataset that changes the batch size of the dataset to current batch
   *  size // num_replicas.
   *
   * @param inputDataset A variant tensor representing the input dataset.
   * @param numReplicas A scalar representing the number of replicas to distribute this batch across. As
   *  a result of this transformation the current batch size would end up being
   *  divided  by this parameter.
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @param options carries optional attribute values
   * @return a new instance of RebatchDataset
   */
  public RebatchDataset rebatchDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> numReplicas, List<Class<? extends TType>> outputTypes,
      List<Shape> outputShapes, RebatchDataset.Options... options) {
    return RebatchDataset.create(scope, inputDataset, numReplicas, outputTypes, outputShapes, options);
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
   * The ExperimentalSetStatsAggregatorDataset operation
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
   * The ExperimentalSleepDataset operation
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
   * @return a new instance of SlidingWindowDataset
   */
  public SlidingWindowDataset slidingWindowDataset(Operand<? extends TType> inputDataset,
      Operand<TInt64> windowSize, Operand<TInt64> windowShift, Operand<TInt64> windowStride,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return SlidingWindowDataset.create(scope, inputDataset, windowSize, windowShift, windowStride, outputTypes, outputShapes);
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
   * Creates a statistics manager resource.
   *
   * @param options carries optional attribute values
   * @return a new instance of StatsAggregatorHandle
   */
  public StatsAggregatorHandle statsAggregatorHandle(StatsAggregatorHandle.Options... options) {
    return StatsAggregatorHandle.create(scope, options);
  }

  /**
   * Produces a summary of any statistics recorded by the given statistics manager.
   *
   * @param iterator The iterator value
   * @return a new instance of StatsAggregatorSummary
   */
  public StatsAggregatorSummary statsAggregatorSummary(Operand<? extends TType> iterator) {
    return StatsAggregatorSummary.create(scope, iterator);
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
   * @return a new instance of TakeWhileDataset
   */
  public TakeWhileDataset takeWhileDataset(Operand<? extends TType> inputDataset,
      Iterable<Operand<?>> otherArguments, ConcreteFunction predicate,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return TakeWhileDataset.create(scope, inputDataset, otherArguments, predicate, outputTypes, outputShapes);
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
   * Creates a dataset that uses a custom thread pool to compute {@code input_dataset}.
   *
   * @param numThreads The number of threads in the thread pool.
   * @param displayName A human-readable name for the threads that may be visible in some
   *  visualizations.
   *  threadpool.
   * @param options carries optional attribute values
   * @return a new instance of ThreadPoolHandle
   */
  public ThreadPoolHandle threadPoolHandle(Long numThreads, String displayName,
      ThreadPoolHandle.Options... options) {
    return ThreadPoolHandle.create(scope, numThreads, displayName, options);
  }

  /**
   * A dataset that splits the elements of its input into multiple elements.
   *
   * @param inputDataset The inputDataset value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of UnbatchDataset
   */
  public UnbatchDataset unbatchDataset(Operand<? extends TType> inputDataset,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return UnbatchDataset.create(scope, inputDataset, outputTypes, outputShapes);
  }

  /**
   * Creates a dataset that contains the unique elements of {@code input_dataset}.
   *
   * @param inputDataset The inputDataset value
   * @param outputTypes The value of the outputTypes attribute
   * @param outputShapes The value of the outputShapes attribute
   * @return a new instance of UniqueDataset
   */
  public UniqueDataset uniqueDataset(Operand<? extends TType> inputDataset,
      List<Class<? extends TType>> outputTypes, List<Shape> outputShapes) {
    return UniqueDataset.create(scope, inputDataset, outputTypes, outputShapes);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
