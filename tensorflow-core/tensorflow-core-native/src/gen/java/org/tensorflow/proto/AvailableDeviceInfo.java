// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tsl/protobuf/test_log.proto

package org.tensorflow.proto;

/**
 * <pre>
 * Matches DeviceAttributes
 * </pre>
 *
 * Protobuf type {@code tensorflow.AvailableDeviceInfo}
 */
public final class AvailableDeviceInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tensorflow.AvailableDeviceInfo)
    AvailableDeviceInfoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AvailableDeviceInfo.newBuilder() to construct.
  private AvailableDeviceInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AvailableDeviceInfo() {
    name_ = "";
    type_ = "";
    physicalDescription_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new AvailableDeviceInfo();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_AvailableDeviceInfo_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_AvailableDeviceInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.tensorflow.proto.AvailableDeviceInfo.class, org.tensorflow.proto.AvailableDeviceInfo.Builder.class);
  }

  public static final int NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object name_;
  /**
   * <pre>
   * Device name.
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The name.
   */
  @java.lang.Override
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * Device name.
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TYPE_FIELD_NUMBER = 2;
  private volatile java.lang.Object type_;
  /**
   * <pre>
   * Device type, e.g. 'CPU' or 'GPU'.
   * </pre>
   *
   * <code>string type = 2;</code>
   * @return The type.
   */
  @java.lang.Override
  public java.lang.String getType() {
    java.lang.Object ref = type_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      type_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * Device type, e.g. 'CPU' or 'GPU'.
   * </pre>
   *
   * <code>string type = 2;</code>
   * @return The bytes for type.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getTypeBytes() {
    java.lang.Object ref = type_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      type_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MEMORY_LIMIT_FIELD_NUMBER = 3;
  private long memoryLimit_;
  /**
   * <pre>
   * Memory capacity in bytes.
   * </pre>
   *
   * <code>int64 memory_limit = 3;</code>
   * @return The memoryLimit.
   */
  @java.lang.Override
  public long getMemoryLimit() {
    return memoryLimit_;
  }

  public static final int PHYSICAL_DESCRIPTION_FIELD_NUMBER = 4;
  private volatile java.lang.Object physicalDescription_;
  /**
   * <pre>
   * The physical description of this device.
   * </pre>
   *
   * <code>string physical_description = 4;</code>
   * @return The physicalDescription.
   */
  @java.lang.Override
  public java.lang.String getPhysicalDescription() {
    java.lang.Object ref = physicalDescription_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      physicalDescription_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * The physical description of this device.
   * </pre>
   *
   * <code>string physical_description = 4;</code>
   * @return The bytes for physicalDescription.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getPhysicalDescriptionBytes() {
    java.lang.Object ref = physicalDescription_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      physicalDescription_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, name_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(type_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, type_);
    }
    if (memoryLimit_ != 0L) {
      output.writeInt64(3, memoryLimit_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(physicalDescription_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, physicalDescription_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, name_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(type_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, type_);
    }
    if (memoryLimit_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, memoryLimit_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(physicalDescription_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, physicalDescription_);
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
    if (!(obj instanceof org.tensorflow.proto.AvailableDeviceInfo)) {
      return super.equals(obj);
    }
    org.tensorflow.proto.AvailableDeviceInfo other = (org.tensorflow.proto.AvailableDeviceInfo) obj;

    if (!getName()
        .equals(other.getName())) return false;
    if (!getType()
        .equals(other.getType())) return false;
    if (getMemoryLimit()
        != other.getMemoryLimit()) return false;
    if (!getPhysicalDescription()
        .equals(other.getPhysicalDescription())) return false;
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
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType().hashCode();
    hash = (37 * hash) + MEMORY_LIMIT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getMemoryLimit());
    hash = (37 * hash) + PHYSICAL_DESCRIPTION_FIELD_NUMBER;
    hash = (53 * hash) + getPhysicalDescription().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.tensorflow.proto.AvailableDeviceInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.AvailableDeviceInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.AvailableDeviceInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.AvailableDeviceInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.AvailableDeviceInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.AvailableDeviceInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.AvailableDeviceInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.AvailableDeviceInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.AvailableDeviceInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.AvailableDeviceInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.AvailableDeviceInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.AvailableDeviceInfo parseFrom(
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
  public static Builder newBuilder(org.tensorflow.proto.AvailableDeviceInfo prototype) {
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
   * Matches DeviceAttributes
   * </pre>
   *
   * Protobuf type {@code tensorflow.AvailableDeviceInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tensorflow.AvailableDeviceInfo)
      org.tensorflow.proto.AvailableDeviceInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_AvailableDeviceInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_AvailableDeviceInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.tensorflow.proto.AvailableDeviceInfo.class, org.tensorflow.proto.AvailableDeviceInfo.Builder.class);
    }

    // Construct using org.tensorflow.proto.AvailableDeviceInfo.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      name_ = "";

      type_ = "";

      memoryLimit_ = 0L;

      physicalDescription_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_AvailableDeviceInfo_descriptor;
    }

    @java.lang.Override
    public org.tensorflow.proto.AvailableDeviceInfo getDefaultInstanceForType() {
      return org.tensorflow.proto.AvailableDeviceInfo.getDefaultInstance();
    }

    @java.lang.Override
    public org.tensorflow.proto.AvailableDeviceInfo build() {
      org.tensorflow.proto.AvailableDeviceInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.tensorflow.proto.AvailableDeviceInfo buildPartial() {
      org.tensorflow.proto.AvailableDeviceInfo result = new org.tensorflow.proto.AvailableDeviceInfo(this);
      result.name_ = name_;
      result.type_ = type_;
      result.memoryLimit_ = memoryLimit_;
      result.physicalDescription_ = physicalDescription_;
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
      if (other instanceof org.tensorflow.proto.AvailableDeviceInfo) {
        return mergeFrom((org.tensorflow.proto.AvailableDeviceInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.tensorflow.proto.AvailableDeviceInfo other) {
      if (other == org.tensorflow.proto.AvailableDeviceInfo.getDefaultInstance()) return this;
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (!other.getType().isEmpty()) {
        type_ = other.type_;
        onChanged();
      }
      if (other.getMemoryLimit() != 0L) {
        setMemoryLimit(other.getMemoryLimit());
      }
      if (!other.getPhysicalDescription().isEmpty()) {
        physicalDescription_ = other.physicalDescription_;
        onChanged();
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
              name_ = input.readStringRequireUtf8();

              break;
            } // case 10
            case 18: {
              type_ = input.readStringRequireUtf8();

              break;
            } // case 18
            case 24: {
              memoryLimit_ = input.readInt64();

              break;
            } // case 24
            case 34: {
              physicalDescription_ = input.readStringRequireUtf8();

              break;
            } // case 34
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

    private java.lang.Object name_ = "";
    /**
     * <pre>
     * Device name.
     * </pre>
     *
     * <code>string name = 1;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * Device name.
     * </pre>
     *
     * <code>string name = 1;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * Device name.
     * </pre>
     *
     * <code>string name = 1;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Device name.
     * </pre>
     *
     * <code>string name = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Device name.
     * </pre>
     *
     * <code>string name = 1;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object type_ = "";
    /**
     * <pre>
     * Device type, e.g. 'CPU' or 'GPU'.
     * </pre>
     *
     * <code>string type = 2;</code>
     * @return The type.
     */
    public java.lang.String getType() {
      java.lang.Object ref = type_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        type_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * Device type, e.g. 'CPU' or 'GPU'.
     * </pre>
     *
     * <code>string type = 2;</code>
     * @return The bytes for type.
     */
    public com.google.protobuf.ByteString
        getTypeBytes() {
      java.lang.Object ref = type_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        type_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * Device type, e.g. 'CPU' or 'GPU'.
     * </pre>
     *
     * <code>string type = 2;</code>
     * @param value The type to set.
     * @return This builder for chaining.
     */
    public Builder setType(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Device type, e.g. 'CPU' or 'GPU'.
     * </pre>
     *
     * <code>string type = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearType() {
      
      type_ = getDefaultInstance().getType();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Device type, e.g. 'CPU' or 'GPU'.
     * </pre>
     *
     * <code>string type = 2;</code>
     * @param value The bytes for type to set.
     * @return This builder for chaining.
     */
    public Builder setTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      type_ = value;
      onChanged();
      return this;
    }

    private long memoryLimit_ ;
    /**
     * <pre>
     * Memory capacity in bytes.
     * </pre>
     *
     * <code>int64 memory_limit = 3;</code>
     * @return The memoryLimit.
     */
    @java.lang.Override
    public long getMemoryLimit() {
      return memoryLimit_;
    }
    /**
     * <pre>
     * Memory capacity in bytes.
     * </pre>
     *
     * <code>int64 memory_limit = 3;</code>
     * @param value The memoryLimit to set.
     * @return This builder for chaining.
     */
    public Builder setMemoryLimit(long value) {
      
      memoryLimit_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Memory capacity in bytes.
     * </pre>
     *
     * <code>int64 memory_limit = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearMemoryLimit() {
      
      memoryLimit_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object physicalDescription_ = "";
    /**
     * <pre>
     * The physical description of this device.
     * </pre>
     *
     * <code>string physical_description = 4;</code>
     * @return The physicalDescription.
     */
    public java.lang.String getPhysicalDescription() {
      java.lang.Object ref = physicalDescription_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        physicalDescription_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * The physical description of this device.
     * </pre>
     *
     * <code>string physical_description = 4;</code>
     * @return The bytes for physicalDescription.
     */
    public com.google.protobuf.ByteString
        getPhysicalDescriptionBytes() {
      java.lang.Object ref = physicalDescription_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        physicalDescription_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * The physical description of this device.
     * </pre>
     *
     * <code>string physical_description = 4;</code>
     * @param value The physicalDescription to set.
     * @return This builder for chaining.
     */
    public Builder setPhysicalDescription(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      physicalDescription_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The physical description of this device.
     * </pre>
     *
     * <code>string physical_description = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearPhysicalDescription() {
      
      physicalDescription_ = getDefaultInstance().getPhysicalDescription();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The physical description of this device.
     * </pre>
     *
     * <code>string physical_description = 4;</code>
     * @param value The bytes for physicalDescription to set.
     * @return This builder for chaining.
     */
    public Builder setPhysicalDescriptionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      physicalDescription_ = value;
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


    // @@protoc_insertion_point(builder_scope:tensorflow.AvailableDeviceInfo)
  }

  // @@protoc_insertion_point(class_scope:tensorflow.AvailableDeviceInfo)
  private static final org.tensorflow.proto.AvailableDeviceInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.tensorflow.proto.AvailableDeviceInfo();
  }

  public static org.tensorflow.proto.AvailableDeviceInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AvailableDeviceInfo>
      PARSER = new com.google.protobuf.AbstractParser<AvailableDeviceInfo>() {
    @java.lang.Override
    public AvailableDeviceInfo parsePartialFrom(
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

  public static com.google.protobuf.Parser<AvailableDeviceInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AvailableDeviceInfo> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.tensorflow.proto.AvailableDeviceInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

