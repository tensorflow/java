// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/framework/tensor.proto

package org.tensorflow.proto.framework;

public interface VariantTensorDataProtoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.VariantTensorDataProto)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Name of the type of objects being serialized.
   * </pre>
   *
   * <code>string type_name = 1;</code>
   * @return The typeName.
   */
  java.lang.String getTypeName();
  /**
   * <pre>
   * Name of the type of objects being serialized.
   * </pre>
   *
   * <code>string type_name = 1;</code>
   * @return The bytes for typeName.
   */
  com.google.protobuf.ByteString
      getTypeNameBytes();

  /**
   * <pre>
   * Portions of the object that are not Tensors.
   * </pre>
   *
   * <code>bytes metadata = 2;</code>
   * @return The metadata.
   */
  com.google.protobuf.ByteString getMetadata();

  /**
   * <pre>
   * Tensors contained within objects being serialized.
   * </pre>
   *
   * <code>repeated .tensorflow.TensorProto tensors = 3;</code>
   */
  java.util.List<org.tensorflow.proto.framework.TensorProto> 
      getTensorsList();
  /**
   * <pre>
   * Tensors contained within objects being serialized.
   * </pre>
   *
   * <code>repeated .tensorflow.TensorProto tensors = 3;</code>
   */
  org.tensorflow.proto.framework.TensorProto getTensors(int index);
  /**
   * <pre>
   * Tensors contained within objects being serialized.
   * </pre>
   *
   * <code>repeated .tensorflow.TensorProto tensors = 3;</code>
   */
  int getTensorsCount();
  /**
   * <pre>
   * Tensors contained within objects being serialized.
   * </pre>
   *
   * <code>repeated .tensorflow.TensorProto tensors = 3;</code>
   */
  java.util.List<? extends org.tensorflow.proto.framework.TensorProtoOrBuilder> 
      getTensorsOrBuilderList();
  /**
   * <pre>
   * Tensors contained within objects being serialized.
   * </pre>
   *
   * <code>repeated .tensorflow.TensorProto tensors = 3;</code>
   */
  org.tensorflow.proto.framework.TensorProtoOrBuilder getTensorsOrBuilder(
      int index);
}
