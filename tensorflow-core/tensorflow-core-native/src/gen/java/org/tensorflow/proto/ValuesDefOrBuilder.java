// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/protobuf/control_flow.proto

package org.tensorflow.proto;

public interface ValuesDefOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.ValuesDef)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Value names that have been seen in this context.
   * </pre>
   *
   * <code>repeated string values = 1;</code>
   * @return A list containing the values.
   */
  java.util.List<java.lang.String>
      getValuesList();
  /**
   * <pre>
   * Value names that have been seen in this context.
   * </pre>
   *
   * <code>repeated string values = 1;</code>
   * @return The count of values.
   */
  int getValuesCount();
  /**
   * <pre>
   * Value names that have been seen in this context.
   * </pre>
   *
   * <code>repeated string values = 1;</code>
   * @param index The index of the element to return.
   * @return The values at the given index.
   */
  java.lang.String getValues(int index);
  /**
   * <pre>
   * Value names that have been seen in this context.
   * </pre>
   *
   * <code>repeated string values = 1;</code>
   * @param index The index of the value to return.
   * @return The bytes of the values at the given index.
   */
  com.google.protobuf.ByteString
      getValuesBytes(int index);

  /**
   * <pre>
   * Value names referenced by but external to this context.
   * </pre>
   *
   * <code>map&lt;string, string&gt; external_values = 2;</code>
   */
  int getExternalValuesCount();
  /**
   * <pre>
   * Value names referenced by but external to this context.
   * </pre>
   *
   * <code>map&lt;string, string&gt; external_values = 2;</code>
   */
  boolean containsExternalValues(
      java.lang.String key);
  /**
   * Use {@link #getExternalValuesMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.String>
  getExternalValues();
  /**
   * <pre>
   * Value names referenced by but external to this context.
   * </pre>
   *
   * <code>map&lt;string, string&gt; external_values = 2;</code>
   */
  java.util.Map<java.lang.String, java.lang.String>
  getExternalValuesMap();
  /**
   * <pre>
   * Value names referenced by but external to this context.
   * </pre>
   *
   * <code>map&lt;string, string&gt; external_values = 2;</code>
   */

  /* nullable */
java.lang.String getExternalValuesOrDefault(
      java.lang.String key,
      /* nullable */
java.lang.String defaultValue);
  /**
   * <pre>
   * Value names referenced by but external to this context.
   * </pre>
   *
   * <code>map&lt;string, string&gt; external_values = 2;</code>
   */

  java.lang.String getExternalValuesOrThrow(
      java.lang.String key);
}
