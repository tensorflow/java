// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/tsl/protobuf/test_log.proto

package org.tensorflow.proto;

public interface CPUInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.CPUInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 num_cores = 1;</code>
   * @return The numCores.
   */
  long getNumCores();

  /**
   * <code>int64 num_cores_allowed = 2;</code>
   * @return The numCoresAllowed.
   */
  long getNumCoresAllowed();

  /**
   * <pre>
   * How fast are these cpus?
   * </pre>
   *
   * <code>double mhz_per_cpu = 3;</code>
   * @return The mhzPerCpu.
   */
  double getMhzPerCpu();

  /**
   * <pre>
   * Additional cpu information. For example,
   * Intel Ivybridge with HyperThreading (24 cores) dL1:32KB dL2:256KB dL3:30MB
   * </pre>
   *
   * <code>string cpu_info = 4;</code>
   * @return The cpuInfo.
   */
  java.lang.String getCpuInfo();
  /**
   * <pre>
   * Additional cpu information. For example,
   * Intel Ivybridge with HyperThreading (24 cores) dL1:32KB dL2:256KB dL3:30MB
   * </pre>
   *
   * <code>string cpu_info = 4;</code>
   * @return The bytes for cpuInfo.
   */
  com.google.protobuf.ByteString
      getCpuInfoBytes();

  /**
   * <pre>
   * What kind of cpu scaling is enabled on the host.
   * Examples include "performance", "ondemand", "conservative", "mixed".
   * </pre>
   *
   * <code>string cpu_governor = 5;</code>
   * @return The cpuGovernor.
   */
  java.lang.String getCpuGovernor();
  /**
   * <pre>
   * What kind of cpu scaling is enabled on the host.
   * Examples include "performance", "ondemand", "conservative", "mixed".
   * </pre>
   *
   * <code>string cpu_governor = 5;</code>
   * @return The bytes for cpuGovernor.
   */
  com.google.protobuf.ByteString
      getCpuGovernorBytes();

  /**
   * <pre>
   * Cache sizes (in bytes), e.g. "L2": 262144 (for 256KB)
   * </pre>
   *
   * <code>map&lt;string, int64&gt; cache_size = 6;</code>
   */
  int getCacheSizeCount();
  /**
   * <pre>
   * Cache sizes (in bytes), e.g. "L2": 262144 (for 256KB)
   * </pre>
   *
   * <code>map&lt;string, int64&gt; cache_size = 6;</code>
   */
  boolean containsCacheSize(
      java.lang.String key);
  /**
   * Use {@link #getCacheSizeMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.Long>
  getCacheSize();
  /**
   * <pre>
   * Cache sizes (in bytes), e.g. "L2": 262144 (for 256KB)
   * </pre>
   *
   * <code>map&lt;string, int64&gt; cache_size = 6;</code>
   */
  java.util.Map<java.lang.String, java.lang.Long>
  getCacheSizeMap();
  /**
   * <pre>
   * Cache sizes (in bytes), e.g. "L2": 262144 (for 256KB)
   * </pre>
   *
   * <code>map&lt;string, int64&gt; cache_size = 6;</code>
   */

  long getCacheSizeOrDefault(
      java.lang.String key,
      long defaultValue);
  /**
   * <pre>
   * Cache sizes (in bytes), e.g. "L2": 262144 (for 256KB)
   * </pre>
   *
   * <code>map&lt;string, int64&gt; cache_size = 6;</code>
   */

  long getCacheSizeOrThrow(
      java.lang.String key);
}
