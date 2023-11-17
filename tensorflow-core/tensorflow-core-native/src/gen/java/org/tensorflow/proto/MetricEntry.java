// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tensorflow/tsl/protobuf/test_log.proto

package org.tensorflow.proto;

/**
 * Protobuf type {@code tensorflow.MetricEntry}
 */
public final class MetricEntry extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tensorflow.MetricEntry)
    MetricEntryOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MetricEntry.newBuilder() to construct.
  private MetricEntry(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MetricEntry() {
    name_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new MetricEntry();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_MetricEntry_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_MetricEntry_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.tensorflow.proto.MetricEntry.class, org.tensorflow.proto.MetricEntry.Builder.class);
  }

  public static final int NAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object name_;
  /**
   * <pre>
   * Metric name
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
   * Metric name
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

  public static final int VALUE_FIELD_NUMBER = 2;
  private double value_;
  /**
   * <pre>
   * Metric value
   * </pre>
   *
   * <code>double value = 2;</code>
   * @return The value.
   */
  @java.lang.Override
  public double getValue() {
    return value_;
  }

  public static final int MIN_VALUE_FIELD_NUMBER = 3;
  private com.google.protobuf.DoubleValue minValue_;
  /**
   * <pre>
   * The minimum acceptable value for the metric if specified
   * </pre>
   *
   * <code>.google.protobuf.DoubleValue min_value = 3;</code>
   * @return Whether the minValue field is set.
   */
  @java.lang.Override
  public boolean hasMinValue() {
    return minValue_ != null;
  }
  /**
   * <pre>
   * The minimum acceptable value for the metric if specified
   * </pre>
   *
   * <code>.google.protobuf.DoubleValue min_value = 3;</code>
   * @return The minValue.
   */
  @java.lang.Override
  public com.google.protobuf.DoubleValue getMinValue() {
    return minValue_ == null ? com.google.protobuf.DoubleValue.getDefaultInstance() : minValue_;
  }
  /**
   * <pre>
   * The minimum acceptable value for the metric if specified
   * </pre>
   *
   * <code>.google.protobuf.DoubleValue min_value = 3;</code>
   */
  @java.lang.Override
  public com.google.protobuf.DoubleValueOrBuilder getMinValueOrBuilder() {
    return getMinValue();
  }

  public static final int MAX_VALUE_FIELD_NUMBER = 4;
  private com.google.protobuf.DoubleValue maxValue_;
  /**
   * <pre>
   * The maximum acceptable value for the metric if specified
   * </pre>
   *
   * <code>.google.protobuf.DoubleValue max_value = 4;</code>
   * @return Whether the maxValue field is set.
   */
  @java.lang.Override
  public boolean hasMaxValue() {
    return maxValue_ != null;
  }
  /**
   * <pre>
   * The maximum acceptable value for the metric if specified
   * </pre>
   *
   * <code>.google.protobuf.DoubleValue max_value = 4;</code>
   * @return The maxValue.
   */
  @java.lang.Override
  public com.google.protobuf.DoubleValue getMaxValue() {
    return maxValue_ == null ? com.google.protobuf.DoubleValue.getDefaultInstance() : maxValue_;
  }
  /**
   * <pre>
   * The maximum acceptable value for the metric if specified
   * </pre>
   *
   * <code>.google.protobuf.DoubleValue max_value = 4;</code>
   */
  @java.lang.Override
  public com.google.protobuf.DoubleValueOrBuilder getMaxValueOrBuilder() {
    return getMaxValue();
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
    if (java.lang.Double.doubleToRawLongBits(value_) != 0) {
      output.writeDouble(2, value_);
    }
    if (minValue_ != null) {
      output.writeMessage(3, getMinValue());
    }
    if (maxValue_ != null) {
      output.writeMessage(4, getMaxValue());
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
    if (java.lang.Double.doubleToRawLongBits(value_) != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, value_);
    }
    if (minValue_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getMinValue());
    }
    if (maxValue_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getMaxValue());
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
    if (!(obj instanceof org.tensorflow.proto.MetricEntry)) {
      return super.equals(obj);
    }
    org.tensorflow.proto.MetricEntry other = (org.tensorflow.proto.MetricEntry) obj;

    if (!getName()
        .equals(other.getName())) return false;
    if (java.lang.Double.doubleToLongBits(getValue())
        != java.lang.Double.doubleToLongBits(
            other.getValue())) return false;
    if (hasMinValue() != other.hasMinValue()) return false;
    if (hasMinValue()) {
      if (!getMinValue()
          .equals(other.getMinValue())) return false;
    }
    if (hasMaxValue() != other.hasMaxValue()) return false;
    if (hasMaxValue()) {
      if (!getMaxValue()
          .equals(other.getMaxValue())) return false;
    }
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
    hash = (37 * hash) + VALUE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getValue()));
    if (hasMinValue()) {
      hash = (37 * hash) + MIN_VALUE_FIELD_NUMBER;
      hash = (53 * hash) + getMinValue().hashCode();
    }
    if (hasMaxValue()) {
      hash = (37 * hash) + MAX_VALUE_FIELD_NUMBER;
      hash = (53 * hash) + getMaxValue().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.tensorflow.proto.MetricEntry parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.MetricEntry parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.MetricEntry parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.MetricEntry parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.MetricEntry parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.tensorflow.proto.MetricEntry parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.tensorflow.proto.MetricEntry parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.MetricEntry parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.MetricEntry parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.MetricEntry parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.tensorflow.proto.MetricEntry parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.tensorflow.proto.MetricEntry parseFrom(
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
  public static Builder newBuilder(org.tensorflow.proto.MetricEntry prototype) {
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
   * Protobuf type {@code tensorflow.MetricEntry}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tensorflow.MetricEntry)
      org.tensorflow.proto.MetricEntryOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_MetricEntry_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_MetricEntry_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.tensorflow.proto.MetricEntry.class, org.tensorflow.proto.MetricEntry.Builder.class);
    }

    // Construct using org.tensorflow.proto.MetricEntry.newBuilder()
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

      value_ = 0D;

      if (minValueBuilder_ == null) {
        minValue_ = null;
      } else {
        minValue_ = null;
        minValueBuilder_ = null;
      }
      if (maxValueBuilder_ == null) {
        maxValue_ = null;
      } else {
        maxValue_ = null;
        maxValueBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.tensorflow.proto.TestLogProtos.internal_static_tensorflow_MetricEntry_descriptor;
    }

    @java.lang.Override
    public org.tensorflow.proto.MetricEntry getDefaultInstanceForType() {
      return org.tensorflow.proto.MetricEntry.getDefaultInstance();
    }

    @java.lang.Override
    public org.tensorflow.proto.MetricEntry build() {
      org.tensorflow.proto.MetricEntry result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.tensorflow.proto.MetricEntry buildPartial() {
      org.tensorflow.proto.MetricEntry result = new org.tensorflow.proto.MetricEntry(this);
      result.name_ = name_;
      result.value_ = value_;
      if (minValueBuilder_ == null) {
        result.minValue_ = minValue_;
      } else {
        result.minValue_ = minValueBuilder_.build();
      }
      if (maxValueBuilder_ == null) {
        result.maxValue_ = maxValue_;
      } else {
        result.maxValue_ = maxValueBuilder_.build();
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
      if (other instanceof org.tensorflow.proto.MetricEntry) {
        return mergeFrom((org.tensorflow.proto.MetricEntry)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.tensorflow.proto.MetricEntry other) {
      if (other == org.tensorflow.proto.MetricEntry.getDefaultInstance()) return this;
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (other.getValue() != 0D) {
        setValue(other.getValue());
      }
      if (other.hasMinValue()) {
        mergeMinValue(other.getMinValue());
      }
      if (other.hasMaxValue()) {
        mergeMaxValue(other.getMaxValue());
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
            case 17: {
              value_ = input.readDouble();

              break;
            } // case 17
            case 26: {
              input.readMessage(
                  getMinValueFieldBuilder().getBuilder(),
                  extensionRegistry);

              break;
            } // case 26
            case 34: {
              input.readMessage(
                  getMaxValueFieldBuilder().getBuilder(),
                  extensionRegistry);

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
     * Metric name
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
     * Metric name
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
     * Metric name
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
     * Metric name
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
     * Metric name
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

    private double value_ ;
    /**
     * <pre>
     * Metric value
     * </pre>
     *
     * <code>double value = 2;</code>
     * @return The value.
     */
    @java.lang.Override
    public double getValue() {
      return value_;
    }
    /**
     * <pre>
     * Metric value
     * </pre>
     *
     * <code>double value = 2;</code>
     * @param value The value to set.
     * @return This builder for chaining.
     */
    public Builder setValue(double value) {
      
      value_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Metric value
     * </pre>
     *
     * <code>double value = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearValue() {
      
      value_ = 0D;
      onChanged();
      return this;
    }

    private com.google.protobuf.DoubleValue minValue_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.DoubleValue, com.google.protobuf.DoubleValue.Builder, com.google.protobuf.DoubleValueOrBuilder> minValueBuilder_;
    /**
     * <pre>
     * The minimum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue min_value = 3;</code>
     * @return Whether the minValue field is set.
     */
    public boolean hasMinValue() {
      return minValueBuilder_ != null || minValue_ != null;
    }
    /**
     * <pre>
     * The minimum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue min_value = 3;</code>
     * @return The minValue.
     */
    public com.google.protobuf.DoubleValue getMinValue() {
      if (minValueBuilder_ == null) {
        return minValue_ == null ? com.google.protobuf.DoubleValue.getDefaultInstance() : minValue_;
      } else {
        return minValueBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * The minimum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue min_value = 3;</code>
     */
    public Builder setMinValue(com.google.protobuf.DoubleValue value) {
      if (minValueBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        minValue_ = value;
        onChanged();
      } else {
        minValueBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * The minimum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue min_value = 3;</code>
     */
    public Builder setMinValue(
        com.google.protobuf.DoubleValue.Builder builderForValue) {
      if (minValueBuilder_ == null) {
        minValue_ = builderForValue.build();
        onChanged();
      } else {
        minValueBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * The minimum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue min_value = 3;</code>
     */
    public Builder mergeMinValue(com.google.protobuf.DoubleValue value) {
      if (minValueBuilder_ == null) {
        if (minValue_ != null) {
          minValue_ =
            com.google.protobuf.DoubleValue.newBuilder(minValue_).mergeFrom(value).buildPartial();
        } else {
          minValue_ = value;
        }
        onChanged();
      } else {
        minValueBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * The minimum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue min_value = 3;</code>
     */
    public Builder clearMinValue() {
      if (minValueBuilder_ == null) {
        minValue_ = null;
        onChanged();
      } else {
        minValue_ = null;
        minValueBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * The minimum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue min_value = 3;</code>
     */
    public com.google.protobuf.DoubleValue.Builder getMinValueBuilder() {
      
      onChanged();
      return getMinValueFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * The minimum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue min_value = 3;</code>
     */
    public com.google.protobuf.DoubleValueOrBuilder getMinValueOrBuilder() {
      if (minValueBuilder_ != null) {
        return minValueBuilder_.getMessageOrBuilder();
      } else {
        return minValue_ == null ?
            com.google.protobuf.DoubleValue.getDefaultInstance() : minValue_;
      }
    }
    /**
     * <pre>
     * The minimum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue min_value = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.DoubleValue, com.google.protobuf.DoubleValue.Builder, com.google.protobuf.DoubleValueOrBuilder> 
        getMinValueFieldBuilder() {
      if (minValueBuilder_ == null) {
        minValueBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.DoubleValue, com.google.protobuf.DoubleValue.Builder, com.google.protobuf.DoubleValueOrBuilder>(
                getMinValue(),
                getParentForChildren(),
                isClean());
        minValue_ = null;
      }
      return minValueBuilder_;
    }

    private com.google.protobuf.DoubleValue maxValue_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.DoubleValue, com.google.protobuf.DoubleValue.Builder, com.google.protobuf.DoubleValueOrBuilder> maxValueBuilder_;
    /**
     * <pre>
     * The maximum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue max_value = 4;</code>
     * @return Whether the maxValue field is set.
     */
    public boolean hasMaxValue() {
      return maxValueBuilder_ != null || maxValue_ != null;
    }
    /**
     * <pre>
     * The maximum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue max_value = 4;</code>
     * @return The maxValue.
     */
    public com.google.protobuf.DoubleValue getMaxValue() {
      if (maxValueBuilder_ == null) {
        return maxValue_ == null ? com.google.protobuf.DoubleValue.getDefaultInstance() : maxValue_;
      } else {
        return maxValueBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * The maximum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue max_value = 4;</code>
     */
    public Builder setMaxValue(com.google.protobuf.DoubleValue value) {
      if (maxValueBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        maxValue_ = value;
        onChanged();
      } else {
        maxValueBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * The maximum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue max_value = 4;</code>
     */
    public Builder setMaxValue(
        com.google.protobuf.DoubleValue.Builder builderForValue) {
      if (maxValueBuilder_ == null) {
        maxValue_ = builderForValue.build();
        onChanged();
      } else {
        maxValueBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * The maximum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue max_value = 4;</code>
     */
    public Builder mergeMaxValue(com.google.protobuf.DoubleValue value) {
      if (maxValueBuilder_ == null) {
        if (maxValue_ != null) {
          maxValue_ =
            com.google.protobuf.DoubleValue.newBuilder(maxValue_).mergeFrom(value).buildPartial();
        } else {
          maxValue_ = value;
        }
        onChanged();
      } else {
        maxValueBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * The maximum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue max_value = 4;</code>
     */
    public Builder clearMaxValue() {
      if (maxValueBuilder_ == null) {
        maxValue_ = null;
        onChanged();
      } else {
        maxValue_ = null;
        maxValueBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * The maximum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue max_value = 4;</code>
     */
    public com.google.protobuf.DoubleValue.Builder getMaxValueBuilder() {
      
      onChanged();
      return getMaxValueFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * The maximum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue max_value = 4;</code>
     */
    public com.google.protobuf.DoubleValueOrBuilder getMaxValueOrBuilder() {
      if (maxValueBuilder_ != null) {
        return maxValueBuilder_.getMessageOrBuilder();
      } else {
        return maxValue_ == null ?
            com.google.protobuf.DoubleValue.getDefaultInstance() : maxValue_;
      }
    }
    /**
     * <pre>
     * The maximum acceptable value for the metric if specified
     * </pre>
     *
     * <code>.google.protobuf.DoubleValue max_value = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.DoubleValue, com.google.protobuf.DoubleValue.Builder, com.google.protobuf.DoubleValueOrBuilder> 
        getMaxValueFieldBuilder() {
      if (maxValueBuilder_ == null) {
        maxValueBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.DoubleValue, com.google.protobuf.DoubleValue.Builder, com.google.protobuf.DoubleValueOrBuilder>(
                getMaxValue(),
                getParentForChildren(),
                isClean());
        maxValue_ = null;
      }
      return maxValueBuilder_;
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


    // @@protoc_insertion_point(builder_scope:tensorflow.MetricEntry)
  }

  // @@protoc_insertion_point(class_scope:tensorflow.MetricEntry)
  private static final org.tensorflow.proto.MetricEntry DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.tensorflow.proto.MetricEntry();
  }

  public static org.tensorflow.proto.MetricEntry getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MetricEntry>
      PARSER = new com.google.protobuf.AbstractParser<MetricEntry>() {
    @java.lang.Override
    public MetricEntry parsePartialFrom(
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

  public static com.google.protobuf.Parser<MetricEntry> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MetricEntry> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.tensorflow.proto.MetricEntry getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

