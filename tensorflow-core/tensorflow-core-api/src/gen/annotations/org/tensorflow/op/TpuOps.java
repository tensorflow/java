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
import org.tensorflow.op.tpu.CollateTPUEmbeddingMemory;
import org.tensorflow.op.tpu.Compile;
import org.tensorflow.op.tpu.CompileSucceededAssert;
import org.tensorflow.op.tpu.ComputeDedupDataSize;
import org.tensorflow.op.tpu.ConfigureAndInitializeGlobalTPU;
import org.tensorflow.op.tpu.ConfigureTPUEmbeddingHost;
import org.tensorflow.op.tpu.ConfigureTPUEmbeddingMemory;
import org.tensorflow.op.tpu.ConnectTPUEmbeddingHosts;
import org.tensorflow.op.tpu.ConvertToCooTensor;
import org.tensorflow.op.tpu.DTensorRestore;
import org.tensorflow.op.tpu.Execute;
import org.tensorflow.op.tpu.ExecuteAndUpdateVariables;
import org.tensorflow.op.tpu.ExecuteTPUEmbeddingPartitioner;
import org.tensorflow.op.tpu.FinalizeTPUEmbedding;
import org.tensorflow.op.tpu.GetMinibatchSplitsWithPhysicalReplica;
import org.tensorflow.op.tpu.GetMinibatchesInCsrWithPhysicalReplica;
import org.tensorflow.op.tpu.GlobalIterId;
import org.tensorflow.op.tpu.PartitionedOutput;
import org.tensorflow.op.tpu.ShutdownTPUSystem;
import org.tensorflow.op.tpu.StoreMinibatchStatisticsInFdo;
import org.tensorflow.op.tpu.TPUAnnotateTensorsWithDynamicShape;
import org.tensorflow.op.tpu.TPUCopyWithDynamicShape;
import org.tensorflow.op.tpu.TPURoundRobin;
import org.tensorflow.op.tpu.TpuHandleToProtoKey;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TType;

/**
 * An API for building {@code tpu} operations as {@link Op Op}s
 *
 * @see {@link Ops}
 */
public final class TpuOps {
  private final Scope scope;

  private final Ops ops;

  TpuOps(Ops ops) {
    this.scope = ops.scope();
    this.ops = ops;
  }

  /**
   * An op that merges the string-encoded memory config protos from all hosts.
   *
   * @param memoryConfigs String-encoded memory config protos containing metadata about
   *  the memory allocations reserved for TPUEmbedding across all hosts.
   * @return a new instance of CollateTPUEmbeddingMemory
   */
  public CollateTPUEmbeddingMemory collateTPUEmbeddingMemory(
      Iterable<Operand<TString>> memoryConfigs) {
    return CollateTPUEmbeddingMemory.create(scope, memoryConfigs);
  }

  /**
   * Compiles a computations for execution on one or more TPU devices.
   *  For the internal use of the distributed TPU compiler.
   *  <p>'num_computations' is the number of computations to be compiled.
   *  'function' is a function containing the computation to compile.
   *  'dynamic_shapes' contains dynamic shapes of arguments whose shapes were not
   *  known statically at TPUReplication rewrite time.
   *  'guaranteed_constants' is a list of tensors which have been guaranteed to not
   *  change their values during the session lifetime. These contain tensors marked as
   *  constant using the GuaranteeConstOp.
   *  'metadata' is a serialized TPUCompileMetadataProto describing
   *  the shapes and types of the inputs to the computation, as well as a mapping onto
   *  the TPU pod topology.
   *  Each 'program' output is a string key that is passed to the _TPUExecute op and
   *  used to look up the program in the compilation cache.
   *  'may_modify_variables' indicates whether variables may be modified.
   *
   * @param dynamicShapes The dynamicShapes value
   * @param guaranteedConstants The guaranteedConstants value
   * @param numComputations The value of the numComputations attribute
   * @param function The value of the function attribute
   * @param metadata The value of the metadata attribute
   * @return a new instance of Compile
   */
  public Compile compile(Iterable<Operand<TInt64>> dynamicShapes,
      Iterable<Operand<?>> guaranteedConstants, Long numComputations, ConcreteFunction function,
      String metadata) {
    return Compile.create(scope, dynamicShapes, guaranteedConstants, numComputations, function, metadata);
  }

