// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: xla/tsl/protobuf/test_log.proto

package org.tensorflow.proto;

public interface CommitIdOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.CommitId)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Submitted changelist.
   * </pre>
   *
   * <code>int64 changelist = 1;</code>
   * @return Whether the changelist field is set.
   */
  boolean hasChangelist();
  /**
   * <pre>
   * Submitted changelist.
   * </pre>
   *
   * <code>int64 changelist = 1;</code>
   * @return The changelist.
   */
  long getChangelist();

  /**
   * <code>string hash = 2;</code>
   * @return Whether the hash field is set.
   */
  boolean hasHash();
  /**
   * <code>string hash = 2;</code>
   * @return The hash.
   */
  java.lang.String getHash();
  /**
   * <code>string hash = 2;</code>
   * @return The bytes for hash.
   */
  com.google.protobuf.ByteString
      getHashBytes();

  /**
   * <pre>
   * Hash of intermediate change between hash/changelist and what was tested.
   * Not used if the build is from a commit without modifications.
   * </pre>
   *
   * <code>string snapshot = 3;</code>
   * @return The snapshot.
   */
  java.lang.String getSnapshot();
  /**
   * <pre>
   * Hash of intermediate change between hash/changelist and what was tested.
   * Not used if the build is from a commit without modifications.
   * </pre>
   *
   * <code>string snapshot = 3;</code>
   * @return The bytes for snapshot.
   */
  com.google.protobuf.ByteString
      getSnapshotBytes();

  /**
   * <pre>
   * Changelist tested if the change list is not already submitted.
   * </pre>
   *
   * <code>int64 pending_changelist = 4;</code>
   * @return The pendingChangelist.
   */
  long getPendingChangelist();

  public org.tensorflow.proto.CommitId.KindCase getKindCase();
}
