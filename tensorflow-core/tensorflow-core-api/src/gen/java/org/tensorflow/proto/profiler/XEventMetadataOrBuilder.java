// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/profiler/protobuf/xplane.proto

package org.tensorflow.proto.profiler;

public interface XEventMetadataOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.profiler.XEventMetadata)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * XPlane.event_metadata map key.
   * </pre>
   *
   * <code>int64 id = 1;</code>
   */
  long getId();

  /**
   * <pre>
   * Name of the event.
   * </pre>
   *
   * <code>string name = 2;</code>
   */
  java.lang.String getName();
  /**
   * <pre>
   * Name of the event.
   * </pre>
   *
   * <code>string name = 2;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   * Name of the event shown in trace viewer.
   * </pre>
   *
   * <code>string display_name = 4;</code>
   */
  java.lang.String getDisplayName();
  /**
   * <pre>
   * Name of the event shown in trace viewer.
   * </pre>
   *
   * <code>string display_name = 4;</code>
   */
  com.google.protobuf.ByteString
      getDisplayNameBytes();

  /**
   * <pre>
   * Additional metadata in serialized format.
   * </pre>
   *
   * <code>bytes metadata = 3;</code>
   */
  com.google.protobuf.ByteString getMetadata();

  /**
   * <pre>
   * XStats that are constant for all XEvents with the same metadata_id.
   * Each of these XStats should have a different metadata_id.
   * </pre>
   *
   * <code>repeated .tensorflow.profiler.XStat stats = 5;</code>
   */
  java.util.List<org.tensorflow.proto.profiler.XStat> 
      getStatsList();
  /**
   * <pre>
   * XStats that are constant for all XEvents with the same metadata_id.
   * Each of these XStats should have a different metadata_id.
   * </pre>
   *
   * <code>repeated .tensorflow.profiler.XStat stats = 5;</code>
   */
  org.tensorflow.proto.profiler.XStat getStats(int index);
  /**
   * <pre>
   * XStats that are constant for all XEvents with the same metadata_id.
   * Each of these XStats should have a different metadata_id.
   * </pre>
   *
   * <code>repeated .tensorflow.profiler.XStat stats = 5;</code>
   */
  int getStatsCount();
  /**
   * <pre>
   * XStats that are constant for all XEvents with the same metadata_id.
   * Each of these XStats should have a different metadata_id.
   * </pre>
   *
   * <code>repeated .tensorflow.profiler.XStat stats = 5;</code>
   */
  java.util.List<? extends org.tensorflow.proto.profiler.XStatOrBuilder> 
      getStatsOrBuilderList();
  /**
   * <pre>
   * XStats that are constant for all XEvents with the same metadata_id.
   * Each of these XStats should have a different metadata_id.
   * </pre>
   *
   * <code>repeated .tensorflow.profiler.XStat stats = 5;</code>
   */
  org.tensorflow.proto.profiler.XStatOrBuilder getStatsOrBuilder(
      int index);

  /**
   * <pre>
   * XPlane.event_metadata map key for children events.
   * </pre>
   *
   * <code>repeated int64 child_id = 6;</code>
   */
  java.util.List<java.lang.Long> getChildIdList();
  /**
   * <pre>
   * XPlane.event_metadata map key for children events.
   * </pre>
   *
   * <code>repeated int64 child_id = 6;</code>
   */
  int getChildIdCount();
  /**
   * <pre>
   * XPlane.event_metadata map key for children events.
   * </pre>
   *
   * <code>repeated int64 child_id = 6;</code>
   */
  long getChildId(int index);
}