  /**
   * Asserts that compilation succeeded.
   *  This op produces no output and closes the device during failure to ensure all
   *  pending device interactions fail.
   *  <p>'compilation_status' is a serialized CompilationResultProto.
   *
   * @param compilationStatus The compilationStatus value
   * @return a new instance of CompileSucceededAssert
   */
  public CompileSucceededAssert compileSucceededAssert(Operand<TString> compilationStatus) {
    return CompileSucceededAssert.create(scope, compilationStatus);
  }

  /**
   * An op computes the size of the deduplication data from embedding core and returns the updated config.
   *  This op is to compute size of the deduplication data so to provide this
   *  information to the op that computes the tuple mask of deduplication data can
   *  have static output shape.
   *
   * @param config Serialized TPUEmbeddingConfiguration proto.
   * @return a new instance of ComputeDedupDataSize
   */
  public ComputeDedupDataSize computeDedupDataSize(String config) {
    return ComputeDedupDataSize.create(scope, config);
  }

  /**
   * An op that sets up the centralized structures for a distributed TPU system.
   *
   * @param options carries optional attribute values
   * @return a new instance of ConfigureAndInitializeGlobalTPU
   */
  public ConfigureAndInitializeGlobalTPU configureAndInitializeGlobalTPU(
      ConfigureAndInitializeGlobalTPU.Options... options) {
    return ConfigureAndInitializeGlobalTPU.create(scope, options);
  }

  /**
   * An op that configures the TPUEmbedding software on a host.
   *
   * @param commonConfig A string-encoded common configuration proto containing metadata
   *  about the TPUEmbedding partitioner output.
   * @param memoryConfig A string-encoded memory config proto containing metadata about
   *  the memory allocations reserved for TPUEmbedding.
   * @param config An TPUEmbeddingConfiguration proto serialized to a string,
   *  describing the desired TPUEmbedding configuration.
   * @return a new instance of ConfigureTPUEmbeddingHost
   */
  public ConfigureTPUEmbeddingHost configureTPUEmbeddingHost(Operand<TString> commonConfig,
      Operand<TString> memoryConfig, String config) {
    return ConfigureTPUEmbeddingHost.create(scope, commonConfig, memoryConfig, config);
  }

  /**
   * An op that configures the TPUEmbedding software on a host.
   *
   * @param commonConfig A string-encoded CommonConfiguration proto containing metadata
   *  about the TPUEmbedding partitioner output and the HBM size (in bytes) required
   *  for operation.
   * @return a new instance of ConfigureTPUEmbeddingMemory
   */
  public ConfigureTPUEmbeddingMemory configureTPUEmbeddingMemory(Operand<TString> commonConfig) {
    return ConfigureTPUEmbeddingMemory.create(scope, commonConfig);
  }

  /**
   * An op that sets up communication between TPUEmbedding host software instances
   *  after ConfigureTPUEmbeddingHost has been called on each host.
   *
   * @param networkConfigs Strings containing metadata about the hostname and RPC port
   *  used for communication with all hosts.
   * @return a new instance of ConnectTPUEmbeddingHosts
   */
  public ConnectTPUEmbeddingHosts connectTPUEmbeddingHosts(
      Iterable<Operand<TString>> networkConfigs) {
    return ConnectTPUEmbeddingHosts.create(scope, networkConfigs);
  }

  /**
   * The ConvertToCooTensor operation
   *
   * @param indicesOrRowSplits The indicesOrRowSplits value
   * @param values The values value
   * @param weights The weights value
   * @param sampleCount The value of the sampleCount attribute
   * @param combiner The value of the combiner attribute
   * @return a new instance of ConvertToCooTensor
   */
  public ConvertToCooTensor convertToCooTensor(Operand<TInt32> indicesOrRowSplits,
      Operand<TInt32> values, Operand<TFloat32> weights, Long sampleCount, String combiner) {
    return ConvertToCooTensor.create(scope, indicesOrRowSplits, values, weights, sampleCount, combiner);
  }

