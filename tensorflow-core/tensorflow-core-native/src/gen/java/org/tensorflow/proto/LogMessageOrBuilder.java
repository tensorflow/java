// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/util/event.proto

package org.tensorflow.proto;

@java.lang.Deprecated public interface LogMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.LogMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.tensorflow.LogMessage.Level level = 1;</code>
   * @return The enum numeric value on the wire for level.
   */
  int getLevelValue();
  /**
   * <code>.tensorflow.LogMessage.Level level = 1;</code>
   * @return The level.
   */
  org.tensorflow.proto.LogMessage.Level getLevel();

  /**
   * <code>string message = 2;</code>
   * @return The message.
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 2;</code>
   * @return The bytes for message.
   */
  com.google.protobuf.ByteString
      getMessageBytes();
}
