// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/framework/full_type.proto

package org.tensorflow.proto.framework;

public interface FullTypeDefOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.FullTypeDef)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * The principal type represented by this object. This may be a concrete type
   * (Tensor, Dataset) a type variable (used for dependent types) a type
   * symbol (Any, Union). See FullTypeId for details.
   * </pre>
   *
   * <code>.tensorflow.FullTypeId type_id = 1;</code>
   * @return The enum numeric value on the wire for typeId.
   */
  int getTypeIdValue();
  /**
   * <pre>
   * The principal type represented by this object. This may be a concrete type
   * (Tensor, Dataset) a type variable (used for dependent types) a type
   * symbol (Any, Union). See FullTypeId for details.
   * </pre>
   *
   * <code>.tensorflow.FullTypeId type_id = 1;</code>
   * @return The typeId.
   */
  org.tensorflow.proto.framework.FullTypeId getTypeId();

  /**
   * <code>repeated .tensorflow.FullTypeDef args = 2;</code>
   */
  java.util.List<org.tensorflow.proto.framework.FullTypeDef> 
      getArgsList();
  /**
   * <code>repeated .tensorflow.FullTypeDef args = 2;</code>
   */
  org.tensorflow.proto.framework.FullTypeDef getArgs(int index);
  /**
   * <code>repeated .tensorflow.FullTypeDef args = 2;</code>
   */
  int getArgsCount();
  /**
   * <code>repeated .tensorflow.FullTypeDef args = 2;</code>
   */
  java.util.List<? extends org.tensorflow.proto.framework.FullTypeDefOrBuilder> 
      getArgsOrBuilderList();
  /**
   * <code>repeated .tensorflow.FullTypeDef args = 2;</code>
   */
  org.tensorflow.proto.framework.FullTypeDefOrBuilder getArgsOrBuilder(
      int index);

  /**
   * <code>string s = 3;</code>
   * @return Whether the s field is set.
   */
  boolean hasS();
  /**
   * <code>string s = 3;</code>
   * @return The s.
   */
  java.lang.String getS();
  /**
   * <code>string s = 3;</code>
   * @return The bytes for s.
   */
  com.google.protobuf.ByteString
      getSBytes();

  /**
   * <pre>
   * TODO(mdan): list/tensor, map? Need to reconcile with TFT_RECORD, etc.
   * </pre>
   *
   * <code>int64 i = 4;</code>
   * @return Whether the i field is set.
   */
  boolean hasI();
  /**
   * <pre>
   * TODO(mdan): list/tensor, map? Need to reconcile with TFT_RECORD, etc.
   * </pre>
   *
   * <code>int64 i = 4;</code>
   * @return The i.
   */
  long getI();

  public org.tensorflow.proto.framework.FullTypeDef.AttrCase getAttrCase();
}