  /**
   * The DTensorRestoreV2 operation
   *
   * @param prefix The prefix value
   * @param tensorNames The tensorNames value
   * @param shapeAndSlices The shapeAndSlices value
   * @param inputShapes The value of the inputShapes attribute
   * @param inputLayouts The value of the inputLayouts attribute
   * @param dtypes The value of the dtypes attribute
   * @return a new instance of DTensorRestore
   */
  public DTensorRestore dTensorRestore(Operand<TString> prefix, Operand<TString> tensorNames,
      Operand<TString> shapeAndSlices, List<Shape> inputShapes, List<String> inputLayouts,
      List<Class<? extends TType>> dtypes) {
    return DTensorRestore.create(scope, prefix, tensorNames, shapeAndSlices, inputShapes, inputLayouts, dtypes);
  }

  /**
   * Op that loads and executes a TPU program on a TPU device.
   *  For the internal use of the distributed TPU compiler.
   *
   * @param args The args value
   * @param key The key value
   * @param Tresults The value of the Tresults attribute
   * @return a new instance of Execute
   */
  public Execute execute(Iterable<Operand<?>> args, Operand<TString> key,
      List<Class<? extends TType>> Tresults) {
    return Execute.create(scope, args, key, Tresults);
  }

  /**
   * Op that executes a program with optional in-place variable updates.
   *  It (optionally) reads device variables, loads and executes a TPU program on a
   *  TPU device, and then (optionally) in-place updates variables using the program
   *  outputs, as specified in attributes device_var_reads_indices (program input
   *  indices from directly reading variables) and device_var_updates_indices (program
   *  output indices used to update variables, -1 means no-update/read-only). Such
   *  program outputs are consumed by these variables will not appear in the op
   *  output. For the internal use of the distributed TPU compiler.
   *
   * @param args The args value
   * @param key The key value
   * @param Tresults The value of the Tresults attribute
   * @param deviceVarReadsIndices The value of the deviceVarReadsIndices attribute
   * @param deviceVarUpdatesIndices The value of the deviceVarUpdatesIndices attribute
   * @return a new instance of ExecuteAndUpdateVariables
   */
  public ExecuteAndUpdateVariables executeAndUpdateVariables(Iterable<Operand<?>> args,
      Operand<TString> key, List<Class<? extends TType>> Tresults, List<Long> deviceVarReadsIndices,
      List<Long> deviceVarUpdatesIndices) {
    return ExecuteAndUpdateVariables.create(scope, args, key, Tresults, deviceVarReadsIndices, deviceVarUpdatesIndices);
  }

  /**
   * An op that executes the TPUEmbedding partitioner on the central configuration
   *  device and computes the HBM size (in bytes) required for TPUEmbedding operation.
   *
   * @param config An TPUEmbeddingConfiguration proto serialized to a string,
   *  describing the desired TPUEmbedding configuration.
   * @return a new instance of ExecuteTPUEmbeddingPartitioner
   */
  public ExecuteTPUEmbeddingPartitioner executeTPUEmbeddingPartitioner(String config) {
    return ExecuteTPUEmbeddingPartitioner.create(scope, config);
  }

  /**
   * An op that finalizes the TPUEmbedding configuration.
   *
   * @param commonConfig A string-encoded common configuration proto containing metadata
   *  about the TPUEmbedding partitioner output and the HBM size (in bytes) required
   *  for operation.
   * @param memoryConfig A string-encoded memory config proto containing metadata about
   *  the memory allocations reserved for TPUEmbedding.
   * @return a new instance of FinalizeTPUEmbedding
   */
  public FinalizeTPUEmbedding finalizeTPUEmbedding(Operand<TString> commonConfig,
      Operand<TString> memoryConfig) {
    return FinalizeTPUEmbedding.create(scope, commonConfig, memoryConfig);
  }

