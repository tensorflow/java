// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/example/example.proto

package org.tensorflow.proto;

public final class ExampleProtos {
  private ExampleProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_Example_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_Example_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_SequenceExample_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_SequenceExample_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n%tensorflow/core/example/example.proto\022" +
      "\ntensorflow\032%tensorflow/core/example/fea" +
      "ture.proto\"1\n\007Example\022&\n\010features\030\001 \001(\0132" +
      "\024.tensorflow.Features\"i\n\017SequenceExample" +
      "\022%\n\007context\030\001 \001(\0132\024.tensorflow.Features\022" +
      "/\n\rfeature_lists\030\002 \001(\0132\030.tensorflow.Feat" +
      "ureListsB\177\n\024org.tensorflow.protoB\rExampl" +
      "eProtosP\001ZSgithub.com/tensorflow/tensorf" +
      "low/tensorflow/go/core/example/example_p" +
      "rotos_go_proto\370\001\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          org.tensorflow.proto.FeatureProtos.getDescriptor(),
        });
    internal_static_tensorflow_Example_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_tensorflow_Example_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_Example_descriptor,
        new java.lang.String[] { "Features", });
    internal_static_tensorflow_SequenceExample_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_tensorflow_SequenceExample_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_SequenceExample_descriptor,
        new java.lang.String[] { "Context", "FeatureLists", });
    org.tensorflow.proto.FeatureProtos.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
