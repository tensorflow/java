// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/example/feature.proto

package org.tensorflow.proto.example;

public interface BytesListOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.BytesList)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated bytes value = 1;</code>
   * @return A list containing the value.
   */
  java.util.List<com.google.protobuf.ByteString> getValueList();
  /**
   * <code>repeated bytes value = 1;</code>
   * @return The count of value.
   */
  int getValueCount();
  /**
   * <code>repeated bytes value = 1;</code>
   * @param index The index of the element to return.
   * @return The value at the given index.
   */
  com.google.protobuf.ByteString getValue(int index);
}