  /**
   * The GetMinibatchSplitsWithPhysicalReplica operation
   *
   * @param programKey The programKey value
   * @param rowIds The rowIds value
   * @param colIds The colIds value
   * @param gains The gains value
   * @param sampleCount The value of the sampleCount attribute
   * @param numReplica The value of the numReplica attribute
   * @param tableVocabSize The value of the tableVocabSize attribute
   * @param featureWidth The value of the featureWidth attribute
   * @param numScPerChip The value of the numScPerChip attribute
   * @param tableName The value of the tableName attribute
   * @param miniBatchSplits The value of the miniBatchSplits attribute
   * @return a new instance of GetMinibatchSplitsWithPhysicalReplica
   */
  public GetMinibatchSplitsWithPhysicalReplica getMinibatchSplitsWithPhysicalReplica(
      Operand<TString> programKey, Operand<TInt32> rowIds, Operand<TInt32> colIds,
      Operand<TFloat32> gains, Long sampleCount, Long numReplica, Long tableVocabSize,
      Long featureWidth, Long numScPerChip, String tableName, String miniBatchSplits) {
    return GetMinibatchSplitsWithPhysicalReplica.create(scope, programKey, rowIds, colIds, gains, sampleCount, numReplica, tableVocabSize, featureWidth, numScPerChip, tableName, miniBatchSplits);
  }

  /**
   * The GetMinibatchesInCsrWithPhysicalReplica operation
   *
   * @param programKey The programKey value
   * @param rowIds The rowIds value
   * @param colIds The colIds value
   * @param gains The gains value
   * @param splits The splits value
   * @param idCounts The idCounts value
   * @param sampleCount The value of the sampleCount attribute
   * @param numReplica The value of the numReplica attribute
   * @param maxMinibatchesPerSc The value of the maxMinibatchesPerSc attribute
   * @param maxIdsPerChipPerSample The value of the maxIdsPerChipPerSample attribute
   * @param tableVocabSize The value of the tableVocabSize attribute
   * @param featureWidth The value of the featureWidth attribute
   * @param numScPerChip The value of the numScPerChip attribute
   * @param tableName The value of the tableName attribute
   * @param miniBatchInCsr The value of the miniBatchInCsr attribute
   * @return a new instance of GetMinibatchesInCsrWithPhysicalReplica
   */
  public GetMinibatchesInCsrWithPhysicalReplica getMinibatchesInCsrWithPhysicalReplica(
      Operand<TString> programKey, Operand<TInt32> rowIds, Operand<TInt32> colIds,
      Operand<TFloat32> gains, Operand<TInt64> splits, Operand<TInt32> idCounts, Long sampleCount,
      Long numReplica, Long maxMinibatchesPerSc, Long maxIdsPerChipPerSample, Long tableVocabSize,
      Long featureWidth, Long numScPerChip, String tableName, String miniBatchInCsr) {
    return GetMinibatchesInCsrWithPhysicalReplica.create(scope, programKey, rowIds, colIds, gains, splits, idCounts, sampleCount, numReplica, maxMinibatchesPerSc, maxIdsPerChipPerSample, tableVocabSize, featureWidth, numScPerChip, tableName, miniBatchInCsr);
  }

  /**
   * The GlobalIterId operation
   *
   * @return a new instance of GlobalIterId
   */
  public GlobalIterId globalIterId() {
    return GlobalIterId.create(scope);
  }

  /**
   * An op that demultiplexes a tensor to be sharded by XLA to a list of partitioned
   *  outputs outside the XLA computation. Supports ND sharding.
   *
   * @param <T> data type for {@code output} output
   * @param inputs A tensor which represents the full shape of partitioned tensors.
   * @param numSplits The value of the numSplits attribute
   * @param partitionDims A list of integers describing how each dimension is partitioned. Emptiness
   *  indicates the inputs are replicated.
   * @param <T> data type for {@code TPUPartitionedOutputV2} output and operands
   * @return a new instance of PartitionedOutput
   */
  public <T extends TType> PartitionedOutput<T> partitionedOutput(Operand<T> inputs, Long numSplits,
      List<Long> partitionDims) {
    return PartitionedOutput.create(scope, inputs, numSplits, partitionDims);
  }

