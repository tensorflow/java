// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/tsl/protobuf/test_log.proto

package org.tensorflow.proto;

public interface RunConfigurationOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.RunConfiguration)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated string argument = 1;</code>
   * @return A list containing the argument.
   */
  java.util.List<java.lang.String>
      getArgumentList();
  /**
   * <code>repeated string argument = 1;</code>
   * @return The count of argument.
   */
  int getArgumentCount();
  /**
   * <code>repeated string argument = 1;</code>
   * @param index The index of the element to return.
   * @return The argument at the given index.
   */
  java.lang.String getArgument(int index);
  /**
   * <code>repeated string argument = 1;</code>
   * @param index The index of the value to return.
   * @return The bytes of the argument at the given index.
   */
  com.google.protobuf.ByteString
      getArgumentBytes(int index);

  /**
   * <pre>
   * Environment variables used to run the test/benchmark.
   * </pre>
   *
   * <code>map&lt;string, string&gt; env_vars = 2;</code>
   */
  int getEnvVarsCount();
  /**
   * <pre>
   * Environment variables used to run the test/benchmark.
   * </pre>
   *
   * <code>map&lt;string, string&gt; env_vars = 2;</code>
   */
  boolean containsEnvVars(
      java.lang.String key);
  /**
   * Use {@link #getEnvVarsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.String>
  getEnvVars();
  /**
   * <pre>
   * Environment variables used to run the test/benchmark.
   * </pre>
   *
   * <code>map&lt;string, string&gt; env_vars = 2;</code>
   */
  java.util.Map<java.lang.String, java.lang.String>
  getEnvVarsMap();
  /**
   * <pre>
   * Environment variables used to run the test/benchmark.
   * </pre>
   *
   * <code>map&lt;string, string&gt; env_vars = 2;</code>
   */

  /* nullable */
java.lang.String getEnvVarsOrDefault(
      java.lang.String key,
      /* nullable */
java.lang.String defaultValue);
  /**
   * <pre>
   * Environment variables used to run the test/benchmark.
   * </pre>
   *
   * <code>map&lt;string, string&gt; env_vars = 2;</code>
   */

  java.lang.String getEnvVarsOrThrow(
      java.lang.String key);
}
