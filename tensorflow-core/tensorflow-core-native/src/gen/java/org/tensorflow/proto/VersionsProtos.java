// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/framework/versions.proto

package org.tensorflow.proto;

public final class VersionsProtos {
  private VersionsProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_VersionDef_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_VersionDef_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n(tensorflow/core/framework/versions.pro" +
      "to\022\ntensorflow\"K\n\nVersionDef\022\020\n\010producer" +
      "\030\001 \001(\005\022\024\n\014min_consumer\030\002 \001(\005\022\025\n\rbad_cons" +
      "umers\030\003 \003(\005B|\n\024org.tensorflow.protoB\016Ver" +
      "sionsProtosP\001ZOgithub.com/tensorflow/ten" +
      "sorflow/tensorflow/go/core/framework/ver" +
      "sions_go_proto\370\001\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_tensorflow_VersionDef_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_tensorflow_VersionDef_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_VersionDef_descriptor,
        new java.lang.String[] { "Producer", "MinConsumer", "BadConsumers", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
