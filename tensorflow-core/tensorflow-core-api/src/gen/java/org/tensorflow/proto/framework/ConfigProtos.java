// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/protobuf/config.proto

package org.tensorflow.proto.framework;

public final class ConfigProtos {
  private ConfigProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_GPUOptions_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_GPUOptions_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_GPUOptions_Experimental_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_GPUOptions_Experimental_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_GPUOptions_Experimental_VirtualDevices_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_GPUOptions_Experimental_VirtualDevices_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_OptimizerOptions_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_OptimizerOptions_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_GraphOptions_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_GraphOptions_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_ThreadPoolOptionProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_ThreadPoolOptionProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_RPCOptions_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_RPCOptions_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_SessionMetadata_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_SessionMetadata_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_ConfigProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_ConfigProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_ConfigProto_DeviceCountEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_ConfigProto_DeviceCountEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_ConfigProto_Experimental_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_ConfigProto_Experimental_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_RunOptions_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_RunOptions_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_RunOptions_Experimental_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_RunOptions_Experimental_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_RunOptions_Experimental_RunHandlerPoolOptions_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_RunOptions_Experimental_RunHandlerPoolOptions_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_RunMetadata_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_RunMetadata_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_RunMetadata_FunctionGraphs_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_RunMetadata_FunctionGraphs_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_TensorConnection_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_TensorConnection_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_CallableOptions_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_CallableOptions_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_CallableOptions_FeedDevicesEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_CallableOptions_FeedDevicesEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_CallableOptions_FetchDevicesEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_CallableOptions_FetchDevicesEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n%tensorflow/core/protobuf/config.proto\022" +
      "\ntensorflow\032*tensorflow/core/framework/c" +
      "ost_graph.proto\032%tensorflow/core/framewo" +
      "rk/graph.proto\032*tensorflow/core/framewor" +
      "k/step_stats.proto\032&tensorflow/core/prot" +
      "obuf/cluster.proto\0322tensorflow/core/prot" +
      "obuf/coordination_config.proto\032$tensorfl" +
      "ow/core/protobuf/debug.proto\032.tensorflow" +
      "/core/protobuf/rewriter_config.proto\"\221\006\n" +
      "\nGPUOptions\022\'\n\037per_process_gpu_memory_fr" +
      "action\030\001 \001(\001\022\024\n\014allow_growth\030\004 \001(\010\022\026\n\016al" +
      "locator_type\030\002 \001(\t\022\037\n\027deferred_deletion_" +
      "bytes\030\003 \001(\003\022\033\n\023visible_device_list\030\005 \001(\t" +
      "\022\"\n\032polling_active_delay_usecs\030\006 \001(\005\022$\n\034" +
      "polling_inactive_delay_msecs\030\007 \001(\005\022\034\n\024fo" +
      "rce_gpu_compatible\030\010 \001(\010\0229\n\014experimental" +
      "\030\t \001(\0132#.tensorflow.GPUOptions.Experimen" +
      "tal\032\312\003\n\014Experimental\022K\n\017virtual_devices\030" +
      "\001 \003(\01322.tensorflow.GPUOptions.Experiment" +
      "al.VirtualDevices\022\032\n\022use_unified_memory\030" +
      "\002 \001(\010\022#\n\033num_dev_to_dev_copy_streams\030\003 \001" +
      "(\005\022\035\n\025collective_ring_order\030\004 \001(\t\022\035\n\025tim" +
      "estamped_allocator\030\005 \001(\010\022#\n\033kernel_track" +
      "er_max_interval\030\007 \001(\005\022 \n\030kernel_tracker_" +
      "max_bytes\030\010 \001(\005\022\"\n\032kernel_tracker_max_pe" +
      "nding\030\t \001(\005\022\'\n\037internal_fragmentation_fr" +
      "action\030\n \001(\001\022\035\n\025use_cuda_malloc_async\030\013 " +
      "\001(\010\032;\n\016VirtualDevices\022\027\n\017memory_limit_mb" +
      "\030\001 \003(\002\022\020\n\010priority\030\002 \003(\005\"\235\003\n\020OptimizerOp" +
      "tions\022+\n#do_common_subexpression_elimina" +
      "tion\030\001 \001(\010\022\033\n\023do_constant_folding\030\002 \001(\010\022" +
      "$\n\034max_folded_constant_in_bytes\030\006 \001(\003\022\034\n" +
      "\024do_function_inlining\030\004 \001(\010\0225\n\topt_level" +
      "\030\003 \001(\0162\".tensorflow.OptimizerOptions.Lev" +
      "el\022E\n\020global_jit_level\030\005 \001(\0162+.tensorflo" +
      "w.OptimizerOptions.GlobalJitLevel\022\026\n\016cpu" +
      "_global_jit\030\007 \001(\010\" \n\005Level\022\006\n\002L1\020\000\022\017\n\002L0" +
      "\020\377\377\377\377\377\377\377\377\377\001\"C\n\016GlobalJitLevel\022\013\n\007DEFAULT" +
      "\020\000\022\020\n\003OFF\020\377\377\377\377\377\377\377\377\377\001\022\010\n\004ON_1\020\001\022\010\n\004ON_2\020\002" +
      "\"\356\002\n\014GraphOptions\022\036\n\026enable_recv_schedul" +
      "ing\030\002 \001(\010\0227\n\021optimizer_options\030\003 \001(\0132\034.t" +
      "ensorflow.OptimizerOptions\022\030\n\020build_cost" +
      "_model\030\004 \001(\003\022\036\n\026build_cost_model_after\030\t" +
      " \001(\003\022\024\n\014infer_shapes\030\005 \001(\010\022\032\n\022place_prun" +
      "ed_graph\030\006 \001(\010\022 \n\030enable_bfloat16_sendre" +
      "cv\030\007 \001(\010\022\025\n\rtimeline_step\030\010 \001(\005\0223\n\017rewri" +
      "te_options\030\n \001(\0132\032.tensorflow.RewriterCo" +
      "nfigJ\004\010\001\020\002R%skip_common_subexpression_el" +
      "imination\"A\n\025ThreadPoolOptionProto\022\023\n\013nu" +
      "m_threads\030\001 \001(\005\022\023\n\013global_name\030\002 \001(\t\"\325\001\n" +
      "\nRPCOptions\022$\n\034use_rpc_for_inprocess_mas" +
      "ter\030\001 \001(\010\022\035\n\025compression_algorithm\030\002 \001(\t" +
      "\022\031\n\021compression_level\030\003 \001(\005\022\032\n\022cache_rpc" +
      "_response\030\004 \001(\010\022*\n\"disable_session_conne" +
      "ction_sharing\030\005 \001(\010\022\037\n\027num_channels_per_" +
      "target\030\006 \001(\005\"0\n\017SessionMetadata\022\014\n\004name\030" +
      "\001 \001(\t\022\017\n\007version\030\002 \001(\003\"\256\016\n\013ConfigProto\022>" +
      "\n\014device_count\030\001 \003(\0132(.tensorflow.Config" +
      "Proto.DeviceCountEntry\022$\n\034intra_op_paral" +
      "lelism_threads\030\002 \001(\005\022$\n\034inter_op_paralle" +
      "lism_threads\030\005 \001(\005\022\037\n\027use_per_session_th" +
      "reads\030\t \001(\010\022G\n\034session_inter_op_thread_p" +
      "ool\030\014 \003(\0132!.tensorflow.ThreadPoolOptionP" +
      "roto\022\030\n\020placement_period\030\003 \001(\005\022\026\n\016device" +
      "_filters\030\004 \003(\t\022+\n\013gpu_options\030\006 \001(\0132\026.te" +
      "nsorflow.GPUOptions\022\034\n\024allow_soft_placem" +
      "ent\030\007 \001(\010\022\034\n\024log_device_placement\030\010 \001(\010\022" +
      "/\n\rgraph_options\030\n \001(\0132\030.tensorflow.Grap" +
      "hOptions\022\037\n\027operation_timeout_in_ms\030\013 \001(" +
      "\003\022+\n\013rpc_options\030\r \001(\0132\026.tensorflow.RPCO" +
      "ptions\022+\n\013cluster_def\030\016 \001(\0132\026.tensorflow" +
      ".ClusterDef\022\035\n\025isolate_session_state\030\017 \001" +
      "(\010\022(\n share_cluster_devices_in_session\030\021" +
      " \001(\010\022:\n\014experimental\030\020 \001(\0132$.tensorflow." +
      "ConfigProto.Experimental\0322\n\020DeviceCountE" +
      "ntry\022\013\n\003key\030\001 \001(\t\022\r\n\005value\030\002 \001(\005:\0028\001\032\250\010\n" +
      "\014Experimental\022\037\n\027collective_group_leader" +
      "\030\001 \001(\t\022\025\n\rexecutor_type\030\003 \001(\t\022\032\n\022recv_bu" +
      "f_max_chunk\030\004 \001(\005\022\031\n\021use_numa_affinity\030\005" +
      " \001(\010\0225\n-collective_deterministic_sequent" +
      "ial_execution\030\006 \001(\010\022\027\n\017collective_nccl\030\007" +
      " \001(\010\0226\n.share_session_state_in_clustersp" +
      "ec_propagation\030\010 \001(\010\022\037\n\027disable_thread_s" +
      "pinning\030\t \001(\010\022(\n share_cluster_devices_i" +
      "n_session\030\n \001(\010\0225\n\020session_metadata\030\013 \001(" +
      "\0132\033.tensorflow.SessionMetadata\022!\n\031optimi" +
      "ze_for_static_graph\030\014 \001(\010\022\032\n\022enable_mlir" +
      "_bridge\030\r \001(\010\022S\n\023mlir_bridge_rollout\030\021 \001" +
      "(\01626.tensorflow.ConfigProto.Experimental" +
      ".MlirBridgeRollout\022&\n\036enable_mlir_graph_" +
      "optimization\030\020 \001(\010\022\'\n\037disable_output_par" +
      "tition_graphs\030\016 \001(\010\022#\n\033xla_fusion_autotu" +
      "ner_thresh\030\017 \001(\003\022\020\n\010use_tfrt\030\022 \001(\010\022\'\n\037di" +
      "sable_functional_ops_lowering\030\025 \001(\010\022\'\n\037x" +
      "la_prefer_single_graph_cluster\030\026 \001(\010\022B\n\023" +
      "coordination_config\030\027 \001(\0132%.tensorflow.C" +
      "oordinationServiceConfig\"\332\001\n\021MlirBridgeR" +
      "ollout\022#\n\037MLIR_BRIDGE_ROLLOUT_UNSPECIFIE" +
      "D\020\000\022\037\n\033MLIR_BRIDGE_ROLLOUT_ENABLED\020\001\022 \n\034" +
      "MLIR_BRIDGE_ROLLOUT_DISABLED\020\002\022)\n%MLIR_B" +
      "RIDGE_ROLLOUT_SAFE_MODE_ENABLED\020\003\0222\n.MLI" +
      "R_BRIDGE_ROLLOUT_SAFE_MODE_FALLBACK_ENAB" +
      "LED\020\004J\004\010\002\020\003J\004\010\023\020\024J\004\010\024\020\025\"\341\004\n\nRunOptions\0226" +
      "\n\013trace_level\030\001 \001(\0162!.tensorflow.RunOpti" +
      "ons.TraceLevel\022\025\n\rtimeout_in_ms\030\002 \001(\003\022\034\n" +
      "\024inter_op_thread_pool\030\003 \001(\005\022\037\n\027output_pa" +
      "rtition_graphs\030\005 \001(\010\022/\n\rdebug_options\030\006 " +
      "\001(\0132\030.tensorflow.DebugOptions\022*\n\"report_" +
      "tensor_allocations_upon_oom\030\007 \001(\010\0229\n\014exp" +
      "erimental\030\010 \001(\0132#.tensorflow.RunOptions." +
      "Experimental\032\322\001\n\014Experimental\022\034\n\024collect" +
      "ive_graph_key\030\001 \001(\003\022\034\n\024use_run_handler_p" +
      "ool\030\002 \001(\010\022[\n\030run_handler_pool_options\030\003 " +
      "\001(\01329.tensorflow.RunOptions.Experimental" +
      ".RunHandlerPoolOptions\032)\n\025RunHandlerPool" +
      "Options\022\020\n\010priority\030\001 \001(\003\"R\n\nTraceLevel\022" +
      "\014\n\010NO_TRACE\020\000\022\022\n\016SOFTWARE_TRACE\020\001\022\022\n\016HAR" +
      "DWARE_TRACE\020\002\022\016\n\nFULL_TRACE\020\003J\004\010\004\020\005\"\207\003\n\013" +
      "RunMetadata\022)\n\nstep_stats\030\001 \001(\0132\025.tensor" +
      "flow.StepStats\022,\n\ncost_graph\030\002 \001(\0132\030.ten" +
      "sorflow.CostGraphDef\022.\n\020partition_graphs" +
      "\030\003 \003(\0132\024.tensorflow.GraphDef\022?\n\017function" +
      "_graphs\030\004 \003(\0132&.tensorflow.RunMetadata.F" +
      "unctionGraphs\032\255\001\n\016FunctionGraphs\022.\n\020part" +
      "ition_graphs\030\001 \003(\0132\024.tensorflow.GraphDef" +
      "\0224\n\026pre_optimization_graph\030\002 \001(\0132\024.tenso" +
      "rflow.GraphDef\0225\n\027post_optimization_grap" +
      "h\030\003 \001(\0132\024.tensorflow.GraphDef\":\n\020TensorC" +
      "onnection\022\023\n\013from_tensor\030\001 \001(\t\022\021\n\tto_ten" +
      "sor\030\002 \001(\t\"\260\003\n\017CallableOptions\022\014\n\004feed\030\001 " +
      "\003(\t\022\r\n\005fetch\030\002 \003(\t\022\016\n\006target\030\003 \003(\t\022+\n\013ru" +
      "n_options\030\004 \001(\0132\026.tensorflow.RunOptions\022" +
      "7\n\021tensor_connection\030\005 \003(\0132\034.tensorflow." +
      "TensorConnection\022B\n\014feed_devices\030\006 \003(\0132," +
      ".tensorflow.CallableOptions.FeedDevicesE" +
      "ntry\022D\n\rfetch_devices\030\007 \003(\0132-.tensorflow" +
      ".CallableOptions.FetchDevicesEntry\022\027\n\017fe" +
      "tch_skip_sync\030\010 \001(\010\0322\n\020FeedDevicesEntry\022" +
      "\013\n\003key\030\001 \001(\t\022\r\n\005value\030\002 \001(\t:\0028\001\0323\n\021Fetch" +
      "DevicesEntry\022\013\n\003key\030\001 \001(\t\022\r\n\005value\030\002 \001(\t" +
      ":\0028\001B\212\001\n\036org.tensorflow.proto.frameworkB" +
      "\014ConfigProtosP\001ZUgithub.com/tensorflow/t" +
      "ensorflow/tensorflow/go/core/protobuf/fo" +
      "r_core_protos_go_proto\370\001\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          org.tensorflow.proto.framework.CostGraphProtos.getDescriptor(),
          org.tensorflow.proto.framework.GraphProtos.getDescriptor(),
          org.tensorflow.proto.framework.StepStatsProtos.getDescriptor(),
          org.tensorflow.proto.distruntime.ClusterProtos.getDescriptor(),
          org.tensorflow.proto.distruntime.CoordinationConfig.getDescriptor(),
          org.tensorflow.proto.framework.DebugProtos.getDescriptor(),
          org.tensorflow.proto.framework.RewriterConfigProtos.getDescriptor(),
        });
    internal_static_tensorflow_GPUOptions_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_tensorflow_GPUOptions_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_GPUOptions_descriptor,
        new java.lang.String[] { "PerProcessGpuMemoryFraction", "AllowGrowth", "AllocatorType", "DeferredDeletionBytes", "VisibleDeviceList", "PollingActiveDelayUsecs", "PollingInactiveDelayMsecs", "ForceGpuCompatible", "Experimental", });
    internal_static_tensorflow_GPUOptions_Experimental_descriptor =
      internal_static_tensorflow_GPUOptions_descriptor.getNestedTypes().get(0);
    internal_static_tensorflow_GPUOptions_Experimental_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_GPUOptions_Experimental_descriptor,
        new java.lang.String[] { "VirtualDevices", "UseUnifiedMemory", "NumDevToDevCopyStreams", "CollectiveRingOrder", "TimestampedAllocator", "KernelTrackerMaxInterval", "KernelTrackerMaxBytes", "KernelTrackerMaxPending", "InternalFragmentationFraction", "UseCudaMallocAsync", });
    internal_static_tensorflow_GPUOptions_Experimental_VirtualDevices_descriptor =
      internal_static_tensorflow_GPUOptions_Experimental_descriptor.getNestedTypes().get(0);
    internal_static_tensorflow_GPUOptions_Experimental_VirtualDevices_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_GPUOptions_Experimental_VirtualDevices_descriptor,
        new java.lang.String[] { "MemoryLimitMb", "Priority", });
    internal_static_tensorflow_OptimizerOptions_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_tensorflow_OptimizerOptions_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_OptimizerOptions_descriptor,
        new java.lang.String[] { "DoCommonSubexpressionElimination", "DoConstantFolding", "MaxFoldedConstantInBytes", "DoFunctionInlining", "OptLevel", "GlobalJitLevel", "CpuGlobalJit", });
    internal_static_tensorflow_GraphOptions_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_tensorflow_GraphOptions_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_GraphOptions_descriptor,
        new java.lang.String[] { "EnableRecvScheduling", "OptimizerOptions", "BuildCostModel", "BuildCostModelAfter", "InferShapes", "PlacePrunedGraph", "EnableBfloat16Sendrecv", "TimelineStep", "RewriteOptions", });
    internal_static_tensorflow_ThreadPoolOptionProto_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_tensorflow_ThreadPoolOptionProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_ThreadPoolOptionProto_descriptor,
        new java.lang.String[] { "NumThreads", "GlobalName", });
    internal_static_tensorflow_RPCOptions_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_tensorflow_RPCOptions_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_RPCOptions_descriptor,
        new java.lang.String[] { "UseRpcForInprocessMaster", "CompressionAlgorithm", "CompressionLevel", "CacheRpcResponse", "DisableSessionConnectionSharing", "NumChannelsPerTarget", });
    internal_static_tensorflow_SessionMetadata_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_tensorflow_SessionMetadata_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_SessionMetadata_descriptor,
        new java.lang.String[] { "Name", "Version", });
    internal_static_tensorflow_ConfigProto_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_tensorflow_ConfigProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_ConfigProto_descriptor,
        new java.lang.String[] { "DeviceCount", "IntraOpParallelismThreads", "InterOpParallelismThreads", "UsePerSessionThreads", "SessionInterOpThreadPool", "PlacementPeriod", "DeviceFilters", "GpuOptions", "AllowSoftPlacement", "LogDevicePlacement", "GraphOptions", "OperationTimeoutInMs", "RpcOptions", "ClusterDef", "IsolateSessionState", "ShareClusterDevicesInSession", "Experimental", });
    internal_static_tensorflow_ConfigProto_DeviceCountEntry_descriptor =
      internal_static_tensorflow_ConfigProto_descriptor.getNestedTypes().get(0);
    internal_static_tensorflow_ConfigProto_DeviceCountEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_ConfigProto_DeviceCountEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_tensorflow_ConfigProto_Experimental_descriptor =
      internal_static_tensorflow_ConfigProto_descriptor.getNestedTypes().get(1);
    internal_static_tensorflow_ConfigProto_Experimental_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_ConfigProto_Experimental_descriptor,
        new java.lang.String[] { "CollectiveGroupLeader", "ExecutorType", "RecvBufMaxChunk", "UseNumaAffinity", "CollectiveDeterministicSequentialExecution", "CollectiveNccl", "ShareSessionStateInClusterspecPropagation", "DisableThreadSpinning", "ShareClusterDevicesInSession", "SessionMetadata", "OptimizeForStaticGraph", "EnableMlirBridge", "MlirBridgeRollout", "EnableMlirGraphOptimization", "DisableOutputPartitionGraphs", "XlaFusionAutotunerThresh", "UseTfrt", "DisableFunctionalOpsLowering", "XlaPreferSingleGraphCluster", "CoordinationConfig", });
    internal_static_tensorflow_RunOptions_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_tensorflow_RunOptions_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_RunOptions_descriptor,
        new java.lang.String[] { "TraceLevel", "TimeoutInMs", "InterOpThreadPool", "OutputPartitionGraphs", "DebugOptions", "ReportTensorAllocationsUponOom", "Experimental", });
    internal_static_tensorflow_RunOptions_Experimental_descriptor =
      internal_static_tensorflow_RunOptions_descriptor.getNestedTypes().get(0);
    internal_static_tensorflow_RunOptions_Experimental_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_RunOptions_Experimental_descriptor,
        new java.lang.String[] { "CollectiveGraphKey", "UseRunHandlerPool", "RunHandlerPoolOptions", });
    internal_static_tensorflow_RunOptions_Experimental_RunHandlerPoolOptions_descriptor =
      internal_static_tensorflow_RunOptions_Experimental_descriptor.getNestedTypes().get(0);
    internal_static_tensorflow_RunOptions_Experimental_RunHandlerPoolOptions_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_RunOptions_Experimental_RunHandlerPoolOptions_descriptor,
        new java.lang.String[] { "Priority", });
    internal_static_tensorflow_RunMetadata_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_tensorflow_RunMetadata_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_RunMetadata_descriptor,
        new java.lang.String[] { "StepStats", "CostGraph", "PartitionGraphs", "FunctionGraphs", });
    internal_static_tensorflow_RunMetadata_FunctionGraphs_descriptor =
      internal_static_tensorflow_RunMetadata_descriptor.getNestedTypes().get(0);
    internal_static_tensorflow_RunMetadata_FunctionGraphs_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_RunMetadata_FunctionGraphs_descriptor,
        new java.lang.String[] { "PartitionGraphs", "PreOptimizationGraph", "PostOptimizationGraph", });
    internal_static_tensorflow_TensorConnection_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_tensorflow_TensorConnection_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_TensorConnection_descriptor,
        new java.lang.String[] { "FromTensor", "ToTensor", });
    internal_static_tensorflow_CallableOptions_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_tensorflow_CallableOptions_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_CallableOptions_descriptor,
        new java.lang.String[] { "Feed", "Fetch", "Target", "RunOptions", "TensorConnection", "FeedDevices", "FetchDevices", "FetchSkipSync", });
    internal_static_tensorflow_CallableOptions_FeedDevicesEntry_descriptor =
      internal_static_tensorflow_CallableOptions_descriptor.getNestedTypes().get(0);
    internal_static_tensorflow_CallableOptions_FeedDevicesEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_CallableOptions_FeedDevicesEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_tensorflow_CallableOptions_FetchDevicesEntry_descriptor =
      internal_static_tensorflow_CallableOptions_descriptor.getNestedTypes().get(1);
    internal_static_tensorflow_CallableOptions_FetchDevicesEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_CallableOptions_FetchDevicesEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    org.tensorflow.proto.framework.CostGraphProtos.getDescriptor();
    org.tensorflow.proto.framework.GraphProtos.getDescriptor();
    org.tensorflow.proto.framework.StepStatsProtos.getDescriptor();
    org.tensorflow.proto.distruntime.ClusterProtos.getDescriptor();
    org.tensorflow.proto.distruntime.CoordinationConfig.getDescriptor();
    org.tensorflow.proto.framework.DebugProtos.getDescriptor();
    org.tensorflow.proto.framework.RewriterConfigProtos.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
