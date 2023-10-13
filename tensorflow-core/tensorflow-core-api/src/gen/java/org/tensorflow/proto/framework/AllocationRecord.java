// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/framework/step_stats.proto

package org.tensorflow.proto.framework;

/**
 * <pre>
 * An allocation/de-allocation operation performed by the allocator.
 * </pre>
 *
 * Protobuf type {@code tensorflow.AllocationRecord}
 */
public final class AllocationRecord extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tensorflow.AllocationRecord)
    AllocationRecordOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AllocationRecord.newBuilder() to construct.
  private AllocationRecord(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllocationRecord() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new AllocationRecord();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.tensorflow.proto.framework.StepStatsProtos.internal_static_tensorflow_AllocationRecord_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.tensorflow.proto.framework.StepStatsProtos.internal_static_tensorflow_AllocationRecord_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.tensorflow.proto.framework.AllocationRecord.class, org.tensorflow.proto.framework.AllocationRecord.Builder.class);
  }

  public static final int ALLOC_MICROS_FIELD_NUMBER = 1;
  private long allocMicros_;
  /**
   * <pre>
   * The timestamp of the operation.
   * </pre>
   *
   * <code>int64 alloc_micros = 1;</code>
   * @return The allocMicros.
   */
  @java.lang.Override
  public long getAllocMicros() {
    return allocMicros_;
  }

  public static final int ALLOC_BYTES_FIELD_NUMBER = 2;
  private long allocBytes_;
  /**
   * <pre>
   * Number of bytes allocated, or de-allocated if negative.
   * </pre>
   *
   * <code>int64 alloc_bytes = 2;</code>
   * @return The allocBytes.
   */
  @java.lang.Override
  public long getAllocBytes() {
    return allocBytes_;
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
    if (allocMicros_ != 0L) {
      output.writeInt64(1, allocMicros_);
    }
    if (allocBytes_ != 0L) {
      output.writeInt64(2, allocBytes_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (allocMicros_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, allocMicros_);
    }
    if (allocBytes_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, allocBytes_);
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
    if (!(obj instanceof org.tensorflow.proto.framework.AllocationRecord)) {
      return super.equals(obj);
    }
    org.tensorflow.proto.framework.AllocationRecord other = (org.tensorflow.proto.framework.AllocationRecord) obj;

    if (getAllocMicros()
        != other.getAllocMicros()) return false;
    if (getAllocBytes()
        != other.getAllocBytes()) return false;
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
    hash = (37 * hash) + ALLOC_MICROS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAllocMicros());
    hash = (37 * hash) + ALLOC_BYTES_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAllocBytes());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.tensorflow.proto.framework.AllocationRecord parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.framework.AllocationRecord parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.framework.AllocationRecord parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.framework.AllocationRecord parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.framework.AllocationRecord parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.framework.AllocationRecord parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.framework.AllocationRecord parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.framework.AllocationRecord parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.framework.AllocationRecord parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.framework.AllocationRecord parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.framework.AllocationRecord parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.framework.AllocationRecord parseFrom(
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
  public static Builder newBuilder(org.tensorflow.proto.framework.AllocationRecord prototype) {
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
   * An allocation/de-allocation operation performed by the allocator.
   * </pre>
   *
   * Protobuf type {@code tensorflow.AllocationRecord}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tensorflow.AllocationRecord)
      org.tensorflow.proto.framework.AllocationRecordOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.tensorflow.proto.framework.StepStatsProtos.internal_static_tensorflow_AllocationRecord_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.tensorflow.proto.framework.StepStatsProtos.internal_static_tensorflow_AllocationRecord_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.tensorflow.proto.framework.AllocationRecord.class, org.tensorflow.proto.framework.AllocationRecord.Builder.class);
    }

    // Construct using org.tensorflow.proto.framework.AllocationRecord.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      allocMicros_ = 0L;

      allocBytes_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.tensorflow.proto.framework.StepStatsProtos.internal_static_tensorflow_AllocationRecord_descriptor;
    }

    @java.lang.Override
    public org.tensorflow.proto.framework.AllocationRecord getDefaultInstanceForType() {
      return org.tensorflow.proto.framework.AllocationRecord.getDefaultInstance();
    }

    @java.lang.Override
    public org.tensorflow.proto.framework.AllocationRecord build() {
      org.tensorflow.proto.framework.AllocationRecord result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.tensorflow.proto.framework.AllocationRecord buildPartial() {
      org.tensorflow.proto.framework.AllocationRecord result = new org.tensorflow.proto.framework.AllocationRecord(this);
      result.allocMicros_ = allocMicros_;
      result.allocBytes_ = allocBytes_;
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
      if (other instanceof org.tensorflow.proto.framework.AllocationRecord) {
        return mergeFrom((org.tensorflow.proto.framework.AllocationRecord)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.tensorflow.proto.framework.AllocationRecord other) {
      if (other == org.tensorflow.proto.framework.AllocationRecord.getDefaultInstance()) return this;
      if (other.getAllocMicros() != 0L) {
        setAllocMicros(other.getAllocMicros());
      }
      if (other.getAllocBytes() != 0L) {
        setAllocBytes(other.getAllocBytes());
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
            case 8: {
              allocMicros_ = input.readInt64();

              break;
            } // case 8
            case 16: {
              allocBytes_ = input.readInt64();

              break;
            } // case 16
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

    private long allocMicros_ ;
    /**
     * <pre>
     * The timestamp of the operation.
     * </pre>
     *
     * <code>int64 alloc_micros = 1;</code>
     * @return The allocMicros.
     */
    @java.lang.Override
    public long getAllocMicros() {
      return allocMicros_;
    }
    /**
     * <pre>
     * The timestamp of the operation.
     * </pre>
     *
     * <code>int64 alloc_micros = 1;</code>
     * @param value The allocMicros to set.
     * @return This builder for chaining.
     */
    public Builder setAllocMicros(long value) {
      
      allocMicros_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The timestamp of the operation.
     * </pre>
     *
     * <code>int64 alloc_micros = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearAllocMicros() {
      
      allocMicros_ = 0L;
      onChanged();
      return this;
    }

    private long allocBytes_ ;
    /**
     * <pre>
     * Number of bytes allocated, or de-allocated if negative.
     * </pre>
     *
     * <code>int64 alloc_bytes = 2;</code>
     * @return The allocBytes.
     */
    @java.lang.Override
    public long getAllocBytes() {
      return allocBytes_;
    }
    /**
     * <pre>
     * Number of bytes allocated, or de-allocated if negative.
     * </pre>
     *
     * <code>int64 alloc_bytes = 2;</code>
     * @param value The allocBytes to set.
     * @return This builder for chaining.
     */
    public Builder setAllocBytes(long value) {
      
      allocBytes_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Number of bytes allocated, or de-allocated if negative.
     * </pre>
     *
     * <code>int64 alloc_bytes = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearAllocBytes() {
      
      allocBytes_ = 0L;
      onChanged();
      return this;
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


    // @@protoc_insertion_point(builder_scope:tensorflow.AllocationRecord)
  }

  // @@protoc_insertion_point(class_scope:tensorflow.AllocationRecord)
  private static final org.tensorflow.proto.framework.AllocationRecord DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.tensorflow.proto.framework.AllocationRecord();
  }

  public static org.tensorflow.proto.framework.AllocationRecord getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllocationRecord>
      PARSER = new com.google.protobuf.AbstractParser<AllocationRecord>() {
    @java.lang.Override
    public AllocationRecord parsePartialFrom(
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

  public static com.google.protobuf.Parser<AllocationRecord> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllocationRecord> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.tensorflow.proto.framework.AllocationRecord getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

