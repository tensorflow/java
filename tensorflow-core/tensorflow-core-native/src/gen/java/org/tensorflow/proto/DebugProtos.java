// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/protobuf/debug.proto

package org.tensorflow.proto;

public final class DebugProtos {
  private DebugProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_DebugTensorWatch_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_DebugTensorWatch_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_DebugOptions_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_DebugOptions_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_DebuggedSourceFile_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_DebuggedSourceFile_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tensorflow_DebuggedSourceFiles_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tensorflow_DebuggedSourceFiles_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n$tensorflow/core/protobuf/debug.proto\022\n" +
      "tensorflow\"\216\001\n\020DebugTensorWatch\022\021\n\tnode_" +
      "name\030\001 \001(\t\022\023\n\013output_slot\030\002 \001(\005\022\021\n\tdebug" +
      "_ops\030\003 \003(\t\022\022\n\ndebug_urls\030\004 \003(\t\022+\n#tolera" +
      "te_debug_op_creation_failures\030\005 \001(\010\"\201\001\n\014" +
      "DebugOptions\022=\n\027debug_tensor_watch_opts\030" +
      "\004 \003(\0132\034.tensorflow.DebugTensorWatch\022\023\n\013g" +
      "lobal_step\030\n \001(\003\022\035\n\025reset_disk_byte_usag" +
      "e\030\013 \001(\010\"j\n\022DebuggedSourceFile\022\014\n\004host\030\001 " +
      "\001(\t\022\021\n\tfile_path\030\002 \001(\t\022\025\n\rlast_modified\030" +
      "\003 \001(\003\022\r\n\005bytes\030\004 \001(\003\022\r\n\005lines\030\005 \003(\t\"K\n\023D" +
      "ebuggedSourceFiles\0224\n\014source_files\030\001 \003(\013" +
      "2\036.tensorflow.DebuggedSourceFileB\177\n\024org." +
      "tensorflow.protoB\013DebugProtosP\001ZUgithub." +
      "com/tensorflow/tensorflow/tensorflow/go/" +
      "core/protobuf/for_core_protos_go_proto\370\001" +
      "\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_tensorflow_DebugTensorWatch_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_tensorflow_DebugTensorWatch_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_DebugTensorWatch_descriptor,
        new java.lang.String[] { "NodeName", "OutputSlot", "DebugOps", "DebugUrls", "TolerateDebugOpCreationFailures", });
    internal_static_tensorflow_DebugOptions_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_tensorflow_DebugOptions_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_DebugOptions_descriptor,
        new java.lang.String[] { "DebugTensorWatchOpts", "GlobalStep", "ResetDiskByteUsage", });
    internal_static_tensorflow_DebuggedSourceFile_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_tensorflow_DebuggedSourceFile_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_DebuggedSourceFile_descriptor,
        new java.lang.String[] { "Host", "FilePath", "LastModified", "Bytes", "Lines", });
    internal_static_tensorflow_DebuggedSourceFiles_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_tensorflow_DebuggedSourceFiles_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tensorflow_DebuggedSourceFiles_descriptor,
        new java.lang.String[] { "SourceFiles", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
