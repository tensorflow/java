// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/core/protobuf/config.proto

package org.tensorflow.proto;

/**
 * <pre>
 * Defines a connection between two tensors in a `GraphDef`.
 * </pre>
 *
 * Protobuf type {@code tensorflow.TensorConnection}
 */
public final class TensorConnection extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tensorflow.TensorConnection)
    TensorConnectionOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TensorConnection.newBuilder() to construct.
  private TensorConnection(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TensorConnection() {
    fromTensor_ = "";
    toTensor_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new TensorConnection();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.tensorflow.proto.ConfigProtos.internal_static_tensorflow_TensorConnection_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.tensorflow.proto.ConfigProtos.internal_static_tensorflow_TensorConnection_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.tensorflow.proto.TensorConnection.class, org.tensorflow.proto.TensorConnection.Builder.class);
  }

  public static final int FROM_TENSOR_FIELD_NUMBER = 1;
  private volatile java.lang.Object fromTensor_;
  /**
   * <pre>
   * A tensor name. The value of this tensor will be substituted for
   * the tensor named in `to_tensor`.
   * </pre>
   *
   * <code>string from_tensor = 1;</code>
   * @return The fromTensor.
   */
  @java.lang.Override
  public java.lang.String getFromTensor() {
    java.lang.Object ref = fromTensor_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      fromTensor_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * A tensor name. The value of this tensor will be substituted for
   * the tensor named in `to_tensor`.
   * </pre>
   *
   * <code>string from_tensor = 1;</code>
   * @return The bytes for fromTensor.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getFromTensorBytes() {
    java.lang.Object ref = fromTensor_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      fromTensor_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TO_TENSOR_FIELD_NUMBER = 2;
  private volatile java.lang.Object toTensor_;
  /**
   * <pre>
   * A tensor name. The value of this tensor will be bound to the
   * value of the tensor named in `from_tensor`.
   * </pre>
   *
   * <code>string to_tensor = 2;</code>
   * @return The toTensor.
   */
  @java.lang.Override
  public java.lang.String getToTensor() {
    java.lang.Object ref = toTensor_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      toTensor_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * A tensor name. The value of this tensor will be bound to the
   * value of the tensor named in `from_tensor`.
   * </pre>
   *
   * <code>string to_tensor = 2;</code>
   * @return The bytes for toTensor.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getToTensorBytes() {
    java.lang.Object ref = toTensor_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      toTensor_ = b;
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(fromTensor_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, fromTensor_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(toTensor_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, toTensor_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(fromTensor_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, fromTensor_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(toTensor_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, toTensor_);
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
    if (!(obj instanceof org.tensorflow.proto.TensorConnection)) {
      return super.equals(obj);
    }
    org.tensorflow.proto.TensorConnection other = (org.tensorflow.proto.TensorConnection) obj;

    if (!getFromTensor()
        .equals(other.getFromTensor())) return false;
    if (!getToTensor()
        .equals(other.getToTensor())) return false;
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
    hash = (37 * hash) + FROM_TENSOR_FIELD_NUMBER;
    hash = (53 * hash) + getFromTensor().hashCode();
    hash = (37 * hash) + TO_TENSOR_FIELD_NUMBER;
    hash = (53 * hash) + getToTensor().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.tensorflow.proto.TensorConnection parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.TensorConnection parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.TensorConnection parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.TensorConnection parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.TensorConnection parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.TensorConnection parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.TensorConnection parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.TensorConnection parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.TensorConnection parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.TensorConnection parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.TensorConnection parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.TensorConnection parseFrom(
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
  public static Builder newBuilder(org.tensorflow.proto.TensorConnection prototype) {
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
   * Defines a connection between two tensors in a `GraphDef`.
   * </pre>
   *
   * Protobuf type {@code tensorflow.TensorConnection}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tensorflow.TensorConnection)
      org.tensorflow.proto.TensorConnectionOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.tensorflow.proto.ConfigProtos.internal_static_tensorflow_TensorConnection_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.tensorflow.proto.ConfigProtos.internal_static_tensorflow_TensorConnection_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.tensorflow.proto.TensorConnection.class, org.tensorflow.proto.TensorConnection.Builder.class);
    }

    // Construct using org.tensorflow.proto.TensorConnection.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      fromTensor_ = "";

      toTensor_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.tensorflow.proto.ConfigProtos.internal_static_tensorflow_TensorConnection_descriptor;
    }

    @java.lang.Override
    public org.tensorflow.proto.TensorConnection getDefaultInstanceForType() {
      return org.tensorflow.proto.TensorConnection.getDefaultInstance();
    }

    @java.lang.Override
    public org.tensorflow.proto.TensorConnection build() {
      org.tensorflow.proto.TensorConnection result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.tensorflow.proto.TensorConnection buildPartial() {
      org.tensorflow.proto.TensorConnection result = new org.tensorflow.proto.TensorConnection(this);
      result.fromTensor_ = fromTensor_;
      result.toTensor_ = toTensor_;
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
      if (other instanceof org.tensorflow.proto.TensorConnection) {
        return mergeFrom((org.tensorflow.proto.TensorConnection)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.tensorflow.proto.TensorConnection other) {
      if (other == org.tensorflow.proto.TensorConnection.getDefaultInstance()) return this;
      if (!other.getFromTensor().isEmpty()) {
        fromTensor_ = other.fromTensor_;
        onChanged();
      }
      if (!other.getToTensor().isEmpty()) {
        toTensor_ = other.toTensor_;
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
              fromTensor_ = input.readStringRequireUtf8();

              break;
            } // case 10
            case 18: {
              toTensor_ = input.readStringRequireUtf8();

              break;
            } // case 18
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

    private java.lang.Object fromTensor_ = "";
    /**
     * <pre>
     * A tensor name. The value of this tensor will be substituted for
     * the tensor named in `to_tensor`.
     * </pre>
     *
     * <code>string from_tensor = 1;</code>
     * @return The fromTensor.
     */
    public java.lang.String getFromTensor() {
      java.lang.Object ref = fromTensor_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        fromTensor_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * A tensor name. The value of this tensor will be substituted for
     * the tensor named in `to_tensor`.
     * </pre>
     *
     * <code>string from_tensor = 1;</code>
     * @return The bytes for fromTensor.
     */
    public com.google.protobuf.ByteString
        getFromTensorBytes() {
      java.lang.Object ref = fromTensor_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        fromTensor_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * A tensor name. The value of this tensor will be substituted for
     * the tensor named in `to_tensor`.
     * </pre>
     *
     * <code>string from_tensor = 1;</code>
     * @param value The fromTensor to set.
     * @return This builder for chaining.
     */
    public Builder setFromTensor(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      fromTensor_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * A tensor name. The value of this tensor will be substituted for
     * the tensor named in `to_tensor`.
     * </pre>
     *
     * <code>string from_tensor = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearFromTensor() {
      
      fromTensor_ = getDefaultInstance().getFromTensor();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * A tensor name. The value of this tensor will be substituted for
     * the tensor named in `to_tensor`.
     * </pre>
     *
     * <code>string from_tensor = 1;</code>
     * @param value The bytes for fromTensor to set.
     * @return This builder for chaining.
     */
    public Builder setFromTensorBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      fromTensor_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object toTensor_ = "";
    /**
     * <pre>
     * A tensor name. The value of this tensor will be bound to the
     * value of the tensor named in `from_tensor`.
     * </pre>
     *
     * <code>string to_tensor = 2;</code>
     * @return The toTensor.
     */
    public java.lang.String getToTensor() {
      java.lang.Object ref = toTensor_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        toTensor_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * A tensor name. The value of this tensor will be bound to the
     * value of the tensor named in `from_tensor`.
     * </pre>
     *
     * <code>string to_tensor = 2;</code>
     * @return The bytes for toTensor.
     */
    public com.google.protobuf.ByteString
        getToTensorBytes() {
      java.lang.Object ref = toTensor_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        toTensor_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * A tensor name. The value of this tensor will be bound to the
     * value of the tensor named in `from_tensor`.
     * </pre>
     *
     * <code>string to_tensor = 2;</code>
     * @param value The toTensor to set.
     * @return This builder for chaining.
     */
    public Builder setToTensor(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      toTensor_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * A tensor name. The value of this tensor will be bound to the
     * value of the tensor named in `from_tensor`.
     * </pre>
     *
     * <code>string to_tensor = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearToTensor() {
      
      toTensor_ = getDefaultInstance().getToTensor();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * A tensor name. The value of this tensor will be bound to the
     * value of the tensor named in `from_tensor`.
     * </pre>
     *
     * <code>string to_tensor = 2;</code>
     * @param value The bytes for toTensor to set.
     * @return This builder for chaining.
     */
    public Builder setToTensorBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      toTensor_ = value;
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


    // @@protoc_insertion_point(builder_scope:tensorflow.TensorConnection)
  }

  // @@protoc_insertion_point(class_scope:tensorflow.TensorConnection)
  private static final org.tensorflow.proto.TensorConnection DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.tensorflow.proto.TensorConnection();
  }

  public static org.tensorflow.proto.TensorConnection getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TensorConnection>
      PARSER = new com.google.protobuf.AbstractParser<TensorConnection>() {
    @java.lang.Override
    public TensorConnection parsePartialFrom(
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

  public static com.google.protobuf.Parser<TensorConnection> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TensorConnection> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.tensorflow.proto.TensorConnection getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

