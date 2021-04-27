// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/framework/dataset_options.proto

package org.tensorflow.proto.data;

public interface OptimizationOptionsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.data.OptimizationOptions)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bool apply_default_optimizations = 1;</code>
   */
  boolean getApplyDefaultOptimizations();

  /**
   * <code>bool autotune = 2;</code>
   */
  boolean getAutotune();

  /**
   * <code>bool autotune_buffers = 3;</code>
   */
  boolean getAutotuneBuffers();

  /**
   * <code>int32 autotune_cpu_budget = 4;</code>
   */
  int getAutotuneCpuBudget();

  /**
   * <code>int64 autotune_ram_budget = 5;</code>
   */
  long getAutotuneRamBudget();

  /**
   * <code>bool filter_fusion = 6;</code>
   */
  boolean getFilterFusion();

  /**
   * <code>bool map_and_batch_fusion = 9;</code>
   */
  boolean getMapAndBatchFusion();

  /**
   * <code>bool map_and_filter_fusion = 10;</code>
   */
  boolean getMapAndFilterFusion();

  /**
   * <code>bool map_fusion = 11;</code>
   */
  boolean getMapFusion();

  /**
   * <code>bool map_parallelization = 12;</code>
   */
  boolean getMapParallelization();

  /**
   * <code>bool noop_elimination = 14;</code>
   */
  boolean getNoopElimination();

  /**
   * <code>bool parallel_batch = 15;</code>
   */
  boolean getParallelBatch();

  /**
   * <code>bool shuffle_and_repeat_fusion = 17;</code>
   */
  boolean getShuffleAndRepeatFusion();

  public org.tensorflow.proto.data.OptimizationOptions.OptionalApplyDefaultOptimizationsCase getOptionalApplyDefaultOptimizationsCase();

  public org.tensorflow.proto.data.OptimizationOptions.OptionalAutotuneCase getOptionalAutotuneCase();

  public org.tensorflow.proto.data.OptimizationOptions.OptionalAutotuneBuffersCase getOptionalAutotuneBuffersCase();

  public org.tensorflow.proto.data.OptimizationOptions.OptionalAutotuneCpuBudgetCase getOptionalAutotuneCpuBudgetCase();

  public org.tensorflow.proto.data.OptimizationOptions.OptionalAutotuneRamBudgetCase getOptionalAutotuneRamBudgetCase();

  public org.tensorflow.proto.data.OptimizationOptions.OptionalFilterFusionCase getOptionalFilterFusionCase();

  public org.tensorflow.proto.data.OptimizationOptions.OptionalMapAndBatchFusionCase getOptionalMapAndBatchFusionCase();

  public org.tensorflow.proto.data.OptimizationOptions.OptionalMapAndFilterFusionCase getOptionalMapAndFilterFusionCase();

  public org.tensorflow.proto.data.OptimizationOptions.OptionalMapFusionCase getOptionalMapFusionCase();

  public org.tensorflow.proto.data.OptimizationOptions.OptionalMapParallelizationCase getOptionalMapParallelizationCase();

  public org.tensorflow.proto.data.OptimizationOptions.OptionalNoopEliminationCase getOptionalNoopEliminationCase();

  public org.tensorflow.proto.data.OptimizationOptions.OptionalParallelBatchCase getOptionalParallelBatchCase();

  public org.tensorflow.proto.data.OptimizationOptions.OptionalShuffleAndRepeatFusionCase getOptionalShuffleAndRepeatFusionCase();
}