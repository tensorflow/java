// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/example/feature.proto

package org.tensorflow.proto.example;

public interface Int64ListOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.Int64List)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated int64 value = 1 [packed = true];</code>
   * @return A list containing the value.
   */
  java.util.List<java.lang.Long> getValueList();
  /**
   * <code>repeated int64 value = 1 [packed = true];</code>
   * @return The count of value.
   */
  int getValueCount();
  /**
   * <code>repeated int64 value = 1 [packed = true];</code>
   * @param index The index of the element to return.
   * @return The value at the given index.
   */
  long getValue(int index);
}
