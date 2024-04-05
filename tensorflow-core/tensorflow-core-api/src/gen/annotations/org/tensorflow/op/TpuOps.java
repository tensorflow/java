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
import org.tensorflow.op.tpu.AllToAll;
import org.tensorflow.op.tpu.CollateTPUEmbeddingMemory;
import org.tensorflow.op.tpu.CompilationResult;
import org.tensorflow.op.tpu.Compile;
import org.tensorflow.op.tpu.CompileSucceededAssert;
import org.tensorflow.op.tpu.ComputeDedupDataSize;
import org.tensorflow.op.tpu.ComputeDedupDataTupleMask;
import org.tensorflow.op.tpu.ConfigureAndInitializeGlobalTPU;
import org.tensorflow.op.tpu.ConfigureDistributedTPU;
import org.tensorflow.op.tpu.ConfigureTPUEmbedding;
import org.tensorflow.op.tpu.ConfigureTPUEmbeddingHost;
import org.tensorflow.op.tpu.ConfigureTPUEmbeddingMemory;
import org.tensorflow.op.tpu.ConnectTPUEmbeddingHosts;
import org.tensorflow.op.tpu.ConvertToCooTensor;
import org.tensorflow.op.tpu.CrossReplicaSum;
import org.tensorflow.op.tpu.DTensorRestore;
import org.tensorflow.op.tpu.DynamicEnqueueTPUEmbeddingArbitraryTensorBatch;
import org.tensorflow.op.tpu.DynamicEnqueueTPUEmbeddingRaggedTensorBatch;
import org.tensorflow.op.tpu.EmbeddingActivations;
import org.tensorflow.op.tpu.EnqueueTPUEmbeddingArbitraryTensorBatch;
import org.tensorflow.op.tpu.EnqueueTPUEmbeddingBatch;
import org.tensorflow.op.tpu.EnqueueTPUEmbeddingIntegerBatch;
import org.tensorflow.op.tpu.EnqueueTPUEmbeddingRaggedTensorBatch;
import org.tensorflow.op.tpu.EnqueueTPUEmbeddingSparseBatch;
import org.tensorflow.op.tpu.EnqueueTPUEmbeddingSparseTensorBatch;
import org.tensorflow.op.tpu.Execute;
import org.tensorflow.op.tpu.ExecuteAndUpdateVariables;
import org.tensorflow.op.tpu.ExecuteTPUEmbeddingPartitioner;
import org.tensorflow.op.tpu.FinalizeTPUEmbedding;
import org.tensorflow.op.tpu.GetMinibatchSplitsWithPhysicalReplica;
import org.tensorflow.op.tpu.GetMinibatchesInCsrWithPhysicalReplica;
import org.tensorflow.op.tpu.GlobalIterId;
import org.tensorflow.op.tpu.InfeedDequeue;
import org.tensorflow.op.tpu.InfeedDequeueTuple;
import org.tensorflow.op.tpu.InfeedEnqueue;
import org.tensorflow.op.tpu.InfeedEnqueuePrelinearizedBuffer;
import org.tensorflow.op.tpu.InfeedEnqueueTuple;
import org.tensorflow.op.tpu.IsTPUEmbeddingInitialized;
import org.tensorflow.op.tpu.LoadAllTPUEmbeddingParameters;
import org.tensorflow.op.tpu.LoadTPUEmbeddingADAMParameters;
import org.tensorflow.op.tpu.LoadTPUEmbeddingAdadeltaParameters;
import org.tensorflow.op.tpu.LoadTPUEmbeddingAdagradMomentumParameters;
import org.tensorflow.op.tpu.LoadTPUEmbeddingAdagradParameters;
import org.tensorflow.op.tpu.LoadTPUEmbeddingCenteredRMSPropParameters;
import org.tensorflow.op.tpu.LoadTPUEmbeddingFTRLParameters;
import org.tensorflow.op.tpu.LoadTPUEmbeddingFrequencyEstimatorParameters;
import org.tensorflow.op.tpu.LoadTPUEmbeddingMDLAdagradLightParameters;
import org.tensorflow.op.tpu.LoadTPUEmbeddingMomentumParameters;
import org.tensorflow.op.tpu.LoadTPUEmbeddingProximalAdagradParameters;
import org.tensorflow.op.tpu.LoadTPUEmbeddingProximalYogiParameters;
import org.tensorflow.op.tpu.LoadTPUEmbeddingRMSPropParameters;
import org.tensorflow.op.tpu.LoadTPUEmbeddingStochasticGradientDescentParameters;
import org.tensorflow.op.tpu.MergeDedupData;
import org.tensorflow.op.tpu.OrdinalSelector;
import org.tensorflow.op.tpu.OutfeedDequeue;
import org.tensorflow.op.tpu.OutfeedDequeueTuple;
import org.tensorflow.op.tpu.OutfeedDequeueTupleV2;
import org.tensorflow.op.tpu.OutfeedDequeueV2;
import org.tensorflow.op.tpu.OutfeedEnqueue;
import org.tensorflow.op.tpu.OutfeedEnqueueTuple;
import org.tensorflow.op.tpu.PartitionedCall;
import org.tensorflow.op.tpu.PartitionedInput;
import org.tensorflow.op.tpu.PartitionedOutput;
import org.tensorflow.op.tpu.Prelinearize;
import org.tensorflow.op.tpu.PrelinearizeTuple;
import org.tensorflow.op.tpu.RecvTPUEmbeddingActivations;
import org.tensorflow.op.tpu.ReplicateMetadata;
import org.tensorflow.op.tpu.ReplicatedInput;
import org.tensorflow.op.tpu.ReplicatedOutput;
import org.tensorflow.op.tpu.RetrieveAllTPUEmbeddingParameters;
import org.tensorflow.op.tpu.RetrieveTPUEmbeddingADAMParameters;
import org.tensorflow.op.tpu.RetrieveTPUEmbeddingAdadeltaParameters;
import org.tensorflow.op.tpu.RetrieveTPUEmbeddingAdagradMomentumParameters;
import org.tensorflow.op.tpu.RetrieveTPUEmbeddingAdagradParameters;
import org.tensorflow.op.tpu.RetrieveTPUEmbeddingCenteredRMSPropParameters;
import org.tensorflow.op.tpu.RetrieveTPUEmbeddingFTRLParameters;
import org.tensorflow.op.tpu.RetrieveTPUEmbeddingFrequencyEstimatorParameters;
import org.tensorflow.op.tpu.RetrieveTPUEmbeddingMDLAdagradLightParameters;
import org.tensorflow.op.tpu.RetrieveTPUEmbeddingMomentumParameters;
import org.tensorflow.op.tpu.RetrieveTPUEmbeddingProximalAdagradParameters;
import org.tensorflow.op.tpu.RetrieveTPUEmbeddingProximalYogiParameters;
import org.tensorflow.op.tpu.RetrieveTPUEmbeddingRMSPropParameters;
import org.tensorflow.op.tpu.RetrieveTPUEmbeddingStochasticGradientDescentParameters;
import org.tensorflow.op.tpu.SendTPUEmbeddingGradients;
import org.tensorflow.op.tpu.ShutdownDistributedTPU;
import org.tensorflow.op.tpu.ShutdownTPUSystem;
import org.tensorflow.op.tpu.SplitDedupData;
import org.tensorflow.op.tpu.StoreMinibatchStatisticsInFdo;
import org.tensorflow.op.tpu.TPUAnnotateTensorsWithDynamicShape;
import org.tensorflow.op.tpu.TPUCompilationResult;
import org.tensorflow.op.tpu.TPUCopyWithDynamicShape;
import org.tensorflow.op.tpu.TPUEmbeddingActivations;
import org.tensorflow.op.tpu.TPUReplicateMetadata;
import org.tensorflow.op.tpu.TPUReplicatedInput;
import org.tensorflow.op.tpu.TPUReplicatedOutput;
import org.tensorflow.op.tpu.TPUReshardVariables;
import org.tensorflow.op.tpu.TPURoundRobin;
import org.tensorflow.op.tpu.TpuHandleToProtoKey;
import org.tensorflow.op.tpu.WorkerHeartbeat;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;
import org.tensorflow.types.TInt64;
import org.tensorflow.types.TString;
import org.tensorflow.types.family.TNumber;
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
   * An Op to exchange data across TPU replicas.
   *  On each replica, the input is split into {@code split_count} blocks along
   *  {@code split_dimension} and send to the other replicas given group_assignment. After
   *  receiving {@code split_count} - 1 blocks from other replicas, we concatenate the
   *  blocks along {@code concat_dimension} as the output.
   *  <p>For example, suppose there are 2 TPU replicas:
   *  replica 0 receives input: {@code [[A, B]]}
   *  replica 1 receives input: {@code [[C, D]]}
   *  <p>group_assignment={@code [[0, 1]]}
   *  concat_dimension=0
   *  split_dimension=1
   *  split_count=2
   *  <p>replica 0's output: {@code [[A], [C]]}
   *  replica 1's output: {@code [[B], [D]]}
   *
   * @param <T> data type for {@code output} output
   * @param input The local input to the sum.
   * @param groupAssignment An int32 tensor with shape
   *  [num_groups, num_replicas_per_group]. {@code group_assignment[i]} represents the
   *  replica ids in the ith subgroup.
   * @param concatDimension The dimension number to concatenate.
   * @param splitDimension The dimension number to split.
   * @param splitCount The number of splits, this number must equal to the sub-group
   *  size(group_assignment.get_shape()[1])
   * @param <T> data type for {@code AllToAll} output and operands
   * @return a new instance of AllToAll
   */
  public <T extends TType> AllToAll<T> allToAll(Operand<T> input, Operand<TInt32> groupAssignment,
      Long concatDimension, Long splitDimension, Long splitCount) {
    return AllToAll.create(scope, input, groupAssignment, concatDimension, splitDimension, splitCount);
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
   * Returns the result of a TPU compilation.
   *  This operation returns the result of a TPU compilation as a serialized
   *  CompilationResultProto, which holds a status and an error message if an error
   *  occurred during compilation.
   *
   * @return a new instance of CompilationResult
   */
  public CompilationResult compilationResult() {
    return CompilationResult.create(scope);
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
   * An op computes tuple mask of deduplication data from embedding core.
   *  The deduplication data receiving from embedding core is a Tensor with
   *  type=DT_VARIANT. The tensor itself is an XLA nested tuple, whose elements are
   *  rank 1 tensors. This op is to represents types and length of these elements.
   *
   * @param config Serialized TPUEmbeddingConfiguration proto.
   * @return a new instance of ComputeDedupDataTupleMask
   */
  public ComputeDedupDataTupleMask computeDedupDataTupleMask(String config) {
    return ComputeDedupDataTupleMask.create(scope, config);
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
   * Sets up the centralized structures for a distributed TPU system.
   *
   * @param options carries optional attribute values
   * @return a new instance of ConfigureDistributedTPU
   */
  public ConfigureDistributedTPU configureDistributedTPU(
      ConfigureDistributedTPU.Options... options) {
    return ConfigureDistributedTPU.create(scope, options);
  }

  /**
   * Sets up TPUEmbedding in a distributed TPU system.
   *
   * @param config Serialized tensorflow.tpu.TPUEmbeddingConfiguration that
   *  describes the embedding lookups of the program.
   * @return a new instance of ConfigureTPUEmbedding
   */
  public ConfigureTPUEmbedding configureTPUEmbedding(String config) {
    return ConfigureTPUEmbedding.create(scope, config);
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
   * An Op to sum inputs across replicated TPU instances.
   *  Each instance supplies its own input.
   *  <p>For example, suppose there are 8 TPU instances: {@code [A, B, C, D, E, F, G, H]}.
   *  Passing group_assignment={@code [[0,2,4,6],[1,3,5,7]]} sets {@code A, C, E, G} as group 0,
   *  and {@code B, D, F, H} as group 1. Thus we get the outputs:
   *  {@code [A+C+E+G, B+D+F+H, A+C+E+G, B+D+F+H, A+C+E+G, B+D+F+H, A+C+E+G, B+D+F+H]}.
   *
   * @param <T> data type for {@code output} output
   * @param input The local input to the sum.
   * @param groupAssignment An int32 tensor with shape
   *  [num_groups, num_replicas_per_group]. {@code group_assignment[i]} represents the
   *  replica ids in the ith subgroup.
   * @param <T> data type for {@code CrossReplicaSum} output and operands
   * @return a new instance of CrossReplicaSum
   */
  public <T extends TNumber> CrossReplicaSum<T> crossReplicaSum(Operand<T> input,
      Operand<TInt32> groupAssignment) {
    return CrossReplicaSum.create(scope, input, groupAssignment);
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
   * Eases the porting of code that uses tf.nn.embedding_lookup_sparse().
   *  embedding_indices[i] and aggregation_weights[i] correspond
   *  to the ith feature.
   *  <p>The tensors at corresponding positions in the three input lists (sample_indices,
   *  embedding_indices and aggregation_weights) must have the same shape, i.e. rank 1
   *  with dim_size() equal to the total number of lookups into the table described by
   *  the corresponding feature.
   *
   * @param sampleIndicesOrRowSplits A list of rank 2 Tensors specifying the training example to which the
   *  corresponding embedding_indices and aggregation_weights values belong.
   *  If the size of its first dimension is 0, we assume each embedding_indices
   *  belongs to a different sample. Both int32 and int64 are allowed and will
   *  be converted to int32 internally.
   *  <p>Or a list of rank 1 Tensors specifying the row splits for splitting
   *  embedding_indices and aggregation_weights into rows. It corresponds to
   *  ids.row_splits in embedding_lookup(), when ids is a RaggedTensor. When
   *  enqueuing N-D ragged tensor, only the last dimension is allowed to be ragged.
   *  the row splits is 1-D dense tensor. When empty, we assume a dense tensor is
   *  passed to the op Both int32 and int64 are allowed and will be converted to
   *  int32 internally.
   * @param embeddingIndices A list of rank 1 Tensors, indices into the embedding
   *  tables. Both int32 and int64 are allowed and will be converted to
   *  int32 internally.
   * @param aggregationWeights A list of rank 1 Tensors containing per training
   *  example aggregation weights. Both float32 and float64 are allowed and will
   *  be converted to float32 internally.
   * @param modeOverride A string input that overrides the mode specified in the
   *  TPUEmbeddingConfiguration. Supported values are {'unspecified', 'inference',
   *  'training', 'backward_pass_only'}. When set to 'unspecified', the mode set
   *  in TPUEmbeddingConfiguration is used, otherwise mode_override is used.
   * @param deviceOrdinal The TPU device to use. Should be &gt;= 0 and less than the number
   *  of TPU cores in the task on which the node is placed.
   * @param options carries optional attribute values
   * @return a new instance of DynamicEnqueueTPUEmbeddingArbitraryTensorBatch
   */
  public DynamicEnqueueTPUEmbeddingArbitraryTensorBatch dynamicEnqueueTPUEmbeddingArbitraryTensorBatch(
      Iterable<Operand<? extends TNumber>> sampleIndicesOrRowSplits,
      Iterable<Operand<? extends TNumber>> embeddingIndices,
      Iterable<Operand<? extends TNumber>> aggregationWeights, Operand<TString> modeOverride,
      Operand<TInt32> deviceOrdinal,
      DynamicEnqueueTPUEmbeddingArbitraryTensorBatch.Options... options) {
    return DynamicEnqueueTPUEmbeddingArbitraryTensorBatch.create(scope, sampleIndicesOrRowSplits, embeddingIndices, aggregationWeights, modeOverride, deviceOrdinal, options);
  }

  /**
   * The DynamicEnqueueTPUEmbeddingRaggedTensorBatch operation
   *
   * @param sampleSplits The sampleSplits value
   * @param embeddingIndices The embeddingIndices value
   * @param aggregationWeights The aggregationWeights value
   * @param modeOverride The modeOverride value
   * @param deviceOrdinal The deviceOrdinal value
   * @param tableIds The value of the tableIds attribute
   * @param options carries optional attribute values
   * @return a new instance of DynamicEnqueueTPUEmbeddingRaggedTensorBatch
   */
  public DynamicEnqueueTPUEmbeddingRaggedTensorBatch dynamicEnqueueTPUEmbeddingRaggedTensorBatch(
      Iterable<Operand<? extends TNumber>> sampleSplits,
      Iterable<Operand<? extends TNumber>> embeddingIndices,
      Iterable<Operand<? extends TNumber>> aggregationWeights, Operand<TString> modeOverride,
      Operand<TInt32> deviceOrdinal, List<Long> tableIds,
      DynamicEnqueueTPUEmbeddingRaggedTensorBatch.Options... options) {
    return DynamicEnqueueTPUEmbeddingRaggedTensorBatch.create(scope, sampleSplits, embeddingIndices, aggregationWeights, modeOverride, deviceOrdinal, tableIds, options);
  }

  /**
   * An op enabling differentiation of TPU Embeddings.
   *  This op simply returns its first input, which is assumed to have been sliced
   *  from the Tensors returned by TPUEmbeddingDequeueActivations. The presence of
   *  this op, and its first argument being a trainable Variable, enables automatic
   *  differentiation of graphs containing embeddings via the TPU Embedding Python
   *  libraries.
   *
   * @param embeddingVariable A trainable variable, enabling optimizers to find this op.
   * @param slicedActivations The embedding activations Tensor to return.
   * @param tableId The id of the table in the embedding layer configuration from which
   *  these activations were computed.
   * @param lookupId Identifier of the set of embedding indices which produced these
   *  activations.
   * @return a new instance of EmbeddingActivations
   */
  public EmbeddingActivations embeddingActivations(Operand<TFloat32> embeddingVariable,
      Operand<TFloat32> slicedActivations, Long tableId, Long lookupId) {
    return EmbeddingActivations.create(scope, embeddingVariable, slicedActivations, tableId, lookupId);
  }

  /**
   * Eases the porting of code that uses tf.nn.embedding_lookup_sparse().
   *  embedding_indices[i] and aggregation_weights[i] correspond
   *  to the ith feature.
   *  <p>The tensors at corresponding positions in the three input lists (sample_indices,
   *  embedding_indices and aggregation_weights) must have the same shape, i.e. rank 1
   *  with dim_size() equal to the total number of lookups into the table described by
   *  the corresponding feature.
   *
   * @param sampleIndicesOrRowSplits A list of rank 2 Tensors specifying the training example to which the
   *  corresponding embedding_indices and aggregation_weights values belong.
   *  If the size of its first dimension is 0, we assume each embedding_indices
   *  belongs to a different sample. Both int32 and int64 are allowed and will
   *  be converted to int32 internally.
   *  <p>Or a list of rank 1 Tensors specifying the row splits for splitting
   *  embedding_indices and aggregation_weights into rows. It corresponds to
   *  ids.row_splits in embedding_lookup(), when ids is a RaggedTensor. When
   *  enqueuing N-D ragged tensor, only the last dimension is allowed to be ragged.
   *  the row splits is 1-D dense tensor. When empty, we assume a dense tensor is
   *  passed to the op Both int32 and int64 are allowed and will be converted to
   *  int32 internally.
   * @param embeddingIndices A list of rank 1 Tensors, indices into the embedding
   *  tables. Both int32 and int64 are allowed and will be converted to
   *  int32 internally.
   * @param aggregationWeights A list of rank 1 Tensors containing per training
   *  example aggregation weights. Both float32 and float64 are allowed and will
   *  be converted to float32 internally.
   * @param modeOverride A string input that overrides the mode specified in the
   *  TPUEmbeddingConfiguration. Supported values are {'unspecified', 'inference',
   *  'training', 'backward_pass_only'}. When set to 'unspecified', the mode set
   *  in TPUEmbeddingConfiguration is used, otherwise mode_override is used.
   * @param options carries optional attribute values
   * @return a new instance of EnqueueTPUEmbeddingArbitraryTensorBatch
   */
  public EnqueueTPUEmbeddingArbitraryTensorBatch enqueueTPUEmbeddingArbitraryTensorBatch(
      Iterable<Operand<? extends TNumber>> sampleIndicesOrRowSplits,
      Iterable<Operand<? extends TNumber>> embeddingIndices,
      Iterable<Operand<? extends TNumber>> aggregationWeights, Operand<TString> modeOverride,
      EnqueueTPUEmbeddingArbitraryTensorBatch.Options... options) {
    return EnqueueTPUEmbeddingArbitraryTensorBatch.create(scope, sampleIndicesOrRowSplits, embeddingIndices, aggregationWeights, modeOverride, options);
  }

  /**
   * An op that enqueues a list of input batch tensors to TPUEmbedding.
   *  An op that enqueues a list of input batch tensors to TPUEmbedding.
   *
   * @param batch A list of 1D tensors, one for each embedding table, containing the
   *  batch inputs encoded as dist_belief.SparseFeatures protos. If the weight
   *  field in the SparseFeatures proto is not populated for an ID, a weight of
   *  1.0 is assumed.
   * @param modeOverride A string input that overrides the mode specified in the
   *  TPUEmbeddingConfiguration. Supported values are {'unspecified', 'inference',
   *  'training', 'backward_pass_only'}. When set to 'unspecified', the mode set
   *  in TPUEmbeddingConfiguration is used, otherwise mode_override is used.
   * @param options carries optional attribute values
   * @return a new instance of EnqueueTPUEmbeddingBatch
   */
  public EnqueueTPUEmbeddingBatch enqueueTPUEmbeddingBatch(Iterable<Operand<TString>> batch,
      Operand<TString> modeOverride, EnqueueTPUEmbeddingBatch.Options... options) {
    return EnqueueTPUEmbeddingBatch.create(scope, batch, modeOverride, options);
  }

  /**
   * An op that enqueues a list of input batch tensors to TPUEmbedding.
   *
   * @param batch A list of 1D tensors, one for each embedding table, containing the
   *  indices into the tables.
   * @param modeOverride A string input that overrides the mode specified in the
   *  TPUEmbeddingConfiguration. Supported values are {'unspecified', 'inference',
   *  'training', 'backward_pass_only'}. When set to 'unspecified', the mode set
   *  in TPUEmbeddingConfiguration is used, otherwise mode_override is used.
   * @param options carries optional attribute values
   * @return a new instance of EnqueueTPUEmbeddingIntegerBatch
   */
  public EnqueueTPUEmbeddingIntegerBatch enqueueTPUEmbeddingIntegerBatch(
      Iterable<Operand<TInt32>> batch, Operand<TString> modeOverride,
      EnqueueTPUEmbeddingIntegerBatch.Options... options) {
    return EnqueueTPUEmbeddingIntegerBatch.create(scope, batch, modeOverride, options);
  }

  /**
   * Eases the porting of code that uses tf.nn.embedding_lookup().
   *  sample_splits[i], embedding_indices[i] and aggregation_weights[i] correspond
   *  to the ith feature. table_ids[i] indicates which embedding table to look up ith
   *  feature.
   *  <p>The tensors at corresponding positions in two of the input lists,
   *  embedding_indices and aggregation_weights, must have the same shape, i.e. rank 1
   *  with dim_size() equal to the total number of lookups into the table described by
   *  the corresponding feature.
   *
   * @param sampleSplits A list of rank 1 Tensors specifying the break points for splitting
   *  embedding_indices and aggregation_weights into rows.
   *  It corresponds to ids.row_splits in embedding_lookup(), when ids is a
   *  RaggedTensor.
   * @param embeddingIndices A list of rank 1 Tensors, indices into the embedding tables.
   *  It corresponds to ids.values in embedding_lookup(), when ids is a RaggedTensor.
   * @param aggregationWeights A list of rank 1 Tensors containing per training example
   *  aggregation weights. It corresponds to the values field of a RaggedTensor
   *  with the same row_splits as ids in embedding_lookup(), when ids is a
   *  RaggedTensor.
   * @param modeOverride A string input that overrides the mode specified in the
   *  TPUEmbeddingConfiguration. Supported values are {'unspecified', 'inference',
   *  'training', 'backward_pass_only'}. When set to 'unspecified', the mode set
   *  in TPUEmbeddingConfiguration is used, otherwise mode_override is used.
   * @param tableIds A list of integers specifying the identifier of the embedding table
   *  (offset of TableDescriptor in the TPUEmbeddingConfiguration) to lookup the
   *  corresponding input. The ith input is looked up using table_ids[i]. The size
   *  of the table_ids list must be equal to that of sample_indices,
   *  embedding_indices and aggregation_weights.
   * @param options carries optional attribute values
   * @return a new instance of EnqueueTPUEmbeddingRaggedTensorBatch
   */
  public EnqueueTPUEmbeddingRaggedTensorBatch enqueueTPUEmbeddingRaggedTensorBatch(
      Iterable<Operand<? extends TNumber>> sampleSplits,
      Iterable<Operand<? extends TNumber>> embeddingIndices,
      Iterable<Operand<? extends TNumber>> aggregationWeights, Operand<TString> modeOverride,
      List<Long> tableIds, EnqueueTPUEmbeddingRaggedTensorBatch.Options... options) {
    return EnqueueTPUEmbeddingRaggedTensorBatch.create(scope, sampleSplits, embeddingIndices, aggregationWeights, modeOverride, tableIds, options);
  }

  /**
   * An op that enqueues TPUEmbedding input indices from a SparseTensor.
   *  This Op eases the porting of code that uses embedding_lookup_sparse(),
   *  although some Python preprocessing of the SparseTensor arguments to
   *  embedding_lookup_sparse() is required to produce the arguments to this Op,
   *  since only a single EnqueueTPUEmbeddingSparseBatch Op is allowed per training
   *  step.
   *  <p>The tensors at corresponding positions in the three input lists
   *  must have the same shape, i.e. rank 1 with dim_size() equal to the total
   *  number of lookups into the table described by the corresponding table_id.
   *
   * @param sampleIndices A list of rank 1 Tensors specifying the training example and
   *  feature to which the corresponding embedding_indices and aggregation_weights
   *  values belong. sample_indices[i] must equal b * nf + f, where nf is the
   *  number of features from the corresponding table, f is in [0, nf), and
   *  b is in [0, batch size).
   * @param embeddingIndices A list of rank 1 Tensors, indices into the embedding tables.
   * @param aggregationWeights A list of rank 1 Tensors containing per sample -- i.e. per
   *  (training example, feature) -- aggregation weights.
   * @param modeOverride A string input that overrides the mode specified in the
   *  TPUEmbeddingConfiguration. Supported values are {'unspecified', 'inference',
   *  'training', 'backward_pass_only'}. When set to 'unspecified', the mode set
   *  in TPUEmbeddingConfiguration is used, otherwise mode_override is used.
   * @param options carries optional attribute values
   * @return a new instance of EnqueueTPUEmbeddingSparseBatch
   */
  public EnqueueTPUEmbeddingSparseBatch enqueueTPUEmbeddingSparseBatch(
      Iterable<Operand<? extends TNumber>> sampleIndices,
      Iterable<Operand<? extends TNumber>> embeddingIndices,
      Iterable<Operand<? extends TNumber>> aggregationWeights, Operand<TString> modeOverride,
      EnqueueTPUEmbeddingSparseBatch.Options... options) {
    return EnqueueTPUEmbeddingSparseBatch.create(scope, sampleIndices, embeddingIndices, aggregationWeights, modeOverride, options);
  }

  /**
   * Eases the porting of code that uses tf.nn.embedding_lookup_sparse().
   *  sample_indices[i], embedding_indices[i] and aggregation_weights[i] correspond
   *  to the ith feature. table_ids[i] indicates which embedding table to look up ith
   *  feature.
   *  <p>The tensors at corresponding positions in the three input lists (sample_indices,
   *  embedding_indices and aggregation_weights) must have the same shape, i.e. rank 1
   *  with dim_size() equal to the total number of lookups into the table described by
   *  the corresponding feature.
   *
   * @param sampleIndices A list of rank 1 Tensors specifying the training example to
   *  which the corresponding embedding_indices and aggregation_weights values
   *  belong. It corresponds to sp_ids.indices[:,0] in  embedding_lookup_sparse().
   * @param embeddingIndices A list of rank 1 Tensors, indices into the embedding tables.
   *  It corresponds to sp_ids.values in embedding_lookup_sparse().
   * @param aggregationWeights A list of rank 1 Tensors containing per training example
   *  aggregation weights. It corresponds to sp_weights.values in
   *  embedding_lookup_sparse().
   * @param modeOverride A string input that overrides the mode specified in the
   *  TPUEmbeddingConfiguration. Supported values are {'unspecified', 'inference',
   *  'training', 'backward_pass_only'}. When set to 'unspecified', the mode set
   *  in TPUEmbeddingConfiguration is used, otherwise mode_override is used.
   * @param tableIds A list of integers specifying the identifier of the embedding table
   *  (offset of TableDescriptor in the TPUEmbeddingConfiguration) to lookup the
   *  corresponding input. The ith input is looked up using table_ids[i]. The size
   *  of the table_ids list must be equal to that of sample_indices,
   *  embedding_indices and aggregation_weights.
   * @param options carries optional attribute values
   * @return a new instance of EnqueueTPUEmbeddingSparseTensorBatch
   */
  public EnqueueTPUEmbeddingSparseTensorBatch enqueueTPUEmbeddingSparseTensorBatch(
      Iterable<Operand<? extends TNumber>> sampleIndices,
      Iterable<Operand<? extends TNumber>> embeddingIndices,
      Iterable<Operand<? extends TNumber>> aggregationWeights, Operand<TString> modeOverride,
      List<Long> tableIds, EnqueueTPUEmbeddingSparseTensorBatch.Options... options) {
    return EnqueueTPUEmbeddingSparseTensorBatch.create(scope, sampleIndices, embeddingIndices, aggregationWeights, modeOverride, tableIds, options);
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
   * A placeholder op for a value that will be fed into the computation.
   *
   * @param <T> data type for {@code output} output
   * @param dtype The type of elements in the tensor.
   * @param shape The shape of the tensor.
   * @param <T> data type for {@code InfeedDequeue} output and operands
   * @return a new instance of InfeedDequeue
   */
  public <T extends TType> InfeedDequeue<T> infeedDequeue(Class<T> dtype, Shape shape) {
    return InfeedDequeue.create(scope, dtype, shape);
  }

  /**
   * Fetches multiple values from infeed as an XLA tuple.
   *
   * @param dtypes The element types of each element in {@code outputs}.
   * @param shapes The shapes of each tensor in {@code outputs}.
   * @return a new instance of InfeedDequeueTuple
   */
  public InfeedDequeueTuple infeedDequeueTuple(List<Class<? extends TType>> dtypes,
      List<Shape> shapes) {
    return InfeedDequeueTuple.create(scope, dtypes, shapes);
  }

  /**
   * An op which feeds a single Tensor value into the computation.
   *
   * @param input A tensor that will be provided using the infeed mechanism.
   * @param options carries optional attribute values
   * @return a new instance of InfeedEnqueue
   */
  public InfeedEnqueue infeedEnqueue(Operand<? extends TType> input,
      InfeedEnqueue.Options... options) {
    return InfeedEnqueue.create(scope, input, options);
  }

  /**
   * An op which enqueues prelinearized buffer into TPU infeed.
   *
   * @param input A variant tensor representing linearized output.
   * @param options carries optional attribute values
   * @return a new instance of InfeedEnqueuePrelinearizedBuffer
   */
  public InfeedEnqueuePrelinearizedBuffer infeedEnqueuePrelinearizedBuffer(
      Operand<? extends TType> input, InfeedEnqueuePrelinearizedBuffer.Options... options) {
    return InfeedEnqueuePrelinearizedBuffer.create(scope, input, options);
  }

  /**
   * Feeds multiple Tensor values into the computation as an XLA tuple.
   *
   * @param inputs A list of tensors that will be provided using the infeed mechanism.
   * @param shapes The shapes of each tensor in {@code inputs}.
   * @param options carries optional attribute values
   * @return a new instance of InfeedEnqueueTuple
   */
  public InfeedEnqueueTuple infeedEnqueueTuple(Iterable<Operand<?>> inputs, List<Shape> shapes,
      InfeedEnqueueTuple.Options... options) {
    return InfeedEnqueueTuple.create(scope, inputs, shapes, options);
  }

  /**
   * Whether TPU Embedding is initialized in a distributed TPU system.
   *
   * @param options carries optional attribute values
   * @return a new instance of IsTPUEmbeddingInitialized
   */
  public IsTPUEmbeddingInitialized isTPUEmbeddingInitialized(
      IsTPUEmbeddingInitialized.Options... options) {
    return IsTPUEmbeddingInitialized.create(scope, options);
  }

  /**
   * An op that loads optimization parameters into embedding memory.
   *  An op that loads optimization parameters into embedding memory. Must be
   *  preceded by a ConfigureTPUEmbeddingHost op that sets up the correct embedding
   *  table configuration. For example, this op is used to install parameters that are
   *  loaded from a checkpoint before a training loop is executed.  For Adagrad,
   *  auxiliary1 should be the accumulators. For SGD, all of the auxiliary* values
   *  should be empty. For FTRL, auxiliary1 should be the accumulators and auxiliary2
   *  should be the linear terms. For ADAM, auxiliary1 should be the momenta and
   *  auxiliary2 should be the velocities.
   *
   * @param parameters A list of tensors, one for each embedding table,
   *  containing the initial embedding table parameters to use in embedding
   *  lookups.
   * @param auxiliary1 A list of tensors, one for each embedding table, containing the
   *  initial values of the first auxiliary optimization parameter to use in embedding
   *  training loop updates. The shape of each entry is ignored (and thus can be
   *  empty) for those tables whose optimization algorithms do not have at least one
   *  auxiliary parameter.
   * @param auxiliary2 A list of tensors, one for each embedding table, containing the
   *  initial values of the second auxiliary optimization parameter to use in
   *  embedding training loop updates. The shape of each entry is ignored (and thus
   *  can be empty) for those tables whose optimization algorithms do not have at
   *  least two auxiliary
   * @param auxiliary3 A list of tensors, one for each embedding table, containing the
   *  initial values of the third auxiliary optimization parameter to use in embedding
   *  training loop updates. The shape of each entry is ignored (and thus can be
   *  empty) for those tables whose optimization algorithms do not have three
   *  auxiliary parameters.
   * @param auxiliary4 A list of tensors, one for each embedding table, containing the
   *  initial values of the second auxiliary optimization parameter to use in
   *  embedding training loop updates. The shape of each entry is ignored (and thus
   *  can be empty) for those tables whose optimization algorithms do not have at
   *  least four auxiliary
   * @param auxiliary5 A list of tensors, one for each embedding table, containing the
   *  initial values of the third auxiliary optimization parameter to use in embedding
   *  training loop updates. The shape of each entry is ignored (and thus can be
   *  empty) for those tables whose optimization algorithms do not have five
   *  auxiliary parameters.
   * @param auxiliary6 A list of tensors, one for each embedding table, containing the
   *  initial values of the second auxiliary optimization parameter to use in
   *  embedding training loop updates. The shape of each entry is ignored (and thus
   *  can be empty) for those tables whose optimization algorithms do not have at
   *  least six auxiliary
   * @param auxiliary7 A list of tensors, one for each embedding table, containing the
   *  initial values of the third auxiliary optimization parameter to use in embedding
   *  training loop updates. The shape of each entry is ignored (and thus can be
   *  empty) for those tables whose optimization algorithms do not have sevan
   *  auxiliary parameters.
   * @param config An TPUEmbeddingConfiguration proto describing the
   *  table parameters being loaded, serialized to a string.
   * @param numShards Number of shards into which the embedding tables are divided.
   * @param shardId Identifier of shard for this operation.
   * @return a new instance of LoadAllTPUEmbeddingParameters
   */
  public LoadAllTPUEmbeddingParameters loadAllTPUEmbeddingParameters(
      Iterable<Operand<TFloat32>> parameters, Iterable<Operand<TFloat32>> auxiliary1,
      Iterable<Operand<TFloat32>> auxiliary2, Iterable<Operand<TFloat32>> auxiliary3,
      Iterable<Operand<TFloat32>> auxiliary4, Iterable<Operand<TFloat32>> auxiliary5,
      Iterable<Operand<TFloat32>> auxiliary6, Iterable<Operand<TFloat32>> auxiliary7, String config,
      Long numShards, Long shardId) {
    return LoadAllTPUEmbeddingParameters.create(scope, parameters, auxiliary1, auxiliary2, auxiliary3, auxiliary4, auxiliary5, auxiliary6, auxiliary7, config, numShards, shardId);
  }

  /**
   * Load ADAM embedding parameters.
   *  An op that loads optimization parameters into HBM for embedding. Must be
   *  preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
   *  embedding table configuration. For example, this op is used to install
   *  parameters that are loaded from a checkpoint before a training loop is
   *  executed.
   *
   * @param parameters Value of parameters used in the ADAM optimization algorithm.
   * @param momenta Value of momenta used in the ADAM optimization algorithm.
   * @param velocities Value of velocities used in the ADAM optimization algorithm.
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingADAMParameters
   */
  public LoadTPUEmbeddingADAMParameters loadTPUEmbeddingADAMParameters(Operand<TFloat32> parameters,
      Operand<TFloat32> momenta, Operand<TFloat32> velocities, Long numShards, Long shardId,
      LoadTPUEmbeddingADAMParameters.Options... options) {
    return LoadTPUEmbeddingADAMParameters.create(scope, parameters, momenta, velocities, numShards, shardId, options);
  }

  /**
   * Load Adadelta embedding parameters.
   *  An op that loads optimization parameters into HBM for embedding. Must be
   *  preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
   *  embedding table configuration. For example, this op is used to install
   *  parameters that are loaded from a checkpoint before a training loop is
   *  executed.
   *
   * @param parameters Value of parameters used in the Adadelta optimization algorithm.
   * @param accumulators Value of accumulators used in the Adadelta optimization algorithm.
   * @param updates Value of updates used in the Adadelta optimization algorithm.
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingAdadeltaParameters
   */
  public LoadTPUEmbeddingAdadeltaParameters loadTPUEmbeddingAdadeltaParameters(
      Operand<TFloat32> parameters, Operand<TFloat32> accumulators, Operand<TFloat32> updates,
      Long numShards, Long shardId, LoadTPUEmbeddingAdadeltaParameters.Options... options) {
    return LoadTPUEmbeddingAdadeltaParameters.create(scope, parameters, accumulators, updates, numShards, shardId, options);
  }

  /**
   * Load Adagrad Momentum embedding parameters.
   *  An op that loads optimization parameters into HBM for embedding. Must be
   *  preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
   *  embedding table configuration. For example, this op is used to install
   *  parameters that are loaded from a checkpoint before a training loop is
   *  executed.
   *
   * @param parameters Value of parameters used in the Adagrad Momentum optimization algorithm.
   * @param accumulators Value of accumulators used in the Adagrad Momentum optimization algorithm.
   * @param momenta Value of momenta used in the Adagrad Momentum optimization algorithm.
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingAdagradMomentumParameters
   */
  public LoadTPUEmbeddingAdagradMomentumParameters loadTPUEmbeddingAdagradMomentumParameters(
      Operand<TFloat32> parameters, Operand<TFloat32> accumulators, Operand<TFloat32> momenta,
      Long numShards, Long shardId, LoadTPUEmbeddingAdagradMomentumParameters.Options... options) {
    return LoadTPUEmbeddingAdagradMomentumParameters.create(scope, parameters, accumulators, momenta, numShards, shardId, options);
  }

  /**
   * Load Adagrad embedding parameters.
   *  An op that loads optimization parameters into HBM for embedding. Must be
   *  preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
   *  embedding table configuration. For example, this op is used to install
   *  parameters that are loaded from a checkpoint before a training loop is
   *  executed.
   *
   * @param parameters Value of parameters used in the Adagrad optimization algorithm.
   * @param accumulators Value of accumulators used in the Adagrad optimization algorithm.
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingAdagradParameters
   */
  public LoadTPUEmbeddingAdagradParameters loadTPUEmbeddingAdagradParameters(
      Operand<TFloat32> parameters, Operand<TFloat32> accumulators, Long numShards, Long shardId,
      LoadTPUEmbeddingAdagradParameters.Options... options) {
    return LoadTPUEmbeddingAdagradParameters.create(scope, parameters, accumulators, numShards, shardId, options);
  }

  /**
   * Load centered RMSProp embedding parameters.
   *  An op that loads optimization parameters into HBM for embedding. Must be
   *  preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
   *  embedding table configuration. For example, this op is used to install
   *  parameters that are loaded from a checkpoint before a training loop is
   *  executed.
   *
   * @param parameters Value of parameters used in the centered RMSProp optimization algorithm.
   * @param ms Value of ms used in the centered RMSProp optimization algorithm.
   * @param mom Value of mom used in the centered RMSProp optimization algorithm.
   * @param mg Value of mg used in the centered RMSProp optimization algorithm.
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingCenteredRMSPropParameters
   */
  public LoadTPUEmbeddingCenteredRMSPropParameters loadTPUEmbeddingCenteredRMSPropParameters(
      Operand<TFloat32> parameters, Operand<TFloat32> ms, Operand<TFloat32> mom,
      Operand<TFloat32> mg, Long numShards, Long shardId,
      LoadTPUEmbeddingCenteredRMSPropParameters.Options... options) {
    return LoadTPUEmbeddingCenteredRMSPropParameters.create(scope, parameters, ms, mom, mg, numShards, shardId, options);
  }

  /**
   * Load FTRL embedding parameters.
   *  An op that loads optimization parameters into HBM for embedding. Must be
   *  preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
   *  embedding table configuration. For example, this op is used to install
   *  parameters that are loaded from a checkpoint before a training loop is
   *  executed.
   *
   * @param parameters Value of parameters used in the FTRL optimization algorithm.
   * @param accumulators Value of accumulators used in the FTRL optimization algorithm.
   * @param linears Value of linears used in the FTRL optimization algorithm.
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingFTRLParameters
   */
  public LoadTPUEmbeddingFTRLParameters loadTPUEmbeddingFTRLParameters(Operand<TFloat32> parameters,
      Operand<TFloat32> accumulators, Operand<TFloat32> linears, Long numShards, Long shardId,
      LoadTPUEmbeddingFTRLParameters.Options... options) {
    return LoadTPUEmbeddingFTRLParameters.create(scope, parameters, accumulators, linears, numShards, shardId, options);
  }

  /**
   * Load frequency estimator embedding parameters.
   *  An op that loads optimization parameters into HBM for embedding. Must be
   *  preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
   *  embedding table configuration. For example, this op is used to install
   *  parameters that are loaded from a checkpoint before a training loop is
   *  executed.
   *
   * @param parameters Value of parameters used in the frequency estimator optimization algorithm.
   * @param lastHitStep Value of last_hit_step used in the frequency estimator optimization algorithm.
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingFrequencyEstimatorParameters
   */
  public LoadTPUEmbeddingFrequencyEstimatorParameters loadTPUEmbeddingFrequencyEstimatorParameters(
      Operand<TFloat32> parameters, Operand<TFloat32> lastHitStep, Long numShards, Long shardId,
      LoadTPUEmbeddingFrequencyEstimatorParameters.Options... options) {
    return LoadTPUEmbeddingFrequencyEstimatorParameters.create(scope, parameters, lastHitStep, numShards, shardId, options);
  }

  /**
   * Load MDL Adagrad Light embedding parameters.
   *  An op that loads optimization parameters into HBM for embedding. Must be
   *  preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
   *  embedding table configuration. For example, this op is used to install
   *  parameters that are loaded from a checkpoint before a training loop is
   *  executed.
   *
   * @param parameters Value of parameters used in the MDL Adagrad Light optimization algorithm.
   * @param accumulators Value of accumulators used in the MDL Adagrad Light optimization algorithm.
   * @param weights Value of weights used in the MDL Adagrad Light optimization algorithm.
   * @param benefits Value of benefits used in the MDL Adagrad Light optimization algorithm.
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingMDLAdagradLightParameters
   */
  public LoadTPUEmbeddingMDLAdagradLightParameters loadTPUEmbeddingMDLAdagradLightParameters(
      Operand<TFloat32> parameters, Operand<TFloat32> accumulators, Operand<TFloat32> weights,
      Operand<TFloat32> benefits, Long numShards, Long shardId,
      LoadTPUEmbeddingMDLAdagradLightParameters.Options... options) {
    return LoadTPUEmbeddingMDLAdagradLightParameters.create(scope, parameters, accumulators, weights, benefits, numShards, shardId, options);
  }

  /**
   * Load Momentum embedding parameters.
   *  An op that loads optimization parameters into HBM for embedding. Must be
   *  preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
   *  embedding table configuration. For example, this op is used to install
   *  parameters that are loaded from a checkpoint before a training loop is
   *  executed.
   *
   * @param parameters Value of parameters used in the Momentum optimization algorithm.
   * @param momenta Value of momenta used in the Momentum optimization algorithm.
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingMomentumParameters
   */
  public LoadTPUEmbeddingMomentumParameters loadTPUEmbeddingMomentumParameters(
      Operand<TFloat32> parameters, Operand<TFloat32> momenta, Long numShards, Long shardId,
      LoadTPUEmbeddingMomentumParameters.Options... options) {
    return LoadTPUEmbeddingMomentumParameters.create(scope, parameters, momenta, numShards, shardId, options);
  }

  /**
   * Load proximal Adagrad embedding parameters.
   *  An op that loads optimization parameters into HBM for embedding. Must be
   *  preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
   *  embedding table configuration. For example, this op is used to install
   *  parameters that are loaded from a checkpoint before a training loop is
   *  executed.
   *
   * @param parameters Value of parameters used in the proximal Adagrad optimization algorithm.
   * @param accumulators Value of accumulators used in the proximal Adagrad optimization algorithm.
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingProximalAdagradParameters
   */
  public LoadTPUEmbeddingProximalAdagradParameters loadTPUEmbeddingProximalAdagradParameters(
      Operand<TFloat32> parameters, Operand<TFloat32> accumulators, Long numShards, Long shardId,
      LoadTPUEmbeddingProximalAdagradParameters.Options... options) {
    return LoadTPUEmbeddingProximalAdagradParameters.create(scope, parameters, accumulators, numShards, shardId, options);
  }

  /**
   * The LoadTPUEmbeddingProximalYogiParameters operation
   *
   * @param parameters The parameters value
   * @param v The v value
   * @param m The m value
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingProximalYogiParameters
   */
  public LoadTPUEmbeddingProximalYogiParameters loadTPUEmbeddingProximalYogiParameters(
      Operand<TFloat32> parameters, Operand<TFloat32> v, Operand<TFloat32> m, Long numShards,
      Long shardId, LoadTPUEmbeddingProximalYogiParameters.Options... options) {
    return LoadTPUEmbeddingProximalYogiParameters.create(scope, parameters, v, m, numShards, shardId, options);
  }

  /**
   * Load RMSProp embedding parameters.
   *  An op that loads optimization parameters into HBM for embedding. Must be
   *  preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
   *  embedding table configuration. For example, this op is used to install
   *  parameters that are loaded from a checkpoint before a training loop is
   *  executed.
   *
   * @param parameters Value of parameters used in the RMSProp optimization algorithm.
   * @param ms Value of ms used in the RMSProp optimization algorithm.
   * @param mom Value of mom used in the RMSProp optimization algorithm.
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingRMSPropParameters
   */
  public LoadTPUEmbeddingRMSPropParameters loadTPUEmbeddingRMSPropParameters(
      Operand<TFloat32> parameters, Operand<TFloat32> ms, Operand<TFloat32> mom, Long numShards,
      Long shardId, LoadTPUEmbeddingRMSPropParameters.Options... options) {
    return LoadTPUEmbeddingRMSPropParameters.create(scope, parameters, ms, mom, numShards, shardId, options);
  }

  /**
   * Load SGD embedding parameters.
   *  An op that loads optimization parameters into HBM for embedding. Must be
   *  preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
   *  embedding table configuration. For example, this op is used to install
   *  parameters that are loaded from a checkpoint before a training loop is
   *  executed.
   *
   * @param parameters Value of parameters used in the stochastic gradient descent optimization algorithm.
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of LoadTPUEmbeddingStochasticGradientDescentParameters
   */
  public LoadTPUEmbeddingStochasticGradientDescentParameters loadTPUEmbeddingStochasticGradientDescentParameters(
      Operand<TFloat32> parameters, Long numShards, Long shardId,
      LoadTPUEmbeddingStochasticGradientDescentParameters.Options... options) {
    return LoadTPUEmbeddingStochasticGradientDescentParameters.create(scope, parameters, numShards, shardId, options);
  }

  /**
   * An op merges elements of integer and float tensors into deduplication data as
   *  XLA tuple.
   *  This op merges outputs of SplitDedupDataOp, which gives two 1-D tensors, integer
   *  and floating point. With respect to tuple_mask, this op merges values of these
   *  two tensors into an XLA tuple, which should be as same as input to
   *  SplitDedupDataOp.
   *
   * @param integerTensor A 1-D integer tensor, includes integer elements of deduplication data tuple.
   * @param floatTensor A 1-D float tensor, includes float elements of deduplication data tuple.
   * @param tupleMask A serialized TensorProto string of output tuple mask. This mask is a 2-D tensor,
   *  with first column as tuple element type, and second column as span of this type.
   *  For example, an output tuple of (1, 2, 0.1, 3), its mask is [[0, 2], [1, 1], [0,
   *  1]]. We expect only two types of elements: integer(0) and float(1).
   * @param options carries optional attribute values
   * @return a new instance of MergeDedupData
   */
  public MergeDedupData mergeDedupData(Operand<? extends TNumber> integerTensor,
      Operand<? extends TNumber> floatTensor, String tupleMask, MergeDedupData.Options... options) {
    return MergeDedupData.create(scope, integerTensor, floatTensor, tupleMask, options);
  }

  /**
   * A TPU core selector Op.
   *  This Op produces a set of TPU cores (for warm-up) or a single TPU core
   *  (for regular inference) to execute the TPU program on. The output is
   *  consumed by TPUPartitionedCall.
   *
   * @return a new instance of OrdinalSelector
   */
  public OrdinalSelector ordinalSelector() {
    return OrdinalSelector.create(scope);
  }

  /**
   * Retrieves a single tensor from the computation outfeed.
   *  This operation will block indefinitely until data is available.
   *
   * @param <T> data type for {@code output} output
   * @param dtype The type of elements in the tensor.
   * @param shape The shape of the tensor.
   * @param options carries optional attribute values
   * @param <T> data type for {@code OutfeedDequeue} output and operands
   * @return a new instance of OutfeedDequeue
   */
  public <T extends TType> OutfeedDequeue<T> outfeedDequeue(Class<T> dtype, Shape shape,
      OutfeedDequeue.Options... options) {
    return OutfeedDequeue.create(scope, dtype, shape, options);
  }

  /**
   * Retrieve multiple values from the computation outfeed.
   *  This operation will block indefinitely until data is available. Output {@code i}
   *  corresponds to XLA tuple element {@code i}.
   *
   * @param dtypes The element types of each element in {@code outputs}.
   * @param shapes The shapes of each tensor in {@code outputs}.
   * @param options carries optional attribute values
   * @return a new instance of OutfeedDequeueTuple
   */
  public OutfeedDequeueTuple outfeedDequeueTuple(List<Class<? extends TType>> dtypes,
      List<Shape> shapes, OutfeedDequeueTuple.Options... options) {
    return OutfeedDequeueTuple.create(scope, dtypes, shapes, options);
  }

  /**
   * Retrieve multiple values from the computation outfeed. Device ordinal is a
   *  tensor allowing dynamic outfeed.
   *  This operation will block indefinitely until data is available. Output {@code i}
   *  corresponds to XLA tuple element {@code i}.
   *
   * @param deviceOrdinal An int scalar tensor, representing the TPU device to use. This should be -1 when
   *  the Op is running on a TPU device, and &gt;= 0 when the Op is running on the CPU
   *  device.
   * @param dtypes The element types of each element in {@code outputs}.
   * @param shapes The shapes of each tensor in {@code outputs}.
   * @return a new instance of OutfeedDequeueTupleV2
   */
  public OutfeedDequeueTupleV2 outfeedDequeueTupleV2(Operand<TInt32> deviceOrdinal,
      List<Class<? extends TType>> dtypes, List<Shape> shapes) {
    return OutfeedDequeueTupleV2.create(scope, deviceOrdinal, dtypes, shapes);
  }

  /**
   * Retrieves a single tensor from the computation outfeed. Device ordinal is a
   *  tensor allowing dynamic outfeed.
   *  This operation will block indefinitely until data is available.
   *
   * @param <T> data type for {@code output} output
   * @param deviceOrdinal An int scalar tensor, representing the TPU device to use. This should be -1 when
   *  the Op is running on a TPU device, and &gt;= 0 when the Op is running on the CPU
   *  device.
   * @param dtype The type of elements in the tensor.
   * @param shape The shape of the tensor.
   * @param <T> data type for {@code OutfeedDequeueV2} output and operands
   * @return a new instance of OutfeedDequeueV2
   */
  public <T extends TType> OutfeedDequeueV2<T> outfeedDequeueV2(Operand<TInt32> deviceOrdinal,
      Class<T> dtype, Shape shape) {
    return OutfeedDequeueV2.create(scope, deviceOrdinal, dtype, shape);
  }

  /**
   * Enqueue a Tensor on the computation outfeed.
   *
   * @param input A tensor that will be inserted into the outfeed queue.
   * @return a new instance of OutfeedEnqueue
   */
  public OutfeedEnqueue outfeedEnqueue(Operand<? extends TType> input) {
    return OutfeedEnqueue.create(scope, input);
  }

  /**
   * Enqueue multiple Tensor values on the computation outfeed.
   *
   * @param inputs A list of tensors that will be inserted into the outfeed queue as an
   *  XLA tuple.
   * @return a new instance of OutfeedEnqueueTuple
   */
  public OutfeedEnqueueTuple outfeedEnqueueTuple(Iterable<Operand<?>> inputs) {
    return OutfeedEnqueueTuple.create(scope, inputs);
  }

  /**
   * Calls a function placed on a specified TPU device.
   *
   * @param args The arguments to the function.
   * @param deviceOrdinal The TPU device ordinal to run the function on.
   * @param Tout The types of the outputs of the function.
   * @param f The function to call.
   * @param options carries optional attribute values
   * @return a new instance of PartitionedCall
   */
  public PartitionedCall partitionedCall(Iterable<Operand<?>> args, Operand<TInt32> deviceOrdinal,
      List<Class<? extends TType>> Tout, ConcreteFunction f, PartitionedCall.Options... options) {
    return PartitionedCall.create(scope, args, deviceOrdinal, Tout, f, options);
  }

  /**
   * An op that groups a list of partitioned inputs together. Supports ND sharding.
   *
   * @param <T> data type for {@code output} output
   * @param inputs A list of partitioned inputs which must have the same shape.
   * @param partitionDims A list of integers describing how each dimension is partitioned. Emptiness
   *  indicates the inputs are replicated.
   * @param options carries optional attribute values
   * @param <T> data type for {@code TPUPartitionedInputV2} output and operands
   * @return a new instance of PartitionedInput
   */
  public <T extends TType> PartitionedInput<T> partitionedInput(Iterable<Operand<T>> inputs,
      List<Long> partitionDims, PartitionedInput.Options... options) {
    return PartitionedInput.create(scope, inputs, partitionDims, options);
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
   * An op which linearizes one Tensor value to an opaque variant tensor.
   *
   * @param input A tensor that will be linearized.
   * @param options carries optional attribute values
   * @return a new instance of Prelinearize
   */
  public Prelinearize prelinearize(Operand<? extends TType> input,
      Prelinearize.Options... options) {
    return Prelinearize.create(scope, input, options);
  }

  /**
   * An op which linearizes multiple Tensor values to an opaque variant tensor.
   *
   * @param inputs A list of tensors that will be provided using the infeed mechanism.
   * @param shapes The shapes of each tensor in {@code inputs}.
   * @param options carries optional attribute values
   * @return a new instance of PrelinearizeTuple
   */
  public PrelinearizeTuple prelinearizeTuple(Iterable<Operand<?>> inputs, List<Shape> shapes,
      PrelinearizeTuple.Options... options) {
    return PrelinearizeTuple.create(scope, inputs, shapes, options);
  }

  /**
   * An op that receives embedding activations on the TPU.
   *  The TPU system performs the embedding lookups and aggregations specified by
   *  the arguments to TPUEmbeddingEnqueue(Integer/Sparse/SparseTensor)Batch. The
   *  results of these aggregations are visible to the Tensorflow Graph as the
   *  outputs of a RecvTPUEmbeddingActivations op. This op returns a list containing
   *  one Tensor of activations per table specified in the model. There can be at
   *  most one RecvTPUEmbeddingActivations op in the TPU graph.
   *
   * @param numOutputs The number of output activation tensors, equal to the number of
   *  embedding tables in the model.
   * @param config Serialized TPUEmbeddingConfiguration proto.
   * @return a new instance of RecvTPUEmbeddingActivations
   */
  public RecvTPUEmbeddingActivations recvTPUEmbeddingActivations(Long numOutputs, String config) {
    return RecvTPUEmbeddingActivations.create(scope, numOutputs, config);
  }

  /**
   * Metadata indicating how the TPU computation should be replicated.
   *  This operation holds the metadata common to operations of a {@code tpu.replicate()} computation subgraph.
   *
   * @param numReplicas Number of replicas of the computation
   * @param options carries optional attribute values
   * @return a new instance of ReplicateMetadata
   */
  public ReplicateMetadata replicateMetadata(Long numReplicas,
      ReplicateMetadata.Options... options) {
    return ReplicateMetadata.create(scope, numReplicas, options);
  }

  /**
   * Connects N inputs to an N-way replicated TPU computation.
   *  This operation holds a replicated input to a {@code tpu.replicate()} computation subgraph.
   *  Each replicated input has the same shape and type alongside the output.
   *  <p>For example:
   *  <pre>
   *  %a = &quot;tf.opA&quot;()
   *  %b = &quot;tf.opB&quot;()
   *  %replicated_input = &quot;tf.TPUReplicatedInput&quot;(%a, %b)
   *  %computation = &quot;tf.Computation&quot;(%replicated_input)
   *  </pre>
   *  <p>The above computation has a replicated input of two replicas.
   *
   * @param <T> data type for {@code output} output
   * @param inputs The inputs value
   * @param options carries optional attribute values
   * @param <T> data type for {@code TPUReplicatedInput} output and operands
   * @return a new instance of ReplicatedInput
   */
  public <T extends TType> ReplicatedInput<T> replicatedInput(Iterable<Operand<T>> inputs,
      ReplicatedInput.Options... options) {
    return ReplicatedInput.create(scope, inputs, options);
  }

  /**
   * Connects N outputs from an N-way replicated TPU computation.
   *  This operation holds a replicated output from a {@code tpu.replicate()} computation subgraph.
   *  Each replicated output has the same shape and type alongside the input.
   *  <p>For example:
   *  <pre>
   *  %computation = &quot;tf.Computation&quot;()
   *  %replicated_output:2 = &quot;tf.TPUReplicatedOutput&quot;(%computation)
   *  </pre>
   *  <p>The above computation has a replicated output of two replicas.
   *
   * @param <T> data type for {@code outputs} output
   * @param input The input value
   * @param numReplicas The value of the numReplicas attribute
   * @param <T> data type for {@code TPUReplicatedOutput} output and operands
   * @return a new instance of ReplicatedOutput
   */
  public <T extends TType> ReplicatedOutput<T> replicatedOutput(Operand<T> input,
      Long numReplicas) {
    return ReplicatedOutput.create(scope, input, numReplicas);
  }

  /**
   * An op that retrieves optimization parameters from embedding to host memory.
   *  An op that retrieves optimization parameters from embedding to host memory.
   *  Must be preceded by a ConfigureTPUEmbeddingHost op that sets up the correct
   *  embedding table configuration. For example, this op is used to retrieve updated
   *  parameters before saving a checkpoint.  For Adagrad, auxiliary1 will contain the
   *  accumulators after running this op. For SGD, all of the auxiliary* values will
   *  be empty (0x0 tensors for that table). For FTRL, auxiliary1 will contain the
   *  accumulators and auxiliary2 will contain the linear terms. For ADAM, auxiliary1
   *  will contain the momenta and auxiliary2 will contain the velocities.
   *
   * @param NumTables The number of embedding tables.
   * @param config An TPUEmbeddingConfiguration proto describing the
   *  table parameters being loaded, serialized to a string.
   * @param numShards Number of shards into which the embedding tables are divided.
   * @param shardId Identifier of shard for this operation.
   * @return a new instance of RetrieveAllTPUEmbeddingParameters
   */
  public RetrieveAllTPUEmbeddingParameters retrieveAllTPUEmbeddingParameters(Long NumTables,
      String config, Long numShards, Long shardId) {
    return RetrieveAllTPUEmbeddingParameters.create(scope, NumTables, config, numShards, shardId);
  }

  /**
   * Retrieve ADAM embedding parameters.
   *  An op that retrieves optimization parameters from embedding to host
   *  memory. Must be preceded by a ConfigureTPUEmbeddingHost op that sets up
   *  the correct embedding table configuration. For example, this op is
   *  used to retrieve updated parameters before saving a checkpoint.
   *
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingADAMParameters
   */
  public RetrieveTPUEmbeddingADAMParameters retrieveTPUEmbeddingADAMParameters(Long numShards,
      Long shardId, RetrieveTPUEmbeddingADAMParameters.Options... options) {
    return RetrieveTPUEmbeddingADAMParameters.create(scope, numShards, shardId, options);
  }

  /**
   * Retrieve Adadelta embedding parameters.
   *  An op that retrieves optimization parameters from embedding to host
   *  memory. Must be preceded by a ConfigureTPUEmbeddingHost op that sets up
   *  the correct embedding table configuration. For example, this op is
   *  used to retrieve updated parameters before saving a checkpoint.
   *
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingAdadeltaParameters
   */
  public RetrieveTPUEmbeddingAdadeltaParameters retrieveTPUEmbeddingAdadeltaParameters(
      Long numShards, Long shardId, RetrieveTPUEmbeddingAdadeltaParameters.Options... options) {
    return RetrieveTPUEmbeddingAdadeltaParameters.create(scope, numShards, shardId, options);
  }

  /**
   * Retrieve Adagrad Momentum embedding parameters.
   *  An op that retrieves optimization parameters from embedding to host
   *  memory. Must be preceded by a ConfigureTPUEmbeddingHost op that sets up
   *  the correct embedding table configuration. For example, this op is
   *  used to retrieve updated parameters before saving a checkpoint.
   *
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingAdagradMomentumParameters
   */
  public RetrieveTPUEmbeddingAdagradMomentumParameters retrieveTPUEmbeddingAdagradMomentumParameters(
      Long numShards, Long shardId,
      RetrieveTPUEmbeddingAdagradMomentumParameters.Options... options) {
    return RetrieveTPUEmbeddingAdagradMomentumParameters.create(scope, numShards, shardId, options);
  }

  /**
   * Retrieve Adagrad embedding parameters.
   *  An op that retrieves optimization parameters from embedding to host
   *  memory. Must be preceded by a ConfigureTPUEmbeddingHost op that sets up
   *  the correct embedding table configuration. For example, this op is
   *  used to retrieve updated parameters before saving a checkpoint.
   *
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingAdagradParameters
   */
  public RetrieveTPUEmbeddingAdagradParameters retrieveTPUEmbeddingAdagradParameters(Long numShards,
      Long shardId, RetrieveTPUEmbeddingAdagradParameters.Options... options) {
    return RetrieveTPUEmbeddingAdagradParameters.create(scope, numShards, shardId, options);
  }

  /**
   * Retrieve centered RMSProp embedding parameters.
   *  An op that retrieves optimization parameters from embedding to host
   *  memory. Must be preceded by a ConfigureTPUEmbeddingHost op that sets up
   *  the correct embedding table configuration. For example, this op is
   *  used to retrieve updated parameters before saving a checkpoint.
   *
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingCenteredRMSPropParameters
   */
  public RetrieveTPUEmbeddingCenteredRMSPropParameters retrieveTPUEmbeddingCenteredRMSPropParameters(
      Long numShards, Long shardId,
      RetrieveTPUEmbeddingCenteredRMSPropParameters.Options... options) {
    return RetrieveTPUEmbeddingCenteredRMSPropParameters.create(scope, numShards, shardId, options);
  }

  /**
   * Retrieve FTRL embedding parameters.
   *  An op that retrieves optimization parameters from embedding to host
   *  memory. Must be preceded by a ConfigureTPUEmbeddingHost op that sets up
   *  the correct embedding table configuration. For example, this op is
   *  used to retrieve updated parameters before saving a checkpoint.
   *
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingFTRLParameters
   */
  public RetrieveTPUEmbeddingFTRLParameters retrieveTPUEmbeddingFTRLParameters(Long numShards,
      Long shardId, RetrieveTPUEmbeddingFTRLParameters.Options... options) {
    return RetrieveTPUEmbeddingFTRLParameters.create(scope, numShards, shardId, options);
  }

  /**
   * Retrieve frequency estimator embedding parameters.
   *  An op that retrieves optimization parameters from embedding to host
   *  memory. Must be preceded by a ConfigureTPUEmbeddingHost op that sets up
   *  the correct embedding table configuration. For example, this op is
   *  used to retrieve updated parameters before saving a checkpoint.
   *
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingFrequencyEstimatorParameters
   */
  public RetrieveTPUEmbeddingFrequencyEstimatorParameters retrieveTPUEmbeddingFrequencyEstimatorParameters(
      Long numShards, Long shardId,
      RetrieveTPUEmbeddingFrequencyEstimatorParameters.Options... options) {
    return RetrieveTPUEmbeddingFrequencyEstimatorParameters.create(scope, numShards, shardId, options);
  }

  /**
   * Retrieve MDL Adagrad Light embedding parameters.
   *  An op that retrieves optimization parameters from embedding to host
   *  memory. Must be preceded by a ConfigureTPUEmbeddingHost op that sets up
   *  the correct embedding table configuration. For example, this op is
   *  used to retrieve updated parameters before saving a checkpoint.
   *
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingMDLAdagradLightParameters
   */
  public RetrieveTPUEmbeddingMDLAdagradLightParameters retrieveTPUEmbeddingMDLAdagradLightParameters(
      Long numShards, Long shardId,
      RetrieveTPUEmbeddingMDLAdagradLightParameters.Options... options) {
    return RetrieveTPUEmbeddingMDLAdagradLightParameters.create(scope, numShards, shardId, options);
  }

  /**
   * Retrieve Momentum embedding parameters.
   *  An op that retrieves optimization parameters from embedding to host
   *  memory. Must be preceded by a ConfigureTPUEmbeddingHost op that sets up
   *  the correct embedding table configuration. For example, this op is
   *  used to retrieve updated parameters before saving a checkpoint.
   *
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingMomentumParameters
   */
  public RetrieveTPUEmbeddingMomentumParameters retrieveTPUEmbeddingMomentumParameters(
      Long numShards, Long shardId, RetrieveTPUEmbeddingMomentumParameters.Options... options) {
    return RetrieveTPUEmbeddingMomentumParameters.create(scope, numShards, shardId, options);
  }

  /**
   * Retrieve proximal Adagrad embedding parameters.
   *  An op that retrieves optimization parameters from embedding to host
   *  memory. Must be preceded by a ConfigureTPUEmbeddingHost op that sets up
   *  the correct embedding table configuration. For example, this op is
   *  used to retrieve updated parameters before saving a checkpoint.
   *
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingProximalAdagradParameters
   */
  public RetrieveTPUEmbeddingProximalAdagradParameters retrieveTPUEmbeddingProximalAdagradParameters(
      Long numShards, Long shardId,
      RetrieveTPUEmbeddingProximalAdagradParameters.Options... options) {
    return RetrieveTPUEmbeddingProximalAdagradParameters.create(scope, numShards, shardId, options);
  }

  /**
   * The RetrieveTPUEmbeddingProximalYogiParameters operation
   *
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingProximalYogiParameters
   */
  public RetrieveTPUEmbeddingProximalYogiParameters retrieveTPUEmbeddingProximalYogiParameters(
      Long numShards, Long shardId, RetrieveTPUEmbeddingProximalYogiParameters.Options... options) {
    return RetrieveTPUEmbeddingProximalYogiParameters.create(scope, numShards, shardId, options);
  }

  /**
   * Retrieve RMSProp embedding parameters.
   *  An op that retrieves optimization parameters from embedding to host
   *  memory. Must be preceded by a ConfigureTPUEmbeddingHost op that sets up
   *  the correct embedding table configuration. For example, this op is
   *  used to retrieve updated parameters before saving a checkpoint.
   *
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingRMSPropParameters
   */
  public RetrieveTPUEmbeddingRMSPropParameters retrieveTPUEmbeddingRMSPropParameters(Long numShards,
      Long shardId, RetrieveTPUEmbeddingRMSPropParameters.Options... options) {
    return RetrieveTPUEmbeddingRMSPropParameters.create(scope, numShards, shardId, options);
  }

  /**
   * Retrieve SGD embedding parameters.
   *  An op that retrieves optimization parameters from embedding to host
   *  memory. Must be preceded by a ConfigureTPUEmbeddingHost op that sets up
   *  the correct embedding table configuration. For example, this op is
   *  used to retrieve updated parameters before saving a checkpoint.
   *
   * @param numShards The value of the numShards attribute
   * @param shardId The value of the shardId attribute
   * @param options carries optional attribute values
   * @return a new instance of RetrieveTPUEmbeddingStochasticGradientDescentParameters
   */
  public RetrieveTPUEmbeddingStochasticGradientDescentParameters retrieveTPUEmbeddingStochasticGradientDescentParameters(
      Long numShards, Long shardId,
      RetrieveTPUEmbeddingStochasticGradientDescentParameters.Options... options) {
    return RetrieveTPUEmbeddingStochasticGradientDescentParameters.create(scope, numShards, shardId, options);
  }

  /**
   * Performs gradient updates of embedding tables.
   *
   * @param inputs A TensorList of gradients with which to update embedding tables.
   *  This argument has the same length and shapes as the return value of
   *  RecvTPUEmbeddingActivations, but contains gradients of the model's loss
   *  with respect to the embedding activations. The embedding tables are updated
   *  from these gradients via the optimizer specified in the TPU embedding
   *  configuration given to tpu.initialize_system.
   * @param learningRates A TensorList of float32 scalars, one for each dynamic learning
   *  rate tag: see the comments in
   *  //third_party/tensorflow/core/protobuf/tpu/optimization_parameters.proto.
   *  Multiple tables can share the same dynamic learning rate tag as specified
   *  in the configuration. If the learning rates for all tables are constant,
   *  this list should be empty.
   * @param config Serialized TPUEmbeddingConfiguration proto.
   * @param options carries optional attribute values
   * @return a new instance of SendTPUEmbeddingGradients
   */
  public SendTPUEmbeddingGradients sendTPUEmbeddingGradients(Iterable<Operand<TFloat32>> inputs,
      Iterable<Operand<TFloat32>> learningRates, String config,
      SendTPUEmbeddingGradients.Options... options) {
    return SendTPUEmbeddingGradients.create(scope, inputs, learningRates, config, options);
  }

  /**
   * Shuts down a running distributed TPU system.
   *  The op returns an error if no system is running.
   *
   * @return a new instance of ShutdownDistributedTPU
   */
  public ShutdownDistributedTPU shutdownDistributedTPU() {
    return ShutdownDistributedTPU.create(scope);
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
   * An op splits input deduplication data XLA tuple into integer and floating point
   *  tensors.
   *  Deduplication data is an XLA tuple, which consists of integer and floating point
   *  values. This op is to split these values into two groups for two types, and
   *  construct each group as one tensor to return.
   *
   * @param <T> data type for {@code integer_tensor} output
   * @param <U> data type for {@code float_tensor} output
   * @param input An XLA tuple including integer and float elements as deduplication data tuple.
   * @param integerType integer_tensor type. Allowed types: int32, int64, uint32, uint64.
   * @param floatType float_tensor type. Allowed types: half, bfloat16, float.
   * @param tupleMask A serialized TensorProto string of output tuple mask. This mask is a 2-D tensor,
   *  with first column as tuple element type, and second column as span of this type.
   *  For example, an output tuple of (1, 2, 0.1, 3), its mask is [[0, 2], [1, 1], [0,
   *  1]]. We expect only two types of elements: integer(0) and float(1).
   * @param options carries optional attribute values
   * @param <T> data type for {@code SplitDedupData} output and operands
   * @param <U> data type for {@code SplitDedupData} output and operands
   * @return a new instance of SplitDedupData
   */
  public <T extends TNumber, U extends TNumber> SplitDedupData<T, U> splitDedupData(
      Operand<? extends TType> input, Class<T> integerType, Class<U> floatType, String tupleMask,
      SplitDedupData.Options... options) {
    return SplitDedupData.create(scope, input, integerType, floatType, tupleMask, options);
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
   * Returns the result of a TPU compilation.
   *  This operation returns the result of a TPU compilation as a serialized
   *  CompilationResultProto, which holds a status and an error message if an error
   *  occurred during compilation.
   *
   * @deprecated use {@link org.tensorflow.op.tpu.CompilationResult} instead
   * @return a new instance of TPUCompilationResult
   */
  @Deprecated
  public TPUCompilationResult tPUCompilationResult() {
    return TPUCompilationResult.create(scope);
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
   * An op enabling differentiation of TPU Embeddings.
   *  This op simply returns its first input, which is assumed to have been sliced
   *  from the Tensors returned by TPUEmbeddingDequeueActivations. The presence of
   *  this op, and its first argument being a trainable Variable, enables automatic
   *  differentiation of graphs containing embeddings via the TPU Embedding Python
   *  libraries.
   *
   * @deprecated use {@link org.tensorflow.op.tpu.EmbeddingActivations} instead
   * @param embeddingVariable A trainable variable, enabling optimizers to find this op.
   * @param slicedActivations The embedding activations Tensor to return.
   * @param tableId The id of the table in the embedding layer configuration from which
   *  these activations were computed.
   * @param lookupId Identifier of the set of embedding indices which produced these
   *  activations.
   * @return a new instance of TPUEmbeddingActivations
   */
  @Deprecated
  public TPUEmbeddingActivations tPUEmbeddingActivations(Operand<TFloat32> embeddingVariable,
      Operand<TFloat32> slicedActivations, Long tableId, Long lookupId) {
    return TPUEmbeddingActivations.create(scope, embeddingVariable, slicedActivations, tableId, lookupId);
  }

  /**
   * Metadata indicating how the TPU computation should be replicated.
   *  This operation holds the metadata common to operations of a {@code tpu.replicate()} computation subgraph.
   *
   * @deprecated use {@link org.tensorflow.op.tpu.ReplicateMetadata} instead
   * @param numReplicas Number of replicas of the computation
   * @param options carries optional attribute values
   * @return a new instance of TPUReplicateMetadata
   */
  @Deprecated
  public TPUReplicateMetadata tPUReplicateMetadata(Long numReplicas,
      TPUReplicateMetadata.Options... options) {
    return TPUReplicateMetadata.create(scope, numReplicas, options);
  }

  /**
   * Connects N inputs to an N-way replicated TPU computation.
   *  This operation holds a replicated input to a {@code tpu.replicate()} computation subgraph.
   *  Each replicated input has the same shape and type alongside the output.
   *  <p>For example:
   *  <pre>
   *  %a = &quot;tf.opA&quot;()
   *  %b = &quot;tf.opB&quot;()
   *  %replicated_input = &quot;tf.TPUReplicatedInput&quot;(%a, %b)
   *  %computation = &quot;tf.Computation&quot;(%replicated_input)
   *  </pre>
   *  <p>The above computation has a replicated input of two replicas.
   *
   * @param <T> data type for {@code output} output
   * @deprecated use {@link org.tensorflow.op.tpu.ReplicatedInput} instead
   * @param inputs The inputs value
   * @param options carries optional attribute values
   * @param <T> data type for {@code TPUReplicatedInput} output and operands
   * @return a new instance of TPUReplicatedInput
   */
  @Deprecated
  public <T extends TType> TPUReplicatedInput<T> tPUReplicatedInput(Iterable<Operand<T>> inputs,
      TPUReplicatedInput.Options... options) {
    return TPUReplicatedInput.create(scope, inputs, options);
  }

  /**
   * Connects N outputs from an N-way replicated TPU computation.
   *  This operation holds a replicated output from a {@code tpu.replicate()} computation subgraph.
   *  Each replicated output has the same shape and type alongside the input.
   *  <p>For example:
   *  <pre>
   *  %computation = &quot;tf.Computation&quot;()
   *  %replicated_output:2 = &quot;tf.TPUReplicatedOutput&quot;(%computation)
   *  </pre>
   *  <p>The above computation has a replicated output of two replicas.
   *
   * @param <T> data type for {@code outputs} output
   * @deprecated use {@link org.tensorflow.op.tpu.ReplicatedOutput} instead
   * @param input The input value
   * @param numReplicas The value of the numReplicas attribute
   * @param <T> data type for {@code TPUReplicatedOutput} output and operands
   * @return a new instance of TPUReplicatedOutput
   */
  @Deprecated
  public <T extends TType> TPUReplicatedOutput<T> tPUReplicatedOutput(Operand<T> input,
      Long numReplicas) {
    return TPUReplicatedOutput.create(scope, input, numReplicas);
  }

  /**
   * Op that reshards on-device TPU variables to specified state.
   *  Op that reshards on-device TPU variables to specified state. Internal use only.
   *  <p>The sharding state is represented as the key of the compilation that generated
   *  the sharding/unsharding programs along with the main program. new_format_key
   *  specifies the desired state, and format_state_var is the current state of the
   *  variables.
   *
   * @param vars The vars value
   * @param newFormatKey The newFormatKey value
   * @param formatStateVar The formatStateVar value
   * @return a new instance of TPUReshardVariables
   */
  public TPUReshardVariables tPUReshardVariables(Iterable<Operand<? extends TType>> vars,
      Operand<TString> newFormatKey, Operand<? extends TType> formatStateVar) {
    return TPUReshardVariables.create(scope, vars, newFormatKey, formatStateVar);
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
   * Worker heartbeat op.
   *  Heartbeats may be sent periodically to indicate the coordinator is still active,
   *  to retrieve the current worker status and to expedite shutdown when necessary.
   *
   * @param request A string tensor containing a serialized WorkerHeartbeatRequest
   * @return a new instance of WorkerHeartbeat
   */
  public WorkerHeartbeat workerHeartbeat(Operand<TString> request) {
    return WorkerHeartbeat.create(scope, request);
  }

  /**
   * Get the parent {@link Ops} object.
   */
  public final Ops ops() {
    return ops;
  }
}
