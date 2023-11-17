// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/framework/kernel_def.proto

package org.tensorflow.proto;

/**
 * <pre>
 * A collection of KernelDefs
 * </pre>
 *
 * Protobuf type {@code tensorflow.KernelList}
 */
public final class KernelList extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tensorflow.KernelList)
    KernelListOrBuilder {
private static final long serialVersionUID = 0L;
  // Use KernelList.newBuilder() to construct.
  private KernelList(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private KernelList() {
    kernel_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new KernelList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.tensorflow.proto.KernelDefProtos.internal_static_tensorflow_KernelList_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.tensorflow.proto.KernelDefProtos.internal_static_tensorflow_KernelList_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.tensorflow.proto.KernelList.class, org.tensorflow.proto.KernelList.Builder.class);
  }

  public static final int KERNEL_FIELD_NUMBER = 1;
  private java.util.List<org.tensorflow.proto.KernelDef> kernel_;
  /**
   * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
   */
  @java.lang.Override
  public java.util.List<org.tensorflow.proto.KernelDef> getKernelList() {
    return kernel_;
  }
  /**
   * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
   */
  @java.lang.Override
  public java.util.List<? extends org.tensorflow.proto.KernelDefOrBuilder> 
      getKernelOrBuilderList() {
    return kernel_;
  }
  /**
   * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
   */
  @java.lang.Override
  public int getKernelCount() {
    return kernel_.size();
  }
  /**
   * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
   */
  @java.lang.Override
  public org.tensorflow.proto.KernelDef getKernel(int index) {
    return kernel_.get(index);
  }
  /**
   * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
   */
  @java.lang.Override
  public org.tensorflow.proto.KernelDefOrBuilder getKernelOrBuilder(
      int index) {
    return kernel_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < kernel_.size(); i++) {
      output.writeMessage(1, kernel_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < kernel_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, kernel_.get(i));
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.tensorflow.proto.KernelList)) {
      return super.equals(obj);
    }
    org.tensorflow.proto.KernelList other = (org.tensorflow.proto.KernelList) obj;

    if (!getKernelList()
        .equals(other.getKernelList())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getKernelCount() > 0) {
      hash = (37 * hash) + KERNEL_FIELD_NUMBER;
      hash = (53 * hash) + getKernelList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.tensorflow.proto.KernelList parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.KernelList parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.KernelList parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.KernelList parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.KernelList parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.KernelList parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.KernelList parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.KernelList parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.KernelList parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.KernelList parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.KernelList parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.KernelList parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.tensorflow.proto.KernelList prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * A collection of KernelDefs
   * </pre>
   *
   * Protobuf type {@code tensorflow.KernelList}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tensorflow.KernelList)
      org.tensorflow.proto.KernelListOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.tensorflow.proto.KernelDefProtos.internal_static_tensorflow_KernelList_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.tensorflow.proto.KernelDefProtos.internal_static_tensorflow_KernelList_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.tensorflow.proto.KernelList.class, org.tensorflow.proto.KernelList.Builder.class);
    }

    // Construct using org.tensorflow.proto.KernelList.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (kernelBuilder_ == null) {
        kernel_ = java.util.Collections.emptyList();
      } else {
        kernel_ = null;
        kernelBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.tensorflow.proto.KernelDefProtos.internal_static_tensorflow_KernelList_descriptor;
    }

    @java.lang.Override
    public org.tensorflow.proto.KernelList getDefaultInstanceForType() {
      return org.tensorflow.proto.KernelList.getDefaultInstance();
    }

    @java.lang.Override
    public org.tensorflow.proto.KernelList build() {
      org.tensorflow.proto.KernelList result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.tensorflow.proto.KernelList buildPartial() {
      org.tensorflow.proto.KernelList result = new org.tensorflow.proto.KernelList(this);
      int from_bitField0_ = bitField0_;
      if (kernelBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          kernel_ = java.util.Collections.unmodifiableList(kernel_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.kernel_ = kernel_;
      } else {
        result.kernel_ = kernelBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.tensorflow.proto.KernelList) {
        return mergeFrom((org.tensorflow.proto.KernelList)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.tensorflow.proto.KernelList other) {
      if (other == org.tensorflow.proto.KernelList.getDefaultInstance()) return this;
      if (kernelBuilder_ == null) {
        if (!other.kernel_.isEmpty()) {
          if (kernel_.isEmpty()) {
            kernel_ = other.kernel_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureKernelIsMutable();
            kernel_.addAll(other.kernel_);
          }
          onChanged();
        }
      } else {
        if (!other.kernel_.isEmpty()) {
          if (kernelBuilder_.isEmpty()) {
            kernelBuilder_.dispose();
            kernelBuilder_ = null;
            kernel_ = other.kernel_;
            bitField0_ = (bitField0_ & ~0x00000001);
            kernelBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getKernelFieldBuilder() : null;
          } else {
            kernelBuilder_.addAllMessages(other.kernel_);
          }
        }
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              org.tensorflow.proto.KernelDef m =
                  input.readMessage(
                      org.tensorflow.proto.KernelDef.parser(),
                      extensionRegistry);
              if (kernelBuilder_ == null) {
                ensureKernelIsMutable();
                kernel_.add(m);
              } else {
                kernelBuilder_.addMessage(m);
              }
              break;
            } // case 10
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.util.List<org.tensorflow.proto.KernelDef> kernel_ =
      java.util.Collections.emptyList();
    private void ensureKernelIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        kernel_ = new java.util.ArrayList<org.tensorflow.proto.KernelDef>(kernel_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        org.tensorflow.proto.KernelDef, org.tensorflow.proto.KernelDef.Builder, org.tensorflow.proto.KernelDefOrBuilder> kernelBuilder_;

    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public java.util.List<org.tensorflow.proto.KernelDef> getKernelList() {
      if (kernelBuilder_ == null) {
        return java.util.Collections.unmodifiableList(kernel_);
      } else {
        return kernelBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public int getKernelCount() {
      if (kernelBuilder_ == null) {
        return kernel_.size();
      } else {
        return kernelBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public org.tensorflow.proto.KernelDef getKernel(int index) {
      if (kernelBuilder_ == null) {
        return kernel_.get(index);
      } else {
        return kernelBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public Builder setKernel(
        int index, org.tensorflow.proto.KernelDef value) {
      if (kernelBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureKernelIsMutable();
        kernel_.set(index, value);
        onChanged();
      } else {
        kernelBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public Builder setKernel(
        int index, org.tensorflow.proto.KernelDef.Builder builderForValue) {
      if (kernelBuilder_ == null) {
        ensureKernelIsMutable();
        kernel_.set(index, builderForValue.build());
        onChanged();
      } else {
        kernelBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public Builder addKernel(org.tensorflow.proto.KernelDef value) {
      if (kernelBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureKernelIsMutable();
        kernel_.add(value);
        onChanged();
      } else {
        kernelBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public Builder addKernel(
        int index, org.tensorflow.proto.KernelDef value) {
      if (kernelBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureKernelIsMutable();
        kernel_.add(index, value);
        onChanged();
      } else {
        kernelBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public Builder addKernel(
        org.tensorflow.proto.KernelDef.Builder builderForValue) {
      if (kernelBuilder_ == null) {
        ensureKernelIsMutable();
        kernel_.add(builderForValue.build());
        onChanged();
      } else {
        kernelBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public Builder addKernel(
        int index, org.tensorflow.proto.KernelDef.Builder builderForValue) {
      if (kernelBuilder_ == null) {
        ensureKernelIsMutable();
        kernel_.add(index, builderForValue.build());
        onChanged();
      } else {
        kernelBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public Builder addAllKernel(
        java.lang.Iterable<? extends org.tensorflow.proto.KernelDef> values) {
      if (kernelBuilder_ == null) {
        ensureKernelIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, kernel_);
        onChanged();
      } else {
        kernelBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public Builder clearKernel() {
      if (kernelBuilder_ == null) {
        kernel_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        kernelBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public Builder removeKernel(int index) {
      if (kernelBuilder_ == null) {
        ensureKernelIsMutable();
        kernel_.remove(index);
        onChanged();
      } else {
        kernelBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public org.tensorflow.proto.KernelDef.Builder getKernelBuilder(
        int index) {
      return getKernelFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public org.tensorflow.proto.KernelDefOrBuilder getKernelOrBuilder(
        int index) {
      if (kernelBuilder_ == null) {
        return kernel_.get(index);  } else {
        return kernelBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public java.util.List<? extends org.tensorflow.proto.KernelDefOrBuilder> 
         getKernelOrBuilderList() {
      if (kernelBuilder_ != null) {
        return kernelBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(kernel_);
      }
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public org.tensorflow.proto.KernelDef.Builder addKernelBuilder() {
      return getKernelFieldBuilder().addBuilder(
          org.tensorflow.proto.KernelDef.getDefaultInstance());
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public org.tensorflow.proto.KernelDef.Builder addKernelBuilder(
        int index) {
      return getKernelFieldBuilder().addBuilder(
          index, org.tensorflow.proto.KernelDef.getDefaultInstance());
    }
    /**
     * <code>repeated .tensorflow.KernelDef kernel = 1;</code>
     */
    public java.util.List<org.tensorflow.proto.KernelDef.Builder> 
         getKernelBuilderList() {
      return getKernelFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        org.tensorflow.proto.KernelDef, org.tensorflow.proto.KernelDef.Builder, org.tensorflow.proto.KernelDefOrBuilder> 
        getKernelFieldBuilder() {
      if (kernelBuilder_ == null) {
        kernelBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            org.tensorflow.proto.KernelDef, org.tensorflow.proto.KernelDef.Builder, org.tensorflow.proto.KernelDefOrBuilder>(
                kernel_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        kernel_ = null;
      }
      return kernelBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:tensorflow.KernelList)
  }

  // @@protoc_insertion_point(class_scope:tensorflow.KernelList)
  private static final org.tensorflow.proto.KernelList DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.tensorflow.proto.KernelList();
  }

  public static org.tensorflow.proto.KernelList getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<KernelList>
      PARSER = new com.google.protobuf.AbstractParser<KernelList>() {
    @java.lang.Override
    public KernelList parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<KernelList> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<KernelList> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.tensorflow.proto.KernelList getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

