// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/protobuf/device_filters.proto

package org.tensorflow.proto.distruntime;

public interface ClusterDeviceFiltersOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.ClusterDeviceFilters)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .tensorflow.JobDeviceFilters jobs = 1;</code>
   */
  java.util.List<org.tensorflow.proto.distruntime.JobDeviceFilters> 
      getJobsList();
  /**
   * <code>repeated .tensorflow.JobDeviceFilters jobs = 1;</code>
   */
  org.tensorflow.proto.distruntime.JobDeviceFilters getJobs(int index);
  /**
   * <code>repeated .tensorflow.JobDeviceFilters jobs = 1;</code>
   */
  int getJobsCount();
  /**
   * <code>repeated .tensorflow.JobDeviceFilters jobs = 1;</code>
   */
  java.util.List<? extends org.tensorflow.proto.distruntime.JobDeviceFiltersOrBuilder> 
      getJobsOrBuilderList();
  /**
   * <code>repeated .tensorflow.JobDeviceFilters jobs = 1;</code>
   */
  org.tensorflow.proto.distruntime.JobDeviceFiltersOrBuilder getJobsOrBuilder(
      int index);
}
