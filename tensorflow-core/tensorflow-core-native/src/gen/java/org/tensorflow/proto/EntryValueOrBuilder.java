// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tsl/protobuf/test_log.proto

package org.tensorflow.proto;

public interface EntryValueOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.EntryValue)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>double double_value = 1;</code>
   * @return Whether the doubleValue field is set.
   */
  boolean hasDoubleValue();
  /**
   * <code>double double_value = 1;</code>
   * @return The doubleValue.
   */
  double getDoubleValue();

  /**
   * <code>string string_value = 2;</code>
   * @return Whether the stringValue field is set.
   */
  boolean hasStringValue();
  /**
   * <code>string string_value = 2;</code>
   * @return The stringValue.
   */
  java.lang.String getStringValue();
  /**
   * <code>string string_value = 2;</code>
   * @return The bytes for stringValue.
   */
  com.google.protobuf.ByteString
      getStringValueBytes();

  public org.tensorflow.proto.EntryValue.KindCase getKindCase();
}