// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/tsl/protobuf/test_log.proto

package org.tensorflow.proto;

public interface TestResultsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.TestResults)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * The target of the run, e.g.:
   *  //tensorflow/core:kernels_adjust_contrast_op_benchmark_test
   * </pre>
   *
   * <code>string target = 1;</code>
   * @return The target.
   */
  java.lang.String getTarget();
  /**
   * <pre>
   * The target of the run, e.g.:
   *  //tensorflow/core:kernels_adjust_contrast_op_benchmark_test
   * </pre>
   *
   * <code>string target = 1;</code>
   * @return The bytes for target.
   */
  com.google.protobuf.ByteString
      getTargetBytes();

  /**
   * <pre>
   * The list of tests or benchmarks in this run.
   * </pre>
   *
   * <code>.tensorflow.BenchmarkEntries entries = 2;</code>
   * @return Whether the entries field is set.
   */
  boolean hasEntries();
  /**
   * <pre>
   * The list of tests or benchmarks in this run.
   * </pre>
   *
   * <code>.tensorflow.BenchmarkEntries entries = 2;</code>
   * @return The entries.
   */
  org.tensorflow.proto.BenchmarkEntries getEntries();
  /**
   * <pre>
   * The list of tests or benchmarks in this run.
   * </pre>
   *
   * <code>.tensorflow.BenchmarkEntries entries = 2;</code>
   */
  org.tensorflow.proto.BenchmarkEntriesOrBuilder getEntriesOrBuilder();

  /**
   * <pre>
   * The configuration of the build (compiled opt? with cuda? any copts?)
   * </pre>
   *
   * <code>.tensorflow.BuildConfiguration build_configuration = 3;</code>
   * @return Whether the buildConfiguration field is set.
   */
  boolean hasBuildConfiguration();
  /**
   * <pre>
   * The configuration of the build (compiled opt? with cuda? any copts?)
   * </pre>
   *
   * <code>.tensorflow.BuildConfiguration build_configuration = 3;</code>
   * @return The buildConfiguration.
   */
  org.tensorflow.proto.BuildConfiguration getBuildConfiguration();
  /**
   * <pre>
   * The configuration of the build (compiled opt? with cuda? any copts?)
   * </pre>
   *
   * <code>.tensorflow.BuildConfiguration build_configuration = 3;</code>
   */
  org.tensorflow.proto.BuildConfigurationOrBuilder getBuildConfigurationOrBuilder();

  /**
   * <pre>
   * The commit id (git hash or changelist)
   * </pre>
   *
   * <code>.tensorflow.CommitId commit_id = 4;</code>
   * @return Whether the commitId field is set.
   */
  boolean hasCommitId();
  /**
   * <pre>
   * The commit id (git hash or changelist)
   * </pre>
   *
   * <code>.tensorflow.CommitId commit_id = 4;</code>
   * @return The commitId.
   */
  org.tensorflow.proto.CommitId getCommitId();
  /**
   * <pre>
   * The commit id (git hash or changelist)
   * </pre>
   *
   * <code>.tensorflow.CommitId commit_id = 4;</code>
   */
  org.tensorflow.proto.CommitIdOrBuilder getCommitIdOrBuilder();

  /**
   * <pre>
   * The time the run started (in seconds of UTC time since Unix epoch)
   * </pre>
   *
   * <code>int64 start_time = 5;</code>
   * @return The startTime.
   */
  long getStartTime();

  /**
   * <pre>
   * The amount of time the total run took (wall time in seconds)
   * </pre>
   *
   * <code>double run_time = 6;</code>
   * @return The runTime.
   */
  double getRunTime();

  /**
   * <pre>
   * Machine-specific parameters (Platform and CPU info)
   * </pre>
   *
   * <code>.tensorflow.MachineConfiguration machine_configuration = 7;</code>
   * @return Whether the machineConfiguration field is set.
   */
  boolean hasMachineConfiguration();
  /**
   * <pre>
   * Machine-specific parameters (Platform and CPU info)
   * </pre>
   *
   * <code>.tensorflow.MachineConfiguration machine_configuration = 7;</code>
   * @return The machineConfiguration.
   */
  org.tensorflow.proto.MachineConfiguration getMachineConfiguration();
  /**
   * <pre>
   * Machine-specific parameters (Platform and CPU info)
   * </pre>
   *
   * <code>.tensorflow.MachineConfiguration machine_configuration = 7;</code>
   */
  org.tensorflow.proto.MachineConfigurationOrBuilder getMachineConfigurationOrBuilder();

  /**
   * <pre>
   * Run-specific parameters (arguments, etc)
   * </pre>
   *
   * <code>.tensorflow.RunConfiguration run_configuration = 8;</code>
   * @return Whether the runConfiguration field is set.
   */
  boolean hasRunConfiguration();
  /**
   * <pre>
   * Run-specific parameters (arguments, etc)
   * </pre>
   *
   * <code>.tensorflow.RunConfiguration run_configuration = 8;</code>
   * @return The runConfiguration.
   */
  org.tensorflow.proto.RunConfiguration getRunConfiguration();
  /**
   * <pre>
   * Run-specific parameters (arguments, etc)
   * </pre>
   *
   * <code>.tensorflow.RunConfiguration run_configuration = 8;</code>
   */
  org.tensorflow.proto.RunConfigurationOrBuilder getRunConfigurationOrBuilder();

  /**
   * <pre>
   * Benchmark target identifier.
   * </pre>
   *
   * <code>string name = 9;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <pre>
   * Benchmark target identifier.
   * </pre>
   *
   * <code>string name = 9;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>.tensorflow.TestResults.BenchmarkType benchmark_type = 10;</code>
   * @return The enum numeric value on the wire for benchmarkType.
   */
  int getBenchmarkTypeValue();
  /**
   * <code>.tensorflow.TestResults.BenchmarkType benchmark_type = 10;</code>
   * @return The benchmarkType.
   */
  org.tensorflow.proto.TestResults.BenchmarkType getBenchmarkType();

  /**
   * <pre>
   * Used for differentiating between continuous and debug builds.
   * Must be one of:
   * * cbuild: results from continuous build.
   * * presubmit: results from oneshot requests.
   * * culprit: results from culprit finder rerun.
   * </pre>
   *
   * <code>string run_mode = 11;</code>
   * @return The runMode.
   */
  java.lang.String getRunMode();
  /**
   * <pre>
   * Used for differentiating between continuous and debug builds.
   * Must be one of:
   * * cbuild: results from continuous build.
   * * presubmit: results from oneshot requests.
   * * culprit: results from culprit finder rerun.
   * </pre>
   *
   * <code>string run_mode = 11;</code>
   * @return The bytes for runMode.
   */
  com.google.protobuf.ByteString
      getRunModeBytes();

  /**
   * <pre>
   * TensorFlow version this benchmark runs against.
   * This can be either set to full version or just the major version.
   * </pre>
   *
   * <code>string tf_version = 12;</code>
   * @return The tfVersion.
   */
  java.lang.String getTfVersion();
  /**
   * <pre>
   * TensorFlow version this benchmark runs against.
   * This can be either set to full version or just the major version.
   * </pre>
   *
   * <code>string tf_version = 12;</code>
   * @return The bytes for tfVersion.
   */
  com.google.protobuf.ByteString
      getTfVersionBytes();
}
