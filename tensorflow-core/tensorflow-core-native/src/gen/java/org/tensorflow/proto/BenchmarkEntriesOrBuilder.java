// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/tsl/protobuf/test_log.proto

package org.tensorflow.proto;

public interface BenchmarkEntriesOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.BenchmarkEntries)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .tensorflow.BenchmarkEntry entry = 1;</code>
   */
  java.util.List<org.tensorflow.proto.BenchmarkEntry> 
      getEntryList();
  /**
   * <code>repeated .tensorflow.BenchmarkEntry entry = 1;</code>
   */
  org.tensorflow.proto.BenchmarkEntry getEntry(int index);
  /**
   * <code>repeated .tensorflow.BenchmarkEntry entry = 1;</code>
   */
  int getEntryCount();
  /**
   * <code>repeated .tensorflow.BenchmarkEntry entry = 1;</code>
   */
  java.util.List<? extends org.tensorflow.proto.BenchmarkEntryOrBuilder> 
      getEntryOrBuilderList();
  /**
   * <code>repeated .tensorflow.BenchmarkEntry entry = 1;</code>
   */
  org.tensorflow.proto.BenchmarkEntryOrBuilder getEntryOrBuilder(
      int index);
}