  /**
   * An op that shuts down the TPU system.
   *
   * @return a new instance of ShutdownTPUSystem
   */
  public ShutdownTPUSystem shutdownTPUSystem() {
    return ShutdownTPUSystem.create(scope);
  }

  /**
   * The StoreMinibatchStatisticsInFdo operation
   *
   * @param programKey The programKey value
   * @param maxIds The maxIds value
   * @param maxUniques The maxUniques value
   * @param sampleCount The value of the sampleCount attribute
   * @param numReplica The value of the numReplica attribute
   * @param featureWidth The value of the featureWidth attribute
   * @param numScPerChip The value of the numScPerChip attribute
   * @param tableName The value of the tableName attribute
   * @param miniBatchSplits The value of the miniBatchSplits attribute
   * @return a new instance of StoreMinibatchStatisticsInFdo
   */
  public StoreMinibatchStatisticsInFdo storeMinibatchStatisticsInFdo(Operand<TString> programKey,
      Operand<TInt32> maxIds, Operand<TInt32> maxUniques, Long sampleCount, Long numReplica,
      Long featureWidth, Long numScPerChip, String tableName, String miniBatchSplits) {
    return StoreMinibatchStatisticsInFdo.create(scope, programKey, maxIds, maxUniques, sampleCount, numReplica, featureWidth, numScPerChip, tableName, miniBatchSplits);
  }

  /**
   * The TPUAnnotateTensorsWithDynamicShape operation
   *
   * @param tensors The tensors value
   * @return a new instance of TPUAnnotateTensorsWithDynamicShape
   */
  public TPUAnnotateTensorsWithDynamicShape tPUAnnotateTensorsWithDynamicShape(
      Iterable<Operand<?>> tensors) {
    return TPUAnnotateTensorsWithDynamicShape.create(scope, tensors);
  }

  /**
   * Op that copies host tensor to device with dynamic shape support.
   *  For internal use only.
   *
   * @param tensors The tensors value
   * @param unpaddedSizes The unpaddedSizes value
   * @return a new instance of TPUCopyWithDynamicShape
   */
  public TPUCopyWithDynamicShape tPUCopyWithDynamicShape(Iterable<Operand<?>> tensors,
      Iterable<Operand<TInt32>> unpaddedSizes) {
    return TPUCopyWithDynamicShape.create(scope, tensors, unpaddedSizes);
  }

  /**
   * Round-robin load balancing on TPU cores.
   *  A load balancing op that round-robins among TPU cores.
   *  <p>This op round-robins between the integers in [0, NumTPUCoresVisiblePerHost]. It
   *  is useful for interfacing with TensorFlow ops that take as input a TPU core on
   *  which to execute computations, such as {@code TPUPartitionedCall}.
   *  <p>device_ordinal: An integer in [0, NumTPUCoresVisiblePerHost].
   *
   * @return a new instance of TPURoundRobin
   */
  public TPURoundRobin tPURoundRobin() {
    return TPURoundRobin.create(scope);
  }

  /**
   * Converts XRT's uid handles to TensorFlow-friendly input format.
   *  Converts a uid handle for a compiled program into a vector of proto keys.
   *  <p>XRT compile ops return uids, and the TensorFlow execute op takes a proto
   *  key. This op enables a client to compile on TPU using XRT and execute using the
   *  standard TensorFlow execute op.
   *  <p>'uid' is the input handle.
   *  'proto_keys' is a vector of proto keys, one for each core program.
   *
   * @param uid The uid value
   * @return a new instance of TpuHandleToProtoKey
   */
  public TpuHandleToProtoKey tpuHandleToProtoKey(Operand<TInt64> uid) {
    return TpuHandleToProtoKey.create(scope, uid);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
