// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/protobuf/debug.proto

package org.tensorflow.proto;

public interface DebuggedSourceFilesOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tensorflow.DebuggedSourceFiles)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * A collection of source code files.
   * </pre>
   *
   * <code>repeated .tensorflow.DebuggedSourceFile source_files = 1;</code>
   */
  java.util.List<org.tensorflow.proto.DebuggedSourceFile> 
      getSourceFilesList();
  /**
   * <pre>
   * A collection of source code files.
   * </pre>
   *
   * <code>repeated .tensorflow.DebuggedSourceFile source_files = 1;</code>
   */
  org.tensorflow.proto.DebuggedSourceFile getSourceFiles(int index);
  /**
   * <pre>
   * A collection of source code files.
   * </pre>
   *
   * <code>repeated .tensorflow.DebuggedSourceFile source_files = 1;</code>
   */
  int getSourceFilesCount();
  /**
   * <pre>
   * A collection of source code files.
   * </pre>
   *
   * <code>repeated .tensorflow.DebuggedSourceFile source_files = 1;</code>
   */
  java.util.List<? extends org.tensorflow.proto.DebuggedSourceFileOrBuilder> 
      getSourceFilesOrBuilderList();
  /**
   * <pre>
   * A collection of source code files.
   * </pre>
   *
   * <code>repeated .tensorflow.DebuggedSourceFile source_files = 1;</code>
   */
  org.tensorflow.proto.DebuggedSourceFileOrBuilder getSourceFilesOrBuilder(
      int index);
}