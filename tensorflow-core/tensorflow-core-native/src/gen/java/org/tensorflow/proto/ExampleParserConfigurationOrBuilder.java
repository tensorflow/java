// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/example/example_parser_configuration.proto

package org.tensorflow.proto;

public interface ExampleParserConfigurationOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.ExampleParserConfiguration)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>map&lt;string, .tensorflow.FeatureConfiguration&gt; feature_map = 1;</code>
   */
  int getFeatureMapCount();
  /**
   * <code>map&lt;string, .tensorflow.FeatureConfiguration&gt; feature_map = 1;</code>
   */
  boolean containsFeatureMap(
      java.lang.String key);
  /**
   * Use {@link #getFeatureMapMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, org.tensorflow.proto.FeatureConfiguration>
  getFeatureMap();
  /**
   * <code>map&lt;string, .tensorflow.FeatureConfiguration&gt; feature_map = 1;</code>
   */
  java.util.Map<java.lang.String, org.tensorflow.proto.FeatureConfiguration>
  getFeatureMapMap();
  /**
   * <code>map&lt;string, .tensorflow.FeatureConfiguration&gt; feature_map = 1;</code>
   */

  /* nullable */
org.tensorflow.proto.FeatureConfiguration getFeatureMapOrDefault(
      java.lang.String key,
      /* nullable */
org.tensorflow.proto.FeatureConfiguration defaultValue);
  /**
   * <code>map&lt;string, .tensorflow.FeatureConfiguration&gt; feature_map = 1;</code>
   */

  org.tensorflow.proto.FeatureConfiguration getFeatureMapOrThrow(
      java.lang.String key);
}
