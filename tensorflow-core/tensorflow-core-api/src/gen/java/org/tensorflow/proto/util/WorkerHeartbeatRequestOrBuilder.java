// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/util/event.proto

package org.tensorflow.proto.util;

public interface WorkerHeartbeatRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.WorkerHeartbeatRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.tensorflow.WorkerShutdownMode shutdown_mode = 1;</code>
   * @return The enum numeric value on the wire for shutdownMode.
   */
  int getShutdownModeValue();
  /**
   * <code>.tensorflow.WorkerShutdownMode shutdown_mode = 1;</code>
   * @return The shutdownMode.
   */
  org.tensorflow.proto.util.WorkerShutdownMode getShutdownMode();

  /**
   * <code>.tensorflow.WatchdogConfig watchdog_config = 2;</code>
   * @return Whether the watchdogConfig field is set.
   */
  boolean hasWatchdogConfig();
  /**
   * <code>.tensorflow.WatchdogConfig watchdog_config = 2;</code>
   * @return The watchdogConfig.
   */
  org.tensorflow.proto.util.WatchdogConfig getWatchdogConfig();
  /**
   * <code>.tensorflow.WatchdogConfig watchdog_config = 2;</code>
   */
  org.tensorflow.proto.util.WatchdogConfigOrBuilder getWatchdogConfigOrBuilder();

  /**
   * <code>.tensorflow.RequestedExitCode exit_code = 3;</code>
   * @return Whether the exitCode field is set.
   */
  boolean hasExitCode();
  /**
   * <code>.tensorflow.RequestedExitCode exit_code = 3;</code>
   * @return The exitCode.
   */
  org.tensorflow.proto.util.RequestedExitCode getExitCode();
  /**
   * <code>.tensorflow.RequestedExitCode exit_code = 3;</code>
   */
  org.tensorflow.proto.util.RequestedExitCodeOrBuilder getExitCodeOrBuilder();
}
