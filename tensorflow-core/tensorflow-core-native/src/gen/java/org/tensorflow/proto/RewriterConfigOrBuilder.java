// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/protobuf/rewriter_config.proto

package org.tensorflow.proto;

public interface RewriterConfigOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.RewriterConfig)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * CPU Conversion settings between NHCW and NCHW.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.CpuLayout cpu_layout_conversion = 50;</code>
   * @return The enum numeric value on the wire for cpuLayoutConversion.
   */
  int getCpuLayoutConversionValue();
  /**
   * <pre>
   * CPU Conversion settings between NHCW and NCHW.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.CpuLayout cpu_layout_conversion = 50;</code>
   * @return The cpuLayoutConversion.
   */
  org.tensorflow.proto.RewriterConfig.CpuLayout getCpuLayoutConversion();

  /**
   * <pre>
   * Optimize tensor layouts (default is ON)
   * e.g. This will try to use NCHW layout on GPU which is faster.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle layout_optimizer = 1;</code>
   * @return The enum numeric value on the wire for layoutOptimizer.
   */
  int getLayoutOptimizerValue();
  /**
   * <pre>
   * Optimize tensor layouts (default is ON)
   * e.g. This will try to use NCHW layout on GPU which is faster.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle layout_optimizer = 1;</code>
   * @return The layoutOptimizer.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getLayoutOptimizer();

  /**
   * <pre>
   * Fold constants (default is ON)
   * Statically infer the value of tensors when possible, and materialize the
   * result using constants.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle constant_folding = 3;</code>
   * @return The enum numeric value on the wire for constantFolding.
   */
  int getConstantFoldingValue();
  /**
   * <pre>
   * Fold constants (default is ON)
   * Statically infer the value of tensors when possible, and materialize the
   * result using constants.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle constant_folding = 3;</code>
   * @return The constantFolding.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getConstantFolding();

  /**
   * <pre>
   * Shape optimizations (default is ON)
   * Simplify computations made on shapes.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle shape_optimization = 13;</code>
   * @return The enum numeric value on the wire for shapeOptimization.
   */
  int getShapeOptimizationValue();
  /**
   * <pre>
   * Shape optimizations (default is ON)
   * Simplify computations made on shapes.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle shape_optimization = 13;</code>
   * @return The shapeOptimization.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getShapeOptimization();

  /**
   * <pre>
   * Remapping (default is ON)
   * Remap subgraphs onto more efficient implementations.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle remapping = 14;</code>
   * @return The enum numeric value on the wire for remapping.
   */
  int getRemappingValue();
  /**
   * <pre>
   * Remapping (default is ON)
   * Remap subgraphs onto more efficient implementations.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle remapping = 14;</code>
   * @return The remapping.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getRemapping();

  /**
   * <pre>
   * Common subgraph elimination (default is ON)
   * e.g. Simplify arithmetic ops; merge ops with same value (like constants).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle common_subgraph_elimination = 24;</code>
   * @return The enum numeric value on the wire for commonSubgraphElimination.
   */
  int getCommonSubgraphEliminationValue();
  /**
   * <pre>
   * Common subgraph elimination (default is ON)
   * e.g. Simplify arithmetic ops; merge ops with same value (like constants).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle common_subgraph_elimination = 24;</code>
   * @return The commonSubgraphElimination.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getCommonSubgraphElimination();

  /**
   * <pre>
   * Arithmetic optimizations (default is ON)
   * e.g. Simplify arithmetic ops; merge ops with same value (like constants).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle arithmetic_optimization = 7;</code>
   * @return The enum numeric value on the wire for arithmeticOptimization.
   */
  int getArithmeticOptimizationValue();
  /**
   * <pre>
   * Arithmetic optimizations (default is ON)
   * e.g. Simplify arithmetic ops; merge ops with same value (like constants).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle arithmetic_optimization = 7;</code>
   * @return The arithmeticOptimization.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getArithmeticOptimization();

  /**
   * <pre>
   * Control dependency optimizations (default is ON).
   * Remove redundant control dependencies, which may enable other optimization.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle dependency_optimization = 8;</code>
   * @return The enum numeric value on the wire for dependencyOptimization.
   */
  int getDependencyOptimizationValue();
  /**
   * <pre>
   * Control dependency optimizations (default is ON).
   * Remove redundant control dependencies, which may enable other optimization.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle dependency_optimization = 8;</code>
   * @return The dependencyOptimization.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getDependencyOptimization();

  /**
   * <pre>
   * Loop optimizations (default is ON).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle loop_optimization = 9;</code>
   * @return The enum numeric value on the wire for loopOptimization.
   */
  int getLoopOptimizationValue();
  /**
   * <pre>
   * Loop optimizations (default is ON).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle loop_optimization = 9;</code>
   * @return The loopOptimization.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getLoopOptimization();

  /**
   * <pre>
   * Function optimizations (default is ON).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle function_optimization = 10;</code>
   * @return The enum numeric value on the wire for functionOptimization.
   */
  int getFunctionOptimizationValue();
  /**
   * <pre>
   * Function optimizations (default is ON).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle function_optimization = 10;</code>
   * @return The functionOptimization.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getFunctionOptimization();

  /**
   * <pre>
   * Strips debug-related nodes from the graph (off by default).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle debug_stripper = 11;</code>
   * @return The enum numeric value on the wire for debugStripper.
   */
  int getDebugStripperValue();
  /**
   * <pre>
   * Strips debug-related nodes from the graph (off by default).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle debug_stripper = 11;</code>
   * @return The debugStripper.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getDebugStripper();

  /**
   * <pre>
   * If true, don't remove unnecessary ops from the graph
   * </pre>
   *
   * <code>bool disable_model_pruning = 2;</code>
   * @return The disableModelPruning.
   */
  boolean getDisableModelPruning();

  /**
   * <pre>
   * Try to allocate some independent Op outputs contiguously in order to
   * merge or eliminate downstream Ops (off by default).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle scoped_allocator_optimization = 15;</code>
   * @return The enum numeric value on the wire for scopedAllocatorOptimization.
   */
  int getScopedAllocatorOptimizationValue();
  /**
   * <pre>
   * Try to allocate some independent Op outputs contiguously in order to
   * merge or eliminate downstream Ops (off by default).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle scoped_allocator_optimization = 15;</code>
   * @return The scopedAllocatorOptimization.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getScopedAllocatorOptimization();

  /**
   * <pre>
   * Force small ops onto the CPU (default is OFF).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle pin_to_host_optimization = 18;</code>
   * @return The enum numeric value on the wire for pinToHostOptimization.
   */
  int getPinToHostOptimizationValue();
  /**
   * <pre>
   * Force small ops onto the CPU (default is OFF).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle pin_to_host_optimization = 18;</code>
   * @return The pinToHostOptimization.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getPinToHostOptimization();

  /**
   * <pre>
   * Enable the swap of kernel implementations based on the device placement
   * (default is ON).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle implementation_selector = 22;</code>
   * @return The enum numeric value on the wire for implementationSelector.
   */
  int getImplementationSelectorValue();
  /**
   * <pre>
   * Enable the swap of kernel implementations based on the device placement
   * (default is ON).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle implementation_selector = 22;</code>
   * @return The implementationSelector.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getImplementationSelector();

  /**
   * <pre>
   * Optimize data types for CUDA/oneDNN (default is OFF).
   * This will try to use float16 on GPU/CPU which is faster.
   * Note that this can change the numerical stability of the graph and may
   * require the use of loss scaling to maintain model convergence.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle auto_mixed_precision = 23;</code>
   * @return The enum numeric value on the wire for autoMixedPrecision.
   */
  int getAutoMixedPrecisionValue();
  /**
   * <pre>
   * Optimize data types for CUDA/oneDNN (default is OFF).
   * This will try to use float16 on GPU/CPU which is faster.
   * Note that this can change the numerical stability of the graph and may
   * require the use of loss scaling to maintain model convergence.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle auto_mixed_precision = 23;</code>
   * @return The autoMixedPrecision.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getAutoMixedPrecision();

  /**
   * <pre>
   * Optimize data types for oneDNN (default is OFF).
   * This will try to use bfloat16 on CPUs, which is faster.
   * Note that this can change the numerical stability of the graph.
   * Note: this is deprecated.
   * It is replaced by auto_mixed_precision_onednn_bfloat16
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle auto_mixed_precision_mkl = 25;</code>
   * @return The enum numeric value on the wire for autoMixedPrecisionMkl.
   */
  int getAutoMixedPrecisionMklValue();
  /**
   * <pre>
   * Optimize data types for oneDNN (default is OFF).
   * This will try to use bfloat16 on CPUs, which is faster.
   * Note that this can change the numerical stability of the graph.
   * Note: this is deprecated.
   * It is replaced by auto_mixed_precision_onednn_bfloat16
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle auto_mixed_precision_mkl = 25;</code>
   * @return The autoMixedPrecisionMkl.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getAutoMixedPrecisionMkl();

  /**
   * <pre>
   * Optimize data types for oneDNN (default is OFF).
   * This will try to use bfloat16 on CPUs, which is faster.
   * Note that this can change the numerical stability of the graph.
   * Note: this is equivalent to the deprecated option auto_mixed_precision_mkl
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle auto_mixed_precision_onednn_bfloat16 = 31;</code>
   * @return The enum numeric value on the wire for autoMixedPrecisionOnednnBfloat16.
   */
  int getAutoMixedPrecisionOnednnBfloat16Value();
  /**
   * <pre>
   * Optimize data types for oneDNN (default is OFF).
   * This will try to use bfloat16 on CPUs, which is faster.
   * Note that this can change the numerical stability of the graph.
   * Note: this is equivalent to the deprecated option auto_mixed_precision_mkl
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle auto_mixed_precision_onednn_bfloat16 = 31;</code>
   * @return The autoMixedPrecisionOnednnBfloat16.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getAutoMixedPrecisionOnednnBfloat16();

  /**
   * <pre>
   * Emulate a model using data type float16 on CPU (default is OFF).
   * This will try to emulate the float16 inputs and outputs of an operator
   * on CPU to have better correlation with float16 on GPU; however the
   * computation in the operator is based on float32.
   * Note that this can change the numerical stability of the graph.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle auto_mixed_precision_cpu = 29;</code>
   * @return The enum numeric value on the wire for autoMixedPrecisionCpu.
   */
  int getAutoMixedPrecisionCpuValue();
  /**
   * <pre>
   * Emulate a model using data type float16 on CPU (default is OFF).
   * This will try to emulate the float16 inputs and outputs of an operator
   * on CPU to have better correlation with float16 on GPU; however the
   * computation in the operator is based on float32.
   * Note that this can change the numerical stability of the graph.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle auto_mixed_precision_cpu = 29;</code>
   * @return The autoMixedPrecisionCpu.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getAutoMixedPrecisionCpu();

  /**
   * <pre>
   * Disable the entire meta optimizer (off by default).
   * </pre>
   *
   * <code>bool disable_meta_optimizer = 19;</code>
   * @return The disableMetaOptimizer.
   */
  boolean getDisableMetaOptimizer();

  /**
   * <pre>
   * Disable the TFG optimizer (off by default).
   * </pre>
   *
   * <code>bool disable_tfg_optimizer = 32;</code>
   * @return The disableTfgOptimizer.
   */
  boolean getDisableTfgOptimizer();

  /**
   * <pre>
   * Optimizers registered by plugin (default is ON)
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle use_plugin_optimizers = 28;</code>
   * @return The enum numeric value on the wire for usePluginOptimizers.
   */
  int getUsePluginOptimizersValue();
  /**
   * <pre>
   * Optimizers registered by plugin (default is ON)
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle use_plugin_optimizers = 28;</code>
   * @return The usePluginOptimizers.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getUsePluginOptimizers();

  /**
   * <pre>
   * Conditional code motion (default is ON).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle experimental_conditional_code_motion = 30;</code>
   * @return The enum numeric value on the wire for experimentalConditionalCodeMotion.
   */
  int getExperimentalConditionalCodeMotionValue();
  /**
   * <pre>
   * Conditional code motion (default is ON).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.Toggle experimental_conditional_code_motion = 30;</code>
   * @return The experimentalConditionalCodeMotion.
   */
  org.tensorflow.proto.RewriterConfig.Toggle getExperimentalConditionalCodeMotion();

  /**
   * <pre>
   * Controls how many times we run the optimizers in meta optimizer (default
   * is once).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.NumIterationsType meta_optimizer_iterations = 12;</code>
   * @return The enum numeric value on the wire for metaOptimizerIterations.
   */
  int getMetaOptimizerIterationsValue();
  /**
   * <pre>
   * Controls how many times we run the optimizers in meta optimizer (default
   * is once).
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.NumIterationsType meta_optimizer_iterations = 12;</code>
   * @return The metaOptimizerIterations.
   */
  org.tensorflow.proto.RewriterConfig.NumIterationsType getMetaOptimizerIterations();

  /**
   * <pre>
   * The minimum number of nodes in a graph to optimizer. For smaller graphs,
   * optimization is skipped.
   * 0 means the system picks an appropriate number.
   * &lt; 0 means do not skip optimization.
   * </pre>
   *
   * <code>int32 min_graph_nodes = 17;</code>
   * @return The minGraphNodes.
   */
  int getMinGraphNodes();

  /**
   * <pre>
   * Disable optimizations that assume compressed tensors. Note that this flag
   * is experimental and may be removed in the future.
   * </pre>
   *
   * <code>bool experimental_disable_compressed_tensor_optimization = 26;</code>
   * @return The experimentalDisableCompressedTensorOptimization.
   */
  boolean getExperimentalDisableCompressedTensorOptimization();

  /**
   * <pre>
   * Disable folding quantization emulation ops such as FakeQuantWithMinMax* and
   * QuantizeAndDequantize*. Some compilers (e.g. the TF-to-tflite converter)
   * have to extract quantization configs (e.g. min/max range, number of bits,
   * and per-channel) from the quantization emulation ops. Note that this flag
   * is experimental and may be removed in the future. See b/174138564 for more
   * details.
   * </pre>
   *
   * <code>bool experimental_disable_folding_quantization_emulation = 27;</code>
   * @return The experimentalDisableFoldingQuantizationEmulation.
   */
  boolean getExperimentalDisableFoldingQuantizationEmulation();

  /**
   * <pre>
   * Configures memory optimization passes through the meta-optimizer. Has no
   * effect on manually requested memory optimization passes in the optimizers
   * field.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.MemOptType memory_optimization = 4;</code>
   * @return The enum numeric value on the wire for memoryOptimization.
   */
  int getMemoryOptimizationValue();
  /**
   * <pre>
   * Configures memory optimization passes through the meta-optimizer. Has no
   * effect on manually requested memory optimization passes in the optimizers
   * field.
   * </pre>
   *
   * <code>.tensorflow.RewriterConfig.MemOptType memory_optimization = 4;</code>
   * @return The memoryOptimization.
   */
  org.tensorflow.proto.RewriterConfig.MemOptType getMemoryOptimization();

  /**
   * <pre>
   * A node name scope for node names which are valid outputs of recomputations.
   * Inputs to nodes that match this scope may be recomputed (subject either to
   * manual annotation of those input nodes or to manual annotation and
   * heuristics depending on memory_optimization), but the nodes themselves will
   * not be recomputed. This matches any sub-scopes as well, meaning the scope
   * can appear not just as a top-level scope. For example, if the value is
   * "gradients/", the default, it will match node name "gradients/foo",
   * "foo/gradients/bar", but not "foo_gradients/"
   * </pre>
   *
   * <code>string memory_optimizer_target_node_name_scope = 6;</code>
   * @return The memoryOptimizerTargetNodeNameScope.
   */
  java.lang.String getMemoryOptimizerTargetNodeNameScope();
  /**
   * <pre>
   * A node name scope for node names which are valid outputs of recomputations.
   * Inputs to nodes that match this scope may be recomputed (subject either to
   * manual annotation of those input nodes or to manual annotation and
   * heuristics depending on memory_optimization), but the nodes themselves will
   * not be recomputed. This matches any sub-scopes as well, meaning the scope
   * can appear not just as a top-level scope. For example, if the value is
   * "gradients/", the default, it will match node name "gradients/foo",
   * "foo/gradients/bar", but not "foo_gradients/"
   * </pre>
   *
   * <code>string memory_optimizer_target_node_name_scope = 6;</code>
   * @return The bytes for memoryOptimizerTargetNodeNameScope.
   */
  com.google.protobuf.ByteString
      getMemoryOptimizerTargetNodeNameScopeBytes();

  /**
   * <pre>
   * Maximum number of milliseconds to spend optimizing a single graph before
   * timing out. If less than or equal to 0 (default value) the optimizer will
   * never time out.
   * </pre>
   *
   * <code>int64 meta_optimizer_timeout_ms = 20;</code>
   * @return The metaOptimizerTimeoutMs.
   */
  long getMetaOptimizerTimeoutMs();

  /**
   * <pre>
   * Configures AutoParallel optimization passes either through the
   * meta-optimizer or when manually specified through the optimizers field.
   * </pre>
   *
   * <code>.tensorflow.AutoParallelOptions auto_parallel = 5;</code>
   * @return Whether the autoParallel field is set.
   */
  boolean hasAutoParallel();
  /**
   * <pre>
   * Configures AutoParallel optimization passes either through the
   * meta-optimizer or when manually specified through the optimizers field.
   * </pre>
   *
   * <code>.tensorflow.AutoParallelOptions auto_parallel = 5;</code>
   * @return The autoParallel.
   */
  org.tensorflow.proto.AutoParallelOptions getAutoParallel();
  /**
   * <pre>
   * Configures AutoParallel optimization passes either through the
   * meta-optimizer or when manually specified through the optimizers field.
   * </pre>
   *
   * <code>.tensorflow.AutoParallelOptions auto_parallel = 5;</code>
   */
  org.tensorflow.proto.AutoParallelOptionsOrBuilder getAutoParallelOrBuilder();

  /**
   * <pre>
   * If true, any optimization pass failing will cause the MetaOptimizer to
   * stop with an error. By default - or when set to false, failing passes are
   * skipped silently.
   * </pre>
   *
   * <code>bool fail_on_optimizer_errors = 21;</code>
   * @return The failOnOptimizerErrors.
   */
  boolean getFailOnOptimizerErrors();

  /**
   * <code>.tensorflow.ScopedAllocatorOptions scoped_allocator_opts = 16;</code>
   * @return Whether the scopedAllocatorOpts field is set.
   */
  boolean hasScopedAllocatorOpts();
  /**
   * <code>.tensorflow.ScopedAllocatorOptions scoped_allocator_opts = 16;</code>
   * @return The scopedAllocatorOpts.
   */
  org.tensorflow.proto.ScopedAllocatorOptions getScopedAllocatorOpts();
  /**
   * <code>.tensorflow.ScopedAllocatorOptions scoped_allocator_opts = 16;</code>
   */
  org.tensorflow.proto.ScopedAllocatorOptionsOrBuilder getScopedAllocatorOptsOrBuilder();

  /**
   * <pre>
   * If non-empty, will use this as an alternative way to specify a list of
   * optimizations to turn on and the order of the optimizations (replacing the
   * meta-optimizer).
   * Of the RewriterConfig options, only the AutoParallel configuration options
   * (the auto_parallel field) apply to manually requested optimization passes
   * ("autoparallel"). Memory optimization passes ("memory") invoked here are
   * not configurable (in contrast to memory optimization passes through the
   * meta-optimizer) and act only on manual op annotations.
   * Custom optimizers (see custom_optimizers) that are not part of this
   * schedule will be run after - in the order that they were specified.
   * </pre>
   *
   * <code>repeated string optimizers = 100;</code>
   * @return A list containing the optimizers.
   */
  java.util.List<java.lang.String>
      getOptimizersList();
  /**
   * <pre>
   * If non-empty, will use this as an alternative way to specify a list of
   * optimizations to turn on and the order of the optimizations (replacing the
   * meta-optimizer).
   * Of the RewriterConfig options, only the AutoParallel configuration options
   * (the auto_parallel field) apply to manually requested optimization passes
   * ("autoparallel"). Memory optimization passes ("memory") invoked here are
   * not configurable (in contrast to memory optimization passes through the
   * meta-optimizer) and act only on manual op annotations.
   * Custom optimizers (see custom_optimizers) that are not part of this
   * schedule will be run after - in the order that they were specified.
   * </pre>
   *
   * <code>repeated string optimizers = 100;</code>
   * @return The count of optimizers.
   */
  int getOptimizersCount();
  /**
   * <pre>
   * If non-empty, will use this as an alternative way to specify a list of
   * optimizations to turn on and the order of the optimizations (replacing the
   * meta-optimizer).
   * Of the RewriterConfig options, only the AutoParallel configuration options
   * (the auto_parallel field) apply to manually requested optimization passes
   * ("autoparallel"). Memory optimization passes ("memory") invoked here are
   * not configurable (in contrast to memory optimization passes through the
   * meta-optimizer) and act only on manual op annotations.
   * Custom optimizers (see custom_optimizers) that are not part of this
   * schedule will be run after - in the order that they were specified.
   * </pre>
   *
   * <code>repeated string optimizers = 100;</code>
   * @param index The index of the element to return.
   * @return The optimizers at the given index.
   */
  java.lang.String getOptimizers(int index);
  /**
   * <pre>
   * If non-empty, will use this as an alternative way to specify a list of
   * optimizations to turn on and the order of the optimizations (replacing the
   * meta-optimizer).
   * Of the RewriterConfig options, only the AutoParallel configuration options
   * (the auto_parallel field) apply to manually requested optimization passes
   * ("autoparallel"). Memory optimization passes ("memory") invoked here are
   * not configurable (in contrast to memory optimization passes through the
   * meta-optimizer) and act only on manual op annotations.
   * Custom optimizers (see custom_optimizers) that are not part of this
   * schedule will be run after - in the order that they were specified.
   * </pre>
   *
   * <code>repeated string optimizers = 100;</code>
   * @param index The index of the value to return.
   * @return The bytes of the optimizers at the given index.
   */
  com.google.protobuf.ByteString
      getOptimizersBytes(int index);

  /**
   * <pre>
   * list of CustomGraphOptimizers to apply.
   * </pre>
   *
   * <code>repeated .tensorflow.RewriterConfig.CustomGraphOptimizer custom_optimizers = 200;</code>
   */
  java.util.List<org.tensorflow.proto.RewriterConfig.CustomGraphOptimizer> 
      getCustomOptimizersList();
  /**
   * <pre>
   * list of CustomGraphOptimizers to apply.
   * </pre>
   *
   * <code>repeated .tensorflow.RewriterConfig.CustomGraphOptimizer custom_optimizers = 200;</code>
   */
  org.tensorflow.proto.RewriterConfig.CustomGraphOptimizer getCustomOptimizers(int index);
  /**
   * <pre>
   * list of CustomGraphOptimizers to apply.
   * </pre>
   *
   * <code>repeated .tensorflow.RewriterConfig.CustomGraphOptimizer custom_optimizers = 200;</code>
   */
  int getCustomOptimizersCount();
  /**
   * <pre>
   * list of CustomGraphOptimizers to apply.
   * </pre>
   *
   * <code>repeated .tensorflow.RewriterConfig.CustomGraphOptimizer custom_optimizers = 200;</code>
   */
  java.util.List<? extends org.tensorflow.proto.RewriterConfig.CustomGraphOptimizerOrBuilder> 
      getCustomOptimizersOrBuilderList();
  /**
   * <pre>
   * list of CustomGraphOptimizers to apply.
   * </pre>
   *
   * <code>repeated .tensorflow.RewriterConfig.CustomGraphOptimizer custom_optimizers = 200;</code>
   */
  org.tensorflow.proto.RewriterConfig.CustomGraphOptimizerOrBuilder getCustomOptimizersOrBuilder(
      int index);

  /**
   * <pre>
   * VerifierConfig specifying the verifiers to be run after every optimizer.
   * </pre>
   *
   * <code>.tensorflow.VerifierConfig inter_optimizer_verifier_config = 300;</code>
   * @return Whether the interOptimizerVerifierConfig field is set.
   */
  boolean hasInterOptimizerVerifierConfig();
  /**
   * <pre>
   * VerifierConfig specifying the verifiers to be run after every optimizer.
   * </pre>
   *
   * <code>.tensorflow.VerifierConfig inter_optimizer_verifier_config = 300;</code>
   * @return The interOptimizerVerifierConfig.
   */
  org.tensorflow.proto.VerifierConfig getInterOptimizerVerifierConfig();
  /**
   * <pre>
   * VerifierConfig specifying the verifiers to be run after every optimizer.
   * </pre>
   *
   * <code>.tensorflow.VerifierConfig inter_optimizer_verifier_config = 300;</code>
   */
  org.tensorflow.proto.VerifierConfigOrBuilder getInterOptimizerVerifierConfigOrBuilder();

  /**
   * <pre>
   * VerifierConfig specifying the verifiers to be run at the end, after all
   * optimizers have run.
   * </pre>
   *
   * <code>.tensorflow.VerifierConfig post_optimization_verifier_config = 301;</code>
   * @return Whether the postOptimizationVerifierConfig field is set.
   */
  boolean hasPostOptimizationVerifierConfig();
  /**
   * <pre>
   * VerifierConfig specifying the verifiers to be run at the end, after all
   * optimizers have run.
   * </pre>
   *
   * <code>.tensorflow.VerifierConfig post_optimization_verifier_config = 301;</code>
   * @return The postOptimizationVerifierConfig.
   */
  org.tensorflow.proto.VerifierConfig getPostOptimizationVerifierConfig();
  /**
   * <pre>
   * VerifierConfig specifying the verifiers to be run at the end, after all
   * optimizers have run.
   * </pre>
   *
   * <code>.tensorflow.VerifierConfig post_optimization_verifier_config = 301;</code>
   */
  org.tensorflow.proto.VerifierConfigOrBuilder getPostOptimizationVerifierConfigOrBuilder();
}
