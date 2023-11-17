// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/protobuf/config.proto

package org.tensorflow.proto;

public interface RunMetadataOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.RunMetadata)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Statistics traced for this step. Populated if tracing is turned on via the
   * "RunOptions" proto.
   * EXPERIMENTAL: The format and set of events may change in future versions.
   * </pre>
   *
   * <code>.tensorflow.StepStats step_stats = 1;</code>
   * @return Whether the stepStats field is set.
   */
  boolean hasStepStats();
  /**
   * <pre>
   * Statistics traced for this step. Populated if tracing is turned on via the
   * "RunOptions" proto.
   * EXPERIMENTAL: The format and set of events may change in future versions.
   * </pre>
   *
   * <code>.tensorflow.StepStats step_stats = 1;</code>
   * @return The stepStats.
   */
  org.tensorflow.proto.StepStats getStepStats();
  /**
   * <pre>
   * Statistics traced for this step. Populated if tracing is turned on via the
   * "RunOptions" proto.
   * EXPERIMENTAL: The format and set of events may change in future versions.
   * </pre>
   *
   * <code>.tensorflow.StepStats step_stats = 1;</code>
   */
  org.tensorflow.proto.StepStatsOrBuilder getStepStatsOrBuilder();

  /**
   * <pre>
   * The cost graph for the computation defined by the run call.
   * </pre>
   *
   * <code>.tensorflow.CostGraphDef cost_graph = 2;</code>
   * @return Whether the costGraph field is set.
   */
  boolean hasCostGraph();
  /**
   * <pre>
   * The cost graph for the computation defined by the run call.
   * </pre>
   *
   * <code>.tensorflow.CostGraphDef cost_graph = 2;</code>
   * @return The costGraph.
   */
  org.tensorflow.proto.CostGraphDef getCostGraph();
  /**
   * <pre>
   * The cost graph for the computation defined by the run call.
   * </pre>
   *
   * <code>.tensorflow.CostGraphDef cost_graph = 2;</code>
   */
  org.tensorflow.proto.CostGraphDefOrBuilder getCostGraphOrBuilder();

  /**
   * <pre>
   * Graphs of the partitions executed by executors.
   * </pre>
   *
   * <code>repeated .tensorflow.GraphDef partition_graphs = 3;</code>
   */
  java.util.List<org.tensorflow.proto.GraphDef> 
      getPartitionGraphsList();
  /**
   * <pre>
   * Graphs of the partitions executed by executors.
   * </pre>
   *
   * <code>repeated .tensorflow.GraphDef partition_graphs = 3;</code>
   */
  org.tensorflow.proto.GraphDef getPartitionGraphs(int index);
  /**
   * <pre>
   * Graphs of the partitions executed by executors.
   * </pre>
   *
   * <code>repeated .tensorflow.GraphDef partition_graphs = 3;</code>
   */
  int getPartitionGraphsCount();
  /**
   * <pre>
   * Graphs of the partitions executed by executors.
   * </pre>
   *
   * <code>repeated .tensorflow.GraphDef partition_graphs = 3;</code>
   */
  java.util.List<? extends org.tensorflow.proto.GraphDefOrBuilder> 
      getPartitionGraphsOrBuilderList();
  /**
   * <pre>
   * Graphs of the partitions executed by executors.
   * </pre>
   *
   * <code>repeated .tensorflow.GraphDef partition_graphs = 3;</code>
   */
  org.tensorflow.proto.GraphDefOrBuilder getPartitionGraphsOrBuilder(
      int index);

  /**
   * <pre>
   * This is only populated for graphs that are run as functions in TensorFlow
   * V2. There will be an entry below for each function that is traced.
   * The main use cases of the post_optimization_graph and the partition_graphs
   * is to give the caller insight into the graphs that were actually run by the
   * runtime. Additional information (such as those in step_stats) will match
   * these graphs.
   * We also include the pre_optimization_graph since it is usually easier to
   * read, and is helpful in situations where the caller wants to get a high
   * level idea of what the built graph looks like (since the various graph
   * optimization passes might change the structure of the graph significantly).
   * </pre>
   *
   * <code>repeated .tensorflow.RunMetadata.FunctionGraphs function_graphs = 4;</code>
   */
  java.util.List<org.tensorflow.proto.RunMetadata.FunctionGraphs> 
      getFunctionGraphsList();
  /**
   * <pre>
   * This is only populated for graphs that are run as functions in TensorFlow
   * V2. There will be an entry below for each function that is traced.
   * The main use cases of the post_optimization_graph and the partition_graphs
   * is to give the caller insight into the graphs that were actually run by the
   * runtime. Additional information (such as those in step_stats) will match
   * these graphs.
   * We also include the pre_optimization_graph since it is usually easier to
   * read, and is helpful in situations where the caller wants to get a high
   * level idea of what the built graph looks like (since the various graph
   * optimization passes might change the structure of the graph significantly).
   * </pre>
   *
   * <code>repeated .tensorflow.RunMetadata.FunctionGraphs function_graphs = 4;</code>
   */
  org.tensorflow.proto.RunMetadata.FunctionGraphs getFunctionGraphs(int index);
  /**
   * <pre>
   * This is only populated for graphs that are run as functions in TensorFlow
   * V2. There will be an entry below for each function that is traced.
   * The main use cases of the post_optimization_graph and the partition_graphs
   * is to give the caller insight into the graphs that were actually run by the
   * runtime. Additional information (such as those in step_stats) will match
   * these graphs.
   * We also include the pre_optimization_graph since it is usually easier to
   * read, and is helpful in situations where the caller wants to get a high
   * level idea of what the built graph looks like (since the various graph
   * optimization passes might change the structure of the graph significantly).
   * </pre>
   *
   * <code>repeated .tensorflow.RunMetadata.FunctionGraphs function_graphs = 4;</code>
   */
  int getFunctionGraphsCount();
  /**
   * <pre>
   * This is only populated for graphs that are run as functions in TensorFlow
   * V2. There will be an entry below for each function that is traced.
   * The main use cases of the post_optimization_graph and the partition_graphs
   * is to give the caller insight into the graphs that were actually run by the
   * runtime. Additional information (such as those in step_stats) will match
   * these graphs.
   * We also include the pre_optimization_graph since it is usually easier to
   * read, and is helpful in situations where the caller wants to get a high
   * level idea of what the built graph looks like (since the various graph
   * optimization passes might change the structure of the graph significantly).
   * </pre>
   *
   * <code>repeated .tensorflow.RunMetadata.FunctionGraphs function_graphs = 4;</code>
   */
  java.util.List<? extends org.tensorflow.proto.RunMetadata.FunctionGraphsOrBuilder> 
      getFunctionGraphsOrBuilderList();
  /**
   * <pre>
   * This is only populated for graphs that are run as functions in TensorFlow
   * V2. There will be an entry below for each function that is traced.
   * The main use cases of the post_optimization_graph and the partition_graphs
   * is to give the caller insight into the graphs that were actually run by the
   * runtime. Additional information (such as those in step_stats) will match
   * these graphs.
   * We also include the pre_optimization_graph since it is usually easier to
   * read, and is helpful in situations where the caller wants to get a high
   * level idea of what the built graph looks like (since the various graph
   * optimization passes might change the structure of the graph significantly).
   * </pre>
   *
   * <code>repeated .tensorflow.RunMetadata.FunctionGraphs function_graphs = 4;</code>
   */
  org.tensorflow.proto.RunMetadata.FunctionGraphsOrBuilder getFunctionGraphsOrBuilder(
      int index);

  /**
   * <pre>
   * Metadata about the session.
   * </pre>
   *
   * <code>.tensorflow.SessionMetadata session_metadata = 5;</code>
   * @return Whether the sessionMetadata field is set.
   */
  boolean hasSessionMetadata();
  /**
   * <pre>
   * Metadata about the session.
   * </pre>
   *
   * <code>.tensorflow.SessionMetadata session_metadata = 5;</code>
   * @return The sessionMetadata.
   */
  org.tensorflow.proto.SessionMetadata getSessionMetadata();
  /**
   * <pre>
   * Metadata about the session.
   * </pre>
   *
   * <code>.tensorflow.SessionMetadata session_metadata = 5;</code>
   */
  org.tensorflow.proto.SessionMetadataOrBuilder getSessionMetadataOrBuilder();
}
