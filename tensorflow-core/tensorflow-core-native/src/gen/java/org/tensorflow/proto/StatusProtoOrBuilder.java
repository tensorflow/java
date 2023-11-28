// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tsl/protobuf/status.proto

package org.tensorflow.proto;

public interface StatusProtoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.StatusProto)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Status code as defined in tensorflow/tsl/protobuf/error_codes.proto.
   * </pre>
   *
   * <code>.tensorflow.error.Code code = 1;</code>
   * @return The enum numeric value on the wire for code.
   */
  int getCodeValue();
  /**
   * <pre>
   * Status code as defined in tensorflow/tsl/protobuf/error_codes.proto.
   * </pre>
   *
   * <code>.tensorflow.error.Code code = 1;</code>
   * @return The code.
   */
  org.tensorflow.proto.error.Code getCode();

  /**
   * <pre>
   * Detail error message.
   * </pre>
   *
   * <code>string message = 2;</code>
   * @return The message.
   */
  java.lang.String getMessage();
  /**
   * <pre>
   * Detail error message.
   * </pre>
   *
   * <code>string message = 2;</code>
   * @return The bytes for message.
   */
  com.google.protobuf.ByteString
      getMessageBytes();
}
